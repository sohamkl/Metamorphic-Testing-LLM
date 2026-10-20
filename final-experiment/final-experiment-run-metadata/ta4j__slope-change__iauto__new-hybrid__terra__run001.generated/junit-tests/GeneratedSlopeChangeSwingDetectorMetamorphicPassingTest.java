import java.time.Duration;
import java.time.Instant;
import java.util.List;

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

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static void exercise(SlopeChangeConfig config, BarSeries sourceSeries, int index) {
        SlopeChangeSwingDetector sourceDetector = new SlopeChangeSwingDetector(config);
        ElliottDegree degree = ElliottDegree.MINOR;
        SwingDetectorResult sourceOutput = sourceDetector.detect(sourceSeries, index, degree);

        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                sourceDetector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(
                followUpSeries, followUpIndex, followUpDegree);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeConfig config(
            int window,
            int confirmationBars,
            int atrPeriod,
            double minSlopeChange,
            double minAtrReversal) {
        return new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal);
    }

    private static BarSeries series(String name, double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 1.0;
            lows[i] = closes[i] - 1.0;
        }
        return series(name, closes, highs, lows);
    }

    private static BarSeries series(String name, double[] closes, double[] highs, double[] lows) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < closes.length; i++) {
            Num open = result.numFactory().numOf(closes[i]);
            Num high = result.numFactory().numOf(highs[i]);
            Num low = result.numFactory().numOf(lows[i]);
            Num close = result.numFactory().numOf(closes[i]);
            Num volume = result.numFactory().numOf(1.0);
            Num amount = result.numFactory().numOf(closes[i]);
            Instant begin = start.plusSeconds(60L * i);
            result.addBar(new BaseBar(
                    Duration.ofMinutes(1),
                    begin,
                    begin.plusSeconds(60),
                    open,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    0L));
        }
        return result;
    }

    private static void assertMetamorphicRelation(
            SwingDetectorResult sourceOutput,
            SwingDetectorResult followUpOutput) {
        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();
        if (sourcePivots.size() != followUpPivots.size()) {
            throw new AssertionError("Pivot counts differ: source=" + sourcePivots.size()
                    + ", follow-up=" + followUpPivots.size());
        }

        for (int i = 0; i < sourcePivots.size(); i++) {
            SwingPivot source = sourcePivots.get(i);
            SwingPivot followUp = followUpPivots.get(i);
            if (source.index() != followUp.index()) {
                throw new AssertionError("Pivot index differs at position " + i);
            }
            if (source.type() != followUp.type()) {
                throw new AssertionError("Pivot type differs at position " + i);
            }
            assertTranslated(source.price(), followUp.price(), "pivot price", i);
        }

        List<ElliottSwing> sourceSwings = sourceOutput.swings();
        List<ElliottSwing> followUpSwings = followUpOutput.swings();
        if (sourceSwings.size() != followUpSwings.size()) {
            throw new AssertionError("Swing counts differ: source=" + sourceSwings.size()
                    + ", follow-up=" + followUpSwings.size());
        }

        for (int i = 0; i < sourceSwings.size(); i++) {
            ElliottSwing source = sourceSwings.get(i);
            ElliottSwing followUp = followUpSwings.get(i);
            if (source.fromIndex() != followUp.fromIndex()) {
                throw new AssertionError("Swing start index differs at position " + i);
            }
            if (source.toIndex() != followUp.toIndex()) {
                throw new AssertionError("Swing end index differs at position " + i);
            }
            if (!source.degree().equals(followUp.degree())) {
                throw new AssertionError("Swing degree differs at position " + i);
            }
            assertTranslated(source.fromPrice(), followUp.fromPrice(), "swing from price", i);
            assertTranslated(source.toPrice(), followUp.toPrice(), "swing to price", i);
        }
    }

    private static void assertTranslated(Num source, Num followUp, String field, int position) {
        double expected = source.doubleValue() + 100.0;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(1.0e-9,
                Math.max(Math.abs(expected), Math.abs(actual)) * 1.0e-12);
        if (!Double.isFinite(expected) || !Double.isFinite(actual)
                || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Translated " + field + " differs at position " + position
                    + ": expected " + expected + " +/- " + tolerance + " but was " + actual);
        }
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0), series("below-one", 10.0), -1);
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation2() {
        exercise(config(3, 2, 2, 0.1, 0.5), series("below-many", 10.0, 11.0, 12.0), -20);
    }

    @Test
    void INDEX_AT_BEGIN_WITH_INSUFFICIENT_FUTURE_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0), series("begin-short", 10.0, 11.0), 0);
    }

    @Test
    void INDEX_AT_BEGIN_WITH_INSUFFICIENT_FUTURE_variation2() {
        exercise(config(4, 2, 2, 0.0, 0.5), series("begin-four", 10.0, 11.0, 12.0), 0);
    }

    @Test
    void SERIES_ONE_BAR_SHORT_OF_FIRST_CANDIDATE_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0), series("short-two", 1.0, 2.0, 3.0), 2);
    }

    @Test
    void SERIES_ONE_BAR_SHORT_OF_FIRST_CANDIDATE_variation2() {
        exercise(config(3, 2, 2, 0.0, 0.5), series("short-three", 1.0, 2.0, 3.0, 4.0, 5.0, 6.0), 5);
    }

    @Test
    void FIRST_CANDIDATE_EXACTLY_AVAILABLE_FLAT_CLOSES_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0), series("flat-two", 7.0, 7.0, 7.0, 7.0), 3);
    }

    @Test
    void FIRST_CANDIDATE_EXACTLY_AVAILABLE_FLAT_CLOSES_variation2() {
        exercise(config(3, 2, 2, 0.0, 0.0),
                series("flat-three", 19.0, 19.0, 19.0, 19.0, 19.0, 19.0, 19.0, 19.0), 7);
    }

    @Test
    void SLOPE_CHANGE_STRICTLY_BELOW_THRESHOLD_variation1() {
        exercise(config(2, 1, 1, 10.0, 0.0), series("threshold-small", 0.0, 1.0, 0.0, -1.0), 3);
    }

    @Test
    void SLOPE_CHANGE_STRICTLY_BELOW_THRESHOLD_variation2() {
        exercise(config(3, 1, 2, 5.0, 0.0),
                series("threshold-gradual", 1.0, 2.0, 3.0, 3.5, 3.0, 2.5, 2.0), 6);
    }

    @Test
    void EQUAL_OR_SAME_DIRECTION_SLOPES_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0), series("same-positive", 0.0, 1.0, 2.0, 3.0), 3);
    }

    @Test
    void EQUAL_OR_SAME_DIRECTION_SLOPES_variation2() {
        exercise(config(3, 1, 1, 0.0, 0.0), series("same-negative", 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0), 6);
    }

    @Test
    void HIGH_CONFIRMATION_FIRST_POST_WINDOW_FAILURE_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0), series("high-first-fail", 0.0, 3.0, 2.0, 2.0), 3);
    }

    @Test
    void HIGH_CONFIRMATION_FIRST_POST_WINDOW_FAILURE_variation2() {
        exercise(config(3, 1, 2, 0.0, 0.0),
                series("high-first-fail-three", 0.0, 1.0, 4.0, 3.0, 2.0, 2.0, 2.0), 6);
    }

    @Test
    void LOW_CONFIRMATION_LATER_POST_WINDOW_FAILURE_variation1() {
        exercise(config(2, 2, 1, 0.0, 0.0), series("low-later-fail", 2.0, 0.0, 1.0, 2.0, 2.0), 4);
    }

    @Test
    void LOW_CONFIRMATION_LATER_POST_WINDOW_FAILURE_variation2() {
        exercise(config(3, 2, 2, 0.0, 0.0),
                series("low-later-fail-three", 5.0, 3.0, 0.0, 1.0, 2.0, 3.0, 3.0, 3.0), 7);
    }

    @Test
    void CONFIRMED_INITIAL_HIGH_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0), series("initial-high", 0.0, 2.0, 1.0, 0.0), 3);
    }

    @Test
    void CONFIRMED_INITIAL_HIGH_variation2() {
        exercise(config(3, 2, 2, 0.0, 0.0),
                series("initial-high-three", 0.0, 1.0, 4.0, 3.0, 2.0, 1.0, 0.0, -1.0), 7);
    }

    @Test
    void CONFIRMED_INITIAL_LOW_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0), series("initial-low", 2.0, 0.0, 1.0, 2.0), 3);
    }

    @Test
    void CONFIRMED_INITIAL_LOW_variation2() {
        exercise(config(3, 2, 2, 0.0, 0.0),
                series("initial-low-three", 5.0, 4.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0), 7);
    }

    @Test
    void HIGH_EXTREME_TIE_SELECTS_EARLIEST_INDEX_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0),
                series("high-tie-two", new double[] {0.0, 2.0, 1.0, 0.0},
                        new double[] {1.0, 9.0, 9.0, 1.0},
                        new double[] {-1.0, 0.0, -1.0, -2.0}), 3);
    }

    @Test
    void HIGH_EXTREME_TIE_SELECTS_EARLIEST_INDEX_variation2() {
        exercise(config(3, 2, 2, 0.0, 0.0),
                series("high-tie-three", new double[] {0.0, 1.0, 4.0, 3.0, 2.0, 1.0, 0.0, -1.0},
                        new double[] {1.0, 2.0, 10.0, 10.0, 8.0, 3.0, 2.0, 1.0},
                        new double[] {-1.0, 0.0, 2.0, 1.0, 0.0, -1.0, -2.0, -3.0}), 7);
    }

    @Test
    void LOW_EXTREME_TIE_SELECTS_EARLIEST_INDEX_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0),
                series("low-tie-two", new double[] {2.0, 0.0, 1.0, 2.0},
                        new double[] {3.0, 1.0, 2.0, 3.0},
                        new double[] {1.0, -5.0, -5.0, 1.0}), 3);
    }

    @Test
    void LOW_EXTREME_TIE_SELECTS_EARLIEST_INDEX_variation2() {
        exercise(config(3, 2, 2, 0.0, 0.0),
                series("low-tie-three", new double[] {5.0, 4.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0},
                        new double[] {6.0, 5.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0},
                        new double[] {4.0, 3.0, -8.0, -8.0, 0.0, 1.0, 2.0, 3.0}), 7);
    }

    @Test
    void ALTERNATING_HIGH_TO_LOW_ADDS_SWING_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0),
                series("high-low-wave", 0.0, 3.0, 2.0, 0.0, -2.0, -1.0, 1.0, 3.0, 2.0, 0.0), 9);
    }

    @Test
    void ALTERNATING_HIGH_TO_LOW_ADDS_SWING_variation2() {
        exercise(config(3, 1, 2, 0.0, 0.0),
                series("high-low-wave-three", 0.0, 1.0, 4.0, 3.0, 1.0, -2.0, -3.0, -1.0, 2.0, 4.0, 3.0, 1.0), 11);
    }

    @Test
    void ALTERNATING_LOW_TO_HIGH_ADDS_SWING_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0),
                series("low-high-wave", 3.0, 0.0, 1.0, 3.0, 5.0, 4.0, 2.0, 0.0, 1.0, 3.0), 9);
    }

    @Test
    void ALTERNATING_LOW_TO_HIGH_ADDS_SWING_variation2() {
        exercise(config(3, 1, 2, 0.0, 0.0),
                series("low-high-wave-three", 5.0, 3.0, 0.0, 1.0, 3.0, 5.0, 6.0, 4.0, 1.0, 0.0, 2.0, 4.0), 11);
    }

    @Test
    void SAME_TYPE_HIGH_STRONGER_REPLACES_PREVIOUS_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0),
                series("higher-highs", 0.0, 2.0, 1.0, 0.0, 3.0, 5.0, 4.0, 2.0, 0.0), 8);
    }

    @Test
    void SAME_TYPE_HIGH_STRONGER_REPLACES_PREVIOUS_variation2() {
        exercise(config(3, 1, 2, 0.0, 0.0),
                series("higher-highs-three", 0.0, 1.0, 3.0, 2.0, 0.0, 2.0, 5.0, 4.0, 2.0, 0.0), 9);
    }

    @Test
    void SAME_TYPE_LOW_STRONGER_REPLACES_PREVIOUS_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0),
                series("lower-lows", 4.0, 2.0, 3.0, 4.0, 1.0, -1.0, 0.0, 2.0, 4.0), 8);
    }

    @Test
    void SAME_TYPE_LOW_STRONGER_REPLACES_PREVIOUS_variation2() {
        exercise(config(3, 1, 2, 0.0, 0.0),
                series("lower-lows-three", 6.0, 4.0, 1.0, 2.0, 4.0, 2.0, -2.0, -1.0, 1.0, 3.0), 9);
    }

    @Test
    void SAME_TYPE_HIGH_NOT_HIGHER_IS_IGNORED_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0),
                series("weaker-highs", 0.0, 5.0, 4.0, 1.0, 3.0, 4.0, 3.0, 1.0, 0.0), 8);
    }

    @Test
    void SAME_TYPE_HIGH_NOT_HIGHER_IS_IGNORED_variation2() {
        exercise(config(3, 1, 2, 0.0, 0.0),
                series("weaker-highs-three", 0.0, 2.0, 6.0, 4.0, 1.0, 2.0, 4.0, 3.0, 1.0, 0.0), 9);
    }

    @Test
    void SAME_TYPE_LOW_NOT_LOWER_IS_IGNORED_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0),
                series("weaker-lows", 5.0, 0.0, 1.0, 4.0, 2.0, 1.0, 2.0, 4.0, 5.0), 8);
    }

    @Test
    void SAME_TYPE_LOW_NOT_LOWER_IS_IGNORED_variation2() {
        exercise(config(3, 1, 2, 0.0, 0.0),
                series("weaker-lows-three", 6.0, 3.0, -1.0, 1.0, 4.0, 3.0, 0.0, 1.0, 3.0, 5.0), 9);
    }

    @Test
    void ATR_FILTER_REJECTS_WEAK_SECOND_PIVOT_variation1() {
        exercise(config(2, 1, 1, 0.0, 100.0),
                series("atr-reject-large", 0.0, 2.0, 1.0, 0.0, -1.0, 0.0, 1.0, 0.0), 7);
    }

    @Test
    void ATR_FILTER_REJECTS_WEAK_SECOND_PIVOT_variation2() {
        exercise(config(3, 1, 2, 0.0, 50.0),
                series("atr-reject-three", 0.0, 2.0, 4.0, 3.0, 1.0, 0.0, 1.0, 2.0, 1.0, 0.0), 9);
    }

    @Test
    void ATR_FILTER_ACCEPTS_SUFFICIENT_SECOND_PIVOT_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.1),
                series("atr-accept-large", 0.0, 10.0, 8.0, 4.0, -5.0, -3.0, 2.0, 6.0, 4.0), 8);
    }

    @Test
    void ATR_FILTER_ACCEPTS_SUFFICIENT_SECOND_PIVOT_variation2() {
        exercise(config(3, 1, 2, 0.0, 0.1),
                series("atr-accept-three", 0.0, 4.0, 10.0, 8.0, 2.0, -6.0, -4.0, 1.0, 7.0, 5.0), 9);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASSES_DISTANCE_CHECK_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0),
                series("zero-atr-wave", 0.0, 2.0, 1.0, 0.0, -1.0, 0.0, 1.0, 0.0), 7);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASSES_DISTANCE_CHECK_variation2() {
        exercise(config(3, 1, 2, 0.0, 0.0),
                series("zero-atr-three", 3.0, 5.0, 7.0, 5.0, 2.0, 0.0, 2.0, 4.0, 3.0, 1.0), 9);
    }

    @Test
    void INDEX_ABOVE_END_CLAMPS_TO_END_variation1() {
        exercise(config(2, 1, 1, 0.0, 0.0), series("above-end-high", 0.0, 2.0, 1.0, 0.0), 40);
    }

    @Test
    void INDEX_ABOVE_END_CLAMPS_TO_END_variation2() {
        exercise(config(3, 2, 2, 0.0, 0.0),
                series("above-end-low", 5.0, 4.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0), 80);
    }
}
