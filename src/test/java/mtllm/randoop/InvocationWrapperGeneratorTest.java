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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void storesInstanceReceiverInInputAndTransformsItWithArguments() throws Exception {
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path prompt = tempDir.resolve("instance-prompt.yaml");
        Files.writeString(prompt, """
                SUTClassFile: src/test/java/mtllm/randoop/fixture/InstanceMethodSut.java
                TargetFunction: public int combine(int value)
                MR: Transform both the receiver and argument.
                MRProvider: DEV
                DeveloperMrFile: src/test/java/mtllm/randoop/fixture/InstanceMethodSpec.java
                DeveloperFollowUpMethod: increaseBoth
                DeveloperAssertMethod: assertRelation
                OutputRoot: %s
                """.formatted(tempDir.toString()));
        PromptConfig config = PromptConfigLoader.load(prompt, repoRoot);

        InvocationWrapperGenerator.Generated generated =
                InvocationWrapperGenerator.generate(config, getClass().getClassLoader());

        assertNotNull(generated);
        String source = Files.readString(generated.sourceFile());
        assertTrue(source.contains("private final mtllm.randoop.fixture.InstanceMethodSut receiver"));
        assertTrue(source.contains("source.receiver().combine(source.arg0())"));
        assertTrue(source.contains("InstanceMethodSpec.increaseBoth(source.receiver(), source.arg0())"));
        assertTrue(!source.contains("ReflectiveObjectFactory"));

        Path classes = tempDir.resolve("instance-classes");
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
            Class<?> receiverType = Class.forName(
                    "mtllm.randoop.fixture.InstanceMethodSut", true, getClass().getClassLoader());
            Object receiver = receiverType.getConstructor(int.class).newInstance(3);
            Object sourceInput = input.getConstructor(receiverType, int.class).newInstance(receiver, 4);
            Method invoke = wrapper.getMethod("invoke", input);
            Method followUp = wrapper.getMethod("generateFollowUp", input);

            assertEquals(7, invoke.invoke(null, sourceInput));
            Object followUpInput = followUp.invoke(null, sourceInput);
            assertEquals(9, invoke.invoke(null, followUpInput));
        }
    }

    @Test
    void rejectsNullReferenceArgumentsAsUnusableSeeds() throws Exception {
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path prompt = tempDir.resolve("callback-prompt.yaml");
        Files.writeString(prompt, """
                SUTClassFile: src/test/java/mtllm/randoop/fixture/CallbackSut.java
                TargetFunction: public static Decision apply(DecisionCallback callback, String value)
                MR: Repeating the callback preserves its result.
                MRProvider: LLM
                OutputRoot: %s
                """.formatted(tempDir));
        PromptConfig config = PromptConfigLoader.load(prompt, repoRoot);

        InvocationWrapperGenerator.Generated generated =
                InvocationWrapperGenerator.generate(config, getClass().getClassLoader());
        assertNotNull(generated);
        String source = Files.readString(generated.sourceFile());
        assertTrue(source.contains("source.arg0() != null && source.arg1() != null"));
    }

    @Test
    void generatesTypedCollectionConstructionBridgeWithoutExposingRawListConstructor() throws Exception {
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        Path prompt = tempDir.resolve("collection-prompt.yaml");
        Files.writeString(prompt, """
                SUTClassFile: src/test/java/mtllm/randoop/fixture/CollectionInputSut.java
                TargetFunction: public int sum(List<Item> items)
                MR: Repeating the invocation preserves its result.
                MRProvider: LLM
                OutputRoot: %s
                """.formatted(tempDir));
        PromptConfig config = PromptConfigLoader.load(prompt, repoRoot);

        InvocationWrapperGenerator.Generated generated =
                InvocationWrapperGenerator.generate(config, getClass().getClassLoader());

        assertNotNull(generated);
        String source = Files.readString(generated.sourceFile());
        assertTrue(source.contains("private final java.util.List<mtllm.randoop.fixture.CollectionInputSut.Item> arg0"));
        assertTrue(source.contains("private Input(mtllm.randoop.fixture.CollectionInputSut receiver, java.util.List<"));
        assertTrue(source.contains("public Input(mtllm.randoop.fixture.CollectionInputSut receiver, mtllm.randoop.fixture.CollectionInputSut.Item arg0Element0)"));
        assertTrue(source.contains("java.util.List.of(arg0Element0)"));
        assertTrue(source.contains("!source.arg0().isEmpty()"));
        assertTrue(source.contains("value instanceof mtllm.randoop.fixture.CollectionInputSut.Item"));

        Path classes = tempDir.resolve("collection-classes");
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
            Class<?> sutType = Class.forName(
                    "mtllm.randoop.fixture.CollectionInputSut", true, getClass().getClassLoader());
            Class<?> itemType = Class.forName(
                    "mtllm.randoop.fixture.CollectionInputSut$Item", true, getClass().getClassLoader());
            Class<?> itemImplType = Class.forName(
                    "mtllm.randoop.fixture.CollectionInputSut$ItemImpl", true, getClass().getClassLoader());
            Object receiver = sutType.getConstructor().newInstance();
            Object item = itemImplType.getConstructor(int.class).newInstance(7);
            Object sourceInput = input.getConstructor(sutType, itemType).newInstance(receiver, item);

            assertThrows(NoSuchMethodException.class, () -> input.getConstructor(sutType, List.class));
            assertEquals(7, wrapper.getMethod("invoke", input).invoke(null, sourceInput));
            assertEquals(true, wrapper.getMethod("isUsable", input).invoke(null, sourceInput));
        }
    }
}
