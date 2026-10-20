import org.junit.jupiter.api.Test;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    private static Element element(String name) {
        return new Element(name);
    }

    private static final class RouteFilter implements NodeFilter {
        private final int mode;

        private RouteFilter(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (mode) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth == 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 3:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 4:
                    return "first".equals(node.nodeName()) ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 5:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 6:
                    return "first".equals(node.nodeName()) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 7:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 8:
                    return "first".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 9:
                    return "subtree".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 10:
                    return "middle".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 11:
                    return depth == 2 && "leaf".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 12:
                    return "second".equals(node.nodeName()) ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (mode) {
                case 13:
                    return depth == 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 14:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 15:
                    return "first".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 16:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 17:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 18:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 19:
                    return depth == 1 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    @Test
    public void SINGLE_ELEMENT_CONTINUE_leafElement() {
        NodeFilter filter = new RouteFilter(0);
        Node root = element("root");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_NON_ELEMENT_LEAF_CONTINUE_dataNode() {
        NodeFilter filter = new RouteFilter(0);
        Node root = new DataNode("payload");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_CHILD_DESCENT_AND_ASCENT_singleLeaf() {
        NodeFilter filter = new RouteFilter(0);
        Element root = element("root");
        root.appendElement("child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTIPLE_LEAF_SIBLINGS_CONTINUE_threeElements() {
        NodeFilter filter = new RouteFilter(0);
        Element root = element("root");
        root.appendElement("one");
        root.appendElement("two");
        root.appendElement("three");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEEP_CHAIN_CONTINUE_depthThree() {
        NodeFilter filter = new RouteFilter(0);
        Element root = element("root");
        Element level1 = root.appendElement("level1");
        Element level2 = level1.appendElement("level2");
        level2.appendElement("level3");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_NODE_KIND_CHILDREN_CONTINUE_elementAndText() {
        NodeFilter filter = new RouteFilter(0);
        Element root = element("root");
        root.appendElement("child");
        root.appendChild(new TextNode("text"));
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_STOP_AT_ROOT_immediateStop() {
        NodeFilter filter = new RouteFilter(1);
        Element root = element("root");
        root.appendElement("child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_STOP_AT_DESCENDANT_firstChild() {
        NodeFilter filter = new RouteFilter(2);
        Element root = element("root");
        root.appendElement("child");
        root.appendElement("later");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_STOP_AT_LEAF_firstLeaf() {
        NodeFilter filter = new RouteFilter(13);
        Element root = element("root");
        root.appendElement("first");
        root.appendElement("later");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_STOP_AT_ROOT_afterDescendants() {
        NodeFilter filter = new RouteFilter(14);
        Element root = element("root");
        root.appendElement("child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_ROOT_nestedRoot() {
        NodeFilter filter = new RouteFilter(3);
        Element root = element("root");
        root.appendElement("child").appendElement("grandchild");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_FIRST_SIBLING_nestedFirst() {
        NodeFilter filter = new RouteFilter(4);
        Element root = element("root");
        root.appendElement("first").appendElement("hidden");
        root.appendElement("second");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_ROOT_nonLeafRoot() {
        NodeFilter filter = new RouteFilter(5);
        Element root = element("root");
        root.appendElement("child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_ON_NONFINAL_CHILD_firstLeaf() {
        NodeFilter filter = new RouteFilter(6);
        Element root = element("root");
        root.appendElement("first");
        root.appendElement("second");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_ON_FINAL_CHILD_onlyChild() {
        NodeFilter filter = new RouteFilter(6);
        Element root = element("root");
        root.appendElement("first");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_ROOT_detachedRoot() {
        NodeFilter filter = new RouteFilter(7);
        Element root = element("root");
        root.appendElement("child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_NONFINAL_LEAF_CHILD_firstSibling() {
        NodeFilter filter = new RouteFilter(8);
        Element root = element("root");
        root.appendElement("first");
        root.appendElement("second");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_FINAL_LEAF_CHILD_onlyChild() {
        NodeFilter filter = new RouteFilter(8);
        Element root = element("root");
        root.appendElement("first");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_SUBTREE_CHILD_nestedFirst() {
        NodeFilter filter = new RouteFilter(9);
        Element root = element("root");
        root.appendElement("subtree").appendElement("descendant");
        root.appendElement("later");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_NONFINAL_LEAF_CHILD_firstSibling() {
        NodeFilter filter = new RouteFilter(15);
        Element root = element("root");
        root.appendElement("first");
        root.appendElement("second");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_FINAL_LEAF_CHILD_onlyChild() {
        NodeFilter filter = new RouteFilter(15);
        Element root = element("root");
        root.appendElement("first");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_ROOT_afterNormalTraversal() {
        NodeFilter filter = new RouteFilter(16);
        Element root = element("root");
        root.appendElement("child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_SKIP_CHILDREN_AT_ROOT_leafRoot() {
        NodeFilter filter = new RouteFilter(17);
        Node root = element("root");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_AT_ROOT_leafRoot() {
        NodeFilter filter = new RouteFilter(18);
        Node root = element("root");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONROOT_TAIL_SKIP_RESULT_RESET_finalChild() {
        NodeFilter filter = new RouteFilter(19);
        Element root = element("root");
        root.appendElement("child");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void REMOVE_MIDDLE_OF_THREE_SIBLINGS_middleLeaf() {
        NodeFilter filter = new RouteFilter(10);
        Element root = element("root");
        root.appendElement("first");
        root.appendElement("middle");
        root.appendElement("third");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SKIP_CHILDREN_WITH_NESTED_SIBLING_twoNestedChildren() {
        NodeFilter filter = new RouteFilter(4);
        Element root = element("root");
        root.appendElement("first").appendElement("hidden");
        root.appendElement("second").appendElement("visible");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STOP_AFTER_PRIOR_SIBLING_COMPLETES_secondSibling() {
        NodeFilter filter = new RouteFilter(12);
        Element root = element("root");
        root.appendElement("first");
        root.appendElement("second");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEEP_LEAF_REMOVE_WITH_ANCESTOR_TAILS_depthTwoLeaf() {
        NodeFilter filter = new RouteFilter(11);
        Element root = element("root");
        Element intermediate = root.appendElement("intermediate");
        intermediate.appendElement("leaf");
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
