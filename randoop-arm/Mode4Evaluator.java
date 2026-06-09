import mtllm.spec.MetamorphicSpec;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * Evaluates a set of source inputs exactly the way a Mode 4 candidate test would:
 * run the SUT on the source, apply the developer's follow-up transform, run the SUT on the
 * follow-up, and invoke the developer's assertion. A thrown AssertionError means the
 * metamorphic relation was violated -- i.e. a bug-revealing input.
 *
 * <p>This computes the same pass/fail outcome that Soham's ActualResultTestSplitter would
 * produce from running the generated JUnit class, but directly and without the Maven/Surefire
 * machinery, so the comparison numbers are reliable and fast.</p>
 *
 * <p>Generic over the SUT input type {@code I} and output type {@code O}. To evaluate a new SUT
 * the caller supplies the SUT call as a {@code Function<I,O>}, the developer's
 * {@link MetamorphicSpec}, a signature function (for counting distinct failure shapes), and an
 * optional "size" function used only for the diversity histogram.</p>
 *
 * @param <I> SUT input type
 * @param <O> SUT output type
 */
public final class Mode4Evaluator<I, O> {

    private final Function<? super I, ? extends O> sut;
    private final MetamorphicSpec<I, O> spec;
    private final Function<? super I, String> signature;
    private final ToIntFunction<? super I> sizeOf; // nullable -> no histogram

    /**
     * @param sut       the system under test, as input -&gt; output
     * @param spec      the developer-owned metamorphic relation
     * @param signature distinct-shape key for failing inputs (use the harvester's signature)
     * @param sizeOf    optional input "size" for the diversity histogram; null to skip it
     */
    public Mode4Evaluator(Function<? super I, ? extends O> sut,
                          MetamorphicSpec<I, O> spec,
                          Function<? super I, String> signature,
                          ToIntFunction<? super I> sizeOf) {
        this.sut = sut;
        this.spec = spec;
        this.signature = signature;
        this.sizeOf = sizeOf;
    }

    public static final class Result {
        public final int total;
        public final int passed;
        public final int bugRevealing;       // assertRelation threw -> MR violated
        public final int errored;            // unexpected exception
        public final TreeMap<Integer, Integer> sizeHistogram; // empty if no size fn
        public final int distinctFailurePatterns;

        Result(int total, int passed, int bugRevealing, int errored,
               TreeMap<Integer, Integer> hist, int distinctFailurePatterns) {
            this.total = total;
            this.passed = passed;
            this.bugRevealing = bugRevealing;
            this.errored = errored;
            this.sizeHistogram = hist;
            this.distinctFailurePatterns = distinctFailurePatterns;
        }
    }

    public Result evaluate(List<I> sources) {
        int passed = 0;
        int bugRevealing = 0;
        int errored = 0;
        TreeMap<Integer, Integer> hist = new TreeMap<>();
        Set<String> failurePatterns = new LinkedHashSet<>();

        for (I source : sources) {
            if (sizeOf != null) {
                hist.merge(sizeOf.applyAsInt(source), 1, Integer::sum);
            }
            try {
                O sourceOutput = sut.apply(source);
                I followUp = spec.generateFollowUp(source);
                O followUpOutput = sut.apply(followUp);
                spec.assertRelation(sourceOutput, followUpOutput);
                passed++;
            } catch (AssertionError violated) {
                bugRevealing++;
                // A "pattern" = the structural shape that triggered the violation, so we can
                // tell "found the bug 50 different ways" from "found it 50 identical ways".
                failurePatterns.add(signature.apply(source));
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
        if (!r.sizeHistogram.isEmpty()) {
            System.out.println("size histogram:                " + r.sizeHistogram);
        }
        if (r.total > 0) {
            double rate = (100.0 * r.bugRevealing) / r.total;
            System.out.printf("bug-revealing rate:            %.1f%%%n", rate);
        }
        System.out.println();
    }
}
