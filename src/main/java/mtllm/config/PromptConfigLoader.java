package mtllm.config;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reads prompt.yaml and turns top-level YAML entries into a PromptConfig object.
 */
public final class PromptConfigLoader {
    private PromptConfigLoader() {
    }

    public static PromptConfig load(Path promptPath, Path repoRoot) throws IOException {
        if (!Files.isRegularFile(promptPath)) {
            throw new IllegalArgumentException("Missing prompt file: " + promptPath);
        }

        Map<String, Object> values = parseYaml(promptPath);

        Path sutClassFile = resolveRequiredSutClassFile(stringValue(values, "SUTClassFile"), repoRoot);
        List<Path> supportFiles = parseSupportFiles(values.get("SUTSupportFiles"), repoRoot);
        List<Path> sutClasspath = parseClasspath(values.get("SUTClasspath"), repoRoot);
        Path developerMrFile = resolveOptionalPath(stringValue(values, "DeveloperMrFile"), repoRoot);
        Path outputRoot = resolveOutputRoot(stringValue(values, "OutputRoot"), repoRoot, sutClassFile);
        String developerMrSource = readOptionalSource(developerMrFile);

        boolean jsonRequired = parseBoolean(stringValue(values, "JsonRequired"), false, "JsonRequired");
        boolean testSuiteRequired = parseBoolean(stringValue(values, "TestSuiteRequired"), true, "TestSuiteRequired");
        MRProvider mrProvider = MRProvider.fromConfig(stringValue(values, "MRProvider"));
        GenerationMode mode = deriveMode(jsonRequired, testSuiteRequired, mrProvider);
        String developerFollowUpMethod = stringValue(values, "DeveloperFollowUpMethod");
        String developerAssertMethod = stringValue(values, "DeveloperAssertMethod");
        validateDeveloperMrConfig(mode, developerMrFile, developerFollowUpMethod, developerAssertMethod);

        return new PromptConfig(
                sutClassFile,
                stringValue(values, "TargetFunction"),
                supportFiles,
                sutClasspath,
                firstNonBlank(stringValue(values, "SUTDescription"), stringValue(values, "SUT")),
                stringValue(values, "MRInput"),
                stringValue(values, "MROutput"),
                stringValue(values, "MR"),
                parsePositiveInt(stringValue(values, "Count"), 5, "Count"),
                firstNonBlank(stringValue(values, "InputDomain"), stringValue(values, "Constraints")),
                firstNonBlank(stringValue(values, "GeneratedClassName"), "GeneratedMetamorphicTest"),
                mode,
                jsonRequired,
                testSuiteRequired,
                mrProvider,
                developerMrFile,
                developerMrSource,
                developerFollowUpMethod,
                developerAssertMethod,
                outputRoot,
                parseNonNegativeInt(stringValue(values, "MaxRepairAttempts"), 1, "MaxRepairAttempts"),
                InputGenerator.fromConfig(stringValue(values, "InputGenerator")),
                parseStringList(values.get("RandoopTargetClasses")),
                "");
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> parseYaml(Path promptPath) throws IOException {
        Yaml yaml = new Yaml();
        try (Reader reader = Files.newBufferedReader(promptPath, StandardCharsets.UTF_8)) {
            Object loaded = yaml.load(reader);
            if (loaded == null) {
                return Map.of();
            }
            if (!(loaded instanceof Map<?, ?> map)) {
                throw new IllegalArgumentException("Prompt YAML must contain top-level key-value fields: " + promptPath);
            }
            return (Map<String, Object>) map;
        }
    }

    private static String stringValue(Map<String, Object> values, String key) {
        Object value = values.get(key);
        if (value == null) {
            return "";
        }
        if (value instanceof List<?> list) {
            return list.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
        }
        return String.valueOf(value).trim();
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

    private static List<Path> parseSupportFiles(Object raw, Path repoRoot) {
        if (raw == null) {
            return List.of();
        }
        if (raw instanceof List<?> list) {
            return list.stream()
                    .map(String::valueOf)
                    .map(part -> resolveOptionalPath(part, repoRoot))
                    .filter(path -> path != null)
                    .toList();
        }
        String value = String.valueOf(raw).trim();
        if (value.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(value.split(","))
                .map(part -> resolveOptionalPath(part, repoRoot))
                .filter(path -> path != null)
                .toList();
    }

    private static List<Path> parseClasspath(Object raw, Path repoRoot) {
        List<Path> entries = parseSupportFiles(raw, repoRoot);
        for (Path entry : entries) {
            if (!Files.exists(entry)) {
                throw new IllegalArgumentException("Missing SUTClasspath entry: " + entry);
            }
        }
        return entries;
    }

    private static List<String> parseStringList(Object raw) {
        if (raw == null) {
            return List.of();
        }
        if (raw instanceof List<?> list) {
            return list.stream()
                    .map(String::valueOf)
                    .map(String::trim)
                    .filter(value -> !value.isEmpty())
                    .toList();
        }
        String value = String.valueOf(raw).trim();
        if (value.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(value.split(","))
                .map(String::trim)
                .filter(part -> !part.isEmpty())
                .toList();
    }

    private static Path resolveOptionalPath(String raw, Path repoRoot) {
        if (raw == null || raw.trim().isEmpty()) {
            return null;
        }
        Path path = Path.of(raw.trim());
        return path.isAbsolute() ? path.normalize() : repoRoot.resolve(path).normalize();
    }

    private static Path resolveRequiredSutClassFile(String raw, Path repoRoot) {
        Path path = resolveOptionalPath(raw, repoRoot);
        if (path == null) {
            throw new IllegalArgumentException("SUTClassFile is required.");
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("Missing SUTClassFile: " + path);
        }
        return path;
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
