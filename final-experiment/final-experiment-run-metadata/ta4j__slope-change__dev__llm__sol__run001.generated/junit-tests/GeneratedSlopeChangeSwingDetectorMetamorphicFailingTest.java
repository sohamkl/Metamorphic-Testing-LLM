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
import org.ta4j.core.num.NaN;
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static ElliottDegree degree(int offset) {
        ElliottDegree[] degrees = ElliottDegree.values();
        return ElliottDegree.valueOf(degrees[Math.floorMod(offset, degrees.length)].name());
    }

    private static BarSeries emptySeries(String name) {
        return new BaseBarSeriesBuilder().withName(name).build();
    }

    private static BarSeries alternatingSeries(String name) {
        return series(name, new double[] { 0.0, 2.0, 3.0, 1.0, 0.0, 2.0, 3.0, 1.0, 0.0, 2.0, 3.0, 1.0 });
    }

    private static BarSeries series(String name, double[] closes) {
        return series(name, closes, null, null);
    }

    private static BarSeries series(String name, double[] closes, double[] highs, double[] lows) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        for (int i = 0; i < closes.length; i++) {
            double closeValue = closes[i];
            double highValue = highs == null ? (Double.isFinite(closeValue) ? closeValue + 1.0 : Double.NaN) : highs[i];
            double lowValue = lows == null ? (Double.isFinite(closeValue) ? closeValue - 1.0 : Double.NaN) : lows[i];
            Num close = num(result, closeValue);
            Num high = num(result, highValue);
            Num low = num(result, lowValue);
            Num open = close;
            Num volume = result.numFactory().one();
            Num amount = result.numFactory().numOf(1000.0 + i);
            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);
            result.addBar(new BaseBar(BAR_DURATION, begin, end, open, high, low, close, volume, amount, 1L));
        }
        return result;
    }

    private static Num num(BarSeries series, double value) {
        return Double.isNaN(value) ? NaN.NaN : series.numFactory().numOf(value);
    }
}
