import org.junit.jupiter.api.Test;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static Element node(String name, Node... children) {
        Element element = new Element(name, "");
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    public static final class ContinueFilter implements NodeFilter {

        public ContinueFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    public static final class PlanFilter implements NodeFilter {

        private final String headName;

        private final FilterResult headResult;

        private final String tailName;

        private final FilterResult tailResult;

        public PlanFilter(String headName, FilterResult headResult, String tailName, FilterResult tailResult) {
            this.headName = headName;
            this.headResult = headResult;
            this.tailName = tailName;
            this.tailResult = tailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return headName != null && headName.equals(node.nodeName()) ? headResult : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return tailName != null && tailName.equals(node.nodeName()) ? tailResult : FilterResult.CONTINUE;
        }
    }

}
