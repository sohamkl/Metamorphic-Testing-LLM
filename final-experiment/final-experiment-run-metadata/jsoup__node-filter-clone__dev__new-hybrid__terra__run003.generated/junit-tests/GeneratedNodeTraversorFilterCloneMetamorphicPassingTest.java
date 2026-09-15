import org.junit.jupiter.api.Test;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    private static Element node(String name, Node... children) {
        Element element = new Element(name, "");
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    public static final class ContinueFilter implements NodeFilter {

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

    public static final class PlanFilter implements NodeFilter {

        private final String headName;

        private final FilterResult headResult;

        private final String tailName;

        private final FilterResult tailResult;

        public PlanFilter(String headName, FilterResult headResult, String tailName, FilterResult tailResult) {
            this.headName = headName;
            this.headResult = headResult;
            this.tailName = tailName;
            this.tailResult = tailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return headName != null && headName.equals(node.nodeName()) ? headResult : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return tailName != null && tailName.equals(node.nodeName()) ? tailResult : FilterResult.CONTINUE;
        }
    }

    @Test
    public void CONTINUE_DETACHED_LEAF_ROOT_leafElement() {
        NodeFilter filter = new ContinueFilter();
        Node root = node("root");
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTINUE_SINGLE_CHILD_ASCENT_leafChild() {
        NodeFilter filter = new ContinueFilter();
        Node root = node("root", node("child"));
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTINUE_TWO_LEAF_SIBLINGS_twoChildren() {
        NodeFilter filter = new ContinueFilter();
        Node root = node("root", node("first"), node("second"));
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTINUE_DEEP_LINEAR_CHAIN_depthThree() {
        NodeFilter filter = new ContinueFilter();
        Node root = node("root", node("one", node("two", node("three"))));
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTINUE_BRANCH_AFTER_NESTED_SUBTREE_branchingTree() {
        NodeFilter filter = new ContinueFilter();
        Node root = node("root", node("first", node("nested", node("deep"))), node("later"));
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_THEN_TAIL_REMOVE_ROOT_prunesThenReturnsRemove() {
        NodeFilter filter = new PlanFilter("root", NodeFilter.FilterResult.SKIP_CHILDREN, "root", NodeFilter.FilterResult.REMOVE);
        Node root = node("root", node("child", node("grandchild")));
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTINUE_NON_ELEMENT_LEAF_NODE_dataNode() {
        NodeFilter filter = new ContinueFilter();
        Node root = new DataNode("detached-data");
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
