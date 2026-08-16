package mtllm.runner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** Runs framework subprocesses with consistent output capture, PATH setup, and timeouts. */
public final class ProcessRunner {
    private ProcessRunner() {
    }

    public static Result run(List<String> command, Path workDir) throws IOException, InterruptedException {
        return run(command, workDir, 0);
    }

    public static Result run(List<String> command, Path workDir, long timeoutSeconds)
            throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command)
                .directory(workDir.toFile())
                .redirectErrorStream(true);
        String existingPath = builder.environment().getOrDefault("PATH", "");
        builder.environment().put("PATH", "/bin:/usr/bin:/opt/homebrew/bin:/usr/local/bin:" + existingPath);

        Process process = builder.start();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        Thread outputReader = new Thread(() -> {
            try {
                process.getInputStream().transferTo(buffer);
            } catch (IOException ignored) {
                // Destroying a timed-out process can close its output stream.
            }
        }, "mtllm-process-output");
        outputReader.start();

        boolean completed;
        try {
            completed = timeoutSeconds <= 0
                    ? waitWithoutTimeout(process)
                    : process.waitFor(timeoutSeconds, TimeUnit.SECONDS);
            if (!completed) {
                process.destroyForcibly();
                process.waitFor();
            }
            outputReader.join();
        } catch (InterruptedException interrupted) {
            process.destroyForcibly();
            outputReader.interrupt();
            Thread.currentThread().interrupt();
            throw interrupted;
        }

        if (!completed) {
            buffer.write(("\nProcess timed out after " + timeoutSeconds + " seconds: "
                    + String.join(" ", command) + "\n").getBytes(StandardCharsets.UTF_8));
        }
        return new Result(completed ? process.exitValue() : 124,
                buffer.toString(StandardCharsets.UTF_8), !completed);
    }

    private static boolean waitWithoutTimeout(Process process) throws InterruptedException {
        process.waitFor();
        return true;
    }

    public record Result(int exitCode, String output, boolean timedOut) {
        public Result {
            output = output == null ? "" : output;
        }
    }
}
