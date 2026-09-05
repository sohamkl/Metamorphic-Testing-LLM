package mtllm;

import mtllm.config.GenerationMode;
import mtllm.config.InputGenerator;
import mtllm.config.MRProvider;
import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import mtllm.domain.InputDomainInferenceService;
import mtllm.llm.LlmClient;
import mtllm.llm.OpenAiClient;
import mtllm.llm.TokenUsage;
import mtllm.randoop.RandoopInputRunner;
import mtllm.runner.DataGeneratorRunner;
import mtllm.runner.GeneratedTestRunner;
import mtllm.runner.RepairLoop;
import mtllm.runner.TestRunResult;
import mtllm.sut.SutContext;
import mtllm.sut.SutContextLoader;
import mtllm.sut.ProjectDiscovery;
import mtllm.util.DotEnv;
import mtllm.util.GeneratedNames;
import mtllm.util.JsonUtil;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;

/**
 * Starts the prototype from the command line and wires the backend pieces together.
 *
 * <p>In simple terms, this is the main controller: it reads configuration, loads the SUT,
 * calls the LLM, writes the generated JUnit test, and optionally runs/repairs it.</p>
 */
public final class App {
    private App() {
    }

    public static void main(String[] args) {
        long startNanos = System.nanoTime();
        try {
            Path repoRoot = Path.of("").toAbsolutePath().normalize();
            Path promptPath = args.length > 0
                    ? Path.of(args[0]).toAbsolutePath().normalize()
                    : repoRoot.resolve("prompt.yaml");

            Map<String, String> env = DotEnv.load(repoRoot.resolve(".env"));
            PromptConfig config = PromptConfigLoader.load(promptPath, repoRoot);
            InputGenerator requestedInputGenerator = config.inputGenerator();
            String effectiveExecutionTag = "STANDARD";
            String randoopSeedStatus = "NOT_APPLICABLE";
            String model = DotEnv.firstNonBlank(System.getenv("OPENAI_MODEL"), env.get("OPENAI_MODEL"), "gpt-4o-mini");
            String baseUrl = DotEnv.firstNonBlank(
                    System.getenv("OPENAI_BASE_URL"),
                    env.get("OPENAI_BASE_URL"),
                    "https://api.openai.com/v1");
            String apiKey = DotEnv.firstNonBlank(
                    System.getenv("OPENAI_API_KEY_OPENAI"),
                    env.get("OPENAI_API_KEY_OPENAI"));
            boolean needsApiKey = !config.inputGenerator().usesRandoop()
                    || config.inputGenerator().seedsWithLlm();
            if (needsApiKey && apiKey.isBlank()) {
                throw new IllegalStateException(
                        "Missing OPENAI_API_KEY_OPENAI. Put it in .env or the environment.");
            }
            String junitJar = DotEnv.firstNonBlank(
                    System.getenv("JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR"),
                    env.get("JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR"),
                    System.getenv("JUNIT_JAR"),
                    env.get("JUNIT_JAR"));
            String mavenCommand = DotEnv.firstNonBlank(
                    System.getenv("MAVEN_CMD"),
                    env.get("MAVEN_CMD"),
                    "mvn");

            config = ProjectDiscovery.enrichClasspath(config, mavenCommand);
            SutContext sutContext = SutContextLoader.load(config, repoRoot);
            LlmClient llmClient = needsApiKey ? new OpenAiClient(apiKey, model, baseUrl) : null;
            if (needsApiKey && config.inputDomainRequirements().isEmpty()) {
                System.out.println("No InputDomain supplied; inferring a grounded structured domain...");
                InputDomainInferenceService.InferenceResult inference =
                        new InputDomainInferenceService(llmClient).infer(config, sutContext);
                config = config.withInputDomainRequirements(inference.requirements());
                System.out.println("Wrote inferred input domain to " + inference.artifact());
            }
            Path outputRoot = config.outputRoot();
            GeneratedTestRunner testRunner = new GeneratedTestRunner(
                    repoRoot,
                    outputRoot.resolve("junit-tests/classes"),
                    outputRoot.resolve("junit-support"),
                    junitJar,
                    mavenCommand);
            DataGeneratorRunner dataGeneratorRunner = new DataGeneratorRunner(
                    repoRoot,
                    outputRoot.resolve("data-generator-code/classes"),
                    outputRoot.resolve("json-data"),
                    outputRoot.resolve("reports"));

            TestRunResult result;
            int repairAttempts = 0;
            int additiveRepairAttempts = 0;
            if (config.inputGenerator() == InputGenerator.HYBRID
                    && config.mrProvider() == MRProvider.LLM) {
                RandoopInputRunner sourceRunner = new RandoopInputRunner(
                        repoRoot,
                        repoRoot.resolve("lib/randoop-all-4.3.4.jar"),
                        repoRoot.resolve("target/classes"),
                        config.outputRoot().resolve("hybrid-randoop"));
                System.out.println("Generating final source inputs with LLM-seeded Randoop (HYBRID)...");
                String sourceExamples = sourceRunner.generateLlmSeededSourceExamples(config, promptPath);
                if (sourceExamples.isBlank() || sourceExamples.equals("[]")) {
                    throw new IllegalStateException(
                            "HYBRID generated no executable source inputs for the LLM MR stage.");
                }
                System.out.println("Randoop source inputs harvested; asking the LLM to generate the MR tests...");

                PromptConfig harvestedConfig = config.withRandoopSeedExamples(sourceExamples);
                SutContext harvestedContext = SutContextLoader.load(harvestedConfig, repoRoot);
                RepairLoop repairLoop = new RepairLoop(
                        llmClient,
                        testRunner,
                        dataGeneratorRunner,
                        outputRoot.resolve("junit-tests"),
                        outputRoot.resolve("data-generator-code"));
                result = repairLoop.generateRunAndRepair(harvestedConfig, harvestedContext);
                repairAttempts = repairLoop.lastRepairAttempts();
                additiveRepairAttempts = repairLoop.lastAdditiveRepairAttempts();
            } else if (config.inputGenerator().randoopSeedsLlm()) {
                RandoopInputRunner seedRunner = new RandoopInputRunner(
                        repoRoot,
                        repoRoot.resolve("lib/randoop-all-4.3.4.jar"),
                        repoRoot.resolve("target/classes"),
                        config.outputRoot().resolve("new-hybrid-randoop"));
                System.out.println("Generating API-grounded seed examples with Randoop (NEW_HYBRID)...");
                String seedExamples = "";
                String seedFailure = "";
                try {
                    seedExamples = seedRunner.generateSeedExamples(config, promptPath);
                } catch (InterruptedException failure) {
                    Thread.currentThread().interrupt();
                    throw failure;
                } catch (Exception failure) {
                    seedFailure = failure.getMessage() == null
                            ? failure.getClass().getSimpleName()
                            : failure.getMessage();
                    randoopSeedStatus = "ERROR";
                }
                if (hasNoRandoopSeeds(seedExamples)) {
                    if (!randoopSeedStatus.equals("ERROR")) {
                        randoopSeedStatus = "EMPTY";
                    }
                    effectiveExecutionTag = randoopSeedStatus.equals("ERROR")
                            ? "NEW_HYBRID_FALLBACK_LLM_SEED_ERROR"
                            : "NEW_HYBRID_FALLBACK_LLM_NO_SEEDS";
                    System.err.println("[INPUT_GENERATOR_FALLBACK] requested=NEW_HYBRID effective=LLM"
                            + " seedStatus=" + randoopSeedStatus
                            + (seedFailure.isBlank() ? "" : " reason=" + seedFailure.replace('\n', ' ')));

                    config = config.withInputGenerator(InputGenerator.LLM);
                    SutContext fallbackContext = SutContextLoader.load(config, repoRoot);
                    RepairLoop repairLoop = new RepairLoop(
                            llmClient,
                            testRunner,
                            dataGeneratorRunner,
                            outputRoot.resolve("junit-tests"),
                            outputRoot.resolve("data-generator-code"));
                    result = repairLoop.generateRunAndRepair(config, fallbackContext);
                    repairAttempts = repairLoop.lastRepairAttempts();
                    additiveRepairAttempts = repairLoop.lastAdditiveRepairAttempts();
                } else {
                    randoopSeedStatus = "GENERATED";
                    effectiveExecutionTag = "NEW_HYBRID_WITH_RANDOOP_SEEDS";
                    System.out.println("Randoop seed examples harvested; asking the LLM to generate final source inputs...");

                    PromptConfig groundedConfig = config.withRandoopSeedExamples(seedExamples);
                    SutContext groundedContext = SutContextLoader.load(groundedConfig, repoRoot);
                    RepairLoop repairLoop = new RepairLoop(
                            llmClient,
                            testRunner,
                            dataGeneratorRunner,
                            outputRoot.resolve("junit-tests"),
                            outputRoot.resolve("data-generator-code"));
                    result = repairLoop.generateRunAndRepair(groundedConfig, groundedContext);
                    repairAttempts = repairLoop.lastRepairAttempts();
                    additiveRepairAttempts = repairLoop.lastAdditiveRepairAttempts();
                }
            } else if (config.inputGenerator().usesRandoop()) {
                result = runRandoop(config, sutContext, repoRoot, promptPath, dataGeneratorRunner);
            } else {
                RepairLoop repairLoop = new RepairLoop(
                        llmClient,
                        testRunner,
                        dataGeneratorRunner,
                        outputRoot.resolve("junit-tests"),
                        outputRoot.resolve("data-generator-code"));
                result = repairLoop.generateRunAndRepair(config, sutContext);
                repairAttempts = repairLoop.lastRepairAttempts();
                additiveRepairAttempts = repairLoop.lastAdditiveRepairAttempts();
            }
            System.out.println("\n--- Result: " + result.status() + " ---");
            if (!result.output().isBlank()) {
                System.out.println(result.output());
            }
            writeRunMetrics(outputRoot, config, String.valueOf(result.status()),
                    requestedInputGenerator, effectiveExecutionTag, randoopSeedStatus,
                    llmClient == null ? TokenUsage.EMPTY : llmClient.tokenUsage(), startNanos,
                    repairAttempts, additiveRepairAttempts);
        } catch (Exception e) {
            System.err.println("Runner failed: " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    /**
     * Records what the run cost, so the input-generation arms can be compared on more than
     * mutation score. Tokens cover every call including repairs; RANDOOP reports 0 because it
     * never builds an LLM client. Failing to write metrics must not fail the run.
     */
    private static void writeRunMetrics(
            Path outputRoot, PromptConfig config, String status, InputGenerator requestedInputGenerator,
            String effectiveExecutionTag, String randoopSeedStatus, TokenUsage usage, long startNanos,
            int repairAttempts, int additiveRepairAttempts) {
        double elapsedSeconds = (System.nanoTime() - startNanos) / 1_000_000_000.0;
        // Locale.ROOT so a comma-decimal locale cannot emit "32,4" and break the JSON.
        String elapsed = String.format(Locale.ROOT, "%.1f", elapsedSeconds);
        System.out.println("Run took " + elapsed + "s and used " + usage.totalTokens()
                + " tokens (" + usage.promptTokens() + " in, " + usage.completionTokens()
                + " out, " + usage.reasoningTokens() + " reasoning).");
        try {
            Files.createDirectories(outputRoot);
            String json = "{\n"
                    + "  \"dataset\": " + JsonUtil.quote(String.valueOf(outputRoot.getFileName())) + ",\n"
                    + "  \"inputGenerator\": " + JsonUtil.quote(requestedInputGenerator.name()) + ",\n"
                    + "  \"requestedInputGenerator\": " + JsonUtil.quote(requestedInputGenerator.name()) + ",\n"
                    + "  \"effectiveInputGenerator\": " + JsonUtil.quote(config.inputGenerator().name()) + ",\n"
                    + "  \"executionTag\": " + JsonUtil.quote(effectiveExecutionTag) + ",\n"
                    + "  \"randoopSeedStatus\": " + JsonUtil.quote(randoopSeedStatus) + ",\n"
                    + "  \"mrProvider\": " + JsonUtil.quote(config.mrProvider().name()) + ",\n"
                    + "  \"status\": " + JsonUtil.quote(status) + ",\n"
                    + "  \"promptTokens\": " + usage.promptTokens() + ",\n"
                    + "  \"completionTokens\": " + usage.completionTokens() + ",\n"
                    + "  \"reasoningTokens\": " + usage.reasoningTokens() + ",\n"
                    + "  \"totalTokens\": " + usage.totalTokens() + ",\n"
                    + "  \"repairAttempts\": " + repairAttempts + ",\n"
                    + "  \"additiveRepairAttempts\": " + additiveRepairAttempts + ",\n"
                    + "  \"totalRepairAttempts\": " + (repairAttempts + additiveRepairAttempts) + ",\n"
                    + "  \"elapsedSeconds\": " + elapsed + "\n"
                    + "}\n";
            Path artifact = outputRoot.resolve("metrics.json");
            Files.writeString(artifact, json, StandardCharsets.UTF_8);
            System.out.println("Wrote run metrics to " + artifact);
        } catch (Exception e) {
            System.err.println("Could not write run metrics: " + e.getMessage());
        }
    }

    static boolean hasNoRandoopSeeds(String seedExamples) {
        return seedExamples == null || seedExamples.isBlank()
                || seedExamples.replaceAll("\\s", "").equals("[]");
    }

    /**
     * Randoop input-generation path (phase 1: JSON data). Harvests source inputs with Randoop in a
     * subprocess, then runs the shared split/report seam. Requires MRProvider: DEV.
     */
    private static TestRunResult runRandoop(
            PromptConfig config, SutContext sutContext, Path repoRoot, Path promptPath,
            DataGeneratorRunner dataGeneratorRunner) throws Exception {
        if (config.mrProvider() != MRProvider.DEV) {
            return TestRunResult.failed("InputGenerator " + config.inputGenerator()
                    + " requires MRProvider: DEV -- Randoop applies the developer-owned MR in-process; "
                    + "there is no LLM-written oracle in this path.");
        }

        // Derive the executed-data sub-config so writeSplitAndReport runs the passing/failing split
        // + report (the combined BOTH mode does not, by itself, generate executed MT data).
        String baseName = GeneratedNames.baseName(config.generatedClassName());
        PromptConfig dataConfig = config.withOutputMode(
                GenerationMode.DEVELOPER_MR_DATA, true, false, baseName + "Data");

        RandoopInputRunner runner = new RandoopInputRunner(
                repoRoot,
                repoRoot.resolve("lib/randoop-all-4.3.4.jar"),
                repoRoot.resolve("target/classes"),
                config.outputRoot().resolve("randoop"));

        System.out.println("Generating source inputs with Randoop (" + config.inputGenerator() + ")...");
        RandoopInputRunner.GenerationResult generation = runner.generate(config, promptPath);
        TestRunResult result = dataGeneratorRunner.writeSplitAndReport(generation.json(), dataConfig, sutContext);

        if (generation.suiteEmitted()) {
            System.out.println("Wrote Randoop object JUnit suite:");
            System.out.println("  passing -> " + generation.passingFile());
            System.out.println("  failing -> " + generation.failingFile());
            if (generation.suiteCompiled()) {
                System.out.println("  suite compile-gate: PASSED");
            } else {
                System.out.println("  suite compile-gate: FAILED/skipped\n" + generation.suiteCompileOutput());
            }
        }
        return result;
    }

}
