package mtllm.report;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import mtllm.config.PromptConfig;
import mtllm.sut.SutContext;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Writes a human-readable HTML report for executed metamorphic test data.
 *
 * <p>In simple terms, this turns the generated JSON pass/fail data into a browser-friendly
 * summary page for demos and supervisor review.</p>
 */
public final class HtmlReportWriter {
    private static final String TEMPLATE_DIRECTORY = "reports";
    private static final String TEMPLATE_NAME = "mt-report.ftl";

    private HtmlReportWriter() {
    }

    public static Path writeExecutedDataReport(
            Path reportsDir,
            PromptConfig config,
            SutContext sutContext,
            List<String> allEntries,
            List<String> passingEntries,
            List<String> failingEntries,
            Path fullJsonFile,
            Path passingJsonFile,
            Path failingJsonFile) throws IOException {
        return writeExecutedDataReport(
                reportsDir,
                config,
                sutContext,
                allEntries,
                passingEntries,
                failingEntries,
                fullJsonFile,
                passingJsonFile,
                failingJsonFile,
                List.of(),
                List.of());
    }

    public static Path writeExecutedDataReport(
            Path reportsDir,
            PromptConfig config,
            SutContext sutContext,
            List<String> allEntries,
            List<String> passingEntries,
            List<String> failingEntries,
            Path fullJsonFile,
            Path passingJsonFile,
            Path failingJsonFile,
            List<String> passingTestNames,
            List<String> failingTestNames) throws IOException {
        Files.createDirectories(reportsDir);

        Path reportFile = reportsDir.resolve(baseName(config.generatedClassName()) + "Report.html");
        Map<String, Object> model = new LinkedHashMap<>();
        model.put("title", baseName(config.generatedClassName()) + " Metamorphic Test Report");
        model.put("generatedAt", LocalDateTime.now().toString());
        model.put("sutClassFile", valueOrMissing(sutContext.classFile()));
        model.put("targetFunction", valueOrMissing(config.targetFunction()));
        model.put("metamorphicRelation", config.metamorphicRelationStatement());
        model.put("inputDomain", valueOrMissing(config.inputDomain()));
        model.put("generatedClassName", config.generatedClassName());
        model.put("mrProvider", config.mrProvider().name());
        model.put("jsonRequired", config.jsonRequired());
        model.put("testSuiteRequired", config.testSuiteRequired());
        model.put("totalCount", allEntries.size());
        model.put("passingCount", passingEntries.size());
        model.put("failingCount", failingEntries.size());
        model.put("allEntries", allEntries);
        model.put("passingEntries", passingEntries);
        model.put("failingEntries", failingEntries);
        model.put("fullJsonFile", valueOrMissing(fullJsonFile));
        model.put("passingJsonFile", valueOrMissing(passingJsonFile));
        model.put("failingJsonFile", valueOrMissing(failingJsonFile));
        model.put("testSuiteGenerated", config.testSuiteRequired());
        model.put("passingTestNames", passingTestNames);
        model.put("failingTestNames", failingTestNames);
        model.put("totalTestNames", passingTestNames.size() + failingTestNames.size());
        model.put("caseRows", caseRows(passingEntries, failingEntries, passingTestNames, failingTestNames, config));

        Template template = templateConfiguration().getTemplate(TEMPLATE_NAME);
        try (Writer writer = Files.newBufferedWriter(reportFile, StandardCharsets.UTF_8)) {
            template.process(model, writer);
        } catch (TemplateException e) {
            throw new IOException("Could not render HTML report with FreeMarker.", e);
        }

        return reportFile;
    }

    private static List<ReportCaseRow> caseRows(
            List<String> passingEntries,
            List<String> failingEntries,
            List<String> passingTestNames,
            List<String> failingTestNames,
            PromptConfig config) {
        List<ReportCaseRow> rows = new ArrayList<>();
        appendRows(rows, failingEntries, failingTestNames, "Fail", config);
        appendRows(rows, passingEntries, passingTestNames, "Pass", config);
        return rows;
    }

    private static void appendRows(
            List<ReportCaseRow> rows,
            List<String> entries,
            List<String> testNames,
            String status,
            PromptConfig config) {
        for (int i = 0; i < entries.size(); i++) {
            String entry = entries.get(i);
            String testName = i < testNames.size() ? testNames.get(i) : "not linked";
            rows.add(new ReportCaseRow(
                    rows.size() + 1,
                    status,
                    testName,
                    fieldValue(entry, "source"),
                    fieldValue(entry, "sourceOutput"),
                    fieldValue(entry, "followUp"),
                    fieldValue(entry, "followUpOutput"),
                    expectedValue(entry, config),
                    entry));
        }
    }

