package mtllm.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Reads local .env settings used by the prototype.
 *
 * <p>In simple terms, this loads things like OPENAI_API_KEY_OPENAI and the optional JUnit jar path
 * without requiring the user to export environment variables manually.</p>
 */
public final class DotEnv {
    private DotEnv() {
    }

    public static Map<String, String> load(Path envPath) {
        Map<String, String> values = new HashMap<>();
        if (!Files.exists(envPath)) {
            return values;
        }
        try {
            for (String line : Files.readAllLines(envPath, StandardCharsets.UTF_8)) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }
                int eq = trimmed.indexOf('=');
                if (eq <= 0) {
                    continue;
                }
                String key = trimmed.substring(0, eq).trim();
                String value = trimmed.substring(eq + 1).trim();
                values.put(key, stripQuotes(value));
            }
        } catch (IOException ignored) {
            return values;
        }
        return values;
    }

    public static String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
        }
        return "";
    }

    private static String stripQuotes(String value) {
        if (value.length() >= 2) {
            char first = value.charAt(0);
            char last = value.charAt(value.length() - 1);
            if ((first == '"' && last == '"') || (first == '\'' && last == '\'')) {
                return value.substring(1, value.length() - 1);
            }
        }
        return value;
    }
}
