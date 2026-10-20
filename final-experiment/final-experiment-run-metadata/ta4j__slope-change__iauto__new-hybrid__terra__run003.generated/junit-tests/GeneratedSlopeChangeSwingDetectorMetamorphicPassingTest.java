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

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private void exercise(double[] closes, int index, ElliottDegree degree, SlopeChangeConfig configuration) {
        exerciseWithWicks(closes, closes, closes, index, degree, configuration);
    }

    private void exerciseWithWicks(double[] closes, double[] highs, double[] lows, int index, ElliottDegree degree, SlopeChangeConfig configuration) {
        BarSeries sourceSeries = series(closes, highs, lows);
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(configuration);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private SlopeChangeConfig config(int window, int confirmationBars, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeConfig(window, confirmationBars, 1, minSlopeChange, minAtrReversal);
    }

    private BarSeries series(double[] closes, double[] highs, double[] lows) {
        if (closes.length != highs.length || closes.length != lows.length) {
            throw new IllegalArgumentException("OHLC fixture arrays must have the same length");
        }
        BarSeries series = new BaseBarSeriesBuilder().withName("metamorphic-source").build();
        Instant base = Instant.parse("2020-01-01T09:00:00Z");
        Duration period = Duration.ofMinutes(1);
        for (int i = 0; i < closes.length; i++) {
            Num close = series.numFactory().numOf(closes[i]);
            Num high = series.numFactory().numOf(highs[i]);
            Num low = series.numFactory().numOf(lows[i]);
            Instant begin = base.plusSeconds(60L * i);
            Instant end = begin.plusSeconds(60L);
            series.addBar(new BaseBar(period, begin, end, close, high, low, close, series.numFactory().one(), close, 1L));
        }
        return series;
    }

    private void assertMetamorphicRelation(SwingDetectorResult sourceOutput, SwingDetectorResult followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");
        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();
        if (sourcePivots.size() != followUpPivots.size()) {
            throw new AssertionError("Pivot counts differ: source=" + sourcePivots.size() + ", follow-up=" + followUpPivots.size());
        }
        for (int i = 0; i < sourcePivots.size(); i++) {
            SwingPivot source = sourcePivots.get(i);
            SwingPivot followUp = followUpPivots.get(i);
            if (source.index() != followUp.index() || source.type() != followUp.type()) {
                throw new AssertionError("Corresponding pivot index or type differs at position " + i);
            }
            assertTranslated(source.price(), followUp.price(), "pivot price", i);
        }
        List<ElliottSwing> sourceSwings = sourceOutput.swings();
        List<ElliottSwing> followUpSwings = followUpOutput.swings();
        if (sourceSwings.size() != followUpSwings.size()) {
            throw new AssertionError("Swing counts differ: source=" + sourceSwings.size() + ", follow-up=" + followUpSwings.size());
        }
        for (int i = 0; i < sourceSwings.size(); i++) {
            ElliottSwing source = sourceSwings.get(i);
            ElliottSwing followUp = followUpSwings.get(i);
            if (source.fromIndex() != followUp.fromIndex() || source.toIndex() != followUp.toIndex() || !Objects.equals(source.degree(), followUp.degree())) {
                throw new AssertionError("Corresponding swing metadata differs at position " + i);
            }
            assertTranslated(source.fromPrice(), followUp.fromPrice(), "swing from price", i);
            assertTranslated(source.toPrice(), followUp.toPrice(), "swing to price", i);
        }
    }

    private void assertTranslated(Num source, Num followUp, String field, int position) {
        double expected = source.doubleValue() + 100.0;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(1.0e-9, Math.max(Math.abs(expected), Math.abs(actual)) * 1.0e-12);
        if (!Double.isFinite(expected) || !Double.isFinite(actual) || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Translated " + field + " differs at position " + position + ": expected " + expected + " +/- " + tolerance + ", actual " + actual);
        }
    }

    @Test
    void INSUFFICIENT_HISTORY_FOR_FIRST_CANDIDATE_variation1() {
        exercise(new double[] { 10, 11, 12 }, 100, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void INSUFFICIENT_HISTORY_FOR_FIRST_CANDIDATE_variation2() {
        exercise(new double[] { 20, 20, 20, 20 }, 4, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void INSUFFICIENT_HISTORY_FOR_FIRST_CANDIDATE_variation3() {
        exercise(new double[] { 8 }, 10, ElliottDegree.PRIMARY, config(3, 2, 0.0, 0.0));
    }

    @Test
    void EXACT_MINIMUM_HISTORY_ONE_CANDIDATE_variation1() {
        exercise(new double[] { 1, 2, 3, 2, 1, 0 }, 5, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void EXACT_MINIMUM_HISTORY_ONE_CANDIDATE_variation2() {
        exercise(new double[] { 6, 5, 4, 5, 6, 7 }, 5, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void EXACT_MINIMUM_HISTORY_ONE_CANDIDATE_variation3() {
        exercise(new double[] { 10, 20, 30, 20, 10, 5 }, 5, ElliottDegree.PRIMARY, config(3, 1, 0.1, 0.0));
    }

    @Test
    void SLOPE_CHANGE_BELOW_MINIMUM_variation1() {
        exercise(new double[] { 10, 10, 10, 10, 10, 10, 10 }, 6, ElliottDegree.MINOR, config(3, 1, 1.0, 0.0));
    }

    @Test
    void SLOPE_CHANGE_BELOW_MINIMUM_variation2() {
        exercise(new double[] { 30, 31, 32, 33, 34, 35, 36 }, 6, ElliottDegree.INTERMEDIATE, config(3, 1, 5.0, 0.0));
    }

    @Test
    void QUALIFYING_MAGNITUDE_WITHOUT_DIRECTION_REVERSAL_variation1() {
        exercise(new double[] { 1, 2, 3, 6, 10, 15, 21 }, 6, ElliottDegree.PRIMARY, config(3, 1, 0.1, 0.0));
    }

    @Test
    void QUALIFYING_MAGNITUDE_WITHOUT_DIRECTION_REVERSAL_variation2() {
        exercise(new double[] { 30, 25, 21, 18, 16, 15, 14 }, 6, ElliottDegree.MINOR, config(3, 1, 0.1, 0.0));
    }

    @Test
    void HIGH_REVERSAL_REJECTED_BY_PERSISTENCE_variation1() {
        exercise(new double[] { 1, 2, 3, 2, 3, 4, 5 }, 6, ElliottDegree.INTERMEDIATE, config(3, 2, 0.0, 0.0));
    }

    @Test
    void HIGH_REVERSAL_REJECTED_BY_PERSISTENCE_variation2() {
        exercise(new double[] { 5, 7, 9, 7, 8, 10, 11, 12 }, 7, ElliottDegree.PRIMARY, config(3, 2, 0.0, 0.0));
    }

    @Test
    void HIGH_REVERSAL_REJECTED_BY_PERSISTENCE_variation3() {
        exercise(new double[] { 10, 12, 14, 11, 12, 14, 16 }, 6, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void LOW_REVERSAL_REJECTED_BY_PERSISTENCE_variation1() {
        exercise(new double[] { 5, 4, 3, 4, 3, 2, 1 }, 6, ElliottDegree.INTERMEDIATE, config(3, 2, 0.0, 0.0));
    }

    @Test
    void LOW_REVERSAL_REJECTED_BY_PERSISTENCE_variation2() {
        exercise(new double[] { 20, 18, 16, 18, 17, 15, 13, 12 }, 7, ElliottDegree.PRIMARY, config(3, 2, 0.0, 0.0));
    }

    @Test
    void LOW_REVERSAL_REJECTED_BY_PERSISTENCE_variation3() {
        exercise(new double[] { 12, 10, 8, 11, 10, 8, 7 }, 6, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void FIRST_CONFIRMED_HIGH_PIVOT_variation1() {
        exercise(new double[] { 2, 4, 6, 4, 2, 1 }, 5, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void FIRST_CONFIRMED_HIGH_PIVOT_variation2() {
        exercise(new double[] { 10, 15, 20, 15, 10, 5 }, 5, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void FIRST_CONFIRMED_HIGH_PIVOT_variation3() {
        exerciseWithWicks(new double[] { 100, 103, 107, 104, 101, 99 }, new double[] { 100, 103, 107, 120, 101, 99 }, new double[] { 100, 103, 107, 104, 101, 99 }, 5, ElliottDegree.PRIMARY, config(3, 1, 0.0, 0.0));
    }

    @Test
    void FIRST_CONFIRMED_LOW_PIVOT_variation1() {
        exercise(new double[] { 6, 4, 2, 4, 6, 8 }, 5, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void FIRST_CONFIRMED_LOW_PIVOT_variation2() {
        exercise(new double[] { 30, 20, 10, 20, 30, 40 }, 5, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void FIRST_CONFIRMED_LOW_PIVOT_variation3() {
        exerciseWithWicks(new double[] { 105, 102, 99, 102, 105, 110 }, new double[] { 105, 102, 99, 102, 105, 110 }, new double[] { 105, 102, 80, 102, 105, 110 }, 5, ElliottDegree.PRIMARY, config(3, 1, 0.0, 0.0));
    }

    @Test
    void ALTERNATING_HIGH_LOW_PIVOTS_CREATE_SWING_variation1() {
        exercise(new double[] { 1, 2, 3, 2, 1, 0, 1, 2, 3, 2, 1, 0 }, 11, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void ALTERNATING_HIGH_LOW_PIVOTS_CREATE_SWING_variation2() {
        exercise(new double[] { 5, 7, 9, 7, 5, 3, 5, 7, 9, 7, 5, 3 }, 11, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void ALTERNATING_HIGH_LOW_PIVOTS_CREATE_SWING_variation3() {
        exercise(new double[] { 20, 25, 30, 25, 20, 15, 20, 25, 30, 25, 20, 15 }, 11, ElliottDegree.PRIMARY, config(3, 1, 0.0, 0.0));
    }

    @Test
    void MORE_EXTREME_SECOND_HIGH_REPLACES_PREVIOUS_HIGH_variation1() {
        exercise(new double[] { 1, 3, 5, 3, 2, 3, 6, 4, 2, 1 }, 9, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void MORE_EXTREME_SECOND_HIGH_REPLACES_PREVIOUS_HIGH_variation2() {
        exercise(new double[] { 10, 12, 14, 12, 11, 13, 17, 14, 11, 9 }, 9, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void MORE_EXTREME_SECOND_HIGH_REPLACES_PREVIOUS_HIGH_variation3() {
        exercise(new double[] { 40, 45, 50, 46, 43, 48, 55, 50, 44, 40 }, 9, ElliottDegree.PRIMARY, config(3, 1, 0.0, 0.0));
    }

    @Test
    void MORE_EXTREME_SECOND_LOW_REPLACES_PREVIOUS_LOW_variation1() {
        exercise(new double[] { 6, 4, 2, 4, 5, 3, 1, 3, 5, 6 }, 9, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void MORE_EXTREME_SECOND_LOW_REPLACES_PREVIOUS_LOW_variation2() {
        exercise(new double[] { 20, 16, 12, 16, 18, 14, 8, 13, 17, 20 }, 9, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void MORE_EXTREME_SECOND_LOW_REPLACES_PREVIOUS_LOW_variation3() {
        exercise(new double[] { 50, 45, 40, 44, 47, 41, 35, 40, 45, 50 }, 9, ElliottDegree.PRIMARY, config(3, 1, 0.0, 0.0));
    }

    @Test
    void ATR_MAGNITUDE_FILTER_REJECTS_SECOND_PIVOT_variation1() {
        exercise(new double[] { 1, 3, 5, 3, 1, 2, 3, 2, 1, 0 }, 9, ElliottDegree.MINOR, config(3, 1, 0.0, 100.0));
    }

    @Test
    void ATR_MAGNITUDE_FILTER_REJECTS_SECOND_PIVOT_variation2() {
        exercise(new double[] { 10, 14, 18, 14, 10, 11, 12, 11, 10, 9 }, 9, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 50.0));
    }

    @Test
    void ATR_MAGNITUDE_FILTER_REJECTS_SECOND_PIVOT_variation3() {
        exercise(new double[] { 30, 35, 40, 35, 30, 31, 32, 31, 30, 29 }, 9, ElliottDegree.PRIMARY, config(3, 1, 0.0, 25.0));
    }

    @Test
    void HIGH_EXTREME_TIE_USES_EARLIEST_HIGH_variation1() {
        exercise(new double[] { 1, 3, 3, 2, 1, 0 }, 5, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void HIGH_EXTREME_TIE_USES_EARLIEST_HIGH_variation2() {
        exercise(new double[] { 10, 15, 15, 12, 9, 6 }, 5, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void HIGH_EXTREME_TIE_USES_EARLIEST_HIGH_variation3() {
        exercise(new double[] { 50, 60, 60, 55, 50, 45 }, 5, ElliottDegree.PRIMARY, config(3, 1, 0.0, 0.0));
    }

    @Test
    void LOW_EXTREME_TIE_USES_EARLIEST_LOW_variation1() {
        exercise(new double[] { 3, 1, 1, 2, 3, 4 }, 5, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void LOW_EXTREME_TIE_USES_EARLIEST_LOW_variation2() {
        exercise(new double[] { 20, 15, 15, 18, 21, 24 }, 5, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void LOW_EXTREME_TIE_USES_EARLIEST_LOW_variation3() {
        exercise(new double[] { 60, 50, 50, 55, 60, 65 }, 5, ElliottDegree.PRIMARY, config(3, 1, 0.0, 0.0));
    }

    @Test
    void INDEX_BELOW_BEGIN_IS_CLAMPED_variation1() {
        exercise(new double[] { 1, 2, 3, 2, 1, 0, 1, 2 }, -1, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void INDEX_BELOW_BEGIN_IS_CLAMPED_variation2() {
        exercise(new double[] { 10, 12, 14, 12, 10, 8, 10, 12 }, -100, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void INDEX_ABOVE_END_IS_CLAMPED_variation1() {
        exercise(new double[] { 1, 2, 3, 2, 1, 0 }, 100, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void INDEX_ABOVE_END_IS_CLAMPED_variation2() {
        exercise(new double[] { 6, 5, 4, 5, 6, 7 }, 99, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void INTERIOR_INDEX_EXCLUDES_LATER_REVERSAL_variation1() {
        exercise(new double[] { 1, 2, 3, 2, 1, 0, 1, 2, 3, 2, 1, 0 }, 5, ElliottDegree.MINOR, config(3, 1, 0.0, 0.0));
    }

    @Test
    void INTERIOR_INDEX_EXCLUDES_LATER_REVERSAL_variation2() {
        exercise(new double[] { 10, 15, 20, 15, 10, 5, 10, 15, 20, 15, 10, 5 }, 5, ElliottDegree.INTERMEDIATE, config(3, 1, 0.0, 0.0));
    }

    @Test
    void INTERIOR_INDEX_EXCLUDES_LATER_REVERSAL_variation3() {
        exercise(new double[] { 30, 25, 20, 25, 30, 35, 30, 25, 20, 25, 30, 35 }, 5, ElliottDegree.PRIMARY, config(3, 1, 0.0, 0.0));
    }
}
