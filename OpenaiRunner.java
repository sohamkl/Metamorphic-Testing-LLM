import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Reads prompt.txt config, asks an LLM to generate test-data-producing Java code,
// compiles and runs it, and writes the JSON output to out.txt.
// If the generated code fails to compile/run/validate, it feeds the error back
// to the LLM and retries (up to MaxRepairAttempts).
public class OpenaiRunner {

    // Cap how much SUT source we embed in the prompt to avoid token limits
    private static final int MAX_SOURCE_CHARS = 30000;

    public static void main(String[] args) {
        try {
            // Load config
            Path promptPath = Path.of("prompt.txt");
            if (!Files.isRegularFile(promptPath)) {
                throw new RuntimeException("Missing prompt.txt (run from project root).");
            }
            List<String> lines = Files.readAllLines(promptPath, StandardCharsets.UTF_8);
            if (lines.isEmpty()) {
                throw new RuntimeException("prompt.txt is empty.");
            }

            Map<String, String> env = loadDotEnv(Path.of(".env"));
            String apiKey = firstNonBlank(System.getenv("OPENAI_API_KEY"), env.get("OPENAI_API_KEY"), "");
            if (apiKey.isEmpty()) {
                throw new RuntimeException("Missing OPENAI_API_KEY. Put it in .env or environment.");
            }
            String model = firstNonBlank(System.getenv("OPENAI_MODEL"), env.get("OPENAI_MODEL"), "gpt-5-mini");
            String baseUrl = firstNonBlank(System.getenv("OPENAI_BASE_URL"), env.get("OPENAI_BASE_URL"),
                    "https://api.openai.com/v1");

            PromptConfig cfg = parsePromptConfig(lines);
            Path repoRoot = Path.of("").toAbsolutePath().normalize();
            int expectedCount = Integer.parseInt(cfg.count);
            boolean expectFollowUp = cfg.level.equals("inputs-and-followup");
            List<Path> sutFiles = collectSutFiles(cfg, repoRoot);

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(15))
                    .build();

            // Repair loop
            String currentPrompt = buildPrompt(cfg, repoRoot);
            String generatedCode = null;
            String lastOutput = "";
            boolean success = false;

            for (int attempt = 1; attempt <= cfg.maxRepairAttempts; attempt++) {
                System.out.println("\n========== Attempt " + attempt + " / " + cfg.maxRepairAttempts
                        + " ==========");

                // Call LLM
                String llmResponse = callLlm(client, baseUrl, apiKey, model, currentPrompt);
                generatedCode = stripMarkdownFences(llmResponse);

                // Write generated file
                Path generatedFile = repoRoot.resolve(cfg.generatedClassName + ".java");
                Files.writeString(generatedFile, generatedCode, StandardCharsets.UTF_8);
                System.out.println("Wrote " + generatedFile.getFileName());
                System.out.println("\n--- Generated source ---");
                System.out.println(generatedCode);
                System.out.println("--- End ---\n");

                // Compile
                RunResult compileResult = compile(generatedFile, sutFiles, repoRoot);
                if (!compileResult.success) {
                    lastOutput = "COMPILE ERROR:\n" + compileResult.output;
                    System.out.println(lastOutput);
                    if (attempt < cfg.maxRepairAttempts) {
                        currentPrompt = buildRepairPrompt(cfg, repoRoot, generatedCode,
                                compileResult.output, "compilation");
                    }
                    continue;
                }
                System.out.println("Compilation successful.");

                // Run
                RunResult runResult = run(cfg.generatedClassName, repoRoot);
                if (!runResult.success) {
                    lastOutput = "RUNTIME ERROR:\n" + runResult.output;
                    System.out.println(lastOutput);
                    if (attempt < cfg.maxRepairAttempts) {
                        currentPrompt = buildRepairPrompt(cfg, repoRoot, generatedCode,
                                runResult.output, "execution");
                    }
                    continue;
                }

                lastOutput = runResult.output.trim();
                System.out.println("Output:\n" + lastOutput);

                // Validate the JSON output
                String error = validateJsonOutput(lastOutput, expectedCount, expectFollowUp);
                if (error != null) {
                    System.out.println("Validation failed: " + error);
                    if (attempt < cfg.maxRepairAttempts) {
                        currentPrompt = buildRepairPrompt(cfg, repoRoot, generatedCode,
                                error, "output validation");
                    }
                    continue;
                }

                success = true;
                System.out.println("\nSuccess! Generated " + expectedCount + "+ test data entries.");
                break;
            }

            // Write final output
            Files.writeString(repoRoot.resolve("out.txt"), lastOutput, StandardCharsets.UTF_8);
            System.out.println("Wrote output to out.txt");

            if (!success) {
                System.err.println("\nFailed after " + cfg.maxRepairAttempts + " attempt(s). See out.txt.");
                System.exit(1);
            }

        } catch (Exception e) {
            System.err.println("OpenaiRunner failed: " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    // -------------------------------------------------------------------------
    // LLM interaction
    // -------------------------------------------------------------------------

    private static String callLlm(HttpClient client, String baseUrl, String apiKey,
                                   String model, String prompt)
            throws IOException, InterruptedException {

        String payload = "{"
                + "\"model\":" + jsonQuoted(model) + ","
                + "\"messages\":[{\"role\":\"user\",\"content\":" + jsonQuoted(prompt) + "}]"
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/chat/completions"))
                .timeout(Duration.ofMinutes(5))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();

        System.out.println("Calling " + model + "...");
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException("OpenAI HTTP " + response.statusCode() + ": " + response.body());
        }

        String content = extractOpenAiContent(response.body());
        if (content == null) {
            throw new RuntimeException("Could not extract content from OpenAI response.");
        }
        return content;
    }

    // -------------------------------------------------------------------------
    // Prompt building
    // -------------------------------------------------------------------------

    private static String buildPrompt(PromptConfig cfg, Path repoRoot) {
        StringBuilder p = new StringBuilder();

        // SUT context (so the LLM knows what types to generate)
        p.append(buildSutSection(cfg, repoRoot));

        // Metamorphic relation
        p.append("Metamorphic Relation: ").append(buildMrStatement(cfg)).append("\n\n");

        // Input domain
        if (!cfg.constraints.isBlank()) {
            p.append("Input domain and edge cases to cover: ").append(cfg.constraints).append("\n\n");
        }

        // Task description
        p.append("Task: Generate a complete, compilable Java class named ")
         .append(cfg.generatedClassName).append(".\n");
        p.append("This class generates TEST DATA only. It does NOT run tests or assert anything.\n\n");

        p.append("The class must contain:\n\n");

        // generateSources() — always required
        p.append("1. A generateSources() method that returns a List of source test inputs.\n");
        p.append("   - Use real generation logic: loops, Random with a fixed seed, helpers.\n");
        p.append("   - Do NOT hardcode literal input values.\n");
        p.append("   - Generate at least ").append(cfg.count).append(" diverse inputs.\n");
        p.append("   - Cover the edge cases from the input domain.\n");
        p.append("   - Infer the input type from the SUT method signature.\n\n");

        // generateFollowUp() — only at inputs-and-followup level
        if (cfg.level.equals("inputs-and-followup")) {
            p.append("2. A generateFollowUp(source) method that transforms a source input ")
             .append("into a follow-up input.\n");
            p.append("   - Apply the metamorphic relation's INPUT transformation.\n");
            p.append("   - Must actually transform the input, not just copy it.\n");
            p.append("   - Do NOT call or import the SUT.\n\n");
        }

        // main() — prints JSON
        String num = cfg.level.equals("inputs-and-followup") ? "3" : "2";
        p.append(num).append(". A main(String[] args) method that:\n");
        p.append("   - Calls generateSources() to get all source inputs.\n");

        if (cfg.level.equals("inputs-and-followup")) {
            p.append("   - For each source, calls generateFollowUp(source).\n");
            p.append("   - Prints a JSON array to stdout in this exact format:\n");
            p.append("     [{\"source\": <value>, \"followUp\": <value>}]\n\n");
        } else {
            p.append("   - Prints a JSON array to stdout in this exact format:\n");
            p.append("     [{\"source\": <value>}]\n\n");
        }

        p.append("Requirements:\n");
        p.append("- No package declaration.\n");
        p.append("- You MAY import SUT types to construct input objects (e.g. new Student(...)).\n");
        p.append("- Do NOT call the method under test. Only generate input data.\n");
        p.append("- If inputs are objects, serialize them as JSON objects matching their fields.\n");
        p.append("- The JSON must be valid and printed to stdout only.\n");
        p.append("- Output ONLY the Java source code. No markdown fences, no explanation.\n");

        return p.toString();
    }

    private static String buildRepairPrompt(PromptConfig cfg, Path repoRoot,
                                             String previousCode, String error,
                                             String failurePhase) {
        StringBuilder p = new StringBuilder();

        p.append(buildSutSection(cfg, repoRoot));
        p.append("Metamorphic Relation: ").append(buildMrStatement(cfg)).append("\n\n");

        p.append("Your previous generated code:\n");
        p.append("```java\n").append(previousCode).append("\n```\n\n");

        p.append("It failed during ").append(failurePhase).append(":\n```\n");
        String truncated = error.length() > 3000
                ? error.substring(0, 3000) + "\n... (truncated)"
                : error;
        p.append(truncated).append("\n```\n\n");

        p.append("Generate a corrected version of the Java class named ")
         .append(cfg.generatedClassName).append(".\n");
        p.append("Fix the errors while keeping:\n");
        p.append("- generateSources() with real generation logic (not hardcoded values)\n");
        if (cfg.level.equals("inputs-and-followup")) {
            p.append("- generateFollowUp(source) that transforms input per the MR\n");
        }
        p.append("- main() that prints valid JSON to stdout\n");
        p.append("Output ONLY Java source code. No markdown, no explanation.\n");

        return p.toString();
    }

    private static String buildSutSection(PromptConfig cfg, Path repoRoot) {
        StringBuilder sut = new StringBuilder();
        sut.append("You are generating test input data for metamorphic testing of a Java SUT.\n\n");

        if (!cfg.sutClassFile.isBlank()) {
            Path classPath = resolveUserPath(cfg.sutClassFile, repoRoot);
            String source = readFileWithLimit(classPath, MAX_SOURCE_CHARS, "SUTClassFile");

            sut.append("SUT class: ").append(classPath.getFileName()).append("\n");
            if (!cfg.targetFunction.isBlank()) {
                sut.append("Target method: ").append(cfg.targetFunction).append("\n");
            }
            sut.append("SUT source:\n```java\n").append(source).append("\n```\n\n");

            // Support files
            List<Path> supportFiles = getSupportFiles(cfg, classPath, repoRoot);
            for (Path dep : supportFiles) {
                String depSource = readFileWithLimit(dep, MAX_SOURCE_CHARS / 2, "dependency");
                sut.append("Dependency ").append(dep.getFileName()).append(":\n");
                sut.append("```java\n").append(depSource).append("\n```\n\n");
            }
        } else if (!cfg.sutDescription.isBlank()) {
            sut.append("SUT description: ").append(cfg.sutDescription).append("\n\n");
        }

        return sut.toString();
    }

    private static String buildMrStatement(PromptConfig cfg) {
        if (!cfg.mrInput.isBlank() && !cfg.mrOutput.isBlank()) {
            return "If " + cfg.mrInput + ", then " + cfg.mrOutput;
        } else if (!cfg.mr.isBlank()) {
            return cfg.mr;
        }
        return "not specified";
    }

    // -------------------------------------------------------------------------
    // Compile and run
    // -------------------------------------------------------------------------

    private static RunResult compile(Path javaFile, List<Path> extraFiles, Path workDir) {
        try {
            List<String> cmd = new ArrayList<>();
            cmd.add("javac");
            cmd.add(workDir.relativize(javaFile).toString());
            for (Path f : extraFiles) {
                cmd.add(workDir.relativize(f).toString());
            }
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.directory(workDir.toFile());
            pb.redirectErrorStream(true);
            Process proc = pb.start();
            String output = new String(proc.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            int exit = proc.waitFor();
            return new RunResult(exit == 0, output);
        } catch (IOException | InterruptedException e) {
            return new RunResult(false, "Compile error: " + e.getMessage());
        }
    }

    private static RunResult run(String className, Path workDir) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", className);
            pb.directory(workDir.toFile());
            pb.redirectErrorStream(true);
            Process proc = pb.start();
            String output = new String(proc.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            int exit = proc.waitFor();
            return new RunResult(exit == 0, output);
        } catch (IOException | InterruptedException e) {
            return new RunResult(false, "Run error: " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // JSON validation — basic checks, not a full parser.
    // Returns null if valid, or an error message that gets fed to the repair loop.
    // -------------------------------------------------------------------------

    private static String validateJsonOutput(String output, int expectedCount,
                                              boolean expectFollowUp) {
        String trimmed = output.trim();

        if (!trimmed.startsWith("[") || !trimmed.endsWith("]")) {
            return "Output is not a JSON array (must start with [ and end with ]).";
        }

        int sourceCount = countOccurrences(trimmed, "\"source\"");
        if (sourceCount < expectedCount) {
            return "Expected at least " + expectedCount + " entries with \"source\", found "
                    + sourceCount + ".";
        }

        if (expectFollowUp) {
            int followUpCount = countOccurrences(trimmed, "\"followUp\"");
            if (followUpCount < expectedCount) {
                return "Expected at least " + expectedCount + " entries with \"followUp\", found "
                        + followUpCount + ".";
            }
        }

        return null;
    }

    private static int countOccurrences(String text, String search) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(search, index)) != -1) {
            count++;
            index += search.length();
        }
        return count;
    }

    // -------------------------------------------------------------------------
    // Config parsing
    // -------------------------------------------------------------------------

    private static PromptConfig parsePromptConfig(List<String> lines) {
        PromptConfig cfg = new PromptConfig();

        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            int idx = line.indexOf(':');
            if (idx <= 0) continue;
            String key = line.substring(0, idx).trim();
            String value = line.substring(idx + 1).trim();

            switch (key) {
                case "SUTClassFile":       cfg.sutClassFile = value; break;
                case "TargetFunction":     cfg.targetFunction = value; break;
                case "SUTSupportFiles":    cfg.sutSupportFiles = value; break;
                case "SUT":                cfg.sutDescription = value; break;
                case "MRInput":            cfg.mrInput = value; break;
                case "MROutput":           cfg.mrOutput = value; break;
                case "MR":                 cfg.mr = value; break;
                case "Count":              cfg.count = value; break;
                case "InputDomain":
                case "Constraints":        cfg.constraints = value; break;
                case "GeneratedClassName": cfg.generatedClassName = value; break;
                case "Level":              cfg.level = value; break;
                case "MaxRepairAttempts":
                    try { cfg.maxRepairAttempts = Integer.parseInt(value); }
                    catch (NumberFormatException ignored) {}
                    break;
                default: break;
            }
        }

        // Validate level
        if (!cfg.level.equals("inputs-only") && !cfg.level.equals("inputs-and-followup")) {
            throw new RuntimeException("Invalid Level: \"" + cfg.level
                    + "\". Must be \"inputs-only\" or \"inputs-and-followup\".");
        }

        return cfg;
    }

