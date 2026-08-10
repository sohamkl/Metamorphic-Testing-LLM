package mtllm.randoop;

import mtllm.config.MRProvider;
import mtllm.config.PromptConfig;
import mtllm.sut.JavaSourceNames;
import mtllm.sut.TargetMethodResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/** Generates the typed one-input boundary required by Randoop for zero/multi-argument SUT methods. */
public final class InvocationWrapperGenerator {
    private InvocationWrapperGenerator() {
    }

    public static Generated generate(PromptConfig config, ClassLoader loader) throws Exception {
        Class<?> sutClass = Class.forName(JavaSourceNames.qualifiedName(config.sutClassFile()), false, loader);
        Method target = TargetMethodResolver.resolve(sutClass, config.targetFunction());
        if (target.getParameterCount() == 1) {
            return null;
        }
        if (target.getReturnType() == void.class) {
            throw new IllegalArgumentException(
                    "Automatic Randoop invocation wrappers do not support void target methods: " + target);
        }

        Method developerFollowUp = null;
        if (config.mrProvider() == MRProvider.DEV) {
            Class<?> specClass = Class.forName(JavaSourceNames.qualifiedName(config.developerMrFile()), false, loader);
            developerFollowUp = specClass.getMethod(
                    simpleName(config.developerFollowUpMethod()), target.getParameterTypes());
            if (developerFollowUp.getReturnType() != Object[].class) {
                throw new IllegalArgumentException("For a multi-argument SUT, developer follow-up method "
                        + developerFollowUp.toGenericString() + " must return Object[] containing one transformed "
                        + "value per target argument.");
            }
        }

        String signature = sutClass.getName() + "#" + TargetMethodResolver.signature(target);
        String className = "MtllmGenerated" + sanitize(sutClass.getSimpleName())
                + capitalize(target.getName()) + "Invocation" + Integer.toUnsignedString(signature.hashCode(), 36);
        Path sourceDir = config.outputRoot().resolve("generated-support");
        Files.createDirectories(sourceDir);
        try (var existing = Files.list(sourceDir)) {
            for (Path path : existing
                    .filter(file -> file.getFileName().toString().matches("MtllmGenerated.*Invocation.*\\.java"))
                    .toList()) {
                Files.deleteIfExists(path);
            }
        }
        Path sourceFile = sourceDir.resolve(className + ".java");
        Files.writeString(sourceFile,
                render(className, sutClass, target, developerFollowUp), StandardCharsets.UTF_8);
        return new Generated(className, className + "$Input", sourceFile);
    }

    static String render(String className, Class<?> sutClass, Method target, Method developerFollowUp) {
        Class<?>[] parameters = target.getParameterTypes();
        StringBuilder out = new StringBuilder();
        out.append("/** Framework-generated typed invocation boundary for Randoop. */\n");
        out.append("public final class ").append(className).append(" {\n");
        out.append("    private ").append(className).append("() {}\n\n");
        out.append("    public static final class Input {\n");
        for (int i = 0; i < parameters.length; i++) {
            out.append("        private final ").append(typeName(parameters[i])).append(" arg").append(i).append(";\n");
        }
        out.append("\n        public Input(");
        appendParameters(out, parameters);
        out.append(") {\n");
        for (int i = 0; i < parameters.length; i++) {
            out.append("            this.arg").append(i).append(" = arg").append(i).append(";\n");
        }
        out.append("        }\n");
        for (int i = 0; i < parameters.length; i++) {
            out.append("\n        public ").append(typeName(parameters[i])).append(" arg").append(i)
                    .append("() { return arg").append(i).append("; }\n");
        }
        out.append("    }\n\n");

        out.append("    public static ").append(typeName(target.getReturnType())).append(" invoke(Input source) {\n");
        out.append("        try {\n            ");
        if (!Modifier.isStatic(target.getModifiers())) {
            out.append(typeName(sutClass)).append(" receiver = (").append(typeName(sutClass))
                    .append(") mtllm.sut.ReflectiveObjectFactory.create(")
                    .append(typeName(sutClass)).append(".class);\n            ");
        }
        out.append("return ")
                .append(Modifier.isStatic(target.getModifiers()) ? typeName(sutClass) : "receiver")
                .append('.').append(target.getName()).append('(');
        appendAccessors(out, parameters.length, "source");
        out.append(");\n");
        out.append("        } catch (RuntimeException | Error failure) {\n")
                .append("            throw failure;\n")
                .append("        } catch (Throwable failure) {\n")
                .append("            throw new IllegalStateException(\"SUT invocation failed\", failure);\n")
                .append("        }\n    }\n");

        if (developerFollowUp != null) {
            out.append("\n    public static Input generateFollowUp(Input source) {\n")
                    .append("        Object[] values = ").append(typeName(developerFollowUp.getDeclaringClass()))
                    .append('.').append(developerFollowUp.getName()).append('(');
            appendAccessors(out, parameters.length, "source");
            out.append(");\n")
                    .append("        if (values == null || values.length != ").append(parameters.length).append(") {\n")
                    .append("            throw new IllegalArgumentException(\"Developer follow-up must return exactly ")
                    .append(parameters.length).append(" values\");\n")
                    .append("        }\n")
                    .append("        return new Input(");
            for (int i = 0; i < parameters.length; i++) {
                if (i > 0) out.append(", ");
                out.append(fromObject(parameters[i], "values[" + i + "]"));
            }
            out.append(");\n    }\n");
        }
        out.append("}\n");
        return out.toString();
    }

    private static void appendParameters(StringBuilder out, Class<?>[] types) {
        for (int i = 0; i < types.length; i++) {
            if (i > 0) out.append(", ");
            out.append(typeName(types[i])).append(" arg").append(i);
        }
    }

    private static void appendAccessors(StringBuilder out, int count, String variable) {
        for (int i = 0; i < count; i++) {
            if (i > 0) out.append(", ");
            out.append(variable).append(".arg").append(i).append("()");
        }
    }

    private static String fromObject(Class<?> type, String value) {
        if (!type.isPrimitive()) return "(" + typeName(type) + ") " + value;
        if (type == int.class) return "((Integer) " + value + ").intValue()";
        if (type == long.class) return "((Long) " + value + ").longValue()";
        if (type == double.class) return "((Double) " + value + ").doubleValue()";
        if (type == float.class) return "((Float) " + value + ").floatValue()";
        if (type == short.class) return "((Short) " + value + ").shortValue()";
        if (type == byte.class) return "((Byte) " + value + ").byteValue()";
        if (type == boolean.class) return "((Boolean) " + value + ").booleanValue()";
        if (type == char.class) return "((Character) " + value + ").charValue()";
        throw new IllegalArgumentException("Unsupported primitive: " + type);
    }

    private static String typeName(Class<?> type) {
        String canonical = type.getCanonicalName();
        return canonical == null ? type.getTypeName().replace('$', '.') : canonical;
    }

    private static String simpleName(String qualified) {
        int dot = qualified.lastIndexOf('.');
        return dot >= 0 ? qualified.substring(dot + 1) : qualified;
    }

    private static String sanitize(String value) {
        return value.replaceAll("[^A-Za-z0-9_$]", "");
    }

    private static String capitalize(String value) {
        return value.isEmpty() ? value : Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    public record Generated(String className, String inputClassName, Path sourceFile) {
    }
}
