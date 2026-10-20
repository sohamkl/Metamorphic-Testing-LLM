import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.DecimalNum;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicTest {

    @Test
    void EMPTY_SERIES_variation1() {
        run(new SlopeChangeSwingDetector(3), emptySeries(), -10, ElliottDegree.MINOR);
    }

    @Test
    void TOO_SHORT_FOR_CANDIDATE_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(5, 2, 2, 0.0, 0.0)),
                priceSeries(8, 0.0), 7, ElliottDegree.MINOR);
    }

    @Test
    void EXACT_SINGLE_CANDIDATE_BOUNDARY_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 2, 0.0, 0.0)),
                priceSeries(6, 0.4), 5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void INDEX_BELOW_SERIES_BEGIN_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(80, 0.0), -1, ElliottDegree.MINOR);
    }

    @Test
    void INDEX_AT_SERIES_BEGIN_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(80, 0.7), 0, ElliottDegree.MINOR);
    }

    @Test
    void INDEX_INSIDE_CANDIDATE_RANGE_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(100, 1.2), 48, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void INDEX_AT_SERIES_END_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(120, 1.8), 119, ElliottDegree.PRIMARY);
    }

    @Test
    void INDEX_ABOVE_SERIES_END_variation1() {
        run(new SlopeChangeSwingDetector(4),
                waveSeries(90, 2.3), 1000, ElliottDegree.MINOR);
    }

    @Test
    void SLOPE_CHANGE_BELOW_THRESHOLD_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(4, 1, 2, 1000.0, 0.0)),
                waveSeries(90, 2.9), 89, ElliottDegree.MINOR);
    }

    @Test
    void SAME_DIRECTION_SLOPES_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(4, 2, 3, 0.0, 0.0)),
                risingSeries(90), 89, ElliottDegree.MINOR);
    }

    @Test
    void HIGH_REVERSAL_PERSISTENCE_SUCCEEDS_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(120, 3.5), 119, ElliottDegree.MINOR);
    }

    @Test
    void LOW_REVERSAL_PERSISTENCE_SUCCEEDS_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(120, 4.1), 119, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void HIGH_PERSISTENCE_FAILS_FIRST_CHECK_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.5)),
                risingSeries(90), 89, ElliottDegree.MINOR);
    }

    @Test
    void LOW_PERSISTENCE_FAILS_LATER_CHECK_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 3, 2, 0.0, 0.0)),
                waveSeries(110, 4.8), 109, ElliottDegree.MINOR);
    }

    @Test
    void UNIQUE_HIGH_EXTREME_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(130, 5.4), 129, ElliottDegree.PRIMARY);
    }

    @Test
    void UNIQUE_LOW_EXTREME_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(4, 1, 2, 0.0, 0.0)),
                waveSeries(130, 6.0), 129, ElliottDegree.PRIMARY);
    }

    @Test
    void TIED_HIGH_EXTREME_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                steppedSeries(120, 0), 119, ElliottDegree.MINOR);
    }

    @Test
    void TIED_LOW_EXTREME_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                steppedSeries(120, 3), 119, ElliottDegree.MINOR);
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_COMPARISON_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 20, 0.0, 100.0)),
                waveSeries(120, 6.6), 119, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_ACCEPTS_REVERSAL_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 1, 0.0, 0.0)),
                waveSeries(140, 7.2), 139, ElliottDegree.MINOR);
    }

    @Test
    void ATR_THRESHOLD_NOT_MET_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 100.0)),
                waveSeries(120, 7.8), 119, ElliottDegree.MINOR);
    }

    @Test
    void OPPOSITE_TYPE_APPENDS_PIVOT_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(180, 8.4), 179, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void HIGH_SAME_TYPE_MORE_EXTREME_REPLACES_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(180, 9.0), 179, ElliottDegree.MINOR);
    }

    @Test
    void LOW_SAME_TYPE_MORE_EXTREME_REPLACES_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(4, 1, 2, 0.0, 0.0)),
                waveSeries(180, 9.6), 179, ElliottDegree.MINOR);
    }

    @Test
    void NO_PIVOTS_NO_SWINGS_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(5, 2, 3, 10000.0, 100.0)),
                risingSeries(100), 99, ElliottDegree.MINOR);
    }

    @Test
    void TWO_ALTERNATING_PIVOTS_CREATE_ONE_SWING_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(140, 10.2), 139, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void MULTIPLE_ALTERNATING_PIVOTS_CREATE_ORDERED_SWINGS_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(4, 2, 2, 0.0, 0.0)),
                waveSeries(220, 10.8), 219, ElliottDegree.PRIMARY);
    }

    @Test
    void CUSTOM_LARGER_WINDOW_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(7, 1, 3, 0.0, 0.0)),
                waveSeries(180, 11.4), 179, ElliottDegree.MINOR);
    }

    @Test
    void CUSTOM_LONGER_CONFIRMATION_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 4, 3, 0.0, 0.0)),
                waveSeries(180, 12.0), 179, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void MIN_SLOPE_CHANGE_AT_BOUNDARY_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 1.0, 0.0)),
                waveSeries(140, 12.6), 139, ElliottDegree.MINOR);
    }

    @Test
    void ATR_MAGNITUDE_AT_BOUNDARY_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 1.0)),
                waveSeries(160, 13.2), 159, ElliottDegree.MINOR);
    }

    @Test
    void TRANSLATION_PRESERVES_EMPTY_RESULT_variation1() {
        run(new SlopeChangeSwingDetector(5), emptySeries(), 0, ElliottDegree.MINOR);
    }

    @Test
    void TRANSLATION_PRESERVES_CONFIRMED_SWINGS_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)),
                waveSeries(220, 13.8), 219, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void TRANSLATION_PRESERVES_CONFIRMED_SWINGS_variation2() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(5, 2, 3, 0.0, 0.0)),
                waveSeries(240, 14.4), 170, ElliottDegree.PRIMARY);
    }

    @Test
    void TRANSLATION_WITH_VOLUME_AMOUNT_UPDATE_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.25, 0.0)),
                waveSeries(180, 15.0), 179, ElliottDegree.MINOR);
    }

    private static void run(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {
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
        Assertions.assertEquals(
                sourceOutput.pivots().size(),
                followUpOutput.pivots().size());

        for (int i = 0; i < sourceOutput.pivots().size(); i++) {
            var sourcePivot = sourceOutput.pivots().get(i);
            var followUpPivot = followUpOutput.pivots().get(i);

            Assertions.assertEquals(sourcePivot.index(), followUpPivot.index());
            Assertions.assertEquals(sourcePivot.type(), followUpPivot.type());
            assertTranslated(
                    sourcePivot.price().doubleValue(),
                    followUpPivot.price().doubleValue());
        }

        Assertions.assertEquals(
                sourceOutput.swings().size(),
                followUpOutput.swings().size());

        for (int i = 0; i < sourceOutput.swings().size(); i++) {
            var sourceSwing = sourceOutput.swings().get(i);
            var followUpSwing = followUpOutput.swings().get(i);

            Assertions.assertEquals(sourceSwing.fromIndex(), followUpSwing.fromIndex());
            Assertions.assertEquals(sourceSwing.toIndex(), followUpSwing.toIndex());
            Assertions.assertEquals(sourceSwing.degree(), followUpSwing.degree());

            assertTranslated(
                    sourceSwing.fromPrice().doubleValue(),
                    followUpSwing.fromPrice().doubleValue());
            assertTranslated(
                    sourceSwing.toPrice().doubleValue(),
                    followUpSwing.toPrice().doubleValue());
        }
    }

    private static void assertTranslated(double source, double followUp) {
        double expected = source + 100.0;
        double tolerance = Math.max(
                1.0e-9,
                Math.max(Math.abs(expected), Math.abs(followUp)) * 1.0e-12);

        Assertions.assertTrue(
                Double.isFinite(expected)
                        && Double.isFinite(followUp)
                        && Math.abs(expected - followUp) <= tolerance);
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeriesBuilder()
                .withName("empty")
                .build();
    }

    private static BarSeries risingSeries(int length) {
        BaseBarSeriesBuilder builder = new BaseBarSeriesBuilder()
                .withName("rising");

        for (int i = 0; i < length; i++) {
            double close = 100.0 + i * 0.75;
            addBar(builder, i, close, 1.0 + (i % 3));
        }
        return builder.build();
    }

    private static BarSeries priceSeries(int length, double phase) {
        BaseBarSeriesBuilder builder = new BaseBarSeriesBuilder()
                .withName("prices");

        for (int i = 0; i < length; i++) {
            double close = 100.0
                    + 0.8 * Math.sin(i * 0.37 + phase)
                    + 0.2 * Math.cos(i * 0.11 + phase);
            addBar(builder, i, close, 1.0 + (i % 3));
        }
        return builder.build();
    }

    private static BarSeries waveSeries(int length, double phase) {
        BaseBarSeriesBuilder builder = new BaseBarSeriesBuilder()
                .withName("wave");

        for (int i = 0; i < length; i++) {
            double close = 100.0
                    + 14.0 * Math.sin(i * 0.31 + phase)
                    + 2.5 * Math.sin(i * 0.09 + phase * 0.5)
                    + 0.35 * Math.cos(i * 0.17);
            addBar(builder, i, close, 2.0 + (i % 4));
        }
        return builder.build();
    }

    private static BarSeries steppedSeries(int length, int phase) {
        BaseBarSeriesBuilder builder = new BaseBarSeriesBuilder()
                .withName("stepped");

        for (int i = 0; i < length; i++) {
            int position = Math.floorMod(i + phase, 12);
            double close = 100.0 + (position < 6 ? position : 11 - position);
            addBar(builder, i, close, 1.0);
        }
        return builder.build();
    }

    private static void addBar(
            BaseBarSeriesBuilder builder,
            int index,
            double close,
            double volume) {
        double open = close - 0.2;
        double high = close + 0.8 + (index % 2) * 0.1;
        double low = close - 0.8 - (index % 2) * 0.1;

        Instant begin = Instant.parse("2020-01-01T00:00:00Z")
                .plusSeconds(index * 60L);

        builder.addBar(new BaseBar(
                Duration.ofMinutes(1),
                begin,
                begin.plusSeconds(60),
                open,
                high,
                low,
                close,
                volume,
                DecimalNum.valueOf(close * volume),
                index + 1));
    }
}
