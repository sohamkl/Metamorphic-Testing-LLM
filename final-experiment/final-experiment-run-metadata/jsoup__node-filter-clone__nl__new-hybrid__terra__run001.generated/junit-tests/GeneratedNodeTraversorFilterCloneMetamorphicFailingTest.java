import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final class SourceCase {
        final Element root;
        final Map<String, NodeFilter.FilterResult> decisions;

        SourceCase(Element root, Map<String, NodeFilter.FilterResult> decisions) {
            this.root = root;
            this.decisions = decisions;
        }
    }

    private static final class FollowUpCase {
        final Element root;
        final Map<String, NodeFilter.FilterResult> decisions;

        FollowUpCase(Element root, Map<String, NodeFilter.FilterResult> decisions) {
            this.root = root;
            this.decisions = decisions;
        }
    }

    private static final class RunResult {
        final NodeFilter.FilterResult result;
        final List<String> callbacks;
        final String outerHtml;

        RunResult(NodeFilter.FilterResult result, List<String> callbacks, String outerHtml) {
            this.result = result;
            this.callbacks = callbacks;
            this.outerHtml = outerHtml;
        }
    }

    private static final class RecordingFilter implements NodeFilter {
        private final Map<String, NodeFilter.FilterResult> decisions;
        private final List<String> callbacks = new ArrayList<>();

        RecordingFilter(Map<String, NodeFilter.FilterResult> decisions) {
            this.decisions = new LinkedHashMap<>(decisions);
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return record("H", node, depth);
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return record("T", node, depth);
        }

        private FilterResult record(String phase, Node node, int depth) {
            String role = ((Element) node).attr("data-role");
            FilterResult result = decisions.getOrDefault(
                phase + ":" + role, FilterResult.CONTINUE);
            callbacks.add(phase + ":" + role + ":" + depth + ":" + result);
            return result;
        }
    }

    private static Element node(String role) {
        Element element = new Element("n");
        element.attr("data-role", role);
        return element;
    }

    private static Element oneChild() {
        Element root = node("root");
        root.appendChild(node("only"));
        return root;
    }

    private static Element depthTwoChain() {
        Element root = node("root");
        Element internal = node("internal");
        internal.appendChild(node("leaf2"));
        root.appendChild(internal);
        return root;
    }

    private static Element branching() {
        Element root = node("root");
        Element first = node("first");
        first.appendChild(node("nested"));
        root.appendChild(first);
        root.appendChild(node("second"));
        return root;
    }

    private static Element twoLeaves() {
        Element root = node("root");
        root.appendChild(node("first"));
        root.appendChild(node("second"));
        return root;
    }

    private static Element threeLeaves() {
        Element root = node("root");
        root.appendChild(node("first"));
        root.appendChild(node("middle"));
        root.appendChild(node("last"));
        return root;
    }

    private static Map<String, NodeFilter.FilterResult> decisions() {
        return new LinkedHashMap<>();
    }

    private static Map<String, NodeFilter.FilterResult> decisions(
        String key, NodeFilter.FilterResult value) {
        Map<String, NodeFilter.FilterResult> map = decisions();
        map.put(key, value);
        return map;
    }

    private static Map<String, NodeFilter.FilterResult> decisions(
        String firstKey, NodeFilter.FilterResult firstValue,
        String secondKey, NodeFilter.FilterResult secondValue) {
        Map<String, NodeFilter.FilterResult> map = decisions(firstKey, firstValue);
        map.put(secondKey, secondValue);
        return map;
    }

    private static FollowUpCase generateFollowUp(SourceCase source) {
        return new FollowUpCase((Element) source.root.clone(), new LinkedHashMap<>(source.decisions));
    }

    private static RunResult run(Element root, Map<String, NodeFilter.FilterResult> policy) {
        RecordingFilter filter = new RecordingFilter(policy);
        NodeFilter.FilterResult result = NodeTraversor.filter(filter, root);
        return new RunResult(result, filter.callbacks, root.outerHtml());
    }

    private static void assertMetamorphicRelation(RunResult source, RunResult followUp) {
        Assertions.assertEquals(source.result, followUp.result);
        Assertions.assertEquals(source.callbacks, followUp.callbacks);
        Assertions.assertEquals(source.outerHtml, followUp.outerHtml);
    }

    private static void assertMetamorphicRelationFor(SourceCase source) {
        Assertions.assertNull(source.root.parentNode());
        FollowUpCase followUp = generateFollowUp(source);
        Assertions.assertNotNull(followUp.root);
        Assertions.assertNull(followUp.root.parentNode());
        Assertions.assertEquals(source.root.outerHtml(), followUp.root.outerHtml());

        RunResult sourceResult = run(source.root, source.decisions);
        RunResult followUpResult = run(followUp.root, followUp.decisions);

        if (!source.decisions.isEmpty()) {
            boolean actionObserved = false;
            for (NodeFilter.FilterResult configured : source.decisions.values()) {
                if (configured != NodeFilter.FilterResult.CONTINUE) {
                    for (String callback : sourceResult.callbacks) {
                        if (callback.endsWith(":" + configured)) {
                            actionObserved = true;
                            break;
                        }
                    }
                }
            }
            Assertions.assertTrue(actionObserved);
        }

        assertMetamorphicRelation(sourceResult, followUpResult);
    }

}
