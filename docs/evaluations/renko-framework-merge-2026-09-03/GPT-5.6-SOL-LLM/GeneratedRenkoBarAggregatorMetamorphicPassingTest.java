import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DoubleNumFactory;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.NumFactory;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final NumFactory NUM_FACTORY = DoubleNumFactory.getInstance();

    private static void check(RenkoBarAggregator sourceReceiver, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);

        Object[] followUpInput =
                RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceReceiver, sourceBars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUpInput[0];

        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUpInput[1];

        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> bars(double initialClose, double boxSize, double[] normalizedOffsets) {
        Double[] volumes = new Double[normalizedOffsets.length];
        double[] amounts = new double[normalizedOffsets.length];
        long[] trades = new long[normalizedOffsets.length];

        for (int i = 0; i < normalizedOffsets.length; i++) {
            volumes[i] = i + 1.0;
            amounts[i] = (i + 1) * 0.25;
            trades[i] = i + 1L;
        }

        return bars(initialClose, boxSize, normalizedOffsets, volumes, amounts, trades);
    }

    private static List<Bar> bars(double initialClose, double boxSize, double[] normalizedOffsets,
            Double[] volumes, double[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(normalizedOffsets.length);

        for (int i = 0; i < normalizedOffsets.length; i++) {
            double close = initialClose + normalizedOffsets[i] * boxSize;
            double open = close - boxSize * 0.125;
            double high = Math.max(open, close) + boxSize * 2.0;
            double low = Math.min(open, close) - boxSize * 2.0;
            result.add(bar(i, open, high, low, close, volumes[i], amounts[i], trades[i]));
        }

        return result;
    }

    private static Bar bar(int index, double open, double high, double low, double close,
            Double volume, double amount, long trades) {
        Instant beginTime = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant endTime = beginTime.plus(PERIOD);

        Num openNum = NUM_FACTORY.numOf(open);
        Num highNum = NUM_FACTORY.numOf(high);
        Num lowNum = NUM_FACTORY.numOf(low);
        Num closeNum = NUM_FACTORY.numOf(close);
        Num volumeNum = volume == null ? null : NUM_FACTORY.numOf(volume);
        Num amountNum = NUM_FACTORY.numOf(amount);

        return new BaseBar(PERIOD, beginTime, endTime, openNum, highNum, lowNum, closeNum,
                volumeNum, amountNum, trades);
    }

    @Test
    public void EMPTY_SOURCE_LIST_variation1() {
        check(new RenkoBarAggregator(0.000000001), new ArrayList<>());
    }

    @Test
    public void SINGLE_BAR_INITIALIZATION_ONLY_variation1() {
        check(new RenkoBarAggregator(0.5, 2), bars(0.0, 0.5, new double[] { 0.0 }));
    }

    @Test
    public void MULTI_BAR_FLAT_CLOSES_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0),
                bars(5_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, 0.0, 0.0, 0.0 }));
    }

    @Test
    public void JUST_BELOW_FIRST_UP_THRESHOLD_variation1() {
        check(new RenkoBarAggregator(0.000000001, 1),
                bars(-10.0, 0.000000001, new double[] { 0.0, 0.125, 0.25, 0.375, 0.5 }));
    }

    @Test
    public void EXACT_FIRST_UP_BRICK_variation1() {
        check(new RenkoBarAggregator(0.5), bars(0.0, 0.5, new double[] { 0.0, 1.0 }));
    }

    @Test
    public void JUST_ABOVE_FIRST_DOWN_THRESHOLD_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0, 3),
                bars(5_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, -0.5 }));
    }

    @Test
    public void EXACT_FIRST_DOWN_BRICK_variation1() {
        check(new RenkoBarAggregator(0.25), bars(-2.0, 0.25, new double[] { 0.0, -1.0 }));
    }

    @Test
    public void MULTIPLE_UP_BRICKS_ONE_SOURCE_BAR_variation1() {
        check(new RenkoBarAggregator(0.5, 2), bars(0.0, 0.5, new double[] { 0.0, 3.0 }));
    }

    @Test
    public void MULTIPLE_DOWN_BRICKS_ONE_SOURCE_BAR_variation1() {
        check(new RenkoBarAggregator(2_000_000_000.0),
                bars(8_000_000_000.0, 2_000_000_000.0, new double[] { 0.0, -3.0 }));
    }

    @Test
    public void PENDING_METRICS_RELEASED_ON_FIRST_UP_BRICK_variation1() {
        check(new RenkoBarAggregator(0.125, 1),
                bars(-1.0, 0.125, new double[] { 0.0, 0.25, 0.75, 1.0 },
                        new Double[] { 1.0, 2.0, null, 4.0 },
                        new double[] { 0.125, 0.25, 0.375, 0.5 },
                        new long[] { 1L, 2L, 3L, 4L }));
    }

    @Test
    public void PENDING_METRICS_RELEASED_ON_FIRST_DOWN_BRICK_variation1() {
        check(new RenkoBarAggregator(0.5),
                bars(0.0, 0.5, new double[] { 0.0, -0.25, -0.75, -1.0 },
                        new Double[] { 1.0, null, 2.0, 3.0 },
                        new double[] { 0.5, 0.75, 1.25, 1.5 },
                        new long[] { 1L, 2L, 3L, 4L }));
    }

    @Test
    public void CONTINUE_ESTABLISHED_UP_DIRECTION_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0, 3),
                bars(4_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, 1.0, 2.0 }));
    }

    @Test
    public void CONTINUE_ESTABLISHED_DOWN_DIRECTION_variation1() {
        check(new RenkoBarAggregator(0.000000001),
                bars(-1.0, 0.000000001, new double[] { 0.0, -1.0, -2.0 }));
    }

    @Test
    public void UP_DIRECTION_SUB_REVERSAL_PULLBACK_variation1() {
        check(new RenkoBarAggregator(0.5, 2),
                bars(0.0, 0.5, new double[] { 0.0, 1.0, -0.5 },
                        new Double[] { 1.0, 2.0, 3.0 },
                        new double[] { 0.25, 0.5, 0.75 },
                        new long[] { 2L, 3L, 5L }));
    }

    @Test
    public void DOWN_DIRECTION_SUB_REVERSAL_BOUNCE_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0),
                bars(5_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, -1.0, 0.5 }));
    }

    @Test
    public void EXACT_UP_TO_DOWN_REVERSAL_R2_variation1() {
        check(new RenkoBarAggregator(0.125, 2),
                bars(-4.0, 0.125, new double[] { 0.0, 1.0, -1.0 }));
    }

    @Test
    public void OVERSHOOT_UP_TO_DOWN_REVERSAL_variation1() {
        check(new RenkoBarAggregator(0.5),
                bars(0.0, 0.5, new double[] { 0.0, 1.0, -2.5 }));
    }

    @Test
    public void EXACT_DOWN_TO_UP_REVERSAL_R2_variation1() {
        check(new RenkoBarAggregator(2_000_000_000.0, 2),
                bars(8_000_000_000.0, 2_000_000_000.0, new double[] { 0.0, -1.0, 1.0 }));
    }

    @Test
    public void OVERSHOOT_DOWN_TO_UP_REVERSAL_variation1() {
        check(new RenkoBarAggregator(0.25),
                bars(-5.0, 0.25, new double[] { 0.0, -1.0, 2.5 }));
    }

    @Test
    public void ONE_BOX_UP_TO_DOWN_REVERSAL_variation1() {
        check(new RenkoBarAggregator(0.5, 1),
                bars(0.0, 0.5, new double[] { 0.0, 1.0, 0.0 }));
    }

    @Test
    public void ONE_BOX_DOWN_TO_UP_REVERSAL_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0, 1),
                bars(5_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, -1.0, 0.0 }));
    }

    @Test
    public void R3_JUST_SHORT_OF_REVERSAL_variation1() {
        check(new RenkoBarAggregator(0.125, 3),
                bars(-2.0, 0.125, new double[] { 0.0, 1.0, -1.5 }));
    }

    @Test
    public void EXACT_UP_TO_DOWN_REVERSAL_R3_variation1() {
        check(new RenkoBarAggregator(0.5, 3),
                bars(0.0, 0.5, new double[] { 0.0, 1.0, -2.0 }));
    }

    @Test
    public void DEFAULT_CONSTRUCTOR_TWO_BRICK_REVERSAL_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0),
                bars(5_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, -1.0, 1.0 }));
    }

    @Test
    public void DELAYED_FIRST_EMISSION_USES_SOURCE_END_variation1() {
        check(new RenkoBarAggregator(0.000000001),
                bars(-1.0, 0.000000001, new double[] { 0.0, 0.5, 1.0 }));
    }

    @Test
    public void MULTI_BRICK_JUMP_ADVANCES_FUTURE_TIMESTAMPS_variation1() {
        check(new RenkoBarAggregator(0.25, 2),
                bars(2.0, 0.25, new double[] { 0.0, 3.0 }));
    }

    @Test
    public void SOURCE_END_EQUALS_NEXT_SCHEDULED_END_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0),
                bars(4_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, 1.0, 2.0 }));
    }

    @Test
    public void SCHEDULED_END_AHEAD_OF_LATER_SOURCE_variation1() {
        check(new RenkoBarAggregator(0.000000001, 1),
                bars(-2.0, 0.000000001, new double[] { 0.0, 3.0, 4.0 }));
    }

    @Test
    public void METRICS_ONLY_ON_FIRST_BRICK_OF_JUMP_variation1() {
        check(new RenkoBarAggregator(0.5),
                bars(0.0, 0.5, new double[] { 0.0, 3.0 },
                        new Double[] { 2.0, 5.0 },
                        new double[] { 0.75, 1.25 },
                        new long[] { 3L, 7L }));
    }

    @Test
    public void METRICS_RESET_THEN_REACCUMULATE_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0, 3),
                bars(5_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, 2.0, 3.0 },
                        new Double[] { 1.0, 2.0, 4.0 },
                        new double[] { 0.25, 0.5, 1.0 },
                        new long[] { 1L, 2L, 4L }));
    }

    @Test
    public void MIXED_NULL_AND_NON_NULL_VOLUME_variation1() {
        check(new RenkoBarAggregator(0.25),
                bars(-3.0, 0.25, new double[] { 0.0, 0.5, 1.0 },
                        new Double[] { null, 2.5, null },
                        new double[] { 0.25, 0.5, 0.75 },
                        new long[] { 1L, 2L, 3L }));
    }

    @Test
    public void ALL_ZERO_METRICS_variation1() {
        check(new RenkoBarAggregator(0.5, 2),
                bars(0.0, 0.5, new double[] { 0.0, -2.0 },
                        new Double[] { 0.0, 0.0 },
                        new double[] { 0.0, 0.0 },
                        new long[] { 0L, 0L }));
    }

    @Test
    public void FRACTIONAL_MONETARY_AMOUNT_ACCUMULATION_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0),
                bars(5_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, 0.5, 1.0 },
                        new Double[] { 1.0, 1.0, 1.0 },
                        new double[] { 0.125, 0.375, 0.625 },
                        new long[] { 0L, 0L, 0L }));
    }

    @Test
    public void NONZERO_TRADE_ACCUMULATION_variation1() {
        check(new RenkoBarAggregator(0.000000001, 1),
                bars(-1.0, 0.000000001, new double[] { 0.0, -0.5, -1.0 },
                        new Double[] { 0.0, 0.0, 0.0 },
                        new double[] { 0.0, 0.0, 0.0 },
                        new long[] { 2L, 3L, 5L }));
    }

    @Test
    public void SOURCE_OPEN_HIGH_LOW_DO_NOT_CONTROL_EMISSION_variation1() {
        List<Bar> source = new ArrayList<>();
        source.add(bar(0, -1.5, 3.0, -3.0, 0.0, 1.0, 0.25, 1L));
        source.add(bar(1, 2.0, 4.0, -2.0, 0.5, 2.0, 0.5, 2L));
        check(new RenkoBarAggregator(0.5), source);
    }

    @Test
    public void INTRABAR_EXTREMES_CROSS_BOX_WITHOUT_CLOSE_CROSSING_variation1() {
        List<Bar> source = new ArrayList<>();
        source.add(bar(0, 5_000_000_000.0, 7_000_000_000.0, 3_000_000_000.0,
                5_000_000_000.0, 0.0, 0.0, 0L));
        source.add(bar(1, 5_250_000_000.0, 7_000_000_000.0, 4_000_000_000.0,
                5_250_000_000.0, 0.0, 0.0, 0L));
        source.add(bar(2, 4_500_000_000.0, 6_000_000_000.0, 3_500_000_000.0,
                4_500_000_000.0, 0.0, 0.0, 0L));
        source.add(bar(3, 5_500_000_000.0, 7_500_000_000.0, 4_500_000_000.0,
                5_500_000_000.0, 0.0, 0.0, 0L));
        source.add(bar(4, 4_750_000_000.0, 6_500_000_000.0, 3_000_000_000.0,
                4_750_000_000.0, 0.0, 0.0, 0L));
        check(new RenkoBarAggregator(1_000_000_000.0, 3), source);
    }

    @Test
    public void NEGATIVE_PRICE_BASELINE_variation1() {
        check(new RenkoBarAggregator(0.25),
                bars(-4.0, 0.25, new double[] { 0.0, 1.0, 2.0 }));
    }

    @Test
    public void ZERO_PRICE_BASELINE_variation1() {
        check(new RenkoBarAggregator(0.5, 2),
                bars(0.0, 0.5, new double[] { 0.0, -1.0 }));
    }

    @Test
    public void FRACTIONAL_BOX_SIZE_EXACT_BOUNDARIES_variation1() {
        check(new RenkoBarAggregator(0.25, 2),
                bars(2.0, 0.25, new double[] { 0.0, 1.0, -1.0 }));
    }

    @Test
    public void VERY_SMALL_POSITIVE_BOX_SIZE_variation1() {
        check(new RenkoBarAggregator(0.000000001, 1),
                bars(-1.0, 0.000000001, new double[] { 0.0, 2.0 }));
    }

    @Test
    public void LARGE_FINITE_BOX_AND_PRICES_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0),
                bars(5_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, -2.0 }));
    }

    @Test
    public void ALTERNATING_EXACT_REVERSALS_variation1() {
        check(new RenkoBarAggregator(2_000_000_000.0, 2),
                bars(8_000_000_000.0, 2_000_000_000.0, new double[] { 0.0, 1.0, -1.0, 1.0 }));
    }

    @Test
    public void PENDING_METRICS_DISCARDED_AT_END_variation1() {
        check(new RenkoBarAggregator(0.125),
                bars(-2.0, 0.125, new double[] { 0.0, 1.0, 1.25, 0.5 },
                        new Double[] { 1.0, 2.0, null, 4.0 },
                        new double[] { 0.25, 0.5, 1.25, 2.5 },
                        new long[] { 1L, 2L, 4L, 8L }));
    }

    @Test
    public void REPEATED_CLOSE_AT_LAST_BRICK_BOUNDARY_variation1() {
        check(new RenkoBarAggregator(0.5, 2),
                bars(0.0, 0.5, new double[] { 0.0, 1.0, 1.0, 1.0 }));
    }

    @Test
    public void CONTINUATION_OVERSHOOT_WITH_REMAINDER_variation1() {
        check(new RenkoBarAggregator(1_000_000_000.0),
                bars(5_000_000_000.0, 1_000_000_000.0, new double[] { 0.0, -1.0, -3.5 }));
    }

    @Test
    public void SUB_BOX_OSCILLATION_THEN_UP_EMISSION_variation1() {
        check(new RenkoBarAggregator(0.000000001, 1),
                bars(-1.0, 0.000000001, new double[] { 0.0, 0.75, -0.75, 0.5, 1.0 }));
    }

    @Test
    public void SUB_BOX_OSCILLATION_THEN_DOWN_EMISSION_variation1() {
        check(new RenkoBarAggregator(0.5),
                bars(0.0, 0.5, new double[] { 0.0, -0.75, 0.75, -0.5, -1.0 }));
    }
}
