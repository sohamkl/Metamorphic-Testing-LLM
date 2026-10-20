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

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
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

}
