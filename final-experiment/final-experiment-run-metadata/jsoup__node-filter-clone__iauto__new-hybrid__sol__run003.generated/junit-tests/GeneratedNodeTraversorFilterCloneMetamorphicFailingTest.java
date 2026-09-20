import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    public static final class AttributePolicy implements NodeFilter {
        public AttributePolicy() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            String result = ((Element) node).attr("data-head");
            return result.isEmpty() ? FilterResult.CONTINUE : FilterResult.valueOf(result);
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            String result = ((Element) node).attr("data-tail");
            return result.isEmpty() ? FilterResult.CONTINUE : FilterResult.valueOf(result);
        }
    }

    public static final class DepthSkipPolicy implements NodeFilter {
        private final int threshold;

        public DepthSkipPolicy(int threshold) {
            this.threshold = threshold;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return depth >= threshold ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static Element node(String id) {
        return new Element("n").attr("id", id);
    }

    private static Element child(Element parent, String id) {
        Element child = node(id);
        parent.appendChild(child);
        return child;
    }

    private static Element head(Element node, NodeFilter.FilterResult result) {
        node.attr("data-head", result.name());
        return node;
    }

    private static Element tail(Element node, NodeFilter.FilterResult result) {
        node.attr("data-tail", result.name());
        return node;
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            Node sourceRoot,
            Node followUpRoot) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError("FilterResult mismatch: source=" + sourceOutput
                    + ", follow-up=" + followUpOutput);
        }
        String sourceHtml = sourceRoot.outerHtml();
        String followUpHtml = followUpRoot.outerHtml();
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError("Final outerHtml mismatch:\nsource=" + sourceHtml
                    + "\nfollow-up=" + followUpHtml);
        }
    }

}
