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

public class GeneratedSlopeChangeSwingDetectorMetamorphicTest {

    @Test
    public void EMPTY_ZERO_BASED_SERIES_1() {
        run(new SlopeChangeSwingDetector(3), 0, Integer.MIN_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMP_1() {
        run(new SlopeChangeSwingDetector(config(4, 2, 3, 0.0, 0.0)), 5, -1, ElliottDegree.PRIMARY);
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMP_2() {
        run(new SlopeChangeSwingDetector(3), 25, 8, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void INDEX_ABOVE_END_CLAMP_1() {
        run(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)), 80, 80, ElliottDegree.CYCLE);
    }

    @Test
    public void INDEX_ABOVE_END_CLAMP_2() {
        run(new SlopeChangeSwingDetector(4), 100, Integer.MAX_VALUE, ElliottDegree.PRIMARY);
    }

    @Test
    public void TOO_SHORT_FOR_A_CANDIDATE_1() {
        run(new SlopeChangeSwingDetector(config(8, 3, 4, 1.0, 0.5)), 10, Integer.MAX_VALUE,
                ElliottDegree.PRIMARY);
    }

    @Test
    public void TOO_SHORT_FOR_A_CANDIDATE_2() {
        run(new SlopeChangeSwingDetector(6), 8, Integer.MIN_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void EXACTLY_ONE_CANDIDATE_BOUNDARY_1() {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(config(3, 1, 2, 0.0, 0.0));
        run(detector, 8, 7, ElliottDegree.MINOR);
    }

    @Test
    public void EXACTLY_ONE_CANDIDATE_BOUNDARY_2() {
        run(new SlopeChangeSwingDetector(config(4, 1, 2, 0.0, 0.0)), 10, 9, ElliottDegree.PRIMARY);
    }

    @Test
    public void ZERO_OR_BELOW_THRESHOLD_SLOPE_CHANGE_1() {
        run(new SlopeChangeSwingDetector(config(5, 2, 3, 1000.0, 0.0)), 90, 89,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ZERO_OR_BELOW_THRESHOLD_SLOPE_CHANGE_2() {
        run(new SlopeChangeSwingDetector(config(3, 2, 3, 1000.0, 0.0)), 40, Integer.MAX_VALUE,
                ElliottDegree.CYCLE);
    }

    @Test
    public void CONFIRMED_HIGH_REVERSAL_1() {
        run(new SlopeChangeSwingDetector(config(3, 1, 2, 0.0, 0.0)), 70, 69, ElliottDegree.MINOR);
    }

    @Test
    public void CONFIRMED_HIGH_REVERSAL_2() {
        run(new SlopeChangeSwingDetector(4), 100, Integer.MAX_VALUE, ElliottDegree.PRIMARY);
    }

    @Test
    public void CONFIRMED_HIGH_REVERSAL_3() {
        run(new SlopeChangeSwingDetector(config(5, 2, 3, 0.1, 0.25)), 120, 119,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void CONFIRMED_LOW_REVERSAL_1() {
        run(new SlopeChangeSwingDetector(3), 70, 69, ElliottDegree.MINOR);
    }

    @Test
    public void CONFIRMED_LOW_REVERSAL_2() {
        run(new SlopeChangeSwingDetector(config(4, 1, 2, 0.0, 0.0)), 90, 89,
                ElliottDegree.PRIMARY);
    }

    @Test
    public void CONFIRMED_LOW_REVERSAL_3() {
        run(new SlopeChangeSwingDetector(5), 130, Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void PERSISTENCE_REJECTS_REVERSAL_1() {
        run(new SlopeChangeSwingDetector(config(4, 3, 3, 0.0, 0.0)), 100, Integer.MIN_VALUE,
                ElliottDegree.PRIMARY);
    }

    @Test
    public void PERSISTENCE_REJECTS_REVERSAL_2() {
        run(new SlopeChangeSwingDetector(4), 100, Integer.MIN_VALUE, ElliottDegree.PRIMARY);
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_FILTER_1() {
        run(new SlopeChangeSwingDetector(config(3, 2, 20, 0.0, 10.0)), 120, Integer.MAX_VALUE,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_FILTER_2() {
        run(new SlopeChangeSwingDetector(3), 60, 0, ElliottDegree.MINOR);
    }

    @Test
    public void ATR_MAGNITUDE_REJECTS_WEAK_REVERSAL_1() {
        run(new SlopeChangeSwingDetector(config(4, 2, 3, 0.0, 20.0)), 110, 109,
                ElliottDegree.PRIMARY);
    }

    @Test
    public void ATR_MAGNITUDE_REJECTS_WEAK_REVERSAL_2() {
        run(new SlopeChangeSwingDetector(3), 120, Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void NONFINITE_ATR_REJECTS_LATER_PIVOT_1() {
        run(new SlopeChangeSwingDetector(config(3, 2, 200, 0.0, 1.0)), 120, Integer.MAX_VALUE,
                ElliottDegree.MINOR);
    }

    @Test
    public void EXTREME_SELECTION_WITH_TIES_1() {
        run(new SlopeChangeSwingDetector(3), 100, Integer.MIN_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void EXTREME_SELECTION_WITH_TIES_2() {
        run(new SlopeChangeSwingDetector(config(4, 2, 4, 0.0, 0.0)), 120, 119,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void SAME_TYPE_STRONGER_PIVOT_REPLACEMENT_1() {
        run(new SlopeChangeSwingDetector(3), 130, 129, ElliottDegree.PRIMARY);
    }

    @Test
    public void SAME_TYPE_STRONGER_PIVOT_REPLACEMENT_2() {
        run(new SlopeChangeSwingDetector(config(4, 1, 3, 0.0, 0.0)), 130, 129,
                ElliottDegree.MINOR);
    }

    @Test
    public void SAME_TYPE_WEAKER_PIVOT_IGNORED_1() {
        run(new SlopeChangeSwingDetector(3), 140, Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void SAME_TYPE_WEAKER_PIVOT_IGNORED_2() {
        run(new SlopeChangeSwingDetector(config(5, 2, 3, 0.0, 0.5)), 140, Integer.MAX_VALUE,
                ElliottDegree.PRIMARY);
    }

    @Test
    public void ALTERNATING_PIVOTS_FORM_SWINGS_1() {
        run(new SlopeChangeSwingDetector(3), 140, 139, ElliottDegree.MINOR);
    }

    @Test
    public void ALTERNATING_PIVOTS_FORM_SWINGS_2() {
        run(new SlopeChangeSwingDetector(config(3, 1, 2, 0.0, 0.0)), 140, 139,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void DEGREE_PROPAGATES_TO_SWINGS_1() {
        run(new SlopeChangeSwingDetector(3), 140, 139, ElliottDegree.PRIMARY);
    }

    @Test
    public void DEGREE_PROPAGATES_TO_SWINGS_2() {
        run(new SlopeChangeSwingDetector(config(3, 1, 2, 0.0, 0.0)), 140, Integer.MAX_VALUE,
                ElliottDegree.CYCLE);
    }

    private static void run(
            SlopeChangeSwingDetector detector,
            int length,
            int index,
            ElliottDegree degree) {
        BarSeries sourceSeries = sourceSeries(length);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);

        Object[] followUp = generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput =
                followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder()
                .withName(sourceSeries.getName() + "-translated")
                .withNumFactory(sourceSeries.numFactory())
                .build();

        Num translation = sourceSeries.numFactory().numOf(100.0);

        for (int barIndex = sourceSeries.getBeginIndex();
                barIndex <= sourceSeries.getEndIndex();
                barIndex++) {
            Bar sourceBar = sourceSeries.getBar(barIndex);
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

    private static BarSeries sourceSeries(int length) {
        BarSeries series = new BaseBarSeriesBuilder()
                .withName("slope-change-source")
                .build();

        Instant baseTime = Instant.parse("2020-01-01T00:00:00Z");

        for (int i = 0; i < length; i++) {
            double close = 100.0 + 12.0 * Math.sin(i * Math.PI / 4.0);
            double open = close + ((i % 3) - 1) * 0.25;
            double high = Math.max(open, close) + 1.0 + (i % 4) * 0.1;
            double low = Math.min(open, close) - 1.0 - (i % 5) * 0.1;
            double volume = 10.0 + (i % 7);
            double amount = close * volume;
            Instant begin = baseTime.plusSeconds(i * 60L);
            Instant end = begin.plusSeconds(60L);

            series.addBar(new BaseBar(
                    Duration.ofMinutes(1),
                    begin,
                    end,
                    series.numFactory().numOf(open),
                    series.numFactory().numOf(high),
                    series.numFactory().numOf(low),
                    series.numFactory().numOf(close),
                    series.numFactory().numOf(volume),
                    series.numFactory().numOf(amount),
                    i + 1L));
        }

        return series;
    }
}
