import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OllamaRunner {
    private static final int MAX_SOURCE_CHARS = 30000;

    public static void main(String[] args) throws Exception {
        Path promptPath = Path.of("prompt.txt");
        if (!Files.isRegularFile(promptPath)) {
            throw new RuntimeException("Missing prompt.txt (run from project root).");
        }
        List<String> lines = Files.readAllLines(promptPath, StandardCharsets.UTF_8);
        PromptConfig cfg = parsePromptConfig(lines);
        String constructedPrompt = buildPromptFromConfig(cfg, Path.of("").toAbsolutePath().normalize());

        System.out.println("Sending prompt to Ollama...");

        // --- STEP 3: Send HTTP POST Request to Ollama ---
        String payload = "{"
                + "\"model\":" + jsonQuoted(cfg.model) + ","
                + "\"prompt\":" + jsonQuoted(constructedPrompt) + ","
                + "\"stream\":false"
                + "}";

        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:11434/api/generate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        if (res.statusCode() != 200) {
            throw new RuntimeException("HTTP Error: " + res.statusCode() + " - " + res.body());
        }

        // --- STEP 4: Extract the generated text from Ollama's JSON response ---
        String extractedJson = extractJsonStringField(res.body(), "response");
        if (extractedJson == null) {
            throw new RuntimeException("Could not find 'response' field in Ollama output.");
        }
        extractedJson = stripMarkdownFences(extractedJson);

        Files.writeString(Path.of("out.txt"), extractedJson, StandardCharsets.UTF_8);
        System.out.println("Wrote extracted LLM response to out.txt\n");

        // --- STEP 5: Parse the test arrays and run them ---
        System.out.println("Running Metamorphic Tests...");
        
        Pattern arrayPattern = Pattern.compile("\"source\"\\s*:\\s*\\[(.*?)\\]\\s*,\\s*\"followUp\"\\s*:\\s*\\[(.*?)\\]", Pattern.DOTALL);
        Matcher arrayMatcher = arrayPattern.matcher(extractedJson);

        int passed = 0;
        int total = 0;

        while (arrayMatcher.find()) {
            total++;
            int[] source = parseArray(arrayMatcher.group(1));
            int[] followUp = parseArray(arrayMatcher.group(2));

            // Execute the actual sorting method against both arrays
            int[] sortedSource = SortUtil.sortArray(source);
            int[] sortedFollowUp = SortUtil.sortArray(followUp);

            boolean isPass = Arrays.equals(sortedSource, sortedFollowUp);
            
            if (isPass) {
                passed++;
                System.out.println("[PASS] Source: " + Arrays.toString(source) + " -> FollowUp: " + Arrays.toString(followUp));
            } else {
                System.out.println("[FAIL] Source: " + Arrays.toString(source) + " -> FollowUp: " + Arrays.toString(followUp));
                System.out.println("       Sorted Source: " + Arrays.toString(sortedSource));
                System.out.println("       Sorted FollowUp: " + Arrays.toString(sortedFollowUp));
            }
        }

        // --- STEP 6: Report final results ---
        System.out.println("\n--- Test Summary ---");
        if (total == 0) {
            System.out.println("Warning: No tests were generated/parsed successfully.");
        } else {
            System.out.println("Total tests parsed: " + total);
            System.out.println("Passed: " + passed);
            System.out.println("Failed: " + (total - passed));
        }
    }

    private static PromptConfig parsePromptConfig(List<String> lines) {
        PromptConfig cfg = new PromptConfig();
        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }
            int idx = line.indexOf(':');
            if (idx <= 0) {
                continue;
            }
            String key = line.substring(0, idx).trim();
            String value = line.substring(idx + 1).trim();
            switch (key) {
                case "Model" -> cfg.model = value;
                case "SUTClassFile" -> cfg.sutClassFile = value;
                case "TargetFunction" -> cfg.targetFunction = value;
                case "SUTSupportFiles" -> cfg.sutSupportFiles = value;
                case "SUT" -> cfg.sutDescription = value;
                case "MR" -> cfg.mr = value;
                case "Count" -> cfg.count = value;
                case "DataType" -> cfg.dataType = value;
                case "InputDomain" -> cfg.constraints = value;
                case "Constraints" -> cfg.constraints = value; // backward compatibility
                default -> {
                    // ignore unknown keys
                }
            }
        }
        return cfg;
    }

    private static String buildPromptFromConfig(PromptConfig cfg, Path repoRoot) {
        String sutSection;
        if (!cfg.sutClassFile.isBlank()) {
            Path classPath = resolveUserPath(cfg.sutClassFile, repoRoot);
            String classSource = readFileWithLimit(classPath, MAX_SOURCE_CHARS, "SUTClassFile");
            StringBuilder sut = new StringBuilder();
            sut.append("System Under Test (class-level):\n")
                    .append("SUT class file: ").append(classPath).append("\n");
            if (!cfg.targetFunction.isBlank()) {
                sut.append("Target function: ").append(cfg.targetFunction).append("\n");
            }
            sut.append("SUT class source:\n")
                    .append("```java\n")
                    .append(classSource)
                    .append("\n```\n");
            if (!cfg.sutSupportFiles.isBlank()) {
                for (String part : cfg.sutSupportFiles.split(",")) {
                    String p = part.trim();
                    if (p.isEmpty()) {
                        continue;
                    }
                    Path support = resolveUserPath(p, repoRoot);
                    String supportSource = readFileWithLimit(support, MAX_SOURCE_CHARS / 2, "SUTSupportFiles");
                    sut.append("Support source from ").append(support).append(":\n")
                            .append("```java\n")
                            .append(supportSource)
                            .append("\n```\n");
                }
            }
            sutSection = sut.toString();
        } else {
            // Backward compatibility: preserve brief SUT description if class file isn't supplied.
            String fallback = cfg.sutDescription.isBlank()
                    ? "A Java class-level SUT for metamorphic testing."
                    : cfg.sutDescription;
            sutSection = "System Under Test:\n" + fallback + "\n";
        }

        return sutSection
                + "Metamorphic Relation: " + cfg.mr + "\n"
                + "Task: Generate exactly " + cfg.count + " edge-case test pairs.\n"
                + "Input/Output type: " + cfg.dataType + "\n"
                + "Input domain: " + cfg.constraints + "\n"
                + "Output ONLY valid JSON in this exact schema: "
                + "[ { \"source\": [1, 2], \"followUp\": [2, 1] } ] "
                + "No markdown, no conversational text, no Java code.";
    }

    private static Path resolveUserPath(String raw, Path repoRoot) {
        if (raw.isBlank()) {
            throw new RuntimeException("Empty path provided.");
        }
        Path p = Path.of(raw);
        return p.isAbsolute() ? p.normalize() : repoRoot.resolve(p).normalize();
    }

    private static String readFileWithLimit(Path path, int maxChars, String label) {
        try {
            if (!Files.isRegularFile(path)) {
                throw new RuntimeException(label + " file not found: " + path);
            }
            String content = Files.readString(path, StandardCharsets.UTF_8);
            if (content.isBlank()) {
                throw new RuntimeException(label + " file is empty: " + path);
            }
            if (content.length() > maxChars) {
                return content.substring(0, maxChars)
                        + "\n// ... truncated for prompt size (" + content.length() + " chars total)";
            }
            return content;
        } catch (IOException e) {
            throw new RuntimeException("Unable to read " + label + " file: " + path + " (" + e.getMessage() + ")");
        }
    }

    private static String extractJsonStringField(String json, String fieldName) {
        String needle = "\"" + fieldName + "\":\"";
        int start = json.indexOf(needle);
        if (start < 0) {
            return null;
        }
        return parseJsonStringLiteral(json, start + needle.length());
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

    private static String stripMarkdownFences(String s) {
        String t = s.trim();
        if (t.startsWith("```")) {
            int firstNewline = t.indexOf('\n');
            t = firstNewline >= 0 ? t.substring(firstNewline + 1).trim() : t.substring(3).trim();
        }
        if (t.endsWith("```")) {
            t = t.substring(0, t.length() - 3).trim();
        }
        return t;
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

    private static final class PromptConfig {
        String model = "llama3";
        String sutClassFile = "";
        String targetFunction = "";
        String sutSupportFiles = "";
        String sutDescription = "";
        String mr = "Permutation";
        String count = "5";
        String dataType = "int[]";
        String constraints = "Return valid JSON only.";
    }

    // Helper method to convert comma-separated string into int[] array
    private static int[] parseArray(String innerContent) {
        if (innerContent == null || innerContent.trim().isEmpty()) {
            return new int[0];
        }
        String[] parts = innerContent.split(",");
        List<Integer> list = new ArrayList<>();
        for (String p : parts) {
            String trimmed = p.trim();
            if (!trimmed.isEmpty()) {
                list.add(Integer.parseInt(trimmed));
            }
        }
        
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
