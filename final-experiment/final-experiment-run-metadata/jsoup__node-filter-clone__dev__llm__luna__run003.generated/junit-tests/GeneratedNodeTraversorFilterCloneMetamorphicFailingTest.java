import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static void run(Node root, NodeFilter sourceFilter) {
        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, root);
        Object[] followUp =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Element leaf() {
        return new Element("root");
    }

    private static Element oneChild() {
        Element root = new Element("root");
        root.appendElement("child");
        return root;
    }

    private static Element twoChildren() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        return root;
    }

    private static Element threeChildren() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("middle");
        root.appendElement("last");
        return root;
    }

    private static Element branched() {
        Element root = new Element("root");
        Element first = root.appendElement("first");
        first.appendElement("first-leaf");
        Element second = root.appendElement("second");
        second.appendElement("second-leaf");
        return root;
    }

    private static Element chain() {
        Element root = new Element("root");
        Element one = root.appendElement("one");
        Element two = one.appendElement("two");
        two.appendElement("three");
        return root;
    }

    private static Element deepChain() {
        Element root = new Element("root");
        Element one = root.appendElement("one");
        Element two = one.appendElement("two");
        Element three = two.appendElement("three");
        three.appendElement("four");
        return root;
    }

    private static Element nested() {
        Element root = new Element("root");
        Element parent = root.appendElement("parent");
        parent.appendElement("first");
        parent.appendElement("second");
        root.appendElement("sibling");
        return root;
    }

    public static final class Policy implements NodeFilter {
        private final int mode;

        public Policy(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (mode) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 4:
                case 5:
                case 10:
                case 26:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 11:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 12:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 14:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 15:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 16:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 19:
                    return depth == 1 && node.childNodeSize() > 0
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 29:
                    if (depth == 1 && node.siblingIndex() == 0)
                        return FilterResult.SKIP_CHILDREN;
                    if (depth == 2)
                        return FilterResult.REMOVE;
                    return FilterResult.CONTINUE;
                case 30:
                    return depth == 1 && node.siblingIndex() == 1
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (mode) {
                case 5:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 6:
                    return depth > 0 && node.childNodeSize() == 0
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 7:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 8:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 20:
                    return depth == 1 && node.childNodeSize() == 0
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 21:
                    return depth == 1 && node.childNodeSize() == 0
                            && node.nextSibling() == null
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 22:
                    return depth >= 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 23:
                    return depth == 1 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 24:
                    return depth == 1 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 25:
                case 32:
                    return depth == 1 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 27:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 28:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }
}
