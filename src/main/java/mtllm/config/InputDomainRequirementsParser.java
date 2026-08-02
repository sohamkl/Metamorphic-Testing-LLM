package mtllm.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/** Parses and validates scalar or structured InputDomain YAML values. */
final class InputDomainRequirementsParser {
    private static final Set<String> DOMAIN_FIELDS = Set.of(
            "summary", "globalConstraints", "diversity", "scenarios");
    private static final Set<String> SCENARIO_FIELDS = Set.of(
            "id", "category", "description", "preconditions", "expectedSourceBehavior",
            "targetCases", "emptyOutputAllowed");

    private InputDomainRequirementsParser() {
    }

    static InputDomainRequirements parse(Object raw, String legacyFallback, int count) {
        if (raw == null) {
            return legacyFallback == null || legacyFallback.isBlank()
                    ? InputDomainRequirements.empty()
                    : InputDomainRequirements.legacy(legacyFallback);
        }
        if (raw instanceof String text) {
            return InputDomainRequirements.legacy(text);
        }
        if (!(raw instanceof Map<?, ?> map)) {
            throw new IllegalArgumentException("InputDomain must be text or a YAML mapping.");
        }

        rejectUnknownFields(map, DOMAIN_FIELDS, "InputDomain");
        String summary = optionalString(map.get("summary"), "InputDomain.summary");
        List<String> constraints = stringList(
                map.get("globalConstraints"), "InputDomain.globalConstraints", false);
        Map<String, List<String>> diversity = parseDiversity(map.get("diversity"));
        List<ScenarioRequirement> scenarios = parseScenarios(map.get("scenarios"), count);

        InputDomainRequirements requirements = new InputDomainRequirements(
                summary, constraints, diversity, scenarios, "");
        if (requirements.isEmpty()) {
            throw new IllegalArgumentException("Structured InputDomain must contain at least one field.");
        }
        return requirements;
    }

    private static Map<String, List<String>> parseDiversity(Object raw) {
        if (raw == null) {
            return Map.of();
        }
        if (!(raw instanceof Map<?, ?> map)) {
            throw new IllegalArgumentException("InputDomain.diversity must be a YAML mapping.");
        }
        Map<String, List<String>> dimensions = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String name = String.valueOf(entry.getKey()).trim();
            if (name.isEmpty()) {
                throw new IllegalArgumentException("InputDomain.diversity contains an empty dimension name.");
            }
            dimensions.put(name, stringList(
                    entry.getValue(), "InputDomain.diversity." + name, true));
        }
        return dimensions;
    }

    private static List<ScenarioRequirement> parseScenarios(Object raw, int count) {
        if (raw == null) {
            return List.of();
        }
        if (!(raw instanceof List<?> list)) {
            throw new IllegalArgumentException("InputDomain.scenarios must be a YAML list.");
        }

        List<ScenarioRequirement> scenarios = new ArrayList<>();
        Set<String> ids = new HashSet<>();
        int totalTargets = 0;
        for (int index = 0; index < list.size(); index++) {
            String path = "InputDomain.scenarios[" + index + "]";
            Object item = list.get(index);
            if (!(item instanceof Map<?, ?> map)) {
                throw new IllegalArgumentException(path + " must be a YAML mapping.");
            }
            rejectUnknownFields(map, SCENARIO_FIELDS, path);

            String id = requiredString(map.get("id"), path + ".id");
            if (!id.matches("[A-Za-z][A-Za-z0-9_-]*")) {
                throw new IllegalArgumentException(
                        path + ".id must start with a letter and contain only letters, numbers, '_' or '-': " + id);
            }
            if (!ids.add(id.toLowerCase(Locale.ROOT))) {
                throw new IllegalArgumentException("Duplicate scenario id: " + id);
            }

            String description = requiredString(map.get("description"), path + ".description");
            ScenarioCategory category = ScenarioCategory.fromConfig(
                    optionalString(map.get("category"), path + ".category"), path + ".category");
            List<String> preconditions = stringList(map.get("preconditions"), path + ".preconditions", false);
            List<String> expectedBehavior = stringList(
                    map.get("expectedSourceBehavior"), path + ".expectedSourceBehavior", false);
            int targetCases = positiveInt(map.get("targetCases"), 1, path + ".targetCases");
            boolean emptyOutputAllowed = booleanValue(
                    map.get("emptyOutputAllowed"), false, path + ".emptyOutputAllowed");

            totalTargets += targetCases;
            scenarios.add(new ScenarioRequirement(
                    id, category, description, preconditions, expectedBehavior,
                    targetCases, emptyOutputAllowed));
        }
        if (totalTargets > count) {
            throw new IllegalArgumentException(
                    "InputDomain scenario targetCases total " + totalTargets + " exceeds Count " + count + ".");
        }
        return List.copyOf(scenarios);
    }

    private static List<String> stringList(Object raw, String path, boolean required) {
        if (raw == null) {
            if (required) {
                throw new IllegalArgumentException(path + " must contain at least one value.");
            }
            return List.of();
        }
        if (!(raw instanceof List<?> list)) {
            throw new IllegalArgumentException(path + " must be a YAML list.");
        }
        List<String> values = list.stream()
                .map(String::valueOf)
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .toList();
        if (values.isEmpty() && required) {
            throw new IllegalArgumentException(path + " must contain at least one value.");
        }
        return values;
    }

    private static String requiredString(Object raw, String path) {
        String value = optionalString(raw, path);
        if (value.isEmpty()) {
            throw new IllegalArgumentException(path + " is required.");
        }
        return value;
    }

    private static String optionalString(Object raw, String path) {
        if (raw == null) {
            return "";
        }
        if (raw instanceof Map<?, ?> || raw instanceof List<?>) {
            throw new IllegalArgumentException(path + " must be text.");
        }
        return String.valueOf(raw).trim();
    }

    private static int positiveInt(Object raw, int fallback, String path) {
        if (raw == null) {
            return fallback;
        }
        try {
            int value = raw instanceof Number number
                    ? number.intValue()
                    : Integer.parseInt(String.valueOf(raw).trim());
            if (value <= 0) {
                throw new IllegalArgumentException(path + " must be greater than zero.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(path + " must be a number: " + raw);
        }
    }

    private static boolean booleanValue(Object raw, boolean fallback, String path) {
        if (raw == null) {
            return fallback;
        }
        if (raw instanceof Boolean value) {
            return value;
        }
        String value = String.valueOf(raw).trim();
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes")) {
            return true;
        }
        if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no")) {
            return false;
        }
        throw new IllegalArgumentException(path + " must be true or false: " + raw);
    }

    private static void rejectUnknownFields(Map<?, ?> map, Set<String> allowed, String path) {
        for (Object key : map.keySet()) {
            String field = String.valueOf(key);
            if (!allowed.contains(field)) {
                throw new IllegalArgumentException(path + " contains unknown field: " + field);
            }
        }
    }
}
