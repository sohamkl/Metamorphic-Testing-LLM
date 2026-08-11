package mtllm.sut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarFile;

/** Checks whether a Java source type is already supplied by a compiled classpath entry. */
public final class CompiledClassPath {
    private CompiledClassPath() {
    }

    public static boolean contains(List<Path> classpath, Path sourceFile) {
        if (sourceFile == null) {
            return false;
        }
        String classEntry = JavaSourceNames.qualifiedName(sourceFile).replace('.', '/') + ".class";
        for (Path entry : classpath) {
            if (Files.isDirectory(entry) && Files.isRegularFile(entry.resolve(classEntry))) {
                return true;
            }
            if (Files.isRegularFile(entry) && entry.getFileName().toString().endsWith(".jar")) {
                try (JarFile jar = new JarFile(entry.toFile())) {
                    if (jar.getJarEntry(classEntry) != null) {
                        return true;
                    }
                } catch (IOException ignored) {
                    // An unreadable classpath entry cannot provide the compiled type.
                }
            }
        }
        return false;
    }
}
