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

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");

    private static Num n(double value) {
        return DecimalNum.valueOf(value);
    }

    private static Bar bar(int index, double close) {
        return bar(index, close, close, close, close, 1.0 + index, 10.0 + index, index + 1L);
    }

    private static Bar bar(int index, double close, Double volume, Double amount, long trades) {
        return bar(index, close, close, close, close, volume, amount, trades);
    }

    private static Bar bar(int index, double open, double high, double low, double close,
            Double volume, Double amount, long trades) {
        Instant begin = BASE.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        return new BaseBar(PERIOD, begin, end, n(open), n(high), n(low), n(close),
                volume == null ? null : n(volume),
                amount == null ? null : n(amount),
                trades);
    }

    @SafeVarargs
    private static List<Bar> bars(Bar... values) {
        return List.of(values);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        if (sourceOutput.size() != followUpOutput.size()) {
            throw new AssertionError("Renko brick counts differ: source=" + sourceOutput.size()
                    + ", follow-up=" + followUpOutput.size());
        }

        for (int index = 0; index < sourceOutput.size(); index++) {
            Bar source = sourceOutput.get(index);
            Bar followUp = followUpOutput.get(index);

            if (!Objects.equals(source.getTimePeriod(), followUp.getTimePeriod())) {
                throw new AssertionError("Time periods differ at brick " + index);
            }
            if (!Objects.equals(source.getBeginTime(), followUp.getBeginTime())) {
                throw new AssertionError("Begin times differ at brick " + index);
            }
            if (!Objects.equals(source.getEndTime(), followUp.getEndTime())) {
                throw new AssertionError("End times differ at brick " + index);
            }
            if (!Objects.equals(source.getVolume(), followUp.getVolume())) {
                throw new AssertionError("Volumes differ at brick " + index);
            }
            if (source.getTrades() != followUp.getTrades()) {
                throw new AssertionError("Trade counts differ at brick " + index);
            }

            int sourceDirection = source.getClosePrice().isGreaterThan(source.getOpenPrice()) ? 1
                    : source.getClosePrice().isLessThan(source.getOpenPrice()) ? -1 : 0;
            int followUpDirection = followUp.getClosePrice().isGreaterThan(followUp.getOpenPrice()) ? 1
                    : followUp.getClosePrice().isLessThan(followUp.getOpenPrice()) ? -1 : 0;
            if (sourceDirection != followUpDirection) {
                throw new AssertionError("Directions differ at brick " + index);
            }

            Num factor = source.getOpenPrice().getNumFactory().numOf(2.0);
            if (!followUp.getOpenPrice().isEqual(source.getOpenPrice().multipliedBy(factor))) {
                throw new AssertionError("Open price is not scaled at brick " + index);
            }
            if (!followUp.getHighPrice().isEqual(source.getHighPrice().multipliedBy(factor))) {
                throw new AssertionError("High price is not scaled at brick " + index);
            }
            if (!followUp.getLowPrice().isEqual(source.getLowPrice().multipliedBy(factor))) {
                throw new AssertionError("Low price is not scaled at brick " + index);
            }
            if (!followUp.getClosePrice().isEqual(source.getClosePrice().multipliedBy(factor))) {
                throw new AssertionError("Close price is not scaled at brick " + index);
            }

            if (source.getAmount() == null || followUp.getAmount() == null) {
                if (source.getAmount() != followUp.getAmount()) {
                    throw new AssertionError("Amount nullability differs at brick " + index);
                }
            } else if (!followUp.getAmount().isEqual(source.getAmount().multipliedBy(factor))) {
                throw new AssertionError("Amount is not scaled at brick " + index);
            }
        }
    }

    @Test
    public void EMPTY_SOURCE_LIST_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = List.of();
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_BAR_ANCHOR_ONLY_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(bar(0, -1.0, 6.0, -7.0, -1.0, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_BAR_FLAT_CLOSES_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(4);
        List<Bar> input = bars(
                bar(0, -20, -18, -24, -20, null, null, 1),
                bar(1, -19, -10, -30, -20, null, null, 2),
                bar(2, -21, -15, -28, -20, null, null, 3));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SUB_BOX_MOVEMENT_BOTH_SIDES_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(5.0, 4);
        List<Bar> input = bars(
                bar(0, 100),
                bar(1, 104, null, 8.0, 2),
                bar(2, 96, 3.0, null, 3),
                bar(3, 102, 4.0, 9.0, 4));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_UP_EXACT_BOX_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, 0, 20, -5, 0, 2.0, 4.0, 1),
                bar(1, 10));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_DOWN_EXACT_BOX_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(
                bar(0, -10, 0.0, 0.0, 0),
                bar(1, -12.5, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_UP_MULTIPLE_BRICKS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, 100, null, null, 2),
                bar(1, 130, null, null, 3));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_DOWN_MULTIPLE_BRICKS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 5);
        List<Bar> input = bars(
                bar(0, 0, null, 2.0, 1),
                bar(1, -1, 3.0, null, 2),
                bar(2, -4, null, 5.0, 3),
                bar(3, -10, 4.0, 6.0, 4));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_UP_WITH_RESIDUAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(bar(0, -20), bar(1, 5));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_DOWN_WITH_RESIDUAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(bar(0, 100, 0.0, 0.0, 0), bar(1, 93.75, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_CONTINUATION_EXACT_BOX_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(bar(0, 0, null, null, 1), bar(1, 10, null, null, 2), bar(2, 20, null, null, 3));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_CONTINUATION_EXACT_BOX_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 5);
        List<Bar> input = bars(bar(0, -10), bar(1, -12.5), bar(2, -15));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_CONTINUATION_MULTIPLE_BRICKS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(bar(0, 100), bar(1, 110), bar(2, 135));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_CONTINUATION_MULTIPLE_BRICKS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(bar(0, 0, 0.0, 0.0, 0), bar(1, -2.5, 0.0, 0.0, 0),
                bar(2, -8.75, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_PULLBACK_BELOW_REVERSAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(bar(0, -30, null, null, 1), bar(1, -20, null, null, 2),
                bar(2, -30, null, null, 3));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_RALLY_BELOW_REVERSAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 4);
        List<Bar> input = bars(bar(0, 50), bar(1, 47.5), bar(2, 52.5));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_TO_DOWN_EXACT_REVERSAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(bar(0, 0), bar(1, 10), bar(2, -10));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_TO_UP_EXACT_REVERSAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(bar(0, -10, 0.0, 0.0, 0), bar(1, -12.5, 0.0, 0.0, 0),
                bar(2, -7.5, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_TO_DOWN_BEYOND_REVERSAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(bar(0, 100, null, null, 1), bar(1, 110, null, null, 2),
                bar(2, 80, null, null, 3));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_TO_UP_BEYOND_REVERSAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 4);
        List<Bar> input = bars(bar(0, 0), bar(1, -2.5), bar(2, 10));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_BOX_UP_TO_DOWN_REVERSAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10, 1);
        List<Bar> input = bars(bar(0, -20), bar(1, -10), bar(2, -20));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_BOX_DOWN_TO_UP_REVERSAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 1);
        List<Bar> input = bars(bar(0, 20, 0.0, 0.0, 0), bar(1, 17.5, 0.0, 0.0, 0),
                bar(2, 20, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEFAULT_TWO_BOX_REVERSAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(bar(0, 0, null, null, 1), bar(1, 10, null, null, 2),
                bar(2, -10, null, null, 3));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THREE_BOX_SUPPRESSES_TWO_BOX_PULLBACK_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 3);
        List<Bar> input = bars(bar(0, -10), bar(1, -7.5), bar(2, -12.5));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THREE_BOX_EXACT_REVERSAL_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10, 3);
        List<Bar> input = bars(bar(0, 100), bar(1, 110), bar(2, 80));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_REVERSAL_AMOUNT_DELAY_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 5);
        List<Bar> input = bars(bar(0, 0, 0.0, 0.0, 0), bar(1, -2.5, 0.0, 0.0, 0),
                bar(2, 5, 0.0, 0.0, 0), bar(3, 10, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PENDING_METRICS_ACROSS_QUIET_BARS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, -20, 2.0, 3.0, 1),
                bar(1, -17, 4.0, 5.0, 2),
                bar(2, -24, 6.0, 7.0, 3),
                bar(3, -10, 8.0, 9.0, 4));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void METRICS_RESET_AFTER_EMISSION_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 4);
        List<Bar> input = bars(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 102.5, 3.0, 4.0, 2),
                bar(2, 103, 5.0, 6.0, 3),
                bar(3, 105, 7.0, 8.0, 4));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_BRICK_METRIC_ZEROING_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, 0, 2.0, 4.0, 1),
                bar(1, 30, 3.0, 6.0, 2));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ALL_NULL_VOLUME_AND_AMOUNT_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(
                bar(0, -10, null, null, 1),
                bar(1, -15, null, null, 2));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_NULL_AND_NON_NULL_METRICS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, 100, null, 3.0, 1),
                bar(1, 104, 2.0, null, 2),
                bar(2, 110, 4.0, 5.0, 3));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FIRST_BAR_METRICS_INCLUDED_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 4);
        List<Bar> input = bars(
                bar(0, 0, 7.0, 9.0, 5),
                bar(1, 1, null, 2.0, 1),
                bar(2, 2.5, 3.0, null, 2));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FIRST_EMISSION_USES_SOURCE_END_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(bar(0, -30), bar(1, -26), bar(2, -20));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_BRICK_SYNTHETIC_FUTURE_TIMES_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(
                bar(0, 100, 0.0, 0.0, 0),
                bar(1, 107.5, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SCHEDULE_AFTER_NEXT_SOURCE_END_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, 0, null, null, 1),
                bar(1, 30, null, null, 2),
                bar(2, 40, null, null, 3));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOURCE_END_EQUALS_SCHEDULE_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 4);
        List<Bar> input = bars(
                bar(0, -10),
                bar(1, -2.5),
                bar(2, -2),
                bar(3, -1.5),
                bar(4, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOURCE_END_OVERTAKES_SCHEDULE_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, 100),
                bar(1, 130),
                bar(2, 131),
                bar(3, 132),
                bar(4, 133),
                bar(5, 140));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ALTERNATING_EXACT_REVERSALS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(
                bar(0, 0, 0.0, 0.0, 0),
                bar(1, 2.5, 0.0, 0.0, 0),
                bar(2, -2.5, 0.0, 0.0, 0),
                bar(3, 2.5, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_PULLBACK_THEN_RESUME_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, -30, null, null, 1),
                bar(1, -20, null, null, 2),
                bar(2, -30, null, null, 3),
                bar(3, -10, null, null, 4));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_RALLY_THEN_RESUME_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 4);
        List<Bar> input = bars(
                bar(0, 50),
                bar(1, 47.5),
                bar(2, 52.5),
                bar(3, 45));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UPWARD_BRICKS_CROSS_ZERO_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(bar(0, -15), bar(1, 15));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWNWARD_BRICKS_NEGATIVE_REGION_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(
                bar(0, -10, 0.0, 0.0, 0),
                bar(1, -17.5, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRACTIONAL_BOX_EXACT_THRESHOLDS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5);
        List<Bar> input = bars(
                bar(0, 100, null, null, 1),
                bar(1, 102.5, null, null, 2),
                bar(2, 105, null, null, 3));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTRABAR_EXTREMES_DO_NOT_EMIT_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 4);
        List<Bar> input = bars(
                bar(0, 0, 0, 0, 0, 1.0, 2.0, 1),
                bar(1, 1, 8, -1, 1, null, 3.0, 2),
                bar(2, -1, 2, -8, -1, 4.0, null, 3),
                bar(3, 0.5, 9, -9, 0.5, 5.0, 6.0, 4));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_CLOSE_PRICES_DO_NOT_DEFINE_BRICKS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, -25, -5, -35, -20, 2.0, 4.0, 1),
                bar(1, -12, 5, -40, -10, 3.0, 6.0, 2));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void REVERSAL_MULTI_BRICK_METRIC_PLACEMENT_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 102.5, 3.0, 4.0, 2),
                bar(2, 101.5, 5.0, 6.0, 3),
                bar(3, 95, 7.0, 8.0, 4));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_BRICK_PER_SUCCESSIVE_BAR_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, 0, null, null, 1),
                bar(1, 10, null, null, 2),
                bar(2, 20, null, null, 3),
                bar(3, 30, null, null, 4));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DELAYED_INITIAL_DOWN_DIRECTION_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 5);
        List<Bar> input = bars(
                bar(0, -10, null, 2.0, 1),
                bar(1, -9, 3.0, null, 2),
                bar(2, -11, null, 4.0, 3),
                bar(3, -12.5, 5.0, 6.0, 4));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FLAT_CLOSE_AFTER_UP_DIRECTION_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(10);
        List<Bar> input = bars(
                bar(0, 100),
                bar(1, 110),
                bar(2, 110, 4.0, 8.0, 3));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXPLICIT_ZERO_METRICS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2.5, 2);
        List<Bar> input = bars(
                bar(0, 0, 7, -7, 0, 0.0, 0.0, 0),
                bar(1, 2.5, 9, -9, 2.5, 0.0, 0.0, 0),
                bar(2, 5, 10, -10, 5, 0.0, 0.0, 0));
        List<Bar> sourceOutput = source.aggregate(input);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, input);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
