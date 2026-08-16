package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.sut.CompiledClassPath;
import mtllm.sut.SutContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Compiles and optionally executes the generated JUnit 5 test class.
 *
 * <p>In simple terms, this class checks whether the model's Java code is actually usable by
 * running Maven/JUnit. For JUnit generation modes, it then splits candidate tests into separate
 * passing and failing files using actual execution results.</p>
 */
public final class GeneratedTestRunner {
    private final Path repoRoot;
    private final Path classesDir;
    private final Path supportSourceDir;
    private final String junitConsoleJar;
    private final String mavenCommand;
    private ActualResultTestSplitter.SplitResult lastSplitResult;

    public GeneratedTestRunner(Path repoRoot, Path classesDir, Path supportSourceDir, String junitConsoleJar, String mavenCommand) {
        this.repoRoot = repoRoot;
        this.classesDir = classesDir;
        this.supportSourceDir = supportSourceDir;
        this.junitConsoleJar = junitConsoleJar == null ? "" : junitConsoleJar.trim();
        this.mavenCommand = mavenCommand == null || mavenCommand.trim().isEmpty() ? "mvn" : mavenCommand.trim();
    }

    public TestRunResult compileAndRun(Path generatedTestFile, PromptConfig config, SutContext sutContext) throws Exception {
        lastSplitResult = null;
        String countValidationError = validateGeneratedTestCount(generatedTestFile, config);
        if (countValidationError != null) {
            return TestRunResult.failed("Generated JUnit validation failed:\n" + countValidationError);
        }
        if (junitConsoleJar.isBlank()) {
            return runWithMavenIfAvailable(generatedTestFile, config, sutContext);
        }
        Files.createDirectories(classesDir);

        TestRunResult compileResult = compile(generatedTestFile, config, sutContext);
        if (!compileResult.passed()) {
            return compileResult;
        }
        RuntimeResourceCopier.copyFor(config, classesDir);
        return run(config.generatedClassName());
    }

    private String validateGeneratedTestCount(Path generatedTestFile, PromptConfig config) throws IOException {
        int testCount = 0;
        for (String line : Files.readAllLines(generatedTestFile, StandardCharsets.UTF_8)) {
            String trimmed = line.trim();
            if ("@Test".equals(trimmed) || trimmed.startsWith("@Test(")) {
                testCount++;
            }
        }
        if (testCount > config.count()) {
            return "Expected at most " + config.count() + " JUnit test methods, found " + testCount + ".";
        }
        return null;
    }

    private TestRunResult runWithMavenIfAvailable(Path generatedTestFile, PromptConfig config, SutContext sutContext) throws Exception {
        if (!Files.isRegularFile(repoRoot.resolve("pom.xml"))) {
            return TestRunResult.skipped(
                    "Generated test was written, but compile/run was skipped because neither "
                            + "JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR nor pom.xml is configured.");
        }

        prepareMavenTestSources(generatedTestFile, config, sutContext);

        RuntimeResourceCopier.copyFor(config, repoRoot.resolve("target").resolve("test-classes"));

        List<String> command = new ArrayList<>();
        command.add(mavenCommand);
        command.add("test");
        if (!config.mavenProfiles().isEmpty()) {
            command.add("-P" + String.join(",", config.mavenProfiles()));
        }
        command.add("-Dtest=" + config.generatedClassName());

        try {
            Files.deleteIfExists(repoRoot.resolve("target")
                    .resolve("surefire-reports")
                    .resolve("TEST-" + config.generatedClassName() + ".xml"));
            ProcessRunner.Result result = ProcessRunner.run(command, repoRoot);
            if (result.exitCode() == 0) {
                return splitActualResults(generatedTestFile, config, result.output());
            }
            if (isCompilationFailure(result.output())) {
                return TestRunResult.failed("Maven compilation failed:\n" + result.output());
            }
            if (isExpectedTestFailureOutput(result.output())) {
                return splitActualResults(generatedTestFile, config, result.output());
            }
            return TestRunResult.failed("Maven test failed:\n" + result.output());
        } catch (IOException e) {
            return TestRunResult.skipped(
                    "Generated test was written, but Maven could not be started. "
                            + "Install Maven or configure JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR. "
                            + "Details: " + e.getMessage());
        }
    }

