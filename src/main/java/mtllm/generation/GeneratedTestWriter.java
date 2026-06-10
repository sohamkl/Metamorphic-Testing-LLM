package mtllm.generation;

import mtllm.util.CodeFence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Writes the LLM-generated JUnit class to disk.
 *
 * <p>In simple terms, this class takes the Java code returned by the model and saves it as a
 * .java file under the generated JUnit test output folder.</p>
 */
public final class GeneratedTestWriter {
    private GeneratedTestWriter() {
    }

    public static Path write(Path outputDir, String className, String javaCode) throws IOException {
        Files.createDirectories(outputDir);
        Path outputFile = outputDir.resolve(className + ".java");
        Files.writeString(outputFile, CodeFence.strip(javaCode).trim() + System.lineSeparator(), StandardCharsets.UTF_8);
        return outputFile;
    }
}
