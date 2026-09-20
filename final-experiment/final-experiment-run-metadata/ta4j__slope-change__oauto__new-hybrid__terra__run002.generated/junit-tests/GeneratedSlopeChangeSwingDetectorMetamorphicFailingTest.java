import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static void exercise(int index, ElliottDegree degree, double... closes) {
        BarSeries sourceSeries = series(closes);
        SlopeChangeSwingDetector sourceDetector = new SlopeChangeSwingDetector(3);
        SwingDetectorResult sourceOutput = sourceDetector.detect(sourceSeries, index, degree);
        FollowUp followUp = generateFollowUp(sourceDetector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = followUp.detector.detect(followUp.series, followUp.index, followUp.degree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder().withName(sourceSeries.getName() + "-translated").withNumFactory(sourceSeries.numFactory()).build();
        Num translation = sourceSeries.numFactory().numOf(100.0);
        for (int barIndex = sourceSeries.getBeginIndex(); barIndex <= sourceSeries.getEndIndex(); barIndex++) {
            Bar sourceBar = sourceSeries.getBar(barIndex);
            Num translatedAmount = sourceBar.getAmount().plus(translation.multipliedBy(sourceBar.getVolume()));
            translatedSeries.addBar(new BaseBar(sourceBar.getTimePeriod(), sourceBar.getBeginTime(), sourceBar.getEndTime(), sourceBar.getOpenPrice().plus(translation), sourceBar.getHighPrice().plus(translation), sourceBar.getLowPrice().plus(translation), sourceBar.getClosePrice().plus(translation), sourceBar.getVolume(), translatedAmount, sourceBar.getTrades()));
        }
        return new FollowUp(new SlopeChangeSwingDetector(detector.getConfig()), translatedSeries, index, degree);
    }

    private static BarSeries series(double... closes) {
        BarSeries result = new BaseBarSeriesBuilder().withName("source").build();
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < closes.length; i++) {
            Num close = result.numFactory().numOf(closes[i]);
            Num range = result.numFactory().numOf(2);
            Num volume = result.numFactory().numOf(10);
            Instant begin = base.plusSeconds(i * 60L);
            result.addBar(new BaseBar(Duration.ofMinutes(1), begin, begin.plusSeconds(60), close, close.plus(range), close.minus(range), close, volume, close.multipliedBy(volume), 1L));
        }
        return result;
    }

    private static double[] highTurn() {
        return new double[] { 100, 110, 120, 110, 100, 90, 80 };
    }

    private static double[] lowTurn() {
        return new double[] { 120, 110, 100, 110, 120, 130, 140 };
    }

    private static final class FollowUp {

        private final SlopeChangeSwingDetector detector;

        private final BarSeries series;

        private final int index;

        private final ElliottDegree degree;

        private FollowUp(SlopeChangeSwingDetector detector, BarSeries series, int index, ElliottDegree degree) {
            this.detector = detector;
            this.series = series;
            this.index = index;
            this.degree = degree;
        }
    }
}
