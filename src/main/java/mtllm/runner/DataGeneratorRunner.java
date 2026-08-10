package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.report.HtmlReportWriter;
import mtllm.sut.SutContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Compiles and runs generated Java data-generator classes.
 *
 * <p>In simple terms, this checks that the LLM-generated data generator compiles,
 * executes, prints valid-looking JSON, and produces enough generated entries.</p>
 */
public final class DataGeneratorRunner {
    private final Path repoRoot;
    private final Path classesDir;
    private final Path outputDir;
    private final Path reportsDir;
    private ExecutedDataSummary lastSummary = ExecutedDataSummary.empty();

    public DataGeneratorRunner(Path repoRoot, Path classesDir, Path outputDir, Path reportsDir) {
        this.repoRoot = repoRoot;
        this.classesDir = classesDir;
        this.outputDir = outputDir;
        this.reportsDir = reportsDir;
    }

    public TestRunResult compileRunAndValidate(Path generatedFile, PromptConfig config, SutContext sutContext)
            throws Exception {
        lastSummary = ExecutedDataSummary.empty();
        Files.createDirectories(classesDir);

        String sourceConsistencyError = validateDeterministicSourceGeneration(generatedFile);
        if (sourceConsistencyError != null) {
            return TestRunResult.failed("Generated source-input consistency validation failed:\n"
                    + sourceConsistencyError);
        }

        TestRunResult compileResult = compile(generatedFile, config, sutContext);
        if (!compileResult.passed()) {
            return compileResult;
        }

        RuntimeResourceCopier.copyFor(config, classesDir);

        TestRunResult runResult = run(config.generatedClassName());
        if (!runResult.passed()) {
            return runResult;
        }

        String repeatedExecutionError = validateRepeatedExecution(config.generatedClassName());
        if (repeatedExecutionError != null) {
            return TestRunResult.failed("Generated source-input consistency validation failed:\n"
                    + repeatedExecutionError);
        }

        String validationError = validateJsonOutput(runResult.output(), config);
        if (validationError != null) {
            return TestRunResult.failed("Generated JSON validation failed:\n" + validationError
                    + "\n\nOutput:\n" + runResult.output());
        }

        return writeSplitAndReport(runResult.output(), config, sutContext);
    }

    private String validateDeterministicSourceGeneration(Path generatedFile) throws IOException {
        String source = Files.readString(generatedFile, StandardCharsets.UTF_8);
        List<String> forbiddenConstructs = List.of(
                "java.util.Random",
                "new Random(",
                "Math.random(",
                "ThreadLocalRandom",
                "SecureRandom",
                "UUID.randomUUID(",
                "System.currentTimeMillis(",
                "System.nanoTime(",
                "Instant.now(",
                "LocalDate.now(",
                "LocalDateTime.now(");

        List<String> found = new ArrayList<>();
        String compactSource = stripCommentsAndLiterals(source).replaceAll("\\s+", "");
        for (String forbidden : forbiddenConstructs) {
            if (compactSource.contains(forbidden.replaceAll("\\s+", ""))) {
                found.add(forbidden);
            }
        }
        if (found.isEmpty()) {
            return null;
        }

        return "The data generator uses nondeterministic source construction: "
                + String.join(", ", found)
                + ". generateSources() is called by both JSON execution and generated JUnit tests, so its "
                + "ordered values must be repeatable. Replace these constructs with explicit fixtures or an "
                + "immutable value table.";
    }

    private String stripCommentsAndLiterals(String source) {
        StringBuilder code = new StringBuilder(source.length());
        int state = 0;

        for (int i = 0; i < source.length(); i++) {
            char current = source.charAt(i);
            char next = i + 1 < source.length() ? source.charAt(i + 1) : '\0';

            if (state == 0) {
                if (current == '/' && next == '/') {
                    state = 1;
                    i++;
                } else if (current == '/' && next == '*') {
                    state = 2;
                    i++;
                } else if (current == '"') {
                    state = 3;
                } else if (current == '\'') {
                    state = 4;
                } else {
                    code.append(current);
                }
            } else if (state == 1) {
                if (current == '\n') {
                    state = 0;
                    code.append(current);
                }
            } else if (state == 2) {
                if (current == '*' && next == '/') {
                    state = 0;
                    i++;
                }
            } else if (state == 3 || state == 4) {
                char closingQuote = state == 3 ? '"' : '\'';
                if (current == '\\') {
                    i++;
                } else if (current == closingQuote) {
                    state = 0;
                }
            }
        }
        return code.toString();
    }

