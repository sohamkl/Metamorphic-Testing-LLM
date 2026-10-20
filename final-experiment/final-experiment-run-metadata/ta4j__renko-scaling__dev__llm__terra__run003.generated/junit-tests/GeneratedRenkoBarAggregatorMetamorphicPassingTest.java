import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNumFactory;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.NumFactory;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Instant START = Instant.parse("2024-01-01T00:00:00Z");
    private static final NumFactory NUM_FACTORY = DecimalNumFactory.getInstance();

    private static void run(RenkoBarAggregator aggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> bars(Duration period, double[] closes, Double[] volumes, Double[] amounts,
            long[] trades, boolean distinctOhlc) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            Instant begin = START.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            double close = closes[i];
            Num open = n(distinctOhlc ? close - 2.0 : close);
            Num high = n(distinctOhlc ? close + 2.0 : close);
            Num low = n(distinctOhlc ? close - 3.0 : close);
            result.add(new BaseBar(period, begin, end, open, high, low, n(close),
                    volumes[i] == null ? null : n(volumes[i]),
                    amounts[i] == null ? null : n(amounts[i]), trades[i]));
        }
        return result;
    }

    private static Num n(double value) {
        return NUM_FACTORY.numOf(value);
    }

    @Test
    public void testEMPTY_LIST_variation1() {
        run(new RenkoBarAggregator(1.0), List.of());
    }

    @Test
    public void testSINGLETON_BAR_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100}, new Double[] {2.0},
                        new Double[] {20.0}, new long[] {1}, true));
    }

    @Test
    public void testFLAT_MULTI_BAR_SEQUENCE_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 100, 100, 100},
                        new Double[] {1.0, 2.0, 3.0, 4.0},
                        new Double[] {10.0, 20.0, 30.0, 40.0},
                        new long[] {1, 2, 3, 4}, false));
    }

    @Test
    public void testINITIAL_UP_MOVE_BELOW_BOX_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 100.5},
                        new Double[] {2.0, null}, new Double[] {20.0, 30.0},
                        new long[] {1, 2}, true));
    }

    @Test
    public void testINITIAL_DOWN_MOVE_BELOW_BOX_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 99.5},
                        new Double[] {2.0, 3.0}, new Double[] {20.0, null},
                        new long[] {1, 2}, false));
    }

    @Test
    public void testINITIAL_UP_EXACT_ONE_BOX_variation1() {
        run(new RenkoBarAggregator(1.0),
                bars(Duration.ofMinutes(1), new double[] {100, 101},
                        new Double[] {2.0, 3.0}, new Double[] {20.0, 30.0},
                        new long[] {3_000_000_000L, 4_000_000_000L}, true));
    }

    @Test
    public void testINITIAL_DOWN_EXACT_ONE_BOX_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 99},
                        new Double[] {2.0, 3.0}, new Double[] {20.0, 30.0},
                        new long[] {1, 2}, false));
    }

    @Test
    public void testINITIAL_MULTI_UP_BRICKS_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 103},
                        new Double[] {4.0, 7.0}, new Double[] {40.0, 70.0},
                        new long[] {2, 4}, true));
    }

    @Test
    public void testINITIAL_MULTI_DOWN_BRICKS_variation1() {
        run(new RenkoBarAggregator(1.0, 3),
                bars(Duration.ofMinutes(1), new double[] {100, 97},
                        new Double[] {4.0, 6.0}, new Double[] {40.0, 60.0},
                        new long[] {2, 3}, false));
    }

    @Test
    public void testUPWARD_CONTINUATION_variation1() {
        run(new RenkoBarAggregator(0.5, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 101, 103},
                        new Double[] {1.0, null, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, true));
    }

    @Test
    public void testDOWNWARD_CONTINUATION_variation1() {
        run(new RenkoBarAggregator(1.0),
                bars(Duration.ofMinutes(1), new double[] {100, 99, 97},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, null, 30.0},
                        new long[] {1, 2, 3}, false));
    }

    @Test
    public void testUP_RETRACE_SHORT_OF_REVERSAL_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 101, 100},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {3_000_000_000L, 4_000_000_000L, 5_000_000_000L}, true));
    }

    @Test
    public void testDOWN_RETRACE_SHORT_OF_REVERSAL_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 99, 100},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, false));
    }

    @Test
    public void testUP_TO_DOWN_EXACT_TWO_BOX_REVERSAL_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 101, 99},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, true));
    }

    @Test
    public void testDOWN_TO_UP_EXACT_TWO_BOX_REVERSAL_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 99, 101},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, false));
    }

    @Test
    public void testUP_TO_DOWN_LARGE_REVERSAL_variation1() {
        run(new RenkoBarAggregator(1.0),
                bars(Duration.ofMinutes(1), new double[] {100, 102, 98},
                        new Double[] {1.0, null, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, true));
    }

    @Test
    public void testDOWN_TO_UP_LARGE_REVERSAL_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 98, 102},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, null, 30.0},
                        new long[] {1, 2, 3}, false));
    }

    @Test
    public void testREVERSAL_AMOUNT_ONE_UP_TO_DOWN_variation1() {
        run(new RenkoBarAggregator(1.0, 1),
                bars(Duration.ofMinutes(1), new double[] {100, 101, 100},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, true));
    }

    @Test
    public void testREVERSAL_AMOUNT_THREE_EXACT_variation1() {
        run(new RenkoBarAggregator(1.0, 3),
                bars(Duration.ofMinutes(1), new double[] {100, 101, 98},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, false));
    }

    @Test
    public void testREVERSAL_AMOUNT_THREE_ONE_SHORT_variation1() {
        run(new RenkoBarAggregator(1.0, 3),
                bars(Duration.ofMinutes(1), new double[] {100, 101, 99},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, true));
    }

    @Test
    public void testDEFAULT_CONSTRUCTOR_TWO_BOX_REVERSAL_variation1() {
        run(new RenkoBarAggregator(1.0),
                bars(Duration.ofMinutes(1), new double[] {100, 101, 99},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, false));
    }

    @Test
    public void testFRACTIONAL_BOX_THRESHOLD_variation1() {
        run(new RenkoBarAggregator(0.5, 2),
                bars(Duration.ofMinutes(1), new double[] {10, 11},
                        new Double[] {1.0, null}, new Double[] {10.0, 20.0},
                        new long[] {1, 2}, true));
    }

    @Test
    public void testPENDING_METADATA_ACCUMULATES_UNTIL_FIRST_BRICK_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 100.5, 101},
                        new Double[] {2.0, 3.0, 5.0}, new Double[] {20.0, 30.0, 50.0},
                        new long[] {1, 2, 3}, false));
    }

    @Test
    public void testMULTI_BRICK_METADATA_FIRST_ONLY_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 103},
                        new Double[] {4.0, 6.0}, new Double[] {40.0, 60.0},
                        new long[] {2, 3}, false));
    }

    @Test
    public void testPENDING_METADATA_AFTER_PRIOR_EMISSION_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 101, 101.5, 102},
                        new Double[] {1.0, 2.0, 3.0, 4.0},
                        new Double[] {10.0, 20.0, 30.0, 40.0},
                        new long[] {1, 1, 1, 1}, false));
    }

    @Test
    public void testNULL_VOLUME_IS_NOT_ADDED_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 100.5, 101},
                        new Double[] {2.0, null, 5.0}, new Double[] {20.0, 30.0, 50.0},
                        new long[] {1, 2, 3}, true));
    }

    @Test
    public void testNULL_AMOUNT_IS_NOT_ADDED_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 100.5, 101},
                        new Double[] {2.0, 3.0, 5.0}, new Double[] {20.0, null, 50.0},
                        new long[] {1, 2, 3}, false));
    }

    @Test
    public void testNULL_VOLUME_AND_AMOUNT_WITH_MULTI_BRICK_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 102},
                        new Double[] {null, 5.0}, new Double[] {40.0, null},
                        new long[] {2, 3}, true));
    }

    @Test
    public void testLARGE_TRADE_ACCUMULATION_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 100.5, 101},
                        new Double[] {2.0, 3.0, 5.0}, new Double[] {20.0, 30.0, 50.0},
                        new long[] {3_000_000_000L, 4_000_000_000L, 5_000_000_000L}, false));
    }

    @Test
    public void testSOURCE_END_TIME_ADVANCES_BRICK_TIME_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 101, 102},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, true));
    }

    @Test
    public void testMULTI_BRICK_TIME_INCREMENT_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 103},
                        new Double[] {1.0, 2.0}, new Double[] {10.0, 20.0},
                        new long[] {1, 2}, false));
    }

    @Test
    public void testNEXT_BRICK_TIME_EXCEEDS_LATER_SOURCE_END_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 103, 104},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, true));
    }

    @Test
    public void testMINIMAL_NANOSECOND_PERIOD_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofNanos(1), new double[] {100, 101, 102},
                        new Double[] {1.0, 2.0, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, false));
    }

    @Test
    public void testLONG_SOURCE_PERIOD_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofDays(1), new double[] {100, 99, 98},
                        new Double[] {1.0, null, 3.0}, new Double[] {10.0, 20.0, 30.0},
                        new long[] {1, 2, 3}, true));
    }

    @Test
    public void testVALID_NON_CLOSE_OHLC_VALUES_variation1() {
        run(new RenkoBarAggregator(1.0, 2),
                bars(Duration.ofMinutes(1), new double[] {100, 101},
                        new Double[] {2.0, 3.0}, new Double[] {20.0, 30.0},
                        new long[] {1, 2}, true));
    }
}
