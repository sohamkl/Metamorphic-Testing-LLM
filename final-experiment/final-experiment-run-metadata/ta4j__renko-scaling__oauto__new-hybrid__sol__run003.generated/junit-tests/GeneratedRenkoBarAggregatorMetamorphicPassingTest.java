import java.lang.reflect.Field;
import java.math.MathContext;
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

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration DEFAULT_PERIOD = Duration.ofMinutes(1);

    private static void exercise(
            RenkoBarAggregator sourceReceiver, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);
        FollowUp followUp = generateFollowUp(sourceReceiver, sourceBars);
        List<Bar> followUpOutput = followUp.receiver.aggregate(followUp.bars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(
            RenkoBarAggregator sourceReceiver, List<Bar> sourceBars) {
        try {
            Field boxSizeField =
                    RenkoBarAggregator.class.getDeclaredField("boxSize");
            Field reversalAmountField =
                    RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            boxSizeField.setAccessible(true);
            reversalAmountField.setAccessible(true);

            Number sourceBoxSize = (Number) boxSizeField.get(sourceReceiver);
            int reversalAmount = reversalAmountField.getInt(sourceReceiver);

            RenkoBarAggregator followUpReceiver = new RenkoBarAggregator(
                    sourceBoxSize.doubleValue() * 2.0,
                    reversalAmount);

            List<Bar> followUpBars = new ArrayList<>(sourceBars.size());
            for (Bar sourceBar : sourceBars) {
                followUpBars.add(new BaseBar(
                        sourceBar.getTimePeriod(),
                        sourceBar.getBeginTime(),
                        sourceBar.getEndTime(),
                        scale(sourceBar.getOpenPrice()),
                        scale(sourceBar.getHighPrice()),
                        scale(sourceBar.getLowPrice()),
                        scale(sourceBar.getClosePrice()),
                        sourceBar.getVolume(),
                        scale(sourceBar.getAmount()),
                        sourceBar.getTrades()));
            }
            return new FollowUp(followUpReceiver, followUpBars);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException(
                    "Unable to construct scaled Renko follow-up input",
                    failure);
        }
    }

    private static Num scale(Num value) {
        return value == null
                ? null
                : value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(double... closes) {
        return bars(DEFAULT_PERIOD, closes);
    }

    private static List<Bar> bars(Duration period, double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = DecimalNum.valueOf(closes[i]);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    DecimalNum.valueOf(i + 1),
                    DecimalNum.valueOf((i + 1) * 10),
                    i + 1L));
        }
        return result;
    }

    private static List<Bar> customBars(
            Duration period,
            Double[] closes,
            Double[] volumes,
            Double[] amounts,
            long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = number(closes[i]);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    number(volumes[i]),
                    number(amounts[i]),
                    trades[i]));
        }
        return result;
    }

    private static List<Bar> barsWithNullOhl(
            Duration period, double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = DecimalNum.valueOf(closes[i]);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    null,
                    i == 0 ? null : close,
                    null,
                    close,
                    DecimalNum.valueOf(i + 1),
                    DecimalNum.valueOf((i + 1) * 10),
                    i + 1L));
        }
        return result;
    }

    private static List<Bar> barsWithExtremeOhl(
            Duration period, double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = DecimalNum.valueOf(closes[i]);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    DecimalNum.valueOf(1000.0 + i),
                    DecimalNum.valueOf(10000.0 + i),
                    DecimalNum.valueOf(-10000.0 - i),
                    close,
                    DecimalNum.valueOf(i + 1),
                    DecimalNum.valueOf((i + 1) * 10),
                    i + 1L));
        }
        return result;
    }

    private static List<Bar> mixedFactoryBars(double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            MathContext context =
                    i % 2 == 0 ? MathContext.DECIMAL64 : MathContext.DECIMAL128;
            Instant begin = BASE_TIME.plus(DEFAULT_PERIOD.multipliedBy(i));
            Instant end = begin.plus(DEFAULT_PERIOD);
            Num close = DecimalNum.valueOf(closes[i], context);
            result.add(new BaseBar(
                    DEFAULT_PERIOD,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    DecimalNum.valueOf(i + 1, context),
                    DecimalNum.valueOf((i + 1) * 10, context),
                    i + 1L));
        }
        return result;
    }

    private static Num number(Double value) {
        return value == null ? null : DecimalNum.valueOf(value);
    }

    private static final class FollowUp {
        private final RenkoBarAggregator receiver;
        private final List<Bar> bars;

        private FollowUp(
                RenkoBarAggregator receiver, List<Bar> bars) {
            this.receiver = receiver;
            this.bars = bars;
        }
    }

    @Test
    public void EMPTY_SOURCE_LIST_variation1() {
        exercise(new RenkoBarAggregator(0.5), List.<Bar>of());
    }

    @Test
    public void SINGLE_BAR_BASELINE_ONLY_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(0.0));
    }

    @Test
    public void MULTIPLE_FLAT_CLOSES_variation1() {
        exercise(new RenkoBarAggregator(4), bars(10.0, 10.0, 10.0, 10.0));
    }

    @Test
    public void INITIAL_UP_MOVE_JUST_BELOW_BOX_variation1() {
        exercise(new RenkoBarAggregator(1024.0, Integer.MAX_VALUE),
                bars(-4096.0, -3073.0));
    }

    @Test
    public void INITIAL_UP_MOVE_EXACT_BOX_variation1() {
        exercise(new RenkoBarAggregator(0.5), bars(8.0, 8.5));
    }

    @Test
    public void INITIAL_UP_MOVE_BETWEEN_ONE_AND_TWO_BOXES_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(-1.0, 0.5));
    }

    @Test
    public void INITIAL_UP_MULTI_BRICK_JUMP_variation1() {
        exercise(new RenkoBarAggregator(4), bars(-4.0, 8.0));
    }

    @Test
    public void INITIAL_DOWN_MOVE_EXACT_BOX_variation1() {
        exercise(new RenkoBarAggregator(1099511627776.0, 2),
                bars(-1099511627776.0, -2199023255552.0));
    }

    @Test
    public void INITIAL_DOWN_MULTI_BRICK_JUMP_variation1() {
        exercise(new RenkoBarAggregator(0.5), bars(4.0, 2.5));
    }

    @Test
    public void ESTABLISHED_UP_CONTINUATION_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(0.0, 1.0, 2.0));
    }

    @Test
    public void ESTABLISHED_DOWN_CONTINUATION_variation1() {
        exercise(new RenkoBarAggregator(4), bars(8.0, 4.0, 0.0));
    }

    @Test
    public void UP_TREND_PULLBACK_BELOW_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(8.0, 2), bars(-40.0, -32.0, -44.0));
    }

    @Test
    public void UP_TREND_EXACT_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(0.5), bars(10.0, 10.5, 9.5));
    }

    @Test
    public void UP_TREND_REVERSAL_OVERSHOOT_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(0.0, 1.0, -3.0));
    }

    @Test
    public void DOWN_TREND_PULLBACK_BELOW_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(4.0, 2), bars(-2.0, -6.0, 0.0));
    }

    @Test
    public void DOWN_TREND_EXACT_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(16.0, 2), bars(-64.0, -80.0, -48.0));
    }

    @Test
    public void DOWN_TREND_REVERSAL_OVERSHOOT_variation1() {
        exercise(new RenkoBarAggregator(0.5), bars(2.0, 1.5, 3.5));
    }

    @Test
    public void ONE_BOX_REVERSAL_AMOUNT_variation1() {
        exercise(new RenkoBarAggregator(1.0, 1), bars(0.0, 1.0, 0.0));
    }

    @Test
    public void THREE_BOX_REVERSAL_JUST_SHORT_variation1() {
        exercise(new RenkoBarAggregator(4.0, 3), bars(-2.0, 2.0, -8.0));
    }

    @Test
    public void THREE_BOX_EXACT_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(8.0, 3), bars(-40.0, -32.0, -56.0));
    }

    @Test
    public void BIDIRECTIONAL_WHIPSAW_variation1() {
        exercise(new RenkoBarAggregator(0.5), bars(5.0, 5.5, 4.5, 5.5));
    }

    @Test
    public void ALTERNATING_SUB_BOX_MOVEMENTS_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2),
                bars(0.0, 0.5, -0.5, 0.75, -0.75));
    }

    @Test
    public void PENDING_METRICS_ACROSS_NON_EMITTING_BARS_variation1() {
        exercise(new RenkoBarAggregator(4.0), customBars(
                DEFAULT_PERIOD,
                new Double[] { 10.0, 11.0, 13.0, 14.0 },
                new Double[] { 1.0, 2.0, 3.0, 4.0 },
                new Double[] { 10.0, 20.0, 30.0, 40.0 },
                new long[] { 1L, 2L, 3L, 4L }));
    }

    @Test
    public void NULL_VOLUME_CONTRIBUTIONS_variation1() {
        exercise(new RenkoBarAggregator(8.0, 2), customBars(
                DEFAULT_PERIOD,
                new Double[] { -40.0, -36.0, -32.0 },
                new Double[] { null, 3.0, 5.0 },
                new Double[] { 10.0, 20.0, 30.0 },
                new long[] { 1L, 2L, 3L }));
    }

    @Test
    public void NULL_AMOUNT_CONTRIBUTIONS_variation1() {
        exercise(new RenkoBarAggregator(0.5), customBars(
                DEFAULT_PERIOD,
                new Double[] { 5.0, 4.75, 4.5 },
                new Double[] { 1.0, 2.0, 3.0 },
                new Double[] { null, 20.0, null },
                new long[] { 1L, 2L, 3L }));
    }

    @Test
    public void ALL_NULL_VOLUME_AND_AMOUNT_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), customBars(
                DEFAULT_PERIOD,
                new Double[] { -1.0, 0.0 },
                new Double[] { null, null },
                new Double[] { null, null },
                new long[] { 2L, 3L }));
    }

    @Test
    public void ZERO_METRICS_variation1() {
        exercise(new RenkoBarAggregator(4.0), customBars(
                DEFAULT_PERIOD,
                new Double[] { 2.0, -2.0 },
                new Double[] { 0.0, 0.0 },
                new Double[] { 0.0, 0.0 },
                new long[] { 0L, 0L }));
    }

    @Test
    public void MULTI_BRICK_METRIC_SENTINELS_variation1() {
        exercise(new RenkoBarAggregator(16.0, 2), customBars(
                DEFAULT_PERIOD,
                new Double[] { -96.0, -32.0 },
                new Double[] { 2.0, 7.0 },
                new Double[] { 20.0, 70.0 },
                new long[] { 2L, 7L }));
    }

    @Test
    public void SOURCE_END_AFTER_SCHEDULED_END_variation1() {
        exercise(new RenkoBarAggregator(0.5), bars(12.0, 12.5));
    }

    @Test
    public void SOURCE_END_EQUALS_SCHEDULED_END_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(20.0, 21.0, 22.0));
    }

    @Test
    public void SOURCE_END_BEFORE_SYNTHETIC_SCHEDULE_variation1() {
        exercise(new RenkoBarAggregator(4.0), bars(-8.0, 4.0, 8.0));
    }

    @Test
    public void MULTI_BRICK_SYNTHETIC_TIME_SEQUENCE_variation1() {
        exercise(new RenkoBarAggregator(8.0, 2), bars(-64.0, -32.0));
    }

    @Test
    public void DEFAULT_CONSTRUCTOR_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(0.5), bars(4.0, 4.5, 3.5));
    }

    @Test
    public void FRACTIONAL_BOX_SIZE_variation1() {
        exercise(new RenkoBarAggregator(0.5, 2), bars(0.0, 0.5, 1.0));
    }

    @Test
    public void LARGE_EXACT_BOX_SIZE_variation1() {
        double box = 1099511627776.0;
        exercise(new RenkoBarAggregator(box),
                bars(4.0 * box, 3.0 * box, 2.0 * box));
    }

    @Test
    public void MAXIMUM_REVERSAL_AMOUNT_variation1() {
        exercise(new RenkoBarAggregator(1.0, Integer.MAX_VALUE),
                bars(-1000.0, -999.0, -1100.0));
    }

    @Test
    public void UPWARD_ZERO_CROSSING_variation1() {
        exercise(new RenkoBarAggregator(0.5), bars(-0.5, 0.0, 0.5));
    }

    @Test
    public void DOWNWARD_ZERO_CROSSING_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(1.0, 0.0, -1.0));
    }

    @Test
    public void STRICTLY_NEGATIVE_PRICE_REGION_variation1() {
        exercise(new RenkoBarAggregator(4.0), bars(-20.0, -16.0, -12.0));
    }

    @Test
    public void NULL_UNUSED_OHL_FIELDS_variation1() {
        exercise(new RenkoBarAggregator(8.0, 2),
                barsWithNullOhl(DEFAULT_PERIOD, -48.0, -40.0));
    }

    @Test
    public void EXTREME_UNUSED_OHL_RANGES_variation1() {
        exercise(new RenkoBarAggregator(0.5),
                barsWithExtremeOhl(DEFAULT_PERIOD, 10.0, 9.5));
    }

    @Test
    public void REPEATED_EXACT_THRESHOLD_CLOSE_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2),
                bars(0.0, 1.0, 1.0, 2.0));
    }

    @Test
    public void LONG_MIXED_PATH_SEQUENCE_variation1() {
        exercise(new RenkoBarAggregator(4.0, 2),
                bars(0.0, 2.0, 4.0, 8.0, 6.0, 0.0, -4.0, 4.0));
    }

    @Test
    public void MIXED_COMPATIBLE_NUM_FACTORIES_variation1() {
        exercise(new RenkoBarAggregator(8.0, 2),
                mixedFactoryBars(-40.0, -32.0, -24.0));
    }

    @Test
    public void MINIMUM_TIME_PERIOD_variation1() {
        exercise(new RenkoBarAggregator(0.5),
                bars(Duration.ofNanos(1), 2.0, 3.5));
    }
}
