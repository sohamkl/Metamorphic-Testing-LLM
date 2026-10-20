import java.util.Objects;

import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static Element element(String name) {
        return new Element(name, "");
    }

    private static Element chain(int nodeCount) {
        Element root = element("root");
        Element current = root;
        for (int index = 1; index < nodeCount; index++) {
            Element child = element("node" + index);
            current.appendChild(child);
            current = child;
        }
        return root;
    }

    private static Element twoLeaves() {
        Element root = element("root");
        root.appendChild(element("first"));
        root.appendChild(element("second"));
        return root;
    }

    private static Element twoLeavesWithThird() {
        Element root = twoLeaves();
        root.appendChild(element("third"));
        return root;
    }

    private static void execute(NodeFilter filter, Node sourceRoot) {
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, sourceRoot);
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, sourceRoot);
        Node followUpRoot = (Node) followUp[1];
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], followUpRoot);
        assertMetamorphicRelation(sourceOutput, followUpOutput, sourceRoot, followUpRoot);
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
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
                    "Filtering the source and clone produced different DOMs:\nsource="
                            + sourceHtml + "\nfollow-up=" + followUpHtml);
        }
    }

    private static final class RuleFilter implements NodeFilter {
        private final int rule;

        private RuleFilter(int rule) {
            this.rule = rule;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (rule) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth == 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 3:
                case 17:
                    return "first".equals(node.nodeName())
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 4:
                    return "first".equals(node.nodeName())
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 5:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 6:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 7:
                    return depth == 3 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 15:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 16:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (rule) {
                case 8:
                    return "first".equals(node.nodeName())
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case 9:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 10:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 11:
                    return "only".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 12:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 13:
                    return "first".equals(node.nodeName())
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 14:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 17:
                    return "first".equals(node.nodeName())
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }
}
