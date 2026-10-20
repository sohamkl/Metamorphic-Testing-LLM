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

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static void verifyRelation(
            RenkoBarAggregator receiver, List<Bar> bars) {
        List<Bar> sourceOutput = receiver.aggregate(bars);
        FollowUp followUp = generateFollowUp(receiver, bars);
        List<Bar> followUpOutput =
                followUp.getReceiver().aggregate(followUp.getBars());
        RenkoBarAggregatorMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(
            RenkoBarAggregator receiver, List<Bar> bars) {
        try {
            Field boxSizeField =
                    RenkoBarAggregator.class.getDeclaredField("boxSize");
            Field reversalAmountField =
                    RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            boxSizeField.setAccessible(true);
            reversalAmountField.setAccessible(true);

            Number boxSize = (Number) boxSizeField.get(receiver);
            int reversalAmount = reversalAmountField.getInt(receiver);

            RenkoBarAggregator scaledReceiver =
                    new RenkoBarAggregator(
                            boxSize.doubleValue() * 2.0,
                            reversalAmount);

            List<Bar> scaledBars = new ArrayList<Bar>(bars.size());
            for (Bar source : bars) {
                scaledBars.add(new BaseBar(
                        source.getTimePeriod(),
                        source.getBeginTime(),
                        source.getEndTime(),
                        scale(source.getOpenPrice()),
                        scale(source.getHighPrice()),
                        scale(source.getLowPrice()),
                        scale(source.getClosePrice()),
                        source.getVolume(),
                        scale(source.getAmount()),
                        source.getTrades()));
            }

            return new FollowUp(scaledReceiver, scaledBars);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException(
                    "Unable to create scaled Renko follow-up input",
                    failure);
        }
    }

    private static Num scale(Num value) {
        if (value == null) {
            return null;
        }
        return value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(Duration period, double... closes) {
        List<Bar> result = new ArrayList<Bar>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            result.add(createBar(
                    period,
                    index,
                    close,
                    close + 0.1,
                    close - 0.1,
                    close,
                    Double.valueOf(index + 1.0),
                    Double.valueOf(close * (index + 1.0)),
                    index + 1L));
        }
        return result;
    }

    private static List<Bar> wideBars(Duration period, double... closes) {
        List<Bar> result = new ArrayList<Bar>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            result.add(createBar(
                    period,
                    index,
                    close + 10.0,
                    close + 5000.0,
                    close - 5000.0,
                    close,
                    Double.valueOf(index + 2.0),
                    Double.valueOf(close * (index + 2.0)),
                    index + 2L));
        }
        return result;
    }

    private static List<Bar> metricBars(
            Duration period,
            double[] closes,
            Double[] volumes,
            Double[] amounts,
            long[] trades) {
        List<Bar> result = new ArrayList<Bar>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            result.add(createBar(
                    period,
                    index,
                    close,
                    close + 0.1,
                    close - 0.1,
                    close,
                    volumes[index],
                    amounts[index],
                    trades[index]));
        }
        return result;
    }

    private static Bar createBar(
            Duration period,
            int index,
            double open,
            double high,
            double low,
            double close,
            Double volume,
            Double amount,
            long trades) {
        Instant begin = BASE_TIME.plus(period.multipliedBy(index));
        Instant end = begin.plus(period);
        return new BaseBar(
                period,
                begin,
                end,
                num(open),
                num(high),
                num(low),
                num(close),
                volume == null ? null : num(volume.doubleValue()),
                amount == null ? null : num(amount.doubleValue()),
                trades);
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private static final class FollowUp {
        private final RenkoBarAggregator receiver;
        private final List<Bar> bars;

        private FollowUp(
                RenkoBarAggregator receiver,
                List<Bar> bars) {
            this.receiver = receiver;
            this.bars = bars;
        }

        private RenkoBarAggregator getReceiver() {
            return receiver;
        }

        private List<Bar> getBars() {
            return bars;
        }
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1_defaultFractional() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.5);
        List<Bar> bars = List.of();
        verifyRelation(receiver, bars);
    }

    @Test
    void EMPTY_SOURCE_LIST_variation2_explicitIntegral() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2, 2);
        List<Bar> bars = List.copyOf(new ArrayList<Bar>());
        verifyRelation(receiver, bars);
    }

    @Test
    void SINGLE_BAR_SEEDS_ONLY_variation1_positiveClose() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000);
        List<Bar> bars = bars(Duration.ofDays(1), 2500.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void SINGLE_BAR_SEEDS_ONLY_variation2_zeroClose() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.25, 1);
        List<Bar> bars = List.copyOf(bars(Duration.ofMillis(100), 0.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void FLAT_MULTI_BAR_SEQUENCE_variation1_nonzeroMetrics() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2);
        List<Bar> bars = bars(Duration.ofMinutes(1), 10.0, 10.0, 10.0, 10.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void FLAT_MULTI_BAR_SEQUENCE_variation2_zeroMetrics() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000, 3);
        List<Bar> bars = metricBars(
                Duration.ofDays(1),
                new double[] { -5000.0, -5000.0, -5000.0 },
                new Double[] { 0.0, 0.0, 0.0 },
                new Double[] { 0.0, 0.0, 0.0 },
                new long[] { 0L, 0L, 0L });
        verifyRelation(receiver, List.copyOf(bars));
    }

    @Test
    void SUB_BOX_MOVEMENT_BOTH_SIDES_variation1_upward() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.5);
        List<Bar> bars = bars(Duration.ofMillis(100), 10.0, 10.4995);
        verifyRelation(receiver, bars);
    }

    @Test
    void SUB_BOX_MOVEMENT_BOTH_SIDES_variation2_downward() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2, 2);
        List<Bar> bars = List.copyOf(wideBars(Duration.ofMinutes(1), 0.0, -1.998));
        verifyRelation(receiver, bars);
    }

    @Test
    void DEFAULT_CONSTRUCTOR_EXACT_UP_variation1_largeBox() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000);
        List<Bar> bars = bars(Duration.ofDays(1), -3000.0, -2000.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void INITIAL_EXACT_UP_BOUNDARY_variation1_reversalOne() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.25, 1);
        List<Bar> bars = List.copyOf(wideBars(Duration.ofMillis(100), 4.0, 4.25));
        verifyRelation(receiver, bars);
    }

    @Test
    void INITIAL_EXACT_UP_BOUNDARY_variation2_reversalThree() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2, 3);
        List<Bar> bars = bars(Duration.ofMinutes(1), 0.0, 2.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void INITIAL_MULTI_BRICK_UP_variation1_twoBricks() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000, 3);
        List<Bar> bars = List.copyOf(wideBars(Duration.ofDays(1), -5000.0, -2750.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void INITIAL_MULTI_BRICK_UP_variation2_fourBricks() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.125);
        List<Bar> bars = bars(Duration.ofMillis(100), 1.0, 1.53125);
        verifyRelation(receiver, bars);
    }

    @Test
    void INITIAL_EXACT_DOWN_BOUNDARY_variation1_reversalOne() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2, 1);
        List<Bar> bars = List.copyOf(wideBars(Duration.ofMinutes(1), 0.0, -2.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void INITIAL_EXACT_DOWN_BOUNDARY_variation2_reversalThree() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000, 3);
        List<Bar> bars = bars(Duration.ofDays(1), -2000.0, -3000.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void INITIAL_MULTI_BRICK_DOWN_variation1_twoBricks() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.25, 1);
        List<Bar> bars = List.copyOf(wideBars(Duration.ofMillis(100), 2.0, 1.4375));
        verifyRelation(receiver, bars);
    }

    @Test
    void INITIAL_MULTI_BRICK_DOWN_variation2_fourBricks() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2);
        List<Bar> bars = bars(Duration.ofMinutes(1), 0.0, -8.5);
        verifyRelation(receiver, bars);
    }

    @Test
    void UP_DIRECTION_SINGLE_CONTINUATION_variation1_largeNegativeRegion() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000, 3);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofDays(1), -5000.0, -4000.0, -3000.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void UP_DIRECTION_MULTI_CONTINUATION_variation1_fractional() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.25);
        List<Bar> bars = bars(Duration.ofMillis(100), 1.0, 1.25, 2.0625);
        verifyRelation(receiver, bars);
    }

    @Test
    void UP_PULLBACK_BELOW_REVERSAL_variation1_immediatelyShort() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2, 2);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofMinutes(1), 10.0, 12.0, 8.002));
        verifyRelation(receiver, bars);
    }

    @Test
    void UP_PULLBACK_BELOW_REVERSAL_variation2_oneBox() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000);
        List<Bar> bars = bars(
                Duration.ofDays(1), -3000.0, -2000.0, -3000.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void UP_EXACT_REVERSAL_THRESHOLD_variation1_reversalOne() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.5, 1);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofMillis(100), 4.0, 4.5, 4.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void UP_EXACT_REVERSAL_THRESHOLD_variation2_reversalTwo() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2);
        List<Bar> bars = bars(Duration.ofMinutes(1), 0.0, 2.0, -2.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void UP_EXACT_REVERSAL_THRESHOLD_variation3_reversalThree() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000, 3);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofDays(1), -5000.0, -4000.0, -7000.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void UP_REVERSAL_BEYOND_THRESHOLD_variation1_threeAndHalfBoxes() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = bars(Duration.ofMillis(100), 4.0, 4.5, 2.75);
        verifyRelation(receiver, bars);
    }

    @Test
    void DOWN_DIRECTION_SINGLE_CONTINUATION_variation1_integral() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2, 2);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofMinutes(1), 0.0, -2.0, -4.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void DOWN_DIRECTION_MULTI_CONTINUATION_variation1_largeBox() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000);
        List<Bar> bars = bars(
                Duration.ofDays(1), -1000.0, -2000.0, -5250.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void DOWN_REBOUND_BELOW_REVERSAL_variation1_immediatelyShort() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofMillis(100), 2.0, 1.5, 2.4995));
        verifyRelation(receiver, bars);
    }

    @Test
    void DOWN_REBOUND_BELOW_REVERSAL_variation2_oneBox() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2);
        List<Bar> bars = bars(Duration.ofMinutes(1), 0.0, -2.0, 0.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void DOWN_EXACT_REVERSAL_THRESHOLD_variation1_reversalOne() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000, 1);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofDays(1), -5000.0, -6000.0, -5000.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void DOWN_EXACT_REVERSAL_THRESHOLD_variation2_reversalTwo() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.25, 2);
        List<Bar> bars = bars(Duration.ofMillis(100), 1.0, 0.75, 1.25);
        verifyRelation(receiver, bars);
    }

    @Test
    void DOWN_EXACT_REVERSAL_THRESHOLD_variation3_reversalThree() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2, 3);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofMinutes(1), 0.0, -2.0, 4.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void DOWN_REVERSAL_BEYOND_THRESHOLD_variation1_threeAndHalfBoxes() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000, 2);
        List<Bar> bars = bars(
                Duration.ofDays(1), -5000.0, -6000.0, -2500.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void PENDING_METRICS_ACROSS_QUIET_BARS_variation1_distinctMetrics() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.5, 1);
        List<Bar> bars = metricBars(
                Duration.ofMillis(100),
                new double[] { 10.0, 10.1, 10.3, 10.5 },
                new Double[] { 1.0, 2.0, 3.0, 4.0 },
                new Double[] { 10.0, 20.0, 30.0, 40.0 },
                new long[] { 1L, 2L, 3L, 4L });
        verifyRelation(receiver, List.copyOf(bars));
    }

    @Test
    void NULL_OPTIONAL_METRICS_variation1_allNull() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2);
        List<Bar> bars = metricBars(
                Duration.ofMinutes(1),
                new double[] { 0.0, 0.5, 2.0 },
                new Double[] { null, null, null },
                new Double[] { null, null, null },
                new long[] { 1L, 2L, 3L });
        verifyRelation(receiver, bars);
    }

    @Test
    void NULL_OPTIONAL_METRICS_variation2_alternatingNulls() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000, 3);
        List<Bar> bars = metricBars(
                Duration.ofDays(1),
                new double[] { -5000.0, -4900.0, -4500.0, -4000.0 },
                new Double[] { null, 2.0, null, 4.0 },
                new Double[] { 10.0, null, 30.0, null },
                new long[] { 1L, 2L, 3L, 4L });
        verifyRelation(receiver, List.copyOf(bars));
    }

    @Test
    void MULTI_BRICK_METRIC_ALLOCATION_variation1_upward() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.25);
        List<Bar> bars = metricBars(
                Duration.ofMillis(100),
                new double[] { 1.0, 2.0 },
                new Double[] { 3.0, 7.0 },
                new Double[] { 30.0, 70.0 },
                new long[] { 3L, 7L });
        verifyRelation(receiver, bars);
    }

    @Test
    void MULTI_BRICK_METRIC_ALLOCATION_variation2_downward() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2, 2);
        List<Bar> bars = metricBars(
                Duration.ofMinutes(1),
                new double[] { 0.0, -8.0 },
                new Double[] { 5.0, 9.0 },
                new Double[] { 50.0, 90.0 },
                new long[] { 5L, 9L });
        verifyRelation(receiver, List.copyOf(bars));
    }

    @Test
    void SOURCE_END_TIME_SELECTED_variation1_quietGap() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000);
        List<Bar> bars = bars(
                Duration.ofDays(1), -5000.0, -4900.0, -4800.0, -4000.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void FUTURE_SCHEDULED_END_TIME_SELECTED_variation1_multiBrickSchedule() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.5, 1);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofMillis(100), 10.0, 12.0, 12.5));
        verifyRelation(receiver, bars);
    }

    @Test
    void ALTERNATING_EXACT_REVERSALS_variation1_bothDirections() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2);
        List<Bar> bars = bars(
                Duration.ofMinutes(1), 0.0, 2.0, -2.0, 2.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void ZERO_AND_NEGATIVE_PRICE_REGIONS_variation1_crossZero() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1, 2);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofDays(1), 0.0, 1.0, -1.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void ZERO_AND_NEGATIVE_PRICE_REGIONS_variation2_allNegative() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.5);
        List<Bar> bars = bars(
                Duration.ofMillis(100), -10.0, -11.0, -10.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void FRACTIONAL_BOX_AND_CLOSES_variation1_halfBox() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofMinutes(1), 0.0, 0.5, 1.5));
        verifyRelation(receiver, bars);
    }

    @Test
    void FRACTIONAL_BOX_AND_CLOSES_variation2_eighthBox() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.125, 3);
        List<Bar> bars = bars(
                Duration.ofDays(1), -1.0, -0.875, -0.625);
        verifyRelation(receiver, bars);
    }

    @Test
    void BOX_NUMBER_REPRESENTATIONS_variation1_integralNumber() {
        RenkoBarAggregator receiver =
                new RenkoBarAggregator(Integer.valueOf(2), 1);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofMillis(100), 1.0, 5.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void BOX_NUMBER_REPRESENTATIONS_variation2_bigDecimalNumber() {
        RenkoBarAggregator receiver =
                new RenkoBarAggregator(new BigDecimal("0.50"));
        List<Bar> bars = bars(Duration.ofMinutes(1), 0.0, -1.0);
        verifyRelation(receiver, bars);
    }

    @Test
    void SOURCE_OHLC_DOES_NOT_DRIVE_THRESHOLDS_variation1_wideRanges() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(1000, 3);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofDays(1), -5000.0, -4900.0, -4000.0));
        verifyRelation(receiver, bars);
    }

    @Test
    void DISTINCT_VALID_SOURCE_PERIODS_variation1_subSecond() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(0.25);
        List<Bar> bars = bars(
                Duration.ofMillis(100), 1.0, 1.5, 0.5);
        verifyRelation(receiver, bars);
    }

    @Test
    void DISTINCT_VALID_SOURCE_PERIODS_variation2_oneDay() {
        RenkoBarAggregator receiver = new RenkoBarAggregator(2, 2);
        List<Bar> bars = List.copyOf(wideBars(
                Duration.ofDays(1), 0.0, -4.0, 4.0));
        verifyRelation(receiver, bars);
    }
}
