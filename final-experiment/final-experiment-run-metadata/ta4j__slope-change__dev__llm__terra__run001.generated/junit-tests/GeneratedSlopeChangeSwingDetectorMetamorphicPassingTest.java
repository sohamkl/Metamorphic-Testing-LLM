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

    private static void execute(String name, int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal, double[] closes, int requestedIndex, boolean positivePayload) {
        BarSeries sourceSeries = new BaseBarSeriesBuilder().withName(name).build();
        addBars(sourceSeries, closes, null, null, positivePayload);
        invoke(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal, sourceSeries, requestedIndex);
    }

    private static void executeWithExtremes(String name, int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal, double[] closes, double[] highs, double[] lows, int requestedIndex, boolean positivePayload) {
        BarSeries sourceSeries = new BaseBarSeriesBuilder().withName(name).build();
        addBars(sourceSeries, closes, highs, lows, positivePayload);
        invoke(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal, sourceSeries, requestedIndex);
    }

    private static void addBars(BarSeries series, double[] closes, double[] highs, double[] lows, boolean positivePayload) {
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = highs == null ? (close == 0.0 ? 0.0 : close + 1.0) : highs[i];
            double low = lows == null ? (close == 0.0 ? 0.0 : close - 1.0) : lows[i];
            Num volume = series.numFactory().numOf(positivePayload ? i + 1.0 : 0.0);
            Num amount = series.numFactory().numOf(positivePayload ? (i + 1.0) * 10.0 : 0.0);
            Instant begin = base.plusSeconds(i * 60L);
            series.addBar(new BaseBar(Duration.ofMinutes(1), begin, begin.plusSeconds(60), series.numFactory().numOf(close), series.numFactory().numOf(high), series.numFactory().numOf(low), series.numFactory().numOf(close), volume, amount, 0L));
        }
    }

    private static void invoke(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal, BarSeries sourceSeries, int requestedIndex) {
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
        ElliottDegree degree = ElliottDegree.valueOf("MINOR");
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, requestedIndex, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, requestedIndex, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_BAR_NONEMPTY_variation1() {
        execute("single-bar", 2, 1, 1, 0.1, 0.0, new double[] { 10.0 }, 0, true);
    }

    @Test
    public void ONE_BAR_SHORT_OF_FIRST_CANDIDATE_variation1() {
        execute("one-short", 3, 2, 2, 1.0, 0.0, new double[] { 0, 1, 2, 3, 2, 1 }, 5, false);
    }

    @Test
    public void MINIMUM_LENGTH_SINGLE_CANDIDATE_variation1() {
        execute("minimum-high", 2, 2, 2, 2.0, 0.0, new double[] { 0, 1, 2, 1, 0 }, 4, false);
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMPS_TO_BEGIN_variation1() {
        execute("below-begin", 2, 2, 2, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0, -1, -2 }, -7, true);
    }

    @Test
    public void INDEX_BEFORE_CANDIDATE_HORIZON_variation1() {
        execute("before-horizon", 2, 2, 2, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0, -1, -2 }, 2, false);
    }

    @Test
    public void INDEX_AT_END_variation1() {
        execute("at-end", 2, 1, 1, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0, -1, -2, -1, 0 }, 8, true);
    }

    @Test
    public void INDEX_ABOVE_END_CLAMPS_TO_END_variation1() {
        execute("above-end", 2, 1, 1, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0, -1, -2, -1, 0 }, 100, false);
    }

    @Test
    public void FLAT_CLOSES_variation1() {
        execute("flat", 2, 2, 2, 0.1, 0.0, new double[] { 7, 7, 7, 7, 7, 7, 7 }, 99, true);
    }

    @Test
    public void MONOTONIC_RISING_CLOSES_variation1() {
        execute("rising", 2, 1, 1, 0.1, 0.0, new double[] { 0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5 }, 6, true);
    }

    @Test
    public void MONOTONIC_FALLING_CLOSES_variation1() {
        execute("falling", 2, 1, 1, 0.1, 0.0, new double[] { 8, 7, 6, 5, 4, 3, 2 }, 6, false);
    }

    @Test
    public void SAME_SIGN_SLOPE_CHANGE_ABOVE_THRESHOLD_variation1() {
        execute("accelerating-rise", 2, 1, 1, 0.5, 0.0, new double[] { 0, 1, 2, 4, 7, 11, 16 }, 6, false);
    }

    @Test
    public void OPPOSITE_SIGN_CHANGE_STRICTLY_BELOW_THRESHOLD_variation1() {
        execute("weak-turn", 2, 2, 2, 3.0, 0.0, new double[] { 0, 1, 2, 1, 0 }, 4, true);
    }

    @Test
    public void HIGH_TURN_AT_EXACT_SLOPE_THRESHOLD_variation1() {
        execute("exact-high", 2, 2, 2, 2.0, 0.0, new double[] { 0, 1, 2, 1, 0 }, 10, true);
    }

    @Test
    public void LOW_TURN_AT_EXACT_SLOPE_THRESHOLD_variation1() {
        execute("exact-low", 2, 2, 2, 2.0, 0.0, new double[] { 2, 1, 0, 1, 2 }, 10, false);
    }

    @Test
    public void HIGH_PERSISTENCE_FIRST_SLOPE_ZERO_variation1() {
        execute("high-zero-persistence", 2, 2, 2, 1.0, 0.0, new double[] { 0, 1, 2, 1, 1 }, 4, false);
    }

    @Test
    public void HIGH_PERSISTENCE_LATER_POSITIVE_variation1() {
        execute("high-later-positive", 2, 2, 2, 1.0, 0.0, new double[] { 0, 1, 2, 1, 2 }, 4, true);
    }

    @Test
    public void LOW_PERSISTENCE_LATER_NEGATIVE_variation1() {
        execute("low-later-negative", 2, 2, 2, 1.0, 0.0, new double[] { 2, 1, 0, 1, 0 }, 4, true);
    }

    @Test
    public void FIRST_CONFIRMED_HIGH_variation1() {
        execute("first-high", 2, 2, 2, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0 }, 20, false);
    }

    @Test
    public void FIRST_CONFIRMED_LOW_variation1() {
        execute("first-low", 2, 2, 2, 1.0, 0.0, new double[] { 2, 1, 0, 1, 2 }, 20, false);
    }

    @Test
    public void HIGH_EXTREME_TIE_USES_EARLIEST_INDEX_variation1() {
        executeWithExtremes("high-tie", 2, 2, 2, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0 }, new double[] { 1, 9, 9, 2, 1 }, new double[] { -1, 0, 1, 0, -1 }, 4, true);
    }

    @Test
    public void LOW_EXTREME_TIE_USES_EARLIEST_INDEX_variation1() {
        executeWithExtremes("low-tie", 2, 2, 2, 1.0, 0.0, new double[] { 2, 1, 0, 1, 2 }, new double[] { 3, 2, 1, 2, 3 }, new double[] { 1, -7, -7, 0, 1 }, 4, false);
    }

    @Test
    public void ALTERNATING_HIGH_THEN_LOW_variation1() {
        execute("high-low", 2, 1, 1, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0, -1, -2, -1, 0 }, 8, false);
    }

    @Test
    public void ALTERNATING_LOW_THEN_HIGH_variation1() {
        execute("low-high", 2, 1, 1, 1.0, 0.0, new double[] { 2, 1, 0, 1, 2, 3, 4, 3, 2 }, 8, true);
    }

    @Test
    public void SAME_TYPE_HIGH_REPLACED_BY_HIGHER_HIGH_variation1() {
        execute("higher-high", 2, 1, 1, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0, 1, 3, 2, 1 }, 8, true);
    }

    @Test
    public void SAME_TYPE_LOW_REPLACED_BY_LOWER_LOW_variation1() {
        execute("lower-low", 2, 1, 1, 1.0, 0.0, new double[] { 2, 1, 0, 1, 2, 1, -1, 0, 1 }, 8, false);
    }

    @Test
    public void SAME_TYPE_HIGH_NOT_REPLACED_BY_EQUAL_OR_LOWER_variation1() {
        execute("equal-high", 2, 1, 1, 1.0, 0.0, new double[] { 0, 1, 3, 2, 1, 2, 3, 2, 1 }, 8, true);
    }

    @Test
    public void SAME_TYPE_LOW_NOT_REPLACED_BY_EQUAL_OR_HIGHER_variation1() {
        execute("equal-low", 2, 1, 1, 1.0, 0.0, new double[] { 3, 2, 0, 1, 2, 1, 0, 1, 2 }, 8, false);
    }

    @Test
    public void MAGNITUDE_FILTER_ZERO_MULTIPLIER_variation1() {
        execute("zero-multiplier", 2, 1, 1, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0, -1, -2, -1, 0 }, 8, true);
    }

    @Test
    public void MAGNITUDE_FILTER_REJECTS_WEAK_REVERSAL_variation1() {
        execute("weak-reversal", 2, 1, 2, 1.0, 100.0, new double[] { 0, 1, 2, 1, 0, -1, -2, -1, 0 }, 8, true);
    }

    @Test
    public void MAGNITUDE_FILTER_ACCEPTS_EXACT_EQUALITY_variation1() {
        execute("atr-equality", 2, 1, 1, 1.0, 3.0, new double[] { 0, 1, 2, 1, 0, -1, -2, -1, 0 }, 8, false);
    }

    @Test
    public void THREE_ALTERNATING_PIVOTS_TWO_SWINGS_variation1() {
        execute("three-alternating", 2, 1, 1, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0, -1, -2, -1, 0, 1, 2, 1, 0 }, 12, false);
    }

    @Test
    public void FOUR_ALTERNATING_PIVOTS_variation1() {
        execute("four-alternating", 2, 1, 1, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0, -1, -2, -1, 0, 1, 2, 1, 0, -1, -2, -1, 0 }, 16, true);
    }

    @Test
    public void ZERO_PRICE_BARS_variation1() {
        execute("zero-price", 2, 2, 2, 1.0, 0.0, new double[] { -2, -1, 0, -1, -2 }, 4, false);
    }

    @Test
    public void FRACTIONAL_PRICE_TURNS_variation1() {
        execute("fractional-turns", 2, 1, 1, 0.5, 0.0, new double[] { 0.25, 1.75, 3.25, 1.75, 0.25, -1.25, -2.75, -1.25, 0.25 }, 8, true);
    }

    @Test
    public void ZERO_VOLUME_ZERO_AMOUNT_variation1() {
        execute("zero-payload", 2, 1, 1, 1.0, 0.0, new double[] { 0, 1, 2, 1, 0, -1, -2, -1, 0 }, 8, false);
    }

    @Test
    public void POSITIVE_VOLUME_AND_AMOUNT_variation1() {
        execute("positive-payload", 2, 1, 1, 1.0, 0.0, new double[] { 2, 1, 0, 1, 2, 3, 4, 3, 2 }, 8, true);
    }
}
