import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class OpenaiRunner {
    public static void main(String[] args) {
        try {
            String prompt = Files.readString(Path.of("prompt.txt"), StandardCharsets.UTF_8).trim();
            if (prompt.isEmpty()) {
                throw new RuntimeException("prompt.txt is empty.");
            }

            Map<String, String> env = loadDotEnv(Path.of(".env"));
            String apiKey = firstNonBlank(System.getenv("OPENAI_API_KEY"), env.get("OPENAI_API_KEY"), "");
            if (apiKey.isEmpty()) {
                throw new RuntimeException("Missing OPENAI_API_KEY. Put it in .env or environment.");
            }

            String model = firstNonBlank(System.getenv("OPENAI_MODEL"), env.get("OPENAI_MODEL"), "gpt-4o-mini");
            String baseUrl = firstNonBlank(System.getenv("OPENAI_BASE_URL"), env.get("OPENAI_BASE_URL"),
                    "https://api.openai.com/v1");

            String payload = "{"
                    + "\"model\":" + jsonQuoted(model) + ","
                    + "\"messages\":[{\"role\":\"user\",\"content\":" + jsonQuoted(prompt) + "}]"
                    + "}";

            HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(15)).build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/chat/completions"))
                    .timeout(Duration.ofMinutes(5))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException("OpenAI HTTP Error: " + response.statusCode() + " - " + response.body());
            }

            String output = extractOpenAiContent(response.body());
            if (output == null) {
                throw new RuntimeException("Could not extract response content from OpenAI output.");
            }

            Files.writeString(Path.of("out.txt"), output, StandardCharsets.UTF_8);
            System.out.println("Wrote OpenAI response to out.txt");
        } catch (Exception e) {
            System.err.println("OpenaiRunner failed: " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    private static String extractOpenAiContent(String json) {
        int messageIdx = json.indexOf("\"message\"");
        if (messageIdx < 0) {
            return null;
        }
        int contentIdx = json.indexOf("\"content\":\"", messageIdx);
        if (contentIdx < 0) {
            return null;
        }
        int start = contentIdx + "\"content\":\"".length();
        return parseJsonStringLiteral(json, start);
    }

    private static String parseJsonStringLiteral(String json, int startIndex) {
        int i = startIndex;
        StringBuilder out = new StringBuilder();
        boolean escaping = false;

        while (i < json.length()) {
            char c = json.charAt(i++);
            if (escaping) {
                switch (c) {
                    case '"' -> out.append('"');
                    case '\\' -> out.append('\\');
                    case '/' -> out.append('/');
                    case 'b' -> out.append('\b');
                    case 'f' -> out.append('\f');
                    case 'n' -> out.append('\n');
                    case 'r' -> out.append('\r');
                    case 't' -> out.append('\t');
                    case 'u' -> {
                        if (i + 4 > json.length()) {
                            return null;
                        }
                        String hex = json.substring(i, i + 4);
                        i += 4;
                        try {
                            out.append((char) Integer.parseInt(hex, 16));
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    }
                    default -> out.append(c);
                }
                escaping = false;
                continue;
            }
            if (c == '\\') {
                escaping = true;
                continue;
            }
            if (c == '"') {
                return out.toString();
            }
            out.append(c);
        }
        return null;
    }

    private static String jsonQuoted(String s) {
        return "\"" + jsonEscape(s) + "\"";
    }

    private static String jsonEscape(String s) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\' -> out.append("\\\\");
                case '"' -> out.append("\\\"");
                case '\n' -> out.append("\\n");
                case '\r' -> out.append("\\r");
                case '\t' -> out.append("\\t");
                default -> {
                    if (c <= 0x1F) {
                        out.append(String.format("\\u%04x", (int) c));
                    } else {
                        out.append(c);
                    }
                }
            }
        }
        return out.toString();
    }

    private static Map<String, String> loadDotEnv(Path envPath) {
        Map<String, String> values = new HashMap<>();
        try {
            if (!Files.exists(envPath)) {
                return values;
            }
            for (String line : Files.readAllLines(envPath, StandardCharsets.UTF_8)) {
                String t = line.trim();
                if (t.isEmpty() || t.startsWith("#")) {
                    continue;
                }
                int eq = t.indexOf('=');
                if (eq <= 0) {
                    continue;
                }
                String key = t.substring(0, eq).trim();
                String value = t.substring(eq + 1).trim();
                values.put(key, value);
            }
        } catch (IOException ignored) {
            return values;
        }
        return values;
    }

    private static String firstNonBlank(String... values) {
        for (String v : values) {
            if (v != null && !v.trim().isEmpty()) {
                return v.trim();
            }
        }
        return "";
    }
}
