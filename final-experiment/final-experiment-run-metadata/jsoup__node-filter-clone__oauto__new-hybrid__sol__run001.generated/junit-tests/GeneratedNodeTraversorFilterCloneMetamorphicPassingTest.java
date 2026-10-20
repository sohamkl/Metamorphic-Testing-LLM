import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    public static final class MarkedPolicy implements NodeFilter {
        public MarkedPolicy() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return markedResult(node, "_head", "H_");
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return markedResult(node, "_tail", "T_");
        }

        private static FilterResult markedResult(Node node, String attribute, String textPrefix) {
            if (node instanceof Element) {
                String value = ((Element) node).attr(attribute);
                if (!value.isEmpty()) {
                    return FilterResult.valueOf(value);
                }
            }
            if (node instanceof TextNode) {
                String text = ((TextNode) node).getWholeText();
                if (text.startsWith(textPrefix)) {
                    return FilterResult.valueOf(text.substring(textPrefix.length()));
                }
            }
            return FilterResult.CONTINUE;
        }
    }

    public static final class DepthPolicy implements NodeFilter {
        private final int threshold;
        private final FilterResult selectedResult;

        public DepthPolicy(int threshold, FilterResult selectedResult) {
            this.threshold = threshold;
            this.selectedResult = selectedResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return depth >= threshold ? selectedResult : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class FollowUp {
        private final NodeFilter filter;
        private final Node root;

        private FollowUp(NodeFilter filter, Node root) {
            this.filter = filter;
            this.root = root;
        }
    }

    private static FollowUp generateFollowUp(NodeFilter filter, Node root) {
        Object[] transformed =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        return new FollowUp((NodeFilter) transformed[0], (Node) transformed[1]);
    }

    private static Element element(String tag) {
        return new Element(tag);
    }

    private static Element head(Element node, NodeFilter.FilterResult result) {
        node.attr("_head", result.name());
        return node;
    }

    private static Element tail(Element node, NodeFilter.FilterResult result) {
        node.attr("_tail", result.name());
        return node;
    }

    private static Element chain(int nodeCount) {
        Element root = element("n0");
        Element cursor = root;
        for (int index = 1; index < nodeCount; index++) {
            Element child = element("n" + index);
            cursor.appendChild(child);
            cursor = child;
        }
        return root;
    }

    private static Element twoLeafChildren() {
        Element root = element("root");
        root.appendChild(element("first"));
        root.appendChild(element("second"));
        return root;
    }

    private static void exercise(NodeFilter sourceFilter, Node sourceRoot) {
        FollowUp followUp = generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(followUp.filter, followUp.root);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_ROOT_CONTINUE_variation1_elementLeaf() {
        exercise(new MarkedPolicy(), element("root"));
    }

    @Test
    public void ROOT_TAIL_STOP_variation1_nonElementLeaf() {
        exercise(new MarkedPolicy(), new TextNode("T_STOP"));
    }

    @Test
    public void ROOT_TAIL_REMOVE_SENTINEL_variation1_detachedElement() {
        exercise(new MarkedPolicy(), tail(element("root"), NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void ROOT_TAIL_SKIP_CHILDREN_RESULT_variation1_textRoot() {
        exercise(new MarkedPolicy(), new TextNode("T_SKIP_CHILDREN"));
    }

    @Test
    public void ROOT_TAIL_SKIP_ENTIRELY_RESULT_variation1_elementLeaf() {
        exercise(
                new MarkedPolicy(),
                tail(element("root"), NodeFilter.FilterResult.SKIP_ENTIRELY));
    }

    @Test
    public void ROOT_HEAD_STOP_variation1_nonElementRoot() {
        exercise(new MarkedPolicy(), new TextNode("H_STOP"));
    }

    @Test
    public void LEAF_ROOT_SKIP_CHILDREN_variation1_elementLeaf() {
        exercise(
                new MarkedPolicy(),
                head(element("root"), NodeFilter.FilterResult.SKIP_CHILDREN));
    }

    @Test
    public void LEAF_ROOT_SKIP_ENTIRELY_variation1_nonElementLeaf() {
        exercise(new MarkedPolicy(), new TextNode("H_SKIP_ENTIRELY"));
    }

    @Test
    public void LEAF_ROOT_REMOVE_variation1_elementLeaf() {
        exercise(
                new MarkedPolicy(),
                head(element("root"), NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_TAIL_STOP_variation1_preservedChildren() {
        Element root = head(element("root"), NodeFilter.FilterResult.SKIP_CHILDREN);
        tail(root, NodeFilter.FilterResult.STOP);
        root.appendChild(element("first"));
        root.appendChild(element("second"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void ONE_CHILD_FULL_TRAVERSAL_variation1_leafChild() {
        Element root = element("root");
        root.appendChild(element("only"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void DEEP_CHAIN_FULL_TRAVERSAL_variation1_fourNodes() {
        exercise(new MarkedPolicy(), chain(5));
    }

    @Test
    public void TWO_SIBLINGS_FULL_TRAVERSAL_variation1_leafSiblings() {
        exercise(new MarkedPolicy(), twoLeafChildren());
    }

    @Test
    public void BRANCHED_DEPTH_FIRST_ORDER_variation1_internalBeforeSibling() {
        Element root = element("root");
        Element branch = element("branch");
        branch.appendChild(element("leaf-a"));
        branch.appendChild(element("leaf-b"));
        root.appendChild(branch);
        root.appendChild(element("later"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void SKIP_CHILDREN_INTERNAL_NONLAST_variation1_continueAtSibling() {
        Element root = element("root");
        Element selected = head(element("selected"), NodeFilter.FilterResult.SKIP_CHILDREN);
        selected.appendChild(element("unvisited"));
        root.appendChild(selected);
        root.appendChild(element("later"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void SKIP_CHILDREN_INTERNAL_LAST_variation1_ascentAfterTail() {
        Element root = element("root");
        root.appendChild(element("first"));
        Element selected = head(element("selected"), NodeFilter.FilterResult.SKIP_CHILDREN);
        selected.appendChild(element("unvisited"));
        root.appendChild(selected);
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_PRESERVES_SUBTREE_variation1_branchedTree() {
        Element root = head(element("root"), NodeFilter.FilterResult.SKIP_CHILDREN);
        Element branch = element("branch");
        branch.appendChild(element("deep"));
        root.appendChild(branch);
        root.appendChild(element("other"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void SKIP_CHILDREN_ON_LEAF_CHILD_variation1_nonlastLeaf() {
        Element root = element("root");
        root.appendChild(head(element("selected"), NodeFilter.FilterResult.SKIP_CHILDREN));
        root.appendChild(element("next"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void SKIP_ENTIRELY_INTERNAL_NONLAST_variation1_subtreeRetained() {
        Element root = element("root");
        Element selected = head(element("selected"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        selected.appendChild(element("unvisited"));
        root.appendChild(selected);
        root.appendChild(element("later"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void SKIP_ENTIRELY_INTERNAL_LAST_variation1_ascendWithoutSelectedTail() {
        Element root = element("root");
        root.appendChild(element("first"));
        Element selected = head(element("selected"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        selected.appendChild(element("unvisited"));
        root.appendChild(selected);
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_PRESERVES_SUBTREE_variation1_deepTree() {
        Element root = head(chain(5), NodeFilter.FilterResult.SKIP_ENTIRELY);
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_NONLAST_LEAF_variation1_siblingAdvanceRemoval() {
        Element root = element("root");
        root.appendChild(head(element("remove-me"), NodeFilter.FilterResult.REMOVE));
        root.appendChild(element("survivor"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_LAST_LEAF_variation1_ascentRemoval() {
        Element root = element("root");
        root.appendChild(element("first"));
        root.appendChild(head(element("remove-me"), NodeFilter.FilterResult.REMOVE));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_NONLAST_SUBTREE_variation1_pruneBeforeSibling() {
        Element root = element("root");
        Element selected = head(element("remove-branch"), NodeFilter.FilterResult.REMOVE);
        selected.appendChild(element("unvisited-a"));
        selected.appendChild(element("unvisited-b"));
        root.appendChild(selected);
        root.appendChild(element("survivor"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_LAST_SUBTREE_variation1_pruneDuringAscent() {
        Element root = element("root");
        root.appendChild(element("first"));
        Element selected = head(element("remove-branch"), NodeFilter.FilterResult.REMOVE);
        selected.appendChild(element("unvisited"));
        root.appendChild(selected);
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void ROOT_HEAD_REMOVE_PRESERVES_ROOT_variation1_nonleafRoot() {
        Element root = head(element("root"), NodeFilter.FilterResult.REMOVE);
        root.appendChild(element("child"));
        root.appendChild(element("other"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void TAIL_REMOVE_NONLAST_LEAF_variation1_removeAfterBothCallbacks() {
        Element root = element("root");
        root.appendChild(tail(element("remove-me"), NodeFilter.FilterResult.REMOVE));
        root.appendChild(element("survivor"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void TAIL_REMOVE_LAST_LEAF_variation1_removeInAscentLoop() {
        Element root = element("root");
        root.appendChild(element("first"));
        root.appendChild(tail(element("remove-me"), NodeFilter.FilterResult.REMOVE));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void TAIL_REMOVE_NONLAST_INTERNAL_NODE_variation1_afterDescendants() {
        Element root = element("root");
        Element selected = tail(element("remove-branch"), NodeFilter.FilterResult.REMOVE);
        selected.appendChild(element("visited-leaf"));
        root.appendChild(selected);
        root.appendChild(element("survivor"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void TAIL_REMOVE_LAST_INTERNAL_NODE_variation1_multilevelAscent() {
        Element root = element("root");
        root.appendChild(element("first"));
        Element selected = tail(element("remove-branch"), NodeFilter.FilterResult.REMOVE);
        Element nested = element("nested");
        nested.appendChild(element("deep-leaf"));
        selected.appendChild(nested);
        root.appendChild(selected);
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void TAIL_STOP_NONLAST_LEAF_variation1_laterSiblingUnreached() {
        Element root = element("root");
        root.appendChild(tail(element("stop-here"), NodeFilter.FilterResult.STOP));
        root.appendChild(element("later"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void TAIL_STOP_LAST_LEAF_variation1_stopInsideAscent() {
        Element root = element("root");
        root.appendChild(element("first"));
        root.appendChild(tail(element("stop-here"), NodeFilter.FilterResult.STOP));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void TAIL_STOP_INTERNAL_AFTER_DESCENDANTS_variation1_completedSubtree() {
        Element root = element("root");
        Element selected = tail(element("stop-branch"), NodeFilter.FilterResult.STOP);
        selected.appendChild(element("visited-a"));
        selected.appendChild(element("visited-b"));
        root.appendChild(selected);
        root.appendChild(element("later"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void HEAD_STOP_FIRST_CHILD_variation1_immediateAfterDescent() {
        Element root = element("root");
        Element selected = head(element("stop-here"), NodeFilter.FilterResult.STOP);
        selected.appendChild(element("unvisited"));
        root.appendChild(selected);
        root.appendChild(element("later"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void HEAD_STOP_DEEP_DESCENDANT_variation1_depthThree() {
        Element root = element("root");
        Element first = element("level-one");
        Element second = element("level-two");
        Element selected = head(element("level-three"), NodeFilter.FilterResult.STOP);
        second.appendChild(selected);
        first.appendChild(second);
        root.appendChild(first);
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void HEAD_STOP_LATER_SIBLING_variation1_firstSiblingCompletes() {
        Element root = element("root");
        root.appendChild(element("completed-first"));
        root.appendChild(head(element("stop-second"), NodeFilter.FilterResult.STOP));
        root.appendChild(element("unvisited-third"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void ROOT_STOP_PRESERVES_CHILDREN_variation1_wideSubtree() {
        Element root = head(element("root"), NodeFilter.FilterResult.STOP);
        for (int index = 0; index < 6; index++) {
            root.appendChild(element("child" + index));
        }
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void TAIL_SKIP_CHILDREN_THEN_SIBLING_variation1_nonlastLeaf() {
        Element root = element("root");
        root.appendChild(
                tail(element("selected"), NodeFilter.FilterResult.SKIP_CHILDREN));
        root.appendChild(element("next"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_THEN_SIBLING_variation1_nonlastInternal() {
        Element root = element("root");
        Element selected =
                tail(element("selected"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        selected.appendChild(element("visited-descendant"));
        root.appendChild(selected);
        root.appendChild(element("next"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void MIXED_PRUNING_RESULTS_ACROSS_SIBLINGS_variation1_fourPolicies() {
        Element root = element("root");

        Element continued = element("continued");
        continued.appendChild(element("continued-leaf"));

        Element skipChildren =
                head(element("skip-children"), NodeFilter.FilterResult.SKIP_CHILDREN);
        skipChildren.appendChild(element("preserved-unvisited"));

        Element skipEntirely =
                head(element("skip-entirely"), NodeFilter.FilterResult.SKIP_ENTIRELY);
        skipEntirely.appendChild(element("preserved-unvisited"));

        Element remove = head(element("remove"), NodeFilter.FilterResult.REMOVE);
        remove.appendChild(element("removed-with-parent"));

        root.appendChild(continued);
        root.appendChild(skipChildren);
        root.appendChild(skipEntirely);
        root.appendChild(remove);
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void MULTIPLE_REMOVAL_PATHS_variation1_headAndTailRemoval() {
        Element root = element("root");
        root.appendChild(head(element("head-remove"), NodeFilter.FilterResult.REMOVE));
        root.appendChild(element("middle"));
        root.appendChild(tail(element("tail-remove"), NodeFilter.FilterResult.REMOVE));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void PRUNED_DESCENDANT_STOP_NOT_REACHED_variation1_skipChildrenBarrier() {
        Element root = element("root");
        Element pruned = head(element("pruned"), NodeFilter.FilterResult.SKIP_CHILDREN);
        pruned.appendChild(head(element("unreachable-stop"), NodeFilter.FilterResult.STOP));
        root.appendChild(pruned);
        root.appendChild(element("outside"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void REMOVED_DESCENDANT_STOP_NOT_REACHED_variation1_removedBarrier() {
        Element root = element("root");
        Element removed = head(element("removed"), NodeFilter.FilterResult.REMOVE);
        removed.appendChild(head(element("unreachable-stop"), NodeFilter.FilterResult.STOP));
        root.appendChild(removed);
        root.appendChild(element("outside"));
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void HETEROGENEOUS_NODE_SUBTYPES_variation1_textAndCommentDescendants() {
        Element root = element("root");
        root.appendChild(new TextNode("text"));
        root.appendChild(new Comment("comment"));
        Element nested = element("nested");
        nested.appendChild(new TextNode("more text"));
        root.appendChild(nested);
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void NON_ELEMENT_ROOT_variation1_detachedTextNode() {
        exercise(new MarkedPolicy(), new TextNode("ordinary detached text"));
    }

    @Test
    public void WIDE_SIBLING_SEQUENCE_variation1_twelveLeaves() {
        Element root = element("root");
        for (int index = 0; index < 12; index++) {
            root.appendChild(element("leaf" + index));
        }
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void DEEP_ASCENT_AFTER_LAST_LEAF_variation1_depthTwelve() {
        exercise(new MarkedPolicy(), chain(12));
    }

    @Test
    public void MIXED_LEAF_AND_INTERNAL_SIBLINGS_variation1_alternatingShapes() {
        Element root = element("root");
        root.appendChild(element("leaf-a"));

        Element internalA = element("internal-a");
        internalA.appendChild(element("nested-a"));
        root.appendChild(internalA);

        root.appendChild(element("leaf-b"));

        Element internalB = element("internal-b");
        Element nestedB = element("nested-b");
        nestedB.appendChild(element("deep-b"));
        internalB.appendChild(nestedB);
        root.appendChild(internalB);

        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void NO_ARGUMENT_FILTER_COPY_variation1_continueTree() {
        Element root = element("root");
        root.appendChild(element("first"));
        Element second = element("second");
        second.appendChild(element("nested"));
        root.appendChild(second);
        exercise(new MarkedPolicy(), root);
    }

    @Test
    public void PARAMETERIZED_FILTER_COPY_variation1_depthConfiguredSkip() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("pruned-grandchild"));
        root.appendChild(first);
        root.appendChild(element("second"));
        exercise(
                new DepthPolicy(1, NodeFilter.FilterResult.SKIP_CHILDREN),
                root);
    }
}
