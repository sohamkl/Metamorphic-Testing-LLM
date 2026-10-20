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

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static ElliottDegree degree(int offset) {
        ElliottDegree[] degrees = ElliottDegree.values();
        return ElliottDegree.valueOf(degrees[Math.floorMod(offset, degrees.length)].name());
    }

    private static BarSeries emptySeries(String name) {
        return new BaseBarSeriesBuilder().withName(name).build();
    }

    private static BarSeries alternatingSeries(String name) {
        return series(name, new double[] { 0.0, 2.0, 3.0, 1.0, 0.0, 2.0, 3.0, 1.0, 0.0, 2.0, 3.0, 1.0 });
    }

    private static BarSeries series(String name, double[] closes) {
        return series(name, closes, null, null);
    }

    private static BarSeries series(String name, double[] closes, double[] highs, double[] lows) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        for (int i = 0; i < closes.length; i++) {
            double closeValue = closes[i];
            double highValue = highs == null ? (Double.isFinite(closeValue) ? closeValue + 1.0 : Double.NaN) : highs[i];
            double lowValue = lows == null ? (Double.isFinite(closeValue) ? closeValue - 1.0 : Double.NaN) : lows[i];
            Num close = num(result, closeValue);
            Num high = num(result, highValue);
            Num low = num(result, lowValue);
            Num open = close;
            Num volume = result.numFactory().one();
            Num amount = result.numFactory().numOf(1000.0 + i);
            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);
            result.addBar(new BaseBar(BAR_DURATION, begin, end, open, high, low, close, volume, amount, 1L));
        }
        return result;
    }

    private static Num num(BarSeries series, double value) {
        return Double.isNaN(value) ? NaN.NaN : series.numFactory().numOf(value);
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMP_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("below-begin", new double[] { 10.0, 11.0, 10.0 });
        int index = -7;
        ElliottDegree degree = degree(1);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INDEX_ABOVE_END_CLAMP_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("above-end", new double[] { 0.0, 2.0, 1.0, 0.0 });
        int index = 1000;
        ElliottDegree degree = degree(2);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BAR_SHORT_OF_FIRST_CANDIDATE_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("one-short", new double[] { 0.0, 2.0, 1.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(3);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_FIRST_CANDIDATE_REACHABLE_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.5);
        BarSeries sourceSeries = series("exact-first", new double[] { 0.0, 2.0, 1.0, 0.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(4);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FLAT_FINITE_WINDOWS_variation1() {
        SlopeChangeSwingDetector detector = detector(3, 2, 3, 0.0, 0.0);
        BarSeries sourceSeries = series("flat", new double[] { 25.0, 25.0, 25.0, 25.0, 25.0, 25.0, 25.0, 25.0, 25.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(6);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SLOPE_CHANGE_BELOW_MINIMUM_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 3.0, 0.0);
        BarSeries sourceSeries = series("below-slope-minimum", new double[] { 10.0, 11.0, 10.5, 10.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(7);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SLOPE_CHANGE_EQUAL_MINIMUM_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 3.0, 0.5);
        BarSeries sourceSeries = series("equal-slope-minimum", new double[] { 0.0, 2.0, 1.0, 0.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(8);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SAME_SIGN_SLOPES_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("same-sign", new double[] { 0.0, 2.0, 10.0, 13.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(9);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_SIDE_OF_DIRECTION_CHANGE_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 1.0, 0.0);
        BarSeries sourceSeries = series("zero-side", new double[] { 4.0, 4.0, 8.0, 10.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(10);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONFIRMED_HIGH_PIVOT_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 2, 3, 0.5, 0.0);
        BarSeries sourceSeries = series("confirmed-high", new double[] { 0.0, 2.0, 3.0, 1.0, 0.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(11);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONFIRMED_LOW_PIVOT_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 2, 3, 0.5, 0.0);
        BarSeries sourceSeries = series("confirmed-low", new double[] { 4.0, 2.0, 1.0, 3.0, 4.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(12);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FIRST_CONFIRMATION_SLOPE_FAILS_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("first-confirmation-failure", new double[] { 0.0, 2.0, 2.0, 2.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(13);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LATER_CONFIRMATION_SLOPE_FAILS_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 2, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("later-confirmation-failure", new double[] { 0.0, 2.0, 5.0, 4.0, 6.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(14);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONFINITE_ADJACENT_SLOPE_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("nan-adjacent", new double[] { 0.0, 2.0, Double.NaN, 0.0 }, null, null);
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(15);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONFINITE_CONFIRMATION_SLOPE_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 2, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("nan-confirmation", new double[] { 0.0, 2.0, 5.0, 4.0, Double.NaN }, null, null);
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(16);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONFINITE_EXTREME_ABORT_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("nan-extreme", new double[] { 0.0, 2.0, 1.0, 0.0 }, new double[] { 1.0, 3.0, Double.NaN, 1.0 }, new double[] { -1.0, 1.0, 0.0, -1.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(17);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TIED_EXTREME_KEEPS_EARLIEST_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("tied-extreme", new double[] { 0.0, 2.0, 1.0, 0.0 }, new double[] { 1.0, 5.0, 5.0, 1.0 }, new double[] { -1.0, 1.0, 0.0, -1.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(18);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LATER_STRICT_EXTREME_REPLACES_INITIAL_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("later-strict-extreme", new double[] { 0.0, 2.0, 1.0, 0.0 }, new double[] { 1.0, 3.0, 6.0, 1.0 }, new double[] { -1.0, 1.0, 0.0, -1.0 });
        int index = 100;
        ElliottDegree degree = degree(19);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 3, 0.5, 1000.0);
        BarSeries sourceSeries = series("first-bypass", new double[] { 4.0, 2.0, 1.0, 3.0 }, new double[] { 5.0, Double.NaN, 2.0, 4.0 }, new double[] { 3.0, 1.0, 0.0, 2.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(20);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_MULTIPLIER_BYPASSES_WEAK_REVERSAL_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 3, 0.5, 0.0);
        BarSeries sourceSeries = alternatingSeries("zero-multiplier-weak");
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(21);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_MULTIPLIER_BYPASSES_NONFINITE_ATR_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("zero-multiplier-nan-atr", new double[] { 0.0, 2.0, 3.0, 1.0, 0.0, 2.0, 3.0 }, new double[] { 1.0, 3.0, 4.0, Double.NaN, 1.0, 3.0, 4.0 }, new double[] { -1.0, 1.0, 2.0, 0.0, -1.0, 1.0, 2.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(22);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 1.0);
        BarSeries sourceSeries = series("nonfinite-atr-reject", new double[] { 0.0, 2.0, 3.0, 1.0, 0.0, 2.0, 3.0 }, new double[] { 1.0, 3.0, 4.0, Double.NaN, 1.0, 3.0, 4.0 }, new double[] { -1.0, 1.0, 2.0, 0.0, -1.0, 1.0, 2.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(23);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ATR_REVERSAL_BELOW_THRESHOLD_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 1, 0.5, 3.0);
        BarSeries sourceSeries = alternatingSeries("atr-below");
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(24);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ATR_REVERSAL_EQUAL_THRESHOLD_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 1, 0.5, 2.5);
        BarSeries sourceSeries = alternatingSeries("atr-equal");
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(25);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ATR_REVERSAL_ABOVE_THRESHOLD_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 1, 0.5, 2.0);
        BarSeries sourceSeries = alternatingSeries("atr-above");
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(26);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OPPOSITE_TYPE_PIVOT_APPEND_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 1, 0.5, 1.0);
        BarSeries sourceSeries = alternatingSeries("opposite-append");
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(27);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REPEATED_HIGH_REPLACES_LOWER_HIGH_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("replace-high", new double[] { 0.0, 2.0, 3.0, 1.0, 0.0 }, new double[] { 1.0, 3.0, 4.0, 10.0, 1.0 }, new double[] { -1.0, 1.0, 2.0, 0.0, -1.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(28);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REPEATED_HIGH_RETAINS_PREVIOUS_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("retain-high", new double[] { 0.0, 2.0, 3.0, 1.0, 0.0 }, new double[] { 1.0, 8.0, 7.0, 6.0, 1.0 }, new double[] { -1.0, 1.0, 2.0, 0.0, -1.0 });
        int index = 100;
        ElliottDegree degree = degree(29);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REPEATED_LOW_REPLACES_HIGHER_LOW_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("replace-low", new double[] { 4.0, 2.0, 1.0, 3.0, 4.0 }, new double[] { 5.0, 3.0, 2.0, 4.0, 5.0 }, new double[] { 3.0, 1.0, 0.0, -6.0, 3.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(30);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REPEATED_LOW_RETAINS_PREVIOUS_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 2, 0.5, 0.0);
        BarSeries sourceSeries = series("retain-low", new double[] { 4.0, 2.0, 1.0, 3.0, 4.0 }, new double[] { 5.0, 3.0, 2.0, 4.0, 5.0 }, new double[] { 3.0, -5.0, -4.0, -3.0, 3.0 });
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(31);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTIPLE_ALTERNATING_PIVOTS_AND_SWINGS_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 1, 0.5, 1.0);
        BarSeries sourceSeries = alternatingSeries("multiple-alternating");
        int index = sourceSeries.getEndIndex();
        ElliottDegree degree = degree(32);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CAUSAL_INDEX_TRUNCATION_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 1, 0.5, 1.0);
        BarSeries sourceSeries = alternatingSeries("causal-truncation");
        int index = 5;
        ElliottDegree degree = degree(33);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEGREE_PROPAGATION_WITH_NONEMPTY_SWINGS_variation1() {
        SlopeChangeSwingDetector detector = detector(2, 1, 1, 0.5, 1.0);
        BarSeries sourceSeries = alternatingSeries("degree-propagation");
        int index = sourceSeries.getEndIndex() + 20;
        ElliottDegree degree = degree(34);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
