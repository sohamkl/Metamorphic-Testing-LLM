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
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private static void verify(RenkoBarAggregator sourceAggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> bars(double... closes) {
        List<Bar> result = new ArrayList<>();
        for (int index = 0; index < closes.length; index++) {
            result.add(bar(index, closes[index], number(10), number(100), 1));
        }
        return result;
    }

    private static Bar bar(int index, double close, Num volume, Num amount, long trades) {
        Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        Num price = number(close);
        return new BaseBar(PERIOD, begin, end, price, price, price, price, volume, amount, trades);
    }

    private static Num number(double value) {
        return DecimalNum.valueOf(value);
    }

    @Test
    void EMPTY_SOURCE_LIST_defaultConfiguration() {
        verify(new RenkoBarAggregator(1.0), List.of());
    }

    @Test
    void SINGLE_BASELINE_BAR_nullVolume() {
        verify(new RenkoBarAggregator(1.0, 1), List.of(bar(0, 100.0, null, number(100), 1)));
    }

    @Test
    void FLAT_MULTI_BAR_NO_BRICKS_nullAmount() {
        verify(new RenkoBarAggregator(1.0, 2), bars(100.0, 100.0, 100.0));
    }

    @Test
    void UPWARD_MOVE_STRICTLY_BELOW_BOX_nullVolumeAndAmount() {
        verify(new RenkoBarAggregator(1.0, 3), List.of(
                bar(0, 100.0, null, null, 1),
                bar(1, 100.5, null, null, 1)));
    }

    @Test
    void DOWNWARD_MOVE_STRICTLY_BELOW_BOX_zeroFields() {
        verify(new RenkoBarAggregator(1.0), List.of(
                bar(0, 100.0, number(0), number(0), 0),
                bar(1, 99.5, number(0), number(0), 0)));
    }

    @Test
    void INITIAL_UP_EXACT_ONE_BOX_reversalAmountOne() {
        verify(new RenkoBarAggregator(1.0, 1), bars(100.0, 101.0));
    }

    @Test
    void INITIAL_DOWN_EXACT_ONE_BOX_twoBrickConfiguration() {
        verify(new RenkoBarAggregator(1.0, 2), bars(100.0, 99.0));
    }

    @Test
    void INITIAL_UP_MULTI_BRICK_SINGLE_BAR_threeBrickConfiguration() {
        verify(new RenkoBarAggregator(1.0, 3), bars(100.0, 103.0));
    }

    @Test
    void INITIAL_DOWN_MULTI_BRICK_SINGLE_BAR_defaultConfiguration() {
        verify(new RenkoBarAggregator(1.0), bars(100.0, 97.0));
    }

    @Test
    void PENDING_FIELDS_ACROSS_NON_EMITTING_BARS_oneBrickReversal() {
        verify(new RenkoBarAggregator(1.0, 1), bars(100.0, 100.25, 100.5, 101.0));
    }

    @Test
    void UP_CONTINUATION_EXACT_ONE_BOX_nullNumericFields() {
        verify(new RenkoBarAggregator(1.0, 2), List.of(
                bar(0, 100.0, null, null, 1),
                bar(1, 101.0, null, null, 1),
                bar(2, 102.0, null, null, 1)));
    }

    @Test
    void UP_CONTINUATION_MULTI_BRICK_zeroFields() {
        verify(new RenkoBarAggregator(1.0, 3), List.of(
                bar(0, 100.0, number(0), number(0), 0),
                bar(1, 101.0, number(0), number(0), 0),
                bar(2, 104.0, number(0), number(0), 0)));
    }

    @Test
    void UP_PULLBACK_ONE_BOX_NO_REVERSAL_defaultConfiguration() {
        verify(new RenkoBarAggregator(1.0), bars(100.0, 101.0, 100.0));
    }

    @Test
    void UP_PULLBACK_STRICTLY_BELOW_REVERSAL_DISTANCE() {
        verify(new RenkoBarAggregator(1.0, 2), bars(100.0, 101.0, 99.5));
    }

    @Test
    void UP_EXACT_TWO_BOX_REVERSAL() {
        verify(new RenkoBarAggregator(1.0, 2), bars(100.0, 101.0, 99.0));
    }

    @Test
    void UP_REVERSAL_OVERSHOOT_threeBrickConfiguration() {
        verify(new RenkoBarAggregator(1.0, 3), List.of(
                bar(0, 100.0, null, number(100), 1),
                bar(1, 102.0, null, number(100), 1),
                bar(2, 98.0, null, number(100), 1)));
    }

    @Test
    void DOWN_CONTINUATION_EXACT_ONE_BOX_defaultConfiguration() {
        verify(new RenkoBarAggregator(1.0), List.of(
                bar(0, 100.0, number(10), null, 1),
                bar(1, 99.0, number(10), null, 1),
                bar(2, 98.0, number(10), null, 1)));
    }

    @Test
    void DOWN_REBOUND_ONE_BOX_NO_REVERSAL_oneBrickConfiguration() {
        verify(new RenkoBarAggregator(1.0, 1), bars(100.0, 99.0, 100.0));
    }

    @Test
    void DOWN_REBOUND_STRICTLY_BELOW_REVERSAL_DISTANCE() {
        verify(new RenkoBarAggregator(1.0, 2), List.of(
                bar(0, 100.0, number(0), number(0), 0),
                bar(1, 99.0, number(0), number(0), 0),
                bar(2, 100.5, number(0), number(0), 0)));
    }

    @Test
    void DOWN_EXACT_TWO_BOX_REVERSAL() {
        verify(new RenkoBarAggregator(1.0, 2), bars(100.0, 99.0, 101.0));
    }

    @Test
    void DOWN_REVERSAL_OVERSHOOT_defaultConfiguration() {
        verify(new RenkoBarAggregator(1.0), bars(100.0, 98.0, 102.0));
    }

    @Test
    void REVERSAL_AMOUNT_ONE_UP_TO_DOWN_exactBoundary() {
        verify(new RenkoBarAggregator(1.0, 1), bars(100.0, 101.0, 100.0));
    }

    @Test
    void REVERSAL_AMOUNT_ONE_DOWN_TO_UP_exactBoundary() {
        verify(new RenkoBarAggregator(1.0, 1), List.of(
                bar(0, 100.0, null, number(100), 1),
                bar(1, 99.0, null, number(100), 1),
                bar(2, 100.0, null, number(100), 1)));
    }

    @Test
    void REVERSAL_AMOUNT_THREE_SHORT_OF_THRESHOLD() {
        verify(new RenkoBarAggregator(1.0, 3), List.of(
                bar(0, 100.0, number(10), null, 1),
                bar(1, 101.0, number(10), null, 1),
                bar(2, 99.0, number(10), null, 1)));
    }

    @Test
    void REVERSAL_AMOUNT_THREE_EXACT_THRESHOLD() {
        verify(new RenkoBarAggregator(1.0, 3), bars(100.0, 101.0, 98.0));
    }

    @Test
    void DEFAULT_CONSTRUCTOR_TWO_BOX_REVERSAL() {
        verify(new RenkoBarAggregator(1.0), List.of(
                bar(0, 100.0, number(0), number(0), 0),
                bar(1, 99.0, number(0), number(0), 0),
                bar(2, 101.0, number(0), number(0), 0)));
    }

    @Test
    void NULL_VOLUME_ACCUMULATION() {
        verify(new RenkoBarAggregator(1.0, 2), List.of(
                bar(0, 100.0, null, number(100), 1),
                bar(1, 101.0, null, number(100), 1)));
    }

    @Test
    void NULL_AMOUNT_ACCUMULATION() {
        verify(new RenkoBarAggregator(1.0, 3), List.of(
                bar(0, 100.0, number(10), null, 1),
                bar(1, 101.0, number(10), null, 1)));
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_MULTI_BRICK() {
        verify(new RenkoBarAggregator(1.0), List.of(
                bar(0, 100.0, null, null, 1),
                bar(1, 102.0, null, null, 1)));
    }

    @Test
    void ZERO_FIELDS_ON_EMITTING_BARS() {
        verify(new RenkoBarAggregator(1.0, 1), List.of(
                bar(0, 100.0, number(0), number(0), 0),
                bar(1, 101.0, number(0), number(0), 0)));
    }

    @Test
    void DELAYED_BRICK_TIME_AFTER_IDLE_INTERVALS() {
        verify(new RenkoBarAggregator(1.0, 2), List.of(
                bar(0, 100.0, number(10), null, 1),
                bar(1, 100.0, number(10), null, 1),
                bar(2, 100.0, number(10), null, 1),
                bar(3, 101.0, number(10), null, 1)));
    }
}
