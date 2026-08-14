package mtllm;

import mtllm.config.GenerationMode;
import mtllm.config.InputGenerator;
import mtllm.config.MRProvider;
import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import mtllm.llm.LlmClient;
import mtllm.llm.OpenAiClient;
import mtllm.randoop.RandoopInputRunner;
import mtllm.runner.DataGeneratorRunner;
import mtllm.runner.GeneratedTestRunner;
import mtllm.runner.RepairLoop;
import mtllm.runner.TestRunResult;
import mtllm.sut.SutContext;
import mtllm.sut.SutContextLoader;
import mtllm.sut.ProjectDiscovery;
import mtllm.util.DotEnv;

import java.nio.file.Path;
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
        try {
            Path repoRoot = Path.of("").toAbsolutePath().normalize();
            Path promptPath = args.length > 0
                    ? Path.of(args[0]).toAbsolutePath().normalize()
                    : repoRoot.resolve("prompt.yaml");

            Map<String, String> env = DotEnv.load(repoRoot.resolve(".env"));
            PromptConfig config = PromptConfigLoader.load(promptPath, repoRoot);
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
                LlmClient llmClient = new OpenAiClient(apiKey, model, baseUrl);
                RepairLoop repairLoop = new RepairLoop(
                        llmClient,
                        testRunner,
                        dataGeneratorRunner,
                        outputRoot.resolve("junit-tests"),
                        outputRoot.resolve("data-generator-code"));
                result = repairLoop.generateRunAndRepair(harvestedConfig, harvestedContext);
            } else if (config.inputGenerator().randoopSeedsLlm()) {
                RandoopInputRunner seedRunner = new RandoopInputRunner(
                        repoRoot,
                        repoRoot.resolve("lib/randoop-all-4.3.4.jar"),
                        repoRoot.resolve("target/classes"),
                        config.outputRoot().resolve("new-hybrid-randoop"));
                System.out.println("Generating API-grounded seed examples with Randoop (NEW_HYBRID)...");
                String seedExamples = seedRunner.generateSeedExamples(config, promptPath);
                System.out.println("Randoop seed examples harvested; asking the LLM to generate final source inputs...");

                PromptConfig groundedConfig = config.withRandoopSeedExamples(seedExamples);
                SutContext groundedContext = SutContextLoader.load(groundedConfig, repoRoot);
                LlmClient llmClient = new OpenAiClient(apiKey, model, baseUrl);
                RepairLoop repairLoop = new RepairLoop(
                        llmClient,
                        testRunner,
                        dataGeneratorRunner,
                        outputRoot.resolve("junit-tests"),
                        outputRoot.resolve("data-generator-code"));
                result = repairLoop.generateRunAndRepair(groundedConfig, groundedContext);
            } else if (config.inputGenerator().usesRandoop()) {
                result = runRandoop(config, sutContext, repoRoot, promptPath, dataGeneratorRunner);
            } else {
                LlmClient llmClient = new OpenAiClient(apiKey, model, baseUrl);
                RepairLoop repairLoop = new RepairLoop(
                        llmClient,
                        testRunner,
                        dataGeneratorRunner,
                        outputRoot.resolve("junit-tests"),
                        outputRoot.resolve("data-generator-code"));
                result = repairLoop.generateRunAndRepair(config, sutContext);
            }
            System.out.println("\n--- Result: " + result.status() + " ---");
            if (!result.output().isBlank()) {
                System.out.println(result.output());
            }
        } catch (Exception e) {
            System.err.println("Runner failed: " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
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
        String baseName = stripGeneratedSuffix(config.generatedClassName());
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

    private static String stripGeneratedSuffix(String name) {
        if (name.endsWith("Data")) {
            return name.substring(0, name.length() - "Data".length());
        }
        if (name.endsWith("Test")) {
            return name.substring(0, name.length() - "Test".length());
        }
        return name;
    }
}
