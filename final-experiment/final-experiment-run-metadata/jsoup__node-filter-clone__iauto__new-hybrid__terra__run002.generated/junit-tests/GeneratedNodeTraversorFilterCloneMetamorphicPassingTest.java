import java.util.Objects;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    private static Element element(String tag) {
        return new Element(tag);
    }

    private static void assertMetamorphicRelation(
            FilterResult sourceOutput,
            FilterResult followUpOutput,
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
                    "Filtering the source and clone produced different DOMs: source="
                            + sourceHtml + ", follow-up=" + followUpHtml);
        }
    }

    private static final class ScenarioFilter implements NodeFilter {
        private final int mode;

        private ScenarioFilter(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (mode) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth == 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 4:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 5:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 6:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 7:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 8:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 9:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 15:
                    if (depth == 1 && node.siblingIndex() == 0) {
                        return FilterResult.SKIP_CHILDREN;
                    }
                    if (depth == 1 && node.siblingIndex() == 1) {
                        return FilterResult.REMOVE;
                    }
                    return FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (mode) {
                case 3:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 10:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 11:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 12:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 13:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 14:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    @Test
    void CONTINUE_SINGLE_LEAF_ROOT_variation1() {
        Element root = element("leaf");
        NodeFilter filter = new ScenarioFilter(0);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void CONTINUE_DEEP_SINGLE_CHILD_CHAIN_variation1() {
        Element root = element("root");
        Element child = element("child");
        child.appendChild(element("grandchild"));
        root.appendChild(child);
        NodeFilter filter = new ScenarioFilter(0);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void CONTINUE_BRANCHING_SIBLING_ADVANCE_variation1() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("nested"));
        root.appendChild(first);
        root.appendChild(element("second"));
        NodeFilter filter = new ScenarioFilter(0);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_STOP_AT_ROOT_variation1() {
        Element root = element("root");
        root.appendChild(element("child"));
        NodeFilter filter = new ScenarioFilter(1);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_STOP_AT_DESCENDANT_variation1() {
        Element root = element("root");
        root.appendChild(element("child"));
        root.appendChild(element("unvisitedSibling"));
        NodeFilter filter = new ScenarioFilter(2);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void TAIL_STOP_AT_LEAF_ROOT_variation1() {
        Element root = element("leaf");
        NodeFilter filter = new ScenarioFilter(3);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void TAIL_STOP_AFTER_DESCENDANT_SUBTREE_variation1() {
        Element root = element("root");
        Element child = element("child");
        child.appendChild(element("grandchild"));
        root.appendChild(child);
        NodeFilter filter = new ScenarioFilter(3);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_SKIP_CHILDREN_AT_ROOT_variation1() {
        Element root = element("root");
        root.appendChild(element("child"));
        root.appendChild(element("sibling"));
        NodeFilter filter = new ScenarioFilter(4);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_SKIP_CHILDREN_INTERNAL_WITH_SIBLING_variation1() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("hidden"));
        root.appendChild(first);
        root.appendChild(element("second"));
        NodeFilter filter = new ScenarioFilter(5);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_SKIP_CHILDREN_ON_LEAF_variation1() {
        Element root = element("root");
        root.appendChild(element("leaf"));
        NodeFilter filter = new ScenarioFilter(5);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_SKIP_ENTIRELY_AT_ROOT_variation1() {
        Element root = element("root");
        root.appendChild(element("child"));
        NodeFilter filter = new ScenarioFilter(6);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_SKIP_ENTIRELY_INTERNAL_SUBTREE_variation1() {
        Element root = element("root");
        Element first = element("first");
        first.appendChild(element("hidden"));
        root.appendChild(first);
        root.appendChild(element("second"));
        NodeFilter filter = new ScenarioFilter(7);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_SKIP_ENTIRELY_LEAF_WITH_SIBLING_variation1() {
        Element root = element("root");
        root.appendChild(element("firstLeaf"));
        root.appendChild(element("secondLeaf"));
        NodeFilter filter = new ScenarioFilter(7);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_REMOVE_ROOT_variation1() {
        Element root = element("root");
        root.appendChild(element("child"));
        NodeFilter filter = new ScenarioFilter(8);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_REMOVE_LEAF_WITH_NEXT_SIBLING_variation1() {
        Element root = element("root");
        root.appendChild(element("removedLeaf"));
        root.appendChild(element("survivor"));
        NodeFilter filter = new ScenarioFilter(9);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_REMOVE_LAST_LEAF_UNWIND_variation1() {
        Element root = element("root");
        root.appendChild(element("removedLeaf"));
        NodeFilter filter = new ScenarioFilter(9);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void HEAD_REMOVE_INTERNAL_SUBTREE_variation1() {
        Element root = element("root");
        Element removed = element("removed");
        removed.appendChild(element("descendant"));
        root.appendChild(removed);
        root.appendChild(element("survivor"));
        NodeFilter filter = new ScenarioFilter(9);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void TAIL_REMOVE_ROOT_variation1() {
        Element root = element("leaf");
        NodeFilter filter = new ScenarioFilter(10);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void TAIL_REMOVE_LEAF_WITH_NEXT_SIBLING_variation1() {
        Element root = element("root");
        root.appendChild(element("removedLeaf"));
        root.appendChild(element("survivor"));
        NodeFilter filter = new ScenarioFilter(11);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void TAIL_REMOVE_LAST_CHILD_UNWIND_variation1() {
        Element root = element("root");
        root.appendChild(element("removedLeaf"));
        NodeFilter filter = new ScenarioFilter(11);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void TAIL_REMOVE_INTERNAL_SUBTREE_variation1() {
        Element root = element("root");
        Element removed = element("removed");
        removed.appendChild(element("descendant"));
        root.appendChild(removed);
        root.appendChild(element("survivor"));
        NodeFilter filter = new ScenarioFilter(11);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void TAIL_SKIP_CHILDREN_AT_ROOT_variation1() {
        Element root = element("leaf");
        NodeFilter filter = new ScenarioFilter(12);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void TAIL_SKIP_ENTIRELY_AT_ROOT_variation1() {
        Element root = element("leaf");
        NodeFilter filter = new ScenarioFilter(13);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void TAIL_SKIP_ENTIRELY_ON_NONROOT_WITH_SIBLING_variation1() {
        Element root = element("root");
        root.appendChild(element("firstLeaf"));
        root.appendChild(element("secondLeaf"));
        NodeFilter filter = new ScenarioFilter(14);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }

    @Test
    void MIXED_SKIP_CHILDREN_THEN_REMOVE_SIBLING_variation1() {
        Element root = element("root");
        Element retained = element("retained");
        retained.appendChild(element("skippedDescendant"));
        root.appendChild(retained);
        root.appendChild(element("removedSibling"));
        NodeFilter filter = new ScenarioFilter(15);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);

        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput, root, (Node) followUp[1]);
    }
}
