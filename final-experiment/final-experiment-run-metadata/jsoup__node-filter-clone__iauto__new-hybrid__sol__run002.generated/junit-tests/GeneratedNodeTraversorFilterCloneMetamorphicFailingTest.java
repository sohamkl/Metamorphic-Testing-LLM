import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    public static final class MarkerFilter implements NodeFilter {
        public MarkerFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (node instanceof Element) {
                String result = ((Element) node).attr("data-head-result");
                if (!result.isEmpty()) {
                    return FilterResult.valueOf(result);
                }
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            if (node instanceof Element) {
                String result = ((Element) node).attr("data-tail-result");
                if (!result.isEmpty()) {
                    return FilterResult.valueOf(result);
                }
            }
            return FilterResult.CONTINUE;
        }
    }

    private static Element root(String tag) {
        return new Element(tag, "");
    }

    private static Element child(Element parent, String tag) {
        return parent.appendElement(tag);
    }

    private static Element head(Element element, NodeFilter.FilterResult result) {
        element.attr("data-head-result", result.name());
        return element;
    }

    private static Element tail(Element element, NodeFilter.FilterResult result) {
        element.attr("data-tail-result", result.name());
        return element;
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            Node sourceRoot,
            Node followUpRoot) {
        org.junit.jupiter.api.Assertions.assertEquals(sourceOutput, followUpOutput);
        org.junit.jupiter.api.Assertions.assertEquals(
                sourceRoot.outerHtml(), followUpRoot.outerHtml());
    }

}
