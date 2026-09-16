import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private static final Duration PERIOD = Duration.ofSeconds(60);
    private static final Instant FIRST_END = Instant.parse("2020-01-01T00:01:00Z");

    private void assertMetamorphicRelationFor(Fixture source) {
        Fixture followUp = generateFollowUp(source);
        List<Bar> sourceOutput = source.receiver.aggregate(source.bars);
        List<Bar> followUpOutput = followUp.receiver.aggregate(followUp.bars);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private Fixture generateFollowUp(Fixture source) {
        List<Bar> scaledBars = new ArrayList<>();
        for (Bar bar : source.bars) {
            Num factor = bar.numFactory().numOf(2);
            Num volume = bar.getVolume();
            scaledBars.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    bar.getOpenPrice().multipliedBy(factor),
                    bar.getHighPrice().multipliedBy(factor),
                    bar.getLowPrice().multipliedBy(factor),
                    bar.getClosePrice().multipliedBy(factor),
                    volume,
                    bar.getAmount().multipliedBy(factor),
                    bar.getTrades()));
        }
        Number scaledBoxSize = BigDecimal.valueOf(source.boxSize.doubleValue()).multiply(BigDecimal.valueOf(2));
        RenkoBarAggregator receiver = source.defaultConstructor
                ? new RenkoBarAggregator(scaledBoxSize)
                : new RenkoBarAggregator(scaledBoxSize, source.reversalAmount);
        return new Fixture(receiver, scaledBars, scaledBoxSize, source.reversalAmount, source.defaultConstructor);
    }

    private void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        assertEquals(sourceOutput.size(), followUpOutput.size());
        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);
            Num factor = source.numFactory().numOf(2);

            assertEquals(source.getBeginTime(), followUp.getBeginTime());
            assertEquals(source.getEndTime(), followUp.getEndTime());
            assertEquals(source.getTimePeriod(), followUp.getTimePeriod());
            assertEquals(source.getVolume(), followUp.getVolume());
            assertEquals(source.getTrades(), followUp.getTrades());

            assertEquals(source.getOpenPrice().multipliedBy(factor), followUp.getOpenPrice());
            assertEquals(source.getHighPrice().multipliedBy(factor), followUp.getHighPrice());
            assertEquals(source.getLowPrice().multipliedBy(factor), followUp.getLowPrice());
            assertEquals(source.getClosePrice().multipliedBy(factor), followUp.getClosePrice());
            assertEquals(source.getAmount().multipliedBy(factor), followUp.getAmount());

            assertEquals(source.getClosePrice().isGreaterThan(source.getOpenPrice()),
                    followUp.getClosePrice().isGreaterThan(followUp.getOpenPrice()));
            assertTrue(source.getClosePrice().isGreaterThan(source.getOpenPrice())
                    || source.getClosePrice().isLessThan(source.getOpenPrice()));
        }
    }

    private Fixture fixture(double[] closes, Number boxSize, int reversalAmount, boolean defaultConstructor,
            Double[] volumes, double[] amounts, long[] trades) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            Num open = DecimalNum.valueOf(close - 1);
            Num high = DecimalNum.valueOf(close + 1);
            Num low = DecimalNum.valueOf(close - 2);
            Num closeNum = DecimalNum.valueOf(close);
            Num volume = volumes != null && volumes[i] == null ? null
                    : DecimalNum.valueOf(volumes == null ? i + 1 : volumes[i]);
            Num amount = DecimalNum.valueOf(amounts == null ? (i + 1) * 10 : amounts[i]);
            long tradeCount = trades == null ? i + 1 : trades[i];
            Instant end = FIRST_END.plus(PERIOD.multipliedBy(i));
            bars.add(new BaseBar(PERIOD, end.minus(PERIOD), end, open, high, low, closeNum, volume, amount, tradeCount));
        }
        RenkoBarAggregator receiver = defaultConstructor
                ? new RenkoBarAggregator(boxSize)
                : new RenkoBarAggregator(boxSize, reversalAmount);
        return new Fixture(receiver, bars, boxSize, reversalAmount, defaultConstructor);
    }

    private static final class Fixture {
        private final RenkoBarAggregator receiver;
        private final List<Bar> bars;
        private final Number boxSize;
        private final int reversalAmount;
        private final boolean defaultConstructor;

        private Fixture(RenkoBarAggregator receiver, List<Bar> bars, Number boxSize, int reversalAmount,
                boolean defaultConstructor) {
            this.receiver = receiver;
            this.bars = bars;
            this.boxSize = boxSize;
            this.reversalAmount = reversalAmount;
            this.defaultConstructor = defaultConstructor;
        }
    }

    @Test
    void EMPTY_INPUT_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] {}, 10, 2, false, null, null, null));
    }

    @Test
    void SINGLETON_ANCHOR_ONLY_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100 }, 10, 2, false, null, null, null));
    }

    @Test
    void TWO_BAR_FLAT_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 100 }, 10, 2, false, null, null, null));
    }

    @Test
    void SUB_BOX_UP_MOVE_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 109 }, 10, 2, false, null, null, null));
    }

    @Test
    void SUB_BOX_DOWN_MOVE_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 91 }, 10, 2, false, null, null, null));
    }

    @Test
    void EXACT_INITIAL_UP_BOX_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110 }, 10, 2, false, null, null, null));
    }

    @Test
    void EXACT_INITIAL_DOWN_BOX_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 90 }, 10, 2, false, null, null, null));
    }

    @Test
    void TWO_UP_BRICKS_ONE_SOURCE_BAR_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 120 }, 10, 2, false,
                new Double[] { 3d, 7d }, new double[] { 30, 70 }, new long[] { 2, 5 }));
    }

    @Test
    void TWO_DOWN_BRICKS_ONE_SOURCE_BAR_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 80 }, 10, 2, false,
                new Double[] { 4d, 8d }, new double[] { 40, 80 }, new long[] { 1, 6 }));
    }

    @Test
    void GRADUAL_UP_CONTINUATION_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 120, 130 }, 10, 2, false, null, null, null));
    }

    @Test
    void GRADUAL_DOWN_CONTINUATION_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 90, 80, 70 }, 10, 2, false, null, null, null));
    }

    @Test
    void UP_CONTINUATION_AFTER_PARTIAL_RETRACE_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 120, 115, 130 }, 10, 2, false, null, null, null));
    }

    @Test
    void DOWN_CONTINUATION_AFTER_PARTIAL_RETRACE_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 80, 85, 70 }, 10, 2, false, null, null, null));
    }

    @Test
    void UP_RETRACEMENT_ONE_UNIT_BELOW_REVERSAL_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 91 }, 10, 2, false, null, null, null));
    }

    @Test
    void DOWN_RETRACEMENT_ONE_UNIT_BELOW_REVERSAL_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 90, 109 }, 10, 2, false, null, null, null));
    }

    @Test
    void EXACT_UP_TO_DOWN_REVERSAL_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 90 }, 10, 2, false, null, null, null));
    }

    @Test
    void EXACT_DOWN_TO_UP_REVERSAL_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 90, 110 }, 10, 2, false, null, null, null));
    }

    @Test
    void OVERSHOOT_UP_TO_DOWN_REVERSAL_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 80 }, 10, 2, false, null, null, null));
    }

    @Test
    void OVERSHOOT_DOWN_TO_UP_REVERSAL_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 90, 120 }, 10, 2, false, null, null, null));
    }

    @Test
    void REVERSAL_AMOUNT_ONE_EXACT_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 100 }, 10, 1, false, null, null, null));
    }

    @Test
    void REVERSAL_AMOUNT_THREE_NOT_REACHED_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 90 }, 10, 3, false, null, null, null));
    }

    @Test
    void REVERSAL_AMOUNT_THREE_EXACT_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 80 }, 10, 3, false, null, null, null));
    }

    @Test
    void REVERSAL_AMOUNT_FOUR_EXACT_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 70 }, 10, 4, false, null, null, null));
    }

    @Test
    void DEFAULT_CONSTRUCTOR_REVERSAL_TWO_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 90 }, 10, 2, true, null, null, null));
    }

    @Test
    void PENDING_METADATA_BEFORE_FIRST_BRICK_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 104, 107, 110 }, 10, 2, false,
                new Double[] { 2d, 3d, 5d, 7d }, new double[] { 20, 30, 50, 70 }, new long[] { 1, 2, 3, 4 }));
    }

    @Test
    void PER_SOURCE_METADATA_AFTER_EACH_BRICK_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 120 }, 10, 2, false,
                new Double[] { 2d, 3d, 5d }, new double[] { 20, 30, 50 }, new long[] { 1, 2, 3 }));
    }

    @Test
    void MULTI_BRICK_METADATA_ZEROING_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 130 }, 10, 2, false,
                new Double[] { 4d, 6d }, new double[] { 40, 60 }, new long[] { 2, 5 }));
    }

    @Test
    void NULL_VOLUME_TREATED_AS_NO_ADDITION_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110 }, 10, 2, false,
                new Double[] { null, 6d }, new double[] { 40, 60 }, new long[] { 2, 5 }));
    }

    @Test
    void ZERO_VOLUME_ACCUMULATION_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110 }, 10, 2, false,
                new Double[] { 0d, 0d }, new double[] { 40, 60 }, new long[] { 2, 5 }));
    }

    @Test
    void ZERO_TRADES_ACCUMULATION_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110 }, 10, 2, false,
                null, null, new long[] { 0, 0 }));
    }

    @Test
    void DELAYED_FIRST_BRICK_TIMESTAMP_CATCHUP_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 105, 110 }, 10, 2, false, null, null, null));
    }

    @Test
    void MULTI_BRICK_TIMESTAMPS_ADVANCE_PAST_SOURCE_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 130 }, 10, 2, false, null, null, null));
    }

    @Test
    void MULTI_PHASE_UP_DOWN_UP_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 120, 90, 120 }, 10, 2, false, null, null, null));
    }

    @Test
    void MULTI_PHASE_DOWN_UP_DOWN_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 80, 110, 80 }, 10, 2, false, null, null, null));
    }

    @Test
    void DOWN_CONTINUATION_WITHOUT_DIRECTION_REASSIGNMENT_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 90, 70 }, 10, 2, false, null, null, null));
    }

    @Test
    void UP_CONTINUATION_AFTER_EXACT_UP_START_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 140 }, 10, 2, false, null, null, null));
    }

    @Test
    void FRACTIONAL_BOX_SIZE_EXACT_THRESHOLD_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 10.0, 12.5 }, 2.5, 2, false, null, null, null));
    }

    @Test
    void FRACTIONAL_BOX_SIZE_EXACT_REVERSAL_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 10.0, 12.5, 7.5 }, 2.5, 2, false, null, null, null));
    }

    @Test
    void CLOSE_EQUALS_CURRENT_BRICK_CLOSE_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 110, 110 }, 10, 2, false, null, null, null));
    }

    @Test
    void CLOSE_BETWEEN_UP_BRICK_AND_REVERSAL_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 120, 111 }, 10, 2, false, null, null, null));
    }

    @Test
    void CLOSE_BETWEEN_DOWN_BRICK_AND_REVERSAL_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 80, 89 }, 10, 2, false, null, null, null));
    }

    @Test
    void FIRST_EMISSION_AFTER_LONG_FLAT_PREFIX_scaleTwo() {
        assertMetamorphicRelationFor(fixture(new double[] { 100, 100, 100, 100, 110 }, 10, 2, false, null, null, null));
    }
}
