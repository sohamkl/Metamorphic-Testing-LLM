package mtllm.randoop;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Reflection-based default "structural signature" for de-duplicating harvested objects.
 *
 * <p>A signature is a string such that two objects are treated as the SAME structural shape
 * iff their signatures are equal. The harvester uses it only to collapse identical inputs
 * (e.g. the ~2,400 identical empty Orders Randoop builds) so diversity counts are honest.</p>
 *
 * <p>This is the AUTO DEFAULT: a developer adding a new SUT does not have to write a signature
 * lambda unless the default would be wrong. It walks the object graph down to leaves and
 * concatenates the meaningful values:</p>
 * <ul>
 *   <li>primitives / boxed numbers / booleans / chars / strings / enums -&gt; printed as-is</li>
 *   <li>arrays, {@link Iterable}s and {@link Map}s -&gt; recurse into elements (order-sensitive)</li>
 *   <li>other JDK types (java.*, javax.*, jdk.*, sun.*) -&gt; {@code toString()} (avoids
 *       {@code InaccessibleObjectException} from reflecting into closed JDK modules; values like
 *       {@code Instant}/{@code UUID}/{@code LocalDate} already print meaningfully)</li>
 *   <li>the developer's own objects -&gt; recurse into their declared non-static fields</li>
 * </ul>
 *
 * <p><b>When the default is wrong (supply your own lambda instead):</b> the object carries a
 * field that is unique-per-instance or non-deterministic -- a random {@code UUID id}, a
 * {@code createdAt} timestamp stamped at construction, a cached hashcode. Those make every
 * instance read as distinct, so de-dup collapses nothing. The escape hatch is to pass a
 * {@code Function<T,String>} that names only the fields that define "same shape".</p>
 */
public final class StructuralSignature {

    private StructuralSignature() {
    }

    /** Auto-derive a structural signature for an arbitrary object. */
    public static String of(Object o) {
        StringBuilder sb = new StringBuilder();
        append(sb, o, new IdentityHashMap<>());
        return sb.toString();
    }

    private static void append(StringBuilder sb, Object o, IdentityHashMap<Object, Boolean> seen) {
        if (o == null) {
            sb.append("null");
            return;
        }

        Class<?> c = o.getClass();

        // Leaves: things that print meaningfully on their own.
        if (c.isPrimitive() || o instanceof Number || o instanceof Boolean
                || o instanceof Character || o instanceof CharSequence || c.isEnum()) {
            sb.append(o);
            return;
        }

        // Cycle guard for composite values (lists/maps/objects can form cycles).
        if (seen.put(o, Boolean.TRUE) != null) {
            sb.append("<cycle>");
            return;
        }

        if (c.isArray()) {
            sb.append('[');
            int n = Array.getLength(o);
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(',');
                append(sb, Array.get(o, i), seen);
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
                append(sb, e.getKey(), seen);
                sb.append('=');
                append(sb, e.getValue(), seen);
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
                append(sb, e, seen);
            }
            sb.append(']');
            return;
        }

        // Other JDK types: trust toString rather than reflecting into closed modules.
        String pkg = c.getName();
        if (pkg.startsWith("java.") || pkg.startsWith("javax.")
                || pkg.startsWith("jdk.") || pkg.startsWith("sun.")) {
            sb.append(o);
            return;
        }

        // The developer's own object: recurse into declared instance fields.
        sb.append(c.getSimpleName()).append('{');
        boolean first = true;
        for (Field f : c.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers()) || f.isSynthetic()) {
                continue;
            }
            if (!first) sb.append(';');
            first = false;
            sb.append(f.getName()).append('=');
            try {
                f.setAccessible(true);
                append(sb, f.get(o), seen);
            } catch (ReflectiveOperationException | RuntimeException ex) {
                // Field not accessible (e.g. JDK module restriction) -> fall back to identity-free marker.
                sb.append("<inaccessible>");
            }
        }
        sb.append('}');
    }
}
