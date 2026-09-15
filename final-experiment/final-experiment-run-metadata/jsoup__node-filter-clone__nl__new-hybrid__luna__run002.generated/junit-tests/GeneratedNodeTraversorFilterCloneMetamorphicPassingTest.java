import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
    private static final String HEAD = "h";
    private static final String TAIL = "t";

    private static Element element(String name) {
        return new Element(name);
    }

    private static Element marked(String name, String headAction, String tailAction) {
        Element element = element(name);
        if (headAction != null) {
            element.attr(HEAD, headAction);
        }
        if (tailAction != null) {
            element.attr(TAIL, tailAction);
        }
        return element;
    }

    private static final class Fixture {
        final Element root;
        final boolean requiresNonContinue;

        Fixture(Element root, boolean requiresNonContinue) {
            this.root = root;
            this.requiresNonContinue = requiresNonContinue;
        }
    }

    private static final class Observation {
        final NodeFilter.FilterResult result;
        final List<String> callbacks;
        final List<NodeFilter.FilterResult> actions;
        final String outerHtml;
        final boolean sawNonContinue;

        Observation(NodeFilter.FilterResult result, RecordingFilter filter, Node root) {
            this.result = result;
            this.callbacks = new ArrayList<>(filter.callbacks);
            this.actions = new ArrayList<>(filter.actions);
            this.outerHtml = root.outerHtml();
            this.sawNonContinue = filter.sawNonContinue;
        }
    }

    private static final class RecordingFilter implements NodeFilter {
        final List<String> callbacks = new ArrayList<>();
        final List<NodeFilter.FilterResult> actions = new ArrayList<>();
        boolean sawNonContinue;

        private NodeFilter.FilterResult action(Node node, boolean head) {
            String value = node.attr(head ? HEAD : TAIL);
            if (value == null || value.isEmpty()) {
                return NodeFilter.FilterResult.CONTINUE;
            }
            return NodeFilter.FilterResult.valueOf(value);
        }

        private NodeFilter.FilterResult record(Node node, int depth, boolean head) {
            NodeFilter.FilterResult result = action(node, head);
            callbacks.add((head ? "H:" : "T:") + depth + ":" + node.nodeName() + ":" + result);
            actions.add(result);
            if (result != NodeFilter.FilterResult.CONTINUE) {
                sawNonContinue = true;
            }
            return result;
        }

        @Override
        public NodeFilter.FilterResult head(Node node, int depth) {
            return record(node, depth, true);
        }

        @Override
        public NodeFilter.FilterResult tail(Node node, int depth) {
            return record(node, depth, false);
        }
    }

    private static Element generateFollowUp(Fixture source) {
        return (Element) source.root.clone();
    }

    private static Observation run(Node root) {
        RecordingFilter filter = new RecordingFilter();
        NodeFilter.FilterResult result = NodeTraversor.filter(filter, root);
        return new Observation(result, filter, root);
    }

    private static void assertMetamorphicRelationFor(Fixture source) {
        Observation original = run(source.root);
        Element followUpRoot = generateFollowUp(source);
        Observation followUp = run(followUpRoot);
        assertMetamorphicRelation(original, followUp);
        if (source.requiresNonContinue) {
            assertTrue(original.sawNonContinue);
            assertTrue(followUp.sawNonContinue);
        }
    }

    private static void assertMetamorphicRelation(
            Observation original, Observation followUp) {
        assertEquals(original.result, followUp.result);
        assertEquals(original.callbacks, followUp.callbacks);
        assertEquals(original.actions, followUp.actions);
        assertEquals(original.outerHtml, followUp.outerHtml);
    }

    @Test
    public void ROOT_LEAF_CONTINUE_variation1() {
        assertMetamorphicRelationFor(new Fixture(element("root"), false));
    }

    @Test
    public void ROOT_LEAF_SKIP_CHILDREN_variation1() {
        Element root = element("root");
        root.appendChild(marked("child", "SKIP_CHILDREN", "SKIP_CHILDREN"));
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void ROOT_LEAF_SKIP_ENTIRELY_variation1() {
        Element root = marked("root", "SKIP_ENTIRELY", null);
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void ROOT_LEAF_REMOVE_variation1() {
        Element root = marked("root", "REMOVE", null);
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void ROOT_HEAD_STOP_variation1() {
        Element root = marked("root", "STOP", null);
        root.appendElement("child").appendElement("grandchild");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void ROOT_WITH_ONE_CONTINUED_CHILD_variation1() {
        Element root = element("root");
        root.appendElement("child");
        assertMetamorphicRelationFor(new Fixture(root, false));
    }

    @Test
    public void ROOT_WITH_TWO_CONTINUED_SIBLINGS_variation1() {
        Element root = element("root");
        root.appendElement("first");
        root.appendElement("second");
        assertMetamorphicRelationFor(new Fixture(root, false));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_WITH_DESCENDANTS_variation1() {
        Element root = marked("root", "SKIP_CHILDREN", "SKIP_CHILDREN");
        root.appendElement("child").appendElement("grandchild");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void ID_INTERIOR_SKIP_ENTIRELY_WITH_FOLLOWING_SIBLING_variation1() {
        Element root = element("root");
        root.appendChild(marked("first", "SKIP_ENTIRELY", null));
        root.appendElement("second");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void INTERIOR_SKIP_CHILDREN_WITH_FOLLOWING_SIBLING_variation1() {
        Element root = element("root");
        root.appendChild(marked("first", "SKIP_CHILDREN", "SKIP_CHILDREN"))
                .appendElement("hidden");
        root.appendElement("second");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void STOP_DESCENDANT_HEAD_variation1() {
        Element root = element("root");
        root.appendChild(marked("first", "STOP", null));
        root.appendElement("later");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void STOP_FROM_LEAF_TAIL_variation1() {
        Element root = element("root");
        root.appendChild(marked("leaf", null, "STOP"));
        root.appendElement("later");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void STOP_FROM_INTERNAL_TAIL_variation1() {
        Element root = element("root");
        Element internal = marked("internal", null, "STOP");
        internal.appendElement("child");
        root.appendChild(internal);
        root.appendElement("sibling");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void THREE_LEVEL_CONTINUE_CHAIN_variation1() {
        Element root = element("root");
        root.appendElement("one").appendElement("two").appendElement("three");
        assertMetamorphicRelationFor(new Fixture(root, false));
    }

    @Test
    public void ROOT_REMOVE_WITH_DESCENDANTS_variation1() {
        Element root = marked("root", "REMOVE", null);
        root.appendElement("child").appendElement("grandchild");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }
}
