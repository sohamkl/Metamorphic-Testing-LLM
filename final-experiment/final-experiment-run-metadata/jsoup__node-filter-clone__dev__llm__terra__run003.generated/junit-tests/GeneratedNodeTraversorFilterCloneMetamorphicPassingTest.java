import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final class Policy implements NodeFilter {
        private final String selectedId;
        private final FilterResult selectedHeadResult;
        private final FilterResult selectedTailResult;

        private Policy(String selectedId, FilterResult selectedHeadResult, FilterResult selectedTailResult) {
            this.selectedId = selectedId;
            this.selectedHeadResult = selectedHeadResult;
            this.selectedTailResult = selectedTailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return selectedId.equals(node.attr("id")) ? selectedHeadResult : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return selectedId.equals(node.attr("id")) ? selectedTailResult : FilterResult.CONTINUE;
        }
    }

    @Test
    public void CONTINUE_SINGLE_LEAF_ROOT_variation1() {
        Element root = new Element("root").attr("id", "root");
        NodeFilter sourceFilter = new Policy("missing", FilterResult.CONTINUE, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTINUE_ROOT_WITH_MULTIPLE_CHILDREN_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("first").attr("id", "first");
        root.appendElement("second").attr("id", "second");
        root.appendElement("third").attr("id", "third");
        NodeFilter sourceFilter = new Policy("missing", FilterResult.CONTINUE, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTINUE_DEEP_LAST_CHILD_ASCENT_variation1() {
        Element root = new Element("root").attr("id", "root");
        Element one = root.appendElement("one").attr("id", "one");
        Element two = one.appendElement("two").attr("id", "two");
        Element three = two.appendElement("three").attr("id", "three");
        three.appendElement("four").attr("id", "four");
        NodeFilter sourceFilter = new Policy("missing", FilterResult.CONTINUE, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTINUE_MIXED_NODE_TYPES_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendText("leading text");
        Element child = root.appendElement("child").attr("id", "child");
        child.appendText("nested text");
        root.appendElement("sibling").attr("id", "sibling");
        NodeFilter sourceFilter = new Policy("missing", FilterResult.CONTINUE, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_ROOT_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("child").attr("id", "child").appendElement("grandchild").attr("id", "grandchild");
        root.appendElement("other").attr("id", "other");
        NodeFilter sourceFilter = new Policy("root", FilterResult.SKIP_CHILDREN, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_LEAF_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("before").attr("id", "before");
        root.appendElement("leaf").attr("id", "leaf");
        root.appendElement("after").attr("id", "after");
        NodeFilter sourceFilter = new Policy("leaf", FilterResult.SKIP_CHILDREN, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_INTERNAL_SIBLING_variation1() {
        Element root = new Element("root").attr("id", "root");
        Element selected = root.appendElement("selected").attr("id", "selected");
        selected.appendElement("hidden").attr("id", "hidden");
        root.appendElement("following").attr("id", "following");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.SKIP_CHILDREN, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_ON_LEAF_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("before").attr("id", "before");
        root.appendElement("selected").attr("id", "selected");
        root.appendElement("after").attr("id", "after");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.SKIP_ENTIRELY, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_ON_INTERNAL_SUBTREE_variation1() {
        Element root = new Element("root").attr("id", "root");
        Element selected = root.appendElement("selected").attr("id", "selected");
        selected.appendElement("child").attr("id", "child").appendElement("grandchild").attr("id", "grandchild");
        root.appendElement("after").attr("id", "after");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.SKIP_ENTIRELY, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_ON_ROOT_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("child").attr("id", "child");
        root.appendElement("other").attr("id", "other");
        NodeFilter sourceFilter = new Policy("root", FilterResult.SKIP_ENTIRELY, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_LEAF_WITH_NEXT_SIBLING_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("before").attr("id", "before");
        root.appendElement("selected").attr("id", "selected");
        root.appendElement("next").attr("id", "next");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.REMOVE, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_LAST_CHILD_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("first").attr("id", "first");
        root.appendElement("selected").attr("id", "selected");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.REMOVE, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_INTERNAL_SUBTREE_variation1() {
        Element root = new Element("root").attr("id", "root");
        Element selected = root.appendElement("selected").attr("id", "selected");
        selected.appendElement("child").attr("id", "child").appendElement("deep").attr("id", "deep");
        root.appendElement("survivor").attr("id", "survivor");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.REMOVE, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_REMOVE_ROOT_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("child").attr("id", "child").appendElement("deep").attr("id", "deep");
        NodeFilter sourceFilter = new Policy("root", FilterResult.REMOVE, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_LEAF_WITH_NEXT_SIBLING_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("before").attr("id", "before");
        root.appendElement("selected").attr("id", "selected");
        root.appendElement("next").attr("id", "next");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.CONTINUE, FilterResult.REMOVE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_LAST_CHILD_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("first").attr("id", "first");
        root.appendElement("selected").attr("id", "selected");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.CONTINUE, FilterResult.REMOVE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_INTERNAL_NODE_AFTER_DESCENDANTS_variation1() {
        Element root = new Element("root").attr("id", "root");
        Element selected = root.appendElement("selected").attr("id", "selected");
        selected.appendElement("child").attr("id", "child");
        selected.appendElement("otherChild").attr("id", "otherChild");
        root.appendElement("after").attr("id", "after");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.CONTINUE, FilterResult.REMOVE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_REMOVE_ROOT_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("child").attr("id", "child");
        root.appendElement("other").attr("id", "other");
        NodeFilter sourceFilter = new Policy("root", FilterResult.CONTINUE, FilterResult.REMOVE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_STOP_AT_ROOT_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("child").attr("id", "child").appendElement("deep").attr("id", "deep");
        NodeFilter sourceFilter = new Policy("root", FilterResult.STOP, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HEAD_STOP_AT_DESCENDANT_variation1() {
        Element root = new Element("root").attr("id", "root");
        Element first = root.appendElement("first").attr("id", "first");
        first.appendElement("selected").attr("id", "selected");
        root.appendElement("after").attr("id", "after");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.STOP, FilterResult.CONTINUE);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_STOP_AT_LEAF_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("before").attr("id", "before");
        root.appendElement("selected").attr("id", "selected");
        root.appendElement("after").attr("id", "after");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.CONTINUE, FilterResult.STOP);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_STOP_AT_ANCESTOR_variation1() {
        Element root = new Element("root").attr("id", "root");
        Element selected = root.appendElement("selected").attr("id", "selected");
        selected.appendElement("child").attr("id", "child").appendElement("deep").attr("id", "deep");
        root.appendElement("after").attr("id", "after");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.CONTINUE, FilterResult.STOP);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_STOP_AT_ROOT_variation1() {
        Element root = new Element("root").attr("id", "root");
        Element child = root.appendElement("child").attr("id", "child");
        child.appendElement("deep").attr("id", "deep");
        root.appendElement("sibling").attr("id", "sibling");
        NodeFilter sourceFilter = new Policy("root", FilterResult.CONTINUE, FilterResult.STOP);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_SKIP_CHILDREN_AT_ROOT_variation1() {
        Element root = new Element("root").attr("id", "root");
        Element child = root.appendElement("child").attr("id", "child");
        child.appendElement("deep").attr("id", "deep");
        root.appendElement("sibling").attr("id", "sibling");
        NodeFilter sourceFilter = new Policy("root", FilterResult.CONTINUE, FilterResult.SKIP_CHILDREN);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_AT_ROOT_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendText("text");
        Element child = root.appendElement("child").attr("id", "child");
        child.appendElement("deep").attr("id", "deep");
        NodeFilter sourceFilter = new Policy("root", FilterResult.CONTINUE, FilterResult.SKIP_ENTIRELY);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_ON_NONROOT_FINAL_CHILD_variation1() {
        Element root = new Element("root").attr("id", "root");
        root.appendElement("first").attr("id", "first");
        root.appendElement("selected").attr("id", "selected");
        NodeFilter sourceFilter = new Policy("selected", FilterResult.CONTINUE, FilterResult.SKIP_ENTIRELY);

        FilterResult sourceOutput = NodeTraversor.filter(sourceFilter, root);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, root);
        FilterResult followUpOutput = NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