    private void prepareMavenTestSources(Path generatedTestFile, PromptConfig config, SutContext sutContext) throws IOException {
        Files.createDirectories(generatedTestFile.getParent());
        Files.createDirectories(supportSourceDir);
        clearJavaFiles(supportSourceDir);
        Path stagedTestSourceDir = repoRoot.resolve("target").resolve("mtllm-test-sources").resolve("junit-tests");
        Path stagedSupportSourceDir = repoRoot.resolve("target").resolve("mtllm-test-sources").resolve("junit-support");
        Files.createDirectories(stagedTestSourceDir);
        Files.createDirectories(stagedSupportSourceDir);
        clearJavaFiles(stagedTestSourceDir);
        clearJavaFiles(stagedSupportSourceDir);
        copyJavaFileIfPresent(generatedTestFile, stagedTestSourceDir);
        copyUncompiledJavaFileIfPresent(config.sutClassFile(), supportSourceDir, config);
        copyUncompiledJavaFileIfPresent(config.sutClassFile(), stagedSupportSourceDir, config);
        for (SutContext.SourceFile supportFile : sutContext.supportFiles()) {
            copyUncompiledJavaFileIfPresent(supportFile.path(), supportSourceDir, config);
            copyUncompiledJavaFileIfPresent(supportFile.path(), stagedSupportSourceDir, config);
        }
        if (config.mode().usesDeveloperMrHelpers()) {
            copyJavaFileIfPresent(config.developerMrFile(), supportSourceDir);
            copyJavaFileIfPresent(config.developerMrFile(), stagedSupportSourceDir);
        }
        for (Path file : generatedDataSourceFiles(config)) {
            copyJavaFileIfPresent(file, supportSourceDir);
            copyJavaFileIfPresent(file, stagedSupportSourceDir);
        }
        for (Path file : generatedSupportSourceFiles(config)) {
            copyJavaFileIfPresent(file, supportSourceDir);
            copyJavaFileIfPresent(file, stagedSupportSourceDir);
        }
    }

