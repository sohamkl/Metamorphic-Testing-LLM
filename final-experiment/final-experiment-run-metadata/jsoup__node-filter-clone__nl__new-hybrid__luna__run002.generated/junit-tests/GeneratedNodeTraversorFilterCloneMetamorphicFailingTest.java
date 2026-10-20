import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
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
    public void REMOVE_FIRST_CHILD_WITH_FOLLOWING_SIBLING_variation1() {
        Element root = element("root");
        root.appendChild(marked("first", "REMOVE", null));
        root.appendElement("second");
        root.appendElement("third");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void REMOVE_LAST_CHILD_variation1() {
        Element root = element("root");
        root.appendElement("first");
        root.appendChild(marked("last", "REMOVE", null));
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void REMOVE_ONLY_CHILD_variation1() {
        Element root = element("root");
        root.appendChild(marked("only", "REMOVE", null));
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void REMOVE_NESTED_SUBTREE_WITH_SIBLING_variation1() {
        Element root = element("root");
        Element parent = root.appendElement("parent");
        parent.appendChild(marked("nested", "REMOVE", null)).appendElement("hidden");
        parent.appendElement("following");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void MIXED_PRUNE_SKIP_AND_REMOVE_variation1() {
        Element root = element("root");
        root.appendChild(marked("skipped", "SKIP_ENTIRELY", null))
                .appendElement("hidden");
        root.appendChild(marked("pruned", "SKIP_CHILDREN", "SKIP_CHILDREN"))
                .appendElement("hidden");
        root.appendChild(marked("removed", "REMOVE", null));
        root.appendElement("survivor");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }

    @Test
    public void REMOVE_MULTIPLE_SIBLINGS_variation1() {
        Element root = element("root");
        root.appendChild(marked("remove1", "REMOVE", null));
        root.appendElement("keep1");
        root.appendChild(marked("remove2", "REMOVE", null));
        root.appendElement("keep2");
        assertMetamorphicRelationFor(new Fixture(root, true));
    }
}
