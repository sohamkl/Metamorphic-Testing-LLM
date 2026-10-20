import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    public static final class MarkedPolicy implements NodeFilter {
        public MarkedPolicy() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return markedResult(node, "_head", "H_");
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return markedResult(node, "_tail", "T_");
        }

        private static FilterResult markedResult(Node node, String attribute, String textPrefix) {
            if (node instanceof Element) {
                String value = ((Element) node).attr(attribute);
                if (!value.isEmpty()) {
                    return FilterResult.valueOf(value);
                }
            }
            if (node instanceof TextNode) {
                String text = ((TextNode) node).getWholeText();
                if (text.startsWith(textPrefix)) {
                    return FilterResult.valueOf(text.substring(textPrefix.length()));
                }
            }
            return FilterResult.CONTINUE;
        }
    }

    public static final class DepthPolicy implements NodeFilter {
        private final int threshold;
        private final FilterResult selectedResult;

        public DepthPolicy(int threshold, FilterResult selectedResult) {
            this.threshold = threshold;
            this.selectedResult = selectedResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return depth >= threshold ? selectedResult : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class FollowUp {
        private final NodeFilter filter;
        private final Node root;

        private FollowUp(NodeFilter filter, Node root) {
            this.filter = filter;
            this.root = root;
        }
    }

    private static FollowUp generateFollowUp(NodeFilter filter, Node root) {
        Object[] transformed =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        return new FollowUp((NodeFilter) transformed[0], (Node) transformed[1]);
    }

    private static Element element(String tag) {
        return new Element(tag);
    }

    private static Element head(Element node, NodeFilter.FilterResult result) {
        node.attr("_head", result.name());
        return node;
    }

    private static Element tail(Element node, NodeFilter.FilterResult result) {
        node.attr("_tail", result.name());
        return node;
    }

    private static Element chain(int nodeCount) {
        Element root = element("n0");
        Element cursor = root;
        for (int index = 1; index < nodeCount; index++) {
            Element child = element("n" + index);
            cursor.appendChild(child);
            cursor = child;
        }
        return root;
    }

    private static Element twoLeafChildren() {
        Element root = element("root");
        root.appendChild(element("first"));
        root.appendChild(element("second"));
        return root;
    }

    private static void exercise(NodeFilter sourceFilter, Node sourceRoot) {
        FollowUp followUp = generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(followUp.filter, followUp.root);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
