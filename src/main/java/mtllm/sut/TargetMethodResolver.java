package mtllm.sut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** Resolves a configured target method without silently choosing the wrong overload. */
public final class TargetMethodResolver {
    private TargetMethodResolver() {
    }

    public static Method resolve(Class<?> sutClass, String targetFunction) throws NoSuchMethodException {
        String methodName = methodName(targetFunction);
        List<Method> named = Arrays.stream(sutClass.getMethods())
                .filter(method -> method.getName().equals(methodName))
                .sorted(Comparator.comparing(TargetMethodResolver::signature))
                .toList();
        if (named.isEmpty()) {
            throw new NoSuchMethodException("No public method '" + methodName + "' on " + sutClass.getName());
        }

        List<String> configuredTypes = configuredParameterTypes(targetFunction);
        if (hasParameterList(targetFunction)) {
            List<Method> exact = named.stream()
                    .filter(method -> parametersMatch(method, configuredTypes))
                    .toList();
            if (exact.size() == 1) {
                return exact.get(0);
            }
        } else if (named.size() == 1) {
            return named.get(0);
        }

        String candidates = named.stream().map(TargetMethodResolver::signature).reduce((a, b) -> a + ", " + b).orElse("");
        throw new NoSuchMethodException("Target method is ambiguous or its configured parameter types do not match. "
                + "Use a complete signature. Candidates on " + sutClass.getName() + ": " + candidates);
    }

    public static String methodName(String targetFunction) {
        String value = targetFunction == null ? "" : targetFunction.trim();
        int open = value.indexOf('(');
        String prefix = open >= 0 ? value.substring(0, open).trim() : value;
        int dot = prefix.lastIndexOf('.');
        int space = prefix.lastIndexOf(' ');
        return prefix.substring(Math.max(dot, space) + 1).trim();
    }

    public static String signature(Method method) {
        return method.getName() + "(" + Arrays.stream(method.getParameterTypes())
                .map(Class::getTypeName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("") + ")";
    }

    private static boolean parametersMatch(Method method, List<String> configuredTypes) {
        if (method.getParameterCount() != configuredTypes.size()) {
            return false;
        }
        Class<?>[] actual = method.getParameterTypes();
        for (int i = 0; i < actual.length; i++) {
            String configured = eraseGenerics(configuredTypes.get(i)).replace("...", "[]");
            String canonical = actual[i].getTypeName();
            String simple = simpleTypeName(actual[i]);
            if (!configured.equals(canonical) && !configured.equals(simple)
                    && !configured.endsWith("." + simple)) {
                return false;
            }
        }
        return true;
    }

    private static String simpleTypeName(Class<?> type) {
        return type.isArray() ? simpleTypeName(type.getComponentType()) + "[]" : type.getSimpleName();
    }

    private static List<String> configuredParameterTypes(String targetFunction) {
        if (targetFunction == null) {
            return List.of();
        }
        int open = targetFunction.indexOf('(');
        int close = targetFunction.lastIndexOf(')');
        if (open < 0 || close < open || targetFunction.substring(open + 1, close).trim().isEmpty()) {
            return List.of();
        }
        List<String> parameters = splitParameters(targetFunction.substring(open + 1, close));
        List<String> types = new ArrayList<>();
        for (String parameter : parameters) {
            String cleaned = parameter.trim().replaceAll("@\\w+(?:\\([^)]*\\))?\\s*", "")
                    .replaceAll("\\bfinal\\s+", "").trim();
            int lastSpace = cleaned.lastIndexOf(' ');
            types.add(lastSpace > 0 ? cleaned.substring(0, lastSpace).trim() : cleaned);
        }
        return types;
    }

    private static boolean hasParameterList(String targetFunction) {
        return targetFunction != null
                && targetFunction.indexOf('(') >= 0
                && targetFunction.lastIndexOf(')') > targetFunction.indexOf('(');
    }

    private static List<String> splitParameters(String input) {
        List<String> result = new ArrayList<>();
        int genericDepth = 0;
        int start = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '<') genericDepth++;
            if (ch == '>') genericDepth--;
            if (ch == ',' && genericDepth == 0) {
                result.add(input.substring(start, i));
                start = i + 1;
            }
        }
        result.add(input.substring(start));
        return result;
    }

    private static String eraseGenerics(String type) {
        StringBuilder result = new StringBuilder();
        int depth = 0;
        for (int i = 0; i < type.length(); i++) {
            char ch = type.charAt(i);
            if (ch == '<') {
                depth++;
            } else if (ch == '>') {
                depth--;
            } else if (depth == 0) {
                result.append(ch);
            }
        }
        return result.toString().trim();
    }
}
