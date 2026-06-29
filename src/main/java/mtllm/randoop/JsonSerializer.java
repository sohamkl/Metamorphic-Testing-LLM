package mtllm.randoop;

import mtllm.util.JsonUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Reflection-based JSON serializer for arbitrary harvested objects.
 *
 * <p>The LLM data-generator path serializes its inputs by writing bespoke code per SUT. The
 * Randoop path harvests live objects of an arbitrary type, so it needs to serialize them
 * generically into the same {@code {"source": ..., "followUp": ...}} JSON shape the rest of the
 * pipeline (split + HTML report) already consumes. This walks the object graph exactly like
 * {@link StructuralSignature} (the de-dup signature) but emits JSON instead of a signature
 * string, so a developer adds no serialization code for a new object SUT.</p>
 *
 * <ul>
 *   <li>numbers (incl. {@code BigDecimal}) / booleans -&gt; bare JSON literals</li>
 *   <li>char / {@link CharSequence} / enum -&gt; quoted strings</li>
 *   <li>arrays, {@link Iterable}s -&gt; JSON arrays (handles {@code double[][]}, {@code List<LineItem>})</li>
 *   <li>{@link Map}s -&gt; JSON objects (keys stringified)</li>
 *   <li>other JDK types (java.*, javax.*, jdk.*, sun.*) -&gt; quoted {@code toString()} (avoids
 *       {@code InaccessibleObjectException} from reflecting into closed modules)</li>
 *   <li>the developer's own objects -&gt; JSON object built from declared non-static fields</li>
 * </ul>
 *
 * <p>NaN/Infinity (not valid JSON) serialize as {@code null}. Cycles are detected on the current
 * path (shared siblings serialize fully; only genuine back-references become {@code "<cycle>"}).</p>
 */
public final class JsonSerializer {

    private JsonSerializer() {
    }

    /** Serialize an arbitrary object graph to a JSON value. */
    public static String toJson(Object o) {
        StringBuilder sb = new StringBuilder();
        append(sb, o, new IdentityHashMap<>());
        return sb.toString();
    }

    private static void append(StringBuilder sb, Object o, IdentityHashMap<Object, Boolean> path) {
        if (o == null) {
            sb.append("null");
            return;
        }

        Class<?> c = o.getClass();

        // Leaves.
        if (o instanceof Number || o instanceof Boolean) {
            sb.append(numberOrBool(o));
            return;
        }
        if (o instanceof Character || o instanceof CharSequence || c.isEnum()) {
            sb.append(JsonUtil.quote(o.toString()));
            return;
        }

        // Cycle guard on the current path (removed again once this node's children are done).
        if (path.put(o, Boolean.TRUE) != null) {
            sb.append("\"<cycle>\"");
            return;
        }
        try {
            if (c.isArray()) {
                sb.append('[');
                int n = Array.getLength(o);
                for (int i = 0; i < n; i++) {
                    if (i > 0) sb.append(',');
                    append(sb, Array.get(o, i), path);
                }
                sb.append(']');
                return;
            }
            if (o instanceof Map<?, ?> map) {
                sb.append('{');
                boolean first = true;
                for (Map.Entry<?, ?> e : map.entrySet()) {
                    if (!first) sb.append(',');
                    first = false;
                    sb.append(JsonUtil.quote(String.valueOf(e.getKey()))).append(':');
                    append(sb, e.getValue(), path);
                }
                sb.append('}');
                return;
            }
            if (o instanceof Iterable<?> it) {
                sb.append('[');
                boolean first = true;
                for (Object e : it) {
                    if (!first) sb.append(',');
                    first = false;
                    append(sb, e, path);
                }
                sb.append(']');
                return;
            }

            // Other JDK types: trust toString rather than reflecting into closed modules.
            String pkg = c.getName();
            if (pkg.startsWith("java.") || pkg.startsWith("javax.")
                    || pkg.startsWith("jdk.") || pkg.startsWith("sun.")) {
                sb.append(JsonUtil.quote(o.toString()));
                return;
            }

            // The developer's own object: emit declared instance fields as a JSON object.
            sb.append('{');
            boolean first = true;
            for (Field f : c.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers()) || f.isSynthetic()) {
                    continue;
                }
                if (!first) sb.append(',');
                first = false;
                sb.append(JsonUtil.quote(f.getName())).append(':');
                try {
                    f.setAccessible(true);
                    append(sb, f.get(o), path);
                } catch (ReflectiveOperationException | RuntimeException ex) {
                    sb.append("null");
                }
            }
            sb.append('}');
        } finally {
            path.remove(o);
        }
    }

    private static String numberOrBool(Object o) {
        if (o instanceof Double d && (d.isNaN() || d.isInfinite())) {
            return "null";
        }
        if (o instanceof Float f && (f.isNaN() || f.isInfinite())) {
            return "null";
        }
        return o.toString();
    }
}
