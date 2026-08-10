package mtllm.randoop;

import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvocationWrapperGeneratorTest {
    @TempDir
    Path tempDir;

    @Test
    void generatesCompilableTypedWrapperAndDeveloperFollowUpBridge() throws Exception {
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path prompt = tempDir.resolve("prompt.yaml");
        Files.writeString(prompt, """
                SUTClassFile: src/test/java/mtllm/randoop/fixture/MultiArgumentSut.java
                TargetFunction: public static int combine(int left, int right)
                MR: Increasing both arguments by one increases the output by two.
                MRProvider: DEV
                DeveloperMrFile: src/test/java/mtllm/randoop/fixture/MultiArgumentSpec.java
                DeveloperFollowUpMethod: increaseBoth
                DeveloperAssertMethod: assertRelation
                OutputRoot: %s
                """.formatted(tempDir.toString()));
        PromptConfig config = PromptConfigLoader.load(prompt, repoRoot);

        InvocationWrapperGenerator.Generated generated =
                InvocationWrapperGenerator.generate(config, getClass().getClassLoader());

        assertNotNull(generated);
        String source = Files.readString(generated.sourceFile());
        assertTrue(source.contains("public static final class Input"));
        assertTrue(source.contains("MultiArgumentSut.combine(source.arg0(), source.arg1())"));
        assertTrue(source.contains("MultiArgumentSpec.increaseBoth(source.arg0(), source.arg1())"));

        Path classes = tempDir.resolve("classes");
        Files.createDirectories(classes);
        int compileResult = ToolProvider.getSystemJavaCompiler().run(
                null, null, null,
                "-classpath", System.getProperty("java.class.path"),
                "-d", classes.toString(), generated.sourceFile().toString());
        assertEquals(0, compileResult);

        try (URLClassLoader loader = new URLClassLoader(
                new java.net.URL[]{classes.toUri().toURL()}, getClass().getClassLoader())) {
            Class<?> wrapper = Class.forName(generated.className(), true, loader);
            Class<?> input = Class.forName(generated.inputClassName(), true, loader);
            Object sourceInput = input.getConstructor(int.class, int.class).newInstance(3, 4);
            Method invoke = wrapper.getMethod("invoke", input);
            Method followUp = wrapper.getMethod("generateFollowUp", input);

            assertEquals(7, invoke.invoke(null, sourceInput));
            Object followUpInput = followUp.invoke(null, sourceInput);
            assertEquals(9, invoke.invoke(null, followUpInput));
        }
    }
}
