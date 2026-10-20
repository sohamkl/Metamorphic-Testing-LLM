import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final String NO_MATCH = "__no_matching_node__";

    private static Element detachedRoot(String html) {
        Element root = Jsoup.parseBodyFragment(html).body().child(0);
        root.remove();
        return root;
    }

    private static Policy policy(
            String headId,
            NodeFilter.FilterResult matchingHeadResult,
            NodeFilter.FilterResult defaultHeadResult,
            String tailId,
            NodeFilter.FilterResult matchingTailResult,
            NodeFilter.FilterResult defaultTailResult) {
        return new Policy(
                headId,
                matchingHeadResult,
                defaultHeadResult,
                tailId,
                matchingTailResult,
                defaultTailResult);
    }

    private static Policy allContinue() {
        return policy(
                NO_MATCH,
                NodeFilter.FilterResult.CONTINUE,
                NodeFilter.FilterResult.CONTINUE,
                NO_MATCH,
                NodeFilter.FilterResult.CONTINUE,
                NodeFilter.FilterResult.CONTINUE);
    }

    private static void exercise(NodeFilter sourceFilter, Node sourceRoot) {
        Object[] followUp =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static class Policy implements NodeFilter {
        private final String headId;
        private final FilterResult matchingHeadResult;
        private final FilterResult defaultHeadResult;
        private final String tailId;
        private final FilterResult matchingTailResult;
        private final FilterResult defaultTailResult;

        private Policy(
                String headId,
                FilterResult matchingHeadResult,
                FilterResult defaultHeadResult,
                String tailId,
                FilterResult matchingTailResult,
                FilterResult defaultTailResult) {
            this.headId = headId;
            this.matchingHeadResult = matchingHeadResult;
            this.defaultHeadResult = defaultHeadResult;
            this.tailId = tailId;
            this.matchingTailResult = matchingTailResult;
            this.defaultTailResult = defaultTailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return headId.equals(node.attr("id"))
                    ? matchingHeadResult
                    : defaultHeadResult;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return tailId.equals(node.attr("id"))
                    ? matchingTailResult
                    : defaultTailResult;
        }
    }

    private static final class MultiControlPolicy extends Policy {
        private final String skippedId;
        private final FilterResult skippedResult;
        private final String removedId;
        private final FilterResult removedResult;

        private MultiControlPolicy(
                String skippedId,
                FilterResult skippedResult,
                String removedId,
                FilterResult removedResult) {
            super(
                    NO_MATCH,
                    FilterResult.CONTINUE,
                    FilterResult.CONTINUE,
                    NO_MATCH,
                    FilterResult.CONTINUE,
                    FilterResult.CONTINUE);
            this.skippedId = skippedId;
            this.skippedResult = skippedResult;
            this.removedId = removedId;
            this.removedResult = removedResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            String id = node.attr("id");
            if (skippedId.equals(id)) {
                return skippedResult;
            }
            if (removedId.equals(id)) {
                return removedResult;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    @Test
    public void DETACHED_LEAF_FULL_CONTINUE_variation1_emptyElement() {
        Element root = detachedRoot("<leaf id='r'></leaf>");
        exercise(allContinue(), root);
    }

    @Test
    public void DETACHED_LEAF_FULL_CONTINUE_variation2_distinctLeafTag() {
        Element root = detachedRoot("<marker id='r'></marker>");
        exercise(allContinue(), root);
    }

    @Test
    public void ROOT_HEAD_STOP_variation1_populatedBranchingRoot() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'><x id='x'></x></a><b id='b'></b></root>");
        Policy filter = policy(
                "r", NodeFilter.FilterResult.STOP, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void ROOT_HEAD_STOP_variation2_leafRoot() {
        Element root = detachedRoot("<root id='r'></root>");
        Policy filter = policy(
                "r", NodeFilter.FilterResult.STOP, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void LEAF_TAIL_STOP_variation1_plainLeaf() {
        Element root = detachedRoot("<leaf id='r'></leaf>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "r", NodeFilter.FilterResult.STOP, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void LEAF_TAIL_STOP_variation2_customLeafTag() {
        Element root = detachedRoot("<terminal id='r'></terminal>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "r", NodeFilter.FilterResult.STOP, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void LEAF_HEAD_SKIP_CHILDREN_variation1_noChildren() {
        Element root = detachedRoot("<leaf id='r'></leaf>");
        Policy filter = policy(
                "r", NodeFilter.FilterResult.SKIP_CHILDREN, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void LEAF_HEAD_SKIP_ENTIRELY_variation1_noTailEligible() {
        Element root = detachedRoot("<leaf id='r'></leaf>");
        Policy filter = policy(
                "r", NodeFilter.FilterResult.SKIP_ENTIRELY, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void LEAF_HEAD_REMOVE_variation1_detachedBoundary() {
        Element root = detachedRoot("<leaf id='r'></leaf>");
        Policy filter = policy(
                "r", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void LEAF_TAIL_REMOVE_variation1_detachedBoundary() {
        Element root = detachedRoot("<leaf id='r'></leaf>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "r", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_PRUNES_DESCENT_variation1_nestedChildren() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'><deep id='deep'></deep></a><b id='b'></b></root>");
        Policy filter = policy(
                "r", NodeFilter.FilterResult.SKIP_CHILDREN, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_PRUNES_DESCENT_AND_TAIL_variation1_mixedTree() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'><x id='x'></x></a><b id='b'><y id='y'></y></b></root>");
        Policy filter = policy(
                "r", NodeFilter.FilterResult.SKIP_ENTIRELY, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void ROOT_REMOVE_PRUNES_DESCENT_WITHOUT_DETACHING_ROOT_variation1_populatedRoot() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'></a><b id='b'><x id='x'></x></b></root>");
        Policy filter = policy(
                "r", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void ROOT_TAIL_SKIP_CHILDREN_TERMINAL_variation1_afterDeepTraversal() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'><x id='x'></x></a><b id='b'></b></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "r", NodeFilter.FilterResult.SKIP_CHILDREN, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void DEEP_SINGLE_CHILD_CHAIN_variation1_depthThree() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'><b id='b'><c id='c'></c></b></a></root>");
        exercise(allContinue(), root);
    }

    @Test
    public void DEEP_SINGLE_CHILD_CHAIN_variation2_depthFour() {
        Element root = detachedRoot(
                "<root id='r'><n1 id='n1'><n2 id='n2'><n3 id='n3'><n4 id='n4'></n4></n3></n2></n1></root>");
        exercise(allContinue(), root);
    }

    @Test
    public void ROOT_MULTIPLE_LEAF_SIBLINGS_variation1_threeChildren() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'></a><b id='b'></b><c id='c'></c></root>");
        exercise(allContinue(), root);
    }

    @Test
    public void ROOT_MULTIPLE_LEAF_SIBLINGS_variation2_fourDistinctChildren() {
        Element root = detachedRoot(
                "<root id='r'><one id='one'></one><two id='two'></two>"
                        + "<three id='three'></three><four id='four'></four></root>");
        exercise(allContinue(), root);
    }

    @Test
    public void MIXED_BRANCHING_DEPTH_FIRST_ORDER_variation1_firstBranchNested() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'><a1 id='a1'></a1><a2 id='a2'></a2></a>"
                        + "<b id='b'></b></root>");
        exercise(allContinue(), root);
    }

    @Test
    public void MIXED_BRANCHING_DEPTH_FIRST_ORDER_variation2_multipleNestedBranches() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'><x id='x'><z id='z'></z></x></a>"
                        + "<b id='b'><y id='y'></y></b><c id='c'></c></root>");
        exercise(allContinue(), root);
    }

    @Test
    public void DESCENDANT_HEAD_STOP_variation1_firstDescendant() {
        Element root = detachedRoot(
                "<root id='r'><stop id='target'></stop><later id='later'></later></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.STOP, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void DESCENDANT_HEAD_STOP_variation2_laterSiblingDescendant() {
        Element root = detachedRoot(
                "<root id='r'><first id='first'><x id='x'></x></first>"
                        + "<stop id='target'></stop><later id='later'></later></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.STOP, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void DESCENDANT_TAIL_STOP_variation1_siblingTransitionPath() {
        Element root = detachedRoot(
                "<root id='r'><a id='target'></a><b id='later'></b></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "target", NodeFilter.FilterResult.STOP, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void DESCENDANT_TAIL_STOP_variation2_ascentPath() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'><deep id='target'></deep></a></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "target", NodeFilter.FilterResult.STOP, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_WITH_NEXT_SIBLING_variation1_firstInternalNode() {
        Element root = detachedRoot(
                "<root id='r'><a id='target'><hidden id='hidden'></hidden></a>"
                        + "<b id='next'></b></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.SKIP_CHILDREN, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_WITH_NEXT_SIBLING_variation2_middleInternalNode() {
        Element root = detachedRoot(
                "<root id='r'><first id='first'></first>"
                        + "<middle id='target'><hidden1 id='h1'></hidden1><hidden2 id='h2'></hidden2></middle>"
                        + "<last id='next'></last></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.SKIP_CHILDREN, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void LAST_CHILD_SKIP_CHILDREN_ASCENT_variation1_childBearingLastChild() {
        Element root = detachedRoot(
                "<root id='r'><first id='first'></first>"
                        + "<last id='target'><hidden id='hidden'></hidden></last></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.SKIP_CHILDREN, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void INTERNAL_SKIP_ENTIRELY_WITH_NEXT_SIBLING_variation1_firstBranch() {
        Element root = detachedRoot(
                "<root id='r'><a id='target'><hidden id='hidden'></hidden></a>"
                        + "<b id='next'></b></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.SKIP_ENTIRELY, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void INTERNAL_SKIP_ENTIRELY_WITH_NEXT_SIBLING_variation2_middleBranch() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'></a><b id='target'><x id='x'><y id='y'></y></x></b>"
                        + "<c id='next'></c></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.SKIP_ENTIRELY, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void LAST_CHILD_SKIP_ENTIRELY_ASCENT_variation1_deepLastChild() {
        Element root = detachedRoot(
                "<root id='r'><parent id='parent'><last id='target'><hidden id='hidden'></hidden></last></parent></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.SKIP_ENTIRELY, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void HEAD_REMOVE_WITH_NEXT_SIBLING_variation1_leafBeforeSibling() {
        Element root = detachedRoot(
                "<root id='r'><remove id='target'></remove><keep id='next'></keep></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void HEAD_REMOVE_WITH_NEXT_SIBLING_variation2_internalBeforeSibling() {
        Element root = detachedRoot(
                "<root id='r'><remove id='target'><x id='x'><y id='y'></y></x></remove>"
                        + "<keep id='next'></keep></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void HEAD_REMOVE_LAST_CHILD_variation1_parentCapturePath() {
        Element root = detachedRoot(
                "<root id='r'><parent id='parent'><first id='first'></first>"
                        + "<remove id='target'><hidden id='hidden'></hidden></remove></parent></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void TAIL_REMOVE_WITH_NEXT_SIBLING_variation1_leafSubtree() {
        Element root = detachedRoot(
                "<root id='r'><remove id='target'></remove><keep id='next'></keep></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "target", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void TAIL_REMOVE_WITH_NEXT_SIBLING_variation2_completedInternalSubtree() {
        Element root = detachedRoot(
                "<root id='r'><remove id='target'><x id='x'></x><y id='y'></y></remove>"
                        + "<keep id='next'></keep></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "target", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void TAIL_REMOVE_LAST_CHILD_variation1_ascentRemovalPath() {
        Element root = detachedRoot(
                "<root id='r'><parent id='parent'><remove id='target'><x id='x'></x></remove></parent></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "target", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void HEAD_REMOVE_INTERNAL_SUBTREE_variation1_withFollowingSibling() {
        Element root = detachedRoot(
                "<root id='r'><remove id='target'><a id='a'><deep id='deep'></deep></a></remove>"
                        + "<keep id='keep'></keep></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void HEAD_REMOVE_INTERNAL_SUBTREE_variation2_lastInternalSubtree() {
        Element root = detachedRoot(
                "<root id='r'><keep id='keep'></keep>"
                        + "<remove id='target'><a id='a'></a><b id='b'><deep id='deep'></deep></b></remove></root>");
        Policy filter = policy(
                "target", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void TAIL_REMOVE_COMPLETED_SUBTREE_variation1_branchWithLeaves() {
        Element root = detachedRoot(
                "<root id='r'><remove id='target'><a id='a'></a><b id='b'></b></remove>"
                        + "<keep id='keep'></keep></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "target", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void TAIL_REMOVE_COMPLETED_SUBTREE_variation2_deepCompletedBranch() {
        Element root = detachedRoot(
                "<root id='r'><keep id='keep'></keep>"
                        + "<remove id='target'><a id='a'><b id='b'><c id='c'></c></b></a></remove></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "target", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void MULTILEVEL_ASCENT_AFTER_DEEP_LAST_LEAF_variation1_depthThree() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'><b id='b'><leaf id='leaf'></leaf></b></a></root>");
        exercise(allContinue(), root);
    }

    @Test
    public void MULTILEVEL_ASCENT_AFTER_DEEP_LAST_LEAF_variation2_depthFourAfterSibling() {
        Element root = detachedRoot(
                "<root id='r'><first id='first'></first>"
                        + "<a id='a'><b id='b'><c id='c'><leaf id='leaf'></leaf></c></b></a></root>");
        exercise(allContinue(), root);
    }

    @Test
    public void NONROOT_TAIL_SKIP_CHILDREN_WITH_SIBLING_variation1_tailControlDoesNotPruneSibling() {
        Element root = detachedRoot(
                "<root id='r'><a id='target'><x id='x'></x></a>"
                        + "<b id='next'><y id='y'></y></b></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "target", NodeFilter.FilterResult.SKIP_CHILDREN, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void NONROOT_TAIL_SKIP_ENTIRELY_DURING_ASCENT_variation1_lastChild() {
        Element root = detachedRoot(
                "<root id='r'><parent id='parent'><last id='target'></last></parent></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "target", NodeFilter.FilterResult.SKIP_ENTIRELY, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void ROOT_TAIL_SKIP_ENTIRELY_TERMINAL_variation1_populatedRoot() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'></a><b id='b'><x id='x'></x></b></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "r", NodeFilter.FilterResult.SKIP_ENTIRELY, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void ROOT_TAIL_REMOVE_AFTER_DESCENT_variation1_singleChildChain() {
        Element root = detachedRoot(
                "<root id='r'><a id='a'><b id='b'></b></a></root>");
        Policy filter = policy(
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE,
                "r", NodeFilter.FilterResult.REMOVE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }

    @Test
    public void MULTIPLE_CONTROL_RESULTS_IN_ONE_TREE_variation1_skipChildrenAndRemove() {
        Element root = detachedRoot(
                "<root id='r'>"
                        + "<skip id='skip'><retained id='retained'></retained></skip>"
                        + "<remove id='remove'><gone id='gone'></gone></remove>"
                        + "<keep id='keep'><visited id='visited'></visited></keep>"
                        + "</root>");
        Policy filter = new MultiControlPolicy(
                "skip", NodeFilter.FilterResult.SKIP_CHILDREN,
                "remove", NodeFilter.FilterResult.REMOVE);
        exercise(filter, root);
    }

    @Test
    public void MULTIPLE_CONTROL_RESULTS_IN_ONE_TREE_variation2_skipEntirelyAndRemove() {
        Element root = detachedRoot(
                "<root id='r'>"
                        + "<keep1 id='keep1'><v1 id='v1'></v1></keep1>"
                        + "<skip id='skip'><retained1 id='retained1'></retained1>"
                        + "<retained2 id='retained2'></retained2></skip>"
                        + "<remove id='remove'><gone id='gone'><deep id='deep'></deep></gone></remove>"
                        + "<keep2 id='keep2'><v2 id='v2'></v2></keep2>"
                        + "</root>");
        Policy filter = new MultiControlPolicy(
                "skip", NodeFilter.FilterResult.SKIP_ENTIRELY,
                "remove", NodeFilter.FilterResult.REMOVE);
        exercise(filter, root);
    }

    @Test
    public void EXACTLY_ONE_ROOT_CHILD_BOUNDARY_variation1_directAscent() {
        Element root = detachedRoot(
                "<root id='r'><only id='only'></only></root>");
        exercise(allContinue(), root);
    }

    @Test
    public void MIXED_RUNTIME_NODE_ROLES_variation1_elementsAndTextNodes() {
        Element root = detachedRoot(
                "<root id='r'><branch id='branch'>alpha<leaf id='leaf'></leaf>beta</branch>"
                        + "<other id='other'>gamma</other></root>");
        Policy filter = policy(
                "leaf", NodeFilter.FilterResult.SKIP_CHILDREN, NodeFilter.FilterResult.CONTINUE,
                NO_MATCH, NodeFilter.FilterResult.CONTINUE, NodeFilter.FilterResult.CONTINUE);
        exercise(filter, root);
    }
}
