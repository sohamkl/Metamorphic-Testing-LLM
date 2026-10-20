import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;
import org.jsoup.select.NodeTraversor;

import jsoupmt.NodeTraversorFilterCloneMetamorphicSpec;

public class GeneratedNodeTraversorFilterCloneMetamorphicFailingTest {

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

}
