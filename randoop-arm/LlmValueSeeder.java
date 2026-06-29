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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Asks an LLM for domain-relevant seed values to load into Randoop's value pool.
 *
 * <p>Instead of Randoop drawing from its tiny default pool ("hi!", 0, 1, -1), the LLM
 * reads the SUT source and returns strings/integers/doubles that make sense for the domain
 * (realistic item names, quantities that exercise edge cases, prices). Randoop then builds
 * Order objects using those values. This is the "LLM seeds Randoop" hybrid:
 * LLM contributes domain knowledge, Randoop contributes volume.</p>
 *
 * <p>Self-contained: no dependency on mtllm.* packages so it compiles with the default-package
 * randoop-arm sources without needing the full Maven classpath.</p>
 */
public final class LlmValueSeeder {

    private LlmValueSeeder() {
    }

    /**
     * Ask the LLM to generate seed values appropriate for the given SUT.
     *
     * <p>SUT-agnostic: pass the source of however many classes describe the SUT (the input data
     * classes plus the method under test). Comments are stripped before sending, so the LLM must
     * INFER edge cases from the code itself rather than reading them from a developer comment.</p>
     *
     * <p>Returns a mix of String, Integer, and Double objects ready to pass to
     * {@code RandoopHarvester.harvest(timeLimitMillis, llmSeeds)}.</p>
     */
    public static List<Object> generateSeeds(String apiKey, String model, List<String> sutSources)
            throws Exception {
        StringBuilder combined = new StringBuilder();
        for (String src : sutSources) {
            if (src == null || src.isBlank()) {
                continue;
            }
            combined.append(stripComments(src)).append("\n\n");
        }
        String prompt = buildPrompt(combined.toString());
        String responseBody = callOpenAi(apiKey, model, prompt);
        String content = extractContent(responseBody);
        List<Object> seeds = parseSeeds(content);
        System.out.println("[LlmValueSeeder] LLM returned " + seeds.size() + " seed values");
        return seeds;
    }

    /**
     * Strip Java comments so the LLM cannot read edge cases out of a developer comment (e.g. a
     * "// quantities above 5 are capped" giveaway) -- it must infer them from the code. Naive: it
     * also strips any {@code //} or block comment that appears inside a string literal, which is
     * harmless for value seeding.
     */
    static String stripComments(String source) {
        String noBlock = source.replaceAll("(?s)/\\*.*?\\*/", "");
        return noBlock.replaceAll("(?m)//.*$", "");
    }

    /**
     * Read a KEY=value entry from a .env file, searching the current directory and up to
     * 5 parent directories. Handles running from worktrees where .env lives at the repo root.
     */
    static String readEnvValue(String key) throws IOException {
        Path dir = Path.of(".").toAbsolutePath().normalize();
        for (int i = 0; i < 6; i++) {
            Path envFile = dir.resolve(".env");
            if (Files.exists(envFile)) {
                for (String line : Files.readAllLines(envFile, StandardCharsets.UTF_8)) {
                    String trimmed = line.trim();
                    if (trimmed.startsWith(key + "=")) {
                        return trimmed.substring(key.length() + 1).strip();
                    }
                }
            }
            Path parent = dir.getParent();
            if (parent == null) break;
            dir = parent;
        }
        return "";
    }

    private static String buildPrompt(String sutSource) {
        return "You are seeding the value pool of a random test generator (Randoop) for a Java "
                + "system under test. Randoop builds objects by calling the constructors and "
                + "methods below, drawing the argument values from a pool. Fill that pool with "
                + "values likely to exercise the edge cases and boundary conditions you can INFER "
                + "from the code.\n\n"
                + "Source under test:\n```java\n" + sutSource + "\n```\n\n"
                + "Return a JSON object with exactly three keys:\n"
                + "  \"strings\"  — at least 15 String values appropriate for this code: typical "
                + "values plus edge cases (empty string, single character, very long, special "
                + "characters).\n"
                + "  \"integers\" — at least 15 int values: typical values PLUS boundary values "
                + "you infer from the code. Inspect every comparison, cap, limit, or threshold in "
                + "the logic (e.g. a Math.min/Math.max, a < or > test, a magic constant) and "
                + "include values just below, exactly at, and just above each one.\n"
                + "  \"doubles\"  — at least 15 double values: typical values plus boundaries "
                + "(0.0, a very small value, a very large value, and any thresholds in the code).\n\n"
                + "Base every boundary choice on the logic in the code above, not on generic "
                + "defaults. Return ONLY the JSON object. No explanation, no markdown fences.";
    }

