import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static final class ScriptedFilter implements NodeFilter {
        public ScriptedFilter() {
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

    private static final class DepthSelectiveFilter implements NodeFilter {
        public DepthSelectiveFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (depth == 1 && node instanceof Element
                    && ((Element) node).hasClass("skip-at-depth-one")) {
                return FilterResult.SKIP_CHILDREN;
            }
            if (depth >= 2 && node instanceof Element
                    && ((Element) node).hasClass("remove-deep")) {
                return FilterResult.REMOVE;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class NoMatchFilter implements NodeFilter {
        public NoMatchFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (node instanceof Element && ((Element) node).hasClass("absent-policy-match")) {
                return FilterResult.REMOVE;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static Element element(String tagName, Node... children) {
        Element element = new Element(tagName);
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static Element head(Element element, NodeFilter.FilterResult result) {
        element.attr("data-head-result", result.name());
        return element;
    }

    private static Element tail(Element element, NodeFilter.FilterResult result) {
        element.attr("data-tail-result", result.name());
        return element;
    }

    private static void exercise(NodeFilter sourceFilter, Node sourceRoot) {
        Object[] followUp = NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(
                sourceFilter, sourceRoot);
        NodeFilter followUpFilter = (NodeFilter) followUp[0];
        Node followUpRoot = (Node) followUp[1];

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(followUpFilter, followUpRoot);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
