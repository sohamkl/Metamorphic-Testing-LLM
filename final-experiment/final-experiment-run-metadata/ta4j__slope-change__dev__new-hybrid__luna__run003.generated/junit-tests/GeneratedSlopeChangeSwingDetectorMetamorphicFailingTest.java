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
import org.ta4j.core.num.DoubleNumFactory;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static void verify(
            SlopeChangeSwingDetector detector,
            BarSeries source,
            int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput =
                detector.detect(source, index, degree);

        Object[] followUp =
                SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                        detector, source, index, degree);

        SlopeChangeSwingDetector followUpDetector =
                (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput =
                followUpDetector.detect(
                        followUpSeries, followUpIndex, followUpDegree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static ElliottDegree degree(String name) {
        return ElliottDegree.valueOf(name);
    }

    private static SlopeChangeConfig config(
            int window,
            int confirmationBars,
            int atrPeriod,
            double minSlopeChange,
            double minAtrReversal) {
        return new SlopeChangeConfig(
                window,
                confirmationBars,
                atrPeriod,
                minSlopeChange,
                minAtrReversal);
    }

    private static BarSeries series(double... closes) {
        return series(closes, false, false, false, false, false);
    }

    private static BarSeries series(
            double[] closes,
            boolean zeroVolume,
            boolean nonFiniteHigh,
            boolean nonFiniteLow,
            boolean nonFiniteClose,
            boolean largeValues) {
        BaseBarSeriesBuilder builder = new BaseBarSeriesBuilder()
                .withName("source");

        if (nonFiniteHigh || nonFiniteLow || nonFiniteClose) {
            builder.withNumFactory(DoubleNumFactory.getInstance());
        }

        BarSeries result = builder.build();
        Instant start = Instant.parse("2020-01-01T00:00:00Z");

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            if (largeValues) {
                close += 1.0e12;
            }

            double open = close;
            double high = close + 0.75;
            double low = close - 0.75;

            if (nonFiniteHigh && i == 8) {
                high = Double.NaN;
            }
            if (nonFiniteLow && i == 8) {
                low = Double.NaN;
            }
            if (nonFiniteClose && i == 6) {
                close = Double.NaN;
                open = Double.NaN;
            }

            Num volume = result.numFactory().numOf(zeroVolume ? 0.0 : 1.0);
            Num amount = result.numFactory().numOf(0.0);

            result.addBar(new BaseBar(
                    Duration.ofMinutes(1),
                    start.plusSeconds(i * 60L),
                    start.plusSeconds((i + 1L) * 60L),
                    result.numFactory().numOf(open),
                    result.numFactory().numOf(high),
                    result.numFactory().numOf(low),
                    result.numFactory().numOf(close),
                    volume,
                    amount,
                    0L));
        }
        return result;
    }

    private static BarSeries generatedSeries(
            int length,
            double base,
            double amplitude,
            int period) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % period;
            int half = period / 2;
            values[i] = base
                    + amplitude * (phase < half ? phase : period - phase);
        }
        return series(values);
    }

    private static BarSeries waveSeries(int length) {
        return generatedSeries(length, 100.0, 20.0, 12);
    }

    private static BarSeries invertedWaveSeries(int length) {
        return generatedSeries(length, 100.0, -20.0, 12);
    }

    private static BarSeries flatSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 100.0;
        }
        return series(values);
    }

    private static BarSeries ascendingSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 100.0 + i;
        }
        return series(values);
    }

    private static BarSeries descendingSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 200.0 - i;
        }
        return series(values);
    }

    private static BarSeries alternatingSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 8;
            values[i] = 100.0 + (phase < 4 ? phase : 8 - phase);
        }
        return series(values);
    }

    private static BarSeries boundedWaveSeries(int length) {
        return generatedSeries(length, 100.0, 5.0, 10);
    }

    private static BarSeries repeatedHighSeries(int length) {
        return generatedSeries(length, 100.0, 30.0, 16);
    }

    private static BarSeries repeatedLowSeries(int length) {
        return generatedSeries(length, 100.0, -30.0, 16);
    }

    private static BarSeries tiedHighSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 8;
            values[i] = phase < 4
                    ? 100.0 + phase
                    : 104.0 - (phase - 4);
        }
        return series(values);
    }

    private static BarSeries tiedLowSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 8;
            values[i] = phase < 4
                    ? 100.0 - phase
                    : 97.0 + (phase - 4);
        }
        return series(values);
    }

    private static BarSeries largeSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 14;
            int half = 7;
            values[i] = 1.0e6
                    + 1.0e6 * (phase < half ? phase : 14 - phase);
        }
        return series(values, false, false, false, false, true);
    }

    private static BarSeries zeroVolumeSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 12;
            values[i] = 100.0 + (phase < 6 ? phase : 12 - phase);
        }
        return series(values, true, false, false, false, false);
    }

    private static BarSeries zeroVolumeWaveSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 16;
            values[i] = 100.0 + (phase < 8 ? phase : 16 - phase);
        }
        return series(values, true, false, false, false, false);
    }

    private static BarSeries nonFiniteCloseSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 100.0 + (i % 9);
        }
        return series(values, false, false, false, true, false);
    }

    private static BarSeries nonFiniteHighSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 12;
            values[i] = 100.0 + (phase < 6 ? phase : 12 - phase);
        }
        return series(values, false, true, false, false, false);
    }

    private static BarSeries nonFiniteLowSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 12;
            values[i] = 100.0 - (phase < 6 ? phase : 12 - phase);
        }
        return series(values, false, false, true, false, false);
    }

    private static BarSeries mixedSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 20;
            if (phase < 5) {
                values[i] = 100.0;
            } else if (phase < 10) {
                values[i] = 100.0 + phase - 5;
            } else if (phase < 15) {
                values[i] = 105.0 - (phase - 10) * 2.0;
            } else {
                values[i] = 95.0 + (phase - 15) * 0.25;
            }
        }
        return series(values);
    }
}
