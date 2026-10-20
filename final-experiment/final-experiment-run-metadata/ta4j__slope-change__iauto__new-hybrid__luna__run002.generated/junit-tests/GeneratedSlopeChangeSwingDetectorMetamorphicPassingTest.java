import java.time.Duration;
import java.time.Instant;
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
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static void verify(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(SwingDetectorResult source, SwingDetectorResult followUp) {
        if (source.pivots().size() != followUp.pivots().size()) {
            throw new AssertionError("Pivot counts differ");
        }
        for (int i = 0; i < source.pivots().size(); i++) {
            SwingPivot sourcePivot = source.pivots().get(i);
            SwingPivot followUpPivot = followUp.pivots().get(i);
            if (sourcePivot.index() != followUpPivot.index()) {
                throw new AssertionError("Pivot indices differ at position " + i);
            }
            if (sourcePivot.type() != followUpPivot.type()) {
                throw new AssertionError("Pivot types differ at position " + i);
            }
            assertTranslated(sourcePivot.price(), followUpPivot.price(), "pivot price", i);
        }
        if (source.swings().size() != followUp.swings().size()) {
            throw new AssertionError("Swing counts differ");
        }
        for (int i = 0; i < source.swings().size(); i++) {
            ElliottSwing sourceSwing = source.swings().get(i);
            ElliottSwing followUpSwing = followUp.swings().get(i);
            if (sourceSwing.fromIndex() != followUpSwing.fromIndex()) {
                throw new AssertionError("Swing start indices differ at position " + i);
            }
            if (sourceSwing.toIndex() != followUpSwing.toIndex()) {
                throw new AssertionError("Swing end indices differ at position " + i);
            }
            if (sourceSwing.degree() != followUpSwing.degree()) {
                throw new AssertionError("Swing degrees differ at position " + i);
            }
            assertTranslated(sourceSwing.fromPrice(), followUpSwing.fromPrice(), "swing start price", i);
            assertTranslated(sourceSwing.toPrice(), followUpSwing.toPrice(), "swing end price", i);
        }
    }

    private static void assertTranslated(Num source, Num followUp, String field, int position) {
        double expected = source.doubleValue() + 100.0;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(1.0e-9, Math.max(Math.abs(expected), Math.abs(actual)) * 1.0e-12);
        if (!Double.isFinite(expected) || !Double.isFinite(actual) || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Translated " + field + " differs at position " + position + ": expected " + expected + " but was " + actual);
        }
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeriesBuilder().withName("empty").build();
    }

    private static BarSeries risingSeries(int length) {
        BaseBarSeries series = new BaseBarSeriesBuilder().withName("rising").build();
        for (int i = 0; i < length; i++) {
            addBar(series, 10.0 + i, 1.0 + (i % 3));
        }
        return series;
    }

    private static BarSeries patternedSeries(int length, int variation) {
        BaseBarSeries series = new BaseBarSeriesBuilder().withName("pattern-" + variation).build();
        double[] wave = { 1.0, 2.0, 3.0, 4.0, 3.0, 2.0, 1.0, 2.0, 3.0, 5.0, 3.0, 2.0, 1.0, 3.0, 4.0, 2.0 };
        for (int i = 0; i < length; i++) {
            double close = wave[(i + variation) % wave.length] + variation * 0.01;
            double volume = 1.0 + (i % 4);
            addBar(series, close, volume);
        }
        return series;
    }

    private static BarSeries tiedSeries(int length) {
        BaseBarSeries series = new BaseBarSeriesBuilder().withName("tied").build();
        double[] wave = { 1.0, 2.0, 3.0, 4.0, 3.0, 2.0, 1.0, 2.0, 3.0, 4.0, 3.0, 2.0 };
        for (int i = 0; i < length; i++) {
            double close = wave[i % wave.length];
            double high = close + ((i % 6 == 2 || i % 6 == 3) ? 2.0 : 1.0);
            double low = close - 1.0;
            addBar(series, close, 1.0, high, low);
        }
        return series;
    }

    private static BarSeries variedVolumeSeries(int length) {
        BaseBarSeries series = new BaseBarSeriesBuilder().withName("varied-volume").build();
        double[] wave = { 1.0, 2.0, 3.0, 5.0, 3.0, 2.0, 1.0, 2.0, 4.0, 6.0, 3.0, 2.0 };
        for (int i = 0; i < length; i++) {
            double close = wave[i % wave.length];
            double volume = 0.5 + (i % 5) * 1.25;
            addBar(series, close, volume);
        }
        return series;
    }

    private static void addBar(BaseBarSeries series, double close, double volume) {
        addBar(series, close, volume, close + 1.0, close - 1.0);
    }

    private static void addBar(BaseBarSeries series, double close, double volume, double high, double low) {
        Instant begin = Instant.parse("2020-01-01T00:00:00Z").plus(Duration.ofMinutes(series.getBarCount()));
        Instant end = begin.plus(Duration.ofMinutes(1));
        Num openPrice = series.numFactory().numOf(close);
        Num highPrice = series.numFactory().numOf(high);
        Num lowPrice = series.numFactory().numOf(low);
        Num closePrice = series.numFactory().numOf(close);
        Num volumeValue = series.numFactory().numOf(volume);
        Num amountValue = series.numFactory().numOf(close * volume);
        Bar bar = new BaseBar(Duration.ofMinutes(1), begin, end, openPrice, highPrice, lowPrice, closePrice, volumeValue, amountValue, 1);
        series.addBar(bar);
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMP_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.01, 0.0)), patternedSeries(4, 1), -1, ElliottDegree.MINOR);
    }

    @Test
    void INDEX_AT_BEGIN_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)), patternedSeries(20, 2), 0, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void INDEX_ABOVE_END_CLAMP_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.1)), patternedSeries(36, 3), Integer.MAX_VALUE, ElliottDegree.PRIMARY);
    }

    @Test
    void NO_CANDIDATE_BEFORE_REQUIRED_LENGTH_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(4, 2, 3, 0.0, 0.0)), patternedSeries(5, 4), Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    void EXACT_SINGLE_CANDIDATE_WINDOW_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.01, 0.0)), patternedSeries(7, 5), Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    void SAME_SIGN_SLOPES_REJECTED_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)), risingSeries(24), 23, ElliottDegree.MINOR);
    }

    @Test
    void SLOPE_CHANGE_BELOW_THRESHOLD_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 1000.0, 0.0)), patternedSeries(30, 6), 15, ElliottDegree.MINOR);
    }

    @Test
    void HIGH_REVERSAL_PERSISTENCE_FAILS_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 3, 0.0, 0.0)), patternedSeries(30, 7), 29, ElliottDegree.MINOR);
    }

    @Test
    void LOW_REVERSAL_PERSISTENCE_FAILS_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 2, 3, 0.0, 0.0)), patternedSeries(30, 8), 29, ElliottDegree.MINOR);
    }

    @Test
    void HIGH_PIVOT_FIRST_ACCEPTED_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)), patternedSeries(30, 9), 29, ElliottDegree.MINOR);
    }

    @Test
    void LOW_PIVOT_FIRST_ACCEPTED_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)), patternedSeries(30, 10), 29, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void EXTREME_PIVOT_TIE_RETains_FIRST_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)), tiedSeries(30), 29, ElliottDegree.MINOR);
    }

    @Test
    void ATR_NONFINITE_REJECTS_PRIOR_PIVOT_CANDIDATE_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 100, 0.0, 0.5)), patternedSeries(30, 11), 29, ElliottDegree.MINOR);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASSES_FILTER_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 100, 0.0, 0.0)), patternedSeries(30, 12), Integer.MAX_VALUE, ElliottDegree.MINOR);
    }

    @Test
    void WEAK_REVERSAL_REJECTED_BY_ATR_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 100.0)), patternedSeries(30, 13), -1, ElliottDegree.MINOR);
    }

    @Test
    void STRONG_ALTERNATING_REVERSAL_ACCEPTED_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)), patternedSeries(45, 14), 44, ElliottDegree.PRIMARY);
    }

    @Test
    void STRONGER_SAME_TYPE_HIGH_REPLACES_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.01, 0.0)), patternedSeries(45, 15), 44, ElliottDegree.MINOR);
    }

    @Test
    void STRONGER_SAME_TYPE_LOW_REPLACES_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.01, 0.0)), patternedSeries(45, 16), 44, ElliottDegree.MINOR);
    }

    @Test
    void WEAKER_SAME_TYPE_HIGH_KEPT_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.1)), patternedSeries(45, 17), Integer.MAX_VALUE, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void WEAKER_SAME_TYPE_LOW_KEPT_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.1)), patternedSeries(45, 18), -4, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void MULTIPLE_PIVOTS_AND_SWINGS_PRESERVE_DEGREE_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)), patternedSeries(60, 19), 59, ElliottDegree.CYCLE);
    }

    @Test
    void CAUSAL_PREFIX_OUTPUT_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)), patternedSeries(60, 20), 20, ElliottDegree.MINOR);
    }

    @Test
    void CAUSAL_PREFIX_OUTPUT_2() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.1)), patternedSeries(60, 21), 59, ElliottDegree.MINOR);
    }

    @Test
    void TRANSLATION_WITH_VOLUME_AND_AMOUNT_UPDATE_1() {
        verify(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.0, 0.0)), variedVolumeSeries(48), 47, ElliottDegree.PRIMARY);
    }
}
