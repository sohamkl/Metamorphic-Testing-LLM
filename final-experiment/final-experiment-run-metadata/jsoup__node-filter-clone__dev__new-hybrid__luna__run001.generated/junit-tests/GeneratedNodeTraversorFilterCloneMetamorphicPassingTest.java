import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    public static final class Policy implements NodeFilter {
        private final int caseId;

        public Policy(int caseId) {
            this.caseId = caseId;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (depth == 0) {
                switch (caseId) {
                    case 1:
                    case 6:
                    case 16:
                    case 31:
                    case 36:
                    case 41:
                    case 46:
                        return FilterResult.STOP;
                    case 3:
                    case 8:
                    case 17:
                    case 18:
                    case 38:
                    case 43:
                        return FilterResult.SKIP_CHILDREN;
                    case 4:
                    case 9:
                    case 14:
                    case 19:
                    case 34:
                    case 39:
                    case 44:
                        return FilterResult.SKIP_ENTIRELY;
                    case 10:
                        return FilterResult.REMOVE;
                    default:
                        return FilterResult.CONTINUE;
                }
            }

            int sibling = node.siblingIndex();

            switch (caseId) {
                case 19:
                case 20:
                case 21:
                    return FilterResult.SKIP_ENTIRELY;
                case 23:
                case 24:
                    return FilterResult.STOP;
                case 25:
                case 26:
                    return FilterResult.SKIP_CHILDREN;
                case 29:
                case 30:
                case 31:
                case 32:
                case 47:
                case 48:
                    return FilterResult.REMOVE;
                case 37:
                case 38:
                    if (sibling == 0)
                        return FilterResult.SKIP_CHILDREN;
                    if (sibling == 1)
                        return FilterResult.REMOVE;
                    return FilterResult.CONTINUE;
                case 39:
                    return depth >= 2 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 40:
                    return depth >= 2 ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            if (depth == 0) {
                switch (caseId) {
                    case 41:
                        return FilterResult.SKIP_CHILDREN;
                    case 43:
                        return FilterResult.REMOVE;
                    default:
                        return FilterResult.CONTINUE;
                }
            }

            switch (caseId) {
                case 7:
                case 27:
                case 28:
                    return FilterResult.STOP;
                case 12:
                case 33:
                case 34:
                case 35:
                case 36:
                    return FilterResult.REMOVE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    private static Node root(int shape) {
        Element root = new Element("root");

        switch (shape) {
            case 1:
                root.appendElement("child");
                break;
            case 2:
                root.appendElement("a");
                root.appendElement("b");
                root.appendElement("c");
                break;
            case 3:
                Element a = root.appendElement("a");
                Element b = a.appendElement("b");
                b.appendElement("c");
                break;
            case 4:
                Element first = root.appendElement("a");
                first.appendElement("aa");
                Element second = root.appendElement("b");
                second.appendElement("ba");
                root.appendElement("c");
                break;
            default:
                break;
        }

        return root;
    }

    private static void run(int caseId, int shape) {
        Node source = root(shape);
        NodeFilter sourceFilter = new Policy(caseId);

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, source);

        Object[] followUp =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(
                        sourceFilter, source);

        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(
                        (NodeFilter) followUp[0],
                        (Node) followUp[1]);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_HEAD_STOP_variation1() {
        run(1, 0);
    }

    @Test
    void ROOT_HEAD_STOP_variation2() {
        run(2, 1);
    }

    @Test
    void ROOT_HEAD_SKIP_ENTIRE_variation1() {
        run(3, 2);
    }

    @Test
    void ROOT_HEAD_SKIP_ENTIRE_variation2() {
        run(4, 3);
    }

    @Test
    void ROOT_HEAD_CONTINUE_LEAF_variation1() {
        run(5, 4);
    }

    @Test
    void ROOT_HEAD_CONTINUE_LEAF_variation2() {
        run(6, 0);
    }

    @Test
    void ROOT_HEAD_SKIP_CHILDREN_LEAF_variation1() {
        run(7, 1);
    }

    @Test
    void ROOT_HEAD_SKIP_CHILDREN_LEAF_variation2() {
        run(8, 2);
    }

    @Test
    void ROOT_HEAD_REMOVE_WITH_DESCENDANTS_variation1() {
        run(9, 3);
    }

    @Test
    void ROOT_HEAD_REMOVE_WITH_DESCENDANTS_variation2() {
        run(10, 4);
    }

    @Test
    void ROOT_CONTINUE_ONE_CHILD_variation1() {
        run(11, 0);
    }

    @Test
    void ROOT_CONTINUE_ONE_CHILD_variation2() {
        run(12, 1);
    }

    @Test
    void ROOT_CONTINUE_MULTIPLE_SIBLINGS_variation1() {
        run(13, 2);
    }

    @Test
    void ROOT_CONTINUE_MULTIPLE_SIBLINGS_variation2() {
        run(14, 3);
    }

    @Test
    void DEEP_CONTINUE_CHAIN_variation1() {
        run(15, 4);
    }

    @Test
    void DEEP_CONTINUE_CHAIN_variation2() {
        run(16, 0);
    }

    @Test
    void ROOT_SKIP_CHILDREN_PRUNES_DIRECT_CHILDREN_variation1() {
        run(17, 1);
    }

    @Test
    void ROOT_SKIP_CHILDREN_PRUNES_DIRECT_CHILDREN_variation2() {
        run(18, 2);
    }

    @Test
    void CHILD_SKIP_ENTIRE_WITH_FOLLOWING_SIBLING_variation1() {
        run(19, 3);
    }

    @Test
    void CHILD_SKIP_ENTIRE_WITH_FOLLOWING_SIBLING_variation2() {
        run(20, 4);
    }

    @Test
    void CHILD_SKIP_ENTIRE_LAST_SIBLING_variation1() {
        run(21, 0);
    }

    @Test
    void CHILD_SKIP_ENTIRE_LAST_SIBLING_variation2() {
        run(22, 1);
    }

    @Test
    void CHILD_STOP_variation1() {
        run(23, 2);
    }

    @Test
    void CHILD_STOP_variation2() {
        run(24, 3);
    }

    @Test
    void CHILD_SKIP_CHILDREN_TAIL_CONTINUE_variation1() {
        run(25, 4);
    }

    @Test
    void CHILD_SKIP_CHILDREN_TAIL_CONTINUE_variation2() {
        run(26, 0);
    }

    @Test
    void CHILD_TAIL_STOP_variation1() {
        run(27, 1);
    }

    @Test
    void CHILD_TAIL_STOP_variation2() {
        run(28, 2);
    }

    @Test
    void CHILD_REMOVE_WITH_FOLLOWING_SIBLING_variation1() {
        run(29, 3);
    }

    @Test
    void CHILD_REMOVE_WITH_FOLLOWING_SIBLING_variation2() {
        run(30, 4);
    }

    @Test
    void CHILD_REMOVE_LAST_SIBLING_variation1() {
        run(31, 0);
    }

    @Test
    void CHILD_REMOVE_LAST_SIBLING_variation2() {
        run(32, 1);
    }

    @Test
    void CHILD_TAIL_REMOVE_WITH_FOLLOWING_SIBLING_variation1() {
        run(33, 2);
    }

    @Test
    void CHILD_TAIL_REMOVE_WITH_FOLLOWING_SIBLING_variation2() {
        run(34, 3);
    }

    @Test
    void CHILD_TAIL_REMOVE_LAST_variation1() {
        run(35, 4);
    }

    @Test
    void CHILD_TAIL_REMOVE_LAST_variation2() {
        run(36, 0);
    }

    @Test
    void MIXED_PRUNE_AND_REMOVE_variation1() {
        run(37, 1);
    }

    @Test
    void MIXED_PRUNE_AND_REMOVE_variation2() {
        run(38, 2);
    }

    @Test
    void NESTED_STOP_AFTER_DESCENT_variation1() {
        run(39, 3);
    }

    @Test
    void NESTED_STOP_AFTER_DESCENT_variation2() {
        run(40, 4);
    }

    @Test
    void TERMINAL_ROOT_TAIL_SKIP_CHILDREN_variation1() {
        run(41, 0);
    }

    @Test
    void TERMINAL_ROOT_TAIL_SKIP_CHILDREN_variation2() {
        run(42, 1);
    }

    @Test
    void TERMINAL_ROOT_TAIL_REMOVE_variation1() {
        run(43, 2);
    }

    @Test
    void TERMINAL_ROOT_TAIL_REMOVE_variation2() {
        run(44, 3);
    }

    @Test
    void BRANCHED_DEPTH_AND_SIBLING_BOUNDARY_variation1() {
        run(45, 4);
    }

    @Test
    void BRANCHED_DEPTH_AND_SIBLING_BOUNDARY_variation2() {
        run(46, 0);
    }

    @Test
    void CLONE_STABLE_STRUCTURAL_MUTATION_variation1() {
        run(47, 1);
    }

    @Test
    void CLONE_STABLE_STRUCTURAL_MUTATION_variation2() {
        run(48, 2);
    }
}