    private void copyJavaFileIfPresent(Path sourceFile, Path targetDir) throws IOException {
        if (sourceFile == null || !Files.isRegularFile(sourceFile)) {
            return;
        }
        Files.copy(sourceFile, targetDir.resolve(sourceFile.getFileName()), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    private void copyUncompiledJavaFileIfPresent(Path sourceFile, Path targetDir, PromptConfig config)
            throws IOException {
        if (!CompiledClassPath.contains(config.sutClasspath(), sourceFile)) {
            copyJavaFileIfPresent(sourceFile, targetDir);
        }
    }

    private void clearJavaFiles(Path targetDir) throws IOException {
        try (var stream = Files.list(targetDir)) {
            for (Path file : stream.filter(path -> path.getFileName().toString().endsWith(".java")).toList()) {
                Files.deleteIfExists(file);
            }
        }
    }

    private TestRunResult splitActualResults(Path generatedTestFile, PromptConfig config, String mavenOutput) throws Exception {
        ActualResultTestSplitter.SplitResult splitResult = ActualResultTestSplitter.split(
                repoRoot,
                generatedTestFile,
                config);
        stageSplitTestsForMaven(splitResult);
        lastSplitResult = splitResult;
        return TestRunResult.passed(
                "Generated candidate tests were executed and split by actual JUnit results.\n"
                        + "Passing tests: " + splitResult.passingCount() + " -> " + splitResult.passingFile() + "\n"
                        + "Failing tests: " + splitResult.failingCount() + " -> " + splitResult.failingFile() + "\n\n"
                        + mavenOutput);
    }

    private void stageSplitTestsForMaven(ActualResultTestSplitter.SplitResult splitResult) throws IOException {
        Path stagedTestSourceDir = repoRoot.resolve("target").resolve("mtllm-test-sources").resolve("junit-tests");
        Files.createDirectories(stagedTestSourceDir);
        clearJavaFiles(stagedTestSourceDir);
        copyJavaFileIfPresent(splitResult.passingFile(), stagedTestSourceDir);
        copyJavaFileIfPresent(splitResult.failingFile(), stagedTestSourceDir);
    }

    public ActualResultTestSplitter.SplitResult lastSplitResult() {
        return lastSplitResult;
    }

    private TestRunResult compile(Path generatedTestFile, PromptConfig config, SutContext sutContext) throws Exception {
        List<String> command = new ArrayList<>();
        command.add("javac");
        command.add("-cp");
        command.add(classpath(config));
        command.add("-d");
        command.add(classesDir.toString());
        if (config.sutClassFile() != null
                && !CompiledClassPath.contains(config.sutClasspath(), config.sutClassFile())) {
            command.add(config.sutClassFile().toString());
        }
        for (SutContext.SourceFile supportFile : sutContext.supportFiles()) {
            if (!CompiledClassPath.contains(config.sutClasspath(), supportFile.path())) {
                command.add(supportFile.path().toString());
            }
        }
        if (config.mode().usesDeveloperMrHelpers() && config.developerMrFile() != null) {
            command.add(config.developerMrFile().toString());
        }
        for (Path file : generatedDataSourceFiles(config)) {
            command.add(file.toString());
        }
        command.addAll(generatedSupportSourceFiles(config).stream().map(Path::toString).toList());
        command.add(generatedTestFile.toString());

        ProcessRunner.Result result = ProcessRunner.run(command, repoRoot);
        if (result.exitCode() == 0) {
            return TestRunResult.passed(result.output());
        }
        return TestRunResult.failed("Compilation failed:\n" + result.output());
    }

    private List<Path> generatedDataSourceFiles(PromptConfig config) throws IOException {
        Path generatedDataDir = config.outputRoot().resolve("data-generator-code");
        if (!Files.isDirectory(generatedDataDir)) {
            return List.of();
        }
        try (var stream = Files.list(generatedDataDir)) {
            return stream
                    .filter(path -> path.getFileName().toString().endsWith(".java"))
                    .toList();
        }
    }

    private List<Path> generatedSupportSourceFiles(PromptConfig config) throws IOException {
        Path generatedSupportDir = config.outputRoot().resolve("generated-support");
        if (!Files.isDirectory(generatedSupportDir)) {
            return List.of();
        }
        try (var stream = Files.list(generatedSupportDir)) {
            return stream.filter(path -> path.getFileName().toString().endsWith(".java"))
                    .sorted()
                    .toList();
        }
    }

    private TestRunResult run(String generatedClassName) throws Exception {
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("-jar");
        command.add(junitConsoleJar);
        command.add("--class-path");
        command.add(classesDir.toString() + pathSeparator() + repoRoot);
        command.add("--select-class");
        command.add(generatedClassName);

        ProcessRunner.Result result = ProcessRunner.run(command, repoRoot);
        if (result.exitCode() == 0) {
            return TestRunResult.passed(result.output());
        }
        if (isExpectedTestFailureOutput(result.output())) {
            return TestRunResult.passed("Generated failing-only suite found MR violations:\n" + result.output());
        }
        return TestRunResult.failed("JUnit execution failed:\n" + result.output());
    }

    private static boolean isCompilationFailure(String output) {
        String lower = output.toLowerCase();
        return lower.contains("compilation failure")
                || lower.contains("compilation error")
                || lower.contains("failed to execute goal org.apache.maven.plugins:maven-compiler-plugin");
    }

    private static boolean isExpectedTestFailureOutput(String output) {
        String lower = output.toLowerCase();
        if (lower.matches("(?s).*errors: [1-9][0-9]*.*")
                || lower.matches("(?s).*tests errored: [1-9][0-9]*.*")) {
            return false;
        }
        return lower.matches("(?s).*failures: [1-9][0-9]*.*")
                || lower.matches("(?s).*tests failed: [1-9][0-9]*.*");
    }

    private String classpath(PromptConfig config) {
        List<String> entries = new ArrayList<>();
        entries.add(junitConsoleJar);
        entries.add(repoRoot.toString());
        entries.add(classesDir.toString());
        entries.addAll(config.sutClasspath().stream().map(Path::toString).toList());
        return String.join(pathSeparator(), entries);
    }

    private static String pathSeparator() {
        return System.getProperty("path.separator");
    }

}
