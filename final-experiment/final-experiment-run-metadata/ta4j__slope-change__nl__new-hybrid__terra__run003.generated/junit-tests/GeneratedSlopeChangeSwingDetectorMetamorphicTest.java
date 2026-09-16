import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicTest {

    private static final double TRANSLATION = 100.0;
    private static final double TOLERANCE = 1.0e-9;
    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    @Test
    void empty_series_variation1() {
        verify(new double[0], config(2, 1, 2, 0.0, 0.0), -7, ElliottDegree.MINOR);
    }

    @Test
    void evaluation_before_first_candidate_variation1() {
        verify(new double[] {10, 11, 12}, config(2, 1, 2, 0.0, 0.0), -1, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void exactly_one_candidate_horizon_variation1() {
        verify(new double[] {10, 14, 11, 8}, config(2, 1, 2, 0.0, 0.0), 3, ElliottDegree.MINOR);
    }

    @Test
    void index_above_end_clamps_to_end_variation1() {
        verify(new double[] {10, 14, 12, 9, 7, 6}, config(2, 1, 2, 0.0, 0.0), 99, ElliottDegree.PRIMARY);
    }

    @Test
    void index_below_begin_clamps_to_begin_variation1() {
        verify(new double[] {10, 14, 12, 9}, config(2, 1, 2, 0.0, 0.0), -3, ElliottDegree.CYCLE);
    }

    @Test
    void interior_evaluation_prefix_only_variation1() {
        verify(new double[] {10, 14, 12, 9, 7, 6, 12, 16, 13, 9, 6, 5},
                config(2, 1, 2, 0.0, 0.0), 7, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void window_one_zero_denominator_variation1() {
        verify(new double[] {10, 11, 9, 8, 7}, config(1, 1, 1, 0.0, 0.0), 4, ElliottDegree.MINUETTE);
    }

    @Test
    void flat_closes_variation1() {
        verify(new double[] {10, 10, 10, 10, 10, 10}, config(2, 1, 2, 0.0, 0.0), 5, ElliottDegree.MINUTE);
    }

    @Test
    void equal_nonzero_slopes_variation1() {
        verify(new double[] {10, 12, 14, 16, 18, 20}, config(2, 1, 2, 0.1, 0.0), 5, ElliottDegree.MINOR);
    }

    @Test
    void subthreshold_slope_change_variation1() {
        verify(new double[] {10.0, 10.3, 10.1, 9.9, 9.8}, config(2, 1, 2, 1.0, 0.0), 4,
                ElliottDegree.SUB_MINUETTE);
    }

    @Test
    void same_sign_slope_change_variation1() {
        verify(new double[] {10, 14, 15, 16, 16.5, 17}, config(2, 1, 2, 0.5, 0.0), 5,
                ElliottDegree.MINUETTE);
    }

    @Test
    void high_persistence_failure_variation1() {
        verify(new double[] {10, 14, 12, 13, 15, 16}, config(2, 2, 2, 0.0, 0.0), 5, ElliottDegree.MINOR);
    }

    @Test
    void low_persistence_failure_variation1() {
        verify(new double[] {14, 10, 12, 11, 9, 8}, config(2, 2, 2, 0.0, 0.0), 5, ElliottDegree.MINOR);
    }

    @Test
    void first_confirmed_high_variation1() {
        verify(new double[] {10, 14, 12, 9, 7, 6}, config(2, 1, 2, 0.0, 0.0), 5,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void first_confirmed_high_variation2() {
        verify(new double[] {20, 25, 22, 18, 15, 13, 12}, config(2, 1, 2, 0.0, 0.0), 6,
                ElliottDegree.PRIMARY);
    }

    @Test
    void first_confirmed_low_variation1() {
        verify(new double[] {14, 10, 12, 15, 18, 20}, config(2, 1, 2, 0.0, 0.0), 5,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void first_confirmed_low_variation2() {
        verify(new double[] {30, 25, 27, 31, 34, 36, 38}, config(2, 1, 2, 0.0, 0.0), 6,
                ElliottDegree.PRIMARY);
    }

    @Test
    void high_extreme_earliest_tie_variation1() {
        verify(new double[] {10, 14, 14, 9, 7, 6}, config(2, 1, 2, 0.0, 0.0), 5, ElliottDegree.MINOR);
    }

    @Test
    void high_extreme_earliest_tie_variation2() {
        verify(new double[] {50, 55, 55, 49, 46, 43, 40}, config(2, 1, 2, 0.0, 0.0), 6,
                ElliottDegree.CYCLE);
    }

    @Test
    void low_extreme_earliest_tie_variation1() {
        verify(new double[] {14, 10, 10, 15, 18, 21}, config(2, 1, 2, 0.0, 0.0), 5, ElliottDegree.MINOR);
    }

    @Test
    void low_extreme_earliest_tie_variation2() {
        verify(new double[] {24, 20, 20, 26, 29, 31, 34}, config(2, 1, 2, 0.0, 0.0), 6,
                ElliottDegree.CYCLE);
    }

    @Test
    void zero_atr_multiplier_variation1() {
        verify(new double[] {10, 14, 11, 8, 10, 14, 11, 7, 10, 15, 12, 8},
                config(2, 1, 2, 0.0, 0.0), 11, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void atr_reversal_below_threshold_variation1() {
        verify(new double[] {10, 14, 12, 9, 10, 12, 11, 10}, config(2, 1, 2, 0.0, 10.0), 7,
                ElliottDegree.MINOR);
    }

    @Test
    void atr_reversal_below_threshold_variation2() {
        verify(new double[] {30, 34, 32, 29, 30, 32, 31, 30, 29}, config(2, 1, 2, 0.0, 8.0), 8,
                ElliottDegree.PRIMARY);
    }

    @Test
    void atr_reversal_equal_threshold_variation1() {
        verify(new double[] {10, 20, 15, 10, 15, 20, 15, 10}, config(2, 1, 1, 0.0, 1.0), 7,
                ElliottDegree.MINOR);
    }

    @Test
    void nonfinite_atr_rejects_later_pivot_variation1() {
        verify(new double[] {10, 14, 11, 8, 10, 13, 11, 9}, config(2, 1, 20, 0.0, 1.0), 7,
                ElliottDegree.MINUTE);
    }

    @Test
    void alternating_high_low_swings_variation1() {
        verify(new double[] {10, 14, 11, 8, 10, 14, 11, 7, 10, 15, 12, 8},
                config(2, 1, 2, 0.0, 0.0), 11, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void alternating_high_low_swings_variation2() {
        verify(new double[] {50, 55, 52, 48, 51, 56, 53, 47, 50, 57, 54, 49},
                config(2, 1, 2, 0.0, 0.0), 11, ElliottDegree.PRIMARY);
    }

    @Test
    void replace_prior_high_with_higher_high_variation1() {
        verify(new double[] {10, 14, 11, 8, 9, 15, 12, 9, 8}, config(2, 1, 2, 0.0, 0.0), 8,
                ElliottDegree.MINOR);
    }

    @Test
    void replace_prior_high_with_higher_high_variation2() {
        verify(new double[] {20, 25, 22, 18, 19, 28, 24, 20, 17}, config(2, 1, 2, 0.0, 0.0), 8,
                ElliottDegree.CYCLE);
    }

    @Test
    void replace_prior_low_with_lower_low_variation1() {
        verify(new double[] {14, 10, 12, 15, 13, 8, 10, 13, 15}, config(2, 1, 2, 0.0, 0.0), 8,
                ElliottDegree.MINOR);
    }

    @Test
    void replace_prior_low_with_lower_low_variation2() {
        verify(new double[] {30, 24, 27, 31, 28, 20, 23, 27, 30}, config(2, 1, 2, 0.0, 0.0), 8,
                ElliottDegree.CYCLE);
    }

    @Test
    void retain_same_type_non_extreme_pivot_variation1() {
        verify(new double[] {10, 16, 13, 9, 10, 14, 12, 8, 7}, config(2, 1, 2, 0.0, 0.0), 8,
                ElliottDegree.PRIMARY);
    }

    @Test
    void nonfinite_close_skips_candidate_variation1() {
        verify(new double[] {10, 12, 11, 10, 9, 8}, config(3, 1, 2, 0.0, 0.0), 5, ElliottDegree.MINOR);
    }

    @Test
    void nonzero_begin_index_variation1() {
        BarSeries source = retainedSeries(new double[] {20, 24, 21, 18, 16, 15}, 2);
        assertMetamorphicRelationFor(source, config(2, 1, 2, 0.0, 0.0), source.getEndIndex(),
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void distinct_elliott_degrees_variation1() {
        verify(new double[] {10, 14, 11, 8, 10, 14, 11, 7, 10, 15, 12, 8},
                config(2, 1, 2, 0.0, 0.0), 11, ElliottDegree.MINOR);
    }

    @Test
    void distinct_elliott_degrees_variation2() {
        verify(new double[] {10, 14, 11, 8, 10, 14, 11, 7, 10, 15, 12, 8},
                config(2, 1, 2, 0.0, 0.0), 11, ElliottDegree.GRAND_SUPERCYCLE);
    }

    private static SlopeChangeConfig config(int window, int confirmationBars, int atrPeriod,
            double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal);
    }

    private void verify(double[] closes, SlopeChangeConfig config, int evaluationIndex, ElliottDegree degree) {
        assertMetamorphicRelationFor(series(closes), config, evaluationIndex, degree);
    }

    private void assertMetamorphicRelationFor(BarSeries source, SlopeChangeConfig config, int evaluationIndex,
            ElliottDegree degree) {
        SlopeChangeSwingDetector sourceDetector = new SlopeChangeSwingDetector(config);
        SlopeChangeSwingDetector followUpDetector = new SlopeChangeSwingDetector(config);
        SwingDetectorResult sourceOutput = sourceDetector.detect(source, evaluationIndex, degree);
        SwingDetectorResult followUpOutput = followUpDetector.detect(generateFollowUp(source), evaluationIndex, degree);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private BarSeries series(double[] closes) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            bars.add(bar(closes[i], i));
        }
        return new BaseBarSeries("slope-change-source", bars);
    }

    private BarSeries retainedSeries(double[] closes, int discardedBars) {
        BaseBarSeries result = new BaseBarSeries("slope-change-retained", new ArrayList<>());
        result.setMaximumBarCount(closes.length);
        for (int i = 0; i < discardedBars; i++) {
            result.addBar(bar(1.0, i));
        }
        for (int i = 0; i < closes.length; i++) {
            result.addBar(bar(closes[i], discardedBars + i));
        }
        return result;
    }

    private Bar bar(double close, int position) {
        Num value = DecimalNum.valueOf(close);
        Num two = DecimalNum.valueOf(2.0);
        Num volume = DecimalNum.valueOf(10.0);
        return new BaseBar(PERIOD, BASE_TIME.plus(PERIOD.multipliedBy(position)),
                BASE_TIME.plus(PERIOD.multipliedBy(position + 1L)), value, value.plus(two), value.minus(two),
                value, volume, value.multipliedBy(volume), 1L);
    }

    private BarSeries generateFollowUp(BarSeries source) {
        List<Bar> translated = new ArrayList<>();
        Num translation = source.numFactory().numOf(TRANSLATION);
        for (int index = source.getBeginIndex(); index <= source.getEndIndex(); index++) {
            Bar original = source.getBar(index);
            translated.add(new BaseBar(original.getTimePeriod(), original.getBeginTime(), original.getEndTime(),
                    original.getOpenPrice().plus(translation), original.getHighPrice().plus(translation),
                    original.getLowPrice().plus(translation), original.getClosePrice().plus(translation),
                    original.getVolume(),
                    original.getAmount().plus(translation.multipliedBy(original.getVolume())),
                    original.getTrades()));
        }
        if (source.isEmpty() || source.getBeginIndex() == 0) {
            return new BaseBarSeries("slope-change-follow-up", translated);
        }

        BaseBarSeries result = new BaseBarSeries("slope-change-follow-up", new ArrayList<>());
        result.setMaximumBarCount(translated.size());
        Bar padding = translated.get(0);
        for (int i = 0; i < source.getBeginIndex(); i++) {
            result.addBar(padding);
        }
        for (Bar translatedBar : translated) {
            result.addBar(translatedBar);
        }
        return result;
    }

    private void assertMetamorphicRelation(SwingDetectorResult source, SwingDetectorResult followUp) {
        assertEquals(source.pivots().size(), followUp.pivots().size());
        assertEquals(source.swings().size(), followUp.swings().size());

        for (int i = 0; i < source.pivots().size(); i++) {
            SwingPivot sourcePivot = source.pivots().get(i);
            SwingPivot followUpPivot = followUp.pivots().get(i);
            assertEquals(sourcePivot.index(), followUpPivot.index());
            assertEquals(sourcePivot.type(), followUpPivot.type());
            assertEquals(sourcePivot.price().doubleValue() + TRANSLATION, followUpPivot.price().doubleValue(),
                    TOLERANCE);
        }

        for (int i = 0; i < source.swings().size(); i++) {
            Object sourceSwing = source.swings().get(i);
            Object followUpSwing = followUp.swings().get(i);
            assertEquals(property(sourceSwing, "fromIndex"), property(followUpSwing, "fromIndex"));
            assertEquals(property(sourceSwing, "toIndex"), property(followUpSwing, "toIndex"));
            assertEquals(property(sourceSwing, "degree"), property(followUpSwing, "degree"));
            assertEquals(((Num) property(sourceSwing, "fromPrice")).doubleValue() + TRANSLATION,
                    ((Num) property(followUpSwing, "fromPrice")).doubleValue(), TOLERANCE);
            assertEquals(((Num) property(sourceSwing, "toPrice")).doubleValue() + TRANSLATION,
                    ((Num) property(followUpSwing, "toPrice")).doubleValue(), TOLERANCE);
        }
    }

    private Object property(Object target, String methodName) {
        try {
            Method method = target.getClass().getMethod(methodName);
            return method.invoke(target);
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError("Unable to read swing property " + methodName, exception);
        }
    }
}
