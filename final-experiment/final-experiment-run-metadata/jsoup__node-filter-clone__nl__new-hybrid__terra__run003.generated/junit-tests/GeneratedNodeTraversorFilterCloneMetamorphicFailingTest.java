import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static void assertMetamorphicRelationFor(Element sourceRoot,
                                                     Map<String, NodeFilter.FilterResult> configuredRules) {
        Assertions.assertNull(sourceRoot.parentNode());
        Assertions.assertFalse(sourceRoot.outerHtml().isEmpty());

        ExecutionInput source = new ExecutionInput(sourceRoot, configuredRules);
        ExecutionInput followUp = generateFollowUp(source);

        Assertions.assertNull(followUp.root.parentNode());
        Assertions.assertEquals(source.root.outerHtml(), followUp.root.outerHtml());

        ExecutionOutput sourceOutput = run(source);
        ExecutionOutput followUpOutput = run(followUp);

        assertMetamorphicRelation(sourceOutput, followUpOutput, configuredRules);
    }

    private static ExecutionInput generateFollowUp(ExecutionInput source) {
        Element clone = source.root.clone();
        return new ExecutionInput(clone, new LinkedHashMap<String, NodeFilter.FilterResult>(source.rules));
    }

    private static ExecutionOutput run(ExecutionInput input) {
        RecordingFilter filter = new RecordingFilter(input.rules);
        NodeFilter.FilterResult result = NodeTraversor.filter(filter, input.root);
        return new ExecutionOutput(result, filter.events, input.root.outerHtml());
    }

    private static void assertMetamorphicRelation(ExecutionOutput source,
                                                  ExecutionOutput followUp,
                                                  Map<String, NodeFilter.FilterResult> configuredRules) {
        Assertions.assertEquals(source.result, followUp.result);
        Assertions.assertEquals(source.events, followUp.events);
        Assertions.assertEquals(source.outerHtml, followUp.outerHtml);

        for (NodeFilter.FilterResult configured : configuredRules.values()) {
            if (configured != NodeFilter.FilterResult.CONTINUE) {
                Assertions.assertTrue(containsResult(source.events, configured));
                Assertions.assertTrue(containsResult(followUp.events, configured));
            }
        }
    }

    private static boolean containsResult(List<String> events, NodeFilter.FilterResult result) {
        String suffix = "=" + result.name();
        for (String event : events) {
            if (event.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

    private static Map<String, NodeFilter.FilterResult> rules(Object... entries) {
        Map<String, NodeFilter.FilterResult> result = new LinkedHashMap<String, NodeFilter.FilterResult>();
        for (int i = 0; i < entries.length; i += 2) {
            result.put((String) entries[i], (NodeFilter.FilterResult) entries[i + 1]);
        }
        return result;
    }

    private static Element leaf() {
        return new Element("leaf");
    }

    private static Element chain(int depth) {
        Element root = new Element("root");
        Element cursor = root;
        for (int i = 1; i <= depth; i++) {
            cursor = cursor.appendElement("n" + i);
        }
        return root;
    }

    private static Element branch(int children) {
        Element root = new Element("root");
        for (int i = 0; i < children; i++) {
            root.appendElement("c" + i);
        }
        return root;
    }

    private static Element mixedDepth() {
        Element root = new Element("root");
        Element first = root.appendElement("a");
        first.appendElement("a1");
        root.appendElement("b");
        return root;
    }

    private static Element internalBranch() {
        Element root = new Element("root");
        Element first = root.appendElement("a");
        first.appendElement("a1");
        root.appendElement("b");
        return root;
    }

    private static final class ExecutionInput {
        private final Element root;
        private final Map<String, NodeFilter.FilterResult> rules;

        private ExecutionInput(Element root, Map<String, NodeFilter.FilterResult> rules) {
            this.root = root;
            this.rules = Collections.unmodifiableMap(
                new LinkedHashMap<String, NodeFilter.FilterResult>(rules)
            );
        }
    }

    private static final class ExecutionOutput {
        private final NodeFilter.FilterResult result;
        private final List<String> events;
        private final String outerHtml;

        private ExecutionOutput(NodeFilter.FilterResult result, List<String> events, String outerHtml) {
            this.result = result;
            this.events = new ArrayList<String>(events);
            this.outerHtml = outerHtml;
        }
    }

    private static final class RecordingFilter implements NodeFilter {
        private final Map<String, NodeFilter.FilterResult> rules;
        private final List<String> events = new ArrayList<String>();

        private RecordingFilter(Map<String, NodeFilter.FilterResult> rules) {
            this.rules = rules;
        }

        @Override
        public NodeFilter.FilterResult head(Node node, int depth) {
            return record("H", node, depth);
        }

        @Override
        public NodeFilter.FilterResult tail(Node node, int depth) {
            return record("T", node, depth);
        }

        private NodeFilter.FilterResult record(String phase, Node node, int depth) {
            String position = logicalPosition(node);
            NodeFilter.FilterResult result = rules.get(phase + ":" + position);
            if (result == null) {
                result = NodeFilter.FilterResult.CONTINUE;
            }
            events.add(phase + "@" + depth + ":" + position + "=" + result.name());
            return result;
        }

        private String logicalPosition(Node node) {
            if (node.parentNode() == null) {
                return "root";
            }

            List<Integer> indexes = new ArrayList<Integer>();
            Node cursor = node;
            while (cursor.parentNode() != null) {
                indexes.add(cursor.siblingIndex());
                cursor = cursor.parentNode();
            }

            StringBuilder position = new StringBuilder();
            for (int i = indexes.size() - 1; i >= 0; i--) {
                position.append('/').append(indexes.get(i));
            }
            return position.toString();
        }
    }

    @Test
    public void STOP_AFTER_PRIOR_REMOVE_variation1() {
        assertMetamorphicRelationFor(
            branch(2),
            rules(
                "H:/0", NodeFilter.FilterResult.REMOVE,
                "H:/1", NodeFilter.FilterResult.STOP
            )
        );
    }
}