    private String validateRepeatedExecution(String className) {
        try (URLClassLoader loader = new URLClassLoader(
                new java.net.URL[]{classesDir.toUri().toURL()},
                DataGeneratorRunner.class.getClassLoader())) {
            Class<?> generatedClass = Class.forName(className, true, loader);
            Method main = generatedClass.getMethod("main", String[].class);
            String firstOutput = invokeMainAndCaptureOutput(main);
            String secondOutput = invokeMainAndCaptureOutput(main);

            if (firstOutput.trim().equals(secondOutput.trim())) {
                return null;
            }
            return "The generated class produced different executed JSON when main() was invoked twice in "
                    + "the same JVM. generateSources() and the SUT/MR execution must be deterministic so JSON "
                    + "and JUnit use the same indexed cases. Use explicit ordered fixtures instead of "
                    + "randomness, time, or mutable global state.";
        } catch (Exception exception) {
            Throwable cause = exception.getCause() == null ? exception : exception.getCause();
            return "The generated class could not be executed repeatedly in the same JVM: "
                    + cause.getClass().getSimpleName() + ": " + cause.getMessage();
        }
    }

    private String invokeMainAndCaptureOutput(Method main) throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try (PrintStream capturedOut = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            System.setOut(capturedOut);
            main.invoke(null, (Object) new String[0]);
        } finally {
            System.setOut(originalOut);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }

    /**
     * Write the executed-MT JSON, split it into passing/failing, and emit the HTML report.
     *
     * <p>Shared seam: the LLM data-generator path calls this after running the generated class;
     * the Randoop input mode calls it directly with the JSON it produced in-process. The split and
     * report run only for executed-MT modes ({@code generatesExecutedMtData()}).
     * {@code executedJson} must be a JSON array of
     * {@code {source, followUp, sourceOutput, followUpOutput, passed}} entries.</p>
     */
    public TestRunResult writeSplitAndReport(String executedJson, PromptConfig config, SutContext sutContext)
            throws Exception {
        lastSummary = ExecutedDataSummary.empty();
        Files.createDirectories(outputDir);
        Path outputFile = outputDir.resolve(config.generatedClassName() + ".json");
        String prettyJson = prettyPrintJsonLike(executedJson) + System.lineSeparator();
        Files.writeString(outputFile, prettyJson, StandardCharsets.UTF_8);

        SplitResult splitResult = SplitResult.empty();
        if (config.mode().generatesExecutedMtData()) {
            splitResult = splitExecutedMtData(executedJson, config);
        }

        String reportMessage = "";
        if (config.mode().generatesExecutedMtData()) {
            Path reportFile = HtmlReportWriter.writeExecutedDataReport(
                    reportsDir,
                    config,
                    sutContext,
                    splitResult.allEntries,
                    splitResult.passingEntries,
                    splitResult.failingEntries,
                    outputFile,
                    splitResult.passingFile,
                    splitResult.failingFile);
            lastSummary = new ExecutedDataSummary(
                    splitResult.allEntries,
                    splitResult.passingEntries,
                    splitResult.failingEntries,
                    outputFile,
                    splitResult.passingFile,
                    splitResult.failingFile,
                    reportFile);
            reportMessage = "\nWrote HTML report to " + reportFile;
        }

        return TestRunResult.passed("Wrote generated data JSON to " + outputFile
                + splitResult.message()
                + reportMessage
                + "\n\n" + executedJson);
    }

    public ExecutedDataSummary lastSummary() {
        return lastSummary;
    }

