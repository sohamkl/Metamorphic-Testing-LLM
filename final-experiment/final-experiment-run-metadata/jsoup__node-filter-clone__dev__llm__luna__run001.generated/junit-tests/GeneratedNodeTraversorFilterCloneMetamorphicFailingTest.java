import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {
    private static Element element(String role, int id) {
        return new Element("div").attr("r", role).attr("id", Integer.toString(id));
    }

    private static Element leaf(int id) {
        return element("root", id);
    }

    private static Element oneChild(int id) {
        Element root = element("root", id);
        root.appendChild(element("child", id));
        return root;
    }

    private static Element oneGrandchild(int id) {
        Element root = element("root", id);
        Element child = element("child", id);
        child.appendChild(element("grand", id));
        root.appendChild(child);
        return root;
    }

    private static Element twoChildren(int id) {
        Element root = element("root", id);
        root.appendChild(element("first", id));
        root.appendChild(element("second", id));
        return root;
    }

    private static Element twoNonLeafChildren(int id) {
        Element root = element("root", id);
        Element first = element("first", id);
        Element second = element("second", id);
        first.appendChild(element("first-grand", id));
        second.appendChild(element("second-grand", id));
        root.appendChild(first);
        root.appendChild(second);
        return root;
    }

    private static Element chainFour(int id) {
        Element root = element("root", id);
        Element child = element("child", id);
        Element grand = element("grand", id);
        grand.appendChild(element("deep", id));
        child.appendChild(grand);
        root.appendChild(child);
        return root;
    }

    private static void check(Element root, String mode) {
        Policy policy = new Policy(mode);
        Object[] followUp =
                NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(policy, root);

        NodeFilter.FilterResult sourceOutput =
                NodeTraversor.filter(policy, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter(
                        (NodeFilter) followUp[0],
                        (Node) followUp[1]);

        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static final class Policy implements NodeFilter {
        private final String mode;

        private Policy(String mode) {
            this.mode = mode;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            String role = node.attr("r");

            switch (mode) {
                case "leaf-stop":
                    return FilterResult.STOP;
                case "leaf-skip-children":
                    return FilterResult.SKIP_CHILDREN;
                case "leaf-skip-entirely":
                    return FilterResult.SKIP_ENTIRELY;
                case "leaf-remove":
                    return FilterResult.REMOVE;
                case "root-stop":
                    return role.equals("root")
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case "child-stop":
                    return role.equals("child")
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case "child-skip-children":
                    return role.equals("child")
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case "root-skip-children":
                    return role.equals("root")
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case "root-skip-entirely":
                    return role.equals("root")
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case "root-remove":
                    return role.equals("root")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case "child-skip-entirely":
                    return role.equals("child")
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case "child-remove":
                    return role.equals("child")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case "first-remove":
                    return role.equals("first")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case "first-skip-entirely":
                    return role.equals("first")
                            ? FilterResult.SKIP_ENTIRELY : FilterResult.CONTINUE;
                case "grand-stop":
                    return role.equals("grand")
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case "grand-remove":
                    return role.equals("grand")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case "deep-remove":
                    return role.equals("deep")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case "mixed":
                    if (role.equals("first"))
                        return FilterResult.SKIP_CHILDREN;
                    if (role.equals("second"))
                        return FilterResult.REMOVE;
                    return FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            String role = node.attr("r");

            switch (mode) {
                case "leaf-skip-children":
                    return FilterResult.SKIP_CHILDREN;
                case "root-skip-children":
                    return role.equals("root")
                            ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
                case "first-tail-remove":
                    return role.equals("first")
                            ? FilterResult.REMOVE : FilterResult.CONTINUE;
                case "root-tail-stop":
                    return role.equals("root")
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                case "second-tail-stop":
                    return role.equals("second")
                            ? FilterResult.STOP : FilterResult.CONTINUE;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }
}
