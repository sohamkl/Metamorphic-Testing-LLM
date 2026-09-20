import java.lang.reflect.Proxy;
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

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static final double TRANSLATION = 100.0;

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

    private static FollowUp generateFollowUp(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder().withName(sourceSeries.getName() + "-translated").withNumFactory(sourceSeries.numFactory()).build();
        Num translation = sourceSeries.numFactory().numOf(TRANSLATION);
        for (int barIndex = sourceSeries.getBeginIndex(); barIndex <= sourceSeries.getEndIndex(); barIndex++) {
            Bar sourceBar = sourceSeries.getBar(barIndex);
            Num translatedAmount = sourceBar.getAmount().plus(translation.multipliedBy(sourceBar.getVolume()));
            translatedSeries.addBar(new BaseBar(sourceBar.getTimePeriod(), sourceBar.getBeginTime(), sourceBar.getEndTime(), sourceBar.getOpenPrice().plus(translation), sourceBar.getHighPrice().plus(translation), sourceBar.getLowPrice().plus(translation), sourceBar.getClosePrice().plus(translation), sourceBar.getVolume(), translatedAmount, sourceBar.getTrades()));
        }
        return new FollowUp(new SlopeChangeSwingDetector(detector.getConfig()), translatedSeries, index, degree);
    }

    private static void verify(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        FollowUp followUp = generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = followUp.detector.detect(followUp.series, followUp.index, followUp.degree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static ElliottDegree degree(int index) {
        ElliottDegree[] values = ElliottDegree.values();
        return values[Math.floorMod(index, values.length)];
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries emptyBeginZeroSeries(final String name) {
        final BarSeries factorySource = new BaseBarSeriesBuilder().withName(name + "-factory").build();
        return (BarSeries) Proxy.newProxyInstance(BarSeries.class.getClassLoader(), new Class<?>[] { BarSeries.class }, (proxy, method, args) -> {
            String methodName = method.getName();
            if ("isEmpty".equals(methodName)) {
                return true;
            }
            if ("getBeginIndex".equals(methodName)) {
                return 0;
            }
            if ("getEndIndex".equals(methodName)) {
                return -1;
            }
            if ("getName".equals(methodName)) {
                return name;
            }
            if ("numFactory".equals(methodName)) {
                return factorySource.numFactory();
            }
            if ("getBarCount".equals(methodName)) {
                return 0;
            }
            if ("toString".equals(methodName)) {
                return name;
            }
            if ("hashCode".equals(methodName)) {
                return System.identityHashCode(proxy);
            }
            if ("equals".equals(methodName)) {
                return proxy == args[0];
            }
            throw new UnsupportedOperationException(methodName);
        });
    }

    private static BarSeries series(double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        double[] volumes = new double[closes.length];
        double[] amounts = new double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 0.25;
            lows[i] = closes[i] - 0.25;
            volumes[i] = i % 3 == 0 ? 0.0 : i + 1.0;
            amounts[i] = closes[i] * volumes[i] + i;
            trades[i] = i + 1L;
        }
        return series("prices", closes, highs, lows, volumes, amounts, trades);
    }

    private static BarSeries series(String name, double[] closes, double[] highs, double[] lows, double[] volumes, double[] amounts, long[] trades) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);
            Num close = result.numFactory().numOf(closes[i]);
            Num high = result.numFactory().numOf(highs[i]);
            Num low = result.numFactory().numOf(lows[i]);
            Num volume = result.numFactory().numOf(volumes[i]);
            Num amount = result.numFactory().numOf(amounts[i]);
            result.addBar(new BaseBar(BAR_DURATION, begin, end, close, high, low, close, volume, amount, trades[i]));
        }
        return result;
    }

    private static BarSeries custom(double[] closes, double[] highs, double[] lows) {
        double[] volumes = new double[closes.length];
        double[] amounts = new double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = i % 2 == 0 ? 0.0 : 10.0 + i;
            amounts[i] = volumes[i] * closes[i] + i * 0.5;
            trades[i] = 2L * i + 1L;
        }
        return series("custom", closes, highs, lows, volumes, amounts, trades);
    }

}
