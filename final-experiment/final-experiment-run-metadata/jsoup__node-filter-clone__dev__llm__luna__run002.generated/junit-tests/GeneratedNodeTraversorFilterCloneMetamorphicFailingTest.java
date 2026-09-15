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
            boolean firstChild = depth == 1 && node.siblingIndex() == 0;
            boolean secondChild = depth == 1 && node.siblingIndex() == 1;

            if (mode == 1 || mode == 2 || mode == 3
                    || mode == 9 || mode == 10 || mode == 17
                    || mode == 18 || mode == 19 || mode == 20) {
                return FilterResult.CONTINUE;
            }
            if (mode == 4 || mode == 5) {
                return depth == 0
                        ? FilterResult.SKIP_CHILDREN
                        : FilterResult.CONTINUE;
            }
            if (mode == 6) {
                return depth == 0
                        ? FilterResult.SKIP_ENTIRELY
                        : FilterResult.CONTINUE;
            }
            if (mode == 7) {
                return depth == 0
                        ? FilterResult.STOP
                        : FilterResult.CONTINUE;
            }
            if (mode == 8) {
                return depth == 0
                        ? FilterResult.REMOVE
                        : FilterResult.CONTINUE;
            }
            if (mode == 11) {
                return depth == 1
                        ? FilterResult.STOP
                        : FilterResult.CONTINUE;
            }
            if (mode == 12 || mode == 21) {
                return firstChild
                        ? FilterResult.SKIP_CHILDREN
                        : FilterResult.CONTINUE;
            }
            if (mode == 13 || mode == 22) {
                return firstChild
                        ? FilterResult.SKIP_ENTIRELY
                        : FilterResult.CONTINUE;
            }
            if (mode == 14 || mode == 16 || mode == 23) {
                if (firstChild) {
                    return FilterResult.REMOVE;
                }
                if (mode == 23 && secondChild) {
                    return FilterResult.STOP;
                }
                return FilterResult.CONTINUE;
            }
            if (mode == 15) {
                return depth == 1
                        ? FilterResult.REMOVE
                        : FilterResult.CONTINUE;
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            boolean firstChild = depth == 1 && node.siblingIndex() == 0;

            if (mode == 5) {
                return depth == 0
                        ? FilterResult.SKIP_CHILDREN
                        : FilterResult.CONTINUE;
            }
            if (mode == 9) {
                return depth == 0
                        ? FilterResult.REMOVE
                        : FilterResult.CONTINUE;
            }
            if (mode == 10) {
                return depth == 0
                        ? FilterResult.STOP
                        : FilterResult.CONTINUE;
            }
            if (mode == 12) {
                return firstChild
                        ? FilterResult.SKIP_CHILDREN
                        : FilterResult.CONTINUE;
            }
            if (mode == 17 || mode == 18) {
                return firstChild
                        ? FilterResult.REMOVE
                        : FilterResult.CONTINUE;
            }
            if (mode == 19) {
                return depth == 1
                        ? FilterResult.SKIP_CHILDREN
                        : FilterResult.CONTINUE;
            }
            if (mode == 20) {
                return depth == 1
                        ? FilterResult.STOP
                        : FilterResult.CONTINUE;
            }
            if (mode == 22) {
                return depth == 0
                        ? FilterResult.SKIP_CHILDREN
                        : FilterResult.CONTINUE;
            }
            return FilterResult.CONTINUE;
        }
    }

    private static Element element(String name) {
        return new Element(name);
    }

    private static Element chain(String... names) {
        Element root = element(names[0]);
        Element current = root;
        for (int i = 1; i < names.length; i++) {
            Element child = element(names[i]);
            current.appendChild(child);
            current = child;
        }
        return root;
    }

    private static Element rootWithLeafChildren(String name, int count) {
        Element root = element(name);
        for (int i = 0; i < count; i++) {
            root.appendChild(element("child" + i));
        }
        return root;
    }

    private static Element rootWithNonLeafAndSibling(String name) {
        Element root = element(name);
        Element first = element("first");
        first.appendChild(element("grandchild"));
        root.appendChild(first);
        root.appendChild(element("second"));
        return root;
    }

    private static void execute(Node root, int mode) {
        NodeFilter sourceFilter = new Policy(mode);
        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, root);

        Object[] followUp =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(
                        sourceFilter, root);

        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(
                        (NodeFilter) followUp[0],
                        (Node) followUp[1]);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
