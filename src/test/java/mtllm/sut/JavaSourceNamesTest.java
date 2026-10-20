package mtllm.sut;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaSourceNamesTest {
    @TempDir
    Path tempDir;

    @Test
    void derivesQualifiedAndDefaultPackageNames() throws Exception {
        Path packaged = tempDir.resolve("Packaged.java");
        Files.writeString(packaged, "package sample.api; public class Packaged {}\n");
        Path unqualified = tempDir.resolve("Plain.java");
        Files.writeString(unqualified, "public class Plain {}\n");

        assertEquals("sample.api.Packaged", JavaSourceNames.qualifiedName(packaged));
        assertEquals("Plain", JavaSourceNames.qualifiedName(unqualified));
        assertEquals("", JavaSourceNames.qualifiedName(null));
    }
}
