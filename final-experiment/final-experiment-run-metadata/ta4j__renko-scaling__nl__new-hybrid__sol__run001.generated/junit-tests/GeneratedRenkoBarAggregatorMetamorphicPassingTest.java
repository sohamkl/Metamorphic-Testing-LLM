import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_END = Instant.parse("2020-01-01T00:01:00Z");
    private static final BigDecimal SCALE = new BigDecimal("2");

    private static final class SourceCase {
        private final RenkoBarAggregator receiver;
        private final List<Bar> bars;
        private final BigDecimal boxSize;
        private final int reversalAmount;
        private final boolean defaultConstructor;

        private SourceCase(RenkoBarAggregator receiver, List<Bar> bars, BigDecimal boxSize, int reversalAmount,
                boolean defaultConstructor) {
            this.receiver = receiver;
            this.bars = bars;
            this.boxSize = boxSize;
            this.reversalAmount = reversalAmount;
            this.defaultConstructor = defaultConstructor;
        }
    }

    private static SourceCase sourceCase(boolean defaultConstructor, String boxSize, int reversalAmount,
            double[] closes) {
        return sourceCase(defaultConstructor, boxSize, reversalAmount, closes, standardVolumes(closes.length),
                standardAmounts(closes.length), standardTrades(closes.length), 0);
    }

    private static SourceCase sourceCase(boolean defaultConstructor, String boxSize, int reversalAmount,
            double[] closes, int ohlcStyle) {
        return sourceCase(defaultConstructor, boxSize, reversalAmount, closes, standardVolumes(closes.length),
                standardAmounts(closes.length), standardTrades(closes.length), ohlcStyle);
    }

    private static SourceCase sourceCase(boolean defaultConstructor, String boxSize, int reversalAmount,
            double[] closes, Double[] volumes, double[] amounts, long[] trades, int ohlcStyle) {
        Assertions.assertEquals(closes.length, volumes.length);
        Assertions.assertEquals(closes.length, amounts.length);
        Assertions.assertEquals(closes.length, trades.length);

        BigDecimal box = new BigDecimal(boxSize);
        int effectiveReversalAmount = defaultConstructor ? 2 : reversalAmount;
        RenkoBarAggregator receiver = defaultConstructor
                ? new RenkoBarAggregator(box)
                : new RenkoBarAggregator(box, reversalAmount);

        List<Bar> bars = new ArrayList<Bar>();
        for (int i = 0; i < closes.length; i++) {
            bars.add(createBar(i, closes[i], volumes[i], amounts[i], trades[i], ohlcStyle));
        }

        return new SourceCase(receiver, bars, box, effectiveReversalAmount, defaultConstructor);
    }

    private static Bar createBar(int index, double close, Double volume, double amount, long trades, int style) {
        double open;
        double high;
        double low;

        if (style == 0) {
            open = close;
            high = close;
            low = close;
        } else if (style == 1) {
            open = close - 0.25;
            high = close + 1.0;
            low = close - 1.0;
        } else {
            open = close + 0.25;
            high = close + 2.0;
            low = close - 2.0;
        }

        Instant endTime = BASE_END.plus(PERIOD.multipliedBy(index));
        Instant beginTime = endTime.minus(PERIOD);
        Num volumeNum = volume == null ? null : num(volume.doubleValue());

        return new BaseBar(PERIOD, beginTime, endTime, num(open), num(high), num(low), num(close), volumeNum,
                num(amount), trades);
    }

    private static Double[] standardVolumes(int length) {
        Double[] values = new Double[length];
        for (int i = 0; i < length; i++) {
            values[i] = Double.valueOf(i + 1.0);
        }
        return values;
    }

    private static double[] standardAmounts(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 10.0 + i * 3.0;
        }
        return values;
    }

    private static long[] standardTrades(int length) {
        long[] values = new long[length];
        for (int i = 0; i < length; i++) {
            values[i] = i + 1L;
        }
        return values;
    }

    private static DecimalNum num(double value) {
        return DecimalNum.valueOf(BigDecimal.valueOf(value));
    }

    private static SourceCase generateFollowUp(SourceCase source) {
        BigDecimal scaledBoxSize = source.boxSize.multiply(SCALE);
        RenkoBarAggregator followUpReceiver = source.defaultConstructor
                ? new RenkoBarAggregator(scaledBoxSize)
                : new RenkoBarAggregator(scaledBoxSize, source.reversalAmount);

        List<Bar> scaledBars = new ArrayList<Bar>();
        for (Bar bar : source.bars) {
            Num factor = bar.numFactory().numOf(SCALE);
            Num scaledAmount = bar.getAmount().multipliedBy(factor);

            scaledBars.add(new BaseBar(bar.getTimePeriod(), bar.getBeginTime(), bar.getEndTime(),
                    bar.getOpenPrice().multipliedBy(factor),
                    bar.getHighPrice().multipliedBy(factor),
                    bar.getLowPrice().multipliedBy(factor),
                    bar.getClosePrice().multipliedBy(factor),
                    bar.getVolume(),
                    scaledAmount,
                    bar.getTrades()));
        }

        return new SourceCase(followUpReceiver, scaledBars, scaledBoxSize, source.reversalAmount,
                source.defaultConstructor);
    }

    private static void assertMetamorphicRelationFor(SourceCase source) {
        SourceCase followUp = generateFollowUp(source);
        List<Bar> sourceOutput = source.receiver.aggregate(source.bars);
        List<Bar> followUpOutput = followUp.receiver.aggregate(followUp.bars);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        Assertions.assertEquals(sourceOutput.size(), followUpOutput.size(), "Renko brick count differs");

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar sourceBrick = sourceOutput.get(i);
            Bar followUpBrick = followUpOutput.get(i);
            Num factor = sourceBrick.numFactory().numOf(SCALE);

            Assertions.assertEquals(sourceBrick.getTimePeriod(), followUpBrick.getTimePeriod(),
                    "Time period mismatch at brick " + i);
            Assertions.assertEquals(sourceBrick.getBeginTime(), followUpBrick.getBeginTime(),
                    "Begin time mismatch at brick " + i);
            Assertions.assertEquals(sourceBrick.getEndTime(), followUpBrick.getEndTime(),
                    "End time mismatch at brick " + i);
            Assertions.assertEquals(sourceBrick.getTrades(), followUpBrick.getTrades(),
                    "Trade count mismatch at brick " + i);

            assertNumericallyEqual(sourceBrick.getVolume(), followUpBrick.getVolume(), "volume", i);
            assertNumericallyEqual(sourceBrick.getOpenPrice().multipliedBy(factor), followUpBrick.getOpenPrice(),
                    "open", i);
            assertNumericallyEqual(sourceBrick.getHighPrice().multipliedBy(factor), followUpBrick.getHighPrice(),
                    "high", i);
            assertNumericallyEqual(sourceBrick.getLowPrice().multipliedBy(factor), followUpBrick.getLowPrice(),
                    "low", i);
            assertNumericallyEqual(sourceBrick.getClosePrice().multipliedBy(factor), followUpBrick.getClosePrice(),
                    "close", i);
            assertNumericallyEqual(sourceBrick.getAmount().multipliedBy(factor), followUpBrick.getAmount(),
                    "amount", i);

            int sourceDirection = sourceBrick.getClosePrice().compareTo(sourceBrick.getOpenPrice());
            int followUpDirection = followUpBrick.getClosePrice().compareTo(followUpBrick.getOpenPrice());
            Assertions.assertEquals(Integer.signum(sourceDirection), Integer.signum(followUpDirection),
                    "Direction mismatch at brick " + i);
        }
    }

    private static void assertNumericallyEqual(Num expected, Num actual, String field, int index) {
        if (expected == null || actual == null) {
            Assertions.assertEquals(expected, actual, field + " nullability mismatch at brick " + index);
        } else {
            Assertions.assertTrue(expected.isEqual(actual),
                    field + " mismatch at brick " + index + ": expected " + expected + " but was " + actual);
        }
    }

    @Test
    public void EMPTY_SOURCE_LIST_variation1_defaultConstructor() {
        assertMetamorphicRelationFor(sourceCase(true, "4", 2, new double[] {}));
    }

    @Test
    public void SINGLE_BAR_INITIALIZATION_ONLY_variation1_explicitZeroAnchor() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 2, new double[] { 0 }, 1));
    }

    @Test
    public void SINGLE_BAR_INITIALIZATION_ONLY_variation2_defaultNegativeAnchor() {
        assertMetamorphicRelationFor(sourceCase(true, "1.5", 2, new double[] { -10 }, 2));
    }

    @Test
    public void MULTIBAR_SUB_BOX_MOVEMENT_variation1_explicitLargeReversal() {
        assertMetamorphicRelationFor(sourceCase(false, "4", 5, new double[] { 20, 22, 18.5, 21 }));
    }

    @Test
    public void MULTIBAR_SUB_BOX_MOVEMENT_variation2_fractionalDefault() {
        assertMetamorphicRelationFor(
                sourceCase(true, "1.5", 2, new double[] { 5, 5.5, 4.25, 5.75, 4.75, 5.25 }, 1));
    }

    @Test
    public void INITIAL_UP_AT_EXACT_BOX_variation1_explicit() {
        assertMetamorphicRelationFor(sourceCase(false, "3", 2, new double[] { 10, 13 }, 2));
    }

    @Test
    public void INITIAL_UP_AT_EXACT_BOX_variation2_defaultZeroAnchor() {
        assertMetamorphicRelationFor(sourceCase(true, "2", 2, new double[] { 0, 2 }));
    }

    @Test
    public void INITIAL_DOWN_AT_EXACT_BOX_variation1_explicitLargeReversal() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 5, new double[] { -5, -7 }, 1));
    }

    @Test
    public void INITIAL_DOWN_AT_EXACT_BOX_variation2_defaultAccumulatedMetadata() {
        assertMetamorphicRelationFor(sourceCase(true, "4", 2, new double[] { 30, 31, 26 }, 2));
    }

    @Test
    public void INITIAL_MULTI_BRICK_UP_MOVE_variation1_fractionalOvershoot() {
        assertMetamorphicRelationFor(sourceCase(false, "1.5", 2, new double[] { 10, 15.25 }));
    }

    @Test
    public void INITIAL_MULTI_BRICK_UP_MOVE_variation2_defaultPositiveAnchor() {
        assertMetamorphicRelationFor(sourceCase(true, "2", 2, new double[] { 100, 106, 106.5 }, 1));
    }

    @Test
    public void INITIAL_MULTI_BRICK_DOWN_MOVE_variation1_explicitLargeReversal() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 4, new double[] { 0, -6.5 }, 2));
    }

    @Test
    public void INITIAL_MULTI_BRICK_DOWN_MOVE_variation2_defaultNegativeAnchor() {
        assertMetamorphicRelationFor(sourceCase(true, "1", 2, new double[] { -10, -13 }));
    }

    @Test
    public void UP_CONTINUATION_AFTER_PENDING_ACCUMULATION_variation1_explicit() {
        assertMetamorphicRelationFor(sourceCase(false, "4", 2, new double[] { 20, 24, 26, 28 }, 1));
    }

    @Test
    public void UP_CONTINUATION_AFTER_PENDING_ACCUMULATION_variation2_fractionalDefault() {
        assertMetamorphicRelationFor(
                sourceCase(true, "1.5", 2, new double[] { 5, 6.5, 7.25, 8, 8.4, 9.5 }, 2));
    }

    @Test
    public void DOWN_CONTINUATION_AFTER_PENDING_ACCUMULATION_variation1_explicit() {
        assertMetamorphicRelationFor(sourceCase(false, "3", 4, new double[] { 20, 17, 15.5, 14 }));
    }

    @Test
    public void DOWN_CONTINUATION_AFTER_PENDING_ACCUMULATION_variation2_defaultZeroAnchor() {
        assertMetamorphicRelationFor(sourceCase(true, "2", 2, new double[] { 0, -2, -3, -4 }, 1));
    }

    @Test
    public void UP_TREND_BELOW_REVERSAL_THRESHOLD_variation1_explicitR2() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 2, new double[] { -10, -8, -11 }, 2));
    }

    @Test
    public void UP_TREND_BELOW_REVERSAL_THRESHOLD_variation2_default() {
        assertMetamorphicRelationFor(sourceCase(true, "4", 2, new double[] { 20, 24, 18 }));
    }

    @Test
    public void DOWN_TREND_BELOW_REVERSAL_THRESHOLD_variation1_explicitR5() {
        assertMetamorphicRelationFor(
                sourceCase(false, "1.5", 5, new double[] { 10, 8.5, 9, 9.5, 10, 15.25 }, 1));
    }

    @Test
    public void DOWN_TREND_BELOW_REVERSAL_THRESHOLD_variation2_default() {
        assertMetamorphicRelationFor(sourceCase(true, "2", 2, new double[] { 10, 8, 11 }, 2));
    }

    @Test
    public void UP_TO_DOWN_EXACT_REVERSAL_variation1_explicitR2() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 2, new double[] { 0, 2, -2 }));
    }

    @Test
    public void UP_TO_DOWN_EXACT_REVERSAL_variation2_defaultNegativeAnchor() {
        assertMetamorphicRelationFor(sourceCase(true, "1.5", 2, new double[] { -10, -8.5, -11.5 }, 1));
    }

    @Test
    public void UP_TO_DOWN_REVERSAL_OVERSHOOT_variation1_explicitR4() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 4, new double[] { 20, 22, 21, 10 }, 2));
    }

    @Test
    public void UP_TO_DOWN_REVERSAL_OVERSHOOT_variation2_defaultFractional() {
        assertMetamorphicRelationFor(
                sourceCase(true, "1.25", 2, new double[] { 5, 6.25, 6.5, 6, 1.5, 1.25 }));
    }

    @Test
    public void DOWN_TO_UP_EXACT_REVERSAL_variation1_explicitR2() {
        assertMetamorphicRelationFor(sourceCase(false, "3", 2, new double[] { 10, 7, 13 }, 1));
    }

    @Test
    public void DOWN_TO_UP_EXACT_REVERSAL_variation2_defaultZeroAnchor() {
        assertMetamorphicRelationFor(sourceCase(true, "2", 2, new double[] { 0, -2, 2 }, 2));
    }

    @Test
    public void DOWN_TO_UP_REVERSAL_OVERSHOOT_variation1_explicitR4() {
        assertMetamorphicRelationFor(sourceCase(false, "1", 4, new double[] { -5, -6, -0.5 }));
    }

    @Test
    public void DOWN_TO_UP_REVERSAL_OVERSHOOT_variation2_defaultIntegral() {
        assertMetamorphicRelationFor(sourceCase(true, "3", 2, new double[] { 20, 17, 27 }, 1));
    }

    @Test
    public void ONE_BOX_REVERSAL_AMOUNT_variation1_upThenDown() {
        assertMetamorphicRelationFor(sourceCase(false, "1.5", 1, new double[] { 5, 6.5, 5 }, 2));
    }

    @Test
    public void ONE_BOX_REVERSAL_AMOUNT_variation2_downThenUp() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 1, new double[] { 0, -2, 0 }));
    }

    @Test
    public void THREE_BOX_REVERSAL_BOUNDARY_TRIPLET_variation1_belowThreshold() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 3, new double[] { 0, 2, -3 }, 1));
    }

    @Test
    public void THREE_BOX_REVERSAL_BOUNDARY_TRIPLET_variation2_exactThreshold() {
        assertMetamorphicRelationFor(sourceCase(false, "1.5", 3, new double[] { -10, -8.5, -13 }, 2));
    }

    @Test
    public void THREE_BOX_REVERSAL_BOUNDARY_TRIPLET_variation3_beyondThreshold() {
        assertMetamorphicRelationFor(sourceCase(false, "4", 3, new double[] { 20, 24, 8 }));
    }

    @Test
    public void MULTIPLE_DIRECTION_CHANGES_variation1_defaultR2() {
        assertMetamorphicRelationFor(
                sourceCase(true, "1.5", 2, new double[] { 5, 8, 5, 2, 5, 5.25 }, 1));
    }

    @Test
    public void MULTIPLE_DIRECTION_CHANGES_variation2_explicitR2() {
        assertMetamorphicRelationFor(
                sourceCase(false, "2", 2, new double[] { 20, 24, 20, 16, 20, 20.5 }, 2));
    }

    @Test
    public void SYNTHETIC_FUTURE_BRICK_TIMESTAMPS_variation1_defaultUpward() {
        assertMetamorphicRelationFor(sourceCase(true, "1", 2, new double[] { 0, 4 }));
    }

    @Test
    public void SYNTHETIC_FUTURE_BRICK_TIMESTAMPS_variation2_explicitDownward() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 2, new double[] { -5, -13 }, 1));
    }

    @Test
    public void SOURCE_TIME_CATCHUP_AND_EQUALITY_variation1_equality() {
        assertMetamorphicRelationFor(sourceCase(true, "2", 2, new double[] { 10, 12, 14 }, 2));
    }

    @Test
    public void SOURCE_TIME_CATCHUP_AND_EQUALITY_variation2_catchup() {
        assertMetamorphicRelationFor(
                sourceCase(false, "1.5", 4, new double[] { 5, 6.5, 7, 8, 8.5, 9.5 }));
    }

    @Test
    public void NULL_VOLUME_ACCUMULATION_variation1_allNull() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 1, new double[] { 10, 10.5, 12 },
                new Double[] { null, null, null }, new double[] { 3, 4, 5 }, new long[] { 1, 2, 3 }, 1));
    }

    @Test
    public void NULL_VOLUME_ACCUMULATION_variation2_mixedVolumes() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 2, new double[] { 0, 0.5, 2 },
                new Double[] { null, 4.0, null }, new double[] { 2, 3, 7 }, new long[] { 1, 3, 5 }, 2));
    }

    @Test
    public void ZERO_AND_SAME_SOURCE_METADATA_SENTINELS_variation1_nonzeroPending() {
        assertMetamorphicRelationFor(sourceCase(true, "1", 2, new double[] { -10, -6 },
                new Double[] { 2.0, 3.0 }, new double[] { 5, 7 }, new long[] { 2, 4 }, 0));
    }

    @Test
    public void ZERO_AND_SAME_SOURCE_METADATA_SENTINELS_variation2_allZero() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 4, new double[] { 20, 20.5, 26.5 },
                new Double[] { 0.0, 0.0, 0.0 }, new double[] { 0, 0, 0 }, new long[] { 0, 0, 0 }, 1));
    }

    @Test
    public void INTRABAR_OHLC_AND_AMOUNT_VARIATION_variation1_wideRanges() {
        assertMetamorphicRelationFor(sourceCase(true, "1.25", 2,
                new double[] { 5, 5.5, 5.75, 6, 6.25, 7.5 },
                new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 },
                new double[] { 2, 7, 3, 11, 5, 13 }, new long[] { 1, 1, 2, 3, 5, 8 }, 2));
    }

    @Test
    public void INTRABAR_OHLC_AND_AMOUNT_VARIATION_variation2_closeCenteredRanges() {
        assertMetamorphicRelationFor(sourceCase(false, "2", 2, new double[] { 20, 20.5, 21, 22 },
                new Double[] { 1.0, 1.0, 1.0, 1.0 }, new double[] { 3, 5, 7, 11 },
                new long[] { 1, 2, 3, 4 }, 1));
    }

    @Test
    public void ZERO_NEGATIVE_AND_FRACTIONAL_PRICE_REGIONS_variation1_zeroAnchor() {
        assertMetamorphicRelationFor(sourceCase(true, "2", 2, new double[] { 0, 2 }, 1));
    }

    @Test
    public void ZERO_NEGATIVE_AND_FRACTIONAL_PRICE_REGIONS_variation2_negativeAnchor() {
        assertMetamorphicRelationFor(sourceCase(false, "1", 4, new double[] { -5, -7 }, 2));
    }

    @Test
    public void ZERO_NEGATIVE_AND_FRACTIONAL_PRICE_REGIONS_variation3_fractionalBoundary() {
        assertMetamorphicRelationFor(sourceCase(true, "0.75", 2, new double[] { 0.25, 0.5, 1.0 }));
    }
}
