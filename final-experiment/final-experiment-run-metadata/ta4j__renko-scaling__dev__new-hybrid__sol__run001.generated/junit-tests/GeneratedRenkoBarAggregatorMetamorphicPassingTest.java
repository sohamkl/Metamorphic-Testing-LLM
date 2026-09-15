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

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static void verify(RenkoBarAggregator sourceAggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        Object[] followUp =
                RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = castBars(followUp[1]);

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> bars(double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            result.add(bar(
                    index,
                    close,
                    close - 0.25,
                    close + 0.5,
                    close - 0.5,
                    index + 1.0,
                    (index + 1.0) * 10.0,
                    index + 1L));
        }
        return result;
    }

    private static List<Bar> barsWithMetadata(
            double[] closes,
            Double[] volumes,
            Double[] amounts,
            long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            result.add(bar(
                    index,
                    close,
                    close - 0.25,
                    close + 0.5,
                    close - 0.5,
                    volumes[index],
                    amounts[index],
                    trades[index]));
        }
        return result;
    }

    private static Bar bar(
            int index,
            double close,
            Double open,
            Double high,
            Double low,
            Double volume,
            Double amount,
            long trades) {
        Instant beginTime = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant endTime = beginTime.plus(PERIOD);
        return new BaseBar(
                PERIOD,
                beginTime,
                endTime,
                num(open),
                num(high),
                num(low),
                num(close),
                num(volume),
                num(amount),
                trades);
    }

    private static Num num(Double value) {
        return value == null ? null : DecimalNum.valueOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> castBars(Object value) {
        return (List<Bar>) value;
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        verify(new RenkoBarAggregator(2), List.of());
    }

    @Test
    void SINGLE_BAR_ANCHOR_ONLY_variation1() {
        verify(new RenkoBarAggregator(0.5, 2), bars(0.0));
    }

    @Test
    void MULTI_BAR_FLAT_CLOSES_variation1() {
        verify(new RenkoBarAggregator(4), bars(-2.0, -2.0, -2.0, -2.0));
    }

    @Test
    void NONE_DIRECTION_SUBBOX_OSCILLATION_variation1() {
        verify(new RenkoBarAggregator(4, Integer.MAX_VALUE),
                bars(-20.0, -18.0, -22.0, -17.0, -23.0));
    }

    @Test
    void EXACT_INITIAL_UP_BRICK_variation1() {
        verify(new RenkoBarAggregator(0.5), bars(10.0, 10.5));
    }

    @Test
    void EXACT_INITIAL_DOWN_BRICK_variation1() {
        double box = Math.scalb(1.0, 105);
        verify(new RenkoBarAggregator(box, 2), bars(0.0, -box));
    }

    @Test
    void FRACTIONAL_INITIAL_UP_OVERSHOOT_variation1() {
        verify(new RenkoBarAggregator(2), bars(-1.0, 2.0));
    }

    @Test
    void FRACTIONAL_INITIAL_DOWN_OVERSHOOT_variation1() {
        verify(new RenkoBarAggregator(0.5, Integer.MAX_VALUE), bars(-3.0, -3.75));
    }

    @Test
    void MULTI_BRICK_INITIAL_DOWN_MOVE_variation1() {
        verify(new RenkoBarAggregator(2, 2), bars(0.0, -6.0));
    }

    @Test
    void UP_DIRECTION_CONTINUATION_variation1() {
        verify(new RenkoBarAggregator(0.5), bars(-0.5, 0.0, 0.5));
    }

    @Test
    void DOWN_DIRECTION_CONTINUATION_variation1() {
        double box = Math.scalb(1.0, 103);
        verify(new RenkoBarAggregator(box, Integer.MAX_VALUE),
                bars(-box, -2.0 * box, -3.0 * box));
    }

    @Test
    void UP_PULLBACK_BELOW_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(2), bars(10.0, 12.0, 9.0));
    }

    @Test
    void EXACT_UP_TO_DOWN_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(0.5, 2), bars(0.0, 0.5, -0.5));
    }

    @Test
    void UP_TO_DOWN_REVERSAL_OVERSHOOT_variation1() {
        verify(new RenkoBarAggregator(4), bars(-2.0, 2.0, -12.0));
    }

    @Test
    void DOWN_PULLBACK_BELOW_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(3, 2), bars(-10.0, -13.0, -8.5));
    }

    @Test
    void EXACT_DOWN_TO_UP_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(0.5), bars(5.0, 4.5, 5.5));
    }

    @Test
    void DOWN_TO_UP_REVERSAL_OVERSHOOT_variation1() {
        verify(new RenkoBarAggregator(2, 2), bars(0.0, -2.0, 5.0));
    }

    @Test
    void ONE_BOX_UP_TO_DOWN_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(2, 1), bars(-1.0, 1.0, -1.0));
    }

    @Test
    void ONE_BOX_DOWN_TO_UP_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(0.5, 1), bars(-4.0, -4.5, -4.0));
    }

    @Test
    void EXACT_THREE_BOX_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(2, 3), bars(0.0, 2.0, -4.0));
    }

    @Test
    void MAXIMUM_INT_REVERSAL_WITH_MODEST_MOVE_variation1() {
        verify(new RenkoBarAggregator(0.5, Integer.MAX_VALUE), bars(-1.0, -0.5, -6.0));
    }

    @Test
    void ALTERNATING_CONFIRMED_REVERSALS_variation1() {
        verify(new RenkoBarAggregator(4, 2), bars(-8.0, -4.0, -12.0, -4.0));
    }

    @Test
    void FRACTIONAL_RESIDUAL_COMPLETED_LATER_variation1() {
        verify(new RenkoBarAggregator(2), bars(10.0, 15.0, 16.0));
    }

    @Test
    void FRACTIONAL_REVERSAL_RESIDUAL_variation1() {
        verify(new RenkoBarAggregator(0.5, 2), bars(0.0, 0.5, -0.75));
    }

    @Test
    void PENDING_METADATA_ACROSS_NONEMITTING_BARS_variation1() {
        verify(new RenkoBarAggregator(4),
                barsWithMetadata(
                        new double[] { -2.0, -1.0, 1.0, 2.0 },
                        new Double[] { 2.0, 3.0, 5.0, 7.0 },
                        new Double[] { 11.0, 13.0, 17.0, 19.0 },
                        new long[] { 1L, 2L, 3L, 4L }));
    }

    @Test
    void UP_MULTI_BRICK_METADATA_SENTINELS_variation1() {
        verify(new RenkoBarAggregator(2, Integer.MAX_VALUE),
                barsWithMetadata(
                        new double[] { -10.0, -2.0 },
                        new Double[] { 3.0, 7.0 },
                        new Double[] { 5.0, 11.0 },
                        new long[] { 2L, 3L }));
    }

    @Test
    void DOWN_MULTI_BRICK_METADATA_SENTINELS_variation1() {
        verify(new RenkoBarAggregator(0.5),
                barsWithMetadata(
                        new double[] { 5.0, 3.0 },
                        new Double[] { 4.0, 6.0 },
                        new Double[] { 8.0, 12.0 },
                        new long[] { 3L, 5L }));
    }

    @Test
    void METADATA_RESET_BETWEEN_EMITTING_SOURCE_BARS_variation1() {
        verify(new RenkoBarAggregator(2, 2),
                barsWithMetadata(
                        new double[] { 0.0, 2.0, 4.0, 6.0 },
                        new Double[] { 1.0, 2.0, 4.0, 8.0 },
                        new Double[] { 10.0, 20.0, 40.0, 80.0 },
                        new long[] { 1L, 2L, 4L, 8L }));
    }

    @Test
    void ALL_NULL_VOLUMES_variation1() {
        verify(new RenkoBarAggregator(2),
                barsWithMetadata(
                        new double[] { -1.0, 1.0 },
                        new Double[] { null, null },
                        new Double[] { 3.0, 5.0 },
                        new long[] { 1L, 2L }));
    }

    @Test
    void MIXED_NULL_AND_PRESENT_VOLUMES_variation1() {
        verify(new RenkoBarAggregator(0.5, Integer.MAX_VALUE),
                barsWithMetadata(
                        new double[] { -3.0, -2.75, -2.5 },
                        new Double[] { null, 6.0, 4.0 },
                        new Double[] { 2.0, 3.0, 5.0 },
                        new long[] { 1L, 1L, 2L }));
    }

    @Test
    void MIXED_NULL_AND_PRESENT_AMOUNTS_variation1() {
        verify(new RenkoBarAggregator(2, 2),
                barsWithMetadata(
                        new double[] { 0.0, -1.0, -2.0 },
                        new Double[] { 2.0, 3.0, 4.0 },
                        new Double[] { null, 7.0, null },
                        new long[] { 1L, 2L, 3L }));
    }

    @Test
    void ZERO_METADATA_VALUES_variation1() {
        verify(new RenkoBarAggregator(0.5),
                barsWithMetadata(
                        new double[] { -0.5, 0.5 },
                        new Double[] { 0.0, 0.0 },
                        new Double[] { 0.0, 0.0 },
                        new long[] { 0L, 0L }));
    }

    @Test
    void SOURCE_END_AFTER_SCHEDULED_END_variation1() {
        verify(new RenkoBarAggregator(4, Integer.MAX_VALUE),
                bars(-12.0, -11.0, -10.0, -8.0));
    }

    @Test
    void SCHEDULED_TIME_AFTER_SOURCE_END_FOR_EXTRA_BRICKS_variation1() {
        verify(new RenkoBarAggregator(2), bars(10.0, 16.0));
    }

    @Test
    void SOURCE_END_EQUALS_SCHEDULED_END_variation1() {
        verify(new RenkoBarAggregator(0.5, 2),
                barsWithMetadata(
                        new double[] { 2.0, 2.5, 3.0 },
                        new Double[] { 2.0, 3.0, 5.0 },
                        new Double[] { 4.0, 6.0, 10.0 },
                        new long[] { 1L, 2L, 3L }));
    }

    @Test
    void NEGATIVE_PRICES_CROSSING_ZERO_variation1() {
        verify(new RenkoBarAggregator(2, Integer.MAX_VALUE),
                bars(-4.0, -2.0, 0.0, 2.0));
    }

    @Test
    void ZERO_PRICE_ANCHOR_variation1() {
        verify(new RenkoBarAggregator(0.5), bars(0.0, -1.0));
    }

    @Test
    void EXACT_BINARY_FRACTION_BOX_variation1() {
        verify(new RenkoBarAggregator(0.5, 2),
                barsWithMetadata(
                        new double[] { 0.0, 0.5, 1.0 },
                        new Double[] { 7.0, 11.0, 13.0 },
                        new Double[] { 14.0, 22.0, 26.0 },
                        new long[] { 5L, 8L, 13L }));
    }

    @Test
    void LARGE_FINITE_SCALE_SAFE_BOX_variation1() {
        double box = Math.scalb(1.0, 110);
        verify(new RenkoBarAggregator(box), bars(box, 2.0 * box));
    }

    @Test
    void DEFAULT_TWO_BRICK_CONSTRUCTOR_variation1() {
        verify(new RenkoBarAggregator(0.5), bars(-2.0, -2.5, -1.5));
    }

    @Test
    void NON_CLOSE_FIELDS_DISTINCT_FROM_CLOSE_variation1() {
        verify(new RenkoBarAggregator(2),
                List.of(
                        bar(0, 8.0, 13.0, 15.0, 7.0, 2.0, 10.0, 1L),
                        bar(1, 10.0, 6.0, 18.0, 4.0, 3.0, 20.0, 2L)));
    }

    @Test
    void NULL_NON_CLOSE_PRICE_FIELDS_variation1() {
        verify(new RenkoBarAggregator(2, 2),
                List.of(
                        bar(0, 0.0, null, null, null, 2.0, 4.0, 1L),
                        bar(1, -2.0, null, 1.0, null, 3.0, 6.0, 2L)));
    }
}
