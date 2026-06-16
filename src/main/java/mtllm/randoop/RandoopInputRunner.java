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

/**
 * Drives the Randoop input-generation mode from inside the pipeline.
 *
 * <p>Runs {@link RandoopDataGenerator} in a <b>separate JVM</b> so the SUT classes are genuinely on
 * the classpath (Randoop reflects them by name; they are not part of the project build). Steps:
 * compile the SUT + support + developer-MR files into a classes dir, launch the generator subprocess
 * with that dir + the Randoop jar + the project classes on {@code -cp}, and read back the
 * executed-MT JSON it writes to a file. The returned JSON is fed to
 * {@code DataGeneratorRunner.writeSplitAndReport}, exactly like the LLM path's output.</p>
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

    /** Compile the SUT, run the generator subprocess, and return the executed-MT JSON array. */
    public String generate(PromptConfig config, Path promptPath) throws Exception {
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

        // 2. Run the generator in a subprocess; it writes the JSON to outJson.
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
        return Files.readString(outJson, StandardCharsets.UTF_8);
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
