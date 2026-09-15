import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

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

    @Test
    public void ROOT_HEAD_STOP_1_leaf() {
        run(leaf(), new Policy(1));
    }

    @Test
    public void ROOT_HEAD_SKIP_ENTIRELY_1_oneChild() {
        run(oneChild(), new Policy(2));
    }

    @Test
    public void ROOT_LEAF_CONTINUE_TAIL_CONTINUE_1_twoChildren() {
        run(twoChildren(), new Policy(3));
    }

    @Test
    public void ROOT_LEAF_SKIP_CHILDREN_1_threeChildren() {
        run(threeChildren(), new Policy(4));
    }

    @Test
    public void ROOT_LEAF_TAIL_SKIP_CHILDREN_1_branched() {
        run(branched(), new Policy(5));
    }

    @Test
    public void ROOT_LEAF_TAIL_SKIP_ENTIRELY_1_chain() {
        run(chain(), new Policy(6));
    }

    @Test
    public void ROOT_LEAF_TAIL_REMOVE_1_nested() {
        run(nested(), new Policy(7));
    }

    @Test
    public void ROOT_LEAF_TAIL_STOP_1_leaf() {
        run(leaf(), new Policy(8));
    }

    @Test
    public void ROOT_CONTINUE_SINGLE_CHILD_1_oneChild() {
        run(oneChild(), new Policy(9));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_WITH_CHILD_1_twoChildren() {
        run(twoChildren(), new Policy(10));
    }

    @Test
    public void ROOT_REMOVE_WITH_CHILD_1_threeChildren() {
        run(threeChildren(), new Policy(11));
    }

    @Test
    public void ROOT_STOP_BEFORE_CHILDREN_1_branched() {
        run(branched(), new Policy(12));
    }

    @Test
    public void TWO_CHILDREN_SIBLING_ADVANCE_1_chainWithSibling() {
        run(twoChildren(), new Policy(13));
    }

    @Test
    public void FIRST_CHILD_REMOVE_WITH_SIBLING_1_nested() {
        run(nested(), new Policy(14));
    }

    @Test
    public void FIRST_CHILD_SKIP_ENTIRELY_WITH_SIBLING_1_twoChildren() {
        run(twoChildren(), new Policy(15));
    }

    @Test
    public void CHILD_STOP_WITH_SIBLING_1_oneChild() {
        run(twoChildren(), new Policy(16));
    }

    @Test
    public void DEEP_CHAIN_ASCENT_1_deepChain() {
        run(deepChain(), new Policy(17));
    }

    @Test
    public void NESTED_SIBLINGS_1_nested() {
        run(nested(), new Policy(18));
    }

    @Test
    public void INTERNAL_CHILD_REMOVE_SUBTREE_1_branched() {
        run(branched(), new Policy(19));
    }

    @Test
    public void LEAF_TAIL_REMOVE_WITH_SIBLING_1_twoChildren() {
        run(twoChildren(), new Policy(20));
    }

    @Test
    public void LAST_CHILD_TAIL_REMOVE_ASCENT_1_nested() {
        run(nested(), new Policy(21));
    }

    @Test
    public void NESTED_TAIL_STOP_1_branched() {
        run(nested(), new Policy(22));
    }

    @Test
    public void NONROOT_TAIL_SKIP_CHILDREN_1_nested() {
        run(nested(), new Policy(23));
    }

    @Test
    public void NONROOT_TAIL_SKIP_ENTIRELY_1_twoChildren() {
        run(twoChildren(), new Policy(24));
    }

    @Test
    public void NONROOT_TAIL_REMOVE_INTERNAL_1_branched() {
        run(branched(), new Policy(25));
    }

    @Test
    public void ROOT_TAIL_SKIP_CHILDREN_1_branched() {
        run(branched(), new Policy(26));
    }

    @Test
    public void ROOT_TAIL_SKIP_ENTIRELY_1_chain() {
        run(deepChain(), new Policy(27));
    }

    @Test
    public void ROOT_TAIL_STOP_AFTER_DESCENDANTS_1_nested() {
        run(nested(), new Policy(28));
    }

    @Test
    public void MIXED_DEPTH_CONTROL_1_branched() {
        run(branched(), new Policy(29));
    }

    @Test
    public void THREE_SIBLING_POSITIONAL_POLICY_1_threeChildren() {
        run(threeChildren(), new Policy(30));
    }

    @Test
    public void EMPTY_DESCENDANT_BRANCH_1_branched() {
        run(branched(), new Policy(31));
    }

    @Test
    public void FOLLOWUP_DOM_AND_RESULT_EQUIVALENCE_1_nested() {
        run(nested(), new Policy(32));
    }
}
