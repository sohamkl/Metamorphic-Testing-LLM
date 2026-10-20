import java.util.Objects;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static Element element(String tag) {
        return new Element(tag);
    }

    private static void assertMetamorphicRelation(
            FilterResult sourceOutput,
            FilterResult followUpOutput,
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
                    "Filtering the source and clone produced different DOMs: source="
                            + sourceHtml + ", follow-up=" + followUpHtml);
        }
    }

    private static final class ScenarioFilter implements NodeFilter {
        private final int mode;

        private ScenarioFilter(int mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (mode) {
                case 1:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 2:
                    return depth == 1 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 4:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 5:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 6:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 7:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 8:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 9:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 15:
                    if (depth == 1 && node.siblingIndex() == 0) {
                        return FilterResult.SKIP_CHILDREN;
                    }
                    if (depth == 1 && node.siblingIndex() == 1) {
                        return FilterResult.REMOVE;
                    }
                    return FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (mode) {
                case 3:
                    return depth == 0 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 10:
                    return depth == 0 ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 11:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case 12:
                    return depth == 0 ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case 13:
                    return depth == 0 ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case 14:
                    return depth == 1 && node.siblingIndex() == 0
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }
}
