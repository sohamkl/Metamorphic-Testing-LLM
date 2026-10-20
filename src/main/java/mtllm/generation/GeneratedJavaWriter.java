package mtllm.generation;

import mtllm.util.CodeFence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/** Writes an LLM-generated Java source file to its selected output directory. */
public final class GeneratedJavaWriter {
    private GeneratedJavaWriter() {
    }

    public static Path write(Path outputDir, String className, String javaCode) throws IOException {
        Files.createDirectories(outputDir);
        Path outputFile = outputDir.resolve(className + ".java");
        Files.writeString(
                outputFile,
                CodeFence.strip(javaCode).trim() + System.lineSeparator(),
                StandardCharsets.UTF_8);
        return outputFile;
    }
}