    private TestRunResult compile(Path generatedFile, PromptConfig config, SutContext sutContext) throws Exception {
        List<String> command = new ArrayList<>();
        command.add("javac");
        command.add("-encoding");
        command.add("UTF-8");
        command.add("-d");
        command.add(classesDir.toString());
        if (config.sutClassFile() != null) {
            command.add(config.sutClassFile().toString());
        }
        for (SutContext.SourceFile supportFile : sutContext.supportFiles()) {
            command.add(supportFile.path().toString());
        }
        if (config.mode().usesDeveloperMrHelpers() && config.developerMrFile() != null) {
            command.add(config.developerMrFile().toString());
        }
        command.add(generatedFile.toString());

        ProcessResult result = runProcess(command, repoRoot);
        if (result.exitCode == 0) {
            return TestRunResult.passed(result.output);
        }
        return TestRunResult.failed("Compilation failed:\n" + result.output);
    }

    private TestRunResult run(String className) throws Exception {
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("-cp");
        command.add(classesDir.toString());
        command.add(className);

        ProcessResult result = runProcess(command, repoRoot);
        if (result.exitCode == 0) {
            return TestRunResult.passed(result.output);
        }
        return TestRunResult.failed("Execution failed:\n" + result.output);
    }

    private String validateJsonOutput(String output, PromptConfig config) {
        String trimmed = output == null ? "" : output.trim();
        if (!trimmed.startsWith("[") || !trimmed.endsWith("]")) {
            return "Output must be a JSON array that starts with [ and ends with ].";
        }

        int entryCount = splitTopLevelObjects(trimmed).size();
        if (entryCount > config.count()) {
            return "Expected at most " + config.count() + " top-level entries, found " + entryCount + ".";
        }

        return null;
    }

    private SplitResult splitExecutedMtData(String rawJson, PromptConfig config) throws IOException {
        List<String> entries = splitTopLevelObjects(rawJson);
        List<String> passing = new ArrayList<>();
        List<String> failing = new ArrayList<>();
        for (String entry : entries) {
            if (hasPassedValue(entry, true)) {
                passing.add(entry);
            } else if (hasPassedValue(entry, false)) {
                failing.add(entry);
            }
        }

        String baseName = baseName(config.generatedClassName());
        Path passingFile = outputDir.resolve(baseName + "Passing.json");
        Path failingFile = outputDir.resolve(baseName + "Failing.json");
        Files.writeString(passingFile, prettyPrintJsonLike(toJsonArray(passing)) + System.lineSeparator(), StandardCharsets.UTF_8);
        Files.writeString(failingFile, prettyPrintJsonLike(toJsonArray(failing)) + System.lineSeparator(), StandardCharsets.UTF_8);

        return new SplitResult(entries, passing, failing, passingFile, failingFile);
    }

    private boolean hasPassedValue(String entry, boolean expectedValue) {
        String value = expectedValue ? "true" : "false";
        return entry.matches("(?s).*\"passed\"\\s*:\\s*" + value + ".*");
    }

