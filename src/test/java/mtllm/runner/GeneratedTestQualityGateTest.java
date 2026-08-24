package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneratedTestQualityGateTest {

    @TempDir
    Path repoRoot;

    @Test
    void acceptsDistinctScenarioTestsThatReachAssertionHelper() throws Exception {
        PromptConfig config = config();
        Path generated = write("""
                import org.junit.jupiter.api.Test;
                import static org.junit.jupiter.api.Assertions.assertEquals;
                public class GeneratedExampleTest {
                    @Test void thresholdBoundaryBelow() { verify(9); }
                    @Test void thresholdBoundaryExact() { verify(10); }
                    private void verify(int source) { assertEquals(source, source); }
                }
                """);

        assertNull(GeneratedTestQualityGate.validate(generated, config));
    }

    @Test
    void rejectsDuplicateBodies() throws Exception {
        PromptConfig config = config();
        Path generated = write("""
                import org.junit.jupiter.api.Test;
                import static org.junit.jupiter.api.Assertions.assertEquals;
                public class GeneratedExampleTest {
                    @Test void thresholdBoundaryBelow() { assertEquals(1, 1); }
                    @Test void thresholdBoundaryExact() { assertEquals(1, 1); }
                }
                """);

        assertTrue(GeneratedTestQualityGate.validate(generated, config).contains("Duplicate"));
    }

    @Test
    void rejectsMissingScenarioCoverage() throws Exception {
        PromptConfig config = config();
        Path generated = write("""
                import org.junit.jupiter.api.Test;
                import static org.junit.jupiter.api.Assertions.assertEquals;
                public class GeneratedExampleTest {
                    @Test void unrelatedCaseOne() { assertEquals(1, 1); }
                    @Test void unrelatedCaseTwo() { assertEquals(2, 2); }
                }
                """);

        assertTrue(GeneratedTestQualityGate.validate(generated, config).contains("THRESHOLD_BOUNDARY"));
        GeneratedTestQualityGate.ValidationResult detailed =
                GeneratedTestQualityGate.validateDetailed(generated, config);
        assertTrue(detailed.onlyMissingScenarios());
        assertEquals(1, detailed.missingScenarios().size());
        assertEquals(2, detailed.missingScenarios().get(0).needed());
    }

    @Test
    void rejectsAnEmptyRequiredSuite() throws Exception {
        PromptConfig config = config();
        Path generated = write("public class GeneratedExampleTest {}\n");

        assertTrue(GeneratedTestQualityGate.validate(generated, config).contains("no JUnit @Test"));
    }

    private PromptConfig config() throws Exception {
        Files.writeString(repoRoot.resolve("ExampleSut.java"), "public final class ExampleSut {}\n");
        Path prompt = repoRoot.resolve("prompt.yaml");
        Files.writeString(prompt, """
                SUTClassFile: ExampleSut.java
                Count: 2
                GeneratedClassName: GeneratedExampleTest
                InputGenerator: LLM
                MRProvider: LLM
                InputDomain:
                  scenarios:
                    - id: THRESHOLD_BOUNDARY
                      description: Values around threshold.
                      targetCases: 2
                """);
        return PromptConfigLoader.load(prompt, repoRoot);
    }

    private Path write(String source) throws Exception {
        Path generated = repoRoot.resolve("GeneratedExampleTest.java");
        Files.writeString(generated, source);
        return generated;
    }
}
