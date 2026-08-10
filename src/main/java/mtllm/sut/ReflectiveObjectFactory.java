package mtllm.sut;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/** Best-effort construction for SUT receivers using public API paths. */
public final class ReflectiveObjectFactory {
    private static final int MAX_DEPTH = 4;

    private ReflectiveObjectFactory() {
    }

    public static Object create(Class<?> type) throws ReflectiveOperationException {
        return create(type, 0, new HashSet<>());
    }

    private static Object create(Class<?> type, int depth, Set<Class<?>> active) throws ReflectiveOperationException {
        if (depth > MAX_DEPTH || !active.add(type)) {
            throw new ReflectiveOperationException("No acyclic public construction path for " + type.getName());
        }
        try {
            Object scalar = scalarValue(type);
            if (scalar != Unresolved.VALUE) {
                return scalar;
            }

            Method[] factories = Arrays.stream(type.getMethods())
                    .filter(method -> Modifier.isStatic(method.getModifiers()))
                    .filter(method -> type.isAssignableFrom(method.getReturnType()))
                    .filter(ReflectiveObjectFactory::isFactoryCandidate)
                    .sorted(Comparator.comparingInt(ReflectiveObjectFactory::factoryScore)
                            .thenComparing(Method::getName))
                    .toArray(Method[]::new);
            for (Method factory : factories) {
                try {
                    return factory.invoke(null, arguments(factory.getParameterTypes(), depth, active));
                } catch (ReflectiveOperationException | IllegalArgumentException ignored) {
                    // Try the next public construction path.
                }
            }

            Constructor<?>[] constructors = Arrays.stream(type.getConstructors())
                    .sorted(Comparator.comparingInt(Constructor::getParameterCount))
                    .toArray(Constructor<?>[]::new);
            for (Constructor<?> constructor : constructors) {
                try {
                    return constructor.newInstance(arguments(constructor.getParameterTypes(), depth, active));
                } catch (ReflectiveOperationException | IllegalArgumentException ignored) {
                    // Try the next public construction path.
                }
            }
            throw new ReflectiveOperationException("No usable public constructor or static factory for " + type.getName());
        } finally {
            active.remove(type);
        }
    }

    private static Object[] arguments(Class<?>[] parameterTypes, int depth, Set<Class<?>> active)
            throws ReflectiveOperationException {
        Object[] values = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            values[i] = create(parameterTypes[i], depth + 1, active);
        }
        return values;
    }

    private static int factoryScore(Method method) {
        String name = method.getName().toLowerCase();
        int nameScore = switch (name) {
            case "of", "create", "newinstance", "getinstance", "defaultinstance", "basic" -> 0;
            case "parse", "from", "valueof", "builder" -> 1;
            default -> 2;
        };
        return nameScore * 10 + method.getParameterCount();
    }

    private static boolean isFactoryCandidate(Method method) {
        String name = method.getName().toLowerCase();
        return name.equals("of")
                || name.startsWith("of")
                || name.startsWith("from")
                || name.startsWith("parse")
                || name.startsWith("create")
                || name.startsWith("newinstance")
                || name.startsWith("getinstance")
                || name.startsWith("default")
                || name.startsWith("basic")
                || name.startsWith("relaxed")
                || name.startsWith("simple")
                || name.startsWith("empty")
                || name.startsWith("zero")
                || name.equals("valueof")
                || name.equals("builder");
    }

    private static Object scalarValue(Class<?> type) {
        if (type == String.class || type == CharSequence.class) return "";
        if (type == boolean.class || type == Boolean.class) return false;
        if (type == byte.class || type == Byte.class) return (byte) 0;
        if (type == short.class || type == Short.class) return (short) 0;
        if (type == int.class || type == Integer.class) return 0;
        if (type == long.class || type == Long.class) return 0L;
        if (type == float.class || type == Float.class) return 0.0f;
        if (type == double.class || type == Double.class) return 0.0d;
        if (type == char.class || type == Character.class) return '\0';
        if (type.isEnum() && type.getEnumConstants().length > 0) return type.getEnumConstants()[0];
        return Unresolved.VALUE;
    }

    private enum Unresolved { VALUE }
}
