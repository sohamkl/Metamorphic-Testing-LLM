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

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private final List<UnsatisfiableScenarioDetector.Retired> retiredScenarios = new ArrayList<>();

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

    /** One write-run cycle, after any unsatisfiable scenarios have been retired. */
    private record Attempt(String code, Path file, TestRunResult result) {
    }

    /**
     * Writes and runs the suite, and if it failed because the inferred domain planned something the
     * SUT or developer MR refuses, drops those tests and runs again.
     *
     * <p>Called after <em>every</em> run, not just the first. A static failure such as a duplicate
     * test body stops the suite ever executing, so the precondition errors only become visible once
     * a repair has cleared the earlier problem. Retiring only after the first run would miss them.</p>
     */
    private Attempt runWithRetirement(String code, PromptConfig config, SutContext sutContext)
            throws Exception {
        Path file = writeGeneratedFile(config, code);
        TestRunResult result = runGeneratedFile(file, config, sutContext);
        if (result.failed()) {
            String pruned = retireUnsatisfiableScenarios(code, config, result.output());
            if (pruned != null) {
                code = pruned;
                file = writeGeneratedFile(config, code);
                result = runGeneratedFile(file, config, sutContext);
            }
        }
        return new Attempt(code, file, result);
    }

    /** Scenarios retired this run, for reporting. Empty is the normal, healthy case. */
    public List<UnsatisfiableScenarioDetector.Retired> retiredScenarios() {
        return List.copyOf(retiredScenarios);
    }

    /**
     * Deletes the tests for any scenario the inferred domain planned but the SUT or developer MR
     * refuses, and stops the gate demanding them back.
     *
     * <p>Without this an impossible scenario is a permanent obligation: the gate reports it missing,
     * the loop asks the model to add it, the model adds it, it throws again, and the repair budget
     * drains. Retiring it lets the rest of the suite stand, and the exclusion is reported rather
     * than hidden so a run that discards ten scenarios is visibly weaker than one that discards
     * none.</p>
     *
     * @return the suite with those tests removed, or {@code null} when nothing qualifies
     */
    private String retireUnsatisfiableScenarios(String code, PromptConfig config, String runOutput) {
        List<UnsatisfiableScenarioDetector.Retired> found =
                UnsatisfiableScenarioDetector.detect(runOutput, config);
        if (found.isEmpty()) {
            return null;
        }
        Set<String> methodsToDrop = UnsatisfiableScenarioDetector.testMethods(found);
        String pruned;
        try {
            CompilationUnit unit = StaticJavaParser.parse(code);
            List<MethodDeclaration> doomed = unit.findAll(MethodDeclaration.class).stream()
                    .filter(method -> methodsToDrop.contains(method.getNameAsString()))
                    .toList();
            if (doomed.isEmpty()) {
                return null;
            }
            doomed.forEach(MethodDeclaration::remove);
            pruned = unit.toString();
        } catch (RuntimeException unparsable) {
            // A suite we cannot parse is the repair loop's problem, not ours.
            return null;
        }
        retiredScenarios.addAll(found);
        testRunner.retireScenarios(UnsatisfiableScenarioDetector.scenarioIds(found));
        for (UnsatisfiableScenarioDetector.Retired retired : found) {
            System.out.println("Retired unsatisfiable scenario " + retired.describe());
        }
        System.out.println("Retired " + found.size() + " scenario(s) the SUT or developer MR rejects; "
                + "re-running the remaining suite.");
        return pruned;
    }

    private TestRunResult generateSingleOutput(PromptConfig config, SutContext sutContext) throws Exception {
        String code = llmClient.complete(PromptBuilder.buildInitialPrompt(config, sutContext));

        Attempt attempt = runWithRetirement(code, config, sutContext);
        code = attempt.code();
        Path generatedFile = attempt.file();
        TestRunResult result = attempt.result();

        int attempts = 0;
        String additiveBaseCode = null;
        String previousAddition = "";
        Map<String, Integer> additiveMissing = null;
        while (result.failed() && attempts < config.maxRepairAttempts()) {
            attempts++;
            GeneratedTestQualityGate.ValidationResult quality = testRunner.lastQualityResult();
            if (config.mode().generatesJUnit()
                    && (additiveBaseCode != null || quality.onlyMissingScenarios())) {
                if (additiveBaseCode == null) {
                    additiveBaseCode = code;
                    additiveMissing = new LinkedHashMap<>();
                    for (GeneratedTestQualityGate.MissingScenario scenario : quality.missingScenarios()) {
                        additiveMissing.put(scenario.id(), scenario.needed());
                    }
                }
                System.out.println("Generated suite is missing scenario coverage. Requesting additive repair attempt "
                        + attempts + "...");
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
                System.out.println("Generated code failed. Requesting repair attempt " + attempts + "...");
                code = llmClient.complete(PromptBuilder.buildRepairPrompt(config, sutContext, code, result));
            }
            attempt = runWithRetirement(code, config, sutContext);
            code = attempt.code();
            generatedFile = attempt.file();
            result = attempt.result();
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