    private static String callOpenAi(String apiKey, String model, String prompt) throws Exception {
        String payload = "{"
                + "\"model\":" + jsonQuote(model) + ","
                + "\"messages\":[{\"role\":\"user\",\"content\":" + jsonQuote(prompt) + "}],"
                + "\"temperature\":0.3"
                + "}";

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> resp = client.send(request,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        if (resp.statusCode() < 200 || resp.statusCode() >= 300) {
            throw new IllegalStateException(
                    "OpenAI HTTP " + resp.statusCode() + ": " + resp.body());
        }
        return resp.body();
    }

    /**
     * Character-scanner extraction of the "content" field from an OpenAI chat response.
     * Handles escape sequences without needing a JSON library.
     */
    static String extractContent(String responseBody) {
        int idx = responseBody.indexOf("\"content\"");
        if (idx < 0) {
            throw new IllegalStateException("No content field in OpenAI response: " + responseBody);
        }
        // Skip to the opening quote of the content value
        int q = responseBody.indexOf('"', idx + 9);
        if (q < 0) {
            throw new IllegalStateException("Malformed content field in OpenAI response");
        }
        q++; // first character of content
        StringBuilder sb = new StringBuilder();
        int i = q;
        while (i < responseBody.length()) {
            char c = responseBody.charAt(i);
            if (c == '"') break;
            if (c == '\\' && i + 1 < responseBody.length()) {
                char esc = responseBody.charAt(++i);
                switch (esc) {
                    case '"':  sb.append('"');  break;
                    case '\\': sb.append('\\'); break;
                    case 'n':  sb.append('\n'); break;
                    case 'r':  sb.append('\r'); break;
                    case 't':  sb.append('\t'); break;
                    default:   sb.append(esc);  break;
                }
            } else {
                sb.append(c);
            }
            i++;
        }
        return sb.toString();
    }

    private static List<Object> parseSeeds(String json) {
        List<Object> seeds = new ArrayList<>();

        String stringsContent = extractArrayContent(json, "strings");
        if (stringsContent != null) {
            Matcher m = Pattern.compile("\"((?:[^\"\\\\]|\\\\.)*)\"").matcher(stringsContent);
            while (m.find()) {
                seeds.add(unescapeJson(m.group(1)));
            }
        }

        String integersContent = extractArrayContent(json, "integers");
        if (integersContent != null) {
            Matcher m = Pattern.compile("-?\\d+").matcher(integersContent);
            while (m.find()) {
                try {
                    seeds.add(Integer.parseInt(m.group()));
                } catch (NumberFormatException ignored) {
                }
            }
        }

        String doublesContent = extractArrayContent(json, "doubles");
        if (doublesContent != null) {
            Matcher m = Pattern.compile("-?\\d+\\.\\d+").matcher(doublesContent);
            while (m.find()) {
                try {
                    seeds.add(Double.parseDouble(m.group()));
                } catch (NumberFormatException ignored) {
                }
            }
        }

        return seeds;
    }

    private static String extractArrayContent(String json, String key) {
        int idx = json.indexOf("\"" + key + "\"");
        if (idx < 0) return null;
        int bracket = json.indexOf('[', idx);
        if (bracket < 0) return null;
        int end = json.indexOf(']', bracket);
        if (end < 0) return null;
        return json.substring(bracket + 1, end);
    }

    private static String unescapeJson(String s) {
        return s.replace("\\\"", "\"")
                .replace("\\\\", "\\")
                .replace("\\n", "\n")
                .replace("\\t", "\t");
    }

    private static String jsonQuote(String s) {
        return "\"" + s.replace("\\", "\\\\")
                        .replace("\"", "\\\"")
                        .replace("\n", "\\n")
                        .replace("\r", "\\r") + "\"";
    }
}
