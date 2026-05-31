package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.generation.GeneratedTestWriter;
import mtllm.llm.LlmClient;
import mtllm.prompt.PromptBuilder;
import mtllm.sut.SutContext;

import java.nio.file.Path;

/**
 * Coordinates generation, validation, and optional repair of the generated test.
 *
 * <p>In simple terms, this class asks the LLM for a JUnit test, tries to compile/run it, and
 * sends errors back to the LLM for a limited number of fixes.</p>
 */
public final class RepairLoop {
    private final LlmClient llmClient;
    private final GeneratedTestRunner testRunner;
    private final Path generatedTestsDir;

    public RepairLoop(LlmClient llmClient, GeneratedTestRunner testRunner, Path generatedTestsDir) {
        this.llmClient = llmClient;
        this.testRunner = testRunner;
        this.generatedTestsDir = generatedTestsDir;
    }

    public TestRunResult generateRunAndRepair(PromptConfig config, SutContext sutContext) throws Exception {
        String code = llmClient.complete(PromptBuilder.buildInitialPrompt(config, sutContext));
        Path generatedFile = GeneratedTestWriter.write(generatedTestsDir, config.generatedClassName(), code);
        System.out.println("Wrote generated JUnit test to " + generatedFile);

        TestRunResult result = testRunner.compileAndRun(generatedFile, config, sutContext);
        int attempts = 0;
        while (result.failed() && attempts < config.maxRepairAttempts()) {
            attempts++;
            System.out.println("Generated test failed. Requesting repair attempt " + attempts + "...");
            code = llmClient.complete(PromptBuilder.buildRepairPrompt(config, sutContext, code, result));
            generatedFile = GeneratedTestWriter.write(generatedTestsDir, config.generatedClassName(), code);
            result = testRunner.compileAndRun(generatedFile, config, sutContext);
        }
        return result;
    }
}
