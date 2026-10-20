import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicPassingTest {

    private static final class ContinuePolicy implements NodeFilter {
        public ContinuePolicy() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class ScriptPolicy implements NodeFilter {
        public ScriptPolicy() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            switch (key(node)) {
                case "HC":
                case "HC_TS":
                case "HC_TR":
                    return FilterResult.SKIP_CHILDREN;
                case "HE":
                    return FilterResult.SKIP_ENTIRELY;
                case "RM":
                    return FilterResult.REMOVE;
                case "ST":
                    return FilterResult.STOP;
                default:
                    return FilterResult.CONTINUE;
            }
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            switch (key(node)) {
                case "TS":
                case "HC_TS":
                    return FilterResult.STOP;
                case "TR":
                case "HC_TR":
                    return FilterResult.REMOVE;
                case "TC":
                    return FilterResult.SKIP_CHILDREN;
                case "TE":
                    return FilterResult.SKIP_ENTIRELY;
                default:
                    return FilterResult.CONTINUE;
            }
        }
    }

    private static final class TargetPolicy implements NodeFilter {
        private final String headId;
        private final FilterResult headResult;
        private final String tailId;
        private final FilterResult tailResult;

        private TargetPolicy(
                String headId,
                FilterResult headResult,
                String tailId,
                FilterResult tailResult) {
            this.headId = headId;
            this.headResult = headResult;
            this.tailId = tailId;
            this.tailResult = tailResult;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return headId != null && headId.equals(key(node))
                    ? headResult
                    : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return tailId != null && tailId.equals(key(node))
                    ? tailResult
                    : FilterResult.CONTINUE;
        }
    }

    private static final class OrdinalStopPolicy implements NodeFilter {
        private int headCount;

        public OrdinalStopPolicy() {
        }

        @Override
        public FilterResult head(Node node, int depth) {
            headCount++;
            return headCount == 3 ? FilterResult.STOP : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class DepthPolicy implements NodeFilter {
        private final int targetDepth;
        private final FilterResult result;

        private DepthPolicy(int targetDepth, FilterResult result) {
            this.targetDepth = targetDepth;
            this.result = result;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            return depth == targetDepth ? result : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static final class SiblingOrdinalPolicy implements NodeFilter {
        private final int targetOrdinal;
        private final FilterResult result;
        private int headOrdinal;

        private SiblingOrdinalPolicy(int targetOrdinal, FilterResult result) {
            this.targetOrdinal = targetOrdinal;
            this.result = result;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            headOrdinal++;
            return headOrdinal == targetOrdinal ? result : FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    private static Element e(String id, Node... children) {
        Element element = new Element("n").attr("id", id);
        for (Node child : children) {
            element.appendChild(child);
        }
        return element;
    }

    private static String key(Node node) {
        return node instanceof Element ? ((Element) node).id() : "";
    }

    private static Object[] generateFollowUp(NodeFilter sourceFilter, Node sourceRoot) {
        if (sourceFilter == null || sourceRoot == null) {
            throw new NullPointerException("Filter and root must be non-null");
        }
        if (sourceRoot.parentNode() != null) {
            throw new IllegalArgumentException("The source DOM root must be detached");
        }

        Node clonedRoot = sourceRoot.clone();
        if (clonedRoot.parentNode() != null) {
            throw new IllegalStateException("The cloned DOM root must be detached");
        }

        NodeFilter clonedFilter;
        if (sourceFilter instanceof ContinuePolicy) {
            clonedFilter = new ContinuePolicy();
        } else if (sourceFilter instanceof ScriptPolicy) {
            clonedFilter = new ScriptPolicy();
        } else if (sourceFilter instanceof TargetPolicy) {
            TargetPolicy policy = (TargetPolicy) sourceFilter;
            clonedFilter = new TargetPolicy(
                    policy.headId, policy.headResult, policy.tailId, policy.tailResult);
        } else if (sourceFilter instanceof OrdinalStopPolicy) {
            clonedFilter = new OrdinalStopPolicy();
        } else if (sourceFilter instanceof DepthPolicy) {
            DepthPolicy policy = (DepthPolicy) sourceFilter;
            clonedFilter = new DepthPolicy(policy.targetDepth, policy.result);
        } else if (sourceFilter instanceof SiblingOrdinalPolicy) {
            SiblingOrdinalPolicy policy = (SiblingOrdinalPolicy) sourceFilter;
            clonedFilter = new SiblingOrdinalPolicy(policy.targetOrdinal, policy.result);
        } else {
            throw new IllegalArgumentException(
                    "Unsupported test filter type: " + sourceFilter.getClass().getName());
        }

        registerExecutionRoots(sourceRoot, clonedRoot);
        return new Object[]{clonedFilter, clonedRoot};
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void registerExecutionRoots(Node sourceRoot, Node clonedRoot) {
        try {
            Class<?> spec = NodeTraversorFilterCloneMetamorphicSpec.class;
            java.lang.reflect.Field rootsField = spec.getDeclaredField("EXECUTION_ROOTS");
            rootsField.setAccessible(true);
            ThreadLocal roots = (ThreadLocal) rootsField.get(null);

            Class<?> executionRootsType = null;
            for (Class<?> nested : spec.getDeclaredClasses()) {
                if ("ExecutionRoots".equals(nested.getSimpleName())) {
                    executionRootsType = nested;
                    break;
                }
            }
            if (executionRootsType == null) {
                throw new IllegalStateException("ExecutionRoots type was not found");
            }

            java.lang.reflect.Constructor<?> constructor =
                    executionRootsType.getDeclaredConstructor(Node.class, Node.class);
            constructor.setAccessible(true);
            roots.set(constructor.newInstance(sourceRoot, clonedRoot));
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException("Could not register metamorphic execution roots", failure);
        }
    }

    @Test
    void DETACHED_LEAF_CONTINUE_variation1() {
        NodeFilter filter = new ContinuePolicy();
        Node root = e("root");
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DETACHED_LEAF_SKIP_CHILDREN_variation1() {
        NodeFilter filter = new TargetPolicy("leaf", FilterResult.SKIP_CHILDREN, null, null);
        Node root = e("leaf");
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DETACHED_LEAF_SKIP_ENTIRELY_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("HE");
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DETACHED_LEAF_REMOVE_SENTINEL_variation1() {
        NodeFilter filter = new TargetPolicy("leaf", FilterResult.REMOVE, null, null);
        Node root = e("leaf");
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DETACHED_LEAF_STOP_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("ST");
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_CHILD_COMPLETE_TRAVERSAL_variation1() {
        NodeFilter filter = new ContinuePolicy();
        Node root = e("root", e("child"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_LEVEL_CHAIN_ASCENT_variation1() {
        NodeFilter filter = new ContinuePolicy();
        Node root = e("d0", e("d1", e("d2", e("d3", e("d4")))));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_SIBLING_CHILDREN_ORDER_variation1() {
        NodeFilter filter = new ContinuePolicy();
        Node root = e("root", e("a"), e("b"), e("c"), e("d"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NESTED_BRANCH_DEPTH_FIRST_ORDER_variation1() {
        NodeFilter filter = new ContinuePolicy();
        Node root = e("root", e("first", e("nested", e("leaf"))), e("second"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_SKIP_CHILDREN_WITH_DESCENDANTS_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("HC", e("a", e("deep")), e("b"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_SKIP_ENTIRELY_WITH_DESCENDANTS_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("HE", e("a"), e("b", e("deep")));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_REMOVE_WITH_DESCENDANTS_variation1() {
        NodeFilter filter = new TargetPolicy("root", FilterResult.REMOVE, null, null);
        Node root = e("root", e("a", e("deep")), e("b"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_STOP_WITH_DESCENDANTS_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("ST", e("a"), e("b", e("deep")));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERNAL_SKIP_CHILDREN_THEN_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("HC", e("hidden", e("deep"))), e("next", e("visited")));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERNAL_SKIP_ENTIRELY_THEN_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("HE", e("hidden")), e("next", e("visited")));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAF_SKIP_CHILDREN_THEN_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("HC"), e("next"), e("last"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAF_SKIP_ENTIRELY_THEN_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("HE"), e("next"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_REMOVE_NODE_WITH_NEXT_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("RM", e("unvisited")), e("next"), e("last"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_REMOVE_LAST_CHILD_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("kept"), e("RM"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_REMOVE_INTERNAL_SUBTREE_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("RM", e("x", e("y"))), e("retained"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_REMOVE_LAST_INTERNAL_SUBTREE_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("kept"), e("RM", e("x"), e("y", e("z"))));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_STOP_FIRST_CHILD_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("ST"), e("later"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_STOP_MIDDLE_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("first"), e("ST"), e("third"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HEAD_STOP_DEEP_DESCENDANT_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("d0", e("d1", e("d2", e("ST", e("later")))), e("other"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_STOP_LEAF_BEFORE_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("TS"), e("unvisited"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_STOP_LAST_CHILD_DURING_ASCENT_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("parent", e("TS")));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_STOP_ROOT_AFTER_COMPLETE_TRAVERSAL_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("TS", e("a", e("deep")), e("b"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_REMOVE_LEAF_WITH_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("TR"), e("next"), e("last"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_REMOVE_LAST_CHILD_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("kept"), e("TR"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_REMOVE_INTERNAL_NODE_AFTER_DESCENDANTS_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("TR", e("child", e("deep"))), e("next"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_REMOVE_ROOT_SENTINEL_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("TR", e("a"), e("b", e("deep")));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_SKIP_CHILDREN_WITH_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("TC", e("visited")), e("next"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_SKIP_ENTIRELY_WITH_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("TE", e("visited")), e("next"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_TAIL_SKIP_CHILDREN_TERMINAL_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("TC", e("a", e("deep")), e("b"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ROOT_TAIL_SKIP_ENTIRELY_TERMINAL_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("TE", e("a"), e("b", e("deep")));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SKIP_CHILDREN_THEN_TAIL_STOP_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("HC_TS", e("hidden")), e("unvisited"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SKIP_CHILDREN_THEN_TAIL_REMOVE_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("HC_TR", e("untouched", e("deep"))), e("next"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REMOVE_FIRST_OF_THREE_SIBLINGS_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("RM"), e("second"), e("third"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REMOVE_MIDDLE_OF_THREE_SIBLINGS_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("first"), e("RM"), e("third"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REMOVE_ALL_ROOT_CHILDREN_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("RM"), e("RM"), e("RM"), e("RM"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REMOVE_DEEP_LEAF_PRESERVE_ANCESTORS_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("d0", e("d1", e("d2", e("RM"))), e("other"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REMOVE_THEN_LATER_STOP_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("RM", e("subtree")), e("ST"), e("unvisited"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SKIP_SUBTREE_THEN_REMOVE_LATER_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("HE", e("keptChild")), e("RM", e("removedChild")));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REMOVE_CHILDREN_THEN_PARENT_TAIL_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("root", e("parent", e("RM"), e("RM"), e("RM")), e("later"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_LEVEL_REMOVAL_ASCENT_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e("d0", e("d1", e("d2", e("RM"))));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NO_ARG_STATEFUL_VISIT_ORDINAL_POLICY_variation1() {
        NodeFilter filter = new OrdinalStopPolicy();
        Node root = e("root", e("first"), e("second"), e("third"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARAMETERIZED_DEPTH_POLICY_COPY_variation1() {
        NodeFilter filter = new DepthPolicy(2, FilterResult.SKIP_CHILDREN);
        Node root = e("d0", e("d1", e("d2", e("d3"))), e("branch"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARAMETERIZED_SIBLING_ORDINAL_POLICY_COPY_variation1() {
        NodeFilter filter = new SiblingOrdinalPolicy(3, FilterResult.SKIP_ENTIRELY);
        Node root = e("root", e("first"), e("second", e("hidden")), e("third"), e("fourth"));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_SKIP_CHILDREN_AND_FULL_BRANCH_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e(
                "root",
                e("HC", e("hidden", e("deep"))),
                e("full", e("child", e("leaf"))));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TAIL_REMOVE_NODE_FOLLOWED_BY_DEEP_SIBLING_variation1() {
        NodeFilter filter = new ScriptPolicy();
        Node root = e(
                "root",
                e("TR", e("completedChild")),
                e("deepSibling", e("level2", e("level3"))));
        Object[] followUp = generateFollowUp(filter, root);
        FilterResult sourceOutput = NodeTraversor.filter(filter, root);
        FilterResult followUpOutput = NodeTraversor.filter(
                (NodeFilter) followUp[0], (Node) followUp[1]);
        NodeTraversorFilterCloneMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
