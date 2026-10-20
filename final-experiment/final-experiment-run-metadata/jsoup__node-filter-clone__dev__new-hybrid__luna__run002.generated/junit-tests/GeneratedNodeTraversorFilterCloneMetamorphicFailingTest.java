import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeFilter.FilterResult;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final class Policy implements NodeFilter {
        private final int mode;

        Policy(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            String name = node.nodeName();

            switch (mode) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 3:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 4:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 5:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 6:
                    return "first".equals(name) ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 7:
                    return "first".equals(name) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 8:
                    return "stop-child".equals(name) ? FilterResult.STOP : FilterResult.CONTINUE;
                case 9:
                    return "first".equals(name) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 10:
                    return "sole".equals(name) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 11:
                    return "first".equals(name) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 12:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 13:
                    return "second".equals(name) ? FilterResult.STOP : FilterResult.CONTINUE;
                case 14:
                    if ("first".equals(name)) return FilterResult.SKIP_CHILDREN;
                    if ("second".equals(name)) return FilterResult.REMOVE;
                    return FilterResult.CONTINUE;
                case 15:
                    return depth >= 2 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 16:
                    return "remove-a".equals(name) || "remove-b".equals(name)
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            String name = node.nodeName();

            switch (mode) {
                case 4:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 5:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 9:
                    return "tail-stop".equals(name) ? FilterResult.STOP : FilterResult.CONTINUE;
                case 10:
                    return "sole".equals(name) ? FilterResult.STOP : FilterResult.CONTINUE;
                case 11:
                    return "inner".equals(name) ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    private static Element element(String name) {
        return new Element(name);
    }

    private static void verify(NodeFilter filter, Node root) {
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
