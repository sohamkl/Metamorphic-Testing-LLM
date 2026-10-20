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

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private static Bar bar(int index, double close, double volume, double amount, long trades) {
        return barWithNullableFields(index, close, volume, amount, trades);
    }

    private static Bar barWithNullableFields(int index, double close, Double volume, Double amount, long trades) {
        Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        Num price = DecimalNum.valueOf(close);
        Num volumeNum = volume == null ? null : DecimalNum.valueOf(volume);
        Num amountNum = amount == null ? null : DecimalNum.valueOf(amount);
        return new BaseBar(PERIOD, begin, end, price, price, price, price, volumeNum, amountNum, trades);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        if (sourceOutput.size() != followUpOutput.size()) {
            throw new AssertionError("Renko brick counts differ: source=" + sourceOutput.size()
                    + ", follow-up=" + followUpOutput.size());
        }

        for (int index = 0; index < sourceOutput.size(); index++) {
            Bar source = sourceOutput.get(index);
            Bar followUp = followUpOutput.get(index);

            if (!Objects.equals(source.getBeginTime(), followUp.getBeginTime())
                    || !Objects.equals(source.getEndTime(), followUp.getEndTime())
                    || !Objects.equals(source.getTimePeriod(), followUp.getTimePeriod())
                    || !Objects.equals(source.getVolume(), followUp.getVolume())
                    || source.getTrades() != followUp.getTrades()) {
                throw new AssertionError("Non-price brick properties differ at index " + index);
            }

            int sourceDirection = source.getClosePrice().isGreaterThan(source.getOpenPrice()) ? 1
                    : source.getClosePrice().isLessThan(source.getOpenPrice()) ? -1 : 0;
            int followUpDirection = followUp.getClosePrice().isGreaterThan(followUp.getOpenPrice()) ? 1
                    : followUp.getClosePrice().isLessThan(followUp.getOpenPrice()) ? -1 : 0;
            if (sourceDirection != followUpDirection) {
                throw new AssertionError("Brick directions differ at index " + index);
            }

            assertScaledValue(source.getOpenPrice(), followUp.getOpenPrice(), "open", index);
            assertScaledValue(source.getHighPrice(), followUp.getHighPrice(), "high", index);
            assertScaledValue(source.getLowPrice(), followUp.getLowPrice(), "low", index);
            assertScaledValue(source.getClosePrice(), followUp.getClosePrice(), "close", index);
            assertScaledValue(source.getAmount(), followUp.getAmount(), "amount", index);
        }
    }

    private static void assertScaledValue(Num source, Num followUp, String field, int index) {
        if (source == null || followUp == null) {
            if (source != followUp) {
                throw new AssertionError("Null mismatch for " + field + " at index " + index);
            }
            return;
        }

        Num expected = source.multipliedBy(source.getNumFactory().numOf(2.0));
        if (!followUp.isEqual(expected)) {
            throw new AssertionError("Scaled " + field + " differs at index " + index);
        }
    }

    @Test
    void test_EMPTY_LIST_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of();

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_SINGLE_ANCHOR_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(bar(0, 10.0, 5.0, 20.0, 1));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_FLAT_MULTI_BAR_NO_EMISSION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 1.0, 2.0, 1),
                bar(1, 10.0, 2.0, 4.0, 2),
                bar(2, 10.0, 3.0, 6.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_SUB_BOX_UPWARD_MOVE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 2.0, 4.0, 1),
                bar(1, 10.5, 3.0, 6.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_EXACT_INITIAL_UP_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 5.0, 10.0, 1),
                bar(1, 11.0, 7.0, 14.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_EXACT_INITIAL_DOWN_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 4.0, 8.0, 1),
                bar(1, 9.0, 6.0, 12.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_MULTI_UP_BRICKS_ONE_SOURCE_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 2.0, 4.0, 1),
                bar(1, 13.0, 9.0, 18.0, 5));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_PENDING_FIELDS_AND_DELAYED_FIRST_BRICK_TIME_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 2.0, 4.0, 1),
                bar(1, 10.0, 3.0, 6.0, 2),
                bar(2, 11.0, 5.0, 10.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_UP_CONTINUATION_AFTER_INITIAL_UP_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 1.0, 2.0, 1),
                bar(1, 11.0, 2.0, 4.0, 2),
                bar(2, 12.0, 3.0, 6.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_UP_RETRACE_BELOW_TWO_BOX_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 1.0, 2.0, 1),
                bar(1, 11.0, 2.0, 4.0, 2),
                bar(2, 9.5, 3.0, 6.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_UP_EXACT_TWO_BOX_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 2.0, 4.0, 1),
                bar(1, 11.0, 3.0, 6.0, 2),
                bar(2, 9.0, 4.0, 8.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_UP_OVERSHOOT_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 2.0, 4.0, 1),
                bar(1, 12.0, 4.0, 8.0, 2),
                bar(2, 8.0, 6.0, 12.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_DOWN_CONTINUATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 1.0, 2.0, 1),
                bar(1, 9.0, 2.0, 4.0, 2),
                bar(2, 7.0, 3.0, 6.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_DOWN_EXACT_TWO_BOX_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 2.0, 4.0, 1),
                bar(1, 9.0, 3.0, 6.0, 2),
                bar(2, 11.0, 4.0, 8.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_ONE_BOX_REVERSAL_CONFIGURATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 1.0, 2.0, 1),
                bar(1, 11.0, 2.0, 4.0, 2),
                bar(2, 10.0, 3.0, 6.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_THREE_BOX_REVERSAL_NOT_REACHED_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 1.0, 2.0, 1),
                bar(1, 11.0, 2.0, 4.0, 2),
                bar(2, 8.5, 3.0, 6.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_DEFAULT_CONSTRUCTOR_HALF_BOX_UPWARD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> sourceBars = List.of(
                bar(0, 10.0, 2.0, 4.0, 1),
                bar(1, 11.0, 4.0, 8.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_NULL_VOLUME_AND_AMOUNT_ARE_SKIPPED_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> sourceBars = List.of(
                barWithNullableFields(0, 10.0, null, null, 2),
                barWithNullableFields(1, 11.0, 7.0, 3.0, 4));

        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