    // -------------------------------------------------------------------------
    // SUT file collection (for compilation)
    // -------------------------------------------------------------------------

    private static List<Path> collectSutFiles(PromptConfig cfg, Path repoRoot) {
        List<Path> files = new ArrayList<>();
        if (!cfg.sutClassFile.isBlank()) {
            files.add(resolveUserPath(cfg.sutClassFile, repoRoot));
        }
        if (!cfg.sutSupportFiles.isBlank()) {
            for (String part : cfg.sutSupportFiles.split(",")) {
                String p = part.trim();
                if (!p.isEmpty()) files.add(resolveUserPath(p, repoRoot));
            }
        }
        return files;
    }

    // -------------------------------------------------------------------------
    // Support file resolution
    // -------------------------------------------------------------------------

    private static List<Path> getSupportFiles(PromptConfig cfg, Path classPath, Path repoRoot) {
        if (!cfg.sutSupportFiles.isBlank()) {
            List<Path> files = new ArrayList<>();
            for (String part : cfg.sutSupportFiles.split(",")) {
                String p = part.trim();
                if (!p.isEmpty()) {
                    files.add(resolveUserPath(p, repoRoot));
                }
            }
            return files;
        }
        return detectFirstLevelDependencies(classPath, repoRoot);
    }

    // Scans the SUT file's imports to find local .java files that match.
    // Only used when SUTSupportFiles is left blank in prompt.txt.
    private static List<Path> detectFirstLevelDependencies(Path classFile, Path repoRoot) {
        List<Path> deps = new ArrayList<>();
        try {
            String source = Files.readString(classFile, StandardCharsets.UTF_8);
            Pattern importPattern = Pattern.compile("^import\\s+([\\w.]+);", Pattern.MULTILINE);
            Matcher matcher = importPattern.matcher(source);

            List<Path> javaFiles = new ArrayList<>();
            collectJavaFiles(repoRoot, classFile, javaFiles);

            while (matcher.find()) {
                String importName = matcher.group(1);
                if (importName.startsWith("java.") || importName.startsWith("javax.")
                        || importName.startsWith("sun.") || importName.startsWith("com.sun.")) {
                    continue;
                }
                String simpleName = importName.substring(importName.lastIndexOf('.') + 1);
                if (simpleName.equals("*")) continue;

                String fileName = simpleName + ".java";
                for (Path jf : javaFiles) {
                    if (jf.getFileName().toString().equals(fileName)) {
                        deps.add(jf);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            // best effort
        }
        return deps;
    }

    private static void collectJavaFiles(Path dir, Path excludeFile, List<Path> result)
            throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    String name = entry.getFileName().toString();
                    if (name.equals(".git") || name.equals("lib") || name.equals(".claude")) continue;
                    collectJavaFiles(entry, excludeFile, result);
                } else if (entry.toString().endsWith(".java") && !entry.equals(excludeFile)) {
                    result.add(entry);
                }
            }
        }
    }

    // -------------------------------------------------------------------------
    // File helpers
    // -------------------------------------------------------------------------

    private static Path resolveUserPath(String raw, Path repoRoot) {
        if (raw.isBlank()) throw new RuntimeException("Empty path provided.");
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
                return content.substring(0, maxChars) + "\n// ... truncated";
            }
            return content;
        } catch (IOException e) {
            throw new RuntimeException("Cannot read " + label + ": " + path);
        }
    }

    // LLMs sometimes wrap code in ```java ... ``` despite being told not to
    private static String stripMarkdownFences(String raw) {
        String trimmed = raw.trim();
        if (trimmed.startsWith("```")) {
            int nl = trimmed.indexOf('\n');
            if (nl >= 0) trimmed = trimmed.substring(nl + 1);
        }
        if (trimmed.endsWith("```")) {
            trimmed = trimmed.substring(0, trimmed.length() - 3).trim();
        }
        return trimmed;
    }

    // -------------------------------------------------------------------------
    // OpenAI response parsing
    // Hand-rolled because we avoid adding a JSON library dependency.
    // -------------------------------------------------------------------------

    private static String extractOpenAiContent(String json) {
        int msgIdx = json.indexOf("\"message\"");
        if (msgIdx < 0) return null;

        int keyIdx = json.indexOf("\"content\"", msgIdx);
        if (keyIdx < 0) return null;

        int i = keyIdx + "\"content\"".length();
        while (i < json.length() && Character.isWhitespace(json.charAt(i))) i++;
        if (i >= json.length() || json.charAt(i) != ':') return null;
        i++;
        while (i < json.length() && Character.isWhitespace(json.charAt(i))) i++;
        if (i >= json.length() || json.charAt(i) != '"') return null;

        return parseJsonStringLiteral(json, i + 1);
    }

    private static String parseJsonStringLiteral(String json, int start) {
        StringBuilder out = new StringBuilder();
        boolean escaping = false;
        int i = start;

        while (i < json.length()) {
            char c = json.charAt(i++);

            if (escaping) {
                switch (c) {
                    case '"':  out.append('"');  break;
                    case '\\': out.append('\\'); break;
                    case '/':  out.append('/');  break;
                    case 'n':  out.append('\n'); break;
                    case 'r':  out.append('\r'); break;
                    case 't':  out.append('\t'); break;
                    case 'b':  out.append('\b'); break;
                    case 'f':  out.append('\f'); break;
                    case 'u':
                        if (i + 4 > json.length()) return null;
                        try {
                            out.append((char) Integer.parseInt(json.substring(i, i + 4), 16));
                        } catch (NumberFormatException e) { return null; }
                        i += 4;
                        break;
                    default: out.append(c);
                }
                escaping = false;
            } else if (c == '\\') {
                escaping = true;
            } else if (c == '"') {
                return out.toString();
            } else {
                out.append(c);
            }
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // JSON encoding
    // -------------------------------------------------------------------------

    private static String jsonQuoted(String s) {
        return "\"" + jsonEscape(s) + "\"";
    }

    private static String jsonEscape(String s) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\': out.append("\\\\"); break;
                case '"':  out.append("\\\""); break;
                case '\n': out.append("\\n");  break;
                case '\r': out.append("\\r");  break;
                case '\t': out.append("\\t");  break;
                default:
                    if (c <= 0x1F) out.append(String.format("\\u%04x", (int) c));
                    else out.append(c);
            }
        }
        return out.toString();
    }

    // -------------------------------------------------------------------------
    // Environment
    // -------------------------------------------------------------------------

    private static Map<String, String> loadDotEnv(Path envPath) {
        Map<String, String> values = new HashMap<>();
        try {
            if (!Files.exists(envPath)) return values;
            for (String line : Files.readAllLines(envPath, StandardCharsets.UTF_8)) {
                String t = line.trim();
                if (t.isEmpty() || t.startsWith("#")) continue;
                int eq = t.indexOf('=');
                if (eq <= 0) continue;
                values.put(t.substring(0, eq).trim(), t.substring(eq + 1).trim());
            }
        } catch (IOException ignored) {}
        return values;
    }

    private static String firstNonBlank(String... values) {
        for (String v : values) {
            if (v != null && !v.trim().isEmpty()) return v.trim();
        }
        return "";
    }

    // -------------------------------------------------------------------------
    // Data classes
    // -------------------------------------------------------------------------

    private static final class PromptConfig {
        String sutClassFile       = "";
        String targetFunction     = "";
        String sutSupportFiles    = "";
        String sutDescription     = "";
        String mrInput            = "";
        String mrOutput           = "";
        String mr                 = "";
        String count              = "5";
        String constraints        = "";
        String generatedClassName = "GeneratedData";
        String level              = "inputs-and-followup";
        int    maxRepairAttempts  = 3;
    }

    private static final class RunResult {
        final boolean success;
        final String  output;
        RunResult(boolean success, String output) {
            this.success = success;
            this.output  = output;
        }
    }
}
