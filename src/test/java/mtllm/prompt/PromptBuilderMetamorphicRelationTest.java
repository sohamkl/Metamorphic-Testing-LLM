package mtllm.prompt;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import mtllm.runner.TestRunResult;
import mtllm.sut.SutContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PromptBuilderMetamorphicRelationTest {
    private static final String MR_PROSE = "If the source value is doubled, then the result is unchanged.";

    @TempDir
    Path repoRoot;

    @Test
    void developerMrPromptsOmitMrProseAndKeepHelperCode() throws Exception {
        PromptConfig config = loadPrompt("""
                MRProvider: DEV
                DeveloperMrFile: ExampleSpec.java
                DeveloperFollowUpMethod: ExampleSpec.generateFollowUp
                DeveloperAssertMethod: ExampleSpec.assertRelation
                """);
        SutContext context = context(config);

        List<String> prompts = List.of(
                PromptBuilder.buildInitialPrompt(config, context),
                PromptBuilder.buildRepairPrompt(config, context, "class Broken {}", TestRunResult.failed("boom")),
                PromptBuilder.buildMissingScenarioRepairPrompt(config, context, "class Existing {}", Map.of("S1", 1)));

        for (String prompt : prompts) {
            assertFalse(prompt.contains("Metamorphic relation:"));
            assertFalse(prompt.contains("source value is doubled"));
            assertTrue(prompt.contains("public static int generateFollowUp"));
            assertTrue(prompt.contains("ExampleSpec.assertRelation"));
        }
    }

    @Test
    void llmMrPromptStillStatesMrProse() throws Exception {
        PromptConfig config = loadPrompt("MRProvider: LLM\n");

        String prompt = PromptBuilder.buildInitialPrompt(config, context(config));

        assertTrue(prompt.contains("Metamorphic relation:"));
        assertTrue(prompt.contains(MR_PROSE));
    }

    private SutContext context(PromptConfig config) throws Exception {
        return new SutContext(config.sutClassFile(), Files.readString(config.sutClassFile()), List.of());
    }

    private PromptConfig loadPrompt(String providerYaml) throws Exception {
        Files.writeString(repoRoot.resolve("ExampleSut.java"), """
                public final class ExampleSut {
                    public static int run(int source) {
                        return source;
                    }
                }
                """);
        Files.writeString(repoRoot.resolve("ExampleSpec.java"), """
                public final class ExampleSpec {
                    public static int generateFollowUp(int source) {
                        return source * 2;
                    }

                    public static void assertRelation(int sourceOutput, int followUpOutput) {
                    }
                }
                """);
        Path prompt = repoRoot.resolve("prompt.yaml");
        Files.writeString(prompt, providerYaml + """
                SUTClassFile: ExampleSut.java
                TargetFunction: public static int ExampleSut.run(int source)
                MRInput: the source value is doubled
                MROutput: the result is unchanged
                Count: 2
                """);
        return PromptConfigLoader.load(prompt, repoRoot);
    }
}
