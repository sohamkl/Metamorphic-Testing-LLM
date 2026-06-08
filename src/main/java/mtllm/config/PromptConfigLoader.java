package mtllm.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads prompt.txt and turns key-value lines into a PromptConfig object.
 *
 * <p>In simple terms, this class understands the small config-file format used by the prototype.</p>
 */
public final class PromptConfigLoader {
    private PromptConfigLoader() {
    }

    public static PromptConfig load(Path promptPath, Path repoRoot) throws IOException {
        if (!Files.isRegularFile(promptPath)) {
            throw new IllegalArgumentException("Missing prompt file: " + promptPath);
        }

        Map<String, String> values = new LinkedHashMap<>();
        for (String raw : Files.readAllLines(promptPath, StandardCharsets.UTF_8)) {
            String line = raw.trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }
            int idx = line.indexOf(':');
            if (idx <= 0) {
                continue;
            }
            values.put(line.substring(0, idx).trim(), line.substring(idx + 1).trim());
        }

        Path sutClassFile = resolveOptionalPath(values.get("SUTClassFile"), repoRoot);
        List<Path> supportFiles = parseSupportFiles(values.get("SUTSupportFiles"), repoRoot);
        Path developerMrFile = resolveOptionalPath(values.get("DeveloperMrFile"), repoRoot);
        String developerMrSource = readOptionalSource(developerMrFile);

        GenerationMode mode = GenerationMode.fromConfig(values.get("Mode"), values.get("Level"));
        String developerFollowUpMethod = values.getOrDefault("DeveloperFollowUpMethod", "");
        String developerAssertMethod = values.getOrDefault("DeveloperAssertMethod", "");
        validateDeveloperMrConfig(mode, developerMrFile, developerFollowUpMethod, developerAssertMethod);

        return new PromptConfig(
                sutClassFile,
                values.getOrDefault("TargetFunction", ""),
                supportFiles,
                values.getOrDefault("SUT", ""),
                values.getOrDefault("MRInput", ""),
                values.getOrDefault("MROutput", ""),
                values.getOrDefault("MR", ""),
                parsePositiveInt(values.get("Count"), 5, "Count"),
                firstNonBlank(values.get("InputDomain"), values.get("Constraints"), ""),
                values.getOrDefault("GeneratedClassName", "GeneratedMetamorphicTest"),
                mode,
                developerMrFile,
                developerMrSource,
                developerFollowUpMethod,
                developerAssertMethod,
                parseNonNegativeInt(values.get("MaxRepairAttempts"), 1, "MaxRepairAttempts"));
    }

    private static void validateDeveloperMrConfig(
            GenerationMode mode,
            Path developerMrFile,
            String developerFollowUpMethod,
            String developerAssertMethod) {
        if (!mode.usesDeveloperMrHelpers()) {
            return;
        }
        if (developerMrFile == null) {
            throw new IllegalArgumentException("Mode " + mode.number() + " requires DeveloperMrFile.");
        }
        if (developerFollowUpMethod == null || developerFollowUpMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("Mode " + mode.number() + " requires DeveloperFollowUpMethod.");
        }
        if (developerAssertMethod == null || developerAssertMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("Mode " + mode.number() + " requires DeveloperAssertMethod.");
        }
    }

    private static List<Path> parseSupportFiles(String raw, Path repoRoot) {
        List<Path> paths = new ArrayList<>();
        if (raw == null || raw.trim().isEmpty()) {
            return paths;
        }
        String[] parts = raw.split(",");
        for (String part : parts) {
            Path path = resolveOptionalPath(part, repoRoot);
            if (path != null) {
                paths.add(path);
            }
        }
        return paths;
    }

    private static Path resolveOptionalPath(String raw, Path repoRoot) {
        if (raw == null || raw.trim().isEmpty()) {
            return null;
        }
        Path path = Path.of(raw.trim());
        return path.isAbsolute() ? path.normalize() : repoRoot.resolve(path).normalize();
    }

    private static String readOptionalSource(Path path) throws IOException {
        if (path == null) {
            return "";
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("Missing DeveloperMrFile: " + path);
        }
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    private static int parsePositiveInt(String raw, int fallback, String fieldName) {
        int parsed = parseNonNegativeInt(raw, fallback, fieldName);
        if (parsed <= 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than zero.");
        }
        return parsed;
    }

    private static int parseNonNegativeInt(String raw, int fallback, String fieldName) {
        if (raw == null || raw.trim().isEmpty()) {
            return fallback;
        }
        try {
            int parsed = Integer.parseInt(raw.trim());
            if (parsed < 0) {
                throw new IllegalArgumentException(fieldName + " must not be negative.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a number: " + raw);
        }
    }

    private static String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
        }
        return "";
    }
}
