package mtllm.examples.ta4j;

import java.util.List;
import java.util.Objects;

import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.indicators.elliott.ElliottSwing;
import org.ta4j.core.num.Num;

/** Uniform price-translation relation for the Ta4j slope-change swing detector. */
public final class SlopeChangeSwingDetectorMetamorphicSpec {
    private static final double TRANSLATION = 100.0;
    private static final double ABSOLUTE_TOLERANCE = 1.0e-9;
    private static final double RELATIVE_TOLERANCE = 1.0e-12;

    private SlopeChangeSwingDetectorMetamorphicSpec() {
    }

    public static Object[] generateFollowUp(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {
        Objects.requireNonNull(detector, "detector");
        Objects.requireNonNull(sourceSeries, "sourceSeries");
        Objects.requireNonNull(degree, "degree");
        if (sourceSeries.getBeginIndex() != 0) {
            throw new IllegalArgumentException(
                    "Price-translation MR requires a source series whose begin index is zero");
        }

        BarSeries translatedSeries = new BaseBarSeriesBuilder()
                .withName(sourceSeries.getName() + "-translated")
                .withNumFactory(sourceSeries.numFactory())
                .build();
        Num translation = sourceSeries.numFactory().numOf(TRANSLATION);

        for (int barIndex = sourceSeries.getBeginIndex(); barIndex <= sourceSeries.getEndIndex(); barIndex++) {
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

        return new Object[]{
                new SlopeChangeSwingDetector(detector.getConfig()),
                translatedSeries,
                index,
                degree};
    }

    public static void assertRelation(SwingDetectorResult sourceOutput, SwingDetectorResult followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");

        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();
        assertSameSize("pivot", sourcePivots.size(), followUpPivots.size(),
                pivotSignature(sourcePivots), pivotSignature(followUpPivots));
        for (int index = 0; index < sourcePivots.size(); index++) {
            SwingPivot source = sourcePivots.get(index);
            SwingPivot followUp = followUpPivots.get(index);
            assertEqual(source.index(), followUp.index(), "pivot index", index);
            assertEqual(source.type(), followUp.type(), "pivot type", index);
            assertTranslated(source.price(), followUp.price(), "pivot price", index);
        }

        List<ElliottSwing> sourceSwings = sourceOutput.swings();
        List<ElliottSwing> followUpSwings = followUpOutput.swings();
        assertSameSize("swing", sourceSwings.size(), followUpSwings.size(),
                swingSignature(sourceSwings), swingSignature(followUpSwings));
        for (int index = 0; index < sourceSwings.size(); index++) {
            ElliottSwing source = sourceSwings.get(index);
            ElliottSwing followUp = followUpSwings.get(index);
            assertEqual(source.fromIndex(), followUp.fromIndex(), "swing start index", index);
            assertEqual(source.toIndex(), followUp.toIndex(), "swing end index", index);
            assertEqual(source.degree(), followUp.degree(), "swing degree", index);
            assertTranslated(source.fromPrice(), followUp.fromPrice(), "swing start price", index);
            assertTranslated(source.toPrice(), followUp.toPrice(), "swing end price", index);
        }
    }

    private static void assertSameSize(
            String subject, int sourceSize, int followUpSize, String sourceSignature, String followUpSignature) {
        if (sourceSize != followUpSize) {
            throw new AssertionError("Expected the same " + subject + " count, but source was "
                    + sourceSize + " " + sourceSignature + " and follow-up was "
                    + followUpSize + " " + followUpSignature);
        }
    }

    private static void assertTranslated(Num source, Num followUp, String field, int index) {
        double expected = source.doubleValue() + TRANSLATION;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(
                ABSOLUTE_TOLERANCE,
                Math.max(Math.abs(expected), Math.abs(actual)) * RELATIVE_TOLERANCE);
        if (!Double.isFinite(expected) || !Double.isFinite(actual)
                || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Expected translated " + field + " at position " + index
                    + " to be " + expected + " +/- " + tolerance + ", but was " + actual);
        }
    }

    private static void assertEqual(Object source, Object followUp, String field, int index) {
        if (!Objects.equals(source, followUp)) {
            throw new AssertionError("Expected equal " + field + " at position " + index
                    + ", but source was " + source + " and follow-up was " + followUp);
        }
    }

    private static String pivotSignature(List<SwingPivot> pivots) {
        return pivots.stream()
                .map(pivot -> pivot.type() + "@" + pivot.index())
                .toList()
                .toString();
    }

    private static String swingSignature(List<ElliottSwing> swings) {
        return swings.stream()
                .map(swing -> swing.fromIndex() + "->" + swing.toIndex())
                .toList()
                .toString();
    }
}
