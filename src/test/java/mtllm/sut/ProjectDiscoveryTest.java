package mtllm.sut;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectDiscoveryTest {
    @TempDir
    Path root;

    @Test
    void findsNearestMavenModuleForSut() throws Exception {
        Path module = root.resolve("module");
        Path source = module.resolve("src/main/java/example/Sut.java");
        Files.createDirectories(source.getParent());
        Files.writeString(module.resolve("pom.xml"), "<project/>\n");
        Files.writeString(source, "package example; public class Sut {}\n");

        assertEquals(module, ProjectDiscovery.findMavenProject(source, root));
    }
}
