import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Tag;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    private static Element element(String name) {
        return new Element(Tag.valueOf(name), "");
    }

    private static Element child(Element parent, String name) {
        Element child = element(name);
        parent.appendChild(child);
        return child;
    }

    private static Element head(Element node, NodeFilter.FilterResult result) {
        node.attr("data-head-result", result.name());
        return node;
    }

    private static Element tail(Element node, NodeFilter.FilterResult result) {
        node.attr("data-tail-result", result.name());
        return node;
    }

    private static final class MarkupPolicy implements NodeFilter {
        @Override
        public FilterResult head(Node node, int depth) {
            if (node instanceof Element) {
                String configured = ((Element) node).attr("data-head-result");
                if (!configured.isEmpty()) {
                    return FilterResult.valueOf(configured);
                }
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            if (node instanceof Element) {
                String configured = ((Element) node).attr("data-tail-result");
                if (!configured.isEmpty()) {
                    return FilterResult.valueOf(configured);
                }
            }
            return FilterResult.CONTINUE;
        }
    }

    private static final class DepthTwoPruningPolicy implements NodeFilter {
        @Override
        public FilterResult head(Node node, int depth) {
            return depth == 2 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class ScheduledPolicy implements NodeFilter {
        private int headCount;

        @Override
        public FilterResult head(Node node, int depth) {
            headCount++;
            if (headCount == 2) {
                return FilterResult.SKIP_CHILDREN;
            }
            if (headCount == 3) {
                return FilterResult.REMOVE;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    @Test
    public void SINGLETON_CONTINUE_variation1_detachedLeaf() {
        NodeFilter filter = new MarkupPolicy();
        Node root = element("singleton");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_variation1_nonLeafRoot() {
        NodeFilter filter = new MarkupPolicy();
        Element root = head(element("root"), NodeFilter.FilterResult.SKIP_CHILDREN);
        child(root, "unvisited");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_variation1_branchedRoot() {
        NodeFilter filter = new MarkupPolicy();
        Element root = head(element("root"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        Element first = child(root, "first");
        child(first, "nested");
        child(root, "second");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_HEAD_REMOVE_variation1_deepDetachedRoot() {
        NodeFilter filter = new MarkupPolicy();
        Element root = head(element("root"), NodeFilter.FilterResult.REMOVE);
        Element a = child(root, "a");
        Element b = child(root, "b");
        Element c = child(root, "c");
        child(child(child(b, "level1"), "level2"), "level3");
        child(a, "a-child");
        child(c, "c-child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_HEAD_STOP_variation1_immediateTermination() {
        NodeFilter filter = new MarkupPolicy();
        Node root = head(element("root"), NodeFilter.FilterResult.STOP);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_TAIL_STOP_variation1_leafTailTermination() {
        NodeFilter filter = new MarkupPolicy();
        Node root = tail(element("root"), NodeFilter.FilterResult.STOP);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_TAIL_REMOVE_variation1_detachedLeafRetained() {
        NodeFilter filter = new MarkupPolicy();
        Node root = tail(element("root"), NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_TAIL_SKIP_CHILDREN_variation1_leafResult() {
        NodeFilter filter = new MarkupPolicy();
        Node root = tail(element("root"), NodeFilter.FilterResult.SKIP_CHILDREN);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_TAIL_SKIP_ENTIRELY_variation1_leafResult() {
        NodeFilter filter = new MarkupPolicy();
        Node root = tail(element("root"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_CHILD_FULL_TRAVERSAL_variation1_basicDescent() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "only-child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEEP_CHAIN_ASCENT_variation1_fourLevels() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element level1 = child(root, "level1");
        Element level2 = child(level1, "level2");
        Element level3 = child(level2, "level3");
        child(level3, "level4");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TWO_LEAF_SIBLINGS_variation1_directAdvancement() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "first");
        child(root, "second");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BRANCHED_DEPTH_FIRST_ORDER_variation1_nestedFirstBranch() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element first = child(root, "first");
        child(first, "first-a");
        Element firstB = child(first, "first-b");
        child(firstB, "deep");
        Element second = child(root, "second");
        child(second, "second-a");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_WITH_NEXT_SIBLING_variation1_firstBranch() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element selected = head(child(root, "selected"), NodeFilter.FilterResult.SKIP_CHILDREN);
        child(selected, "pruned-child");
        child(root, "next");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_LAST_SIBLING_variation1_upwardLoop() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "first");
        Element selected = head(child(root, "selected"), NodeFilter.FilterResult.SKIP_CHILDREN);
        child(selected, "pruned-child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERNAL_SKIP_ENTIRELY_WITH_NEXT_SIBLING_variation1_untouchedSubtree() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element selected = head(child(root, "selected"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(child(selected, "nested"), "deep");
        child(root, "next");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERNAL_SKIP_ENTIRELY_LAST_SIBLING_variation1_ascentReset() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "first");
        Element selected = head(child(root, "selected"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(selected, "unvisited");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_FIRST_LEAF_variation1_preserveSecondCursor() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        head(child(root, "remove-first"), NodeFilter.FilterResult.REMOVE);
        child(root, "survivor");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_MIDDLE_LEAF_variation1_threeSiblings() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "first");
        head(child(root, "remove-middle"), NodeFilter.FilterResult.REMOVE);
        child(root, "last");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_LAST_LEAF_variation1_parentCaptured() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "first");
        head(child(root, "remove-last"), NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_ONLY_CHILD_variation1_rootBecomesEmpty() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        head(child(root, "only-child"), NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_INTERNAL_SUBTREE_variation1_nextSiblingSurvives() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element selected = head(child(root, "remove-subtree"), NodeFilter.FilterResult.REMOVE);
        child(selected, "unvisited-a");
        child(selected, "unvisited-b");
        child(root, "next");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_LAST_INTERNAL_SUBTREE_variation1_ascentRemoval() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "first");
        Element selected = head(child(root, "remove-subtree"), NodeFilter.FilterResult.REMOVE);
        child(child(selected, "unvisited"), "deep");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_FIRST_LEAF_variation1_removeAfterVisit() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        tail(child(root, "remove-first"), NodeFilter.FilterResult.REMOVE);
        child(root, "next");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_MIDDLE_LEAF_variation1_originalNextVisited() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "first");
        tail(child(root, "remove-middle"), NodeFilter.FilterResult.REMOVE);
        child(root, "last");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_LAST_LEAF_variation1_removeDuringAscent() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "first");
        tail(child(root, "remove-last"), NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_INTERNAL_SUBTREE_WITH_SIBLING_variation1_completeThenRemove() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element selected = tail(child(root, "remove-subtree"), NodeFilter.FilterResult.REMOVE);
        child(selected, "nested-a");
        child(child(selected, "nested-b"), "deep");
        child(root, "next");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_LAST_INTERNAL_SUBTREE_variation1_multilevelAscent() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "first");
        Element selected = tail(child(root, "remove-subtree"), NodeFilter.FilterResult.REMOVE);
        child(child(selected, "nested"), "leaf");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NESTED_HEAD_STOP_BEFORE_DESCENT_variation1_internalStop() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element selected = head(child(root, "stop-here"), NodeFilter.FilterResult.STOP);
        child(selected, "unvisited");
        child(root, "later");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIBLING_HEAD_STOP_variation1_afterCompletedFirstSibling() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element first = child(root, "first");
        child(first, "first-child");
        head(child(root, "stop-second"), NodeFilter.FilterResult.STOP);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAF_TAIL_STOP_BEFORE_SIBLING_variation1_availableNextSibling() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        tail(child(root, "stop-on-tail"), NodeFilter.FilterResult.STOP);
        child(root, "unvisited-next");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERNAL_TAIL_STOP_DURING_ASCENT_variation1_completedSubtree() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element selected = tail(child(root, "stop-internal"), NodeFilter.FilterResult.STOP);
        child(selected, "first-leaf");
        child(selected, "last-leaf");
        child(root, "unvisited-sibling");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SKIP_CHILDREN_THEN_TAIL_STOP_variation1_prunedTermination() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element selected = head(child(root, "selected"), NodeFilter.FilterResult.SKIP_CHILDREN);
        tail(selected, NodeFilter.FilterResult.STOP);
        child(selected, "unvisited-child");
        child(root, "unvisited-sibling");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SKIP_CHILDREN_THEN_TAIL_REMOVE_variation1_prunedSubtreeRemoved() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element selected = head(child(root, "selected"), NodeFilter.FilterResult.SKIP_CHILDREN);
        tail(selected, NodeFilter.FilterResult.REMOVE);
        child(selected, "untouched-child");
        child(root, "next");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SKIP_CHILDREN_THEN_TAIL_SKIP_ENTIRELY_variation1_advanceWithoutDeletion() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element selected = head(child(root, "selected"), NodeFilter.FilterResult.SKIP_CHILDREN);
        tail(selected, NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(selected, "unvisited-child");
        child(root, "next");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NESTED_TAIL_SKIP_CHILDREN_RESET_variation1_lastChildResultReset() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element parent = child(root, "parent");
        tail(child(parent, "last-child"), NodeFilter.FilterResult.SKIP_CHILDREN);
        child(root, "later-root-child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NESTED_TAIL_SKIP_ENTIRELY_RESET_variation1_parentStillTailed() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        Element parent = child(root, "parent");
        tail(child(parent, "last-child"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(root, "later-root-child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void REMOVE_ONLY_CHILD_FROM_TAIL_variation1_rootBecomesChildless() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        tail(child(root, "only-child"), NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEPTH_SELECTED_PRUNING_variation1_multipleDepthTwoBranches() {
        NodeFilter filter = new DepthTwoPruningPolicy();
        Element root = element("root");
        Element a = child(root, "a");
        Element a2 = child(a, "a-depth-two");
        child(a2, "a-depth-three");
        Element b = child(root, "b");
        Element b2 = child(b, "b-depth-two");
        child(b2, "b-depth-three");
        Element c = child(root, "c");
        Element c2 = child(c, "c-depth-two");
        child(c2, "c-depth-three");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_SIBLING_HEAD_RESULTS_variation1_skipRemoveContinue() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");

        Element skipped = head(child(root, "skipped"), NodeFilter.FilterResult.SKIP_CHILDREN);
        child(skipped, "skipped-child");

        Element removed = head(child(root, "removed"), NodeFilter.FilterResult.REMOVE);
        child(removed, "removed-child");

        Element continued = child(root, "continued");
        child(child(continued, "continued-child"), "continued-deep");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_AND_NONEMPTY_BRANCHES_variation1_leafBeforeInternal() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        child(root, "leaf");
        Element branch = child(root, "branch");
        child(branch, "nested");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WIDE_DEEP_TREE_variation1_multipleBranchPoints() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");

        Element a = child(root, "a");
        child(a, "a1");
        Element a2 = child(a, "a2");
        child(a2, "a2-deep");

        Element b = child(root, "b");
        Element b1 = child(b, "b1");
        child(b1, "b1-left");
        child(b1, "b1-right");
        child(b, "b2");

        Element c = child(root, "c");
        child(c, "c1");
        child(c, "c2");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONSECUTIVE_HEAD_REMOVALS_variation1_twoRemovedBeforeSurvivor() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        head(child(root, "remove-one"), NodeFilter.FilterResult.REMOVE);
        head(child(root, "remove-two"), NodeFilter.FilterResult.REMOVE);
        Element survivor = child(root, "survivor");
        child(survivor, "survivor-child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void REMOVE_ALL_CHILDREN_MIXED_PATHS_variation1_threeHeadRemovals() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        head(child(root, "remove-one"), NodeFilter.FilterResult.REMOVE);
        head(child(root, "remove-two"), NodeFilter.FilterResult.REMOVE);
        head(child(root, "remove-final"), NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STOP_AFTER_PRIOR_REMOVAL_variation1_mutateThenTerminate() {
        NodeFilter filter = new MarkupPolicy();
        Element root = element("root");
        head(child(root, "removed-first"), NodeFilter.FilterResult.REMOVE);
        head(child(root, "stop-second"), NodeFilter.FilterResult.STOP);
        child(root, "unvisited-third");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_TAIL_REMOVE_AFTER_DESCENDANTS_variation1_nonLeafRootRetained() {
        NodeFilter filter = new MarkupPolicy();
        Element root = tail(element("root"), NodeFilter.FilterResult.REMOVE);
        Element first = child(root, "first");
        child(first, "nested");
        child(root, "second");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_TAIL_STOP_AFTER_REMOVALS_variation1_descendantMutationPersists() {
        NodeFilter filter = new MarkupPolicy();
        Element root = tail(element("root"), NodeFilter.FilterResult.STOP);
        head(child(root, "removed"), NodeFilter.FilterResult.REMOVE);
        Element survivor = child(root, "survivor");
        child(survivor, "nested");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDER_SENSITIVE_FRESH_FILTER_variation1_resetSchedule() {
        NodeFilter filter = new ScheduledPolicy();
        Element root = element("root");

        Element first = child(root, "first");
        child(first, "pruned-descendant");

        Element second = child(root, "second");
        child(second, "removed-descendant");

        Element third = child(root, "third");
        Element thirdChild = child(third, "third-child");
        child(thirdChild, "third-deep");

        child(root, "fourth");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
