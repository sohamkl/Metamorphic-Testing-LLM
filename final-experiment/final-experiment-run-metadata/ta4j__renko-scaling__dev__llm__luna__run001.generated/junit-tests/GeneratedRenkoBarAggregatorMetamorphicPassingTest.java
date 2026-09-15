import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DoubleNum;
import org.ta4j.core.num.Num;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static Num num(Number value) {
        return value == null ? null : DoubleNum.valueOf(value.doubleValue());
    }

    private static Bar bar(int index, Number close, Number volume, Number amount, long trades) {
        Instant end = BASE_TIME.plusSeconds((index + 1) * 60L);
        Instant begin = end.minus(PERIOD);
        Num price = num(close);
        return new BaseBar(PERIOD, begin, end, price, price, price, price, num(volume), num(amount),
                trades);
    }

    private static List<Bar> bars(Number... closes) {
        Bar[] result = new Bar[closes.length];
        for (int i = 0; i < closes.length; i++) {
            result[i] = bar(i, closes[i], 1.0, 10.0, 1L);
        }
        return List.of(result);
    }

    @Test
    void EMPTY_SOURCE_LIST_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> source = List.of();
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_BAR_NO_MOVEMENT_REFERENCE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = List.of(bar(0, 0.0, null, 10.0, 3));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FLAT_CLOSE_SEQUENCE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = List.of(
                bar(0, -5.0, 1.0, null, 2),
                bar(1, -5.0, 2.0, null, 5));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_EXACT_BOX_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 12.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_EXACT_BOX_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 8.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MOVEMENT_BELOW_BOX_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 11.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MULTIPLE_BRICKS_ONE_BAR_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(
                bar(0, 10.0, 1.0, 10.0, 1),
                bar(1, 16.0, 7.0, 70.0, 7));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MULTIPLE_BRICKS_ONE_BAR_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(
                bar(0, 10.0, 1.0, 10.0, 1),
                bar(1, 4.0, 8.0, 80.0, 8));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_CONTINUATION_ONE_BOX_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 12.0, 14.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_CONTINUATION_ONE_BOX_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 8.0, 6.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_CONTINUATION_MULTIPLE_BOXES_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 12.0, 18.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_CONTINUATION_MULTIPLE_BOXES_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 8.0, 2.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_JUST_BELOW_THRESHOLD_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 12.0, 9.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_EXACT_DISTANCE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 12.0, 8.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_MULTIPLE_BOXES_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 14.0, 6.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_EXACT_DISTANCE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 8.0, 12.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_AMOUNT_ONE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = bars(10.0, 12.0, 10.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_AMOUNT_GREATER_THAN_TWO_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1.5, 3);
        List<Bar> source = bars(10.0, 11.5, 7.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ACCUMULATED_PENDING_QUANTITIES_BEFORE_EMISSION_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(
                bar(0, 10.0, 1.0, 10.0, 1),
                bar(1, 11.0, 2.0, 20.0, 2),
                bar(2, 12.0, 3.0, 30.0, 3));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_OR_AMOUNT_FIELDS_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(
                bar(0, 10.0, null, 10.0, 1),
                bar(1, 12.0, 3.0, null, 2));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BRICK_END_TIME_SCHEDULED_BOUNDARY_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> source = bars(10.0, 14.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BRICK_END_TIME_SOURCE_LATER_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 13.0, 13.0, 13.0, 13.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_AND_NEGATIVE_PRICE_LEVELS_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = bars(-2.0, 2.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NON_INTEGER_BOX_SIZE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> source = bars(1.0, 1.5, 2.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_QUANTITIES_AND_POSITIVE_TRADES_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = List.of(
                bar(0, 10.0, 0.0, 0.0, 1),
                bar(1, 12.0, 0.0, 0.0, 5));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_UP_DOWN_MULTI_BAR_SEQUENCE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 12.0, 14.0, 10.0, 8.0, 6.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec
                .generateFollowUp(sourceAggregator, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
