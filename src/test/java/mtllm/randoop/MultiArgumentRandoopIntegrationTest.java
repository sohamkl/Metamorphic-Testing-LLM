package mtllm.randoop;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import mtllm.sut.ProjectDiscovery;
import mtllm.util.DotEnv;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiArgumentRandoopIntegrationTest {
    @TempDir
    Path tempDir;

    /**
     * Resolves the Maven command the same way App does. A bare "mvn" can never launch on
     * Windows, because CreateProcess only ever appends ".exe" and never ".cmd", so the
     * MAVEN_CMD override in .env has to be honoured here too.
     */
    private static String mavenCommand(Path repoRoot) {
        return DotEnv.firstNonBlank(
                System.getenv("MAVEN_CMD"),
                DotEnv.load(repoRoot.resolve(".env")).get("MAVEN_CMD"),
                "mvn");
    }

    @Test
    @Timeout(value = 45, unit = TimeUnit.SECONDS)
    @EnabledIfEnvironmentVariable(named = "MTLLM_RUN_RANDOOP_INTEGRATION", matches = "true")
    void harvestsTypedCasesForTwoArgumentSut() throws Exception {
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path prompt = tempDir.resolve("prompt.yaml");
        Files.writeString(prompt, """
                SUTClassFile: src/test/java/mtllm/randoop/fixture/MultiArgumentSut.java
                TargetFunction: public static int combine(int left, int right)
                MR: Repeating the operation preserves the result.
                MRProvider: LLM
                InputGenerator: NEW_HYBRID
                Count: 5
                OutputRoot: %s
                """.formatted(tempDir));
        PromptConfig config = PromptConfigLoader.load(prompt, repoRoot);
        RandoopInputRunner runner = new RandoopInputRunner(
                repoRoot,
                repoRoot.resolve("lib/randoop-all-4.3.4.jar"),
                repoRoot.resolve("target/classes"),
                tempDir.resolve("randoop-work"));

        String seeds = runner.generateSeedExamples(config, prompt);

        assertFalse(seeds.equals("[]"));
        assertTrue(seeds.contains("MtllmGeneratedMultiArgumentSutCombineInvocation"));
        assertTrue(seeds.contains("\"arg0\"") && seeds.contains("\"arg1\""));
        assertTrue(Files.isDirectory(tempDir.resolve("generated-support")));
    }

    @Test
    @Timeout(value = 45, unit = TimeUnit.SECONDS)
    @EnabledIfEnvironmentVariable(named = "MTLLM_RUN_RANDOOP_INTEGRATION", matches = "true")
    void evaluatesDeveloperMrAndCompilesGeneratedSuiteForTwoArguments() throws Exception {
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path prompt = tempDir.resolve("prompt-dev.yaml");
        Files.writeString(prompt, """
                SUTClassFile: src/test/java/mtllm/randoop/fixture/MultiArgumentSut.java
                TargetFunction: public static int combine(int left, int right)
                MR: Increasing both arguments by one increases the output by two.
                MRProvider: DEV
                DeveloperMrFile: src/test/java/mtllm/randoop/fixture/MultiArgumentSpec.java
                DeveloperFollowUpMethod: increaseBoth
                DeveloperAssertMethod: assertRelation
                InputGenerator: RANDOOP
                Count: 5
                JsonRequired: true
                TestSuiteRequired: true
                OutputRoot: %s
                GeneratedClassName: GeneratedMultiArgumentMetamorphicTest
                """.formatted(tempDir));
        PromptConfig config = PromptConfigLoader.load(prompt, repoRoot);
        RandoopInputRunner runner = new RandoopInputRunner(
                repoRoot,
                repoRoot.resolve("lib/randoop-all-4.3.4.jar"),
                repoRoot.resolve("target/classes"),
                tempDir.resolve("randoop-dev-work"));

        RandoopInputRunner.GenerationResult result = runner.generate(config, prompt);

        assertFalse(result.json().equals("[]"));
        assertTrue(result.json().contains("\"passed\":true"));
        assertTrue(result.suiteEmitted());
        assertTrue(result.suiteCompiled(), result.suiteCompileOutput());
        assertTrue(Files.readString(result.passingFile()).contains(".generateFollowUp("));
    }

    @Test
    @Timeout(value = 45, unit = TimeUnit.SECONDS)
    @EnabledIfEnvironmentVariable(named = "MTLLM_RUN_RANDOOP_INTEGRATION", matches = "true")
    void harvestsReceiverAndArgumentForSpatial4jInstanceMethod() throws Exception {
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path prompt = repoRoot.resolve("examples/spatial4j/prompt.yaml");
        PromptConfig config = ProjectDiscovery.enrichClasspath(
                PromptConfigLoader.load(prompt, repoRoot), mavenCommand(repoRoot));
        RandoopInputRunner runner = new RandoopInputRunner(
                repoRoot,
                repoRoot.resolve("lib/randoop-all-4.3.4.jar"),
                repoRoot.resolve("target/classes"),
                tempDir.resolve("spatial4j-randoop-work"));

        String seeds = runner.generateSeedExamples(config, prompt);

        assertFalse(seeds.equals("[]"));
        assertTrue(seeds.contains("MtllmGeneratedCircleImplRelateInvocation"));
        assertTrue(seeds.contains("\"receiver\"") && seeds.contains("\"arg0\""));
    }

    @Test
    @Timeout(value = 60, unit = TimeUnit.SECONDS)
    @EnabledIfEnvironmentVariable(named = "MTLLM_RUN_RANDOOP_INTEGRATION", matches = "true")
    void synthesizesNonNullNodeFilterSeedsForJsoup() throws Exception {
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path prompt = repoRoot.resolve("examples/jsoup/mt-testing/prompt1.yaml");
        PromptConfig config = ProjectDiscovery.enrichClasspath(
                PromptConfigLoader.load(prompt, repoRoot), mavenCommand(repoRoot));
        RandoopInputRunner runner = new RandoopInputRunner(
                repoRoot,
                repoRoot.resolve("lib/randoop-all-4.3.4.jar"),
                repoRoot.resolve("target/classes"),
                tempDir.resolve("jsoup-callback-randoop-work"));

        String seeds = runner.generateSeedExamples(config, prompt);

        assertFalse(seeds.equals("[]"));
        assertFalse(seeds.contains("\"arg0\":null"));
        assertFalse(seeds.contains("\"arg1\":null"));
        assertTrue(seeds.contains("MtllmGeneratedNodeFilterCallback"), seeds);
    }
}
