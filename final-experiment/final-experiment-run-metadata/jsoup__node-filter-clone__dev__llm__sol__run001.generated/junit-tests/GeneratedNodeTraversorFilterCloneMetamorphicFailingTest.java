import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Tag;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static Element element(String name) {
        return new Element(Tag.valueOf(name), "");
    }

    private static Element child(Element parent, String name) {
        Element child = element(name);
        parent.appendChild(child);
        return child;
    }

    private static Element head(Element node, NodeFilter.FilterResult result) {
        node.attr("data-head-result", result.name());
        return node;
    }

    private static Element tail(Element node, NodeFilter.FilterResult result) {
        node.attr("data-tail-result", result.name());
        return node;
    }

    private static final class MarkupPolicy implements NodeFilter {
        @Override
        public FilterResult head(Node node, int depth) {
            if (node instanceof Element) {
                String configured = ((Element) node).attr("data-head-result");
                if (!configured.isEmpty()) {
                    return FilterResult.valueOf(configured);
                }
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            if (node instanceof Element) {
                String configured = ((Element) node).attr("data-tail-result");
                if (!configured.isEmpty()) {
                    return FilterResult.valueOf(configured);
                }
            }
            return FilterResult.CONTINUE;
        }
    }

    private static final class DepthTwoPruningPolicy implements NodeFilter {
        @Override
        public FilterResult head(Node node, int depth) {
            return depth == 2 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class ScheduledPolicy implements NodeFilter {
        private int headCount;

        @Override
        public FilterResult head(Node node, int depth) {
            headCount++;
            if (headCount == 2) {
                return FilterResult.SKIP_CHILDREN;
            }
            if (headCount == 3) {
                return FilterResult.REMOVE;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

}
