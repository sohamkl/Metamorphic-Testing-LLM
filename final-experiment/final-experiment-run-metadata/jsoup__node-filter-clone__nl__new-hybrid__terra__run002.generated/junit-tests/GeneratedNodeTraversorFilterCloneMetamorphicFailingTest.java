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

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
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

}
