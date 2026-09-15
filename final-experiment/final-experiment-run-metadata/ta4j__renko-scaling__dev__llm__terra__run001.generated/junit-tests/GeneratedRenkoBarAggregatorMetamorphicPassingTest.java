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

    private static void verify(RenkoBarAggregator aggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];

        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> sourceBars(double... closes) {
        return sourceBarsWithNullMetadata(closes, new boolean[closes.length], new boolean[closes.length]);
    }

    private static List<Bar> sourceBarsWithNullMetadata(
            double[] closes,
            boolean[] nullVolumes,
            boolean[] nullAmounts) {
        List<Bar> bars = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            Num close = DecimalNum.valueOf(closes[index]);
            Num volume = nullVolumes[index] ? null : DecimalNum.valueOf(10 + index);
            Num amount = nullAmounts[index] ? null : DecimalNum.valueOf(100 + index);
            Instant beginTime = BASE_TIME.plus(PERIOD.multipliedBy(index));
            Instant endTime = beginTime.plus(PERIOD);

            bars.add(new BaseBar(
                    PERIOD,
                    beginTime,
                    endTime,
                    close,
                    close,
                    close,
                    close,
                    volume,
                    amount,
                    index + 1L));
        }
        return bars;
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0);
        verify(aggregator, List.of());
    }

    @Test
    void SINGLE_ANCHOR_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0));
    }

    @Test
    void FLAT_MULTI_BAR_SEQUENCE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 100.0, 100.0, 100.0));
    }

    @Test
    void UPWARD_MOVE_JUST_INSIDE_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        verify(aggregator, sourceBars(100.0, 101.999));
    }

    @Test
    void DOWNWARD_MOVE_JUST_INSIDE_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 98.001));
    }

    @Test
    void INITIAL_EXACT_UPWARD_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 102.0));
    }

    @Test
    void INITIAL_EXACT_DOWNWARD_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 98.0));
    }

    @Test
    void INITIAL_UPWARD_MULTI_BOX_SINGLE_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 106.0));
    }

    @Test
    void INITIAL_DOWNWARD_MULTI_BOX_SINGLE_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 94.0));
    }

    @Test
    void UPWARD_TREND_CONTINUATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        verify(aggregator, sourceBars(100.0, 102.0, 104.0));
    }

    @Test
    void DOWNWARD_TREND_CONTINUATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 98.0, 96.0));
    }

    @Test
    void UP_DIRECTION_REVERSAL_INSIDE_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 102.0, 99.0));
    }

    @Test
    void UP_DIRECTION_REVERSAL_AT_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 102.0, 98.0));
    }

    @Test
    void DOWN_DIRECTION_REVERSAL_INSIDE_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 98.0, 101.0));
    }

    @Test
    void DOWN_DIRECTION_REVERSAL_AT_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 98.0, 102.0));
    }

    @Test
    void ONE_BOX_REVERSAL_CONFIGURATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 1);
        verify(aggregator, sourceBars(100.0, 102.0, 100.0));
    }

    @Test
    void THREE_BOX_REVERSAL_CONFIGURATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        verify(aggregator, sourceBars(100.0, 102.0, 96.0));
    }

    @Test
    void REVERSAL_OVERSHOOT_MULTI_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 102.0, 93.0));
    }

    @Test
    void POST_REVERSAL_DOWNWARD_CONTINUATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 102.0, 98.0, 96.0));
    }

    @Test
    void PENDING_METADATA_ACROSS_NON_EMITTING_BARS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(100.0, 101.0, 102.0));
    }

    @Test
    void NULL_VOLUME_IGNORED_IN_PENDING_TOTAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBarsWithNullMetadata(
                new double[] {100.0, 102.0},
                new boolean[] {false, true},
                new boolean[] {false, false}));
    }

    @Test
    void NULL_AMOUNT_IGNORED_IN_PENDING_TOTAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBarsWithNullMetadata(
                new double[] {100.0, 102.0},
                new boolean[] {false, false},
                new boolean[] {false, true}));
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBarsWithNullMetadata(
                new double[] {100.0, 102.0},
                new boolean[] {true, true},
                new boolean[] {true, true}));
    }

    @Test
    void FRACTIONAL_BOX_EXACT_BOUNDARIES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        verify(aggregator, sourceBars(10.0, 10.5, 11.0));
    }

    @Test
    void LARGER_BOX_MULTI_BRICK_MOVE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(3.0, 2);
        verify(aggregator, sourceBars(100.0, 106.0));
    }

    @Test
    void ZERO_AND_NEGATIVE_PRICE_DOWNWARD_MOVE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, sourceBars(0.0, -2.0));
    }
}
