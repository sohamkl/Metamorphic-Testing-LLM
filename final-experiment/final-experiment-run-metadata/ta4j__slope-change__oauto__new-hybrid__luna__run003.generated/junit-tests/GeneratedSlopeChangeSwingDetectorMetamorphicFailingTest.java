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

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

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
}
