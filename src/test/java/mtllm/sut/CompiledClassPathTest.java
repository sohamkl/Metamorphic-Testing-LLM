package mtllm.sut;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompiledClassPathTest {
    @TempDir
    Path tempDir;

    @Test
    void findsCompiledTypeUsingSourcePackageAndName() throws Exception {
        Path source = tempDir.resolve("source/Example.java");
        Files.createDirectories(source.getParent());
        Files.writeString(source, "package sample.deep; public class Example {}\n");

        Path classes = tempDir.resolve("classes");
        Path classFile = classes.resolve("sample/deep/Example.class");
        Files.createDirectories(classFile.getParent());
        Files.write(classFile, new byte[]{0});

        assertTrue(CompiledClassPath.contains(List.of(classes), source));
        assertFalse(CompiledClassPath.contains(List.of(tempDir.resolve("other")), source));
    }
}
