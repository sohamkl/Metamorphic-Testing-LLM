import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    private static Element leaf(String name) {
        return new Element(name);
    }

    private static Element tree(String name, Node... children) {
        Element root = new Element(name);
        for (Node child : children) {
            root.appendChild(child);
        }
        return root;
    }

    private static Element chain(String prefix, int length) {
        Element root = new Element(prefix + "0");
        Element current = root;
        for (int i = 1; i < length; i++) {
            Element child = new Element(prefix + i);
            current.appendChild(child);
            current = child;
        }
        return root;
    }

    private static Element branch(String prefix) {
        return tree(prefix + "root",
                tree(prefix + "left", leaf(prefix + "leftLeaf")),
                tree(prefix + "middle", leaf(prefix + "middleLeaf")),
                leaf(prefix + "right"));
    }

    private static void checkClone(NodeFilter filter, Node root) {
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp =
                jsoupmt.NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(
                sourceOutput,
                followUpOutput,
                root.outerHtml(),
                ((Node) followUp[1]).outerHtml());
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            String sourceHtml,
            String followUpHtml) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Different terminal results: " + sourceOutput + " and " + followUpOutput);
        }
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError(
                    "Different final DOMs: " + sourceHtml + " and " + followUpHtml);
        }
    }

    @Test
    void LEAF_CONTINUE_TAIL_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy00(), leaf("root"));
    }

    @Test
    void LEAF_SKIP_CHILDREN_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy01(),
                tree("root", leaf("child")));
    }

    @Test
    void LEAF_SKIP_ENTIRELY_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy02(),
                tree("root", leaf("left"), leaf("right")));
    }

    @Test
    void LEAF_SKIP_ENTIRELY_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                chain("chain", 3));
    }

    @Test
    void LEAF_SKIP_ENTIRELY_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy04(),
                branch("skip"));
    }

    @Test
    void ROOT_HEAD_STOP_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy04(), leaf("stopLeaf"));
    }

    @Test
    void ROOT_HEAD_STOP_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy04(),
                tree("stopRoot", leaf("child")));
    }

    @Test
    void ROOT_HEAD_STOP_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy04(),
                tree("stopRoot", leaf("left"), leaf("right")));
    }

    @Test
    void ROOT_REMOVE_SENTINEL_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy03(),
                chain("remove", 3));
    }

    @Test
    void ROOT_REMOVE_SENTINEL_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy03(),
                branch("removeBranch"));
    }

    @Test
    void ROOT_REMOVE_SENTINEL_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy03(),
                tree("removeLeafRoot", leaf("child")));
    }

    @Test
    void ROOT_CONTINUE_SINGLE_CHILD_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy00(),
                tree("single", leaf("child")));
    }

    @Test
    void ROOT_CONTINUE_SINGLE_CHILD_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy00(),
                tree("two", leaf("first"), leaf("second")));
    }

    @Test
    void ROOT_CONTINUE_SINGLE_CHILD_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy16(1),
                chain("continue", 3));
    }

    @Test
    void ROOT_CONTINUE_SIBLING_ADVANCE_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy00(),
                branch("siblings"));
    }

    @Test
    void ROOT_CONTINUE_SIBLING_ADVANCE_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy00(), leaf("only"));
    }

    @Test
    void ROOT_CONTINUE_SIBLING_ADVANCE_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy00(),
                tree("siblings", leaf("one")));
    }

    @Test
    void INTERNAL_SKIP_CHILDREN_WITH_SIBLING_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy05(1),
                tree("root", tree("pruned", leaf("hidden")), leaf("survivor")));
    }

    @Test
    void INTERNAL_SKIP_CHILDREN_WITH_SIBLING_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy05(1),
                chain("prunedChain", 4));
    }

    @Test
    void INTERNAL_SKIP_CHILDREN_WITH_SIBLING_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy05(1),
                branch("pruneBranch"));
    }

    @Test
    void INTERNAL_SKIP_ENTIRELY_WITH_SIBLING_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy06(1),
                tree("root", tree("ignored", leaf("hidden")), leaf("survivor")));
    }

    @Test
    void INTERNAL_SKIP_ENTIRELY_WITH_SIBLING_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy06(1),
                chain("ignoredChain", 3));
    }

    @Test
    void INTERNAL_SKIP_ENTIRELY_WITH_SIBLING_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy06(2),
                branch("ignoredBranch"));
    }

    @Test
    void DESCENDENT_HEAD_STOP_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy08(1),
                chain("headStop", 3));
    }

    @Test
    void DESCENDENT_HEAD_STOP_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy08(1),
                branch("headStopBranch"));
    }

    @Test
    void DESCENDENT_HEAD_STOP_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy08(1),
                tree("headStopRoot", leaf("child")));
    }

    @Test
    void DESCENDENT_TAIL_STOP_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy16(1),
                tree("tailStop", leaf("child"), leaf("later")));
    }

    @Test
    void DESCENDENT_TAIL_STOP_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy16(1),
                tree("tailStop", leaf("left"), leaf("right")));
    }

    @Test
    void DESCENDENT_TAIL_STOP_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy16(2),
                chain("tailChain", 4));
    }

    @Test
    void REMOVE_FIRST_CHILD_WITH_SIBLING_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                tree("root", tree("removed", leaf("hidden")), leaf("survivor")));
    }

    @Test
    void REMOVE_FIRST_CHILD_WITH_SIBLING_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                tree("root", tree("removed", leaf("hidden")), leaf("middle"), leaf("last")));
    }

    @Test
    void REMOVE_FIRST_CHILD_WITH_SIBLING_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                branch("removeFirst"));
    }

    @Test
    void REMOVE_LAST_CHILD_ASCEND_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                tree("root", leaf("last")));
    }

    @Test
    void REMOVE_LAST_CHILD_ASCEND_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                chain("removeLast", 3));
    }

    @Test
    void REMOVE_LAST_CHILD_ASCEND_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                tree("root", tree("branch", leaf("last"))));
    }

    @Test
    void REMOVE_INTERNAL_SUBTREE_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                tree("root", tree("removed", leaf("deep")), leaf("survivor")));
    }

    @Test
    void REMOVE_INTERNAL_SUBTREE_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                tree("root", tree("removed", tree("deep", leaf("leaf"))), leaf("survivor")));
    }

    @Test
    void REMOVE_INTERNAL_SUBTREE_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                branch("removeSubtree"));
    }

    @Test
    void NESTED_CHAIN_ASCEND_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy00(),
                chain("ascending", 3));
    }

    @Test
    void NESTED_CHAIN_ASCEND_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy00(),
                chain("deepAscending", 5));
    }

    @Test
    void NESTED_CHAIN_ASCEND_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy16(3),
                chain("tailAscending", 4));
    }

    @Test
    void MIXED_PRUNE_REMOVE_SIBLING_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy05(1),
                tree("mixed", tree("prune", leaf("hidden")),
                        tree("remove", leaf("removed")), leaf("survivor")));
    }

    @Test
    void MIXED_PRUNE_REMOVE_SIBLING_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy06(1),
                tree("mixed", tree("first", leaf("hidden")),
                        tree("second", leaf("removed")), leaf("third")));
    }

    @Test
    void MIXED_PRUNE_REMOVE_SIBLING_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1),
                branch("mixedBranch"));
    }

    @Test
    void ROOT_SKIP_CHILDREN_WITH_CHILDREN_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy01(),
                tree("root", tree("hidden", leaf("deep")), leaf("sibling")));
    }

    @Test
    void ROOT_SKIP_CHILDREN_WITH_CHILDREN_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy01(),
                tree("root", leaf("first"), leaf("second")));
    }

    @Test
    void ROOT_SKIP_CHILDREN_WITH_CHILDREN_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy01(),
                branch("rootSkip"));
    }

    @Test
    void ROOT_CONTINUE_CHILD_STOP_TAIL_variation1() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy16(1),
                tree("root", tree("first", leaf("a")), leaf("later")));
    }

    @Test
    void ROOT_CONTINUE_CHILD_STOP_TAIL_variation2() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy16(2),
                chain("rootTail", 4));
    }

    @Test
    void ROOT_CONTINUE_CHILD_STOP_TAIL_variation3() {
        checkClone(new MtllmGeneratedNodeFilterCallback15ar4v1Policy16(1),
                branch("rootTailBranch"));
    }
}
