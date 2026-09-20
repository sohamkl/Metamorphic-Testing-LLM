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
import org.ta4j.core.num.DoubleNumFactory;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.NumFactory;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final double TRANSLATION = 100.0;
    private static final double ABSOLUTE_TOLERANCE = 1.0e-9;
    private static final double RELATIVE_TOLERANCE = 1.0e-12;
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static void exercise(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);

        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                detector, sourceSeries, index, degree);

        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput = followUpDetector.detect(
                followUpSeries, followUpIndex, followUpDegree);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars,
            double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(
                window, confirmationBars, 3, minSlopeChange, minAtrReversal));
    }

    private static BarSeries highTurn(double base, double step) {
        return series(highTurnValues(base, step));
    }

    private static double[] highTurnValues(double base, double step) {
        return new double[] {
                base,
                base + step,
                base + 2 * step,
                base + step,
                base,
                base - step,
                base - 2 * step
        };
    }

    private static BarSeries lowTurn(double base, double step) {
        return series(new double[] {
                base,
                base - step,
                base - 2 * step,
                base - step,
                base,
                base + step,
                base + 2 * step
        });
    }

    private static BarSeries alternating(double base, double step, int turns) {
        int segments = Math.max(2, turns + 1);
        double[] close = new double[segments * 6 + 1];
        for (int index = 0; index < close.length; index++) {
            int segment = index / 6;
            int offset = index % 6;
            double direction = segment % 2 == 0 ? 1.0 : -1.0;
            double segmentBase = base + (segment % 2 == 0 ? 0.0 : 6.0 * step);
            close[index] = segmentBase + direction * offset * step;
        }
        return series(close);
    }

    private static BarSeries alternatingWithAuxiliaryData(double base, double step, int turns) {
        int segments = Math.max(2, turns + 1);
        double[] close = new double[segments * 6 + 1];
        for (int index = 0; index < close.length; index++) {
            int segment = index / 6;
            int offset = index % 6;
            double direction = segment % 2 == 0 ? 1.0 : -1.0;
            double segmentBase = base + (segment % 2 == 0 ? 0.0 : 6.0 * step);
            close[index] = segmentBase + direction * offset * step;
        }
        return series(close, null, null, 3);
    }

    private static BarSeries doubleSeries(double[] close) {
        return createSeries("double-series", close, null, null, 1, DoubleNumFactory.getInstance());
    }

    private static BarSeries doubleSeries(double[] close, double[] high, double[] low, int auxiliaryMode) {
        return createSeries("double-series", close, high, low, auxiliaryMode, DoubleNumFactory.getInstance());
    }

    private static BarSeries series(double[] close) {
        return series(close, null, null, 1);
    }

    private static BarSeries series(double[] close, double[] high, double[] low, int auxiliaryMode) {
        BarSeries probe = new BaseBarSeriesBuilder().withName("probe").build();
        return createSeries("source-series", close, high, low, auxiliaryMode, probe.numFactory());
    }

    private static BarSeries createSeries(String name, double[] close, double[] high, double[] low,
            int auxiliaryMode, NumFactory numFactory) {
        BarSeries series = new BaseBarSeriesBuilder()
                .withName(name)
                .withNumFactory(numFactory)
                .build();

        for (int index = 0; index < close.length; index++) {
            double closeValue = close[index];
            double highValue = high == null ? closeValue + 1.0 : high[index];
            double lowValue = low == null ? closeValue - 1.0 : low[index];
            double openValue = closeValue;

            double volumeValue;
            double amountValue;
            long trades;
            if (auxiliaryMode == 0) {
                volumeValue = 0.0;
                amountValue = 0.0;
                trades = 0L;
            } else if (auxiliaryMode == 2) {
                volumeValue = index % 2 == 0 ? 0.0 : index + 1.0;
                amountValue = index % 3 == 0 ? 0.0 : Math.abs(closeValue) * volumeValue;
                trades = index % 4;
            } else if (auxiliaryMode == 3) {
                volumeValue = 1.0 + index % 5;
                amountValue = (Math.abs(closeValue) + index + 1.0) * volumeValue;
                trades = 1L + index % 7;
            } else {
                volumeValue = 10.0;
                amountValue = Math.abs(closeValue) * volumeValue;
                trades = 1L;
            }

            Num open = numFactory.numOf(openValue);
            Num highPrice = numFactory.numOf(highValue);
            Num lowPrice = numFactory.numOf(lowValue);
            Num closePrice = numFactory.numOf(closeValue);
            Num volume = numFactory.numOf(volumeValue);
            Num amount = numFactory.numOf(amountValue);

            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(index));
            Instant end = begin.plus(BAR_DURATION);
            series.addBar(new BaseBar(
                    BAR_DURATION,
                    begin,
                    end,
                    open,
                    highPrice,
                    lowPrice,
                    closePrice,
                    volume,
                    amount,
                    trades));
        }
        return series;
    }

    private static void assertMetamorphicRelation(SwingDetectorResult sourceOutput,
            SwingDetectorResult followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");

        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();

        if (sourcePivots.size() != followUpPivots.size()) {
            throw new AssertionError("Pivot count differs: source=" + sourcePivots.size()
                    + ", follow-up=" + followUpPivots.size());
        }

        for (int index = 0; index < sourcePivots.size(); index++) {
            SwingPivot source = sourcePivots.get(index);
            SwingPivot followUp = followUpPivots.get(index);

            if (source.index() != followUp.index()) {
                throw new AssertionError("Pivot index differs at position " + index
                        + ": source=" + source.index() + ", follow-up=" + followUp.index());
            }
            if (!Objects.equals(source.type(), followUp.type())) {
                throw new AssertionError("Pivot type differs at position " + index
                        + ": source=" + source.type() + ", follow-up=" + followUp.type());
            }
            assertTranslated(source.price(), followUp.price(), "pivot price", index);
        }

        List<ElliottSwing> sourceSwings = sourceOutput.swings();
        List<ElliottSwing> followUpSwings = followUpOutput.swings();

        if (sourceSwings.size() != followUpSwings.size()) {
            throw new AssertionError("Swing count differs: source=" + sourceSwings.size()
                    + ", follow-up=" + followUpSwings.size());
        }

        for (int index = 0; index < sourceSwings.size(); index++) {
            ElliottSwing source = sourceSwings.get(index);
            ElliottSwing followUp = followUpSwings.get(index);

            if (source.fromIndex() != followUp.fromIndex()) {
                throw new AssertionError("Swing start index differs at position " + index
                        + ": source=" + source.fromIndex() + ", follow-up=" + followUp.fromIndex());
            }
            if (source.toIndex() != followUp.toIndex()) {
                throw new AssertionError("Swing end index differs at position " + index
                        + ": source=" + source.toIndex() + ", follow-up=" + followUp.toIndex());
            }
            if (!Objects.equals(source.degree(), followUp.degree())) {
                throw new AssertionError("Swing degree differs at position " + index
                        + ": source=" + source.degree() + ", follow-up=" + followUp.degree());
            }

            assertTranslated(source.fromPrice(), followUp.fromPrice(), "swing start price", index);
            assertTranslated(source.toPrice(), followUp.toPrice(), "swing end price", index);
        }
    }

    private static void assertTranslated(Num source, Num followUp, String field, int index) {
        double expected = source.doubleValue() + TRANSLATION;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(
                ABSOLUTE_TOLERANCE,
                Math.max(Math.abs(expected), Math.abs(actual)) * RELATIVE_TOLERANCE);

        if (!Double.isFinite(expected)
                || !Double.isFinite(actual)
                || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Translated " + field + " differs at position " + index
                    + ": expected=" + expected + " +/- " + tolerance + ", actual=" + actual);
        }
    }

    @Test
    public void EMPTY_ZERO_BASED_SERIES_variation1() {
        exercise(detector(3, 2, 0.0, 0.5), series(new double[] { -10.0 }), -1, ElliottDegree.MINOR);
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation1() {
        exercise(detector(3, 2, 0.0, 0.0), series(new double[] { -100.5, -100.4, -100.3 }), -7,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void INDEX_ABOVE_END_CLAMPS_TO_END_variation1() {
        exercise(detector(3, 2, 2.0, 0.0), highTurn(0.0, 1.0), 100, ElliottDegree.MINUTE);
    }

    @Test
    public void ONE_BAR_SHORT_OF_FIRST_CANDIDATE_variation1() {
        exercise(detector(3, 2, 0.0, 0.5), series(new double[] { 10, 11, 12, 11, 10, 9 }), 5,
                ElliottDegree.PRIMARY);
    }

    @Test
    public void EXACTLY_ONE_CANDIDATE_variation1() {
        exercise(detector(3, 2, 0.0, 0.5), highTurn(1_000_000.0, 20.0), 6, ElliottDegree.CYCLE);
    }

    @Test
    public void CAUSAL_SUFFIX_IGNORED_variation1() {
        double[] close = { -20, -18, -16, -18, -20, -22, -24, 200, 100, -200, 300, -300 };
        exercise(detector(3, 2, 0.0, 0.0), series(close), 6, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void FLAT_CLOSE_WINDOWS_variation1() {
        exercise(detector(3, 2, 0.0, 0.5), series(new double[] { -99.8, -99.8, -99.8, -99.8, -99.8,
                -99.8, -99.8, -99.8, -99.8 }), 8, ElliottDegree.MINUETTE);
    }

    @Test
    public void SLOPE_CHANGE_STRICTLY_BELOW_THRESHOLD_variation1() {
        exercise(detector(3, 2, 1.0, 0.0), highTurn(0.0, 0.2), 6, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void HIGH_SLOPE_CHANGE_EQUAL_THRESHOLD_variation1() {
        exercise(detector(3, 2, 2.0, 0.5), highTurn(25.0, 1.0), 6, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void LOW_SLOPE_CHANGE_EQUAL_THRESHOLD_variation1() {
        exercise(detector(3, 2, 2.0, 0.5), lowTurn(2_000_000.0, 1.0), 6, ElliottDegree.MINOR);
    }

    @Test
    public void POSITIVE_TO_POSITIVE_NOT_A_REVERSAL_variation1() {
        exercise(detector(3, 2, 0.5, 0.0), series(new double[] { -20, -19, -18, -15, -12, -9, -6 }),
                6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void NEGATIVE_TO_NEGATIVE_NOT_A_REVERSAL_variation1() {
        exercise(detector(3, 2, 0.5, 0.0),
                series(new double[] { -90, -91, -92, -95, -98, -101, -104 }), 6, ElliottDegree.MINUTE);
    }

    @Test
    public void ZERO_BEFORE_SLOPE_NOT_A_REVERSAL_variation1() {
        exercise(detector(3, 2, 1.0, 0.0), series(new double[] { 0, 0, 0, -1, -2, -3, -4 }), 6,
                ElliottDegree.PRIMARY);
    }

    @Test
    public void ZERO_AFTER_SLOPE_NOT_A_REVERSAL_variation1() {
        exercise(detector(3, 2, 1.0, 0.0), series(new double[] { 5, 6, 7, 7, 7, 7, 7 }), 6,
                ElliottDegree.CYCLE);
    }

    @Test
    public void NONFINITE_BEFORE_SLOPE_variation1() {
        exercise(detector(3, 2, 0.0, 0.5),
                doubleSeries(new double[] { Double.NaN, 1, 2, 1, 0, -1, -2 }), 6,
                ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void NONFINITE_AFTER_SLOPE_variation1() {
        exercise(detector(3, 2, 0.0, 0.5),
                doubleSeries(new double[] { -10, -9, -8, -9, Double.NaN, -11, -12 }), 6,
                ElliottDegree.MINUETTE);
    }

    @Test
    public void CONFIRMED_HIGH_REVERSAL_variation1() {
        exercise(detector(3, 2, 0.0, 0.5), highTurn(-101.0, 2.0), 6, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void CONFIRMED_LOW_REVERSAL_variation1() {
        exercise(detector(3, 2, 0.0, 0.5), lowTurn(-4.0, 2.0), 6, ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void HIGH_CONFIRMATION_FAILS_FIRST_CHECK_variation1() {
        double[] close = { 10, 11, 12, 11, 10, 9, 13 };
        exercise(detector(3, 2, 0.0, 0.0), series(close), 6, ElliottDegree.MINOR);
    }

    @Test
    public void HIGH_CONFIRMATION_FAILS_LATER_CHECK_variation1() {
        double[] close = { 1000, 1010, 1020, 1010, 1000, 990, 1040 };
        exercise(detector(3, 2, 0.0, 0.0), series(close), 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void LOW_CONFIRMATION_FAILS_variation1() {
        double[] close = { -20, -21, -22, -21, -20, -19, -25 };
        exercise(detector(3, 2, 0.0, 0.5), series(close), 6, ElliottDegree.MINUTE);
    }

    @Test
    public void NONFINITE_CONFIRMATION_SLOPE_variation1() {
        double[] close = { -100, -98, -96, -98, -100, -102, Double.NaN };
        exercise(detector(3, 2, 0.0, 0.5), doubleSeries(close), 6, ElliottDegree.PRIMARY);
    }

    @Test
    public void HIGH_EXTREME_AT_TRANSITION_START_variation1() {
        double[] close = { 0, 2, 4, 2, 0, -2, -4 };
        double[] high = { 1, 3, 20, 8, 7, -1, -3 };
        exercise(detector(3, 2, 0.0, 0.0), series(close, high, null, 0), 6, ElliottDegree.CYCLE);
    }

    @Test
    public void HIGH_EXTREME_AT_TRANSITION_END_variation1() {
        double[] close = { 10, 12, 14, 12, 10, 8, 6 };
        double[] high = { 11, 13, 15, 16, 25, 9, 7 };
        exercise(detector(3, 2, 0.0, 0.0), series(close, high, null, 1), 6,
                ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void HIGH_EXTREME_TIE_KEEPS_EARLIEST_variation1() {
        double[] close = { 100, 102, 104, 102, 100, 98, 96 };
        double[] high = { 101, 103, 110, 110, 102, 99, 97 };
        exercise(detector(3, 2, 0.0, 0.5), series(close, high, null, 2), 6, ElliottDegree.MINUETTE);
    }

    @Test
    public void LOW_EXTREME_AT_TRANSITION_START_variation1() {
        double[] close = { -10, -12, -14, -12, -10, -8, -6 };
        double[] low = { -11, -13, -30, -20, -11, -9, -7 };
        exercise(detector(3, 2, 0.0, 0.0), series(close, null, low, 0), 6, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void LOW_EXTREME_AT_TRANSITION_END_variation1() {
        double[] close = { -99, -101, -103, -101, -99, -97, -95 };
        double[] low = { -100, -102, -104, -110, -120, -98, -96 };
        exercise(detector(3, 2, 0.0, 0.0), series(close, null, low, 1), 6,
                ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void LOW_EXTREME_TIE_KEEPS_EARLIEST_variation1() {
        double[] close = { 4, 2, 0, 2, 4, 6, 8 };
        double[] low = { 3, 1, -10, -10, 3, 5, 7 };
        exercise(detector(3, 2, 0.0, 0.5), series(close, null, low, 2), 6, ElliottDegree.MINOR);
    }

    @Test
    public void NONFINITE_TRANSITION_EXTREME_REJECTS_PIVOT_variation1() {
        double[] close = { 20, 22, 24, 22, 20, 18, 16 };
        double[] high = { 21, 23, 25, Double.NaN, 21, 19, 17 };
        exercise(detector(3, 2, 0.0, 0.0), doubleSeries(close, high, null, 1), 6,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_MAGNITUDE_FILTER_variation1() {
        exercise(detector(3, 2, 0.0, 1000.0), highTurn(1_000_000.0, 3.0), 6, ElliottDegree.MINUTE);
    }

    @Test
    public void ZERO_MULTIPLIER_BYPASSES_LATER_FILTER_variation1() {
        exercise(detector(3, 2, 0.0, 0.0), alternating(-20.0, 4.0, 2), 12, ElliottDegree.PRIMARY);
    }

    @Test
    public void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation1() {
        double[] close = { -100, -98, -96, -98, -100, -102, -104, -102, -100, Double.NaN, -96,
                -98, -100 };
        exercise(detector(3, 2, 0.0, 0.5), doubleSeries(close), 12, ElliottDegree.CYCLE);
    }

    @Test
    public void REVERSAL_STRICTLY_BELOW_ATR_THRESHOLD_variation1() {
        exercise(detector(3, 2, 0.0, 100.0), alternating(0.0, 0.25, 2), 12,
                ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void REVERSAL_EQUAL_TO_ATR_THRESHOLD_variation1() {
        exercise(detector(3, 2, 0.0, 1.0), alternating(20.0, 2.0, 2), 12, ElliottDegree.MINUETTE);
    }

    @Test
    public void REVERSAL_ABOVE_ATR_THRESHOLD_variation1() {
        exercise(detector(3, 2, 0.0, 0.1), alternating(1_000_000.0, 20.0, 2), 12,
                ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void ALTERNATING_HIGH_LOW_PAIR_variation1() {
        exercise(detector(3, 2, 0.0, 0.0), alternating(-30.0, 5.0, 2), 12,
                ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void THREE_OR_MORE_ALTERNATING_PIVOTS_variation1() {
        exercise(detector(3, 2, 0.0, 0.0), alternating(-99.5, 3.0, 4), 24, ElliottDegree.MINOR);
    }

    @Test
    public void SAME_HIGH_REPLACED_BY_HIGHER_variation1() {
        double[] close = { 0, 2, 4, 2, 0, -2, 1, 3, 5, 3, 1, -1, -3 };
        double[] high = { 1, 3, 6, 4, 2, 0, 2, 4, 12, 5, 3, 1, -2 };
        exercise(detector(3, 2, 0.0, 0.0), series(close, high, null, 0), 12,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void SAME_HIGH_NOT_REPLACED_variation1() {
        double[] close = { 20, 22, 24, 22, 20, 18, 21, 23, 25, 23, 21, 19, 17 };
        double[] high = { 21, 23, 40, 24, 22, 20, 22, 24, 30, 25, 23, 21, 19 };
        exercise(detector(3, 2, 0.0, 0.0), series(close, high, null, 1), 12, ElliottDegree.MINUTE);
    }

    @Test
    public void SAME_LOW_REPLACED_BY_LOWER_variation1() {
        double[] close = { 1000, 998, 996, 998, 1000, 1002, 999, 997, 995, 997, 999, 1001, 1003 };
        double[] low = { 999, 997, 990, 997, 999, 1001, 998, 996, 980, 996, 998, 1000, 1002 };
        exercise(detector(3, 2, 0.0, 0.0), series(close, null, low, 2), 12, ElliottDegree.PRIMARY);
    }

    @Test
    public void SAME_LOW_NOT_REPLACED_variation1() {
        double[] close = { -20, -22, -24, -22, -20, -18, -21, -23, -25, -23, -21, -19, -17 };
        double[] low = { -21, -23, -40, -23, -21, -19, -22, -24, -30, -24, -22, -20, -18 };
        exercise(detector(3, 2, 0.0, 0.0), series(close, null, low, 0), 12, ElliottDegree.CYCLE);
    }

    @Test
    public void DEGREE_PROPAGATES_TO_ALL_SWINGS_variation1() {
        exercise(detector(3, 2, 0.0, 0.0), alternating(-101.0, 4.0, 4), 24,
                ElliottDegree.SUB_MINUETTE);
    }

    @Test
    public void CLOSE_DIRECTION_HIGH_LOCATION_DISAGREEMENT_variation1() {
        double[] close = { 0, 2, 4, 2, 0, -2, -4 };
        double[] high = { 2, 4, 6, 30, 5, 0, -2 };
        exercise(detector(3, 2, 0.0, 0.5), series(close, high, null, 1), 6, ElliottDegree.MINUETTE);
    }

    @Test
    public void CLOSE_DIRECTION_LOW_LOCATION_DISAGREEMENT_variation1() {
        double[] close = { 10, 8, 6, 8, 10, 12, 14 };
        double[] low = { 8, 6, 4, -20, 8, 10, 12 };
        exercise(detector(3, 2, 0.0, 0.0), series(close, null, low, 2), 6,
                ElliottDegree.SUPER_CYCLE);
    }

    @Test
    public void NEGATIVE_AND_NEAR_TRANSLATION_OFFSET_PRICES_variation1() {
        exercise(detector(3, 2, 0.0, 0.0), alternating(-100.25, 2.0, 3), 18,
                ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    public void AUXILIARY_VOLUME_AMOUNT_AND_TRADES_COPIED_variation1() {
        exercise(detector(3, 2, 0.0, 0.0), alternatingWithAuxiliaryData(50.0, 5.0, 3), 18,
                ElliottDegree.MINOR);
    }

    @Test
    public void ZERO_VOLUME_ZERO_AMOUNT_SERIES_variation1() {
        exercise(detector(3, 2, 0.0, 0.5), series(highTurnValues(-99.0, 2.0), null, null, 0), 6,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    public void ALL_MULTIPLE_CANDIDATES_REJECTED_variation1() {
        double[] close = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        exercise(detector(3, 2, 0.5, 0.5), series(close), 13, ElliottDegree.MINUTE);
    }

    @Test
    public void SINGLE_PIVOT_NO_SWING_SENTINEL_variation1() {
        double[] close = { 10, 12, 14, 12, 10, 8, 6, 6, 6, 6, 6, 6, 6 };
        exercise(detector(3, 2, 0.0, 0.5), series(close), 12, ElliottDegree.PRIMARY);
    }

    @Test
    public void FULL_END_INDEX_EVALUATION_variation1() {
        BarSeries source = highTurn(1_000_000.0, 10.0);
        exercise(detector(3, 2, 0.0, 0.0), source, source.getEndIndex(), ElliottDegree.CYCLE);
    }
}
