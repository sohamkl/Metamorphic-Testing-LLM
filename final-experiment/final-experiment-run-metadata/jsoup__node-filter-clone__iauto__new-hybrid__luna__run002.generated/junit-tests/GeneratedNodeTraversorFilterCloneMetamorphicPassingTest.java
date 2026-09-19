import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final class Policy implements NodeFilter {
        private final int mode;

        Policy(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            String name = node.nodeName();
            switch (mode) {
                case 1:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 2:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 3:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 4:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 5:
                    return "skip".equals(name) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 6:
                    return "skip".equals(name) ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 7:
                    return "rm".equals(name) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 8:
                    return "stop".equals(name) ? FilterResult.STOP : FilterResult.CONTINUE;
                case 9:
                    return depth >= 1 && "stop".equals(name)
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 10:
                    return "rm".equals(name) ? FilterResult.CONTINUE : FilterResult.CONTINUE;
                case 11:
                    return FilterResult.CONTINUE;
                case 12:
                    return FilterResult.CONTINUE;
                case 13:
                    if ("skip".equals(name)) return FilterResult.SKIP_CHILDREN;
                    if ("entire".equals(name)) return FilterResult.SKIP_ENTIRELY;
                    if ("rm".equals(name)) return FilterResult.REMOVE;
                    return FilterResult.CONTINUE;
                case 14:
                    return depth >= 1 && "stop".equals(name)
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            String name = node.nodeName();
            switch (mode) {
                case 1:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 10:
                    return "rm".equals(name) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 11:
                    return "skip".equals(name) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 12:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 14:
                    return depth >= 1 && "stop".equals(name)
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    private static Element root(String name) {
        return new Element(name);
    }

    private static Element child(String name) {
        return new Element(name);
    }

    private static Element withChildren(String name, String... children) {
        Element result = root(name);
        for (String child : children)
            result.appendChild(child(child));
        return result;
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            Node sourceRoot,
            Node followUpRoot) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
        Assertions.assertEquals(sourceRoot.outerHtml(), followUpRoot.outerHtml());
    }

    @Test
    public void SINGLE_ROOT_CONTINUE_1_singleNode() {
        Element sourceRoot = root("root");
        NodeFilter sourceFilter = new Policy(0);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void SINGLE_ROOT_SKIP_CHILDREN_1_rootWithOneChild() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("leaf"));
        NodeFilter sourceFilter = new Policy(1);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void SINGLE_ROOT_SKIP_ENTIRELY_1_rootWithMultipleChildren() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("a"));
        sourceRoot.appendChild(child("b"));
        NodeFilter sourceFilter = new Policy(2);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void SINGLE_ROOT_REMOVE_1_deepChain() {
        Element sourceRoot = root("root");
        Element a = child("a");
        Element b = child("b");
        a.appendChild(b);
        sourceRoot.appendChild(a);
        NodeFilter sourceFilter = new Policy(3);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_HEAD_STOP_1_branchingTree() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("a"));
        Element branch = child("branch");
        branch.appendChild(child("leaf"));
        sourceRoot.appendChild(branch);
        NodeFilter sourceFilter = new Policy(4);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_CONTINUE_TO_CHILD_1_mixedSiblings() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("leaf"));
        Element branch = child("branch");
        branch.appendChild(child("nested"));
        sourceRoot.appendChild(branch);
        NodeFilter sourceFilter = new Policy(0);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_SKIP_CHILDREN_WITH_DESCENDANTS_1_singleNode() {
        Element sourceRoot = root("root");
        Element branch = child("branch");
        branch.appendChild(child("nested"));
        sourceRoot.appendChild(branch);
        NodeFilter sourceFilter = new Policy(1);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_WITH_DESCENDANTS_1_rootWithOneChild() {
        Element sourceRoot = root("root");
        Element branch = child("branch");
        branch.appendChild(child("nested"));
        sourceRoot.appendChild(branch);
        NodeFilter sourceFilter = new Policy(2);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void DEEP_CONTINUE_ASCENT_1_deepChain() {
        Element sourceRoot = root("root");
        Element a = child("a");
        Element b = child("b");
        Element c = child("c");
        b.appendChild(c);
        a.appendChild(b);
        sourceRoot.appendChild(a);
        NodeFilter sourceFilter = new Policy(0);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void CHILD_SKIP_ENTIRELY_WITH_SIBLING_1_deepChain() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("skip"));
        sourceRoot.appendChild(child("next"));
        NodeFilter sourceFilter = new Policy(5);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void CHILD_SKIP_CHILDREN_WITH_SIBLING_1_branchingTree() {
        Element sourceRoot = root("root");
        Element skip = child("skip");
        skip.appendChild(child("nested"));
        sourceRoot.appendChild(skip);
        sourceRoot.appendChild(child("next"));
        NodeFilter sourceFilter = new Policy(6);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void CHILD_REMOVE_WITH_LATER_SIBLING_1_mixedSiblings() {
        Element sourceRoot = root("root");
        Element removed = child("rm");
        removed.appendChild(child("nested"));
        sourceRoot.appendChild(removed);
        sourceRoot.appendChild(child("next"));
        NodeFilter sourceFilter = new Policy(7);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void CHILD_REMOVE_AS_LAST_SIBLING_1_singleChild() {
        Element sourceRoot = root("root");
        Element removed = child("rm");
        removed.appendChild(child("nested"));
        sourceRoot.appendChild(removed);
        NodeFilter sourceFilter = new Policy(7);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void DESCENDANT_HEAD_STOP_1_rootWithOneChild() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("stop"));
        sourceRoot.appendChild(child("later"));
        NodeFilter sourceFilter = new Policy(8);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void LEAF_TAIL_STOP_1_rootWithMultipleChildren() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("stop"));
        sourceRoot.appendChild(child("later"));
        NodeFilter sourceFilter = new Policy(14);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_TAIL_STOP_1_deepChain() {
        Element sourceRoot = root("root");
        Element a = child("a");
        a.appendChild(child("b"));
        sourceRoot.appendChild(a);
        NodeFilter sourceFilter = new Policy(12);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void TAIL_REMOVE_WITH_LATER_SIBLING_1_branchingTree() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("rm"));
        sourceRoot.appendChild(child("next"));
        NodeFilter sourceFilter = new Policy(10);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_WITH_SIBLING_1_mixedSiblings() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("skip"));
        sourceRoot.appendChild(child("next"));
        NodeFilter sourceFilter = new Policy(11);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void MIXED_BRANCHING_POLICY_1_singleNode() {
        Element sourceRoot = root("root");
        Element skip = child("skip");
        skip.appendChild(child("inside"));
        sourceRoot.appendChild(skip);
        sourceRoot.appendChild(child("entire"));
        sourceRoot.appendChild(child("rm"));
        sourceRoot.appendChild(child("keep"));
        NodeFilter sourceFilter = new Policy(13);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void MIXED_BRANCHING_POLICY_2_rootWithOneChild() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("skip"));
        sourceRoot.appendChild(child("entire"));
        sourceRoot.appendChild(child("rm"));
        NodeFilter sourceFilter = new Policy(13);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void ROOT_REMOVE_TERMINAL_1_rootWithMultipleChildren() {
        Element sourceRoot = root("root");
        sourceRoot.appendChild(child("a"));
        sourceRoot.appendChild(child("b"));
        NodeFilter sourceFilter = new Policy(3);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void CLONE_STRUCTURAL_EQUIVALENCE_AFTER_REMOVALS_1_deepChain() {
        Element sourceRoot = root("root");
        Element a = child("a");
        a.appendChild(child("rm"));
        sourceRoot.appendChild(a);
        sourceRoot.appendChild(child("rm"));
        NodeFilter sourceFilter = new Policy(7);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }

    @Test
    public void CLONE_STRUCTURAL_EQUIVALENCE_AFTER_REMOVALS_2_branchingTree() {
        Element sourceRoot = root("root");
        Element left = child("left");
        left.appendChild(child("rm"));
        sourceRoot.appendChild(left);
        sourceRoot.appendChild(child("rm"));
        sourceRoot.appendChild(child("keep"));
        NodeFilter sourceFilter = new Policy(7);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, (Node) followUp[1]);
    }
}
