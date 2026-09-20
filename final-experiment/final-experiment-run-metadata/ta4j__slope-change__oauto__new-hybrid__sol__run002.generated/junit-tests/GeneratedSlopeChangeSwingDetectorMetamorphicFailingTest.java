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
import org.ta4j.core.num.DoubleNumFactory;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final double[] HIGH = { 10, 12, 14, 13, 11, 9, 8 };
    private static final double[] LOW = { 14, 12, 10, 11, 13, 15, 16 };
    private static final double[] FLAT = { 10, 10, 10, 10, 10, 10, 10 };
    private static final double[] POSITIVE_SAME_SIGN = { 10, 11, 12, 13, 15, 18, 22 };
    private static final double[] NEGATIVE_SAME_SIGN = { 22, 18, 15, 13, 12, 11, 10 };
    private static final double[] ZERO_BEFORE = { 10, 10, 10, 9, 8, 7, 6 };
    private static final double[] HIGH_PERSISTENCE_LATE_FAILURE = { 10, 12, 14, 13, 11, 9, 13 };
    private static final double[] LOW_PERSISTENCE_LATE_FAILURE = { 14, 12, 10, 11, 13, 15, 11 };
    private static final double[] ZIGZAG = {
            10, 12, 14, 12, 10, 8, 10, 12, 14, 12, 10, 8, 10, 12, 14, 12, 10, 8, 10, 12, 14
    };
    private static final double[] ZIGZAG_WIDE = {
            20, 25, 30, 25, 20, 15, 20, 25, 30, 25, 20, 15, 20, 25, 30, 25, 20, 15, 20, 25, 30
    };
    private static final double[] TWO_HIGHS = {
            10, 12, 14, 13, 11, 9, 10, 11, 13, 15, 14, 12, 10, 9
    };
    private static final double[] TWO_LOWS = {
            16, 14, 12, 13, 15, 17, 16, 15, 13, 11, 12, 14, 16, 17
    };

    private static void exercise(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {

        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        FollowUp followUp = generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = followUp.detector.detect(
                followUp.series, followUp.index, followUp.degree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {

        BarSeries translatedSeries = new BaseBarSeriesBuilder()
                .withName(sourceSeries.getName() + "-translated")
                .withNumFactory(sourceSeries.numFactory())
                .build();
        Num translation = sourceSeries.numFactory().numOf(100.0);

        if (!sourceSeries.isEmpty()) {
            for (int barIndex = sourceSeries.getBeginIndex();
                    barIndex <= sourceSeries.getEndIndex();
                    barIndex++) {

                Bar sourceBar = sourceSeries.getBar(barIndex);
                Num translatedAmount = sourceBar.getAmount()
                        .plus(translation.multipliedBy(sourceBar.getVolume()));

                translatedSeries.addBar(new BaseBar(
                        sourceBar.getTimePeriod(),
                        sourceBar.getBeginTime(),
                        sourceBar.getEndTime(),
                        sourceBar.getOpenPrice().plus(translation),
                        sourceBar.getHighPrice().plus(translation),
                        sourceBar.getLowPrice().plus(translation),
                        sourceBar.getClosePrice().plus(translation),
                        sourceBar.getVolume(),
                        translatedAmount,
                        sourceBar.getTrades()));
            }
        }

        return new FollowUp(
                new SlopeChangeSwingDetector(detector.getConfig()),
                translatedSeries,
                index,
                degree);
    }

    private static SlopeChangeSwingDetector detector(
            int window,
            int confirmationBars,
            int atrPeriod,
            double minSlopeChange,
            double minAtrReversal) {

        return new SlopeChangeSwingDetector(new SlopeChangeConfig(
                window,
                confirmationBars,
                atrPeriod,
                minSlopeChange,
                minAtrReversal));
    }

    private static BarSeries emptySeries(String name) {
        return new BaseBarSeriesBuilder().withName(name).build();
    }

    private static BarSeries series(String name, double[] closes, int volumeMode) {
        return specializedSeries(name, closes, volumeMode, -1, -1, -1);
    }

    private static BarSeries specializedSeries(
            String name,
            double[] closes,
            int volumeMode,
            int highBoostIndex,
            int lowDropIndex,
            int unusedIndex) {

        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = close + 1.0;
            double low = close - 1.0;

            if (i == highBoostIndex) {
                high = close + 20.0;
            }
            if (i == lowDropIndex) {
                low = close - 20.0;
            }

            double volume = volume(volumeMode, i);
            double amount = close * volume + i;
            addBar(result, period, base.plus(period.multipliedBy(i)),
                    close, high, low, close, volume, amount, i + 1L);
        }
        return result;
    }

    private static BarSeries tieSeries(
            String name,
            double[] closes,
            boolean highTie,
            int volumeMode) {

        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant base = Instant.parse("2021-02-03T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = close + 1.0;
            double low = close - 1.0;

            if (highTie && (i == 2 || i == 3)) {
                high = 30.0;
            }
            if (!highTie && (i == 2 || i == 3)) {
                low = -10.0;
            }

            double volume = volume(volumeMode, i);
            addBar(result, period, base.plus(period.multipliedBy(i)),
                    close, high, low, close, volume,
                    close * volume + 2.0 * i, 10L + i);
        }
        return result;
    }

    private static BarSeries nanCloseSeries(
            String name,
            double[] closes,
            int nanCloseIndex,
            int volumeMode) {

        BarSeries result = doubleNumSeries(name);
        Instant base = Instant.parse("2022-04-05T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            double ordinaryClose = closes[i];
            double close = i == nanCloseIndex ? Double.NaN : ordinaryClose;
            double open = close;
            double high = Double.isNaN(close) ? Double.NaN : close + 1.0;
            double low = Double.isNaN(close) ? Double.NaN : close - 1.0;
            double volume = volume(volumeMode, i);
            double amount = ordinaryClose * volume + i;

            addBar(result, period, base.plus(period.multipliedBy(i)),
                    open, high, low, close, volume, amount, 20L + i);
        }
        return result;
    }

    private static BarSeries nanExtremeSeries(
            String name,
            double[] closes,
            int nanIndex,
            boolean nanHigh,
            int volumeMode) {

        BarSeries result = doubleNumSeries(name);
        Instant base = Instant.parse("2023-06-07T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = close + 1.0;
            double low = close - 1.0;

            if (i == nanIndex) {
                if (nanHigh) {
                    high = Double.NaN;
                } else {
                    low = Double.NaN;
                }
            }

            double volume = volume(volumeMode, i);
            addBar(result, period, base.plus(period.multipliedBy(i)),
                    close, high, low, close, volume,
                    close * volume + i, 30L + i);
        }
        return result;
    }

    private static BarSeries nanAtrSeries(
            String name,
            double[] closes,
            int nanHighIndex,
            int volumeMode) {

        BarSeries result = doubleNumSeries(name);
        Instant base = Instant.parse("2024-08-09T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = i == nanHighIndex ? Double.NaN : close + 1.0;
            double low = close - 1.0;
            double volume = volume(volumeMode, i);

            addBar(result, period, base.plus(period.multipliedBy(i)),
                    close, high, low, close, volume,
                    close * volume + 3.0 * i, 40L + i);
        }
        return result;
    }

    private static BarSeries doubleNumSeries(String name) {
        return new BaseBarSeriesBuilder()
                .withName(name)
                .withNumFactory(DoubleNumFactory.getInstance())
                .build();
    }

    private static void addBar(
            BarSeries series,
            Duration period,
            Instant begin,
            double open,
            double high,
            double low,
            double close,
            double volume,
            double amount,
            long trades) {

        Instant end = begin.plus(period);
        series.addBar(new BaseBar(
                period,
                begin,
                end,
                series.numFactory().numOf(open),
                series.numFactory().numOf(high),
                series.numFactory().numOf(low),
                series.numFactory().numOf(close),
                series.numFactory().numOf(volume),
                series.numFactory().numOf(amount),
                trades));
    }

    private static double volume(int mode, int index) {
        if (mode == 0) {
            return 0.0;
        }
        if (mode == 1) {
            return 10.0;
        }
        return 1.0 + (index % 5) * 2.5;
    }

    private static double[] slice(double[] values, int length) {
        double[] result = new double[length];
        System.arraycopy(values, 0, result, 0, length);
        return result;
    }

    private static double[] shifted(double[] values, double shift) {
        double[] result = values.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] += shift;
        }
        return result;
    }

    private static final class FollowUp {
        private final SlopeChangeSwingDetector detector;
        private final BarSeries series;
        private final int index;
        private final ElliottDegree degree;

        private FollowUp(
                SlopeChangeSwingDetector detector,
                BarSeries series,
                int index,
                ElliottDegree degree) {
            this.detector = detector;
            this.series = series;
            this.index = index;
            this.degree = degree;
        }
    }
}
