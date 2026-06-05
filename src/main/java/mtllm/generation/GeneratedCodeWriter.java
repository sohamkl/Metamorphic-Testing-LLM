package mtllm.generation;

import mtllm.util.CodeFence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Writes non-JUnit generated Java code to disk.
 *
 * <p>In simple terms, this saves Mode 1 and Mode 2 data-generator classes under
 * generated-code/ instead of generated-tests/.</p>
 */
public final class GeneratedCodeWriter {
    private GeneratedCodeWriter() {
    }

    public static Path write(Path outputDir, String className, String javaCode) throws IOException {
        Files.createDirectories(outputDir);
        Path outputFile = outputDir.resolve(className + ".java");
        Files.writeString(outputFile, CodeFence.strip(javaCode).trim() + System.lineSeparator(), StandardCharsets.UTF_8);
        return outputFile;
    }
}
