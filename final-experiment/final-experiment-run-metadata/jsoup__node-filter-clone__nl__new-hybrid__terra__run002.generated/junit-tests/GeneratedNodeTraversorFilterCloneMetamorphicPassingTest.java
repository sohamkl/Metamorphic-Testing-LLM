import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final class Rule {
        final boolean head;
        final String id;
        final NodeFilter.FilterResult result;

        Rule(boolean head, String id, NodeFilter.FilterResult result) {
            this.head = head;
            this.id = id;
            this.result = result;
        }
    }

    private static final class RecordingFilter implements NodeFilter {
        private final Map<String, NodeFilter.FilterResult> rules = new HashMap<>();
        final List<String> callbacks = new ArrayList<>();
        boolean nonContinueSeen;

        RecordingFilter(Rule... configured) {
            for (Rule rule : configured)
                rules.put((rule.head ? "H:" : "T:") + rule.id, rule.result);
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return record(true, node, depth);
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return record(false, node, depth);
        }

        private FilterResult record(boolean head, Node node, int depth) {
            String id = node instanceof Element ? ((Element) node).attr("id") : node.nodeName();
            FilterResult result = rules.getOrDefault((head ? "H:" : "T:") + id, FilterResult.CONTINUE);
            callbacks.add((head ? "H" : "T") + ":" + id + ":" + depth + ":" + result);
            if (result != FilterResult.CONTINUE)
                nonContinueSeen = true;
            return result;
        }
    }

    private static final class Outcome {
        final NodeFilter.FilterResult result;
        final List<String> callbacks;
        final boolean nonContinueSeen;
        final String outerHtml;

        Outcome(NodeFilter.FilterResult result, List<String> callbacks, boolean nonContinueSeen, String outerHtml) {
            this.result = result;
            this.callbacks = callbacks;
            this.nonContinueSeen = nonContinueSeen;
            this.outerHtml = outerHtml;
        }
    }

    private static Element n(String id, Node... children) {
        Element element = new Element("n", "");
        element.attr("id", id);
        for (Node child : children)
            element.appendChild(child);
        return element;
    }

    private static Rule h(String id, NodeFilter.FilterResult result) {
        return new Rule(true, id, result);
    }

    private static Rule t(String id, NodeFilter.FilterResult result) {
        return new Rule(false, id, result);
    }

    private static Outcome run(Node root, Rule... rules) {
        RecordingFilter filter = new RecordingFilter(rules);
        NodeFilter.FilterResult result = NodeTraversor.filter(filter, root);
        return new Outcome(result, filter.callbacks, filter.nonContinueSeen, root.outerHtml());
    }

    private static void assertMetamorphicRelation(Outcome source, Outcome followUp) {
        assertEquals(source.result, followUp.result);
        assertEquals(source.callbacks, followUp.callbacks);
        assertEquals(source.nonContinueSeen, followUp.nonContinueSeen);
        assertEquals(source.outerHtml, followUp.outerHtml);
    }

    private static void assertMetamorphicRelationFor(Node source, Rule... rules) {
        assertNotNull(source);
        assertNull(source.parentNode());
        Node followUp = source.clone();
        assertNotNull(followUp);
        assertNull(followUp.parentNode());
        assertEquals(source.outerHtml(), followUp.outerHtml());

        Outcome sourceOutput = run(source, rules);
        Outcome followUpOutput = run(followUp, rules);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
        boolean configuredNonContinue = Arrays.stream(rules)
            .anyMatch(rule -> rule.result != NodeFilter.FilterResult.CONTINUE);
        if (configuredNonContinue) {
            assertTrue(sourceOutput.nonContinueSeen);
            assertTrue(followUpOutput.nonContinueSeen);
        } else {
            assertFalse(sourceOutput.nonContinueSeen);
            assertFalse(followUpOutput.nonContinueSeen);
        }
    }

    @Test
    public void LEAF_CONTINUE_variation1() {
        assertMetamorphicRelationFor(n("r"));
    }

    @Test
    public void ONE_CHILD_CONTINUE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a")));
    }

    @Test
    public void TWO_LEAF_SIBLINGS_CONTINUE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b")));
    }

    @Test
    public void THREE_LEAF_SIBLINGS_CONTINUE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b"), n("c")));
    }

    @Test
    public void DEPTH_TWO_CHAIN_CONTINUE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("b"))));
    }

    @Test
    public void DEPTH_FOUR_CHAIN_CONTINUE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("b", n("c", n("d"))))));
    }

    @Test
    public void BRANCHED_TREE_CONTINUE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b", n("c"), n("d"))));
    }

    @Test
    public void INTERNAL_FIRST_SIBLING_CONTINUE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("b")), n("c")));
    }

    @Test
    public void NON_ELEMENT_LEAF_ROOT_CONTINUE_variation1() {
        assertMetamorphicRelationFor(new TextNode("plain"));
    }

    @Test
    public void HEAD_STOP_AT_ROOT_variation1() {
        assertMetamorphicRelationFor(n("r", n("a")), h("r", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void HEAD_STOP_AT_FIRST_CHILD_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b")), h("a", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void HEAD_STOP_AT_MIDDLE_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b"), n("c")), h("b", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void HEAD_STOP_AT_DEEPEST_NODE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("b", n("c")))), h("c", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void TAIL_STOP_AT_LEAF_ROOT_variation1() {
        assertMetamorphicRelationFor(n("r"), t("r", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void TAIL_STOP_AT_FIRST_CHILD_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b")), t("a", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void TAIL_STOP_AT_LAST_CHILD_ASCENT_variation1() {
        assertMetamorphicRelationFor(n("r", n("a")), t("a", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void TAIL_STOP_AT_INTERNAL_NODE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("b")), n("c")), t("a", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void TAIL_STOP_AT_ROOT_AFTER_DESCENDANTS_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("b")), n("c")), t("r", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_ROOT_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("b"))), h("r", NodeFilter.FilterResult.SKIP_CHILDREN));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_FIRST_INTERNAL_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x")), n("b")),
            h("a", NodeFilter.FilterResult.SKIP_CHILDREN));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_LAST_INTERNAL_CHILD_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x"))),
            h("a", NodeFilter.FilterResult.SKIP_CHILDREN));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_LEAF_variation1() {
        assertMetamorphicRelationFor(n("r", n("a")),
            h("a", NodeFilter.FilterResult.SKIP_CHILDREN));
    }

    @Test
    public void TAIL_SKIP_CHILDREN_AT_ROOT_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("b"))),
            t("r", NodeFilter.FilterResult.SKIP_CHILDREN));
    }

    @Test
    public void TAIL_SKIP_CHILDREN_AT_FIRST_LEAF_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b")),
            t("a", NodeFilter.FilterResult.SKIP_CHILDREN));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_ROOT_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("b"))),
            h("r", NodeFilter.FilterResult.SKIP_ENTIRELY));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_FIRST_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b")),
            h("a", NodeFilter.FilterResult.SKIP_ENTIRELY));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_LAST_CHILD_variation1() {
        assertMetamorphicRelationFor(n("r", n("a")),
            h("a", NodeFilter.FilterResult.SKIP_ENTIRELY));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_INTERNAL_SUBTREE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x")), n("b")),
            h("a", NodeFilter.FilterResult.SKIP_ENTIRELY));
    }

    @Test
    public void HEAD_REMOVE_DETACHED_ROOT_variation1() {
        assertMetamorphicRelationFor(n("r", n("a")),
            h("r", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void HEAD_REMOVE_FIRST_LEAF_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b")),
            h("a", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void HEAD_REMOVE_MIDDLE_LEAF_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b"), n("c")),
            h("b", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void HEAD_REMOVE_LAST_ONLY_CHILD_variation1() {
        assertMetamorphicRelationFor(n("r", n("a")),
            h("a", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void HEAD_REMOVE_INTERNAL_SUBTREE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x")), n("b")),
            h("a", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void TAIL_REMOVE_FIRST_LEAF_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b")),
            t("a", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void TAIL_REMOVE_MIDDLE_LEAF_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b"), n("c")),
            t("b", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void TAIL_REMOVE_LAST_ONLY_CHILD_variation1() {
        assertMetamorphicRelationFor(n("r", n("a")),
            t("a", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void TAIL_REMOVE_INTERNAL_SUBTREE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x")), n("b")),
            t("a", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void TAIL_REMOVE_DETACHED_ROOT_variation1() {
        assertMetamorphicRelationFor(n("r"),
            t("r", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_THEN_REMOVE_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x")), n("b")),
            h("a", NodeFilter.FilterResult.SKIP_CHILDREN),
            h("b", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_THEN_CONTINUE_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x")), n("b", n("y"))),
            h("a", NodeFilter.FilterResult.SKIP_ENTIRELY));
    }

    @Test
    public void HEAD_REMOVE_FIRST_CHILD_THEN_STOP_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b")),
            h("a", NodeFilter.FilterResult.REMOVE),
            h("b", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void TAIL_REMOVE_FIRST_CHILD_THEN_STOP_SIBLING_variation1() {
        assertMetamorphicRelationFor(n("r", n("a"), n("b")),
            t("a", NodeFilter.FilterResult.REMOVE),
            h("b", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void SKIP_CHILDREN_INTERNAL_THEN_TAIL_STOP_ROOT_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x"))),
            h("r", NodeFilter.FilterResult.SKIP_CHILDREN),
            t("r", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void CONTINUE_CHILDREN_THEN_TAIL_REMOVE_ROOT_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x")), n("b")),
            t("r", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void DEEP_BRANCH_REMOVE_LAST_GRANDCHILD_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x"), n("y")), n("b")),
            t("y", NodeFilter.FilterResult.REMOVE));
    }

    @Test
    public void DEEP_BRANCH_SKIP_ENTIRELY_LAST_GRANDCHILD_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x"), n("y"))),
            h("y", NodeFilter.FilterResult.SKIP_ENTIRELY));
    }

    @Test
    public void DEEP_BRANCH_SKIP_CHILDREN_INTERNAL_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x"), n("y")), n("b")),
            h("a", NodeFilter.FilterResult.SKIP_CHILDREN));
    }

    @Test
    public void HEAD_STOP_AFTER_COMPLETED_LEFT_SUBTREE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x")), n("b", n("y"))),
            h("b", NodeFilter.FilterResult.STOP));
    }

    @Test
    public void TAIL_STOP_AFTER_COMPLETED_LEFT_SUBTREE_variation1() {
        assertMetamorphicRelationFor(n("r", n("a", n("x")), n("b", n("y"))),
            t("a", NodeFilter.FilterResult.STOP));
    }
}
