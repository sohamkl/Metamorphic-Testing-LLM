import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final class Policy implements NodeFilter {
        private final String specialNodeName;
        private final FilterResult specialHead;
        private final FilterResult specialTail;
        private final FilterResult defaultHead;
        private final FilterResult defaultTail;

        private Policy(
                String specialNodeName,
                FilterResult specialHead,
                FilterResult specialTail,
                FilterResult defaultHead,
                FilterResult defaultTail) {
            this.specialNodeName = specialNodeName;
            this.specialHead = specialHead;
            this.specialTail = specialTail;
            this.defaultHead = defaultHead;
            this.defaultTail = defaultTail;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return node.nodeName().equals(specialNodeName) ? specialHead : defaultHead;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return node.nodeName().equals(specialNodeName) ? specialTail : defaultTail;
        }
    }

    private static void run(Node root, NodeFilter filter) {
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0],
                (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAF_CONTINUE_CONTINUE_leafContinue() {
        Element root = new Element("leaf");
        run(root, new Policy("never", FilterResult.CONTINUE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void LEAF_HEAD_STOP_immediateStop() {
        Element root = new Element("leaf");
        run(root, new Policy("leaf", FilterResult.STOP, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void LEAF_TAIL_STOP_tailStop() {
        Element root = new Element("leaf");
        run(root, new Policy("leaf", FilterResult.CONTINUE, FilterResult.STOP,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void LEAF_HEAD_SKIP_CHILDREN_leafSkipChildren() {
        Element root = new Element("leaf");
        run(root, new Policy("leaf", FilterResult.SKIP_CHILDREN, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void LEAF_HEAD_SKIP_CHILDREN_TAIL_STOP_skipThenStop() {
        Element root = new Element("leaf");
        run(root, new Policy("leaf", FilterResult.SKIP_CHILDREN, FilterResult.STOP,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void LEAF_HEAD_SKIP_ENTIRELY_leafSkipped() {
        Element root = new Element("leaf");
        run(root, new Policy("leaf", FilterResult.SKIP_ENTIRELY, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void LEAF_HEAD_REMOVE_ROOT_detachedRemoval() {
        Element root = new Element("leaf");
        run(root, new Policy("leaf", FilterResult.REMOVE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void LEAF_TAIL_SKIP_CHILDREN_tailSkipChildren() {
        Element root = new Element("leaf");
        run(root, new Policy("leaf", FilterResult.CONTINUE, FilterResult.SKIP_CHILDREN,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void LEAF_TAIL_SKIP_ENTIRELY_tailSkipEntirely() {
        Element root = new Element("leaf");
        run(root, new Policy("leaf", FilterResult.CONTINUE, FilterResult.SKIP_ENTIRELY,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void LEAF_TAIL_REMOVE_ROOT_detachedTailRemoval() {
        Element root = new Element("leaf");
        run(root, new Policy("leaf", FilterResult.CONTINUE, FilterResult.REMOVE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void SINGLE_CHILD_DEPTH_TRANSITION_oneLeafChild() {
        Element root = new Element("root");
        root.appendElement("child");
        run(root, new Policy("never", FilterResult.CONTINUE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void TWO_LEAF_SIBLINGS_twoChildren() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        run(root, new Policy("never", FilterResult.CONTINUE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void BRANCHING_DEPTH_FIRST_CONTINUE_branchingTree() {
        Element root = new Element("root");
        Element branch = root.appendElement("branch");
        branch.appendElement("grandchild");
        root.appendElement("sibling");
        run(root, new Policy("never", FilterResult.CONTINUE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void DEEP_CHAIN_ASCENT_fourNodeChain() {
        Element root = new Element("root");
        Element first = root.appendElement("one");
        Element second = first.appendElement("two");
        second.appendElement("three");
        run(root, new Policy("never", FilterResult.CONTINUE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_NONLEAF_rootPrunesDescendants() {
        Element root = new Element("root");
        root.appendElement("child").appendElement("grandchild");
        run(root, new Policy("root", FilterResult.SKIP_CHILDREN, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_TAIL_REMOVE_rootTailRemoval() {
        Element root = new Element("root");
        root.appendElement("child");
        run(root, new Policy("root", FilterResult.SKIP_CHILDREN, FilterResult.REMOVE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_NONLEAF_rootSkipped() {
        Element root = new Element("root");
        root.appendElement("child").appendElement("grandchild");
        run(root, new Policy("root", FilterResult.SKIP_ENTIRELY, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void ROOT_HEAD_REMOVE_NONLEAF_rootHeadRemoval() {
        Element root = new Element("root");
        root.appendElement("child").appendElement("grandchild");
        run(root, new Policy("root", FilterResult.REMOVE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_STOP_AT_DESCENDANT_childStops() {
        Element root = new Element("root");
        root.appendElement("child");
        run(root, new Policy("child", FilterResult.STOP, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void TAIL_STOP_WITH_FOLLOWING_SIBLING_firstChildTailStops() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        run(root, new Policy("first", FilterResult.CONTINUE, FilterResult.STOP,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void TAIL_STOP_ON_LAST_CHILD_onlyChildTailStops() {
        Element root = new Element("root");
        root.appendElement("child");
        run(root, new Policy("child", FilterResult.CONTINUE, FilterResult.STOP,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void CHILD_SKIP_CHILDREN_WITH_SIBLING_internalChildPruned() {
        Element root = new Element("root");
        root.appendElement("first").appendElement("hidden");
        root.appendElement("second");
        run(root, new Policy("first", FilterResult.SKIP_CHILDREN, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void CHILD_SKIP_ENTIRELY_WITH_SIBLING_internalChildSkipped() {
        Element root = new Element("root");
        root.appendElement("first").appendElement("hidden");
        root.appendElement("second");
        run(root, new Policy("first", FilterResult.SKIP_ENTIRELY, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void CHILD_TAIL_SKIP_CHILDREN_WITH_SIBLING_tailSkipsChildrenResult() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        run(root, new Policy("first", FilterResult.CONTINUE, FilterResult.SKIP_CHILDREN,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void CHILD_TAIL_SKIP_ENTIRELY_WITH_SIBLING_tailSkipsEntirelyResult() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        run(root, new Policy("first", FilterResult.CONTINUE, FilterResult.SKIP_ENTIRELY,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_REMOVE_FIRST_CHILD_firstSiblingRemoved() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        run(root, new Policy("first", FilterResult.REMOVE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_REMOVE_LAST_CHILD_lastSiblingRemoved() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        run(root, new Policy("second", FilterResult.REMOVE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_REMOVE_ONLY_CHILD_onlyChildRemoved() {
        Element root = new Element("root");
        root.appendElement("child");
        run(root, new Policy("child", FilterResult.REMOVE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void HEAD_REMOVE_INTERNAL_SUBTREE_internalSubtreeRemoved() {
        Element root = new Element("root");
        root.appendElement("first").appendElement("grandchild");
        root.appendElement("second");
        run(root, new Policy("first", FilterResult.REMOVE, FilterResult.CONTINUE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void TAIL_REMOVE_FIRST_CHILD_firstSiblingTailRemoval() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        run(root, new Policy("first", FilterResult.CONTINUE, FilterResult.REMOVE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void TAIL_REMOVE_LAST_CHILD_lastSiblingTailRemoval() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        run(root, new Policy("second", FilterResult.CONTINUE, FilterResult.REMOVE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void TAIL_REMOVE_ONLY_CHILD_onlyChildTailRemoval() {
        Element root = new Element("root");
        root.appendElement("child");
        run(root, new Policy("child", FilterResult.CONTINUE, FilterResult.REMOVE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }

    @Test
    public void TAIL_REMOVE_INTERNAL_SUBTREE_completedSubtreeRemoved() {
        Element root = new Element("root");
        root.appendElement("first").appendElement("grandchild");
        root.appendElement("second");
        run(root, new Policy("first", FilterResult.CONTINUE, FilterResult.REMOVE,
                FilterResult.CONTINUE, FilterResult.CONTINUE));
    }
}
