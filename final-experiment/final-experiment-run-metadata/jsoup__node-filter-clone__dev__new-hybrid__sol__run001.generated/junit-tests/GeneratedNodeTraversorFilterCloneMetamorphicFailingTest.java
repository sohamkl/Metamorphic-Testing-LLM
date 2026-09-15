import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final class Policy implements NodeFilter {
        private final int mode;

        private Policy(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (mode) {
                case 1:
                    return FilterResult.STOP;
                case 3:
                    return FilterResult.SKIP_CHILDREN;
                case 4:
                    return FilterResult.SKIP_ENTIRELY;
                case 5:
                    return FilterResult.REMOVE;
                case 10:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 11:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 12:
                    return hasId(node, "target")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 14:
                    return hasId(node, "target")
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 17:
                    if (hasId(node, "prune")) {
                        return FilterResult.SKIP_CHILDREN;
                    }
                    if (hasId(node, "remove")) {
                        return FilterResult.REMOVE;
                    }
                    return FilterResult.CONTINUE;
                case 18:
                    return depth == 1 && node.siblingIndex() == 1
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 19:
                    return depth == 1
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 21:
                    if (hasId(node, "remove")) {
                        return FilterResult.REMOVE;
                    }
                    if (hasId(node, "stop")) {
                        return FilterResult.STOP;
                    }
                    return FilterResult.CONTINUE;
                case 24:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 25:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (mode) {
                case 2:
                    return FilterResult.STOP;
                case 6:
                    return FilterResult.REMOVE;
                case 7:
                    return FilterResult.SKIP_CHILDREN;
                case 8:
                    return FilterResult.SKIP_ENTIRELY;
                case 13:
                    return hasId(node, "target")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 15:
                    return hasId(node, "target")
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 16:
                    return depth == 0
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 20:
                    return depth == 1
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 22:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 23:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 25:
                    return hasId(node, "target")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        private static boolean hasId(Node node, String id) {
            return node instanceof Element && id.equals(((Element) node).id());
        }
    }

    private static Element element(String tag, String id, Node... children) {
        Element element = new Element(tag);
        if (id != null && !id.isEmpty()) {
            element.attr("id", id);
        }
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static void exercise(NodeFilter sourceFilter, Node sourceRoot) {
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(
                sourceFilter, sourceRoot);

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
