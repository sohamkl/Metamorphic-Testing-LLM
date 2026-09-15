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
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeriesBuilder().withName("empty-translation-source").build();
    }

    private static BarSeries series(double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int index = 0; index < closes.length; index++) {
            highs[index] = closes[index] + 1.0;
            lows[index] = closes[index] - 1.0;
        }
        return seriesWithRanges(closes, highs, lows);
    }

    private static BarSeries seriesWithRanges(double[] closes, double[] highs, double[] lows) {
        BarSeries result = new BaseBarSeriesBuilder().withName("deterministic-slope-series").build();
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        for (int index = 0; index < closes.length; index++) {
            result.addBar(new BaseBar(Duration.ofMinutes(1), start.plus(Duration.ofMinutes(index)), start.plus(Duration.ofMinutes(index + 1)), result.numFactory().numOf(closes[index]), result.numFactory().numOf(highs[index]), result.numFactory().numOf(lows[index]), result.numFactory().numOf(closes[index]), result.numFactory().numOf(1.0), result.numFactory().numOf(closes[index]), 1L));
        }
        return result;
    }

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

    @Test
    public void INDEX_BEFORE_BEGIN_CLAMPS_TO_ZERO_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(10, 11, 12, 13), -5, ElliottDegree.MINUTE);
    }

    @Test
    public void HISTORY_ONE_BAR_SHORT_OF_FIRST_CANDIDATE_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(10, 11, 12, 13, 14), 4, ElliottDegree.MINUETTE);
    }

    @Test
    public void MINIMUM_HISTORY_ONE_CANDIDATE_SAME_DIRECTION_variation1() {
        exercise(new SlopeChangeSwingDetector(3), series(10, 11, 12, 13, 14, 15), 5, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void MINIMUM_HISTORY_FLAT_SLOPES_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(20, 20, 20, 20, 20, 20), 100, ElliottDegree.MINOR);
    }

    @Test
    public void SLOPE_CHANGE_BELOW_CONFIGURED_THRESHOLD_variation1() {
        exercise(detector(3, 1, 1, 10.0, 0.0), series(10, 11, 12, 13, 12, 11), 5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void CONFIRMED_HIGH_FIRST_PIVOT_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(10, 11, 12, 13, 12, 11), 5, ElliottDegree.PRIMARY);
    }

    @Test
    public void CONFIRMED_LOW_FIRST_PIVOT_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(13, 12, 11, 10, 11, 12), 5, ElliottDegree.CYCLE);
    }

    @Test
    public void HIGH_PERSISTENCE_FAILURE_ON_ZERO_SLOPE_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(10, 11, 12, 13, 12, 12), 5, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void LOW_PERSISTENCE_FAILURE_BY_DIRECTION_REBOUND_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(13, 12, 11, 10, 11, 10), 100, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void EARLIEST_TIED_HIGH_EXTREME_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), seriesWithRanges(new double[] { 10, 11, 12, 13, 12, 11 }, new double[] { 11, 12, 15, 15, 13, 12 }, new double[] { 9, 10, 11, 12, 11, 10 }), 5, ElliottDegree.MINOR);
    }

    @Test
    public void EARLIEST_TIED_LOW_EXTREME_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), seriesWithRanges(new double[] { 13, 12, 11, 10, 11, 12 }, new double[] { 14, 13, 12, 11, 12, 13 }, new double[] { 12, 11, 8, 8, 10, 11 }), 5, ElliottDegree.MINUTE);
    }

    @Test
    public void OPPOSITE_TYPE_REJECTED_BY_ATR_MAGNITUDE_variation1() {
        exercise(detector(3, 1, 1, 0.0, 100.0), series(10, 11, 12, 13, 12, 11, 10, 11, 12, 13, 12, 11), 11, ElliottDegree.MINUETTE);
    }

    @Test
    public void OPPOSITE_TYPE_ACCEPTED_AT_ATR_BOUNDARY_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.5), series(10, 11, 12, 13, 12, 11, 10, 11, 12, 13, 12, 11), 11, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void ZERO_ATR_MULTIPLIER_BYPASSES_ATR_FILTER_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(10, 11, 12, 13, 12, 11, 10, 11, 12, 13, 12, 11), 100, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ALTERNATING_HIGH_LOW_HIGH_SWINGS_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(10, 11, 12, 13, 12, 11, 10, 11, 12, 13, 12, 11, 10, 11, 12, 13, 12, 11), 17, ElliottDegree.PRIMARY);
    }

    @Test
    public void HIGH_REPLACED_BY_LATER_HIGHER_HIGH_variation1() {
        exercise(detector(3, 1, 1, 0.0, 100.0), series(10, 11, 12, 13, 12, 11, 12, 13, 14, 15, 14, 13), 11, ElliottDegree.CYCLE);
    }

    @Test
    public void LOWER_SUBSEQUENT_HIGH_IS_NOT_RETAINED_variation1() {
        exercise(detector(3, 1, 1, 0.0, 100.0), series(10, 11, 12, 14, 13, 12, 11, 12, 13, 12, 11, 10), 11, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void LOW_REPLACED_BY_LATER_LOWER_LOW_variation1() {
        exercise(detector(3, 1, 1, 0.0, 100.0), series(15, 14, 13, 12, 13, 14, 13, 12, 11, 10, 11, 12), 11, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void HIGHER_SUBSEQUENT_LOW_IS_NOT_RETAINED_variation1() {
        exercise(detector(3, 1, 1, 0.0, 100.0), series(15, 14, 13, 11, 12, 13, 14, 13, 12, 13, 14, 15), 100, ElliottDegree.MINOR);
    }

    @Test
    public void INDEX_PREFIX_EXCLUDES_LATER_TURN_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(10, 11, 12, 13, 14, 15, 16, 15, 14, 13, 12), 4, ElliottDegree.MINUTE);
    }

    @Test
    public void INDEX_AFTER_END_EQUALS_END_EVALUATION_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(10, 11, 12, 13, 12, 11, 10, 11, 12, 13, 12, 11), 1000, ElliottDegree.MINUETTE);
    }

    @Test
    public void DEGREE_VARIATION_ON_NONEMPTY_SWINGS_variation1() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(10, 11, 12, 13, 12, 11, 10, 11, 12, 13, 12, 11), 11, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void DEGREE_VARIATION_ON_NONEMPTY_SWINGS_variation2() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(20, 21, 22, 23, 22, 21, 20, 21, 22, 23, 22, 21), 11, ElliottDegree.MINOR);
    }

    @Test
    public void DEGREE_VARIATION_ON_NONEMPTY_SWINGS_variation3() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(30, 31, 32, 33, 32, 31, 30, 31, 32, 33, 32, 31), 100, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void DEGREE_VARIATION_ON_NONEMPTY_SWINGS_variation4() {
        exercise(detector(3, 1, 1, 0.0, 0.0), series(40, 41, 42, 43, 42, 41, 40, 41, 42, 43, 42, 41), 11, ElliottDegree.PRIMARY);
    }
}
