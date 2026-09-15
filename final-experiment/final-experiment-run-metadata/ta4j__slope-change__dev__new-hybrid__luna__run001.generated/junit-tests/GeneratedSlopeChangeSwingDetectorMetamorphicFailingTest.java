import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
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

    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");

    private static void run(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static BarSeries bars(double[] closes) {
        return bars(10.0, closes);
    }

    private static BarSeries bars(double volume, double[] closes) {
        BarSeries series = new BaseBarSeriesBuilder().withName("slope-change-fixture").build();
        for (int i = 0; i < closes.length; i++) {
            Num close = series.numFactory().numOf(closes[i]);
            Num high = close.plus(series.numFactory().numOf(0.5));
            Num low = close.minus(series.numFactory().numOf(0.5));
            Num open = close;
            Num barVolume = series.numFactory().numOf(volume);
            Num amount = series.numFactory().numOf(1.0);
            Instant begin = START.plus(Duration.ofDays(i));
            Instant end = START.plus(Duration.ofDays(i + 1L));
            series.addBar(new BaseBar(Duration.ofDays(1), begin, end, open, high, low, close, barVolume, amount, 0L));
        }
        return series;
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static double[] wave() {
        return new double[] { 1, 2, 4, 3, 2, 1, 2, 3, 5, 4, 3, 2, 1, 3, 6, 4, 2, 4, 7, 5, 3, 1, 3, 6 };
    }

    private static double[] highWave() {
        return new double[] { 1, 2, 4, 3, 2, 1, 2, 3, 5, 4, 3, 2, 1 };
    }

    private static double[] lowWave() {
        return new double[] { 5, 4, 2, 3, 4, 5, 4, 3, 1, 2, 3, 4, 5 };
    }

}
