import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    public static final class FirstChildSkipEntirelyFilter implements NodeFilter {
        public FirstChildSkipEntirelyFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return "first".equals(node.nodeName()) ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    public static final class FirstChildRemoveFilter implements NodeFilter {
        public FirstChildRemoveFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return "first".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    public static final class TailRemoveFirstChildFilter implements NodeFilter {
        public TailRemoveFirstChildFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return "first".equals(node.nodeName()) ? FilterResult.REMOVE : FilterResult.CONTINUE;
        }
    }

    public static final class TailRemoveDepthOneFilter implements NodeFilter {
        public TailRemoveDepthOneFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return depth == 1 ? FilterResult.REMOVE : FilterResult.CONTINUE;
        }
    }

    public static final class FirstChildSkipChildrenFilter implements NodeFilter {
        public FirstChildSkipChildrenFilter() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return "first".equals(node.nodeName()) ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }
}
