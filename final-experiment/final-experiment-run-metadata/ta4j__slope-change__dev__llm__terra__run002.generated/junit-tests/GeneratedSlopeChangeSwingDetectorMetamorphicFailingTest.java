import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static void exercise(SlopeChangeSwingDetector detector, BarSeries source, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(source, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, source, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static ElliottDegree degree(int offset) {
        ElliottDegree[] degrees = ElliottDegree.values();
        return degrees[Math.floorMod(offset, degrees.length)];
    }

    private static BarSeries series(String name, double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 1.0;
            lows[i] = closes[i] - 1.0;
        }
        return customSeries(name, closes, highs, lows);
    }

    private static BarSeries customSeries(String name, double[] closes, double[] highs, double[] lows) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant start = Instant.parse("2020-01-02T09:00:00Z");
        Duration period = Duration.ofMinutes(1);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = start.plusSeconds(60L * i);
            Instant end = begin.plus(period);
            result.addBar(new BaseBar(period, begin, end, result.numFactory().numOf(closes[i]), result.numFactory().numOf(highs[i]), result.numFactory().numOf(lows[i]), result.numFactory().numOf(closes[i]), result.numFactory().numOf(1.0), result.numFactory().numOf(1.0), 1L));
        }
        return result;
    }
}
