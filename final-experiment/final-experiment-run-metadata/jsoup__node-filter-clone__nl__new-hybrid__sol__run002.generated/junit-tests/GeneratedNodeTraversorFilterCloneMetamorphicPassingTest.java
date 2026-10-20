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

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
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

    @Test
    public void CONTINUE_LEAF_ROOTS_variation1_textNode() {
        assertMetamorphicRelationFor(new TextNode("detached-text"), allContinue());
    }

    @Test
    public void CONTINUE_LEAF_ROOTS_variation2_emptyElement() {
        assertMetamorphicRelationFor(element("empty", "leaf-root"), allContinue());
    }

    @Test
    public void CONTINUE_LEAF_ROOTS_variation3_dataNode() {
        assertMetamorphicRelationFor(new DataNode("detached-data"), allContinue());
    }

    @Test
    public void CONTINUE_SINGLE_CHILD_CHAINS_variation1_depthTwo() {
        assertMetamorphicRelationFor(chain("chain-two", 2), allContinue());
    }

    @Test
    public void CONTINUE_SINGLE_CHILD_CHAINS_variation2_depthFive() {
        assertMetamorphicRelationFor(chain("chain-five", 5), allContinue());
    }

    @Test
    public void CONTINUE_WIDE_SIBLING_SETS_variation1_adjacentLeaves() {
        assertMetamorphicRelationFor(wideLeaves("wide-leaves", 4), allContinue());
    }

    @Test
    public void CONTINUE_WIDE_SIBLING_SETS_variation2_adjacentContainers() {
        assertMetamorphicRelationFor(wideContainers("wide-containers", 3), allContinue());
    }

    @Test
    public void CONTINUE_MIXED_BRANCHING_DOM_variation1_elementRoot() {
        assertMetamorphicRelationFor(mixedTree("mixed-element"), allContinue());
    }

    @Test
    public void CONTINUE_MIXED_BRANCHING_DOM_variation2_documentRoot() {
        assertMetamorphicRelationFor(documentTree("mixed-document"), allContinue());
    }

    @Test
    public void STOP_AT_ROOT_HEAD_variation1_branchingRoot() {
        assertMetamorphicRelationFor(
                mixedTree("stop-root"),
                atRootHead(NodeFilter.FilterResult.STOP),
                NodeFilter.FilterResult.STOP);
    }

    @Test
    public void STOP_AT_DEEP_HEAD_variation1_depthTwo() {
        assertMetamorphicRelationFor(
                chain("deep-stop-two", 3),
                atHead("level-2", NodeFilter.FilterResult.STOP),
                NodeFilter.FilterResult.STOP);
    }

    @Test
    public void STOP_AT_DEEP_HEAD_variation2_depthFour() {
        assertMetamorphicRelationFor(
                chain("deep-stop-four", 5),
                atHead("level-4", NodeFilter.FilterResult.STOP),
                NodeFilter.FilterResult.STOP);
    }

    @Test
    public void STOP_AT_LATER_SIBLING_HEAD_variation1_secondSibling() {
        assertMetamorphicRelationFor(
                wideContainers("later-stop-a", 4),
                atHead("branch-1", NodeFilter.FilterResult.STOP),
                NodeFilter.FilterResult.STOP);
    }

    @Test
    public void STOP_AT_LATER_SIBLING_HEAD_variation2_lastSibling() {
        assertMetamorphicRelationFor(
                wideLeaves("later-stop-b", 4),
                atHead("item-3", NodeFilter.FilterResult.STOP),
                NodeFilter.FilterResult.STOP);
    }

    @Test
    public void STOP_AT_NONROOT_LEAF_TAIL_variation1_firstLeaf() {
        assertMetamorphicRelationFor(
                wideLeaves("leaf-tail-stop-a", 3),
                atTail("item-0", NodeFilter.FilterResult.STOP),
                NodeFilter.FilterResult.STOP);
    }

    @Test
    public void STOP_AT_NONROOT_LEAF_TAIL_variation2_nestedLeaf() {
        assertMetamorphicRelationFor(
                mixedTree("leaf-tail-stop-b"),
                atTail("first-leaf", NodeFilter.FilterResult.STOP),
                NodeFilter.FilterResult.STOP);
    }

    @Test
    public void STOP_AT_INTERNAL_TAIL_variation1_completedBranch() {
        assertMetamorphicRelationFor(
                wideContainers("internal-tail-stop", 3),
                atTail("branch-1", NodeFilter.FilterResult.STOP),
                NodeFilter.FilterResult.STOP);
    }

    @Test
    public void STOP_AT_ROOT_TAIL_variation1_completeTree() {
        assertMetamorphicRelationFor(
                mixedTree("root-tail-stop"),
                atRootTail(NodeFilter.FilterResult.STOP),
                NodeFilter.FilterResult.STOP);
    }

    @Test
    public void SKIP_CHILDREN_AT_ROOT_variation1_shallowChildren() {
        assertMetamorphicRelationFor(
                wideLeaves("skip-root-a", 3),
                atRootHead(NodeFilter.FilterResult.SKIP_CHILDREN),
                NodeFilter.FilterResult.SKIP_CHILDREN);
    }

    @Test
    public void SKIP_CHILDREN_AT_ROOT_variation2_deepDocument() {
        assertMetamorphicRelationFor(
                documentTree("skip-root-b"),
                atRootHead(NodeFilter.FilterResult.SKIP_CHILDREN),
                NodeFilter.FilterResult.SKIP_CHILDREN);
    }

    @Test
    public void SKIP_CHILDREN_INTERNAL_WITH_SIBLING_variation1_firstBranch() {
        assertMetamorphicRelationFor(
                wideContainers("skip-internal-a", 3),
                atHead("branch-0", NodeFilter.FilterResult.SKIP_CHILDREN),
                NodeFilter.FilterResult.SKIP_CHILDREN);
    }

    @Test
    public void SKIP_CHILDREN_INTERNAL_WITH_SIBLING_variation2_middleBranch() {
        assertMetamorphicRelationFor(
                wideContainers("skip-internal-b", 4),
                atHead("branch-2", NodeFilter.FilterResult.SKIP_CHILDREN),
                NodeFilter.FilterResult.SKIP_CHILDREN);
    }

    @Test
    public void SKIP_CHILDREN_ON_LAST_CHILD_variation1_ascentPath() {
        assertMetamorphicRelationFor(
                wideContainers("skip-last", 3),
                atHead("branch-2", NodeFilter.FilterResult.SKIP_CHILDREN),
                NodeFilter.FilterResult.SKIP_CHILDREN);
    }

    @Test
    public void SKIP_ENTIRELY_AT_ROOT_variation1_containerRoot() {
        assertMetamorphicRelationFor(
                mixedTree("skip-entire-root"),
                atRootHead(NodeFilter.FilterResult.SKIP_ENTIRELY),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
    }

    @Test
    public void SKIP_ENTIRELY_INTERNAL_WITH_SIBLING_variation1_firstBranch() {
        assertMetamorphicRelationFor(
                wideContainers("skip-entire-a", 3),
                atHead("branch-0", NodeFilter.FilterResult.SKIP_ENTIRELY),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
    }

    @Test
    public void SKIP_ENTIRELY_INTERNAL_WITH_SIBLING_variation2_middleBranch() {
        assertMetamorphicRelationFor(
                wideContainers("skip-entire-b", 4),
                atHead("branch-1", NodeFilter.FilterResult.SKIP_ENTIRELY),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
    }

    @Test
    public void SKIP_ENTIRELY_ON_LAST_CHILD_variation1_lastInternalNode() {
        assertMetamorphicRelationFor(
                wideContainers("skip-entire-last", 3),
                atHead("branch-2", NodeFilter.FilterResult.SKIP_ENTIRELY),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
    }

    @Test
    public void REMOVE_AT_ROOT_HEAD_SENTINEL_variation1_containerRoot() {
        assertMetamorphicRelationFor(
                mixedTree("remove-root-a"),
                atRootHead(NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_AT_ROOT_HEAD_SENTINEL_variation2_leafRoot() {
        assertMetamorphicRelationFor(
                new TextNode("remove-root-leaf"),
                atRootHead(NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_INTERNAL_SUBTREE_WITH_SIBLING_variation1_firstBranch() {
        assertMetamorphicRelationFor(
                wideContainers("remove-subtree-a", 3),
                atHead("branch-0", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_INTERNAL_SUBTREE_WITH_SIBLING_variation2_middleBranch() {
        assertMetamorphicRelationFor(
                wideContainers("remove-subtree-b", 4),
                atHead("branch-2", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_LAST_CHILD_DURING_ASCENT_variation1_internalLastChild() {
        assertMetamorphicRelationFor(
                wideContainers("remove-last-a", 3),
                atHead("branch-2", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_LAST_CHILD_DURING_ASCENT_variation2_nestedLastLeaf() {
        Element root = element("root", "remove-last-b");
        Element parent = child(root, "parent", "parent");
        textChild(parent, "leaf", "only-last", "payload");
        assertMetamorphicRelationFor(
                root,
                atHead("only-last", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_MIDDLE_LEAF_THEN_CONTINUE_variation1_secondOfThree() {
        assertMetamorphicRelationFor(
                wideLeaves("remove-middle-a", 3),
                atHead("item-1", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_MIDDLE_LEAF_THEN_CONTINUE_variation2_thirdOfFive() {
        assertMetamorphicRelationFor(
                wideLeaves("remove-middle-b", 5),
                atHead("item-2", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_FROM_LEAF_TAIL_WITH_SIBLING_variation1_firstLeaf() {
        assertMetamorphicRelationFor(
                wideLeaves("tail-remove-sibling-a", 3),
                atTail("item-0", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_FROM_LEAF_TAIL_WITH_SIBLING_variation2_middleLeaf() {
        assertMetamorphicRelationFor(
                wideLeaves("tail-remove-sibling-b", 4),
                atTail("item-1", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_FROM_LAST_LEAF_TAIL_variation1_directLastChild() {
        assertMetamorphicRelationFor(
                wideLeaves("tail-remove-last-a", 3),
                atTail("item-2", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_FROM_LAST_LEAF_TAIL_variation2_nestedOnlyChild() {
        Element root = element("root", "tail-remove-last-b");
        Element branch = child(root, "branch", "branch");
        textChild(branch, "leaf", "nested-last", "last");
        assertMetamorphicRelationFor(
                root,
                atTail("nested-last", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_FROM_INTERNAL_TAIL_WITH_SIBLING_variation1_completedSubtree() {
        assertMetamorphicRelationFor(
                wideContainers("tail-remove-internal", 3),
                atTail("branch-1", NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void REMOVE_FROM_ROOT_TAIL_SENTINEL_variation1_completeTraversal() {
        assertMetamorphicRelationFor(
                mixedTree("tail-remove-root"),
                atRootTail(NodeFilter.FilterResult.REMOVE),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void SKIP_CHILDREN_FROM_ROOT_TAIL_variation1_terminalPropagation() {
        assertMetamorphicRelationFor(
                mixedTree("tail-skip-children-root"),
                atRootTail(NodeFilter.FilterResult.SKIP_CHILDREN),
                NodeFilter.FilterResult.SKIP_CHILDREN);
    }

    @Test
    public void SKIP_ENTIRELY_FROM_ROOT_TAIL_variation1_terminalPropagation() {
        assertMetamorphicRelationFor(
                wideContainers("tail-skip-entire-root", 3),
                atRootTail(NodeFilter.FilterResult.SKIP_ENTIRELY),
                NodeFilter.FilterResult.SKIP_ENTIRELY);
    }

    @Test
    public void MIXED_SKIP_AND_REMOVE_BRANCHES_variation1_firstAndThird() {
        Element root = wideContainers("mixed-actions-a", 4);
        PolicyFactory policy = headActions(
                new String[]{"branch-0", "branch-2"},
                new NodeFilter.FilterResult[]{
                        NodeFilter.FilterResult.SKIP_CHILDREN,
                        NodeFilter.FilterResult.REMOVE
                });
        assertMetamorphicRelationFor(
                root,
                policy,
                NodeFilter.FilterResult.SKIP_CHILDREN,
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void MIXED_SKIP_AND_REMOVE_BRANCHES_variation2_middleAndLater() {
        Element root = wideContainers("mixed-actions-b", 5);
        PolicyFactory policy = headActions(
                new String[]{"branch-1", "branch-3"},
                new NodeFilter.FilterResult[]{
                        NodeFilter.FilterResult.SKIP_CHILDREN,
                        NodeFilter.FilterResult.REMOVE
                });
        assertMetamorphicRelationFor(
                root,
                policy,
                NodeFilter.FilterResult.SKIP_CHILDREN,
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void ALL_NONCONTINUE_ACTIONS_BEFORE_STOP_variation1_orderedBranches() {
        Element root = wideContainers("all-actions", 5);
        PolicyFactory policy = headActions(
                new String[]{"branch-0", "branch-1", "branch-2", "branch-3"},
                new NodeFilter.FilterResult[]{
                        NodeFilter.FilterResult.SKIP_CHILDREN,
                        NodeFilter.FilterResult.SKIP_ENTIRELY,
                        NodeFilter.FilterResult.REMOVE,
                        NodeFilter.FilterResult.STOP
                });
        assertMetamorphicRelationFor(
                root,
                policy,
                NodeFilter.FilterResult.SKIP_CHILDREN,
                NodeFilter.FilterResult.SKIP_ENTIRELY,
                NodeFilter.FilterResult.REMOVE,
                NodeFilter.FilterResult.STOP);
    }

    @Test
    public void ORDINAL_POLICY_ON_STRUCTURALLY_SIMILAR_SIBLINGS_variation1_threeEqualItems() {
        Element root = element("root", "ordinal-a");
        for (int i = 0; i < 3; i++) {
            Element item = new Element("item");
            item.appendChild(new TextNode("same"));
            root.appendChild(item);
        }
        assertMetamorphicRelationFor(
                root,
                secondMatchingSiblingRemoval("item"),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void ORDINAL_POLICY_ON_STRUCTURALLY_SIMILAR_SIBLINGS_variation2_fourEqualContainers() {
        Element root = element("root", "ordinal-b");
        for (int i = 0; i < 4; i++) {
            Element group = new Element("group");
            group.appendElement("leaf").text("equal");
            root.appendChild(group);
        }
        assertMetamorphicRelationFor(
                root,
                secondMatchingSiblingRemoval("group"),
                NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void NESTED_TAIL_REMOVALS_ACROSS_ASCENTS_variation1_ancestorHasSibling() {
        Element root = element("root", "nested-tail-a");
        Element selected = child(root, "branch", "selected");
        textChild(selected, "leaf", "selected-leaf", "payload");
        textChild(root, "survivor", "survivor", "keep");

        PolicyFactory policy = tailActions(
                new String[]{"selected-leaf", "selected"},
                new NodeFilter.FilterResult[]{
                        NodeFilter.FilterResult.REMOVE,
                        NodeFilter.FilterResult.REMOVE
                });
        assertMetamorphicRelationFor(root, policy, NodeFilter.FilterResult.REMOVE);
    }

    @Test
    public void NESTED_TAIL_REMOVALS_ACROSS_ASCENTS_variation2_ancestorIsLastChild() {
        Element root = element("root", "nested-tail-b");
        textChild(root, "prefix", "prefix", "keep");
        Element selected = child(root, "branch", "selected-last");
        Element inner = child(selected, "inner", "inner");
        textChild(inner, "leaf", "last-leaf", "payload");

        PolicyFactory policy = tailActions(
                new String[]{"last-leaf", "selected-last"},
                new NodeFilter.FilterResult[]{
                        NodeFilter.FilterResult.REMOVE,
                        NodeFilter.FilterResult.REMOVE
                });
        assertMetamorphicRelationFor(root, policy, NodeFilter.FilterResult.REMOVE);
    }
}
