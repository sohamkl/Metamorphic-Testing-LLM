import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeFilter.FilterResult;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
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

    @Test
    public void SINGLE_LEAF_CONTINUE_basic() {
        Node root = element("leaf");
        verify(new Policy(0), root);
    }

    @Test
    public void LEAF_ROOT_STOP_AT_HEAD_basic() {
        Node root = element("stop-root");
        verify(new Policy(1), root);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_basic() {
        Element root = element("root-skip");
        root.appendChild(element("child-a"));
        root.appendChild(element("child-b"));
        verify(new Policy(2), root);
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_basic() {
        Element root = element("root-entire");
        Element child = element("child");
        child.appendChild(element("grandchild"));
        root.appendChild(child);
        verify(new Policy(3), root);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_TERMINAL_basic() {
        Element root = element("root-terminal");
        Element branch = element("branch");
        branch.appendChild(element("leaf"));
        root.appendChild(branch);
        root.appendChild(element("sibling"));
        verify(new Policy(4), root);
    }

    @Test
    public void ROOT_TAIL_REMOVE_basic() {
        Element root = element("root-remove-tail");
        root.appendChild(element("child"));
        verify(new Policy(5), root);
    }

    @Test
    public void FULL_DEPTH_FIRST_CONTINUE_basic() {
        Element root = element("full-root");
        Element first = element("first");
        first.appendChild(element("grandchild"));
        root.appendChild(first);
        root.appendChild(element("second"));
        verify(new Policy(0), root);
    }

    @Test
    public void DEEP_CHAIN_MULTI_LEVEL_ASCENT_basic() {
        Element root = element("chain-0");
        Element one = element("chain-1");
        Element two = element("chain-2");
        Element three = element("chain-3");
        Element four = element("chain-4");
        root.appendChild(one);
        one.appendChild(two);
        two.appendChild(three);
        three.appendChild(four);
        verify(new Policy(0), root);
    }

    @Test
    public void WIDE_SIBLING_CONTINUE_basic() {
        Element root = element("wide-root");
        root.appendChild(element("wide-a"));
        root.appendChild(element("wide-b"));
        root.appendChild(element("wide-c"));
        root.appendChild(element("wide-d"));
        verify(new Policy(0), root);
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_WITH_SIBLING_basic() {
        Element root = element("skip-child-root");
        Element first = element("first");
        first.appendChild(element("hidden"));
        root.appendChild(first);
        root.appendChild(element("later"));
        verify(new Policy(6), root);
    }

    @Test
    public void INTERNAL_SKIP_ENTIRELY_WITH_SIBLING_basic() {
        Element root = element("skip-entire-root");
        Element first = element("first");
        first.appendChild(element("hidden"));
        root.appendChild(first);
        root.appendChild(element("later"));
        verify(new Policy(7), root);
    }

    @Test
    public void ROOT_CONTINUE_CHILD_STOP_basic() {
        Element root = element("continue-root");
        root.appendChild(element("stop-child"));
        root.appendChild(element("unreached"));
        verify(new Policy(8), root);
    }

    @Test
    public void CHILD_TAIL_STOP_basic() {
        Element root = element("child-tail-root");
        root.appendChild(element("tail-stop"));
        verify(new Policy(9), root);
    }

    @Test
    public void INTERNAL_TAIL_STOP_basic() {
        Element root = element("internal-tail-root");
        Element inner = element("inner");
        inner.appendChild(element("descendant"));
        root.appendChild(inner);
        verify(new Policy(11), root);
    }

    @Test
    public void REMOVE_LEAF_WITH_FOLLOWING_SIBLING_basic() {
        Element root = element("remove-leaf-root");
        root.appendChild(element("first"));
        root.appendChild(element("second"));
        root.appendChild(element("third"));
        verify(new Policy(9), root);
    }

    @Test
    public void REMOVE_ONLY_CHILD_basic() {
        Element root = element("remove-only-root");
        root.appendChild(element("sole"));
        verify(new Policy(10), root);
    }

    @Test
    public void REMOVE_INTERNAL_SUBTREE_basic() {
        Element root = element("remove-subtree-root");
        Element first = element("first");
        first.appendChild(element("nested"));
        root.appendChild(first);
        root.appendChild(element("retained"));
        verify(new Policy(11), root);
    }

    @Test
    public void REMOVE_ROOT_basic() {
        Element root = element("remove-root");
        root.appendChild(element("descendant"));
        verify(new Policy(12), root);
    }

    @Test
    public void STOP_AFTER_SIBLING_TRANSITION_basic() {
        Element root = element("sibling-stop-root");
        root.appendChild(element("first"));
        root.appendChild(element("second"));
        root.appendChild(element("third"));
        verify(new Policy(13), root);
    }

    @Test
    public void MIXED_PRUNE_AND_REMOVE_basic() {
        Element root = element("mixed-root");
        Element first = element("first");
        first.appendChild(element("first-descendant"));
        root.appendChild(first);
        root.appendChild(element("second"));
        verify(new Policy(14), root);
    }

    @Test
    public void DEPTH_BASED_PRUNE_basic() {
        Element root = element("depth-root");
        Element level1 = element("level-one");
        Element level2 = element("level-two");
        level2.appendChild(element("level-three"));
        level1.appendChild(level2);
        root.appendChild(level1);
        root.appendChild(element("side"));
        verify(new Policy(15), root);
    }

    @Test
    public void MULTIPLE_LEAF_REMOVALS_basic() {
        Element root = element("multiple-remove-root");
        Element left = element("left");
        left.appendChild(element("remove-a"));
        left.appendChild(element("keep-a"));
        Element right = element("right");
        right.appendChild(element("remove-b"));
        right.appendChild(element("keep-b"));
        root.appendChild(left);
        root.appendChild(right);
        verify(new Policy(16), root);
    }

    @Test
    public void HETEROGENEOUS_NODE_TREE_basic() {
        Element root = element("heterogeneous-root");
        Element branch = element("branch");
        branch.appendChild(new TextNode("text-content"));
        branch.appendChild(element("element-child"));
        root.appendChild(branch);
        root.appendChild(new TextNode("sibling-text"));
        verify(new Policy(0), root);
    }
}
