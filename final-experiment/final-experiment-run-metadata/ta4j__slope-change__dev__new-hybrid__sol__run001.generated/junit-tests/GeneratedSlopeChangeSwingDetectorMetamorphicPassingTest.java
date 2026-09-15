import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.NaN;
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static void exercise(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeries("", new ArrayList<>());
    }

    private static BarSeries series(double[] closes, double spread, boolean varyingVolume, boolean zeroVolume) {
        return series(closes, defaultHighs(closes, spread), defaultLows(closes, spread), varyingVolume, zeroVolume);
    }

    private static BarSeries series(double[] closes, double[] highs, double[] lows, boolean varyingVolume) {
        return series(closes, highs, lows, varyingVolume, false);
    }

    private static BarSeries series(double[] closes, double[] highs, double[] lows, boolean varyingVolume, boolean zeroVolume) {
        BarSeries result = new BaseBarSeriesBuilder().withName("slope-change-source").build();
        for (int i = 0; i < closes.length; i++) {
            boolean finiteClose = Double.isFinite(closes[i]);
            double openValue = finiteClose ? closes[i] : 0.0;
            double volumeValue = zeroVolume ? 0.0 : varyingVolume ? i + 1.0 : 5.0;
            double amountValue = zeroVolume ? 0.0 : volumeValue * openValue;
            Num open = result.numFactory().numOf(openValue);
            Num high = number(result, highs[i]);
            Num low = number(result, lows[i]);
            Num close = number(result, closes[i]);
            Num volume = result.numFactory().numOf(volumeValue);
            Num amount = result.numFactory().numOf(amountValue);
            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);
            result.addBar(new BaseBar(BAR_DURATION, begin, end, open, high, low, close, volume, amount, i + 1L));
        }
        return result;
    }

    private static Num number(BarSeries series, double value) {
        return Double.isFinite(value) ? series.numFactory().numOf(value) : NaN.NaN;
    }

    private static double[] defaultHighs(double[] closes, double spread) {
        double[] values = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            values[i] = Double.isFinite(closes[i]) ? closes[i] + spread : spread;
        }
        return values;
    }

    private static double[] defaultLows(double[] closes, double spread) {
        double[] values = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            values[i] = Double.isFinite(closes[i]) ? closes[i] - spread : -spread;
        }
        return values;
    }

    private static double[] constant(int size, double value) {
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = value;
        }
        return result;
    }

    private static double[] linear(int size, double start, double step) {
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = start + step * i;
        }
        return result;
    }

    private static double[] highShape(double level, int size) {
        double[] result = new double[size];
        int peak = Math.max(1, (size - 1) / 2);
        for (int i = 0; i < size; i++) {
            result[i] = level + (i <= peak ? i : 2.0 * peak - i);
        }
        return result;
    }

    private static double[] lowShape(double level, int size) {
        double[] high = highShape(0.0, size);
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = level - high[i];
        }
        return result;
    }

    private static double[] zigzag(double level, int size) {
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            int phase = i % 8;
            double offset;
            if (phase <= 3) {
                offset = phase;
            } else {
                offset = 6 - phase;
            }
            result[i] = level + offset;
        }
        return result;
    }

    @Test
    public void SINGLE_BAR_NO_CANDIDATE_variation1_negativeIndex() {
        exercise(detector(3, 2, 3, 0.5, 0.5), series(new double[] { 0.001 }, 0.01, false, false), -1, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ONE_BAR_SHORT_OF_FIRST_CANDIDATE_variation1_largeWindow() {
        double[] closes = linear(11, -20.0, -0.75);
        exercise(detector(5, 3, 4, 0.1, 0.0), series(closes, 0.25, true, false), 10, ElliottDegree.PRIMARY);
    }

    @Test
    public void EXACTLY_ONE_CANDIDATE_variation1_positiveToZero() {
        double[] closes = { 1_000_000.0, 1_000_001.0, 1_000_001.0, 1_000_001.0 };
        exercise(detector(2, 1, 3, 2.0, 0.5), series(closes, 4.0, false, true), 3, ElliottDegree.CYCLE);
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMP_variation1_integerMinimum() {
        exercise(detector(3, 2, 3, 0.0, 0.0), series(highShape(100.25, 10), 0.4, false, false), Integer.MIN_VALUE, ElliottDegree.MINUTE);
    }

    @Test
    public void INDEX_ABOVE_END_CLAMP_variation1_integerMaximum() {
        exercise(detector(5, 3, 4, 0.0, 0.5), series(highShape(0.01, 15), 0.05, true, false), Integer.MAX_VALUE, ElliottDegree.MINUETTE);
    }

    @Test
    public void INDEX_AT_FIRST_ENABLING_BOUNDARY_variation1_futureBarsPresent() {
        exercise(detector(2, 1, 2, 0.0, 0.0), series(new double[] { -4, -3, -2, -3, -4, -5, 20, 30 }, 0.2, false, true), 3, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void FLAT_CLOSES_BELOW_SLOPE_THRESHOLD_variation1_largeFinitePrices() {
        exercise(detector(3, 2, 4, 0.25, 0.5), series(constant(9, 900_000.5), 2.5, false, false), Integer.MAX_VALUE, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void REVERSAL_CHANGE_JUST_BELOW_MINIMUM_variation1_strictThreshold() {
        exercise(detector(5, 3, 4, 2.01, 0.0), series(highShape(50.125, 13), 0.3, true, false), 12, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void HIGH_REVERSAL_AT_MINIMUM_SLOPE_CHANGE_variation1_exactBoundary() {
        exercise(detector(3, 1, 3, 2.0, 0.5), series(highShape(0.005, 6), 0.02, false, true), 5, ElliottDegree.MINOR);
    }

    @Test
    public void LOW_REVERSAL_ABOVE_MINIMUM_SLOPE_CHANGE_variation1_confirmed() {
        exercise(detector(3, 2, 3, 0.5, 0.0), series(lowShape(-30.0, 7), 0.3, false, false), 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void POSITIVE_TO_POSITIVE_NO_REVERSAL_variation1_differentMagnitudes() {
        double[] closes = { 10, 11, 12, 14, 16, 18, 20, 22, 24 };
        exercise(detector(3, 3, 3, 0.5, 0.5), series(closes, 0.2, true, false), 8, ElliottDegree.PRIMARY);
    }

    @Test
    public void NEGATIVE_TO_NEGATIVE_NO_REVERSAL_variation1_differentMagnitudes() {
        double[] closes = { 20, 19, 18, 16, 14, 12 };
        exercise(detector(3, 1, 3, 0.5, 0.0), series(closes, 0.2, false, true), 5, ElliottDegree.CYCLE);
    }

    @Test
    public void POSITIVE_TO_ZERO_NO_HIGH_variation1_flatAfterWindow() {
        double[] closes = { 2, 3, 4, 4, 4, 4, 4 };
        exercise(detector(3, 2, 3, 0.5, 0.5), series(closes, 0.1, false, false), 6, ElliottDegree.MINUTE);
    }

    @Test
    public void ZERO_TO_POSITIVE_NO_LOW_variation1_flatBeforeWindow() {
        double[] closes = { -3, -3, -3, -2, -1, 0, 1, 2, 3 };
        exercise(detector(3, 3, 4, 0.5, 0.0), series(closes, 0.15, true, false), 8, ElliottDegree.MINUETTE);
    }

    @Test
    public void NONFINITE_BEFORE_SLOPE_variation1_nanClose() {
        double[] closes = { Double.NaN, 1, 2, 1, 0, -1 };
        exercise(detector(3, 1, 3, 0.0, 0.5), series(closes, 0.2, false, true), 5, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void NONFINITE_AFTER_SLOPE_variation1_nanClose() {
        double[] closes = { 0, 1, 2, Double.NaN, 0, -1, -2 };
        exercise(detector(3, 2, 3, 0.0, 0.0), series(closes, 0.2, false, false), 6, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void HIGH_PERSISTENCE_FAILS_FIRST_CONFIRMATION_variation1_zeroDirection() {
        double[] closes = { 0, 1, 2, 2, 2, 2, 3, 4, 5, 6 };
        exercise(detector(4, 3, 3, 0.0, 0.5), series(closes, 0.2, true, false), 9, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void LOW_PERSISTENCE_FAILS_LATER_variation1_secondConfirmation() {
        double[] closes = { 3, 2, 1, 2, 3, 4, 3 };
        exercise(detector(3, 2, 3, 0.0, 0.0), series(closes, 0.25, false, true), 6, ElliottDegree.MINOR);
    }

    @Test
    public void NONFINITE_PERSISTENCE_SLOPE_variation1_laterNan() {
        double[] closes = { 0, 1, 2, 1, 0, -1, Double.NaN };
        exercise(detector(3, 2, 3, 0.0, 0.5), series(closes, 0.25, false, false), 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void CONFIRMED_HIGH_FIRST_PIVOT_variation1_fractional() {
        exercise(detector(5, 3, 4, 0.0, 0.0), series(highShape(25.375, 13), 0.125, true, false), 12, ElliottDegree.PRIMARY);
    }

    @Test
    public void CONFIRMED_LOW_FIRST_PIVOT_variation1_nearZero() {
        exercise(detector(2, 1, 2, 0.0, 0.5), series(new double[] { 0.003, 0.002, 0.001, 0.002 }, 0.0002, false, true), 3, ElliottDegree.CYCLE);
    }

    @Test
    public void HIGH_EXTREME_AT_TRANSITION_START_variation1_explicitHigh() {
        double[] closes = highShape(-20.0, 7);
        double[] highs = defaultHighs(closes, 0.2);
        highs[2] = 10.0;
        exercise(detector(3, 2, 3, 0.0, 0.0), series(closes, highs, defaultLows(closes, 0.2), false), 6, ElliottDegree.MINUTE);
    }

    @Test
    public void HIGH_EXTREME_AT_TRANSITION_END_variation1_explicitHigh() {
        double[] closes = highShape(1_000_000.0, 13);
        double[] highs = defaultHighs(closes, 2.0);
        highs[6] = 1_000_100.0;
        exercise(detector(5, 3, 4, 0.0, 0.5), series(closes, highs, defaultLows(closes, 2.0), true), 12, ElliottDegree.MINUETTE);
    }

    @Test
    public void LOW_EXTREME_AT_INTERIOR_variation1_explicitLow() {
        double[] closes = lowShape(10.25, 6);
        double[] lows = defaultLows(closes, 0.1);
        lows[3] = 0.125;
        exercise(detector(3, 1, 3, 0.0, 0.0), series(closes, defaultHighs(closes, 0.1), lows, false), 5, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void TIED_HIGH_EXTREME_KEEPS_EARLIEST_variation1_twoEqualHighs() {
        double[] closes = highShape(0.01, 7);
        double[] highs = defaultHighs(closes, 0.05);
        highs[2] = 5.0;
        highs[4] = 5.0;
        exercise(detector(3, 2, 3, 0.0, 0.5), series(closes, highs, defaultLows(closes, 0.05), false), 6, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void TIED_LOW_EXTREME_KEEPS_EARLIEST_variation1_twoEqualLows() {
        double[] closes = lowShape(-10.0, 13);
        double[] lows = defaultLows(closes, 0.2);
        lows[4] = -30.0;
        lows[7] = -30.0;
        exercise(detector(5, 3, 4, 0.0, 0.0), series(closes, defaultHighs(closes, 0.2), lows, true), 12, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void NONFINITE_INITIAL_EXTREME_variation1_nanHigh() {
        double[] closes = { 0, 1, 2, 1, 0, -1 };
        double[] highs = defaultHighs(closes, 0.2);
        highs[2] = Double.NaN;
        exercise(detector(3, 1, 3, 0.0, 0.5), series(closes, highs, defaultLows(closes, 0.2), false), 5, ElliottDegree.MINOR);
    }

    @Test
    public void NONFINITE_LATER_EXTREME_variation1_nanHigh() {
        double[] closes = { 0, 1, 2, 1, 0, -1, -2 };
        double[] highs = defaultHighs(closes, 0.2);
        highs[4] = Double.NaN;
        exercise(detector(3, 2, 3, 0.0, 0.0), series(closes, highs, defaultLows(closes, 0.2), false), 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void FIRST_PIVOT_ACCEPTED_WITH_NONFINITE_ATR_variation1_earlyPivot() {
        double[] closes = highShape(0.02, 13);
        double[] highs = defaultHighs(closes, 0.01);
        highs[0] = Double.NaN;
        exercise(detector(5, 3, 10, 0.0, 0.5), series(closes, highs, defaultLows(closes, 0.01), true), 12, ElliottDegree.PRIMARY);
    }

    @Test
    public void ZERO_MULTIPLIER_BYPASSES_ATR_variation1_alternatingPivots() {
        exercise(detector(2, 1, 3, 0.0, 0.0), series(zigzag(0.0, 18), 0.15, false, true), Integer.MAX_VALUE, ElliottDegree.CYCLE);
    }

    @Test
    public void NONFINITE_ATR_REJECTS_SUBSEQUENT_PIVOT_variation1_positiveMultiplier() {
        double[] closes = zigzag(1_000_000.0, 18);
        double[] highs = defaultHighs(closes, 1.0);
        highs[6] = Double.NaN;
        exercise(detector(3, 2, 8, 0.0, 0.5), series(closes, highs, defaultLows(closes, 1.0), false), Integer.MAX_VALUE, ElliottDegree.MINUTE);
    }

    @Test
    public void REVERSAL_BELOW_ATR_THRESHOLD_variation1_weakMove() {
        exercise(detector(5, 3, 3, 0.0, 20.0), series(zigzag(30.5, 20), 5.0, true, false), Integer.MAX_VALUE, ElliottDegree.MINUETTE);
    }

    @Test
    public void REVERSAL_EQUAL_TO_ATR_THRESHOLD_variation1_inclusiveBoundary() {
        exercise(detector(2, 1, 1, 0.0, 1.0), series(new double[] { 0, 2, 0, -2, 0, 2, 0, -2 }, 0.0, false, true), Integer.MAX_VALUE, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void REVERSAL_ABOVE_ATR_THRESHOLD_variation1_strongMove() {
        exercise(detector(3, 2, 3, 0.0, 0.1), series(zigzag(-50.0, 20), 0.1, false, false), Integer.MAX_VALUE, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void STRONGER_REPEATED_HIGH_REPLACES_variation1_secondPeakHigher() {
        double[] closes = { 0, 1, 2, 1, 0, 1, 4, 3, 2, 1, 0, -1, -2 };
        exercise(detector(3, 1, 3, 0.0, 0.0), series(closes, 0.2, true, false), Integer.MAX_VALUE, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void WEAKER_REPEATED_HIGH_RETAINED_variation1_secondPeakLower() {
        double[] closes = { 0, 2, 4, 3, 2, 3, 3.5, 2.5, 1.5, 0.5, -0.5 };
        exercise(detector(2, 1, 2, 0.0, 0.0), series(closes, 0.1, false, true), Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void STRONGER_REPEATED_LOW_REPLACES_variation1_secondTroughLower() {
        double[] closes = { 4, 3, 2, 3, 4, 3, 0, 1, 2, 3, 4, 5 };
        exercise(detector(3, 2, 3, 0.0, 0.0), series(closes, 0.2, false, false), Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void WEAKER_REPEATED_LOW_RETAINED_variation1_secondTroughHigher() {
        double[] closes = { 4, 2, 0, 1, 2, 1, 0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5 };
        exercise(detector(4, 3, 4, 0.0, 0.0), series(closes, 0.2, true, false), Integer.MAX_VALUE, ElliottDegree.PRIMARY);
    }

    @Test
    public void ALTERNATING_HIGH_LOW_CREATES_SWING_variation1_twoTurns() {
        exercise(detector(2, 1, 3, 0.0, 0.0), series(zigzag(1_000_000.0, 14), 1.0, false, true), Integer.MAX_VALUE, ElliottDegree.CYCLE);
    }

    @Test
    public void THREE_ALTERNATING_PIVOTS_CREATE_TWO_SWINGS_variation1_threeTurns() {
        exercise(detector(3, 2, 3, 0.0, 0.0), series(zigzag(40.25, 24), 0.25, false, false), Integer.MAX_VALUE, ElliottDegree.MINUTE);
    }

    @Test
    public void CAUSAL_PREFIX_EXCLUDES_FUTURE_REVERSAL_variation1_truncatedEvaluation() {
        exercise(detector(5, 3, 4, 0.0, 0.0), series(zigzag(0.01, 30), 0.01, true, false), 15, ElliottDegree.MINUETTE);
    }

    @Test
    public void FRACTIONAL_PRICE_REVERSAL_variation1_nonIntegralPivot() {
        double[] closes = { 10.125, 10.625, 11.125, 10.625, 10.125, 9.625 };
        exercise(detector(3, 1, 3, 0.0, 0.0), series(closes, 0.0625, false, true), 5, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void NEGATIVE_PRICE_REVERSAL_IF_ACCEPTED_variation1_negativeOhlc() {
        exercise(detector(3, 2, 3, 0.0, 0.5), series(highShape(-50.0, 7), 0.2, false, false), 6, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void LARGE_FINITE_PRICE_REVERSAL_variation1_billionScale() {
        exercise(detector(5, 3, 4, 0.0, 0.0), series(highShape(1_000_000_000.25, 13), 8.0, true, false), 12, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void ZERO_VOLUME_AND_AMOUNT_variation1_confirmedLow() {
        exercise(detector(2, 1, 2, 0.0, 0.5), series(new double[] { 0.03, 0.02, 0.01, 0.02 }, 0.002, false, true), 3, ElliottDegree.MINOR);
    }

    @Test
    public void VARYING_VOLUME_AND_AMOUNT_variation1_multiplePivots() {
        exercise(detector(3, 2, 3, 0.0, 0.0), series(zigzag(-25.0, 22), 0.2, true, false), Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void DISTINCT_ELLIOTT_DEGREES_variation1_supercycleSwings() {
        exercise(detector(5, 3, 5, 0.0, 0.0), series(zigzag(500_000.5, 32), 1.5, true, false), Integer.MAX_VALUE, ElliottDegree.SUPER_CYCLE);
    }
}
