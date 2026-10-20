import org.junit.jupiter.api.Test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final String NO_MATCH = "__no_matching_node__";

    private static Element detachedRoot(String html) {
        Element root = Jsoup.parseBodyFragment(html).body().child(0);
        root.remove();
        return root;
    }

    private static Policy policy(
            String headId,
            NodeFilter.FilterResult matchingHeadResult,
            NodeFilter.FilterResult defaultHeadResult,
            String tailId,
            NodeFilter.FilterResult matchingTailResult,
            NodeFilter.FilterResult defaultTailResult) {
        return new Policy(
                headId,
                matchingHeadResult,
                defaultHeadResult,
                tailId,
                matchingTailResult,
                defaultTailResult);
    }

    private static Policy allContinue() {
        return policy(
                NO_MATCH,
                NodeFilter.FilterResult.CONTINUE,
                NodeFilter.FilterResult.CONTINUE,
                NO_MATCH,
                NodeFilter.FilterResult.CONTINUE,
                NodeFilter.FilterResult.CONTINUE);
    }

    private static void exercise(NodeFilter sourceFilter, Node sourceRoot) {
        Object[] followUp =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(sourceFilter, sourceRoot);

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, sourceRoot);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static class Policy implements NodeFilter {
        private final String headId;
        private final FilterResult matchingHeadResult;
        private final FilterResult defaultHeadResult;
        private final String tailId;
        private final FilterResult matchingTailResult;
        private final FilterResult defaultTailResult;

        private Policy(
                String headId,
                FilterResult matchingHeadResult,
                FilterResult defaultHeadResult,
                String tailId,
                FilterResult matchingTailResult,
                FilterResult defaultTailResult) {
            this.headId = headId;
            this.matchingHeadResult = matchingHeadResult;
            this.defaultHeadResult = defaultHeadResult;
            this.tailId = tailId;
            this.matchingTailResult = matchingTailResult;
            this.defaultTailResult = defaultTailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return headId.equals(node.attr("id"))
                    ? matchingHeadResult
                    : defaultHeadResult;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return tailId.equals(node.attr("id"))
                    ? matchingTailResult
                    : defaultTailResult;
        }
    }

    private static final class MultiControlPolicy extends Policy {
        private final String skippedId;
        private final FilterResult skippedResult;
        private final String removedId;
        private final FilterResult removedResult;

        private MultiControlPolicy(
                String skippedId,
                FilterResult skippedResult,
                String removedId,
                FilterResult removedResult) {
            super(
                    NO_MATCH,
                    FilterResult.CONTINUE,
                    FilterResult.CONTINUE,
                    NO_MATCH,
                    FilterResult.CONTINUE,
                    FilterResult.CONTINUE);
            this.skippedId = skippedId;
            this.skippedResult = skippedResult;
            this.removedId = removedId;
            this.removedResult = removedResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            String id = node.attr("id");
            if (skippedId.equals(id)) {
                return skippedResult;
            }
            if (removedId.equals(id)) {
                return removedResult;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }
}
