import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final String HEAD = "head";
    private static final String TAIL = "tail";

    private static void assertMetamorphicRelationFor(SourceCase source) {
        Assertions.assertNotNull(source);
        Assertions.assertNotNull(source.root);
        Assertions.assertNull(source.root.parentNode());

        String initialOuterHtml = source.root.outerHtml();
        FollowUp followUp = generateFollowUp(source);

        Assertions.assertNull(source.root.parentNode());
        Assertions.assertNull(followUp.root.parentNode());
        Assertions.assertEquals(initialOuterHtml, followUp.root.outerHtml());
        assertNoSharedNodeIdentity(source.root, followUp.root);

        ExecutionOutput sourceOutput = execute(source.root, source.rules);
        ExecutionOutput followUpOutput = execute(followUp.root, followUp.rules);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(SourceCase source) {
        Node clonedRoot = source.root.clone();
        List<Rule> clonedRules = new ArrayList<>();
        for (Rule rule : source.rules) {
            clonedRules.add(new Rule(rule.id, rule.phase, rule.path, rule.result));
        }
        return new FollowUp(clonedRoot, clonedRules);
    }

    private static ExecutionOutput execute(Node root, List<Rule> rules) {
        IdentityHashMap<Node, String> paths = new IdentityHashMap<>();
        IdentityHashMap<Node, Node> initialParents = new IdentityHashMap<>();
        indexInitialTree(root, "", paths, initialParents);

        LoggingFilter filter = new LoggingFilter(rules, paths);
        NodeFilter.FilterResult terminalResult = NodeTraversor.filter(filter, root);

        List<String> removals = new ArrayList<>();
        for (Map.Entry<Node, Node> entry : initialParents.entrySet()) {
            Node initialParent = entry.getValue();
            if (initialParent != null && entry.getKey().parentNode() == null) {
                removals.add("removed:" + paths.get(entry.getKey()));
            }
        }
        Collections.sort(removals);
        filter.actions.addAll(removals);

        for (Rule rule : rules) {
            if (rule.result != NodeFilter.FilterResult.CONTINUE) {
                Assertions.assertTrue(
                        filter.reachedRuleIds.containsKey(rule.id),
                        "Configured action was not reached: " + rule.id);
            }
        }

        return new ExecutionOutput(
                terminalResult,
                new ArrayList<>(filter.events),
                new ArrayList<>(filter.actions),
                root.outerHtml());
    }

    private static void assertMetamorphicRelation(
            ExecutionOutput sourceOutput,
            ExecutionOutput followUpOutput) {

        Assertions.assertEquals(sourceOutput.terminalResult, followUpOutput.terminalResult);
        Assertions.assertEquals(sourceOutput.events, followUpOutput.events);
        Assertions.assertEquals(sourceOutput.actions, followUpOutput.actions);
        Assertions.assertEquals(sourceOutput.finalOuterHtml, followUpOutput.finalOuterHtml);
    }

    private static void assertNoSharedNodeIdentity(Node source, Node clone) {
        Assertions.assertNotSame(source, clone);
        Assertions.assertEquals(source.childNodeSize(), clone.childNodeSize());
        for (int i = 0; i < source.childNodeSize(); i++) {
            assertNoSharedNodeIdentity(source.childNode(i), clone.childNode(i));
        }
    }

    private static void indexInitialTree(
            Node node,
            String path,
            IdentityHashMap<Node, String> paths,
            IdentityHashMap<Node, Node> initialParents) {

        paths.put(node, path);
        initialParents.put(node, node.parentNode());
        for (int i = 0; i < node.childNodeSize(); i++) {
            String childPath = path.isEmpty() ? Integer.toString(i) : path + "/" + i;
            indexInitialTree(node.childNode(i), childPath, paths, initialParents);
        }
    }

    private static SourceCase testCase(Node root, Rule... rules) {
        return new SourceCase(root, Arrays.asList(rules));
    }

    private static Rule rule(
            String phase,
            String path,
            NodeFilter.FilterResult result) {

        return new Rule(phase + ":" + path + ":" + result, phase, path, result);
    }

    private static Element element(String name, Node... children) {
        Element element = new Element(name, "https://example.test/");
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static Document document(Node... children) {
        Document document = new Document(
                "https://example.test/",
                "https://example.test/document");
        for (Node child : children) {
            document.appendChild(child);
        }
        return document;
    }

    private static final class SourceCase {
        private final Node root;
        private final List<Rule> rules;

        private SourceCase(Node root, List<Rule> rules) {
            this.root = root;
            this.rules = new ArrayList<>(rules);
        }
    }

    private static final class FollowUp {
        private final Node root;
        private final List<Rule> rules;

        private FollowUp(Node root, List<Rule> rules) {
            this.root = root;
            this.rules = rules;
        }
    }

    private static final class Rule {
        private final String id;
        private final String phase;
        private final String path;
        private final NodeFilter.FilterResult result;

        private Rule(
                String id,
                String phase,
                String path,
                NodeFilter.FilterResult result) {

            this.id = id;
            this.phase = phase;
            this.path = path;
            this.result = result;
        }

        private boolean matches(String callbackPhase, String nodePath) {
            return phase.equals(callbackPhase) && path.equals(nodePath);
        }
    }

    private static final class ExecutionOutput {
        private final NodeFilter.FilterResult terminalResult;
        private final List<String> events;
        private final List<String> actions;
        private final String finalOuterHtml;

        private ExecutionOutput(
                NodeFilter.FilterResult terminalResult,
                List<String> events,
                List<String> actions,
                String finalOuterHtml) {

            this.terminalResult = terminalResult;
            this.events = events;
            this.actions = actions;
            this.finalOuterHtml = finalOuterHtml;
        }
    }

    private static final class LoggingFilter implements NodeFilter {
        private final List<Rule> rules;
        private final IdentityHashMap<Node, String> paths;
        private final List<String> events = new ArrayList<>();
        private final List<String> actions = new ArrayList<>();
        private final Map<String, Boolean> reachedRuleIds = new LinkedHashMap<>();

        private LoggingFilter(
                List<Rule> rules,
                IdentityHashMap<Node, String> paths) {

            this.rules = new ArrayList<>(rules);
            this.paths = paths;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return callback(HEAD, node, depth);
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return callback(TAIL, node, depth);
        }

        private FilterResult callback(String phase, Node node, int depth) {
            String path = paths.get(node);
            Assertions.assertNotNull(path, "Traversal reached a node outside the initial tree");

            Rule matchingRule = null;
            for (Rule rule : rules) {
                if (rule.matches(phase, path)) {
                    Assertions.assertNull(
                            matchingRule,
                            "Multiple rules match the same callback event");
                    matchingRule = rule;
                }
            }

            FilterResult result = matchingRule == null
                    ? FilterResult.CONTINUE
                    : matchingRule.result;

            events.add(phase + "|" + depth + "|" + path + "|" + result);

            if (matchingRule != null) {
                reachedRuleIds.put(matchingRule.id, Boolean.TRUE);
                if (result != FilterResult.CONTINUE) {
                    actions.add("action:" + matchingRule.id);
                }
            }

            return result;
        }
    }
}
