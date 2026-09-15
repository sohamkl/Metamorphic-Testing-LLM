import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static DecimalNum n(double value) {
        return DecimalNum.valueOf(value);
    }

    private static List<Bar> bars(double[] closes, int metadataMode) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            Instant end = BASE.plusSeconds(i + 1L);
            DecimalNum volume = metadataMode == 1 || metadataMode == 3 ? null : n(i + 1);
            DecimalNum amount = metadataMode == 2 || metadataMode == 3 ? null : n((i + 1) * 10);
            long trades = metadataMode == 4 ? (i + 1L) * 3L : i + 1L;
            DecimalNum close = n(closes[i]);
            result.add(new BaseBar(
                    PERIOD,
                    end.minus(PERIOD),
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

    private static void exercise(RenkoBarAggregator aggregator, List<Bar> source) {
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        exercise(new RenkoBarAggregator(0.25, 1), List.of());
    }

    @Test
    void SINGLE_BAR_NO_MOVEMENT_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100}, 1));
    }

    @Test
    void FLAT_CLOSE_SEQUENCE_variation1() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 100}, 2));
    }

    @Test
    void INITIAL_UP_BELOW_BOX_variation1() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 105, 106}, 3));
    }

    @Test
    void INITIAL_UP_EXACT_BOX_variation1() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 100.25, 100.20, 100.25}, 4));
    }

    @Test
    void INITIAL_UP_EXACT_BOX_variation2() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 101, 102, 100, 98}, 4));
    }

    @Test
    void INITIAL_DOWN_EXACT_BOX_variation1() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 98, 96, 98, 100, 102}, 0));
    }

    @Test
    void INITIAL_DOWN_EXACT_BOX_variation2() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 90, 80, 70}, 1));
    }

    @Test
    void INITIAL_UP_MULTIPLE_BOXES_variation1() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 101}, 2));
    }

    @Test
    void INITIAL_UP_MULTIPLE_BOXES_variation2() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 104}, 3));
    }

    @Test
    void INITIAL_UP_MULTIPLE_BOXES_variation3() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 100, 108}, 4));
    }

    @Test
    void INITIAL_DOWN_MULTIPLE_BOXES_variation1() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 40, 20, 0}, 4));
    }

    @Test
    void INITIAL_DOWN_MULTIPLE_BOXES_variation2() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 98, 97}, 0));
    }

    @Test
    void UP_CONTINUATION_EXACT_THRESHOLD_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 101, 102, 103, 104, 105}, 1));
    }

    @Test
    void UP_CONTINUATION_EXACT_THRESHOLD_variation2() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 102}, 2));
    }

    @Test
    void DOWN_CONTINUATION_EXACT_THRESHOLD_variation1() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 90, 80}, 3));
    }

    @Test
    void DOWN_CONTINUATION_EXACT_THRESHOLD_variation2() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 99.75, 99.5}, 4));
    }

    @Test
    void UP_REVERSAL_JUST_BELOW_DISTANCE_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 101, 102, 101.01}, 4));
    }

    @Test
    void UP_REVERSAL_JUST_BELOW_DISTANCE_variation2() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 102, 104, 98.1}, 0));
    }

    @Test
    void UP_REVERSAL_EXACT_DISTANCE_variation1() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 110, 60}, 1));
    }

    @Test
    void UP_REVERSAL_EXACT_DISTANCE_variation2() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 100.25, 100}, 2));
    }

    @Test
    void UP_REVERSAL_EXACT_DISTANCE_variation3() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 102, 100}, 3));
    }

    @Test
    void DOWN_REVERSAL_JUST_BELOW_DISTANCE_variation1() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 98, 96, 101.9}, 4));
    }

    @Test
    void DOWN_REVERSAL_JUST_BELOW_DISTANCE_variation2() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 90, 80, 129.9}, 3));
    }

    @Test
    void DOWN_REVERSAL_EXACT_DISTANCE_variation1() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 99.75, 100}, 0));
    }

    @Test
    void DOWN_REVERSAL_EXACT_DISTANCE_variation2() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 98, 100}, 1));
    }

    @Test
    void DOWN_REVERSAL_EXACT_DISTANCE_variation3() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 94, 100}, 2));
    }

    @Test
    void REVERSAL_AMOUNT_ONE_variation1() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 110, 120, 130, 140, 150}, 3));
    }

    @Test
    void REVERSAL_AMOUNT_ONE_variation2() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 100.25, 100}, 4));
    }

    @Test
    void REVERSAL_AMOUNT_GREATER_THAN_ONE_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 101}, 4));
    }

    @Test
    void REVERSAL_AMOUNT_GREATER_THAN_ONE_variation2() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 98, 99, 104}, 0));
    }

    @Test
    void PENDING_METADATA_ACROSS_NONEMITTING_BARS_variation1() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 101, 102, 110}, 1));
    }

    @Test
    void PENDING_METADATA_ACROSS_NONEMITTING_BARS_variation2() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 100.1, 100.2, 100.25}, 2));
    }

    @Test
    void NULL_VOLUME_METADATA_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 101, 102}, 1));
    }

    @Test
    void NULL_VOLUME_METADATA_variation2() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 104, 108}, 1));
    }

    @Test
    void NULL_AMOUNT_METADATA_variation1() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 110}, 2));
    }

    @Test
    void NULL_AMOUNT_METADATA_variation2() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 99.75}, 2));
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_variation1() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 101}, 3));
    }

    @Test
    void NONZERO_TRADE_PROPAGATION_variation1() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 101, 106}, 4));
    }

    @Test
    void NONZERO_TRADE_PROPAGATION_variation2() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 90, 80, 70}, 4));
    }

    @Test
    void MULTIPLE_BRICKS_TIME_SCHEDULING_variation1() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 102}, 4));
    }

    @Test
    void MULTIPLE_BRICKS_TIME_SCHEDULING_variation2() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 105, 100}, 4));
    }

    @Test
    void MULTIPLE_BRICKS_TIME_SCHEDULING_variation3() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 108}, 0));
    }

    @Test
    void LATER_SOURCE_END_AFTER_SCHEDULE_variation1() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 110, 120}, 1));
    }

    @Test
    void SMALL_POSITIVE_BOX_variation1() {
        exercise(new RenkoBarAggregator(0.25, 1), bars(new double[] {100, 100.1, 100.25}, 2));
    }

    @Test
    void SMALL_POSITIVE_BOX_variation2() {
        exercise(new RenkoBarAggregator(1.0, 2), bars(new double[] {100, 101, 105}, 3));
    }

    @Test
    void LARGER_POSITIVE_BOX_variation1() {
        exercise(new RenkoBarAggregator(2.0, 3), bars(new double[] {100, 101, 102, 104}, 4));
    }

    @Test
    void LARGER_POSITIVE_BOX_variation2() {
        exercise(new RenkoBarAggregator(10.0, 5), bars(new double[] {100, 110, 60, 50, 40}, 4));
    }
}
