package mtllm.sut;

import mtllm.config.PromptConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Loads the SUT class source and any support files needed by the prompt.
 *
 * <p>In simple terms, this class reads the Java files from disk and does a best-effort scan for
 * first-level dependencies when the user does not list them manually.</p>
 */
public final class SutContextLoader {
    private static final int MAX_SOURCE_CHARS = 30000;

    private SutContextLoader() {
    }

    public static SutContext load(PromptConfig config, Path repoRoot) {
        if (config.sutClassFile() == null) {
            return new SutContext(null, "", List.of());
        }
        Path classFile = config.sutClassFile();
        String classSource = readFileWithLimit(classFile, MAX_SOURCE_CHARS, "SUTClassFile");

        List<Path> supportPaths = new ArrayList<>(config.sutSupportFiles());
        if (supportPaths.isEmpty()) {
            supportPaths.addAll(detectFirstLevelDependencies(classFile, repoRoot));
        }

        List<SutContext.SourceFile> supportSources = new ArrayList<>();
        Set<Path> seen = new LinkedHashSet<>();
        for (Path supportPath : supportPaths) {
            Path normalized = supportPath.normalize();
            if (normalized.equals(classFile.normalize()) || !seen.add(normalized)) {
                continue;
            }
            supportSources.add(new SutContext.SourceFile(
                    normalized,
                    readFileWithLimit(normalized, MAX_SOURCE_CHARS / 2, "SUTSupportFiles")));
        }

        Path generatedSupportDir = config.outputRoot().resolve("generated-support");
        if (Files.isDirectory(generatedSupportDir)) {
            try (var generated = Files.list(generatedSupportDir)) {
                for (Path path : generated.filter(file -> file.toString().endsWith(".java")).sorted().toList()) {
                    Path normalized = path.normalize();
                    if (seen.add(normalized)) {
                        supportSources.add(new SutContext.SourceFile(
                                normalized,
                                readFileWithLimit(normalized, MAX_SOURCE_CHARS / 2, "generated support file")));
                    }
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Cannot read generated support files from "
                        + generatedSupportDir, e);
            }
        }

        return new SutContext(classFile, classSource, supportSources, SutApiInspector.inspect(config));
    }

    private static List<Path> detectFirstLevelDependencies(Path classFile, Path repoRoot) {
        List<Path> deps = new ArrayList<>();
        try {
            String source = Files.readString(classFile, StandardCharsets.UTF_8);
            Pattern importPattern = Pattern.compile("^import\\s+([\\w.]+);", Pattern.MULTILINE);
            Matcher matcher = importPattern.matcher(source);

            List<Path> javaFiles = new ArrayList<>();
            collectJavaFiles(repoRoot, classFile, javaFiles);

            while (matcher.find()) {
                String importName = matcher.group(1);
                if (isJdkImport(importName)) {
                    continue;
                }
                String simpleName = importName.substring(importName.lastIndexOf('.') + 1);
                if (simpleName.equals("*")) {
                    continue;
                }
                String fileName = simpleName + ".java";
                for (Path javaFile : javaFiles) {
                    if (javaFile.getFileName().toString().equals(fileName)) {
                        deps.add(javaFile);
                        break;
                    }
                }
            }
        } catch (IOException ignored) {
            return deps;
        }
        return deps;
    }

    private static boolean isJdkImport(String importName) {
        return importName.startsWith("java.")
                || importName.startsWith("javax.")
                || importName.startsWith("jdk.")
                || importName.startsWith("sun.")
                || importName.startsWith("com.sun.");
    }

    private static void collectJavaFiles(Path dir, Path excludeFile, List<Path> result) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    if (!entry.getFileName().toString().equals(".git")
                            && !entry.getFileName().toString().equals("generated")) {
                        collectJavaFiles(entry, excludeFile, result);
                    }
                } else if (entry.toString().endsWith(".java") && !entry.normalize().equals(excludeFile.normalize())) {
                    result.add(entry);
                }
            }
        }
    }

    private static String readFileWithLimit(Path path, int maxChars, String label) {
        try {
            if (!Files.isRegularFile(path)) {
                throw new IllegalArgumentException(label + " file not found: " + path);
            }
            String content = Files.readString(path, StandardCharsets.UTF_8);
            if (content.isBlank()) {
                throw new IllegalArgumentException(label + " file is empty: " + path);
            }
            if (content.length() > maxChars) {
                return content.substring(0, maxChars)
                        + "\n// ... truncated for prompt size (" + content.length() + " chars total)";
            }
            return content;
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read " + label + " file: " + path + " (" + e.getMessage() + ")");
        }
    }
}
