package mtllm.runner;

import mtllm.config.PromptConfig;
import mtllm.sut.SutContext;

import java.io.ByteArrayOutputStream;
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
    private final String junitConsoleJar;
    private final String mavenCommand;

    public GeneratedTestRunner(Path repoRoot, Path classesDir, String junitConsoleJar, String mavenCommand) {
        this.repoRoot = repoRoot;
        this.classesDir = classesDir;
        this.junitConsoleJar = junitConsoleJar == null ? "" : junitConsoleJar.trim();
        this.mavenCommand = mavenCommand == null || mavenCommand.trim().isEmpty() ? "mvn" : mavenCommand.trim();
    }

    public TestRunResult compileAndRun(Path generatedTestFile, PromptConfig config, SutContext sutContext) throws Exception {
        if (junitConsoleJar.isBlank()) {
            return runWithMavenIfAvailable(generatedTestFile, config);
        }
        Files.createDirectories(classesDir);

        TestRunResult compileResult = compile(generatedTestFile, config, sutContext);
        if (!compileResult.passed()) {
            return compileResult;
        }
        return run(config.generatedClassName());
    }

    private TestRunResult runWithMavenIfAvailable(Path generatedTestFile, PromptConfig config) throws Exception {
        if (!Files.isRegularFile(repoRoot.resolve("pom.xml"))) {
            return TestRunResult.skipped(
                    "Generated test was written, but compile/run was skipped because neither "
                            + "JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR nor pom.xml is configured.");
        }

        List<String> command = new ArrayList<>();
        command.add(mavenCommand);
        command.add("test");
        command.add("-Dtest=" + config.generatedClassName());

        try {
            Files.deleteIfExists(repoRoot.resolve("target")
                    .resolve("surefire-reports")
                    .resolve("TEST-" + config.generatedClassName() + ".xml"));
            ProcessResult result = runProcess(command, repoRoot);
            if (result.exitCode == 0) {
                return splitActualResults(generatedTestFile, config, result.output);
            }
            if (isCompilationFailure(result.output)) {
                return TestRunResult.failed("Maven compilation failed:\n" + result.output);
            }
            if (isExpectedTestFailureOutput(result.output)) {
                return splitActualResults(generatedTestFile, config, result.output);
            }
            return TestRunResult.failed("Maven test failed:\n" + result.output);
        } catch (IOException e) {
            return TestRunResult.skipped(
                    "Generated test was written, but Maven could not be started. "
                            + "Install Maven or configure JUNIT_PLATFORM_CONSOLE_STANDALONE_JAR. "
                            + "Details: " + e.getMessage());
        }
    }

    private TestRunResult splitActualResults(Path generatedTestFile, PromptConfig config, String mavenOutput) throws Exception {
        ActualResultTestSplitter.SplitResult splitResult = ActualResultTestSplitter.split(
                repoRoot,
                generatedTestFile,
                config);
        return TestRunResult.passed(
                "Generated candidate tests were executed and split by actual JUnit results.\n"
                        + "Passing tests: " + splitResult.passingCount() + " -> " + splitResult.passingFile() + "\n"
                        + "Failing tests: " + splitResult.failingCount() + " -> " + splitResult.failingFile() + "\n\n"
                        + mavenOutput);
    }

    private TestRunResult compile(Path generatedTestFile, PromptConfig config, SutContext sutContext) throws Exception {
        List<String> command = new ArrayList<>();
        command.add("javac");
        command.add("-cp");
        command.add(classpath());
        command.add("-d");
        command.add(classesDir.toString());
        if (config.sutClassFile() != null) {
            command.add(config.sutClassFile().toString());
        }
        for (SutContext.SourceFile supportFile : sutContext.supportFiles()) {
            command.add(supportFile.path().toString());
        }
        command.add(generatedTestFile.toString());

        ProcessResult result = runProcess(command, repoRoot);
        if (result.exitCode == 0) {
            return TestRunResult.passed(result.output);
        }
        return TestRunResult.failed("Compilation failed:\n" + result.output);
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

        ProcessResult result = runProcess(command, repoRoot);
        if (result.exitCode == 0) {
            return TestRunResult.passed(result.output);
        }
        if (isExpectedTestFailureOutput(result.output)) {
            return TestRunResult.passed("Generated failing-only suite found MR violations:\n" + result.output);
        }
        return TestRunResult.failed("JUnit execution failed:\n" + result.output);
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

    private String classpath() {
        return junitConsoleJar + pathSeparator() + repoRoot + pathSeparator() + classesDir;
    }

    private static String pathSeparator() {
        return System.getProperty("path.separator");
    }

    private static ProcessResult runProcess(List<String> command, Path workDir) throws IOException, InterruptedException {
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
}
