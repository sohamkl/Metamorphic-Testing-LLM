package mtllm.randoop;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
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
}
