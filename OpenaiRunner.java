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

public class OpenaiRunner {

    private static final int MAX_SOURCE_CHARS = 30000;

    public static void main(String[] args) {
        try {
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

            String model   = firstNonBlank(System.getenv("OPENAI_MODEL"),    env.get("OPENAI_MODEL"),    "gpt-4o-mini");
            String baseUrl = firstNonBlank(System.getenv("OPENAI_BASE_URL"), env.get("OPENAI_BASE_URL"), "https://api.openai.com/v1");

            PromptConfig cfg    = parsePromptConfig(lines);
            String       prompt = buildPromptFromConfig(cfg, Path.of("").toAbsolutePath().normalize());

            String payload = "{"
                    + "\"model\":"    + jsonQuoted(model) + ","
                    + "\"messages\":[{\"role\":\"user\",\"content\":" + jsonQuoted(prompt) + "}]"
                    + "}";

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(15))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/chat/completions"))
                    .timeout(Duration.ofMinutes(5))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException("OpenAI HTTP error " + response.statusCode() + ": " + response.body());
            }

            String output = extractOpenAiContent(response.body());
            if (output == null) {
                throw new RuntimeException("Could not extract response content from OpenAI output.");
            }

            String javaSource = stripMarkdownFences(output);

            Path generatedFile = Path.of("GeneratedTest.java");
            Files.writeString(generatedFile, javaSource, StandardCharsets.UTF_8);
            System.out.println("=== Generated source code ===");
            System.out.println(javaSource);
            System.out.println("=== End of generated source ===\n");
            System.out.println("Wrote generated code to " + generatedFile);

