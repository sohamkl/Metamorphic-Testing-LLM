package mtllm.runner;

import mtllm.config.PromptConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashSet;
import java.util.Set;

/** Copies conventional Maven resources required by a compiled SUT into its runtime classpath. */
public final class RuntimeResourceCopier {
    private RuntimeResourceCopier() {
    }

    public static void copyFor(PromptConfig config, Path classesDir) throws IOException {
        Files.createDirectories(classesDir);
        Set<Path> resourceDirs = new LinkedHashSet<>();
        addResourceDir(resourceDirs, config.sutClassFile());
        for (Path supportFile : config.sutSupportFiles()) {
            addResourceDir(resourceDirs, supportFile);
        }
        addResourceDir(resourceDirs, config.developerMrFile());

        for (Path resourceDir : resourceDirs) {
            copyDirectory(resourceDir, classesDir);
        }
    }

    private static void addResourceDir(Set<Path> resourceDirs, Path sourceFile) {
        if (sourceFile == null) {
            return;
        }
        Path current = sourceFile.toAbsolutePath().normalize().getParent();
        while (current != null && current.getFileName() != null) {
            if (current.endsWith(Path.of("src", "main", "java"))) {
                Path resources = current.getParent().resolve("resources");
                if (Files.isDirectory(resources)) {
                    resourceDirs.add(resources);
                }
                return;
            }
            current = current.getParent();
        }
    }

    private static void copyDirectory(Path sourceDir, Path targetDir) throws IOException {
        try (var paths = Files.walk(sourceDir)) {
            for (Path source : paths.filter(Files::isRegularFile).toList()) {
                Path target = targetDir.resolve(sourceDir.relativize(source));
                Files.createDirectories(target.getParent());
                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
