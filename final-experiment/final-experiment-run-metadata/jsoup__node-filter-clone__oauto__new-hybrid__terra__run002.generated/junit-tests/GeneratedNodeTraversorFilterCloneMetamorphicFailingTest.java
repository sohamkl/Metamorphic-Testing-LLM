import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static void run(NodeFilter filter, Node root) {
        Object[] followUp = generateFollowUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(NodeFilter filter, Node root) {
        return NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
    }

    private static Element element(String name, Node... children) {
        Element element = new Element(name);
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    public static final class ScenarioFilter implements NodeFilter {
        private final String rule;

        public ScenarioFilter(String rule) {
            this.rule = rule;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if ("H_STOP_ROOT".equals(rule) && depth == 0) return FilterResult.STOP;
            if ("H_STOP_FIRST".equals(rule) && "first".equals(node.nodeName())) return FilterResult.STOP;
            if ("H_SKIP_ROOT".equals(rule) && depth == 0) return FilterResult.SKIP_CHILDREN;
            if ("H_SKIP_FIRST".equals(rule) && "first".equals(node.nodeName())) return FilterResult.SKIP_CHILDREN;
            if ("H_ENTIRE_ROOT".equals(rule) && depth == 0) return FilterResult.SKIP_ENTIRELY;
            if ("H_ENTIRE_FIRST".equals(rule) && "first".equals(node.nodeName())) return FilterResult.SKIP_ENTIRELY;
            if ("H_ENTIRE_ONLY".equals(rule) && "only".equals(node.nodeName())) return FilterResult.SKIP_ENTIRELY;
            if ("H_REMOVE_ROOT".equals(rule) && depth == 0) return FilterResult.REMOVE;
            if ("H_REMOVE_FIRST".equals(rule) && "first".equals(node.nodeName())) return FilterResult.REMOVE;
            if ("H_REMOVE_CHILD".equals(rule) && "child".equals(node.nodeName())) return FilterResult.REMOVE;
            if ("H_REMOVE_GRAND".equals(rule) && "grand".equals(node.nodeName())) return FilterResult.REMOVE;
            if ("H_NULL_ROOT".equals(rule) && depth == 0) return null;
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            if ("T_STOP_ROOT".equals(rule) && depth == 0) return FilterResult.STOP;
            if ("T_STOP_FIRST".equals(rule) && "first".equals(node.nodeName())) return FilterResult.STOP;
            if ("T_REMOVE_FIRST".equals(rule) && "first".equals(node.nodeName())) return FilterResult.REMOVE;
            if ("T_REMOVE_CHILD".equals(rule) && "child".equals(node.nodeName())) return FilterResult.REMOVE;
            if ("T_REMOVE_ROOT".equals(rule) && depth == 0) return FilterResult.REMOVE;
            if ("T_SKIP_ROOT".equals(rule) && depth == 0) return FilterResult.SKIP_CHILDREN;
            if ("T_ENTIRE_ROOT".equals(rule) && depth == 0) return FilterResult.SKIP_ENTIRELY;
            if ("T_NULL_ROOT".equals(rule) && depth == 0) return null;
            return FilterResult.CONTINUE;
        }
    }

}