    private List<String> splitTopLevelObjects(String rawJson) {
        String trimmed = rawJson == null ? "" : rawJson.trim();
        List<String> entries = new ArrayList<>();
        int depth = 0;
        int objectStart = -1;
        boolean inString = false;
        boolean escaping = false;

        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);
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
            if (c == '{') {
                if (depth == 0) {
                    objectStart = i;
                }
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0 && objectStart >= 0) {
                    entries.add(trimmed.substring(objectStart, i + 1));
                    objectStart = -1;
                }
            }
        }
        return entries;
    }

    private String toJsonArray(List<String> entries) {
        return "[" + String.join(",", entries) + "]";
    }

    private String baseName(String generatedClassName) {
        if (generatedClassName.endsWith("Data")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Data".length());
        }
        return generatedClassName;
    }

    private String prettyPrintJsonLike(String json) {
        String trimmed = json == null ? "" : json.trim();
        StringBuilder out = new StringBuilder();
        int indent = 0;
        boolean inString = false;
        boolean escaping = false;

        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);

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
                out.append(c);
                inString = !inString;
                continue;
            }

            if (inString) {
                out.append(c);
                continue;
            }

            switch (c) {
                case '{':
                case '[':
                    out.append(c);
                    indent++;
                    appendNewlineAndIndent(out, indent);
                    break;
                case '}':
                case ']':
                    indent = Math.max(0, indent - 1);
                    appendNewlineAndIndent(out, indent);
                    out.append(c);
                    break;
                case ',':
                    out.append(c);
                    appendNewlineAndIndent(out, indent);
                    break;
                case ':':
                    out.append(": ");
                    break;
                default:
                    if (!Character.isWhitespace(c)) {
                        out.append(c);
                    }
                    break;
            }
        }

        return out.toString();
    }

    private void appendNewlineAndIndent(StringBuilder out, int indent) {
        out.append(System.lineSeparator());
        for (int i = 0; i < indent; i++) {
            out.append("  ");
        }
    }

    private ProcessResult runProcess(List<String> command, Path workDir) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command)
                .directory(workDir.toFile())
                .redirectErrorStream(true);
        String existingPath = builder.environment().getOrDefault("PATH", "");
        builder.environment().put("PATH", "/bin:/usr/bin:/opt/homebrew/bin:/usr/local/bin:" + existingPath);
        Process process = builder.start();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        process.getInputStream().transferTo(buffer);
        int exitCode = process.waitFor();
        return new ProcessResult(exitCode, buffer.toString(StandardCharsets.UTF_8));
    }

    private static final class ProcessResult {
        private final int exitCode;
        private final String output;

        private ProcessResult(int exitCode, String output) {
            this.exitCode = exitCode;
            this.output = output == null ? "" : output;
        }
    }

    public static final class ExecutedDataSummary {
        private final List<String> allEntries;
        private final List<String> passingEntries;
        private final List<String> failingEntries;
        private final Path fullJsonFile;
        private final Path passingJsonFile;
        private final Path failingJsonFile;
        private final Path reportFile;

        private ExecutedDataSummary(
                List<String> allEntries,
                List<String> passingEntries,
                List<String> failingEntries,
                Path fullJsonFile,
                Path passingJsonFile,
                Path failingJsonFile,
                Path reportFile) {
            this.allEntries = List.copyOf(allEntries);
            this.passingEntries = List.copyOf(passingEntries);
            this.failingEntries = List.copyOf(failingEntries);
            this.fullJsonFile = fullJsonFile;
            this.passingJsonFile = passingJsonFile;
            this.failingJsonFile = failingJsonFile;
            this.reportFile = reportFile;
        }

        private static ExecutedDataSummary empty() {
            return new ExecutedDataSummary(List.of(), List.of(), List.of(), null, null, null, null);
        }

        public boolean present() {
            return reportFile != null;
        }

        public List<String> allEntries() {
            return allEntries;
        }

        public List<String> passingEntries() {
            return passingEntries;
        }

        public List<String> failingEntries() {
            return failingEntries;
        }

        public List<Integer> allIndexes() {
            List<Integer> indexes = new ArrayList<>();
            for (int i = 0; i < allEntries.size(); i++) {
                indexes.add(i);
            }
            return indexes;
        }

        public Path fullJsonFile() {
            return fullJsonFile;
        }

        public Path passingJsonFile() {
            return passingJsonFile;
        }

        public Path failingJsonFile() {
            return failingJsonFile;
        }

        public Path reportFile() {
            return reportFile;
        }
    }

    private static final class SplitResult {
        private final List<String> allEntries;
        private final List<String> passingEntries;
        private final List<String> failingEntries;
        private final Path passingFile;
        private final Path failingFile;

        private SplitResult(
                List<String> allEntries,
                List<String> passingEntries,
                List<String> failingEntries,
                Path passingFile,
                Path failingFile) {
            this.allEntries = allEntries;
            this.passingEntries = passingEntries;
            this.failingEntries = failingEntries;
            this.passingFile = passingFile;
            this.failingFile = failingFile;
        }

        private static SplitResult empty() {
            return new SplitResult(List.of(), List.of(), List.of(), null, null);
        }

        private String message() {
            if (passingFile == null || failingFile == null) {
                return "";
            }
            return "\nWrote passing data JSON to " + passingFile
                    + "\nWrote failing data JSON to " + failingFile
                    + "\nPassing entries: " + passingEntries.size()
                    + "\nFailing entries: " + failingEntries.size();
        }
    }
}
