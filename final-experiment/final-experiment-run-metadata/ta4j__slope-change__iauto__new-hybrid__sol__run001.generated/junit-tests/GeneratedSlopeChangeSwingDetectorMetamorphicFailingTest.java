import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.indicators.elliott.ElliottSwing;
import org.ta4j.core.num.DoubleNumFactory;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.NumFactory;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final double TRANSLATION = 100.0;
    private static final double ABSOLUTE_TOLERANCE = 1.0e-9;
    private static final double RELATIVE_TOLERANCE = 1.0e-12;
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

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

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars,
            double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(
                window, confirmationBars, 3, minSlopeChange, minAtrReversal));
    }

    private static BarSeries highTurn(double base, double step) {
        return series(highTurnValues(base, step));
    }

    private static double[] highTurnValues(double base, double step) {
        return new double[] {
                base,
                base + step,
                base + 2 * step,
                base + step,
                base,
                base - step,
                base - 2 * step
        };
    }

    private static BarSeries lowTurn(double base, double step) {
        return series(new double[] {
                base,
                base - step,
                base - 2 * step,
                base - step,
                base,
                base + step,
                base + 2 * step
        });
    }

    private static BarSeries alternating(double base, double step, int turns) {
        int segments = Math.max(2, turns + 1);
        double[] close = new double[segments * 6 + 1];
        for (int index = 0; index < close.length; index++) {
            int segment = index / 6;
            int offset = index % 6;
            double direction = segment % 2 == 0 ? 1.0 : -1.0;
            double segmentBase = base + (segment % 2 == 0 ? 0.0 : 6.0 * step);
            close[index] = segmentBase + direction * offset * step;
        }
        return series(close);
    }

    private static BarSeries alternatingWithAuxiliaryData(double base, double step, int turns) {
        int segments = Math.max(2, turns + 1);
        double[] close = new double[segments * 6 + 1];
        for (int index = 0; index < close.length; index++) {
            int segment = index / 6;
            int offset = index % 6;
            double direction = segment % 2 == 0 ? 1.0 : -1.0;
            double segmentBase = base + (segment % 2 == 0 ? 0.0 : 6.0 * step);
            close[index] = segmentBase + direction * offset * step;
        }
        return series(close, null, null, 3);
    }

    private static BarSeries doubleSeries(double[] close) {
        return createSeries("double-series", close, null, null, 1, DoubleNumFactory.getInstance());
    }

    private static BarSeries doubleSeries(double[] close, double[] high, double[] low, int auxiliaryMode) {
        return createSeries("double-series", close, high, low, auxiliaryMode, DoubleNumFactory.getInstance());
    }

    private static BarSeries series(double[] close) {
        return series(close, null, null, 1);
    }

    private static BarSeries series(double[] close, double[] high, double[] low, int auxiliaryMode) {
        BarSeries probe = new BaseBarSeriesBuilder().withName("probe").build();
        return createSeries("source-series", close, high, low, auxiliaryMode, probe.numFactory());
    }

    private static BarSeries createSeries(String name, double[] close, double[] high, double[] low,
            int auxiliaryMode, NumFactory numFactory) {
        BarSeries series = new BaseBarSeriesBuilder()
                .withName(name)
                .withNumFactory(numFactory)
                .build();

        for (int index = 0; index < close.length; index++) {
            double closeValue = close[index];
            double highValue = high == null ? closeValue + 1.0 : high[index];
            double lowValue = low == null ? closeValue - 1.0 : low[index];
            double openValue = closeValue;

            double volumeValue;
            double amountValue;
            long trades;
            if (auxiliaryMode == 0) {
                volumeValue = 0.0;
                amountValue = 0.0;
                trades = 0L;
            } else if (auxiliaryMode == 2) {
                volumeValue = index % 2 == 0 ? 0.0 : index + 1.0;
                amountValue = index % 3 == 0 ? 0.0 : Math.abs(closeValue) * volumeValue;
                trades = index % 4;
            } else if (auxiliaryMode == 3) {
                volumeValue = 1.0 + index % 5;
                amountValue = (Math.abs(closeValue) + index + 1.0) * volumeValue;
                trades = 1L + index % 7;
            } else {
                volumeValue = 10.0;
                amountValue = Math.abs(closeValue) * volumeValue;
                trades = 1L;
            }

            Num open = numFactory.numOf(openValue);
            Num highPrice = numFactory.numOf(highValue);
            Num lowPrice = numFactory.numOf(lowValue);
            Num closePrice = numFactory.numOf(closeValue);
            Num volume = numFactory.numOf(volumeValue);
            Num amount = numFactory.numOf(amountValue);

            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(index));
            Instant end = begin.plus(BAR_DURATION);
            series.addBar(new BaseBar(
                    BAR_DURATION,
                    begin,
                    end,
                    open,
                    highPrice,
                    lowPrice,
                    closePrice,
                    volume,
                    amount,
                    trades));
        }
        return series;
    }

    private static void assertMetamorphicRelation(SwingDetectorResult sourceOutput,
            SwingDetectorResult followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");

        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();

        if (sourcePivots.size() != followUpPivots.size()) {
            throw new AssertionError("Pivot count differs: source=" + sourcePivots.size()
                    + ", follow-up=" + followUpPivots.size());
        }

        for (int index = 0; index < sourcePivots.size(); index++) {
            SwingPivot source = sourcePivots.get(index);
            SwingPivot followUp = followUpPivots.get(index);

            if (source.index() != followUp.index()) {
                throw new AssertionError("Pivot index differs at position " + index
                        + ": source=" + source.index() + ", follow-up=" + followUp.index());
            }
            if (!Objects.equals(source.type(), followUp.type())) {
                throw new AssertionError("Pivot type differs at position " + index
                        + ": source=" + source.type() + ", follow-up=" + followUp.type());
            }
            assertTranslated(source.price(), followUp.price(), "pivot price", index);
        }

        List<ElliottSwing> sourceSwings = sourceOutput.swings();
        List<ElliottSwing> followUpSwings = followUpOutput.swings();

        if (sourceSwings.size() != followUpSwings.size()) {
            throw new AssertionError("Swing count differs: source=" + sourceSwings.size()
                    + ", follow-up=" + followUpSwings.size());
        }

        for (int index = 0; index < sourceSwings.size(); index++) {
            ElliottSwing source = sourceSwings.get(index);
            ElliottSwing followUp = followUpSwings.get(index);

            if (source.fromIndex() != followUp.fromIndex()) {
                throw new AssertionError("Swing start index differs at position " + index
                        + ": source=" + source.fromIndex() + ", follow-up=" + followUp.fromIndex());
            }
            if (source.toIndex() != followUp.toIndex()) {
                throw new AssertionError("Swing end index differs at position " + index
                        + ": source=" + source.toIndex() + ", follow-up=" + followUp.toIndex());
            }
            if (!Objects.equals(source.degree(), followUp.degree())) {
                throw new AssertionError("Swing degree differs at position " + index
                        + ": source=" + source.degree() + ", follow-up=" + followUp.degree());
            }

            assertTranslated(source.fromPrice(), followUp.fromPrice(), "swing start price", index);
            assertTranslated(source.toPrice(), followUp.toPrice(), "swing end price", index);
        }
    }

    private static void assertTranslated(Num source, Num followUp, String field, int index) {
        double expected = source.doubleValue() + TRANSLATION;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(
                ABSOLUTE_TOLERANCE,
                Math.max(Math.abs(expected), Math.abs(actual)) * RELATIVE_TOLERANCE);

        if (!Double.isFinite(expected)
                || !Double.isFinite(actual)
                || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Translated " + field + " differs at position " + index
                    + ": expected=" + expected + " +/- " + tolerance + ", actual=" + actual);
        }
    }
}
