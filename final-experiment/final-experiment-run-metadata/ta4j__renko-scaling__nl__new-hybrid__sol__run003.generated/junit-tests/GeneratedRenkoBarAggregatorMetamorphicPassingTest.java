import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration SHORT_PERIOD = Duration.ofMinutes(1);
    private static final Duration LONG_PERIOD = Duration.ofHours(1);

    private static final class SourceCase {
        private final BigDecimal boxSize;
        private final int reversalAmount;
        private final boolean oneArgumentConstructor;
        private final List<Bar> bars;

        private SourceCase(BigDecimal boxSize, int reversalAmount, boolean oneArgumentConstructor, List<Bar> bars) {
            this.boxSize = boxSize;
            this.reversalAmount = reversalAmount;
            this.oneArgumentConstructor = oneArgumentConstructor;
            this.bars = bars;
        }

        private BigDecimal boxSize() {
            return boxSize;
        }

        private int reversalAmount() {
            return reversalAmount;
        }

        private boolean oneArgumentConstructor() {
            return oneArgumentConstructor;
        }

        private List<Bar> bars() {
            return bars;
        }

        private RenkoBarAggregator receiver() {
            if (oneArgumentConstructor) {
                return new RenkoBarAggregator(boxSize);
            }
            return new RenkoBarAggregator(boxSize, reversalAmount);
        }
    }

    private static SourceCase source(String boxSize, int reversalAmount, boolean oneArgumentConstructor,
            Duration period, double... closes) {
        Double[] volumes = new Double[closes.length];
        double[] amounts = new double[closes.length];
        long[] trades = new long[closes.length];

        for (int i = 0; i < closes.length; i++) {
            volumes[i] = Double.valueOf(i + 1.0);
            amounts[i] = (i + 1) * 2.5;
            trades[i] = i + 1L;
        }

        return sourceWithMetrics(boxSize, reversalAmount, oneArgumentConstructor, period,
                closes, volumes, amounts, trades);
    }

    private static SourceCase sourceWithMetrics(String boxSize, int reversalAmount,
            boolean oneArgumentConstructor, Duration period, double[] closes, Double[] volumes,
            double[] amounts, long[] trades) {
        if (closes.length != volumes.length || closes.length != amounts.length
                || closes.length != trades.length) {
            throw new IllegalArgumentException("Metric arrays must have equal lengths");
        }

        double[] opens = new double[closes.length];
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];

        for (int i = 0; i < closes.length; i++) {
            opens[i] = closes[i] + (i % 2 == 0 ? -0.25 : 0.25);
            highs[i] = Math.max(opens[i], closes[i]) + 5.0 + i;
            lows[i] = Math.min(opens[i], closes[i]) - 5.0 - i;
        }

        return new SourceCase(new BigDecimal(boxSize), reversalAmount, oneArgumentConstructor,
                customBars(period, opens, highs, lows, closes, volumes, amounts, trades));
    }

    private static List<Bar> customBars(Duration period, double[] opens, double[] highs,
            double[] lows, double[] closes, Double[] volumes, double[] amounts, long[] trades) {
        int size = closes.length;
        if (opens.length != size || highs.length != size || lows.length != size
                || volumes.length != size || amounts.length != size || trades.length != size) {
            throw new IllegalArgumentException("Bar arrays must have equal lengths");
        }

        List<Bar> bars = new ArrayList<Bar>(size);
        for (int i = 0; i < size; i++) {
            Instant begin = BASE_TIME.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num volume = volumes[i] == null ? null : num(volumes[i].doubleValue());

            bars.add(new BaseBar(
                    period,
                    begin,
                    end,
                    num(opens[i]),
                    num(highs[i]),
                    num(lows[i]),
                    num(closes[i]),
                    volume,
                    num(amounts[i]),
                    trades[i]));
        }
        return bars;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(BigDecimal.valueOf(value));
    }

    private static void assertMetamorphicRelationFor(SourceCase source) {
        SourceCase followUp = generateFollowUp(source);
        List<Bar> sourceOutput = source.receiver().aggregate(source.bars());
        List<Bar> followUpOutput = followUp.receiver().aggregate(followUp.bars());
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static SourceCase generateFollowUp(SourceCase source) {
        List<Bar> transformedBars = new ArrayList<Bar>(source.bars().size());

        for (Bar bar : source.bars()) {
            Num two = bar.numFactory().numOf(2);
            transformedBars.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    scale(bar.getOpenPrice(), two),
                    scale(bar.getHighPrice(), two),
                    scale(bar.getLowPrice(), two),
                    scale(bar.getClosePrice(), two),
                    bar.getVolume(),
                    scale(bar.getAmount(), two),
                    bar.getTrades()));
        }

        return new SourceCase(
                source.boxSize().multiply(BigDecimal.valueOf(2)),
                source.reversalAmount(),
                source.oneArgumentConstructor(),
                transformedBars);
    }

    private static Num scale(Num value, Num factor) {
        return value.multipliedBy(factor);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar sourceBrick = sourceOutput.get(i);
            Bar followUpBrick = followUpOutput.get(i);
            Num two = sourceBrick.numFactory().numOf(2);

            assertEquals(direction(sourceBrick), direction(followUpBrick));
            assertEquals(sourceBrick.getBeginTime(), followUpBrick.getBeginTime());
            assertEquals(sourceBrick.getEndTime(), followUpBrick.getEndTime());
            assertEquals(sourceBrick.getTimePeriod(), followUpBrick.getTimePeriod());
            assertNumEqual(sourceBrick.getVolume(), followUpBrick.getVolume());
            assertEquals(sourceBrick.getTrades(), followUpBrick.getTrades());

            assertNumEqual(scale(sourceBrick.getOpenPrice(), two), followUpBrick.getOpenPrice());
            assertNumEqual(scale(sourceBrick.getHighPrice(), two), followUpBrick.getHighPrice());
            assertNumEqual(scale(sourceBrick.getLowPrice(), two), followUpBrick.getLowPrice());
            assertNumEqual(scale(sourceBrick.getClosePrice(), two), followUpBrick.getClosePrice());
            assertNumEqual(scale(sourceBrick.getAmount(), two), followUpBrick.getAmount());
        }
    }

    private static int direction(Bar bar) {
        if (bar.getClosePrice().isGreaterThan(bar.getOpenPrice())) {
            return 1;
        }
        if (bar.getClosePrice().isLessThan(bar.getOpenPrice())) {
            return -1;
        }
        return 0;
    }

    private static void assertNumEqual(Num expected, Num actual) {
        assertTrue(expected.isEqual(actual),
                "Expected numerically equal values but got expected=" + expected + ", actual=" + actual);
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        assertMetamorphicRelationFor(source("2", 2, true, SHORT_PERIOD));
    }

    @Test
    void SINGLE_BAR_ESTABLISHES_ANCHOR_variation1() {
        assertMetamorphicRelationFor(source("2", 1, false, LONG_PERIOD, 0.0));
    }

    @Test
    void SINGLE_BAR_ESTABLISHES_ANCHOR_variation2() {
        assertMetamorphicRelationFor(sourceWithMetrics("1.5", 2, false, SHORT_PERIOD,
                new double[] { -4.5 }, new Double[] { null }, new double[] { 3.25 }, new long[] { 7 }));
    }

    @Test
    void MULTI_BAR_FLAT_CLOSES_variation1() {
        assertMetamorphicRelationFor(source("3", 4, false, LONG_PERIOD, 12.0, 12.0, 12.0, 12.0));
    }

    @Test
    void STRICTLY_SUB_BOX_UPWARD_MOVE_variation1() {
        assertMetamorphicRelationFor(source("2", 2, true, SHORT_PERIOD, -0.5, 0.0, 1.25));
    }

    @Test
    void STRICTLY_SUB_BOX_DOWNWARD_MOVE_variation1() {
        assertMetamorphicRelationFor(source("4", 1, false, LONG_PERIOD, -10.0, -11.0, -13.5));
    }

    @Test
    void EXACT_INITIAL_UP_THRESHOLD_variation1_defaultConstructor() {
        assertMetamorphicRelationFor(source("2", 2, true, SHORT_PERIOD, 10.0, 12.0));
    }

    @Test
    void EXACT_INITIAL_UP_THRESHOLD_variation2_explicitReversalTwo() {
        assertMetamorphicRelationFor(source("2.5", 2, false, LONG_PERIOD, -1.0, 0.0, 1.5));
    }

    @Test
    void EXACT_INITIAL_DOWN_THRESHOLD_variation1_defaultConstructor() {
        assertMetamorphicRelationFor(source("3", 2, true, SHORT_PERIOD, -4.0, -7.0));
    }

    @Test
    void EXACT_INITIAL_DOWN_THRESHOLD_variation2_explicitConfiguration() {
        assertMetamorphicRelationFor(source("1.25", 1, false, LONG_PERIOD, 8.0, 7.5, 6.75));
    }

    @Test
    void JUST_BELOW_INITIAL_UP_THRESHOLD_variation1() {
        assertMetamorphicRelationFor(source("2", 2, false, SHORT_PERIOD, 0.0, 1.5, 1.75));
    }

    @Test
    void JUST_ABOVE_INITIAL_DOWN_THRESHOLD_variation1() {
        assertMetamorphicRelationFor(source("4", 4, false, LONG_PERIOD, -5.0, -8.5, -8.75, -7.0));
    }

    @Test
    void MULTIPLE_INITIAL_UP_BRICKS_variation1() {
        assertMetamorphicRelationFor(source("2", 2, true, SHORT_PERIOD, 10.0, 16.0));
    }

    @Test
    void MULTIPLE_INITIAL_UP_BRICKS_variation2() {
        assertMetamorphicRelationFor(source("1.5", 1, false, LONG_PERIOD, -2.0, -1.5, 4.0));
    }

    @Test
    void MULTIPLE_INITIAL_DOWN_BRICKS_variation1() {
        assertMetamorphicRelationFor(sourceWithMetrics("2", 2, false, SHORT_PERIOD,
                new double[] { -2.0, -8.0 }, new Double[] { null, 5.0 },
                new double[] { 2.0, 7.0 }, new long[] { 3, 4 }));
    }

    @Test
    void MULTIPLE_INITIAL_DOWN_BRICKS_variation2() {
        assertMetamorphicRelationFor(source("1", 4, false, LONG_PERIOD, 10.0, 10.25, 7.0));
    }

    @Test
    void FRACTIONAL_BOX_OVERSHOOT_variation1_upward() {
        assertMetamorphicRelationFor(source("2", 2, true, SHORT_PERIOD, -1.0, 4.0));
    }

    @Test
    void FRACTIONAL_BOX_OVERSHOOT_variation2_downward() {
        assertMetamorphicRelationFor(source("2", 1, false, LONG_PERIOD, -3.0, -8.5));
    }

    @Test
    void UP_DIRECTION_CONTINUATION_variation1() {
        assertMetamorphicRelationFor(source("2", 2, false, SHORT_PERIOD, 10.0, 12.0, 16.0));
    }

    @Test
    void DOWN_DIRECTION_CONTINUATION_variation1() {
        assertMetamorphicRelationFor(source("2.5", 4, false, LONG_PERIOD, 1.0, -1.5, -6.5));
    }

    @Test
    void UP_TREND_MOVE_BELOW_REVERSAL_variation1() {
        assertMetamorphicRelationFor(source("2", 2, true, SHORT_PERIOD, -4.0, -2.0, -5.5));
    }

    @Test
    void DOWN_TREND_MOVE_BELOW_REVERSAL_variation1() {
        assertMetamorphicRelationFor(source("3", 2, false, LONG_PERIOD, 10.0, 7.0, 12.5));
    }

    @Test
    void EXACT_UP_TO_DOWN_REVERSAL_variation1() {
        assertMetamorphicRelationFor(source("2", 2, false, SHORT_PERIOD, 0.0, 2.0, -2.0));
    }

    @Test
    void EXACT_UP_TO_DOWN_REVERSAL_variation2() {
        assertMetamorphicRelationFor(source("1.5", 3, false, LONG_PERIOD, -3.0, -1.5, -6.0));
    }

    @Test
    void EXACT_DOWN_TO_UP_REVERSAL_variation1() {
        assertMetamorphicRelationFor(source("2", 2, true, SHORT_PERIOD, 5.0, 3.0, 7.0));
    }

    @Test
    void EXACT_DOWN_TO_UP_REVERSAL_variation2() {
        assertMetamorphicRelationFor(source("1.25", 1, false, LONG_PERIOD, -2.0, -3.25, -2.0));
    }

    @Test
    void UP_TO_DOWN_REVERSAL_OVERSHOOT_variation1() {
        assertMetamorphicRelationFor(source("2", 2, false, SHORT_PERIOD, -2.0, 0.0, -6.5));
    }

    @Test
    void DOWN_TO_UP_REVERSAL_OVERSHOOT_variation1() {
        assertMetamorphicRelationFor(source("1.5", 3, false, LONG_PERIOD, 4.0, 2.5, 9.25));
    }

    @Test
    void ONE_BOX_UP_TO_DOWN_REVERSAL_variation1() {
        assertMetamorphicRelationFor(source("2", 1, false, SHORT_PERIOD, 0.0, 2.0, 0.0));
    }

    @Test
    void ONE_BOX_DOWN_TO_UP_REVERSAL_variation1() {
        assertMetamorphicRelationFor(source("2.5", 1, false, LONG_PERIOD, -2.5, -5.0, -2.5));
    }

    @Test
    void LARGE_REVERSAL_AMOUNT_NOT_REACHED_variation1() {
        assertMetamorphicRelationFor(source("2", 4, false, SHORT_PERIOD, 10.0, 12.0, 6.0));
    }

    @Test
    void LARGE_REVERSAL_AMOUNT_EXACTLY_REACHED_variation1() {
        assertMetamorphicRelationFor(source("1.5", 4, false, LONG_PERIOD, -1.0, 0.5, -5.5));
    }

    @Test
    void ALTERNATING_EXACT_REVERSALS_variation1() {
        assertMetamorphicRelationFor(source("2", 2, true, SHORT_PERIOD, 0.0, 2.0, -2.0, 2.0));
    }

    @Test
    void ALTERNATING_EXACT_REVERSALS_variation2() {
        assertMetamorphicRelationFor(source("1.25", 2, false, LONG_PERIOD, 5.0, 3.75, 6.25, 3.75));
    }

    @Test
    void OSCILLATION_INSIDE_ACTIVE_DEAD_ZONE_variation1() {
        assertMetamorphicRelationFor(sourceWithMetrics("2", 2, false, SHORT_PERIOD,
                new double[] { -1.0, 1.0, 0.25, -2.5, 0.75 },
                new Double[] { null, 3.0, null, 4.0, 2.0 },
                new double[] { 1.0, 2.0, 3.0, 4.0, 5.0 },
                new long[] { 1, 2, 3, 4, 5 }));
    }

    @Test
    void PENDING_METRICS_ACCUMULATE_BEFORE_FIRST_EMISSION_variation1() {
        assertMetamorphicRelationFor(sourceWithMetrics("2", 4, false, LONG_PERIOD,
                new double[] { -10.0, -10.5, -11.0, -12.0 },
                new Double[] { 2.0, 3.0, 4.0, 5.0 },
                new double[] { 1.0, 2.0, 3.0, 4.0 },
                new long[] { 1, 2, 3, 4 }));
    }

    @Test
    void PENDING_METRICS_ACCUMULATE_BEFORE_FIRST_EMISSION_variation2() {
        assertMetamorphicRelationFor(sourceWithMetrics("2", 2, true, SHORT_PERIOD,
                new double[] { 10.0, 10.5, 11.0, 12.0 },
                new Double[] { 1.0, 2.0, 3.0, 4.0 },
                new double[] { 2.0, 3.0, 5.0, 7.0 },
                new long[] { 2, 3, 5, 7 }));
    }

    @Test
    void FIRST_BAR_METRICS_FLOW_TO_LATER_BRICK_variation1() {
        assertMetamorphicRelationFor(sourceWithMetrics("2", 1, false, LONG_PERIOD,
                new double[] { 0.0, 0.5, 2.0 },
                new Double[] { 9.0, 0.0, 1.0 },
                new double[] { 11.0, 0.0, 2.0 },
                new long[] { 13, 0, 1 }));
    }

    @Test
    void MULTI_BRICK_METRIC_ZERO_SENTINELS_variation1() {
        assertMetamorphicRelationFor(sourceWithMetrics("2", 2, false, SHORT_PERIOD,
                new double[] { -2.0, -8.0 },
                new Double[] { null, 6.0 },
                new double[] { 4.0, 8.0 },
                new long[] { 5, 9 }));
    }

    @Test
    void MULTI_BRICK_METRIC_ZERO_SENTINELS_variation2() {
        assertMetamorphicRelationFor(sourceWithMetrics("1.5", 4, false, LONG_PERIOD,
                new double[] { 4.0, 4.25, 4.5, 10.0 },
                new Double[] { 2.0, 3.0, 4.0, 5.0 },
                new double[] { 1.0, 2.0, 4.0, 8.0 },
                new long[] { 1, 2, 4, 8 }));
    }

    @Test
    void NULL_VOLUMES_ARE_IGNORED_variation1() {
        assertMetamorphicRelationFor(sourceWithMetrics("2", 2, true, SHORT_PERIOD,
                new double[] { -1.0, -0.5, 1.0 },
                new Double[] { null, 3.0, null },
                new double[] { 2.0, 4.0, 6.0 },
                new long[] { 1, 2, 3 }));
    }

    @Test
    void ZERO_METRICS_ON_EMITTING_BAR_variation1() {
        assertMetamorphicRelationFor(sourceWithMetrics("2", 1, false, LONG_PERIOD,
                new double[] { -4.0, -6.0 },
                new Double[] { 0.0, 0.0 },
                new double[] { 0.0, 0.0 },
                new long[] { 0, 0 }));
    }

    @Test
    void TRAILING_PENDING_METRICS_DISCARDED_variation1() {
        assertMetamorphicRelationFor(sourceWithMetrics("2", 2, false, SHORT_PERIOD,
                new double[] { 10.0, 12.0, 12.5, 11.0 },
                new Double[] { 1.0, 2.0, null, 20.0 },
                new double[] { 1.0, 2.0, 30.0, 40.0 },
                new long[] { 1, 2, 30, 40 }));
    }

    @Test
    void DELAYED_EMISSION_USES_SOURCE_END_TIME_variation1() {
        assertMetamorphicRelationFor(source("2", 4, false, LONG_PERIOD, -1.0, -0.5, 0.0, 1.0));
    }

    @Test
    void MULTI_BRICK_SYNTHETIC_END_TIMES_variation1() {
        assertMetamorphicRelationFor(source("2", 2, true, SHORT_PERIOD, -4.0, -10.5));
    }

    @Test
    void MULTI_BRICK_SYNTHETIC_END_TIMES_variation2() {
        assertMetamorphicRelationFor(source("1.5", 1, false, LONG_PERIOD, 2.0, 7.75));
    }

    @Test
    void SYNTHETIC_SCHEDULE_THEN_SOURCE_CATCH_UP_variation1() {
        assertMetamorphicRelationFor(sourceWithMetrics("1", 2, false, SHORT_PERIOD,
                new double[] { 0.0, 3.0, 4.0, 4.0, 4.0, 5.0 },
                new Double[] { null, 2.0, 3.0, null, 4.0, 5.0 },
                new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 },
                new long[] { 1, 2, 3, 4, 5, 6 }));
    }

    @Test
    void DISTINCT_POSITIVE_SOURCE_PERIODS_variation1() {
        assertMetamorphicRelationFor(source("2.5", 4, false, Duration.ofHours(6),
                -5.0, -12.5, -12.0, -15.0));
    }

    @Test
    void SOURCE_OHLC_EXTREMES_DO_NOT_DRIVE_BRICKS_variation1() {
        List<Bar> bars = customBars(
                SHORT_PERIOD,
                new double[] { 100.0, -500.0, 1.0 },
                new double[] { 1000.0, 900.0, 800.0 },
                new double[] { -1000.0, -900.0, -800.0 },
                new double[] { 10.0, 10.5, 12.0 },
                new Double[] { 2.0, 3.0, 4.0 },
                new double[] { 5.0, 6.0, 7.0 },
                new long[] { 1, 2, 3 });
        assertMetamorphicRelationFor(new SourceCase(new BigDecimal("2"), 2, true, bars));
    }

    @Test
    void NEGATIVE_PRICE_AND_ZERO_CROSSING_variation1() {
        assertMetamorphicRelationFor(source("1.5", 1, false, LONG_PERIOD, -4.0, 2.25));
    }
}
