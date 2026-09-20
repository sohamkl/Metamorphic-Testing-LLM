import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    public static final class AttributePolicy implements NodeFilter {
        public AttributePolicy() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            String result = ((Element) node).attr("data-head");
            return result.isEmpty() ? FilterResult.CONTINUE : FilterResult.valueOf(result);
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            String result = ((Element) node).attr("data-tail");
            return result.isEmpty() ? FilterResult.CONTINUE : FilterResult.valueOf(result);
        }
    }

    public static final class DepthSkipPolicy implements NodeFilter {
        private final int threshold;

        public DepthSkipPolicy(int threshold) {
            this.threshold = threshold;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return depth >= threshold ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static Element node(String id) {
        return new Element("n").attr("id", id);
    }

    private static Element child(Element parent, String id) {
        Element child = node(id);
        parent.appendChild(child);
        return child;
    }

    private static Element head(Element node, NodeFilter.FilterResult result) {
        node.attr("data-head", result.name());
        return node;
    }

    private static Element tail(Element node, NodeFilter.FilterResult result) {
        node.attr("data-tail", result.name());
        return node;
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            Node sourceRoot,
            Node followUpRoot) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError("FilterResult mismatch: source=" + sourceOutput
                    + ", follow-up=" + followUpOutput);
        }
        String sourceHtml = sourceRoot.outerHtml();
        String followUpHtml = followUpRoot.outerHtml();
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError("Final outerHtml mismatch:\nsource=" + sourceHtml
                    + "\nfollow-up=" + followUpHtml);
        }
    }

    @Test
    public void LEAF_ROOT_CONTINUE_variation1() {
        Element root = node("root");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_ROOT_SKIP_CHILDREN_variation1() {
        Element root = head(node("root"), NodeFilter.FilterResult.SKIP_CHILDREN);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_ROOT_SKIP_ENTIRELY_variation1() {
        Element root = head(node("root"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_ROOT_REMOVE_variation1() {
        Element root = head(node("root"), NodeFilter.FilterResult.REMOVE);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_ROOT_HEAD_STOP_variation1() {
        Element root = head(node("root"), NodeFilter.FilterResult.STOP);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_ROOT_TAIL_STOP_variation1() {
        Element root = tail(node("root"), NodeFilter.FilterResult.STOP);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_ROOT_TAIL_REMOVE_variation1() {
        Element root = tail(node("root"), NodeFilter.FilterResult.REMOVE);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_WITH_DESCENDANTS_variation1() {
        Element root = head(node("root"), NodeFilter.FilterResult.SKIP_CHILDREN);
        Element branch = child(root, "branch");
        child(branch, "deep");
        child(root, "sibling");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_WITH_DESCENDANTS_variation1() {
        Element root = head(node("root"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(root, "first");
        child(root, "second");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_REMOVE_WITH_DESCENDANTS_variation1() {
        Element root = head(node("root"), NodeFilter.FilterResult.REMOVE);
        Element child = child(root, "child");
        child(child, "grandchild");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ONE_CHILD_FULL_TRAVERSAL_variation1() {
        Element root = node("root");
        child(root, "only");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TWO_SIBLINGS_FULL_TRAVERSAL_variation1() {
        Element root = node("root");
        child(root, "first");
        child(root, "second");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void DEEP_CHAIN_FULL_ASCENT_variation1() {
        Element root = node("root");
        Element depth1 = child(root, "depth1");
        Element depth2 = child(depth1, "depth2");
        Element depth3 = child(depth2, "depth3");
        child(depth3, "depth4");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void BRANCHED_DEPTH_FIRST_ORDER_variation1() {
        Element root = node("root");
        Element first = child(root, "first");
        Element nested = child(first, "nested");
        child(nested, "deep-leaf");
        child(root, "second");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SKIP_CHILDREN_INTERNAL_WITH_SIBLING_variation1() {
        Element root = node("root");
        Element first = head(child(root, "first"), NodeFilter.FilterResult.SKIP_CHILDREN);
        child(first, "skipped");
        child(root, "second");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SKIP_CHILDREN_LAST_INTERNAL_variation1() {
        Element root = node("root");
        child(root, "first");
        Element last = head(child(root, "last"), NodeFilter.FilterResult.SKIP_CHILDREN);
        Element nested = child(last, "skipped");
        child(nested, "deep-skipped");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SKIP_ENTIRELY_INTERNAL_WITH_SIBLING_variation1() {
        Element root = node("root");
        Element first = head(child(root, "first"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(first, "skipped");
        child(root, "second");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SKIP_ENTIRELY_LAST_INTERNAL_variation1() {
        Element root = node("root");
        child(root, "first");
        Element last = head(child(root, "last"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(last, "skipped");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void HEAD_REMOVE_FIRST_LEAF_variation1() {
        Element root = node("root");
        head(child(root, "first"), NodeFilter.FilterResult.REMOVE);
        child(root, "second");
        child(root, "third");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void HEAD_REMOVE_LAST_LEAF_variation1() {
        Element root = node("root");
        child(root, "first");
        child(root, "middle");
        head(child(root, "last"), NodeFilter.FilterResult.REMOVE);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void HEAD_REMOVE_ONLY_CHILD_variation1() {
        Element root = node("root");
        head(child(root, "only"), NodeFilter.FilterResult.REMOVE);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void HEAD_REMOVE_INTERNAL_WITH_SIBLING_variation1() {
        Element root = node("root");
        Element removed = head(child(root, "removed"), NodeFilter.FilterResult.REMOVE);
        child(removed, "removed-child");
        child(root, "survivor");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void HEAD_REMOVE_LAST_INTERNAL_variation1() {
        Element root = node("root");
        child(root, "first");
        Element removed = head(child(root, "removed-last"), NodeFilter.FilterResult.REMOVE);
        child(removed, "removed-child");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void HEAD_STOP_INTERNAL_NODE_variation1() {
        Element root = node("root");
        Element before = child(root, "before");
        child(before, "before-leaf");
        Element stopped = head(child(root, "stopped"), NodeFilter.FilterResult.STOP);
        child(stopped, "unvisited-child");
        child(root, "later");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void HEAD_STOP_DEEP_LEAF_variation1() {
        Element root = node("root");
        Element depth1 = child(root, "depth1");
        Element depth2 = child(depth1, "depth2");
        Element depth3 = child(depth2, "depth3");
        head(child(depth3, "stopped-leaf"), NodeFilter.FilterResult.STOP);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void HEAD_STOP_LATER_SIBLING_variation1() {
        Element root = node("root");
        child(root, "first");
        child(root, "second");
        head(child(root, "stopped-third"), NodeFilter.FilterResult.STOP);
        child(root, "unvisited-fourth");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_STOP_FIRST_CHILD_variation1() {
        Element root = node("root");
        tail(child(root, "first"), NodeFilter.FilterResult.STOP);
        child(root, "second");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_STOP_LAST_DEEP_LEAF_variation1() {
        Element root = node("root");
        Element depth1 = child(root, "depth1");
        Element depth2 = child(depth1, "depth2");
        tail(child(depth2, "last-leaf"), NodeFilter.FilterResult.STOP);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_STOP_INTERNAL_AFTER_DESCENDANTS_variation1() {
        Element root = node("root");
        Element internal = tail(child(root, "internal"), NodeFilter.FilterResult.STOP);
        child(internal, "first-leaf");
        child(internal, "last-leaf");
        child(root, "unvisited-sibling");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_STOP_ROOT_AFTER_COMPLETE_TRAVERSAL_variation1() {
        Element root = tail(node("root"), NodeFilter.FilterResult.STOP);
        Element child = child(root, "child");
        child(child, "grandchild");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_REMOVE_FIRST_LEAF_variation1() {
        Element root = node("root");
        tail(child(root, "first"), NodeFilter.FilterResult.REMOVE);
        child(root, "second");
        child(root, "third");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_REMOVE_LAST_LEAF_variation1() {
        Element root = node("root");
        child(root, "first");
        child(root, "middle");
        tail(child(root, "last"), NodeFilter.FilterResult.REMOVE);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_REMOVE_ONLY_CHILD_variation1() {
        Element root = node("root");
        tail(child(root, "only"), NodeFilter.FilterResult.REMOVE);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_REMOVE_INTERNAL_WITH_SIBLING_variation1() {
        Element root = node("root");
        Element removed = tail(child(root, "removed"), NodeFilter.FilterResult.REMOVE);
        child(removed, "visited-child");
        child(root, "survivor");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_REMOVE_LAST_INTERNAL_variation1() {
        Element root = node("root");
        child(root, "first");
        Element removed = tail(child(root, "removed-last"), NodeFilter.FilterResult.REMOVE);
        Element nested = child(removed, "visited-child");
        child(nested, "deep-child");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SKIP_CHILDREN_TAIL_REMOVE_WITH_SIBLING_variation1() {
        Element root = node("root");
        Element removed = head(child(root, "removed"), NodeFilter.FilterResult.SKIP_CHILDREN);
        tail(removed, NodeFilter.FilterResult.REMOVE);
        child(removed, "skipped-child");
        child(root, "survivor");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SKIP_CHILDREN_TAIL_REMOVE_LAST_variation1() {
        Element root = node("root");
        child(root, "first");
        Element removed = head(child(root, "removed-last"), NodeFilter.FilterResult.SKIP_CHILDREN);
        tail(removed, NodeFilter.FilterResult.REMOVE);
        child(removed, "skipped-child");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SKIP_CHILDREN_TAIL_STOP_variation1() {
        Element root = node("root");
        Element stopped = head(child(root, "stopped"), NodeFilter.FilterResult.SKIP_CHILDREN);
        tail(stopped, NodeFilter.FilterResult.STOP);
        child(stopped, "skipped-child");
        child(root, "unvisited-sibling");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_WITH_SIBLING_variation1() {
        Element root = node("root");
        tail(child(root, "first"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(root, "second");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_DURING_ASCENT_variation1() {
        Element root = node("root");
        Element branch = child(root, "branch");
        tail(child(branch, "last-child"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void CHILD_REMOVAL_THEN_PARENT_TAIL_STOP_variation1() {
        Element root = node("root");
        Element parent = tail(child(root, "parent"), NodeFilter.FilterResult.STOP);
        head(child(parent, "removed-child"), NodeFilter.FilterResult.REMOVE);
        child(root, "unvisited-sibling");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void DESCENDANT_AND_ANCESTOR_TAIL_REMOVALS_variation1() {
        Element root = node("root");
        Element ancestor = tail(child(root, "ancestor"), NodeFilter.FilterResult.REMOVE);
        head(child(ancestor, "removed-descendant"), NodeFilter.FilterResult.REMOVE);
        child(ancestor, "remaining-descendant");
        child(root, "outside");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void REMOVE_ALL_CHILDREN_KEEP_ROOT_variation1() {
        Element root = node("root");
        head(child(root, "first"), NodeFilter.FilterResult.REMOVE);
        head(child(root, "second"), NodeFilter.FilterResult.REMOVE);
        head(child(root, "third"), NodeFilter.FilterResult.REMOVE);
        head(child(root, "fourth"), NodeFilter.FilterResult.REMOVE);
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void MIXED_WIDE_POLICY_variation1() {
        Element root = node("root");

        Element continued = child(root, "continued");
        child(continued, "continued-child");

        Element pruned = head(child(root, "pruned"), NodeFilter.FilterResult.SKIP_CHILDREN);
        child(pruned, "pruned-child");

        Element skipped = head(child(root, "skipped"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(skipped, "skipped-child");

        Element removed = head(child(root, "removed"), NodeFilter.FilterResult.REMOVE);
        child(removed, "removed-child");

        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void DEPTH_SELECTED_POLICY_variation1() {
        Element root = node("root");
        Element first = child(root, "first");
        Element firstDepth2 = child(first, "first-depth2");
        child(firstDepth2, "first-depth3");
        Element second = child(root, "second");
        Element secondDepth2 = child(second, "second-depth2");
        child(secondDepth2, "second-depth3");
        NodeFilter filter = new DepthSkipPolicy(2);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void MULTIPLE_BRANCH_ASCENTS_variation1() {
        Element root = node("root");
        Element first = child(root, "first-branch");
        Element firstNested = child(first, "first-nested");
        child(firstNested, "first-leaf");

        Element second = child(root, "second-branch");
        Element secondNested = child(second, "second-nested");
        child(secondNested, "second-leaf");

        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void STOP_AFTER_EARLIER_REMOVAL_variation1() {
        Element root = node("root");
        head(child(root, "removed-first"), NodeFilter.FilterResult.REMOVE);
        child(root, "completed-second");
        head(child(root, "stopped-third"), NodeFilter.FilterResult.STOP);
        child(root, "unvisited-fourth");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SKIPPED_BRANCH_BEFORE_REMOVED_BRANCH_variation1() {
        Element root = node("root");

        Element skipped = head(child(root, "skipped"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(skipped, "retained-descendant");

        Element removed = head(child(root, "removed"), NodeFilter.FilterResult.REMOVE);
        child(removed, "removed-descendant");

        child(root, "outside");

        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_TAIL_SKIP_CHILDREN_RESULT_variation1() {
        Element root = tail(node("root"), NodeFilter.FilterResult.SKIP_CHILDREN);
        Element first = child(root, "first");
        child(first, "grandchild");
        child(root, "second");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_TAIL_REMOVE_AFTER_DESCENDANTS_variation1() {
        Element root = tail(node("root"), NodeFilter.FilterResult.REMOVE);
        Element first = child(root, "first");
        child(first, "grandchild");
        child(root, "second");
        NodeFilter filter = new AttributePolicy();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }
}
