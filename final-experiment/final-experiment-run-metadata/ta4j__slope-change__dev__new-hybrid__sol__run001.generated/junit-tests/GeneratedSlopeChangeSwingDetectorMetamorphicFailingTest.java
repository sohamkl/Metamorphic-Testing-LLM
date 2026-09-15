import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
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

    private static void exercise(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
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

    private static BarSeries emptySeries() {
        return new BaseBarSeries("", new ArrayList<>());
    }

    private static BarSeries series(double[] closes, double spread, boolean varyingVolume, boolean zeroVolume) {
        return series(closes, defaultHighs(closes, spread), defaultLows(closes, spread), varyingVolume, zeroVolume);
    }

    private static BarSeries series(double[] closes, double[] highs, double[] lows, boolean varyingVolume) {
        return series(closes, highs, lows, varyingVolume, false);
    }

    private static BarSeries series(double[] closes, double[] highs, double[] lows, boolean varyingVolume, boolean zeroVolume) {
        BarSeries result = new BaseBarSeriesBuilder().withName("slope-change-source").build();
        for (int i = 0; i < closes.length; i++) {
            boolean finiteClose = Double.isFinite(closes[i]);
            double openValue = finiteClose ? closes[i] : 0.0;
            double volumeValue = zeroVolume ? 0.0 : varyingVolume ? i + 1.0 : 5.0;
            double amountValue = zeroVolume ? 0.0 : volumeValue * openValue;
            Num open = result.numFactory().numOf(openValue);
            Num high = number(result, highs[i]);
            Num low = number(result, lows[i]);
            Num close = number(result, closes[i]);
            Num volume = result.numFactory().numOf(volumeValue);
            Num amount = result.numFactory().numOf(amountValue);
            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);
            result.addBar(new BaseBar(BAR_DURATION, begin, end, open, high, low, close, volume, amount, i + 1L));
        }
        return result;
    }

    private static Num number(BarSeries series, double value) {
        return Double.isFinite(value) ? series.numFactory().numOf(value) : NaN.NaN;
    }

    private static double[] defaultHighs(double[] closes, double spread) {
        double[] values = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            values[i] = Double.isFinite(closes[i]) ? closes[i] + spread : spread;
        }
        return values;
    }

    private static double[] defaultLows(double[] closes, double spread) {
        double[] values = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            values[i] = Double.isFinite(closes[i]) ? closes[i] - spread : -spread;
        }
        return values;
    }

    private static double[] constant(int size, double value) {
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = value;
        }
        return result;
    }

    private static double[] linear(int size, double start, double step) {
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = start + step * i;
        }
        return result;
    }

    private static double[] highShape(double level, int size) {
        double[] result = new double[size];
        int peak = Math.max(1, (size - 1) / 2);
        for (int i = 0; i < size; i++) {
            result[i] = level + (i <= peak ? i : 2.0 * peak - i);
        }
        return result;
    }

    private static double[] lowShape(double level, int size) {
        double[] high = highShape(0.0, size);
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = level - high[i];
        }
        return result;
    }

    private static double[] zigzag(double level, int size) {
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            int phase = i % 8;
            double offset;
            if (phase <= 3) {
                offset = phase;
            } else {
                offset = 6 - phase;
            }
            result[i] = level + offset;
        }
        return result;
    }
}
