package mtllm.config;

import java.util.List;

/** A named, measurable source-input scenario requested by the developer. */
public record ScenarioRequirement(
        String id,
        ScenarioCategory category,
        String description,
        List<String> preconditions,
        List<String> expectedSourceBehavior,
        int targetCases,
        boolean emptyOutputAllowed) {

    public ScenarioRequirement {
        id = id == null ? "" : id.trim();
        category = category == null ? ScenarioCategory.NORMAL : category;
        description = description == null ? "" : description.trim();
        preconditions = preconditions == null ? List.of() : List.copyOf(preconditions);
        expectedSourceBehavior = expectedSourceBehavior == null
                ? List.of()
                : List.copyOf(expectedSourceBehavior);
    }
}
