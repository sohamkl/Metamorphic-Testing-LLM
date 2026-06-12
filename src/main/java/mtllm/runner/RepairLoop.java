package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.config.GenerationMode;
import mtllm.generation.GeneratedCodeWriter;
import mtllm.generation.GeneratedTestWriter;
import mtllm.llm.LlmClient;
import mtllm.prompt.PromptBuilder;
import mtllm.report.HtmlReportWriter;
import mtllm.sut.SutContext;

import java.nio.file.Path;
import java.nio.file.Files;

/**
 * Coordinates generation, validation, and optional repair of the generated test.
 *
 * <p>In simple terms, this class asks the LLM for a JUnit test, tries to compile/run it, and
 * sends errors back to the LLM for a limited number of fixes.</p>
 */
public final class RepairLoop {
    private final LlmClient llmClient;
    private final GeneratedTestRunner testRunner;
    private final DataGeneratorRunner dataGeneratorRunner;
    private final Path generatedTestsDir;
    private final Path generatedCodeDir;

    public RepairLoop(
            LlmClient llmClient,
            GeneratedTestRunner testRunner,
            DataGeneratorRunner dataGeneratorRunner,
            Path generatedTestsDir,
            Path generatedCodeDir) {
        this.llmClient = llmClient;
        this.testRunner = testRunner;
        this.dataGeneratorRunner = dataGeneratorRunner;
        this.generatedTestsDir = generatedTestsDir;
        this.generatedCodeDir = generatedCodeDir;
    }

    public TestRunResult generateRunAndRepair(PromptConfig config, SutContext sutContext) throws Exception {
        if (config.mode().generatesBothOutputs()) {
            return generateBothOutputs(config, sutContext);
        }
        return generateSingleOutput(config, sutContext);
    }

    private TestRunResult generateBothOutputs(PromptConfig config, SutContext sutContext) throws Exception {
        String baseName = baseName(config.generatedClassName());
        PromptConfig dataConfig = config.withOutputMode(
                config.mode() == GenerationMode.DEVELOPER_MR_BOTH
                        ? GenerationMode.DEVELOPER_MR_DATA
                        : GenerationMode.INPUTS_AND_FOLLOWUP,
                true,
                false,
                baseName + "Data");
        PromptConfig junitConfig = config.withOutputMode(
                config.mode() == GenerationMode.DEVELOPER_MR_BOTH
                        ? GenerationMode.DEVELOPER_MR_JUNIT
                        : GenerationMode.FULL_JUNIT,
                false,
                true,
                baseName + "Test");

        TestRunResult dataResult = generateSingleOutput(dataConfig, sutContext);
        if (dataResult.failed()) {
            return TestRunResult.failed("JSON output generation failed. JUnit generation was not run.\n\n"
                    + dataResult.output());
        }

        Files.deleteIfExists(generatedTestsDir.resolve(baseName + "Data.java"));
        TestRunResult junitResult = generateSingleOutput(junitConfig, sutContext);
        if (junitResult.failed()) {
            return TestRunResult.failed("JSON output generation passed, but JUnit generation failed.\n\n"
                    + "JSON result:\n" + dataResult.output()
                    + "\n\nJUnit result:\n" + junitResult.output());
        }

        String reportUpdate = refreshReportWithTestMethods(config, sutContext);
        return TestRunResult.passed("Generated both requested outputs.\n\n"
                + "JSON result:\n" + dataResult.output()
                + "\n\nJUnit result:\n" + junitResult.output()
                + reportUpdate);
    }

    private String refreshReportWithTestMethods(PromptConfig config, SutContext sutContext) throws Exception {
        DataGeneratorRunner.ExecutedDataSummary dataSummary = dataGeneratorRunner.lastSummary();
        ActualResultTestSplitter.SplitResult testSummary = testRunner.lastSplitResult();
        if (!dataSummary.present() || testSummary == null) {
            return "";
        }

        Path reportFile = HtmlReportWriter.writeExecutedDataReport(
                dataSummary.reportFile().getParent(),
                config,
                sutContext,
                dataSummary.allEntries(),
                dataSummary.passingEntries(),
                dataSummary.failingEntries(),
                dataSummary.fullJsonFile(),
                dataSummary.passingJsonFile(),
                dataSummary.failingJsonFile(),
                testSummary.passingMethodNames(),
                testSummary.failingMethodNames());
        return "\n\nUpdated HTML report with generated JUnit test method names: " + reportFile;
    }

    private TestRunResult generateSingleOutput(PromptConfig config, SutContext sutContext) throws Exception {
        String code = llmClient.complete(PromptBuilder.buildInitialPrompt(config, sutContext));
        Path generatedFile = writeGeneratedFile(config, code);

        TestRunResult result = runGeneratedFile(generatedFile, config, sutContext);
        int attempts = 0;
        while (result.failed() && attempts < config.maxRepairAttempts()) {
            attempts++;
            System.out.println("Generated code failed. Requesting repair attempt " + attempts + "...");
            code = llmClient.complete(PromptBuilder.buildRepairPrompt(config, sutContext, code, result));
            generatedFile = writeGeneratedFile(config, code);
            result = runGeneratedFile(generatedFile, config, sutContext);
        }
        return result;
    }

    private String baseName(String generatedClassName) {
        if (generatedClassName.endsWith("Data")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Data".length());
        }
        if (generatedClassName.endsWith("Test")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Test".length());
        }
        return generatedClassName;
    }

    private Path writeGeneratedFile(PromptConfig config, String code) throws Exception {
        Path generatedFile;
        if (config.mode().generatesJUnit()) {
            generatedFile = GeneratedTestWriter.write(generatedTestsDir, config.generatedClassName(), code);
            System.out.println("Wrote generated JUnit test to " + generatedFile);
        } else {
            generatedFile = GeneratedCodeWriter.write(generatedCodeDir, config.generatedClassName(), code);
            System.out.println("Wrote generated data-generator code to " + generatedFile);
        }
        return generatedFile;
    }

    private TestRunResult runGeneratedFile(Path generatedFile, PromptConfig config, SutContext sutContext) throws Exception {
        if (config.mode().generatesJUnit()) {
            return testRunner.compileAndRun(generatedFile, config, sutContext);
        }
        return dataGeneratorRunner.compileRunAndValidate(generatedFile, config, sutContext);
    }
}
