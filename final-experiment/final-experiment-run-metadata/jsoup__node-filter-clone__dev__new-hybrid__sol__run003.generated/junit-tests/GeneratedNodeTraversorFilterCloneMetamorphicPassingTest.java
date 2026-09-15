import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final String NONE = "__none__";

    private static Element element(String name, Element... children) {
        Element element = new Element(name, "");
        for (Element child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static NodeFilter rule(
            String firstHeadNode,
            NodeFilter.FilterResult firstHeadResult,
            String secondHeadNode,
            NodeFilter.FilterResult secondHeadResult,
            String tailNode,
            NodeFilter.FilterResult tailResult) {
        return new RuleFilter(
                firstHeadNode,
                firstHeadResult,
                secondHeadNode,
                secondHeadResult,
                tailNode,
                tailResult);
    }

    private static final class ContinueFilter implements NodeFilter {
        public ContinueFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class RuleFilter implements NodeFilter {
        private final String firstHeadNode;
        private final FilterResult firstHeadResult;
        private final String secondHeadNode;
        private final FilterResult secondHeadResult;
        private final String tailNode;
        private final FilterResult tailResult;

        public RuleFilter(
                String firstHeadNode,
                FilterResult firstHeadResult,
                String secondHeadNode,
                FilterResult secondHeadResult,
                String tailNode,
                FilterResult tailResult) {
            this.firstHeadNode = firstHeadNode;
            this.firstHeadResult = firstHeadResult;
            this.secondHeadNode = secondHeadNode;
            this.secondHeadResult = secondHeadResult;
            this.tailNode = tailNode;
            this.tailResult = tailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (node.nodeName().equals(firstHeadNode)) {
                return firstHeadResult;
            }
            if (node.nodeName().equals(secondHeadNode)) {
                return secondHeadResult;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return node.nodeName().equals(tailNode)
                    ? tailResult
                    : FilterResult.CONTINUE;
        }
    }

    @Test
    public void SINGLE_ROOT_CONTINUE_variation1() {
        Element root = element("r");
        NodeFilter filter = new ContinueFilter();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_CHILD_DEPTH_FIRST_variation1() {
        Element root = element("r", element("a"));
        NodeFilter filter = new ContinueFilter();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LINEAR_DEPTH_TWO_variation1() {
        Element root = element("r", element("a", element("b")));
        NodeFilter filter = new ContinueFilter();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTIPLE_ROOT_CHILDREN_variation1() {
        Element root = element("r", element("a"), element("b"), element("c"));
        NodeFilter filter = new ContinueFilter();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BRANCHED_DEPTH_FIRST_ORDER_variation1() {
        Element root = element(
                "r",
                element("a", element("x"), element("y")),
                element("b", element("z")));
        NodeFilter filter = new ContinueFilter();
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STOP_AT_ROOT_HEAD_variation1() {
        Element root = element("r", element("a"));
        NodeFilter filter = rule("r", NodeFilter.FilterResult.STOP, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STOP_AT_FIRST_DESCENDANT_HEAD_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.STOP, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STOP_AT_DEEP_HEAD_variation1() {
        Element root = element("r", element("a", element("x")), element("b"));
        NodeFilter filter = rule("x", NodeFilter.FilterResult.STOP, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STOP_AT_LATER_SIBLING_HEAD_variation1() {
        Element root = element("r", element("a"), element("b"), element("c"));
        NodeFilter filter = rule("b", NodeFilter.FilterResult.STOP, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_variation1() {
        Element root = element("r", element("a", element("x")), element("b"));
        NodeFilter filter = rule("r", NodeFilter.FilterResult.SKIP_CHILDREN, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FIRST_INTERNAL_SKIP_CHILDREN_variation1() {
        Element root = element("r", element("a", element("x"), element("y")), element("b"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.SKIP_CHILDREN, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LAST_INTERNAL_SKIP_CHILDREN_variation1() {
        Element root = element("r", element("a"), element("b", element("x")));
        NodeFilter filter = rule("b", NodeFilter.FilterResult.SKIP_CHILDREN, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAF_SKIP_CHILDREN_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.SKIP_CHILDREN, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SKIP_CHILDREN_TAIL_STOP_variation1() {
        Element root = element("r", element("a", element("x")), element("b"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.SKIP_CHILDREN, NONE,
                NodeFilter.FilterResult.CONTINUE, "a", NodeFilter.FilterResult.STOP);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SKIP_CHILDREN_TAIL_REMOVE_variation1() {
        Element root = element("r", element("a", element("x")), element("b"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.SKIP_CHILDREN, NONE,
                NodeFilter.FilterResult.CONTINUE, "a", NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_TAIL_STOP_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule("r", NodeFilter.FilterResult.SKIP_CHILDREN, NONE,
                NodeFilter.FilterResult.CONTINUE, "r", NodeFilter.FilterResult.STOP);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_TAIL_REMOVE_variation1() {
        Element root = element("r", element("a", element("x")), element("b"));
        NodeFilter filter = rule("r", NodeFilter.FilterResult.SKIP_CHILDREN, NONE,
                NodeFilter.FilterResult.CONTINUE, "r", NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule("r", NodeFilter.FilterResult.SKIP_ENTIRELY, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FIRST_INTERNAL_SKIP_ENTIRELY_variation1() {
        Element root = element("r", element("a", element("x")), element("b"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.SKIP_ENTIRELY, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LAST_INTERNAL_SKIP_ENTIRELY_variation1() {
        Element root = element("r", element("a"), element("b", element("x"), element("y")));
        NodeFilter filter = rule("b", NodeFilter.FilterResult.SKIP_ENTIRELY, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAF_SKIP_ENTIRELY_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.SKIP_ENTIRELY, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_HEAD_REMOVE_variation1() {
        Element root = element("r", element("a", element("x")), element("b"));
        NodeFilter filter = rule("r", NodeFilter.FilterResult.REMOVE, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FIRST_LEAF_HEAD_REMOVE_variation1() {
        Element root = element("r", element("a"), element("b"), element("c"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.REMOVE, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIDDLE_LEAF_HEAD_REMOVE_variation1() {
        Element root = element("r", element("a"), element("b"), element("c"));
        NodeFilter filter = rule("b", NodeFilter.FilterResult.REMOVE, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LAST_LEAF_HEAD_REMOVE_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule("b", NodeFilter.FilterResult.REMOVE, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FIRST_INTERNAL_HEAD_REMOVE_variation1() {
        Element root = element("r", element("a", element("x"), element("y")), element("b"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.REMOVE, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONLY_CHILD_INTERNAL_HEAD_REMOVE_variation1() {
        Element root = element("r", element("a", element("x"), element("y")));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.REMOVE, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONSECUTIVE_HEAD_REMOVALS_variation1() {
        Element root = element("r", element("a"), element("b"), element("c"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.REMOVE, "b",
                NodeFilter.FilterResult.REMOVE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_THEN_LATER_STOP_variation1() {
        Element root = element("r", element("a"), element("b"), element("c"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.REMOVE, "b",
                NodeFilter.FilterResult.STOP, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAF_TAIL_STOP_WITH_SIBLING_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "a", NodeFilter.FilterResult.STOP);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LAST_LEAF_TAIL_STOP_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "b", NodeFilter.FilterResult.STOP);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERNAL_TAIL_STOP_variation1() {
        Element root = element("r", element("a", element("x"), element("y")), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "a", NodeFilter.FilterResult.STOP);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_TAIL_STOP_variation1() {
        Element root = element("r", element("a", element("x")), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "r", NodeFilter.FilterResult.STOP);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAF_TAIL_REMOVE_WITH_SIBLING_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "a", NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LAST_LEAF_TAIL_REMOVE_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "b", NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERNAL_TAIL_REMOVE_WITH_SIBLING_variation1() {
        Element root = element("r", element("a", element("x"), element("y")), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "a", NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LAST_INTERNAL_TAIL_REMOVE_variation1() {
        Element root = element("r", element("a"), element("b", element("x"), element("y")));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "b", NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONLY_CHILD_TAIL_REMOVE_variation1() {
        Element root = element("r", element("a", element("x")));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "a", NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_TAIL_REMOVE_variation1() {
        Element root = element("r", element("a", element("x")), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "r", NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONROOT_TAIL_SKIP_CHILDREN_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "a", NodeFilter.FilterResult.SKIP_CHILDREN);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ASCENT_TAIL_SKIP_CHILDREN_variation1() {
        Element root = element("r", element("a", element("x")));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "x", NodeFilter.FilterResult.SKIP_CHILDREN);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONROOT_TAIL_SKIP_ENTIRELY_variation1() {
        Element root = element("r", element("a"), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "a", NodeFilter.FilterResult.SKIP_ENTIRELY);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ASCENT_TAIL_SKIP_ENTIRELY_variation1() {
        Element root = element("r", element("a", element("x")));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "x", NodeFilter.FilterResult.SKIP_ENTIRELY);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_TAIL_SKIP_CHILDREN_variation1() {
        Element root = element(
                "r",
                element("a", element("x"), element("y")),
                element("b", element("z")));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "r", NodeFilter.FilterResult.SKIP_CHILDREN);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ROOT_TAIL_SKIP_ENTIRELY_variation1() {
        Element root = element("r", element("a", element("x")), element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "r", NodeFilter.FilterResult.SKIP_ENTIRELY);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_SKIP_AND_REMOVE_variation1() {
        Element root = element(
                "r",
                element("a", element("x")),
                element("b", element("y")),
                element("c"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.SKIP_CHILDREN, "b",
                NodeFilter.FilterResult.REMOVE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_SKIP_ENTIRELY_AND_DEEP_TRAVERSAL_variation1() {
        Element root = element(
                "r",
                element("a", element("x"), element("y")),
                element("b", element("z", element("q"))));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.SKIP_ENTIRELY, NONE,
                NodeFilter.FilterResult.CONTINUE, NONE, NodeFilter.FilterResult.CONTINUE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void REMOVE_COMPLETED_PARENT_THEN_VISIT_SIBLING_variation1() {
        Element root = element(
                "r",
                element("a", element("x"), element("y", element("q"))),
                element("b"));
        NodeFilter filter = rule(NONE, NodeFilter.FilterResult.CONTINUE, NONE,
                NodeFilter.FilterResult.CONTINUE, "a", NodeFilter.FilterResult.REMOVE);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PRIOR_REMOVAL_THEN_TAIL_STOP_variation1() {
        Element root = element("r", element("a"), element("b"), element("c"));
        NodeFilter filter = rule("a", NodeFilter.FilterResult.REMOVE, NONE,
                NodeFilter.FilterResult.CONTINUE, "b", NodeFilter.FilterResult.STOP);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
