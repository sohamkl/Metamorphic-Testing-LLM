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
import org.ta4j.core.num.NaN;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final double[] HIGH = { 0, 2, 4, 4, 2, 0, -2 };
    private static final double[] LOW = { 4, 2, 0, 0, 2, 4, 6 };
    private static final double[] FLAT = { 3, 3, 3, 3, 3, 3, 3 };
    private static final double[] SAME_SIGN_UP = { 0, 1, 2, 3, 5, 7, 9 };
    private static final double[] SAME_SIGN_DOWN = { 9, 7, 5, 4, 3, 2, 1 };
    private static final double[] HIGH_WRONG_CONFIRMATION = { 0, 2, 4, 4, 2, 0, 4 };
    private static final double[] HIGH_ZERO_CONFIRMATION = { 0, 2, 4, 4, 2, 0, 2 };
    private static final double[] MULTI = {
            0, 2, 4, 6, 4, 2, 0, -2, 0, 2, 4, 6, 4, 2, 0, -2, 0, 2, 4, 6, 4, 2, 0
    };

    private static void exercise(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);

        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput = followUpDetector.detect(
                followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod,
            double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(
                window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static ElliottDegree degree(String name) {
        return ElliottDegree.valueOf(name);
    }

    private static BarSeries series(String name, double baseline, double[] closes) {
        return series(name, baseline, closes, offset(closes, 1), offset(closes, -1));
    }

    private static BarSeries series(String name, double baseline, double[] closes, double[] highs, double[] lows) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant origin = Instant.parse("2024-01-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            Num close = value(result, baseline, closes[i]);
            Num high = value(result, baseline, highs[i]);
            Num low = value(result, baseline, lows[i]);
            Num volume = result.numFactory().numOf(10 + i);
            Num amount = close.multipliedBy(volume);
            Instant begin = origin.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);

            result.addBar(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    1L + i));
        }
        return result;
    }

    private static BarSeries seriesWithNonfiniteClose(String name, double baseline, double[] closes,
            int nonfiniteIndex) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant origin = Instant.parse("2024-02-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            Num close = i == nonfiniteIndex ? NaN.NaN : result.numFactory().numOf(baseline + closes[i]);
            Num high = result.numFactory().numOf(baseline + closes[i] + 1);
            Num low = result.numFactory().numOf(baseline + closes[i] - 1);
            Num volume = result.numFactory().numOf(20 + i);
            Num amount = close.multipliedBy(volume);
            Instant begin = origin.plus(period.multipliedBy(i));
            result.addBar(new BaseBar(
                    period,
                    begin,
                    begin.plus(period),
                    close,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    2L + i));
        }
        return result;
    }

    private static Num value(BarSeries series, double baseline, double adjustment) {
        return Double.isNaN(adjustment)
                ? NaN.NaN
                : series.numFactory().numOf(baseline + adjustment);
    }

    private static double[] offset(double[] source, double amount) {
        double[] result = new double[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = source[i] + amount;
        }
        return result;
    }

    private static double[] negate(double[] source) {
        double[] result = new double[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = -source[i];
        }
        return result;
    }

    private static BarSeries sameTypeHighSeries(String name, double baseline, double firstHigh,
            double secondHigh) {
        double[] highs = offset(MULTI, 1);
        double[] lows = offset(MULTI, -1);
        highs[3] = firstHigh;
        lows[7] = Double.NaN;
        highs[11] = secondHigh;
        lows[15] = Double.NaN;
        highs[19] = secondHigh;
        return series(name, baseline, MULTI, highs, lows);
    }

    private static BarSeries sameTypeLowSeries(String name, double baseline, double firstLow,
            double secondLow) {
        double[] closes = negate(MULTI);
        double[] highs = offset(closes, 1);
        double[] lows = offset(closes, -1);
        lows[3] = firstLow;
        highs[7] = Double.NaN;
        lows[11] = secondLow;
        highs[15] = Double.NaN;
        lows[19] = secondLow;
        return series(name, baseline, closes, highs, lows);
    }

    @Test
    void INSUFFICIENT_COMPLETE_WINDOWS_variation1() {
        exercise(new SlopeChangeSwingDetector(3), series("short", 20, new double[] { 1, 2, 3 }), -5,
                degree("MINOR"));
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation1() {
        exercise(detector(3, 2, 3, 0, 0.5), series("negative-later-high", -30, HIGH), -1,
                degree("INTERMEDIATE"));
    }

    @Test
    void INDEX_ABOVE_END_CLAMPS_TO_SERIES_END_variation1() {
        exercise(detector(3, 2, 3, 0, 0), series("large-multi", 1_000_000_000, MULTI), 10_000,
                degree("PRIMARY"));
    }

    @Test
    void INDEX_ABOVE_END_CLAMPS_TO_SERIES_END_variation2() {
        exercise(new SlopeChangeSwingDetector(3), series("ordinary-high", 100, HIGH), Integer.MAX_VALUE,
                degree("CYCLE"));
    }

    @Test
    void EXACT_ONE_CANDIDATE_FLAT_SLOPES_variation1() {
        exercise(detector(3, 2, 3, 1, 0.5), series("flat-negative", -50, FLAT), 100,
                degree("MINUTE"));
    }

    @Test
    void EXACT_ONE_CANDIDATE_FLAT_SLOPES_variation2() {
        exercise(detector(3, 2, 3, 0, 0), series("flat-large", 900_000_000, FLAT), 6,
                degree("MINUETTE"));
    }

    @Test
    void SLOPE_CHANGE_STRICTLY_BELOW_MINIMUM_variation1() {
        exercise(new SlopeChangeSwingDetector(
                new SlopeChangeConfig(3, 2, 3, 5.0, 0.5)),
                series("below-threshold-high", 40, HIGH), 6, degree("SUB_MINUETTE"));
    }

    @Test
    void SLOPE_CHANGE_STRICTLY_BELOW_MINIMUM_variation2() {
        exercise(detector(3, 2, 3, 6, 1), series("below-threshold-low", -80, LOW), 6,
                degree("MINOR"));
    }

    @Test
    void SLOPE_CHANGE_EXACTLY_AT_MINIMUM_variation1() {
        exercise(detector(3, 2, 3, 4, 0), series("exact-high", 1_000_000, HIGH), 6,
                degree("INTERMEDIATE"));
    }

    @Test
    void SLOPE_CHANGE_EXACTLY_AT_MINIMUM_variation2() {
        exercise(detector(3, 2, 3, 4, 0.5), series("exact-low", 25, LOW), 999,
                degree("PRIMARY"));
    }

    @Test
    void LARGE_CHANGE_WITHOUT_SIGN_REVERSAL_variation1() {
        exercise(detector(3, 2, 3, 1, 0.5), series("same-sign-up", -20, SAME_SIGN_UP), 6,
                degree("CYCLE"));
    }

    @Test
    void LARGE_CHANGE_WITHOUT_SIGN_REVERSAL_variation2() {
        exercise(detector(3, 2, 3, 1, 0), series("same-sign-down", 800_000_000, SAME_SIGN_DOWN), 6,
                degree("MINUTE"));
    }

    @Test
    void CONFIRMED_HIGH_PIVOT_variation1() {
        exercise(new SlopeChangeSwingDetector(3), series("confirmed-high-first", 100, HIGH), 6,
                degree("MINUETTE"));
    }

    @Test
    void CONFIRMED_HIGH_PIVOT_variation2() {
        double[] highs = offset(HIGH, -1);
        highs[2] = 7;
        highs[3] = 12;
        highs[4] = 8;
        exercise(detector(3, 2, 3, 1, 2), series("confirmed-high-later", -100, HIGH, highs, offset(HIGH, -2)),
                6, degree("SUB_MINUETTE"));
    }

    @Test
    void CONFIRMED_LOW_PIVOT_variation1() {
        exercise(detector(3, 2, 3, 1, 0), series("confirmed-low-first", 1_000_000_000, LOW), 100,
                degree("MINOR"));
    }

    @Test
    void CONFIRMED_LOW_PIVOT_variation2() {
        double[] lows = offset(LOW, -1);
        lows[2] = -2;
        lows[3] = -8;
        lows[4] = -3;
        exercise(new SlopeChangeSwingDetector(3), series("confirmed-low-later", 50, LOW, offset(LOW, 2), lows),
                6, degree("INTERMEDIATE"));
    }

    @Test
    void CONFIRMATION_WRONG_DIRECTION_variation1() {
        exercise(detector(3, 2, 3, 1, 0.5), series("wrong-high-confirmation", -40, HIGH_WRONG_CONFIRMATION), 6,
                degree("PRIMARY"));
    }

    @Test
    void CONFIRMATION_WRONG_DIRECTION_variation2() {
        exercise(detector(3, 2, 3, 1, 0), series("wrong-low-confirmation", 500_000_000,
                negate(HIGH_WRONG_CONFIRMATION)), 6, degree("CYCLE"));
    }

    @Test
    void CONFIRMATION_ZERO_SLOPE_variation1() {
        exercise(new SlopeChangeSwingDetector(3), series("zero-confirmation", 75, HIGH_ZERO_CONFIRMATION), 6,
                degree("MINUTE"));
    }

    @Test
    void NONFINITE_BEFORE_SLOPE_SENTINEL_variation1() {
        exercise(detector(3, 2, 3, 0, 1), seriesWithNonfiniteClose("nan-before", -20, HIGH, 1), 50,
                degree("MINUETTE"));
    }

    @Test
    void NONFINITE_AFTER_SLOPE_SENTINEL_variation1() {
        exercise(detector(3, 2, 3, 0, 0), seriesWithNonfiniteClose("nan-after", 1_000_000, HIGH, 4), 6,
                degree("SUB_MINUETTE"));
    }

    @Test
    void NONFINITE_CONFIRMATION_SLOPE_SENTINEL_variation1() {
        double[] values = { 0, 2, 4, 4, 2, 0, -2, -4 };
        exercise(detector(3, 3, 3, 0, 0.5), seriesWithNonfiniteClose("nan-confirmation", 30, values, 7), 7,
                degree("MINOR"));
    }

    @Test
    void NONFINITE_EXTREME_AT_INTERVAL_START_variation1() {
        double[] highs = offset(HIGH, 1);
        highs[2] = Double.NaN;
        exercise(detector(3, 2, 3, 0, 1), series("nan-extreme-start", -60, HIGH, highs, offset(HIGH, -1)), 6,
                degree("INTERMEDIATE"));
    }

    @Test
    void NONFINITE_EXTREME_LATER_IN_INTERVAL_variation1() {
        double[] highs = offset(HIGH, 1);
        highs[3] = Double.NaN;
        exercise(detector(3, 2, 3, 0, 0), series("nan-extreme-later", 900_000_000, HIGH, highs, offset(HIGH, -1)),
                6, degree("PRIMARY"));
    }

    @Test
    void EQUAL_HIGH_EXTREME_TIE_variation1() {
        double[] highs = offset(HIGH, 1);
        highs[2] = 10;
        highs[3] = 10;
        exercise(new SlopeChangeSwingDetector(3), series("equal-highs", 100, HIGH, highs, offset(HIGH, -1)), 99,
                degree("CYCLE"));
    }

    @Test
    void EQUAL_LOW_EXTREME_TIE_variation1() {
        double[] lows = offset(LOW, -1);
        lows[2] = -10;
        lows[3] = -10;
        exercise(detector(3, 2, 3, 0, 0.5), series("equal-lows", -100, LOW, offset(LOW, 1), lows), 6,
                degree("MINUTE"));
    }

    @Test
    void FIRST_PIVOT_BYPASSES_ATR_FILTER_variation1() {
        exercise(detector(3, 2, 50, 1, 1000), series("first-pivot-atr-bypass", 1_000_000_000, HIGH), 6,
                degree("MINUETTE"));
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASS_variation1() {
        exercise(detector(3, 2, 20, 1, 0), series("zero-atr-multiplier", 90, MULTI), 22,
                degree("SUB_MINUETTE"));
    }

    @Test
    void FINITE_ATR_MAGNITUDE_REJECTION_variation1() {
        exercise(detector(3, 2, 3, 1, 100), series("atr-rejection-high-low", -20, MULTI), 15,
                degree("MINOR"));
    }

    @Test
    void FINITE_ATR_MAGNITUDE_REJECTION_variation2() {
        exercise(detector(3, 2, 2, 1, 500), series("atr-rejection-low-high", 700_000_000, negate(MULTI)), 22,
                degree("INTERMEDIATE"));
    }

    @Test
    void FINITE_ATR_MAGNITUDE_BOUNDARY_ACCEPTANCE_variation1() {
        exercise(detector(3, 2, 1, 1, 1), series("atr-inclusive-one", 20, MULTI), 15,
                degree("PRIMARY"));
    }

    @Test
    void FINITE_ATR_MAGNITUDE_BOUNDARY_ACCEPTANCE_variation2() {
        exercise(detector(3, 2, 3, 1, 0.25), series("atr-above-threshold", -70, negate(MULTI)), 22,
                degree("CYCLE"));
    }

    @Test
    void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation1() {
        double[] highs = offset(MULTI, 1);
        highs[5] = Double.NaN;
        exercise(detector(3, 2, 14, 1, 2), series("nonfinite-atr-one", 1_000_000, MULTI, highs, offset(MULTI, -1)),
                15, degree("MINUTE"));
    }

    @Test
    void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation2() {
        double[] lows = offset(negate(MULTI), -1);
        lows[6] = Double.NaN;
        exercise(detector(3, 2, 14, 1, 3),
                series("nonfinite-atr-two", 200, negate(MULTI), offset(negate(MULTI), 1), lows), 22,
                degree("MINUETTE"));
    }

    @Test
    void ALTERNATING_PIVOTS_CREATE_SWING_variation1() {
        exercise(detector(3, 2, 3, 1, 0.25), series("high-then-low", -80, MULTI), 11,
                degree("SUB_MINUETTE"));
    }

    @Test
    void ALTERNATING_PIVOTS_CREATE_SWING_variation2() {
        exercise(detector(3, 2, 3, 1, 0), series("low-then-high", 800_000_000, negate(MULTI)), 11,
                degree("MINOR"));
    }

    @Test
    void STRONGER_SAME_TYPE_HIGH_REPLACES_variation1() {
        exercise(detector(3, 2, 3, 1, 0), sameTypeHighSeries("stronger-high", 100, 14, 25), 15,
                degree("INTERMEDIATE"));
    }

    @Test
    void STRONGER_SAME_TYPE_HIGH_REPLACES_variation2() {
        exercise(detector(3, 2, 3, 1, 0.1), sameTypeHighSeries("stronger-high-negative", -100, 10, 30), 22,
                degree("PRIMARY"));
    }

    @Test
    void STRONGER_SAME_TYPE_LOW_REPLACES_variation1() {
        exercise(detector(3, 2, 3, 1, 0), sameTypeLowSeries("stronger-low", 1_000_000_000, -12, -24), 15,
                degree("CYCLE"));
    }

    @Test
    void STRONGER_SAME_TYPE_LOW_REPLACES_variation2() {
        exercise(detector(3, 2, 3, 1, 0.1), sameTypeLowSeries("stronger-low-ordinary", 80, -8, -28), 22,
                degree("MINUTE"));
    }

    @Test
    void WEAKER_OR_EQUAL_SAME_TYPE_IS_RETAINED_variation1() {
        exercise(detector(3, 2, 3, 1, 0), sameTypeHighSeries("weaker-high", -60, 30, 15), 15,
                degree("MINUETTE"));
    }

    @Test
    void WEAKER_OR_EQUAL_SAME_TYPE_IS_RETAINED_variation2() {
        exercise(detector(3, 2, 3, 1, 0), sameTypeLowSeries("weaker-low", 600_000_000, -30, -12), 22,
                degree("SUB_MINUETTE"));
    }

    @Test
    void MULTIPLE_ALTERNATING_PIVOTS_AND_SWINGS_variation1() {
        exercise(new SlopeChangeSwingDetector(3), series("many-alternating-default", 100, MULTI), 22,
                degree("MINOR"));
    }

    @Test
    void MULTIPLE_ALTERNATING_PIVOTS_AND_SWINGS_variation2() {
        exercise(detector(3, 2, 3, 1, 0.2), series("many-alternating-negative", -100, negate(MULTI)), 22,
                degree("INTERMEDIATE"));
    }

    @Test
    void INDEX_TRUNCATES_LATE_REVERSAL_variation1() {
        exercise(detector(3, 2, 3, 1, 0), series("truncate-after-first", 1_000_000_000, MULTI), 7,
                degree("PRIMARY"));
    }

    @Test
    void INDEX_TRUNCATES_LATE_REVERSAL_variation2() {
        exercise(new SlopeChangeSwingDetector(3), series("truncate-after-second", 40, MULTI), 11,
                degree("CYCLE"));
    }

    @Test
    void DEGREE_PROPAGATION_ACROSS_IDENTICAL_PATTERN_variation1() {
        exercise(detector(3, 2, 3, 1, 0), series("degree-pattern-one", -40, MULTI), 15,
                degree("MINUTE"));
    }

    @Test
    void DEGREE_PROPAGATION_ACROSS_IDENTICAL_PATTERN_variation2() {
        exercise(detector(3, 2, 3, 1, 0), series("degree-pattern-two", -40, MULTI), 15,
                degree("PRIMARY"));
    }

    @Test
    void TRANSLATION_SENSITIVE_NUMERIC_BASELINES_variation1() {
        exercise(new SlopeChangeSwingDetector(3), series("negative-baseline", -1_000, MULTI), 22,
                degree("MINUETTE"));
    }

    @Test
    void TRANSLATION_SENSITIVE_NUMERIC_BASELINES_variation2() {
        exercise(detector(3, 2, 3, 1, 0.25), series("large-baseline", 1_000_000_000_000.0, MULTI), 1000,
                degree("SUB_MINUETTE"));
    }
}
