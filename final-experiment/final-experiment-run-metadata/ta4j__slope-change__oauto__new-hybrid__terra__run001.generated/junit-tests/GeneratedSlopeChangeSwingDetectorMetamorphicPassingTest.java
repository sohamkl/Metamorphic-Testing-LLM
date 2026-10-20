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
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static final double TRANSLATION = 100.0;

    private static void run(int tag, double[] closes, int index, ElliottDegree degree, double minSlopeChange, double minAtrReversal) {
        BarSeries sourceSeries = sourceSeries("source-" + tag, closes, tag);
        SlopeChangeConfig config = new SlopeChangeConfig(3, 2, 2, minSlopeChange, minAtrReversal);
        SlopeChangeSwingDetector sourceDetector = new SlopeChangeSwingDetector(config);
        SwingDetectorResult sourceOutput = sourceDetector.detect(sourceSeries, index, degree);
        Object[] followUp = generateFollowUp(sourceDetector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder().withName(sourceSeries.getName() + "-translated").withNumFactory(sourceSeries.numFactory()).build();
        Num translation = sourceSeries.numFactory().numOf(TRANSLATION);
        for (int barIndex = sourceSeries.getBeginIndex(); barIndex <= sourceSeries.getEndIndex(); barIndex++) {
            Bar sourceBar = sourceSeries.getBar(barIndex);
            translatedSeries.addBar(new BaseBar(sourceBar.getTimePeriod(), sourceBar.getBeginTime(), sourceBar.getEndTime(), sourceBar.getOpenPrice().plus(translation), sourceBar.getHighPrice().plus(translation), sourceBar.getLowPrice().plus(translation), sourceBar.getClosePrice().plus(translation), sourceBar.getVolume(), sourceBar.getAmount().plus(translation.multipliedBy(sourceBar.getVolume())), sourceBar.getTrades()));
        }
        return new Object[] { new SlopeChangeSwingDetector(detector.getConfig()), translatedSeries, index, degree };
    }

    private static BarSeries sourceSeries(String name, double[] closes, int tag) {
        BarSeries series = new BaseBarSeriesBuilder().withName(name).build();
        double offset = tag * 0.01;
        for (int i = 0; i < closes.length; i++) {
            Num close = series.numFactory().numOf(closes[i] + offset);
            Num open = series.numFactory().numOf(closes[i] - 0.25 + offset);
            Num high = series.numFactory().numOf(closes[i] + 1.0 + offset);
            Num low = series.numFactory().numOf(closes[i] - 1.0 + offset);
            Instant begin = BASE_TIME.plusSeconds(i * 60L);
            Instant end = begin.plusSeconds(60L);
            series.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, open, high, low, close, series.numFactory().numOf(10.0), series.numFactory().numOf(1000.0), 1L));
        }
        return series;
    }

    private static double[] flat(int size) {
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = 10.0;
        }
        return values;
    }

    private static double[] rising(int size) {
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = i + 1.0;
        }
        return values;
    }

    private static double[] falling(int size) {
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = size - i;
        }
        return values;
    }

    private static double[] highTurn() {
        return new double[] { 1, 2, 3, 2, 1, 0, -1 };
    }

    private static double[] lowTurn() {
        return new double[] { 7, 6, 5, 6, 7, 8, 9 };
    }

    private static double[] compactWave() {
        return new double[] { 2, 3, 4, 3, 2, 3, 4, 3, 2, 1, 2, 3, 2, 1, 0, -1, 0 };
    }

    private static double[] wave() {
        return new double[] { 1, 2, 3, 2, 1, 2, 3, 4, 3, 2, 1, 2, 3, 2, 1, 0, -1 };
    }

    private static double[] extendedWave() {
        return new double[] { 1, 2, 3, 2, 1, 2, 3, 4, 3, 2, 1, 2, 3, 2, 1, 0, -1, 0, 1, 2, 1, 0, -1, -2 };
    }

    @Test
    void INDEX_BEFORE_BEGIN_CLAMPS_TO_ZERO_variation1() {
        run(2, flat(5), -3, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void INDEX_BEFORE_BEGIN_CLAMPS_TO_ZERO_variation2() {
        run(3, highTurn(), -1, ElliottDegree.MINUTE, 0.1, 0.5);
    }

    @Test
    void INDEX_AFTER_END_CLAMPS_TO_FINAL_BAR_variation1() {
        run(4, lowTurn(), 100, ElliottDegree.MINOR, 0.1, 0.5);
    }

    @Test
    void INDEX_AFTER_END_CLAMPS_TO_FINAL_BAR_variation2() {
        run(5, highTurn(), 100, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void ONE_BAR_BEFORE_FIRST_ELIGIBLE_CANDIDATE_variation1() {
        run(6, flat(6), 5, ElliottDegree.MINUTE, 0.1, 0.5);
    }

    @Test
    void ONE_BAR_BEFORE_FIRST_ELIGIBLE_CANDIDATE_variation2() {
        run(7, rising(6), 5, ElliottDegree.MINOR, 0.1, 0.5);
    }

    @Test
    void EXACTLY_ONE_ELIGIBLE_CANDIDATE_variation1() {
        run(8, lowTurn(), 6, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void EXACTLY_ONE_ELIGIBLE_CANDIDATE_variation2() {
        run(9, highTurn(), 6, ElliottDegree.MINUTE, 0.1, 0.5);
    }

    @Test
    void SAME_DIRECTION_SLOPES_SKIP_CANDIDATE_variation1() {
        run(10, rising(10), 9, ElliottDegree.MINOR, 0.1, 0.5);
    }

    @Test
    void SAME_DIRECTION_SLOPES_SKIP_CANDIDATE_variation2() {
        run(11, falling(10), 9, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void SLOPE_CHANGE_BELOW_THRESHOLD_variation1() {
        run(12, lowTurn(), 100, ElliottDegree.MINUTE, 10.0, 0.5);
    }

    @Test
    void SLOPE_CHANGE_BELOW_THRESHOLD_variation2() {
        run(13, highTurn(), 100, ElliottDegree.MINOR, 10.0, 0.5);
    }

    @Test
    void HIGH_REJECTION_ON_FIRST_PERSISTENCE_SLOPE_variation1() {
        run(14, new double[] { 1, 2, 3, 2, 3, 4, 5 }, 6, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void HIGH_REJECTION_ON_FIRST_PERSISTENCE_SLOPE_variation2() {
        run(15, new double[] { 2, 4, 6, 4, 6, 8, 10 }, 6, ElliottDegree.MINUTE, 0.1, 0.5);
    }

    @Test
    void HIGH_REJECTION_ON_FIRST_PERSISTENCE_SLOPE_variation3() {
        run(16, wave(), 100, ElliottDegree.MINOR, 0.1, 0.5);
    }

    @Test
    void HIGH_REJECTION_ON_LATER_PERSISTENCE_SLOPE_variation1() {
        run(17, new double[] { 1, 2, 3, 2, 1, 0, 1 }, 6, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void HIGH_REJECTION_ON_LATER_PERSISTENCE_SLOPE_variation2() {
        run(18, new double[] { 2, 3, 4, 3, 2, 1, 2 }, 6, ElliottDegree.MINUTE, 0.1, 0.5);
    }

    @Test
    void HIGH_REJECTION_ON_LATER_PERSISTENCE_SLOPE_variation3() {
        run(19, new double[] { 3, 5, 7, 5, 3, 1, 2 }, 6, ElliottDegree.MINOR, 0.1, 0.5);
    }

    @Test
    void CONFIRMED_LOW_FIRST_PIVOT_variation1() {
        run(20, lowTurn(), 100, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void CONFIRMED_LOW_FIRST_PIVOT_variation2() {
        run(21, new double[] { 9, 7, 5, 6, 8, 10, 12 }, 6, ElliottDegree.MINUTE, 0.1, 0.5);
    }

    @Test
    void CONFIRMED_LOW_FIRST_PIVOT_variation3() {
        run(22, new double[] { 12, 10, 8, 9, 11, 13, 15 }, 6, ElliottDegree.MINOR, 0.1, 0.5);
    }

    @Test
    void CONFIRMED_HIGH_INTERIOR_EXTREME_variation1() {
        run(23, highTurn(), 6, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void CONFIRMED_HIGH_INTERIOR_EXTREME_variation2() {
        run(24, new double[] { 1, 3, 5, 4, 2, 0, -2 }, 6, ElliottDegree.MINUTE, 0.1, 0.5);
    }

    @Test
    void CONFIRMED_HIGH_INTERIOR_EXTREME_variation3() {
        run(25, new double[] { 2, 5, 8, 6, 3, 0, -3 }, 100, ElliottDegree.MINOR, 0.1, 0.5);
    }

    @Test
    void LOW_INTERIOR_EXTREME_variation1() {
        run(26, lowTurn(), 6, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void LOW_INTERIOR_EXTREME_variation2() {
        run(27, new double[] { 10, 8, 6, 7, 9, 11, 13 }, 6, ElliottDegree.MINUTE, 0.1, 0.5);
    }

    @Test
    void LOW_INTERIOR_EXTREME_variation3() {
        run(28, new double[] { 15, 12, 9, 10, 12, 14, 16 }, 100, ElliottDegree.MINOR, 0.1, 0.5);
    }

    @Test
    void EQUAL_EXTREME_RETAINS_EARLIEST_INDEX_variation1() {
        run(29, new double[] { 1, 2, 3, 2, 1, 0, -1 }, 6, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void EQUAL_EXTREME_RETAINS_EARLIEST_INDEX_variation2() {
        run(30, new double[] { 7, 6, 5, 6, 7, 8, 9 }, 6, ElliottDegree.MINUTE, 0.1, 0.5);
    }

    @Test
    void OPPOSITE_PIVOT_REJECTED_BY_ATR_MAGNITUDE_variation1() {
        run(31, wave(), 16, ElliottDegree.MINOR, 0.1, 100.0);
    }

    @Test
    void OPPOSITE_PIVOT_REJECTED_BY_ATR_MAGNITUDE_variation2() {
        run(32, wave(), 100, ElliottDegree.INTERMEDIATE, 0.1, 100.0);
    }

    @Test
    void OPPOSITE_PIVOT_REJECTED_BY_ATR_MAGNITUDE_variation3() {
        run(33, compactWave(), 16, ElliottDegree.MINUTE, 0.1, 100.0);
    }

    @Test
    void OPPOSITE_PIVOT_ACCEPTED_AT_OR_ABOVE_ATR_MAGNITUDE_variation1() {
        run(34, wave(), 16, ElliottDegree.MINOR, 0.1, 0.5);
    }

    @Test
    void OPPOSITE_PIVOT_ACCEPTED_AT_OR_ABOVE_ATR_MAGNITUDE_variation2() {
        run(35, compactWave(), 16, ElliottDegree.INTERMEDIATE, 0.1, 0.5);
    }

    @Test
    void OPPOSITE_PIVOT_ACCEPTED_AT_OR_ABOVE_ATR_MAGNITUDE_variation3() {
        run(36, wave(), 100, ElliottDegree.MINUTE, 0.1, 0.5);
    }

    @Test
    void OPPOSITE_PIVOT_ACCEPTED_AT_OR_ABOVE_ATR_MAGNITUDE_variation4() {
        run(37, extendedWave(), 100, ElliottDegree.MINOR, 0.1, 0.5);
    }

    @Test
    void SAME_TYPE_HIGH_REPLACED_BY_HIGHER_HIGH_variation1() {
        run(38, wave(), 16, ElliottDegree.INTERMEDIATE, 0.1, 0.0);
    }

    @Test
    void SAME_TYPE_HIGH_REPLACED_BY_HIGHER_HIGH_variation2() {
        run(39, extendedWave(), 100, ElliottDegree.MINUTE, 0.1, 0.0);
    }

    @Test
    void SAME_TYPE_HIGH_REPLACED_BY_HIGHER_HIGH_variation3() {
        run(40, highTurn(), 6, ElliottDegree.MINOR, 0.1, 0.0);
    }

    @Test
    void SAME_TYPE_LOW_REPLACED_BY_LOWER_LOW_variation1() {
        run(41, wave(), 16, ElliottDegree.INTERMEDIATE, 0.1, 0.0);
    }

    @Test
    void SAME_TYPE_LOW_REPLACED_BY_LOWER_LOW_variation2() {
        run(42, extendedWave(), 100, ElliottDegree.MINUTE, 0.1, 0.0);
    }

    @Test
    void SAME_TYPE_LOW_REPLACED_BY_LOWER_LOW_variation3() {
        run(43, lowTurn(), 6, ElliottDegree.MINOR, 0.1, 0.0);
    }

    @Test
    void SAME_TYPE_NON_EXTREME_PIVOT_RETAINED_variation1() {
        run(44, wave(), 14, ElliottDegree.INTERMEDIATE, 0.1, 0.0);
    }

    @Test
    void SAME_TYPE_NON_EXTREME_PIVOT_RETAINED_variation2() {
        run(45, compactWave(), 16, ElliottDegree.MINUTE, 0.1, 0.0);
    }

    @Test
    void SAME_TYPE_NON_EXTREME_PIVOT_RETAINED_variation3() {
        run(46, extendedWave(), 100, ElliottDegree.MINOR, 0.1, 0.0);
    }
}
