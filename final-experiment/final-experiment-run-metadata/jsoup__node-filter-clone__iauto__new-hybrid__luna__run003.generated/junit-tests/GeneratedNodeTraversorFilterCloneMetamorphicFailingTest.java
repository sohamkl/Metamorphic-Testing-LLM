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
            String name = node.nodeName();
            if (mode == 1 && depth == 0) return FilterResult.STOP;
            if (mode == 2 && depth == 0) return FilterResult.SKIP_CHILDREN;
            if (mode == 3 && depth == 0) return FilterResult.SKIP_ENTIRELY;
            if (mode == 4 && depth == 0) return FilterResult.REMOVE;
            if (mode == 5 && depth > 0) return FilterResult.SKIP_CHILDREN;
            if (mode == 6 && depth > 0) return FilterResult.SKIP_ENTIRELY;
            if (mode == 7 && name.contains("remove")) return FilterResult.REMOVE;
            if (mode == 8 && name.contains("stop")) return FilterResult.STOP;
            if (mode == 9 && name.contains("prune")) return FilterResult.SKIP_CHILDREN;
            if (mode == 10 && name.contains("skip")) return FilterResult.SKIP_ENTIRELY;
            if (mode == 11 && name.contains("remove")) return FilterResult.REMOVE;
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            String name = node.nodeName();
            if (mode == 12 && depth > 0 && name.contains("stop")) return FilterResult.STOP;
            if (mode == 13 && name.contains("remove")) return FilterResult.REMOVE;
            if (mode == 14 && depth == 0) return FilterResult.STOP;
            if (mode == 15 && depth > 0 && name.contains("prune")) {
                return FilterResult.SKIP_CHILDREN;
            }
            if (mode == 16 && depth > 0 && name.contains("remove")) {
                return FilterResult.REMOVE;
            }
            return FilterResult.CONTINUE;
        }
    }

    private static Element sourceTree(int caseNumber) {
        Element root = new Element("root" + caseNumber);
        int shape = caseNumber % 5;

        if (caseNumber == 1 || caseNumber == 6 || caseNumber == 11
                || caseNumber == 16 || caseNumber == 21 || caseNumber == 26
                || caseNumber == 31 || caseNumber == 36 || caseNumber == 41
                || caseNumber == 46) {
            return root;
        }

        if (shape == 0) {
            root.appendElement("child" + caseNumber);
        } else if (shape == 1) {
            Element a = root.appendElement("a" + caseNumber);
            a.appendElement("deep" + caseNumber);
            a.appendElement("leaf" + caseNumber);
        } else if (shape == 2) {
            root.appendElement("first" + caseNumber);
            root.appendElement("middle" + caseNumber);
            root.appendElement("last" + caseNumber);
        } else if (shape == 3) {
            Element a = root.appendElement("branch" + caseNumber);
            Element b = a.appendElement("nested" + caseNumber);
            b.appendElement("deep" + caseNumber);
            root.appendElement("sibling" + caseNumber);
        } else {
            Element prune = root.appendElement("prune" + caseNumber);
            prune.appendElement("pruneChild" + caseNumber);
            Element skip = root.appendElement("skip" + caseNumber);
            skip.appendElement("skipChild" + caseNumber);
            root.appendElement("remove" + caseNumber);
            root.appendElement("stop" + caseNumber);
        }

        return root;
    }

    private static int policyFor(int caseNumber) {
        switch (caseNumber) {
            case 3: return 1;
            case 4: return 2;
            case 5: return 3;
            case 6:
            case 7: return 4;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20: return 0;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25: return 5;
            case 26:
            case 27:
            case 28:
            case 29:
            case 30: return 6;
            case 31:
            case 32:
            case 33:
            case 34:
            case 35: return 7;
            case 36:
            case 37:
            case 38:
            case 39:
            case 40: return 8;
            case 41:
            case 42:
            case 43:
            case 44:
            case 45: return 11;
            case 46:
            case 47:
            case 48: return 0;
            case 49: return 13;
            case 50: return 14;
            default: return 0;
        }
    }

    private static void runCase(int caseNumber) {
        NodeFilter sourceFilter = new Policy(policyFor(caseNumber));
        Node sourceRoot = sourceTree(caseNumber);

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(sourceFilter, sourceRoot);

        Object[] followUp =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(
                        sourceFilter, sourceRoot);
        NodeFilter followUpFilter = (NodeFilter) followUp[0];
        Node followUpRoot = (Node) followUp[1];

        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(followUpFilter, followUpRoot);

        assertMetamorphicRelation(
                sourceOutput, followUpOutput, sourceRoot, followUpRoot);
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            Node sourceRoot,
            Node followUpRoot) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Different terminal results: source=" + sourceOutput
                            + ", follow-up=" + followUpOutput);
        }
        String sourceHtml = sourceRoot.outerHtml();
        String followUpHtml = followUpRoot.outerHtml();
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError(
                    "Different final DOMs: source=" + sourceHtml
                            + ", follow-up=" + followUpHtml);
        }
    }

}
