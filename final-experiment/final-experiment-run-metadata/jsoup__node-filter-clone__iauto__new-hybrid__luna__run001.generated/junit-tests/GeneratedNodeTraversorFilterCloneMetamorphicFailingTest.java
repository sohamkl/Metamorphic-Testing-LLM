import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

    private static Element leaf(String name) {
        return new Element(name);
    }

    private static Element tree(String name, Node... children) {
        Element root = new Element(name);
        for (Node child : children) {
            root.appendChild(child);
        }
        return root;
    }

    private static Element chain(String prefix, int length) {
        Element root = new Element(prefix + "0");
        Element current = root;
        for (int i = 1; i < length; i++) {
            Element child = new Element(prefix + i);
            current.appendChild(child);
            current = child;
        }
        return root;
    }

    private static Element branch(String prefix) {
        return tree(prefix + "root",
                tree(prefix + "left", leaf(prefix + "leftLeaf")),
                tree(prefix + "middle", leaf(prefix + "middleLeaf")),
                leaf(prefix + "right"));
    }

    private static void checkClone(NodeFilter filter, Node root) {
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        Object[] followUp =
                jsoupmt.NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        assertMetamorphicRelation(
                sourceOutput,
                followUpOutput,
                root.outerHtml(),
                ((Node) followUp[1]).outerHtml());
    }

    private static void assertMetamorphicRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput,
            String sourceHtml,
            String followUpHtml) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Different terminal results: " + sourceOutput + " and " + followUpOutput);
        }
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError(
                    "Different final DOMs: " + sourceHtml + " and " + followUpHtml);
        }
    }

}
