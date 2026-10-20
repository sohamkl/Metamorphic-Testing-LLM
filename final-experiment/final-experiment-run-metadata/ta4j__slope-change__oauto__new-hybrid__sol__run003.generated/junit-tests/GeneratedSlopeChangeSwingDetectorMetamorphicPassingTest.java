import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
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

    private static final double TRANSLATION = 100.0;

    private static final double[] HIGH_C1 = { 10, 11, 12, 11, 10, 9 };
    private static final double[] HIGH_C2 = { 10, 11, 12, 11, 10, 9, 8 };
    private static final double[] LOW_C1 = { 12, 11, 10, 11, 12, 13 };
    private static final double[] LOW_C2 = { 12, 11, 10, 11, 12, 13, 14 };
    private static final double[] MULTI_HIGH_FIRST = {
            10, 11, 12, 11, 10, 9, 10, 11, 12, 11, 10, 9
    };
    private static final double[] MULTI_LOW_FIRST = {
            12, 11, 10, 11, 12, 13, 12, 11, 10, 11, 12, 13
    };

    private static void runCase(SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);

        FollowUp followUp = generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = followUp.detector.detect(
                followUp.series,
                followUp.index,
                followUp.degree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder()
                .withName(sourceSeries.getName() + "-translated")
                .withNumFactory(sourceSeries.numFactory())
                .build();

        Num translation = sourceSeries.numFactory().numOf(TRANSLATION);

        for (int barIndex = sourceSeries.getBeginIndex();
                barIndex <= sourceSeries.getEndIndex();
                barIndex++) {
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

        return new FollowUp(
                new SlopeChangeSwingDetector(detector.getConfig()),
                translatedSeries,
                index,
                degree);
    }

    private static SlopeChangeSwingDetector explicit(int window,
            int confirmationBars,
            int atrPeriod,
            double minSlopeChange,
            double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(
                window,
                confirmationBars,
                atrPeriod,
                minSlopeChange,
                minAtrReversal));
    }

    private static BarSeries series(String name,
            double[] closes,
            double volume,
            double amount) {
        return series(name, closes, volume, amount, null, null);
    }

    private static BarSeries series(String name,
            double[] closes,
            double volume,
            double amount,
            double[] highOverrides,
            double[] lowOverrides) {
        BarSeries result = new BaseBarSeriesBuilder()
                .withName(name)
                .build();

        Duration period = Duration.ofMinutes(1);
        Instant base = Instant.parse("2024-01-01T00:00:00Z");

        for (int index = 0; index < closes.length; index++) {
            Num close = num(result, closes[index]);
            Num open = close;
            Num high = close.plus(result.numFactory().numOf(0.5));
            Num low = close.minus(result.numFactory().numOf(0.5));

            if (highOverrides != null && !Double.isNaN(highOverrides[index])) {
                high = num(result, highOverrides[index]);
            }
            if (lowOverrides != null && !Double.isNaN(lowOverrides[index])) {
                low = num(result, lowOverrides[index]);
            }

            Instant begin = base.plus(period.multipliedBy(index));
            Instant end = begin.plus(period);

            result.addBar(new BaseBar(
                    period,
                    begin,
                    end,
                    open,
                    high,
                    low,
                    close,
                    result.numFactory().numOf(volume),
                    result.numFactory().numOf(amount + index),
                    index + 1L));
        }

        return result;
    }

    private static Num num(BarSeries series, double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            return NaN.NaN;
        }
        return series.numFactory().numOf(value);
    }

    private static double[] overrides(int length) {
        double[] values = new double[length];
        Arrays.fill(values, Double.NaN);
        return values;
    }

    private static final class FollowUp {
        private final SlopeChangeSwingDetector detector;
        private final BarSeries series;
        private final int index;
        private final ElliottDegree degree;

        private FollowUp(SlopeChangeSwingDetector detector,
                BarSeries series,
                int index,
                ElliottDegree degree) {
            this.detector = detector;
            this.series = series;
            this.index = index;
            this.degree = degree;
        }
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMP_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("index-below", HIGH_C2, 0, 0),
                -7,
                ElliottDegree.MINOR);
    }

    @Test
    void INSUFFICIENT_BARS_BEFORE_FIRST_CANDIDATE_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("insufficient", LOW_C1, 4, 40),
                4,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void EXACTLY_ONE_EVALUABLE_CANDIDATE_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("one-candidate", HIGH_C2, 2, 24),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void INDEX_INSIDE_SERIES_TRUNCATES_SCAN_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("inside-index", MULTI_HIGH_FIRST, 3, 36),
                5,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void INDEX_AT_SERIES_END_variation1() {
        BarSeries sourceSeries = series("at-end", HIGH_C2, 0, 0);
        runCase(new SlopeChangeSwingDetector(3),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.MINOR);
    }

    @Test
    void INDEX_ABOVE_END_CLAMP_variation1() {
        runCase(explicit(3, 1, 2, 0, 0),
                series("above-end", LOW_C1, 0, 0),
                1000,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void FLAT_CLOSES_ZERO_SLOPES_variation1() {
        BarSeries sourceSeries = series("flat",
                new double[] { 10, 10, 10, 10, 10, 10, 10, 10 },
                2,
                20);
        runCase(new SlopeChangeSwingDetector(3),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.MINOR);
    }

    @Test
    void SLOPE_CHANGE_BELOW_MINIMUM_variation1() {
        runCase(explicit(3, 1, 3, 3, 0),
                series("below-minimum", HIGH_C1, 1, 10),
                99,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SLOPE_CHANGE_EXACTLY_MINIMUM_variation1() {
        runCase(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 3, 2.0, 0.0)),
                series("exact-minimum", LOW_C2, 2, 20),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void SLOPE_CHANGE_ABOVE_MINIMUM_variation1() {
        runCase(explicit(3, 1, 3, 1, 0),
                series("above-minimum", HIGH_C1, 1, 12),
                5,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void POSITIVE_TO_NEGATIVE_HIGH_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("high-turn", HIGH_C2, 0, 0),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void NEGATIVE_TO_POSITIVE_LOW_variation1() {
        runCase(explicit(3, 2, 3, 0, 0),
                series("low-turn", LOW_C2, 5, 50),
                100,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void BOTH_SLOPES_POSITIVE_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("positive-slopes", new double[] { 1, 2, 3, 5, 7, 9, 11 }, 1, 3),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void BOTH_SLOPES_NEGATIVE_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("negative-slopes", new double[] { 12, 11, 10, 8, 6, 4 }, 2, 8),
                5,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ZERO_BEFORE_SLOPE_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("zero-before", new double[] { 12, 12, 12, 11, 10, 9, 8 }, 0, 0),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void ZERO_AFTER_SLOPE_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("zero-after", new double[] { 10, 11, 12, 12, 12, 12 }, 0, 0),
                100,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void NONFINITE_BEFORE_WINDOW_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("nonfinite-before",
                        new double[] { 10, Double.POSITIVE_INFINITY, 12, 11, 10, 9, 8 },
                        1,
                        10),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void NONFINITE_AFTER_WINDOW_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("nonfinite-after",
                        new double[] { 10, 11, 12, Double.POSITIVE_INFINITY, 10, 9 },
                        1,
                        10),
                5,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void PERSISTENCE_FAILS_FIRST_CONFIRMATION_variation1() {
        runCase(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)),
                series("persistence-first", new double[] { 10, 11, 12, 11, 10, 13 }, 2, 20),
                5,
                ElliottDegree.MINOR);
    }

    @Test
    void PERSISTENCE_FAILS_LATER_CONFIRMATION_variation1() {
        runCase(explicit(3, 2, 3, 0, 0),
                series("persistence-later", new double[] { 10, 11, 12, 11, 10, 9, 13 }, 2, 20),
                50,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void PERSISTENCE_NONFINITE_SLOPE_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("persistence-nonfinite",
                        new double[] { 10, 11, 12, 11, 10, 9, Double.POSITIVE_INFINITY },
                        0,
                        0),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void ALL_CONFIRMATION_SLOPES_PASS_variation1() {
        runCase(explicit(3, 2, 3, 0, 0),
                series("all-confirm", HIGH_C2, 4, 40),
                6,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void HIGH_EXTREME_AT_TRANSITION_START_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("high-start",
                        HIGH_C2,
                        1,
                        12,
                        new double[] {
                                Double.NaN, Double.NaN, 20, 15, 14, Double.NaN, Double.NaN
                        },
                        null),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void HIGH_EXTREME_AT_TRANSITION_INTERIOR_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("high-interior",
                        HIGH_C1,
                        2,
                        24,
                        new double[] { Double.NaN, Double.NaN, 14, 21, 13, Double.NaN },
                        null),
                99,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void HIGH_EXTREME_AT_TRANSITION_END_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("high-end",
                        HIGH_C2,
                        0,
                        0,
                        new double[] {
                                Double.NaN, Double.NaN, 14, 15, 22, Double.NaN, Double.NaN
                        },
                        null),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void HIGH_EXTREME_TIE_KEEPS_FIRST_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("high-tie",
                        HIGH_C1,
                        0,
                        0,
                        new double[] { Double.NaN, Double.NaN, 20, 20, 15, Double.NaN },
                        null),
                5,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void LOW_EXTREME_INTERIOR_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("low-interior",
                        LOW_C2,
                        2,
                        20,
                        null,
                        new double[] {
                                Double.NaN, Double.NaN, 8, 3, 7, Double.NaN, Double.NaN
                        }),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void LOW_EXTREME_TIE_KEEPS_FIRST_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("low-tie",
                        LOW_C1,
                        1,
                        10,
                        null,
                        new double[] { Double.NaN, Double.NaN, 4, 4, 7, Double.NaN }),
                20,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void NONFINITE_INITIAL_EXTREME_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("nonfinite-initial-high",
                        HIGH_C2,
                        1,
                        10,
                        new double[] {
                                Double.NaN, Double.NaN, Double.POSITIVE_INFINITY,
                                14, 13, Double.NaN, Double.NaN
                        },
                        null),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void NONFINITE_LATER_EXTREME_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("nonfinite-later-low",
                        LOW_C1,
                        1,
                        10,
                        null,
                        new double[] {
                                Double.NaN, Double.NaN, 8,
                                Double.POSITIVE_INFINITY, 7, Double.NaN
                        }),
                5,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_FILTER_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("first-bypass", LOW_C2, 0, 0),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void FIRST_PIVOT_WITH_NONFINITE_ATR_variation1() {
        runCase(explicit(3, 1, 3, 0, 2),
                series("first-nonfinite-atr",
                        HIGH_C1,
                        3,
                        30,
                        null,
                        new double[] {
                                Double.NaN, Double.POSITIVE_INFINITY,
                                Double.NaN, Double.NaN, Double.NaN, Double.NaN
                        }),
                50,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASS_variation1() {
        BarSeries sourceSeries = series("zero-atr-multiplier", MULTI_HIGH_FIRST, 1, 10);
        runCase(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.MINOR);
    }

    @Test
    void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation1() {
        BarSeries sourceSeries = series("later-nonfinite-atr",
                MULTI_HIGH_FIRST,
                2,
                20,
                null,
                new double[] {
                        Double.NaN, Double.NaN, Double.NaN, Double.NaN,
                        Double.NaN, Double.NaN, Double.POSITIVE_INFINITY,
                        Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN
                });
        runCase(explicit(3, 1, 3, 0, 1),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void REVERSAL_BELOW_ATR_THRESHOLD_variation1() {
        BarSeries sourceSeries = series("below-atr", MULTI_HIGH_FIRST, 1, 10);
        runCase(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 20.0)),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.MINOR);
    }

    @Test
    void REVERSAL_EXACTLY_ATR_THRESHOLD_variation1() {
        runCase(explicit(3, 1, 1, 0, 1),
                series("exact-atr", MULTI_HIGH_FIRST, 0, 0),
                100,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void REVERSAL_ABOVE_ATR_THRESHOLD_variation1() {
        BarSeries sourceSeries = series("above-atr", MULTI_LOW_FIRST, 3, 30);
        runCase(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.1)),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.MINOR);
    }

    @Test
    void ALTERNATING_TYPE_APPENDS_variation1() {
        BarSeries sourceSeries = series("alternating", MULTI_HIGH_FIRST, 1, 10);
        runCase(explicit(3, 1, 3, 0, 0),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SAME_HIGH_STRICTLY_HIGHER_REPLACES_variation1() {
        double[] highs = overrides(MULTI_HIGH_FIRST.length);
        highs[2] = 14;
        highs[8] = 20;
        double[] lows = overrides(MULTI_HIGH_FIRST.length);
        lows[5] = Double.POSITIVE_INFINITY;

        BarSeries sourceSeries = series("same-high-higher",
                MULTI_HIGH_FIRST,
                2,
                20,
                highs,
                lows);
        runCase(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.MINOR);
    }

    @Test
    void SAME_HIGH_EQUAL_OR_LOWER_RETAINED_variation1() {
        double[] highs = overrides(MULTI_HIGH_FIRST.length);
        highs[2] = 20;
        highs[8] = 15;
        double[] lows = overrides(MULTI_HIGH_FIRST.length);
        lows[5] = Double.POSITIVE_INFINITY;

        runCase(explicit(3, 1, 3, 0, 0),
                series("same-high-retain", MULTI_HIGH_FIRST, 1, 10, highs, lows),
                100,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SAME_LOW_STRICTLY_LOWER_REPLACES_variation1() {
        double[] highs = overrides(MULTI_LOW_FIRST.length);
        highs[5] = Double.POSITIVE_INFINITY;
        double[] lows = overrides(MULTI_LOW_FIRST.length);
        lows[2] = 8;
        lows[8] = 2;

        BarSeries sourceSeries = series("same-low-lower",
                MULTI_LOW_FIRST,
                0,
                0,
                highs,
                lows);
        runCase(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.MINOR);
    }

    @Test
    void SAME_LOW_EQUAL_OR_HIGHER_RETAINED_variation1() {
        double[] highs = overrides(MULTI_LOW_FIRST.length);
        highs[5] = Double.POSITIVE_INFINITY;
        double[] lows = overrides(MULTI_LOW_FIRST.length);
        lows[2] = 2;
        lows[8] = 7;

        BarSeries sourceSeries = series("same-low-retain",
                MULTI_LOW_FIRST,
                4,
                40,
                highs,
                lows);
        runCase(explicit(3, 1, 3, 0, 0),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void TWO_PIVOTS_CREATE_ONE_SWING_variation1() {
        runCase(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)),
                series("two-pivots",
                        new double[] { 10, 11, 12, 11, 10, 9, 10, 11, 12 },
                        2,
                        20),
                8,
                ElliottDegree.MINOR);
    }

    @Test
    void MULTIPLE_PIVOTS_CREATE_ORDERED_SWINGS_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("ordered-swings", MULTI_HIGH_FIRST, 3, 36),
                100,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void DEGREE_PROPAGATES_WITHOUT_AFFECTING_PIVOTS_variation1() {
        BarSeries sourceSeries = series("degree-propagation", MULTI_LOW_FIRST, 1, 10);
        runCase(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)),
                sourceSeries,
                sourceSeries.getEndIndex(),
                ElliottDegree.CYCLE);
    }

    @Test
    void ZERO_VOLUME_BAR_PAYLOAD_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("zero-volume", HIGH_C1, 0, 37),
                5,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void NONZERO_VOLUME_AND_AMOUNT_PAYLOAD_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("nonzero-payload", LOW_C2, 7, 123),
                6,
                ElliottDegree.MINOR);
    }

    @Test
    void DEFAULT_CONFIG_CONSTRUCTOR_variation1() {
        runCase(new SlopeChangeSwingDetector(3),
                series("default-constructor", HIGH_C2, 2, 20),
                100,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void EXPLICIT_CONFIG_CONSTRUCTOR_variation1() {
        SlopeChangeConfig config = new SlopeChangeConfig(3, 1, 3, 0.5, 0.0);
        runCase(new SlopeChangeSwingDetector(config),
                series("explicit-constructor", LOW_C1, 1, 10),
                5,
                ElliottDegree.MINOR);
    }

    @Test
    void FUTURE_BARS_DO_NOT_AFFECT_CAUSAL_PREFIX_variation1() {
        runCase(explicit(3, 1, 3, 0, 0),
                series("causal-prefix",
                        new double[] { 10, 11, 12, 11, 10, 9, 100, 10, 120, 5, 140, 1 },
                        5,
                        50),
                5,
                ElliottDegree.INTERMEDIATE);
    }
}
