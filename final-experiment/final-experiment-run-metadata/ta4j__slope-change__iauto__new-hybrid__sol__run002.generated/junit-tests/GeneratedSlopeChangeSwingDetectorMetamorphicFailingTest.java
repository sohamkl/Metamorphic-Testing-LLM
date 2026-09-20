import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.indicators.elliott.ElliottSwing;
import org.ta4j.core.num.NaN;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final double TRANSLATION = 100.0;
    private static final double ABSOLUTE_TOLERANCE = 1.0e-9;
    private static final double RELATIVE_TOLERANCE = 1.0e-12;
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static void verify(SlopeChangeConfig config, BarSeries sourceSeries,
            int index, ElliottDegree degree) {
        SlopeChangeSwingDetector sourceDetector =
                new SlopeChangeSwingDetector(config);
        SwingDetectorResult sourceOutput =
                sourceDetector.detect(sourceSeries, index, degree);

        Object[] followUp =
                SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                        sourceDetector, sourceSeries, index, degree);

        SlopeChangeSwingDetector followUpDetector =
                (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput =
                followUpDetector.detect(
                        followUpSeries, followUpIndex, followUpDegree);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SwingDetectorResult sourceOutput,
            SwingDetectorResult followUpOutput) {
        if (sourceOutput == null || followUpOutput == null) {
            throw new AssertionError("Detector results must both be non-null");
        }

        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();

        if (sourcePivots.size() != followUpPivots.size()) {
            throw new AssertionError(
                    "Expected equal pivot counts, but source had "
                            + sourcePivots.size() + " and follow-up had "
                            + followUpPivots.size());
        }

        for (int i = 0; i < sourcePivots.size(); i++) {
            SwingPivot source = sourcePivots.get(i);
            SwingPivot followUp = followUpPivots.get(i);

            if (source.index() != followUp.index()) {
                throw new AssertionError(
                        "Pivot index differs at position " + i
                                + ": source=" + source.index()
                                + ", follow-up=" + followUp.index());
            }
            if (!Objects.equals(source.type(), followUp.type())) {
                throw new AssertionError(
                        "Pivot type differs at position " + i
                                + ": source=" + source.type()
                                + ", follow-up=" + followUp.type());
            }
            assertTranslated(
                    source.price(), followUp.price(), "pivot price", i);
        }

        List<ElliottSwing> sourceSwings = sourceOutput.swings();
        List<ElliottSwing> followUpSwings = followUpOutput.swings();

        if (sourceSwings.size() != followUpSwings.size()) {
            throw new AssertionError(
                    "Expected equal swing counts, but source had "
                            + sourceSwings.size() + " and follow-up had "
                            + followUpSwings.size());
        }

        for (int i = 0; i < sourceSwings.size(); i++) {
            ElliottSwing source = sourceSwings.get(i);
            ElliottSwing followUp = followUpSwings.get(i);

            if (source.fromIndex() != followUp.fromIndex()) {
                throw new AssertionError(
                        "Swing start index differs at position " + i
                                + ": source=" + source.fromIndex()
                                + ", follow-up=" + followUp.fromIndex());
            }
            if (source.toIndex() != followUp.toIndex()) {
                throw new AssertionError(
                        "Swing end index differs at position " + i
                                + ": source=" + source.toIndex()
                                + ", follow-up=" + followUp.toIndex());
            }
            if (!Objects.equals(source.degree(), followUp.degree())) {
                throw new AssertionError(
                        "Swing degree differs at position " + i
                                + ": source=" + source.degree()
                                + ", follow-up=" + followUp.degree());
            }

            assertTranslated(
                    source.fromPrice(), followUp.fromPrice(),
                    "swing start price", i);
            assertTranslated(
                    source.toPrice(), followUp.toPrice(),
                    "swing end price", i);
        }
    }

    private static void assertTranslated(
            Num source, Num followUp, String field, int position) {
        double expected = source.doubleValue() + TRANSLATION;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(
                ABSOLUTE_TOLERANCE,
                Math.max(Math.abs(expected), Math.abs(actual))
                        * RELATIVE_TOLERANCE);

        if (!Double.isFinite(expected)
                || !Double.isFinite(actual)
                || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError(
                    "Expected translated " + field + " at position "
                            + position + " to be " + expected + " +/- "
                            + tolerance + ", but was " + actual);
        }
    }

    private static SlopeChangeConfig config(
            int window, int confirmationBars, int atrPeriod,
            double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeConfig(
                window, confirmationBars, atrPeriod,
                minSlopeChange, minAtrReversal);
    }

    private static BarSeries highSeries() {
        return series(new double[] { 1, 2, 3, 4, 3, 2, 1 }, 3, 0);
    }

    private static BarSeries lowSeries() {
        return series(new double[] { 5, 4, 3, 2, 3, 4, 5 }, 0, 3);
    }

    private static BarSeries zigzagSeries() {
        return series(new double[] {
                1, 2, 3, 4, 3, 2, 1,
                2, 3, 4, 3, 2, 1,
                2, 3, 4, 3
        }, 3, 6);
    }

    private static BarSeries heterogeneousSeries() {
        return series(
                new double[] { 5, 4, 3, 2, 3, 4, 5, 4, 3 },
                6, 3, false, true);
    }

    private static BarSeries emptyZeroBasedSeries() {
        return new BaseBarSeries("", new ArrayList<Bar>()) {
            @Override
            public int getBeginIndex() {
                return 0;
            }
        };
    }

    private static BarSeries series(
            double[] closes, int highBoostIndex, int lowDropIndex) {
        return series(
                closes, highBoostIndex, lowDropIndex, false, false);
    }

    private static BarSeries series(
            double[] closes, int highBoostIndex, int lowDropIndex,
            boolean includeZeroVolume, boolean heterogeneous) {
        BarSeries result = new BaseBarSeriesBuilder()
                .withName("source")
                .build();

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = finite(close) ? close + 0.25 : Double.NaN;
            double low = finite(close) ? close - 0.25 : Double.NaN;

            if (i == highBoostIndex && finite(close)) {
                high = close + 4.0;
            }
            if (i == lowDropIndex && finite(close)) {
                low = close - 4.0;
            }

            double volume;
            if (includeZeroVolume && i == 1) {
                volume = 0.0;
            } else if (heterogeneous) {
                volume = 1.0 + (i % 3);
            } else {
                volume = 1.0;
            }

            double amount = finite(close)
                    ? close * volume + i * 0.125
                    : 0.0;

            addBar(
                    result, i, close, high, low, close,
                    volume, amount);
        }
        return result;
    }

    private static BarSeries customWickSeries(
            double[] closes, int[] highIndices,
            int[] lowIndices, double wickMagnitude) {
        BarSeries result = new BaseBarSeriesBuilder()
                .withName("wick-source")
                .build();

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = close + 0.25;
            double low = close - 0.25;

            if (contains(highIndices, i)) {
                high = close + wickMagnitude;
            }
            if (contains(lowIndices, i)) {
                low = close - wickMagnitude;
            }

            double volume = 1.0 + (i % 2);
            addBar(
                    result, i, close, high, low, close,
                    volume, close * volume);
        }
        return result;
    }

    private static BarSeries tiedExtremeSeries(
            double[] closes, boolean highTie) {
        BarSeries result = new BaseBarSeriesBuilder()
                .withName("tied-extreme")
                .build();

        for (int i = 0; i < closes.length; i++) {
            double high = closes[i] + 0.25;
            double low = closes[i] - 0.25;

            if (highTie && (i == 2 || i == 3)) {
                high = 20.0;
            }
            if (!highTie && (i == 2 || i == 3)) {
                low = -10.0;
            }

            addBar(
                    result, i, closes[i], high, low, closes[i],
                    1.0, closes[i]);
        }
        return result;
    }

    private static BarSeries seriesWithNonFiniteExtreme(
            double[] closes, int extremeIndex, boolean nonFiniteHigh) {
        BarSeries result = new BaseBarSeriesBuilder()
                .withName("non-finite-extreme")
                .build();

        for (int i = 0; i < closes.length; i++) {
            double high = closes[i] + 0.25;
            double low = closes[i] - 0.25;

            if (i == extremeIndex) {
                if (nonFiniteHigh) {
                    high = Double.NaN;
                } else {
                    low = Double.NaN;
                }
            }

            addBar(
                    result, i, closes[i], high, low, closes[i],
                    1.0, closes[i]);
        }
        return result;
    }

    private static void addBar(
            BarSeries series, int index, double open,
            double high, double low, double close,
            double volume, double amount) {
        Instant begin = BASE_TIME.plus(Duration.ofMinutes(index));
        Instant end = begin.plus(Duration.ofMinutes(1));

        series.addBar(new BaseBar(
                Duration.ofMinutes(1),
                begin,
                end,
                num(series, open),
                num(series, high),
                num(series, low),
                num(series, close),
                num(series, volume),
                num(series, amount),
                1L));
    }

    private static Num num(BarSeries series, double value) {
        return finite(value)
                ? series.numFactory().numOf(value)
                : NaN.NaN;
    }

    private static boolean finite(double value) {
        return Double.isFinite(value);
    }

    private static boolean contains(int[] values, int target) {
        for (int value : values) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    private static double[] shift(double[] values, double offset) {
        double[] shifted = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            shifted[i] = values[i] + offset;
        }
        return shifted;
    }
}
