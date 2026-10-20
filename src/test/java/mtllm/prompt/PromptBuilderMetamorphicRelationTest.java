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
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void iAutoPromptKeepsDeveloperFollowUpAndAsksForTheOutputRelation() throws Exception {
        PromptConfig config = loadPrompt("""
                MRProvider: DEV
                DeveloperMrFile: ExampleSpec.java
                DeveloperFollowUpMethod: ExampleSpec.generateFollowUp
                """);

        String prompt = PromptBuilder.buildInitialPrompt(config, context(config));

        assertTrue(prompt.contains("Metamorphic output relation you must implement:"));
        assertTrue(prompt.contains("the result is unchanged"));
        assertFalse(prompt.contains("Metamorphic input transformation you must implement:"));
        assertFalse(prompt.contains("the source value is doubled"));
        assertTrue(prompt.contains("Developer follow-up method to call: ExampleSpec.generateFollowUp"));
        assertFalse(prompt.contains("Developer assertion method to call:"));
        // the LLM owns the oracle here, so the blanket ban on writing one must not be sent
        assertFalse(prompt.contains("Do not generate an assertMetamorphicRelation"));
        assertTrue(prompt.contains("Do not generate a generateFollowUp method."));
    }

    @Test
    void oAutoPromptKeepsDeveloperAssertionAndAsksForTheFollowUp() throws Exception {
        PromptConfig config = loadPrompt("""
                MRProvider: DEV
                DeveloperMrFile: ExampleSpec.java
                DeveloperAssertMethod: ExampleSpec.assertRelation
                """);

        String prompt = PromptBuilder.buildInitialPrompt(config, context(config));

        assertTrue(prompt.contains("Metamorphic input transformation you must implement:"));
        assertTrue(prompt.contains("the source value is doubled"));
        assertFalse(prompt.contains("Metamorphic output relation you must implement:"));
        assertFalse(prompt.contains("the result is unchanged"));
        assertTrue(prompt.contains("Developer assertion method to call: ExampleSpec.assertRelation"));
        assertFalse(prompt.contains("Developer follow-up method to call:"));
        assertFalse(prompt.contains("Do not generate a generateFollowUp method."));
        assertTrue(prompt.contains("Do not generate an assertMetamorphicRelation"));
    }

    @Test
    void developerMrNeedsAtLeastOneHalfAndProseForTheOther() {
        assertThrows(IllegalArgumentException.class, () -> loadPrompt("""
                MRProvider: DEV
                DeveloperMrFile: ExampleSpec.java
                """));
        assertThrows(IllegalArgumentException.class, () -> loadPromptWithoutProse("""
                MRProvider: DEV
                DeveloperMrFile: ExampleSpec.java
                DeveloperFollowUpMethod: ExampleSpec.generateFollowUp
                """));
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

    /** Same as {@link #loadPrompt} but with no MRInput/MROutput, so a half-developer MR has no prose to fall back on. */
    private PromptConfig loadPromptWithoutProse(String providerYaml) throws Exception {
        writeExampleSources();
        Path prompt = repoRoot.resolve("prompt-no-prose.yaml");
        Files.writeString(prompt, providerYaml + """
                SUTClassFile: ExampleSut.java
                TargetFunction: public static int ExampleSut.run(int source)
                Count: 2
                """);
        return PromptConfigLoader.load(prompt, repoRoot);
    }

    private PromptConfig loadPrompt(String providerYaml) throws Exception {
        writeExampleSources();
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

    private void writeExampleSources() throws Exception {
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
    }
}
