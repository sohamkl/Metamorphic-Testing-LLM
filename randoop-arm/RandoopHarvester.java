import randoop.DummyVisitor;
import randoop.generation.ComponentManager;
import randoop.generation.ForwardGenerator;
import randoop.generation.SeedSequences;
import randoop.main.GenInputsAbstract;
import randoop.main.ThrowClassNameError;
import randoop.operation.TypedOperation;
import randoop.reflection.AccessibilityPredicate;
import randoop.reflection.DefaultReflectionPredicate;
import randoop.reflection.OperationModel;
import randoop.sequence.ExecutableSequence;
import randoop.sequence.ReferenceValue;
import randoop.test.DummyCheckGenerator;
import randoop.types.ClassOrInterfaceType;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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
 *   <li>(optional) a {@code signature} function for de-dup. If omitted, {@link StructuralSignature}
 *       auto-derives one by reflection -- so for clean POJOs you write nothing.</li>
 * </ol>
 *
 * <p>This replaces the old SUT-specific {@code RandoopOrderHarvester}: the harvest mechanism was
 * always generic; only the {@code Order} cast and the hand-written signature were specific, and
 * both are now parameters ({@code targetClass.isInstance} / {@code targetClass.cast} and the
 * optional signature function).</p>
 *
 * @param <T> the object type to harvest
 */
public final class RandoopHarvester<T> {

    private final Class<T> targetClass;
    private final Set<String> classNames;
    private final Function<? super T, String> signature;

    /** Harvest {@code targetClass} instances; de-dup with the reflection auto-signature. */
    public RandoopHarvester(Class<T> targetClass, Set<String> classNames) {
        this(targetClass, classNames, StructuralSignature::of);
    }

    /** Harvest {@code targetClass} instances; de-dup with a caller-supplied signature. */
    public RandoopHarvester(Class<T> targetClass, Set<String> classNames,
                            Function<? super T, String> signature) {
        this.targetClass = targetClass;
        this.classNames = new LinkedHashSet<>(classNames);
        this.signature = signature;
    }

    /** The structural signature this harvester uses (shared with the evaluator for failure shapes). */
    public String signatureOf(T value) {
        return signature.apply(value);
    }

    /** Harvest with no extra seeds (raw Randoop). */
    public List<T> harvest(int timeLimitMillis) throws Exception {
        return harvest(timeLimitMillis, null, 0L);
    }

    public List<T> harvest(int timeLimitMillis, List<Object> extraSeeds) throws Exception {
        return harvest(timeLimitMillis, extraSeeds, 0L);
    }

    /** Run Randoop under several random seeds and union the structurally-distinct results. */
    public List<T> harvestMultiSeed(int timeLimitMillis, List<Object> extraSeeds, long[] randomSeeds)
            throws Exception {
        Map<String, T> union = new LinkedHashMap<>();
        for (long s : randomSeeds) {
            for (T o : harvest(timeLimitMillis, extraSeeds, s)) {
                union.putIfAbsent(signature.apply(o), o);
            }
        }
        return new ArrayList<>(union.values());
    }

    /**
     * Harvest structurally-distinct {@code targetClass} objects within the given time budget.
     *
     * @param extraSeeds meaningful literal values (Strings, ints, doubles) to add to Randoop's
     *                   value pool -- in the hybrid this is what the LLM supplies. Randoop then
     *                   builds objects from these instead of only its tiny default pool.
     */
    public List<T> harvest(int timeLimitMillis, List<Object> extraSeeds, long randomSeed)
            throws Exception {
        randoop.util.Randomness.setSeed(randomSeed);

        OperationModel model = OperationModel.createModel(
                AccessibilityPredicate.IS_PUBLIC,
                new DefaultReflectionPredicate(),
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
                new GenInputsAbstract.Limits(timeLimitMillis, 1_000_000, 5_000, 5_000);

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
        // (e.g. empty) objects collapse to one distinct input.
        IdentityHashMap<Object, Boolean> seenIdentity = new IdentityHashMap<>();
        Map<String, T> distinct = new LinkedHashMap<>();
        List<ExecutableSequence> regression = gen.getRegressionSequences();
        for (ExecutableSequence es : regression) {
            for (ReferenceValue rv : es.getAllValues()) {
                Object o = rv.getObjectValue();
                if (targetClass.isInstance(o) && seenIdentity.put(o, true) == null) {
                    T value = targetClass.cast(o);
                    distinct.putIfAbsent(signature.apply(value), value);
                }
            }
        }
        System.err.println("[harvest] regression seqs=" + regression.size()
                + "  identity-distinct " + targetClass.getSimpleName() + "s=" + seenIdentity.size()
                + "  structurally-distinct=" + distinct.size());
        return new ArrayList<>(distinct.values());
    }
}
