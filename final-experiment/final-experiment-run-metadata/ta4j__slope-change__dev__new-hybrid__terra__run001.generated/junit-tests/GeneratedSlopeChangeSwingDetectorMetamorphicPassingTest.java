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

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeriesBuilder().withName("metamorphic-slope-empty").build();
    }

    private static BarSeries series(double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 1.0;
            lows[i] = closes[i] - 1.0;
        }
        return series(closes, highs, lows);
    }

    private static BarSeries series(double[] closes, double[] highs, double[] lows) {
        BarSeries result = new BaseBarSeriesBuilder().withName("metamorphic-slope-series").build();
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < closes.length; i++) {
            Num close = number(result, closes[i]);
            Num high = number(result, highs[i]);
            Num low = number(result, lows[i]);
            Instant begin = start.plusSeconds(i * 60L);
            Instant end = begin.plusSeconds(60L);
            result.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, close, high, low, close, result.numFactory().one(), result.numFactory().one(), 1L));
        }
        return result;
    }

    private static Num number(BarSeries series, double value) {
        return Double.isNaN(value) ? NaN.NaN : series.numFactory().numOf(value);
    }

    private static void verify(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONEMPTY_SERIES_BEFORE_FIRST_CANDIDATE_short_finite_series() {
        verify(detector(4, 1, 1, 0.0, 0.0), series(10.0, 11.0, 12.0), 2, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void NEGATIVE_INDEX_CLAMPED_TO_BEGIN_long_series() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0), -7, ElliottDegree.MINOR);
    }

    @Test
    void OVERSIZED_INDEX_CLAMPED_TO_END_confirmed_high() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0), 999, ElliottDegree.PRIMARY);
    }

    @Test
    void EXACTLY_ONE_CANDIDATE_WINDOW_confirmed_high() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0), 3, ElliottDegree.MINOR);
    }

    @Test
    void FINITE_SLOPE_CHANGE_BELOW_THRESHOLD_reversal_rejected() {
        verify(detector(2, 1, 1, 11.0, 0.0), series(0.0, 5.0, 0.0, -5.0), 3, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SLOPE_CHANGE_EXACTLY_THRESHOLD_inclusive_gate() {
        verify(detector(2, 1, 1, 10.0, 0.0), series(0.0, 5.0, 0.0, -5.0), 3, ElliottDegree.MINOR);
    }

    @Test
    void SAME_SIGN_SLOPES_AFTER_MAGNITUDE_GATE_positive_slopes() {
        verify(detector(2, 1, 1, 1.0, 0.0), series(0.0, 5.0, 10.0, 11.0), 3, ElliottDegree.CYCLE);
    }

    @Test
    void ZERO_AFTER_SLOPE_NOT_A_REVERSAL_flat_after_window() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 5.0, 5.0, 5.0), 3, ElliottDegree.MINOR);
    }

    @Test
    void HIGH_REVERSAL_FAILS_FIRST_PERSISTENCE_CHECK_nonnegative_following_slope() {
        verify(detector(2, 2, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0, 0.0), 4, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void LOW_REVERSAL_FAILS_LATER_PERSISTENCE_CHECK_nonpositive_later_slope() {
        verify(detector(2, 2, 1, 0.0, 0.0), series(0.0, -10.0, 0.0, 10.0, 0.0), 4, ElliottDegree.MINOR);
    }

    @Test
    void FIRST_HIGH_WITH_INTERIOR_EXTREME_high_override() {
        verify(detector(3, 1, 1, 0.0, 0.0), series(new double[] { 0.0, 10.0, 20.0, 10.0, 0.0, -10.0 }, new double[] { 1.0, 11.0, 21.0, 50.0, 1.0, -9.0 }, new double[] { -1.0, 9.0, 19.0, 9.0, -1.0, -11.0 }), 99, ElliottDegree.PRIMARY);
    }

    @Test
    void FIRST_LOW_AT_TRANSITION_END_low_override() {
        verify(detector(3, 1, 1, 0.0, 0.0), series(new double[] { 0.0, -10.0, -20.0, -10.0, 0.0, 10.0 }, new double[] { 1.0, -9.0, -19.0, -9.0, 1.0, 11.0 }, new double[] { -1.0, -11.0, -21.0, -11.0, -60.0, 9.0 }), 5, ElliottDegree.MINOR);
    }

    @Test
    void ALTERNATING_HIGH_AND_LOW_APPEND_two_pivots() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0, 0.0, 10.0), 5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SUCCESSIVE_HIGH_REPLACES_WEAKER_HIGH_later_higher_high() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0, 0.0, 20.0, 0.0, -20.0), 7, ElliottDegree.MINOR);
    }

    @Test
    void SUCCESSIVE_LOW_REPLACES_WEAKER_LOW_later_lower_low() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, -10.0, 0.0, 10.0, 0.0, -20.0, 0.0, 20.0), 99, ElliottDegree.CYCLE);
    }

    @Test
    void SUCCESSIVE_HIGH_DOES_NOT_REPLACE_EQUAL_OR_LOWER_HIGH_lower_high() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 20.0, 0.0, -20.0, 0.0, 10.0, 0.0, -10.0), 7, ElliottDegree.MINOR);
    }

    @Test
    void ATR_FILTER_REJECTS_WEAK_OPPOSITE_PIVOT_large_multiplier() {
        verify(detector(2, 1, 1, 0.0, 100.0), series(0.0, 10.0, 0.0, -10.0, 0.0, 10.0), 5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ATR_FILTER_ACCEPTS_EXACT_THRESHOLD_inclusive_comparison() {
        verify(detector(2, 1, 1, 0.0, 1.0), series(0.0, 100.0, 0.0, -100.0, 0.0, 100.0), 5, ElliottDegree.MINOR);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASSES_FILTER_small_reversal() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 1.0, 0.0, -1.0, 0.0, 1.0), 100, ElliottDegree.PRIMARY);
    }

    @Test
    void NONFINITE_ATR_REJECTS_LATER_PIVOT_period_exceeds_history() {
        verify(detector(2, 1, 20, 0.0, 1.0), series(0.0, 10.0, 0.0, -10.0, 0.0, 10.0), 5, ElliottDegree.MINOR);
    }

    @Test
    void NAN_CLOSE_SKIPS_CANDIDATE_AT_SLOPE_GATE_nonfinite_close() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(new double[] { 0.0, Double.NaN, 0.0, -10.0 }, new double[] { 1.0, 1.0, 1.0, -9.0 }, new double[] { -1.0, -1.0, -1.0, -11.0 }), 3, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void NAN_EXTREME_VALUE_RETURNS_NULL_PIVOT_nonfinite_high() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(new double[] { 0.0, 10.0, 0.0, -10.0 }, new double[] { 1.0, Double.NaN, 1.0, -9.0 }, new double[] { -1.0, 9.0, -1.0, -11.0 }), 3, ElliottDegree.MINOR);
    }
}
