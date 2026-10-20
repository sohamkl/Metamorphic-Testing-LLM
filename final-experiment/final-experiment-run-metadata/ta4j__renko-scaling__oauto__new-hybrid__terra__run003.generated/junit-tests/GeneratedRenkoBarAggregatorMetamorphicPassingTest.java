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

    private static void exercise(double boxSize, int reversalAmount, List<Bar> sourceBars) {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(boxSize, reversalAmount);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        Object[] followUp = generateFollowUp(boxSize, reversalAmount, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static void exerciseDefault(double boxSize, List<Bar> sourceBars) {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(boxSize);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        Object[] followUp = generateFollowUp(boxSize, 2, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(double boxSize, int reversalAmount, List<Bar> bars) {
        RenkoBarAggregator scaledAggregator = new RenkoBarAggregator(boxSize * 2.0, reversalAmount);
        List<Bar> scaledBars = new ArrayList<>(bars.size());

        for (Bar bar : bars) {
            scaledBars.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    scale(bar.getOpenPrice()),
                    scale(bar.getHighPrice()),
                    scale(bar.getLowPrice()),
                    scale(bar.getClosePrice()),
                    bar.getVolume(),
                    scale(bar.getAmount()),
                    bar.getTrades()));
        }

        return new Object[] { scaledAggregator, scaledBars };
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(double... closes) {
        return createBars(closes, MetricMode.POSITIVE);
    }

    private static List<Bar> nullMetricBars(double... closes) {
        return createBars(closes, MetricMode.NULL);
    }

    private static List<Bar> zeroMetricBars(double... closes) {
        return createBars(closes, MetricMode.ZERO);
    }

    private static List<Bar> nullThenPresentBars(double... closes) {
        List<Bar> result = new ArrayList<>();
        Instant start = Instant.parse("2021-02-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int index = 0; index < closes.length; index++) {
            Num close = DecimalNum.valueOf(closes[index]);
            boolean finalBar = index == closes.length - 1;
            Instant begin = start.plus(period.multipliedBy(index));
            Instant end = begin.plus(period);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    finalBar ? DecimalNum.valueOf(7.0) : null,
                    finalBar ? DecimalNum.valueOf(70.0) : null,
                    finalBar ? 7L : 0L));
        }

        return result;
    }

    private static List<Bar> createBars(double[] closes, MetricMode mode) {
        List<Bar> result = new ArrayList<>();
        Instant start = Instant.parse("2021-01-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int index = 0; index < closes.length; index++) {
            Num close = DecimalNum.valueOf(closes[index]);
            Instant begin = start.plus(period.multipliedBy(index));
            Instant end = begin.plus(period);

            Num volume;
            Num amount;
            long trades;
            if (mode == MetricMode.NULL) {
                volume = null;
                amount = null;
                trades = index + 1L;
            } else if (mode == MetricMode.ZERO) {
                volume = DecimalNum.valueOf(0.0);
                amount = DecimalNum.valueOf(0.0);
                trades = 0L;
            } else {
                volume = DecimalNum.valueOf(index + 1.0);
                amount = DecimalNum.valueOf((index + 1.0) * 10.0);
                trades = index + 1L;
            }

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
                    trades));
        }

        return result;
    }

    private enum MetricMode {
        POSITIVE,
        ZERO,
        NULL
    }

    @Test
    void EMPTY_SOURCE_LIST_defaultConfiguration() {
        exerciseDefault(2.0, List.of());
    }

    @Test
    void SINGLE_BASELINE_BAR_singleClose() {
        exercise(2.0, 2, bars(10.0));
    }

    @Test
    void FLAT_MULTI_BAR_SERIES_constantClose() {
        exercise(3.0, 3, bars(20.0, 20.0, 20.0));
    }

    @Test
    void UPWARD_SUB_BOX_MOVE_halfBox() {
        exercise(2.0, 2, bars(10.0, 11.5));
    }

    @Test
    void DOWNWARD_SUB_BOX_MOVE_halfBox() {
        exercise(2.0, 2, bars(10.0, 8.5));
    }

    @Test
    void INITIAL_EXACT_UP_BRICK_defaultReversal() {
        exerciseDefault(2.0, bars(10.0, 12.0));
    }

    @Test
    void INITIAL_EXACT_DOWN_BRICK_explicitReversal() {
        exercise(2.0, 2, bars(10.0, 8.0));
    }

    @Test
    void INITIAL_MULTI_UP_BRICKS_threeBoxes() {
        exercise(2.0, 1, bars(10.0, 16.0));
    }

    @Test
    void INITIAL_MULTI_DOWN_BRICKS_threeBoxes() {
        exercise(2.0, 3, bars(10.0, 4.0));
    }

    @Test
    void UP_DIRECTION_SINGLE_BOX_CONTINUATION_laterBar() {
        exercise(2.0, 2, bars(10.0, 12.0, 14.0));
    }

    @Test
    void UP_DIRECTION_MULTI_BOX_CONTINUATION_twoBricks() {
        exercise(2.0, 2, bars(10.0, 12.0, 16.0));
    }

    @Test
    void DOWN_DIRECTION_SINGLE_BOX_CONTINUATION_laterBar() {
        exercise(2.0, 2, bars(10.0, 8.0, 6.0));
    }

    @Test
    void DOWN_DIRECTION_MULTI_BOX_CONTINUATION_twoBricks() {
        exercise(2.0, 2, bars(10.0, 8.0, 4.0));
    }

    @Test
    void UP_PULLBACK_BELOW_DEFAULT_REVERSAL_oneBox() {
        exercise(2.0, 2, bars(10.0, 12.0, 10.0));
    }

    @Test
    void UP_EXACT_DEFAULT_REVERSAL_threshold() {
        exercise(2.0, 2, bars(10.0, 12.0, 8.0));
    }

    @Test
    void UP_REVERSAL_BEYOND_DEFAULT_THRESHOLD_threeDownBricks() {
        exercise(2.0, 2, bars(10.0, 12.0, 6.0));
    }

    @Test
    void DOWN_PULLBACK_BELOW_DEFAULT_REVERSAL_oneBox() {
        exercise(2.0, 2, bars(10.0, 8.0, 10.0));
    }

    @Test
    void DOWN_EXACT_DEFAULT_REVERSAL_threshold() {
        exercise(2.0, 2, bars(10.0, 8.0, 12.0));
    }

    @Test
    void DOWN_REVERSAL_BEYOND_DEFAULT_THRESHOLD_threeUpBricks() {
        exercise(2.0, 2, bars(10.0, 8.0, 14.0));
    }

    @Test
    void ONE_BOX_REVERSAL_FROM_UP_immediate() {
        exercise(2.0, 1, bars(10.0, 12.0, 10.0));
    }

    @Test
    void ONE_BOX_REVERSAL_FROM_DOWN_immediate() {
        exercise(2.0, 1, bars(10.0, 8.0, 10.0));
    }

    @Test
    void THREE_BOX_REVERSAL_INSUFFICIENT_PULLBACK_twoBoxes() {
        exercise(2.0, 3, bars(10.0, 12.0, 8.0));
    }

    @Test
    void THREE_BOX_REVERSAL_EXACT_THRESHOLD() {
        exercise(2.0, 3, bars(10.0, 12.0, 6.0));
    }

    @Test
    void DEFAULT_CONSTRUCTOR_TWO_BOX_REVERSAL_documentedDefault() {
        exerciseDefault(3.0, bars(15.0, 18.0, 12.0));
    }

    @Test
    void FRACTIONAL_BOX_EXACT_THRESHOLD_halfPoint() {
        exercise(0.5, 2, bars(10.0, 10.5, 11.0));
    }

    @Test
    void PENDING_METRICS_ACROSS_NON_EMITTING_BARS_positiveMetrics() {
        exercise(2.0, 2, bars(10.0, 11.0, 12.0));
    }

    @Test
    void EXTRA_BRICKS_HAVE_ZERO_METRICS_multiBrickBar() {
        exercise(2.0, 2, bars(10.0, 16.0));
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_allNullMetrics() {
        exercise(2.0, 2, nullMetricBars(10.0, 12.0));
    }

    @Test
    void NULL_THEN_PRESENT_METRICS_lateValues() {
        exercise(2.0, 2, nullThenPresentBars(10.0, 11.0, 12.0));
    }

    @Test
    void EXPLICIT_ZERO_METRICS_zeroValues() {
        exercise(2.0, 2, zeroMetricBars(10.0, 12.0));
    }

    @Test
    void LATE_SOURCE_END_TIME_WINS_delayedEmission() {
        exercise(2.0, 2, bars(10.0, 12.0, 12.0, 12.0, 14.0));
    }

    @Test
    void QUEUED_BRICK_END_TIME_WINS_multiEmission() {
        exercise(2.0, 2, bars(10.0, 14.0, 16.0));
    }

    @Test
    void EQUAL_SOURCE_AND_QUEUED_END_TIME_distinctBoxScale() {
        exercise(3.0, 2, bars(20.0, 23.0, 26.0));
    }

    @Test
    void UP_OSCILLATION_WITHOUT_REVERSAL_THEN_CONTINUATION() {
        exercise(2.0, 2, bars(10.0, 12.0, 10.0, 14.0));
    }

    @Test
    void DOWN_OSCILLATION_WITHOUT_REVERSAL_THEN_CONTINUATION() {
        exercise(2.0, 2, bars(10.0, 8.0, 10.0, 6.0));
    }

    @Test
    void UP_REVERSAL_THEN_DOWN_CONTINUATION() {
        exercise(2.0, 2, bars(10.0, 12.0, 8.0, 6.0));
    }

    @Test
    void DOWN_REVERSAL_THEN_UP_CONTINUATION() {
        exercise(2.0, 2, bars(10.0, 8.0, 12.0, 14.0));
    }

    @Test
    void UP_DOWN_UP_DIRECTION_TRANSITIONS_repeatedReversals() {
        exercise(2.0, 2, bars(10.0, 12.0, 8.0, 12.0));
    }

    @Test
    void DOWN_UP_DOWN_DIRECTION_TRANSITIONS_repeatedReversals() {
        exercise(2.0, 2, bars(10.0, 8.0, 12.0, 8.0));
    }
}
