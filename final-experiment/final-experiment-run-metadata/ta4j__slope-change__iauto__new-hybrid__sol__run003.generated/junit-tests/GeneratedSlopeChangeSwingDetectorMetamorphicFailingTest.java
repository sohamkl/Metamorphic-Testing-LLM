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
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final double TRANSLATION = 100.0;
    private static final double ABSOLUTE_TOLERANCE = 1.0e-9;
    private static final double RELATIVE_TOLERANCE = 1.0e-12;
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static SlopeChangeSwingDetector detector(
            int window,
            int confirmationBars,
            int atrPeriod,
            double minSlopeChange,
            double minAtrReversal) {
        return new SlopeChangeSwingDetector(
                new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries series(
            String name,
            double[] closes,
            double spread,
            double initialVolume) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + spread;
            lows[i] = closes[i] - spread;
        }
        return bars(name, closes, highs, lows, initialVolume);
    }

    private static BarSeries bars(
            String name,
            double[] closes,
            double[] highs,
            double[] lows,
            double initialVolume) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        for (int i = 0; i < closes.length; i++) {
            double volumeValue = initialVolume == 0.0 ? 0.0 : initialVolume + i * 0.25;
            Num open = result.numFactory().numOf(closes[i]);
            Num high = result.numFactory().numOf(highs[i]);
            Num low = result.numFactory().numOf(lows[i]);
            Num close = result.numFactory().numOf(closes[i]);
            Num volume = result.numFactory().numOf(volumeValue);
            Num amount = result.numFactory().numOf(closes[i] * volumeValue);
            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);
            result.addBar(new BaseBar(
                    BAR_DURATION,
                    begin,
                    end,
                    open,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    i));
        }
        return result;
    }

    private static void exercise(
            SlopeChangeSwingDetector sourceDetector,
            BarSeries sourceSeries,
            int sourceIndex,
            ElliottDegree sourceDegree) {
        SwingDetectorResult sourceOutput =
                sourceDetector.detect(sourceSeries, sourceIndex, sourceDegree);

        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                sourceDetector, sourceSeries, sourceIndex, sourceDegree);

        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput =
                followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SwingDetectorResult sourceOutput,
            SwingDetectorResult followUpOutput) {
        if (sourceOutput == null || followUpOutput == null) {
            throw new AssertionError("Both detector results must be nonnull");
        }

        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();
        if (sourcePivots.size() != followUpPivots.size()) {
            throw new AssertionError("Pivot counts differ: source="
                    + sourcePivots.size() + ", follow-up=" + followUpPivots.size());
        }

        for (int i = 0; i < sourcePivots.size(); i++) {
            SwingPivot source = sourcePivots.get(i);
            SwingPivot followUp = followUpPivots.get(i);

            if (source.index() != followUp.index()) {
                throw new AssertionError("Pivot index differs at position " + i
                        + ": source=" + source.index() + ", follow-up=" + followUp.index());
            }
            if (!Objects.equals(source.type(), followUp.type())) {
                throw new AssertionError("Pivot type differs at position " + i
                        + ": source=" + source.type() + ", follow-up=" + followUp.type());
            }
            assertTranslatedNum(source.price(), followUp.price(), "pivot price", i);
        }

        List<ElliottSwing> sourceSwings = sourceOutput.swings();
        List<ElliottSwing> followUpSwings = followUpOutput.swings();
        if (sourceSwings.size() != followUpSwings.size()) {
            throw new AssertionError("Swing counts differ: source="
                    + sourceSwings.size() + ", follow-up=" + followUpSwings.size());
        }

        for (int i = 0; i < sourceSwings.size(); i++) {
            ElliottSwing source = sourceSwings.get(i);
            ElliottSwing followUp = followUpSwings.get(i);

            if (source.fromIndex() != followUp.fromIndex()) {
                throw new AssertionError("Swing start index differs at position " + i
                        + ": source=" + source.fromIndex()
                        + ", follow-up=" + followUp.fromIndex());
            }
            if (source.toIndex() != followUp.toIndex()) {
                throw new AssertionError("Swing end index differs at position " + i
                        + ": source=" + source.toIndex()
                        + ", follow-up=" + followUp.toIndex());
            }
            if (!Objects.equals(source.degree(), followUp.degree())) {
                throw new AssertionError("Swing degree differs at position " + i
                        + ": source=" + source.degree()
                        + ", follow-up=" + followUp.degree());
            }

            assertTranslatedNum(source.fromPrice(), followUp.fromPrice(), "swing start price", i);
            assertTranslatedNum(source.toPrice(), followUp.toPrice(), "swing end price", i);
        }
    }

    private static void assertTranslatedNum(
            Num source,
            Num followUp,
            String field,
            int position) {
        double expected = source.doubleValue() + TRANSLATION;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(
                ABSOLUTE_TOLERANCE,
                Math.max(Math.abs(expected), Math.abs(actual)) * RELATIVE_TOLERANCE);

        if (!Double.isFinite(expected)
                || !Double.isFinite(actual)
                || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Translated " + field + " differs at position " + position
                    + ": expected=" + expected
                    + ", actual=" + actual
                    + ", tolerance=" + tolerance);
        }
    }
}
