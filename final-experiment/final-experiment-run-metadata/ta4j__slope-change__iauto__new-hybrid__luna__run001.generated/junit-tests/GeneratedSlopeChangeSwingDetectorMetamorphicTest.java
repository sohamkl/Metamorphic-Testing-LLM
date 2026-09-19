import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

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

public class GeneratedSlopeChangeSwingDetectorMetamorphicTest {

    @Test
    void EMPTY_SERIES_variation1() {
        verify(new SlopeChangeSwingDetector(1), series(), -10, "MINOR");
    }

    @Test
    void ONE_BAR_SERIES_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.5)),
                series(10.0), 0, "PRIMARY");
    }

    @Test
    void WINDOW_ONE_ZERO_SLOPE_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(1, 1, 2, 0.0, 0.0)),
                series(1.0, 2.0, 1.0), 100, "MINOR");
    }

    @Test
    void BELOW_FIRST_CANDIDATE_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.5)),
                series(1.0, 2.0, 3.0, 2.0, 1.0, 2.0), -1, "CYCLE");
    }

    @Test
    void EXACT_FIRST_CANDIDATE_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 1, 0.0, 0.0)),
                series(1.0, 2.0, 4.0, 3.0), 3, "MINOR");
    }

    @Test
    void EXACT_FIRST_CANDIDATE_variation2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 2, 2, 0.0, 0.5)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 1.0), 5, "PRIMARY");
    }

    @Test
    void EXACT_LAST_CANDIDATE_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 1, 0.0, 0.0)),
                series(4.0, 3.0, 2.0, 3.0), 3, "MINOR");
    }

    @Test
    void EXACT_LAST_CANDIDATE_variation2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 2, 0.0, 0.5)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 1.0, 0.0), 6, "INTERMEDIATE");
    }

    @Test
    void INDEX_CLAMP_BELOW_variation1() {
        verify(new SlopeChangeSwingDetector(2), series(1.0, 2.0, 3.0), -100, "MINOR");
    }

    @Test
    void INDEX_CLAMP_ABOVE_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 1, 0.0, 0.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 4.0), 100, "PRIMARY");
    }

    @Test
    void SAME_SIGN_SLOPES_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, 3.0, 4.0, 5.0, 6.0), 5, "MINOR");
    }

    @Test
    void HIGH_REVERSAL_PIVOT_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0), 4, "INTERMEDIATE");
    }

    @Test
    void HIGH_REVERSAL_PIVOT_variation2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 2, 0, 0.0, 0.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 1.0, 0.0), 6, "MINOR");
    }

    @Test
    void LOW_REVERSAL_PIVOT_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(4.0, 3.0, 2.0, 3.0, 4.0), 4, "PRIMARY");
    }

    @Test
    void LOW_REVERSAL_PIVOT_variation2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 2, 0, 0.0, 0.0)),
                series(4.0, 3.0, 2.0, 3.0, 4.0, 5.0, 6.0), 6, "CYCLE");
    }

    @Test
    void SLOPE_CHANGE_BELOW_THRESHOLD_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 100.0, 0.0)),
                series(1.0, 2.0, 3.0, 2.0, 1.0), 4, "MINOR");
    }

    @Test
    void SLOPE_CHANGE_EXACT_THRESHOLD_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 1, 2.0, 0.0)),
                series(1.0, 2.0, 3.0, 2.0), 3, "MINOR");
    }

    @Test
    void PERSISTENCE_FAILURE_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 2, 0, 0.0, 0.0)),
                series(1.0, 2.0, 3.0, 2.0, 3.0, 4.0, 5.0), 6, "INTERMEDIATE");
    }

    @Test
    void FIRST_PIVOT_ATR_BYPASS_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 10.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0), 4, "MINOR");
    }

    @Test
    void ATR_FILTER_BELOW_THRESHOLD_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 10.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 4.0), 6, "INTERMEDIATE");
    }

    @Test
    void ATR_FILTER_EXACT_THRESHOLD_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.5)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 4.0), 6, "PRIMARY");
    }

    @Test
    void ATR_FILTER_ZERO_MULTIPLIER_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 5.0), 6, "MINOR");
    }

    @Test
    void SAME_TYPE_STRONGER_REPLACEMENT_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 5.0, 4.0, 3.0), 8, "MINOR");
    }

    @Test
    void SAME_TYPE_STRONGER_REPLACEMENT_variation2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.2)),
                series(2.0, 3.0, 5.0, 4.0, 3.0, 4.0, 6.0, 5.0, 4.0), 8, "PRIMARY");
    }

    @Test
    void SAME_TYPE_WEAKER_RETAINED_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 3.0), 6, "MINOR");
    }

    @Test
    void SAME_TYPE_WEAKER_RETAINED_variation2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, 3.0, 5.0, 4.0, 3.0, 4.0, 4.0, 4.0), 8, "PRIMARY");
    }

    @Test
    void ALTERNATING_PIVOTS_AND_SWINGS_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 5.0), 6, "MINOR");
    }

    @Test
    void ALTERNATING_PIVOTS_AND_SWINGS_variation2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.1)),
                series(5.0, 4.0, 3.0, 4.0, 5.0, 4.0, 3.0), 6, "INTERMEDIATE");
    }

    @Test
    void EQUAL_EXTREME_RETains_EARLIEST_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, 3.0, 2.0, 1.0), 4, "MINOR");
    }

    @Test
    void EQUAL_EXTREME_RETains_EARLIEST_variation2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(4.0, 3.0, 2.0, 3.0, 4.0), 4, "PRIMARY");
    }

    @Test
    void NONFINITE_CLOSE_SLOPE_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, Double.NaN, 3.0, 2.0), 4, "MINOR");
    }

    @Test
    void NONFINITE_EXTREME_VALUE_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                seriesWithHigh(Double.NaN, 1.0, 4.0, 3.0, 2.0), 4, "PRIMARY");
    }

    @Test
    void NONFINITE_LATER_ATR_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 10.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 4.0), 6, "MINOR");
    }

    @Test
    void PRICE_TRANSLATION_WITH_VOLUME_AMOUNT_UPDATE_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 2, 0.0, 0.5)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 5.0), 6, "INTERMEDIATE");
    }

    @Test
    void PRICE_TRANSLATION_WITH_VOLUME_AMOUNT_UPDATE_variation2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(5.0, 4.0, 3.0, 4.0, 5.0, 4.0, 3.0), 6, "MINOR");
    }

    @Test
    void PRICE_TRANSLATION_WITH_VOLUME_AMOUNT_UPDATE_variation3() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.5)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 5.0), 100, "PRIMARY");
    }

    @Test
    void VALID_DEGREE_VARIATION_variation1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0), 4, "MINOR");
    }

    @Test
    void VALID_DEGREE_VARIATION_variation2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 0, 0.0, 0.0)),
                series(1.0, 2.0, 4.0, 3.0, 2.0, 3.0, 5.0), 6, "PRIMARY");
    }

    private static void verify(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            String degreeName) {
        ElliottDegree degree = ElliottDegree.valueOf(degreeName);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);

        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                detector, sourceSeries, index, degree);

        SlopeChangeSwingDetector followUpDetector =
                (SlopeChangeSwingDetector) followUp[0];
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
        org.junit.jupiter.api.Assertions.assertEquals(
                sourceOutput.pivots().size(), followUpOutput.pivots().size());

        for (int i = 0; i < sourceOutput.pivots().size(); i++) {
            SwingPivot sourcePivot = sourceOutput.pivots().get(i);
            SwingPivot followUpPivot = followUpOutput.pivots().get(i);

            org.junit.jupiter.api.Assertions.assertEquals(
                    sourcePivot.index(), followUpPivot.index());
            org.junit.jupiter.api.Assertions.assertEquals(
                    sourcePivot.type(), followUpPivot.type());
            assertTranslated(sourcePivot.price(), followUpPivot.price());
        }

        org.junit.jupiter.api.Assertions.assertEquals(
                sourceOutput.swings().size(), followUpOutput.swings().size());

        for (int i = 0; i < sourceOutput.swings().size(); i++) {
            ElliottSwing sourceSwing = sourceOutput.swings().get(i);
            ElliottSwing followUpSwing = followUpOutput.swings().get(i);

            org.junit.jupiter.api.Assertions.assertEquals(
                    sourceSwing.fromIndex(), followUpSwing.fromIndex());
            org.junit.jupiter.api.Assertions.assertEquals(
                    sourceSwing.toIndex(), followUpSwing.toIndex());
            org.junit.jupiter.api.Assertions.assertEquals(
                    sourceSwing.degree(), followUpSwing.degree());
            assertTranslated(sourceSwing.fromPrice(), followUpSwing.fromPrice());
            assertTranslated(sourceSwing.toPrice(), followUpSwing.toPrice());
        }
    }

    private static void assertTranslated(Num source, Num followUp) {
        double expected = source.doubleValue() + 100.0;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(
                1.0e-9,
                Math.max(Math.abs(expected), Math.abs(actual)) * 1.0e-12);

        org.junit.jupiter.api.Assertions.assertTrue(
                Double.isFinite(expected)
                        && Double.isFinite(actual)
                        && Math.abs(expected - actual) <= tolerance);
    }

    private static BarSeries series(double... closes) {
        return seriesWithHighs(closes, null);
    }

    private static BarSeries seriesWithHigh(Double highOverride, double... closes) {
        double[] highs = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = i == 0 && highOverride != null ? highOverride : closes[i] + 0.5;
        }
        return seriesWithHighs(closes, highs);
    }

    private static BarSeries seriesWithHighs(double[] closes, double[] highs) {
        BarSeries result = new BaseBarSeriesBuilder()
                .withName("slope-change-fixture")
                .build();

        ZonedDateTime base = ZonedDateTime.of(
                2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = highs == null ? close + 0.5 : highs[i];
            double low = close - 0.5;
            ZonedDateTime end = base.plusMinutes(i);

            result.addBar(new BaseBar(
                    Duration.ofMinutes(1),
                    end,
                    end,
                    close + 0.25,
                    high,
                    low,
                    close,
                    1.0,
                    close,
                    1));
        }
        return result;
    }
}
