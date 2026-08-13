package mtllm.randoop;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import mtllm.randoop.fixture.CallbackSut;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.tools.ToolProvider;
import java.lang.reflect.Constructor;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CallbackSynthesizerTest {
    @TempDir
    Path tempDir;

    @Test
    void generatesReplayableEnumCallbackPolicies() throws Exception {
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path prompt = tempDir.resolve("prompt.yaml");
        Files.writeString(prompt, """
                SUTClassFile: src/test/java/mtllm/randoop/fixture/CallbackSut.java
                TargetFunction: public static Decision apply(DecisionCallback callback, String value)
                MR: Repeating the callback preserves its result.
                MRProvider: LLM
                InputGenerator: NEW_HYBRID
                OutputRoot: %s
                """.formatted(tempDir));
        PromptConfig config = PromptConfigLoader.load(prompt, repoRoot);

        CallbackSynthesizer.Generated generated =
                CallbackSynthesizer.generate(config, getClass().getClassLoader());

        assertFalse(generated.isEmpty());
        assertTrue(generated.interfaceNames().contains(CallbackSut.DecisionCallback.class.getName()));
        assertTrue(generated.classNames().size() >= 5);
        assertTrue(generated.sourceFiles().stream().map(this::readUnchecked)
                .anyMatch(source -> source.contains("CallbackSut.Decision.STOP")));
        assertTrue(generated.sourceFiles().stream().map(this::readUnchecked)
                .anyMatch(source -> source.contains("arg1 >= threshold")));

        Path classes = tempDir.resolve("classes");
        Files.createDirectories(classes);
        List<String> compilerArgs = new ArrayList<>(List.of(
                "-classpath", System.getProperty("java.class.path"),
                "-d", classes.toString()));
        generated.sourceFiles().forEach(source -> compilerArgs.add(source.toString()));
        assertEquals(0, ToolProvider.getSystemJavaCompiler().run(
                null, null, null, compilerArgs.toArray(String[]::new)));

        try (URLClassLoader loader = new URLClassLoader(
                new java.net.URL[]{classes.toUri().toURL()}, getClass().getClassLoader())) {
            boolean observedStop = false;
            for (String className : generated.classNames()) {
                Class<?> policyClass = Class.forName(className, true, loader);
                Constructor<?> constructor = policyClass.getConstructors()[0];
                Object policy = constructor.getParameterCount() == 0
                        ? constructor.newInstance()
                        : constructor.newInstance(2);
                CallbackSut.Decision decision = CallbackSut.apply(
                        (CallbackSut.DecisionCallback) policy, "value");
                observedStop |= decision == CallbackSut.Decision.STOP;
            }
            assertTrue(observedStop);
        }
    }

    private String readUnchecked(Path path) {
        try {
            return Files.readString(path);
        } catch (Exception failure) {
            throw new AssertionError(failure);
        }
    }
}
