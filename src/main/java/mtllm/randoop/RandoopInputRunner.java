package mtllm.randoop;

import mtllm.config.PromptConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Drives the Randoop input-generation mode from inside the pipeline.
 *
 * <p>Runs {@link RandoopDataGenerator} in a <b>separate JVM</b> so the SUT classes are genuinely on
 * the classpath (Randoop reflects them by name; they are not part of the project build). Steps:
 * compile the SUT + support + developer-MR files into a classes dir, launch the generator subprocess
 * with that dir + the Randoop jar + the project classes on {@code -cp}, and read back the
 * executed-MT JSON it writes to a file. The returned JSON is fed to
 * {@code DataGeneratorRunner.writeSplitAndReport}, exactly like the LLM path's output.</p>
 *
 * <p>When the prompt requires a test suite, the subprocess also writes the object JUnit suite
 * (approach C: {@code …PassingTest} / {@code …FailingTest}) into the example's {@code junit-tests}
 * source dir. This runner then compile-gates those classes (against the just-compiled SUT + JUnit
 * API) so a construction-code defect surfaces here rather than at {@code mvn test} time.</p>
 */
public final class RandoopInputRunner {

    private final Path repoRoot;
    private final Path randoopJar;
    private final Path projectClasses;
    private final Path workDir;

    public RandoopInputRunner(Path repoRoot, Path randoopJar, Path projectClasses, Path workDir) {
        this.repoRoot = repoRoot;
        this.randoopJar = randoopJar;
        this.projectClasses = projectClasses;
        this.workDir = workDir;
    }

    /** All artifacts the Randoop subprocess produced, plus the suite compile-gate outcome. */
    public record GenerationResult(String json, boolean suiteEmitted, Path passingFile, Path failingFile,
                                   boolean suiteCompiled, String suiteCompileOutput) {
    }

    /** Compile the SUT, run the generator subprocess, and return the JSON + emitted suite info. */
    public GenerationResult generate(PromptConfig config, Path promptPath) throws Exception {
        Path classesDir = workDir.resolve("classes");
        Files.createDirectories(classesDir);

        // 1. Compile SUT + support + developer-MR into classesDir so the subprocess (and Randoop)
        //    can load them by name.
        List<String> javac = new ArrayList<>();
        javac.add("javac");
        javac.add("-encoding");
        javac.add("UTF-8");
        javac.add("-d");
        javac.add(classesDir.toString());
        if (config.sutClassFile() != null) {
            javac.add(config.sutClassFile().toString());
        }
        for (Path support : config.sutSupportFiles()) {
            javac.add(support.toString());
        }
        if (config.developerMrFile() != null) {
            javac.add(config.developerMrFile().toString());
        }
        ProcessResult compile = runProcess(javac);
        if (compile.exitCode != 0) {
            throw new IllegalStateException("Randoop SUT compilation failed:\n" + compile.output);
        }

        // 2. Run the generator in a subprocess; it writes the JSON to outJson and (when a test suite
        //    is required) the object JUnit suite into <outputRoot>/junit-tests.
        Path outJson = workDir.resolve("randoop-data.json");
        Files.deleteIfExists(outJson);
        String classpath = String.join(File.pathSeparator,
                projectClasses.toString(), randoopJar.toString(), classesDir.toString());
        List<String> java = new ArrayList<>(List.of(
                "java", "-cp", classpath, "mtllm.randoop.RandoopDataGenerator",
                promptPath.toString(), outJson.toString()));
        ProcessResult run = runProcess(java);
        if (!Files.exists(outJson)) {
            throw new IllegalStateException("Randoop generation produced no output file.\n" + run.output);
        }
        String json = Files.readString(outJson, StandardCharsets.UTF_8);

        if (!config.testSuiteRequired()) {
            return new GenerationResult(json, false, null, null, false, "");
        }

        String base = baseName(config.generatedClassName());
        Path junitDir = config.outputRoot().resolve("junit-tests");
        Path passingFile = junitDir.resolve(base + "PassingTest.java");
        Path failingFile = junitDir.resolve(base + "FailingTest.java");
        if (!Files.exists(passingFile) || !Files.exists(failingFile)) {
            throw new IllegalStateException("Randoop test-suite generation produced no JUnit files in "
                    + junitDir + ".\n" + run.output);
        }

        ProcessResult gate = compileSuiteGate(classesDir, passingFile, failingFile);
        return new GenerationResult(json, true, passingFile, failingFile, gate.exitCode == 0, gate.output);
    }

    /**
     * Compile the emitted passing/failing classes against the already-compiled SUT classes plus the
     * JUnit API jars from the local Maven repo. Non-fatal: if the JUnit jars cannot be located the
     * gate is skipped (reported as not-compiled with an explanatory message), since the suite files
     * have still been written and remain valid for {@code mvn test}.
     */
    private ProcessResult compileSuiteGate(Path sutClassesDir, Path passingFile, Path failingFile)
            throws IOException, InterruptedException {
        String junitClasspath = locateJUnitClasspath();
        if (junitClasspath.isEmpty()) {
            return new ProcessResult(1,
                    "JUnit API jars not found under the local Maven repository; suite compile-gate skipped.");
        }
        Path gateClasses = workDir.resolve("junit-gate-classes");
        Files.createDirectories(gateClasses);
        List<String> javac = new ArrayList<>(List.of(
                "javac", "-encoding", "UTF-8",
                "-cp", junitClasspath + File.pathSeparator + sutClassesDir,
                "-d", gateClasses.toString(),
                passingFile.toString(), failingFile.toString()));
        return runProcess(javac);
    }

    /** Build a classpath of the JUnit API jars (and transitive deps) from {@code ~/.m2/repository}. */
    private static String locateJUnitClasspath() {
        Path m2 = Path.of(System.getProperty("user.home"), ".m2", "repository");
        List<String> jars = new ArrayList<>();
        addLatestJar(jars, m2.resolve("org/junit/jupiter/junit-jupiter-api"), "junit-jupiter-api");
        addLatestJar(jars, m2.resolve("org/junit/platform/junit-platform-commons"), "junit-platform-commons");
        addLatestJar(jars, m2.resolve("org/opentest4j/opentest4j"), "opentest4j");
        addLatestJar(jars, m2.resolve("org/apiguardian/apiguardian-api"), "apiguardian-api");
        // junit-jupiter-api is the only hard requirement (provides org.junit.jupiter.api.Test).
        boolean hasApi = jars.stream().anyMatch(j -> j.contains("junit-jupiter-api"));
        return hasApi ? String.join(File.pathSeparator, jars) : "";
    }

    /** Find the newest non-sources/javadoc {@code <artifact>-<version>.jar} under a Maven artifact dir. */
    private static void addLatestJar(List<String> into, Path artifactDir, String artifact) {
        if (!Files.isDirectory(artifactDir)) {
            return;
        }
        try (Stream<Path> versions = Files.list(artifactDir)) {
            versions.filter(Files::isDirectory)
                    .map(v -> v.resolve(artifact + "-" + v.getFileName() + ".jar"))
                    .filter(Files::isRegularFile)
                    .max(Path::compareTo)
                    .ifPresent(jar -> into.add(jar.toString()));
        } catch (IOException ignored) {
            // Treat an unreadable artifact dir as "jar not present".
        }
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

    private ProcessResult runProcess(List<String> command) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command)
                .directory(repoRoot.toFile())
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
