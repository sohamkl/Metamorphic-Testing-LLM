import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    private static Object[] generateFollowUp(NodeFilter filter, Node root) {
        return NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
    }

    private static Element element(String name) {
        return new Element(name);
    }

    private static Element branchingRoot() {
        Element root = element("root");
        Element a = element("a");
        a.appendChild(element("aChild"));
        root.appendChild(a);
        root.appendChild(element("b"));
        return root;
    }

    private static Element threeChildRoot() {
        Element root = element("root");
        Element a = element("a");
        a.appendChild(element("aChild"));
        root.appendChild(a);
        root.appendChild(element("b"));
        root.appendChild(element("c"));
        return root;
    }

    private static Element deepChain() {
        Element root = element("root");
        Element one = element("one");
        Element two = element("two");
        Element three = element("three");
        root.appendChild(one);
        one.appendChild(two);
        two.appendChild(three);
        return root;
    }

    private static Element oneLeafChildRoot() {
        Element root = element("root");
        root.appendChild(element("a"));
        return root;
    }

    private static Element twoLeafChildRoot() {
        Element root = element("root");
        root.appendChild(element("a"));
        root.appendChild(element("b"));
        return root;
    }

    private static Document documentRoot() {
        Document document = new Document("");
        document.appendChild(branchingRoot());
        return document;
    }

    private static final class ContinueFilter implements NodeFilter {
        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class CodeFilter implements NodeFilter {
        private final int code;

        private CodeFilter(int code) {
            this.code = code;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (code) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth >= 2 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 3:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 4:
                    return "a".equals(node.nodeName()) ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 5:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 6:
                    return "a".equals(node.nodeName()) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 7:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 8:
                    return "a".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 17:
                    if ("a".equals(node.nodeName())) {
                        return FilterResult.SKIP_CHILDREN;
                    }
                    if ("b".equals(node.nodeName())) {
                        return FilterResult.REMOVE;
                    }
                    return FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (code) {
                case 9:
                    return "a".equals(node.nodeName()) ? FilterResult.STOP : FilterResult.CONTINUE;
                case 10:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 11:
                    return "a".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 12:
                    return depth == 1 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 13:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 14:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 15:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 16:
                    return "a".equals(node.nodeName()) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    @Test
    public void SINGLE_LEAF_CONTINUE_variation1() {
        NodeFilter filter = new ContinueFilter();
        Node root = new DataNode("leaf");
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BRANCHING_CONTINUE_DEPTH_FIRST_variation1() {
        NodeFilter filter = new CodeFilter(0);
        Node root = branchingRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEEP_CHAIN_CONTINUE_variation1() {
        NodeFilter filter = new ContinueFilter();
        Node root = deepChain();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_ELEMENT_LEAF_CONTINUE_variation1() {
        NodeFilter filter = new CodeFilter(0);
        Node root = new DataNode("payload");
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTAINER_ROOT_CONTINUE_variation1() {
        NodeFilter filter = new ContinueFilter();
        Node root = documentRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_STOP_AT_ROOT_variation1() {
        NodeFilter filter = new CodeFilter(1);
        Node root = new DataNode("stop");
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_STOP_AT_DEEP_DESCENDANT_variation1() {
        NodeFilter filter = new CodeFilter(2);
        Node root = deepChain();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_ROOT_variation1() {
        NodeFilter filter = new CodeFilter(3);
        Node root = branchingRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_AT_INTERNAL_NODE_variation1() {
        NodeFilter filter = new CodeFilter(4);
        Node root = branchingRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_ROOT_variation1() {
        NodeFilter filter = new CodeFilter(5);
        Node root = branchingRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_AT_INTERNAL_NODE_variation1() {
        NodeFilter filter = new CodeFilter(6);
        Node root = branchingRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_ROOT_variation1() {
        NodeFilter filter = new CodeFilter(7);
        Node root = branchingRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_LEAF_WITH_SIBLING_variation1() {
        NodeFilter filter = new CodeFilter(8);
        Node root = twoLeafChildRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_LAST_CHILD_variation1() {
        NodeFilter filter = new CodeFilter(8);
        Node root = oneLeafChildRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_NONLEAF_SUBTREE_variation1() {
        NodeFilter filter = new CodeFilter(8);
        Node root = branchingRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_STOP_AT_LEAF_variation1() {
        NodeFilter filter = new CodeFilter(9);
        Node root = twoLeafChildRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_STOP_AT_ROOT_variation1() {
        NodeFilter filter = new CodeFilter(10);
        Node root = branchingRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_LEAF_WITH_SIBLING_variation1() {
        NodeFilter filter = new CodeFilter(11);
        Node root = twoLeafChildRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_ONLY_CHILD_variation1() {
        NodeFilter filter = new CodeFilter(12);
        Node root = oneLeafChildRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_ROOT_variation1() {
        NodeFilter filter = new CodeFilter(13);
        Node root = new DataNode("root-data");
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_SKIP_CHILDREN_AT_ROOT_variation1() {
        NodeFilter filter = new CodeFilter(14);
        Node root = documentRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_AT_ROOT_variation1() {
        NodeFilter filter = new CodeFilter(15);
        Node root = new DataNode("tail-skip");
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_SKIP_RESULT_ON_CHILD_WITH_SIBLING_variation1() {
        NodeFilter filter = new CodeFilter(16);
        Node root = twoLeafChildRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_PRUNE_REMOVE_AND_CONTINUE_variation1() {
        NodeFilter filter = new CodeFilter(17);
        Node root = threeChildRoot();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARAMETERIZED_EQUIVALENT_FILTER_variation1() {
        NodeFilter filter = new CodeFilter(4);
        Node root = deepChain();
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
