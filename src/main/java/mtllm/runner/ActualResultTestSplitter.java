package mtllm.runner;

import mtllm.config.PromptConfig;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Splits a generated candidate JUnit class into passing and failing classes using actual JUnit
 * execution results.
 *
 * <p>In simple terms, this class stops the LLM from guessing which tests fail. It reads the Maven
 * test report, sees which @Test methods really passed or failed, then writes separate files.</p>
 */
public final class ActualResultTestSplitter {
    private static final Pattern METHOD_NAME = Pattern.compile(
            "\\b(?:public\\s+|protected\\s+|private\\s+)?(?:static\\s+)?void\\s+([A-Za-z_$][A-Za-z0-9_$]*)\\s*\\(");

    private ActualResultTestSplitter() {
    }

    public static SplitResult split(Path repoRoot, Path generatedTestFile, PromptConfig config) throws Exception {
        Path reportFile = repoRoot.resolve("target")
                .resolve("surefire-reports")
                .resolve("TEST-" + config.generatedClassName() + ".xml");
        TestOutcomes outcomes = readOutcomes(reportFile);
        JavaTestClass javaClass = parseJavaTestClass(generatedTestFile, config.generatedClassName());

        String baseClassName = baseClassName(config.generatedClassName());
        String passingClassName = baseClassName + "PassingTest";
        String failingClassName = baseClassName + "FailingTest";

        List<TestMethod> passingMethods = new ArrayList<>();
        List<TestMethod> failingMethods = new ArrayList<>();
        for (TestMethod method : javaClass.testMethods) {
            if (outcomes.failingMethods.contains(method.name)) {
                failingMethods.add(method);
            } else if (outcomes.allMethods.contains(method.name)) {
                passingMethods.add(method);
            }
        }

        Path outputDir = generatedTestFile.getParent();
        Path passingFile = outputDir.resolve(passingClassName + ".java");
        Path failingFile = outputDir.resolve(failingClassName + ".java");
        Files.writeString(
                passingFile,
                javaClass.render(config.generatedClassName(), passingClassName, passingMethods),
                StandardCharsets.UTF_8);
        Files.writeString(
                failingFile,
                javaClass.render(config.generatedClassName(), failingClassName, failingMethods),
                StandardCharsets.UTF_8);
        Files.deleteIfExists(generatedTestFile);

        return new SplitResult(
                passingFile,
                failingFile,
                methodNames(passingMethods),
                methodNames(failingMethods));
    }

    private static TestOutcomes readOutcomes(Path reportFile) throws Exception {
        if (!Files.isRegularFile(reportFile)) {
            throw new IOException("Missing JUnit XML report: " + reportFile);
        }

        Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(reportFile.toFile());
        NodeList testCases = document.getElementsByTagName("testcase");
        Set<String> allMethods = new LinkedHashSet<>();
        Set<String> failingMethods = new LinkedHashSet<>();

        for (int i = 0; i < testCases.getLength(); i++) {
            Element testCase = (Element) testCases.item(i);
            String name = testCase.getAttribute("name");
            if (name == null || name.isBlank()) {
                continue;
            }
            allMethods.add(name);
            if (hasChild(testCase, "failure") || hasChild(testCase, "error")) {
                failingMethods.add(name);
            }
        }

        return new TestOutcomes(allMethods, failingMethods);
    }

