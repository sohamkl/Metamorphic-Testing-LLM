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

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static final double TRANSLATION = 100.0;

    private static void run(int tag, double[] closes, int index, ElliottDegree degree, double minSlopeChange, double minAtrReversal) {
        BarSeries sourceSeries = sourceSeries("source-" + tag, closes, tag);
        SlopeChangeConfig config = new SlopeChangeConfig(3, 2, 2, minSlopeChange, minAtrReversal);
        SlopeChangeSwingDetector sourceDetector = new SlopeChangeSwingDetector(config);
        SwingDetectorResult sourceOutput = sourceDetector.detect(sourceSeries, index, degree);
        Object[] followUp = generateFollowUp(sourceDetector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder().withName(sourceSeries.getName() + "-translated").withNumFactory(sourceSeries.numFactory()).build();
        Num translation = sourceSeries.numFactory().numOf(TRANSLATION);
        for (int barIndex = sourceSeries.getBeginIndex(); barIndex <= sourceSeries.getEndIndex(); barIndex++) {
            Bar sourceBar = sourceSeries.getBar(barIndex);
            translatedSeries.addBar(new BaseBar(sourceBar.getTimePeriod(), sourceBar.getBeginTime(), sourceBar.getEndTime(), sourceBar.getOpenPrice().plus(translation), sourceBar.getHighPrice().plus(translation), sourceBar.getLowPrice().plus(translation), sourceBar.getClosePrice().plus(translation), sourceBar.getVolume(), sourceBar.getAmount().plus(translation.multipliedBy(sourceBar.getVolume())), sourceBar.getTrades()));
        }
        return new Object[] { new SlopeChangeSwingDetector(detector.getConfig()), translatedSeries, index, degree };
    }

    private static BarSeries sourceSeries(String name, double[] closes, int tag) {
        BarSeries series = new BaseBarSeriesBuilder().withName(name).build();
        double offset = tag * 0.01;
        for (int i = 0; i < closes.length; i++) {
            Num close = series.numFactory().numOf(closes[i] + offset);
            Num open = series.numFactory().numOf(closes[i] - 0.25 + offset);
            Num high = series.numFactory().numOf(closes[i] + 1.0 + offset);
            Num low = series.numFactory().numOf(closes[i] - 1.0 + offset);
            Instant begin = BASE_TIME.plusSeconds(i * 60L);
            Instant end = begin.plusSeconds(60L);
            series.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, open, high, low, close, series.numFactory().numOf(10.0), series.numFactory().numOf(1000.0), 1L));
        }
        return series;
    }

    private static double[] flat(int size) {
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = 10.0;
        }
        return values;
    }

    private static double[] rising(int size) {
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = i + 1.0;
        }
        return values;
    }

    private static double[] falling(int size) {
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = size - i;
        }
        return values;
    }

    private static double[] highTurn() {
        return new double[] { 1, 2, 3, 2, 1, 0, -1 };
    }

    private static double[] lowTurn() {
        return new double[] { 7, 6, 5, 6, 7, 8, 9 };
    }

    private static double[] compactWave() {
        return new double[] { 2, 3, 4, 3, 2, 3, 4, 3, 2, 1, 2, 3, 2, 1, 0, -1, 0 };
    }

    private static double[] wave() {
        return new double[] { 1, 2, 3, 2, 1, 2, 3, 4, 3, 2, 1, 2, 3, 2, 1, 0, -1 };
    }

    private static double[] extendedWave() {
        return new double[] { 1, 2, 3, 2, 1, 2, 3, 4, 3, 2, 1, 2, 3, 2, 1, 0, -1, 0, 1, 2, 1, 0, -1, -2 };
    }
}
