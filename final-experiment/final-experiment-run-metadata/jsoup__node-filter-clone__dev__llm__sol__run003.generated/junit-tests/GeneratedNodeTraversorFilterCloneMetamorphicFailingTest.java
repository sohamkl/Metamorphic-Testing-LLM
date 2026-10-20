import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static final String NONE = "-";

    private static final class Policy implements NodeFilter {
        private final String headRules;
        private final String tailRules;

        private Policy(String headRules, String tailRules) {
            this.headRules = headRules;
            this.tailRules = tailRules;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return lookup(headRules, node.nodeName());
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return lookup(tailRules, node.nodeName());
        }

        private FilterResult lookup(String rules, String nodeName) {
            if (NONE.equals(rules)) {
                return FilterResult.CONTINUE;
            }
            for (String rule : rules.split(";")) {
                int separator = rule.indexOf('=');
                if (separator > 0 && rule.substring(0, separator).equals(nodeName)) {
                    return FilterResult.valueOf(rule.substring(separator + 1));
                }
            }
            return FilterResult.CONTINUE;
        }
    }

    private static Element element(String tag) {
        return new Element(tag);
    }

    private static Element child(Element parent, String tag) {
        Element child = element(tag);
        parent.appendChild(child);
        return child;
    }

    private static Document document() {
        return new Document("");
    }

    private static void exercise(NodeFilter filter, Node root) {
        Object[] followUp =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(
                        (NodeFilter) followUp[0],
                        (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

}
