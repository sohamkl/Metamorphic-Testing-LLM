package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.config.GenerationMode;
import mtllm.generation.DataBackedJUnitWriter;
import mtllm.generation.GeneratedJavaWriter;
import mtllm.generation.GeneratedJUnitCallQualifier;
import mtllm.llm.LlmClient;
import mtllm.prompt.PromptBuilder;
import mtllm.report.HtmlReportWriter;
import mtllm.sut.SutContext;
import mtllm.util.GeneratedNames;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

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
        String baseName = GeneratedNames.baseName(config.generatedClassName());
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

        DataGeneratorRunner.ExecutedDataSummary dataSummary = dataGeneratorRunner.lastSummary();
        if (!dataSummary.present()) {
            return TestRunResult.failed("JSON output generation passed, but no executed data summary was available.");
        }

        Files.deleteIfExists(generatedTestsDir.resolve(baseName + "Data.java"));
        Path generatedJunitFile = DataBackedJUnitWriter.write(
                generatedTestsDir,
                junitConfig,
                dataConfig.generatedClassName(),
                dataSummary);
        System.out.println("Wrote data-backed JUnit test to " + generatedJunitFile);

        TestRunResult junitResult = runGeneratedFile(generatedJunitFile, junitConfig, sutContext);
        if (junitResult.failed()) {
            return TestRunResult.failed("JSON output generation passed, but data-backed JUnit generation failed.\n\n"
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
        int repairAttempts = 0;
        int additiveAttempts = 0;
        String additiveBaseCode = null;
        String previousAddition = "";
        Map<String, Integer> additiveMissing = null;
        while (result.failed()) {
            GeneratedTestQualityGate.ValidationResult quality = testRunner.lastQualityResult();
            if (config.mode().generatesJUnit() && quality.onlyMissingScenarios()) {
                if (additiveAttempts >= config.maxRepairAttempts()) {
                    break;
                }
                additiveAttempts++;
                if (additiveBaseCode == null) {
                    additiveBaseCode = code;
                    additiveMissing = new LinkedHashMap<>();
                    for (GeneratedTestQualityGate.MissingScenario scenario : quality.missingScenarios()) {
                        additiveMissing.put(scenario.id(), scenario.needed());
                    }
                }
                System.out.println("Generated suite is missing scenario coverage. Requesting additive repair attempt "
                        + additiveAttempts + "...");
                String addition = llmClient.complete(PromptBuilder.buildMissingScenarioRepairPrompt(
                        config,
                        sutContext,
                        additiveBaseCode,
                        additiveMissing,
                        previousAddition,
                        result));
                try {
                    code = GeneratedJUnitScenarioMerger.merge(
                            additiveBaseCode, addition, config.generatedClassName(), additiveMissing);
                    previousAddition = addition;
                } catch (RuntimeException mergeFailure) {
                    previousAddition = addition;
                    result = TestRunResult.failed(
                            "Missing-scenario addition could not be merged: " + mergeFailure.getMessage());
                    continue;
                }
            } else {
                if (repairAttempts >= config.maxRepairAttempts()) {
                    break;
                }
                repairAttempts++;
                // Compilation/runtime repair applies to the current merged suite, not the pre-addition base.
                additiveBaseCode = null;
                additiveMissing = null;
                previousAddition = "";
                System.out.println("Generated code failed. Requesting repair attempt " + repairAttempts + "...");
                code = llmClient.complete(PromptBuilder.buildRepairPrompt(config, sutContext, code, result));
            }
            generatedFile = writeGeneratedFile(config, code);
            result = runGeneratedFile(generatedFile, config, sutContext);
        }
        if (result.failed() && additiveBaseCode != null) {
            writeGeneratedFile(config, additiveBaseCode);
            return TestRunResult.failed(result.output()
                    + "\n\nAdditive repair attempts were exhausted; the original generated suite was retained unchanged.");
        }
        return result;
    }

    private Path writeGeneratedFile(PromptConfig config, String code) throws Exception {
        Path generatedFile;
        if (config.mode().generatesJUnit()) {
            code = GeneratedJUnitCallQualifier.qualifyDeveloperMrCalls(code, config);
            generatedFile = GeneratedJavaWriter.write(generatedTestsDir, config.generatedClassName(), code);
            System.out.println("Wrote generated JUnit test to " + generatedFile);
        } else {
            generatedFile = GeneratedJavaWriter.write(generatedCodeDir, config.generatedClassName(), code);
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
