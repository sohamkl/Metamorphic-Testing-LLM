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
        Path outputRoot = resolveOutputRoot(values.get("OutputRoot"), repoRoot, sutClassFile);
        String developerMrSource = readOptionalSource(developerMrFile);

        boolean jsonRequired = parseBoolean(values.get("JsonRequired"), false, "JsonRequired");
        boolean testSuiteRequired = parseBoolean(values.get("TestSuiteRequired"), true, "TestSuiteRequired");
        MRProvider mrProvider = MRProvider.fromConfig(values.get("MRProvider"));
        GenerationMode mode = deriveMode(jsonRequired, testSuiteRequired, mrProvider);
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
                jsonRequired,
                testSuiteRequired,
                mrProvider,
                developerMrFile,
                developerMrSource,
                developerFollowUpMethod,
                developerAssertMethod,
                outputRoot,
                parseNonNegativeInt(values.get("MaxRepairAttempts"), 1, "MaxRepairAttempts"));
    }

    private static Path resolveOutputRoot(String raw, Path repoRoot, Path sutClassFile) {
        Path configured = resolveOptionalPath(raw, repoRoot);
        if (configured != null) {
            return configured;
        }
        Path exampleRoot = inferExampleRoot(sutClassFile, repoRoot);
        if (exampleRoot != null) {
            return exampleRoot.resolve("generated").normalize();
        }
        return repoRoot.resolve("generated").normalize();
    }

    private static Path inferExampleRoot(Path sutClassFile, Path repoRoot) {
        if (sutClassFile == null) {
            return null;
        }
        Path relative;
        try {
            relative = repoRoot.relativize(sutClassFile);
        } catch (IllegalArgumentException e) {
            return null;
        }
        if (relative.getNameCount() >= 3 && relative.getName(0).toString().equals("examples")) {
            return repoRoot.resolve(relative.getName(0)).resolve(relative.getName(1)).normalize();
        }
        return null;
    }

    private static GenerationMode deriveMode(boolean jsonRequired, boolean testSuiteRequired, MRProvider mrProvider) {
        if (!jsonRequired && !testSuiteRequired) {
            throw new IllegalArgumentException("At least one output is required: set JsonRequired or TestSuiteRequired to true.");
        }
        if (jsonRequired && testSuiteRequired) {
            return mrProvider == MRProvider.DEV ? GenerationMode.DEVELOPER_MR_BOTH : GenerationMode.LLM_BOTH;
        }
        if (jsonRequired && mrProvider == MRProvider.DEV) {
            return GenerationMode.DEVELOPER_MR_DATA;
        }
        if (testSuiteRequired && mrProvider == MRProvider.DEV) {
            return GenerationMode.DEVELOPER_MR_JUNIT;
        }
        if (jsonRequired) {
            return GenerationMode.INPUTS_AND_FOLLOWUP;
        }
        return GenerationMode.FULL_JUNIT;
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
            throw new IllegalArgumentException("MRProvider: DEV requires DeveloperMrFile.");
        }
        if (developerFollowUpMethod == null || developerFollowUpMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("MRProvider: DEV requires DeveloperFollowUpMethod.");
        }
        if (developerAssertMethod == null || developerAssertMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("MRProvider: DEV requires DeveloperAssertMethod.");
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

    private static boolean parseBoolean(String raw, boolean fallback, String fieldName) {
        if (raw == null || raw.trim().isEmpty()) {
            return fallback;
        }
        String value = raw.trim();
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("y")) {
            return true;
        }
        if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no") || value.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException(fieldName + " must be true or false: " + raw);
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
