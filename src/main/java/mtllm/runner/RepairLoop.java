package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.generation.GeneratedCodeWriter;
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
    private final DataGeneratorRunner dataGeneratorRunner;
    private final Path generatedTestsDir;
    private final Path generatedCodeDir;

    public RepairLoop(
            LlmClient llmClient,
            GeneratedTestRunner testRunner,
            DataGeneratorRunner dataGeneratorRunner,
            Path generatedTestsDir,
            Path generatedCodeDir) {
        this.llmClient = llmClient;
        this.testRunner = testRunner;
        this.dataGeneratorRunner = dataGeneratorRunner;
        this.generatedTestsDir = generatedTestsDir;
        this.generatedCodeDir = generatedCodeDir;
    }

    public TestRunResult generateRunAndRepair(PromptConfig config, SutContext sutContext) throws Exception {
        String code = llmClient.complete(PromptBuilder.buildInitialPrompt(config, sutContext));
        Path generatedFile = writeGeneratedFile(config, code);

        TestRunResult result = runGeneratedFile(generatedFile, config, sutContext);
        int attempts = 0;
        while (result.failed() && attempts < config.maxRepairAttempts()) {
            attempts++;
            System.out.println("Generated code failed. Requesting repair attempt " + attempts + "...");
            code = llmClient.complete(PromptBuilder.buildRepairPrompt(config, sutContext, code, result));
            generatedFile = writeGeneratedFile(config, code);
            result = runGeneratedFile(generatedFile, config, sutContext);
        }
        return result;
    }

    private Path writeGeneratedFile(PromptConfig config, String code) throws Exception {
        Path generatedFile;
        if (config.mode().generatesJUnit()) {
            generatedFile = GeneratedTestWriter.write(generatedTestsDir, config.generatedClassName(), code);
            System.out.println("Wrote generated JUnit test to " + generatedFile);
        } else {
            generatedFile = GeneratedCodeWriter.write(generatedCodeDir, config.generatedClassName(), code);
            System.out.println("Wrote generated data-generator code to " + generatedFile);
        }
        return generatedFile;
    }

    private TestRunResult runGeneratedFile(Path generatedFile, PromptConfig config, SutContext sutContext) throws Exception {
        if (config.mode().generatesJUnit()) {
            return testRunner.compileAndRun(generatedFile, config, sutContext);
        }
        return dataGeneratorRunner.compileRunAndValidate(generatedFile, config, sutContext);
    }
}
