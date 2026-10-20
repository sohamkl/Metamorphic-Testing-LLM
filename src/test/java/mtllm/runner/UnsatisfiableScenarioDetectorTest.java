package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Fixtures are the real stack traces from the ta4j SlopeChangeSwingDetector run of 08 Sep, where an
 * inferred domain planned two scenarios that the SUT and the developer MR both refuse.
 */
class UnsatisfiableScenarioDetectorTest {

    @TempDir
    Path repoRoot;

    /** Thrown by the developer MR spec, i.e. by a contract the generated code cannot change. */
    private static final String THROWN_IN_DEVELOPER_MR = """
            [ERROR] GeneratedExampleTest.EMPTY_SERIES_SENTINEL_variation1 -- Time elapsed: 0.002 s <<< ERROR!
            java.lang.IllegalArgumentException: Price-translation MR requires a source series whose begin index is zero
            \tat mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(SlopeChangeSwingDetectorMetamorphicSpec.java:35)
            \tat GeneratedExampleTest.exercise(GeneratedExampleTest.java:524)
            \tat GeneratedExampleTest.EMPTY_SERIES_SENTINEL_variation1(GeneratedExampleTest.java:23)
            \tat java.base/java.lang.reflect.Method.invoke(Method.java:565)
            """;

    /** Thrown by the SUT's own argument validation, also outside the generated class. */
    private static final String THROWN_IN_SUT = """
            [ERROR] GeneratedExampleTest.UNIT_WINDOW_variation1 -- Time elapsed: 0.008 s <<< ERROR!
            java.lang.IllegalArgumentException: window must be at least 2
            \tat org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig.<init>(SlopeChangeConfig.java:28)
            \tat org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector.<init>(SlopeChangeSwingDetector.java:46)
            \tat GeneratedExampleTest.UNIT_WINDOW_variation1(GeneratedExampleTest.java:52)
            \tat java.base/java.lang.reflect.Method.invoke(Method.java:565)
            """;

    @Test
    void retiresScenarioWhenPreconditionIsThrownByTheDeveloperMr() throws Exception {
        List<UnsatisfiableScenarioDetector.Retired> retired =
                UnsatisfiableScenarioDetector.detect(THROWN_IN_DEVELOPER_MR, config());

        assertEquals(1, retired.size());
        assertEquals("EMPTY_SERIES_SENTINEL", retired.get(0).scenarioId());
        assertEquals("EMPTY_SERIES_SENTINEL_variation1", retired.get(0).testMethod());
        assertEquals("IllegalArgumentException", retired.get(0).exception());
        assertTrue(retired.get(0).describe().contains("begin index is zero"));
    }

    @Test
    void retiresScenarioWhenPreconditionIsThrownByTheSut() throws Exception {
        List<UnsatisfiableScenarioDetector.Retired> retired =
                UnsatisfiableScenarioDetector.detect(THROWN_IN_SUT, config());

        assertEquals(1, retired.size());
        assertEquals("UNIT_WINDOW", retired.get(0).scenarioId());
    }

    @Test
    void findsEveryUnsatisfiableScenarioInOneRun() throws Exception {
        List<UnsatisfiableScenarioDetector.Retired> retired = UnsatisfiableScenarioDetector.detect(
                THROWN_IN_DEVELOPER_MR + THROWN_IN_SUT, config());

        assertEquals(2, retired.size());
        assertEquals(
                java.util.Set.of("EMPTY_SERIES_SENTINEL", "UNIT_WINDOW"),
                UnsatisfiableScenarioDetector.scenarioIds(retired));
    }

    /** A throw from inside the generated class is bad codegen, which is the repair loop's job. */
    @Test
    void ignoresExceptionThrownInsideTheGeneratedClass() throws Exception {
        String generatedBug = """
                [ERROR] GeneratedExampleTest.EMPTY_SERIES_SENTINEL_variation1 -- <<< ERROR!
                java.lang.NullPointerException: series is null
                \tat GeneratedExampleTest.buildSeries(GeneratedExampleTest.java:100)
                \tat GeneratedExampleTest.EMPTY_SERIES_SENTINEL_variation1(GeneratedExampleTest.java:23)
                """;

        assertTrue(UnsatisfiableScenarioDetector.detect(generatedBug, config()).isEmpty());
    }

    /** A scenario the model simply never wrote must keep being demanded, not retired. */
    @Test
    void ignoresScenarioThatIsMerelyAbsent() throws Exception {
        String cleanRun = "[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0\n";

        assertTrue(UnsatisfiableScenarioDetector.detect(cleanRun, config()).isEmpty());
    }

    /** An unstructured domain has no scenarios to retire. */
    @Test
    void ignoresRunsWithoutAStructuredDomain() throws Exception {
        Files.writeString(repoRoot.resolve("ExampleSut.java"), "public final class ExampleSut {}\n");
        Path prompt = repoRoot.resolve("plain.yaml");
        Files.writeString(prompt, """
                SUTClassFile: ExampleSut.java
                Count: 2
                GeneratedClassName: GeneratedExampleTest
                InputGenerator: LLM
                MRProvider: LLM
                """);
        PromptConfig plain = PromptConfigLoader.load(prompt, repoRoot);

        assertTrue(UnsatisfiableScenarioDetector.detect(THROWN_IN_DEVELOPER_MR, plain).isEmpty());
    }

    private PromptConfig config() throws Exception {
        Files.writeString(repoRoot.resolve("ExampleSut.java"), "public final class ExampleSut {}\n");
        Path prompt = repoRoot.resolve("prompt.yaml");
        Files.writeString(prompt, """
                SUTClassFile: ExampleSut.java
                Count: 3
                GeneratedClassName: GeneratedExampleTest
                InputGenerator: LLM
                MRProvider: LLM
                InputDomain:
                  scenarios:
                    - id: EMPTY_SERIES_SENTINEL
                      description: Detection on an empty series.
                      targetCases: 1
                    - id: UNIT_WINDOW
                      description: Window of one.
                      targetCases: 1
                    - id: THRESHOLD_BOUNDARY
                      description: Values around threshold.
                      targetCases: 1
                """);
        return PromptConfigLoader.load(prompt, repoRoot);
    }
}
