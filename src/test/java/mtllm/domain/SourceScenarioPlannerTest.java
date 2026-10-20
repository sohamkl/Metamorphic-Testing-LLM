package mtllm.domain;

import mtllm.config.InputDomainRequirements;
import mtllm.config.ScenarioCategory;
import mtllm.config.ScenarioRequirement;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SourceScenarioPlannerTest {

    @Test
    void allocatesTargetCasesAndRotatesDiversityValues() {
        InputDomainRequirements requirements = new InputDomainRequirements(
                "summary",
                List.of("valid"),
                Map.of("size", List.of("small", "large")),
                List.of(
                        scenario("NORMAL_PATH", 2),
                        scenario("BOUNDARY_PATH", 2)),
                "");

        assertEquals(List.of(
                        "slot 1: scenario=NORMAL_PATH, variation=1, size=small",
                        "slot 2: scenario=NORMAL_PATH, variation=2, size=large",
                        "slot 3: scenario=BOUNDARY_PATH, variation=1, size=small"),
                SourceScenarioPlanner.plan(requirements, 3));
    }

    private static ScenarioRequirement scenario(String id, int targetCases) {
        return new ScenarioRequirement(
                id,
                ScenarioCategory.NORMAL,
                "description",
                List.of(),
                List.of(),
                targetCases,
                false);
    }
}
