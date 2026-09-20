import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
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

    private static final double TRANSLATION = 100.0;

    private static final double[] HIGH_C1 = { 10, 11, 12, 11, 10, 9 };
    private static final double[] HIGH_C2 = { 10, 11, 12, 11, 10, 9, 8 };
    private static final double[] LOW_C1 = { 12, 11, 10, 11, 12, 13 };
    private static final double[] LOW_C2 = { 12, 11, 10, 11, 12, 13, 14 };
    private static final double[] MULTI_HIGH_FIRST = {
            10, 11, 12, 11, 10, 9, 10, 11, 12, 11, 10, 9
    };
    private static final double[] MULTI_LOW_FIRST = {
            12, 11, 10, 11, 12, 13, 12, 11, 10, 11, 12, 13
    };

    private static void runCase(SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);

        FollowUp followUp = generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = followUp.detector.detect(
                followUp.series,
                followUp.index,
                followUp.degree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder()
                .withName(sourceSeries.getName() + "-translated")
                .withNumFactory(sourceSeries.numFactory())
                .build();

        Num translation = sourceSeries.numFactory().numOf(TRANSLATION);

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

        return new FollowUp(
                new SlopeChangeSwingDetector(detector.getConfig()),
                translatedSeries,
                index,
                degree);
    }

    private static SlopeChangeSwingDetector explicit(int window,
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

    private static BarSeries series(String name,
            double[] closes,
            double volume,
            double amount) {
        return series(name, closes, volume, amount, null, null);
    }

    private static BarSeries series(String name,
            double[] closes,
            double volume,
            double amount,
            double[] highOverrides,
            double[] lowOverrides) {
        BarSeries result = new BaseBarSeriesBuilder()
                .withName(name)
                .build();

        Duration period = Duration.ofMinutes(1);
        Instant base = Instant.parse("2024-01-01T00:00:00Z");

        for (int index = 0; index < closes.length; index++) {
            Num close = num(result, closes[index]);
            Num open = close;
            Num high = close.plus(result.numFactory().numOf(0.5));
            Num low = close.minus(result.numFactory().numOf(0.5));

            if (highOverrides != null && !Double.isNaN(highOverrides[index])) {
                high = num(result, highOverrides[index]);
            }
            if (lowOverrides != null && !Double.isNaN(lowOverrides[index])) {
                low = num(result, lowOverrides[index]);
            }

            Instant begin = base.plus(period.multipliedBy(index));
            Instant end = begin.plus(period);

            result.addBar(new BaseBar(
                    period,
                    begin,
                    end,
                    open,
                    high,
                    low,
                    close,
                    result.numFactory().numOf(volume),
                    result.numFactory().numOf(amount + index),
                    index + 1L));
        }

        return result;
    }

    private static Num num(BarSeries series, double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            return NaN.NaN;
        }
        return series.numFactory().numOf(value);
    }

    private static double[] overrides(int length) {
        double[] values = new double[length];
        Arrays.fill(values, Double.NaN);
        return values;
    }

    private static final class FollowUp {
        private final SlopeChangeSwingDetector detector;
        private final BarSeries series;
        private final int index;
        private final ElliottDegree degree;

        private FollowUp(SlopeChangeSwingDetector detector,
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
