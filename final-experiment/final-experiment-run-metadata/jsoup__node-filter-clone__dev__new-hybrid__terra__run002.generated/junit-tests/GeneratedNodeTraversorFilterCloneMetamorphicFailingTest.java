import org.junit.jupiter.api.Test;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static Element element(String name) {
        return new Element(name);
    }

    private static final class RouteFilter implements NodeFilter {
        private final int mode;

        private RouteFilter(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (mode) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth == 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 3:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 4:
                    return "first".equals(node.nodeName()) ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 5:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 6:
                    return "first".equals(node.nodeName()) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 7:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 8:
                    return "first".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 9:
                    return "subtree".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 10:
                    return "middle".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 11:
                    return depth == 2 && "leaf".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 12:
                    return "second".equals(node.nodeName()) ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (mode) {
                case 13:
                    return depth == 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 14:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 15:
                    return "first".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 16:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 17:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 18:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 19:
                    return depth == 1 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }
}
