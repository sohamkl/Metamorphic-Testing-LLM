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

public class GeneratedSlopeChangeSwingDetectorMetamorphicTest {

    private static final ElliottDegree DEGREE = ElliottDegree.valueOf("MINUTE");

    private static BarSeries series(int length, int pattern) {
        BarSeries series = new BaseBarSeriesBuilder().withName("slope-change-" + length + "-" + pattern).build();
        double[] wave = { 0.0, 2.0, 4.0, 6.0, 5.0, 3.0, 1.0, -1.0, -3.0, -4.0, -2.0, 0.0, 3.0, 5.0, 4.0, 2.0 };
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < length; i++) {
            double value;
            if (pattern == 0) {
                value = 100.0 + i * 0.25;
            } else if (pattern == 1) {
                value = 100.0 + wave[i % wave.length];
            } else if (pattern == 2) {
                value = 1_000_000_000.0 + wave[(i + 3) % wave.length] * 0.01;
            } else if (pattern == 3) {
                value = 100.0 + wave[(i + pattern) % wave.length] + (i % 5) * 0.05;
            } else {
                value = 50.0 + ((i % 12) < 6 ? i % 6 : 12 - (i % 12)) + (pattern % 3) * 0.1;
            }
            double open = value + (i % 2 == 0 ? -0.2 : 0.2);
            double high = Math.max(open, value) + 1.0 + (i % 3) * 0.1;
            double low = Math.min(open, value) - 1.0 - (i % 2) * 0.1;
            double volume = 100.0 + i;
            Instant begin = start.plusSeconds(i * 60L);
            Instant end = begin.plusSeconds(60L);
            Num openNum = series.numFactory().numOf(open);
            Num highNum = series.numFactory().numOf(high);
            Num lowNum = series.numFactory().numOf(low);
            Num closeNum = series.numFactory().numOf(value);
            Num volumeNum = series.numFactory().numOf(volume);
            Num amountNum = series.numFactory().numOf(volume);
            series.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, openNum, highNum, lowNum, closeNum, volumeNum, amountNum, i + 1L));
        }
        return series;
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeriesBuilder().withName("empty-slope-change").build();
    }

    private static void verify(BarSeries sourceSeries, int index, SlopeChangeSwingDetector detector) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, DEGREE);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, DEGREE);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_SERIES_variation2() {
        verify(emptySeries(), 7, new SlopeChangeSwingDetector(5));
    }

    @Test
    void TOO_SHORT_FOR_CANDIDATE_variation1() {
        verify(series(2, 0), 1, new SlopeChangeSwingDetector(3));
    }

    @Test
    void TOO_SHORT_FOR_CANDIDATE_variation2() {
        verify(series(3, 1), 20, new SlopeChangeSwingDetector(5));
    }

    @Test
    void EXACT_SINGLE_CANDIDATE_BOUNDARY_variation1() {
        verify(series(8, 1), 7, new SlopeChangeSwingDetector(3));
    }

    @Test
    void EXACT_SINGLE_CANDIDATE_BOUNDARY_variation2() {
        verify(series(10, 2), 9, new SlopeChangeSwingDetector(4));
    }

    @Test
    void INDEX_CLAMP_BELOW_BEGIN_variation1() {
        verify(series(12, 1), -5, new SlopeChangeSwingDetector(3));
    }

    @Test
    void INDEX_CLAMP_BELOW_BEGIN_variation2() {
        verify(series(15, 2), -1, new SlopeChangeSwingDetector(4));
    }

    @Test
    void INDEX_CLAMP_ABOVE_END_variation1() {
        verify(series(24, 1), 100, new SlopeChangeSwingDetector(3));
    }

    @Test
    void INDEX_CLAMP_ABOVE_END_variation2() {
        verify(series(30, 2), 300, new SlopeChangeSwingDetector(5));
    }

    @Test
    void SAME_SIGN_SLOPES_variation1() {
        verify(series(14, 0), 13, new SlopeChangeSwingDetector(3));
    }

    @Test
    void SAME_SIGN_SLOPES_variation2() {
        verify(series(18, 0), 17, new SlopeChangeSwingDetector(4));
    }

    @Test
    void SLOPE_CHANGE_AT_THRESHOLD_variation1() {
        verify(series(9, 3), -2, new SlopeChangeSwingDetector(3));
    }

    @Test
    void SLOPE_CHANGE_AT_THRESHOLD_variation2() {
        verify(series(20, 1), 12, new SlopeChangeSwingDetector(4));
    }

    @Test
    void HIGH_DIRECTION_WITH_FAILED_PERSISTENCE_variation1() {
        verify(series(22, 3), 21, new SlopeChangeSwingDetector(3));
    }

    @Test
    void HIGH_DIRECTION_WITH_FAILED_PERSISTENCE_variation2() {
        verify(series(23, 0), 40, new SlopeChangeSwingDetector(4));
    }

    @Test
    void LOW_DIRECTION_WITH_FAILED_PERSISTENCE_variation1() {
        verify(series(2, 2), -3, new SlopeChangeSwingDetector(3));
    }

    @Test
    void LOW_DIRECTION_WITH_FAILED_PERSISTENCE_variation2() {
        verify(series(9, 4), 5, new SlopeChangeSwingDetector(4));
    }

    @Test
    void CONFIRMED_HIGH_EXTREME_variation1() {
        verify(series(28, 1), 27, new SlopeChangeSwingDetector(3));
    }

    @Test
    void CONFIRMED_HIGH_EXTREME_variation2() {
        verify(series(32, 2), 80, new SlopeChangeSwingDetector(4));
    }

    @Test
    void CONFIRMED_HIGH_EXTREME_variation3() {
        verify(series(36, 3), 35, new SlopeChangeSwingDetector(5));
    }

    @Test
    void CONFIRMED_LOW_EXTREME_variation1() {
        verify(series(18, 1), 17, new SlopeChangeSwingDetector(3));
    }

    @Test
    void CONFIRMED_LOW_EXTREME_variation2() {
        verify(series(26, 2), 25, new SlopeChangeSwingDetector(4));
    }

    @Test
    void CONFIRMED_LOW_EXTREME_variation3() {
        verify(series(30, 3), 100, new SlopeChangeSwingDetector(3));
    }

    @Test
    void ALTERNATING_CONFIRMED_PIVOTS_variation1() {
        verify(series(36, 1), -1, new SlopeChangeSwingDetector(3));
    }

    @Test
    void ALTERNATING_CONFIRMED_PIVOTS_variation2() {
        verify(series(40, 2), 39, new SlopeChangeSwingDetector(4));
    }

    @Test
    void ALTERNATING_CONFIRMED_PIVOTS_variation3() {
        verify(series(48, 3), 47, new SlopeChangeSwingDetector(5));
    }

    @Test
    void HIGH_SAME_TYPE_REPLACEMENT_variation1() {
        verify(series(28, 3), 100, new SlopeChangeSwingDetector(3));
    }

    @Test
    void HIGH_SAME_TYPE_REPLACEMENT_variation2() {
        verify(series(34, 1), -4, new SlopeChangeSwingDetector(4));
    }

    @Test
    void LOW_SAME_TYPE_REPLACEMENT_variation1() {
        verify(series(30, 2), 15, new SlopeChangeSwingDetector(3));
    }

    @Test
    void LOW_SAME_TYPE_REPLACEMENT_variation2() {
        verify(series(38, 3), 37, new SlopeChangeSwingDetector(5));
    }

    @Test
    void SAME_TYPE_NONIMPROVING_PIVOT_variation1() {
        verify(series(18, 0), 90, new SlopeChangeSwingDetector(3));
    }

    @Test
    void SAME_TYPE_NONIMPROVING_PIVOT_variation2() {
        verify(series(26, 1), -2, new SlopeChangeSwingDetector(4));
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_variation1() {
        verify(series(20, 1), 10, new SlopeChangeSwingDetector(3));
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_variation2() {
        verify(series(28, 2), 27, new SlopeChangeSwingDetector(4));
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASS_variation1() {
        verify(series(24, 3), 50, new SlopeChangeSwingDetector(3));
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASS_variation2() {
        verify(series(18, 1), -1, new SlopeChangeSwingDetector(4));
    }

    @Test
    void MAGNITUDE_FILTER_REJECTS_WEAK_REVERSAL_variation1() {
        verify(series(24, 0), 20, new SlopeChangeSwingDetector(3));
    }

    @Test
    void MAGNITUDE_FILTER_REJECTS_WEAK_REVERSAL_variation2() {
        verify(series(30, 3), 29, new SlopeChangeSwingDetector(4));
    }

    @Test
    void MAGNITUDE_FILTER_ACCEPTS_EQUAL_THRESHOLD_variation1() {
        verify(series(34, 1), 100, new SlopeChangeSwingDetector(3));
    }

    @Test
    void MAGNITUDE_FILTER_ACCEPTS_EQUAL_THRESHOLD_variation2() {
        verify(series(40, 2), -10, new SlopeChangeSwingDetector(5));
    }

    @Test
    void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation1() {
        verify(series(18, 2), 17, new SlopeChangeSwingDetector(3));
    }

    @Test
    void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation2() {
        verify(series(24, 3), 23, new SlopeChangeSwingDetector(4));
    }

    @Test
    void EXTREME_INTERVAL_FINITE_REQUIREMENT_variation1() {
        verify(series(25, 1), 100, new SlopeChangeSwingDetector(3));
    }

    @Test
    void DEGREE_PRESERVED_IN_SWINGS_variation1() {
        verify(series(36, 2), -1, new SlopeChangeSwingDetector(3));
    }

    @Test
    void DEGREE_PRESERVED_IN_SWINGS_variation2() {
        verify(series(42, 1), 41, new SlopeChangeSwingDetector(4));
    }

    @Test
    void DEGREE_PRESERVED_IN_SWINGS_variation3() {
        verify(series(48, 3), 46, new SlopeChangeSwingDetector(5));
    }

    @Test
    void TRANSLATION_NUMERIC_BOUNDARY_variation1() {
        verify(series(30, 2), 100, new SlopeChangeSwingDetector(3));
    }

    @Test
    void TRANSLATION_NUMERIC_BOUNDARY_variation2() {
        verify(series(30, 2), -1, new SlopeChangeSwingDetector(4));
    }
}
