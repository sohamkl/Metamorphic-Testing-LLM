package jsoupmt;

import java.util.List;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.NodeFilter.FilterResult;

import jsoupmt.NodeTraversorFilterSut.FilterAction;
import jsoupmt.NodeTraversorFilterSut.FilterCase;
import jsoupmt.NodeTraversorFilterSut.FilterTrace;
import jsoupmt.NodeTraversorFilterSut.VisitEvent;

/** Developer-owned MR: content appended after a guaranteed STOP point is never traversed. */
public final class NodeFilterStopSuffixMetamorphicSpec {
    private static final String SUFFIX_NODE_NAME = "mr-stop-suffix";
    private static final String SUFFIX_MARKER = "data-mr-stop-suffix";

    private NodeFilterStopSuffixMetamorphicSpec() {
    }

    public static FilterCase generateFollowUp(FilterCase source) {
        Objects.requireNonNull(source, "source");
        require(source.policy().action() == FilterAction.STOP, "Source action must be STOP");
        require(!source.html().contains(SUFFIX_MARKER), "Source HTML already contains the MR suffix marker");

        Document document = Jsoup.parseBodyFragment(source.html());
        document.outputSettings().prettyPrint(false);
        Element root = document.selectFirst(source.rootSelector());
        require(root != null, "Source root selector must match an element");
        root.appendElement(SUFFIX_NODE_NAME)
                .attr(SUFFIX_MARKER, "true")
                .append("<section><p>unvisited metamorphic suffix</p></section>");

        return new FilterCase(
                source.scenarioId(),
                document.body().html(),
                source.rootSelector(),
                source.policy(),
                false);
    }

    public static void assertRelation(FilterTrace sourceOutput, FilterTrace followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");

        require(sourceOutput.policy().action() == FilterAction.STOP, "Source policy must use STOP");
        requireEquals(FilterResult.STOP, sourceOutput.terminalResult(), "source terminal result");
        requireEquals(FilterResult.STOP, followUpOutput.terminalResult(), "follow-up terminal result");
        requireEquals(1, sourceOutput.appliedActions(), "source applied-action count");
        requireEquals(1, followUpOutput.appliedActions(), "follow-up applied-action count");
        require(sourceOutput.matchedTargets() > 0, "Source target must be reached");
        require(followUpOutput.matchedTargets() > 0, "Follow-up target must be reached");

        requireEquals(sourceOutput.scenarioId(), followUpOutput.scenarioId(), "scenario ID");
        requireEquals(sourceOutput.policy(), followUpOutput.policy(), "filter policy");
        requireEquals(sourceOutput.matchedTargets(), followUpOutput.matchedTargets(), "matched-target count");
        requireEquals(eventSignatures(sourceOutput.events()), eventSignatures(followUpOutput.events()),
                "visited-node sequence");
        require(followUpOutput.events().stream().noneMatch(event -> event.nodeName().equals(SUFFIX_NODE_NAME)),
                "Appended suffix must not be visited");

        require(!sourceOutput.finalHtml().contains(SUFFIX_MARKER), "Source output must not contain the suffix marker");
        require(followUpOutput.finalHtml().contains(SUFFIX_MARKER), "Follow-up output must retain the suffix marker");
        requireEquals(
                normalizedHtml(sourceOutput.finalHtml()),
                normalizedHtmlWithoutSuffix(followUpOutput.finalHtml()),
                "final DOM after removing appended suffix");
    }

    private static List<String> eventSignatures(List<VisitEvent> events) {
        return events.stream()
                .map(event -> event.phase() + ":" + event.depth() + ":" + event.nodeName())
                .toList();
    }

    private static String normalizedHtml(String html) {
        return Jsoup.parseBodyFragment(html).body().html();
    }

    private static String normalizedHtmlWithoutSuffix(String html) {
        Document document = Jsoup.parseBodyFragment(html);
        document.select("[" + SUFFIX_MARKER + "]").remove();
        return document.body().html();
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
