import java.util.Objects;

import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    private static Element element(String name) {
        return new Element(name, "");
    }

    private static Element chain(int nodeCount) {
        Element root = element("root");
        Element current = root;
        for (int index = 1; index < nodeCount; index++) {
            Element child = element("node" + index);
            current.appendChild(child);
            current = child;
        }
        return root;
    }

    private static Element twoLeaves() {
        Element root = element("root");
        root.appendChild(element("first"));
        root.appendChild(element("second"));
        return root;
    }

    private static Element twoLeavesWithThird() {
        Element root = twoLeaves();
        root.appendChild(element("third"));
        return root;
    }

    private static void execute(NodeFilter filter, Node sourceRoot) {
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        Node followUpRoot = (Node) followUp[1];
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], followUpRoot);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, followUpRoot);
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            Node sourceRoot,
            Node followUpRoot) {
        if (!Objects.equals(sourceOutput, followUpOutput)) {
            throw new AssertionError(
                    "Filtering the source and clone returned different results: source="
                            + sourceOutput + ", follow-up=" + followUpOutput);
        }
        String sourceHtml = sourceRoot.outerHtml();
        String followUpHtml = followUpRoot.outerHtml();
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError(
                    "Filtering the source and clone produced different DOMs:\nsource="
                            + sourceHtml + "\nfollow-up=" + followUpHtml);
        }
    }

    private static final class RuleFilter implements NodeFilter {
        private final int rule;

        private RuleFilter(int rule) {
            this.rule = rule;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (rule) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth == 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 3:
                case 17:
                    return "first".equals(node.nodeName())
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 4:
                    return "first".equals(node.nodeName())
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 5:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 6:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 7:
                    return depth == 3 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 15:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 16:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (rule) {
                case 8:
                    return "first".equals(node.nodeName())
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 9:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 10:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 11:
                    return "only".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 12:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 13:
                    return "first".equals(node.nodeName())
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 14:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 17:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    @Test
    public void LEAF_ELEMENT_CONTINUES_leafElement() {
        execute(new RuleFilter(0), element("leaf"));
    }

    @Test
    public void SINGLE_CHILD_CHAIN_ASCENDS_threeElementChain() {
        execute(new RuleFilter(0), chain(3));
    }

    @Test
    public void MULTIPLE_SIBLINGS_CONTINUE_threeLeafSiblings() {
        execute(new RuleFilter(0), twoLeavesWithThird());
    }

    @Test
    public void BRANCHING_TREE_CONTINUE_twoBranches() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("firstLeaf"));
        Element second = element("second");
        second.appendChild(element("secondLeafA"));
        second.appendChild(element("secondLeafB"));
        root.appendChild(first);
        root.appendChild(second);
        execute(new RuleFilter(0), root);
    }

    @Test
    public void MIXED_CONTENT_ELEMENT_CONTINUE_textCommentElement() {
        Element root = element("root");
        root.appendChild(new TextNode("text"));
        root.appendChild(new Comment("note"));
        root.appendChild(element("child"));
        execute(new RuleFilter(0), root);
    }

    @Test
    public void DETACHED_DOCUMENT_ROOT_documentTree() {
        Document document = new Document("");
        Element html = document.appendElement("html");
        html.appendElement("body").appendElement("p").text("content");
        execute(new RuleFilter(0), document);
    }

    @Test
    public void TEXT_NODE_ROOT_nonEmptyText() {
        execute(new RuleFilter(0), new TextNode("plain text"));
    }

    @Test
    public void COMMENT_NODE_ROOT_nonEmptyComment() {
        execute(new RuleFilter(0), new Comment("comment data"));
    }

    @Test
    public void DATA_NODE_ROOT_nonEmptyData() {
        execute(new RuleFilter(0), new DataNode("var x = 1;"));
    }

    @Test
    public void DOCUMENT_TYPE_ROOT_doctypeLeaf() {
        execute(new RuleFilter(0), new DocumentType("html", "", ""));
    }

    @Test
    public void HEAD_STOP_AT_ROOT_nonLeafRoot() {
        Element root = element("root");
        root.appendChild(element("child"));
        execute(new RuleFilter(1), root);
    }

    @Test
    public void HEAD_STOP_AT_DESCENDANT_firstChild() {
        Element root = element("root");
        Element child = element("first");
        child.appendChild(element("grandchild"));
        root.appendChild(child);
        execute(new RuleFilter(2), root);
    }

    @Test
    public void TAIL_STOP_AT_LEAF_firstLeaf() {
        execute(new RuleFilter(8), twoLeaves());
    }

    @Test
    public void TAIL_STOP_AT_ANCESTOR_rootTail() {
        Element root = element("root");
        Element child = element("child");
        child.appendChild(element("leaf"));
        root.appendChild(child);
        execute(new RuleFilter(9), root);
    }

    @Test
    public void SKIP_CHILDREN_ON_NONLEAF_firstBranch() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("hidden"));
        root.appendChild(first);
        root.appendChild(element("second"));
        execute(new RuleFilter(3), root);
    }

    @Test
    public void SKIP_CHILDREN_ON_LEAF_rootLeaf() {
        execute(new RuleFilter(15), element("leaf"));
    }

    @Test
    public void SKIP_ENTIRELY_INTERNAL_NODE_firstBranch() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("hidden"));
        root.appendChild(first);
        root.appendChild(element("second"));
        execute(new RuleFilter(4), root);
    }

    @Test
    public void SKIP_ENTIRELY_AT_ROOT_nonLeafRoot() {
        Element root = element("root");
        root.appendChild(element("child"));
        execute(new RuleFilter(16), root);
    }

    @Test
    public void HEAD_REMOVE_BEFORE_SIBLING_firstLeaf() {
        execute(new RuleFilter(5), twoLeaves());
    }

    @Test
    public void HEAD_REMOVE_ONLY_CHILD_AFTER_ASCENT_onlyChild() {
        Element root = element("root");
        Element only = element("first");
        root.appendChild(only);
        execute(new RuleFilter(5), root);
    }

    @Test
    public void HEAD_REMOVE_NONLEAF_SUBTREE_firstBranch() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("nested"));
        root.appendChild(first);
        root.appendChild(element("second"));
        execute(new RuleFilter(5), root);
    }

    @Test
    public void HEAD_REMOVE_AT_ROOT_detachedRoot() {
        Element root = element("root");
        root.appendChild(new TextNode("content"));
        execute(new RuleFilter(6), root);
    }

    @Test
    public void TAIL_REMOVE_BEFORE_SIBLING_firstLeaf() {
        execute(new RuleFilter(10), twoLeaves());
    }

    @Test
    public void TAIL_REMOVE_LAST_CHILD_AFTER_ASCENT_onlyChild() {
        Element root = element("root");
        root.appendChild(element("only"));
        execute(new RuleFilter(11), root);
    }

    @Test
    public void TAIL_REMOVE_AT_ROOT_leafRoot() {
        execute(new RuleFilter(12), element("root"));
    }

    @Test
    public void SKIP_CHILDREN_THEN_TAIL_REMOVE_firstBranch() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("hidden"));
        root.appendChild(first);
        root.appendChild(element("second"));
        execute(new RuleFilter(17), root);
    }

    @Test
    public void TAIL_SKIP_CHILDREN_NONROOT_firstLeaf() {
        execute(new RuleFilter(13), twoLeaves());
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_AT_ROOT_leafRoot() {
        execute(new RuleFilter(14), element("root"));
    }

    @Test
    public void FIRST_SIBLING_SKIP_ENTIRELY_THEN_BRANCH_secondBranch() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("skippedLeaf"));
        Element second = element("second");
        second.appendChild(element("visitedLeaf"));
        root.appendChild(first);
        root.appendChild(second);
        execute(new RuleFilter(4), root);
    }

    @Test
    public void DEPTH_PREDICATE_ON_DEEP_LEAF_fourNodeChain() {
        execute(new RuleFilter(7), chain(4));
    }
}