    private static boolean hasChild(Element element, String tagName) {
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element && tagName.equals(((Element) child).getTagName())) {
                return true;
            }
        }
        return false;
    }

    private static JavaTestClass parseJavaTestClass(Path generatedTestFile, String className) throws IOException {
        List<String> lines = Files.readAllLines(generatedTestFile, StandardCharsets.UTF_8);
        List<String> skeleton = new ArrayList<>();
        Map<String, TestMethod> testMethods = new LinkedHashMap<>();

        int index = 0;
        while (index < lines.size()) {
            String line = lines.get(index);
            if (isTestAnnotation(line)) {
                ExtractedMethod extracted = extractTestMethod(lines, index);
                testMethods.put(extracted.method.name, extracted.method);
                index = extracted.nextIndex;
            } else {
                skeleton.add(line);
                index++;
            }
        }

        return new JavaTestClass(className, skeleton, new ArrayList<>(testMethods.values()));
    }

    private static boolean isTestAnnotation(String line) {
        String trimmed = line.trim();
        return "@Test".equals(trimmed) || trimmed.startsWith("@Test(");
    }

    private static ExtractedMethod extractTestMethod(List<String> lines, int startIndex) {
        List<String> block = new ArrayList<>();
        String methodName = "generatedTest";
        int braceDepth = 0;
        boolean bodyStarted = false;
        int index = startIndex;

        while (index < lines.size()) {
            String line = lines.get(index);
            block.add(line);
            Matcher matcher = METHOD_NAME.matcher(line);
            if (matcher.find()) {
                methodName = matcher.group(1);
            }

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '{') {
                    braceDepth++;
                    bodyStarted = true;
                } else if (c == '}') {
                    braceDepth--;
                }
            }

            index++;
            if (bodyStarted && braceDepth == 0) {
                break;
            }
        }

        return new ExtractedMethod(new TestMethod(methodName, block), index);
    }

    private static String baseClassName(String generatedClassName) {
        if (generatedClassName.endsWith("Test")) {
            return generatedClassName.substring(0, generatedClassName.length() - "Test".length());
        }
        return generatedClassName;
    }

    private static List<String> methodNames(List<TestMethod> methods) {
        return methods.stream().map(method -> method.name).toList();
    }

    public static final class SplitResult {
        private final Path passingFile;
        private final Path failingFile;
        private final List<String> passingMethodNames;
        private final List<String> failingMethodNames;

        private SplitResult(
                Path passingFile,
                Path failingFile,
                List<String> passingMethodNames,
                List<String> failingMethodNames) {
            this.passingFile = passingFile;
            this.failingFile = failingFile;
            this.passingMethodNames = List.copyOf(passingMethodNames);
            this.failingMethodNames = List.copyOf(failingMethodNames);
        }

        public Path passingFile() {
            return passingFile;
        }

        public Path failingFile() {
            return failingFile;
        }

        public int passingCount() {
            return passingMethodNames.size();
        }

        public int failingCount() {
            return failingMethodNames.size();
        }

        public List<String> passingMethodNames() {
            return passingMethodNames;
        }

        public List<String> failingMethodNames() {
            return failingMethodNames;
        }
    }

    private static final class TestOutcomes {
        private final Set<String> allMethods;
        private final Set<String> failingMethods;

        private TestOutcomes(Set<String> allMethods, Set<String> failingMethods) {
            this.allMethods = allMethods;
            this.failingMethods = failingMethods;
        }
    }

    private static final class JavaTestClass {
        private final String className;
        private final List<String> skeleton;
        private final List<TestMethod> testMethods;

        private JavaTestClass(String className, List<String> skeleton, List<TestMethod> testMethods) {
            this.className = className;
            this.skeleton = skeleton;
            this.testMethods = testMethods;
        }

        private String render(String originalClassName, String newClassName, List<TestMethod> selectedMethods) {
            List<String> output = new ArrayList<>();
            int insertionIndex = lastClassClosingBraceIndex(skeleton);

            for (int i = 0; i < skeleton.size(); i++) {
                if (i == insertionIndex) {
                    appendSelectedMethods(output, selectedMethods);
                }
                output.add(skeleton.get(i).replace("class " + originalClassName, "class " + newClassName)
                        .replace("public " + originalClassName + "(", "public " + newClassName + "("));
            }
            return String.join(System.lineSeparator(), collapseBlankRuns(output)).trim() + System.lineSeparator();
        }

        private int lastClassClosingBraceIndex(List<String> lines) {
            for (int i = lines.size() - 1; i >= 0; i--) {
                if ("}".equals(lines.get(i).trim())) {
                    return i;
                }
            }
            return lines.size();
        }

        private void appendSelectedMethods(List<String> output, List<TestMethod> selectedMethods) {
            for (TestMethod method : selectedMethods) {
                output.add("");
                output.addAll(method.lines);
            }
        }

        private List<String> collapseBlankRuns(List<String> lines) {
            List<String> collapsed = new ArrayList<>();
            boolean previousBlank = false;
            for (String line : lines) {
                boolean blank = line.trim().isEmpty();
                if (blank && previousBlank) {
                    continue;
                }
                collapsed.add(line);
                previousBlank = blank;
            }
            return collapsed;
        }
    }

    private static final class TestMethod {
        private final String name;
        private final List<String> lines;

        private TestMethod(String name, List<String> lines) {
            this.name = name;
            this.lines = lines;
        }
    }

    private static final class ExtractedMethod {
        private final TestMethod method;
        private final int nextIndex;

        private ExtractedMethod(TestMethod method, int nextIndex) {
            this.method = method;
            this.nextIndex = nextIndex;
        }
    }
}
