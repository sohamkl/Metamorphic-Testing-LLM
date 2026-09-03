package mtllm.domain;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import mtllm.llm.LlmClient;
import mtllm.sut.SutContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputDomainInferenceServiceTest {

    @TempDir
    Path repoRoot;

    @Test
    void repairsInvalidYamlAndPersistsValidatedDomain() throws Exception {
        PromptConfig config = configWithoutInputDomain();
        QueueClient client = new QueueClient(
                "not: [valid",
                """
                InputDomain:
                  summary: Exercise threshold behavior.
                  globalConstraints:
                    - source is finite
                  diversity:
                    magnitude: [below, exact, above]
                  scenarios:
                    - id: THRESHOLD_BOUNDARY
                      category: BOUNDARY
                      description: Exercise values around the threshold.
                      preconditions:
                        - source is finite
                      expectedSourceBehavior:
                        - execution returns normally
                      targetCases: 3
                      emptyOutputAllowed: false
                """);
        SutContext context = new SutContext(
                config.sutClassFile(), Files.readString(config.sutClassFile()), List.of(),
                "Target parameters: int source");

        InputDomainInferenceService.InferenceResult result =
                new InputDomainInferenceService(client).infer(config, context);

        assertEquals(2, client.calls);
        assertEquals("Exercise threshold behavior.", result.requirements().summary());
        assertEquals(1, result.requirements().scenarios().size());
        assertTrue(Files.readString(result.artifact()).contains("THRESHOLD_BOUNDARY"));
    }

    @Test
    void inferencePromptIncludesGroundingAndCountLimit() throws Exception {
        PromptConfig config = configWithoutInputDomain();
        SutContext context = new SutContext(
                config.sutClassFile(), Files.readString(config.sutClassFile()), List.of(),
                "Construction path: ExampleSut.run(int)");

        String prompt = InputDomainInferenceService.buildPrompt(config, context);

        assertTrue(prompt.contains("Count is a budget of source cases"));
        assertTrue(prompt.contains("targetCases must total no more than"));
        assertTrue(prompt.contains("Construction path: ExampleSut.run(int)"));
        assertTrue(prompt.contains("public static int run"));
        assertTrue(prompt.contains("Do not invent constructors"));
    }

    @Test
    void normalizesInferredScenarioTargetsThatExceedCount() throws Exception {
        PromptConfig config = configWithoutInputDomain();
        QueueClient client = new QueueClient("""
                InputDomain:
                  summary: Cover both paths.
                  scenarios:
                    - id: BELOW_THRESHOLD
                      category: NORMAL
                      description: Value below the threshold.
                      targetCases: 3
                    - id: ABOVE_THRESHOLD
                      category: NORMAL
                      description: Value above the threshold.
                      targetCases: 3
                """);
        SutContext context = new SutContext(
                config.sutClassFile(), Files.readString(config.sutClassFile()), List.of(), "");

        InputDomainInferenceService.InferenceResult result =
                new InputDomainInferenceService(client).infer(config, context);

        assertEquals(1, client.calls);
        assertEquals(2, result.requirements().scenarios().size());
        assertEquals(4, result.requirements().scenarios().stream()
                .mapToInt(scenario -> scenario.targetCases())
                .sum());
        assertTrue(result.requirements().scenarios().stream()
                .allMatch(scenario -> scenario.targetCases() >= 1));
    }

    private PromptConfig configWithoutInputDomain() throws Exception {
        Files.writeString(repoRoot.resolve("ExampleSut.java"), """
                public final class ExampleSut {
                    public static int run(int source) {
                        return source > 10 ? 1 : 0;
                    }
                }
                """);
        Path prompt = repoRoot.resolve("prompt.yaml");
        Files.writeString(prompt, """
                SUTClassFile: ExampleSut.java
                TargetFunction: public static int ExampleSut.run(int source)
                MR: Adding zero preserves the result.
                Count: 4
                OutputRoot: generated/example
                MRProvider: LLM
                """);
        return PromptConfigLoader.load(prompt, repoRoot);
    }

    private static final class QueueClient implements LlmClient {
        private final Queue<String> responses = new ArrayDeque<>();
        private int calls;

        private QueueClient(String... responses) {
            this.responses.addAll(List.of(responses));
        }

        @Override
        public String complete(String prompt) {
            calls++;
            return responses.remove();
        }
    }
}
