package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.report.HtmlReportWriter;
import mtllm.sut.SutContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

        TestRunResult compileResult = compile(generatedFile, config, sutContext);
        if (!compileResult.passed()) {
            return compileResult;
        }

        TestRunResult runResult = run(config.generatedClassName());
        if (!runResult.passed()) {
            return runResult;
        }

        String validationError = validateJsonOutput(runResult.output(), config);
        if (validationError != null) {
            return TestRunResult.failed("Generated JSON validation failed:\n" + validationError
                    + "\n\nOutput:\n" + runResult.output());
        }

        Files.createDirectories(outputDir);
        Path outputFile = outputDir.resolve(config.generatedClassName() + ".json");
        String prettyJson = prettyPrintJsonLike(runResult.output()) + System.lineSeparator();
        Files.writeString(outputFile, prettyJson, StandardCharsets.UTF_8);

        SplitResult splitResult = SplitResult.empty();
        if (config.mode().generatesExecutedMtData()) {
            splitResult = splitExecutedMtData(runResult.output(), config);
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
                + "\n\n" + runResult.output());
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

        int sourceCount = countOccurrences(trimmed, "\"source\"");
        if (sourceCount < config.count()) {
            return "Expected at least " + config.count() + " entries with \"source\", found " + sourceCount + ".";
        }

        int followUpCount = countOccurrences(trimmed, "\"followUp\"");
        if ((config.mode().generatesFollowUpData() || config.mode().generatesExecutedMtData())
                && followUpCount < config.count()) {
            return "Expected at least " + config.count() + " entries with \"followUp\", found " + followUpCount + ".";
        }

        if (config.mode().generatesExecutedMtData()) {
            int sourceOutputCount = countOccurrences(trimmed, "\"sourceOutput\"");
            if (sourceOutputCount < config.count()) {
                return "Expected at least " + config.count()
                        + " entries with \"sourceOutput\", found " + sourceOutputCount + ".";
            }
            int followUpOutputCount = countOccurrences(trimmed, "\"followUpOutput\"");
            if (followUpOutputCount < config.count()) {
                return "Expected at least " + config.count()
                        + " entries with \"followUpOutput\", found " + followUpOutputCount + ".";
            }
            int passedCount = countOccurrences(trimmed, "\"passed\"");
            if (passedCount < config.count()) {
                return "Expected at least " + config.count()
                        + " entries with \"passed\", found " + passedCount + ".";
            }
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

    private int countOccurrences(String text, String search) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(search, index)) != -1) {
            count++;
            index += search.length();
        }
        return count;
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
