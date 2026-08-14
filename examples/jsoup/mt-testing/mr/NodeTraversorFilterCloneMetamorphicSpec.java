package jsoupmt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;

/** Developer-owned deep-clone equivalence relation for {@code NodeTraversor.filter}. */
public final class NodeTraversorFilterCloneMetamorphicSpec {
    private static final ThreadLocal<ExecutionRoots> EXECUTION_ROOTS = new ThreadLocal<>();

    private NodeTraversorFilterCloneMetamorphicSpec() {
    }

    /**
     * Returns the transformed arguments expected by the framework's generated
     * multi-argument invocation wrapper: an equivalent fresh filter and a detached deep clone.
     */
    public static Object[] generateFollowUp(NodeFilter filter, Node root) {
        Objects.requireNonNull(filter, "filter");
        Objects.requireNonNull(root, "root");
        if (root.parentNode() != null) {
            throw new IllegalArgumentException("The source DOM root must be detached");
        }

        Node clonedRoot = root.clone();
        if (clonedRoot.parentNode() != null) {
            throw new IllegalStateException("The cloned DOM root must be detached");
        }

        EXECUTION_ROOTS.set(new ExecutionRoots(root, clonedRoot));
        return new Object[]{freshEquivalentFilter(filter), clonedRoot};
    }

    /** Verifies equivalent terminal results and final DOM structures. */
    public static void assertRelation(
            NodeFilter.FilterResult sourceOutput,
            NodeFilter.FilterResult followUpOutput) {
        ExecutionRoots roots = EXECUTION_ROOTS.get();
        EXECUTION_ROOTS.remove();
        if (roots == null) {
            throw new IllegalStateException("generateFollowUp must run before assertRelation");
        }

        if (!Objects.equals(sourceOutput, followUpOutput)) {
            throw new AssertionError("Filtering the source and clone returned different results: source="
                    + sourceOutput + ", follow-up=" + followUpOutput);
        }
        String sourceHtml = roots.source().outerHtml();
        String followUpHtml = roots.followUp().outerHtml();
        if (!sourceHtml.equals(followUpHtml)) {
            throw new AssertionError("Filtering the source and clone produced different DOMs:\nsource="
                    + sourceHtml + "\nfollow-up=" + followUpHtml);
        }
    }

    private static NodeFilter freshEquivalentFilter(NodeFilter source) {
        Class<?> type = source.getClass();
        try {
            Constructor<?> noArgs = type.getDeclaredConstructor();
            noArgs.setAccessible(true);
            return (NodeFilter) noArgs.newInstance();
        } catch (NoSuchMethodException ignored) {
            // Parameterized synthesized policies are copied from their immutable fields below.
        } catch (ReflectiveOperationException failure) {
            throw copyFailure(type, failure);
        }

        Field[] fields = java.util.Arrays.stream(type.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .toArray(Field[]::new);
        for (Constructor<?> constructor : type.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length != fields.length) {
                continue;
            }
            boolean compatible = true;
            Object[] values = new Object[fields.length];
            try {
                for (int index = 0; index < fields.length; index++) {
                    fields[index].setAccessible(true);
                    values[index] = fields[index].get(source);
                    compatible &= boxed(parameterTypes[index]).isInstance(values[index]);
                }
                if (compatible) {
                    constructor.setAccessible(true);
                    return (NodeFilter) constructor.newInstance(values);
                }
            } catch (ReflectiveOperationException failure) {
                throw copyFailure(type, failure);
            }
        }
        throw new IllegalArgumentException("Cannot create a fresh equivalent NodeFilter of type "
                + type.getName());
    }

    private static IllegalArgumentException copyFailure(Class<?> type, Exception cause) {
        return new IllegalArgumentException(
                "Cannot create a fresh equivalent NodeFilter of type " + type.getName(), cause);
    }

    private static Class<?> boxed(Class<?> type) {
        if (!type.isPrimitive()) return type;
        if (type == int.class) return Integer.class;
        if (type == long.class) return Long.class;
        if (type == double.class) return Double.class;
        if (type == float.class) return Float.class;
        if (type == short.class) return Short.class;
        if (type == byte.class) return Byte.class;
        if (type == boolean.class) return Boolean.class;
        if (type == char.class) return Character.class;
        return type;
    }

    private record ExecutionRoots(Node source, Node followUp) {
    }
}
