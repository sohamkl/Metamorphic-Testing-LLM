package mtllm.randoop;

import mtllm.llm.LlmClient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Asks an LLM for domain-relevant seed values to load into Randoop's value pool.
 *
 * <p>Instead of Randoop drawing from its tiny default pool ("hi!", 0, 1, -1), the LLM reads the
 * SUT source and returns strings/integers/doubles that exercise the domain's edge cases; Randoop
 * then builds objects from those values. This is the "LLM seeds Randoop" hybrid: the LLM
 * contributes semantic knowledge, Randoop contributes volume.</p>
 *
 * <p>This is the pipeline-integrated version: it sends the prompt through the shared
 * {@link LlmClient} (so model / auth / base URL match the rest of the backend) rather than making
 * its own OpenAI call.</p>
 */
public final class LlmValueSeeder {

    private LlmValueSeeder() {
    }

    /**
     * Ask the LLM for seed values from the developer-written {@code InputDomain} description.
     *
     * <p>This is the default product path: the developer's domain description is concise,
     * authoritative, and bounded regardless of how large the SUT is. The LLM's job is to expand
     * that prose into the concrete typed values (strings/ints/doubles) Randoop needs for its value
     * pool. Returns a mix of String/Integer/Double objects ready for
     * {@link RandoopHarvester#harvestSequences(int, List, long)}.</p>
     */
    public static List<Object> generateSeedsFromDomain(LlmClient llm, String inputDomain) throws Exception {
        String content = llm.complete(buildDomainPrompt(inputDomain));
        List<Object> seeds = parseSeeds(content);
        System.out.println("[LlmValueSeeder] (input-domain) LLM returned " + seeds.size() + " seed values");
        return seeds;
    }

    /**
     * Ask the LLM for seed values by reading the SUT source code (fallback when no
     * {@code InputDomain} is provided).
     *
     * <p>Comments are stripped before sending, so the LLM must INFER edge cases from the code
     * (comparisons, caps, thresholds) rather than reading them from a developer comment. Doesn't
     * scale to very large SUTs, but needs no developer description. This is also the mode the
     * measurement experiment uses to test the "LLM infers boundaries from code" claim.</p>
     */
    public static List<Object> generateSeedsFromCode(LlmClient llm, List<String> sutSources) throws Exception {
        StringBuilder combined = new StringBuilder();
        for (String src : sutSources) {
            if (src == null || src.isBlank()) {
                continue;
            }
            combined.append(stripComments(src)).append("\n\n");
        }
        String content = llm.complete(buildCodePrompt(combined.toString()));
        List<Object> seeds = parseSeeds(content);
        System.out.println("[LlmValueSeeder] (source-code) LLM returned " + seeds.size() + " seed values");
        return seeds;
    }

    /**
     * Strip Java comments so the LLM cannot read edge cases out of a developer comment (e.g. a
     * "// quantities above 5 are capped" giveaway) -- it must infer them from the code itself.
     */
    static String stripComments(String source) {
        String noBlock = source.replaceAll("(?s)/\\*.*?\\*/", "");
        return noBlock.replaceAll("(?m)//.*$", "");
    }

    private static String buildDomainPrompt(String inputDomain) {
        return "You are seeding the value pool of a random test generator (Randoop) for a Java "
                + "system under test. Randoop builds objects by calling constructors and methods, "
                + "drawing the argument values from a pool. Fill that pool with values that cover "
                + "the input domain described by the developer below — both the typical cases and "
                + "the edge/boundary cases it implies.\n\n"
                + "Developer's input-domain description:\n\"\"\"\n" + inputDomain + "\n\"\"\"\n\n"
                + "Return a JSON object with exactly three keys:\n"
                + "  \"strings\"  — at least 15 String values fitting the domain: typical values plus "
                + "edge cases (empty string, single character, very long, special characters).\n"
                + "  \"integers\" — at least 15 int values: typical values PLUS the boundaries the "
                + "domain implies (e.g. if it mentions zero, negatives, or 'larger' values, include "
                + "0, small, and clearly-large values, and anything just below/at/above a stated limit).\n"
                + "  \"doubles\"  — at least 15 double values: typical values plus boundaries "
                + "(0.0, a very small value, a very large value, and any threshold the domain names).\n\n"
                + "Base every choice on the domain description above. Return ONLY the JSON object. "
                + "No explanation, no markdown fences.";
    }

    private static String buildCodePrompt(String sutSource) {
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
}
