package mtllm.randoop;

import mtllm.config.PromptConfig;
import mtllm.domain.InputDomainInferenceService;
import mtllm.runner.ProcessRunner;
import mtllm.runner.RuntimeResourceCopier;
import mtllm.sut.CompiledClassPath;
import mtllm.util.GeneratedNames;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
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
        Compilation compilation = compileSut(config);

        // Run the generator in a subprocess; it writes the JSON to outJson and (when a test suite
        // is required) the object JUnit suite into <outputRoot>/junit-tests.
        Path outJson = workDir.resolve("randoop-data.json");
        ProcessRunner.Result run = runGenerator(config, promptPath, outJson, compilation, List.of());
        if (!Files.exists(outJson)) {
            throw new IllegalStateException("Randoop generation produced no output file.\n" + run.output());
        }
        String json = Files.readString(outJson, StandardCharsets.UTF_8);

        if (!config.testSuiteRequired()) {
            return new GenerationResult(json, false, null, null, false, "");
        }

        String base = GeneratedNames.baseName(config.generatedClassName());
        Path junitDir = config.outputRoot().resolve("junit-tests");
        Path passingFile = junitDir.resolve(base + "PassingTest.java");
        Path failingFile = junitDir.resolve(base + "FailingTest.java");
        if (!Files.exists(passingFile) || !Files.exists(failingFile)) {
            throw new IllegalStateException("Randoop test-suite generation produced no JUnit files in "
                    + junitDir + ".\n" + run.output());
        }

        ProcessRunner.Result gate = compileSuiteGate(config, compilation.classesDir, passingFile, failingFile);
        requireSuccessfulSuiteCompile(gate.exitCode(), gate.output());
        return new GenerationResult(json, true, passingFile, failingFile, true, gate.output());
    }

    static void requireSuccessfulSuiteCompile(int exitCode, String output) {
        if (exitCode != 0) {
            throw new IllegalStateException("Randoop JUnit suite compile-gate failed:\n" + output);
        }
    }

    /** Generate local Randoop examples for NEW_HYBRID without applying the MR or writing a suite. */
    public String generateSeedExamples(PromptConfig config, Path promptPath) throws Exception {
        Compilation compilation = compileSut(config);
        Path outJson = workDir.resolve("randoop-seeds.json");
        ProcessRunner.Result run = runGenerator(config, promptPath, outJson, compilation, List.of("--seeds-only"));
        if (!Files.exists(outJson)) {
            throw new IllegalStateException("Randoop seed generation produced no output file.\n" + run.output());
        }
        return Files.readString(outJson, StandardCharsets.UTF_8);
    }

    /** Generate the final LLM-seeded Randoop source fixtures for HYBRID + LLM MR. */
    public String generateLlmSeededSourceExamples(PromptConfig config, Path promptPath) throws Exception {
        Compilation compilation = compileSut(config);
        Path outJson = workDir.resolve("randoop-hybrid-sources.json");
        ProcessRunner.Result run = runGenerator(
                config, promptPath, outJson, compilation, List.of("--seeded-sources-only"));
        if (!Files.exists(outJson)) {
            throw new IllegalStateException(
                    "LLM-seeded Randoop source generation produced no output file.\n" + run.output());
        }
        return Files.readString(outJson, StandardCharsets.UTF_8);
    }

    private Compilation compileSut(PromptConfig config) throws Exception {
        Path classesDir = workDir.resolve("classes");
        Files.createDirectories(classesDir);
        List<String> sources = new ArrayList<>();
        if (config.sutClassFile() != null
                && !CompiledClassPath.contains(config.sutClasspath(), config.sutClassFile())) {
            sources.add(config.sutClassFile().toString());
        }
        for (Path support : config.sutSupportFiles()) {
            if (!CompiledClassPath.contains(config.sutClasspath(), support)) {
                sources.add(support.toString());
            }
        }
        if (config.developerMrFile() != null) {
            sources.add(config.developerMrFile().toString());
        }

        String compileClasspath = sutClasspath(config, projectClasses);
        if (!sources.isEmpty()) {
            List<String> javac = new ArrayList<>(List.of("javac", "-encoding", "UTF-8"));
            if (!compileClasspath.isEmpty()) {
                javac.add("-cp");
                javac.add(compileClasspath);
            }
            javac.add("-d");
            javac.add(classesDir.toString());
            javac.addAll(sources);
            ProcessRunner.Result compile = ProcessRunner.run(javac, repoRoot);
            if (compile.exitCode() != 0) {
                throw new IllegalStateException("Randoop SUT compilation failed:\n" + compile.output());
            }
        }
        RuntimeResourceCopier.copyFor(config, classesDir);
        InvocationWrapperGenerator.Generated wrapper = generateAndCompileWrapper(config, classesDir, compileClasspath);
        CallbackSynthesizer.Generated callbacks = generateAndCompileCallbacks(config, classesDir, compileClasspath);
        return new Compilation(classesDir, wrapper, callbacks);
    }

    private InvocationWrapperGenerator.Generated generateAndCompileWrapper(
            PromptConfig config, Path classesDir, String compileClasspath) throws Exception {
        List<URL> urls = new ArrayList<>();
        urls.add(classesDir.toUri().toURL());
        urls.add(projectClasses.toUri().toURL());
        for (Path path : config.sutClasspath()) {
            urls.add(path.toUri().toURL());
        }
        InvocationWrapperGenerator.Generated wrapper;
        try (URLClassLoader loader = new URLClassLoader(urls.toArray(URL[]::new), getClass().getClassLoader())) {
            wrapper = InvocationWrapperGenerator.generate(config, loader);
        }
        if (wrapper == null) {
            return null;
        }

        List<String> javac = new ArrayList<>(List.of(
                "javac", "-encoding", "UTF-8",
                "-cp", String.join(File.pathSeparator, classesDir.toString(), compileClasspath),
                "-d", classesDir.toString(), wrapper.sourceFile().toString()));
        ProcessRunner.Result compile = ProcessRunner.run(javac, repoRoot);
        if (compile.exitCode() != 0) {
            throw new IllegalStateException("Generated invocation-wrapper compilation failed:\n" + compile.output());
        }
        Path junitSupport = config.outputRoot().resolve("junit-support");
        Files.createDirectories(junitSupport);
        Files.copy(wrapper.sourceFile(), junitSupport.resolve(wrapper.sourceFile().getFileName()),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        return wrapper;
    }

    private CallbackSynthesizer.Generated generateAndCompileCallbacks(
            PromptConfig config, Path classesDir, String compileClasspath) throws Exception {
        List<URL> urls = new ArrayList<>();
        urls.add(classesDir.toUri().toURL());
        urls.add(projectClasses.toUri().toURL());
        for (Path path : config.sutClasspath()) {
            urls.add(path.toUri().toURL());
        }
        CallbackSynthesizer.Generated generated;
        try (URLClassLoader loader = new URLClassLoader(urls.toArray(URL[]::new), getClass().getClassLoader())) {
            generated = CallbackSynthesizer.generate(config, loader);
        }
        if (generated.isEmpty()) {
            deleteGeneratedCallbackSupport(config.outputRoot().resolve("junit-support"));
            return generated;
        }

        List<String> javac = new ArrayList<>(List.of(
                "javac", "-encoding", "UTF-8",
                "-cp", String.join(File.pathSeparator, classesDir.toString(), compileClasspath),
                "-d", classesDir.toString()));
        generated.sourceFiles().forEach(source -> javac.add(source.toString()));
        ProcessRunner.Result compile = ProcessRunner.run(javac, repoRoot);
        if (compile.exitCode() != 0) {
            throw new IllegalStateException("Generated callback-policy compilation failed:\n" + compile.output());
        }
        Path junitSupport = config.outputRoot().resolve("junit-support");
        Files.createDirectories(junitSupport);
        deleteGeneratedCallbackSupport(junitSupport);
        for (Path source : generated.sourceFiles()) {
            Files.copy(source, junitSupport.resolve(source.getFileName()),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }
        System.out.println("Generated " + generated.classNames().size()
                + " callback policies for " + generated.interfaceNames());
        return generated;
    }

    private static void deleteGeneratedCallbackSupport(Path supportDir) throws IOException {
        if (!Files.isDirectory(supportDir)) {
            return;
        }
        try (Stream<Path> files = Files.list(supportDir)) {
            for (Path path : files.filter(file -> file.getFileName().toString()
                    .matches("MtllmGenerated.*Callback.*Policy\\d+\\.java")).toList()) {
                Files.deleteIfExists(path);
            }
        }
    }

    private ProcessRunner.Result runGenerator(
            PromptConfig config, Path promptPath, Path outJson, Compilation compilation, List<String> extraArgs)
            throws Exception {
        Files.deleteIfExists(outJson);
        String classpath = String.join(File.pathSeparator,
                compilation.classesDir.toString(), projectClasses.toString(), randoopJar.toString(),
                runtimeDependencyClasspath(), sutClasspath(config));
        List<String> java = new ArrayList<>(List.of(
                "java", "-ea", "-cp", classpath, "mtllm.randoop.RandoopDataGenerator",
                promptPath.toString(), outJson.toString()));
        Path inferredDomain = config.outputRoot().resolve("input-domain/inferred-input-domain.yaml");
        if (Files.isRegularFile(inferredDomain)
                && InputDomainInferenceService.readArtifact(inferredDomain, config.count())
                        .equals(config.inputDomainRequirements())) {
            java.add("--input-domain-file=" + inferredDomain);
        }
        if (compilation.wrapper != null) {
            java.add("--invocation-class=" + compilation.wrapper.className());
        }
        java.addAll(extraArgs);
        return ProcessRunner.run(java, repoRoot, 240);
    }

    /** Build a classpath of project runtime dependency jars needed by the generator subprocess. */
    private static String runtimeDependencyClasspath() {
        Path m2 = Path.of(System.getProperty("user.home"), ".m2", "repository");
        List<String> jars = new ArrayList<>();
        addLatestJar(jars, m2.resolve("org/yaml/snakeyaml"), "snakeyaml");
        addLatestJar(jars, m2.resolve("io/github/classgraph/classgraph"), "classgraph");
        addLatestJar(jars, m2.resolve("org/instancio/instancio-core"), "instancio-core");
        addLatestJar(jars, m2.resolve("org/jspecify/jspecify"), "jspecify");
        addLatestJar(jars, m2.resolve("org/slf4j/slf4j-api"), "slf4j-api");
        addLatestJar(jars, m2.resolve("com/github/javaparser/javaparser-core"), "javaparser-core");
        return String.join(File.pathSeparator, jars);
    }

    /**
     * Compile the emitted passing/failing classes against the already-compiled SUT classes plus the
     * JUnit API jars from the local Maven repo. A missing JUnit classpath or compilation error is
     * fatal because {@code TestSuiteRequired: true} promises a compilable generated suite.
     */
    private ProcessRunner.Result compileSuiteGate(
            PromptConfig config, Path sutClassesDir, Path passingFile, Path failingFile)
            throws IOException, InterruptedException {
        String junitClasspath = locateJUnitClasspath();
        if (junitClasspath.isEmpty()) {
            return new ProcessRunner.Result(1,
                    "JUnit API jars not found under the local Maven repository; suite compile-gate skipped.", false);
        }
        Path gateClasses = workDir.resolve("junit-gate-classes");
        Files.createDirectories(gateClasses);
        List<String> javac = new ArrayList<>(List.of(
                "javac", "-encoding", "UTF-8",
                "-cp", String.join(File.pathSeparator,
                        junitClasspath, runtimeDependencyClasspath(), sutClassesDir.toString(), sutClasspath(config)),
                "-d", gateClasses.toString(),
                passingFile.toString(), failingFile.toString()));
        return ProcessRunner.run(javac, repoRoot);
    }

    private static String sutClasspath(PromptConfig config, Path... additionalEntries) {
        List<String> entries = new ArrayList<>();
        for (Path entry : additionalEntries) {
            entries.add(entry.toString());
        }
        for (Path entry : config.sutClasspath()) {
            entries.add(entry.toString());
        }
        return String.join(File.pathSeparator, entries);
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
                    .max((left, right) -> compareVersions(
                            left.getParent().getFileName().toString(),
                            right.getParent().getFileName().toString()))
                    .ifPresent(jar -> into.add(jar.toString()));
        } catch (IOException ignored) {
            // Treat an unreadable artifact dir as "jar not present".
        }
    }

    private static int compareVersions(String left, String right) {
        String[] leftParts = left.split("[.-]");
        String[] rightParts = right.split("[.-]");
        int length = Math.max(leftParts.length, rightParts.length);
        for (int i = 0; i < length; i++) {
            String leftPart = i < leftParts.length ? leftParts[i] : "0";
            String rightPart = i < rightParts.length ? rightParts[i] : "0";
            int compared = compareVersionPart(leftPart, rightPart);
            if (compared != 0) {
                return compared;
            }
        }
        return 0;
    }

    private static int compareVersionPart(String left, String right) {
        boolean leftNumeric = left.chars().allMatch(Character::isDigit);
        boolean rightNumeric = right.chars().allMatch(Character::isDigit);
        if (leftNumeric && rightNumeric) {
            return Integer.compare(Integer.parseInt(left), Integer.parseInt(right));
        }
        if (leftNumeric != rightNumeric) {
            return leftNumeric ? 1 : -1;
        }
        return left.compareTo(right);
    }

    private static final class Compilation {
        private final Path classesDir;
        private final InvocationWrapperGenerator.Generated wrapper;
        private final CallbackSynthesizer.Generated callbacks;

        private Compilation(Path classesDir, InvocationWrapperGenerator.Generated wrapper,
                            CallbackSynthesizer.Generated callbacks) {
            this.classesDir = classesDir;
            this.wrapper = wrapper;
            this.callbacks = callbacks;
        }
    }
}
