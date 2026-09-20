import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static Object[] generateFollowUp(NodeFilter filter, Node root) {
        return NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
    }

    private static Element element(String name) {
        return new Element(name);
    }

    private static Element branchingRoot() {
        Element root = element("root");
        Element a = element("a");
        a.appendChild(element("aChild"));
        root.appendChild(a);
        root.appendChild(element("b"));
        return root;
    }

    private static Element threeChildRoot() {
        Element root = element("root");
        Element a = element("a");
        a.appendChild(element("aChild"));
        root.appendChild(a);
        root.appendChild(element("b"));
        root.appendChild(element("c"));
        return root;
    }

    private static Element deepChain() {
        Element root = element("root");
        Element one = element("one");
        Element two = element("two");
        Element three = element("three");
        root.appendChild(one);
        one.appendChild(two);
        two.appendChild(three);
        return root;
    }

    private static Element oneLeafChildRoot() {
        Element root = element("root");
        root.appendChild(element("a"));
        return root;
    }

    private static Element twoLeafChildRoot() {
        Element root = element("root");
        root.appendChild(element("a"));
        root.appendChild(element("b"));
        return root;
    }

    private static Document documentRoot() {
        Document document = new Document("");
        document.appendChild(branchingRoot());
        return document;
    }

    private static final class ContinueFilter implements NodeFilter {
        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class CodeFilter implements NodeFilter {
        private final int code;

        private CodeFilter(int code) {
            this.code = code;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (code) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth >= 2 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 3:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 4:
                    return "a".equals(node.nodeName()) ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 5:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 6:
                    return "a".equals(node.nodeName()) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 7:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 8:
                    return "a".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 17:
                    if ("a".equals(node.nodeName())) {
                        return FilterResult.SKIP_CHILDREN;
                    }
                    if ("b".equals(node.nodeName())) {
                        return FilterResult.REMOVE;
                    }
                    return FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (code) {
                case 9:
                    return "a".equals(node.nodeName()) ? FilterResult.STOP : FilterResult.CONTINUE;
                case 10:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 11:
                    return "a".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 12:
                    return depth == 1 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 13:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 14:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 15:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 16:
                    return "a".equals(node.nodeName()) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

}
