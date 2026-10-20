import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {
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

    @Test
    void LEAF_HEAD_STOP_1() {
        check(leaf(1), "leaf-stop");
    }

    @Test
    void LEAF_HEAD_STOP_2() {
        check(leaf(2), "leaf-stop");
    }

    @Test
    void LEAF_CONTINUE_TAIL_CONTINUE_1() {
        check(leaf(3), "normal");
    }

    @Test
    void LEAF_CONTINUE_TAIL_CONTINUE_2() {
        check(leaf(4), "normal");
    }

    @Test
    void LEAF_SKIP_CHILDREN_1() {
        check(leaf(5), "leaf-skip-children");
    }

    @Test
    void LEAF_SKIP_CHILDREN_2() {
        check(leaf(6), "leaf-skip-children");
    }

    @Test
    void LEAF_SKIP_ENTIRELY_1() {
        check(leaf(7), "leaf-skip-entirely");
    }

    @Test
    void LEAF_SKIP_ENTIRELY_2() {
        check(leaf(8), "leaf-skip-entirely");
    }

    @Test
    void LEAF_REMOVE_1() {
        check(leaf(9), "leaf-remove");
    }

    @Test
    void LEAF_REMOVE_2() {
        check(leaf(10), "leaf-remove");
    }

    @Test
    void ROOT_ONE_CHILD_HEAD_STOP_1() {
        check(oneChild(11), "root-stop");
    }

    @Test
    void ROOT_ONE_CHILD_HEAD_STOP_2() {
        check(oneChild(12), "root-stop");
    }

    @Test
    void ROOT_ONE_CHILD_CONTINUE_CHILD_STOP_1() {
        check(oneChild(13), "child-stop");
    }

    @Test
    void ROOT_ONE_CHILD_CONTINUE_CHILD_STOP_2() {
        check(oneChild(14), "child-stop");
    }

    @Test
    void ONE_CHILD_NORMAL_COMPLETION_1() {
        check(oneChild(15), "normal");
    }

    @Test
    void CHILD_SKIP_CHILDREN_THEN_ROOT_TAIL_1() {
        check(oneGrandchild(16), "child-skip-children");
    }

    @Test
    void CHILD_SKIP_CHILDREN_THEN_ROOT_TAIL_2() {
        check(oneGrandchild(17), "child-skip-children");
    }

    @Test
    void ROOT_SKIP_CHILDREN_WITH_DESCENDANTS_1() {
        check(oneGrandchild(18), "root-skip-children");
    }

    @Test
    void ROOT_SKIP_CHILDREN_WITH_DESCENDANTS_2() {
        check(twoNonLeafChildren(19), "root-skip-children");
    }

    @Test
    void ROOT_SKIP_ENTIRELY_WITH_DESCENDANTS_1() {
        check(oneGrandchild(20), "root-skip-entirely");
    }

    @Test
    void ROOT_SKIP_ENTIRELY_WITH_DESCENDANTS_2() {
        check(twoNonLeafChildren(21), "root-skip-entirely");
    }

    @Test
    void ROOT_REMOVE_WITH_DESCENDANTS_1() {
        check(oneGrandchild(22), "root-remove");
    }

    @Test
    void ROOT_REMOVE_WITH_DESCENDANTS_2() {
        check(twoNonLeafChildren(23), "root-remove");
    }

    @Test
    void CHILD_SKIP_ENTIRELY_1() {
        check(oneGrandchild(24), "child-skip-entirely");
    }

    @Test
    void CHILD_SKIP_ENTIRELY_2() {
        check(oneGrandchild(25), "child-skip-entirely");
    }

    @Test
    void CHILD_REMOVE_SUBTREE_1() {
        check(oneGrandchild(26), "child-remove");
    }

    @Test
    void CHILD_REMOVE_SUBTREE_2() {
        check(oneGrandchild(27), "child-remove");
    }

    @Test
    void TWO_SIBLINGS_FIRST_REMOVE_1() {
        check(twoChildren(28), "first-remove");
    }

    @Test
    void TWO_SIBLINGS_FIRST_REMOVE_2() {
        check(twoNonLeafChildren(29), "first-remove");
    }

    @Test
    void TWO_SIBLINGS_FIRST_REMOVE_3() {
        check(twoChildren(30), "first-remove");
    }

    @Test
    void TWO_SIBLINGS_TAIL_REMOVE_1() {
        check(twoChildren(31), "first-tail-remove");
    }

    @Test
    void TWO_SIBLINGS_TAIL_REMOVE_2() {
        check(twoChildren(32), "first-tail-remove");
    }

    @Test
    void TWO_SIBLINGS_TAIL_REMOVE_3() {
        check(twoNonLeafChildren(33), "first-tail-remove");
    }

    @Test
    void TWO_SIBLINGS_FIRST_SKIP_ENTIRELY_1() {
        check(twoChildren(34), "first-skip-entirely");
    }

    @Test
    void TWO_SIBLINGS_FIRST_SKIP_ENTIRELY_2() {
        check(twoNonLeafChildren(35), "first-skip-entirely");
    }

    @Test
    void NESTED_GRANDCHILD_STOP_1() {
        check(oneGrandchild(36), "grand-stop");
    }

    @Test
    void NESTED_GRANDCHILD_STOP_2() {
        check(oneGrandchild(37), "grand-stop");
    }

    @Test
    void NESTED_GRANDCHILD_REMOVE_1() {
        check(oneGrandchild(38), "grand-remove");
    }

    @Test
    void NESTED_GRANDCHILD_REMOVE_2() {
        check(oneGrandchild(39), "grand-remove");
    }

    @Test
    void NESTED_GRANDCHILD_REMOVE_3() {
        check(oneGrandchild(40), "grand-remove");
    }

    @Test
    void NESTED_CHILD_SKIP_CHILDREN_1() {
        check(oneGrandchild(41), "child-skip-children");
    }

    @Test
    void NESTED_CHILD_SKIP_CHILDREN_2() {
        check(oneGrandchild(42), "child-skip-children");
    }

    @Test
    void ROOT_TAIL_STOP_AFTER_COMPLETE_DESCENT_1() {
        check(oneChild(43), "root-tail-stop");
    }

    @Test
    void ROOT_TAIL_STOP_AFTER_COMPLETE_DESCENT_2() {
        check(oneChild(44), "root-tail-stop");
    }

    @Test
    void SIBLING_TAIL_STOP_1() {
        check(twoChildren(45), "second-tail-stop");
    }

    @Test
    void SIBLING_TAIL_STOP_2() {
        check(twoNonLeafChildren(46), "second-tail-stop");
    }

    @Test
    void DEEP_CHAIN_LAST_NODE_REMOVE_1() {
        check(chainFour(47), "deep-remove");
    }

    @Test
    void DEEP_CHAIN_LAST_NODE_REMOVE_2() {
        check(chainFour(48), "deep-remove");
    }

    @Test
    void MIXED_SIBLING_RESULTS_1() {
        check(twoChildren(49), "mixed");
    }

    @Test
    void MIXED_SIBLING_RESULTS_2() {
        check(twoNonLeafChildren(50), "mixed");
    }
}
