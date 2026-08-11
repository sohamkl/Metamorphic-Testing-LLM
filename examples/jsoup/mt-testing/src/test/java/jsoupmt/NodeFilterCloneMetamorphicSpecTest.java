package jsoupmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterSut.FilterAction;
import jsoupmt.NodeTraversorFilterSut.FilterCase;
import jsoupmt.NodeTraversorFilterSut.FilterTrace;
import mtllm.config.InputGenerator;
import mtllm.config.MRProvider;
import mtllm.config.PromptConfig;
import mtllm.config.PromptConfigLoader;

class NodeFilterCloneMetamorphicSpecTest {
    @Test
    void clonePreservesFilteringForEveryAction() {
        for (FilterAction action : FilterAction.values()) {
            FilterCase source = NodeFilterCaseFactory.repeatedTargets(action);
            FilterTrace sourceOutput = NodeTraversorFilterSut.run(source);
            FilterCase followUp = NodeFilterCloneMetamorphicSpec.generateFollowUp(source);
            FilterTrace followUpOutput = NodeTraversorFilterSut.run(followUp);

            NodeFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
            assertTrue(sourceOutput.appliedActions() > 0);
        }
    }

    @Test
    void followUpUsesTheCloneExecutionPath() {
        FilterCase source = NodeFilterCaseFactory.deepTree(FilterAction.SKIP_CHILDREN);
        FilterCase followUp = NodeFilterCloneMetamorphicSpec.generateFollowUp(source);

        assertFalse(source.cloneRootBeforeFiltering());
        assertTrue(followUp.cloneRootBeforeFiltering());
        assertEquals(source.html(), followUp.html());
        assertEquals(source.policy(), followUp.policy());
    }

    @Test
    void relationRejectsAScenarioWhoseTargetWasNeverReached() {
        FilterCase source = NodeFilterCaseFactory.source(
                "MISSING_TARGET",
                "<main id='root'><p>text</p></main>",
                "#root",
                FilterAction.REMOVE,
                "aside");
        FilterTrace output = NodeTraversorFilterSut.run(source);

        assertThrows(AssertionError.class, () -> NodeFilterCloneMetamorphicSpec.assertRelation(output, output));
    }

    @Test
    void promptConfigurationLoadsWithStructuredScenarios() throws Exception {
        Path repoRoot = Path.of("").toAbsolutePath().normalize();
        PromptConfig config = PromptConfigLoader.load(
                repoRoot.resolve("examples/jsoup/mt-testing/prompt1.yaml"), repoRoot);

        assertEquals(50, config.count());
        assertEquals(InputGenerator.LLM, config.inputGenerator());
        assertEquals(MRProvider.DEV, config.mrProvider());
        assertEquals(List.of("jsoup"), config.mavenProfiles());
        assertEquals(5, config.inputDomainRequirements().scenarios().size());
        assertEquals(1, config.sutClasspath().size());
        assertTrue(Files.isDirectory(config.sutClasspath().get(0)));
    }
}
