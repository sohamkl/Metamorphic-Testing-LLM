import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
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

}
