import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static void exercise(SlopeChangeSwingDetector detector, BarSeries source, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(source, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, source, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], (Integer) followUp[2], (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static ElliottDegree degree(int offset) {
        ElliottDegree[] degrees = ElliottDegree.values();
        return degrees[Math.floorMod(offset, degrees.length)];
    }

    private static BarSeries series(String name, double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 1.0;
            lows[i] = closes[i] - 1.0;
        }
        return customSeries(name, closes, highs, lows);
    }

    private static BarSeries customSeries(String name, double[] closes, double[] highs, double[] lows) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant start = Instant.parse("2020-01-02T09:00:00Z");
        Duration period = Duration.ofMinutes(1);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = start.plusSeconds(60L * i);
            Instant end = begin.plus(period);
            result.addBar(new BaseBar(period, begin, end, result.numFactory().numOf(closes[i]), result.numFactory().numOf(highs[i]), result.numFactory().numOf(lows[i]), result.numFactory().numOf(closes[i]), result.numFactory().numOf(1.0), result.numFactory().numOf(1.0), 1L));
        }
        return result;
    }

    @Test
    public void SINGLE_BAR_NO_CANDIDATE_variation1() {
        exercise(new SlopeChangeSwingDetector(2), series("single", 10.0), 0, degree(1));
    }

    @Test
    public void JUST_BELOW_FIRST_CANDIDATE_LENGTH_variation1() {
        exercise(new SlopeChangeSwingDetector(2), series("below", 1.0, 2.0, 3.0, 2.0, 1.0), 2, degree(2));
    }

    @Test
    public void EXACTLY_ONE_CANDIDATE_WEAK_SLOPE_CHANGE_variation1() {
        BarSeries source = series("weak", 10.0, 10.01, 10.02, 10.03, 10.04, 10.05, 10.06);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(3));
    }

    @Test
    public void EXACTLY_ONE_CANDIDATE_SAME_DIRECTION_variation1() {
        exercise(new SlopeChangeSwingDetector(2), series("same-direction", 1.0, 3.0, 6.0, 10.0, 15.0, 21.0, 28.0), 100, degree(4));
    }

    @Test
    public void HIGH_TRANSITION_PERSISTENCE_FAILURE_variation1() {
        BarSeries source = series("high-persistence-failure", 0.0, 2.0, 4.0, 6.0, 4.0, 6.0, 8.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(5));
    }

    @Test
    public void LOW_TRANSITION_PERSISTENCE_FAILURE_variation1() {
        BarSeries source = series("low-persistence-failure", 8.0, 6.0, 4.0, 2.0, 4.0, 2.0, 0.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(6));
    }

    @Test
    public void CONFIRMED_INITIAL_HIGH_variation1() {
        BarSeries source = series("initial-high", 0.0, 2.0, 4.0, 6.0, 4.0, 2.0, 0.0, -2.0, -4.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(7));
    }

    @Test
    public void CONFIRMED_INITIAL_LOW_variation1() {
        BarSeries source = series("initial-low", 8.0, 6.0, 4.0, 2.0, 4.0, 6.0, 8.0, 10.0, 12.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(8));
    }

    @Test
    public void HIGH_EXTREME_TIE_USES_FIRST_INDEX_variation1() {
        BarSeries source = customSeries("high-tie", new double[] { 0.0, 2.0, 4.0, 6.0, 4.0, 2.0, 0.0, -2.0, -4.0 }, new double[] { 1.0, 3.0, 9.0, 9.0, 5.0, 3.0, 1.0, -1.0, -3.0 }, new double[] { -1.0, 1.0, 3.0, 5.0, 3.0, 1.0, -1.0, -3.0, -5.0 });
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex() + 9, degree(9));
    }

    @Test
    public void LOW_EXTREME_TIE_USES_FIRST_INDEX_variation1() {
        BarSeries source = customSeries("low-tie", new double[] { 8.0, 6.0, 4.0, 2.0, 4.0, 6.0, 8.0, 10.0, 12.0 }, new double[] { 9.0, 7.0, 5.0, 3.0, 5.0, 7.0, 9.0, 11.0, 13.0 }, new double[] { 7.0, 5.0, -1.0, -1.0, 3.0, 5.0, 7.0, 9.0, 11.0 });
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(10));
    }

    @Test
    public void ALTERNATING_CONFIRMED_PIVOTS_APPEND_variation1() {
        BarSeries source = series("alternating", 0.0, 2.0, 4.0, 6.0, 4.0, 2.0, 0.0, -2.0, -4.0, -2.0, 0.0, 2.0, 4.0, 2.0, 0.0, -2.0, -4.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(11));
    }

    @Test
    public void LATER_REVERSAL_REJECTED_BY_FINITE_ATR_variation1() {
        BarSeries source = series("finite-atr", 100.0, 104.0, 108.0, 112.0, 108.0, 104.0, 100.0, 98.0, 100.0, 102.0, 100.0, 98.0, 96.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(12));
    }

    @Test
    public void LATER_REVERSAL_REJECTED_BY_NONFINITE_ATR_variation1() {
        BarSeries source = series("nonfinite-atr", 0.0, 3.0, 6.0, 9.0, 6.0, 3.0, 0.0, -3.0, 0.0, 3.0, 0.0, -3.0, -6.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(13));
    }

    @Test
    public void STRONGER_LATER_HIGH_REPLACES_PRIOR_HIGH_variation1() {
        BarSeries source = series("stronger-high", 0.0, 2.0, 4.0, 6.0, 4.0, 2.0, 0.0, -2.0, 2.0, 6.0, 10.0, 14.0, 10.0, 6.0, 2.0, -2.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex() + 50, degree(14));
    }

    @Test
    public void WEAKER_LATER_HIGH_DOES_NOT_REPLACE_variation1() {
        BarSeries source = series("weaker-high", 0.0, 3.0, 6.0, 9.0, 6.0, 3.0, 0.0, -3.0, 0.0, 2.0, 4.0, 5.0, 3.0, 1.0, -1.0, -3.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(15));
    }

    @Test
    public void LOWER_LATER_LOW_REPLACES_PRIOR_LOW_variation1() {
        BarSeries source = series("lower-low", 10.0, 7.0, 4.0, 1.0, 4.0, 7.0, 10.0, 7.0, 4.0, 1.0, -2.0, 1.0, 4.0, 7.0, 10.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(16));
    }

    @Test
    public void HIGHER_LATER_LOW_DOES_NOT_REPLACE_variation1() {
        BarSeries source = series("higher-low", 12.0, 9.0, 6.0, 3.0, 6.0, 9.0, 12.0, 9.0, 6.0, 5.0, 6.0, 9.0, 12.0, 15.0);
        exercise(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), degree(17));
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation1() {
        exercise(new SlopeChangeSwingDetector(2), series("below-begin", 2.0, 4.0, 6.0, 4.0, 2.0, 0.0), -100, degree(18));
    }

    @Test
    public void INDEX_ABOVE_END_CLAMPS_TO_END_variation1() {
        BarSeries source = series("above-end", 0.0, 2.0, 4.0, 6.0, 4.0, 2.0, 0.0, -2.0, -4.0);
        exercise(new SlopeChangeSwingDetector(2), source, Integer.MAX_VALUE, degree(19));
    }
}
