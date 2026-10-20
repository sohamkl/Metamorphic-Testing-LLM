import org.junit.jupiter.api.Test;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicTest {

    private static Element element(String name) {
        return new Element(name, "");
    }

    private static Element leaf(String name) {
        return element(name);
    }

    private static Element singleChildRoot() {
        Element root = element("root");
        root.appendChild(leaf("child"));
        return root;
    }

    private static Element chainRoot() {
        Element root = element("root");
        Element one = element("one");
        Element two = element("two");
        Element three = element("three");
        root.appendChild(one);
        one.appendChild(two);
        two.appendChild(three);
        return root;
    }

    private static Element branchingRoot() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(leaf("nested"));
        root.appendChild(first);
        root.appendChild(leaf("second"));
        return root;
    }

    private static Element wideRoot() {
        Element root = element("root");
        root.appendChild(leaf("first"));
        root.appendChild(leaf("middle"));
        root.appendChild(leaf("last"));
        return root;
    }

    private static Element mixedRoot() {
        Element root = element("root");
        root.appendChild(element("element-child"));
        root.appendChild(new TextNode("text"));
        root.appendChild(new DataNode("data"));
        return root;
    }

    private static Element selectiveRoot() {
        Element root = element("root");
        Element branch = element("branch");
        branch.appendChild(leaf("nested"));
        root.appendChild(branch);
        root.appendChild(leaf("removable"));
        root.appendChild(leaf("later"));
        return root;
    }

    private static Element removableBranchRoot() {
        Element root = element("root");
        Element branch = element("branch");
        branch.appendChild(leaf("nested"));
        root.appendChild(branch);
        root.appendChild(leaf("later"));
        return root;
    }

    private static Policy allContinue() {
        return new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                -1, -1, NodeFilter.FilterResult.CONTINUE);
    }

    private static Object[] generateFollowUp(NodeFilter filter, Node root) {
        return NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
    }

    private static void run(Node root, NodeFilter filter) {
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAF_ROOT_CONTINUES_variation1() {
        run(new DataNode("leaf-data"), allContinue());
    }

    @Test
    public void SINGLE_CHILD_CONTINUE_variation1() {
        run(singleChildRoot(), allContinue());
    }

    @Test
    public void DEEP_CHAIN_REPEATED_ASCENT_variation1() {
        run(chainRoot(), allContinue());
    }

    @Test
    public void BRANCHING_CONTINUE_ORDER_variation1() {
        run(branchingRoot(), allContinue());
    }

    @Test
    public void WIDE_SIBLING_CONTINUE_variation1() {
        run(wideRoot(), allContinue());
    }

    @Test
    public void MIXED_NODE_KINDS_CONTINUE_variation1() {
        run(mixedRoot(), allContinue());
    }

    @Test
    public void HEAD_STOP_AT_ROOT_variation1() {
        run(branchingRoot(), new Policy(0, 0, NodeFilter.FilterResult.STOP,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_STOP_AT_DEEP_CHILD_variation1() {
        run(chainRoot(), new Policy(2, 0, NodeFilter.FilterResult.STOP,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_STOP_AT_LATER_SIBLING_variation1() {
        run(branchingRoot(), new Policy(1, 1, NodeFilter.FilterResult.STOP,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_ROOT_variation1() {
        run(branchingRoot(), new Policy(0, 0, NodeFilter.FilterResult.SKIP_CHILDREN,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_INTERNAL_NODE_variation1() {
        run(branchingRoot(), new Policy(1, 0, NodeFilter.FilterResult.SKIP_CHILDREN,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_LEAF_variation1() {
        run(singleChildRoot(), new Policy(1, 0, NodeFilter.FilterResult.SKIP_CHILDREN,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_ROOT_variation1() {
        run(branchingRoot(), new Policy(0, 0, NodeFilter.FilterResult.SKIP_ENTIRELY,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_FIRST_CHILD_variation1() {
        run(branchingRoot(), new Policy(1, 0, NodeFilter.FilterResult.SKIP_ENTIRELY,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_MIDDLE_SIBLING_variation1() {
        run(wideRoot(), new Policy(1, 1, NodeFilter.FilterResult.SKIP_ENTIRELY,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_REMOVE_DETACHED_ROOT_variation1() {
        run(branchingRoot(), new Policy(0, 0, NodeFilter.FilterResult.REMOVE,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_REMOVE_FIRST_LEAF_CHILD_variation1() {
        run(wideRoot(), new Policy(1, 0, NodeFilter.FilterResult.REMOVE,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_REMOVE_MIDDLE_CHILD_variation1() {
        run(wideRoot(), new Policy(1, 1, NodeFilter.FilterResult.REMOVE,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_REMOVE_LAST_CHILD_variation1() {
        run(wideRoot(), new Policy(1, 2, NodeFilter.FilterResult.REMOVE,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_REMOVE_NONLEAF_SUBTREE_variation1() {
        run(removableBranchRoot(), new Policy(1, 0, NodeFilter.FilterResult.REMOVE,
                -1, -1, NodeFilter.FilterResult.CONTINUE));
    }

    @Test
    public void TAIL_STOP_AT_ROOT_variation1() {
        run(singleChildRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                0, 0, NodeFilter.FilterResult.STOP));
    }

    @Test
    public void TAIL_STOP_AT_ONLY_CHILD_variation1() {
        run(singleChildRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                1, 0, NodeFilter.FilterResult.STOP));
    }

    @Test
    public void TAIL_STOP_BEFORE_NEXT_SIBLING_variation1() {
        run(wideRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                1, 0, NodeFilter.FilterResult.STOP));
    }

    @Test
    public void TAIL_REMOVE_FIRST_CHILD_variation1() {
        run(wideRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                1, 0, NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void TAIL_REMOVE_LAST_CHILD_variation1() {
        run(wideRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                1, 2, NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void TAIL_REMOVE_INTERNAL_PARENT_variation1() {
        run(removableBranchRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                1, 0, NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void TAIL_REMOVE_DETACHED_ROOT_variation1() {
        run(element("root"), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                0, 0, NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void TAIL_SKIP_CHILDREN_AT_ROOT_variation1() {
        run(branchingRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                0, 0, NodeFilter.FilterResult.SKIP_CHILDREN));
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_AT_ROOT_variation1() {
        run(branchingRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                0, 0, NodeFilter.FilterResult.SKIP_ENTIRELY));
    }

    @Test
    public void TAIL_SKIP_CHILDREN_WITH_NEXT_SIBLING_variation1() {
        run(wideRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                1, 0, NodeFilter.FilterResult.SKIP_CHILDREN));
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_WITH_ASCENT_variation1() {
        run(singleChildRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                1, 0, NodeFilter.FilterResult.SKIP_ENTIRELY));
    }

    @Test
    public void SELECTIVE_SKIP_AND_REMOVE_variation1() {
        run(selectiveRoot(), new DualHeadPolicy());
    }

    @Test
    public void SELECTIVE_TAIL_REMOVE_AFTER_DESCENT_variation1() {
        run(removableBranchRoot(), new Policy(-1, -1, NodeFilter.FilterResult.CONTINUE,
                1, 0, NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void EMPTY_ELEMENT_ROOT_variation1() {
        run(element("empty"), allContinue());
    }

    @Test
    public void NON_ELEMENT_LEAF_ROOT_variation1() {
        run(new TextNode("text"), allContinue());
    }

    @Test
    public void DOCUMENT_LIKE_DETACHED_ROOT_variation1() {
        Document document = new Document("", "");
        document.appendChild(element("document-child"));
        run(document, allContinue());
    }

    @Test
    public void TYPE_SELECTIVE_POLICY_variation1() {
        run(mixedRoot(), new RemoveDataNodePolicy());
    }

    public static final class Policy implements NodeFilter {
        private final int headDepth;
        private final int headIndex;
        private final FilterResult headResult;
        private final int tailDepth;
        private final int tailIndex;
        private final FilterResult tailResult;

        public Policy(int headDepth, int headIndex, FilterResult headResult,
                      int tailDepth, int tailIndex, FilterResult tailResult) {
            this.headDepth = headDepth;
            this.headIndex = headIndex;
            this.headResult = headResult;
            this.tailDepth = tailDepth;
            this.tailIndex = tailIndex;
            this.tailResult = tailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return matches(node, depth, headDepth, headIndex) ? headResult : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return matches(node, depth, tailDepth, tailIndex) ? tailResult : FilterResult.CONTINUE;
        }

        private static boolean matches(Node node, int depth, int targetDepth, int targetIndex) {
            return targetDepth >= 0
                    && depth == targetDepth
                    && (targetIndex < 0 || node.siblingIndex() == targetIndex);
        }
    }

    public static final class DualHeadPolicy implements NodeFilter {
        public DualHeadPolicy() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (depth == 1 && node.siblingIndex() == 0) {
                return FilterResult.SKIP_CHILDREN;
            }
            if (depth == 1 && node.siblingIndex() == 1) {
                return FilterResult.REMOVE;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    public static final class RemoveDataNodePolicy implements NodeFilter {
        public RemoveDataNodePolicy() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return depth > 0 && node instanceof DataNode
                    ? FilterResult.REMOVE
                    : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }
}
