package mtllm.randoop;

import mtllm.config.PromptConfig;
import mtllm.sut.JavaSourceNames;
import mtllm.sut.TargetMethodResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Generates replayable concrete classes for callback-interface target parameters. */
public final class CallbackSynthesizer {
    private static final int MAX_POLICIES_PER_INTERFACE = 32;

    private CallbackSynthesizer() {
    }

    public static Generated generate(PromptConfig config, ClassLoader loader) throws Exception {
        Class<?> sutClass = Class.forName(JavaSourceNames.qualifiedName(config.sutClassFile()), false, loader);
        Method target = TargetMethodResolver.resolve(sutClass, config.targetFunction());
        Path sourceDir = config.outputRoot().resolve("generated-support");
        Files.createDirectories(sourceDir);
        deletePreviousSources(sourceDir);

        List<Path> sources = new ArrayList<>();
        Set<String> classNames = new LinkedHashSet<>();
        Set<String> interfaces = new LinkedHashSet<>();
        for (int parameterIndex = 0; parameterIndex < target.getParameterCount(); parameterIndex++) {
            Class<?> type = target.getParameterTypes()[parameterIndex];
            InterfaceModel model = analyze(type);
            if (model == null) {
                continue;
            }
            interfaces.add(type.getName());
            List<Policy> policies = policies(model);
            String signature = sutClass.getName() + "#" + TargetMethodResolver.signature(target)
                    + "#" + parameterIndex;
            String prefix = "MtllmGenerated" + sanitize(type.getSimpleName())
                    + "Callback" + Integer.toUnsignedString(signature.hashCode(), 36);
            for (int policyIndex = 0; policyIndex < policies.size(); policyIndex++) {
                Policy policy = policies.get(policyIndex);
                String className = prefix + String.format("Policy%02d", policyIndex);
                Path source = sourceDir.resolve(className + ".java");
                Files.writeString(source, render(className, model, policy), StandardCharsets.UTF_8);
                sources.add(source);
                classNames.add(className);
            }
        }
        return new Generated(List.copyOf(sources), Set.copyOf(classNames), Set.copyOf(interfaces));
    }

    /** True when a target parameter is callback-like and can receive generated policies. */
    static boolean supports(Class<?> type) {
        return analyze(type) != null;
    }

    private static InterfaceModel analyze(Class<?> type) {
        if (!type.isInterface() || type.isAnnotation()) {
            return null;
        }
        Map<String, Method> unique = new LinkedHashMap<>();
        Arrays.stream(type.getMethods())
                .filter(method -> !Modifier.isStatic(method.getModifiers()))
                .filter(method -> Modifier.isAbstract(method.getModifiers())
                        || (method.isDefault() && method.getReturnType() != void.class))
                .sorted(Comparator.comparing(CallbackSynthesizer::methodKey))
                .forEach(method -> unique.putIfAbsent(methodKey(method), method));
        if (unique.isEmpty()) {
            return null;
        }

        List<CallbackMethod> methods = new ArrayList<>();
        boolean variableReturn = false;
        for (Method method : unique.values()) {
            List<String> values = returnValues(method.getReturnType());
            if (values == null) {
                return null;
            }
            if (values.size() > 1) {
                variableReturn = true;
            }
            methods.add(new CallbackMethod(method, values));
        }
        return variableReturn ? new InterfaceModel(type, List.copyOf(methods)) : null;
    }

    private static List<Policy> policies(InterfaceModel model) {
        List<Policy> result = new ArrayList<>();
        result.add(new Policy(null, null, -1));
        outer:
        for (CallbackMethod method : model.methods) {
            for (int valueIndex = 1; valueIndex < method.returnValues.size(); valueIndex++) {
                result.add(new Policy(method, method.returnValues.get(valueIndex), -1));
                if (result.size() >= MAX_POLICIES_PER_INTERFACE) break outer;
            }
            int numericParameter = firstNumericParameter(method.method.getParameterTypes());
            if (numericParameter >= 0 && method.returnValues.size() > 1) {
                for (int valueIndex = 1; valueIndex < method.returnValues.size(); valueIndex++) {
                    result.add(new Policy(method, method.returnValues.get(valueIndex), numericParameter));
                    if (result.size() >= MAX_POLICIES_PER_INTERFACE) break outer;
                }
            }
        }
        return List.copyOf(result);
    }

