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

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static void exercise(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index) {
        ElliottDegree degree = ElliottDegree.values()[0];
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static BarSeries bars(double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 1.0;
            lows[i] = closes[i] - 1.0;
        }
        return bars(closes, highs, lows);
    }

    private static BarSeries bars(double[] closes, double[] highs, double[] lows) {
        BarSeries series = new BaseBarSeriesBuilder().withName("metamorphic-source").build();
        for (int i = 0; i < closes.length; i++) {
            Num open = series.numFactory().numOf(closes[i]);
            Num high = series.numFactory().numOf(highs[i]);
            Num low = series.numFactory().numOf(lows[i]);
            Num close = series.numFactory().numOf(closes[i]);
            Num volume = series.numFactory().numOf(1);
            Num amount = series.numFactory().numOf(closes[i]);
            Instant begin = BASE_TIME.plus(Duration.ofHours(i));
            Instant end = begin.plus(Duration.ofHours(1));
            series.addBar(new BaseBar(Duration.ofHours(1), begin, end, open, high, low, close, volume, amount, 1L));
        }
        return series;
    }
}
