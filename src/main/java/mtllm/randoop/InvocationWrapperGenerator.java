package mtllm.randoop;

import mtllm.config.MRProvider;
import mtllm.config.PromptConfig;
import mtllm.sut.JavaSourceNames;
import mtllm.sut.TargetMethodResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** Generates the typed one-input boundary required by Randoop for instance or non-unary SUT methods. */
public final class InvocationWrapperGenerator {
    private static final int MAX_COLLECTION_ARITY = 6;

    private InvocationWrapperGenerator() {
    }

    public static Generated generate(PromptConfig config, ClassLoader loader) throws Exception {
        Class<?> sutClass = Class.forName(JavaSourceNames.qualifiedName(config.sutClassFile()), false, loader);
        Method target = TargetMethodResolver.resolve(sutClass, config.targetFunction());
        boolean instanceMethod = !Modifier.isStatic(target.getModifiers());
        if (!instanceMethod && target.getParameterCount() == 1 && collectionBridge(target) == null) {
            return null;
        }
        if (target.getReturnType() == void.class) {
            throw new IllegalArgumentException(
                    "Automatic Randoop invocation wrappers do not support void target methods: " + target);
        }

        Method developerFollowUp = null;
        if (config.mrProvider() == MRProvider.DEV) {
            Class<?> specClass = Class.forName(JavaSourceNames.qualifiedName(config.developerMrFile()), false, loader);
            Class<?>[] followUpParameters = invocationComponentTypes(sutClass, target);
            developerFollowUp = specClass.getMethod(
                    simpleName(config.developerFollowUpMethod()), followUpParameters);
            if (developerFollowUp.getReturnType() != Object[].class) {
                throw new IllegalArgumentException("For a wrapped SUT invocation, developer follow-up method "
                        + developerFollowUp.toGenericString() + " must return Object[] containing the transformed "
                        + "receiver (for an instance method) followed by the transformed target arguments.");
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
        Type[] genericParameters = target.getGenericParameterTypes();
        boolean instanceMethod = !Modifier.isStatic(target.getModifiers());
        Class<?>[] components = invocationComponentTypes(sutClass, target);
        CollectionBridge collectionBridge = collectionBridge(target);
        StringBuilder out = new StringBuilder();
        out.append("/** Framework-generated typed invocation boundary for Randoop. */\n");
        out.append("public final class ").append(className).append(" {\n");
        out.append("    private ").append(className).append("() {}\n\n");
        out.append("    public static final class Input {\n");
        if (instanceMethod) {
            out.append("        private final ").append(typeName(sutClass)).append(" receiver;\n");
        }
        for (int i = 0; i < parameters.length; i++) {
            out.append("        private final ").append(typeName(genericParameters[i]))
                    .append(" arg").append(i).append(";\n");
        }
        out.append("\n        ").append(collectionBridge == null ? "public" : "private").append(" Input(");
        appendInputParameters(out, sutClass, genericParameters, instanceMethod);
        out.append(") {\n");
        if (instanceMethod) {
            out.append("            this.receiver = receiver;\n");
        }
        for (int i = 0; i < parameters.length; i++) {
            out.append("            this.arg").append(i).append(" = arg").append(i).append(";\n");
        }
        out.append("        }\n");
        if (collectionBridge != null) {
            appendCollectionConstructors(out, sutClass, genericParameters, instanceMethod, collectionBridge);
        }
        if (instanceMethod) {
            out.append("\n        public ").append(typeName(sutClass))
                    .append(" receiver() { return receiver; }\n");
        }
        for (int i = 0; i < parameters.length; i++) {
            out.append("\n        public ").append(typeName(genericParameters[i])).append(" arg").append(i)
                    .append("() { return arg").append(i).append("; }\n");
        }
        out.append("    }\n\n");

        out.append("    public static ").append(typeName(target.getGenericReturnType()))
                .append(" invoke(Input source) {\n");
        out.append("        try {\n            ");
        out.append("return ")
                .append(instanceMethod ? "source.receiver()" : typeName(sutClass))
                .append('.').append(target.getName()).append('(');
        appendAccessors(out, parameters.length, "source");
        out.append(");\n");
        out.append("        } catch (RuntimeException | Error failure) {\n")
                .append("            throw failure;\n")
                .append("        } catch (Throwable failure) {\n")
                .append("            throw new IllegalStateException(\"SUT invocation failed\", failure);\n")
                .append("        }\n    }\n");

        List<String> usabilityChecks = new java.util.ArrayList<>();
        boolean synthesizedCallbackParameter = java.util.Arrays.stream(parameters)
                .anyMatch(CallbackSynthesizer::supports);
        for (int i = 0; i < parameters.length; i++) {
            if (synthesizedCallbackParameter && !parameters[i].isPrimitive()) {
                usabilityChecks.add("source.arg" + i + "() != null");
            }
        }
        if (collectionBridge != null) {
            if (instanceMethod) {
                usabilityChecks.add("source.receiver() != null");
            }
            String accessor = "source.arg" + collectionBridge.parameterIndex + "()";
            usabilityChecks.add(accessor + " != null");
            usabilityChecks.add("!" + accessor + ".isEmpty()");
            usabilityChecks.add(accessor + ".stream().allMatch(value -> value instanceof "
                    + typeName(collectionBridge.runtimeElementType) + ")");
        }
        if (!usabilityChecks.isEmpty()) {
            out.append("\n    public static boolean isUsable(Input source) {\n")
                    .append("        return ").append(String.join(" && ", usabilityChecks)).append(";\n")
                    .append("    }\n");
        }

        if (developerFollowUp != null) {
            out.append("\n    public static Input generateFollowUp(Input source) {\n")
                    .append("        Object[] values = ").append(typeName(developerFollowUp.getDeclaringClass()))
                    .append('.').append(developerFollowUp.getName()).append('(');
            appendComponentAccessors(out, parameters.length, "source", instanceMethod);
            out.append(");\n")
                    .append("        if (values == null || values.length != ").append(components.length).append(") {\n")
                    .append("            throw new IllegalArgumentException(\"Developer follow-up must return exactly ")
                    .append(components.length).append(" values\");\n")
                    .append("        }\n")
                    .append("        return new Input(");
            for (int i = 0; i < components.length; i++) {
                if (i > 0) out.append(", ");
                out.append(fromObject(components[i], "values[" + i + "]"));
            }
            out.append(");\n    }\n");
        }
        out.append("}\n");
        return out.toString();
    }

    private static void appendInputParameters(
            StringBuilder out, Class<?> sutClass, Type[] types, boolean instanceMethod) {
        if (instanceMethod) {
            out.append(typeName(sutClass)).append(" receiver");
        }
        for (int i = 0; i < types.length; i++) {
            if (instanceMethod || i > 0) out.append(", ");
            out.append(typeName(types[i])).append(" arg").append(i);
        }
    }

    private static void appendCollectionConstructors(
            StringBuilder out,
            Class<?> sutClass,
            Type[] parameterTypes,
            boolean instanceMethod,
            CollectionBridge bridge) {
        for (int arity = 1; arity <= MAX_COLLECTION_ARITY; arity++) {
            out.append("\n        public Input(");
            boolean comma = false;
            if (instanceMethod) {
                out.append(typeName(sutClass)).append(" receiver");
                comma = true;
            }
            for (int parameterIndex = 0; parameterIndex < parameterTypes.length; parameterIndex++) {
                if (parameterIndex == bridge.parameterIndex) {
                    for (int elementIndex = 0; elementIndex < arity; elementIndex++) {
                        if (comma) out.append(", ");
                        out.append(typeName(bridge.elementType)).append(" arg")
                                .append(parameterIndex).append("Element").append(elementIndex);
                        comma = true;
                    }
                } else {
                    if (comma) out.append(", ");
                    out.append(typeName(parameterTypes[parameterIndex])).append(" arg").append(parameterIndex);
                    comma = true;
                }
            }
            out.append(") {\n            this(");
            comma = false;
            if (instanceMethod) {
                out.append("receiver");
                comma = true;
            }
            for (int parameterIndex = 0; parameterIndex < parameterTypes.length; parameterIndex++) {
                if (comma) out.append(", ");
                if (parameterIndex == bridge.parameterIndex) {
                    out.append(bridge.factoryMethod).append('(');
                    for (int elementIndex = 0; elementIndex < arity; elementIndex++) {
                        if (elementIndex > 0) out.append(", ");
                        out.append("arg").append(parameterIndex).append("Element").append(elementIndex);
                    }
                    out.append(')');
                } else {
                    out.append("arg").append(parameterIndex);
                }
                comma = true;
            }
            out.append(");\n        }\n");
        }
    }

    private static void appendComponentAccessors(
            StringBuilder out, int argumentCount, String variable, boolean instanceMethod) {
        if (instanceMethod) {
            out.append(variable).append(".receiver()");
        }
        if (argumentCount > 0 && instanceMethod) {
            out.append(", ");
        }
        appendAccessors(out, argumentCount, variable);
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

    private static Class<?>[] invocationComponentTypes(Class<?> sutClass, Method target) {
        Class<?>[] parameters = target.getParameterTypes();
        if (Modifier.isStatic(target.getModifiers())) {
            return parameters;
        }
        Class<?>[] components = new Class<?>[parameters.length + 1];
        components[0] = sutClass;
        System.arraycopy(parameters, 0, components, 1, parameters.length);
        return components;
    }

    private static CollectionBridge collectionBridge(Method target) {
        CollectionBridge found = null;
        Type[] genericParameters = target.getGenericParameterTypes();
        for (int index = 0; index < genericParameters.length; index++) {
            Type parameter = genericParameters[index];
            if (!(parameter instanceof ParameterizedType parameterized)
                    || !(parameterized.getRawType() instanceof Class<?> rawType)
                    || parameterized.getActualTypeArguments().length != 1) {
                continue;
            }
            String factoryMethod;
            if (rawType == java.util.List.class || rawType == java.util.Collection.class) {
                factoryMethod = "java.util.List.of";
            } else if (rawType == java.util.Set.class) {
                factoryMethod = "java.util.Set.of";
            } else {
                continue;
            }
            Type elementType = parameterized.getActualTypeArguments()[0];
            Class<?> runtimeElementType = runtimeClass(elementType);
            if (runtimeElementType == null || runtimeElementType.isPrimitive() || found != null) {
                return null;
            }
            found = new CollectionBridge(index, elementType, runtimeElementType, factoryMethod);
        }
        return found;
    }

    private static Class<?> runtimeClass(Type type) {
        if (type instanceof Class<?> clazz) return clazz;
        if (type instanceof ParameterizedType parameterized
                && parameterized.getRawType() instanceof Class<?> rawType) {
            return rawType;
        }
        if (type instanceof WildcardType wildcard && wildcard.getUpperBounds().length > 0) {
            return runtimeClass(wildcard.getUpperBounds()[0]);
        }
        if (type instanceof TypeVariable<?> variable && variable.getBounds().length > 0) {
            return runtimeClass(variable.getBounds()[0]);
        }
        return null;
    }

    private static String typeName(Type type) {
        if (type instanceof Class<?> clazz) {
            String canonical = clazz.getCanonicalName();
            return canonical == null ? clazz.getTypeName().replace('$', '.') : canonical;
        }
        if (type instanceof ParameterizedType parameterized) {
            StringBuilder name = new StringBuilder(typeName(parameterized.getRawType())).append('<');
            Type[] arguments = parameterized.getActualTypeArguments();
            for (int i = 0; i < arguments.length; i++) {
                if (i > 0) name.append(", ");
                name.append(typeName(arguments[i]));
            }
            return name.append('>').toString();
        }
        if (type instanceof WildcardType wildcard) {
            if (wildcard.getLowerBounds().length > 0) {
                return "? super " + typeName(wildcard.getLowerBounds()[0]);
            }
            if (wildcard.getUpperBounds().length > 0 && wildcard.getUpperBounds()[0] != Object.class) {
                return "? extends " + typeName(wildcard.getUpperBounds()[0]);
            }
            return "?";
        }
        if (type instanceof GenericArrayType array) {
            return typeName(array.getGenericComponentType()) + "[]";
        }
        if (type instanceof TypeVariable<?> variable) {
            return variable.getName();
        }
        return type.getTypeName().replace('$', '.');
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

    private record CollectionBridge(
            int parameterIndex, Type elementType, Class<?> runtimeElementType, String factoryMethod) {
    }
}
