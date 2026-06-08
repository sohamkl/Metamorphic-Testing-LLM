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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * Spike (option B): drive Randoop's generator in-process and harvest the live
 * Order objects it constructs, instead of using its emitted JUnit regression tests.
 */
public class RandoopHarvest {
    public static void main(String[] args) throws Exception {
        Set<String> classnames = new LinkedHashSet<>();
        classnames.add("Order");
        classnames.add("LineItem");
        classnames.add("OrderUtil");

        OperationModel model = OperationModel.createModel(
                AccessibilityPredicate.IS_PUBLIC,
                new DefaultReflectionPredicate(),
                new ArrayList<Pattern>(),          // omit-methods
                classnames,
                new LinkedHashSet<String>(),       // covered-class names
                new ThrowClassNameError(),
                new ArrayList<String>());          // explicit method signatures

        List<TypedOperation> ops = model.getOperations();
        Set<ClassOrInterfaceType> classTypes = model.getClassTypes();
        System.out.println("operations discovered: " + ops.size());

        ComponentManager comp = new ComponentManager(SeedSequences.defaultSeeds());
        GenInputsAbstract.Limits limits =
                new GenInputsAbstract.Limits(15000, 1_000_000, 5000, 5000); // 15s, attempts, generated, output

        ForwardGenerator gen = new ForwardGenerator(
                ops,
                new LinkedHashSet<TypedOperation>(),  // observer methods
                limits,
                comp,
                classTypes);

        gen.setExecutionVisitor(new DummyVisitor());
        gen.setTestCheckGenerator(new DummyCheckGenerator());
        gen.setTestPredicate(es -> true);

        gen.createAndClassifySequences();
        List<ExecutableSequence> seqs = gen.getRegressionSequences();
        System.out.println("executed sequences kept: " + seqs.size());

        // Harvest distinct live Order instances.
        Map<Object, Boolean> seen = new IdentityHashMap<>();
        List<Object> orders = new ArrayList<>();
        for (ExecutableSequence es : seqs) {
            for (ReferenceValue rv : es.getAllValues()) {
                Object o = rv.getObjectValue();
                if (o != null && o.getClass().getSimpleName().equals("Order") && seen.put(o, true) == null) {
                    orders.add(o);
                }
            }
        }
        System.out.println("distinct Order objects harvested: " + orders.size());

        // Diversity: how many line items per harvested order?
        Map<Integer, Integer> sizeHist = new TreeMap<>();
        Class<?> orderCls = Class.forName("Order");
        Class<?> orderUtil = Class.forName("OrderUtil");
        for (Object o : orders) {
            List<?> items = (List<?>) orderCls.getMethod("getItems").invoke(o);
            sizeHist.merge(items.size(), 1, Integer::sum);
        }
        System.out.println("Order size histogram (lineItemCount -> #orders): " + sizeHist);

        System.out.println("--- sample harvested orders (itemCount, calculateTotal) ---");
        int shown = 0;
        for (Object o : orders) {
            if (shown++ >= 10) break;
            List<?> items = (List<?>) orderCls.getMethod("getItems").invoke(o);
            int n = items.size();
            double total = (double) orderUtil.getMethod("calculateTotal", orderCls).invoke(null, o);
            System.out.println("  items=" + n + "  total=" + total);
        }
    }
}
