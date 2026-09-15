import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    public static final class Policy implements NodeFilter {
        private final int caseId;

        public Policy(int caseId) {
            this.caseId = caseId;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (depth == 0) {
                switch (caseId) {
                    case 1:
                    case 6:
                    case 16:
                    case 31:
                    case 36:
                    case 41:
                    case 46:
                        return FilterResult.STOP;
                    case 3:
                    case 8:
                    case 17:
                    case 18:
                    case 38:
                    case 43:
                        return FilterResult.SKIP_CHILDREN;
                    case 4:
                    case 9:
                    case 14:
                    case 19:
                    case 34:
                    case 39:
                    case 44:
                        return FilterResult.SKIP_ENTIRELY;
                    case 10:
                        return FilterResult.REMOVE;
                    default:
                        return FilterResult.CONTINUE;
                }
            }

            int sibling = node.siblingIndex();

            switch (caseId) {
                case 19:
                case 20:
                case 21:
                    return FilterResult.SKIP_ENTIRELY;
                case 23:
                case 24:
                    return FilterResult.STOP;
                case 25:
                case 26:
                    return FilterResult.SKIP_CHILDREN;
                case 29:
                case 30:
                case 31:
                case 32:
                case 47:
                case 48:
                    return FilterResult.REMOVE;
                case 37:
                case 38:
                    if (sibling == 0)
                        return FilterResult.SKIP_CHILDREN;
                    if (sibling == 1)
                        return FilterResult.REMOVE;
                    return FilterResult.CONTINUE;
                case 39:
                    return depth >= 2 ? FilterResult.STOP : FilterResult.CONTINUE;
                case 40:
                    return depth >= 2 ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            if (depth == 0) {
                switch (caseId) {
                    case 41:
                        return FilterResult.SKIP_CHILDREN;
                    case 43:
                        return FilterResult.REMOVE;
                    default:
                        return FilterResult.CONTINUE;
                }
            }

            switch (caseId) {
                case 7:
                case 27:
                case 28:
                    return FilterResult.STOP;
                case 12:
                case 33:
                case 34:
                case 35:
                case 36:
                    return FilterResult.REMOVE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    private static Node root(int shape) {
        Element root = new Element("root");

        switch (shape) {
            case 1:
                root.appendElement("child");
                break;
            case 2:
                root.appendElement("a");
                root.appendElement("b");
                root.appendElement("c");
                break;
            case 3:
                Element a = root.appendElement("a");
                Element b = a.appendElement("b");
                b.appendElement("c");
                break;
            case 4:
                Element first = root.appendElement("a");
                first.appendElement("aa");
                Element second = root.appendElement("b");
                second.appendElement("ba");
                root.appendElement("c");
                break;
            default:
                break;
        }

        return root;
    }

    private static void run(int caseId, int shape) {
        Node source = root(shape);
        NodeFilter sourceFilter = new Policy(caseId);

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, source);

        Object[] followUp =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(
                        sourceFilter, source);

        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(
                        (NodeFilter) followUp[0],
                        (Node) followUp[1]);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
