import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicTest {
    private static final class Policy implements NodeFilter {
        private final int mode;

        Policy(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (mode) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 3:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 4:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 5:
                    return depth == 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 6:
                    return has(node, "skip") ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 7:
                    return depth == 1 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 8:
                    return has(node, "remove") ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 15:
                    if (has(node, "remove")) return FilterResult.REMOVE;
                    if (has(node, "stop")) return FilterResult.STOP;
                    return FilterResult.CONTINUE;
                case 17:
                    return depth >= 1 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (mode) {
                case 9:
                    return depth == 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 10:
                    return depth == 1 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 11:
                    return depth == 1 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 12:
                    return depth == 1 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 13:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 14:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 16:
                    return has(node, "stop") ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        private static boolean has(Node node, String attribute) {
            return node instanceof Element && ((Element) node).hasAttr(attribute);
        }
    }

    private static Object[] generateFollowUp(NodeFilter filter, Node root) {
        return NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
    }

    private static void exercise(Policy filter, Element root) {
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Element leaf(String name) {
        return new Element(name);
    }

    private static Element oneChild() {
        Element root = new Element("root");
        root.appendChild(new Element("child"));
        return root;
    }

    private static Element chain() {
        Element root = new Element("root");
        Element one = new Element("one");
        one.appendChild(new Element("two"));
        root.appendChild(one);
        return root;
    }

    private static Element deepChain() {
        Element root = new Element("root");
        Element one = new Element("one");
        Element two = new Element("two");
        two.appendChild(new Element("three"));
        one.appendChild(two);
        root.appendChild(one);
        return root;
    }

    private static Element siblings() {
        Element root = new Element("root");
        root.appendChild(new Element("first"));
        root.appendChild(new Element("second"));
        root.appendChild(new Element("third"));
        return root;
    }

    private static Element nestedSiblings() {
        Element root = new Element("root");
        root.appendChild(new Element("first"));
        Element middle = new Element("middle");
        middle.appendChild(new Element("nested"));
        root.appendChild(middle);
        root.appendChild(new Element("last"));
        return root;
    }

    private static Element mixed() {
        Element root = new Element("root");
        Element branch = new Element("branch");
        branch.appendChild(new TextNode("text"));
        branch.appendChild(new Element("inner"));
        root.appendChild(branch);
        root.appendChild(new TextNode("tail"));
        return root;
    }

    private static Element markedSiblings() {
        Element root = new Element("root");
        Element remove = new Element("remove");
        remove.attr("remove", "true");
        Element stop = new Element("stop");
        stop.attr("stop", "true");
        root.appendChild(remove);
        root.appendChild(stop);
        root.appendChild(new Element("remaining"));
        return root;
    }

    private static Element markedChain() {
        Element root = new Element("root");
        Element parent = new Element("parent");
        parent.attr("stop", "true");
        Element remove = new Element("remove");
        remove.attr("remove", "true");
        parent.appendChild(remove);
        root.appendChild(parent);
        return root;
    }

    private static Element markedSkipTree() {
        Element root = new Element("root");
        Element branch = new Element("branch");
        branch.attr("skip", "true");
        branch.appendChild(new TextNode("hidden"));
        branch.appendChild(new Element("inside"));
        root.appendChild(branch);
        root.appendChild(new Element("later"));
        return root;
    }

    @Test
    public void ROOT_LEAF_CONTINUE_TAIL_CONTINUE_1() {
        exercise(new Policy(0), leaf("root"));
    }

    @Test
    public void ROOT_HEAD_STOP_1() {
        exercise(new Policy(1), oneChild());
    }

    @Test
    public void ROOT_HEAD_STOP_2() {
        exercise(new Policy(1), deepChain());
    }

    @Test
    public void ROOT_SKIP_CHILDREN_TAIL_CONTINUE_1() {
        exercise(new Policy(2), siblings());
    }

    @Test
    public void ROOT_SKIP_CHILDREN_TAIL_CONTINUE_2() {
        exercise(new Policy(2), mixed());
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_1() {
        exercise(new Policy(3), oneChild());
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_2() {
        exercise(new Policy(3), mixed());
    }

    @Test
    public void ROOT_REMOVE_WITH_DESCENDANTS_1() {
        exercise(new Policy(4), chain());
    }

    @Test
    public void ROOT_CONTINUE_ONE_CHILD_1() {
        exercise(new Policy(0), oneChild());
    }

    @Test
    public void NESTED_CONTINUE_DEPTH_TWO_1() {
        exercise(new Policy(0), deepChain());
    }

    @Test
    public void CHILD_HEAD_STOP_1() {
        exercise(new Policy(5), oneChild());
    }

    @Test
    public void CHILD_HEAD_STOP_2() {
        exercise(new Policy(5), mixed());
    }

    @Test
    public void CHILD_SKIP_CHILDREN_TAIL_CONTINUE_1() {
        exercise(new Policy(6), markedSkipTree());
    }

    @Test
    public void CHILD_SKIP_CHILDREN_TAIL_CONTINUE_2() {
        exercise(new Policy(6), nestedSiblings());
    }

    @Test
    public void CHILD_SKIP_ENTIRELY_WITH_SIBLING_1() {
        exercise(new Policy(7), siblings());
    }

    @Test
    public void CHILD_SKIP_ENTIRELY_WITH_SIBLING_2() {
        exercise(new Policy(7), mixed());
    }

    @Test
    public void CHILD_REMOVE_WITH_NEXT_SIBLING_1() {
        exercise(new Policy(8), markedSiblings());
    }

    @Test
    public void CHILD_REMOVE_WITH_NEXT_SIBLING_2() {
        exercise(new Policy(8), nestedSiblings());
    }

    @Test
    public void CHILD_REMOVE_LAST_SIBLING_1() {
        Element root = new Element("root");
        Element first = new Element("first");
        Element last = new Element("last");
        last.attr("remove", "true");
        root.appendChild(first);
        root.appendChild(last);
        exercise(new Policy(8), root);
    }

    @Test
    public void CHILD_REMOVE_LAST_SIBLING_2() {
        exercise(new Policy(8), chain());
    }

    @Test
    public void INTERNAL_REMOVE_SKIPS_SUBTREE_1() {
        Element root = new Element("root");
        Element branch = new Element("branch");
        branch.attr("remove", "true");
        branch.appendChild(new Element("hidden"));
        root.appendChild(branch);
        root.appendChild(new Element("later"));
        exercise(new Policy(8), root);
    }

    @Test
    public void INTERNAL_REMOVE_SKIPS_SUBTREE_2() {
        exercise(new Policy(8), mixed());
    }

    @Test
    public void CHILD_TAIL_STOP_1() {
        exercise(new Policy(9), chain());
    }

    @Test
    public void CHILD_TAIL_STOP_2() {
        exercise(new Policy(9), siblings());
    }

    @Test
    public void CHILD_TAIL_REMOVE_WITH_SIBLING_1() {
        exercise(new Policy(10), siblings());
    }

    @Test
    public void CHILD_TAIL_REMOVE_WITH_SIBLING_2() {
        exercise(new Policy(10), mixed());
    }

    @Test
    public void CHILD_TAIL_REMOVE_LAST_1() {
        exercise(new Policy(10), oneChild());
    }

    @Test
    public void CHILD_TAIL_REMOVE_LAST_2() {
        exercise(new Policy(10), chain());
    }

    @Test
    public void CHILD_TAIL_SKIP_ENTIRELY_1() {
        exercise(new Policy(11), siblings());
    }

    @Test
    public void CHILD_TAIL_SKIP_CHILDREN_1() {
        exercise(new Policy(12), nestedSiblings());
    }

    @Test
    public void ROOT_TAIL_STOP_1() {
        exercise(new Policy(13), leaf("root"));
    }

    @Test
    public void ROOT_TAIL_STOP_2() {
        exercise(new Policy(13), oneChild());
    }

    @Test
    public void ROOT_TAIL_REMOVE_1() {
        exercise(new Policy(14), chain());
    }

    @Test
    public void MULTIPLE_SIBLINGS_DEPTH_FIRST_ORDER_1() {
        exercise(new Policy(0), siblings());
    }

    @Test
    public void MULTIPLE_SIBLINGS_DEPTH_FIRST_ORDER_2() {
        exercise(new Policy(0), nestedSiblings());
    }

    @Test
    public void MIXED_ELEMENT_TEXT_CONTINUE_1() {
        exercise(new Policy(0), mixed());
    }

    @Test
    public void MIXED_ELEMENT_TEXT_CONTINUE_2() {
        exercise(new Policy(0), deepChain());
    }

    @Test
    public void MIXED_POLICY_SUBTREE_PRUNING_1() {
        exercise(new Policy(6), markedSkipTree());
    }

    @Test
    public void MIXED_POLICY_SUBTREE_PRUNING_2() {
        exercise(new Policy(17), nestedSiblings());
    }

    @Test
    public void STOP_AFTER_REMOVAL_SIBLING_1() {
        exercise(new Policy(15), markedSiblings());
    }

    @Test
    public void STOP_AFTER_REMOVAL_SIBLING_2() {
        Element root = new Element("root");
        Element remove = new Element("remove");
        remove.attr("remove", "true");
        Element stop = new Element("stop");
        stop.attr("stop", "true");
        root.appendChild(remove);
        root.appendChild(stop);
        root.appendChild(new TextNode("remaining"));
        exercise(new Policy(15), root);
    }

    @Test
    public void STOP_AFTER_PARENT_ASCENT_1() {
        exercise(new Policy(16), markedChain());
    }

    @Test
    public void FILTER_STATE_COPIED_BY_VALUE_1() {
        exercise(new Policy(17), chain());
    }

    @Test
    public void FILTER_STATE_COPIED_BY_VALUE_2() {
        exercise(new Policy(15), markedSiblings());
    }

    @Test
    public void DETACHED_ROOT_WITH_NO_DESCENDANTS_AND_NO_MUTATION_1() {
        exercise(new Policy(3), leaf("root"));
    }
}
