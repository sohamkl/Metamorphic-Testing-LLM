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
import randoop.sequence.ExecutableSequence;
import randoop.sequence.Variable;
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

    /**
     * Harvest structurally-distinct {@code targetClass} objects within the given time budget.
     *
     * @param extraSeeds meaningful literal values (Strings, ints, doubles) to add to Randoop's
     *                   value pool -- in the hybrid this is what the LLM supplies. Randoop then
     *                   builds objects from these instead of only its tiny default pool.
     */
    public List<T> harvest(int timeLimitMillis, List<Object> extraSeeds, long randomSeed)
            throws Exception {
        return toValues(harvestSequences(timeLimitMillis, extraSeeds, randomSeed));
    }

    /** Run Randoop under several random seeds and union the structurally-distinct results. */
    public List<T> harvestMultiSeed(int timeLimitMillis, List<Object> extraSeeds, long[] randomSeeds)
            throws Exception {
        return toValues(harvestSequencesMultiSeed(timeLimitMillis, extraSeeds, randomSeeds));
    }

    /**
     * Like {@link #harvest(int, List, long)} but returns each distinct object paired with the
     * Randoop {@link ExecutableSequence} that built it, for the JUnit-suite (approach C) path.
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

    private List<T> toValues(List<Harvested<T>> harvested) {
        List<T> values = new ArrayList<>(harvested.size());
        for (Harvested<T> h : harvested) {
            values.add(h.value());
        }
        return values;
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
                    T value = targetClass.cast(o);
                    String sig = signature.apply(value);
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
}