            String compileAndRunOutput = compileAndRun(generatedFile);
            Files.writeString(Path.of("out.txt"), compileAndRunOutput, StandardCharsets.UTF_8);
            System.out.println("\n=== Execution output ===");
            System.out.println(compileAndRunOutput);
            System.out.println("=== End of execution output ===");
            System.out.println("\nWrote execution output to out.txt");

        } catch (Exception e) {
            System.err.println("OpenaiRunner failed: " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    // -------------------------------------------------------------------------
    // Config parsing
    // -------------------------------------------------------------------------

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
            String key   = line.substring(0, idx).trim();
            String value = line.substring(idx + 1).trim();

            switch (key) {
                case "SUTClassFile":
                    cfg.sutClassFile = value;
                    break;
                case "TargetFunction":
                    cfg.targetFunction = value;
                    break;
                case "SUTSupportFiles":
                    cfg.sutSupportFiles = value;
                    break;
                case "SUT":
                    cfg.sutDescription = value;
                    break;
                case "MRInput":
                    cfg.mrInput = value;
                    break;
                case "MROutput":
                    cfg.mrOutput = value;
                    break;
                case "MR":
                    cfg.mr = value;
                    break;
                case "Count":
                    cfg.count = value;
                    break;
                case "DataType":
                    cfg.dataType = value;
                    break;
                case "InputDomain":
                    cfg.constraints = value;
                    break;
                case "Constraints":
                    cfg.constraints = value;
                    break;
                default:
                    break;
            }
        }
        return cfg;
    }

    // -------------------------------------------------------------------------
    // Prompt building
    // -------------------------------------------------------------------------

    private static String buildPromptFromConfig(PromptConfig cfg, Path repoRoot) {
        String sutSection;

        if (!cfg.sutClassFile.isBlank()) {
            Path   classPath   = resolveUserPath(cfg.sutClassFile, repoRoot);
            String classSource = readFileWithLimit(classPath, MAX_SOURCE_CHARS, "SUTClassFile");

            StringBuilder sut = new StringBuilder();
            sut.append("System Under Test (class-level):\n");
            sut.append("SUT class file: ").append(classPath).append("\n");

            if (!cfg.targetFunction.isBlank()) {
                sut.append("Target function: ").append(cfg.targetFunction).append("\n");
            }

            sut.append("SUT class source:\n");
            sut.append("```java\n");
            sut.append(classSource);
            sut.append("\n```\n");

            if (!cfg.sutSupportFiles.isBlank()) {
                // Manually listed support files
                String[] parts = cfg.sutSupportFiles.split(",");
                for (String part : parts) {
                    String p = part.trim();
                    if (p.isEmpty()) {
                        continue;
                    }
                    Path   support       = resolveUserPath(p, repoRoot);
                    String supportSource = readFileWithLimit(support, MAX_SOURCE_CHARS / 2, "SUTSupportFiles");
                    sut.append("Support source from ").append(support.getFileName()).append(":\n");
                    sut.append("```java\n");
                    sut.append(supportSource);
                    sut.append("\n```\n");
                }
            } else {
                // Auto-detect first-level dependencies from import statements
                List<Path> autoDeps = detectFirstLevelDependencies(classPath, repoRoot);
                for (Path dep : autoDeps) {
                    System.out.println("Auto-detected dependency: " + dep.getFileName());
                    String depSource = readFileWithLimit(dep, MAX_SOURCE_CHARS / 2, "auto-detected dependency");
                    sut.append("Dependency source from ").append(dep.getFileName()).append(":\n");
                    sut.append("```java\n");
                    sut.append(depSource);
                    sut.append("\n```\n");
                }
            }

            sutSection = sut.toString();

        } else {
            // Fallback: use a plain text SUT description if no class file is provided
            String description;
            if (cfg.sutDescription.isBlank()) {
                description = "A Java class-level SUT for metamorphic testing.";
            } else {
                description = cfg.sutDescription;
            }
            sutSection = "System Under Test:\n" + description + "\n";
        }

        String mrStatement;
        if (!cfg.mrInput.isBlank() && !cfg.mrOutput.isBlank()) {
            mrStatement = "If " + cfg.mrInput + ", then " + cfg.mrOutput;
        } else if (!cfg.mr.isBlank()) {
            mrStatement = cfg.mr;
        } else {
            mrStatement = "not specified";
        }

        String domainLine;
        if (!cfg.constraints.isBlank()) {
            domainLine = "Input domain: " + cfg.constraints + "\n";
        } else {
            domainLine = "";
        }

        return sutSection
                + "Metamorphic Relation: " + mrStatement + "\n"
                + "Task: Generate exactly " + cfg.count + " edge-case test pairs.\n"
                + "Input/Output type: " + cfg.dataType + "\n"
                + domainLine
                + "\n"
                + "IMPORTANT: Output a COMPLETE, compilable Java class called GeneratedTest.\n"
                + "The class must have a public static void main(String[] args) method.\n"
                + "The main method must programmatically generate the test pairs using real logic "
                + "(loops, Random, helper methods, shuffling, etc.) — do NOT hardcode literal values.\n"
                + "The generation logic should respect the metamorphic relation: "
                + "build a source input, then derive the follow-up input from it according to the relation.\n"
                + "Cover the input domain edge cases specified above.\n"
                + "At the end of main, print the pairs as a JSON array to stdout in this exact schema:\n"
                + "[ { \"source\": <value>, \"followUp\": <value> } ]\n"
                + "Do NOT call or import the system under test — only generate inputs.\n"
                + "Output ONLY the Java source code. No markdown fences, no explanation, no conversational text.";
    }

    // -------------------------------------------------------------------------
    // Dependency detection
    // -------------------------------------------------------------------------

    private static List<Path> detectFirstLevelDependencies(Path classFile, Path repoRoot) {
        List<Path> deps = new ArrayList<>();
        try {
            String  source        = Files.readString(classFile, StandardCharsets.UTF_8);
            Pattern importPattern = Pattern.compile("^import\\s+([\\w.]+);", Pattern.MULTILINE);
            Matcher matcher       = importPattern.matcher(source);

            List<Path> javaFiles = new ArrayList<>();
            collectJavaFiles(repoRoot, classFile, javaFiles);

            while (matcher.find()) {
                String importName = matcher.group(1);

                // Skip standard Java library imports
                if (importName.startsWith("java.")
                        || importName.startsWith("javax.")
                        || importName.startsWith("sun.")
                        || importName.startsWith("com.sun.")) {
                    continue;
                }

                // Get the simple class name from the end of the import path
                String simpleName = importName.substring(importName.lastIndexOf('.') + 1);
                if (simpleName.equals("*")) {
                    continue;
                }

                // Look for a matching .java file in the repo
                String fileName = simpleName + ".java";
                for (Path jf : javaFiles) {
                    if (jf.getFileName().toString().equals(fileName)) {
                        deps.add(jf);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            // Best-effort — skip silently if the file walk fails
        }
        return deps;
    }

    private static void collectJavaFiles(Path dir, Path excludeFile, List<Path> result) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
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
        if (raw.isBlank()) {
            throw new RuntimeException("Empty path provided.");
        }
        Path p = Path.of(raw);
        Path resolved;
        if (p.isAbsolute()) {
            resolved = p.normalize();
        } else {
            resolved = repoRoot.resolve(p).normalize();
        }
        return resolved;
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

    // -------------------------------------------------------------------------
    // Code generation helpers
    // -------------------------------------------------------------------------

    private static String stripMarkdownFences(String raw) {
        String trimmed = raw.trim();
        if (trimmed.startsWith("```")) {
            int firstNewline = trimmed.indexOf('\n');
            if (firstNewline >= 0) {
                trimmed = trimmed.substring(firstNewline + 1);
            }
        }
        if (trimmed.endsWith("```")) {
            trimmed = trimmed.substring(0, trimmed.length() - 3).trim();
        }
        return trimmed;
    }

    private static String compileAndRun(Path javaFile) {
        try {
            String fileName = javaFile.getFileName().toString();
            String className = fileName.replace(".java", "");

            System.out.println("Compiling " + fileName + "...");
            ProcessBuilder javac = new ProcessBuilder("javac", fileName);
            javac.directory(javaFile.toAbsolutePath().getParent().toFile());
            javac.redirectErrorStream(true);
            Process compileProc = javac.start();
            String compileOutput = new String(compileProc.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            int compileExit = compileProc.waitFor();

            if (compileExit != 0) {
                throw new RuntimeException("Compilation failed (exit " + compileExit + "):\n" + compileOutput);
            }
            System.out.println("Compilation successful.");

            System.out.println("Running " + className + "...");
            ProcessBuilder java = new ProcessBuilder("java", className);
            java.directory(javaFile.toAbsolutePath().getParent().toFile());
            java.redirectErrorStream(true);
            Process runProc = java.start();
            String runOutput = new String(runProc.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            int runExit = runProc.waitFor();

            if (runExit != 0) {
                throw new RuntimeException("Execution failed (exit " + runExit + "):\n" + runOutput);
            }

            return runOutput.trim();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to compile/run generated code: " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Config data class
    // -------------------------------------------------------------------------

    private static final class PromptConfig {
        String sutClassFile    = "";
        String targetFunction  = "";
        String sutSupportFiles = "";
        String sutDescription  = "";
        String mrInput         = "";
        String mrOutput        = "";
        String mr              = "";
        String count           = "5";
        String dataType        = "int[]";
        String constraints     = "";
    }

    // -------------------------------------------------------------------------
    // OpenAI response parsing
    // -------------------------------------------------------------------------

    private static String extractOpenAiContent(String json) {
        int messageIdx = json.indexOf("\"message\"");
        if (messageIdx < 0) {
            return null;
        }

        int keyIdx = json.indexOf("\"content\"", messageIdx);
        if (keyIdx < 0) {
            return null;
        }

        // Step past the key name, then skip whitespace up to the colon
        int i = keyIdx + "\"content\"".length();
        while (i < json.length() && Character.isWhitespace(json.charAt(i))) {
            i++;
        }
        if (i >= json.length() || json.charAt(i) != ':') {
            return null;
        }

        // Skip the colon, then skip whitespace up to the opening quote
        i++;
        while (i < json.length() && Character.isWhitespace(json.charAt(i))) {
            i++;
        }
        if (i >= json.length() || json.charAt(i) != '"') {
            return null;
        }

        return parseJsonStringLiteral(json, i + 1);
    }

    private static String parseJsonStringLiteral(String json, int startIndex) {
        int     i         = startIndex;
        boolean escaping  = false;
        StringBuilder out = new StringBuilder();

        while (i < json.length()) {
            char c = json.charAt(i);
            i++;

            if (escaping) {
                switch (c) {
                    case '"':
                        out.append('"');
                        break;
                    case '\\':
                        out.append('\\');
                        break;
                    case '/':
                        out.append('/');
                        break;
                    case 'b':
                        out.append('\b');
                        break;
                    case 'f':
                        out.append('\f');
                        break;
                    case 'n':
                        out.append('\n');
                        break;
                    case 'r':
                        out.append('\r');
                        break;
                    case 't':
                        out.append('\t');
                        break;
                    case 'u':
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
                        break;
                    default:
                        out.append(c);
                        break;
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

    // -------------------------------------------------------------------------
    // JSON encoding helpers
    // -------------------------------------------------------------------------

    private static String jsonQuoted(String s) {
        return "\"" + jsonEscape(s) + "\"";
    }

    private static String jsonEscape(String s) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\':
                    out.append("\\\\");
                    break;
                case '"':
                    out.append("\\\"");
                    break;
                case '\n':
                    out.append("\\n");
                    break;
                case '\r':
                    out.append("\\r");
                    break;
                case '\t':
                    out.append("\\t");
                    break;
                default:
                    if (c <= 0x1F) {
                        out.append(String.format("\\u%04x", (int) c));
                    } else {
                        out.append(c);
                    }
                    break;
            }
        }
        return out.toString();
    }

    // -------------------------------------------------------------------------
    // Environment / config helpers
    // -------------------------------------------------------------------------

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
                String key   = t.substring(0, eq).trim();
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
