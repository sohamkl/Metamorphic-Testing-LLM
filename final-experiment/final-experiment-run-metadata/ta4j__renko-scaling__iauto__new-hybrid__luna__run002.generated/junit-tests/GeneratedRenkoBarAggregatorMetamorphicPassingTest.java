import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static Bar bar(int index, double close, boolean metadata) {
        Num price = n(close);
        Num volume = metadata ? n(index + 1.0) : n(0.0);
        Num amount = metadata ? n((index + 1.0) * 10.0) : n(0.0);
        return new BaseBar(
                PERIOD,
                BASE.plusSeconds(index),
                BASE.plusSeconds(index + 1L),
                price,
                price,
                price,
                price,
                volume,
                amount,
                metadata ? index + 1L : 0L);
    }

    private static Bar barWithNullOptionalFields(int index, double close) {
        return new BaseBar(
                PERIOD,
                BASE.plusSeconds(index),
                BASE.plusSeconds(index + 1L),
                null,
                null,
                null,
                n(close),
                null,
                null,
                0L);
    }

    private static Num n(double value) {
        return DecimalNum.valueOf(value);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        org.junit.jupiter.api.Assertions.assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int index = 0; index < sourceOutput.size(); index++) {
            Bar source = sourceOutput.get(index);
            Bar followUp = followUpOutput.get(index);

            org.junit.jupiter.api.Assertions.assertEquals(source.getTimePeriod(), followUp.getTimePeriod());
            org.junit.jupiter.api.Assertions.assertEquals(source.getBeginTime(), followUp.getBeginTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getEndTime(), followUp.getEndTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getVolume(), followUp.getVolume());
            org.junit.jupiter.api.Assertions.assertEquals(source.getTrades(), followUp.getTrades());

            int sourceDirection = Integer.compare(
                    source.getClosePrice().compareTo(source.getOpenPrice()), 0);
            int followUpDirection = Integer.compare(
                    followUp.getClosePrice().compareTo(followUp.getOpenPrice()), 0);
            org.junit.jupiter.api.Assertions.assertEquals(sourceDirection, followUpDirection);

            assertScaled(source.getOpenPrice(), followUp.getOpenPrice());
            assertScaled(source.getHighPrice(), followUp.getHighPrice());
            assertScaled(source.getLowPrice(), followUp.getLowPrice());
            assertScaled(source.getClosePrice(), followUp.getClosePrice());
            assertScaled(source.getAmount(), followUp.getAmount());
        }
    }

    private static void assertScaled(Num source, Num followUp) {
        if (source == null || followUp == null) {
            org.junit.jupiter.api.Assertions.assertSame(source, followUp);
        } else {
            Num expected = source.multipliedBy(source.getNumFactory().numOf(2.0));
            org.junit.jupiter.api.Assertions.assertTrue(followUp.isEqual(expected));
        }
    }

    @Test
    void EMPTY_SOURCE_LIST_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> source = List.of();
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_BAR_NO_MOVEMENT_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = List.of(bar(0, 100.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_EXACT_ONE_BOX_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 102.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_EXACT_ONE_BOX_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(10.0, 1);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 90.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MULTIPLE_BOXES_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 106.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MULTIPLE_BOXES_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 94.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_THRESHOLD_JUST_BELOW_UP_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = List.of(bar(0, 100.0, false), bar(1, 101.999, false));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_THRESHOLD_JUST_BELOW_DOWN_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(10.0, 2);
        List<Bar> source = List.of(bar(0, 100.0, false), bar(1, 90.001, false));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METADATA_BEFORE_FIRST_BRICK_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = List.of(
                bar(0, 100.5, true),
                bar(1, 101.0, true),
                bar(2, 102.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_CONTINUATION_ONE_BRICK_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 102.0, true), bar(2, 104.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_CONTINUATION_MULTIPLE_BRICKS_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 102.0, true), bar(2, 108.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_JUST_BELOW_DISTANCE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 102.0, true), bar(2, 98.001, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_EXACT_DISTANCE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 102.0, true), bar(2, 98.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_MULTIPLE_BOXES_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 102.0, true), bar(2, 94.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_CONTINUATION_ONE_BRICK_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 98.0, true), bar(2, 96.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_CONTINUATION_MULTIPLE_BRICKS_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(10.0, 1);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 98.0, true), bar(2, 92.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_JUST_BELOW_DISTANCE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 99.5, true), bar(2, 100.499, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_EXACT_DISTANCE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 99.0, true), bar(2, 102.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_MULTIPLE_BOXES_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 98.0, true), bar(2, 106.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_MOVEMENT_AFTER_UP_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 102.0, true), bar(2, 102.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_MOVEMENT_AFTER_DOWN_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 98.0, true), bar(2, 98.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTIPLE_BRICKS_ZERO_METADATA_TAIL_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 103.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_OPTIONAL_METADATA_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(
                barWithNullOptionalFields(0, 100.0),
                barWithNullOptionalFields(1, 102.0));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_AMOUNT_ONE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 102.0, true), bar(2, 100.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_AMOUNT_THREE_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = List.of(bar(0, 100.0, true), bar(1, 102.0, true), bar(2, 96.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONZERO_METADATA_ACROSS_SOURCE_BARS_1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = List.of(
                bar(0, 101.0, true),
                bar(1, 101.5, true),
                bar(2, 103.0, true),
                bar(3, 103.0, true));
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
