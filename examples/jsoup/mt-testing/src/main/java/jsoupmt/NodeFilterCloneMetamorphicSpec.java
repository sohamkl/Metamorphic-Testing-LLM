package jsoupmt;

import java.util.Objects;

import org.jsoup.select.NodeFilter.FilterResult;

import jsoupmt.NodeTraversorFilterSut.FilterCase;
import jsoupmt.NodeTraversorFilterSut.FilterTrace;

/** Developer-owned clone-equivalence metamorphic relation for NodeTraversor.filter. */
public final class NodeFilterCloneMetamorphicSpec {
    private NodeFilterCloneMetamorphicSpec() {
    }

    public static FilterCase generateFollowUp(FilterCase source) {
        Objects.requireNonNull(source, "source");
        return new FilterCase(
                source.scenarioId(),
                source.html(),
                source.rootSelector(),
                source.policy(),
                true);
    }

    public static void assertRelation(FilterTrace sourceOutput, FilterTrace followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");

        require(!sourceOutput.events().isEmpty(), "Source traversal must visit at least one node");
        require(sourceOutput.matchedTargets() > 0, "Source target must be reached");
        require(sourceOutput.appliedActions() > 0, "Source action must be applied");
        require(followUpOutput.matchedTargets() > 0, "Follow-up target must be reached");
        require(followUpOutput.appliedActions() > 0, "Follow-up action must be applied");

        requireEquals(sourceOutput.scenarioId(), followUpOutput.scenarioId(), "scenario ID");
        requireEquals(sourceOutput.policy(), followUpOutput.policy(), "filter policy");
        requireEquals(sourceOutput.terminalResult(), followUpOutput.terminalResult(), "terminal result");
        requireEquals(sourceOutput.matchedTargets(), followUpOutput.matchedTargets(), "matched-target count");
        requireEquals(sourceOutput.appliedActions(), followUpOutput.appliedActions(), "applied-action count");
        requireEquals(sourceOutput.events(), followUpOutput.events(), "ordered traversal events");
        requireEquals(sourceOutput.finalHtml(), followUpOutput.finalHtml(), "final DOM HTML");

        if (sourceOutput.policy().action() == NodeTraversorFilterSut.FilterAction.STOP) {
            requireEquals(FilterResult.STOP, sourceOutput.terminalResult(), "STOP terminal result");
        }
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void requireEquals(Object expected, Object actual, String subject) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(subject + " differs: expected <" + expected + "> but was <" + actual + ">");
        }
    }
}
