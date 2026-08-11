package jsoupmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterSut.FilterAction;
import jsoupmt.NodeTraversorFilterSut.FilterCase;
import jsoupmt.NodeTraversorFilterSut.FilterTrace;

class NodeFilterStopSuffixMetamorphicSpecTest {

    @Test
    void appendsMarkedSuffixWithoutChangingSourcePolicy() {
        FilterCase source = NodeFilterCaseFactory.source(
                "STOP_MIDDLE",
                "<div id='root'><p>before</p><target>stop</target><p>after</p></div>",
                "#root",
                FilterAction.STOP,
                "target");

        FilterCase followUp = NodeFilterStopSuffixMetamorphicSpec.generateFollowUp(source);

        assertEquals(source.scenarioId(), followUp.scenarioId());
        assertEquals(source.rootSelector(), followUp.rootSelector());
        assertEquals(source.policy(), followUp.policy());
        assertFalse(followUp.cloneRootBeforeFiltering());
        assertFalse(source.html().contains("data-mr-stop-suffix"));
        assertTrue(followUp.html().contains("data-mr-stop-suffix"));
    }

    @Test
    void appendedSuffixDoesNotChangeTraversalBeforeStop() {
        FilterCase source = NodeFilterCaseFactory.source(
                "STOP_DEEP",
                "<main id='root'><section><article><target>stop</target></article></section>"
                        + "<footer>after</footer></main>",
                "#root",
                FilterAction.STOP,
                "target");
        FilterCase followUp = NodeFilterStopSuffixMetamorphicSpec.generateFollowUp(source);

        FilterTrace sourceOutput = NodeTraversorFilterSut.run(source);
        FilterTrace followUpOutput = NodeTraversorFilterSut.run(followUp);

        NodeFilterStopSuffixMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
