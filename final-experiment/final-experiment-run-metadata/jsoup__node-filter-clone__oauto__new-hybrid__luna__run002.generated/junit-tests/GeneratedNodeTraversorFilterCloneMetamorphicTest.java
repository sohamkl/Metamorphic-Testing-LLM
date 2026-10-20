import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicTest {
    private static final int CONTINUE = 0;
    private static final int SKIP_CHILDREN = 1;
    private static final int SKIP_ENTIRELY = 2;
    private static final int REMOVE = 3;
    private static final int STOP = 4;

    private static final class Policy implements NodeFilter {
        private final int mode;
        private final int threshold;

        Policy(int mode) {
            this(mode, 0);
        }

        Policy(int mode, int threshold) {
            this.mode = mode;
            this.threshold = threshold;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (mode) {
                case CONTINUE:
                    return FilterResult.CONTINUE;
                case SKIP_CHILDREN:
                    return FilterResult.SKIP_CHILDREN;
                case SKIP_ENTIRELY:
                    return FilterResult.SKIP_ENTIRELY;
                case REMOVE:
                    return FilterResult.REMOVE;
                case STOP:
                    return FilterResult.STOP;
                case 10:
                    return depth >= threshold
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 11:
                    return depth >= threshold
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 12:
                    return depth >= threshold
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 13:
                    return depth >= threshold
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 24:
                    if ("second".equals(node.nodeName()))
                        return FilterResult.SKIP_CHILDREN;
                    return FilterResult.CONTINUE;
                case 25:
                    if ("first".equals(node.nodeName()))
                        return FilterResult.SKIP_CHILDREN;
                    if (depth == 2)
                        return FilterResult.REMOVE;
                    return FilterResult.CONTINUE;
                case 26:
                    if ("first".equals(node.nodeName()))
                        return FilterResult.SKIP_CHILDREN;
                    if ("second".equals(node.nodeName()))
                        return FilterResult.SKIP_ENTIRELY;
                    return FilterResult.CONTINUE;
                case 28:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 29:
                    return "second".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 30:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 31:
                    return "second".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 35:
                    return depth >= threshold
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 36:
                    return depth >= threshold
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (mode) {
                case 20:
                    return depth == 0
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 21:
                    return FilterResult.SKIP_CHILDREN;
                case 22:
                    return depth == threshold
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 23:
                    return depth == threshold
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 24:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 32:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 33:
                    return "second".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 34:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    private static Object[] generateFollowUp(NodeFilter filter, Node root) {
        if (!(filter instanceof Policy)) {
            throw new IllegalArgumentException("Unsupported filter type");
        }
        Policy source = (Policy) filter;
        Node clonedRoot = root.clone();
        return new Object[]{new Policy(source.mode, source.threshold), clonedRoot};
    }

    private static Element leaf(String name) {
        return new Element(name);
    }

    private static Element chain() {
        Element root = new Element("root");
        Element child = root.appendElement("child");
        child.appendElement("grandchild");
        return root;
    }

    private static Element branch() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        return root;
    }

    private static Element wideBranch() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        root.appendElement("third");
        return root;
    }

    private static Element deepBranch() {
        Element root = new Element("root");
        Element first = root.appendElement("first");
        first.appendElement("first-leaf");
        Element second = root.appendElement("second");
        second.appendElement("second-middle").appendElement("second-leaf");
        root.appendElement("third").appendElement("third-leaf");
        return root;
    }

    @Test
    void SINGLE_LEAF_CONTINUE_1_constant() {
        NodeFilter filter = new Policy(CONTINUE);
        Node root = leaf("root");
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_LEAF_SKIP_CHILDREN_1_boundary() {
        NodeFilter filter = new Policy(SKIP_CHILDREN);
        Node root = leaf("root");
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_LEAF_SKIP_ENTIRELY_1_boundary() {
        NodeFilter filter = new Policy(SKIP_ENTIRELY);
        Node root = leaf("root");
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_LEAF_REMOVE_1_boundary() {
        NodeFilter filter = new Policy(REMOVE);
        Node root = leaf("root");
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_STOP_1_boundary() {
        NodeFilter filter = new Policy(STOP);
        Node root = branch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LINEAR_CONTINUE_DEPTH_1_depthBased() {
        NodeFilter filter = new Policy(CONTINUE, 2);
        Node root = chain();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BRANCHING_CONTINUE_ORDER_1_headTail() {
        NodeFilter filter = new Policy(CONTINUE);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_SKIP_CHILDREN_1_pruning() {
        NodeFilter filter = new Policy(SKIP_CHILDREN);
        Node root = branch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERNAL_SKIP_CHILDREN_1_depthBased() {
        NodeFilter filter = new Policy(10, 1);
        Node root = branch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_SKIP_ENTIRELY_1_pruning() {
        NodeFilter filter = new Policy(SKIP_ENTIRELY);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERNAL_SKIP_ENTIRELY_1_depthBased() {
        NodeFilter filter = new Policy(26);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DESCENDANT_STOP_1_depthBased() {
        NodeFilter filter = new Policy(13, 2);
        Node root = wideBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_STOP_AT_LEAF_1_depthBased() {
        NodeFilter filter = new Policy(22, 2);
        Node root = chain();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_SKIP_CHILDREN_RESULT_1_boundary() {
        NodeFilter filter = new Policy(21);
        Node root = branch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_TAIL_SKIP_ENTIRELY_1_boundary() {
        NodeFilter filter = new Policy(20);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_REMOVE_NONLAST_LEAF_1_eventPosition() {
        NodeFilter filter = new Policy(28);
        Node root = branch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_REMOVE_LAST_LEAF_1_eventPosition() {
        NodeFilter filter = new Policy(29);
        Node root = branch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_REMOVE_NONLAST_SUBTREE_1_eventPosition() {
        NodeFilter filter = new Policy(30);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_REMOVE_LAST_SUBTREE_1_eventPosition() {
        NodeFilter filter = new Policy(31);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_REMOVE_NONLAST_LEAF_1_eventPosition() {
        NodeFilter filter = new Policy(32);
        Node root = branch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_REMOVE_LAST_LEAF_1_eventPosition() {
        NodeFilter filter = new Policy(33);
        Node root = branch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_REMOVE_INTERNAL_1_eventPosition() {
        NodeFilter filter = new Policy(34);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_HEAD_RESULTS_BY_DEPTH_1_mixedDepth() {
        NodeFilter filter = new Policy(25);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_HEAD_AND_TAIL_RESULTS_1_eventPosition() {
        NodeFilter filter = new Policy(24);
        Node root = wideBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COMPLETE_TRAVERSAL_WITH_TERMINAL_TAIL_RESULT_1_rootTail() {
        NodeFilter filter = new Policy(20);
        Node root = wideBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLONE_EQUIVALENCE_AFTER_REMOVALS_1_depthBased() {
        NodeFilter filter = new Policy(35, 2);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLONE_EQUIVALENCE_AFTER_STOP_1_depthBased() {
        NodeFilter filter = new Policy(36, 2);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLONE_EQUIVALENCE_WITH_PRUNING_1_eventPosition() {
        NodeFilter filter = new Policy(26);
        Node root = deepBranch();
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
