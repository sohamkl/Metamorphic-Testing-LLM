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

    private static final double[] HIGH = { 0, 2, 4, 4, 2, 0, -2 };
    private static final double[] LOW = { 4, 2, 0, 0, 2, 4, 6 };
    private static final double[] FLAT = { 3, 3, 3, 3, 3, 3, 3 };
    private static final double[] SAME_SIGN_UP = { 0, 1, 2, 3, 5, 7, 9 };
    private static final double[] SAME_SIGN_DOWN = { 9, 7, 5, 4, 3, 2, 1 };
    private static final double[] HIGH_WRONG_CONFIRMATION = { 0, 2, 4, 4, 2, 0, 4 };
    private static final double[] HIGH_ZERO_CONFIRMATION = { 0, 2, 4, 4, 2, 0, 2 };
    private static final double[] MULTI = {
            0, 2, 4, 6, 4, 2, 0, -2, 0, 2, 4, 6, 4, 2, 0, -2, 0, 2, 4, 6, 4, 2, 0
    };

    private static void exercise(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);

        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput = followUpDetector.detect(
                followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod,
            double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(
                window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static ElliottDegree degree(String name) {
        return ElliottDegree.valueOf(name);
    }

    private static BarSeries series(String name, double baseline, double[] closes) {
        return series(name, baseline, closes, offset(closes, 1), offset(closes, -1));
    }

    private static BarSeries series(String name, double baseline, double[] closes, double[] highs, double[] lows) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant origin = Instant.parse("2024-01-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            Num close = value(result, baseline, closes[i]);
            Num high = value(result, baseline, highs[i]);
            Num low = value(result, baseline, lows[i]);
            Num volume = result.numFactory().numOf(10 + i);
            Num amount = close.multipliedBy(volume);
            Instant begin = origin.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);

            result.addBar(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    1L + i));
        }
        return result;
    }

    private static BarSeries seriesWithNonfiniteClose(String name, double baseline, double[] closes,
            int nonfiniteIndex) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant origin = Instant.parse("2024-02-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            Num close = i == nonfiniteIndex ? NaN.NaN : result.numFactory().numOf(baseline + closes[i]);
            Num high = result.numFactory().numOf(baseline + closes[i] + 1);
            Num low = result.numFactory().numOf(baseline + closes[i] - 1);
            Num volume = result.numFactory().numOf(20 + i);
            Num amount = close.multipliedBy(volume);
            Instant begin = origin.plus(period.multipliedBy(i));
            result.addBar(new BaseBar(
                    period,
                    begin,
                    begin.plus(period),
                    close,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    2L + i));
        }
        return result;
    }

    private static Num value(BarSeries series, double baseline, double adjustment) {
        return Double.isNaN(adjustment)
                ? NaN.NaN
                : series.numFactory().numOf(baseline + adjustment);
    }

    private static double[] offset(double[] source, double amount) {
        double[] result = new double[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = source[i] + amount;
        }
        return result;
    }

    private static double[] negate(double[] source) {
        double[] result = new double[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = -source[i];
        }
        return result;
    }

    private static BarSeries sameTypeHighSeries(String name, double baseline, double firstHigh,
            double secondHigh) {
        double[] highs = offset(MULTI, 1);
        double[] lows = offset(MULTI, -1);
        highs[3] = firstHigh;
        lows[7] = Double.NaN;
        highs[11] = secondHigh;
        lows[15] = Double.NaN;
        highs[19] = secondHigh;
        return series(name, baseline, MULTI, highs, lows);
    }

    private static BarSeries sameTypeLowSeries(String name, double baseline, double firstLow,
            double secondLow) {
        double[] closes = negate(MULTI);
        double[] highs = offset(closes, 1);
        double[] lows = offset(closes, -1);
        lows[3] = firstLow;
        highs[7] = Double.NaN;
        lows[11] = secondLow;
        highs[15] = Double.NaN;
        lows[19] = secondLow;
        return series(name, baseline, closes, highs, lows);
    }
}
