package mtllm.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Typed input-domain constraints and scenario requirements parsed from prompt.yaml. */
public record InputDomainRequirements(
        String summary,
        List<String> globalConstraints,
        Map<String, List<String>> diversityDimensions,
        List<ScenarioRequirement> scenarios,
        String legacyDescription) {

    public InputDomainRequirements {
        summary = valueOrEmpty(summary);
        globalConstraints = globalConstraints == null ? List.of() : List.copyOf(globalConstraints);
        diversityDimensions = immutableDimensions(diversityDimensions);
        scenarios = scenarios == null ? List.of() : List.copyOf(scenarios);
        legacyDescription = valueOrEmpty(legacyDescription);
    }

    public static InputDomainRequirements empty() {
        return new InputDomainRequirements("", List.of(), Map.of(), List.of(), "");
    }

    public static InputDomainRequirements legacy(String description) {
        return new InputDomainRequirements("", List.of(), Map.of(), List.of(), description);
    }

    public boolean isEmpty() {
        return summary.isBlank()
                && globalConstraints.isEmpty()
                && diversityDimensions.isEmpty()
                && scenarios.isEmpty()
                && legacyDescription.isBlank();
    }

    public boolean isStructured() {
        return !summary.isBlank()
                || !globalConstraints.isEmpty()
                || !diversityDimensions.isEmpty()
                || !scenarios.isEmpty();
    }

    /** Stable plain-text projection for prompts, reports, and legacy consumers. */
    public String asText() {
        if (!isStructured()) {
            return legacyDescription;
        }

        StringBuilder text = new StringBuilder();
        appendLine(text, "Summary", summary);
        appendList(text, "Global constraints", globalConstraints);
        if (!diversityDimensions.isEmpty()) {
            text.append("Diversity dimensions:\n");
            diversityDimensions.forEach((name, values) -> text.append("- ")
                    .append(name)
                    .append(": ")
                    .append(String.join(", ", values))
                    .append("\n"));
        }
        if (!scenarios.isEmpty()) {
            text.append("Required scenarios:\n");
            for (ScenarioRequirement scenario : scenarios) {
                text.append("Scenario ").append(scenario.id())
                        .append(" [").append(scenario.category()).append("]\n");
                text.append("Description: ").append(scenario.description()).append("\n");
                text.append("Target cases: ").append(scenario.targetCases()).append("\n");
                text.append("Empty source output allowed: ")
                        .append(scenario.emptyOutputAllowed() ? "yes" : "no")
                        .append("\n");
                appendList(text, "Preconditions", scenario.preconditions());
                appendList(text, "Expected source behavior", scenario.expectedSourceBehavior());
            }
        }
        return text.toString().trim();
    }

    private static Map<String, List<String>> immutableDimensions(Map<String, List<String>> dimensions) {
        if (dimensions == null || dimensions.isEmpty()) {
            return Map.of();
        }
        Map<String, List<String>> copy = new LinkedHashMap<>();
        dimensions.forEach((key, values) -> copy.put(key, List.copyOf(values)));
        return Collections.unmodifiableMap(copy);
    }

    private static void appendLine(StringBuilder text, String heading, String value) {
        if (!value.isBlank()) {
            text.append(heading).append(": ").append(value).append("\n");
        }
    }

    private static void appendList(StringBuilder text, String heading, List<String> values) {
        if (values.isEmpty()) {
            return;
        }
        text.append(heading).append(":\n");
        values.forEach(value -> text.append("- ").append(value).append("\n"));
    }

    private static String valueOrEmpty(String value) {
        return value == null ? "" : value.trim();
    }
}
