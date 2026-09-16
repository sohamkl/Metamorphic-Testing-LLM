import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private enum Phase {
        HEAD, TAIL
    }

    private static final class Rule {
        final Phase phase;
        final String path;
        final NodeFilter.FilterResult result;

        Rule(Phase phase, String path, NodeFilter.FilterResult result) {
            this.phase = phase;
            this.path = path;
            this.result = result;
        }
    }

    private static final class Policy {
        final List<Rule> rules;

        Policy(Rule... rules) {
            this.rules = List.of(rules);
        }

        NodeFilter.FilterResult resultFor(Phase phase, String path) {
            for (Rule rule : rules) {
                if (rule.phase == phase && rule.path.equals(path)) {
                    return rule.result;
                }
            }
            return NodeFilter.FilterResult.CONTINUE;
        }

        boolean hasNonContinueRule() {
            for (Rule rule : rules) {
                if (rule.result != NodeFilter.FilterResult.CONTINUE) {
                    return true;
                }
            }
            return false;
        }
    }

    private static final class Source {
        final Node root;
        final Policy policy;

        Source(Node root, Policy policy) {
            this.root = root;
            this.policy = policy;
        }
    }

    private static final class ExecutionOutput {
        final NodeFilter.FilterResult terminalResult;
        final List<String> callbackSequence;
        final List<NodeFilter.FilterResult> actions;
        final String finalOuterHtml;

        ExecutionOutput(
                NodeFilter.FilterResult terminalResult,
                List<String> callbackSequence,
                List<NodeFilter.FilterResult> actions,
                String finalOuterHtml) {
            this.terminalResult = terminalResult;
            this.callbackSequence = callbackSequence;
            this.actions = actions;
            this.finalOuterHtml = finalOuterHtml;
        }

        boolean containsNonContinueAction() {
            for (NodeFilter.FilterResult action : actions) {
                if (action != NodeFilter.FilterResult.CONTINUE) {
                    return true;
                }
            }
            return false;
        }
    }

    private static final class RecordingFilter implements NodeFilter {
        private final Policy policy;
        private final Map<Node, String> initialPaths;
        private final List<String> callbackSequence = new ArrayList<>();
        private final List<NodeFilter.FilterResult> actions = new ArrayList<>();

        RecordingFilter(Policy policy, Node root) {
            this.policy = policy;
            this.initialPaths = new IdentityHashMap<>();
            recordInitialPaths(root, "");
        }

        private void recordInitialPaths(Node node, String path) {
            initialPaths.put(node, path);
            for (int i = 0; i < node.childNodeSize(); i++) {
                String childPath = path.isEmpty() ? Integer.toString(i) : path + "/" + i;
                recordInitialPaths(node.childNode(i), childPath);
            }
        }

        @Override
        public NodeFilter.FilterResult head(Node node, int depth) {
            return record(Phase.HEAD, node, depth);
        }

        @Override
        public NodeFilter.FilterResult tail(Node node, int depth) {
            return record(Phase.TAIL, node, depth);
        }

        private NodeFilter.FilterResult record(Phase phase, Node node, int depth) {
            String path = initialPaths.get(node);
            Assertions.assertNotNull(path, "Every callback node must belong to the initial tree");
            NodeFilter.FilterResult result = policy.resultFor(phase, path);
            callbackSequence.add(phase + "|" + depth + "|" + path + "|" + result);
            actions.add(result);
            return result;
        }
    }

    private static Element t(String tagName, Node... children) {
        Element element = new Element(tagName);
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static Rule h(String path, NodeFilter.FilterResult result) {
        return new Rule(Phase.HEAD, path, result);
    }

    private static Rule tail(String path, NodeFilter.FilterResult result) {
        return new Rule(Phase.TAIL, path, result);
    }

    private static Policy continuePolicy() {
        return new Policy();
    }

    private static Source source(Node root, Rule... rules) {
        return new Source(root, new Policy(rules));
    }

    private static Source generateFollowUp(Source source) {
        Assertions.assertNotNull(source);
        Assertions.assertNotNull(source.root);
        Assertions.assertNull(source.root.parentNode());
        Node clone = source.root.clone();
        Assertions.assertNotSame(source.root, clone);
        Assertions.assertNull(clone.parentNode());
        Assertions.assertEquals(source.root.outerHtml(), clone.outerHtml());
        assertNoSharedIdentities(source.root, clone);
        return new Source(clone, source.policy);
    }

    private static void assertNoSharedIdentities(Node original, Node clone) {
        Assertions.assertNotSame(original, clone);
        Assertions.assertEquals(original.childNodeSize(), clone.childNodeSize());
        for (int i = 0; i < original.childNodeSize(); i++) {
            assertNoSharedIdentities(original.childNode(i), clone.childNode(i));
        }
    }

    private static ExecutionOutput run(Source source) {
        RecordingFilter filter = new RecordingFilter(source.policy, source.root);
        NodeFilter.FilterResult result = NodeTraversor.filter(filter, source.root);
        return new ExecutionOutput(
                result,
                List.copyOf(filter.callbackSequence),
                List.copyOf(filter.actions),
                source.root.outerHtml());
    }

    private static void assertMetamorphicRelationFor(Source source) {
        Assertions.assertNotNull(source);
        Assertions.assertNotNull(source.root);
        Assertions.assertNull(source.root.parentNode());

        Source followUp = generateFollowUp(source);
        ExecutionOutput sourceOutput = run(source);
        ExecutionOutput followUpOutput = run(followUp);

        assertMetamorphicRelation(sourceOutput, followUpOutput);

        if (source.policy.hasNonContinueRule()) {
            Assertions.assertTrue(sourceOutput.containsNonContinueAction());
            Assertions.assertTrue(followUpOutput.containsNonContinueAction());
        }
    }

    private static void assertMetamorphicRelation(
            ExecutionOutput sourceOutput,
            ExecutionOutput followUpOutput) {
        Assertions.assertEquals(sourceOutput.terminalResult, followUpOutput.terminalResult);
        Assertions.assertEquals(sourceOutput.callbackSequence, followUpOutput.callbackSequence);
        Assertions.assertEquals(sourceOutput.actions, followUpOutput.actions);
        Assertions.assertEquals(sourceOutput.finalOuterHtml, followUpOutput.finalOuterHtml);
    }

    @Test
    void CONTINUE_SINGLE_LEAF_ROOT_variation1_detachedLeaf() {
        assertMetamorphicRelationFor(new Source(t("r"), continuePolicy()));
    }

    @Test
    void CONTINUE_SINGLE_CHILD_CHAINS_variation1_depthOne() {
        assertMetamorphicRelationFor(new Source(t("r", t("a")), continuePolicy()));
    }

    @Test
    void CONTINUE_SINGLE_CHILD_CHAINS_variation2_depthFour() {
        assertMetamorphicRelationFor(
                new Source(t("r", t("a", t("b", t("c", t("d"))))), continuePolicy()));
    }

    @Test
    void CONTINUE_SIBLING_FANOUT_variation1_twoLeaves() {
        assertMetamorphicRelationFor(new Source(t("r", t("a"), t("b")), continuePolicy()));
    }

    @Test
    void CONTINUE_SIBLING_FANOUT_variation2_fiveLeaves() {
        assertMetamorphicRelationFor(
                new Source(t("r", t("a"), t("b"), t("c"), t("d"), t("e")), continuePolicy()));
    }

    @Test
    void CONTINUE_MIXED_BRANCHING_TREE_variation1_depthTwo() {
        assertMetamorphicRelationFor(
                new Source(t("r", t("a", t("x"), t("y")), t("b")), continuePolicy()));
    }

    @Test
    void CONTINUE_MIXED_BRANCHING_TREE_variation2_depthFour() {
        assertMetamorphicRelationFor(
                new Source(
                        t("r",
                                t("a", t("x", t("p", t("q"))), t("y")),
                                t("b", t("z"))),
                        continuePolicy()));
    }

    @Test
    void STOP_AT_ROOT_HEAD_variation1_descendantsRemainUnvisited() {
        assertMetamorphicRelationFor(
                source(t("r", t("a", t("x")), t("b")), h("", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void STOP_AT_FIRST_CHILD_HEAD_variation1_twoChildren() {
        assertMetamorphicRelationFor(
                source(t("r", t("a"), t("b")), h("0", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void STOP_AT_LATER_SIBLING_HEAD_variation1_secondOfTwo() {
        assertMetamorphicRelationFor(
                source(t("r", t("a", t("x")), t("b")), h("1", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void STOP_AT_LATER_SIBLING_HEAD_variation2_middleOfFour() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b", t("x")), t("c"), t("d")),
                        h("2", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void STOP_AT_DEEP_DESCENDANT_HEAD_variation1_depthTwo() {
        assertMetamorphicRelationFor(
                source(t("r", t("a", t("x")), t("b")), h("0/0", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void STOP_AT_DEEP_DESCENDANT_HEAD_variation2_depthFour() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a", t("b", t("c", t("d")))), t("later")),
                        h("0/0/0/0", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void STOP_AT_LEAF_TAIL_variation1_leafWithNextSibling() {
        assertMetamorphicRelationFor(
                source(t("r", t("a"), t("b")), tail("0", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void STOP_AT_LEAF_TAIL_variation2_lastChildLeaf() {
        assertMetamorphicRelationFor(
                source(t("r", t("a"), t("b")), tail("1", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void STOP_AT_INTERNAL_TAIL_variation1_internalWithNextSibling() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a", t("x"), t("y")), t("b")),
                        tail("0", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void STOP_AT_INTERNAL_TAIL_variation2_internalLastChild() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b", t("x", t("z")))),
                        tail("1", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void STOP_AT_ROOT_TAIL_variation1_fullTraversalBeforeStop() {
        assertMetamorphicRelationFor(
                source(t("r", t("a", t("x")), t("b")), tail("", NodeFilter.FilterResult.STOP)));
    }

    @Test
    void SKIP_CHILDREN_AT_ROOT_variation1_grandchildPresent() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a", t("x")), t("b")),
                        h("", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    void SKIP_CHILDREN_INTERNAL_WITH_LATER_SIBLING_variation1_firstOfTwo() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a", t("x"), t("y")), t("b")),
                        h("0", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    void SKIP_CHILDREN_INTERNAL_WITH_LATER_SIBLING_variation2_middleOfThree() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b", t("x")), t("c")),
                        h("1", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    void SKIP_CHILDREN_LAST_CHILD_ASCENT_variation1_parentIsRoot() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b", t("x"))),
                        h("1", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    void SKIP_CHILDREN_LAST_CHILD_ASCENT_variation2_parentBelowRoot() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("p", t("a"), t("b", t("x")))),
                        h("0/1", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    void SKIP_CHILDREN_ON_LEAF_variation1_nonRootLeaf() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b", t("x"))),
                        h("0", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    void NONCONTINUE_RESULTS_FROM_TAIL_variation1_rootSkipChildren() {
        assertMetamorphicRelationFor(
                source(t("r", t("a")), tail("", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    void NONCONTINUE_RESULTS_FROM_TAIL_variation2_rootSkipEntirely() {
        assertMetamorphicRelationFor(
                source(t("r", t("a", t("x"))), tail("", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    void NONCONTINUE_RESULTS_FROM_TAIL_variation3_nonRootLastChildReset() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("p", t("leaf"))),
                        tail("0/0", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    void SKIP_ENTIRELY_AT_ROOT_variation1_descendantsPresent() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a", t("x")), t("b")),
                        h("", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    void SKIP_ENTIRELY_INTERNAL_SUBTREE_variation1_withNextSibling() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a", t("x"), t("y")), t("b")),
                        h("0", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    void SKIP_ENTIRELY_INTERNAL_SUBTREE_variation2_lastChild() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b", t("x", t("z")))),
                        h("1", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    void SKIP_ENTIRELY_LEAF_POSITIONS_variation1_leafWithNextSibling() {
        assertMetamorphicRelationFor(
                source(t("r", t("a"), t("b")), h("0", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    void SKIP_ENTIRELY_LEAF_POSITIONS_variation2_lastChildLeaf() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b"), t("c")),
                        h("2", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    void REMOVE_AT_ROOT_HEAD_variation1_detachedNonLeafRoot() {
        assertMetamorphicRelationFor(
                source(t("r", t("a", t("x"))), h("", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_LEAF_WITH_NEXT_SIBLING_HEAD_variation1_firstOfTwo() {
        assertMetamorphicRelationFor(
                source(t("r", t("a"), t("b")), h("0", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_LEAF_WITH_NEXT_SIBLING_HEAD_variation2_middleOfThree() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b"), t("c")),
                        h("1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_LAST_CHILD_HEAD_variation1_parentIsRoot() {
        assertMetamorphicRelationFor(
                source(t("r", t("a"), t("b")), h("1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_LAST_CHILD_HEAD_variation2_parentBelowRoot() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("p", t("a"), t("b"))),
                        h("0/1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_INTERNAL_SUBTREE_HEAD_variation1_withNextSibling() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a", t("x"), t("y")), t("b")),
                        h("0", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_INTERNAL_SUBTREE_HEAD_variation2_lastChild() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b", t("x", t("z")))),
                        h("1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_LEAF_WITH_NEXT_SIBLING_TAIL_variation1_firstOfTwo() {
        assertMetamorphicRelationFor(
                source(t("r", t("a"), t("b")), tail("0", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_LEAF_WITH_NEXT_SIBLING_TAIL_variation2_middleOfThree() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b"), t("c")),
                        tail("1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_LAST_CHILD_TAIL_variation1_parentIsRoot() {
        assertMetamorphicRelationFor(
                source(t("r", t("a"), t("b")), tail("1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_LAST_CHILD_TAIL_variation2_parentBelowRoot() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("p", t("a"), t("b"))),
                        tail("0/1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_INTERNAL_NODE_TAIL_variation1_withNextSibling() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a", t("x"), t("y")), t("b")),
                        tail("0", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_INTERNAL_NODE_TAIL_variation2_lastChild() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a"), t("b", t("x", t("z")))),
                        tail("1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_AT_ROOT_TAIL_variation1_completedDetachedRoot() {
        assertMetamorphicRelationFor(
                source(t("r", t("a", t("x")), t("b")), tail("", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void SKIP_THEN_REMOVE_LATER_SIBLING_variation1_pruneThenDelete() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("a", t("x"), t("y")), t("b"), t("c")),
                        h("0", NodeFilter.FilterResult.SKIP_CHILDREN),
                        h("1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_THEN_COMPLETE_ANCESTOR_TAILS_variation1_deepRemoval() {
        assertMetamorphicRelationFor(
                source(
                        t("r",
                                t("outer",
                                        t("inner", t("remove"), t("keep", t("leaf"))),
                                        t("outerLater")),
                                t("rootLater")),
                        h("0/0/0", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void MULTIPLE_REACHED_REMOVALS_variation1_twoIndependentSubtrees() {
        assertMetamorphicRelationFor(
                source(
                        t("r",
                                t("a", t("x")),
                                t("keep"),
                                t("b", t("y")),
                                t("later")),
                        h("0", NodeFilter.FilterResult.REMOVE),
                        h("2", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    void REMOVE_BEFORE_LATER_STOP_variation1_committedRemovalThenStop() {
        assertMetamorphicRelationFor(
                source(
                        t("r", t("remove", t("x")), t("stop"), t("unvisited")),
                        h("0", NodeFilter.FilterResult.REMOVE),
                        h("1", NodeFilter.FilterResult.STOP)));
    }
}
