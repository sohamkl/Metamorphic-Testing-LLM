import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final class Policy implements NodeFilter {
        private final String selectedId;
        private final FilterResult selectedHeadResult;
        private final FilterResult selectedTailResult;

        private Policy(String selectedId, FilterResult selectedHeadResult, FilterResult selectedTailResult) {
            this.selectedId = selectedId;
            this.selectedHeadResult = selectedHeadResult;
            this.selectedTailResult = selectedTailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return selectedId.equals(node.attr("id")) ? selectedHeadResult : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return selectedId.equals(node.attr("id")) ? selectedTailResult : FilterResult.CONTINUE;
        }
    }

}
