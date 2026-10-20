import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final double TRANSLATION = 100.0;
    private static final double TOLERANCE = 1.0e-9;
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private static void verify(double[] closes, int index, ElliottDegree degree) {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(3), series(closes), index, degree);
    }

    private static void assertMetamorphicRelationFor(SlopeChangeSwingDetector detector, BarSeries source, int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(source, index, degree);
        SwingDetectorResult followUpOutput = detector.detect(generateFollowUp(source), index, degree);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(SwingDetectorResult sourceOutput,
            SwingDetectorResult followUpOutput) {
        assertEquals(sourceOutput.pivots().size(), followUpOutput.pivots().size());
        assertEquals(sourceOutput.swings().size(), followUpOutput.swings().size());

        for (int i = 0; i < sourceOutput.pivots().size(); i++) {
            SwingPivot sourcePivot = sourceOutput.pivots().get(i);
            SwingPivot followUpPivot = followUpOutput.pivots().get(i);
            assertEquals(sourcePivot.index(), followUpPivot.index());
            assertEquals(sourcePivot.type(), followUpPivot.type());
            assertEquals(sourcePivot.price().doubleValue() + TRANSLATION, followUpPivot.price().doubleValue(),
                    TOLERANCE);
        }

        for (int i = 0; i < sourceOutput.swings().size(); i++) {
            var sourceSwing = sourceOutput.swings().get(i);
            var followUpSwing = followUpOutput.swings().get(i);
            assertEquals(sourceSwing.fromIndex(), followUpSwing.fromIndex());
            assertEquals(sourceSwing.toIndex(), followUpSwing.toIndex());
            assertEquals(sourceSwing.degree(), followUpSwing.degree());
            assertEquals(sourceSwing.fromPrice().doubleValue() + TRANSLATION,
                    followUpSwing.fromPrice().doubleValue(), TOLERANCE);
            assertEquals(sourceSwing.toPrice().doubleValue() + TRANSLATION,
                    followUpSwing.toPrice().doubleValue(), TOLERANCE);
        }
    }

    private static BarSeries generateFollowUp(BarSeries source) {
        if (source.isEmpty()) {
            return emptySeries();
        }

        List<Bar> translatedBars = new ArrayList<>();
        Num shift = source.numFactory().numOf(TRANSLATION);
        for (int index = source.getBeginIndex(); index <= source.getEndIndex(); index++) {
            Bar bar = source.getBar(index);
            Num volume = bar.getVolume();
            translatedBars.add(new BaseBar(bar.getTimePeriod(), bar.getBeginTime(), bar.getEndTime(),
                    bar.getOpenPrice().plus(shift), bar.getHighPrice().plus(shift), bar.getLowPrice().plus(shift),
                    bar.getClosePrice().plus(shift), volume,
                    bar.getAmount().plus(shift.multipliedBy(volume)), bar.getTrades()));
        }
        return new BaseBarSeries("translated", translatedBars);
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeries("empty", new ArrayList<>());
    }

    private static BarSeries trimmedSeries(double[] closes) {
        BaseBarSeries result = (BaseBarSeries) series(closes);
        result.setMaximumBarCount(7);
        return result;
    }

    private static BarSeries series(double[] closes) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            Num close = DecimalNum.valueOf(closes[i]);
            Num open = DecimalNum.valueOf(closes[i] - 0.1);
            Num high = DecimalNum.valueOf(closes[i] + 0.5);
            Num low = DecimalNum.valueOf(closes[i] - 0.5);
            Num volume = DecimalNum.valueOf(10 + i);
            Instant end = BASE_TIME.plus(PERIOD.multipliedBy(i + 1L));
            Instant begin = end.minus(PERIOD);
            bars.add(new BaseBar(PERIOD, begin, end, open, high, low, close, volume,
                    close.multipliedBy(volume), i + 1L));
        }
        return new BaseBarSeries("source", bars);
    }

    @Test
    void EMPTY_SERIES_variation1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(3), emptySeries(), -100, ElliottDegree.MINOR);
    }

    @Test
    void NONEMPTY_BELOW_MINIMUM_CANDIDATE_LENGTH_variation1() {
        verify(new double[] { 10, 11, 12, 13, 14, 15 }, 5, ElliottDegree.MINOR);
    }

    @Test
    void INDEX_BEFORE_BEGIN_CLAMPS_TO_BEGIN_variation1() {
        verify(new double[] { 10, 12, 14, 16, 18, 20, 22 }, -1, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void INDEX_AFTER_END_CLAMPS_TO_END_variation1() {
        verify(new double[] { 1, 2, 3, 2, 1, 0, -1, -2, -3 }, Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    void EXACT_MINIMUM_LENGTH_SINGLE_HIGH_CANDIDATE_variation1() {
        verify(new double[] { 1, 2, 3, 2, 1, 0, -1 }, 6, ElliottDegree.PRIMARY);
    }

    @Test
    void EXACT_MINIMUM_LENGTH_SINGLE_LOW_CANDIDATE_variation1() {
        verify(new double[] { 3, 2, 1, 2, 3, 4, 5 }, 6, ElliottDegree.PRIMARY);
    }

    @Test
    void FLAT_CLOSES_SKIP_SLOPE_CHANGE_GATE_variation1() {
        verify(new double[] { 7, 7, 7, 7, 7, 7, 7 }, 6, ElliottDegree.MINUTE);
    }

    @Test
    void MONOTONE_INCREASING_CLOSES_SKIP_DIRECTION_GATE_variation1() {
        verify(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 8, ElliottDegree.MINUTE);
    }

    @Test
    void SAME_SIGN_SLOPES_WITH_LARGE_CHANGE_variation1() {
        verify(new double[] { 1, 2, 3, 6, 10, 15, 21 }, 6, ElliottDegree.MINUETTE);
    }

    @Test
    void POSITIVE_TO_NEGATIVE_BELOW_MIN_SLOPE_CHANGE_variation1() {
        verify(new double[] { 10, 10.1, 10.2, 10.1, 10.0, 9.9, 9.8 }, 100, ElliottDegree.MINUETTE);
    }

    @Test
    void POSITIVE_TO_NEGATIVE_AT_MIN_SLOPE_CHANGE_variation1() {
        verify(new double[] { 2, 4, 6, 4, 2, 0, -2 }, 6, ElliottDegree.CYCLE);
    }

    @Test
    void NEGATIVE_TO_POSITIVE_AT_MIN_SLOPE_CHANGE_variation1() {
        verify(new double[] { 6, 4, 2, 4, 6, 8, 10 }, 6, ElliottDegree.CYCLE);
    }

    @Test
    void HIGH_REJECTED_BY_ZERO_PERSISTENCE_SLOPE_variation1() {
        verify(new double[] { 1, 2, 3, 2, 1, 1, 1, 1 }, 7, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    void LOW_REJECTED_BY_ZERO_PERSISTENCE_SLOPE_variation1() {
        verify(new double[] { 3, 2, 1, 2, 3, 3, 3, 3 }, 7, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    void HIGH_REJECTED_BY_POSITIVE_PERSISTENCE_variation1() {
        verify(new double[] { 1, 2, 3, 2, 1, 2, 3, 4 }, 7, ElliottDegree.MINOR);
    }

    @Test
    void LOW_REJECTED_BY_NEGATIVE_PERSISTENCE_variation1() {
        verify(new double[] { 3, 2, 1, 2, 3, 2, 1, 0 }, 7, ElliottDegree.MINOR);
    }

    @Test
    void CONFIRMED_HIGH_EXTREME_AT_START_variation1() {
        verify(new double[] { 1, 3, 5, 3, 2, 1, 0 }, 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void CONFIRMED_HIGH_EXTREME_IN_INTERIOR_variation1() {
        verify(new double[] { 1, 2, 4, 3, 1, 0, -2 }, 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void CONFIRMED_HIGH_EXTREME_AT_END_variation1() {
        verify(new double[] { 1, 2, 3, 4, 0, -1, -2 }, 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void CONFIRMED_LOW_EXTREME_AT_START_variation1() {
        verify(new double[] { 5, 3, 1, 2, 3, 4, 5 }, 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void CONFIRMED_LOW_EXTREME_IN_INTERIOR_variation1() {
        verify(new double[] { 6, 4, 2, 1, 3, 4, 6 }, 6, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void HIGH_TIED_EXTREME_KEEPS_EARLIEST_INDEX_variation1() {
        verify(new double[] { 1, 3, 5, 5, 1, 0, -1 }, 6, ElliottDegree.MINOR);
    }

    @Test
    void LOW_TIED_EXTREME_KEEPS_EARLIEST_INDEX_variation1() {
        verify(new double[] { 5, 3, 1, 1, 4, 5, 6 }, 6, ElliottDegree.MINOR);
    }

    @Test
    void FIRST_PIVOT_ACCEPTED_WITH_FINITE_ATR_variation1() {
        verify(new double[] { 10, 12, 14, 11, 8, 5, 2 }, 6, ElliottDegree.PRIMARY);
    }

    @Test
    void ALTERNATING_PIVOTS_PASS_ATR_MAGNITUDE_variation1() {
        verify(new double[] { 1, 2, 3, 2, 1, 0, -1, 0, 2, 4, 6, 5, 4, 3, 2 }, 14,
                ElliottDegree.PRIMARY);
    }

    @Test
    void ALTERNATING_PIVOT_EXACTLY_AT_ATR_THRESHOLD_variation1() {
        verify(new double[] { 10, 12, 14, 12, 10, 8, 6, 8, 10, 12, 14 }, 10, ElliottDegree.MINUTE);
    }

    @Test
    void ALTERNATING_PIVOT_BELOW_ATR_THRESHOLD_variation1() {
        verify(new double[] { 10, 11, 12, 11, 10, 9, 8, 9, 10, 11, 12 }, 10, ElliottDegree.MINUTE);
    }

    @Test
    void STRONGER_CONSECUTIVE_HIGH_REPLACES_PREVIOUS_HIGH_variation1() {
        verify(new double[] { 1, 3, 5, 3, 1, 0, -1, 1, 4, 7, 5, 3, 1 }, 12, ElliottDegree.CYCLE);
    }

    @Test
    void WEAKER_CONSECUTIVE_HIGH_IS_RETAINED_OUT_variation1() {
        verify(new double[] { 1, 4, 7, 4, 1, 0, -1, 1, 3, 5, 3, 1, 0 }, 12, ElliottDegree.CYCLE);
    }

    @Test
    void LOWER_CONSECUTIVE_LOW_REPLACES_PREVIOUS_LOW_variation1() {
        verify(new double[] { 7, 4, 1, 3, 5, 7, 8, 6, 3, 0, 2, 4, 6 }, 12, ElliottDegree.CYCLE);
    }

    @Test
    void HIGHER_CONSECUTIVE_LOW_IS_RETAINED_OUT_variation1() {
        verify(new double[] { 7, 4, 1, 3, 5, 7, 8, 6, 4, 2, 4, 6, 8 }, 12, ElliottDegree.CYCLE);
    }

    @Test
    void THREE_ALTERNATING_PIVOTS_CREATE_TWO_SWINGS_variation1() {
        verify(new double[] { 1, 3, 5, 3, 1, -1, -3, -1, 2, 5, 8, 6, 4, 2, 0 }, 14,
                ElliottDegree.GRAND_SUPERCYCLE);
    }
}
