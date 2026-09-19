import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeneratedSlopeChangeSwingDetectorMetamorphicTest {

    @Test
    void EMPTY_SERIES_defaultConstructor() {
        verify(new SlopeChangeSwingDetector(3), emptySeries(), -1, ElliottDegree.MINOR);
    }

    @Test
    void EMPTY_SERIES_configuredConstructor() {
        verify(new SlopeChangeSwingDetector(config(4, 2, 3, 0.0, 0.5)),
                emptySeries(), 0, ElliottDegree.PRIMARY);
    }

    @Test
    void SHORT_SERIES_NO_CANDIDATE_beforeWindow() {
        verify(new SlopeChangeSwingDetector(config(5, 2, 3, 0.0, 1.0)),
                series(new double[] { 1, 2, 3, 2, 1 }, false), 4, ElliottDegree.MINOR);
    }

    @Test
    void SHORT_SERIES_NO_CANDIDATE_confirmationBoundary() {
        verify(new SlopeChangeSwingDetector(config(4, 3, 3, 0.0, 0.0)),
                series(new double[] { 1, 2, 3, 4, 3, 2, 1 }, false), 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMP() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                roundedPeak(18, false), -10, ElliottDegree.PRIMARY);
    }

    @Test
    void INDEX_ABOVE_END_CLAMP() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                alternatingTurns(32), 100, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void EXACT_END_INDEX_BOUNDARY_singleCandidate() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                series(new double[] { 1, 2, 3, 2, 1, 0 }, false), 5, ElliottDegree.MINOR);
    }

    @Test
    void EXACT_END_INDEX_BOUNDARY_multipleCandidates() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                roundedTrough(14, false), 13, ElliottDegree.PRIMARY);
    }

    @Test
    void CONSTANT_CLOSE_ZERO_SLOPE() {
        verify(new SlopeChangeSwingDetector(config(3, 2, 3, 0.0, 0.5)),
                constantSeries(16), 15, ElliottDegree.PRIMARY);
    }

    @Test
    void SLOPE_CHANGE_BELOW_THRESHOLD() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 1000.0, 0.0)),
                roundedPeak(20, false), 19, ElliottDegree.MINOR);
    }

    @Test
    void CONFIRMED_HIGH_REVERSAL() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                roundedPeak(20, false), 19, ElliottDegree.MINOR);
    }

    @Test
    void CONFIRMED_LOW_REVERSAL() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                roundedTrough(20, false), 19, ElliottDegree.PRIMARY);
    }

    @Test
    void REVERSAL_FAILS_PERSISTENCE() {
        verify(new SlopeChangeSwingDetector(config(3, 3, 3, 0.0, 0.0)),
                briefTurn(20), 19, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void EXTREME_HIGH_AT_INTERVAL_BOUNDARIES() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                boundaryHighSeries(20), 19, ElliottDegree.MINOR);
    }

    @Test
    void EXTREME_LOW_AT_INTERVAL_BOUNDARIES() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                boundaryLowSeries(20), 19, ElliottDegree.PRIMARY);
    }

    @Test
    void FIRST_PIVOT_ATR_BYPASS() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 2.0)),
                roundedPeak(20, false), 19, ElliottDegree.MINOR);
    }

    @Test
    void SUBSEQUENT_PIVOT_REJECTED_BY_ATR() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 1000.0)),
                alternatingTurns(36), 35, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SUBSEQUENT_PIVOT_ACCEPTED_ATR_BOUNDARY() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                alternatingTurns(36), 35, ElliottDegree.PRIMARY);
    }

    @Test
    void SAME_TYPE_HIGH_REPLACEMENT_AND_RETENTION() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                alternatingTurns(44), 43, ElliottDegree.MINOR);
    }

    @Test
    void SAME_TYPE_LOW_REPLACEMENT_AND_RETENTION() {
        verify(new SlopeChangeSwingDetector(config(4, 1, 4, 0.0, 0.0)),
                alternatingTurns(48), 47, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void OPPOSITE_PIVOTS_FORM_SWINGS() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                alternatingTurns(40), 39, ElliottDegree.PRIMARY);
    }

    @Test
    void NONFINITE_CLOSE_SKIPS_SLOPE() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                series(new double[] {
                        1, 2, 3, 4, Double.NaN, 4, 3, 2, 1, 0
                }, false), 9, ElliottDegree.MINOR);
    }

    @Test
    void NONFINITE_EXTREME_OR_ATR_VALUE() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                boundaryHighSeries(20), 19, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ZERO_VOLUME_translation() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.5)),
                roundedPeak(20, true), 19, ElliottDegree.MINOR);
    }

    @Test
    void DIFFERENT_DEGREES_preserveSwingDegree() {
        verify(new SlopeChangeSwingDetector(3),
                alternatingTurns(32), 31, ElliottDegree.CYCLE);
    }

    @Test
    void NONZERO_BEGIN_INDEX_MR_REJECTION() {
        BarSeries source = roundedPeak(12, false);
        source.setMaximumBarCount(8);

        new SlopeChangeSwingDetector(3).detect(source, source.getEndIndex(), ElliottDegree.MINOR);

        assertThrows(IllegalArgumentException.class,
                () -> generateFollowUp(new SlopeChangeSwingDetector(3),
                        source, source.getEndIndex(), ElliottDegree.MINOR));
    }

    private static void verify(
            SlopeChangeSwingDetector detector,
            BarSeries source,
            int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(source, index, degree);

        Object[] followUp = generateFollowUp(detector, source, index, degree);
        SlopeChangeSwingDetector followUpDetector =
                (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput =
                followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {
        if (sourceSeries.getBeginIndex() != 0) {
            throw new IllegalArgumentException(
                    "Price-translation MR requires a source series whose begin index is zero");
        }

        BarSeries translatedSeries = new BaseBarSeriesBuilder()
                .withName(sourceSeries.getName() + "-translated")
                .withNumFactory(sourceSeries.numFactory())
                .build();

        Num translation = sourceSeries.numFactory().numOf(100.0);

        for (int i = sourceSeries.getBeginIndex(); i <= sourceSeries.getEndIndex(); i++) {
            Bar sourceBar = sourceSeries.getBar(i);
            Num translatedAmount = sourceBar.getAmount()
                    .plus(translation.multipliedBy(sourceBar.getVolume()));

            translatedSeries.addBar(new BaseBar(
                    sourceBar.getTimePeriod(),
                    sourceBar.getBeginTime(),
                    sourceBar.getEndTime(),
                    sourceBar.getOpenPrice().plus(translation),
                    sourceBar.getHighPrice().plus(translation),
                    sourceBar.getLowPrice().plus(translation),
                    sourceBar.getClosePrice().plus(translation),
                    sourceBar.getVolume(),
                    translatedAmount,
                    sourceBar.getTrades()));
        }

        return new Object[] {
                new SlopeChangeSwingDetector(detector.getConfig()),
                translatedSeries,
                index,
                degree
        };
    }

    private static SlopeChangeConfig config(
            int window,
            int confirmationBars,
            int atrPeriod,
            double minSlopeChange,
            double minAtrReversal) {
        return new SlopeChangeConfig(
                window,
                confirmationBars,
                atrPeriod,
                minSlopeChange,
                minAtrReversal);
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeriesBuilder()
                .withName("empty")
                .build();
    }

    private static BarSeries constantSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 10.0;
        }
        return series(values, false);
    }

    private static BarSeries roundedPeak(int length, boolean zeroVolume) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = i < 6 ? i : 12.0 - i;
        }
        return series(values, zeroVolume);
    }

    private static BarSeries roundedTrough(int length, boolean zeroVolume) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = i < 6 ? 6.0 - i : i - 6.0;
        }
        return series(values, zeroVolume);
    }

    private static BarSeries briefTurn(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            if (i < 6) {
                values[i] = i;
            } else if (i == 6) {
                values[i] = 5.0;
            } else {
                values[i] = 6.0 + (i % 2);
            }
        }
        return series(values, false);
    }

    private static BarSeries alternatingTurns(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 8;
            values[i] = phase < 4 ? phase : 8.0 - phase;
        }
        return series(values, false);
    }

    private static BarSeries boundaryHighSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = i < 6 ? i : 12.0 - i;
        }
        return series(values, false, 2);
    }

    private static BarSeries boundaryLowSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = i < 6 ? 6.0 - i : i - 6.0;
        }
        return series(values, false, -2);
    }

    private static BarSeries series(double[] values, boolean zeroVolume) {
        return series(values, zeroVolume, 0);
    }

    private static BarSeries series(
            double[] values,
            boolean zeroVolume,
            double rangeBias) {
        BarSeries result = new BaseBarSeriesBuilder()
                .withName("fixture")
                .build();

        Instant base = Instant.parse("2020-01-01T00:00:00Z");

        for (int i = 0; i < values.length; i++) {
            double close = values[i];
            double open = close + rangeBias * 0.05;
            double high = Math.max(open, close) + 0.75;
            double low = Math.min(open, close) - 0.75;
            double volume = zeroVolume ? 0.0 : 1.0 + (i % 4);

            result.addBar(new BaseBar(
                    Duration.ofMinutes(1),
                    base.plusSeconds(i * 60L),
                    base.plusSeconds((i + 1L) * 60L),
                    open,
                    high,
                    low,
                    close,
                    volume,
                    10.0 + i,
                    i + 1L));
        }

        return result;
    }
}