    private static String expectedValue(String json, PromptConfig config) {
        String expectedFollowUpOutput = fieldValue(json, "expectedFollowUpOutput");
        if (!expectedFollowUpOutput.equals("not provided")) {
            return expectedFollowUpOutput;
        }
        String expectedOutput = fieldValue(json, "expectedOutput");
        if (!expectedOutput.equals("not provided")) {
            return expectedOutput;
        }
        String expected = fieldValue(json, "expected");
        if (!expected.equals("not provided")) {
            return expected;
        }
        String relation = config.metamorphicRelationStatement();
        return relation == null || relation.isBlank() ? "defined by metamorphic relation" : relation;
    }

    private static String fieldValue(String json, String key) {
        int keyStart = findTopLevelKey(json, key);
        if (keyStart < 0) {
            return "not provided";
        }
        int colon = json.indexOf(':', keyStart);
        if (colon < 0) {
            return "not provided";
        }
        int valueStart = skipWhitespace(json, colon + 1);
        int valueEnd = findValueEnd(json, valueStart);
        if (valueStart < 0 || valueEnd <= valueStart) {
            return "not provided";
        }
        return json.substring(valueStart, valueEnd).trim();
    }

    private static int findTopLevelKey(String json, String key) {
        String quotedKey = "\"" + key + "\"";
        int depth = 0;
        boolean inString = false;
        boolean escaping = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (escaping) {
                escaping = false;
                continue;
            }
            if (c == '\\' && inString) {
                escaping = true;
                continue;
            }
            if (c == '"') {
                if (!inString && depth == 1 && json.startsWith(quotedKey, i)) {
                    return i;
                }
                inString = !inString;
                continue;
            }
            if (inString) {
                continue;
            }
            if (c == '{' || c == '[') {
                depth++;
            } else if (c == '}' || c == ']') {
                depth--;
            }
        }
        return -1;
    }

    private static int skipWhitespace(String text, int index) {
        int current = index;
        while (current < text.length() && Character.isWhitespace(text.charAt(current))) {
            current++;
        }
        return current;
    }

    private static int findValueEnd(String json, int valueStart) {
        if (valueStart >= json.length()) {
            return -1;
        }
        char first = json.charAt(valueStart);
        if (first == '{' || first == '[') {
            return findStructuredValueEnd(json, valueStart);
        }
        if (first == '"') {
            return findStringEnd(json, valueStart);
        }
        int index = valueStart;
        while (index < json.length() && json.charAt(index) != ',' && json.charAt(index) != '}') {
            index++;
        }
        return index;
    }

    private static int findStructuredValueEnd(String json, int valueStart) {
        int depth = 0;
        boolean inString = false;
        boolean escaping = false;
        for (int i = valueStart; i < json.length(); i++) {
            char c = json.charAt(i);
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
            if (c == '{' || c == '[') {
                depth++;
            } else if (c == '}' || c == ']') {
                depth--;
                if (depth == 0) {
                    return i + 1;
                }
            }
        }
        return json.length();
    }

    private static int findStringEnd(String json, int valueStart) {
        boolean escaping = false;
        for (int i = valueStart + 1; i < json.length(); i++) {
            char c = json.charAt(i);
            if (escaping) {
                escaping = false;
                continue;
            }
            if (c == '\\') {
                escaping = true;
            } else if (c == '"') {
                return i + 1;
            }
        }
        return json.length();
    }

    private static Configuration templateConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_34);
        configuration.setClassForTemplateLoading(HtmlReportWriter.class, "/" + TEMPLATE_DIRECTORY);
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        return configuration;
    }

    private static String baseName(String generatedClassName) {
        if (generatedClassName.endsWith("Data")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Data".length());
        }
        if (generatedClassName.endsWith("Test")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Test".length());
        }
        return generatedClassName;
    }

    private static String valueOrMissing(Object value) {
        if (value == null) {
            return "not provided";
        }
        String text = value.toString();
        return text.isBlank() ? "not provided" : text;
    }

    public static final class ReportCaseRow {
        private final int index;
        private final String status;
        private final String testMethod;
        private final String sourceInput;
        private final String sourceOutput;
        private final String followUpInput;
        private final String followUpOutput;
        private final String expectedOutput;
        private final String rawJson;

        private ReportCaseRow(
                int index,
                String status,
                String testMethod,
                String sourceInput,
                String sourceOutput,
                String followUpInput,
                String followUpOutput,
                String expectedOutput,
                String rawJson) {
            this.index = index;
            this.status = status;
            this.testMethod = testMethod;
            this.sourceInput = sourceInput;
            this.sourceOutput = sourceOutput;
            this.followUpInput = followUpInput;
            this.followUpOutput = followUpOutput;
            this.expectedOutput = expectedOutput;
            this.rawJson = rawJson;
        }

        public int getIndex() {
            return index;
        }

        public String getStatus() {
            return status;
        }

        public String getTestMethod() {
            return testMethod;
        }

        public String getSourceInput() {
            return sourceInput;
        }

        public String getSourceOutput() {
            return sourceOutput;
        }

        public String getFollowUpInput() {
            return followUpInput;
        }

        public String getFollowUpOutput() {
            return followUpOutput;
        }

        public String getExpectedOutput() {
            return expectedOutput;
        }

        public String getRawJson() {
            return rawJson;
        }
    }
}
