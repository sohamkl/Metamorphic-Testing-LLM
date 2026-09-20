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

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

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

    @Test
    public void EMPTY_ZERO_BASED_SERIES_variation1() {
        verify(config(3, 2, 3, 0.0, 0.5), emptyZeroBasedSeries(),
                Integer.MIN_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void INSUFFICIENT_PREFIX_BOUNDARIES_variation1_singleBar() {
        verify(config(3, 2, 3, 2.0, 0.0), series(new double[] { 10.0 }, 0, 0),
                -7, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void INSUFFICIENT_PREFIX_BOUNDARIES_variation2_immediatePredecessor() {
        verify(config(3, 2, 3, 0.0, 0.5),
                series(new double[] { 10.25, 11.25, 12.25, 12.25, 11.25, 10.25 }, 0, 0),
                5, ElliottDegree.MINOR);
    }

    @Test
    public void EXACT_FIRST_CANDIDATE_BOUNDARY_variation1_belowThreshold() {
        verify(config(3, 2, 3, 3.0, 0.5),
                series(new double[] {
                        1_000_000_000.0, 1_000_000_001.0, 1_000_000_002.0,
                        1_000_000_003.0, 1_000_000_002.0, 1_000_000_001.0,
                        1_000_000_000.0
                }, 2, 0),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void LOWER_INDEX_CLAMPING_variation1_minimumInteger() {
        verify(config(3, 2, 3, 0.0, 0.5), highSeries(),
                Integer.MIN_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void LOWER_INDEX_CLAMPING_variation2_zero() {
        verify(config(3, 2, 3, 0.0, 0.5), heterogeneousSeries(),
                0, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void COMPLETE_SERIES_AND_UPPER_CLAMP_variation1_exactEnd() {
        BarSeries source = highSeries();
        verify(config(3, 2, 3, 0.0, 0.5), source,
                source.getEndIndex(), ElliottDegree.MINOR);
    }

    @Test
    public void COMPLETE_SERIES_AND_UPPER_CLAMP_variation2_maximumIntegerLargePrices() {
        verify(config(3, 2, 3, 0.0, 0.5),
                series(shift(new double[] { 1, 2, 3, 4, 3, 2, 1 },
                        1_000_000_000.0), 3, 0),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void INTERNAL_INDEX_PREFIX_ONLY_variation1_laterBarsRemain() {
        verify(config(3, 2, 3, 0.0, 0.0), zigzagSeries(),
                8, ElliottDegree.MINOR);
    }

    @Test
    public void FLAT_CLOSE_WINDOWS_variation1_constantCloses() {
        verify(config(3, 2, 3, 0.0, 0.5),
                series(new double[] { 20, 20, 20, 20, 20, 20, 20, 20, 20 }, 0, 0),
                8, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void SAME_SIGN_SLOPES_variation1_risingWindows() {
        verify(config(3, 2, 3, 0.0, 0.5),
                series(new double[] { 1, 2, 3, 5, 8, 12, 17 }, 0, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void ZERO_SLOPE_ON_ONE_SIDE_variation1_flatBeforeFallingAfter() {
        verify(config(3, 2, 3, 0.5, 0.5),
                series(new double[] { 5, 5, 5, 4, 3, 2, 1 }, 0, 0),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void REVERSAL_BELOW_SLOPE_THRESHOLD_variation1_weakStrictReversal() {
        verify(config(3, 2, 3, 2.1, 0.5),
                series(new double[] { 10, 11, 12, 13, 12, 11, 10 }, 0, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void REVERSAL_EXACTLY_AT_SLOPE_THRESHOLD_variation1_high() {
        verify(config(3, 2, 3, 2.0, 0.5), highSeries(),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void REVERSAL_EXACTLY_AT_SLOPE_THRESHOLD_variation2_low() {
        verify(config(3, 2, 3, 2.0, 0.5), lowSeries(),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void CONFIRMED_HIGH_REVERSAL_variation1_zeroVolume() {
        verify(config(3, 2, 3, 0.5, 0.5),
                series(new double[] { 10, 12, 14, 16, 14, 12, 10 },
                        3, 0, true, false),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void CONFIRMED_LOW_REVERSAL_variation1_heterogeneousAmounts() {
        verify(config(3, 2, 3, 0.5, 0.5),
                series(new double[] { 16, 14, 12, 10, 12, 14, 16 },
                        0, 3, false, true),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void PERSISTENCE_FAILS_FIRST_CONFIRMATION_variation1_highSentinel() {
        verify(config(3, 2, 3, 0.0, 0.5),
                series(new double[] { 1, 2, 3, 4, Double.NaN, 2, 3 }, 0, 0),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void PERSISTENCE_FAILS_FIRST_CONFIRMATION_variation2_lowSentinel() {
        verify(config(3, 2, 3, 0.0, 0.5),
                series(new double[] { 5, 4, 3, 2, Double.NaN, 4, 3 }, 0, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void PERSISTENCE_FAILS_LATER_CONFIRMATION_variation1_high() {
        verify(config(3, 2, 3, 0.0, 0.5),
                series(new double[] { 1, 2, 3, 4, 3, 2, 5 }, 0, 0),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void PERSISTENCE_FAILS_LATER_CONFIRMATION_variation2_low() {
        verify(config(3, 2, 3, 0.0, 0.5),
                series(new double[] { 5, 4, 3, 2, 3, 4, 1 }, 0, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void NON_FINITE_REGRESSION_VALUE_variation1_nanClose() {
        verify(config(3, 2, 3, 0.0, 0.5),
                series(new double[] { 1, Double.NaN, 3, 4, 3, 2, 1 }, 0, 0),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void HIGH_EXTREME_LOCATIONS_variation1_transitionStart() {
        verify(config(3, 2, 3, 0.0, 0.5),
                customWickSeries(
                        new double[] { 1, 2, 3, 2.8, 2, 1, 0 },
                        new int[] { 2 }, new int[0], 20.0),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void HIGH_EXTREME_LOCATIONS_variation2_transitionInterior() {
        verify(config(3, 2, 3, 0.0, 0.5),
                customWickSeries(
                        new double[] { 1, 2, 3, 4, 3, 2, 1 },
                        new int[] { 3 }, new int[0], 20.0),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void HIGH_EXTREME_LOCATIONS_variation3_transitionEnd() {
        verify(config(3, 2, 3, 0.0, 0.5),
                customWickSeries(
                        new double[] { 1, 2, 3, 4, 3, 2, 1 },
                        new int[] { 4 }, new int[0], 20.0),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void LOW_EXTREME_LOCATIONS_variation1_transitionStart() {
        verify(config(3, 2, 3, 0.0, 0.5),
                customWickSeries(
                        new double[] { 5, 4, 3, 3.2, 4, 5, 6 },
                        new int[0], new int[] { 2 }, 20.0),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void LOW_EXTREME_LOCATIONS_variation2_transitionInterior() {
        verify(config(3, 2, 3, 0.0, 0.5),
                customWickSeries(
                        new double[] { 5, 4, 3, 2, 3, 4, 5 },
                        new int[0], new int[] { 3 }, 20.0),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void TIED_EXTREMES_KEEP_EARLIEST_variation1_highTie() {
        verify(config(3, 2, 3, 0.0, 0.5),
                tiedExtremeSeries(new double[] { 1, 2, 3, 4, 3, 2, 1 }, true),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void TIED_EXTREMES_KEEP_EARLIEST_variation2_lowTie() {
        verify(config(3, 2, 3, 0.0, 0.5),
                tiedExtremeSeries(new double[] { 5, 4, 3, 2, 3, 4, 5 }, false),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void WICK_PRICE_DETERMINES_PIVOT_variation1_highWick() {
        verify(config(3, 2, 3, 0.0, 0.5),
                customWickSeries(
                        new double[] { 1, 2, 3, 4, 3, 2, 1 },
                        new int[] { 3 }, new int[0], 50.0),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void WICK_PRICE_DETERMINES_PIVOT_variation2_lowWick() {
        verify(config(3, 2, 3, 0.0, 0.5),
                customWickSeries(
                        new double[] { 5, 4, 3, 2, 3, 4, 5 },
                        new int[0], new int[] { 3 }, 50.0),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void NON_FINITE_TRANSITION_EXTREME_variation1_nanHigh() {
        verify(config(3, 2, 3, 0.0, 0.5),
                seriesWithNonFiniteExtreme(
                        new double[] { 1, 2, 3, 4, 3, 2, 1 }, 3, true),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void NON_FINITE_TRANSITION_EXTREME_variation2_nanLow() {
        verify(config(3, 2, 3, 0.0, 0.5),
                seriesWithNonFiniteExtreme(
                        new double[] { 5, 4, 3, 2, 3, 4, 5 }, 3, false),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_FILTER_variation1_largeMultiplier() {
        verify(config(3, 2, 3, 0.0, 10_000.0), highSeries(),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ZERO_ATR_MULTIPLIER_BYPASS_variation1_alternatingCandidates() {
        verify(config(3, 2, 3, 0.0, 0.0), zigzagSeries(),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void REVERSAL_BELOW_ATR_THRESHOLD_variation1_smallDistance() {
        verify(config(3, 2, 3, 0.0, 100.0),
                series(new double[] {
                        10, 11, 12, 13, 12, 11, 10,
                        10.2, 10.4, 10.6, 10.4, 10.2, 10
                }, 3, 7),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void REVERSAL_AT_OR_ABOVE_ATR_THRESHOLD_variation1_equalityOrNearEquality() {
        verify(config(3, 2, 1, 0.0, 1.0),
                series(new double[] { 1, 2, 3, 4, 3, 2, 1, 2, 3, 4 }, 3, 6),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void REVERSAL_AT_OR_ABOVE_ATR_THRESHOLD_variation2_aboveThreshold() {
        verify(config(3, 2, 2, 0.0, 0.25),
                series(new double[] {
                        10, 14, 18, 22, 18, 14, 10,
                        14, 18, 22, 18, 14, 10
                }, 3, 6),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void NON_FINITE_ATR_REJECTS_SUBSEQUENT_PIVOT_variation1_nanTrueRange() {
        verify(config(3, 2, 2, 0.0, 1.0),
                seriesWithNonFiniteExtreme(
                        new double[] {
                                1, 2, 3, 4, 3, 2, 1,
                                2, 3, 4, 3, 2, 1
                        },
                        7, true),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void CONSECUTIVE_HIGH_REPLACEMENT_OUTCOMES_variation1_retained() {
        verify(config(3, 2, 1, 0.0, 0.0),
                series(new double[] {
                        1, 2, 3, 8, 6, 4, 2,
                        2.2, 2.4, 2.6, 2.4, 2.2, 2,
                        3, 4, 5, 4, 3, 2
                }, 3, 6),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void CONSECUTIVE_HIGH_REPLACEMENT_OUTCOMES_variation2_replaced() {
        verify(config(3, 2, 1, 0.0, 0.0),
                series(new double[] {
                        1, 2, 3, 5, 4, 3, 2,
                        2.2, 2.4, 2.6, 2.4, 2.2, 2,
                        5, 8, 11, 8, 5, 2
                }, 14, 6),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void CONSECUTIVE_LOW_REPLACEMENT_OUTCOMES_variation1_retained() {
        verify(config(3, 2, 1, 0.0, 0.0),
                series(new double[] {
                        9, 8, 7, 2, 4, 6, 8,
                        7.8, 7.6, 7.4, 7.6, 7.8, 8,
                        7, 6, 5, 6, 7, 8
                }, 9, 3),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void CONSECUTIVE_LOW_REPLACEMENT_OUTCOMES_variation2_replaced() {
        verify(config(3, 2, 1, 0.0, 0.0),
                series(new double[] {
                        9, 8, 7, 5, 6, 7, 8,
                        7.8, 7.6, 7.4, 7.6, 7.8, 8,
                        6, 3, 0, 3, 6, 8
                }, 9, 14),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    public void ALTERNATING_PAIR_CREATES_SWING_variation1_highThenLow() {
        verify(config(3, 2, 2, 0.0, 0.0),
                series(new double[] {
                        1.25, 2.5, 3.75, 5.0, 3.75,
                        2.5, 1.25, 2.5, 3.75, 5.0
                }, 3, 6),
                9, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ALTERNATING_PAIR_CREATES_SWING_variation2_lowThenHigh() {
        verify(config(3, 2, 2, 0.0, 0.0),
                series(new double[] { 5, 4, 3, 2, 3, 4, 5, 4, 3, 2 }, 6, 3),
                9, ElliottDegree.MINOR);
    }

    @Test
    public void MULTIPLE_ALTERNATING_PIVOTS_variation1_threeTurns() {
        verify(config(3, 2, 2, 0.0, 0.0), zigzagSeries(),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void LAST_ALLOWED_CANDIDATE_IS_INCLUDED_variation1_inclusiveBound() {
        verify(config(3, 2, 3, 0.0, 0.5), lowSeries(),
                6, ElliottDegree.MINOR);
    }

    @Test
    public void TURN_BEYOND_EFFECTIVE_PREFIX_variation1_laterTurnIgnored() {
        verify(config(3, 2, 2, 0.0, 0.0), zigzagSeries(),
                8, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void MULTIPLE_REJECTION_PATHS_ONE_SURVIVOR_variation1_mixedCandidates() {
        verify(config(3, 2, 2, 1.0, 0.5),
                series(new double[] {
                        10, 10, 10, 10,
                        11, 12, 14, 16, 14, 12, 10,
                        10, 10, 10, 11, 10, 11, 10
                }, 7, 0),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }
}
