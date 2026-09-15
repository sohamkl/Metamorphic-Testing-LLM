import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final class Policy implements NodeFilter {
        private final int mode;

        private Policy(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (mode) {
                case 1:
                    return FilterResult.STOP;
                case 3:
                    return FilterResult.SKIP_CHILDREN;
                case 4:
                    return FilterResult.SKIP_ENTIRELY;
                case 5:
                    return FilterResult.REMOVE;
                case 10:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 11:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 12:
                    return hasId(node, "target")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 14:
                    return hasId(node, "target")
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 17:
                    if (hasId(node, "prune")) {
                        return FilterResult.SKIP_CHILDREN;
                    }
                    if (hasId(node, "remove")) {
                        return FilterResult.REMOVE;
                    }
                    return FilterResult.CONTINUE;
                case 18:
                    return depth == 1 && node.siblingIndex() == 1
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 19:
                    return depth == 1
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 21:
                    if (hasId(node, "remove")) {
                        return FilterResult.REMOVE;
                    }
                    if (hasId(node, "stop")) {
                        return FilterResult.STOP;
                    }
                    return FilterResult.CONTINUE;
                case 24:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 25:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (mode) {
                case 2:
                    return FilterResult.STOP;
                case 6:
                    return FilterResult.REMOVE;
                case 7:
                    return FilterResult.SKIP_CHILDREN;
                case 8:
                    return FilterResult.SKIP_ENTIRELY;
                case 13:
                    return hasId(node, "target")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 15:
                    return hasId(node, "target")
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 16:
                    return depth == 0
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 20:
                    return depth == 1
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 22:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 23:
                    return hasId(node, "target")
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 25:
                    return hasId(node, "target")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        private static boolean hasId(Node node, String id) {
            return node instanceof Element && id.equals(((Element) node).id());
        }
    }

    private static Element element(String tag, String id, Node... children) {
        Element element = new Element(tag);
        if (id != null && !id.isEmpty()) {
            element.attr("id", id);
        }
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static void exercise(NodeFilter sourceFilter, Node sourceRoot) {
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(
                sourceFilter, sourceRoot);

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void DETACHED_LEAF_CONTINUE_variation1() {
        exercise(new Policy(0), element("leaf", "root"));
    }

    @Test
    public void LEAF_HEAD_STOP_variation1() {
        exercise(new Policy(1), new TextNode("stop-at-head"));
    }

    @Test
    public void LEAF_TAIL_STOP_variation1() {
        exercise(new Policy(2), new Comment("stop-at-tail"));
    }

    @Test
    public void LEAF_HEAD_SKIP_CHILDREN_variation1() {
        exercise(new Policy(3), element("leaf", "skip-children"));
    }

    @Test
    public void LEAF_HEAD_SKIP_ENTIRELY_variation1() {
        exercise(new Policy(4), new TextNode("skip-entirely"));
    }

    @Test
    public void LEAF_HEAD_REMOVE_variation1() {
        exercise(new Policy(5), element("leaf", "detached-head-remove"));
    }

    @Test
    public void LEAF_TAIL_REMOVE_variation1() {
        exercise(new Policy(6), new Comment("detached-tail-remove"));
    }

    @Test
    public void LEAF_TAIL_SKIP_CHILDREN_variation1() {
        exercise(new Policy(7), element("leaf", "tail-skip-children"));
    }

    @Test
    public void LEAF_TAIL_SKIP_ENTIRELY_variation1() {
        exercise(new Policy(8), new TextNode("tail-skip-entirely"));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_WITH_DESCENDANTS_variation1() {
        exercise(new Policy(3),
                element("root", "root-skip-children",
                        element("branch", "a", element("leaf", "deep-a")),
                        element("branch", "b")));
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_WITH_DESCENDANTS_variation1() {
        exercise(new Policy(4),
                element("root", "root-skip-entirely",
                        element("child", "a"),
                        element("child", "b", element("grandchild", "c"))));
    }

    @Test
    public void ROOT_REMOVE_WITH_DESCENDANTS_variation1() {
        exercise(new Policy(5),
                element("root", "root-remove",
                        element("child", "a",
                                element("grandchild", "b",
                                        element("deep", "c")))));
    }

    @Test
    public void ROOT_STOP_WITH_DESCENDANTS_variation1() {
        exercise(new Policy(1),
                element("root", "root-stop",
                        element("child", "unvisited"),
                        new TextNode("also-unvisited")));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_TAIL_REMOVE_variation1() {
        exercise(new Policy(25),
                element("root", "target",
                        element("child", "a",
                                element("grandchild", "b"))));
    }

    @Test
    public void SINGLE_CHILD_FULL_TRAVERSAL_variation1() {
        exercise(new Policy(0),
                element("root", "single-root",
                        element("child", "only-child")));
    }

    @Test
    public void DEEP_CHAIN_FULL_TRAVERSAL_variation1() {
        exercise(new Policy(0),
                element("level0", "d0",
                        element("level1", "d1",
                                element("level2", "d2",
                                        element("level3", "d3",
                                                element("level4", "d4"))))));
    }

    @Test
    public void MULTIPLE_LEAF_SIBLINGS_variation1() {
        exercise(new Policy(0),
                element("root", "sibling-root",
                        element("leaf", "first"),
                        element("leaf", "middle"),
                        element("leaf", "last"),
                        element("leaf", "extra")));
    }

    @Test
    public void BRANCHING_DEPTH_FIRST_ORDER_variation1() {
        exercise(new Policy(0),
                element("root", "branching-root",
                        element("first", "first",
                                element("nested", "nested",
                                        element("deep", "deep"))),
                        element("second", "second"),
                        element("third", "third",
                                element("nested", "later"))));
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_THEN_SIBLING_variation1() {
        exercise(new Policy(10),
                element("root", "skip-root",
                        element("branch", "target",
                                element("hidden", "one"),
                                element("hidden", "two")),
                        element("sibling", "visited")));
    }

    @Test
    public void LAST_INTERNAL_SKIP_CHILDREN_ASCENT_variation1() {
        exercise(new Policy(10),
                element("root", "last-skip-root",
                        element("prefix", "prefix"),
                        element("branch", "target",
                                element("hidden", "one",
                                        element("deep", "two")))));
    }

    @Test
    public void INTERNAL_SKIP_ENTIRELY_THEN_SIBLING_variation1() {
        exercise(new Policy(11),
                element("root", "skip-entire-root",
                        element("branch", "target",
                                element("hidden", "one")),
                        element("following", "following",
                                element("visited", "nested"))));
    }

    @Test
    public void LAST_CHILD_SKIP_ENTIRELY_ASCENT_variation1() {
        exercise(new Policy(11),
                element("root", "last-entire-root",
                        element("parent", "parent",
                                element("first", "first"),
                                element("last", "target"))));
    }

    @Test
    public void HEAD_REMOVE_FIRST_SIBLING_variation1() {
        exercise(new Policy(12),
                element("root", "remove-first-root",
                        element("first", "target"),
                        element("second", "second"),
                        element("third", "third")));
    }

    @Test
    public void HEAD_REMOVE_MIDDLE_SIBLING_variation1() {
        exercise(new Policy(12),
                element("root", "remove-middle-root",
                        element("first", "first"),
                        element("middle", "target"),
                        element("last", "last")));
    }

    @Test
    public void HEAD_REMOVE_LAST_CHILD_ASCENT_variation1() {
        exercise(new Policy(12),
                element("root", "remove-last-root",
                        element("parent", "parent",
                                element("preserved", "preserved"),
                                element("last", "target"))));
    }

    @Test
    public void HEAD_REMOVE_INTERNAL_SUBTREE_variation1() {
        exercise(new Policy(12),
                element("root", "remove-subtree-root",
                        element("internal", "target",
                                element("child", "a",
                                        element("deep", "b"))),
                        element("survivor", "survivor")));
    }

    @Test
    public void TAIL_REMOVE_FIRST_LEAF_variation1() {
        exercise(new Policy(13),
                element("root", "tail-first-root",
                        element("leaf", "target"),
                        element("leaf", "second"),
                        element("leaf", "third")));
    }

    @Test
    public void TAIL_REMOVE_MIDDLE_LEAF_variation1() {
        exercise(new Policy(13),
                element("root", "tail-middle-root",
                        element("leaf", "first"),
                        element("leaf", "target"),
                        element("leaf", "last")));
    }

    @Test
    public void TAIL_REMOVE_LAST_CHILD_ASCENT_variation1() {
        exercise(new Policy(13),
                element("root", "tail-last-root",
                        element("parent", "parent",
                                element("leaf", "first"),
                                element("leaf", "target"))));
    }

    @Test
    public void TAIL_REMOVE_INTERNAL_AFTER_DESCENDANTS_variation1() {
        exercise(new Policy(13),
                element("root", "tail-internal-root",
                        element("internal", "target",
                                element("child", "a"),
                                element("child", "b",
                                        element("deep", "c"))),
                        element("survivor", "survivor")));
    }

    @Test
    public void SKIP_CHILDREN_THEN_TAIL_REMOVE_variation1() {
        exercise(new Policy(25),
                element("root", "prune-remove-root",
                        element("internal", "target",
                                element("hidden", "a"),
                                element("hidden", "b")),
                        element("following", "following")));
    }

    @Test
    public void REMOVE_ALL_CHILDREN_AT_HEAD_variation1() {
        exercise(new Policy(19),
                element("root", "remove-all-head-root",
                        element("child", "a", element("nested", "a1")),
                        element("child", "b"),
                        element("child", "c", element("nested", "c1")),
                        element("child", "d")));
    }

    @Test
    public void REMOVE_ALL_LEAVES_AT_TAIL_variation1() {
        exercise(new Policy(20),
                element("root", "remove-all-tail-root",
                        element("leaf", "a"),
                        element("leaf", "b"),
                        element("leaf", "c"),
                        element("leaf", "d")));
    }

    @Test
    public void HEAD_STOP_AT_FIRST_CHILD_variation1() {
        exercise(new Policy(14),
                element("root", "stop-first-root",
                        element("child", "target"),
                        element("child", "later")));
    }

    @Test
    public void HEAD_STOP_AT_DEEP_LEAF_variation1() {
        exercise(new Policy(14),
                element("level0", "deep-stop-root",
                        element("level1", "one",
                                element("level2", "two",
                                        element("level3", "target"))),
                        element("later", "later")));
    }

    @Test
    public void HEAD_STOP_AFTER_EARLIER_REMOVAL_variation1() {
        exercise(new Policy(21),
                element("root", "remove-then-stop-root",
                        element("branch", "remove",
                                element("removed-child", "removed-child")),
                        element("branch", "normal",
                                element("leaf", "normal-leaf")),
                        element("branch", "stop",
                                element("unreached", "unreached"))));
    }

    @Test
    public void TAIL_STOP_BEFORE_SIBLING_variation1() {
        exercise(new Policy(15),
                element("root", "tail-stop-sibling-root",
                        element("leaf", "target"),
                        element("leaf", "unvisited"),
                        element("leaf", "also-unvisited")));
    }

    @Test
    public void TAIL_STOP_DURING_ASCENT_variation1() {
        exercise(new Policy(15),
                element("root", "tail-stop-ascent-root",
                        element("parent", "parent",
                                element("leaf", "first"),
                                element("leaf", "target"))));
    }

    @Test
    public void ROOT_TAIL_STOP_AFTER_FULL_TRAVERSAL_variation1() {
        exercise(new Policy(16),
                element("root", "root-tail-stop",
                        element("branch", "a",
                                element("leaf", "a1")),
                        element("branch", "b",
                                element("leaf", "b1"))));
    }

    @Test
    public void NONROOT_TAIL_SKIP_CHILDREN_THEN_SIBLING_variation1() {
        exercise(new Policy(22),
                element("root", "tail-skip-child-root",
                        element("branch", "target",
                                element("leaf", "nested")),
                        element("following", "following")));
    }

    @Test
    public void NONROOT_TAIL_SKIP_ENTIRELY_THEN_SIBLING_variation1() {
        exercise(new Policy(23),
                element("root", "tail-skip-entire-root",
                        element("leaf", "target"),
                        element("following", "following"),
                        element("last", "last")));
    }

    @Test
    public void ASCENT_RESETS_REMOVE_RESULT_variation1() {
        exercise(new Policy(13),
                element("root", "reset-tail-remove-root",
                        element("outer", "outer",
                                element("inner", "inner",
                                        element("leaf", "target")))));
    }

    @Test
    public void ASCENT_RESETS_HEAD_REMOVE_RESULT_variation1() {
        exercise(new Policy(12),
                element("resetRoot", "reset-head-remove-root",
                        element("container", "reset-parent",
                                new TextNode("preserved-prefix"),
                                element("terminal", "target"))));
    }

    @Test
    public void ASCENT_RESETS_SKIP_ENTIRELY_RESULT_variation1() {
        exercise(new Policy(24),
                element("root", "reset-skip-root",
                        element("level1", "level1",
                                element("level2", "level2",
                                        element("last", "target")))));
    }

    @Test
    public void MIXED_BRANCH_PRUNE_REMOVE_CONTINUE_variation1() {
        exercise(new Policy(17),
                element("root", "mixed-root",
                        element("branch", "prune",
                                element("retained", "hidden-a"),
                                element("retained", "hidden-b")),
                        element("branch", "remove",
                                element("removed", "removed-child")),
                        element("branch", "continue",
                                element("visited", "visited-a"),
                                element("visited", "visited-b"))));
    }

    @Test
    public void EQUAL_SERIALIZED_SIBLINGS_ORDINAL_SELECTION_variation1() {
        exercise(new Policy(18),
                element("root", "",
                        element("same", "", element("nested", "")),
                        element("same", "", element("nested", "")),
                        element("same", "", element("nested", ""))));
    }

    @Test
    public void DISTINCT_SIBLING_MARKERS_VERIFY_CURSOR_variation1() {
        exercise(new Policy(12),
                element("root", "cursor-root",
                        element("first", "first",
                                element("payload", "first-payload")),
                        element("middle", "target",
                                element("payload", "middle-payload")),
                        element("last", "last",
                                element("payload", "last-payload"))));
    }

    @Test
    public void HETEROGENEOUS_NODE_TREE_CONTINUE_variation1() {
        exercise(new Policy(0),
                element("root", "heterogeneous-root",
                        new TextNode("prefix"),
                        new Comment("marker"),
                        element("branch", "branch",
                                new TextNode("nested text"),
                                element("deep", "deep",
                                        new Comment("deep comment")))));
    }

    @Test
    public void DEEP_REMOVE_THEN_MULTI_LEVEL_ASCENT_variation1() {
        exercise(new Policy(13),
                element("level0", "deep-remove-root",
                        element("level1", "one",
                                element("level2", "two",
                                        element("level3", "three",
                                                element("leaf", "target"))))));
    }
}
