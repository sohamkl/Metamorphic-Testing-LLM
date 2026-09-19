import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicTest {
    private static final class Policy implements NodeFilter {
        private final int mode;

        Policy() {
            this(0);
        }

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
            if (mode == 5 && "skipChildren".equals(name)) return FilterResult.SKIP_CHILDREN;
            if (mode == 6 && "skipEntirely".equals(name)) return FilterResult.SKIP_ENTIRELY;
            if (mode == 7 && "remove".equals(name)) return FilterResult.REMOVE;
            if (mode == 8 && "stop".equals(name)) return FilterResult.STOP;
            if (mode == 9 && "removeTail".equals(name)) return FilterResult.CONTINUE;
            if (mode == 10 && "mixedSkip".equals(name)) return FilterResult.SKIP_CHILDREN;
            if (mode == 10 && "mixedEntire".equals(name)) return FilterResult.SKIP_ENTIRELY;
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            String name = node.nodeName();
            if (mode == 9 && "removeTail".equals(name)) return FilterResult.REMOVE;
            if (mode == 11 && "tailStop".equals(name)) return FilterResult.STOP;
            if (mode == 12 && "tailSkipChildren".equals(name))
                return FilterResult.SKIP_CHILDREN;
            if (mode == 13 && "tailSkipEntirely".equals(name))
                return FilterResult.SKIP_ENTIRELY;
            if (mode == 14 && "tailRemove".equals(name))
                return FilterResult.REMOVE;
            if (mode == 15 && "tailRootSkip".equals(name) && depth == 0)
                return FilterResult.SKIP_CHILDREN;
            if (mode == 16 && "tailRootEntire".equals(name) && depth == 0)
                return FilterResult.SKIP_ENTIRELY;
            return FilterResult.CONTINUE;
        }
    }

    private static void run(NodeFilter filter, Node root) {
        Object[] followUp = followUp(filter, root);
        NodeFilter.FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        NodeFilter.FilterResult followUpOutput =
                NodeTraversor.filter((NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] followUp(NodeFilter filter, Node root) {
        Node clonedRoot = root.clone();
        NodeFilter freshFilter = new Policy(((Policy) filter).mode);
        NodeTraversorFilterCloneMetamorphicSpec.generateFollowUp(filter, root);
        return new Object[] {freshFilter, clonedRoot};
    }

    private static Element leaf(String name) {
        return new Element(name);
    }

    private static Element oneChild(String name) {
        Element root = new Element(name);
        root.appendElement("child");
        return root;
    }

    private static Element wide(String name) {
        Element root = new Element(name);
        root.appendElement("a");
        root.appendElement("b");
        root.appendElement("c");
        return root;
    }

    private static Element branch(String name) {
        Element root = new Element(name);
        root.appendElement("a").appendElement("a1");
        root.appendElement("b").appendElement("b1");
        root.appendElement("c").appendElement("c1");
        return root;
    }

    private static Element chain(String name, int length) {
        Element root = new Element(name);
        Element current = root;
        for (int i = 1; i < length; i++)
            current = current.appendElement("level" + i);
        return root;
    }

    private static Element heterogeneous(String name) {
        Element root = new Element(name);
        Element branch = root.appendElement("branch");
        branch.appendChild(new TextNode("text"));
        branch.appendChild(new Comment("comment"));
        branch.appendChild(new DataNode("data"));
        root.appendElement("sibling");
        return root;
    }

    private static Element removable() {
        Element root = new Element("root");
        root.appendElement("remove").appendElement("hidden");
        root.appendElement("keep");
        root.appendElement("remove");
        root.appendElement("last");
        return root;
    }

    private static Element mixed() {
        Element root = new Element("root");
        root.appendElement("skipChildren").appendElement("hidden");
        root.appendElement("skipEntirely").appendElement("unvisited");
        root.appendElement("normal").appendElement("visible");
        return root;
    }

    private static Element tailTree(String marker) {
        Element root = new Element("root");
        root.appendElement("before");
        root.appendElement(marker);
        root.appendElement("after");
        return root;
    }

    private static Element tailBranch(String marker) {
        Element root = new Element("root");
        root.appendElement("branch").appendElement(marker);
        root.appendElement("later");
        return root;
    }

    @Test
    public void DETACHED_SINGLE_LEAF_CONTINUE_1() {
        run(new Policy(0), leaf("root"));
    }

    @Test
    public void DETACHED_SINGLE_LEAF_CONTINUE_2() {
        run(new Policy(2), oneChild("root"));
    }

    @Test
    public void ROOT_HEAD_STOP_1() {
        run(new Policy(1), wide("root"));
    }

    @Test
    public void ROOT_HEAD_STOP_2() {
        run(new Policy(1), chain("root", 4));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_1() {
        run(new Policy(2), branch("root"));
    }

    @Test
    public void ROOT_SKIP_CHILDREN_2() {
        run(new Policy(2), heterogeneous("root"));
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_1() {
        run(new Policy(3), wide("root"));
    }

    @Test
    public void ROOT_SKIP_ENTIRELY_2() {
        run(new Policy(3), branch("root"));
    }

    @Test
    public void ROOT_REMOVE_REQUEST_1() {
        run(new Policy(4), oneChild("root"));
    }

    @Test
    public void ROOT_REMOVE_REQUEST_2() {
        run(new Policy(4), chain("root", 4));
    }

    @Test
    public void FULL_DEPTH_FIRST_CONTINUE_1() {
        run(new Policy(0), branch("branching"));
    }

    @Test
    public void FULL_DEPTH_FIRST_CONTINUE_2() {
        run(new Policy(0), heterogeneous("mixed"));
    }

    @Test
    public void FULL_DEPTH_FIRST_CONTINUE_3() {
        run(new Policy(0), chain("chain", 5));
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_THEN_SIBLING_1() {
        run(new Policy(5), mixed());
    }

    @Test
    public void INTERNAL_SKIP_CHILDREN_THEN_SIBLING_2() {
        run(new Policy(5), wide("root"));
    }

    @Test
    public void INTERNAL_SKIP_ENTIRELY_THEN_SIBLING_1() {
        run(new Policy(6), mixed());
    }

    @Test
    public void INTERNAL_SKIP_ENTIRELY_THEN_SIBLING_2() {
        run(new Policy(6), wide("root"));
    }

    @Test
    public void INTERNAL_REMOVE_SUBTREE_WITH_FOLLOWING_SIBLING_1() {
        run(new Policy(7), removable());
    }

    @Test
    public void INTERNAL_REMOVE_SUBTREE_WITH_FOLLOWING_SIBLING_2() {
        run(new Policy(7), branch("root"));
    }

    @Test
    public void FIRST_CHILD_REMOVE_WITH_SIBLINGS_1() {
        run(new Policy(7), removable());
    }

    @Test
    public void FIRST_CHILD_REMOVE_WITH_SIBLINGS_2() {
        Element root = wide("root");
        root.child(0).tagName("remove");
        run(new Policy(7), root);
    }

    @Test
    public void LAST_CHILD_REMOVE_ASCENDS_1() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("remove");
        run(new Policy(7), root);
    }

    @Test
    public void LAST_CHILD_REMOVE_ASCENDS_2() {
        run(new Policy(7), tailBranch("remove"));
    }

    @Test
    public void DESCENDANT_HEAD_STOP_1() {
        Element root = wide("root");
        root.child(1).tagName("stop");
        run(new Policy(8), root);
    }

    @Test
    public void DESCENDANT_HEAD_STOP_2() {
        Element root = branch("root");
        root.child(0).child(0).tagName("stop");
        run(new Policy(8), root);
    }

    @Test
    public void LEAF_TAIL_STOP_1() {
        run(new Policy(11), tailTree("tailStop"));
    }

    @Test
    public void LEAF_TAIL_STOP_2() {
        Element root = new Element("root");
        root.appendElement("first");
        root.appendElement("tailStop");
        root.appendElement("last");
        run(new Policy(11), root);
    }

    @Test
    public void INTERNAL_TAIL_STOP_1() {
        run(new Policy(11), tailBranch("tailStop"));
    }

    @Test
    public void INTERNAL_TAIL_STOP_2() {
        Element root = branch("root");
        root.child(0).tagName("tailStop");
        run(new Policy(11), root);
    }

    @Test
    public void LEAF_TAIL_REMOVE_WITH_SIBLING_1() {
        run(new Policy(9), tailTree("removeTail"));
    }

    @Test
    public void LEAF_TAIL_REMOVE_WITH_SIBLING_2() {
        Element root = wide("root");
        root.child(1).tagName("removeTail");
        run(new Policy(9), root);
    }

    @Test
    public void LEAF_TAIL_REMOVE_AS_LAST_CHILD_1() {
        Element root = new Element("root");
        root.appendElement("before");
        root.appendElement("removeTail");
        run(new Policy(9), root);
    }

    @Test
    public void LEAF_TAIL_REMOVE_AS_LAST_CHILD_2() {
        run(new Policy(9), tailBranch("removeTail"));
    }

    @Test
    public void ROOT_TAIL_SKIP_CHILDREN_1() {
        Element root = chain("tailRootSkip", 5);
        root.tagName("tailSkipChildren");
        run(new Policy(15), root);
    }

    @Test
    public void ROOT_TAIL_SKIP_CHILDREN_2() {
        Element root = heterogeneous("tailRootSkip");
        root.tagName("tailSkipChildren");
        run(new Policy(15), root);
    }

    @Test
    public void ROOT_TAIL_SKIP_ENTIRELY_1() {
        Element root = branch("tailRootEntire");
        root.tagName("tailRootEntire");
        run(new Policy(16), root);
    }

    @Test
    public void ROOT_TAIL_SKIP_ENTIRELY_2() {
        Element root = oneChild("tailRootEntire");
        run(new Policy(16), root);
    }

    @Test
    public void TAIL_RESULT_CONTROLS_SIBLING_REMOVAL_1() {
        run(new Policy(14), tailTree("tailRemove"));
    }

    @Test
    public void TAIL_RESULT_CONTROLS_SIBLING_REMOVAL_2() {
        Element root = branch("root");
        root.child(1).tagName("tailRemove");
        run(new Policy(14), root);
    }

    @Test
    public void DEEP_CHAIN_ASCENT_1() {
        run(new Policy(0), chain("root", 5));
    }

    @Test
    public void DEEP_CHAIN_ASCENT_2() {
        run(new Policy(0), chain("deep", 6));
    }

    @Test
    public void HETEROGENEOUS_BRANCHING_CONTINUE_1() {
        run(new Policy(0), heterogeneous("root"));
    }

    @Test
    public void HETEROGENEOUS_BRANCHING_CONTINUE_2() {
        Element root = new Element("root");
        root.appendChild(new TextNode("text"));
        root.appendChild(new Comment("comment"));
        root.appendElement("element").appendChild(new DataNode("data"));
        run(new Policy(0), root);
    }

    @Test
    public void MIXED_CONTROL_RESULTS_ACROSS_SIBLINGS_1() {
        run(new Policy(10), mixed());
    }

    @Test
    public void MIXED_CONTROL_RESULTS_ACROSS_SIBLINGS_2() {
        Element root = new Element("root");
        root.appendElement("mixedSkip").appendElement("hidden");
        root.appendElement("mixedEntire").appendElement("hidden");
        root.appendElement("normal").appendElement("visible");
        run(new Policy(10), root);
    }

    @Test
    public void CLONE_EQUIVALENCE_AFTER_SUBTREE_REMOVALS_1() {
        run(new Policy(7), removable());
    }

    @Test
    public void CLONE_EQUIVALENCE_AFTER_SUBTREE_REMOVALS_2() {
        Element root = new Element("root");
        root.appendElement("remove").appendElement("first");
        root.appendElement("keep");
        root.appendElement("remove").appendElement("second");
        root.appendElement("last");
        run(new Policy(7), root);
    }
}