    private static String render(String className, InterfaceModel model, Policy policy) {
        StringBuilder source = new StringBuilder();
        source.append("/** Framework-generated deterministic callback policy. */\n")
                .append("public final class ").append(className).append(" implements ")
                .append(typeName(model.type)).append(" {\n");
        if (policy.thresholdParameter >= 0) {
            source.append("    private final int threshold;\n\n")
                    .append("    public ").append(className).append("(int threshold) {\n")
                    .append("        this.threshold = threshold;\n")
                    .append("    }\n\n");
        } else {
            source.append("    public ").append(className).append("() {}\n\n");
        }
        for (CallbackMethod callback : model.methods) {
            Method method = callback.method;
            source.append("    @Override\n    public ").append(typeName(method.getReturnType())).append(' ')
                    .append(method.getName()).append('(');
            Class<?>[] parameters = method.getParameterTypes();
            for (int i = 0; i < parameters.length; i++) {
                if (i > 0) source.append(", ");
                source.append(typeName(parameters[i])).append(" arg").append(i);
            }
            source.append(") {\n");
            if (method.getReturnType() == void.class) {
                source.append("        // Deliberate no-op callback policy.\n");
            } else {
                String baseline = callback.returnValues.get(0);
                if (callback == policy.method && policy.thresholdParameter >= 0) {
                    source.append("        return arg").append(policy.thresholdParameter)
                            .append(" >= threshold ? ").append(policy.returnExpression)
                            .append(" : ").append(baseline).append(";\n");
                } else if (callback == policy.method) {
                    source.append("        return ").append(policy.returnExpression).append(";\n");
                } else {
                    source.append("        return ").append(baseline).append(";\n");
                }
            }
            source.append("    }\n\n");
        }
        return source.append("}\n").toString();
    }

    private static List<String> returnValues(Class<?> type) {
        if (type == void.class) return List.of("");
        if (type == boolean.class || type == Boolean.class) return List.of("false", "true");
        if (type == byte.class || type == Byte.class) return List.of("(byte) 0", "(byte) -1", "(byte) 1");
        if (type == short.class || type == Short.class) return List.of("(short) 0", "(short) -1", "(short) 1");
        if (type == int.class || type == Integer.class) return List.of("0", "-1", "1");
        if (type == long.class || type == Long.class) return List.of("0L", "-1L", "1L");
        if (type == float.class || type == Float.class) return List.of("0.0f", "-1.0f", "1.0f");
        if (type == double.class || type == Double.class) return List.of("0.0d", "-1.0d", "1.0d");
        if (type == char.class || type == Character.class) return List.of("'\\0'", "'a'");
        if (type.isEnum()) {
            Object[] constants = type.getEnumConstants();
            if (constants == null || constants.length == 0) return null;
            return Arrays.stream(constants)
                    .map(constant -> typeName(type) + "." + ((Enum<?>) constant).name())
                    .toList();
        }
        return null;
    }

    private static int firstNumericParameter(Class<?>[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            Class<?> type = parameters[i];
            if (type == byte.class || type == short.class || type == int.class || type == long.class
                    || type == float.class || type == double.class) {
                return i;
            }
        }
        return -1;
    }

    private static void deletePreviousSources(Path sourceDir) throws Exception {
        try (var files = Files.list(sourceDir)) {
            for (Path path : files.filter(file -> file.getFileName().toString()
                    .matches("MtllmGenerated.*Callback.*Policy\\d+\\.java")).toList()) {
                Files.deleteIfExists(path);
            }
        }
    }

    private static String methodKey(Method method) {
        return method.getName() + Arrays.toString(method.getParameterTypes());
    }

    private static String sanitize(String value) {
        return value.replaceAll("[^A-Za-z0-9_$]", "");
    }

    private static String typeName(Class<?> type) {
        String canonical = type.getCanonicalName();
        return canonical == null ? type.getTypeName().replace('$', '.') : canonical;
    }

    public record Generated(List<Path> sourceFiles, Set<String> classNames, Set<String> interfaceNames) {
        public boolean isEmpty() {
            return sourceFiles.isEmpty();
        }
    }

    private record InterfaceModel(Class<?> type, List<CallbackMethod> methods) {
    }

    private record CallbackMethod(Method method, List<String> returnValues) {
    }

    private record Policy(CallbackMethod method, String returnExpression, int thresholdParameter) {
    }
}
