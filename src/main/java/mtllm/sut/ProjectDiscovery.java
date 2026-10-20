package mtllm.sut;

import mtllm.config.PromptConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** Resolves conventional Maven outputs and dependency jars when no SUT classpath is configured. */
public final class ProjectDiscovery {
    private ProjectDiscovery() {
    }

    public static PromptConfig enrichClasspath(PromptConfig config, String mavenCommand)
            throws IOException, InterruptedException {
        if (!config.automaticDiscovery() || !config.sutClasspath().isEmpty()) {
            return config;
        }

        Path projectRoot = findMavenProject(config.sutClassFile(), config.projectRoot());
        if (projectRoot == null) {
            return config;
        }

        Set<Path> discovered = new LinkedHashSet<>();
        addIfDirectory(discovered, projectRoot.resolve("target/classes"));
        addIfDirectory(discovered, projectRoot.resolve("target/test-classes"));

        Path discoveryDir = config.outputRoot().resolve("discovery");
        Files.createDirectories(discoveryDir);
        String profileKey = config.mavenProfiles().isEmpty()
                ? "default"
                : String.join("-", config.mavenProfiles()).replaceAll("[^A-Za-z0-9_.-]", "_");
        Path outputFile = discoveryDir.resolve("maven-classpath-" + profileKey + ".txt");

        List<String> command = new ArrayList<>();
        command.add(mavenCommand == null || mavenCommand.isBlank() ? "mvn" : mavenCommand);
        command.add("-q");
        command.add("-f");
        command.add(projectRoot.resolve("pom.xml").toString());
        if (!config.mavenProfiles().isEmpty()) {
            command.add("-P" + String.join(",", config.mavenProfiles()));
        }
        command.add("dependency:build-classpath");
        command.add("-DincludeScope=test");
        command.add("-Dmdep.outputAbsoluteArtifactFilename=true");
        command.add("-Dmdep.outputFile=" + outputFile);

        Process process = new ProcessBuilder(command)
                .directory(projectRoot.toFile())
                .redirectErrorStream(true)
                .start();
        String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IllegalStateException("Automatic Maven classpath discovery failed for " + projectRoot
                    + ". Configure SUTClasspath explicitly or fix the Maven build.\n" + output);
        }

        if (Files.isRegularFile(outputFile)) {
            String rawClasspath = Files.readString(outputFile, StandardCharsets.UTF_8).trim();
            if (!rawClasspath.isEmpty()) {
                for (String entry : rawClasspath.split(java.util.regex.Pattern.quote(File.pathSeparator))) {
                    Path path = Path.of(entry).toAbsolutePath().normalize();
                    if (Files.exists(path)) {
                        discovered.add(path);
                    }
                }
            }
        }
        return config.withSutClasspath(List.copyOf(discovered));
    }

    static Path findMavenProject(Path sutClassFile, Path configuredProjectRoot) {
        Path start = sutClassFile == null ? configuredProjectRoot : sutClassFile.getParent();
        Path boundary = configuredProjectRoot == null ? null : configuredProjectRoot.toAbsolutePath().normalize();
        for (Path current = start; current != null; current = current.getParent()) {
            if (Files.isRegularFile(current.resolve("pom.xml"))) {
                return current;
            }
            if (boundary != null && current.toAbsolutePath().normalize().equals(boundary)) {
                break;
            }
        }
        return boundary != null && Files.isRegularFile(boundary.resolve("pom.xml")) ? boundary : null;
    }

    private static void addIfDirectory(Set<Path> paths, Path candidate) {
        if (Files.isDirectory(candidate)) {
            paths.add(candidate.toAbsolutePath().normalize());
        }
    }
}
