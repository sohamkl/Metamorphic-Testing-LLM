import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    private static final class ScriptedFilter implements NodeFilter {
        public ScriptedFilter() {
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

    private static final class DepthSelectiveFilter implements NodeFilter {
        public DepthSelectiveFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (depth == 1 && node instanceof Element
                    && ((Element) node).hasClass("skip-at-depth-one")) {
                return FilterResult.SKIP_CHILDREN;
            }
            if (depth >= 2 && node instanceof Element
                    && ((Element) node).hasClass("remove-deep")) {
                return FilterResult.REMOVE;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class NoMatchFilter implements NodeFilter {
        public NoMatchFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (node instanceof Element && ((Element) node).hasClass("absent-policy-match")) {
                return FilterResult.REMOVE;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static Element element(String tagName, Node... children) {
        Element element = new Element(tagName);
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static Element head(Element element, NodeFilter.FilterResult result) {
        element.attr("data-head-result", result.name());
        return element;
    }

    private static Element tail(Element element, NodeFilter.FilterResult result) {
        element.attr("data-tail-result", result.name());
        return element;
    }

    private static void exercise(NodeFilter sourceFilter, Node sourceRoot) {
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(
                sourceFilter, sourceRoot);
        NodeFilter followUpFilter = (NodeFilter) followUp[0];
        Node followUpRoot = (Node) followUp[1];

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(followUpFilter, followUpRoot);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_LEAF_CONTINUE_variation1_elementLeaf() {
        Node root = element("leaf");
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SINGLE_LEAF_CONTINUE_variation2_textLeaf() {
        Node root = new TextNode("detached text leaf");
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void ONE_CHILD_DEPTH_FIRST_variation1_containerAndLeaf() {
        Node root = element("root", element("child"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void BROAD_SIBLING_ORDER_variation1_threeElementChildren() {
        Node root = element("root",
                element("first"),
                element("second"),
                element("third"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void BROAD_SIBLING_ORDER_variation2_mixedLeafChildren() {
        Node root = element("root",
                new TextNode("alpha"),
                element("middle"),
                new TextNode("omega"),
                element("last"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void DEEP_CHAIN_ASCENT_variation1_depthThree() {
        Node root = element("d0",
                element("d1",
                        element("d2",
                                element("d3"))));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void DEEP_CHAIN_ASCENT_variation2_depthFourWithTextLeaf() {
        Node root = element("level0",
                element("level1",
                        element("level2",
                                element("level3",
                                        new TextNode("level4")))));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void BRANCHING_MIXED_NODE_TREE_variation1_elementsAndTextNodes() {
        Node root = element("root",
                element("left",
                        new TextNode("left text"),
                        element("left-leaf")),
                element("right",
                        element("right-inner", new TextNode("right text"))),
                new TextNode("root text"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void STOP_AT_ROOT_HEAD_variation1_nonLeafRoot() {
        Node root = head(
                element("root", element("unvisited"), element("also-unvisited")),
                NodeFilter.FilterResult.STOP);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void STOP_AT_ONLY_CHILD_HEAD_variation1_onlyChildStops() {
        Element child = head(element("child"), NodeFilter.FilterResult.STOP);
        Node root = element("root", child);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void STOP_AT_LATER_SIBLING_HEAD_variation1_secondChildStops() {
        Element stopping = head(element("second"), NodeFilter.FilterResult.STOP);
        Node root = element("root",
                element("first"),
                stopping,
                element("third"),
                element("fourth"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void STOP_AT_DEEP_HEAD_variation1_depthThreeStops() {
        Element stopping = head(element("deep-stop"), NodeFilter.FilterResult.STOP);
        Node root = element("root",
                element("depth-one",
                        element("depth-two", stopping)),
                element("unvisited-branch"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void STOP_AT_LEAF_TAIL_variation1_firstLeafTailStops() {
        Element stopping = tail(element("first-leaf"), NodeFilter.FilterResult.STOP);
        Node root = element("root",
                stopping,
                element("following-sibling"),
                element("later-sibling"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void STOP_AT_INTERNAL_TAIL_variation1_internalSubtreeCompletes() {
        Element internal = tail(
                element("internal",
                        element("nested-one"),
                        element("nested-two")),
                NodeFilter.FilterResult.STOP);
        Node root = element("root", internal, element("unvisited-sibling"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void STOP_AT_ROOT_TAIL_variation1_stopAfterFullTraversal() {
        Element root = tail(
                element("root",
                        element("left", element("deep")),
                        element("right")),
                NodeFilter.FilterResult.STOP);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_CHILDREN_AT_ROOT_variation1_descendantsSuppressed() {
        Element root = head(
                element("root",
                        element("first", element("grandchild")),
                        element("second")),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_CHILDREN_INTERNAL_WITH_SIBLING_variation1_internalBranchSkipped() {
        Element skipped = head(
                element("skipped", element("hidden-one"), element("hidden-two")),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        Node root = element("root", skipped, element("visited-sibling"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_CHILDREN_ON_LEAF_variation1_leafHasNoChildren() {
        Element leaf = head(element("leaf"), NodeFilter.FilterResult.SKIP_CHILDREN);
        Node root = element("root", leaf, element("following"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_CHILDREN_LAST_INTERNAL_CHILD_variation1_ascentAfterSkippedLastChild() {
        Element lastInternal = head(
                element("last-internal", element("hidden-child")),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        Node root = element("root",
                element("parent", element("first"), lastInternal));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_ENTIRELY_AT_ROOT_variation1_rootNotTailed() {
        Element root = head(
                element("root", element("child"), element("other-child")),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_ENTIRELY_INTERNAL_WITH_SIBLING_variation1_subtreeRetained() {
        Element skipped = head(
                element("internal", element("hidden-child")),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        Node root = element("root", skipped, element("following"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_ENTIRELY_LAST_CHILD_variation1_parentStillTailed() {
        Element skipped = head(
                element("last", element("hidden")),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        Node root = element("root",
                element("parent", element("first"), skipped));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_ENTIRELY_DEEP_SUBTREE_variation1_depthTwoSubtreeSkipped() {
        Element skipped = head(
                element("deep-skipped", element("hidden-one"), element("hidden-two")),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        Node root = element("root",
                element("branch", element("before"), skipped, element("after")),
                element("other-branch"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_AT_ROOT_HEAD_variation1_detachedRootRemains() {
        Element root = head(
                element("root", element("child"), element("other")),
                NodeFilter.FilterResult.REMOVE);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_FIRST_CHILD_HEAD_variation1_firstSubtreeRemoved() {
        Element removed = head(
                element("removed-first", element("unvisited-descendant")),
                NodeFilter.FilterResult.REMOVE);
        Node root = element("root", removed, element("retained-second"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_INTERIOR_CHILD_HEAD_variation1_middleSiblingRemoved() {
        Element removed = head(
                element("removed-middle", element("hidden")),
                NodeFilter.FilterResult.REMOVE);
        Node root = element("root",
                element("first"),
                removed,
                element("third"),
                element("fourth"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_LAST_CHILD_HEAD_variation1_ascentRemovalPath() {
        Element removed = head(element("removed-last"), NodeFilter.FilterResult.REMOVE);
        Node root = element("root",
                element("parent", element("first"), removed));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_DEEP_NODE_HEAD_variation1_depthThreeRemoval() {
        Element removed = head(
                element("deep-removed", element("pruned-descendant")),
                NodeFilter.FilterResult.REMOVE);
        Node root = element("root",
                element("level-one",
                        element("level-two", removed, element("retained-peer"))),
                element("other-branch"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_ALL_ROOT_CHILDREN_BY_HEAD_variation1_indexShiftAcrossChildren() {
        Element first = head(element("first"), NodeFilter.FilterResult.REMOVE);
        Element second = head(element("second"), NodeFilter.FilterResult.REMOVE);
        Element third = head(element("third"), NodeFilter.FilterResult.REMOVE);
        Element fourth = head(element("fourth"), NodeFilter.FilterResult.REMOVE);
        Node root = element("root", first, second, third, fourth);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_NESTED_SUBTREE_PRESERVES_SIBLING_variation1_branchRemoved() {
        Element removed = head(
                element("removed-branch",
                        element("nested-one"),
                        element("nested-two", element("nested-three"))),
                NodeFilter.FilterResult.REMOVE);
        Node root = element("root",
                removed,
                element("retained-branch",
                        element("retained-one"),
                        element("retained-two")));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_AT_ROOT_TAIL_variation1_rootCannotBeDetached() {
        Element root = tail(
                element("root",
                        element("left", element("deep")),
                        element("right")),
                NodeFilter.FilterResult.REMOVE);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_LEAF_TAIL_WITH_SIBLING_variation1_leafRemovedAfterTail() {
        Element removed = tail(element("leaf"), NodeFilter.FilterResult.REMOVE);
        Node root = element("root",
                removed,
                element("following"),
                element("later"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_LAST_CHILD_TAIL_variation1_parentRecoveredBeforeDetach() {
        Element removed = tail(element("last"), NodeFilter.FilterResult.REMOVE);
        Node root = element("root",
                element("parent", element("first"), removed));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_INTERNAL_NODE_TAIL_variation1_descendantsVisitedFirst() {
        Element removed = tail(
                element("internal",
                        element("child-one"),
                        element("child-two", element("deep"))),
                NodeFilter.FilterResult.REMOVE);
        Node root = element("root", removed, element("outside"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_INTERNAL_TAIL_WITH_SIBLING_variation1_nextSiblingTraversal() {
        Element removed = tail(
                element("internal", element("nested")),
                NodeFilter.FilterResult.REMOVE);
        Node root = element("root",
                element("before"),
                removed,
                element("following", element("following-child")));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_MULTIPLE_TAILS_ACROSS_DEPTHS_variation1_separateBranches() {
        Element deepRemoved = tail(
                element("deep-removed", new TextNode("visited before removal")),
                NodeFilter.FilterResult.REMOVE);
        Element branchRemoved = tail(
                element("branch-removed", element("branch-child")),
                NodeFilter.FilterResult.REMOVE);
        Node root = element("root",
                element("left", element("left-inner", deepRemoved)),
                branchRemoved,
                element("retained"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_CHILDREN_THEN_REMOVE_SIBLING_variation1_mixedHeadActions() {
        Element skipped = head(
                element("skipped", element("hidden-one"), element("hidden-two")),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        Element removed = head(
                element("removed", element("pruned")),
                NodeFilter.FilterResult.REMOVE);
        Node root = element("root", skipped, removed, element("retained"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_THEN_STOP_LATER_SIBLING_variation1_priorRemovalPersists() {
        Element removed = head(element("removed"), NodeFilter.FilterResult.REMOVE);
        Element stopping = head(element("stopping"), NodeFilter.FilterResult.STOP);
        Node root = element("root",
                removed,
                element("completed"),
                stopping,
                element("unvisited"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_ENTIRELY_LAST_CHILD_THEN_PARENT_TAIL_STOP_variation1_ascentStops() {
        Element skipped = head(
                element("last-child", element("hidden")),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        Element parent = tail(
                element("parent", element("first-child"), skipped),
                NodeFilter.FilterResult.STOP);
        Node root = element("root", parent, element("unvisited-root-sibling"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_CHILDREN_THEN_TAIL_REMOVE_variation1_skippedSubtreeRemoved() {
        Element selected = head(
                element("selected",
                        element("unvisited-one"),
                        element("unvisited-two")),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        tail(selected, NodeFilter.FilterResult.REMOVE);
        Node root = element("root", selected, element("outside"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void ROOT_TAIL_RETURNS_SKIP_CHILDREN_variation1_terminalTailResult() {
        Element root = tail(
                element("root",
                        element("first"),
                        element("second", element("deep"))),
                NodeFilter.FilterResult.SKIP_CHILDREN);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void ROOT_TAIL_RETURNS_SKIP_ENTIRELY_variation1_terminalTailResult() {
        Element root = tail(
                element("root",
                        element("left", element("deep-left")),
                        element("right")),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void DEPTH_SELECTIVE_POLICY_variation1_skipAndDeepRemoval() {
        Element skipped = element("skipped-branch",
                element("hidden-one"),
                element("hidden-two"));
        skipped.addClass("skip-at-depth-one");

        Element deepRemoved = element("deep-removed", element("pruned"));
        deepRemoved.addClass("remove-deep");

        Node root = element("root",
                skipped,
                element("traversed-branch",
                        element("retained-deep"),
                        deepRemoved,
                        element("later-deep")));
        exercise(new DepthSelectiveFilter(), root);
    }

    @Test
    public void MULTIPLE_HEAD_REMOVALS_WITH_INDEX_SHIFT_variation1_consecutiveSiblings() {
        Element firstRemoved = head(element("remove-one"), NodeFilter.FilterResult.REMOVE);
        Element secondRemoved = head(element("remove-two"), NodeFilter.FilterResult.REMOVE);
        Element thirdRemoved = head(element("remove-three"), NodeFilter.FilterResult.REMOVE);
        Node root = element("root",
                element("retained-before"),
                firstRemoved,
                secondRemoved,
                thirdRemoved,
                element("retained-after", element("visited-child")));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void STOP_PREVENTS_PLANNED_LATER_REMOVAL_variation1_laterNodeRetained() {
        Element stopping = head(element("stop-here"), NodeFilter.FilterResult.STOP);
        Element plannedRemoval = head(
                element("would-be-removed", element("child")),
                NodeFilter.FilterResult.REMOVE);
        Node root = element("root",
                element("completed"),
                stopping,
                plannedRemoval,
                element("also-unvisited"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void NO_POLICY_MATCH_FULL_TRAVERSAL_variation1_absentPredicate() {
        Node root = element("root",
                element("left",
                        element("left-deep"),
                        new TextNode("left text")),
                element("right",
                        element("right-deep")),
                element("last"));
        exercise(new NoMatchFilter(), root);
    }

    @Test
    public void LAST_ROOT_CHILD_COMPLETION_BOUNDARY_variation1_lastLeafCompletes() {
        Node root = element("root",
                element("first", element("nested")),
                element("middle"),
                element("last"));
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void REMOVE_ONLY_CHILD_SUBTREE_variation1_onlyChildRemovedOnAscent() {
        Element onlyChild = head(
                element("only-child",
                        element("descendant-one"),
                        element("descendant-two")),
                NodeFilter.FilterResult.REMOVE);
        Node root = element("root", onlyChild);
        exercise(new ScriptedFilter(), root);
    }

    @Test
    public void SKIP_ENTIRELY_ONLY_CHILD_variation1_onlySubtreeRetained() {
        Element onlyChild = head(
                element("only-child",
                        element("hidden-one"),
                        element("hidden-two", element("hidden-deep"))),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
        Node root = element("root", onlyChild);
        exercise(new ScriptedFilter(), root);
    }
}
