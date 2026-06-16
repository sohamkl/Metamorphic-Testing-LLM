package mtllm.generation;

import mtllm.config.PromptConfig;
import mtllm.util.JsonUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Writes JUnit test classes from executed JSON case entries.
 *
 * <p>In simple terms, this makes the backend turn the same JSON cases shown in
 * reports into runnable JUnit tests, so JSON, tests, and reports stay aligned.</p>
 */
public final class JsonBackedJUnitWriter {
    private JsonBackedJUnitWriter() {
    }

    public static Result write(
            Path outputDir,
            PromptConfig config,
            List<String> passingEntries,
            List<String> failingEntries) throws IOException {
        Files.createDirectories(outputDir);

        String baseName = baseName(config.generatedClassName());
        String passingClassName = baseName + "PassingTest";
        String failingClassName = baseName + "FailingTest";

        List<CaseMethod> passingMethods = caseMethods(passingEntries, "passing");
        List<CaseMethod> failingMethods = caseMethods(failingEntries, "failing");

        Path passingFile = outputDir.resolve(passingClassName + ".java");
        Path failingFile = outputDir.resolve(failingClassName + ".java");

        Files.writeString(
                passingFile,
                renderClass(passingClassName, config, passingMethods),
                StandardCharsets.UTF_8);
        Files.writeString(
                failingFile,
                renderClass(failingClassName, config, failingMethods),
                StandardCharsets.UTF_8);

        return new Result(
                passingFile,
                failingFile,
                passingMethods.stream().map(CaseMethod::name).toList(),
                failingMethods.stream().map(CaseMethod::name).toList());
    }

    private static List<CaseMethod> caseMethods(List<String> entries, String fallbackPrefix) {
        List<CaseMethod> methods = new ArrayList<>();
        Set<String> usedNames = new LinkedHashSet<>();
        for (int i = 0; i < entries.size(); i++) {
            String entry = entries.get(i);
            String label = unquote(fieldValue(entry, "label"));
            String methodName = uniqueMethodName(label, fallbackPrefix, i + 1, usedNames);
            methods.add(new CaseMethod(methodName, fieldValue(entry, "source")));
        }
        return methods;
    }

    private static String uniqueMethodName(String label, String fallbackPrefix, int index, Set<String> usedNames) {
        String base = toMethodName(label);
        if (base.isBlank()) {
            base = "test" + capitalize(fallbackPrefix) + "Case" + String.format("%03d", index);
        }
        String candidate = base;
        int suffix = 2;
        while (usedNames.contains(candidate)) {
            candidate = base + suffix;
            suffix++;
        }
        usedNames.add(candidate);
        return candidate;
    }

