import randoop.DummyVisitor;
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
import java.util.regex.Pattern;

/**
 * Randoop-backed source-input generator for the Order SUT.
 *
 * <p>Drives Randoop's generator API in-process and harvests the live Order objects it
 * constructs (rather than using Randoop's emitted JUnit regression tests). Returns
 * STRUCTURALLY DISTINCT orders so the diversity numbers are honest -- Randoop produces many
 * identical empty orders, which would otherwise inflate the count.</p>
 *
 * <p>SUT-specific to Order/LineItem for this comparison arm; generalising to arbitrary SUTs
 * is future work (the harvest mechanism itself is generic; only the structural-signature and
 * Order cast are SUT-specific).</p>
 */
public final class RandoopOrderHarvester {

    private RandoopOrderHarvester() {
    }

    /** Harvest with no extra seeds (raw Randoop). */
    public static List<Order> harvest(int timeLimitMillis) throws Exception {
        return harvest(timeLimitMillis, null, 0L);
    }

    public static List<Order> harvest(int timeLimitMillis, List<Object> extraSeeds) throws Exception {
        return harvest(timeLimitMillis, extraSeeds, 0L);
    }

    /** Run Randoop under several random seeds and union the structurally-distinct results. */
    public static List<Order> harvestMultiSeed(int timeLimitMillis, List<Object> extraSeeds, long[] randomSeeds)
            throws Exception {
        Map<String, Order> union = new LinkedHashMap<>();
        for (long s : randomSeeds) {
            for (Order o : harvest(timeLimitMillis, extraSeeds, s)) {
                union.putIfAbsent(signature(o), o);
            }
        }
        return new ArrayList<>(union.values());
    }

    /**
     * Harvest structurally-distinct Order objects within the given time budget.
     *
     * @param extraSeeds meaningful literal values (Strings, ints, doubles) to add to Randoop's
     *                   value pool -- in the hybrid this is what the LLM supplies. Randoop then
     *                   builds objects from these instead of only its tiny default pool.
     */
    public static List<Order> harvest(int timeLimitMillis, List<Object> extraSeeds, long randomSeed)
            throws Exception {
        randoop.util.Randomness.setSeed(randomSeed);
        Set<String> classnames = new LinkedHashSet<>();
        classnames.add("Order");
        classnames.add("LineItem");
        classnames.add("OrderUtil");

        OperationModel model = OperationModel.createModel(
                AccessibilityPredicate.IS_PUBLIC,
                new DefaultReflectionPredicate(),
                new ArrayList<Pattern>(),
                classnames,
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

        // Collect live Order instances, then de-duplicate by structure so identical
        // (e.g. empty) orders collapse to one distinct input.
        IdentityHashMap<Object, Boolean> seenIdentity = new IdentityHashMap<>();
        Map<String, Order> distinct = new LinkedHashMap<>();
        List<ExecutableSequence> regression = gen.getRegressionSequences();
        for (ExecutableSequence es : regression) {
            for (ReferenceValue rv : es.getAllValues()) {
                Object o = rv.getObjectValue();
                if (o instanceof Order && seenIdentity.put(o, true) == null) {
                    Order order = (Order) o;
                    distinct.putIfAbsent(signature(order), order);
                }
            }
        }
        System.err.println("[harvest] regression seqs=" + regression.size()
                + "  identity-distinct Orders=" + seenIdentity.size()
                + "  structurally-distinct=" + distinct.size());
        return new ArrayList<>(distinct.values());
    }

    /** Structural signature: itemCount + each item's name|qty|price, order-sensitive. */
    static String signature(Order order) {
        StringBuilder sb = new StringBuilder();
        List<LineItem> items = order.getItems();
        sb.append(items.size()).append(':');
        for (LineItem item : items) {
            sb.append('[').append(item.getName()).append('|')
              .append(item.getQuantity()).append('|')
              .append(item.getUnitPrice()).append(']');
        }
        return sb.toString();
    }
}
