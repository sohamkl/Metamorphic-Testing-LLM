import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
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

    @Test
    public void ROOT_HEAD_STOP_1() {
        exercise(new Policy(0), leaf("stopRoot"));
    }

    @Test
    public void ROOT_HEAD_SKIP_ENTIRE_1() {
        exercise(new Policy(1), oneChild("skipEntireRoot"));
    }

    @Test
    public void ROOT_HEAD_SKIP_CHILDREN_1() {
        exercise(new Policy(2), wide("skipChildrenRoot", 3));
    }

    @Test
    public void ROOT_HEAD_SKIP_CHILDREN_2() {
        exercise(new Policy(3), branch("removeRoot"));
    }

    @Test
    public void ROOT_HEAD_REMOVE_1() {
        exercise(new Policy(0), deep("removeDecisionRoot"));
    }

    @Test
    public void ROOT_HEAD_CONTINUE_LEAF_1() {
        exercise(new Policy(4), mixed("continueMixed"));
    }

    @Test
    public void ROOT_HEAD_CONTINUE_LEAF_2() {
        exercise(new Policy(2), leaf("continueLeafBoundary"));
    }

    @Test
    public void ROOT_TAIL_STOP_1() {
        exercise(new Policy(5), oneChild("tailStopOne"));
    }

    @Test
    public void ROOT_TAIL_STOP_2() {
        exercise(new Policy(5), wide("tailStopWide", 3));
    }

    @Test
    public void ROOT_TAIL_REMOVE_1() {
        exercise(new Policy(6), branch("tailRemoveBranch"));
    }

    @Test
    public void FULL_LINEAR_CONTINUE_1() {
        exercise(new Policy(4), deep("linearContinue"));
    }

    @Test
    public void FULL_LINEAR_CONTINUE_2() {
        exercise(new Policy(7), mixed("linearPolicy"));
    }

    @Test
    public void FULL_BRANCHING_CONTINUE_1() {
        exercise(new Policy(1), leaf("branchingLeaf"));
    }

    @Test
    public void FULL_BRANCHING_CONTINUE_2() {
        exercise(new Policy(9), oneChild("branchingRemoval"));
    }

    @Test
    public void FULL_BRANCHING_CONTINUE_3() {
        exercise(new Policy(6), wide("branchingWide", 4));
    }

    @Test
    public void SKIP_CHILDREN_AT_DESCENDANT_1() {
        exercise(new Policy(7), branch("skipDescendant"));
    }

    @Test
    public void SKIP_CHILDREN_AT_DESCENDANT_2() {
        exercise(new Policy(7), deep("skipLinearDescendant"));
    }

    @Test
    public void SKIP_ENTIRE_AT_DESCENDANT_1() {
        exercise(new Policy(8), mixed("skipEntireDescendant"));
    }

    @Test
    public void SKIP_ENTIRE_AT_DESCENDANT_2() {
        exercise(new Policy(8), leaf("skipEntireLeaf"));
    }

    @Test
    public void STOP_AT_DESCENDANT_HEAD_1() {
        exercise(new Policy(13), oneChild("stopDescendant"));
    }

    @Test
    public void STOP_AT_DESCENDANT_HEAD_2() {
        exercise(new Policy(0), wide("stopRootWide", 3));
    }

    @Test
    public void STOP_AT_LEAF_TAIL_1() {
        exercise(new Policy(5), branch("stopLeafTail"));
    }

    @Test
    public void STOP_AT_LEAF_TAIL_2() {
        exercise(new Policy(11), deep("stopDeepTail"));
    }

    @Test
    public void REMOVE_NONLAST_LEAF_1() {
        exercise(new Policy(9), mixed("removeNonlast"));
    }

    @Test
    public void REMOVE_NONLAST_LEAF_2() {
        exercise(new Policy(0), leaf("removeNonlastBoundary"));
    }

    @Test
    public void REMOVE_NONLAST_LEAF_3() {
        exercise(new Policy(2), oneChild("removeNonlastChild"));
    }

    @Test
    public void REMOVE_LAST_LEAF_ASCEND_1() {
        exercise(new Policy(9), wide("removeLastWide", 3));
    }

    @Test
    public void REMOVE_LAST_LEAF_ASCEND_2() {
        exercise(new Policy(11), branch("removeLastBranch"));
    }

    @Test
    public void REMOVE_LAST_LEAF_ASCEND_3() {
        exercise(new Policy(3), deep("removeLastLinear"));
    }

    @Test
    public void REMOVE_INTERNAL_SUBTREE_1() {
        exercise(new Policy(8), mixed("removeInternalMixed"));
    }

    @Test
    public void REMOVE_INTERNAL_SUBTREE_2() {
        exercise(new Policy(6), leaf("removeInternalLeaf"));
    }

    @Test
    public void REMOVE_FROM_LEAF_TAIL_1() {
        exercise(new Policy(11), oneChild("removeLeafTailOne"));
    }

    @Test
    public void REMOVE_FROM_LEAF_TAIL_2() {
        exercise(new Policy(4), wide("removeLeafTailWide", 3));
    }

    @Test
    public void REMOVE_FROM_LAST_TAIL_1() {
        exercise(new Policy(10), branch("removeLastTailBranch"));
    }

    @Test
    public void REMOVE_FROM_LAST_TAIL_2() {
        exercise(new Policy(6), deep("removeLastTailLinear"));
    }

    @Test
    public void TAIL_SKIP_CHILDREN_RESULT_1() {
        exercise(new Policy(12), mixed("tailSkipMixed"));
    }

    @Test
    public void TAIL_SKIP_CHILDREN_RESULT_2() {
        exercise(new Policy(2), leaf("tailSkipLeaf"));
    }

    @Test
    public void MIXED_DEPTH_POLICY_1() {
        exercise(new Policy(15), oneChild("mixedOne"));
    }

    @Test
    public void MIXED_DEPTH_POLICY_2() {
        exercise(new Policy(15), wide("mixedWide", 3));
    }

    @Test
    public void MIXED_DEPTH_POLICY_3() {
        exercise(new Policy(15), branch("mixedBranch"));
    }

    @Test
    public void FILTER_TERMINATES_AFTER_REMOVAL_1() {
        exercise(new Policy(14), deep("terminateAfterRemoval"));
    }

    @Test
    public void FILTER_TERMINATES_AFTER_REMOVAL_2() {
        exercise(new Policy(7), mixed("terminateAfterSkip"));
    }

    @Test
    public void DEEP_SINGLE_CHILD_BOUNDARY_1() {
        exercise(new Policy(1), leaf("singleBoundaryLeaf"));
    }

    @Test
    public void DEEP_SINGLE_CHILD_BOUNDARY_2() {
        exercise(new Policy(5), oneChild("singleBoundaryChild"));
    }

    @Test
    public void WIDE_SIBLING_RANGE_1() {
        exercise(new Policy(0), wide("wideStop", 4));
    }

    @Test
    public void WIDE_SIBLING_RANGE_2() {
        exercise(new Policy(4), branch("wideContinueBranch"));
    }
}
