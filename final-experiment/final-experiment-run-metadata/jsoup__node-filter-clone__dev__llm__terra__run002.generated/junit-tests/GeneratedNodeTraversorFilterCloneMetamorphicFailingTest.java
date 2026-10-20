import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static Element e(String tag) {
        return new Element(Tag.valueOf(tag), "");
    }

    private static void run(NodeFilter filter, Node root) {
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static final class AttributePolicy implements NodeFilter {
        @Override
        public FilterResult head(Node node, int depth) {
            return result(node, "h");
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return result(node, "t");
        }

        private FilterResult result(Node node, String attribute) {
            String value = node.attr(attribute);
            return value.isEmpty() ? FilterResult.CONTINUE : FilterResult.valueOf(value);
        }
    }

    private static final class DepthSkipPolicy implements NodeFilter {
        private final int depth;

        DepthSkipPolicy(int depth) {
            this.depth = depth;
        }

        @Override
        public FilterResult head(Node node, int currentDepth) {
            return currentDepth == depth ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int currentDepth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class MarkerRemovePolicy implements NodeFilter {
        private final String marker;

        MarkerRemovePolicy(String marker) {
            this.marker = marker;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return marker.equals(node.attr("data-marker")) ? FilterResult.REMOVE : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class TypeSkipPolicy implements NodeFilter {
        private final String typeName;

        TypeSkipPolicy(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return node.getClass().getSimpleName().equals(typeName)
                    ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class MutationPolicy implements NodeFilter {
        @Override
        public FilterResult head(Node node, int depth) {
            if (depth == 1 && node instanceof Element)
                node.attr("data-visited", "yes");
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

}
