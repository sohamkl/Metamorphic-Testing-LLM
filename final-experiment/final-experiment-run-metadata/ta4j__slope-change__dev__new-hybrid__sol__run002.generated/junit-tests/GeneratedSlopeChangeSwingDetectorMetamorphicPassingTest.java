import java.lang.reflect.Proxy;
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
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final double[] HIGH_REVERSAL = { 10, 12, 14, 12, 10 };

    private static final double[] LOW_REVERSAL = { 14, 12, 10, 12, 14 };

    private static final double[] WAVE = { 10, 12, 14, 12, 10, 8, 10, 12, 14, 12, 10, 8, 10 };

    private static final double[] INVERTED_WAVE = { 14, 12, 10, 12, 14, 16, 14, 12, 10, 12, 14, 16, 14 };

    private static void execute(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static ElliottDegree degree(String name) {
        return ElliottDegree.valueOf(name);
    }

    private static BarSeries series(String name, double[] closes) {
        return series(name, closes, null, null, null);
    }

    private static BarSeries series(String name, double[] closes, double[] highs, double[] lows, double[] volumes) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);
        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = highs == null ? close + 1.0 : highs[i];
            double low = lows == null ? close - 1.0 : lows[i];
            double volume = volumes == null ? 1.0 : volumes[i];
            Num openNum = result.numFactory().numOf(close);
            Num highNum = result.numFactory().numOf(high);
            Num lowNum = result.numFactory().numOf(low);
            Num closeNum = result.numFactory().numOf(close);
            Num volumeNum = result.numFactory().numOf(volume);
            Num amountNum = result.numFactory().numOf(0);
            Instant begin = base.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            result.addBar(new BaseBar(period, begin, end, openNum, highNum, lowNum, closeNum, volumeNum, amountNum, 0));
        }
        return result;
    }

    private static BarSeries emptyZeroBasedSeries() {
        BarSeries delegate = new BaseBarSeriesBuilder().withName("empty-zero-based").build();
        return (BarSeries) Proxy.newProxyInstance(BarSeries.class.getClassLoader(), new Class<?>[] { BarSeries.class }, (proxy, method, args) -> {
            switch(method.getName()) {
                case "isEmpty":
                    return true;
                case "getBeginIndex":
                    return 0;
                case "getEndIndex":
                    return -1;
                case "getName":
                    return "empty-zero-based";
                case "numFactory":
                    return delegate.numFactory();
                case "toString":
                    return "empty-zero-based";
                case "hashCode":
                    return System.identityHashCode(proxy);
                case "equals":
                    return proxy == args[0];
                default:
                    throw new UnsupportedOperationException(method.getName());
            }
        });
    }

    private static double[] repeat(double value, int count) {
        double[] result = new double[count];
        for (int i = 0; i < count; i++) {
            result[i] = value;
        }
        return result;
    }

    private static double[] shift(double[] values, double amount) {
        double[] result = values.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] += amount;
        }
        return result;
    }

    @Test
    void EMPTY_ZERO_BASED_SERIES_variation1() {
        execute(detector(3, 1, 3, 0.0, 0.5), emptyZeroBasedSeries(), Integer.MIN_VALUE, degree("MINOR"));
    }

    @Test
    void NONEMPTY_TOO_SHORT_FOR_ANY_CANDIDATE_variation1() {
        execute(detector(3, 1, 3, 1.0, 0.0), series("short-negative", new double[] { -80, -79, -78, -79 }), 3, degree("MINUTE"));
    }

    @Test
    void EXACT_MINIMUM_LENGTH_WITHOUT_REVERSAL_variation1() {
        execute(detector(2, 1, 2, 2.0, 1.0), series("minimum-flat", new double[] { 1_000_000.25, 1_000_000.25, 1_000_000.25, 1_000_000.25 }), 3, degree("INTERMEDIATE"));
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation1() {
        execute(detector(2, 1, 2, 0.0, 0.0), series("below-begin-wave", WAVE), -1, degree("PRIMARY"));
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation2() {
        execute(detector(2, 1, 2, 0.0, 0.5), series("below-begin-low", LOW_REVERSAL), Integer.MIN_VALUE, degree("CYCLE"));
    }

    @Test
    void CUTOFF_ONE_BAR_BEFORE_FIRST_COMPLETE_CANDIDATE_variation1() {
        execute(detector(3, 1, 3, 0.0, 0.5), series("causal-cutoff", new double[] { 1_000_000.125, 1_000_001.25, 1_000_002.375, 1_000_001.25, 1_000_000.125, 999_999.0 }), 5, degree("SUB_MINUETTE"));
    }

    @Test
    void INDEX_ABOVE_END_CLAMPS_TO_END_variation1() {
        execute(detector(2, 1, 2, 0.0, 0.5), series("above-end", HIGH_REVERSAL), Integer.MAX_VALUE, degree("GRAND_SUPERCYCLE"));
    }

    @Test
    void FLAT_CLOSE_HISTORY_variation1() {
        execute(detector(2, 1, 3, 0.0, 0.0), series("flat-negative", repeat(-25.0, 10)), 9, degree("MINUETTE"));
    }

    @Test
    void FLAT_CLOSE_HISTORY_variation2() {
        execute(detector(3, 2, 4, 0.25, 1.0), series("flat-large", repeat(5_000_000.375, 12)), Integer.MAX_VALUE, degree("SUPER_CYCLE"));
    }

    @Test
    void SLOPE_CHANGE_STRICTLY_BELOW_MINIMUM_variation1() {
        execute(detector(2, 1, 2, 3.0, 0.5), series("small-high-reversal", new double[] { 10, 11, 12, 11, 10 }), 4, degree("MINOR"));
    }

    @Test
    void SLOPE_CHANGE_STRICTLY_BELOW_MINIMUM_variation2() {
        execute(detector(2, 1, 2, 1.0, 0.5), series("small-low-reversal-negative", new double[] { -20, -19.75, -19.5, -19.75, -20 }), 4, degree("MINUTE"));
    }

    @Test
    void SLOPE_CHANGE_EXACTLY_AT_MINIMUM_variation1() {
        execute(detector(2, 1, 2, 2.0, 0.5), series("exact-threshold", new double[] { 10, 11, 12, 11, 10 }), Integer.MAX_VALUE, degree("INTERMEDIATE"));
    }

    @Test
    void FINITE_SAME_SIGN_SLOPES_variation1() {
        execute(detector(2, 1, 2, 0.5, 0.5), series("same-sign-positive", new double[] { 10, 12, 13, 14, 15 }), 4, degree("PRIMARY"));
    }

    @Test
    void FINITE_SAME_SIGN_SLOPES_variation2() {
        execute(detector(2, 1, 2, 0.5, 0.5), series("same-sign-negative", new double[] { -10, -12, -13, -14, -15 }), 4, degree("CYCLE"));
    }

    @Test
    void CONFIRMED_HIGH_FIRST_PIVOT_variation1() {
        execute(detector(2, 1, 2, 0.0, 4.0), series("confirmed-high", HIGH_REVERSAL), 4, degree("SUB_MINUETTE"));
    }

    @Test
    void CONFIRMED_HIGH_FIRST_PIVOT_variation2() {
        execute(detector(3, 2, 3, 0.0, 0.5), series("rounded-high", new double[] { 10, 12, 14, 15, 14, 12, 10, 8, 6 }), 8, degree("MINOR"));
    }

    @Test
    void CONFIRMED_LOW_FIRST_PIVOT_variation1() {
        execute(detector(2, 1, 2, 0.0, 2.0), series("confirmed-low-negative", shift(LOW_REVERSAL, -80)), 4, degree("MINUTE"));
    }

    @Test
    void CONFIRMED_LOW_FIRST_PIVOT_variation2() {
        execute(detector(3, 2, 3, 0.0, 0.5), series("rounded-low-large", new double[] { 1_000_006.5, 1_000_004.25, 1_000_002.0, 1_000_001.5, 1_000_002.75, 1_000_004.5, 1_000_006.75, 1_000_009.0 }), 7, degree("INTERMEDIATE"));
    }

    @Test
    void FIRST_CONFIRMATION_WRONG_DIRECTION_variation1() {
        execute(detector(3, 2, 3, 0.0, 0.5), series("high-confirmation-turns", new double[] { 10, 12, 14, 13, 12, 13, 14, 15 }), 7, degree("PRIMARY"));
    }

    @Test
    void FIRST_CONFIRMATION_WRONG_DIRECTION_variation2() {
        execute(detector(3, 2, 3, 0.0, 0.0), series("low-confirmation-turns", new double[] { -20, -22, -24, -23, -22, -23, -24, -25 }), 7, degree("CYCLE"));
    }

    @Test
    void FIRST_CONFIRMATION_ZERO_SLOPE_variation1() {
        execute(detector(3, 2, 3, 0.0, 0.5), series("flat-confirmation", new double[] { 100, 102, 104, 103, 102, 102, 102, 102 }), 7, degree("SUB_MINUETTE"));
    }

    @Test
    void LATER_CONFIRMATION_FAILURE_variation1() {
        execute(detector(2, 3, 2, 0.0, 0.5), series("later-high-failure", new double[] { 10, 12, 14, 12, 10, 11, 12, 13 }), 7, degree("MINOR"));
    }

    @Test
    void LATER_CONFIRMATION_FAILURE_variation2() {
        execute(detector(2, 3, 2, 0.0, 0.5), series("later-low-failure", new double[] { -10, -12, -14, -12, -10, -11, -12, -13 }), 7, degree("MINUTE"));
    }

    @Test
    void HIGH_EXTREME_AT_DISTINCT_TRANSITION_POSITIONS_variation1() {
        double[] closes = { 10, 12, 14, 12, 10 };
        double[] highs = { 11, 30, 15, 13, 11 };
        execute(detector(2, 1, 2, 0.0, 0.5), series("high-extreme-start", closes, highs, null, null), 4, degree("INTERMEDIATE"));
    }

    @Test
    void HIGH_EXTREME_AT_DISTINCT_TRANSITION_POSITIONS_variation2() {
        double[] closes = { 10, 12, 14, 12, 10 };
        double[] highs = { 11, 13, 31, 13, 11 };
        execute(detector(2, 1, 2, 0.0, 0.5), series("high-extreme-end", closes, highs, null, null), 4, degree("PRIMARY"));
    }

    @Test
    void LOW_EXTREME_AT_DISTINCT_TRANSITION_POSITIONS_variation1() {
        double[] closes = { -10, -12, -14, -12, -10 };
        double[] lows = { -11, -30, -15, -13, -11 };
        execute(detector(2, 1, 2, 0.0, 0.0), series("low-extreme-start", closes, null, lows, null), 4, degree("CYCLE"));
    }

    @Test
    void LOW_EXTREME_AT_DISTINCT_TRANSITION_POSITIONS_variation2() {
        double[] closes = { 14, 12, 10, 12, 14 };
        double[] lows = { 13, 11, -5, 11, 13 };
        execute(detector(2, 1, 2, 0.0, 0.5), series("low-extreme-end", closes, null, lows, null), 4, degree("SUB_MINUETTE"));
    }

    @Test
    void EQUAL_EXTREMA_RETAIN_EARLIEST_INDEX_variation1() {
        double[] closes = { 10, 12, 14, 12, 10 };
        double[] highs = { 11, 20, 20, 13, 11 };
        execute(detector(2, 1, 2, 0.0, 0.5), series("tied-highs", closes, highs, null, null), 4, degree("MINOR"));
    }

    @Test
    void EQUAL_EXTREMA_RETAIN_EARLIEST_INDEX_variation2() {
        double[] closes = { 14, 12, 10, 12, 14 };
        double[] lows = { 13, 5, 5, 11, 13 };
        execute(detector(2, 1, 2, 0.0, 0.5), series("tied-lows", closes, null, lows, null), 4, degree("MINUTE"));
    }

    @Test
    void THREE_ALTERNATING_CONFIRMED_PIVOTS_variation1() {
        execute(detector(2, 1, 2, 0.0, 0.0), series("three-wave", WAVE), Integer.MAX_VALUE, degree("INTERMEDIATE"));
    }

    @Test
    void THREE_ALTERNATING_CONFIRMED_PIVOTS_variation2() {
        execute(detector(2, 1, 3, 0.5, 0.0), series("three-inverted", INVERTED_WAVE), 12, degree("PRIMARY"));
    }

    @Test
    void THREE_ALTERNATING_CONFIRMED_PIVOTS_variation3() {
        execute(detector(3, 1, 3, 0.0, 0.0), series("three-rounded", new double[] { 20, 22, 24, 26, 24, 22, 20, 18, 20, 22, 24, 26, 24, 22, 20, 18, 20, 22 }), 17, degree("CYCLE"));
    }

    @Test
    void LATER_HIGH_REPLACES_PREVIOUS_HIGH_variation1() {
        execute(detector(2, 1, 2, 0.0, 0.0), series("higher-second-high", new double[] { 10, 12, 14, 12, 13, 15, 17, 15, 13 }), 8, degree("SUB_MINUETTE"));
    }

    @Test
    void LATER_HIGH_REPLACES_PREVIOUS_HIGH_variation2() {
        double[] closes = { 10, 12, 14, 12, 13, 15, 17, 15, 13 };
        double[] highs = { 11, 15, 16, 13, 14, 18, 25, 16, 14 };
        execute(detector(2, 1, 2, 0.0, 0.0), series("higher-custom-high", closes, highs, null, null), 8, degree("MINOR"));
    }

    @Test
    void LATER_HIGH_DOES_NOT_REPLACE_variation1() {
        execute(detector(2, 1, 2, 0.0, 0.0), series("lower-second-high", new double[] { 10, 14, 18, 14, 15, 16, 17, 15, 13 }), 8, degree("MINUTE"));
    }

    @Test
    void LATER_LOW_REPLACES_PREVIOUS_LOW_variation1() {
        execute(detector(2, 1, 2, 0.0, 0.0), series("lower-second-low", new double[] { 18, 16, 14, 16, 15, 13, 11, 13, 15 }), 8, degree("INTERMEDIATE"));
    }

    @Test
    void LATER_LOW_DOES_NOT_REPLACE_variation1() {
        execute(detector(2, 1, 2, 0.0, 0.0), series("higher-second-low", new double[] { 18, 14, 10, 14, 13, 12, 11, 13, 15 }), 8, degree("PRIMARY"));
    }

    @Test
    void ATR_REVERSAL_STRICTLY_BELOW_THRESHOLD_variation1() {
        execute(detector(2, 1, 1, 0.0, 10.0), series("atr-reject-wave", WAVE), 12, degree("CYCLE"));
    }

    @Test
    void ATR_REVERSAL_STRICTLY_BELOW_THRESHOLD_variation2() {
        double[] narrowWave = { -50, -49, -48, -49, -50, -51, -50, -49, -48, -49, -50 };
        execute(detector(2, 1, 2, 0.0, 5.0), series("atr-reject-negative", narrowWave), 10, degree("SUB_MINUETTE"));
    }

    @Test
    void ATR_REVERSAL_EXACTLY_AT_THRESHOLD_variation1() {
        execute(detector(2, 1, 1, 0.0, 3.0), series("atr-exact", WAVE), 6, degree("MINOR"));
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASS_variation1() {
        execute(detector(2, 1, 2, 0.0, 0.0), series("zero-atr-multiplier", WAVE), 12, degree("MINUTE"));
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASS_variation2() {
        double[] closes = { 10, 12, 14, 12, 10, 8, 10, 12, 14 };
        double[] highs = { 11, 13, 15, 13, 11, 9, 11, 13, 15 };
        double[] lows = { 9, 11, 13, 11, 9, 7, 9, 11, 13 };
        execute(detector(2, 1, 20, 0.0, 0.0), series("zero-multiplier-long-atr", closes, highs, lows, null), 8, degree("INTERMEDIATE"));
    }

    @Test
    void ELLIOTT_DEGREE_PROPAGATION_variation1() {
        execute(detector(2, 1, 2, 0.0, 0.0), series("degree-minor", WAVE), 12, degree("MINOR"));
    }

    @Test
    void ELLIOTT_DEGREE_PROPAGATION_variation2() {
        execute(detector(2, 1, 2, 0.0, 0.0), series("degree-grand", WAVE), 12, degree("GRAND_SUPERCYCLE"));
    }

    @Test
    void NEGATIVE_PRICES_TRANSLATED_ACROSS_ZERO_variation1() {
        execute(detector(2, 1, 2, 0.0, 0.0), series("negative-cross-zero", shift(WAVE, -90)), 12, degree("PRIMARY"));
    }

    @Test
    void LARGE_FRACTIONAL_PRICE_HISTORY_variation1() {
        double[] prices = { 1_000_000_000.125, 1_000_000_002.375, 1_000_000_004.625, 1_000_000_002.375, 1_000_000_000.125, 999_999_997.875, 1_000_000_000.125, 1_000_000_002.375, 1_000_000_004.625 };
        execute(detector(2, 1, 2, 0.5, 0.0), series("large-fractional", prices), 8, degree("INTERMEDIATE"));
    }

    @Test
    void AUXILIARY_VOLUME_AND_AMOUNT_TRANSFORMATION_variation1() {
        double[] volumes = { 0, 2, 0, 3, 1, 0, 4, 0, 5, 1, 0, 2, 0 };
        execute(detector(2, 1, 2, 0.0, 0.0), series("volume-and-amount", WAVE, null, null, volumes), 12, degree("MINUTE"));
    }
}
