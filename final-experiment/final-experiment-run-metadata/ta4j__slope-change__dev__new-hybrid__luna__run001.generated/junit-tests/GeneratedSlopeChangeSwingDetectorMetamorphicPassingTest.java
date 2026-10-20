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

    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");

    private static void run(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static BarSeries bars(double[] closes) {
        return bars(10.0, closes);
    }

    private static BarSeries bars(double volume, double[] closes) {
        BarSeries series = new BaseBarSeriesBuilder().withName("slope-change-fixture").build();
        for (int i = 0; i < closes.length; i++) {
            Num close = series.numFactory().numOf(closes[i]);
            Num high = close.plus(series.numFactory().numOf(0.5));
            Num low = close.minus(series.numFactory().numOf(0.5));
            Num open = close;
            Num barVolume = series.numFactory().numOf(volume);
            Num amount = series.numFactory().numOf(1.0);
            Instant begin = START.plus(Duration.ofDays(i));
            Instant end = START.plus(Duration.ofDays(i + 1L));
            series.addBar(new BaseBar(Duration.ofDays(1), begin, end, open, high, low, close, barVolume, amount, 0L));
        }
        return series;
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static double[] wave() {
        return new double[] { 1, 2, 4, 3, 2, 1, 2, 3, 5, 4, 3, 2, 1, 3, 6, 4, 2, 4, 7, 5, 3, 1, 3, 6 };
    }

    private static double[] highWave() {
        return new double[] { 1, 2, 4, 3, 2, 1, 2, 3, 5, 4, 3, 2, 1 };
    }

    private static double[] lowWave() {
        return new double[] { 5, 4, 2, 3, 4, 5, 4, 3, 1, 2, 3, 4, 5 };
    }

    @Test
    public void BELOW_FIRST_CANDIDATE_variation1() {
        run(detector(5, 2, 3, 0.0, 0.5), bars(new double[] { 1, 2, 3, 4, 5, 6, 7, 8 }), 0, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void TOO_SHORT_FOR_TWO_REGRESSION_WINDOWS_variation1() {
        run(detector(4, 2, 3, 0.0, 1.0), bars(new double[] { 7, 6, 5, 4, 3, 2, 1 }), 6, ElliottDegree.MINOR);
    }

    @Test
    public void FLAT_OR_MONOTONE_NO_DIRECTION_CHANGE_variation1() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(new double[] { 4, 4, 4, 4, 4, 4, 4, 4, 4 }), 8, ElliottDegree.PRIMARY);
    }

    @Test
    public void FLAT_OR_MONOTONE_NO_DIRECTION_CHANGE_variation2() {
        run(detector(3, 1, 2, 0.0, 0.5), bars(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }), Integer.MAX_VALUE, ElliottDegree.CYCLE);
    }

    @Test
    public void SLOPE_CHANGE_BELOW_THRESHOLD_variation1() {
        run(detector(3, 1, 2, 10.0, 0.5), bars(new double[] { 10.0, 10.1, 10.2, 10.1, 10.0, 10.1, 10.2 }), -1, ElliottDegree.MINOR);
    }

    @Test
    public void HIGH_REVERSAL_FAILS_PERSISTENCE_variation1() {
        run(detector(3, 2, 2, 0.0, 0.5), bars(new double[] { 1, 2, 3, 4, 3, 2, 3, 4, 5 }), 8, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void LOW_REVERSAL_FAILS_PERSISTENCE_variation1() {
        run(detector(3, 2, 2, 0.0, 0.0), bars(new double[] { 5, 4, 3, 2, 3, 4, 3, 2, 1 }), 8, ElliottDegree.MINOR);
    }

    @Test
    public void FIRST_CONFIRMED_HIGH_variation1() {
        run(detector(3, 1, 3, 0.0, 0.5), bars(new double[] { 1, 2, 3, 5, 4, 3, 2, 1, 2, 3 }), 9, ElliottDegree.PRIMARY);
    }

    @Test
    public void FIRST_CONFIRMED_LOW_variation1() {
        run(detector(3, 1, 3, 0.0, 0.5), bars(new double[] { 5, 4, 3, 1, 2, 3, 4, 5, 4, 3 }), 9, ElliottDegree.MINOR);
    }

    @Test
    public void EXTREME_TIE_USES_FIRST_OCCURRENCE_variation1() {
        run(detector(3, 1, 2, 0.0, 0.5), bars(new double[] { 1, 2, 3, 4, 3.5, 3.5, 2, 1 }), 7, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void EXTREME_TIE_USES_FIRST_OCCURRENCE_variation2() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(new double[] { 5, 4, 3, 2, 2.5, 2.5, 4, 5 }), 7, ElliottDegree.CYCLE);
    }

    @Test
    public void SECOND_PIVOT_PASSES_ATR_FILTER_variation1() {
        run(detector(3, 1, 2, 0.0, 0.1), bars(new double[] { 1, 2, 4, 3, 2, 1, 2, 3, 5, 4, 3 }), 10, ElliottDegree.MINOR);
    }

    @Test
    public void WEAK_REVERSAL_REJECTED_BY_ATR_variation1() {
        run(detector(3, 1, 2, 0.0, 100.0), bars(new double[] { 1, 2, 4, 3, 2, 1, 2, 2.1, 2.2, 2.1 }), 9, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation1() {
        run(detector(3, 1, 50, 0.0, 1.0), bars(new double[] { 1, 2, 4, 3, 2, 1, 2, 3, 4, 3 }), 9, ElliottDegree.PRIMARY);
    }

    @Test
    public void ZERO_ATR_MULTIPLIER_ACCEPTS_LATER_PIVOT_variation1() {
        run(detector(3, 1, 50, 0.0, 0.0), bars(new double[] { 1, 2, 4, 3, 2, 1, 2, 3, 4, 3, 2 }), 10, ElliottDegree.MINOR);
    }

    @Test
    public void HIGH_REPLACED_BY_HIGHER_HIGH_variation1() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(new double[] { 1, 2, 4, 3, 2, 3, 5, 4, 3, 2 }), 9, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void LOW_REPLACED_BY_LOWER_LOW_variation1() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(new double[] { 5, 4, 2, 3, 4, 3, 1, 2, 3, 4 }), 9, ElliottDegree.MINOR);
    }

    @Test
    public void SAME_TYPE_NONIMPROVING_PIVOT_RETAINED_variation1() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(new double[] { 1, 2, 4, 3, 2, 3, 3.5, 3, 2, 3 }), 9, ElliottDegree.PRIMARY);
    }

    @Test
    public void SAME_TYPE_NONIMPROVING_PIVOT_RETAINED_variation2() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(new double[] { 5, 4, 2, 3, 4, 3, 2, 2.5, 3, 4 }), 9, ElliottDegree.CYCLE);
    }

    @Test
    public void MULTIPLE_ALTERNATING_PIVOTS_AND_SWINGS_variation1() {
        run(detector(3, 1, 2, 0.0, 0.1), bars(wave()), 23, ElliottDegree.MINOR);
    }

    @Test
    public void MULTIPLE_ALTERNATING_PIVOTS_AND_SWINGS_variation2() {
        run(detector(4, 1, 3, 0.0, 0.0), bars(new double[] { 1, 2, 4, 7, 4, 2, 5, 8, 5, 2, 4, 7, 3, 1 }), 13, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void INDEX_CLAMPED_BELOW_BEGIN_variation1() {
        run(detector(3, 1, 2, 0.0, 0.5), bars(highWave()), -1, ElliottDegree.MINOR);
    }

    @Test
    public void INDEX_AT_LAST_CANDIDATE_variation1() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(new double[] { 1, 2, 4, 3, 2, 1, 2 }), 5, ElliottDegree.PRIMARY);
    }

    @Test
    public void INDEX_AT_LAST_CANDIDATE_variation2() {
        run(detector(3, 1, 2, 100.0, 0.0), bars(new double[] { 1, 2, 4, 3, 2, 1, 2 }), 5, ElliottDegree.CYCLE);
    }

    @Test
    public void INDEX_ABOVE_SERIES_END_variation1() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(highWave()), Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void ALL_AVAILABLE_DEGREES_variation1() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(wave()), 23, ElliottDegree.PRIMARY);
    }

    @Test
    public void ALL_AVAILABLE_DEGREES_variation2() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(wave()), 23, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void NONZERO_VOLUME_TRANSLATION_variation1() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(25.0, new double[] { 1, 3, 5, 3, 1, 4, 6, 3 }), 7, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ZERO_VOLUME_TRANSLATION_variation1() {
        run(detector(3, 1, 2, 0.0, 0.0), bars(0.0, new double[] { 1, 3, 5, 3, 1, 4, 6, 3 }), 7, ElliottDegree.MINOR);
    }

    @Test
    public void LARGER_WINDOW_CHANGES_CANDIDATE_AVAILABILITY_variation1() {
        run(detector(6, 2, 3, 0.0, 0.5), bars(new double[] { 1, 2, 3, 5, 4, 3, 2, 1, 2, 3, 5, 4, 3, 2, 1 }), 14, ElliottDegree.PRIMARY);
    }
}
