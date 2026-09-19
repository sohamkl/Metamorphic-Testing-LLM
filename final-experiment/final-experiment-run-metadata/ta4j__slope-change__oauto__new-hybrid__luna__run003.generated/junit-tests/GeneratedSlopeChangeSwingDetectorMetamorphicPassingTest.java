import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static void run(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder().withName(sourceSeries.getName() + "-translated").withNumFactory(sourceSeries.numFactory()).build();
        Num translation = sourceSeries.numFactory().numOf(100.0);
        for (int barIndex = sourceSeries.getBeginIndex(); barIndex <= sourceSeries.getEndIndex(); barIndex++) {
            Bar sourceBar = sourceSeries.getBar(barIndex);
            Num translatedAmount = sourceBar.getAmount().plus(translation.multipliedBy(sourceBar.getVolume()));
            translatedSeries.addBar(new BaseBar(sourceBar.getTimePeriod(), sourceBar.getBeginTime(), sourceBar.getEndTime(), sourceBar.getOpenPrice().plus(translation), sourceBar.getHighPrice().plus(translation), sourceBar.getLowPrice().plus(translation), sourceBar.getClosePrice().plus(translation), sourceBar.getVolume(), translatedAmount, sourceBar.getTrades()));
        }
        return new Object[] { new SlopeChangeSwingDetector(detector.getConfig()), translatedSeries, index, degree };
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeriesBuilder().withName("empty").build();
    }

    private static BarSeries singleBarSeries() {
        return series(1, 0);
    }

    private static BarSeries flatSeries(int count) {
        BarSeries series = new BaseBarSeriesBuilder().withName("flat").build();
        addBars(series, count, true, 0);
        return series;
    }

    private static BarSeries series(int count, int variation) {
        BarSeries series = new BaseBarSeriesBuilder().withName("slope-change-" + variation).build();
        addBars(series, count, false, variation);
        return series;
    }

    private static void addBars(BarSeries series, int count, boolean flat, int variation) {
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);
        for (int i = 0; i < count; i++) {
            double close;
            if (flat) {
                close = 100.0;
            } else {
                int phase = i % 12;
                if (phase < 4) {
                    close = 100.0 + phase * 4.0;
                } else if (phase < 8) {
                    close = 112.0 - (phase - 4) * 4.0;
                } else {
                    close = 96.0 + (phase - 8) * 4.0;
                }
                close += (i / 12) * 0.25 + variation * 0.001;
            }
            double open = close + ((i % 3) - 1) * 0.2;
            double high = Math.max(open, close) + 1.0 + (i % 2) * 0.25;
            double low = Math.min(open, close) - 1.0 - (i % 2) * 0.25;
            double volume = 10.0 + (i % 5);
            double amount = 1000.0 + i * 17.0;
            Instant begin = start.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            series.addBar(new BaseBar(period, begin, end, series.numFactory().numOf(open), series.numFactory().numOf(high), series.numFactory().numOf(low), series.numFactory().numOf(close), series.numFactory().numOf(volume), series.numFactory().numOf(amount), i + 1));
        }
    }

    @Test
    void SINGLE_BAR_SERIES_variation1() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0)), singleBarSeries(), 0, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void TOO_SHORT_FOR_FIRST_CANDIDATE_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(6, 0), 3, ElliottDegree.PRIMARY);
    }

    @Test
    void EXACT_FIRST_CANDIDATE_BOUNDARY_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(7, 1), 6, ElliottDegree.CYCLE);
    }

    @Test
    void EXACT_FIRST_CANDIDATE_BOUNDARY_variation2() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(20, 2), 1000, ElliottDegree.MINUTE);
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMP_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 3), -1, ElliottDegree.MINOR);
    }

    @Test
    void INDEX_ABOVE_END_CLAMP_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 4), 1000, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    void FLAT_CLOSE_SLOPES_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, flatSeries(12), 11, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void NONFINITE_CLOSE_SLOPE_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(12, 5), 11, ElliottDegree.PRIMARY);
    }

    @Test
    void SAME_SIGN_SLOPE_CHANGE_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 6), 35, ElliottDegree.CYCLE);
    }

    @Test
    void SAME_SIGN_SLOPE_CHANGE_variation2() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 7), -1, ElliottDegree.MINOR);
    }

    @Test
    void SLOPE_CHANGE_BELOW_THRESHOLD_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.75, 0.5));
        run(detector, series(36, 8), 0, ElliottDegree.MINUTE);
    }

    @Test
    void HIGH_REVERSAL_PERSISTS_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 9), 20, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void LOW_REVERSAL_PERSISTS_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 10), 35, ElliottDegree.PRIMARY);
    }

    @Test
    void REVERSAL_FAILS_CONFIRMATION_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 3, 1, 0.0, 0.0));
        run(detector, series(36, 11), 1000, ElliottDegree.CYCLE);
    }

    @Test
    void REVERSAL_FAILS_CONFIRMATION_variation2() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 3, 1, 0.25, 0.5));
        run(detector, series(36, 12), -1, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    void TIED_EXTREME_PIVOT_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 13), 0, ElliottDegree.MINOR);
    }

    @Test
    void TIED_EXTREME_PIVOT_variation2() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 14), 18, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void NONFINITE_EXTREME_VALUE_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 15), 35, ElliottDegree.PRIMARY);
    }

    @Test
    void NONFINITE_EXTREME_VALUE_variation2() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 16), 1000, ElliottDegree.CYCLE);
    }

    @Test
    void FIRST_PIVOT_BYPASSES_ATR_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.5));
        run(detector, series(36, 17), -1, ElliottDegree.MINUTE);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASS_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(36, 18), 0, ElliottDegree.MINOR);
    }

    @Test
    void ATR_DISTANCE_ACCEPTED_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.5));
        run(detector, series(36, 19), 20, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ATR_DISTANCE_REJECTED_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 10.0));
        run(detector, series(36, 20), 35, ElliottDegree.PRIMARY);
    }

    @Test
    void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 14, 0.0, 0.5));
        run(detector, series(36, 21), 1000, ElliottDegree.CYCLE);
    }

    @Test
    void ALTERNATING_PIVOTS_CREATE_SWINGS_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(60, 22), -1, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    void HIGH_REPLACED_BY_HIGHER_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(60, 23), 0, ElliottDegree.MINOR);
    }

    @Test
    void HIGH_NOT_REPLACED_BY_LOWER_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(60, 24), 20, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void LOW_REPLACED_BY_LOWER_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(60, 25), 59, ElliottDegree.PRIMARY);
    }

    @Test
    void LOW_NOT_REPLACED_BY_HIGHER_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(60, 26), 1000, ElliottDegree.CYCLE);
    }

    @Test
    void DEGREE_PROPAGATED_TO_SWINGS_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.0));
        run(detector, series(60, 27), -1, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    void UNIFORM_TRANSLATION_WITH_VOLUME_AND_AMOUNT_variation1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 1, 0.0, 0.5));
        run(detector, series(60, 28), 59, ElliottDegree.SUPER_CYCLE);
    }
}
