package mtllm.examples.ta4j;

import java.time.Duration;
import java.time.Instant;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.DoubleNumFactory;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorSut.SlopeChangeCase;

/** Randoop-facing factories for valid slope-detector seed objects. */
public final class SlopeChangeCaseFactory {
    private static final Instant BASE_TIME = Instant.parse("2023-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(5);

    private SlopeChangeCaseFactory() {
    }

    public static SlopeChangeCase monotonicSeed(double priceOffset) {
        return fromCloses(shifted(priceOffset,
                100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 122),
                new SlopeChangeConfig(2, 1, 2, 0.0, 0.0));
    }

    public static SlopeChangeCase highThenLowSeed(double priceOffset) {
        return fromCloses(shifted(priceOffset,
                100, 102, 104, 106, 108, 110, 108, 106, 104, 102, 100, 98, 100, 102, 104, 106
        ), new SlopeChangeConfig(2, 1, 5, 0.1, 0.5));
    }

    public static SlopeChangeCase lowThenHighSeed(double priceOffset) {
        return fromCloses(shifted(priceOffset,
                120, 118, 116, 114, 112, 110, 112, 114, 116, 118, 120, 122, 120, 118, 116, 114
        ), new SlopeChangeConfig(2, 1, 5, 0.1, 0.5));
    }

    public static SlopeChangeCase alternatingSeed(double priceOffset) {
        return fromCloses(shifted(priceOffset,
                200, 204, 208, 212, 208, 204, 200, 196, 200, 204, 208, 212,
                208, 204, 200, 196, 200, 204, 208, 212
        ), new SlopeChangeConfig(2, 1, 5, 0.1, 0.5));
    }

    public static SlopeChangeCase roundedSeed(double priceOffset) {
        double offset = boundedOffset(priceOffset);
        BarSeries series = newSeries("rounded-seed");
        for (int index = 0; index < 24; index++) {
            double close = 300.0 + offset + 20.0 * Math.sin(2.0 * Math.PI * index / 12.0);
            addBar(series, index, close);
        }
        return new SlopeChangeCase(series, series.getEndIndex(),
                new SlopeChangeConfig(3, 2, 5, 0.1, 0.5), ElliottDegree.MINUETTE);
    }

    private static double[] shifted(double priceOffset, double... closes) {
        double offset = boundedOffset(priceOffset);
        for (int index = 0; index < closes.length; index++) {
            closes[index] += offset;
        }
        return closes;
    }

    private static double boundedOffset(double priceOffset) {
        if (!Double.isFinite(priceOffset)) {
            return 0.0;
        }
        return Math.max(-50.0, Math.min(50.0, priceOffset));
    }

    private static SlopeChangeCase fromCloses(double[] closes, SlopeChangeConfig config) {
        BarSeries series = newSeries("slope-seed");
        for (int index = 0; index < closes.length; index++) {
            addBar(series, index, closes[index]);
        }
        return new SlopeChangeCase(series, series.getEndIndex(), config, ElliottDegree.MINUETTE);
    }

    private static BarSeries newSeries(String name) {
        return new BaseBarSeriesBuilder()
                .withName(name)
                .withNumFactory(DoubleNumFactory.getInstance())
                .build();
    }

    private static void addBar(BarSeries series, int index, double close) {
        Instant beginTime = BASE_TIME.plus(PERIOD.multipliedBy(index));
        double spread = Math.max(0.1, close * 0.001);
        double volume = 100.0 + index;
        series.addBar(new BaseBar(
                PERIOD,
                beginTime,
                beginTime.plus(PERIOD),
                series.numFactory().numOf(close),
                series.numFactory().numOf(close + spread),
                series.numFactory().numOf(close - spread),
                series.numFactory().numOf(close),
                series.numFactory().numOf(volume),
                series.numFactory().numOf(close * volume),
                index + 1L));
    }
}
