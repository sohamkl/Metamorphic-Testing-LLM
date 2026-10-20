import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static void run(int variation) {
        int window = 3 + variation % 3;
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(window);
        BarSeries source = sourceSeries(variation, window);
        int index = requestedIndex(variation, source);
        ElliottDegree degree = ElliottDegree.values()[variation % ElliottDegree.values().length];
        SwingDetectorResult sourceOutput = detector.detect(source, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, source, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static int requestedIndex(int variation, BarSeries series) {
        if (series.isEmpty()) {
            return variation == 1 ? -1 : 0;
        }
        if (variation == 6) {
            return -4;
        }
        if (variation == 7 || variation == 9 || variation == 18 || variation == 27 || variation == 36 || variation == 43 || variation == 45) {
            return series.getEndIndex() + 5;
        }
        if (variation == 8 || variation == 11 || variation == 14 || variation == 17 || variation == 26 || variation == 35 || variation == 42 || variation == 44 || variation == 49) {
            return series.getEndIndex();
        }
        if (variation == 3 || variation == 12 || variation == 13 || variation == 21 || variation == 30 || variation == 39 || variation == 48) {
            return Math.min(series.getEndIndex(), 1);
        }
        if (variation == 10) {
            return Math.min(series.getEndIndex(), 0);
        }
        return Math.min(series.getEndIndex(), 8 + variation % 5);
    }

    private static BarSeries sourceSeries(int variation, int window) {
        int confirmationBars = 2;
        int length;
        if (variation == 1 || variation == 10 || variation == 19 || variation == 28 || variation == 37 || variation == 46) {
            length = 0;
        } else if (variation == 2 || variation == 11 || variation == 20 || variation == 29 || variation == 38 || variation == 47) {
            length = 1;
        } else if (variation == 3 || variation == 12 || variation == 21 || variation == 30 || variation == 39 || variation == 48) {
            length = Math.max(1, window - 1);
        } else if (variation == 4 || variation == 22 || variation == 31 || variation == 40 || variation == 49) {
            length = window;
        } else if (variation == 5 || variation == 14 || variation == 23 || variation == 32 || variation == 41) {
            length = window + confirmationBars - 1;
        } else if (variation == 6 || variation == 15 || variation == 24 || variation == 33 || variation == 42) {
            length = window + confirmationBars;
        } else if (variation == 7 || variation == 16 || variation == 25 || variation == 34 || variation == 43) {
            length = 2 * window + confirmationBars - 1;
        } else if (variation == 8 || variation == 17 || variation == 26 || variation == 35 || variation == 44) {
            length = 2 * window + confirmationBars;
        } else {
            length = 3 * window + confirmationBars + 5;
        }
        double[] closes = new double[length];
        for (int i = 0; i < length; i++) {
            closes[i] = closeFor(variation, i, length);
        }
        double volume = variation % 3 == 0 ? 0.0 : 10.0;
        return buildSeries(closes, volume, "source-" + variation);
    }

    private static double closeFor(int variation, int index, int length) {
        switch(variation % 7) {
            case 0:
                return 100.0 + index;
            case 1:
                return 100.0 - index;
            case 2:
                return 100.0;
            case 3:
                return 100.0 + (index < length / 2 ? index : length - index);
            case 4:
                return 100.0 - (index < length / 2 ? index : length - index);
            case 5:
                return 100.25 + 0.75 * index;
            default:
                return 100.0 + ((index % 8 < 4) ? index % 4 : 4 - index % 4);
        }
    }

    private static BarSeries buildSeries(double[] closes, double volume, String name) {
        BarSeries series = new BaseBarSeriesBuilder().withName(name).build();
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < closes.length; i++) {
            Num close = series.numFactory().numOf(closes[i]);
            Num one = series.numFactory().numOf(1.0);
            Num open = close;
            Num high = close.plus(one);
            Num low = close.minus(one);
            Num barVolume = series.numFactory().numOf(volume);
            Num amount = series.numFactory().numOf(volume * closes[i]);
            Instant begin = base.plus(Duration.ofMinutes(i));
            Instant end = begin.plus(Duration.ofMinutes(1));
            series.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, open, high, low, close, barVolume, amount, 0L));
        }
        return series;
    }

    @Test
    void EMPTY_SERIES_variation2() {
        run(2);
    }

    @Test
    void SINGLE_BAR_SERIES_variation1() {
        run(3);
    }

    @Test
    void SHORTER_THAN_FIRST_CANDIDATE_variation1() {
        run(4);
    }

    @Test
    void EXACT_FIRST_CANDIDATE_NO_CONFIRMATION_ROOM_variation1() {
        run(5);
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMP_variation1() {
        run(6);
    }

    @Test
    void INDEX_ABOVE_END_CLAMP_variation1() {
        run(7);
    }

    @Test
    void INDEX_AT_BEGIN_variation1() {
        run(8);
    }

    @Test
    void INDEX_BEFORE_FIRST_CANDIDATE_variation1() {
        run(9);
    }

    @Test
    void INDEX_EXACTLY_LAST_CANDIDATE_variation1() {
        run(11);
    }

    @Test
    void STRICTLY_INCREASING_CLOSES_variation1() {
        run(12);
    }

    @Test
    void STRICTLY_DECREASING_CLOSES_variation1() {
        run(13);
    }

    @Test
    void FLAT_CLOSES_variation1() {
        run(14);
    }

    @Test
    void CHANGE_BELOW_MIN_SLOPE_variation1() {
        run(15);
    }

    @Test
    void HIGH_REVERSAL_WITHOUT_PERSISTENCE_variation1() {
        run(16);
    }

    @Test
    void LOW_REVERSAL_WITHOUT_PERSISTENCE_variation1() {
        run(17);
    }

    @Test
    void CONFIRMED_HIGH_PIVOT_variation1() {
        run(18);
    }

    @Test
    void CONFIRMED_LOW_PIVOT_variation1() {
        run(20);
    }

    @Test
    void CONFIRMED_LOW_PIVOT_variation2() {
        run(21);
    }

    @Test
    void HIGH_EXTREME_TIE_variation1() {
        run(22);
    }

    @Test
    void LOW_EXTREME_TIE_variation1() {
        run(23);
    }

    @Test
    void CONFIRMED_PIVOT_WITH_NONFINITE_ATR_variation1() {
        run(24);
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_variation1() {
        run(25);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_ACCEPTS_LATER_PIVOT_variation1() {
        run(26);
    }

    @Test
    void LATER_PIVOT_BELOW_ATR_THRESHOLD_variation1() {
        run(27);
    }

    @Test
    void ALTERNATING_HIGH_LOW_PIVOTS_variation1() {
        run(29);
    }

    @Test
    void ALTERNATING_HIGH_LOW_PIVOTS_variation2() {
        run(30);
    }

    @Test
    void REPEATED_HIGH_REPLACED_BY_HIGHER_variation1() {
        run(31);
    }

    @Test
    void REPEATED_LOW_REPLACED_BY_LOWER_variation1() {
        run(32);
    }

    @Test
    void REPEATED_HIGH_NOT_replaced_variation1() {
        run(33);
    }

    @Test
    void REPEATED_LOW_NOT_REPLACED_variation1() {
        run(34);
    }

    @Test
    void MULTIPLE_SWINGS_AND_DEGREE_PROPAGATION_variation1() {
        run(35);
    }

    @Test
    void MULTIPLE_SWINGS_AND_DEGREE_PROPAGATION_variation2() {
        run(36);
    }

    @Test
    void MULTIPLE_CONFIRMATION_BARS_variation1() {
        run(38);
    }

    @Test
    void MULTIPLE_CONFIRMATION_BARS_variation2() {
        run(39);
    }

    @Test
    void LARGER_REGRESSION_WINDOW_variation1() {
        run(40);
    }

    @Test
    void FRACTIONAL_NUM_FACTORY_PRICES_variation1() {
        run(41);
    }

    @Test
    void ZERO_VOLUME_TRANSLATION_variation1() {
        run(42);
    }

    @Test
    void POSITIVE_VOLUME_AMOUNT_TRANSLATION_variation1() {
        run(43);
    }

    @Test
    void EACH_AVAILABLE_ELLIOTT_DEGREE_variation1() {
        run(44);
    }

    @Test
    void EACH_AVAILABLE_ELLIOTT_DEGREE_variation2() {
        run(45);
    }

    @Test
    void EACH_AVAILABLE_ELLIOTT_DEGREE_variation4() {
        run(47);
    }

    @Test
    void EACH_AVAILABLE_ELLIOTT_DEGREE_variation5() {
        run(48);
    }

    @Test
    void SOURCE_AND_FOLLOWUP_NAME_DIFFER_variation1() {
        run(49);
    }
}
