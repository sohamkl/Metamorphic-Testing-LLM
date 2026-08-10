package mtllm.sut;

import mtllm.config.PromptConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** Builds deterministic API metadata that grounds LLM input construction. */
public final class SutApiInspector {
    private SutApiInspector() {
    }

    public static String inspect(PromptConfig config) {
        try {
            URL[] urls = config.sutClasspath().stream()
                    .map(Path::toUri)
                    .map(uri -> {
                        try {
                            return uri.toURL();
                        } catch (Exception e) {
                            throw new IllegalArgumentException(e);
                        }
                    })
                    .toArray(URL[]::new);
            try (URLClassLoader loader = new URLClassLoader(urls, SutApiInspector.class.getClassLoader())) {
                Class<?> sutClass = Class.forName(
                        JavaSourceNames.qualifiedName(config.sutClassFile()), false, loader);
                Method method = TargetMethodResolver.resolve(sutClass, config.targetFunction());
                return describe(sutClass, method);
            }
        } catch (Exception e) {
            return "Automatic API inspection was attempted but could not load the target: " + e.getMessage();
        }
    }

    private static String describe(Class<?> sutClass, Method method) {
        StringBuilder out = new StringBuilder();
        out.append("Resolved SUT class: ").append(sutClass.getName()).append('\n');
        out.append("Resolved target: ").append(method.toGenericString()).append('\n');
        out.append("Invocation: ").append(Modifier.isStatic(method.getModifiers()) ? "static" : "instance").append('\n');
        out.append("Return type: ").append(method.getGenericReturnType().getTypeName()).append('\n');
        out.append("Parameters:");
        if (method.getParameterCount() == 0) {
            out.append(" none\n");
        } else {
            out.append('\n');
            for (int i = 0; i < method.getParameterCount(); i++) {
                out.append("- arg").append(i).append(": ")
                        .append(method.getGenericParameterTypes()[i].getTypeName()).append('\n');
                appendConstructionPaths(out, method.getParameterTypes()[i], "  ");
            }
        }
        if (!Modifier.isStatic(method.getModifiers())) {
            out.append("Receiver construction paths:\n");
            appendConstructionPaths(out, sutClass, "  ");
        }
        if (method.getParameterCount() != 1) {
            out.append("Randoop note: the framework will generate a typed one-input invocation wrapper for this ")
                    .append(method.getParameterCount()).append("-argument target.\n");
        }
        return out.toString().trim();
    }

    private static void appendConstructionPaths(StringBuilder out, Class<?> type, String indent) {
        List<String> paths = new ArrayList<>();
        Arrays.stream(type.getConstructors())
                .sorted(Comparator.comparingInt(Constructor::getParameterCount))
                .limit(8)
                .map(Constructor::toGenericString)
                .forEach(paths::add);
        Arrays.stream(type.getMethods())
                .filter(method -> Modifier.isStatic(method.getModifiers()))
                .filter(method -> type.isAssignableFrom(method.getReturnType()))
                .sorted(Comparator.comparing(Method::getName)
                        .thenComparingInt(Method::getParameterCount))
                .limit(12)
                .map(Method::toGenericString)
                .forEach(paths::add);
        if (paths.isEmpty()) {
            out.append(indent).append("construction: no public constructor or same-type static factory discovered\n");
            return;
        }
        out.append(indent).append("construction candidates:\n");
        for (String path : paths) {
            out.append(indent).append("- ").append(path).append('\n');
        }
    }
}
