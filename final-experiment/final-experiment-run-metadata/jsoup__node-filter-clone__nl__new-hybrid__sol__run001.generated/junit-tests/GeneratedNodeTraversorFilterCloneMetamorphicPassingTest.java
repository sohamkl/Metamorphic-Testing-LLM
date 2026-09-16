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

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
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

    @Test
    public void CONTINUE_ELEMENT_LEAF_ROOT_variation1() {
        assertMetamorphicRelationFor(testCase(element("root")));
    }

    @Test
    public void CONTINUE_NON_ELEMENT_LEAF_ROOT_variation1() {
        assertMetamorphicRelationFor(testCase(new TextNode("detached leaf")));
    }

    @Test
    public void CONTINUE_SINGLE_CHILD_CHAIN_variation1() {
        Node root = element("root", element("level1", element("level2")));
        assertMetamorphicRelationFor(testCase(root));
    }

    @Test
    public void CONTINUE_ROOT_SIBLING_FANOUT_variation1() {
        Node root = document(element("first"), element("second"), element("third"));
        assertMetamorphicRelationFor(testCase(root));
    }

    @Test
    public void CONTINUE_UNBALANCED_TREE_variation1() {
        Node root = element("root",
                element("shallow"),
                element("deep1", element("deep2", element("deep3"))));
        assertMetamorphicRelationFor(testCase(root));
    }

    @Test
    public void CONTINUE_MIXED_NODE_KINDS_variation1() {
        Node root = document(
                element("element-child", new TextNode("inside")),
                new Comment("comment"),
                new TextNode("text"));
        assertMetamorphicRelationFor(testCase(root));
    }

    @Test
    public void SKIP_CHILDREN_AT_ROOT_variation1() {
        Node root = element("root", element("child", element("grandchild")));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    public void SKIP_CHILDREN_FIRST_INTERNAL_WITH_SIBLING_variation1() {
        Node root = document(
                element("first", element("unvisited")),
                element("second"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    public void SKIP_CHILDREN_LAST_INTERNAL_variation1() {
        Node root = element("root",
                element("first"),
                element("last", element("unvisited")));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "1", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    public void SKIP_CHILDREN_ON_LEAF_variation1() {
        Node root = document(element("selected"), element("following"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    public void SKIP_CHILDREN_DEEP_INTERNAL_variation1() {
        Node root = element("root",
                element("level1",
                        element("selected", element("unvisited"))));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0/0", NodeFilter.FilterResult.SKIP_CHILDREN)));
    }

    @Test
    public void SKIP_ENTIRELY_AT_ROOT_variation1() {
        Node root = document(element("child", element("grandchild")), element("other"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    public void SKIP_ENTIRELY_FIRST_SUBTREE_variation1() {
        Node root = element("root",
                element("selected", element("unvisited")),
                element("following"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    public void SKIP_ENTIRELY_LAST_SUBTREE_variation1() {
        Node root = document(
                element("first"),
                element("selected", element("unvisited")));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "1", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    public void SKIP_ENTIRELY_LEAF_variation1() {
        Node root = element("root", element("selected"), element("following"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    public void SKIP_ENTIRELY_DEEP_ONLY_CHILD_variation1() {
        Node root = document(
                element("level1",
                        element("selected", element("unvisited"))));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0/0", NodeFilter.FilterResult.SKIP_ENTIRELY)));
    }

    @Test
    public void REMOVE_FROM_ROOT_HEAD_variation1() {
        Node root = element("root", element("child"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_MIDDLE_LEAF_FROM_HEAD_variation1() {
        Node root = document(element("first"), element("selected"), element("last"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_LAST_LEAF_FROM_HEAD_variation1() {
        Node root = element("root", element("first"), element("selected"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_FIRST_NONLEAF_SUBTREE_FROM_HEAD_variation1() {
        Node root = document(
                element("selected", element("unvisited", element("deep"))),
                element("following"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_LAST_NONLEAF_SUBTREE_FROM_HEAD_variation1() {
        Node root = element("root",
                element("first"),
                element("selected", element("unvisited")));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_NESTED_ONLY_CHILD_FROM_HEAD_variation1() {
        Node root = document(element("parent", element("selected")));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0/0", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_FROM_ROOT_TAIL_variation1() {
        Node root = element("root", element("child", element("grandchild")));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_MIDDLE_LEAF_FROM_TAIL_variation1() {
        Node root = document(
                element("first"),
                element("selected"),
                element("following", element("deep", element("deeper"))));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_LAST_LEAF_FROM_TAIL_variation1() {
        Node root = element("root", element("first"), element("selected"));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_INTERNAL_NODE_FROM_TAIL_WITH_SIBLING_variation1() {
        Node root = document(
                element("selected", element("visited-child")),
                element("following"));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "0", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_LAST_INTERNAL_NODE_FROM_TAIL_variation1() {
        Node root = element("root",
                element("first"),
                element("selected", element("visited-child")));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_DEEP_LAST_LEAF_WITH_MULTI_ASCENT_variation1() {
        Node root = document(
                element("level1",
                        element("level2",
                                element("selected"))));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "0/0/0", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void STOP_FROM_ROOT_HEAD_variation1() {
        Node root = element("root", element("unvisited"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void STOP_FROM_FIRST_CHILD_HEAD_variation1() {
        Node root = document(element("selected"), element("unvisited"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void STOP_FROM_DEEP_LEAF_HEAD_variation1() {
        Node root = element("root",
                element("level1",
                        element("level2",
                                element("selected"))));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0/0/0", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void STOP_FROM_LATER_SIBLING_HEAD_variation1() {
        Node root = document(
                element("earlier", element("completed")),
                element("selected"),
                element("unvisited"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "1", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void STOP_FROM_INTERNAL_HEAD_AFTER_PRIOR_BRANCH_variation1() {
        Node root = element("root",
                element("prior", element("completed")),
                element("selected", element("unvisited")));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "1", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void STOP_FROM_ROOT_TAIL_variation1() {
        Node root = document(
                element("first"),
                new TextNode("mixed"),
                element("last"));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void STOP_FROM_MIDDLE_LEAF_TAIL_variation1() {
        Node root = element("root",
                element("first"),
                element("selected"),
                element("unvisited"));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "1", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void STOP_FROM_LAST_LEAF_TAIL_variation1() {
        Node root = document(element("first"), element("selected"));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "1", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void STOP_FROM_INTERNAL_NODE_TAIL_variation1() {
        Node root = element("root",
                element("selected", element("visited-child")),
                element("unvisited"));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "0", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void STOP_FROM_DEEP_ANCESTOR_TAIL_variation1() {
        Node root = document(
                element("level1",
                        element("selected",
                                element("deep-leaf"))));
        assertMetamorphicRelationFor(testCase(root,
                rule(TAIL, "0/0", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void SKIP_CHILDREN_THEN_STOP_LATER_SIBLING_variation1() {
        Node root = element("root",
                element("skipped", element("unvisited")),
                element("stopping"),
                element("after-stop"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.SKIP_CHILDREN),
                rule(HEAD, "1", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void SKIP_ENTIRELY_THEN_REMOVE_LATER_SIBLING_variation1() {
        Node root = document(
                element("skipped", element("unvisited")),
                element("removed", element("also-removed")),
                element("following"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.SKIP_ENTIRELY),
                rule(HEAD, "1", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void REMOVE_THEN_STOP_NEXT_SIBLING_variation1() {
        Node root = element("root",
                element("removed"),
                element("stopping"),
                element("unvisited"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.REMOVE),
                rule(HEAD, "1", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void MULTIPLE_REMOVALS_ACROSS_BOTH_ROUTES_variation1() {
        Node root = document(
                element("removed-with-next"),
                element("retained"),
                element("removed-last"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.REMOVE),
                rule(HEAD, "2", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void ALL_PRUNING_ACTIONS_BEFORE_FINAL_STOP_variation1() {
        Node root = element("root",
                element("skip-children", element("unvisited-a")),
                element("skip-entirely", element("unvisited-b")),
                element("remove", element("unvisited-c")),
                element("stop", element("unvisited-d")));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.SKIP_CHILDREN),
                rule(HEAD, "1", NodeFilter.FilterResult.SKIP_ENTIRELY),
                rule(HEAD, "2", NodeFilter.FilterResult.REMOVE),
                rule(HEAD, "3", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void REMOVE_DESCENDANT_THEN_STOP_ROOT_TAIL_variation1() {
        Node root = document(
                element("removed", element("unvisited")),
                element("retained", element("deep", element("leaf"))));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.REMOVE),
                rule(TAIL, "", NodeFilter.FilterResult.STOP)));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_THEN_TAIL_REMOVE_variation1() {
        Node root = element("root",
                element("unvisited-a"),
                element("unvisited-b"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "", NodeFilter.FilterResult.SKIP_CHILDREN),
                rule(TAIL, "", NodeFilter.FilterResult.REMOVE)));
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_THEN_TAIL_REMOVE_variation1() {
        Node root = document(
                element("selected", element("unvisited")),
                element("following"));
        assertMetamorphicRelationFor(testCase(root,
                rule(HEAD, "0", NodeFilter.FilterResult.SKIP_CHILDREN),
                rule(TAIL, "0", NodeFilter.FilterResult.REMOVE)));
    }
}
