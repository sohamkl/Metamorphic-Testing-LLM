package mtllm.domain;

import mtllm.config.InputDomainRequirements;
import mtllm.config.ScenarioRequirement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Converts scenario requirements into deterministic source-case obligations for the LLM. */
public final class SourceScenarioPlanner {
    private SourceScenarioPlanner() {
    }

    public static List<String> plan(InputDomainRequirements requirements, int count) {
        if (requirements == null || requirements.scenarios().isEmpty()) {
            return List.of();
        }
        List<String> slots = new ArrayList<>();
        int slotIndex = 0;
        for (ScenarioRequirement scenario : requirements.scenarios()) {
            for (int variation = 1; variation <= scenario.targetCases() && slots.size() < count; variation++) {
                StringBuilder slot = new StringBuilder();
                slot.append("slot ").append(slotIndex + 1)
                        .append(": scenario=").append(scenario.id())
                        .append(", variation=").append(variation);
                for (Map.Entry<String, List<String>> dimension : requirements.diversityDimensions().entrySet()) {
                    if (!dimension.getValue().isEmpty()) {
                        String value = dimension.getValue().get(slotIndex % dimension.getValue().size());
                        slot.append(", ").append(dimension.getKey()).append("=").append(value);
                    }
                }
                slots.add(slot.toString());
                slotIndex++;
            }
        }
        return List.copyOf(slots);
    }
}
