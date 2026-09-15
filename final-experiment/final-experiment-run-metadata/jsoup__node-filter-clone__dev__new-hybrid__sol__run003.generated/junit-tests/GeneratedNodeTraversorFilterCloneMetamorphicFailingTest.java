import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final String NONE = "__none__";

    private static Element element(String name, Element... children) {
        Element element = new Element(name, "");
        for (Element child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static NodeFilter rule(
            String firstHeadNode,
            NodeFilter.FilterResult firstHeadResult,
            String secondHeadNode,
            NodeFilter.FilterResult secondHeadResult,
            String tailNode,
            NodeFilter.FilterResult tailResult) {
        return new RuleFilter(
                firstHeadNode,
                firstHeadResult,
                secondHeadNode,
                secondHeadResult,
                tailNode,
                tailResult);
    }

    private static final class ContinueFilter implements NodeFilter {
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

    private static final class RuleFilter implements NodeFilter {
        private final String firstHeadNode;
        private final FilterResult firstHeadResult;
        private final String secondHeadNode;
        private final FilterResult secondHeadResult;
        private final String tailNode;
        private final FilterResult tailResult;

        public RuleFilter(
                String firstHeadNode,
                FilterResult firstHeadResult,
                String secondHeadNode,
                FilterResult secondHeadResult,
                String tailNode,
                FilterResult tailResult) {
            this.firstHeadNode = firstHeadNode;
            this.firstHeadResult = firstHeadResult;
            this.secondHeadNode = secondHeadNode;
            this.secondHeadResult = secondHeadResult;
            this.tailNode = tailNode;
            this.tailResult = tailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (node.nodeName().equals(firstHeadNode)) {
                return firstHeadResult;
            }
            if (node.nodeName().equals(secondHeadNode)) {
                return secondHeadResult;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return node.nodeName().equals(tailNode)
                    ? tailResult
                    : FilterResult.CONTINUE;
        }
    }

}
