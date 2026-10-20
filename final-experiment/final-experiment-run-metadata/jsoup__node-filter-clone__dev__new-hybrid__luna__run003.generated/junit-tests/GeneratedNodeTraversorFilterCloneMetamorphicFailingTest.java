import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final class Policy implements NodeFilter {
        private final int mode;

        Policy(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (mode) {
                case 0:
                    return FilterResult.STOP;
                case 1:
                    return FilterResult.SKIP_ENTIRELY;
                case 2:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 3:
                    return FilterResult.REMOVE;
                case 4:
                    return FilterResult.CONTINUE;
                case 5:
                    return FilterResult.CONTINUE;
                case 6:
                    return FilterResult.CONTINUE;
                case 7:
                    return depth >= 1 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 8:
                    return depth >= 1 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 9:
                    return depth >= 1 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 10:
                    return FilterResult.CONTINUE;
                case 11:
                    return FilterResult.CONTINUE;
                case 12:
                    return FilterResult.CONTINUE;
                case 13:
                    return depth >= 2 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 14:
                    return depth == 2 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                default:
                    if (depth == 1) return FilterResult.SKIP_CHILDREN;
                    if (depth >= 2) return FilterResult.REMOVE;
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (mode) {
                case 5:
                    return FilterResult.STOP;
                case 6:
                    return FilterResult.REMOVE;
                case 10:
                    return depth >= 1 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 11:
                    return depth >= 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 12:
                    return depth >= 1 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    private static Element leaf(String name) {
        return new Element(name);
    }

    private static Element oneChild(String name) {
        Element root = new Element(name);
        root.appendElement(name + "child");
        return root;
    }

    private static Element wide(String name, int count) {
        Element root = new Element(name);
        for (int i = 0; i < count; i++) {
            root.appendElement("item" + i);
        }
        return root;
    }

    private static Element branch(String name) {
        Element root = new Element(name);
        Element first = root.appendElement("first");
        first.appendElement("first-a");
        first.appendElement("first-b");
        root.appendElement("second");
        return root;
    }

    private static Element deep(String name) {
        Element root = new Element(name);
        Element one = root.appendElement("one");
        Element two = one.appendElement("two");
        two.appendElement("three");
        return root;
    }

    private static Element mixed(String name) {
        Element root = new Element(name);
        Element left = root.appendElement("left");
        Element leftChild = left.appendElement("left-child");
        leftChild.appendElement("left-leaf");
        root.appendElement("middle");
        Element right = root.appendElement("right");
        right.appendElement("right-child");
        return root;
    }

    private static void exercise(NodeFilter filter, Node root) {
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
