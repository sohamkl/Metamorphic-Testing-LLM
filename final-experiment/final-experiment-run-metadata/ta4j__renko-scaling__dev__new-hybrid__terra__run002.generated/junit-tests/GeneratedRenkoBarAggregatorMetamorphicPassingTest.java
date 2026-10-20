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
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private static void exercise(RenkoBarAggregator aggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];

        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> series(double... closes) {
        List<Bar> result = new ArrayList<>();
        for (int index = 0; index < closes.length; index++) {
            result.add(bar(index, closes[index], index + 1.0, (index + 1.0) * 10.0, index + 1L));
        }
        return result;
    }

    private static List<Bar> bars(Bar... sourceBars) {
        return List.of(sourceBars);
    }

    private static Bar bar(int index, double close, Number volume, Number amount, long trades) {
        Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        Num closePrice = num(close);

        return new BaseBar(
                PERIOD,
                begin,
                end,
                closePrice,
                closePrice,
                closePrice,
                closePrice,
                volume == null ? null : num(volume.doubleValue()),
                amount == null ? null : num(amount.doubleValue()),
                trades);
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    @Test
    void EMPTY_INPUT_variation1() {
        exercise(new RenkoBarAggregator(1), List.of());
    }

    @Test
    void SINGLE_BAR_NO_MOVEMENT_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0));
    }

    @Test
    void SUB_BOX_UPWARD_MOVE_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 100.5));
    }

    @Test
    void SUB_BOX_DOWNWARD_MOVE_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 99.5));
    }

    @Test
    void EXACT_INITIAL_UP_THRESHOLD_variation1() {
        exercise(new RenkoBarAggregator(1, 2), bars(
                bar(0, 100.0, 2, 20, 1),
                bar(1, 101.0, 3, 30, 2)));
    }

    @Test
    void EXACT_INITIAL_DOWN_THRESHOLD_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 99.0));
    }

    @Test
    void MULTI_BRICK_INITIAL_UP_variation1() {
        exercise(new RenkoBarAggregator(1), bars(
                bar(0, 100.0, 2, 20, 1),
                bar(1, 103.0, 5, 50, 3)));
    }

    @Test
    void MULTI_BRICK_INITIAL_DOWN_variation1() {
        exercise(new RenkoBarAggregator(1, 2), bars(
                bar(0, 100.0, 2, 20, 1),
                bar(1, 97.0, 5, 50, 3)));
    }

    @Test
    void UP_CONTINUATION_AT_THRESHOLD_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 101.0, 102.0));
    }

    @Test
    void DOWN_CONTINUATION_AT_THRESHOLD_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 99.0, 98.0));
    }

    @Test
    void UP_TREND_PULLBACK_SHORT_OF_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 101.0, 99.5));
    }

    @Test
    void UP_TREND_EXACT_TWO_BOX_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 101.0, 99.0));
    }

    @Test
    void DOWN_TREND_PULLBACK_SHORT_OF_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 99.0, 100.5));
    }

    @Test
    void DOWN_TREND_EXACT_TWO_BOX_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 99.0, 101.0));
    }

    @Test
    void REVERSAL_AMOUNT_ONE_UP_TO_DOWN_variation1() {
        exercise(new RenkoBarAggregator(1, 1), series(100.0, 101.0, 100.0));
    }

    @Test
    void REVERSAL_AMOUNT_ONE_DOWN_TO_UP_variation1() {
        exercise(new RenkoBarAggregator(1, 1), series(100.0, 99.0, 100.0));
    }

    @Test
    void REVERSAL_AMOUNT_THREE_SHORT_OF_THRESHOLD_variation1() {
        exercise(new RenkoBarAggregator(1, 3), series(100.0, 101.0, 99.0));
    }

    @Test
    void REVERSAL_AMOUNT_THREE_EXACT_THRESHOLD_variation1() {
        exercise(new RenkoBarAggregator(1, 3), series(100.0, 101.0, 98.0));
    }

    @Test
    void LARGE_UP_MOVE_AFTER_UP_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(1, 2), bars(
                bar(0, 100.0, 1, 10, 1),
                bar(1, 101.0, 2, 20, 2),
                bar(2, 97.0, 7, 70, 4)));
    }

    @Test
    void LARGE_DOWN_MOVE_AFTER_DOWN_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(1, 2), bars(
                bar(0, 100.0, 1, 10, 1),
                bar(1, 99.0, 2, 20, 2),
                bar(2, 103.0, 7, 70, 4)));
    }

    @Test
    void PENDING_METRICS_ACROSS_NON_EMITTING_BARS_variation1() {
        exercise(new RenkoBarAggregator(1, 2), bars(
                bar(0, 100.0, 1, 10, 1),
                bar(1, 100.25, 2, 20, 2),
                bar(2, 100.5, 3, 30, 3),
                bar(3, 101.0, 4, 40, 4)));
    }

    @Test
    void POST_EMISSION_PENDING_METRICS_variation1() {
        exercise(new RenkoBarAggregator(1, 2), bars(
                bar(0, 100.0, 1, 10, 1),
                bar(1, 101.0, 2, 20, 2),
                bar(2, 101.5, 3, 30, 3),
                bar(3, 102.0, 4, 40, 4)));
    }

    @Test
    void NULL_VOLUME_ACCUMULATION_variation1() {
        exercise(new RenkoBarAggregator(1, 2), bars(
                bar(0, 100.0, null, 10, 1),
                bar(1, 101.0, 3, 20, 2)));
    }

    @Test
    void NULL_AMOUNT_ACCUMULATION_variation1() {
        exercise(new RenkoBarAggregator(1, 2), bars(
                bar(0, 100.0, 2, null, 1),
                bar(1, 101.0, 3, 20, 2)));
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_variation1() {
        exercise(new RenkoBarAggregator(1, 2), bars(
                bar(0, 100.0, null, null, 1),
                bar(1, 99.0, null, null, 2)));
    }

    @Test
    void ZERO_METRICS_ON_MULTI_BRICK_SOURCE_variation1() {
        exercise(new RenkoBarAggregator(1, 2), bars(
                bar(0, 100.0, 0, 0, 0),
                bar(1, 102.0, 0, 0, 0)));
    }

    @Test
    void FRACTIONAL_DYADIC_BOX_SIZE_variation1() {
        exercise(new RenkoBarAggregator(0.5, 2), series(100.0, 100.5, 101.5));
    }

    @Test
    void LARGER_INTEGER_BOX_SIZE_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100.0, 101.0, 102.0, 106.0));
    }

    @Test
    void DEFAULT_CONSTRUCTOR_TWO_BOX_REVERSAL_variation1() {
        exercise(new RenkoBarAggregator(1), series(100.0, 101.0, 99.0));
    }

    @Test
    void CLOSE_EQUALS_LAST_BRICK_CLOSE_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 101.0, 101.0));
    }

    @Test
    void UP_MOVE_ONE_STEP_BELOW_NEXT_BRICK_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 101.0, 101.5));
    }

    @Test
    void DOWN_MOVE_ONE_STEP_BELOW_NEXT_BRICK_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 99.0, 98.5));
    }

    @Test
    void LATER_SOURCE_TIME_ADVANCES_BRICK_END_variation1() {
        exercise(new RenkoBarAggregator(1, 2), series(100.0, 100.5, 101.0));
    }
}
