import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration MINUTE = Duration.ofMinutes(1);
    private static final Duration NANOSECOND = Duration.ofNanos(1);
    private static final Duration MULTI_DAY = Duration.ofDays(3);

    private static void verify(RenkoBarAggregator sourceAggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        Object[] followUpInput =
                RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUpInput[0];

        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUpInput[1];

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> bars(Duration period, double... closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];

        for (int i = 0; i < closes.length; i++) {
            volumes[i] = (double) (i + 1);
            amounts[i] = (double) ((i + 1) * 2);
            trades[i] = i + 1L;
        }

        return barsWithData(period, closes, volumes, amounts, trades);
    }

    private static List<Bar> barsWithData(
            Duration period,
            double[] closes,
            Double[] volumes,
            Double[] amounts,
            long[] trades) {

        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = START.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = num(closes[i]);
            Num volume = volumes[i] == null ? null : num(volumes[i]);
            Num amount = amounts[i] == null ? null : num(amounts[i]);

            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    volume,
                    amount,
                    trades[i]));
        }
        return result;
    }

    private static List<Bar> barsWithNullNonClosePrices(Duration period, double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = START.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = num(closes[i]);

            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    null,
                    null,
                    null,
                    close,
                    num(i + 1),
                    num((i + 1) * 2),
                    i + 1L));
        }
        return result;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    @Test
    public void test_EMPTY_SOURCE_LIST_variation1() {
        verify(new RenkoBarAggregator(4), List.of());
    }

    @Test
    public void test_SINGLE_BAR_ESTABLISHES_ANCHOR_variation1() {
        verify(new RenkoBarAggregator(0.25, 1), bars(MINUTE, -0.25));
    }

    @Test
    public void test_MULTIPLE_FLAT_CLOSES_variation1() {
        verify(new RenkoBarAggregator(0.125, 2), bars(MULTI_DAY, 1.0, 1.0, 1.0));
    }

    @Test
    public void test_SUB_BOX_UPWARD_MOVE_variation1() {
        verify(new RenkoBarAggregator(8.0, 3), bars(NANOSECOND, -20.0, -16.0));
    }

    @Test
    public void test_SUB_BOX_DOWNWARD_MOVE_variation1() {
        verify(new RenkoBarAggregator(4), bars(MINUTE, 20.0, 18.0));
    }

    @Test
    public void test_EXACT_INITIAL_UP_THRESHOLD_variation1() {
        verify(new RenkoBarAggregator(0.25, 1), bars(MULTI_DAY, -0.25, 0.0));
    }

    @Test
    public void test_EXACT_INITIAL_DOWN_THRESHOLD_variation1() {
        verify(new RenkoBarAggregator(0.125, 2), bars(NANOSECOND, 0.125, 0.0));
    }

    @Test
    public void test_APPROACH_THEN_REACH_UP_THRESHOLD_variation1() {
        verify(new RenkoBarAggregator(8.0, 3), bars(MINUTE, -40.0, -38.0, -34.0, -32.0));
    }

    @Test
    public void test_APPROACH_THEN_REACH_DOWN_THRESHOLD_variation1() {
        verify(new RenkoBarAggregator(4), bars(MULTI_DAY, 20.0, 19.0, 17.0, 16.0));
    }

    @Test
    public void test_THREE_UP_BRICKS_FROM_ONE_BAR_variation1() {
        verify(new RenkoBarAggregator(0.25, 1), bars(NANOSECOND, -0.25, 0.5));
    }

    @Test
    public void test_THREE_DOWN_BRICKS_FROM_ONE_BAR_variation1() {
        verify(new RenkoBarAggregator(0.125, 2), bars(MINUTE, 0.25, -0.125));
    }

    @Test
    public void test_UP_DIRECTION_CONTINUATION_variation1() {
        verify(new RenkoBarAggregator(8.0, 3), bars(MULTI_DAY, -40.0, -32.0, -24.0, -16.0));
    }

    @Test
    public void test_DOWN_DIRECTION_CONTINUATION_variation1() {
        verify(new RenkoBarAggregator(4), bars(NANOSECOND, 20.0, 16.0, 12.0, 8.0));
    }

    @Test
    public void test_UP_PULLBACK_BELOW_REVERSAL_THRESHOLD_variation1() {
        verify(new RenkoBarAggregator(0.25, 2), bars(MINUTE, -0.25, 0.0, -0.375));
    }

    @Test
    public void test_EXACT_TWO_BOX_UP_TO_DOWN_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(0.125, 2), bars(MULTI_DAY, 0.0, 0.125, -0.125));
    }

    @Test
    public void test_UP_TO_DOWN_REVERSAL_OVERSHOOT_variation1() {
        verify(new RenkoBarAggregator(8.0, 2), bars(NANOSECOND, -40.0, -32.0, -56.0));
    }

    @Test
    public void test_DOWN_PULLBACK_BELOW_REVERSAL_THRESHOLD_variation1() {
        verify(new RenkoBarAggregator(4), bars(MINUTE, 20.0, 16.0, 23.0));
    }

    @Test
    public void test_EXACT_TWO_BOX_DOWN_TO_UP_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(0.25, 2), bars(MULTI_DAY, 0.25, 0.0, 0.5));
    }

    @Test
    public void test_DOWN_TO_UP_REVERSAL_OVERSHOOT_variation1() {
        verify(new RenkoBarAggregator(0.125, 2), bars(NANOSECOND, 0.125, 0.0, 0.375));
    }

    @Test
    public void test_ONE_BOX_UP_TO_DOWN_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(8.0, 1), bars(MINUTE, -24.0, -16.0, -24.0));
    }

    @Test
    public void test_ONE_BOX_DOWN_TO_UP_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(4, 1), bars(MULTI_DAY, 20.0, 16.0, 20.0));
    }

    @Test
    public void test_THREE_BOX_EXACT_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(0.25, 3), bars(NANOSECOND, -0.25, 0.0, -0.75));
    }

    @Test
    public void test_LARGE_REVERSAL_NOT_REACHED_variation1() {
        verify(new RenkoBarAggregator(0.125, 5), bars(MINUTE, 0.5, 0.625, 0.125));
    }

    @Test
    public void test_DEFAULT_CONSTRUCTOR_TWO_BOX_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(8.0), bars(MULTI_DAY, -32.0, -24.0, -40.0));
    }

    @Test
    public void test_PENDING_DATA_ACROSS_NON_EMITTING_BARS_variation1() {
        verify(
                new RenkoBarAggregator(4),
                barsWithData(
                        NANOSECOND,
                        new double[] { 20.0, 21.0, 22.0, 24.0 },
                        new Double[] { 1.0, 2.0, 3.0, 4.0 },
                        new Double[] { 2.0, 4.0, 6.0, 8.0 },
                        new long[] { 1L, 2L, 3L, 4L }));
    }

    @Test
    public void test_PENDING_DATA_SURVIVES_BLOCKED_PULLBACK_variation1() {
        verify(
                new RenkoBarAggregator(0.25, 2),
                barsWithData(
                        MINUTE,
                        new double[] { -0.25, 0.0, -0.1875, 0.25 },
                        new Double[] { 1.0, 2.0, 8.0, 16.0 },
                        new Double[] { 2.0, 4.0, 32.0, 64.0 },
                        new long[] { 1L, 2L, 8L, 16L }));
    }

    @Test
    public void test_MULTI_BRICK_DATA_RESET_SENTINEL_variation1() {
        verify(
                new RenkoBarAggregator(0.125, 2),
                barsWithData(
                        MULTI_DAY,
                        new double[] { -0.125, 0.5 },
                        new Double[] { 3.0, 7.0 },
                        new Double[] { 5.0, 11.0 },
                        new long[] { 2L, 13L }));
    }

    @Test
    public void test_NULL_VOLUME_CONTRIBUTIONS_variation1() {
        verify(
                new RenkoBarAggregator(8.0, 3),
                barsWithData(
                        NANOSECOND,
                        new double[] { -24.0, -16.0 },
                        new Double[] { null, null },
                        new Double[] { 2.0, 4.0 },
                        new long[] { 1L, 2L }));
    }

    @Test
    public void test_MIXED_NULL_AND_NON_NULL_VOLUME_variation1() {
        verify(
                new RenkoBarAggregator(4),
                barsWithData(
                        MINUTE,
                        new double[] { 20.0, 24.0 },
                        new Double[] { null, 7.0 },
                        new Double[] { 2.0, 4.0 },
                        new long[] { 1L, 2L }));
    }

    @Test
    public void test_NULL_AMOUNT_CONTRIBUTIONS_variation1() {
        verify(
                new RenkoBarAggregator(0.25, 1),
                barsWithData(
                        MULTI_DAY,
                        new double[] { 0.25, 0.0 },
                        new Double[] { 3.0, 5.0 },
                        new Double[] { null, null },
                        new long[] { 1L, 2L }));
    }

    @Test
    public void test_MIXED_NULL_AND_NON_NULL_AMOUNT_variation1() {
        verify(
                new RenkoBarAggregator(0.125, 2),
                barsWithData(
                        NANOSECOND,
                        new double[] { -0.125, 0.0 },
                        new Double[] { 3.0, 5.0 },
                        new Double[] { null, 8.0 },
                        new long[] { 1L, 2L }));
    }

    @Test
    public void test_NULL_VOLUME_AND_AMOUNT_WITH_ZERO_TRADES_variation1() {
        verify(
                new RenkoBarAggregator(8.0, 3),
                barsWithData(
                        MINUTE,
                        new double[] { -40.0, -24.0 },
                        new Double[] { null, null },
                        new Double[] { null, null },
                        new long[] { 0L, 0L }));
    }

    @Test
    public void test_SOURCE_END_TIME_SELECTED_variation1() {
        verify(new RenkoBarAggregator(4), bars(MULTI_DAY, 20.0, 21.0, 22.0, 24.0));
    }

    @Test
    public void test_FUTURE_NEXT_END_SELECTED_FOR_EXTRA_BRICKS_variation1() {
        verify(
                new RenkoBarAggregator(0.5, 1),
                barsWithData(
                        NANOSECOND,
                        new double[] { 1.0, 2.5 },
                        new Double[] { 5.0, 9.0 },
                        new Double[] { 7.0, 13.0 },
                        new long[] { 3L, 11L }));
    }

    @Test
    public void test_EQUAL_END_TIME_TIE_variation1() {
        verify(new RenkoBarAggregator(0.125, 2), bars(MINUTE, 0.0, 0.25, 0.375));
    }

    @Test
    public void test_UPWARD_PRICE_CROSSING_ZERO_variation1() {
        verify(new RenkoBarAggregator(8.0, 3), bars(MULTI_DAY, -4.0, 4.0));
    }

    @Test
    public void test_DOWNWARD_PRICE_CROSSING_ZERO_variation1() {
        verify(new RenkoBarAggregator(4), bars(NANOSECOND, 2.0, -2.0));
    }

    @Test
    public void test_STRICTLY_NEGATIVE_PRICE_REGION_variation1() {
        verify(new RenkoBarAggregator(0.25, 1), bars(MINUTE, -1.0, -0.5));
    }

    @Test
    public void test_FRACTIONAL_BOX_EXACT_LATTICE_variation1() {
        verify(new RenkoBarAggregator(0.25, 2), bars(MULTI_DAY, 1.0, 1.25, 1.5));
    }

    @Test
    public void test_LARGE_SAFE_BOX_variation1() {
        verify(new RenkoBarAggregator(1.0e100, 3), bars(NANOSECOND, 4.0e100, 3.0e100));
    }

    @Test
    public void test_ALTERNATING_EXACT_REVERSALS_variation1() {
        verify(new RenkoBarAggregator(4), bars(MINUTE, 20.0, 24.0, 16.0, 24.0));
    }

    @Test
    public void test_NULL_NON_CLOSE_PRICE_FIELDS_variation1() {
        verify(
                new RenkoBarAggregator(0.25, 1),
                barsWithNullNonClosePrices(MULTI_DAY, -0.25, 0.0));
    }

    @Test
    public void test_DIVERSE_VALID_SOURCE_PERIODS_variation1_nanosecond() {
        verify(new RenkoBarAggregator(0.125, 2), bars(NANOSECOND, -0.25, 0.0));
    }

    @Test
    public void test_DIVERSE_VALID_SOURCE_PERIODS_variation2_multiDay() {
        verify(new RenkoBarAggregator(8.0, 3), bars(MULTI_DAY, -40.0, -24.0));
    }
}
