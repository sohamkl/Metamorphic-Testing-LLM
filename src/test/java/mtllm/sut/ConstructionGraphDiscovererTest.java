package mtllm.sut;

import mtllm.config.GenerationMode;
import mtllm.config.InputGenerator;
import mtllm.config.MRProvider;
import mtllm.config.PromptConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstructionGraphDiscovererTest {

    @TempDir
    Path projectRoot;

    @Test
    void discoversNestedImplementationsGenericArgumentsAndSourceFactories() throws Exception {
        Path source = projectRoot.resolve("sample/ExternalFactory.java");
        Files.createDirectories(source.getParent());
        Files.writeString(source, """
                package sample;
                public final class ExternalFactory {
                    public static RootInput create() { return null; }
                }
                """);

        ConstructionGraphDiscoverer.Result result = ConstructionGraphDiscoverer.discover(
                config(projectRoot), RootInput.class);

        assertTrue(result.classNames().contains(RootInput.class.getName()));
        assertTrue(result.classNames().contains(PublicImplementation.class.getName()));
        assertTrue(result.classNames().contains(Leaf.class.getName()));
        assertTrue(result.classNames().contains(java.time.Duration.class.getName()));
        assertTrue(result.classNames().contains("sample.ExternalFactory"));
    }

    private static PromptConfig config(Path root) {
        return new PromptConfig(
                root, true, null, "target", List.of(), List.of(), "", "", "", "", 10, null,
                "GeneratedTest", GenerationMode.FULL_JUNIT, true, true, MRProvider.LLM,
                null, "", "", "", root.resolve("generated"), 1, InputGenerator.NEW_HYBRID,
                List.of(), List.of(), "");
    }

    public interface PublicContract {
    }

    public static final class PublicImplementation implements PublicContract {
        public PublicImplementation(Leaf leaf) {
        }
    }

    public static final class Leaf {
        public Leaf(String value) {
        }
    }

    public static final class RootInput {
        public RootInput(PublicContract contract, List<Leaf> leaves, java.time.Duration duration) {
        }
    }
}
