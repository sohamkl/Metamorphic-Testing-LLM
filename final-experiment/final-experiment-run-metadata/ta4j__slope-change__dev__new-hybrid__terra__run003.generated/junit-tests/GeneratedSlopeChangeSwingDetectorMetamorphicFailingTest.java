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

    private static void verify(SlopeChangeSwingDetector detector, BarSeries source, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(source, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, source, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], ((Integer) followUp[2]).intValue(), (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries series(String name, double... relativeCloses) {
        double[] highs = new double[relativeCloses.length];
        double[] lows = new double[relativeCloses.length];
        for (int i = 0; i < relativeCloses.length; i++) {
            highs[i] = relativeCloses[i] + 1.0;
            lows[i] = relativeCloses[i] - 1.0;
        }
        return seriesWithRanges(name, relativeCloses, highs, lows);
    }

    private static BarSeries seriesWithRanges(String name, double[] relativeCloses, double[] relativeHighs, double[] relativeLows) {
        BarSeries series = new BaseBarSeriesBuilder().withName(name).build();
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < relativeCloses.length; i++) {
            Num close = series.numFactory().numOf(100.0 + relativeCloses[i]);
            Num high = series.numFactory().numOf(100.0 + relativeHighs[i]);
            Num low = series.numFactory().numOf(100.0 + relativeLows[i]);
            Num open = series.numFactory().numOf(100.0 + (relativeHighs[i] + relativeLows[i]) / 2.0);
            Num volume = series.numFactory().numOf(1.0);
            Num amount = close;
            Instant begin = base.plusSeconds(i * 60L);
            Instant end = begin.plusSeconds(60L);
            series.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, open, high, low, close, volume, amount, 1L));
        }
        return series;
    }
}
