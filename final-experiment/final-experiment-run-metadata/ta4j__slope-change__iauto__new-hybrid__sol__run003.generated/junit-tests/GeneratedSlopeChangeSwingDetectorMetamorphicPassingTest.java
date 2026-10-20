import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.indicators.elliott.ElliottSwing;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final double TRANSLATION = 100.0;
    private static final double ABSOLUTE_TOLERANCE = 1.0e-9;
    private static final double RELATIVE_TOLERANCE = 1.0e-12;
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static SlopeChangeSwingDetector detector(
            int window,
            int confirmationBars,
            int atrPeriod,
            double minSlopeChange,
            double minAtrReversal) {
        return new SlopeChangeSwingDetector(
                new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries series(
            String name,
            double[] closes,
            double spread,
            double initialVolume) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + spread;
            lows[i] = closes[i] - spread;
        }
        return bars(name, closes, highs, lows, initialVolume);
    }

    private static BarSeries bars(
            String name,
            double[] closes,
            double[] highs,
            double[] lows,
            double initialVolume) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        for (int i = 0; i < closes.length; i++) {
            double volumeValue = initialVolume == 0.0 ? 0.0 : initialVolume + i * 0.25;
            Num open = result.numFactory().numOf(closes[i]);
            Num high = result.numFactory().numOf(highs[i]);
            Num low = result.numFactory().numOf(lows[i]);
            Num close = result.numFactory().numOf(closes[i]);
            Num volume = result.numFactory().numOf(volumeValue);
            Num amount = result.numFactory().numOf(closes[i] * volumeValue);
            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);
            result.addBar(new BaseBar(
                    BAR_DURATION,
                    begin,
                    end,
                    open,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    i));
        }
        return result;
    }

    private static void exercise(
            SlopeChangeSwingDetector sourceDetector,
            BarSeries sourceSeries,
            int sourceIndex,
            ElliottDegree sourceDegree) {
        SwingDetectorResult sourceOutput =
                sourceDetector.detect(sourceSeries, sourceIndex, sourceDegree);

        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                sourceDetector, sourceSeries, sourceIndex, sourceDegree);

        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput =
                followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SwingDetectorResult sourceOutput,
            SwingDetectorResult followUpOutput) {
        if (sourceOutput == null || followUpOutput == null) {
            throw new AssertionError("Both detector results must be nonnull");
        }

        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();
        if (sourcePivots.size() != followUpPivots.size()) {
            throw new AssertionError("Pivot counts differ: source="
                    + sourcePivots.size() + ", follow-up=" + followUpPivots.size());
        }

        for (int i = 0; i < sourcePivots.size(); i++) {
            SwingPivot source = sourcePivots.get(i);
            SwingPivot followUp = followUpPivots.get(i);

            if (source.index() != followUp.index()) {
                throw new AssertionError("Pivot index differs at position " + i
                        + ": source=" + source.index() + ", follow-up=" + followUp.index());
            }
            if (!Objects.equals(source.type(), followUp.type())) {
                throw new AssertionError("Pivot type differs at position " + i
                        + ": source=" + source.type() + ", follow-up=" + followUp.type());
            }
            assertTranslatedNum(source.price(), followUp.price(), "pivot price", i);
        }

        List<ElliottSwing> sourceSwings = sourceOutput.swings();
        List<ElliottSwing> followUpSwings = followUpOutput.swings();
        if (sourceSwings.size() != followUpSwings.size()) {
            throw new AssertionError("Swing counts differ: source="
                    + sourceSwings.size() + ", follow-up=" + followUpSwings.size());
        }

        for (int i = 0; i < sourceSwings.size(); i++) {
            ElliottSwing source = sourceSwings.get(i);
            ElliottSwing followUp = followUpSwings.get(i);

            if (source.fromIndex() != followUp.fromIndex()) {
                throw new AssertionError("Swing start index differs at position " + i
                        + ": source=" + source.fromIndex()
                        + ", follow-up=" + followUp.fromIndex());
            }
            if (source.toIndex() != followUp.toIndex()) {
                throw new AssertionError("Swing end index differs at position " + i
                        + ": source=" + source.toIndex()
                        + ", follow-up=" + followUp.toIndex());
            }
            if (!Objects.equals(source.degree(), followUp.degree())) {
                throw new AssertionError("Swing degree differs at position " + i
                        + ": source=" + source.degree()
                        + ", follow-up=" + followUp.degree());
            }

            assertTranslatedNum(source.fromPrice(), followUp.fromPrice(), "swing start price", i);
            assertTranslatedNum(source.toPrice(), followUp.toPrice(), "swing end price", i);
        }
    }

    private static void assertTranslatedNum(
            Num source,
            Num followUp,
            String field,
            int position) {
        double expected = source.doubleValue() + TRANSLATION;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(
                ABSOLUTE_TOLERANCE,
                Math.max(Math.abs(expected), Math.abs(actual)) * RELATIVE_TOLERANCE);

        if (!Double.isFinite(expected)
                || !Double.isFinite(actual)
                || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Translated " + field + " differs at position " + position
                    + ": expected=" + expected
                    + ", actual=" + actual
                    + ", tolerance=" + tolerance);
        }
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation1_minimumInteger() {
        exercise(new SlopeChangeSwingDetector(2),
                series("below-min", new double[] { 10, 12, 11, 9, 8 }, 0.0, 0.0),
                Integer.MIN_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation2_negativeOne() {
        exercise(detector(5, 2, 3, 0.25, 0.0),
                series("below-negative", new double[] { 30.5, 29.5, 28.5, 29.0, 30.0, 31.0 }, 0.4, 7.0),
                -1, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void SERIES_TOO_SHORT_FOR_ANY_CANDIDATE_variation1_largeWindow() {
        exercise(new SlopeChangeSwingDetector(10),
                series("short-large-window", new double[] { 1000, 1001, 1002, 1003, 1004 }, 2.0, 1.0),
                3, ElliottDegree.PRIMARY);
    }

    @Test
    public void SERIES_TOO_SHORT_FOR_ANY_CANDIDATE_variation2_indexTruncatesPrefix() {
        exercise(detector(2, 2, 2, 0.0, 0.5),
                series("short-prefix", new double[] { 20, 19, 18, 17, 18, 19, 20 }, 0.0, 0.0),
                2, ElliottDegree.MINUTE);
    }

    @Test
    public void FIRST_SCANNABLE_CANDIDATE_NO_REVERSAL_variation1_bothRising() {
        exercise(new SlopeChangeSwingDetector(3),
                series("one-candidate-rise", new double[] { 10.0, 10.5, 11.0, 12.0, 13.5, 15.0, 16.5, 18.0 },
                        0.3, 2.0),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void FIRST_SCANNABLE_CANDIDATE_NO_REVERSAL_variation2_bothFalling() {
        exercise(detector(4, 1, 3, 0.5, 0.5),
                series("one-candidate-fall",
                        new double[] { 500, 495, 490, 485, 470, 460, 450, 440 }, 1.5, 3.0),
                Integer.MAX_VALUE, ElliottDegree.CYCLE);
    }

    @Test
    public void SLOPE_CHANGE_STRICTLY_BELOW_MINIMUM_variation1_smallHighReversal() {
        exercise(detector(2, 1, 2, 5.0, 0.0),
                series("below-q-high", new double[] { 40.0, 41.0, 40.8, 40.5 }, 0.0, 0.0),
                3, ElliottDegree.MINOR);
    }

    @Test
    public void SLOPE_CHANGE_STRICTLY_BELOW_MINIMUM_variation2_smallLowReversal() {
        exercise(detector(3, 1, 3, 2.0, 0.5),
                series("below-q-low",
                        new double[] { 25.0, 24.5, 24.0, 24.1, 24.3, 24.5 }, 0.25, 4.0),
                5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void SLOPE_CHANGE_EXACTLY_AT_MINIMUM_variation1_exactFive() {
        exercise(detector(2, 1, 2, 5.0, 0.5),
                bars("exact-q",
                        new double[] { 10, 12, 11, 8 },
                        new double[] { 10, 13, 12, 9 },
                        new double[] { 10, 11, 10, 7 },
                        2.0),
                3, ElliottDegree.MINOR);
    }

    @Test
    public void BOTH_SLOPES_POSITIVE_variation1_differentPositiveSlopes() {
        exercise(detector(2, 1, 2, 1.0, 1.0),
                series("positive-positive", new double[] { 10, 12, 12.5, 16 }, 0.0, 0.0),
                3, ElliottDegree.MINUTE);
    }

    @Test
    public void BOTH_SLOPES_NEGATIVE_variation1_differentNegativeSlopes() {
        exercise(new SlopeChangeSwingDetector(2),
                series("negative-negative", new double[] { 20.0, 18.0, 17.5, 14.0 }, 0.5, 5.0),
                100, ElliottDegree.MINOR);
    }

    @Test
    public void ZERO_BEFORE_SLOPE_NOT_A_REVERSAL_variation1_flatThenFalling() {
        exercise(detector(3, 1, 2, 0.5, 0.0),
                series("zero-before", new double[] { 50, 50, 50, 48, 46, 44 }, 1.0, 2.0),
                Integer.MAX_VALUE, ElliottDegree.CYCLE);
    }

    @Test
    public void ZERO_AFTER_SLOPE_NOT_A_REVERSAL_variation1_risingThenFlat() {
        exercise(new SlopeChangeSwingDetector(2),
                series("zero-after", new double[] { 10, 12, 15, 15, 15 }, 0.0, 0.0),
                4, ElliottDegree.MINOR);
    }

    @Test
    public void HIGH_REVERSAL_FAILS_FIRST_CONFIRMATION_variation1_confirmationTurnsFlat() {
        exercise(detector(2, 2, 2, 0.0, 0.5),
                series("high-confirm-flat", new double[] { 10, 13, 12, 11, 11 }, 0.4, 3.0),
                4, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void HIGH_REVERSAL_FAILS_FIRST_CONFIRMATION_variation2_confirmationTurnsPositive() {
        exercise(detector(3, 2, 3, 0.0, 0.5),
                series("high-confirm-positive",
                        new double[] { 100, 103, 106, 104, 102, 100, 101, 103 }, 1.0, 4.0),
                7, ElliottDegree.MINOR);
    }

    @Test
    public void LOW_REVERSAL_FAILS_LATER_CONFIRMATION_variation1_secondSlopeZero() {
        exercise(detector(2, 2, 2, 0.0, 0.5),
                series("low-confirm-zero", new double[] { 13, 10, 11, 12, 12 }, 0.0, 0.0),
                4, ElliottDegree.MINUTE);
    }

    @Test
    public void LOW_REVERSAL_FAILS_LATER_CONFIRMATION_variation2_secondSlopeNegative() {
        exercise(detector(3, 2, 3, 0.0, 0.0),
                series("low-confirm-negative",
                        new double[] { 110, 106, 102, 104, 106, 108, 107, 105 }, 0.7, 6.0),
                20, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ACCEPTED_HIGH_EXTREME_AT_START_variation1_windowTwo() {
        exercise(detector(2, 1, 2, 0.0, 0.5),
                bars("high-at-start",
                        new double[] { 10, 13, 12, 10 },
                        new double[] { 10, 16, 14, 12 },
                        new double[] { 9, 12, 11, 9 },
                        1.0),
                Integer.MAX_VALUE, ElliottDegree.MINUTE);
    }

    @Test
    public void ACCEPTED_HIGH_EXTREME_AT_START_variation2_windowFour() {
        exercise(new SlopeChangeSwingDetector(4),
                bars("high-start-wide",
                        new double[] { 50, 52, 54, 56, 55, 53, 51, 49, 47, 45 },
                        new double[] { 51, 53, 55, 61, 58, 56, 54, 52, 50, 48 },
                        new double[] { 49, 51, 53, 55, 54, 52, 50, 48, 46, 44 },
                        0.0),
                9, ElliottDegree.MINOR);
    }

    @Test
    public void ACCEPTED_HIGH_EXTREME_IN_INTERIOR_variation1_uniqueIntrabarHigh() {
        exercise(detector(3, 1, 3, 0.0, 0.5),
                bars("high-interior",
                        new double[] { 20, 22, 24, 23, 21, 19 },
                        new double[] { 21, 23, 25, 30, 24, 22 },
                        new double[] { 19, 21, 23, 22, 20, 18 },
                        2.0),
                5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ACCEPTED_HIGH_EXTREME_IN_INTERIOR_variation2_fractionalPrices() {
        exercise(new SlopeChangeSwingDetector(3),
                bars("high-interior-fractional",
                        new double[] { 70.25, 71.50, 72.75, 72.0, 70.5, 69.0, 67.5 },
                        new double[] { 70.75, 72.0, 73.25, 78.5, 73.0, 71.0, 69.0 },
                        new double[] { 69.75, 71.0, 72.25, 71.5, 70.0, 68.5, 67.0 },
                        3.5),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void ACCEPTED_HIGH_EXTREME_AT_END_variation1_endpointIncluded() {
        exercise(detector(3, 1, 2, 0.0, 0.0),
                bars("high-at-end",
                        new double[] { 10, 12, 14, 13, 11, 9 },
                        new double[] { 11, 13, 15, 16, 20, 10 },
                        new double[] { 9, 11, 13, 12, 10, 8 },
                        0.0),
                5, ElliottDegree.MINUTE);
    }

    @Test
    public void TIED_HIGH_EXTREMES_KEEP_EARLIEST_variation1_twoTiedHighs() {
        exercise(new SlopeChangeSwingDetector(3),
                bars("tied-high",
                        new double[] { 30, 32, 34, 33, 31, 29, 27 },
                        new double[] { 31, 33, 40, 40, 35, 31, 29 },
                        new double[] { 29, 31, 33, 32, 30, 28, 26 },
                        1.0),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void TIED_HIGH_EXTREMES_KEEP_EARLIEST_variation2_threeTiedHighs() {
        exercise(detector(4, 1, 3, 0.0, 0.5),
                bars("three-tied-highs",
                        new double[] { 100, 102, 104, 106, 105, 103, 101, 99 },
                        new double[] { 101, 103, 105, 112, 112, 112, 104, 102 },
                        new double[] { 99, 101, 103, 105, 104, 102, 100, 98 },
                        4.0),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ACCEPTED_LOW_EXTREME_AT_START_variation1_windowTwo() {
        exercise(new SlopeChangeSwingDetector(2),
                bars("low-at-start",
                        new double[] { 15, 12, 13, 15, 17 },
                        new double[] { 16, 13, 14, 16, 18 },
                        new double[] { 14, 8, 11, 14, 16 },
                        0.0),
                4, ElliottDegree.MINOR);
    }

    @Test
    public void ACCEPTED_LOW_EXTREME_AT_START_variation2_windowFour() {
        exercise(detector(4, 1, 3, 0.0, 0.5),
                bars("low-start-wide",
                        new double[] { 60, 57, 54, 51, 52, 55, 58, 61 },
                        new double[] { 61, 58, 55, 52, 53, 56, 59, 62 },
                        new double[] { 59, 56, 53, 45, 49, 52, 55, 58 },
                        3.0),
                7, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ACCEPTED_LOW_EXTREME_IN_INTERIOR_variation1_uniqueIntrabarLow() {
        exercise(new SlopeChangeSwingDetector(3),
                bars("low-interior",
                        new double[] { 40, 38, 36, 37, 39, 41, 43 },
                        new double[] { 41, 39, 37, 38, 40, 42, 44 },
                        new double[] { 39, 37, 35, 30, 37, 40, 42 },
                        2.0),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void ACCEPTED_LOW_EXTREME_IN_INTERIOR_variation2_fractionalLow() {
        exercise(detector(3, 1, 2, 0.0, 0.5),
                bars("low-interior-fractional",
                        new double[] { 80.75, 79.50, 78.25, 79.0, 80.5, 82.0 },
                        new double[] { 81.25, 80.0, 78.75, 79.5, 81.0, 82.5 },
                        new double[] { 80.25, 79.0, 77.75, 72.5, 80.0, 81.5 },
                        0.0),
                5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void TIED_LOW_EXTREMES_KEEP_EARLIEST_variation1_twoTiedLows() {
        exercise(new SlopeChangeSwingDetector(3),
                bars("tied-low",
                        new double[] { 50, 48, 46, 47, 49, 51 },
                        new double[] { 51, 49, 47, 48, 50, 52 },
                        new double[] { 49, 47, 40, 40, 46, 50 },
                        1.5),
                100, ElliottDegree.MINOR);
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_MAGNITUDE_variation1_highWithLargeMultiplier() {
        exercise(detector(3, 1, 2, 0.0, 100.0),
                series("first-high-large-m", new double[] { 1000, 1010, 1020, 1010, 995, 980 }, 5.0, 10.0),
                Integer.MAX_VALUE, ElliottDegree.PRIMARY);
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_MAGNITUDE_variation2_lowWithLargeMultiplier() {
        exercise(detector(2, 1, 1, 0.0, 50.0),
                series("first-low-large-m", new double[] { 30, 25, 26, 28 }, 0.0, 0.0),
                3, ElliottDegree.MINOR);
    }

    @Test
    public void ZERO_MULTIPLIER_BYPASSES_MAGNITUDE_variation1_highThenLow() {
        exercise(detector(2, 1, 2, 0.0, 0.0),
                series("zero-m-high-low", new double[] { 20, 22, 23, 21, 19, 18, 19, 20 }, 0.4, 2.0),
                7, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ZERO_MULTIPLIER_BYPASSES_MAGNITUDE_variation2_lowThenHigh() {
        exercise(detector(2, 1, 2, 0.0, 0.0),
                series("zero-m-low-high", new double[] { 30, 28, 27, 29, 31, 32, 31, 29 }, 1.0, 4.0),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void ATR_REVERSAL_STRICTLY_BELOW_THRESHOLD_variation1_largeThreshold() {
        exercise(detector(2, 1, 1, 0.0, 20.0),
                series("atr-below", new double[] { 50, 55, 56, 54, 53, 52, 53, 54 }, 3.0, 0.0),
                7, ElliottDegree.MINUTE);
    }

    @Test
    public void ATR_REVERSAL_STRICTLY_BELOW_THRESHOLD_variation2_fractionalMove() {
        exercise(detector(3, 1, 2, 0.0, 10.0),
                series("atr-below-fractional",
                        new double[] { 100.0, 101.0, 102.0, 101.5, 101.0, 100.5, 101.0, 101.5, 102.0 },
                        2.5, 5.0),
                8, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ATR_REVERSAL_EXACTLY_AT_THRESHOLD_variation1_unitTrueRange() {
        exercise(detector(2, 1, 1, 0.0, 2.0),
                bars("atr-exact",
                        new double[] { 10, 12, 13, 11, 9, 8, 9, 11 },
                        new double[] { 11, 13, 14, 12, 10, 9, 10, 12 },
                        new double[] { 9, 11, 12, 10, 8, 7, 8, 10 },
                        1.0),
                Integer.MAX_VALUE, ElliottDegree.CYCLE);
    }

    @Test
    public void ATR_REVERSAL_ABOVE_THRESHOLD_variation1_largeAlternatingMove() {
        exercise(new SlopeChangeSwingDetector(2),
                series("atr-above-default", new double[] { 100, 120, 125, 110, 90, 80, 95, 115 }, 1.0, 0.0),
                7, ElliottDegree.MINOR);
    }

    @Test
    public void ATR_REVERSAL_ABOVE_THRESHOLD_variation2_customMultiplier() {
        exercise(detector(3, 1, 2, 0.0, 0.25),
                series("atr-above-custom",
                        new double[] { 200, 210, 220, 215, 200, 185, 190, 205, 220 }, 2.0, 6.0),
                8, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void SAME_TYPE_HIGH_REPLACED_BY_HIGHER_HIGH_variation1_laterHigherPeak() {
        exercise(detector(2, 1, 2, 2.0, 0.0),
                bars("replace-high",
                        new double[] { 20, 30, 29, 28, 28.5, 40, 50, 49, 47 },
                        new double[] { 21, 32, 31, 30, 30, 42, 55, 52, 49 },
                        new double[] { 19, 29, 28, 27, 27.5, 39, 49, 48, 46 },
                        2.0),
                8, ElliottDegree.PRIMARY);
    }

    @Test
    public void SAME_TYPE_HIGH_NOT_REPLACED_variation1_laterLowerPeak() {
        exercise(detector(2, 1, 2, 2.0, 0.0),
                bars("retain-high",
                        new double[] { 20, 40, 39, 38, 38.5, 32, 35, 34, 32 },
                        new double[] { 21, 45, 42, 40, 40, 34, 38, 36, 34 },
                        new double[] { 19, 39, 38, 37, 37.5, 31, 34, 33, 31 },
                        0.0),
                8, ElliottDegree.MINUTE);
    }

    @Test
    public void SAME_TYPE_LOW_REPLACED_BY_LOWER_LOW_variation1_laterLowerTrough() {
        exercise(detector(2, 1, 2, 2.0, 0.0),
                bars("replace-low",
                        new double[] { 60, 50, 51, 52, 51.5, 40, 30, 31, 33 },
                        new double[] { 61, 51, 52, 53, 52.5, 41, 31, 32, 34 },
                        new double[] { 59, 47, 49, 50, 49.5, 38, 25, 29, 32 },
                        3.0),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void SAME_TYPE_LOW_NOT_REPLACED_variation1_laterHigherTrough() {
        exercise(detector(2, 1, 2, 2.0, 0.0),
                bars("retain-low",
                        new double[] { 70, 40, 41, 42, 41.5, 50, 45, 46, 48 },
                        new double[] { 71, 41, 42, 43, 42.5, 51, 46, 47, 49 },
                        new double[] { 69, 35, 39, 40, 39.5, 49, 42, 44, 47 },
                        1.0),
                8, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void HIGH_THEN_LOW_CREATES_SWING_variation1_twoAlternatingPivots() {
        exercise(detector(2, 1, 2, 0.0, 0.0),
                series("high-low", new double[] { 10, 13, 14, 12, 9, 8, 10, 12 }, 0.5, 0.0),
                7, ElliottDegree.MINOR);
    }

    @Test
    public void LOW_THEN_HIGH_CREATES_SWING_variation1_twoAlternatingPivots() {
        exercise(detector(2, 1, 2, 0.0, 0.0),
                series("low-high", new double[] { 20, 17, 16, 18, 21, 22, 20, 18 }, 0.8, 5.0),
                7, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void THREE_ALTERNATING_PIVOTS_CREATE_TWO_SWINGS_variation1_highLowHigh() {
        exercise(detector(2, 1, 2, 0.0, 0.0),
                series("high-low-high",
                        new double[] { 20, 22, 23, 21, 19, 18, 20, 22, 23, 21, 19 }, 0.0, 0.0),
                10, ElliottDegree.PRIMARY);
    }

    @Test
    public void THREE_ALTERNATING_PIVOTS_CREATE_TWO_SWINGS_variation2_lowHighLow() {
        exercise(detector(2, 1, 2, 0.0, 0.0),
                series("low-high-low",
                        new double[] { 30, 28, 27, 29, 31, 32, 30, 28, 27, 29, 31 }, 1.25, 2.0),
                10, ElliottDegree.MINUTE);
    }

    @Test
    public void INDEX_EXCLUDES_FUTURE_REVERSAL_variation1_prefixBeforeHighConfirmation() {
        exercise(new SlopeChangeSwingDetector(3),
                series("exclude-future-high",
                        new double[] { 10, 12, 14, 16, 15, 13, 11, 9, 8 }, 0.5, 3.0),
                3, ElliottDegree.MINOR);
    }

    @Test
    public void INDEX_EXCLUDES_FUTURE_REVERSAL_variation2_prefixBeforeLowConfirmation() {
        exercise(detector(4, 2, 3, 0.0, 0.5),
                series("exclude-future-low",
                        new double[] { 100, 96, 92, 88, 86, 88, 92, 96, 100, 104, 108 }, 2.0, 7.0),
                5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void INDEX_AT_OR_BEYOND_END_USES_FULL_SERIES_variation1_atEnd() {
        BarSeries source = series("full-at-end",
                new double[] { 10, 13, 14, 12, 10, 8, 9, 11 }, 0.0, 0.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), ElliottDegree.MINOR);
    }

    @Test
    public void INDEX_AT_OR_BEYOND_END_USES_FULL_SERIES_variation2_maximumInteger() {
        exercise(detector(3, 2, 3, 0.0, 0.0),
                series("full-max-index",
                        new double[] { 100.5, 103.0, 106.5, 105.0, 102.0, 99.0, 97.0, 98.5, 101.0, 104.0 },
                        1.5, 4.0),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }
}
