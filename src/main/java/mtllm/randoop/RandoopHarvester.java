package mtllm.randoop;

import randoop.DummyVisitor;
import randoop.ExecutionOutcome;
import randoop.NormalExecution;
import randoop.generation.ComponentManager;
import randoop.generation.ForwardGenerator;
import randoop.generation.SeedSequences;
import randoop.main.GenInputsAbstract;
import randoop.main.ThrowClassNameError;
import randoop.operation.TypedOperation;
import randoop.reflection.AccessibilityPredicate;
import randoop.reflection.DefaultReflectionPredicate;
import randoop.reflection.OperationModel;
import randoop.reflection.ReflectionPredicate;
import randoop.sequence.ExecutableSequence;
import randoop.sequence.Variable;
import randoop.test.DummyCheckGenerator;
import randoop.types.ClassOrInterfaceType;

import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Generic Randoop-backed source-input generator for ANY object SUT.
 *
 * <p>Drives Randoop's generator API in-process and harvests the live objects it constructs of a
 * caller-specified target type (rather than using Randoop's emitted JUnit regression tests).
 * Returns STRUCTURALLY DISTINCT instances so diversity numbers are honest -- Randoop produces
 * many identical objects, which would otherwise inflate the count.</p>
 *
 * <p>To target a new SUT you supply:</p>
 * <ol>
 *   <li>{@code targetClass} -- the type you want to harvest (e.g. {@code Order.class});</li>
 *   <li>{@code classNames} -- the classes Randoop may call constructors/methods on to build it
 *       (e.g. {@code Order}, {@code LineItem}, {@code OrderUtil});</li>
 *   <li>{@link StructuralSignature} auto-derives a de-duplication key by reflection.</li>
 * </ol>
 *
 * <p>This replaces the old SUT-specific {@code RandoopOrderHarvester}: the harvest mechanism was
 * always generic; only the {@code Order} cast and the hand-written signature were specific, and
 * both are now handled generically with {@code targetClass.isInstance} and
 * {@code targetClass.cast}.</p>
 *
 * @param <T> the object type to harvest
 */
public final class RandoopHarvester<T> {
    private static final int MAX_REPLAY_STATEMENTS = 160;

    /**
     * A harvested object together with the Randoop sequence that constructed it.
     *
     * <p>The JSON data path only needs {@link #value()}. The JUnit-suite path (approach C) renders
     * compilable construction code from {@code sequence.sequence.toCodeString()} and uses
     * {@code variable.getName()} to know which local variable in that code holds {@code value}, so
     * it can append the SUT call + developer follow-up + assertion onto the right variable.</p>
     *
     * @param value    the harvested target-type object
     * @param sequence the executable sequence Randoop used to build it
     * @param variable the variable within {@code sequence} that holds {@code value} (may be null if
     *                 Randoop cannot map the runtime value back to a variable)
     */
    public record Harvested<T>(T value, ExecutableSequence sequence, Variable variable) {
    }

    private final Class<T> targetClass;
    private final Set<String> classNames;
    /** Harvest {@code targetClass} instances; de-dup with the reflection auto-signature. */
    public RandoopHarvester(Class<T> targetClass, Set<String> classNames) {
        this.targetClass = box(targetClass);
        this.classNames = new LinkedHashSet<>(classNames);
    }

    /**
     * Randoop hands back runtime values as objects, so a primitive target type can never match:
     * {@link Class#isInstance} is specified to return false for every primitive Class. A SUT whose
     * input is a bare {@code double} would therefore harvest nothing at all while still reporting
     * success. Boxing first makes the filter mean what it reads as. Reference types pass through
     * untouched, so this only affects the path that was previously guaranteed to return zero.
     */
    @SuppressWarnings("unchecked")
    private static <T> Class<T> box(Class<T> type) {
        if (type == null || !type.isPrimitive()) {
            return type;
        }
        Class<?> boxed = type == boolean.class ? Boolean.class
                : type == byte.class ? Byte.class
                : type == char.class ? Character.class
                : type == short.class ? Short.class
                : type == int.class ? Integer.class
                : type == long.class ? Long.class
                : type == float.class ? Float.class
                : type == double.class ? Double.class
                : type;
        return (Class<T>) boxed;
    }

    /**
     * Returns each distinct object paired with the Randoop {@link ExecutableSequence} that built it,
     * for seed traceability and generated JUnit construction code.
     */
    public List<Harvested<T>> harvestSequences(int timeLimitMillis, List<Object> extraSeeds, long randomSeed)
            throws Exception {
        return new ArrayList<>(harvestDistinct(timeLimitMillis, extraSeeds, randomSeed).values());
    }

    /** Multi-seed union variant of {@link #harvestSequences}: keeps one sequence per distinct shape. */
    public List<Harvested<T>> harvestSequencesMultiSeed(int timeLimitMillis, List<Object> extraSeeds,
                                                        long[] randomSeeds) throws Exception {
        Map<String, Harvested<T>> union = new LinkedHashMap<>();
        for (long s : randomSeeds) {
            for (Map.Entry<String, Harvested<T>> e : harvestDistinct(timeLimitMillis, extraSeeds, s).entrySet()) {
                union.putIfAbsent(e.getKey(), e.getValue());
            }
        }
        return new ArrayList<>(union.values());
    }

    /**
     * Core harvest: run Randoop once and collect structurally-distinct target-type objects, keyed by
     * structural signature (insertion-ordered), each paired with the sequence that produced it.
     */
    private Map<String, Harvested<T>> harvestDistinct(int timeLimitMillis, List<Object> extraSeeds,
                                                      long randomSeed) throws Exception {
        randoop.util.Randomness.setSeed(randomSeed);

        OperationModel model = OperationModel.createModel(
                AccessibilityPredicate.IS_PUBLIC,
                new ConstructionReflectionPredicate(classNames),
                new ArrayList<Pattern>(),
                new LinkedHashSet<>(classNames),
                new LinkedHashSet<String>(),
                new ThrowClassNameError(),
                new ArrayList<String>());

        List<TypedOperation> ops = model.getOperations();
        Set<ClassOrInterfaceType> classTypes = model.getClassTypes();

        Set<randoop.sequence.Sequence> seeds = new LinkedHashSet<>(SeedSequences.defaultSeeds());
        if (extraSeeds != null && !extraSeeds.isEmpty()) {
            seeds.addAll(SeedSequences.objectsToSeeds(extraSeeds));
        }
        ComponentManager comp = new ComponentManager(seeds);
        GenInputsAbstract.Limits limits =
                new GenInputsAbstract.Limits(timeLimitMillis, 10_000, 5_000, 5_000);

        ForwardGenerator gen = new ForwardGenerator(
                ops,
                new LinkedHashSet<TypedOperation>(),
                limits,
                comp,
                classTypes);
        gen.setExecutionVisitor(new DummyVisitor());
        gen.setTestCheckGenerator(new DummyCheckGenerator());
        gen.setTestPredicate(es -> true);
        gen.createAndClassifySequences();

        // Collect live target-type instances, then de-duplicate by structure so identical
        // (e.g. empty) objects collapse to one distinct input. For the first object of each
        // structural shape we also keep the sequence + variable that built it (the representative).
        IdentityHashMap<Object, Boolean> seenIdentity = new IdentityHashMap<>();
        Map<String, Harvested<T>> distinct = new LinkedHashMap<>();
        List<ExecutableSequence> regression = gen.getRegressionSequences();
        for (ExecutableSequence es : regression) {
            for (int statementIndex = 0; statementIndex < es.size(); statementIndex++) {
                ExecutionOutcome outcome = es.getResult(statementIndex);
                if (!(outcome instanceof NormalExecution normal)) {
                    continue;
                }
                Object o = normal.getRuntimeValue();
                if (targetClass.isInstance(o) && seenIdentity.put(o, true) == null) {
                    if (statementIndex >= MAX_REPLAY_STATEMENTS) {
                        continue;
                    }
                    T value = targetClass.cast(o);
                    String sig = StructuralSignature.of(value);
                    if (!distinct.containsKey(sig)) {
                        Variable variable = es.sequence.getVariable(statementIndex);
                        distinct.put(sig, new Harvested<>(value, es, variable));
                    }
                }
            }
        }
        System.err.println("[harvest] regression seqs=" + regression.size()
                + "  identity-distinct " + targetClass.getSimpleName() + "s=" + seenIdentity.size()
                + "  structurally-distinct=" + distinct.size());
        return distinct;
    }

    /** Restricts Randoop to replayable object-construction operations from the discovered graph. */
    private static final class ConstructionReflectionPredicate implements ReflectionPredicate {
        private final DefaultReflectionPredicate delegate = new DefaultReflectionPredicate();
        private final Set<String> allowedClasses;

        private ConstructionReflectionPredicate(Set<String> allowedClasses) {
            this.allowedClasses = Set.copyOf(allowedClasses);
        }

        @Override
        public boolean test(Class<?> type) {
            return allowedClasses.contains(type.getName()) && delegate.test(type);
        }

        @Override
        public boolean test(Method method) {
            return allowedClasses.contains(method.getDeclaringClass().getName())
                    && Modifier.isStatic(method.getModifiers())
                    && method.getReturnType() != void.class
                    && delegate.test(method);
        }

        @Override
        public boolean test(Constructor<?> constructor) {
            return allowedClasses.contains(constructor.getDeclaringClass().getName())
                    && delegate.test(constructor);
        }

        @Override
        public boolean test(Field field) {
            return allowedClasses.contains(field.getDeclaringClass().getName())
                    && Modifier.isStatic(field.getModifiers())
                    && delegate.test(field);
        }
    }
}
