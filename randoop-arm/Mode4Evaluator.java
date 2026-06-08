import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Evaluates a set of source Orders exactly the way a Mode 4 candidate test would:
 * run the SUT on the source, apply the developer's follow-up transform, run the SUT on the
 * follow-up, and invoke the developer's assertion. A thrown AssertionError means the
 * metamorphic relation was violated -- i.e. a bug-revealing input.
 *
 * <p>This computes the same pass/fail outcome that Soham's ActualResultTestSplitter would
 * produce from running the generated JUnit class, but directly and without the Maven/Surefire
 * machinery, so the comparison numbers are reliable and fast.</p>
 */
public final class Mode4Evaluator {

    private Mode4Evaluator() {
    }

    public static final class Result {
        public final int total;
        public final int passed;
        public final int bugRevealing;       // assertRelation threw -> MR violated
        public final int errored;            // unexpected exception
        public final TreeMap<Integer, Integer> itemCountHistogram;
        public final int distinctFailurePatterns;

        Result(int total, int passed, int bugRevealing, int errored,
               TreeMap<Integer, Integer> hist, int distinctFailurePatterns) {
            this.total = total;
            this.passed = passed;
            this.bugRevealing = bugRevealing;
            this.errored = errored;
            this.itemCountHistogram = hist;
            this.distinctFailurePatterns = distinctFailurePatterns;
        }
    }

    public static Result evaluate(List<Order> sources) {
        int passed = 0;
        int bugRevealing = 0;
        int errored = 0;
        TreeMap<Integer, Integer> hist = new TreeMap<>();
        Set<String> failurePatterns = new LinkedHashSet<>();

        for (Order source : sources) {
            hist.merge(source.getItems().size(), 1, Integer::sum);
            try {
                double sourceOutput = OrderUtil.calculateTotal(source);
                Order followUp = OrderMetamorphicSpec.generateFollowUp(source);
                double followUpOutput = OrderUtil.calculateTotal(followUp);
                OrderMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
                passed++;
            } catch (AssertionError violated) {
                bugRevealing++;
                // A "pattern" = the structural shape that triggered the violation, so we can
                // tell "found the bug 50 different ways" from "found it 50 identical ways".
                failurePatterns.add(RandoopOrderHarvester.signature(source));
            } catch (Throwable unexpected) {
                errored++;
            }
        }
        return new Result(sources.size(), passed, bugRevealing, errored, hist, failurePatterns.size());
    }

    public static void printReport(String armName, Result r) {
        System.out.println("==== " + armName + " ====");
        System.out.println("distinct source inputs:        " + r.total);
        System.out.println("passed (relation held):        " + r.passed);
        System.out.println("BUG-REVEALING (MR violated):   " + r.bugRevealing);
        System.out.println("distinct bug-revealing shapes: " + r.distinctFailurePatterns);
        System.out.println("unexpected errors:             " + r.errored);
        System.out.println("item-count histogram:          " + r.itemCountHistogram);
        if (r.total > 0) {
            double rate = (100.0 * r.bugRevealing) / r.total;
            System.out.printf("bug-revealing rate:            %.1f%%%n", rate);
        }
        System.out.println();
    }
}
