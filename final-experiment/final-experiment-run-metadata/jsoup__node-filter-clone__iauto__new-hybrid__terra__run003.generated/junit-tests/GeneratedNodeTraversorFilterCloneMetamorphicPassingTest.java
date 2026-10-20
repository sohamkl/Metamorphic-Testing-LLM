import java.util.Objects;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final String NEVER = "__never__";

    private static Element element(String name) {
        return new Element(name, "");
    }

    private static final class NamedFilter implements NodeFilter {
        private final String headName;
        private final FilterResult headResult;
        private final String tailName;
        private final FilterResult tailResult;

        public NamedFilter(String headName, FilterResult headResult,
                           String tailName, FilterResult tailResult) {
            this.headName = headName;
            this.headResult = headResult;
            this.tailName = tailName;
            this.tailResult = tailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return node.nodeName().equals(headName) ? headResult : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return node.nodeName().equals(tailName) ? tailResult : FilterResult.CONTINUE;
        }
    }

    private static NodeFilter configured(String headName, FilterResult headResult,
                                         String tailName, FilterResult tailResult) {
        return new NamedFilter(headName, headResult, tailName, tailResult);
    }

    private static void exercise(NodeFilter sourceFilter, Node sourceRoot) {
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(
                sourceFilter, sourceRoot);
        Node followUpRoot = (Node) followUp[1];

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], followUpRoot);

        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, followUpRoot);
    }

    private static void assertMetamorphicRelation(FilterResult sourceOutput,
                                                   FilterResult followUpOutput,
                                                   Node sourceRoot,
                                                   Node followUpRoot) {
        if (!Objects.equals(sourceOutput, followUpOutput)) {
            throw new AssertionError("Filter results differ: source=" + sourceOutput
                    + ", follow-up=" + followUpOutput);
        }

        String sourceHtml = sourceRoot.outerHtml();
        String followUpHtml = followUpRoot.outerHtml();
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError("Final outerHtml differs: source=" + sourceHtml
                    + ", follow-up=" + followUpHtml);
        }
    }

    @Test
    public void CONTINUE_SINGLE_LEAF_ROOT_leafElement() {
        exercise(configured(NEVER, FilterResult.CONTINUE, NEVER, FilterResult.CONTINUE),
                element("single"));
    }

    @Test
    public void CONTINUE_DEEP_TREE_WITH_SIBLINGS_nestedBranch() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("grandchild"));
        root.appendChild(first);
        root.appendChild(element("second"));

        exercise(configured(NEVER, FilterResult.CONTINUE, NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void CONTINUE_MIXED_NODE_KINDS_textAndElement() {
        Element root = element("mixed");
        root.appendChild(new TextNode("alpha"));
        Element child = element("child");
        child.appendChild(new TextNode("beta"));
        root.appendChild(child);

        exercise(configured(NEVER, FilterResult.CONTINUE, NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void HEAD_STOP_AT_ROOT_immediateStop() {
        Element root = element("stopRoot");
        root.appendChild(element("child"));

        exercise(configured("stopRoot", FilterResult.STOP, NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void HEAD_STOP_AT_DESCENDANT_firstChild() {
        Element root = element("stopParent");
        root.appendChild(element("stopChild"));
        root.appendChild(element("later"));

        exercise(configured("stopChild", FilterResult.STOP, NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void TAIL_STOP_AT_ROOT_leafTail() {
        exercise(configured(NEVER, FilterResult.CONTINUE,
                "tailStop", FilterResult.STOP), element("tailStop"));
    }

    @Test
    public void TAIL_STOP_AT_DESCENDANT_beforeSibling() {
        Element root = element("tailParent");
        root.appendChild(element("stopLeaf"));
        root.appendChild(element("laterLeaf"));

        exercise(configured(NEVER, FilterResult.CONTINUE,
                "stopLeaf", FilterResult.STOP), root);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_ROOT_nonEmptyRoot() {
        Element root = element("skipRoot");
        root.appendChild(element("hidden"));

        exercise(configured("skipRoot", FilterResult.SKIP_CHILDREN,
                NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_INTERIOR_NODE_resumeSibling() {
        Element root = element("skipParent");
        Element skipped = element("skipped");
        skipped.appendChild(element("hidden"));
        root.appendChild(skipped);
        root.appendChild(element("visible"));

        exercise(configured("skipped", FilterResult.SKIP_CHILDREN,
                NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_THEN_TAIL_STOP_interiorNode() {
        Element root = element("skipStopRoot");
        Element skipped = element("skipStop");
        skipped.appendChild(element("hidden"));
        root.appendChild(skipped);
        root.appendChild(element("later"));

        exercise(configured("skipStop", FilterResult.SKIP_CHILDREN,
                "skipStop", FilterResult.STOP), root);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_ON_ROOT_prunedRoot() {
        Element root = element("entireRoot");
        root.appendChild(element("child"));

        exercise(configured("entireRoot", FilterResult.SKIP_ENTIRELY,
                NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_ON_INTERIOR_SUBTREE_followingSibling() {
        Element root = element("entireParent");
        Element skipped = element("entireBranch");
        skipped.appendChild(element("hidden"));
        root.appendChild(skipped);
        root.appendChild(element("survivor"));

        exercise(configured("entireBranch", FilterResult.SKIP_ENTIRELY,
                NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void HEAD_REMOVE_ROOT_detachedRoot() {
        Element root = element("removeRoot");
        root.appendChild(element("child"));

        exercise(configured("removeRoot", FilterResult.REMOVE,
                NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void HEAD_REMOVE_SUBTREE_WITH_FOLLOWING_SIBLING_firstBranch() {
        Element root = element("removeParent");
        Element removed = element("removeBranch");
        removed.appendChild(element("nested"));
        root.appendChild(removed);
        root.appendChild(element("survivor"));

        exercise(configured("removeBranch", FilterResult.REMOVE,
                NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void HEAD_REMOVE_LAST_LEAF_ASCENDS_onlyChild() {
        Element root = element("removeLastParent");
        root.appendChild(element("removeOnly"));

        exercise(configured("removeOnly", FilterResult.REMOVE,
                NEVER, FilterResult.CONTINUE), root);
    }

    @Test
    public void TAIL_REMOVE_CHILD_WITH_FOLLOWING_SIBLING_firstLeaf() {
        Element root = element("tailRemoveParent");
        root.appendChild(element("removeLeaf"));
        root.appendChild(element("survivorLeaf"));

        exercise(configured(NEVER, FilterResult.CONTINUE,
                "removeLeaf", FilterResult.REMOVE), root);
    }

    @Test
    public void TAIL_REMOVE_LAST_CHILD_ASCENDS_onlyLeaf() {
        Element root = element("tailRemoveLastParent");
        root.appendChild(element("removeLast"));

        exercise(configured(NEVER, FilterResult.CONTINUE,
                "removeLast", FilterResult.REMOVE), root);
    }

    @Test
    public void TAIL_REMOVE_ROOT_detachedLeaf() {
        exercise(configured(NEVER, FilterResult.CONTINUE,
                "tailRemoveRoot", FilterResult.REMOVE), element("tailRemoveRoot"));
    }

    @Test
    public void TAIL_SKIP_CHILDREN_AT_ROOT_leafRoot() {
        exercise(configured(NEVER, FilterResult.CONTINUE,
                "tailSkipChildren", FilterResult.SKIP_CHILDREN), element("tailSkipChildren"));
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_AT_ROOT_leafRoot() {
        exercise(configured(NEVER, FilterResult.CONTINUE,
                "tailSkipEntirely", FilterResult.SKIP_ENTIRELY), element("tailSkipEntirely"));
    }

    @Test
    public void TAIL_SKIP_CHILDREN_ON_CHILD_WITH_SIBLING_firstLeaf() {
        Element root = element("tailSkipChildParent");
        root.appendChild(element("skipLeaf"));
        root.appendChild(element("followingLeaf"));

        exercise(configured(NEVER, FilterResult.CONTINUE,
                "skipLeaf", FilterResult.SKIP_CHILDREN), root);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_ON_CHILD_WITH_SIBLING_firstLeaf() {
        Element root = element("tailEntireChildParent");
        root.appendChild(element("entireLeaf"));
        root.appendChild(element("followingLeaf"));

        exercise(configured(NEVER, FilterResult.CONTINUE,
                "entireLeaf", FilterResult.SKIP_ENTIRELY), root);
    }

    @Test
    public void MULTI_LEVEL_ASCENT_AFTER_LAST_DESCENDANT_unaryChain() {
        Element root = element("chainRoot");
        Element one = element("one");
        Element two = element("two");
        Element three = element("three");
        two.appendChild(three);
        one.appendChild(two);
        root.appendChild(one);

        exercise(configured(NEVER, FilterResult.CONTINUE, NEVER, FilterResult.CONTINUE), root);
    }
}
