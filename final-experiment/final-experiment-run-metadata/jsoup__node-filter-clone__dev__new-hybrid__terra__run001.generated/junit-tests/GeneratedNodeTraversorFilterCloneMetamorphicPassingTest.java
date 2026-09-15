import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    public static final class FirstChildSkipEntirelyFilter implements NodeFilter {
        public FirstChildSkipEntirelyFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return "first".equals(node.nodeName()) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    public static final class FirstChildRemoveFilter implements NodeFilter {
        public FirstChildRemoveFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return "first".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    public static final class TailRemoveFirstChildFilter implements NodeFilter {
        public TailRemoveFirstChildFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return "first".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
        }
    }

    public static final class TailRemoveDepthOneFilter implements NodeFilter {
        public TailRemoveDepthOneFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return depth == 1 ? FilterResult.REMOVE : FilterResult.CONTINUE;
        }
    }

    public static final class FirstChildSkipChildrenFilter implements NodeFilter {
        public FirstChildSkipChildrenFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return "first".equals(node.nodeName()) ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    @Test
    public void testLEAF_CONTINUE_TAIL_CONTINUE_leafRoot() {
        Element root = new Element("root");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy00();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSINGLE_CHILD_DESCEND_AND_ASCEND_oneLeafChild() {
        Element root = new Element("root");
        root.appendElement("child");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy00();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDIRECT_SIBLING_TRANSITION_twoLeafChildren() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy00();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testDEEP_LAST_DESCENDANT_ASCENT_CHAIN_threeNodeChain() {
        Element root = new Element("root");
        root.appendElement("child").appendElement("grandchild");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy00();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testHEAD_SKIP_CHILDREN_ON_NONLEAF_rootPrunesDescendants() {
        Element root = new Element("root");
        root.appendElement("child").appendElement("grandchild");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy01();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testHEAD_SKIP_ENTIRELY_NONROOT_WITH_SIBLING_firstChildSkipped() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        NodeFilter filter = new FirstChildSkipEntirelyFilter();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testHEAD_SKIP_ENTIRELY_ROOT_TERMINAL_nonLeafRoot() {
        Element root = new Element("root");
        root.appendElement("child");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy02();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testHEAD_STOP_AT_ROOT_rootWithChild() {
        Element root = new Element("root");
        root.appendElement("child");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy04();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testHEAD_STOP_AT_DESCENDANT_onlyChildStops() {
        Element root = new Element("root");
        root.appendElement("child");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy08(1);

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testHEAD_REMOVE_CHILD_WITH_NEXT_SIBLING_firstChildRemoved() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        NodeFilter filter = new FirstChildRemoveFilter();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testHEAD_REMOVE_FINAL_CHILD_AND_ASCEND_onlyChildRemoved() {
        Element root = new Element("root");
        root.appendElement("child");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy07(1);

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testHEAD_REMOVE_ROOT_TERMINAL_detachedRoot() {
        Element root = new Element("root");
        root.appendElement("child");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy03();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testTAIL_REMOVE_CHILD_WITH_NEXT_SIBLING_firstChildRemoved() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("second");
        NodeFilter filter = new TailRemoveFirstChildFilter();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testTAIL_REMOVE_FINAL_CHILD_AND_ASCEND_onlyChildRemoved() {
        Element root = new Element("root");
        root.appendElement("child");
        NodeFilter filter = new TailRemoveDepthOneFilter();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testTAIL_REMOVE_ROOT_TERMINAL_leafRoot() {
        Element root = new Element("root");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy11();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testTAIL_STOP_AT_LEAF_leafRoot() {
        Element root = new Element("root");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy12();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testTAIL_SKIP_CHILDREN_ROOT_TERMINAL_leafRoot() {
        Element root = new Element("root");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy09();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testTAIL_SKIP_ENTIRELY_ROOT_TERMINAL_leafRoot() {
        Element root = new Element("root");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy10();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSKIP_CHILDREN_PARENT_WITH_FOLLOWING_SIBLING_firstParentPruned() {
        Element root = new Element("root");
        root.appendElement("first").appendElement("hidden");
        root.appendElement("second");
        NodeFilter filter = new FirstChildSkipChildrenFilter();

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSKIP_ENTIRELY_FINAL_CHILD_ASCENT_onlyChildSkipped() {
        Element root = new Element("root");
        root.appendElement("child");
        NodeFilter filter = new MtllmGeneratedNodeFilterCallback15ar4v1Policy06(1);

        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
