import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static List<Bar> bars(double... closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = (double) (i + 1);
            amounts[i] = (double) ((i + 1) * 10);
            trades[i] = i + 1L;
        }
        return barsWithMetadata(closes, volumes, amounts, trades);
    }

    private static List<Bar> barsWithMetadata(double[] closes, Double[] volumes, Double[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            Num close = num(closes[i]);
            Num volume = volumes[i] == null ? null : num(volumes[i]);
            Num amount = amounts[i] == null ? null : num(amounts[i]);
            result.add(new BaseBar(PERIOD, begin, end, close, close, close, close, volume, amount, trades[i]));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> castBars(Object value) {
        return (List<Bar>) value;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        org.junit.jupiter.api.Assertions.assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);

            org.junit.jupiter.api.Assertions.assertEquals(direction(source), direction(followUp));
            org.junit.jupiter.api.Assertions.assertEquals(source.getBeginTime(), followUp.getBeginTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getEndTime(), followUp.getEndTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getTimePeriod(), followUp.getTimePeriod());
            org.junit.jupiter.api.Assertions.assertTrue(Objects.equals(source.getVolume(), followUp.getVolume()));
            org.junit.jupiter.api.Assertions.assertEquals(source.getTrades(), followUp.getTrades());

            assertScaledByTwo(source.getOpenPrice(), followUp.getOpenPrice());
            assertScaledByTwo(source.getHighPrice(), followUp.getHighPrice());
            assertScaledByTwo(source.getLowPrice(), followUp.getLowPrice());
            assertScaledByTwo(source.getClosePrice(), followUp.getClosePrice());
            assertScaledByTwo(source.getAmount(), followUp.getAmount());
        }
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

    private static void assertScaledByTwo(Num source, Num followUp) {
        if (source == null || followUp == null) {
            org.junit.jupiter.api.Assertions.assertTrue(source == null && followUp == null);
            return;
        }
        Num expected = source.multipliedBy(source.getNumFactory().numOf(2));
        org.junit.jupiter.api.Assertions.assertTrue(followUp.isEqual(expected));
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.125);
        List<Bar> sourceBars = List.of();
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_BAR_ANCHOR_ONLY_variation1_zeroAnchor() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> sourceBars = bars(0.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_BAR_ANCHOR_ONLY_variation2_negativeAnchor() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2);
        List<Bar> sourceBars = bars(-10.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTIBAR_FLAT_CLOSES_variation1_largeBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1_000_000_000.0, 1);
        List<Bar> sourceBars = bars(0.0, 0.0, 0.0, 0.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTIBAR_FLAT_CLOSES_variation2_positiveAnchor() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.25);
        List<Bar> sourceBars = bars(7.0, 7.0, 7.0, 7.0, 7.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MOVE_BELOW_BOX_variation1_fractionalBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 3);
        List<Bar> sourceBars = bars(0.0, 0.25);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MOVE_BELOW_BOX_variation2_negativeRegion() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2);
        List<Bar> sourceBars = bars(-10.0, -8.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MOVE_EXACT_BOX_variation1_crossesZero() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(4, 2);
        List<Bar> sourceBars = bars(-4.0, -3.0, 0.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MOVE_EXACT_BOX_variation2_defaultReversal() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.125);
        List<Bar> sourceBars = bars(5.0, 5.125);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MOVE_BETWEEN_FIRST_AND_SECOND_BOX_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> sourceBars = bars(0.0, 0.75);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MOVE_BELOW_BOX_variation1_negativeRegion() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2);
        List<Bar> sourceBars = bars(-10.0, -11.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MOVE_BELOW_BOX_variation2_largeBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1_000_000.0, 4);
        List<Bar> sourceBars = bars(2_000_000.0, 1_500_000.0, 1_250_000.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MOVE_EXACT_BOX_variation1_smallBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.125);
        List<Bar> sourceBars = bars(1.0, 0.875);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MOVE_EXACT_BOX_variation2_fractionalBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> sourceBars = bars(0.0, -0.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MOVE_BETWEEN_FIRST_AND_SECOND_BOX_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2);
        List<Bar> sourceBars = bars(-4.0, -7.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MULTI_BRICK_JUMP_variation1_largeBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1_000_000.0, 1);
        List<Bar> sourceBars = bars(-1_000_000.0, 2_000_000.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MULTI_BRICK_JUMP_variation2_smallBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.25);
        List<Bar> sourceBars = bars(1.0, 2.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MULTI_BRICK_JUMP_variation1_fractionalBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 3);
        List<Bar> sourceBars = bars(0.0, -2.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MULTI_BRICK_JUMP_variation2_defaultReversal() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2);
        List<Bar> sourceBars = bars(4.0, -2.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METADATA_BEFORE_FIRST_UP_BRICK_variation1_mixedMetadata() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(4, 2);
        List<Bar> sourceBars = barsWithMetadata(
                new double[] { -2.0, -1.0, 0.0, 2.0 },
                new Double[] { 1.0, 0.0, 3.0, 4.0 },
                new Double[] { 10.0, 20.0, 0.0, 40.0 },
                new long[] { 1, 0, 2, 3 });
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METADATA_BEFORE_FIRST_UP_BRICK_variation2_smallBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.25);
        List<Bar> sourceBars = bars(5.0, 5.0625, 5.125, 5.25);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METADATA_BEFORE_FIRST_DOWN_BRICK_variation1_fractionalBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> sourceBars = bars(0.0, -0.125, -0.25, -0.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METADATA_BEFORE_FIRST_DOWN_BRICK_variation2_negativeRegion() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2);
        List<Bar> sourceBars = bars(-4.0, -4.5, -5.0, -6.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ESTABLISHED_UP_CONTINUATION_variation1_multiBrickContinuation() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(4, 3);
        List<Bar> sourceBars = bars(-4.0, 0.0, 8.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ESTABLISHED_UP_CONTINUATION_variation2_defaultReversal() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.25);
        List<Bar> sourceBars = bars(1.0, 1.25, 1.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ESTABLISHED_DOWN_CONTINUATION_variation1_fractionalBox() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> sourceBars = bars(0.0, -0.5, -1.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ESTABLISHED_DOWN_CONTINUATION_variation2_multiBrickContinuation() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2);
        List<Bar> sourceBars = bars(-2.0, -4.0, -8.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_PULLBACK_BELOW_REVERSAL_variation1_reversalThree() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(4, 3);
        List<Bar> sourceBars = bars(-4.0, 0.0, -8.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_PULLBACK_BELOW_REVERSAL_variation2_defaultReversal() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.25);
        List<Bar> sourceBars = bars(1.0, 1.25, 1.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_BOUNCE_BELOW_REVERSAL_variation1_reversalFour() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 4);
        List<Bar> sourceBars = bars(0.0, -0.5, 1.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_BOUNCE_BELOW_REVERSAL_variation2_defaultReversal() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2);
        List<Bar> sourceBars = bars(-2.0, -4.0, -2.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_TO_DOWN_EXACT_REVERSAL_variation1_twoBoxReversal() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(4, 2);
        List<Bar> sourceBars = bars(-4.0, 0.0, -8.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_TO_DOWN_EXACT_REVERSAL_variation2_threeBoxReversal() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.25, 3);
        List<Bar> sourceBars = bars(1.0, 1.25, 0.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_TO_UP_EXACT_REVERSAL_variation1_oneBoxReversal() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> sourceBars = bars(0.0, -0.5, 0.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_TO_UP_EXACT_REVERSAL_variation2_twoBoxReversal() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2);
        List<Bar> sourceBars = bars(-2.0, -4.0, 0.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_TO_DOWN_REVERSAL_OVERSHOOT_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(4, 3);
        List<Bar> sourceBars = bars(-4.0, 0.0, -16.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_TO_UP_REVERSAL_OVERSHOOT_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.25);
        List<Bar> sourceBars = bars(1.0, 0.75, 1.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BOX_REVERSAL_AMOUNT_variation1_upToDown() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> sourceBars = bars(0.0, 0.5, 0.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BOX_REVERSAL_AMOUNT_variation2_downToUp() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2, 1);
        List<Bar> sourceBars = bars(-2.0, -4.0, -2.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTIPLE_DIRECTION_CYCLES_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(4, 1);
        List<Bar> sourceBars = bars(-4.0, 4.0, -8.0, 8.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_CONTRIBUTIONS_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.25);
        List<Bar> sourceBars = barsWithMetadata(
                new double[] { 1.0, 1.125, 1.25 },
                new Double[] { null, 2.0, null },
                new Double[] { 10.0, null, 30.0 },
                new long[] { 1, 2, 3 });
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_ZERO_AND_NONZERO_METADATA_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 3);
        List<Bar> sourceBars = barsWithMetadata(
                new double[] { 0.0, -0.25, -0.5 },
                new Double[] { 0.0, 2.5, 0.0 },
                new Double[] { 4.0, 0.0, 6.0 },
                new long[] { 0, 5, 0 });
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_BRICK_METADATA_SENTINEL_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(2);
        List<Bar> sourceBars = barsWithMetadata(
                new double[] { -4.0, 2.0 },
                new Double[] { 3.0, 7.0 },
                new Double[] { 30.0, 70.0 },
                new long[] { 2, 5 });
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BRICK_TIMES_ADVANCE_BEYOND_SOURCE_END_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(4, 2);
        List<Bar> sourceBars = bars(-4.0, 12.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SOURCE_TIME_RESUMES_AFTER_QUIET_BARS_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.25, 3);
        List<Bar> sourceBars = bars(1.0, 1.25, 1.25, 1.375, 1.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_PRICES_AND_ZERO_CROSSING_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> sourceBars = bars(-1.0, 0.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_BOX_SIZE_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.5);
        List<Bar> sourceBars = bars(-1.0, -0.5, 0.5);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_REVERSAL_CONSTRUCTOR_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(4);
        List<Bar> sourceBars = bars(-4.0, 0.0, -6.0, -8.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SMALL_POSITIVE_SCALING_SAFE_BOX_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(0.000000125);
        List<Bar> sourceBars = bars(0.000001, 0.000001125);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_SCALING_SAFE_BOX_variation1() {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(1_000_000_000_000.0, 2);
        List<Bar> sourceBars = bars(0.0, 1_000_000_000_000.0);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
