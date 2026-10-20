import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;
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

    private static void verify(
            SlopeChangeSwingDetector detector,
            BarSeries source,
            int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput =
                detector.detect(source, index, degree);

        Object[] followUp =
                SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                        detector, source, index, degree);

        SlopeChangeSwingDetector followUpDetector =
                (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput =
                followUpDetector.detect(
                        followUpSeries, followUpIndex, followUpDegree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static ElliottDegree degree(String name) {
        return ElliottDegree.valueOf(name);
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

    private static BarSeries series(double... closes) {
        return series(closes, false, false, false, false, false);
    }

    private static BarSeries series(
            double[] closes,
            boolean zeroVolume,
            boolean nonFiniteHigh,
            boolean nonFiniteLow,
            boolean nonFiniteClose,
            boolean largeValues) {
        BaseBarSeriesBuilder builder = new BaseBarSeriesBuilder()
                .withName("source");

        if (nonFiniteHigh || nonFiniteLow || nonFiniteClose) {
            builder.withNumFactory(DoubleNumFactory.getInstance());
        }

        BarSeries result = builder.build();
        Instant start = Instant.parse("2020-01-01T00:00:00Z");

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            if (largeValues) {
                close += 1.0e12;
            }

            double open = close;
            double high = close + 0.75;
            double low = close - 0.75;

            if (nonFiniteHigh && i == 8) {
                high = Double.NaN;
            }
            if (nonFiniteLow && i == 8) {
                low = Double.NaN;
            }
            if (nonFiniteClose && i == 6) {
                close = Double.NaN;
                open = Double.NaN;
            }

            Num volume = result.numFactory().numOf(zeroVolume ? 0.0 : 1.0);
            Num amount = result.numFactory().numOf(0.0);

            result.addBar(new BaseBar(
                    Duration.ofMinutes(1),
                    start.plusSeconds(i * 60L),
                    start.plusSeconds((i + 1L) * 60L),
                    result.numFactory().numOf(open),
                    result.numFactory().numOf(high),
                    result.numFactory().numOf(low),
                    result.numFactory().numOf(close),
                    volume,
                    amount,
                    0L));
        }
        return result;
    }

    private static BarSeries generatedSeries(
            int length,
            double base,
            double amplitude,
            int period) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % period;
            int half = period / 2;
            values[i] = base
                    + amplitude * (phase < half ? phase : period - phase);
        }
        return series(values);
    }

    private static BarSeries waveSeries(int length) {
        return generatedSeries(length, 100.0, 20.0, 12);
    }

    private static BarSeries invertedWaveSeries(int length) {
        return generatedSeries(length, 100.0, -20.0, 12);
    }

    private static BarSeries flatSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 100.0;
        }
        return series(values);
    }

    private static BarSeries ascendingSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 100.0 + i;
        }
        return series(values);
    }

    private static BarSeries descendingSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 200.0 - i;
        }
        return series(values);
    }

    private static BarSeries alternatingSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 8;
            values[i] = 100.0 + (phase < 4 ? phase : 8 - phase);
        }
        return series(values);
    }

    private static BarSeries boundedWaveSeries(int length) {
        return generatedSeries(length, 100.0, 5.0, 10);
    }

    private static BarSeries repeatedHighSeries(int length) {
        return generatedSeries(length, 100.0, 30.0, 16);
    }

    private static BarSeries repeatedLowSeries(int length) {
        return generatedSeries(length, 100.0, -30.0, 16);
    }

    private static BarSeries tiedHighSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 8;
            values[i] = phase < 4
                    ? 100.0 + phase
                    : 104.0 - (phase - 4);
        }
        return series(values);
    }

    private static BarSeries tiedLowSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 8;
            values[i] = phase < 4
                    ? 100.0 - phase
                    : 97.0 + (phase - 4);
        }
        return series(values);
    }

    private static BarSeries largeSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 14;
            int half = 7;
            values[i] = 1.0e6
                    + 1.0e6 * (phase < half ? phase : 14 - phase);
        }
        return series(values, false, false, false, false, true);
    }

    private static BarSeries zeroVolumeSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 12;
            values[i] = 100.0 + (phase < 6 ? phase : 12 - phase);
        }
        return series(values, true, false, false, false, false);
    }

    private static BarSeries zeroVolumeWaveSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 16;
            values[i] = 100.0 + (phase < 8 ? phase : 16 - phase);
        }
        return series(values, true, false, false, false, false);
    }

    private static BarSeries nonFiniteCloseSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 100.0 + (i % 9);
        }
        return series(values, false, false, false, true, false);
    }

    private static BarSeries nonFiniteHighSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 12;
            values[i] = 100.0 + (phase < 6 ? phase : 12 - phase);
        }
        return series(values, false, true, false, false, false);
    }

    private static BarSeries nonFiniteLowSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 12;
            values[i] = 100.0 - (phase < 6 ? phase : 12 - phase);
        }
        return series(values, false, false, true, false, false);
    }

    private static BarSeries mixedSeries(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            int phase = i % 20;
            if (phase < 5) {
                values[i] = 100.0;
            } else if (phase < 10) {
                values[i] = 100.0 + phase - 5;
            } else if (phase < 15) {
                values[i] = 105.0 - (phase - 10) * 2.0;
            } else {
                values[i] = 95.0 + (phase - 15) * 0.25;
            }
        }
        return series(values);
    }

    @Test
    void TOO_SHORT_FOR_CANDIDATE_default() {
        verify(new SlopeChangeSwingDetector(5), waveSeries(8), 4, degree("MINOR"));
    }

    @Test
    void TOO_SHORT_FOR_CANDIDATE_nonzeroAtr() {
        verify(new SlopeChangeSwingDetector(config(4, 2, 3, 0.0, 1.0)),
                invertedWaveSeries(5), 20, degree("PRIMARY"));
    }

    @Test
    void TOO_SHORT_FOR_CANDIDATE_zeroAtr() {
        verify(new SlopeChangeSwingDetector(config(8, 3, 3, 1.0, 0.0)),
                flatSeries(7), 100, degree("CYCLE"));
    }

    @Test
    void INDEX_CLAMP_BELOW_BEGIN_flat() {
        verify(new SlopeChangeSwingDetector(3), flatSeries(50), -10, degree("MINOR"));
    }

    @Test
    void INDEX_CLAMP_BELOW_BEGIN_reversal() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                waveSeries(60), -1, degree("INTERMEDIATE"));
    }

    @Test
    void INDEX_CLAMP_ABOVE_END_default() {
        verify(new SlopeChangeSwingDetector(3), waveSeries(70), 1000, degree("PRIMARY"));
    }

    @Test
    void INDEX_CLAMP_ABOVE_END_atr() {
        verify(new SlopeChangeSwingDetector(config(4, 2, 3, 0.0, 0.5)),
                alternatingSeries(80), 500, degree("CYCLE"));
    }

    @Test
    void INDEX_CLAMP_ABOVE_END_large() {
        verify(new SlopeChangeSwingDetector(config(5, 1, 5, 0.5, 0.0)),
                largeSeries(90), Integer.MAX_VALUE, degree("MINUETTE"));
    }

    @Test
    void EXACT_CANDIDATE_BOUNDARIES_first() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                waveSeries(7), 3, degree("MINOR"));
    }

    @Test
    void EXACT_CANDIDATE_BOUNDARIES_last() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                waveSeries(12), 11, degree("PRIMARY"));
    }

    @Test
    void FLAT_OR_SAME_DIRECTION_SLOPES_flat() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                flatSeries(40), 39, degree("INTERMEDIATE"));
    }

    @Test
    void FLAT_OR_SAME_DIRECTION_SLOPES_ascending() {
        verify(new SlopeChangeSwingDetector(config(4, 2, 3, 0.0, 0.0)),
                ascendingSeries(45), 44, degree("PRIMARY"));
    }

    @Test
    void FLAT_OR_SAME_DIRECTION_SLOPES_descending() {
        verify(new SlopeChangeSwingDetector(6),
                descendingSeries(55), 20, degree("CYCLE"));
    }

    @Test
    void REVERSAL_BELOW_SLOPE_THRESHOLD_high() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 100.0, 0.0)),
                waveSeries(50), 49, degree("MINOR"));
    }

    @Test
    void REVERSAL_BELOW_SLOPE_THRESHOLD_low() {
        verify(new SlopeChangeSwingDetector(config(4, 2, 3, 50.0, 0.5)),
                invertedWaveSeries(60), 100, degree("INTERMEDIATE"));
    }

    @Test
    void CONFIRMED_HIGH_REVERSAL() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.1, 0.0)),
                waveSeries(90), 89, degree("MINOR"));
    }

    @Test
    void CONFIRMED_LOW_REVERSAL() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.1, 0.0)),
                invertedWaveSeries(90), 89, degree("INTERMEDIATE"));
    }

    @Test
    void PERSISTENCE_FAILS() {
        verify(new SlopeChangeSwingDetector(config(3, 3, 3, 0.0, 0.0)),
                waveSeries(60), 59, degree("PRIMARY"));
    }

    @Test
    void PERSISTENCE_FAILS_afterConfirmation() {
        verify(new SlopeChangeSwingDetector(config(4, 3, 3, 0.0, 0.0)),
                alternatingSeries(80), 79, degree("CYCLE"));
    }

    @Test
    void EXTREME_PIVOT_TIE_high() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                tiedHighSeries(60), 59, degree("MINOR"));
    }

    @Test
    void EXTREME_PIVOT_TIE_low() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                tiedLowSeries(60), 59, degree("CYCLE"));
    }

    @Test
    void NONFINITE_SLOPE_INPUT_clamped() {
        verify(new SlopeChangeSwingDetector(4),
                nonFiniteCloseSeries(60), 100, degree("PRIMARY"));
    }

    @Test
    void NONFINITE_EXTREME_INPUT_high() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                nonFiniteHighSeries(60), 59, degree("INTERMEDIATE"));
    }

    @Test
    void NONFINITE_EXTREME_INPUT_low() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                nonFiniteLowSeries(60), 59, degree("CYCLE"));
    }

    @Test
    void FIRST_PIVOT_BYPASSES_ATR_FILTER_high() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 1, 0.0, 100.0)),
                waveSeries(50), 49, degree("MINOR"));
    }

    @Test
    void FIRST_PIVOT_BYPASSES_ATR_FILTER_low() {
        verify(new SlopeChangeSwingDetector(config(4, 1, 1, 0.0, 50.0)),
                invertedWaveSeries(60), 59, degree("PRIMARY"));
    }

    @Test
    void FIRST_PIVOT_BYPASSES_ATR_FILTER_long() {
        verify(new SlopeChangeSwingDetector(config(5, 2, 1, 1.0, 10.0)),
                waveSeries(80), 79, degree("CYCLE"));
    }

    @Test
    void ATR_MAGNITUDE_REJECTION_high() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 5, 0.0, 100.0)),
                waveSeries(90), 89, degree("MINOR"));
    }

    @Test
    void ATR_MAGNITUDE_REJECTION_low() {
        verify(new SlopeChangeSwingDetector(config(4, 2, 4, 0.0, 20.0)),
                invertedWaveSeries(100), 99, degree("INTERMEDIATE"));
    }

    @Test
    void ATR_MAGNITUDE_REJECTION_clamped() {
        verify(new SlopeChangeSwingDetector(config(5, 1, 3, 0.0, 5.0)),
                waveSeries(110), 1000, degree("PRIMARY"));
    }

    @Test
    void ALTERNATING_PIVOTS_AND_SWINGS_wave() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                waveSeries(120), 119, degree("MINOR"));
    }

    @Test
    void ALTERNATING_PIVOTS_AND_SWINGS_alternating() {
        verify(new SlopeChangeSwingDetector(config(4, 1, 3, 0.0, 0.0)),
                alternatingSeries(140), 139, degree("INTERMEDIATE"));
    }

    @Test
    void SAME_TYPE_MORE_EXTREME_REPLACEMENT_high() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                repeatedHighSeries(100), 99, degree("PRIMARY"));
    }

    @Test
    void SAME_TYPE_MORE_EXTREME_REPLACEMENT_low() {
        verify(new SlopeChangeSwingDetector(config(4, 1, 3, 0.0, 0.0)),
                repeatedLowSeries(100), 99, degree("CYCLE"));
    }

    @Test
    void SAME_TYPE_MORE_EXTREME_REPLACEMENT_long() {
        verify(new SlopeChangeSwingDetector(config(5, 2, 3, 0.0, 0.0)),
                repeatedHighSeries(130), 129, degree("MINUETTE"));
    }

    @Test
    void SAME_TYPE_NOT_MORE_EXTREME_bounded() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                boundedWaveSeries(90), 89, degree("MINOR"));
    }

    @Test
    void SAME_TYPE_NOT_MORE_EXTREME_atr() {
        verify(new SlopeChangeSwingDetector(config(4, 2, 3, 0.0, 0.0)),
                boundedWaveSeries(110), 109, degree("INTERMEDIATE"));
    }

    @Test
    void COMPLETE_HISTORY_AT_END_INDEX_wave() {
        BarSeries source = waveSeries(150);
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.5)),
                source, source.getEndIndex(), degree("PRIMARY"));
    }

    @Test
    void COMPLETE_HISTORY_AT_END_INDEX_alternating() {
        BarSeries source = alternatingSeries(170);
        verify(new SlopeChangeSwingDetector(config(5, 2, 5, 0.1, 0.0)),
                source, source.getEndIndex(), degree("CYCLE"));
    }

    @Test
    void COMPLETE_HISTORY_AT_END_INDEX_default() {
        BarSeries source = waveSeries(210);
        verify(new SlopeChangeSwingDetector(6),
                source, source.getEndIndex(), degree("MINOR"));
    }

    @Test
    void LARGE_FINITE_TRANSLATION_wave() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                largeSeries(80), 79, degree("INTERMEDIATE"));
    }

    @Test
    void LARGE_FINITE_TRANSLATION_atr() {
        verify(new SlopeChangeSwingDetector(config(4, 2, 4, 0.0, 0.5)),
                largeSeries(110), 109, degree("PRIMARY"));
    }

    @Test
    void DEGREE_VARIATION_minor() {
        verify(new SlopeChangeSwingDetector(3),
                waveSeries(90), 89, ElliottDegree.valueOf("MINOR"));
    }

    @Test
    void DEGREE_VARIATION_grandSupercycle() {
        verify(new SlopeChangeSwingDetector(3),
                waveSeries(90), 89, ElliottDegree.valueOf("GRAND_SUPERCYCLE"));
    }

    @Test
    void ZERO_VOLUME_AMOUNT_TRANSLATION_wave() {
        verify(new SlopeChangeSwingDetector(config(4, 1, 3, 0.0, 0.0)),
                zeroVolumeSeries(100), 99, degree("MINOR"));
    }

    @Test
    void ZERO_VOLUME_AMOUNT_TRANSLATION_reversal() {
        verify(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                zeroVolumeWaveSeries(120), 119, degree("PRIMARY"));
    }

    @Test
    void MIXED_ACCEPTANCE_HISTORY_default() {
        verify(new SlopeChangeSwingDetector(5),
                mixedSeries(180), 1000, degree("CYCLE"));
    }

    @Test
    void MIXED_ACCEPTANCE_HISTORY_filtered() {
        verify(new SlopeChangeSwingDetector(config(3, 2, 3, 0.0, 0.5)),
                mixedSeries(110), 109, degree("INTERMEDIATE"));
    }

    @Test
    void MIXED_ACCEPTANCE_HISTORY_strict() {
        verify(new SlopeChangeSwingDetector(config(4, 1, 5, 1.0, 2.0)),
                mixedSeries(150), 149, degree("PRIMARY"));
    }
}
