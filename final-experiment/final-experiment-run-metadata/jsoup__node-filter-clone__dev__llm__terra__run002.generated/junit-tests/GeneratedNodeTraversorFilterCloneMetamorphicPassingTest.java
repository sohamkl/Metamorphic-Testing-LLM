import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static Element e(String tag) {
        return new Element(Tag.valueOf(tag), "");
    }

    private static void run(NodeFilter filter, Node root) {
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static final class AttributePolicy implements NodeFilter {
        @Override
        public FilterResult head(Node node, int depth) {
            return result(node, "h");
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return result(node, "t");
        }

        private FilterResult result(Node node, String attribute) {
            String value = node.attr(attribute);
            return value.isEmpty() ? FilterResult.CONTINUE : FilterResult.valueOf(value);
        }
    }

    private static final class DepthSkipPolicy implements NodeFilter {
        private final int depth;

        DepthSkipPolicy(int depth) {
            this.depth = depth;
        }

        @Override
        public FilterResult head(Node node, int currentDepth) {
            return currentDepth == depth ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int currentDepth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class MarkerRemovePolicy implements NodeFilter {
        private final String marker;

        MarkerRemovePolicy(String marker) {
            this.marker = marker;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return marker.equals(node.attr("data-marker")) ? FilterResult.REMOVE : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class TypeSkipPolicy implements NodeFilter {
        private final String typeName;

        TypeSkipPolicy(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return node.getClass().getSimpleName().equals(typeName)
                    ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class MutationPolicy implements NodeFilter {
        @Override
        public FilterResult head(Node node, int depth) {
            if (depth == 1 && node instanceof Element)
                node.attr("data-visited", "yes");
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    @Test
    public void LEAF_ROOT_CONTINUES_leafElement() {
        run(new AttributePolicy(), e("root"));
    }

    @Test
    public void ROOT_WITH_SINGLE_LEAF_CHILD_CONTINUES_singleChild() {
        Element root = e("root");
        root.appendElement("child");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TWO_LEAF_SIBLINGS_CONTINUE_twoElements() {
        Element root = e("root");
        root.appendElement("first");
        root.appendElement("second");
        run(new AttributePolicy(), root);
    }

    @Test
    public void THREE_LEVEL_CHAIN_CONTINUE_nestedChain() {
        Element root = e("root");
        root.appendElement("one").appendElement("two");
        run(new AttributePolicy(), root);
    }

    @Test
    public void NESTED_FIRST_CHILD_THEN_SIBLING_CONTINUE_mixedTree() {
        Element root = e("root");
        root.appendElement("first").appendElement("nested");
        root.appendElement("second");
        run(new AttributePolicy(), root);
    }

    @Test
    public void NON_ELEMENT_LEAF_ROOT_CONTINUE_textNode() {
        run(new AttributePolicy(), new TextNode("detached text"));
    }

    @Test
    public void NON_ELEMENT_DESCENDANT_CONTINUE_commentChild() {
        Element root = e("root");
        root.appendChild(new Comment("commentary"));
        run(new AttributePolicy(), root);
    }

    @Test
    public void DOCUMENT_LIKE_ROOT_CONTINUE_document() {
        Document document = Jsoup.parse("<section><p>content</p></section>");
        run(new AttributePolicy(), document);
    }

    @Test
    public void HEAD_STOP_AT_ROOT_stopImmediately() {
        Element root = e("root");
        root.attr("h", "STOP");
        root.appendElement("child");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_STOP_AT_FIRST_CHILD_stopChild() {
        Element root = e("root");
        root.appendElement("first").attr("h", "STOP");
        root.appendElement("later");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_STOP_AT_LATER_SIBLING_stopSecond() {
        Element root = e("root");
        root.appendElement("first");
        root.appendElement("second").attr("h", "STOP");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_ROOT_pruneRoot() {
        Element root = e("root");
        root.attr("h", "SKIP_CHILDREN");
        root.appendElement("child").appendElement("grandchild");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_INTERNAL_NODE_pruneFirstBranch() {
        Element root = e("root");
        root.appendElement("first").attr("h", "SKIP_CHILDREN").appendElement("hidden");
        root.appendElement("second");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_FINAL_CHILD_pruneLastBranch() {
        Element root = e("root");
        root.appendElement("first");
        root.appendElement("last").attr("h", "SKIP_CHILDREN").appendElement("hidden");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_ROOT_skipRoot() {
        Element root = e("root");
        root.attr("h", "SKIP_ENTIRELY");
        root.appendElement("child");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_FIRST_CHILD_skipFirst() {
        Element root = e("root");
        root.appendElement("first").attr("h", "SKIP_ENTIRELY").appendElement("hidden");
        root.appendElement("second");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_FINAL_CHILD_skipLast() {
        Element root = e("root");
        root.appendElement("first");
        root.appendElement("last").attr("h", "SKIP_ENTIRELY").appendElement("hidden");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_ROOT_removeResultAtRoot() {
        Element root = e("root");
        root.attr("h", "REMOVE");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_FIRST_CHILD_WITH_SIBLING_removeFirst() {
        Element root = e("root");
        root.appendElement("first").attr("h", "REMOVE");
        root.appendElement("second");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_MIDDLE_CHILD_removeMiddle() {
        Element root = e("root");
        root.appendElement("first");
        root.appendElement("middle").attr("h", "REMOVE");
        root.appendElement("last");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_FINAL_LEAF_CHILD_removeLast() {
        Element root = e("root");
        root.appendElement("first");
        root.appendElement("last").attr("h", "REMOVE");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_INTERNAL_SUBTREE_removeBranch() {
        Element root = e("root");
        root.appendElement("branch").attr("h", "REMOVE").appendElement("nested");
        root.appendElement("survivor");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_ONLY_CHILD_removeOnlyChild() {
        Element root = e("root");
        root.appendElement("only").attr("h", "REMOVE");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_STOP_AT_LEAF_ROOT_stopTail() {
        Element root = e("root");
        root.attr("t", "STOP");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_STOP_AT_FIRST_CHILD_stopChildTail() {
        Element root = e("root");
        root.appendElement("first").attr("t", "STOP");
        root.appendElement("second");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_STOP_AFTER_NESTED_ASCENT_stopInternalTail() {
        Element root = e("root");
        root.appendElement("branch").attr("t", "STOP").appendElement("leaf");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_REMOVE_FIRST_CHILD_WITH_SIBLING_removeFirstTail() {
        Element root = e("root");
        root.appendElement("first").attr("t", "REMOVE");
        root.appendElement("second");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_REMOVE_FINAL_CHILD_removeLastTail() {
        Element root = e("root");
        root.appendElement("first");
        root.appendElement("last").attr("t", "REMOVE");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_REMOVE_INTERNAL_NODE_removeBranchTail() {
        Element root = e("root");
        root.appendElement("branch").attr("t", "REMOVE").appendElement("leaf");
        root.appendElement("survivor");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_REMOVE_ROOT_removeResultAtCompletion() {
        Element root = e("root");
        root.appendElement("child");
        root.attr("t", "REMOVE");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_SKIP_CHILDREN_AT_ROOT_returnSkipChildren() {
        Element root = e("root");
        root.attr("t", "SKIP_CHILDREN");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_AT_ROOT_returnSkipEntirely() {
        Element root = e("root");
        root.attr("t", "SKIP_ENTIRELY");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_ON_CHILD_WITH_SIBLING_childTailResult() {
        Element root = e("root");
        root.appendElement("first").attr("t", "SKIP_ENTIRELY");
        root.appendElement("second");
        run(new AttributePolicy(), root);
    }

    @Test
    public void TAIL_SKIP_CHILDREN_ON_FINAL_CHILD_finalTailResult() {
        Element root = e("root");
        root.appendElement("first");
        root.appendElement("last").attr("t", "SKIP_CHILDREN");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_THEN_TAIL_REMOVE_pruneAndRemove() {
        Element root = e("root");
        Element branch = root.appendElement("branch");
        branch.attr("h", "SKIP_CHILDREN").attr("t", "REMOVE");
        branch.appendElement("hidden");
        root.appendElement("survivor");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_THEN_TAIL_STOP_pruneThenStop() {
        Element root = e("root");
        root.attr("h", "SKIP_CHILDREN").attr("t", "STOP");
        root.appendElement("hidden");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_CONTINUE_TAIL_REMOVE_NESTED_LEAF_removeNestedFirst() {
        Element root = e("root");
        Element branch = root.appendElement("branch");
        branch.appendElement("first").attr("t", "REMOVE");
        branch.appendElement("second");
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_REMOVE_NESTED_FINAL_LEAF_removeNestedLast() {
        Element root = e("root");
        Element branch = root.appendElement("branch");
        branch.appendElement("first");
        branch.appendElement("last").attr("h", "REMOVE");
        run(new AttributePolicy(), root);
    }

    @Test
    public void ROOT_WITH_MANY_DIRECT_CHILDREN_CONTINUE_threeLeaves() {
        Element root = e("root");
        root.appendElement("one");
        root.appendElement("two");
        root.appendElement("three");
        root.appendElement("four");
        run(new AttributePolicy(), root);
    }

    @Test
    public void MIXED_NODE_TYPES_AMONG_SIBLINGS_elementAndText() {
        Element root = e("root");
        root.appendElement("element");
        root.appendChild(new TextNode("text sibling"));
        root.appendChild(new Comment("comment sibling"));
        run(new AttributePolicy(), root);
    }

    @Test
    public void HEAD_POLICY_PARAMETERIZED_BY_DEPTH_skipConfiguredDepth() {
        Element root = e("root");
        root.appendElement("branch").appendElement("nested").appendElement("deep");
        run(new DepthSkipPolicy(1), root);
    }

    @Test
    public void HEAD_POLICY_PARAMETERIZED_BY_NODE_CONTENT_removeMarkedNode() {
        Element root = e("root");
        root.appendElement("before");
        root.appendElement("target").attr("data-marker", "remove-me");
        root.appendElement("after");
        run(new MarkerRemovePolicy("remove-me"), root);
    }

    @Test
    public void HEAD_POLICY_PARAMETERIZED_BY_NODE_TYPE_skipCommentNodes() {
        Element root = e("root");
        root.appendChild(new Comment("ignored"));
        root.appendElement("survivor");
        run(new TypeSkipPolicy("Comment"), root);
    }

    @Test
    public void DETERMINISTIC_CALLBACK_DOM_MUTATION_WITH_CONTINUE_markChildren() {
        Element root = e("root");
        root.appendElement("first");
        root.appendElement("second").appendElement("nested");
        run(new MutationPolicy(), root);
    }

    @Test
    public void EMPTY_ELEMENT_ROOT_CONTINUE_emptyElement() {
        run(new AttributePolicy(), e("empty"));
    }

    @Test
    public void ROOT_CHILDREN_ALL_REMOVED_removeEveryDirectChild() {
        Element root = e("root");
        root.appendElement("first").attr("h", "REMOVE");
        root.appendElement("second").attr("h", "REMOVE");
        root.appendElement("third").attr("h", "REMOVE");
        run(new AttributePolicy(), root);
    }
}
