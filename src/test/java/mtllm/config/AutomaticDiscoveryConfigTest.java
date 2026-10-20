package mtllm.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AutomaticDiscoveryConfigTest {
    @TempDir
    Path repoRoot;

    @Test
    void locatesSutSourceFromProjectRootAndClassName() throws Exception {
        Path project = repoRoot.resolve("sample-project");
        Path source = project.resolve("src/main/java/example/ExampleSut.java");
        Files.createDirectories(source.getParent());
        Files.writeString(source, "package example; public final class ExampleSut {}\n");
        Path prompt = repoRoot.resolve("prompt.yaml");
        Files.writeString(prompt, """
                ProjectRoot: sample-project
                SUTClass: example.ExampleSut
                TargetFunction: public int run(int value)
                MR: Repeating the operation preserves the result.
                MRProvider: LLM
                """);

        PromptConfig config = PromptConfigLoader.load(prompt, repoRoot);

        assertEquals(source, config.sutClassFile());
        assertEquals(project, config.projectRoot());
        assertTrue(config.automaticDiscovery());
        assertTrue(config.inputDomain().isBlank());
    }
}
