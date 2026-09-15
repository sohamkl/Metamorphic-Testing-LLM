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

    private static void execute(String name, int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal, double[] closes, int requestedIndex, boolean positivePayload) {
        BarSeries sourceSeries = new BaseBarSeriesBuilder().withName(name).build();
        addBars(sourceSeries, closes, null, null, positivePayload);
        invoke(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal, sourceSeries, requestedIndex);
    }

    private static void executeWithExtremes(String name, int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal, double[] closes, double[] highs, double[] lows, int requestedIndex, boolean positivePayload) {
        BarSeries sourceSeries = new BaseBarSeriesBuilder().withName(name).build();
        addBars(sourceSeries, closes, highs, lows, positivePayload);
        invoke(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal, sourceSeries, requestedIndex);
    }

    private static void addBars(BarSeries series, double[] closes, double[] highs, double[] lows, boolean positivePayload) {
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = highs == null ? (close == 0.0 ? 0.0 : close + 1.0) : highs[i];
            double low = lows == null ? (close == 0.0 ? 0.0 : close - 1.0) : lows[i];
            Num volume = series.numFactory().numOf(positivePayload ? i + 1.0 : 0.0);
            Num amount = series.numFactory().numOf(positivePayload ? (i + 1.0) * 10.0 : 0.0);
            Instant begin = base.plusSeconds(i * 60L);
            series.addBar(new BaseBar(Duration.ofMinutes(1), begin, begin.plusSeconds(60), series.numFactory().numOf(close), series.numFactory().numOf(high), series.numFactory().numOf(low), series.numFactory().numOf(close), volume, amount, 0L));
        }
    }

    private static void invoke(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal, BarSeries sourceSeries, int requestedIndex) {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
        ElliottDegree degree = ElliottDegree.valueOf("MINOR");
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, requestedIndex, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, requestedIndex, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
