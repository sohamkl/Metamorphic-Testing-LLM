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

public class OllamaRunner {
    // Usage:
    // java OllamaRunner <provider> <model> <inputFile> <outputFile>
    // java OllamaRunner <model> <inputFile> <outputFile> (defaults provider=openai)
    //
    // Examples:
    // java OllamaRunner ollama llama3.2:1b prompt.txt out.txt
    // java OllamaRunner openai gpt-4o-mini prompt.txt out.txt
    public static void main(String[] args) throws Exception {
        if (args.length != 3 && args.length != 4) {
            System.err.println("Usage: java OllamaRunner <provider> <model> <inputFile> <outputFile>");
            System.err.println(
                    "   or: java OllamaRunner <model> <inputFile> <outputFile>   (provider defaults to openai)");
            System.exit(2);
            return;
        }

        String provider;
        String model;
        Path inputFile;
        Path outputFile;

        if (args.length == 3) {
            provider = "ollama";
            model = args[0];
            inputFile = Path.of(args[1]);
            outputFile = Path.of(args[2]);
        } else {
            provider = args[0];
            model = args[1];
            inputFile = Path.of(args[2]);
            outputFile = Path.of(args[3]);
        }

        String prompt = Files.readString(inputFile, StandardCharsets.UTF_8);

        if (prompt == null || prompt.trim().isEmpty()) {
            System.err.println("Prompt is empty. Check input file: " + inputFile.toAbsolutePath());
            System.exit(2);
            return;
        }

        String responseText = switch (provider.trim().toLowerCase()) {
            case "ollama" -> generateWithOllama(model, prompt);
            case "openai" -> generateWithOpenAi(model, prompt);
            default -> throw new IllegalArgumentException("Unknown provider: " + provider + " (use: ollama | openai)");
        };

        Files.writeString(outputFile, responseText, StandardCharsets.UTF_8);
        System.out.println("Wrote response to: " + outputFile.toAbsolutePath());
    }

    private static String generateWithOllama(String model, String prompt) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        String body = "{"
                + "\"model\":" + jsonString(model) + ","
                + "\"prompt\":" + jsonString(prompt) + ","
                + "\"stream\":false"
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:11434/api/generate"))
                .timeout(Duration.ofMinutes(5))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        if (res.statusCode() < 200 || res.statusCode() >= 300) {
            throw new IOException("Ollama HTTP " + res.statusCode() + ": " + res.body());
        }

        // Expected JSON shape includes: {"response":"...","done":true,...}
        String response = extractJsonStringField(res.body(), "response");
        if (response == null) {
            throw new IOException("Could not parse Ollama response field from: " + res.body());
        }
        return response;
    }

    private static String generateWithOpenAi(String model, String prompt) throws IOException, InterruptedException {
        Map<String, String> env = loadDotEnv(Path.of(".env"));
        String apiKey = firstNonBlank(
                env.get("OPENAI_API_KEY"),
                System.getenv("OPENAI_API_KEY"));
        if (apiKey == null) {
            throw new IOException("Missing OPENAI_API_KEY. Put it in .env or your environment.");
        }

        String baseUrl = firstNonBlank(
                env.get("OPENAI_BASE_URL"),
                System.getenv("OPENAI_BASE_URL"),
                "https://api.openai.com/v1");

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        String body = "{"
                + "\"model\":" + jsonString(model) + ","
                + "\"messages\":[{\"role\":\"user\",\"content\":" + jsonString(prompt) + "}]"
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/chat/completions"))
                .timeout(Duration.ofMinutes(5))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        if (res.statusCode() < 200 || res.statusCode() >= 300) {
            throw new IOException("OpenAI HTTP " + res.statusCode() + ": " + res.body());
        }

        String content = extractOpenAiChatContent(res.body());
        if (content == null) {
            throw new IOException("Could not parse OpenAI response content from: " + res.body());
        }
        return content;
    }

    private static String jsonString(String s) {
        StringBuilder out = new StringBuilder();
        out.append('"');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\' -> out.append("\\\\");
                case '"' -> out.append("\\\"");
                case '\n' -> out.append("\\n");
                case '\r' -> out.append("\\r");
                case '\t' -> out.append("\\t");
                default -> {
                    if (c <= 0x1F)
                        out.append(String.format("\\u%04x", (int) c));
                    else
                        out.append(c);
                }
            }
        }
        out.append('"');
        return out.toString();
    }

    private static String extractJsonStringField(String json, String fieldName) {
        // Minimal JSON parsing for: "fieldName":"<string>"
        // Handles escapes within the JSON string value.
        String needle = "\"" + fieldName + "\":\"";
        int start = json.indexOf(needle);
        if (start < 0)
            return null;
        int i = start + needle.length();
        StringBuilder sb = new StringBuilder();
        boolean escaping = false;
        while (i < json.length()) {
            char c = json.charAt(i++);
            if (escaping) {
                switch (c) {
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u': {
                        if (i + 4 > json.length())
                            return null;
                        String hex = json.substring(i, i + 4);
                        i += 4;
                        try {
                            sb.append((char) Integer.parseInt(hex, 16));
                        } catch (NumberFormatException e) {
                            return null;
                        }
                        break;
                    }
                    default:
                        sb.append(c);
                }
                escaping = false;
                continue;
            }
            if (c == '\\') {
                escaping = true;
                continue;
            }
            if (c == '"') {
                return sb.toString();
            }
            sb.append(c);
        }
        return null;
    }

    private static String extractOpenAiChatContent(String json) {
        // Minimal parsing for: choices[0].message.content (string)
        int msgIdx = json.indexOf("\"message\"");
        if (msgIdx < 0)
            return null;

        int contentKey = json.indexOf("\"content\":\"", msgIdx);
        if (contentKey < 0)
            return null;
        int i = contentKey + "\"content\":\"".length();

        StringBuilder sb = new StringBuilder();
        boolean escaping = false;
        while (i < json.length()) {
            char c = json.charAt(i++);
            if (escaping) {
                switch (c) {
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u': {
                        if (i + 4 > json.length())
                            return null;
                        String hex = json.substring(i, i + 4);
                        i += 4;
                        try {
                            sb.append((char) Integer.parseInt(hex, 16));
                        } catch (NumberFormatException e) {
                            return null;
                        }
                        break;
                    }
                    default:
                        sb.append(c);
                }
                escaping = false;
                continue;
            }
            if (c == '\\') {
                escaping = true;
                continue;
            }
            if (c == '"') {
                return sb.toString();
            }
            sb.append(c);
        }
        return null;
    }

    private static String firstNonBlank(String... values) {
        for (String v : values) {
            if (v != null && !v.trim().isEmpty())
                return v;
        }
        return null;
    }

    private static Map<String, String> loadDotEnv(Path path) {
        Map<String, String> out = new HashMap<>();
        try {
            if (!Files.exists(path))
                return out;
            for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#"))
                    continue;
                int eq = trimmed.indexOf('=');
                if (eq <= 0)
                    continue;
                String key = trimmed.substring(0, eq).trim();
                String value = trimmed.substring(eq + 1).trim();
                if (value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2) {
                    value = value.substring(1, value.length() - 1);
                }
                out.put(key, value);
            }
        } catch (IOException ignored) {
            return out;
        }
        return out;
    }
}
