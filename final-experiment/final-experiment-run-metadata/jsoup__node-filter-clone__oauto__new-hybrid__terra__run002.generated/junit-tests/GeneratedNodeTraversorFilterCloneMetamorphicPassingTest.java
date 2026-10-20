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

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

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

    @Test
    public void ELEMENT_LEAF_CONTINUE_variation1() {
        run(new ScenarioFilter("CONTINUE"), element("root"));
    }

    @Test
    public void DOCUMENT_ROOT_WITH_SINGLE_ELEMENT_CHILD_variation1() {
        Document root = new Document("");
        root.appendChild(element("child"));
        run(new ScenarioFilter("CONTINUE"), root);
    }

    @Test
    public void TEXT_NODE_ROOT_LEAF_variation1() {
        run(new ScenarioFilter("CONTINUE"), new TextNode("text"));
    }

    @Test
    public void COMMENT_NODE_ROOT_LEAF_variation1() {
        run(new ScenarioFilter("CONTINUE"), new Comment("comment"));
    }

    @Test
    public void DATA_NODE_ROOT_LEAF_variation1() {
        run(new ScenarioFilter("CONTINUE"), new DataNode("raw-data"));
    }

    @Test
    public void DOCUMENT_TYPE_ROOT_LEAF_variation1() {
        run(new ScenarioFilter("CONTINUE"), new DocumentType("html", "", ""));
    }

    @Test
    public void XML_DECLARATION_ROOT_LEAF_variation1() {
        run(new ScenarioFilter("CONTINUE"), new XmlDeclaration("xml", false));
    }

    @Test
    public void DEEP_SINGLE_CHILD_CHAIN_CONTINUE_variation1() {
        run(new ScenarioFilter("CONTINUE"),
                element("root", element("middle", element("leaf"))));
    }

    @Test
    public void LEAF_SIBLINGS_CONTINUE_variation1() {
        run(new ScenarioFilter("CONTINUE"),
                element("root", element("first"), element("second")));
    }

    @Test
    public void MIXED_CHILD_NODE_TYPES_variation1() {
        run(new ScenarioFilter("CONTINUE"),
                element("root", element("element-child"), new TextNode("text-child"), new Comment("comment-child")));
    }

    @Test
    public void HEAD_STOP_AT_ROOT_variation1() {
        run(new ScenarioFilter("H_STOP_ROOT"),
                element("root", element("child"), element("later")));
    }

    @Test
    public void HEAD_STOP_AT_NESTED_CHILD_variation1() {
        run(new ScenarioFilter("H_STOP_FIRST"),
                element("root", element("first"), element("second")));
    }

    @Test
    public void TAIL_STOP_AT_ROOT_variation1() {
        run(new ScenarioFilter("T_STOP_ROOT"), element("root", element("child")));
    }

    @Test
    public void TAIL_STOP_AT_CHILD_WITH_SIBLING_variation1() {
        run(new ScenarioFilter("T_STOP_FIRST"),
                element("root", element("first"), element("second")));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_ROOT_variation1() {
        run(new ScenarioFilter("H_SKIP_ROOT"),
                element("root", element("child", element("grandchild"))));
    }

    @Test
    public void HEAD_SKIP_CHILDREN_ON_INTERNAL_NODE_variation1() {
        run(new ScenarioFilter("H_SKIP_FIRST"),
                element("root", element("first", element("hidden")), element("second")));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_ON_ROOT_variation1() {
        run(new ScenarioFilter("H_ENTIRE_ROOT"),
                element("root", element("child")));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_ON_FIRST_SIBLING_variation1() {
        run(new ScenarioFilter("H_ENTIRE_FIRST"),
                element("root", element("first", element("hidden")), element("second")));
    }

    @Test
    public void HEAD_SKIP_ENTIRELY_ON_FINAL_CHILD_variation1() {
        run(new ScenarioFilter("H_ENTIRE_ONLY"),
                element("root", element("only", element("hidden"))));
    }

    @Test
    public void HEAD_REMOVE_ROOT_variation1() {
        run(new ScenarioFilter("H_REMOVE_ROOT"),
                element("root", element("child")));
    }

    @Test
    public void HEAD_REMOVE_FIRST_CHILD_WITH_SIBLING_variation1() {
        run(new ScenarioFilter("H_REMOVE_FIRST"),
                element("root", element("first"), element("second")));
    }

    @Test
    public void HEAD_REMOVE_FINAL_CHILD_variation1() {
        run(new ScenarioFilter("H_REMOVE_CHILD"),
                element("root", element("child")));
    }

    @Test
    public void HEAD_REMOVE_NESTED_CHILD_variation1() {
        run(new ScenarioFilter("H_REMOVE_GRAND"),
                element("root", element("first", element("grand")), element("second")));
    }

    @Test
    public void TAIL_REMOVE_FIRST_CHILD_WITH_SIBLING_variation1() {
        run(new ScenarioFilter("T_REMOVE_FIRST"),
                element("root", element("first"), element("second")));
    }

    @Test
    public void TAIL_REMOVE_FINAL_CHILD_variation1() {
        run(new ScenarioFilter("T_REMOVE_CHILD"),
                element("root", element("child")));
    }

    @Test
    public void TAIL_REMOVE_ROOT_variation1() {
        run(new ScenarioFilter("T_REMOVE_ROOT"), element("root"));
    }

    @Test
    public void TAIL_SKIP_CHILDREN_AT_ROOT_variation1() {
        run(new ScenarioFilter("T_SKIP_ROOT"), element("root"));
    }

    @Test
    public void TAIL_SKIP_ENTIRELY_AT_ROOT_variation1() {
        run(new ScenarioFilter("T_ENTIRE_ROOT"), element("root"));
    }

    @Test
    public void HEAD_NULL_RESULT_AT_ROOT_variation1() {
        run(new ScenarioFilter("H_NULL_ROOT"),
                element("root", element("child")));
    }

    @Test
    public void TAIL_NULL_RESULT_AT_ROOT_variation1() {
        run(new ScenarioFilter("T_NULL_ROOT"), element("root"));
    }
}
