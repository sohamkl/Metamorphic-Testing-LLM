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

    private static final ElliottDegree MINUTE = ElliottDegree.valueOf("MINUTE");

    private static final ElliottDegree MINOR = ElliottDegree.valueOf("MINOR");

    private static void run(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingSwingDetectorArguments arguments = new SlopeChangeSwingSwingDetectorArguments(followUp);
        SwingDetectorResult followUpOutput = arguments.detector.detect(arguments.series, arguments.index, arguments.degree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static BarSeries series(double... closes) {
        return seriesWithVolumes(closes, repeat(closes.length, 1.0));
    }

    private static BarSeries seriesWithVolumes(double[] closes, double[] volumes) {
        BarSeries result = new BaseBarSeriesBuilder().withName("generated").build();
        for (int i = 0; i < closes.length; i++) {
            addBar(result, closes[i], 1.0, volumes[i], 0.0);
        }
        return result;
    }

    private static BarSeries seriesWithHighOffsets(double[] closes, double[] offsets) {
        BarSeries result = new BaseBarSeriesBuilder().withName("generated-highs").build();
        for (int i = 0; i < closes.length; i++) {
            addBar(result, closes[i], offsets[i], 1.0, 0.0);
        }
        return result;
    }

    private static BarSeries seriesWithLowOffsets(double[] closes, double[] offsets) {
        BarSeries result = new BaseBarSeriesBuilder().withName("generated-lows").build();
        for (int i = 0; i < closes.length; i++) {
            addBar(result, closes[i], 1.0, 1.0, offsets[i]);
        }
        return result;
    }

    private static void addBar(BarSeries series, double close, double highOffset, double volume, double lowOffset) {
        Instant begin = Instant.parse("2020-01-01T00:00:00Z").plusSeconds(series.getBarCount() * 60L);
        Instant end = begin.plusSeconds(60L);
        Num open = series.numFactory().numOf(close);
        Num high = series.numFactory().numOf(close + highOffset);
        Num low = series.numFactory().numOf(close - lowOffset);
        Num closeValue = series.numFactory().numOf(close);
        Num volumeValue = series.numFactory().numOf(volume);
        Num amount = series.numFactory().numOf(volume);
        series.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, open, high, low, closeValue, volumeValue, amount, 1));
    }

    private static double[] repeat(int length, double value) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = value;
        }
        return values;
    }

    private static BarSeries reversalSeries() {
        return series(10, 12, 14, 16, 18, 16, 14, 12, 10, 8, 10, 12, 14, 16, 18, 20);
    }

    private static final class SlopeChangeSwingSwingDetectorArguments {

        private final SlopeChangeSwingDetector detector;

        private final BarSeries series;

        private final int index;

        private final ElliottDegree degree;

        private SlopeChangeSwingSwingDetectorArguments(Object[] followUp) {
            this.detector = (SlopeChangeSwingDetector) followUp[0];
            this.series = (BarSeries) followUp[1];
            this.index = (Integer) followUp[2];
            this.degree = (ElliottDegree) followUp[3];
        }
    }
}
