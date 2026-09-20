import java.util.Objects;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final String NEVER = "__never__";

    private static Element element(String name) {
        return new Element(name, "");
    }

    private static final class NamedFilter implements NodeFilter {
        private final String headName;
        private final FilterResult headResult;
        private final String tailName;
        private final FilterResult tailResult;

        public NamedFilter(String headName, FilterResult headResult,
                           String tailName, FilterResult tailResult) {
            this.headName = headName;
            this.headResult = headResult;
            this.tailName = tailName;
            this.tailResult = tailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return node.nodeName().equals(headName) ? headResult : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return node.nodeName().equals(tailName) ? tailResult : FilterResult.CONTINUE;
        }
    }

    private static NodeFilter configured(String headName, FilterResult headResult,
                                         String tailName, FilterResult tailResult) {
        return new NamedFilter(headName, headResult, tailName, tailResult);
    }

    private static void exercise(NodeFilter sourceFilter, Node sourceRoot) {
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(
                sourceFilter, sourceRoot);
        Node followUpRoot = (Node) followUp[1];

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], followUpRoot);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, followUpRoot);
    }

    private static void assertMetamorphicRelation(FilterResult sourceOutput,
                                                   FilterResult followUpOutput,
                                                   Node sourceRoot,
                                                   Node followUpRoot) {
        if (!Objects.equals(sourceOutput, followUpOutput)) {
            throw new AssertionError("Filter results differ: source=" + sourceOutput
                    + ", follow-up=" + followUpOutput);
        }

        String sourceHtml = sourceRoot.outerHtml();
        String followUpHtml = followUpRoot.outerHtml();
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError("Final outerHtml differs: source=" + sourceHtml
                    + ", follow-up=" + followUpHtml);
        }
    }

}
