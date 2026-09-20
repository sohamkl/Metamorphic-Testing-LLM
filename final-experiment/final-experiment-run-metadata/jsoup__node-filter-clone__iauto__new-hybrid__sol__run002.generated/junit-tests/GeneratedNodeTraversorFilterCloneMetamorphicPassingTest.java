import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    public static final class MarkerFilter implements NodeFilter {
        public MarkerFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (node instanceof Element) {
                String result = ((Element) node).attr("data-head-result");
                if (!result.isEmpty()) {
                    return FilterResult.valueOf(result);
                }
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            if (node instanceof Element) {
                String result = ((Element) node).attr("data-tail-result");
                if (!result.isEmpty()) {
                    return FilterResult.valueOf(result);
                }
            }
            return FilterResult.CONTINUE;
        }
    }

    private static Element root(String tag) {
        return new Element(tag, "");
    }

    private static Element child(Element parent, String tag) {
        return parent.appendElement(tag);
    }

    private static Element head(Element element, NodeFilter.FilterResult result) {
        element.attr("data-head-result", result.name());
        return element;
    }

    private static Element tail(Element element, NodeFilter.FilterResult result) {
        element.attr("data-tail-result", result.name());
        return element;
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            Node sourceRoot,
            Node followUpRoot) {
        org.junit.jupiter.api.Assertions.assertEquals(sourceOutput, followUpOutput);
        org.junit.jupiter.api.Assertions.assertEquals(
                sourceRoot.outerHtml(), followUpRoot.outerHtml());
    }

    @Test
    public void SINGLE_LEAF_CONTINUE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("single");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ONE_CHILD_CONTINUE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("one-child");
        child(sourceRoot, "leaf");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void DEEP_CHAIN_CONTINUE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("chain-root");
        Element level1 = child(sourceRoot, "level-one");
        Element level2 = child(level1, "level-two");
        Element level3 = child(level2, "level-three");
        child(level3, "level-four");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void WIDE_SIBLINGS_CONTINUE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("wide-root");
        child(sourceRoot, "first");
        child(sourceRoot, "second");
        child(sourceRoot, "third");
        child(sourceRoot, "fourth");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void BRANCHING_TREE_CONTINUE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("branch-root");
        Element left = child(sourceRoot, "left");
        child(left, "left-leaf");
        Element right = child(sourceRoot, "right");
        child(right, "right-leaf");
        child(right, "right-leaf-two");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_HEAD_STOP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = head(root("stop-root"), NodeFilter.FilterResult.STOP);
        Element child = child(sourceRoot, "unvisited-child");
        child(child, "unvisited-grandchild");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void FIRST_CHILD_HEAD_STOP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("first-stop-root");
        head(child(sourceRoot, "stopping-child"), NodeFilter.FilterResult.STOP);
        child(sourceRoot, "later-child");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void DEEP_HEAD_STOP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("deep-stop-root");
        Element branch = child(sourceRoot, "branch");
        Element internal = child(branch, "internal");
        head(child(internal, "deep-stop"), NodeFilter.FilterResult.STOP);
        child(sourceRoot, "unvisited-sibling");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void LATER_SIBLING_HEAD_STOP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("later-stop-root");
        Element first = child(sourceRoot, "completed-first");
        child(first, "completed-leaf");
        head(child(sourceRoot, "stopping-second"), NodeFilter.FilterResult.STOP);
        child(sourceRoot, "unvisited-third");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void LEAF_TAIL_STOP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("leaf-tail-stop-root");
        tail(child(sourceRoot, "stopping-leaf"), NodeFilter.FilterResult.STOP);
        child(sourceRoot, "later-leaf");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_TAIL_STOP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("internal-tail-stop-root");
        Element internal = tail(child(sourceRoot, "stopping-internal"), NodeFilter.FilterResult.STOP);
        child(internal, "completed-descendant");
        child(sourceRoot, "later-sibling");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_TAIL_STOP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = tail(root("root-tail-stop"), NodeFilter.FilterResult.STOP);
        Element first = child(sourceRoot, "first-branch");
        child(first, "first-leaf");
        Element second = child(sourceRoot, "second-branch");
        child(second, "second-leaf");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = head(root("root-skip-children"), NodeFilter.FilterResult.SKIP_CHILDREN);
        Element child = child(sourceRoot, "retained-child");
        child(child, "retained-grandchild");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_WITH_SIBLING_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("skip-with-sibling-root");
        Element skipped = head(child(sourceRoot, "skipped-internal"),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        child(skipped, "unvisited-descendant");
        Element sibling = child(sourceRoot, "visited-sibling");
        child(sibling, "visited-descendant");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_LAST_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("skip-last-root");
        child(sourceRoot, "first-leaf");
        Element skipped = head(child(sourceRoot, "last-internal"),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        child(skipped, "unvisited-child");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void LEAF_SKIP_CHILDREN_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("leaf-skip-root");
        head(child(sourceRoot, "skip-leaf"), NodeFilter.FilterResult.SKIP_CHILDREN);
        child(sourceRoot, "following-leaf");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void SKIP_CHILDREN_TAIL_STOP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("skip-tail-stop-root");
        Element target = child(sourceRoot, "target");
        head(target, NodeFilter.FilterResult.SKIP_CHILDREN);
        tail(target, NodeFilter.FilterResult.STOP);
        child(target, "unvisited-descendant");
        child(sourceRoot, "later-sibling");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void SKIP_CHILDREN_TAIL_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("skip-tail-remove-root");
        Element target = child(sourceRoot, "removed-target");
        head(target, NodeFilter.FilterResult.SKIP_CHILDREN);
        tail(target, NodeFilter.FilterResult.REMOVE);
        child(target, "removed-unvisited-descendant");
        child(sourceRoot, "surviving-sibling");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = head(root("skip-entire-root"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        Element retained = child(sourceRoot, "retained-child");
        child(retained, "retained-descendant");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void FIRST_CHILD_SKIP_ENTIRELY_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("first-skip-entire-root");
        Element skipped = head(child(sourceRoot, "skipped-first"),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(skipped, "unvisited-descendant");
        Element second = child(sourceRoot, "visited-second");
        child(second, "visited-descendant");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void LAST_CHILD_SKIP_ENTIRELY_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("last-skip-entire-root");
        child(sourceRoot, "completed-first");
        Element skipped = head(child(sourceRoot, "skipped-last"),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(skipped, "unvisited-descendant");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_SUBTREE_SKIP_ENTIRELY_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("internal-skip-entire-root");
        Element skipped = head(child(sourceRoot, "skipped-subtree"),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(skipped, "first-descendant");
        Element nested = child(skipped, "nested-descendant");
        child(nested, "deep-descendant");
        child(sourceRoot, "outside-subtree");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_HEAD_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = head(root("root-head-remove"), NodeFilter.FilterResult.REMOVE);
        Element retained = child(sourceRoot, "retained-child");
        child(retained, "retained-descendant");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ONLY_CHILD_HEAD_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("only-head-remove-root");
        head(child(sourceRoot, "only-child"), NodeFilter.FilterResult.REMOVE);

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void FIRST_CHILD_HEAD_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("first-head-remove-root");
        head(child(sourceRoot, "removed-first"), NodeFilter.FilterResult.REMOVE);
        child(sourceRoot, "surviving-second");
        child(sourceRoot, "surviving-third");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void MIDDLE_CHILD_HEAD_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("middle-head-remove-root");
        child(sourceRoot, "surviving-first");
        head(child(sourceRoot, "removed-middle"), NodeFilter.FilterResult.REMOVE);
        child(sourceRoot, "surviving-last");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void LAST_CHILD_HEAD_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("last-head-remove-root");
        child(sourceRoot, "surviving-first");
        child(sourceRoot, "surviving-middle");
        head(child(sourceRoot, "removed-last"), NodeFilter.FilterResult.REMOVE);

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_SUBTREE_HEAD_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("internal-head-remove-root");
        Element removed = head(child(sourceRoot, "removed-subtree"),
                NodeFilter.FilterResult.REMOVE);
        child(removed, "removed-leaf-one");
        Element nested = child(removed, "removed-nested");
        child(nested, "removed-deep-leaf");
        child(sourceRoot, "surviving-outside");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ALL_CHILDREN_HEAD_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("all-head-remove-root");
        head(child(sourceRoot, "removed-one"), NodeFilter.FilterResult.REMOVE);
        head(child(sourceRoot, "removed-two"), NodeFilter.FilterResult.REMOVE);
        head(child(sourceRoot, "removed-three"), NodeFilter.FilterResult.REMOVE);
        head(child(sourceRoot, "removed-four"), NodeFilter.FilterResult.REMOVE);

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_TAIL_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = tail(root("root-tail-remove"), NodeFilter.FilterResult.REMOVE);
        Element first = child(sourceRoot, "first-branch");
        child(first, "first-leaf");
        Element second = child(sourceRoot, "second-branch");
        child(second, "second-leaf");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ONLY_CHILD_TAIL_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("only-tail-remove-root");
        Element only = tail(child(sourceRoot, "removed-only"), NodeFilter.FilterResult.REMOVE);
        child(only, "visited-before-removal");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void FIRST_CHILD_TAIL_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("first-tail-remove-root");
        Element first = tail(child(sourceRoot, "removed-first"),
                NodeFilter.FilterResult.REMOVE);
        child(first, "visited-first-descendant");
        child(sourceRoot, "surviving-second");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void MIDDLE_CHILD_TAIL_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("middle-tail-remove-root");
        child(sourceRoot, "surviving-first");
        Element middle = tail(child(sourceRoot, "removed-middle"),
                NodeFilter.FilterResult.REMOVE);
        child(middle, "visited-middle-descendant");
        child(sourceRoot, "surviving-last");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void LAST_CHILD_TAIL_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("last-tail-remove-root");
        child(sourceRoot, "surviving-first");
        Element last = tail(child(sourceRoot, "removed-last"),
                NodeFilter.FilterResult.REMOVE);
        child(last, "visited-last-descendant");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_SUBTREE_TAIL_REMOVE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("internal-tail-remove-root");
        Element removed = tail(child(sourceRoot, "removed-subtree"),
                NodeFilter.FilterResult.REMOVE);
        child(removed, "first-descendant");
        Element nested = child(removed, "nested-descendant");
        child(nested, "deep-descendant");
        child(sourceRoot, "surviving-outside");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void REMOVE_THEN_STOP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("remove-then-stop-root");
        Element removed = head(child(sourceRoot, "removed-first"),
                NodeFilter.FilterResult.REMOVE);
        child(removed, "removed-descendant");
        Element second = child(sourceRoot, "second-branch");
        head(child(second, "stopping-node"), NodeFilter.FilterResult.STOP);
        child(second, "unvisited-after-stop");
        child(sourceRoot, "unvisited-final-branch");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void SKIP_THEN_LATER_STOP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("skip-then-stop-root");
        Element skipped = head(child(sourceRoot, "skipped-first"),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(skipped, "unvisited-skipped-descendant");
        Element second = child(sourceRoot, "second-branch");
        head(child(second, "stopping-node"), NodeFilter.FilterResult.STOP);
        child(sourceRoot, "unvisited-third");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void REMOVE_THEN_SKIP_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("remove-then-skip-root");
        head(child(sourceRoot, "removed-first"), NodeFilter.FilterResult.REMOVE);
        Element skipped = head(child(sourceRoot, "skipped-second"),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        child(skipped, "retained-unvisited-child");
        child(sourceRoot, "visited-third");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void DEPTH_SELECTIVE_SKIP_CHILDREN_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("depth-selective-root");

        Element first = head(child(sourceRoot, "selected-first"),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        Element firstNested = child(first, "first-unvisited-child");
        child(firstNested, "first-deep-unvisited");

        Element second = head(child(sourceRoot, "selected-second"),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        Element secondNested = child(second, "second-unvisited-child");
        child(secondNested, "second-deep-unvisited");

        child(sourceRoot, "ordinary-leaf");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void MIXED_RESULT_BRANCHES_COMPLETE_variation1() {
        NodeFilter filter = new MarkerFilter();
        Element sourceRoot = root("mixed-branches-root");

        Element skipped = head(child(sourceRoot, "skipped-first"),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        child(skipped, "retained-unvisited-descendant");

        Element removed = head(child(sourceRoot, "removed-second"),
                NodeFilter.FilterResult.REMOVE);
        child(removed, "removed-descendant");

        Element continued = child(sourceRoot, "continued-third");
        Element nested = child(continued, "continued-nested");
        child(nested, "continued-deep-leaf");

        child(sourceRoot, "continued-fourth");

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }
}
