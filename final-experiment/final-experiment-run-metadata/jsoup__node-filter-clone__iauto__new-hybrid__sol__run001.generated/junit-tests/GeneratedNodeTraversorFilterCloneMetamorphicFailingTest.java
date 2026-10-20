import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static final class ContinueFilter implements NodeFilter {
        public ContinueFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class MarkerFilter implements NodeFilter {
        public MarkerFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return markedResult(node, "data-head");
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return markedResult(node, "data-tail");
        }

        private static FilterResult markedResult(Node node, String attribute) {
            if (!(node instanceof Element)) {
                return FilterResult.CONTINUE;
            }
            String marker = ((Element) node).attr(attribute);
            return marker.isEmpty() ? FilterResult.CONTINUE : FilterResult.valueOf(marker);
        }
    }

    private static final class HeadStopFilter implements NodeFilter {
        public HeadStopFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.STOP;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class HeadSkipChildrenFilter implements NodeFilter {
        public HeadSkipChildrenFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.SKIP_CHILDREN;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class HeadSkipEntirelyFilter implements NodeFilter {
        public HeadSkipEntirelyFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.SKIP_ENTIRELY;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class HeadRemoveFilter implements NodeFilter {
        public HeadRemoveFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.REMOVE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class TailStopFilter implements NodeFilter {
        public TailStopFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.STOP;
        }
    }

    private static final class TailRemoveFilter implements NodeFilter {
        public TailRemoveFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.REMOVE;
        }
    }

    private static final class TailSkipChildrenFilter implements NodeFilter {
        public TailSkipChildrenFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.SKIP_CHILDREN;
        }
    }

    private static final class TailSkipEntirelyFilter implements NodeFilter {
        public TailSkipEntirelyFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.SKIP_ENTIRELY;
        }
    }

    private static Element element(String tag, Node... children) {
        Element element = new Element(tag);
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static Element markedHead(String tag, NodeFilter.FilterResult result, Node... children) {
        Element element = element(tag, children);
        element.attr("data-head", result.name());
        return element;
    }

    private static Element markedTail(String tag, NodeFilter.FilterResult result, Node... children) {
        Element element = element(tag, children);
        element.attr("data-tail", result.name());
        return element;
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            Node sourceRoot,
            Node followUpRoot) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Different terminal FilterResult values: source=" + sourceOutput
                            + ", follow-up=" + followUpOutput);
        }
        String sourceHtml = sourceRoot.outerHtml();
        String followUpHtml = followUpRoot.outerHtml();
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError(
                    "Different final outerHtml values: source=" + sourceHtml
                            + ", follow-up=" + followUpHtml);
        }
    }

}
