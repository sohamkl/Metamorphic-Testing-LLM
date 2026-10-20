import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static SlopeChangeSwingDetector configuredDetector(int window) {
        return new SlopeChangeSwingDetector(SlopeChangeConfig.defaults(window));
    }

    private static int firstCandidateBoundary(SlopeChangeSwingDetector detector) {
        return 2 * detector.getConfig().window()
                + detector.getConfig().confirmationBars() - 2;
    }

    private static double smallChange(SlopeChangeSwingDetector detector) {
        double threshold = detector.getConfig().minSlopeChange();
        return threshold > 0.0 ? threshold / 8.0 : 1.0e-8;
    }

    private static ElliottDegree degree(int offset) {
        ElliottDegree[] values = ElliottDegree.values();
        return values[Math.floorMod(offset, values.length)];
    }

    private static double[] alternating() {
        return new double[] {
                0.0, 6.0, 12.0, 6.0, 0.0, -6.0, 0.0, 6.0,
                12.0, 6.0, 0.0, -6.0, 0.0, 6.0, 12.0, 6.0,
                0.0, -6.0, 0.0, 6.0
        };
    }

    private static double[] constantExtras(int length, double value) {
        double[] extras = new double[length];
        for (int i = 0; i < extras.length; i++) {
            extras[i] = value;
        }
        return extras;
    }

    private static BarSeries series(double baseline, double volume, double[] closeOffsets) {
        return series(baseline, volume, closeOffsets, null, null);
    }

    private static BarSeries series(
            double baseline,
            double volume,
            double[] closeOffsets,
            double[] highExtras,
            double[] lowExtras) {

        BarSeries result = new BaseBarSeriesBuilder()
                .withName("slope-change-source")
                .build();

        for (int i = 0; i < closeOffsets.length; i++) {
            double close = baseline + closeOffsets[i];
            double highExtra = highExtras == null ? 0.0 : highExtras[i];
            double lowExtra = lowExtras == null ? 0.0 : lowExtras[i];
            double high = close + 1.0 + highExtra;
            double low = close - 1.0 - lowExtra;

            Num openNum = result.numFactory().numOf(close);
            Num highNum = result.numFactory().numOf(high);
            Num lowNum = result.numFactory().numOf(low);
            Num closeNum = result.numFactory().numOf(close);
            Num volumeNum = result.numFactory().numOf(volume);
            Num amountNum = result.numFactory().numOf(close * volume);

            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);
            result.addBar(new BaseBar(
                    BAR_DURATION,
                    begin,
                    end,
                    openNum,
                    highNum,
                    lowNum,
                    closeNum,
                    volumeNum,
                    amountNum,
                    1L));
        }
        return result;
    }

    private static void exercise(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {

        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);

        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                detector, sourceSeries, index, degree);

        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput = followUpDetector.detect(
                followUpSeries, followUpIndex, followUpDegree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NO_CANDIDATE_BEFORE_MINIMUM_HISTORY_1_negativeIndex() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 1.0, new double[] { 0.0, 1.0, 2.0 });
        exercise(detector, series, -1, degree(0));
    }

    @Test
    public void NO_CANDIDATE_BEFORE_MINIMUM_HISTORY_2_longestShortPrefix() {
        SlopeChangeSwingDetector detector = configuredDetector(3);
        BarSeries series = series(100_000_000.0, 0.0,
                new double[] { 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7 });
        int firstCandidateEnd = 2 * detector.getConfig().window()
                + detector.getConfig().confirmationBars() - 2;
        exercise(detector, series, Math.max(0, firstCandidateEnd - 1), degree(1));
    }

    @Test
    public void EXACTLY_ONE_CANDIDATE_1_confirmedHigh() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 2.0,
                new double[] { 0.0, 3.0, 4.0, 1.0, -2.0, -4.0, -6.0 });
        exercise(detector, series, firstCandidateBoundary(detector), degree(0));
    }

    @Test
    public void EXACTLY_ONE_CANDIDATE_2_sameSignSlopes() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 0.0,
                new double[] { 0.0, 1.0, 2.0, 4.0, 7.0, 11.0, 16.0 });
        exercise(detector, series, firstCandidateBoundary(detector), degree(1));
    }

    @Test
    public void NEGATIVE_INDEX_CLAMPS_TO_BEGIN_1_minusOne() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 10.0, alternating());
        exercise(detector, series, -1, degree(0));
    }

    @Test
    public void NEGATIVE_INDEX_CLAMPS_TO_BEGIN_2_integerMinimum() {
        SlopeChangeSwingDetector detector = configuredDetector(3);
        BarSeries series = series(100_000_000.0, 3.0, alternating());
        exercise(detector, series, Integer.MIN_VALUE, degree(1));
    }

    @Test
    public void INDEX_ABOVE_END_CLAMPS_TO_END_1_endPlusOne() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(80.0, 1.0, alternating());
        exercise(detector, series, series.getEndIndex() + 1, degree(0));
    }

    @Test
    public void INDEX_ABOVE_END_CLAMPS_TO_END_2_integerMaximum() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 5.0, alternating());
        exercise(detector, series, Integer.MAX_VALUE, degree(1));
    }

    @Test
    public void FLAT_CLOSE_WINDOWS_1_ordinaryBaseline() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(75.0, 1.0,
                new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void FLAT_CLOSE_WINDOWS_2_largeBaselineZeroVolume() {
        SlopeChangeSwingDetector detector = configuredDetector(3);
        BarSeries series = series(100_000_000.0, 0.0,
                new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 });
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void SLOPE_CHANGE_BELOW_MINIMUM_1_smallPositiveChange() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        double epsilon = smallChange(detector);
        BarSeries series = series(100.0, 2.0,
                new double[] { 0.0, epsilon, 2.0 * epsilon, 2.5 * epsilon,
                        3.0 * epsilon, 3.5 * epsilon });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void SLOPE_CHANGE_BELOW_MINIMUM_2_smallRoundedTurn() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        double epsilon = smallChange(detector);
        BarSeries series = series(100_000_000.0, 4.0,
                new double[] { 0.0, epsilon, 2.0 * epsilon, 1.5 * epsilon,
                        epsilon, 0.5 * epsilon });
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void SLOPE_CHANGE_EXACTLY_AT_MINIMUM_1_inclusiveBoundary() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        double half = Math.max(detector.getConfig().minSlopeChange() / 2.0, 1.0e-6);
        BarSeries series = series(100.0, 1.0,
                new double[] { 0.0, half, 2.0 * half, half, 0.0, -half, -2.0 * half });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void LARGE_CHANGE_WITHOUT_DIRECTION_REVERSAL_1_bothPositive() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 3.0,
                new double[] { 0.0, 10.0, 11.0, 31.0, 52.0, 74.0, 97.0 });
        exercise(detector, series, firstCandidateBoundary(detector), degree(1));
    }

    @Test
    public void LARGE_CHANGE_WITHOUT_DIRECTION_REVERSAL_2_bothNegative() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(200.0, 1.0,
                new double[] { 100.0, 80.0, 70.0, 65.0, 60.0, 55.0, 50.0 });
        exercise(detector, series, firstCandidateBoundary(detector), degree(0));
    }

    @Test
    public void ZERO_SLOPE_DIRECTION_BOUNDARY_1_zeroBeforeSlope() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 0.0,
                new double[] { 0.0, 0.0, 2.0, 12.0, 22.0, 32.0, 42.0 });
        exercise(detector, series, firstCandidateBoundary(detector), degree(1));
    }

    @Test
    public void CONFIRMED_HIGH_REVERSAL_1_closeLedTurn() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 2.0,
                new double[] { 0.0, 4.0, 8.0, 3.0, -2.0, -6.0, -9.0 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void CONFIRMED_HIGH_REVERSAL_2_largeBaselineWick() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        double[] closes = { 0.0, 2.0, 5.0, 1.0, -3.0, -6.0, -8.0 };
        double[] highExtra = { 0.0, 0.0, 20.0, 0.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100_000_000.0, 6.0, closes, highExtra, null);
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void CONFIRMED_LOW_REVERSAL_1_closeLedTurn() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 1.0,
                new double[] { 10.0, 6.0, 2.0, 7.0, 12.0, 16.0, 19.0 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void CONFIRMED_LOW_REVERSAL_2_largeBaselineLowWick() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        double[] closes = { 8.0, 4.0, 0.0, 5.0, 10.0, 14.0, 17.0 };
        double[] lowExtra = { 0.0, 0.0, 18.0, 0.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100_000_000.0, 2.0, closes, null, lowExtra);
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void PERSISTENCE_FAILS_FIRST_CONFIRMATION_1_highReversal() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(3);
        BarSeries series = series(100.0, 1.0,
                new double[] { 0.0, 2.0, 4.0, 7.0, 4.0, 1.0, 3.0, 6.0, 9.0, 12.0 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void PERSISTENCE_FAILS_FIRST_CONFIRMATION_2_lowReversal() {
        SlopeChangeSwingDetector detector = configuredDetector(3);
        BarSeries series = series(100_000_000.0, 0.0,
                new double[] { 12.0, 9.0, 6.0, 3.0, 6.0, 9.0, 7.0, 4.0, 1.0, -2.0 });
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void PERSISTENCE_FAILS_LATER_CONFIRMATION_1_highTurnsBackUp() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(120.0, 2.0,
                new double[] { 0.0, 4.0, 7.0, 2.0, 1.0, 6.0, 11.0, 16.0 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void PERSISTENCE_FAILS_LATER_CONFIRMATION_2_lowTurnsBackDown() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 3.0,
                new double[] { 12.0, 8.0, 4.0, 9.0, 10.0, 5.0, 0.0, -5.0 });
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void PERSISTENCE_ZERO_SLOPE_REJECTION_1_highConfirmationFlat() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 1.0,
                new double[] { 0.0, 5.0, 7.0, 2.0, 2.0, 2.0, 2.0 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void PERSISTENCE_ZERO_SLOPE_REJECTION_2_lowConfirmationFlat() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 0.0,
                new double[] { 10.0, 5.0, 3.0, 8.0, 8.0, 8.0, 8.0 });
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void HIGH_EXTREME_LOCATION_1_uniqueInteriorHigh() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(3);
        double[] closes = { 0.0, 2.0, 4.0, 6.0, 4.0, 2.0, 0.0, -2.0, -4.0, -6.0 };
        double[] highExtra = { 0.0, 0.0, 0.0, 1.0, 30.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100.0, 1.0, closes, highExtra, null);
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void HIGH_EXTREME_LOCATION_2_uniqueTransitionEndHigh() {
        SlopeChangeSwingDetector detector = configuredDetector(3);
        double[] closes = { 0.0, 2.0, 4.0, 6.0, 4.0, 2.0, 0.0, -2.0, -4.0, -6.0 };
        double[] highExtra = { 0.0, 0.0, 0.0, 0.0, 0.0, 35.0, 0.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100_000_000.0, 0.0, closes, highExtra, null);
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void TIED_HIGH_USES_EARLIEST_INDEX_1_equalWicks() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(3);
        double[] closes = { 0.0, 2.0, 4.0, 6.0, 4.0, 2.0, 0.0, -2.0, -4.0, -6.0 };
        double[] highExtra = { 0.0, 0.0, 0.0, 20.0, 22.0, 24.0, 0.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100.0, 2.0, closes, highExtra, null);
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void LOW_EXTREME_AND_TIE_LOCATION_1_uniqueLaterLow() {
        SlopeChangeSwingDetector detector = configuredDetector(3);
        double[] closes = { 10.0, 8.0, 6.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0 };
        double[] lowExtra = { 0.0, 0.0, 0.0, 0.0, 0.0, 30.0, 0.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100_000_000.0, 4.0, closes, null, lowExtra);
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void LOW_EXTREME_AND_TIE_LOCATION_2_equalMinimumWicks() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(3);
        double[] closes = { 10.0, 8.0, 6.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0 };
        double[] lowExtra = { 0.0, 0.0, 0.0, 20.0, 22.0, 24.0, 0.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100.0, 1.0, closes, null, lowExtra);
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_MAGNITUDE_1_highFirstPivot() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 5.0,
                new double[] { 0.0, 4.0, 8.0, 2.0, -4.0, -9.0, -13.0 });
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_MAGNITUDE_2_lowFirstPivot() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 1.0,
                new double[] { 12.0, 8.0, 4.0, 10.0, 16.0, 21.0, 25.0 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void ZERO_ATR_MULTIPLIER_BYPASS_1_multipleReversals() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 0.0,
                new double[] { 0.0, 6.0, 12.0, 6.0, 0.0, -6.0, 0.0, 6.0,
                        12.0, 6.0, 0.0, -6.0, 0.0, 6.0 });
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void ATR_REVERSAL_BELOW_THRESHOLD_1_highVolatilitySmallTurn() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        double[] closes = { 0.0, 5.0, 10.0, 5.0, 0.0, -5.0, -4.0, -3.0,
                -4.0, -5.0, -6.0, -5.0, -4.0 };
        double[] highExtra = constantExtras(closes.length, 50.0);
        double[] lowExtra = constantExtras(closes.length, 50.0);
        BarSeries series = series(100.0, 10.0, closes, highExtra, lowExtra);
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void ATR_REVERSAL_BELOW_THRESHOLD_2_largeBaselineWideBars() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        double[] closes = { 0.0, -5.0, -10.0, -5.0, 0.0, 5.0, 4.0, 3.0,
                4.0, 5.0, 6.0, 5.0, 4.0 };
        double[] highExtra = constantExtras(closes.length, 100.0);
        double[] lowExtra = constantExtras(closes.length, 100.0);
        BarSeries series = series(100_000_000.0, 3.0, closes, highExtra, lowExtra);
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void ATR_REVERSAL_EXACTLY_AT_THRESHOLD_1_regularRangeTurns() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 1.0,
                new double[] { 0.0, 5.0, 10.0, 5.0, 0.0, -5.0, 0.0, 5.0,
                        10.0, 5.0, 0.0, -5.0, 0.0, 5.0 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void ALTERNATING_HIGH_LOW_CREATES_SWING_1_highThenLow() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 5.0,
                new double[] { 0.0, 6.0, 12.0, 6.0, 0.0, -6.0, 0.0, 6.0, 12.0 });
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void ALTERNATING_HIGH_LOW_CREATES_SWING_2_lowThenHigh() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 1.0,
                new double[] { 12.0, 6.0, 0.0, 6.0, 12.0, 18.0, 12.0, 6.0, 0.0 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void MULTIPLE_ALTERNATING_PIVOTS_1_fourTurns() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 0.0, alternating());
        exercise(detector, series, Integer.MAX_VALUE, degree(1));
    }

    @Test
    public void MULTIPLE_ALTERNATING_PIVOTS_2_threeTurnsOrdinaryPrices() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 2.0,
                new double[] { 0.0, 8.0, 16.0, 8.0, 0.0, -8.0, 0.0, 8.0,
                        16.0, 8.0, 0.0, -8.0, 0.0, 8.0 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void SAME_TYPE_HIGH_REPLACED_1_laterHigherWick() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        double[] closes = { 0.0, 6.0, 12.0, 6.0, 0.0, 3.0, 6.0, 2.0, -2.0, -6.0 };
        double[] highExtra = { 0.0, 0.0, 2.0, 0.0, 0.0, 0.0, 30.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100_000_000.0, 4.0, closes, highExtra, null);
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void SAME_TYPE_HIGH_RETAINED_1_laterLowerWick() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        double[] closes = { 0.0, 6.0, 12.0, 6.0, 0.0, 3.0, 6.0, 2.0, -2.0, -6.0 };
        double[] highExtra = { 0.0, 0.0, 30.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100.0, 1.0, closes, highExtra, null);
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void SAME_TYPE_LOW_REPLACED_1_laterLowerWick() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        double[] closes = { 12.0, 6.0, 0.0, 6.0, 12.0, 9.0, 6.0, 10.0, 14.0, 18.0 };
        double[] lowExtra = { 0.0, 0.0, 2.0, 0.0, 0.0, 0.0, 30.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100_000_000.0, 5.0, closes, null, lowExtra);
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void SAME_TYPE_LOW_RETAINED_1_laterHigherLow() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        double[] closes = { 12.0, 6.0, 0.0, 6.0, 12.0, 9.0, 6.0, 10.0, 14.0, 18.0 };
        double[] lowExtra = { 0.0, 0.0, 30.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 };
        BarSeries series = series(100.0, 1.0, closes, null, lowExtra);
        exercise(detector, series, series.getEndIndex(), degree(0));
    }

    @Test
    public void PREFIX_CAUSALITY_AT_CANDIDATE_BOUNDARY_1_candidateExcluded() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 0.0,
                new double[] { 0.0, 5.0, 10.0, 4.0, -2.0, -8.0, -13.0, -17.0 });
        exercise(detector, series, Math.max(0, firstCandidateBoundary(detector) - 1), degree(1));
    }

    @Test
    public void PREFIX_CAUSALITY_AT_CANDIDATE_BOUNDARY_2_candidateIncluded() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 3.0,
                new double[] { 0.0, 5.0, 10.0, 4.0, -2.0, -8.0, -13.0, -17.0 });
        exercise(detector, series, firstCandidateBoundary(detector), degree(0));
    }

    @Test
    public void DEGREE_PROPAGATION_ACROSS_SWINGS_1_firstDeclaredDegree() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 4.0, alternating());
        exercise(detector, series, Integer.MAX_VALUE, degree(0));
    }

    @Test
    public void DEGREE_PROPAGATION_ACROSS_SWINGS_2_secondDeclaredDegree() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(2);
        BarSeries series = series(100.0, 1.0, alternating());
        exercise(detector, series, series.getEndIndex(), degree(1));
    }

    @Test
    public void LARGE_BASELINE_SMALL_REVERSAL_1_finiteSmallMovements() {
        SlopeChangeSwingDetector detector = configuredDetector(2);
        BarSeries series = series(100_000_000.0, 2.0,
                new double[] { 0.00, 0.25, 0.50, 0.10, -0.30, -0.65, -0.90,
                        -0.55, -0.20, 0.15, 0.45, 0.10, -0.25 });
        exercise(detector, series, series.getEndIndex(), degree(0));
    }
}
