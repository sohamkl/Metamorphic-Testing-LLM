import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    private static final class ContinueFilter implements NodeFilter {
        public ContinueFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class MarkerFilter implements NodeFilter {
        public MarkerFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return markedResult(node, "data-head");
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return markedResult(node, "data-tail");
        }

        private static FilterResult markedResult(Node node, String attribute) {
            if (!(node instanceof Element)) {
                return FilterResult.CONTINUE;
            }
            String marker = ((Element) node).attr(attribute);
            return marker.isEmpty() ? FilterResult.CONTINUE : FilterResult.valueOf(marker);
        }
    }

    private static final class HeadStopFilter implements NodeFilter {
        public HeadStopFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.STOP;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class HeadSkipChildrenFilter implements NodeFilter {
        public HeadSkipChildrenFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.SKIP_CHILDREN;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class HeadSkipEntirelyFilter implements NodeFilter {
        public HeadSkipEntirelyFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.SKIP_ENTIRELY;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class HeadRemoveFilter implements NodeFilter {
        public HeadRemoveFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.REMOVE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class TailStopFilter implements NodeFilter {
        public TailStopFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.STOP;
        }
    }

    private static final class TailRemoveFilter implements NodeFilter {
        public TailRemoveFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.REMOVE;
        }
    }

    private static final class TailSkipChildrenFilter implements NodeFilter {
        public TailSkipChildrenFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.SKIP_CHILDREN;
        }
    }

    private static final class TailSkipEntirelyFilter implements NodeFilter {
        public TailSkipEntirelyFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.SKIP_ENTIRELY;
        }
    }

    private static Element element(String tag, Node... children) {
        Element element = new Element(tag);
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static Element markedHead(String tag, NodeFilter.FilterResult result, Node... children) {
        Element element = element(tag, children);
        element.attr("data-head", result.name());
        return element;
    }

    private static Element markedTail(String tag, NodeFilter.FilterResult result, Node... children) {
        Element element = element(tag, children);
        element.attr("data-tail", result.name());
        return element;
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            Node sourceRoot,
            Node followUpRoot) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Different terminal FilterResult values: source=" + sourceOutput
                            + ", follow-up=" + followUpOutput);
        }
        String sourceHtml = sourceRoot.outerHtml();
        String followUpHtml = followUpRoot.outerHtml();
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError(
                    "Different final outerHtml values: source=" + sourceHtml
                            + ", follow-up=" + followUpHtml);
        }
    }

    @Test
    public void SINGLETON_CONTINUE_variation1_emptyDocument() {
        NodeFilter filter = new ContinueFilter();
        Node root = new Document("");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SINGLETON_HEAD_STOP_variation1_leafElement() {
        NodeFilter filter = new HeadStopFilter();
        Node root = new Element("singleton-stop");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SINGLETON_HEAD_SKIP_CHILDREN_variation1_textLeaf() {
        NodeFilter filter = new HeadSkipChildrenFilter();
        Node root = new TextNode("skip-children");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SINGLETON_HEAD_SKIP_ENTIRELY_variation1_emptyDocument() {
        NodeFilter filter = new HeadSkipEntirelyFilter();
        Node root = new Document("https://example.test/");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void SINGLETON_HEAD_REMOVE_variation1_leafElement() {
        NodeFilter filter = new HeadRemoveFilter();
        Node root = new Element("detached-remove");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_TAIL_STOP_variation1_textLeaf() {
        NodeFilter filter = new TailStopFilter();
        Node root = new TextNode("tail-stop");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_TAIL_REMOVE_variation1_emptyDocument() {
        NodeFilter filter = new TailRemoveFilter();
        Node root = new Document("");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_TAIL_SKIP_CHILDREN_variation1_leafElement() {
        NodeFilter filter = new TailSkipChildrenFilter();
        Node root = new Element("tail-skip-children");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_TAIL_SKIP_ENTIRELY_variation1_commentLeaf() {
        NodeFilter filter = new TailSkipEntirelyFilter();
        Node root = new Comment("tail-skip-entirely");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ONE_CHILD_FULL_TRAVERSAL_variation1_documentAndElement() {
        NodeFilter filter = new ContinueFilter();
        Document root = new Document("");
        root.appendChild(new Element("only-child"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LINEAR_THREE_LEVEL_ASCENT_variation1_nestedElements() {
        NodeFilter filter = new ContinueFilter();
        Node root = element("level-zero", element("level-one", element("level-two")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void WIDE_SIBLING_TRAVERSAL_variation1_fourLeaves() {
        NodeFilter filter = new ContinueFilter();
        Node root = element("wide",
                new Element("first"),
                new Element("second"),
                new Element("third"),
                new Element("fourth"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void BRANCHING_DEPTH_FIRST_ORDER_variation1_documentBranches() {
        NodeFilter filter = new ContinueFilter();
        Document root = new Document("");
        root.appendChild(element("first-branch",
                element("nested", new TextNode("deep"))));
        root.appendChild(element("second-branch", new TextNode("later")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_WITH_SIBLING_variation1_firstBranch() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedHead("pruned", NodeFilter.FilterResult.SKIP_CHILDREN,
                        element("hidden", new TextNode("unvisited"))),
                element("following", new TextNode("visited")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_LAST_SIBLING_variation1_ascent() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                new Element("earlier"),
                markedHead("last-branch", NodeFilter.FilterResult.SKIP_CHILDREN,
                        element("hidden-child", new TextNode("hidden"))));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_SKIP_ENTIRELY_WITH_SIBLING_variation1_retainedBranch() {
        NodeFilter filter = new MarkerFilter();
        Document root = new Document("");
        root.appendChild(markedHead("skipped", NodeFilter.FilterResult.SKIP_ENTIRELY,
                element("hidden", new TextNode("retained"))));
        root.appendChild(element("next", new TextNode("visited")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_SKIP_ENTIRELY_LAST_SIBLING_variation1_parentTail() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                new Element("first"),
                markedHead("last-skipped", NodeFilter.FilterResult.SKIP_ENTIRELY,
                        new Element("unvisited-child")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_REMOVE_WITH_SIBLING_variation1_removedSubtree() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedHead("remove-branch", NodeFilter.FilterResult.REMOVE,
                        element("discarded", new TextNode("discarded-text"))),
                element("survivor", new TextNode("visited")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_REMOVE_LAST_SIBLING_variation1_ascentRemoval() {
        NodeFilter filter = new MarkerFilter();
        Document root = new Document("");
        root.appendChild(element("container",
                new Element("kept"),
                markedHead("remove-last", NodeFilter.FilterResult.REMOVE,
                        new Element("discarded-child"))));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void INTERNAL_HEAD_STOP_variation1_stopAtBranch() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedHead("stop-branch", NodeFilter.FilterResult.STOP,
                        element("never-visited", new TextNode("hidden"))),
                new Element("later-sibling"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_HEAD_SKIP_CHILDREN_variation1_leafThenSibling() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedHead("leaf-skip-children", NodeFilter.FilterResult.SKIP_CHILDREN),
                new Element("sibling"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_HEAD_SKIP_ENTIRELY_variation1_documentLeaf() {
        NodeFilter filter = new MarkerFilter();
        Document root = new Document("");
        root.appendChild(markedHead("leaf-skipped", NodeFilter.FilterResult.SKIP_ENTIRELY));
        root.appendChild(new Element("following"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_HEAD_REMOVE_WITH_SIBLING_variation1_firstLeaf() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedHead("remove-leaf", NodeFilter.FilterResult.REMOVE),
                new Element("following-leaf"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_HEAD_REMOVE_LAST_CHILD_variation1_nestedLastLeaf() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                element("parent",
                        new Element("kept"),
                        markedHead("remove-last-leaf", NodeFilter.FilterResult.REMOVE)));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void LEAF_HEAD_STOP_variation1_afterEarlierLeaf() {
        NodeFilter filter = new MarkerFilter();
        Document root = new Document("");
        root.appendChild(new Element("earlier"));
        root.appendChild(markedHead("stop-leaf", NodeFilter.FilterResult.STOP));
        root.appendChild(new Element("later"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void CHILD_TAIL_REMOVE_WITH_SIBLING_variation1_subtreeThenSibling() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedTail("remove-after-tail", NodeFilter.FilterResult.REMOVE,
                        new TextNode("visited-content")),
                element("following", new TextNode("survives")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void CHILD_TAIL_REMOVE_ON_ASCENT_variation1_finalChild() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                new Element("kept"),
                markedTail("remove-on-ascent", NodeFilter.FilterResult.REMOVE,
                        new TextNode("visited-before-removal")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void CHILD_TAIL_STOP_variation1_documentChild() {
        NodeFilter filter = new MarkerFilter();
        Document root = new Document("");
        root.appendChild(markedTail("tail-stop-child", NodeFilter.FilterResult.STOP,
                new TextNode("visited")));
        root.appendChild(new Element("not-visited"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void CHILD_TAIL_SKIP_CHILDREN_RESULT_variation1_nonRemovingTail() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedTail("tail-skip-children", NodeFilter.FilterResult.SKIP_CHILDREN),
                new Element("following"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void CHILD_TAIL_SKIP_ENTIRELY_RESULT_variation1_nonRemovingTail() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedTail("tail-skip-entirely", NodeFilter.FilterResult.SKIP_ENTIRELY,
                        new TextNode("visited")),
                new Element("following"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_TAIL_STOP_AFTER_DESCENDANTS_variation1_documentTree() {
        NodeFilter filter = new MarkerFilter();
        Element root = markedTail("root", NodeFilter.FilterResult.STOP,
                element("first", new TextNode("one")),
                element("second", new Comment("two")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void STOP_AFTER_PRIOR_REMOVAL_variation1_removeThenStop() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedHead("removed-first", NodeFilter.FilterResult.REMOVE),
                markedHead("stop-second", NodeFilter.FilterResult.STOP),
                new Element("unvisited-third"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void CONSECUTIVE_SIBLING_REMOVALS_variation1_twoRemoved() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedHead("remove-one", NodeFilter.FilterResult.REMOVE),
                markedHead("remove-two", NodeFilter.FilterResult.REMOVE),
                element("third", new TextNode("visited")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ALL_CHILDREN_REMOVED_variation1_documentChildren() {
        NodeFilter filter = new MarkerFilter();
        Document root = new Document("");
        root.appendChild(markedHead("remove-a", NodeFilter.FilterResult.REMOVE,
                new TextNode("subtree-a")));
        root.appendChild(markedHead("remove-b", NodeFilter.FilterResult.REMOVE,
                new TextNode("subtree-b")));
        root.appendChild(markedHead("remove-c", NodeFilter.FilterResult.REMOVE));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void MIXED_SIBLING_CONTROL_RESULTS_variation1_fourControls() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedHead("removed", NodeFilter.FilterResult.REMOVE,
                        new Element("removed-child")),
                markedHead("skipped-entirely", NodeFilter.FilterResult.SKIP_ENTIRELY,
                        new Element("retained-child")),
                markedHead("skipped-children", NodeFilter.FilterResult.SKIP_CHILDREN,
                        new Element("unvisited-child")),
                element("continued", new TextNode("fully-visited")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void MULTIPLE_PRUNED_SUBTREES_variation1_threeBranches() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                markedHead("skip-children-branch", NodeFilter.FilterResult.SKIP_CHILDREN,
                        element("hidden-a", new TextNode("a"))),
                markedHead("skip-entirely-branch", NodeFilter.FilterResult.SKIP_ENTIRELY,
                        element("hidden-b", new TextNode("b"))),
                element("continued-branch",
                        element("visible", new TextNode("c"))));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void DEEP_DESCENDANT_STOP_variation1_depthThree() {
        NodeFilter filter = new MarkerFilter();
        Document root = new Document("");
        root.appendChild(element("depth-one",
                element("depth-two",
                        markedHead("depth-three-stop", NodeFilter.FilterResult.STOP,
                                new Element("not-visited")))));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void EMPTY_DOCUMENT_ROOT_variation1_noChildren() {
        NodeFilter filter = new ContinueFilter();
        Node root = new Document("https://empty.example/");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void NON_ELEMENT_LEAF_ROOT_variation1_comment() {
        NodeFilter filter = new ContinueFilter();
        Node root = new Comment("detached non-element leaf");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void MIXED_NODE_KIND_TREE_variation1_elementsTextAndComments() {
        NodeFilter filter = new ContinueFilter();
        Document root = new Document("");
        root.appendChild(element("container",
                new TextNode("text"),
                new Comment("comment"),
                element("nested", new TextNode("nested text"))));
        root.appendChild(new Comment("document comment"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_NONEMPTY_variation1_retainedDescendants() {
        NodeFilter filter = new HeadSkipChildrenFilter();
        Node root = element("root",
                element("child", new TextNode("retained")),
                new Element("second-child"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_NONEMPTY_variation1_linearTree() {
        NodeFilter filter = new HeadSkipEntirelyFilter();
        Node root = element("root",
                element("child",
                        element("grandchild", new TextNode("retained"))));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_REMOVE_NONEMPTY_variation1_documentSubtree() {
        NodeFilter filter = new HeadRemoveFilter();
        Document root = new Document("");
        root.appendChild(element("first", new TextNode("one")));
        root.appendChild(element("second", new TextNode("two")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void ROOT_STOP_NONEMPTY_variation1_branchingElement() {
        NodeFilter filter = new HeadStopFilter();
        Node root = element("root",
                element("branch-a", new TextNode("a")),
                element("branch-b", new TextNode("b")),
                element("branch-c", new TextNode("c")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    public void DEEP_INTERNAL_SUBTREE_REMOVE_variation1_resumeAtAncestorSibling() {
        NodeFilter filter = new MarkerFilter();
        Node root = element("root",
                element("first-branch",
                        element("depth-one",
                                markedHead("depth-two-remove", NodeFilter.FilterResult.REMOVE,
                                        element("discarded-child",
                                                new TextNode("discarded"))),
                                element("depth-two-survivor",
                                        new TextNode("visited-after-removal")))),
                element("outside-branch", new TextNode("also-visited")));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }
}
