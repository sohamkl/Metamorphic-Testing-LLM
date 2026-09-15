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
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static void verify(SlopeChangeSwingDetector detector, BarSeries source, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(source, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, source, index, degree);
        SwingDetectorResult followUpOutput = ((SlopeChangeSwingDetector) followUp[0]).detect((BarSeries) followUp[1], ((Integer) followUp[2]).intValue(), (ElliottDegree) followUp[3]);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries series(String name, double... relativeCloses) {
        double[] highs = new double[relativeCloses.length];
        double[] lows = new double[relativeCloses.length];
        for (int i = 0; i < relativeCloses.length; i++) {
            highs[i] = relativeCloses[i] + 1.0;
            lows[i] = relativeCloses[i] - 1.0;
        }
        return seriesWithRanges(name, relativeCloses, highs, lows);
    }

    private static BarSeries seriesWithRanges(String name, double[] relativeCloses, double[] relativeHighs, double[] relativeLows) {
        BarSeries series = new BaseBarSeriesBuilder().withName(name).build();
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < relativeCloses.length; i++) {
            Num close = series.numFactory().numOf(100.0 + relativeCloses[i]);
            Num high = series.numFactory().numOf(100.0 + relativeHighs[i]);
            Num low = series.numFactory().numOf(100.0 + relativeLows[i]);
            Num open = series.numFactory().numOf(100.0 + (relativeHighs[i] + relativeLows[i]) / 2.0);
            Num volume = series.numFactory().numOf(1.0);
            Num amount = close;
            Instant begin = base.plusSeconds(i * 60L);
            Instant end = begin.plusSeconds(60L);
            series.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, open, high, low, close, volume, amount, 1L));
        }
        return series;
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("below-begin", 10, 11, 12, 13, 14, 15, 16), Integer.MIN_VALUE, ElliottDegree.MINUTE);
    }

    @Test
    public void INDEX_ABOVE_END_CLAMPS_TO_END_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("above-end", 0, 1, 2, 3, 2, 1, 0), Integer.MAX_VALUE, ElliottDegree.MINUETTE);
    }

    @Test
    public void INSUFFICIENT_HISTORY_FOR_CANDIDATE_variation1() {
        verify(detector(4, 2, 1, 0.0, 0.0), series("short-history", 5, 6, 7, 8, 9, 10, 11, 12), 7, ElliottDegree.MINOR);
    }

    @Test
    public void EXACT_MINIMUM_HORIZON_SINGLE_CANDIDATE_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("single-candidate", 0, 1, 2, 3, 2, 1, 0), 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void FLAT_CLOSES_SKIP_SLOPE_CHANGE_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("flat", 20, 20, 20, 20, 20, 20, 20, 20, 20), 8, ElliottDegree.CYCLE);
    }

    @Test
    public void SAME_SIGN_SLOPES_SKIP_DIRECTIONAL_PIVOT_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("accelerating-up", 0, 1, 2, 4, 7, 11, 16, 22), 7, ElliottDegree.PRIMARY);
    }

    @Test
    public void OPPOSITE_SLOPES_BELOW_MINIMUM_CHANGE_variation1() {
        verify(detector(3, 2, 1, 10.0, 0.0), series("weak-turn", 0, 1, 2, 3, 2, 1, 0), 6, ElliottDegree.MINOR);
    }

    @Test
    public void HIGH_DIRECTION_FAILS_FIRST_PERSISTENCE_SLOPE_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("high-persistence-failure", 0, 2, 4, 3, 2, 3, 5, 7), 7, ElliottDegree.MINOR);
    }

    @Test
    public void LOW_DIRECTION_FAILS_LATER_PERSISTENCE_SLOPE_variation1() {
        verify(detector(3, 3, 1, 0.0, 0.0), series("low-persistence-failure", 8, 6, 4, 5, 6, 7, 6, 4, 2), 8, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void FIRST_CONFIRMED_HIGH_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("first-high", 0, 2, 4, 6, 4, 2, 0), 6, ElliottDegree.MINOR);
    }

    @Test
    public void FIRST_CONFIRMED_LOW_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("first-low", 8, 6, 4, 2, 4, 6, 8), 6, ElliottDegree.MINUTE);
    }

    @Test
    public void HIGH_EXTREME_TIE_USES_EARLIEST_INDEX_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), seriesWithRanges("high-tie", new double[] { 0, 2, 4, 6, 4, 2, 0 }, new double[] { 1, 3, 10, 10, 5, 3, 1 }, new double[] { -1, 1, 3, 5, 3, 1, -1 }), 6, ElliottDegree.MINOR);
    }

    @Test
    public void LOW_EXTREME_TIE_USES_EARLIEST_INDEX_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), seriesWithRanges("low-tie", new double[] { 8, 6, 4, 2, 4, 6, 8 }, new double[] { 9, 7, 5, 3, 5, 7, 9 }, new double[] { 7, 5, -2, -2, 3, 5, 7 }), 6, ElliottDegree.MINUETTE);
    }

    @Test
    public void OPPOSITE_TYPE_PIVOTS_APPEND_AND_FORM_SWING_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("alternating", 0, 2, 4, 6, 4, 2, 0, 2, 4, 6, 4, 2, 0), 12, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ATR_FILTER_REJECTS_SECOND_PIVOT_variation1() {
        verify(detector(3, 2, 1, 0.0, 100.0), series("atr-reject", 0, 2, 4, 6, 4, 2, 3, 4, 3, 2, 1, 2, 3), 12, ElliottDegree.MINOR);
    }

    @Test
    public void SAME_HIGH_REPLACES_WITH_HIGHER_EXTREME_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("higher-high", 0, 2, 4, 6, 4, 2, 3, 5, 8, 10, 8, 6, 4), 12, ElliottDegree.PRIMARY);
    }

    @Test
    public void SAME_LOW_REPLACES_WITH_LOWER_EXTREME_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("lower-low", 10, 8, 6, 4, 6, 8, 7, 5, 2, 0, 2, 4, 6), 12, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void SAME_TYPE_WEAKER_PIVOT_IS_IGNORED_variation1() {
        verify(detector(3, 2, 1, 0.0, 0.0), series("weaker-high", 0, 3, 6, 9, 6, 3, 4, 5, 7, 8, 6, 4, 2), 12, ElliottDegree.CYCLE);
    }
}
