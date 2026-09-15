import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static void exercise(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index) {
        ElliottDegree degree = ElliottDegree.values()[0];
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static BarSeries bars(double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 1.0;
            lows[i] = closes[i] - 1.0;
        }
        return bars(closes, highs, lows);
    }

    private static BarSeries bars(double[] closes, double[] highs, double[] lows) {
        BarSeries series = new BaseBarSeriesBuilder().withName("metamorphic-source").build();
        for (int i = 0; i < closes.length; i++) {
            Num open = series.numFactory().numOf(closes[i]);
            Num high = series.numFactory().numOf(highs[i]);
            Num low = series.numFactory().numOf(lows[i]);
            Num close = series.numFactory().numOf(closes[i]);
            Num volume = series.numFactory().numOf(1);
            Num amount = series.numFactory().numOf(closes[i]);
            Instant begin = BASE_TIME.plus(Duration.ofHours(i));
            Instant end = begin.plus(Duration.ofHours(1));
            series.addBar(new BaseBar(Duration.ofHours(1), begin, end, open, high, low, close, volume, amount, 1L));
        }
        return series;
    }

    @Test
    void NONEMPTY_BEFORE_FIRST_POSSIBLE_CANDIDATE_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 11, 12, 11), 3);
    }

    @Test
    void NEGATIVE_INDEX_CLAMPED_TO_ZERO_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 11, 12, 13, 12, 11, 10, 11, 12, 13, 12, 11), -3);
    }

    @Test
    void INDEX_ABOVE_END_CLAMPED_TO_SERIES_END_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 12, 14, 16, 14, 12, 10, 9, 8, 7, 6, 5), 100);
    }

    @Test
    void EXACT_FIRST_CANDIDATE_HIGH_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 12, 14, 13, 11, 9, 8, 7), 7);
    }

    @Test
    void EXACT_FIRST_CANDIDATE_LOW_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(14, 12, 10, 11, 13, 15, 16, 17), 7);
    }

    @Test
    void FLAT_CLOSES_SKIP_SLOPE_DIRECTION_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(25, 25, 25, 25, 25, 25, 25, 25, 25, 25), 9);
    }

    @Test
    void SAME_POSITIVE_DIRECTION_SKIP_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 12, 14, 15, 16, 17, 18, 19, 20, 21), 9);
    }

    @Test
    void SAME_NEGATIVE_DIRECTION_SKIP_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(30, 28, 26, 25, 24, 23, 22, 21, 20, 19), 9);
    }

    @Test
    void SLOPE_CHANGE_STRICTLY_BELOW_MINIMUM_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 10.05, 10.10, 10.08, 10.06, 10.04, 10.03, 10.02), 7);
    }

    @Test
    void SLOPE_CHANGE_EXACTLY_MINIMUM_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 11, 12, 11, 10, 9, 8, 7), 7);
    }

    @Test
    void HIGH_PERSISTENCE_ZERO_SLOPE_FAILURE_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 12, 14, 12, 10, 8, 8, 8, 8, 8), 9);
    }

    @Test
    void LOW_PERSISTENCE_WRONG_SIGN_FAILURE_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(14, 12, 10, 12, 14, 16, 14, 12, 10, 8), 9);
    }

    @Test
    void HIGH_PERSISTENCE_LATE_FAILURE_variation1() {
        exercise(new SlopeChangeSwingDetector(4), bars(10, 12, 14, 16, 14, 12, 10, 8, 7, 8, 9, 10, 11), 12);
    }

    @Test
    void UNIQUE_HIGH_EXTREME_DIFFERS_FROM_CLOSE_TURN_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(new double[] { 10, 12, 14, 13, 11, 9, 8, 7 }, new double[] { 11, 13, 15, 30, 12, 10, 9, 8 }, new double[] { 9, 11, 13, 12, 10, 8, 7, 6 }), 7);
    }

    @Test
    void UNIQUE_LOW_EXTREME_DIFFERS_FROM_CLOSE_TURN_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(new double[] { 14, 12, 10, 11, 13, 15, 16, 17 }, new double[] { 15, 13, 11, 12, 14, 16, 17, 18 }, new double[] { 13, 11, 9, 2, 12, 14, 15, 16 }), 7);
    }

    @Test
    void TIED_HIGH_EXTREME_KEEPS_FIRST_INDEX_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(new double[] { 10, 12, 14, 13, 11, 9, 8, 7 }, new double[] { 11, 13, 25, 25, 12, 10, 9, 8 }, new double[] { 9, 11, 13, 12, 10, 8, 7, 6 }), 7);
    }

    @Test
    void TIED_LOW_EXTREME_KEEPS_FIRST_INDEX_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(new double[] { 14, 12, 10, 11, 13, 15, 16, 17 }, new double[] { 15, 13, 11, 12, 14, 16, 17, 18 }, new double[] { 13, 4, 4, 10, 12, 14, 15, 16 }), 7);
    }

    @Test
    void HIGH_THEN_LOW_APPENDS_AND_CREATES_SWING_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 12, 14, 13, 11, 9, 8, 9, 11, 13, 12, 10, 8, 7, 8, 10, 12), 16);
    }

    @Test
    void LOW_THEN_HIGH_APPENDS_AND_CREATES_SWING_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(16, 14, 12, 13, 15, 17, 18, 17, 15, 13, 14, 16, 18, 19, 18, 16, 14), 16);
    }

    @Test
    void THREE_ALTERNATING_PIVOTS_CREATE_TWO_SWINGS_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 12, 14, 13, 11, 9, 8, 9, 11, 13, 12, 10, 8, 9, 11, 13, 12, 10, 8, 7), 19);
    }

    @Test
    void SAME_HIGH_STRONGER_REPLACES_PREVIOUS_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(new double[] { 10, 12, 14, 13, 11, 9, 10, 12, 14, 16, 14, 12, 10, 9 }, new double[] { 11, 13, 20, 14, 12, 10, 11, 13, 15, 30, 15, 13, 11, 10 }, new double[] { 9, 11, 13, 12, 10, 8, 9, 11, 13, 15, 13, 11, 9, 8 }), 13);
    }

    @Test
    void SAME_HIGH_WEAKER_IS_IGNORED_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(new double[] { 10, 12, 14, 13, 11, 9, 10, 12, 14, 15, 13, 11, 9, 8 }, new double[] { 11, 13, 30, 14, 12, 10, 11, 13, 15, 20, 14, 12, 10, 9 }, new double[] { 9, 11, 13, 12, 10, 8, 9, 11, 13, 14, 12, 10, 8, 7 }), 13);
    }

    @Test
    void SAME_LOW_LOWER_REPLACES_PREVIOUS_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(new double[] { 16, 14, 12, 13, 15, 17, 16, 14, 12, 10, 12, 14, 16, 17 }, new double[] { 17, 15, 13, 14, 16, 18, 17, 15, 13, 11, 13, 15, 17, 18 }, new double[] { 15, 13, 8, 12, 14, 16, 15, 13, 11, 2, 11, 13, 15, 16 }), 13);
    }

    @Test
    void SAME_LOW_WEAKER_IS_IGNORED_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(new double[] { 16, 14, 12, 13, 15, 17, 16, 14, 12, 11, 13, 15, 17, 18 }, new double[] { 17, 15, 13, 14, 16, 18, 17, 15, 13, 12, 14, 16, 18, 19 }, new double[] { 15, 13, 2, 12, 14, 16, 15, 13, 11, 8, 12, 14, 16, 17 }), 13);
    }

    @Test
    void SECOND_PIVOT_ATR_DISTANCE_STRICTLY_BELOW_THRESHOLD_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(20, 22, 24, 23, 21, 20, 21, 22, 23, 22, 21, 20, 21, 22, 23), 14);
    }

    @Test
    void SECOND_PIVOT_ATR_DISTANCE_EXACTLY_THRESHOLD_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 12, 14, 13, 11, 9, 8, 9, 11, 13, 12, 10, 8, 9, 11, 13), 15);
    }

    @Test
    void SECOND_PIVOT_ATR_DISTANCE_ABOVE_THRESHOLD_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 14, 18, 16, 12, 8, 6, 10, 16, 22, 20, 14, 8, 5, 9, 15), 15);
    }

    @Test
    void FIRST_PIVOT_BYPASSES_ATR_FILTER_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(30, 32, 34, 31, 28, 25, 24, 23), 7);
    }

    @Test
    void INTERIOR_INDEX_TRUNCATES_LATER_PIVOTS_variation1() {
        exercise(new SlopeChangeSwingDetector(3), bars(10, 12, 14, 13, 11, 9, 8, 9, 11, 13, 12, 10, 8, 7, 8, 10, 12, 14, 13), 10);
    }

    @Test
    void DISTINCT_DEGREE_WITH_NONEMPTY_SWING_OUTPUT_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(3);
        BarSeries sourceSeries = bars(12, 14, 16, 14, 12, 10, 9, 10, 12, 14, 13, 11, 9, 8, 9, 11);
        ElliottDegree degree = ElliottDegree.values()[ElliottDegree.values().length - 1];
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, 15, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, 15, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
