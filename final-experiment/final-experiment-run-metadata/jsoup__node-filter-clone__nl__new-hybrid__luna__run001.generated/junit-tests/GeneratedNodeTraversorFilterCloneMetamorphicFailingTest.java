import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final class Source {
        final Element root;
        final int policy;

        Source(Element root, int policy) {
            this.root = root;
            this.policy = policy;
        }
    }

    private static final class TraceFilter implements NodeFilter {
        private final int policy;
        final List<String> events = new ArrayList<>();

        TraceFilter(int policy) {
            this.policy = policy;
        }

        private FilterResult headResult() {
            switch (policy % 5) {
                case 0:
                    return FilterResult.STOP;
                case 1:
                    return FilterResult.SKIP_CHILDREN;
                case 2:
                    return FilterResult.SKIP_ENTIRELY;
                case 3:
                    return FilterResult.REMOVE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        private FilterResult tailResult() {
            switch (policy % 5) {
                case 1:
                    return FilterResult.SKIP_CHILDREN;
                case 4:
                    return FilterResult.REMOVE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult head(Node node, int depth) {
            FilterResult result = headResult();
            events.add("H:" + depth + ":" + result);
            return result;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            FilterResult result = tailResult();
            events.add("T:" + depth + ":" + result);
            return result;
        }
    }

    private static final class Execution {
        final Node root;
        final NodeFilter.FilterResult result;
        final List<String> events;
        final String html;

        Execution(Node root, NodeFilter.FilterResult result, List<String> events) {
            this.root = root;
            this.result = result;
            this.events = events;
            this.html = root.outerHtml();
        }
    }

    private static Element element(String name) {
        return new Element(name);
    }

    private static Source source(int slot) {
        Element root = element("root" + slot);
        switch (slot % 7) {
            case 0:
                break;
            case 1:
                root.appendElement("child" + slot);
                break;
            case 2:
                root.appendElement("first" + slot);
                root.appendElement("second" + slot);
                break;
            case 3:
                root.appendElement("one" + slot);
                root.appendElement("two" + slot);
                root.appendElement("three" + slot);
                break;
            case 4:
                Element left = root.appendElement("left" + slot);
                left.appendElement("leftA" + slot);
                left.appendElement("leftB" + slot);
                Element right = root.appendElement("right" + slot);
                right.appendElement("rightA" + slot);
                right.appendElement("rightB" + slot);
                break;
            case 5:
                Element a = root.appendElement("a" + slot);
                Element b = a.appendElement("b" + slot);
                Element c = b.appendElement("c" + slot);
                c.appendElement("d" + slot);
                break;
            default:
                Element first = root.appendElement("first" + slot);
                first.appendElement("nested" + slot);
                Element second = root.appendElement("second" + slot);
                second.appendElement("nestedA" + slot);
                second.appendElement("nestedB" + slot);
                root.appendElement("last" + slot);
                break;
        }
        return new Source(root, slot);
    }

    private static Source generateFollowUp(Source source) {
        return new Source((Element) source.root.clone(), source.policy);
    }

    private static Execution execute(Source source) {
        TraceFilter filter = new TraceFilter(source.policy);
        NodeFilter.FilterResult result = NodeTraversor.filter(filter, source.root);
        boolean nonContinue = false;
        for (String event : filter.events) {
            if (!event.endsWith(":" + NodeFilter.FilterResult.CONTINUE)) {
                nonContinue = true;
                break;
            }
        }
        Assertions.assertTrue(nonContinue);
        return new Execution(source.root, result, filter.events);
    }

    private static void assertMetamorphicRelation(Execution original, Execution clone) {
        Assertions.assertEquals(original.result, clone.result);
        Assertions.assertEquals(original.events, clone.events);
        Assertions.assertEquals(original.html, clone.html);
    }

    private static void assertMetamorphicRelationFor(Source source) {
        Assertions.assertNotNull(source.root);
        Assertions.assertNull(source.root.parent());
        Source followUp = generateFollowUp(source);
        Assertions.assertNotNull(followUp.root);
        Assertions.assertNull(followUp.root.parent());
        Execution original = execute(source);
        Execution clone = execute(followUp);
        assertMetamorphicRelation(original, clone);
    }

}
