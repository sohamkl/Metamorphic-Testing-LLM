import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private interface DecisionPolicy {
        NodeFilter.FilterResult decide(boolean head, Node node, int depth);
    }

    private interface PolicyFactory {
        DecisionPolicy create();
    }

    private static final class RecordingFilter implements NodeFilter {
        private final DecisionPolicy policy;
        private final List<String> callbacks = new ArrayList<>();
        private final List<String> actions = new ArrayList<>();

        private RecordingFilter(DecisionPolicy policy) {
            this.policy = policy;
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
            FilterResult result = policy.decide(head, node, depth);
            String phase = head ? "H" : "T";
            String location = structuralPath(node);
            callbacks.add(phase + ":" + depth + ":" + location + ":"
                    + descriptor(node) + ":" + result.name());

            if (result != FilterResult.CONTINUE) {
                actions.add(phase + ":" + location + ":" + result.name());
                if (result == FilterResult.REMOVE) {
                    actions.add(depth == 0
                            ? "ROOT_REMOVE_SENTINEL:" + location
                            : "SUBTREE_REMOVAL:" + location);
                }
            }
            return result;
        }
    }

    private static final class ExecutionOutput {
        private final NodeFilter.FilterResult terminal;
        private final List<String> callbacks;
        private final List<String> actions;
        private final String outerHtml;

        private ExecutionOutput(
                NodeFilter.FilterResult terminal,
                List<String> callbacks,
                List<String> actions,
                String outerHtml) {
            this.terminal = terminal;
            this.callbacks = callbacks;
            this.actions = actions;
            this.outerHtml = outerHtml;
        }
    }

    private static Node generateFollowUp(Node source) {
        Node clone = source.clone();
        Assertions.assertNotSame(source, clone);
        Assertions.assertNull(clone.parentNode());
        Assertions.assertEquals(source.outerHtml(), clone.outerHtml());
        return clone;
    }

    private static void assertMetamorphicRelationFor(
            Node source,
            PolicyFactory policyFactory,
            NodeFilter.FilterResult... requiredActions) {
        Assertions.assertNotNull(source);
        Assertions.assertNull(source.parentNode());

        Node followUp = generateFollowUp(source);
        RecordingFilter sourceFilter = new RecordingFilter(policyFactory.create());
        RecordingFilter followUpFilter = new RecordingFilter(policyFactory.create());

        ExecutionOutput sourceOutput = execute(source, sourceFilter);
        ExecutionOutput followUpOutput = execute(followUp, followUpFilter);

        assertMetamorphicRelation(sourceOutput, followUpOutput);

        for (NodeFilter.FilterResult required : requiredActions) {
            Assertions.assertTrue(
                    containsAction(sourceOutput.actions, required),
                    "Configured action was not reached in source execution: " + required);
            Assertions.assertTrue(
                    containsAction(followUpOutput.actions, required),
                    "Configured action was not reached in clone execution: " + required);
        }
    }

    private static ExecutionOutput execute(Node root, RecordingFilter filter) {
        NodeFilter.FilterResult result = NodeTraversor.filter(filter, root);
        return new ExecutionOutput(
                result,
                new ArrayList<>(filter.callbacks),
                new ArrayList<>(filter.actions),
                root.outerHtml());
    }

    private static void assertMetamorphicRelation(
            ExecutionOutput sourceOutput,
            ExecutionOutput followUpOutput) {
        Assertions.assertEquals(sourceOutput.terminal, followUpOutput.terminal);
        Assertions.assertEquals(sourceOutput.callbacks, followUpOutput.callbacks);
        Assertions.assertEquals(sourceOutput.actions, followUpOutput.actions);
        Assertions.assertEquals(sourceOutput.outerHtml, followUpOutput.outerHtml);
    }

    private static boolean containsAction(
            List<String> actions,
            NodeFilter.FilterResult result) {
        String suffix = ":" + result.name();
        for (String action : actions) {
            if (action.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

    private static String descriptor(Node node) {
        return node.getClass().getSimpleName() + "|" + node.nodeName() + "|" + node.outerHtml();
    }

    private static String structuralPath(Node node) {
        List<Integer> indexes = new ArrayList<>();
        Node cursor = node;
        while (cursor.parentNode() != null) {
            indexes.add(0, cursor.siblingIndex());
            cursor = cursor.parentNode();
        }
        if (indexes.isEmpty()) {
            return "root";
        }
        StringBuilder path = new StringBuilder("root");
        for (Integer index : indexes) {
            path.append('/').append(index);
        }
        return path.toString();
    }

    private static Element element(String tag, String id) {
        Element element = new Element(tag);
        if (id != null) {
            element.attr("id", id);
        }
        return element;
    }

    private static Element child(Element parent, String tag, String id) {
        Element child = element(tag, id);
        parent.appendChild(child);
        return child;
    }

    private static Element textChild(Element parent, String tag, String id, String text) {
        Element child = child(parent, tag, id);
        child.appendChild(new TextNode(text));
        return child;
    }

    private static Element chain(String rootId, int maximumDepth) {
        Element root = element("root", rootId);
        Element cursor = root;
        for (int depth = 1; depth <= maximumDepth; depth++) {
            cursor = child(cursor, "level", "level-" + depth);
        }
        cursor.appendChild(new TextNode("chain-leaf-" + maximumDepth));
        return root;
    }

    private static Element wideLeaves(String rootId, int count) {
        Element root = element("root", rootId);
        for (int i = 0; i < count; i++) {
            textChild(root, "item", "item-" + i, "value-" + i);
        }
        return root;
    }

    private static Element wideContainers(String rootId, int count) {
        Element root = element("root", rootId);
        for (int i = 0; i < count; i++) {
            Element branch = child(root, "branch", "branch-" + i);
            textChild(branch, "leaf", "leaf-" + i, "payload-" + i);
        }
        return root;
    }

    private static Element mixedTree(String rootId) {
        Element root = element("root", rootId);
        root.attr("data-fixture", "clone-stable");
        root.appendChild(new TextNode("prefix"));

        Element first = child(root, "section", "first");
        textChild(first, "span", "first-leaf", "alpha");

        Element second = child(root, "section", "second");
        Element nested = child(second, "article", "nested");
        nested.attr("data-code", "42");
        textChild(nested, "em", "deep-leaf", "beta");

        root.appendChild(new Comment("clone-visible-comment"));
        return root;
    }

    private static Document documentTree(String rootId) {
        Document document = new Document("");
        Element root = document.appendElement("root").attr("id", rootId);
        Element a = root.appendElement("branch").attr("id", "a");
        a.appendElement("leaf").attr("id", "a-leaf").text("A");
        Element b = root.appendElement("branch").attr("id", "b");
        Element nested = b.appendElement("nested").attr("id", "b-nested");
        nested.appendElement("deep").attr("id", "b-deep").text("B");
        root.appendElement("branch").attr("id", "c").text("C");
        return document;
    }

    private static PolicyFactory allContinue() {
        return () -> (head, node, depth) -> NodeFilter.FilterResult.CONTINUE;
    }

    private static PolicyFactory atRootHead(NodeFilter.FilterResult result) {
        return () -> (head, node, depth) ->
                head && depth == 0 ? result : NodeFilter.FilterResult.CONTINUE;
    }

    private static PolicyFactory atRootTail(NodeFilter.FilterResult result) {
        return () -> (head, node, depth) ->
                !head && depth == 0 ? result : NodeFilter.FilterResult.CONTINUE;
    }

    private static PolicyFactory atHead(String id, NodeFilter.FilterResult result) {
        return () -> (head, node, depth) ->
                head && id.equals(nodeId(node))
                        ? result
                        : NodeFilter.FilterResult.CONTINUE;
    }

    private static PolicyFactory atTail(String id, NodeFilter.FilterResult result) {
        return () -> (head, node, depth) ->
                !head && id.equals(nodeId(node))
                        ? result
                        : NodeFilter.FilterResult.CONTINUE;
    }

    private static PolicyFactory headActions(String[] ids, NodeFilter.FilterResult[] results) {
        return () -> (head, node, depth) -> {
            if (!head) {
                return NodeFilter.FilterResult.CONTINUE;
            }
            String id = nodeId(node);
            for (int i = 0; i < ids.length; i++) {
                if (ids[i].equals(id)) {
                    return results[i];
                }
            }
            return NodeFilter.FilterResult.CONTINUE;
        };
    }

    private static PolicyFactory tailActions(String[] ids, NodeFilter.FilterResult[] results) {
        return () -> (head, node, depth) -> {
            if (head) {
                return NodeFilter.FilterResult.CONTINUE;
            }
            String id = nodeId(node);
            for (int i = 0; i < ids.length; i++) {
                if (ids[i].equals(id)) {
                    return results[i];
                }
            }
            return NodeFilter.FilterResult.CONTINUE;
        };
    }

    private static PolicyFactory secondMatchingSiblingRemoval(String tag) {
        return () -> new DecisionPolicy() {
            private int matches;

            @Override
            public NodeFilter.FilterResult decide(boolean head, Node node, int depth) {
                if (head && depth == 1 && tag.equals(node.nodeName())) {
                    matches++;
                    if (matches == 2) {
                        return NodeFilter.FilterResult.REMOVE;
                    }
                }
                return NodeFilter.FilterResult.CONTINUE;
            }
        };
    }

    private static String nodeId(Node node) {
        return node instanceof Element ? ((Element) node).id() : "";
    }

}