    private static String toMethodName(String label) {
        if (label == null || label.isBlank() || "not provided".equals(label)) {
            return "";
        }

        StringBuilder name = new StringBuilder("test");
        boolean capitalizeNext = true;
        for (int i = 0; i < label.length(); i++) {
            char c = label.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                if (capitalizeNext) {
                    name.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    name.append(c);
                }
            } else {
                capitalizeNext = true;
            }
        }
        return name.length() == "test".length() ? "" : name.toString();
    }

    private static String renderClass(String className, PromptConfig config, List<CaseMethod> methods) {
        StringBuilder out = new StringBuilder();
        out.append("import org.junit.jupiter.api.Test;\n\n");
        out.append("public class ").append(className).append(" {\n");
        for (CaseMethod method : methods) {
            out.append("\n");
            out.append("    @Test\n");
            out.append("    public void ").append(method.name()).append("() {\n");
            out.append("        double[][] source = ").append(toJavaSourceLiteral(method.sourceJson())).append(";\n");
            out.append("        int sourceOutput = ").append(config.targetFunction()).append("(source);\n");
            out.append("        double[][] followUp = ").append(config.developerFollowUpMethod()).append("(source);\n");
            out.append("        int followUpOutput = ").append(config.targetFunction()).append("(followUp);\n");
            out.append("        ").append(config.developerAssertMethod()).append("(sourceOutput, followUpOutput);\n");
            out.append("    }\n");
        }
        out.append("}\n");
        return out.toString();
    }

    private static String toJavaSourceLiteral(String jsonValue) {
        String trimmed = jsonValue == null ? "" : jsonValue.trim();
        if (trimmed.startsWith("[[")) {
            return "new double[][] " + arrayInitializer(trimmed);
        }
        if (trimmed.startsWith("[")) {
            return "new double[] " + arrayInitializer(trimmed);
        }
        return trimmed;
    }

    private static String arrayInitializer(String jsonArray) {
        StringBuilder out = new StringBuilder();
        boolean inString = false;
        boolean escaping = false;
        for (int i = 0; i < jsonArray.length(); i++) {
            char c = jsonArray.charAt(i);
            if (escaping) {
                out.append(c);
                escaping = false;
                continue;
            }
            if (c == '\\' && inString) {
                out.append(c);
                escaping = true;
                continue;
            }
            if (c == '"') {
                inString = !inString;
                out.append(c);
                continue;
            }
            if (!inString && c == '[') {
                out.append('{');
            } else if (!inString && c == ']') {
                out.append('}');
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    private static String fieldValue(String json, String key) {
        int keyStart = findTopLevelKey(json, key);
        if (keyStart < 0) {
            return "not provided";
        }
        int colon = json.indexOf(':', keyStart);
        if (colon < 0) {
            return "not provided";
        }
        int valueStart = skipWhitespace(json, colon + 1);
        int valueEnd = findValueEnd(json, valueStart);
        if (valueStart < 0 || valueEnd <= valueStart) {
            return "not provided";
        }
        return json.substring(valueStart, valueEnd).trim();
    }

    private static int findTopLevelKey(String json, String key) {
        String quotedKey = "\"" + key + "\"";
        int depth = 0;
        boolean inString = false;
        boolean escaping = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (escaping) {
                escaping = false;
                continue;
            }
            if (c == '\\' && inString) {
                escaping = true;
                continue;
            }
            if (c == '"') {
                if (!inString && depth == 1 && json.startsWith(quotedKey, i)) {
                    return i;
                }
                inString = !inString;
                continue;
            }
            if (inString) {
                continue;
            }
            if (c == '{' || c == '[') {
                depth++;
            } else if (c == '}' || c == ']') {
                depth--;
            }
        }
        return -1;
    }

    private static int skipWhitespace(String text, int index) {
        int current = index;
        while (current < text.length() && Character.isWhitespace(text.charAt(current))) {
            current++;
        }
        return current;
    }

    private static int findValueEnd(String json, int valueStart) {
        if (valueStart >= json.length()) {
            return -1;
        }
        char first = json.charAt(valueStart);
        if (first == '{' || first == '[') {
            return findStructuredValueEnd(json, valueStart);
        }
        if (first == '"') {
            return findStringEnd(json, valueStart);
        }
        int index = valueStart;
        while (index < json.length() && json.charAt(index) != ',' && json.charAt(index) != '}') {
            index++;
        }
        return index;
    }

    private static int findStructuredValueEnd(String json, int valueStart) {
        int depth = 0;
        boolean inString = false;
        boolean escaping = false;
        for (int i = valueStart; i < json.length(); i++) {
            char c = json.charAt(i);
            if (escaping) {
                escaping = false;
                continue;
            }
            if (c == '\\' && inString) {
                escaping = true;
                continue;
            }
            if (c == '"') {
                inString = !inString;
                continue;
            }
            if (inString) {
                continue;
            }
            if (c == '{' || c == '[') {
                depth++;
            } else if (c == '}' || c == ']') {
                depth--;
                if (depth == 0) {
                    return i + 1;
                }
            }
        }
        return json.length();
    }

    private static int findStringEnd(String json, int valueStart) {
        boolean escaping = false;
        for (int i = valueStart + 1; i < json.length(); i++) {
            char c = json.charAt(i);
            if (escaping) {
                escaping = false;
            } else if (c == '\\') {
                escaping = true;
            } else if (c == '"') {
                return i + 1;
            }
        }
        return json.length();
    }

    private static String unquote(String value) {
        if (value == null || value.length() < 2 || value.charAt(0) != '"') {
            return value == null ? "" : value;
        }
        return JsonUtil.extractOpenAiContent("{\"message\":{\"content\":" + value + "}}");
    }

    private static String baseName(String generatedClassName) {
        if (generatedClassName.endsWith("Data")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Data".length());
        }
        if (generatedClassName.endsWith("Test")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Test".length());
        }
        return generatedClassName;
    }

    private static String capitalize(String value) {
        if (value == null || value.isBlank()) {
            return "";
        }
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    public record Result(
            Path passingFile,
            Path failingFile,
            List<String> passingMethodNames,
            List<String> failingMethodNames) {
    }

    private record CaseMethod(String name, String sourceJson) {
    }
}
