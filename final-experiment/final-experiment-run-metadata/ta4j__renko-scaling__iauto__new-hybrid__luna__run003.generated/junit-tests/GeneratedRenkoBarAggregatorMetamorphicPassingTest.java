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

    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");

    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static List<Bar> bars(double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            result.add(bar(closes[i], 1.0, 10.0, 1));
        }
        return result;
    }

    private static Bar bar(double close, Double volume, Double amount, long trades) {
        Instant begin = START.plus(PERIOD.multipliedBy(currentBarIndex));
        Instant end = begin.plus(PERIOD);
        Num price = number(close);
        Num volumeNum = volume == null ? null : number(volume);
        Num amountNum = amount == null ? null : number(amount);
        Bar result = new BaseBar(PERIOD, begin, end, price, price, price, price, volumeNum, amountNum, trades);
        currentBarIndex++;
        return result;
    }

    private static int currentBarIndex;

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        org.junit.jupiter.api.Assertions.assertEquals(sourceOutput.size(), followUpOutput.size(), "brick count");
        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);
            org.junit.jupiter.api.Assertions.assertEquals(source.getTimePeriod(), followUp.getTimePeriod());
            org.junit.jupiter.api.Assertions.assertEquals(source.getBeginTime(), followUp.getBeginTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getEndTime(), followUp.getEndTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getVolume(), followUp.getVolume());
            org.junit.jupiter.api.Assertions.assertEquals(source.getTrades(), followUp.getTrades());
            int sourceDirection = direction(source);
            int followUpDirection = direction(followUp);
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
            return;
        }
        Num expected = source.multipliedBy(source.getNumFactory().numOf(2.0));
        org.junit.jupiter.api.Assertions.assertTrue(followUp.isEqual(expected));
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

    private static Num number(double value) {
        return DecimalNum.valueOf(new BigDecimal(Double.toString(value)));
    }

    private static void run(RenkoBarAggregator aggregator, List<Bar> source) {
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> source = List.of();
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_BAR_SEEDS_STATE_ONLY_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = List.of(bar(100.0, 1.0, 10.0, 1));
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = bars(100.0, 102.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> source = bars(100.0, 99.5);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUBTHRESHOLD_MOVEMENT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = bars(100.0, 100.5, 101.0, 99.5);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EQUALITY_WITHOUT_MOVEMENT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 2);
        List<Bar> source = bars(50.0, 50.0, 50.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UPWARD_CONTINUATION_ONE_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 3);
        List<Bar> source = bars(20.0, 20.5, 21.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWNWARD_CONTINUATION_ONE_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(20.0, 18.0, 16.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTIPLE_UP_BRICKS_FROM_ONE_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 13.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTIPLE_DOWN_BRICKS_FROM_ONE_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = bars(20.0, 14.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_EXACT_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 11.0, 9.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_JUST_BELOW_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> source = bars(10.0, 11.0, 8.1);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_EXACT_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(20.0, 18.0, 22.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_JUST_BELOW_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> source = bars(10.0, 9.0, 11.9);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BOX_REVERSAL_CONFIGURATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = bars(10.0, 12.0, 10.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_WITH_MULTIPLE_BRICKS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 11.0, 6.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METADATA_ACCUMULATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = List.of(bar(100.0, 2.0, 20.0, 3), bar(101.0, 4.0, 40.0, 5), bar(102.0, 6.0, 60.0, 7));
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_METADATA_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = List.of(bar(10.0, null, 5.0, 1), bar(11.0, 3.0, null, 2));
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_METADATA_AND_TRADES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(bar(10.0, 0.0, 0.0, 0), bar(16.0, 0.0, 0.0, 0));
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BRICK_END_TIME_CATCH_UP_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 3);
        List<Bar> source = bars(10.0, 40.0, 40.0, 40.0, 40.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_CONTINUATION_AND_REVERSAL_SEQUENCE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 11.0, 12.0, 8.0, 6.0, 7.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_THRESHOLD_variation2() {
        run(new RenkoBarAggregator(2.0, 1), bars(30.0, 32.0));
    }

    @Test
    void INITIAL_DOWN_THRESHOLD_variation2() {
        run(new RenkoBarAggregator(1.0, 3), bars(40.0, 39.0));
    }

    @Test
    void UPWARD_CONTINUATION_ONE_BRICK_variation2() {
        run(new RenkoBarAggregator(2.0, 2), bars(30.0, 32.0, 34.0));
    }

    @Test
    void DOWNWARD_CONTINUATION_ONE_BRICK_variation2() {
        run(new RenkoBarAggregator(1.0, 1), bars(30.0, 29.0, 28.0));
    }

    @Test
    void MULTIPLE_UP_BRICKS_FROM_ONE_BAR_variation2() {
        run(new RenkoBarAggregator(2.0, 3), bars(5.0, 11.0));
    }

    @Test
    void MULTIPLE_DOWN_BRICKS_FROM_ONE_BAR_variation2() {
        run(new RenkoBarAggregator(1.5, 2), bars(30.0, 25.5));
    }

    @Test
    void UP_REVERSAL_EXACT_THRESHOLD_variation2() {
        run(new RenkoBarAggregator(2.0, 1), bars(30.0, 32.0, 30.0));
    }

    @Test
    void UP_REVERSAL_EXACT_THRESHOLD_variation3() {
        run(new RenkoBarAggregator(0.5, 3), bars(40.0, 40.5, 39.0));
    }

    @Test
    void UP_REVERSAL_JUST_BELOW_THRESHOLD_variation2() {
        run(new RenkoBarAggregator(2.0, 2), bars(70.0, 72.0, 69.1));
    }

    @Test
    void DOWN_REVERSAL_EXACT_THRESHOLD_variation2() {
        run(new RenkoBarAggregator(1.0, 1), bars(50.0, 49.0, 50.0));
    }

    @Test
    void DOWN_REVERSAL_EXACT_THRESHOLD_variation3() {
        run(new RenkoBarAggregator(2.0, 3), bars(60.0, 58.0, 64.0));
    }

    @Test
    void DOWN_REVERSAL_JUST_BELOW_THRESHOLD_variation2() {
        run(new RenkoBarAggregator(1.0, 2), bars(80.0, 79.0, 82.9));
    }

    @Test
    void ONE_BOX_REVERSAL_CONFIGURATION_variation2() {
        run(new RenkoBarAggregator(0.5, 1), bars(25.0, 25.5, 25.0));
    }

    @Test
    void REVERSAL_WITH_MULTIPLE_BRICKS_variation2() {
        run(new RenkoBarAggregator(2.0, 3), bars(50.0, 52.0, 42.0));
    }

    @Test
    void PENDING_METADATA_ACCUMULATION_variation2() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.5, 2);
        List<Bar> source = List.of(bar(40.0, 2.0, 20.0, 2), bar(40.5, 3.0, 30.0, 4), bar(41.5, 5.0, 50.0, 6));
        run(aggregator, source);
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_METADATA_variation2() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = List.of(bar(60.0, null, 12.0, 1), bar(62.0, 4.0, null, 3));
        run(aggregator, source);
    }

    @Test
    void MIXED_CONTINUATION_AND_REVERSAL_SEQUENCE_variation2() {
        run(new RenkoBarAggregator(2.0, 2), bars(30.0, 32.0, 34.0, 28.0, 24.0, 26.0));
    }
}
