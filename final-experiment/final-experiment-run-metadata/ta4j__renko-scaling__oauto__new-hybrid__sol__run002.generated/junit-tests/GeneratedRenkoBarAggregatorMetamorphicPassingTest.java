import java.lang.reflect.Field;
import java.math.BigDecimal;
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

    private static void runCase(RenkoBarAggregator sourceReceiver, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);
        FollowUp followUp = generateFollowUp(sourceReceiver, sourceBars);
        List<Bar> followUpOutput = followUp.receiver.aggregate(followUp.bars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(RenkoBarAggregator sourceReceiver, List<Bar> sourceBars) {
        if (sourceReceiver == null) {
            throw new NullPointerException("sourceReceiver");
        }
        if (sourceBars == null) {
            throw new NullPointerException("sourceBars");
        }

        RenkoConfiguration configuration = readConfiguration(sourceReceiver);
        RenkoBarAggregator followUpReceiver = new RenkoBarAggregator(
                configuration.boxSize.doubleValue() * 2.0,
                configuration.reversalAmount);

        List<Bar> followUpBars = new ArrayList<Bar>(sourceBars.size());
        for (Bar sourceBar : sourceBars) {
            if (sourceBar == null) {
                throw new NullPointerException("source bars must not contain null");
            }
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
    }

    private static RenkoConfiguration readConfiguration(RenkoBarAggregator sourceReceiver) {
        try {
            Field boxSizeField = RenkoBarAggregator.class.getDeclaredField("boxSize");
            Field reversalAmountField = RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            boxSizeField.setAccessible(true);
            reversalAmountField.setAccessible(true);
            Number boxSize = (Number) boxSizeField.get(sourceReceiver);
            int reversalAmount = reversalAmountField.getInt(sourceReceiver);
            return new RenkoConfiguration(boxSize, reversalAmount);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException("Unable to read Renko configuration", failure);
        }
    }

    private static Num scale(Num value) {
        if (value == null) {
            return null;
        }
        return value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(Duration period, double... closes) {
        return buildBars(period, closes, MetricMode.STANDARD, false);
    }

    private static List<Bar> barsWithNullMetrics(Duration period, double... closes) {
        return buildBars(period, closes, MetricMode.NULL_MIXED, false);
    }

    private static List<Bar> barsZeroMetrics(Duration period, double... closes) {
        return buildBars(period, closes, MetricMode.ZERO, false);
    }

    private static List<Bar> barsWithNullOhlc(Duration period, double... closes) {
        return buildBars(period, closes, MetricMode.STANDARD, true);
    }

    private static List<Bar> buildBars(
            Duration period,
            double[] closes,
            MetricMode metricMode,
            boolean nullableOhlc) {

        List<Bar> result = new ArrayList<Bar>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            Instant endTime = BASE_TIME.plus(period.multipliedBy(index + 1L));
            Instant beginTime = endTime.minus(period);
            Num close = num(closes[index]);

            Num open = close;
            Num high = close.plus(num(0.125));
            Num low = close.minus(num(0.125));

            if (nullableOhlc) {
                if (index % 2 == 0) {
                    open = null;
                }
                if (index % 3 == 0) {
                    high = null;
                }
                if (index % 2 != 0) {
                    low = null;
                }
            }

            Num volume;
            Num amount;
            long trades;

            if (metricMode == MetricMode.ZERO) {
                volume = num(0.0);
                amount = num(0.0);
                trades = 0L;
            } else if (metricMode == MetricMode.NULL_MIXED) {
                volume = index % 2 == 0 ? null : num(index + 1.0);
                amount = index % 3 == 0 ? null : num((index + 1.0) * 10.0);
                trades = index + 1L;
            } else {
                volume = num(index + 1.0);
                amount = num((index + 1.0) * 10.0);
                trades = index + 1L;
            }

            result.add(new BaseBar(
                    period,
                    beginTime,
                    endTime,
                    open,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    trades));
        }
        return result;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private enum MetricMode {
        STANDARD,
        NULL_MIXED,
        ZERO
    }

    private static final class FollowUp {
        private final RenkoBarAggregator receiver;
        private final List<Bar> bars;

        private FollowUp(RenkoBarAggregator receiver, List<Bar> bars) {
            this.receiver = receiver;
            this.bars = bars;
        }
    }

    private static final class RenkoConfiguration {
        private final Number boxSize;
        private final int reversalAmount;

        private RenkoConfiguration(Number boxSize, int reversalAmount) {
            this.boxSize = boxSize;
            this.reversalAmount = reversalAmount;
        }
    }

    @Test
    public void EMPTY_LIST_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.5")), List.<Bar>of());
    }

    @Test
    public void SINGLE_BAR_ANCHOR_ONLY_variation1() {
        runCase(new RenkoBarAggregator(1, 2), bars(Duration.ofSeconds(1), 0.0));
    }

    @Test
    public void MULTI_BAR_FLAT_CLOSES_variation1() {
        runCase(new RenkoBarAggregator(4), barsWithNullMetrics(Duration.ofDays(1), 20.0, 20.0, 20.0));
    }

    @Test
    public void INITIAL_UP_MOVE_JUST_BELOW_BOX_variation1() {
        runCase(new RenkoBarAggregator(1_000_000.0, 5),
                bars(Duration.ofNanos(25), -2_000_000.0, -1_000_001.0));
    }

    @Test
    public void INITIAL_UP_MOVE_EXACTLY_ONE_BOX_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.5")),
                bars(Duration.ofMinutes(1), 0.0, 0.5));
    }

    @Test
    public void INITIAL_UP_MOVE_EXACTLY_ONE_BOX_variation2() {
        runCase(new RenkoBarAggregator(1, 2), bars(Duration.ofDays(1), 10.0, 11.0));
    }

    @Test
    public void INITIAL_UP_MOVE_BETWEEN_ONE_AND_TWO_BOXES_variation1() {
        runCase(new RenkoBarAggregator(4), bars(Duration.ofNanos(100), -20.0, -14.0));
    }

    @Test
    public void INITIAL_DOWN_MOVE_EXACTLY_ONE_BOX_variation1() {
        runCase(new RenkoBarAggregator(100_000.0, 5),
                barsWithNullMetrics(Duration.ofSeconds(10), 1_000_000.0, 900_000.0));
    }

    @Test
    public void INITIAL_DOWN_MOVE_EXACTLY_ONE_BOX_variation2() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.25")),
                bars(Duration.ofDays(1), 0.125, -0.125));
    }

    @Test
    public void INITIAL_MULTI_BRICK_UP_BURST_variation1() {
        runCase(new RenkoBarAggregator(1, 2),
                barsZeroMetrics(Duration.ofNanos(20), -5.0, -3.0));
    }

    @Test
    public void INITIAL_MULTI_BRICK_UP_BURST_variation2() {
        runCase(new RenkoBarAggregator(3), bars(Duration.ofMinutes(1), 0.0, 12.0));
    }

    @Test
    public void INITIAL_MULTI_BRICK_DOWN_BURST_variation1() {
        runCase(new RenkoBarAggregator(100_000.0, 5),
                barsWithNullMetrics(Duration.ofDays(1), 1_000_000.0, 700_000.0));
    }

    @Test
    public void INITIAL_MULTI_BRICK_DOWN_BURST_variation2() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.25")),
                bars(Duration.ofNanos(10), -1.0, -2.0));
    }

    @Test
    public void GRADUAL_UP_ONE_BRICK_PER_BAR_variation1() {
        runCase(new RenkoBarAggregator(1, 2),
                barsZeroMetrics(Duration.ofSeconds(30), 0.0, 1.0, 2.0, 3.0, 4.0));
    }

    @Test
    public void GRADUAL_DOWN_ONE_BRICK_PER_BAR_variation1() {
        runCase(new RenkoBarAggregator(3),
                barsWithNullMetrics(Duration.ofDays(1), 20.0, 17.0, 14.0, 11.0, 8.0));
    }

    @Test
    public void PENDING_METRICS_ACROSS_NON_EMITTING_BARS_variation1() {
        runCase(new RenkoBarAggregator(1_000_000.0, 5),
                barsWithNullMetrics(Duration.ofNanos(50),
                        -5_000_000.0, -4_800_000.0, -5_300_000.0, -4_600_000.0, -4_000_000.0));
    }

    @Test
    public void NULL_VOLUME_AND_AMOUNT_CONTRIBUTIONS_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.5")),
                barsWithNullMetrics(Duration.ofMinutes(1), 0.0, 0.2, 0.5));
    }

    @Test
    public void ZERO_METRICS_ON_SINGLE_EMISSION_variation1() {
        runCase(new RenkoBarAggregator(1, 2),
                barsZeroMetrics(Duration.ofDays(1), 10.0, 11.0));
    }

    @Test
    public void UP_TREND_CONTINUATION_variation1() {
        runCase(new RenkoBarAggregator(4),
                barsWithNullMetrics(Duration.ofNanos(100), -20.0, -16.0, -12.0));
    }

    @Test
    public void UP_TREND_ONE_BOX_PULLBACK_BELOW_REVERSAL_variation1() {
        runCase(new RenkoBarAggregator(100_000.0, 5),
                barsWithNullMetrics(Duration.ofMinutes(1), 0.0, 100_000.0, 200_000.0, 100_000.0));
    }

    @Test
    public void UP_REVERSAL_JUST_SHORT_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.5")),
                bars(Duration.ofDays(1), 10.0, 10.5, 9.6));
    }

    @Test
    public void UP_TO_DOWN_REVERSAL_EXACT_THRESHOLD_variation1() {
        runCase(new RenkoBarAggregator(1, 2),
                barsZeroMetrics(Duration.ofNanos(30), -5.0, -4.0, -6.0));
    }

    @Test
    public void UP_TO_DOWN_REVERSAL_EXACT_THRESHOLD_variation2() {
        runCase(new RenkoBarAggregator(3, 4),
                barsWithNullMetrics(Duration.ofMinutes(1), 0.0, 3.0, -9.0));
    }

    @Test
    public void UP_TO_DOWN_REVERSAL_OVERSHOOT_variation1() {
        runCase(new RenkoBarAggregator(100_000.0, 4),
                barsWithNullMetrics(Duration.ofDays(1), 1_000_000.0, 1_100_000.0, 625_000.0));
    }

    @Test
    public void DOWN_TREND_CONTINUATION_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.25")),
                bars(Duration.ofNanos(15), -1.0, -1.25, -1.5, -1.75));
    }

    @Test
    public void DOWN_TREND_ONE_BOX_BOUNCE_BELOW_REVERSAL_variation1() {
        runCase(new RenkoBarAggregator(1, 2),
                barsZeroMetrics(Duration.ofSeconds(1), 0.0, -1.0, 0.0));
    }

    @Test
    public void DOWN_REVERSAL_JUST_SHORT_variation1() {
        runCase(new RenkoBarAggregator(3, 3),
                barsWithNullMetrics(Duration.ofDays(1), 20.0, 17.0, 24.5));
    }

    @Test
    public void DOWN_TO_UP_REVERSAL_EXACT_THRESHOLD_variation1() {
        runCase(new RenkoBarAggregator(100_000.0, 4),
                barsWithNullMetrics(Duration.ofNanos(50), -1_000_000.0, -1_100_000.0, -700_000.0));
    }

    @Test
    public void DOWN_TO_UP_REVERSAL_EXACT_THRESHOLD_variation2() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.5")),
                bars(Duration.ofMinutes(1), 0.0, -0.5, 0.5));
    }

    @Test
    public void DOWN_TO_UP_REVERSAL_OVERSHOOT_variation1() {
        runCase(new RenkoBarAggregator(1, 2),
                barsZeroMetrics(Duration.ofDays(1), 10.0, 9.0, 11.75));
    }

    @Test
    public void ONE_BOX_REVERSAL_UP_THEN_DOWN_variation1() {
        runCase(new RenkoBarAggregator(3, 1),
                barsWithNullMetrics(Duration.ofNanos(20), -10.0, -7.0, -10.0));
    }

    @Test
    public void ONE_BOX_REVERSAL_DOWN_THEN_UP_variation1() {
        runCase(new RenkoBarAggregator(100_000.0, 1),
                barsWithNullMetrics(Duration.ofMinutes(1), 0.0, -100_000.0, 0.0));
    }

    @Test
    public void THREE_BOX_REVERSAL_SHORT_BY_ONE_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.25"), 3),
                bars(Duration.ofDays(1), 10.0, 10.25, 9.75));
    }

    @Test
    public void THREE_BOX_REVERSAL_EXACT_variation1() {
        runCase(new RenkoBarAggregator(1, 3),
                barsZeroMetrics(Duration.ofNanos(40), -5.0, -4.0, -7.0));
    }

    @Test
    public void DEFAULT_CONSTRUCTOR_TWO_BOX_REVERSAL_variation1() {
        runCase(new RenkoBarAggregator(3),
                barsWithNullMetrics(Duration.ofMinutes(1), 0.0, 3.0, -3.0));
    }

    @Test
    public void DELAYED_FIRST_EMISSION_USES_SOURCE_END_variation1() {
        runCase(new RenkoBarAggregator(100_000.0, 5),
                barsWithNullMetrics(Duration.ofDays(1),
                        1_000_000.0, 1_020_000.0, 980_000.0, 1_050_000.0, 1_100_000.0));
    }

    @Test
    public void UP_BURST_SCHEDULES_FUTURE_BRICK_TIME_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.25")),
                bars(Duration.ofNanos(10), -2.0, -1.25, -1.0));
    }

    @Test
    public void DOWN_BURST_SCHEDULES_FUTURE_BRICK_TIME_variation1() {
        runCase(new RenkoBarAggregator(1, 2),
                barsZeroMetrics(Duration.ofSeconds(1), 0.0, -3.0, -4.0));
    }

    @Test
    public void ALTERNATING_SUB_REVERSAL_MOVEMENTS_variation1() {
        runCase(new RenkoBarAggregator(3, 3),
                barsWithNullMetrics(Duration.ofDays(1), 20.0, 17.0, 18.0, 16.0, 18.5, 16.5));
    }

    @Test
    public void COMPLETE_UP_DOWN_UP_CYCLE_variation1() {
        runCase(new RenkoBarAggregator(100_000.0, 4),
                barsWithNullMetrics(Duration.ofNanos(60),
                        -1_000_000.0, -900_000.0, -1_300_000.0, -900_000.0));
    }

    @Test
    public void NEGATIVE_PRICE_REGION_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.25")),
                bars(Duration.ofMinutes(1), -5.0, -4.75, -4.5));
    }

    @Test
    public void MOVEMENT_CROSSES_ZERO_variation1() {
        runCase(new RenkoBarAggregator(1, 2),
                barsZeroMetrics(Duration.ofDays(1), 0.5, -1.5));
    }

    @Test
    public void FRACTIONAL_BOX_EXACT_THRESHOLDS_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.125")),
                barsWithNullMetrics(Duration.ofNanos(5), -1.0, -0.875));
    }

    @Test
    public void LARGE_FINITE_BOX_AND_PRICES_variation1() {
        runCase(new RenkoBarAggregator(1_000_000_000.0, 5),
                barsWithNullMetrics(Duration.ofMinutes(1),
                        1_000_000_000_000.0, 1_001_000_000_000.0));
    }

    @Test
    public void LARGE_REVERSAL_AMOUNT_WITH_COUNTERTREND_MOVE_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.5"), 6),
                bars(Duration.ofDays(1), 10.0, 9.5, 12.0));
    }

    @Test
    public void TRAILING_PENDING_METRICS_WITH_NO_FINAL_BRICK_variation1() {
        runCase(new RenkoBarAggregator(1, 2),
                bars(Duration.ofNanos(10), -5.0, -4.0, -3.6, -4.2));
    }

    @Test
    public void MULTI_BRICK_METRIC_DISTRIBUTION_variation1() {
        runCase(new RenkoBarAggregator(3), bars(Duration.ofMinutes(1), 0.0, 9.0));
    }

    @Test
    public void NULL_UNUSED_OHLC_FIELDS_variation1() {
        runCase(new RenkoBarAggregator(100_000.0, 5),
                barsWithNullOhlc(Duration.ofDays(1), 1_000_000.0, 1_100_000.0));
    }

    @Test
    public void FINE_GRAINED_POSITIVE_PERIOD_variation1() {
        runCase(new RenkoBarAggregator(new BigDecimal("0.25")),
                bars(Duration.ofNanos(1), -2.0, -1.25, -1.0));
    }

    @Test
    public void NON_INTEGRAL_MULTI_BOX_MOVEMENT_variation1() {
        runCase(new RenkoBarAggregator(1, 2),
                barsZeroMetrics(Duration.ofSeconds(30), 0.0, 3.75));
    }
}
