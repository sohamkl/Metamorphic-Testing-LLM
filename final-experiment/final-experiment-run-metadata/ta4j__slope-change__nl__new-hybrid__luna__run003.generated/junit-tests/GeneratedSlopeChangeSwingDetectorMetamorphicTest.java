import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.ta4j.core.num.NaN;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicTest {

    private static final double TRANSLATION = 100.0;
    private static final double EPSILON = 1e-8;
    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    @Test
    void EMPTY_SERIES_1_empty() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(3),
                new BaseBarSeries("empty", new ArrayList<>()),
                -10,
                ElliottDegree.MINOR);
    }

    @Test
    void SHORT_SERIES_BEFORE_FIRST_CANDIDATE_1_short() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 2, 2, 0.0, 0.5)),
                constantSeries(3, 20.0),
                0,
                ElliottDegree.MINOR);
    }

    @Test
    void EXACT_SINGLE_CANDIDATE_1_singleCandidate() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.0, 0.0)),
                waveSeries(6, 20.0),
                4,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMP_1_belowBegin() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.0, 0.0)),
                waveSeries(24, 20.0),
                -100,
                ElliottDegree.CYCLE);
    }

    @Test
    void INDEX_ABOVE_END_CLAMP_1_aboveEnd() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.0, 0.0)),
                waveSeries(24, 20.0),
                1000,
                ElliottDegree.PRIMARY);
    }

    @Test
    void BEGIN_INDEX_NOT_ZERO_1_derivedSeries() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.0, 0.0)),
                waveSeries(36, 20.0),
                35,
                ElliottDegree.MINOR);
    }

    @Test
    void MONOTONIC_UPWARD_NO_REVERSAL_1_upward() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 2, 2, 0.0, 0.5)),
                monotonicSeries(18, true),
                17,
                ElliottDegree.MINOR);
    }

    @Test
    void MONOTONIC_DOWNWARD_NO_REVERSAL_1_downward() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 2, 2, 0.0, 0.5)),
                monotonicSeries(18, false),
                17,
                ElliottDegree.MINOR);
    }

    @Test
    void ZERO_SLOPE_TRANSITION_1_constant() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.0, 0.0)),
                constantSeries(15, 30.0),
                14,
                ElliottDegree.MINOR);
    }

    @Test
    void SLOPE_CHANGE_BELOW_THRESHOLD_1_threshold() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 1000.0, 0.0)),
                waveSeries(20, 30.0),
                19,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void CONFIRMED_HIGH_1_high() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.01, 0.0)),
                waveSeries(24, 20.0),
                23,
                ElliottDegree.MINOR);
    }

    @Test
    void CONFIRMED_LOW_1_low() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.01, 0.0)),
                invertedWaveSeries(24, 50.0),
                23,
                ElliottDegree.MINOR);
    }

    @Test
    void PERSISTENCE_FAILS_IMMEDIATELY_1_immediate() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 2, 2, 0.01, 0.0)),
                alternatingSeries(24),
                23,
                ElliottDegree.MINOR);
    }

    @Test
    void PERSISTENCE_FAILS_AFTER_CONFIRMATION_1_late() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 3, 2, 0.01, 0.0)),
                alternatingSeries(30),
                29,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void TIED_EXTREME_RETains_FIRST_1_tied() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.01, 0.0)),
                tiedSeries(24),
                23,
                ElliottDegree.MINOR);
    }

    @Test
    void FIRST_PIVOT_FILTER_BYPASS_1_firstPivot() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.01, 1000.0)),
                waveSeries(30, 20.0),
                29,
                ElliottDegree.PRIMARY);
    }

    @Test
    void MAGNITUDE_FILTER_REJECTS_WEAK_REVERSAL_1_weak() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.01, 100.0)),
                alternatingSeries(30),
                29,
                ElliottDegree.MINOR);
    }

    @Test
    void MAGNITUDE_FILTER_ACCEPTS_REVERSAL_1_strong() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.01, 0.0)),
                alternatingSeries(30),
                29,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ATR_NONFINITE_REJECTS_LATER_PIVOT_1_atr() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 100, 0.01, 1.0)),
                alternatingSeries(30),
                29,
                ElliottDegree.MINOR);
    }

    @Test
    void SAME_TYPE_HIGH_REPLACEMENT_1_highReplacement() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(2, 1, 2, 0.01, 0.0)),
                waveSeries(30, 20.0),
                29,
                ElliottDegree.MINOR);
    }

    @Test
    void SAME_TYPE_LOW_REPLACEMENT_1_lowReplacement() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(2, 1, 2, 0.01, 0.0)),
                invertedWaveSeries(30, 50.0),
                29,
                ElliottDegree.MINOR);
    }

    @Test
    void SAME_TYPE_NONREPLACEMENT_1_nonreplacement() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.01, 0.0)),
                alternatingSeries(30),
                29,
                ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ALTERNATING_PIVOTS_AND_SWINGS_1_alternating() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(2, 1, 2, 0.01, 0.0)),
                alternatingSeries(36),
                35,
                ElliottDegree.CYCLE);
    }

    @Test
    void NONFINITE_CLOSE_IN_SLOPE_WINDOW_1_closeWindow() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.01, 0.0)),
                nonFiniteCloseSeries(24),
                23,
                ElliottDegree.MINOR);
    }

    @Test
    void NONFINITE_EXTREME_PRICE_1_extremeWindow() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.01, 0.0)),
                nonFiniteHighSeries(24),
                23,
                ElliottDegree.MINOR);
    }

    @Test
    void DEGREE_PROPAGATED_TO_SWINGS_1_degree() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(2, 1, 2, 0.01, 0.0)),
                alternatingSeries(36),
                35,
                ElliottDegree.GRAND_SUPERCYCLE);
    }

    @Test
    void NUM_IMPLEMENTATION_PRESERVED_1_numeric() {
        assertMetamorphicRelationFor(
                new SlopeChangeSwingDetector(config(3, 1, 2, 0.01, 0.0)),
                alternatingSeries(28),
                27,
                ElliottDegree.MINOR);
    }

    private static void assertMetamorphicRelationFor(
            SlopeChangeSwingDetector detector,
            BarSeries source,
            int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(source, index, degree);
        SwingDetectorResult followUpOutput =
                detector.detect(generateFollowUp(source), index, degree);
        assertMetamorphicRelation(sourceOutput, followUpOutput, degree);
    }

    private static void assertMetamorphicRelation(
            SwingDetectorResult source,
            SwingDetectorResult followUp,
            ElliottDegree degree) {
        assertNotNull(source);
        assertNotNull(followUp);
        assertEquals(source.pivots().size(), followUp.pivots().size());
        assertEquals(source.swings().size(), followUp.swings().size());

        for (int i = 0; i < source.pivots().size(); i++) {
            SwingPivot expected = source.pivots().get(i);
            SwingPivot actual = followUp.pivots().get(i);

            assertEquals(expected.index(), actual.index());
            assertEquals(expected.type(), actual.type());
            assertEquals(
                    expected.price().doubleValue() + TRANSLATION,
                    actual.price().doubleValue(),
                    EPSILON);
        }

        for (int i = 0; i < source.swings().size(); i++) {
            var expected = source.swings().get(i);
            var actual = followUp.swings().get(i);

            assertEquals(expected.fromIndex(), actual.fromIndex());
            assertEquals(expected.toIndex(), actual.toIndex());
            assertEquals(degree, expected.degree());
            assertEquals(expected.degree(), actual.degree());
            assertEquals(
                    expected.fromPrice().doubleValue() + TRANSLATION,
                    actual.fromPrice().doubleValue(),
                    EPSILON);
            assertEquals(
                    expected.toPrice().doubleValue() + TRANSLATION,
                    actual.toPrice().doubleValue(),
                    EPSILON);
        }
    }

    private static BarSeries generateFollowUp(BarSeries source) {
        List<Bar> bars = new ArrayList<>();

        for (int i = source.getBeginIndex(); i <= source.getEndIndex(); i++) {
            Bar bar = source.getBar(i);
            Num translation = source.numFactory().numOf(TRANSLATION);
            Num volume = bar.getVolume();
            Num amount = bar.getAmount().plus(translation.multipliedBy(volume));

            bars.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    bar.getOpenPrice().plus(translation),
                    bar.getHighPrice().plus(translation),
                    bar.getLowPrice().plus(translation),
                    bar.getClosePrice().plus(translation),
                    volume,
                    amount,
                    bar.getTrades()));
        }

        return new BaseBarSeries(source.getName(), bars);
    }

    private static SlopeChangeConfig config(
            int window,
            int confirmationBars,
            int atrPeriod,
            double minSlopeChange,
            double minAtrReversal) {
        return new SlopeChangeConfig(
                window,
                confirmationBars,
                atrPeriod,
                minSlopeChange,
                minAtrReversal);
    }

    private static BarSeries constantSeries(int count, double value) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            bars.add(bar(i, value, value + 1.0, value - 1.0));
        }
        return new BaseBarSeries("constant", bars);
    }

    private static BarSeries monotonicSeries(int count, boolean upward) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double close = upward ? 10.0 + i : 100.0 - i;
            bars.add(bar(i, close, close + 1.0, close - 1.0));
        }
        return new BaseBarSeries("monotonic", bars);
    }

    private static BarSeries waveSeries(int count, double base) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double phase = i % 12;
            double close = base
                    + (phase <= 6 ? phase * 2.0 : (12.0 - phase) * 2.0)
                    + (i / 12) * 0.25;
            bars.add(bar(i, close, close + 1.5, close - 1.5));
        }
        return new BaseBarSeries("wave", bars);
    }

    private static BarSeries invertedWaveSeries(int count, double base) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double phase = i % 12;
            double close = base
                    - (phase <= 6 ? phase * 2.0 : (12.0 - phase) * 2.0)
                    - (i / 12) * 0.25;
            bars.add(bar(i, close, close + 1.5, close - 1.5));
        }
        return new BaseBarSeries("inverted-wave", bars);
    }

    private static BarSeries alternatingSeries(int count) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int phase = i / 3;
            double within = i % 3;
            double close = phase % 2 == 0
                    ? within * 3.0
                    : (2.0 - within) * 3.0;
            close += 20.0 + phase * 0.5;
            bars.add(bar(i, close, close + 1.0, close - 1.0));
        }
        return new BaseBarSeries("alternating", bars);
    }

    private static BarSeries tiedSeries(int count) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double phase = i % 12;
            double close = 20.0 + (phase <= 6 ? phase : 12.0 - phase);
            double high = phase == 5 || phase == 6 ? 100.0 : close + 1.0;
            bars.add(bar(i, close, high, close - 1.0));
        }
        return new BaseBarSeries("tied", bars);
    }

    private static BarSeries nonFiniteCloseSeries(int count) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (i == 4) {
                bars.add(nonFiniteBar(i));
            } else {
                double close = 20.0 + i;
                bars.add(bar(i, close, close + 1.0, close - 1.0));
            }
        }
        return new BaseBarSeries("nonfinite-close", bars);
    }

    private static BarSeries nonFiniteHighSeries(int count) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double phase = i % 8;
            double close = 20.0 + (phase <= 4 ? phase : 8.0 - phase);
            if (i == 5) {
                bars.add(nonFiniteHighBar(i, close));
            } else {
                bars.add(bar(i, close, close + 1.0, close - 1.0));
            }
        }
        return new BaseBarSeries("nonfinite-high", bars);
    }

    private static Bar bar(int index, double close, double high, double low) {
        return new BaseBar(
                PERIOD,
                BASE_TIME.plusSeconds(index * 60L),
                BASE_TIME.plusSeconds((index + 1L) * 60L),
                close,
                Math.max(high, low),
                Math.min(low, high),
                close,
                100.0,
                1000.0,
                10L);
    }

    private static Bar nonFiniteBar(int index) {
        Num nan = NaN.NaN;
        return new BaseBar(
                PERIOD,
                BASE_TIME.plusSeconds(index * 60L),
                BASE_TIME.plusSeconds((index + 1L) * 60L),
                nan,
                nan,
                nan,
                nan,
                100.0,
                1000.0,
                10L);
    }

    private static Bar nonFiniteHighBar(int index, double close) {
        Num nan = NaN.NaN;
        return new BaseBar(
                PERIOD,
                BASE_TIME.plusSeconds(index * 60L),
                BASE_TIME.plusSeconds((index + 1L) * 60L),
                close,
                nan,
                close - 1.0,
                close,
                100.0,
                1000.0,
                10L);
    }
}
