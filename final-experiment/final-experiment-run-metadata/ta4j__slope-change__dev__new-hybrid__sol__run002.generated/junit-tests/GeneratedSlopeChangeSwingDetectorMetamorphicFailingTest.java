import java.lang.reflect.Proxy;
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

    private static final double[] HIGH_REVERSAL = { 10, 12, 14, 12, 10 };

    private static final double[] LOW_REVERSAL = { 14, 12, 10, 12, 14 };

    private static final double[] WAVE = { 10, 12, 14, 12, 10, 8, 10, 12, 14, 12, 10, 8, 10 };

    private static final double[] INVERTED_WAVE = { 14, 12, 10, 12, 14, 16, 14, 12, 10, 12, 14, 16, 14 };

    private static void execute(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static ElliottDegree degree(String name) {
        return ElliottDegree.valueOf(name);
    }

    private static BarSeries series(String name, double[] closes) {
        return series(name, closes, null, null, null);
    }

    private static BarSeries series(String name, double[] closes, double[] highs, double[] lows, double[] volumes) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);
        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = highs == null ? close + 1.0 : highs[i];
            double low = lows == null ? close - 1.0 : lows[i];
            double volume = volumes == null ? 1.0 : volumes[i];
            Num openNum = result.numFactory().numOf(close);
            Num highNum = result.numFactory().numOf(high);
            Num lowNum = result.numFactory().numOf(low);
            Num closeNum = result.numFactory().numOf(close);
            Num volumeNum = result.numFactory().numOf(volume);
            Num amountNum = result.numFactory().numOf(0);
            Instant begin = base.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            result.addBar(new BaseBar(period, begin, end, openNum, highNum, lowNum, closeNum, volumeNum, amountNum, 0));
        }
        return result;
    }

    private static BarSeries emptyZeroBasedSeries() {
        BarSeries delegate = new BaseBarSeriesBuilder().withName("empty-zero-based").build();
        return (BarSeries) Proxy.newProxyInstance(BarSeries.class.getClassLoader(), new Class<?>[] { BarSeries.class }, (proxy, method, args) -> {
            switch(method.getName()) {
                case "isEmpty":
                    return true;
                case "getBeginIndex":
                    return 0;
                case "getEndIndex":
                    return -1;
                case "getName":
                    return "empty-zero-based";
                case "numFactory":
                    return delegate.numFactory();
                case "toString":
                    return "empty-zero-based";
                case "hashCode":
                    return System.identityHashCode(proxy);
                case "equals":
                    return proxy == args[0];
                default:
                    throw new UnsupportedOperationException(method.getName());
            }
        });
    }

    private static double[] repeat(double value, int count) {
        double[] result = new double[count];
        for (int i = 0; i < count; i++) {
            result[i] = value;
        }
        return result;
    }

    private static double[] shift(double[] values, double amount) {
        double[] result = values.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] += amount;
        }
        return result;
    }
}
