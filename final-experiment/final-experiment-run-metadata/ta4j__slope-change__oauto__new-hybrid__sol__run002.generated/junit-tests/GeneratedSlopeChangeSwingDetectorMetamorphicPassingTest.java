import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.DoubleNumFactory;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final double[] HIGH = { 10, 12, 14, 13, 11, 9, 8 };
    private static final double[] LOW = { 14, 12, 10, 11, 13, 15, 16 };
    private static final double[] FLAT = { 10, 10, 10, 10, 10, 10, 10 };
    private static final double[] POSITIVE_SAME_SIGN = { 10, 11, 12, 13, 15, 18, 22 };
    private static final double[] NEGATIVE_SAME_SIGN = { 22, 18, 15, 13, 12, 11, 10 };
    private static final double[] ZERO_BEFORE = { 10, 10, 10, 9, 8, 7, 6 };
    private static final double[] HIGH_PERSISTENCE_LATE_FAILURE = { 10, 12, 14, 13, 11, 9, 13 };
    private static final double[] LOW_PERSISTENCE_LATE_FAILURE = { 14, 12, 10, 11, 13, 15, 11 };
    private static final double[] ZIGZAG = {
            10, 12, 14, 12, 10, 8, 10, 12, 14, 12, 10, 8, 10, 12, 14, 12, 10, 8, 10, 12, 14
    };
    private static final double[] ZIGZAG_WIDE = {
            20, 25, 30, 25, 20, 15, 20, 25, 30, 25, 20, 15, 20, 25, 30, 25, 20, 15, 20, 25, 30
    };
    private static final double[] TWO_HIGHS = {
            10, 12, 14, 13, 11, 9, 10, 11, 13, 15, 14, 12, 10, 9
    };
    private static final double[] TWO_LOWS = {
            16, 14, 12, 13, 15, 17, 16, 15, 13, 11, 12, 14, 16, 17
    };

    private static void exercise(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {

        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        FollowUp followUp = generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = followUp.detector.detect(
                followUp.series, followUp.index, followUp.degree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {

        BarSeries translatedSeries = new BaseBarSeriesBuilder()
                .withName(sourceSeries.getName() + "-translated")
                .withNumFactory(sourceSeries.numFactory())
                .build();
        Num translation = sourceSeries.numFactory().numOf(100.0);

        if (!sourceSeries.isEmpty()) {
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
        }

        return new FollowUp(
                new SlopeChangeSwingDetector(detector.getConfig()),
                translatedSeries,
                index,
                degree);
    }

    private static SlopeChangeSwingDetector detector(
            int window,
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

    private static BarSeries emptySeries(String name) {
        return new BaseBarSeriesBuilder().withName(name).build();
    }

    private static BarSeries series(String name, double[] closes, int volumeMode) {
        return specializedSeries(name, closes, volumeMode, -1, -1, -1);
    }

    private static BarSeries specializedSeries(
            String name,
            double[] closes,
            int volumeMode,
            int highBoostIndex,
            int lowDropIndex,
            int unusedIndex) {

        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = close + 1.0;
            double low = close - 1.0;

            if (i == highBoostIndex) {
                high = close + 20.0;
            }
            if (i == lowDropIndex) {
                low = close - 20.0;
            }

            double volume = volume(volumeMode, i);
            double amount = close * volume + i;
            addBar(result, period, base.plus(period.multipliedBy(i)),
                    close, high, low, close, volume, amount, i + 1L);
        }
        return result;
    }

    private static BarSeries tieSeries(
            String name,
            double[] closes,
            boolean highTie,
            int volumeMode) {

        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant base = Instant.parse("2021-02-03T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = close + 1.0;
            double low = close - 1.0;

            if (highTie && (i == 2 || i == 3)) {
                high = 30.0;
            }
            if (!highTie && (i == 2 || i == 3)) {
                low = -10.0;
            }

            double volume = volume(volumeMode, i);
            addBar(result, period, base.plus(period.multipliedBy(i)),
                    close, high, low, close, volume,
                    close * volume + 2.0 * i, 10L + i);
        }
        return result;
    }

    private static BarSeries nanCloseSeries(
            String name,
            double[] closes,
            int nanCloseIndex,
            int volumeMode) {

        BarSeries result = doubleNumSeries(name);
        Instant base = Instant.parse("2022-04-05T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            double ordinaryClose = closes[i];
            double close = i == nanCloseIndex ? Double.NaN : ordinaryClose;
            double open = close;
            double high = Double.isNaN(close) ? Double.NaN : close + 1.0;
            double low = Double.isNaN(close) ? Double.NaN : close - 1.0;
            double volume = volume(volumeMode, i);
            double amount = ordinaryClose * volume + i;

            addBar(result, period, base.plus(period.multipliedBy(i)),
                    open, high, low, close, volume, amount, 20L + i);
        }
        return result;
    }

    private static BarSeries nanExtremeSeries(
            String name,
            double[] closes,
            int nanIndex,
            boolean nanHigh,
            int volumeMode) {

        BarSeries result = doubleNumSeries(name);
        Instant base = Instant.parse("2023-06-07T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = close + 1.0;
            double low = close - 1.0;

            if (i == nanIndex) {
                if (nanHigh) {
                    high = Double.NaN;
                } else {
                    low = Double.NaN;
                }
            }

            double volume = volume(volumeMode, i);
            addBar(result, period, base.plus(period.multipliedBy(i)),
                    close, high, low, close, volume,
                    close * volume + i, 30L + i);
        }
        return result;
    }

    private static BarSeries nanAtrSeries(
            String name,
            double[] closes,
            int nanHighIndex,
            int volumeMode) {

        BarSeries result = doubleNumSeries(name);
        Instant base = Instant.parse("2024-08-09T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = i == nanHighIndex ? Double.NaN : close + 1.0;
            double low = close - 1.0;
            double volume = volume(volumeMode, i);

            addBar(result, period, base.plus(period.multipliedBy(i)),
                    close, high, low, close, volume,
                    close * volume + 3.0 * i, 40L + i);
        }
        return result;
    }

    private static BarSeries doubleNumSeries(String name) {
        return new BaseBarSeriesBuilder()
                .withName(name)
                .withNumFactory(DoubleNumFactory.getInstance())
                .build();
    }

    private static void addBar(
            BarSeries series,
            Duration period,
            Instant begin,
            double open,
            double high,
            double low,
            double close,
            double volume,
            double amount,
            long trades) {

        Instant end = begin.plus(period);
        series.addBar(new BaseBar(
                period,
                begin,
                end,
                series.numFactory().numOf(open),
                series.numFactory().numOf(high),
                series.numFactory().numOf(low),
                series.numFactory().numOf(close),
                series.numFactory().numOf(volume),
                series.numFactory().numOf(amount),
                trades));
    }

    private static double volume(int mode, int index) {
        if (mode == 0) {
            return 0.0;
        }
        if (mode == 1) {
            return 10.0;
        }
        return 1.0 + (index % 5) * 2.5;
    }

    private static double[] slice(double[] values, int length) {
        double[] result = new double[length];
        System.arraycopy(values, 0, result, 0, length);
        return result;
    }

    private static double[] shifted(double[] values, double shift) {
        double[] result = values.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] += shift;
        }
        return result;
    }

    private static final class FollowUp {
        private final SlopeChangeSwingDetector detector;
        private final BarSeries series;
        private final int index;
        private final ElliottDegree degree;

        private FollowUp(
                SlopeChangeSwingDetector detector,
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
    void EMPTY_ZERO_BASED_SERIES_variation1() {
        exercise(detector(3, 2, 3, 0.0, 0.5), emptySeries("empty"),
                Integer.MIN_VALUE, ElliottDegree.MINOR);
    }

    @Test
    void INDEX_CLAMPED_TO_BEGIN_variation1() {
        exercise(detector(3, 2, 3, 0.0, 0.0),
                series("clamp-begin", slice(HIGH, 3), 1),
                Integer.MIN_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void INDEX_CLAMPED_TO_END_variation1() {
        exercise(detector(3, 2, 3, 4.0, 0.5),
                series("clamp-end", HIGH, 2),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    void PREFIX_TOO_SHORT_FOR_FIRST_CANDIDATE_variation1() {
        exercise(detector(3, 2, 3, 0.0, 0.5),
                series("short-prefix-a", ZIGZAG, 0),
                5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void PREFIX_TOO_SHORT_FOR_FIRST_CANDIDATE_variation2() {
        exercise(detector(4, 2, 3, 0.0, 0.5),
                series("short-prefix-b", slice(LOW, 6), 1),
                6, ElliottDegree.MINOR);
    }

    @Test
    void EXACTLY_ONE_CANDIDATE_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.5),
                series("one-high", HIGH, 2),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void EXACTLY_ONE_CANDIDATE_variation2() {
        exercise(detector(3, 2, 3, 0.5, 0.5),
                series("one-flat", FLAT, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    void SLOPE_CHANGE_BELOW_MINIMUM_variation1() {
        exercise(detector(3, 2, 3, 5.0, 0.0),
                series("below-min-a", HIGH, 1),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SLOPE_CHANGE_BELOW_MINIMUM_variation2() {
        exercise(detector(3, 2, 3, 1.0, 0.5),
                series("below-min-b",
                        new double[] { 10, 10.1, 10.2, 10.15, 10.1, 10.05, 10.0 }, 2),
                6, ElliottDegree.MINOR);
    }

    @Test
    void SLOPE_CHANGE_EXACT_THRESHOLD_variation1() {
        exercise(detector(3, 2, 3, 4.0, 0.5),
                series("threshold-high", HIGH, 0),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SLOPE_CHANGE_EXACT_THRESHOLD_variation2() {
        exercise(detector(3, 2, 3, 4.0, 0.5),
                series("threshold-low", LOW, 1),
                6, ElliottDegree.MINOR);
    }

    @Test
    void SAME_SIGN_SLOPES_variation1() {
        exercise(detector(3, 2, 3, 0.25, 0.5),
                series("same-positive", POSITIVE_SAME_SIGN, 2),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SAME_SIGN_SLOPES_variation2() {
        exercise(detector(3, 2, 3, 0.25, 0.5),
                series("same-negative", NEGATIVE_SAME_SIGN, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    void ZERO_SLOPE_DIRECTION_SENTINEL_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                series("zero-direction", ZERO_BEFORE, 1),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void CONFIRMED_HIGH_REVERSAL_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.5),
                series("confirmed-high-a", HIGH, 2),
                6, ElliottDegree.MINOR);
    }

    @Test
    void CONFIRMED_HIGH_REVERSAL_variation2() {
        exercise(detector(3, 2, 3, 0.0, 0.0),
                specializedSeries("confirmed-high-b", HIGH, 0, 2, -1, -1),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void CONFIRMED_LOW_REVERSAL_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.5),
                series("confirmed-low-a", LOW, 1),
                6, ElliottDegree.MINOR);
    }

    @Test
    void CONFIRMED_LOW_REVERSAL_variation2() {
        exercise(detector(3, 2, 3, 0.0, 0.0),
                specializedSeries("confirmed-low-b", LOW, 2, -1, 3, -1),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void PERSISTENCE_FAILS_FIRST_CHECK_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.5),
                series("persistence-first-high", HIGH_PERSISTENCE_LATE_FAILURE, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    void PERSISTENCE_FAILS_FIRST_CHECK_variation2() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                series("persistence-first-low", LOW_PERSISTENCE_LATE_FAILURE, 1),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void PERSISTENCE_FAILS_LATER_CHECK_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.5),
                series("persistence-later-high", HIGH_PERSISTENCE_LATE_FAILURE, 2),
                6, ElliottDegree.MINOR);
    }

    @Test
    void PERSISTENCE_FAILS_LATER_CHECK_variation2() {
        exercise(detector(3, 2, 3, 0.5, 0.5),
                series("persistence-later-low", LOW_PERSISTENCE_LATE_FAILURE, 0),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void HIGH_EXTREME_LOCATION_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                specializedSeries("high-extreme-end", HIGH, 1, 4, -1, -1),
                6, ElliottDegree.MINOR);
    }

    @Test
    void LOW_EXTREME_LOCATION_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                specializedSeries("low-extreme-start", LOW, 2, -1, 2, -1),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void LOW_EXTREME_LOCATION_variation2() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                specializedSeries("low-extreme-interior", LOW, 0, -1, 3, -1),
                6, ElliottDegree.MINOR);
    }

    @Test
    void LOW_EXTREME_LOCATION_variation3() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                specializedSeries("low-extreme-end", LOW, 1, -1, 4, -1),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void EXTREME_TIE_KEEPS_EARLIEST_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                tieSeries("high-tie", HIGH, true, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    void EXTREME_TIE_KEEPS_EARLIEST_variation2() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                tieSeries("low-tie", LOW, false, 1),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void NON_FINITE_CLOSE_SLOPE_SENTINEL_variation1() {
        exercise(detector(3, 2, 3, 0.0, 0.5),
                nanCloseSeries("nan-before", HIGH, 1, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    void NON_FINITE_CLOSE_SLOPE_SENTINEL_variation2() {
        exercise(detector(3, 2, 3, 0.0, 0.5),
                nanCloseSeries("nan-after", LOW, 4, 1),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void NON_FINITE_EXTREME_SENTINEL_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.5),
                nanExtremeSeries("nan-high", HIGH, 2, true, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    void NON_FINITE_EXTREME_SENTINEL_variation2() {
        exercise(detector(3, 2, 3, 0.5, 0.5),
                nanExtremeSeries("nan-low", LOW, 3, false, 1),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_FILTER_variation1() {
        exercise(detector(3, 2, 20, 0.5, 10.0),
                series("first-bypass-high", HIGH, 1),
                6, ElliottDegree.MINOR);
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_FILTER_variation2() {
        exercise(detector(3, 2, 20, 0.5, 100.0),
                series("first-bypass-low", LOW, 2),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SUBSEQUENT_NON_FINITE_ATR_REJECTED_variation1() {
        exercise(detector(3, 2, 3, 0.5, 1.0),
                nanAtrSeries("later-nan-atr", ZIGZAG, 5, 2),
                12, ElliottDegree.MINOR);
    }

    @Test
    void ATR_REVERSAL_BELOW_THRESHOLD_variation1() {
        exercise(detector(3, 2, 3, 0.5, 10.0),
                series("atr-below-a", ZIGZAG, 2),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ATR_REVERSAL_BELOW_THRESHOLD_variation2() {
        exercise(detector(3, 2, 3, 0.5, 50.0),
                series("atr-below-b", shifted(ZIGZAG, 20.0), 0),
                20, ElliottDegree.MINOR);
    }

    @Test
    void ATR_REVERSAL_EXACT_THRESHOLD_variation1() {
        exercise(detector(3, 2, 1, 0.5, 1.0),
                series("atr-equality-a", ZIGZAG_WIDE, 1),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ATR_REVERSAL_EXACT_THRESHOLD_variation2() {
        exercise(detector(3, 2, 1, 0.5, 2.0),
                series("atr-equality-b", shifted(ZIGZAG_WIDE, 30.0), 2),
                20, ElliottDegree.MINOR);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASS_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                series("zero-atr-a", ZIGZAG, 0),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASS_variation2() {
        exercise(detector(3, 2, 8, 0.5, 0.0),
                series("zero-atr-b", shifted(ZIGZAG_WIDE, 10.0), 1),
                20, ElliottDegree.MINOR);
    }

    @Test
    void ALTERNATING_PIVOTS_CREATE_SWING_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                series("alternating-high-low", ZIGZAG, 2),
                12, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ALTERNATING_PIVOTS_CREATE_SWING_variation2() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                series("alternating-low-high", shifted(ZIGZAG, 40.0), 0),
                15, ElliottDegree.MINOR);
    }

    @Test
    void SAME_TYPE_MORE_EXTREME_REPLACES_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                specializedSeries("same-high-replace", TWO_HIGHS, 1, 9, -1, -1),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SAME_TYPE_NOT_MORE_EXTREME_RETAINED_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                specializedSeries("same-low-retain", TWO_LOWS, 2, -1, 2, -1),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    void MULTIPLE_ALTERNATING_PIVOTS_AND_SWINGS_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                series("multiple-swings", ZIGZAG_WIDE, 0),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void DEGREE_PROPAGATION_VARIATION_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                series("degree-minor", ZIGZAG, 1),
                Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    void DEGREE_PROPAGATION_VARIATION_variation2() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                series("degree-intermediate", shifted(ZIGZAG, 5.0), 2),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void AUXILIARY_VOLUME_AMOUNT_VARIATION_variation1() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                series("zero-volume", HIGH, 0),
                6, ElliottDegree.MINOR);
    }

    @Test
    void AUXILIARY_VOLUME_AMOUNT_VARIATION_variation2() {
        exercise(detector(3, 2, 3, 0.5, 0.0),
                series("varying-volume", LOW, 2),
                Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }
}
