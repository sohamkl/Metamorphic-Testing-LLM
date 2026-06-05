package mtllm;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import mtllm.llm.LlmClient;
import mtllm.llm.OpenAiClient;
import mtllm.runner.DataGeneratorRunner;
import mtllm.runner.GeneratedTestRunner;
import mtllm.runner.RepairLoop;
import mtllm.runner.TestRunResult;
import mtllm.sut.SutContext;
import mtllm.sut.SutContextLoader;
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
            Path promptPath = args.length > 0 ? Path.of(args[0]).toAbsolutePath().normalize() : repoRoot.resolve("prompt.txt");

            Map<String, String> env = DotEnv.load(repoRoot.resolve(".env"));
            String apiKey = DotEnv.firstNonBlank(System.getenv("OPENAI_API_KEY"), env.get("OPENAI_API_KEY"));
            if (apiKey.isBlank()) {
                throw new IllegalStateException("Missing OPENAI_API_KEY. Put it in .env or the environment.");
            }

            String model = DotEnv.firstNonBlank(System.getenv("OPENAI_MODEL"), env.get("OPENAI_MODEL"), "gpt-4o-mini");
            String baseUrl = DotEnv.firstNonBlank(
                    System.getenv("OPENAI_BASE_URL"),
                    env.get("OPENAI_BASE_URL"),
                    "https://api.openai.com/v1");
            String junitJar = DotEnv.firstNonBlank(
                    System.getenv("JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR"),
                    env.get("JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR"),
                    System.getenv("JUNIT_JAR"),
                    env.get("JUNIT_JAR"));
            String mavenCommand = DotEnv.firstNonBlank(
                    System.getenv("MAVEN_CMD"),
                    env.get("MAVEN_CMD"),
                    "mvn");

            PromptConfig config = PromptConfigLoader.load(promptPath, repoRoot);
            SutContext sutContext = SutContextLoader.load(config, repoRoot);
            LlmClient llmClient = new OpenAiClient(apiKey, model, baseUrl);
            GeneratedTestRunner testRunner = new GeneratedTestRunner(
                    repoRoot,
                    repoRoot.resolve("generated-tests/classes"),
                    junitJar,
                    mavenCommand);
            DataGeneratorRunner dataGeneratorRunner = new DataGeneratorRunner(
                    repoRoot,
                    repoRoot.resolve("generated-code/classes"),
                    repoRoot.resolve("generated-data"));
            RepairLoop repairLoop = new RepairLoop(
                    llmClient,
                    testRunner,
                    dataGeneratorRunner,
                    repoRoot.resolve("generated-tests"),
                    repoRoot.resolve("generated-code"));

            TestRunResult result = repairLoop.generateRunAndRepair(config, sutContext);
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
}
