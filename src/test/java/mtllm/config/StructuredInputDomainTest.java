package mtllm.config;

import mtllm.prompt.PromptBuilder;
import mtllm.sut.SutContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StructuredInputDomainTest {

    @TempDir
    Path repoRoot;

    @Test
    void parsesStructuredInputDomainAndRendersScenarioPrompt() throws Exception {
        PromptConfig config = loadPrompt("""
                SUTClassFile: ExampleSut.java
                TargetFunction: public static int ExampleSut.run(int source)
                Count: 3
                InputDomain:
                  summary: Exercise threshold behavior.
                  globalConstraints:
                    - Inputs must be positive.
                  diversity:
                    sizes: [small, large]
                  scenarios:
                    - id: ABOVE_THRESHOLD
                      category: BOUNDARY
                      description: Cross the threshold.
                      preconditions:
                        - source is greater than 10
                      expectedSourceBehavior:
                        - source output is non-empty
                      targetCases: 2
                      emptyOutputAllowed: false
                """);

        InputDomainRequirements requirements = config.inputDomainRequirements();
        assertTrue(requirements.isStructured());
        assertEquals("Exercise threshold behavior.", requirements.summary());
        assertEquals(List.of("small", "large"), requirements.diversityDimensions().get("sizes"));
        assertEquals(1, requirements.scenarios().size());
        assertEquals(ScenarioCategory.BOUNDARY, requirements.scenarios().get(0).category());

        SutContext context = new SutContext(
                config.sutClassFile(), Files.readString(config.sutClassFile()), List.of());
        String prompt = PromptBuilder.buildInitialPrompt(config, context);
        assertTrue(prompt.contains("Structured input domain and scenario requirements:"));
        assertTrue(prompt.contains("Scenario ABOVE_THRESHOLD [BOUNDARY]"));
        assertTrue(prompt.contains("Target cases: 2"));
        assertTrue(prompt.contains("Empty source output allowed: no"));
        assertTrue(prompt.contains("Include the scenario ID in every generated test method name"));
    }

    @Test
    void preservesLegacyScalarInputDomain() throws Exception {
        PromptConfig config = loadPrompt("""
                SUTClassFile: ExampleSut.java
                InputDomain: Generate positive boundary values.
                """);

        assertFalse(config.inputDomainRequirements().isStructured());
        assertEquals("Generate positive boundary values.", config.inputDomain());
    }

    @Test
    void treatsHybridRandoopExamplesAsFinalSourceFixtures() throws Exception {
        PromptConfig config = loadPrompt("""
                SUTClassFile: ExampleSut.java
                TargetFunction: public static int ExampleSut.run(int source)
                InputGenerator: HYBRID
                Count: 2
                InputDomain: Generate positive values.
                """).withRandoopSeedExamples(
                        "[{\"value\":7,\"constructionCode\":\"int int0 = 7;\"}]");

        SutContext context = new SutContext(
                config.sutClassFile(), Files.readString(config.sutClassFile()), List.of());
        String prompt = PromptBuilder.buildInitialPrompt(config, context);

        assertTrue(prompt.contains("HYBRID final source inputs harvested by LLM-seeded Randoop"));
        assertTrue(prompt.contains("final source fixtures for this run"));
        assertTrue(prompt.contains("Do not invent, replace, or randomly vary the source inputs"));
        assertFalse(prompt.contains("Generate additional diverse source inputs"));
    }

    @Test
    void rejectsDuplicateScenarioIds() throws Exception {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> loadPrompt("""
                SUTClassFile: ExampleSut.java
                Count: 3
                InputDomain:
                  scenarios:
                    - id: EDGE_CASE
                      description: First edge.
                    - id: edge_case
                      description: Duplicate edge.
                """));

        assertTrue(error.getMessage().contains("Duplicate scenario id"));
    }

    @Test
    void rejectsScenarioTargetsAboveCount() throws Exception {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> loadPrompt("""
                SUTClassFile: ExampleSut.java
                Count: 2
                InputDomain:
                  scenarios:
                    - id: MANY
                      description: Too many requested cases.
                      targetCases: 3
                """));

        assertTrue(error.getMessage().contains("exceeds Count 2"));
    }

    @Test
    void rejectsUnknownStructuredField() throws Exception {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> loadPrompt("""
                SUTClassFile: ExampleSut.java
                InputDomain:
                  summary: Valid summary.
                  scenarious: []
                """));

        assertTrue(error.getMessage().contains("unknown field: scenarious"));
    }

    private PromptConfig loadPrompt(String yaml) throws Exception {
        Files.writeString(repoRoot.resolve("ExampleSut.java"), """
                public final class ExampleSut {
                    public static int run(int source) {
                        return source;
                    }
                }
                """);
        Path prompt = repoRoot.resolve("prompt.yaml");
        Files.writeString(prompt, "MRProvider: LLM\n" + yaml);
        return PromptConfigLoader.load(prompt, repoRoot);
    }
}
