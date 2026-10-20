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

    private static final ElliottDegree MINUTE = ElliottDegree.valueOf("MINUTE");

    private static final ElliottDegree MINOR = ElliottDegree.valueOf("MINOR");

    private static void run(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingSwingDetectorArguments arguments = new SlopeChangeSwingSwingDetectorArguments(followUp);
        SwingDetectorResult followUpOutput = arguments.detector.detect(arguments.series, arguments.index, arguments.degree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static BarSeries series(double... closes) {
        return seriesWithVolumes(closes, repeat(closes.length, 1.0));
    }

    private static BarSeries seriesWithVolumes(double[] closes, double[] volumes) {
        BarSeries result = new BaseBarSeriesBuilder().withName("generated").build();
        for (int i = 0; i < closes.length; i++) {
            addBar(result, closes[i], 1.0, volumes[i], 0.0);
        }
        return result;
    }

    private static BarSeries seriesWithHighOffsets(double[] closes, double[] offsets) {
        BarSeries result = new BaseBarSeriesBuilder().withName("generated-highs").build();
        for (int i = 0; i < closes.length; i++) {
            addBar(result, closes[i], offsets[i], 1.0, 0.0);
        }
        return result;
    }

    private static BarSeries seriesWithLowOffsets(double[] closes, double[] offsets) {
        BarSeries result = new BaseBarSeriesBuilder().withName("generated-lows").build();
        for (int i = 0; i < closes.length; i++) {
            addBar(result, closes[i], 1.0, 1.0, offsets[i]);
        }
        return result;
    }

    private static void addBar(BarSeries series, double close, double highOffset, double volume, double lowOffset) {
        Instant begin = Instant.parse("2020-01-01T00:00:00Z").plusSeconds(series.getBarCount() * 60L);
        Instant end = begin.plusSeconds(60L);
        Num open = series.numFactory().numOf(close);
        Num high = series.numFactory().numOf(close + highOffset);
        Num low = series.numFactory().numOf(close - lowOffset);
        Num closeValue = series.numFactory().numOf(close);
        Num volumeValue = series.numFactory().numOf(volume);
        Num amount = series.numFactory().numOf(volume);
        series.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, open, high, low, closeValue, volumeValue, amount, 1));
    }

    private static double[] repeat(int length, double value) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = value;
        }
        return values;
    }

    private static BarSeries reversalSeries() {
        return series(10, 12, 14, 16, 18, 16, 14, 12, 10, 8, 10, 12, 14, 16, 18, 20);
    }

    private static final class SlopeChangeSwingSwingDetectorArguments {

        private final SlopeChangeSwingDetector detector;

        private final BarSeries series;

        private final int index;

        private final ElliottDegree degree;

        private SlopeChangeSwingSwingDetectorArguments(Object[] followUp) {
            this.detector = (SlopeChangeSwingDetector) followUp[0];
            this.series = (BarSeries) followUp[1];
            this.index = (Integer) followUp[2];
            this.degree = (ElliottDegree) followUp[3];
        }
    }

    @Test
    public void TOO_SHORT_FOR_ANY_CANDIDATE_variation1() {
        run(new SlopeChangeSwingDetector(3), series(10, 11, 12, 13, 14), 2, MINOR);
    }

    @Test
    public void EXACT_MINIMUM_CANDIDATE_LENGTH_variation1() {
        run(new SlopeChangeSwingDetector(2), series(10, 12, 14, 13, 11, 10), 5, MINUTE);
    }

    @Test
    public void INDEX_BELOW_SERIES_RANGE_variation1() {
        run(new SlopeChangeSwingDetector(2), reversalSeries(), -7, MINOR);
    }

    @Test
    public void INDEX_ABOVE_SERIES_RANGE_variation1() {
        run(new SlopeChangeSwingDetector(2), reversalSeries(), 1000, MINUTE);
    }

    @Test
    public void INDEX_AT_END_BOUNDARY_variation1() {
        run(new SlopeChangeSwingDetector(2), reversalSeries(), 15, MINOR);
    }

    @Test
    public void FLAT_CLOSE_SERIES_variation1() {
        run(new SlopeChangeSwingDetector(2), series(repeat(24, 50.0)), 23, MINUTE);
    }

    @Test
    public void SAME_SIGN_POSITIVE_SLOPES_variation1() {
        run(new SlopeChangeSwingDetector(2), series(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 100, MINOR);
    }

    @Test
    public void SAME_SIGN_NEGATIVE_SLOPES_variation1() {
        run(new SlopeChangeSwingDetector(2), series(20, 19, 18, 17, 16, 15, 14, 13, 12, 11), -1, MINUTE);
    }

    @Test
    public void HIGH_REVERSAL_CONFIRMED_variation1() {
        run(new SlopeChangeSwingDetector(2), series(10, 12, 14, 16, 18, 17, 15, 13, 12, 11, 10, 9, 8, 7, 6), 14, MINOR);
    }

    @Test
    public void HIGH_REVERSAL_CONFIRMED_variation2() {
        run(new SlopeChangeSwingDetector(3), series(8, 10, 12, 14, 16, 18, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3), 17, MINUTE);
    }

    @Test
    public void LOW_REVERSAL_CONFIRMED_variation1() {
        run(new SlopeChangeSwingDetector(2), series(20, 18, 16, 14, 12, 10, 11, 13, 15, 17, 18, 19, 20, 21, 22), 14, MINOR);
    }

    @Test
    public void LOW_REVERSAL_CONFIRMED_variation2() {
        run(new SlopeChangeSwingDetector(3), series(24, 22, 20, 18, 16, 14, 12, 13, 15, 17, 19, 21, 22, 23, 24, 25, 26, 27), -2, MINUTE);
    }

    @Test
    public void SLOPE_CHANGE_BELOW_THRESHOLD_variation1() {
        run(new SlopeChangeSwingDetector(2), series(10, 11, 12, 11.9, 11.8, 11.7, 11.6, 11.5, 11.4, 11.3), 8, MINOR);
    }

    @Test
    public void PERSISTENCE_FAILS_ON_ZERO_variation1() {
        run(new SlopeChangeSwingDetector(3), series(10, 12, 14, 13, 13, 13, 12, 11, 10, 9, 8, 7), 11, MINOR);
    }

    @Test
    public void PERSISTENCE_FAILS_ON_WRONG_SIGN_variation1() {
        run(new SlopeChangeSwingDetector(2), series(10, 12, 14, 13, 12, 13, 14, 15, 16, 17), 9, MINUTE);
    }

    @Test
    public void HIGH_EXTREME_AT_INTERVAL_START_variation1() {
        run(new SlopeChangeSwingDetector(2), seriesWithHighOffsets(new double[] { 10, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4 }, new double[] { 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }), 11, MINOR);
    }

    @Test
    public void LOW_EXTREME_AT_INTERVAL_END_variation1() {
        run(new SlopeChangeSwingDetector(2), seriesWithLowOffsets(new double[] { 20, 18, 16, 14, 12, 13, 15, 17, 19, 20, 21, 22 }, new double[] { 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1 }), 11, MINUTE);
    }

    @Test
    public void TIED_EXTREME_KEEPS_FIRST_variation1() {
        run(new SlopeChangeSwingDetector(2), seriesWithHighOffsets(new double[] { 10, 12, 14, 13, 11, 10, 9, 8, 7, 6, 5, 4 }, new double[] { 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1 }), 11, MINOR);
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_MAGNITUDE_FILTER_variation1() {
        run(new SlopeChangeSwingDetector(2), reversalSeries(), -1, MINUTE);
    }

    @Test
    public void MAGNITUDE_FILTER_REJECTS_WEAK_REVERSAL_variation1() {
        run(new SlopeChangeSwingDetector(2), series(10, 12, 14, 12, 10, 11, 12, 11, 10, 11, 12, 11, 10), 12, MINUTE);
    }

    @Test
    public void MAGNITUDE_FILTER_ACCEPTS_EQUAL_THRESHOLD_variation1() {
        run(new SlopeChangeSwingDetector(2), series(10, 12, 14, 12, 10, 8, 10, 12, 15, 13, 11, 9, 8, 10, 12), 14, MINOR);
    }

    @Test
    public void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation1() {
        run(new SlopeChangeSwingDetector(3), series(10, 12, 14, 16, 14, 12, 10, 11, 12, 13, 12, 11, 10, 9, 8, 7, 6, 5), -1, MINUTE);
    }

    @Test
    public void ALTERNATING_PIVOTS_CREATE_SWINGS_variation1() {
        run(new SlopeChangeSwingDetector(2), series(10, 12, 14, 12, 10, 8, 10, 12, 14, 12, 10, 8, 10, 12, 14, 12, 10, 8), 17, MINOR);
    }

    @Test
    public void SAME_TYPE_MORE_EXTREME_REPLACES_variation1() {
        run(new SlopeChangeSwingDetector(2), series(10, 12, 15, 13, 11, 10, 12, 16, 14, 12, 10, 9, 8, 7, 6), 14, MINOR);
    }

    @Test
    public void SAME_TYPE_NOT_MORE_EXTREME_REMAINS_variation1() {
        run(new SlopeChangeSwingDetector(2), series(10, 12, 15, 13, 11, 10, 12, 14, 13, 12, 11, 10, 9, 8, 7), -4, MINUTE);
    }

    @Test
    public void DEGREE_PROPAGATES_TO_SWINGS_variation1() {
        run(new SlopeChangeSwingDetector(2), series(10, 13, 16, 14, 11, 8, 11, 15, 18, 15, 11, 8, 10, 14, 17, 14, 10, 7), 17, MINOR);
    }

    @Test
    public void PRICE_TRANSLATION_WITH_NONZERO_VOLUME_variation1() {
        run(new SlopeChangeSwingDetector(2), seriesWithVolumes(new double[] { 10, 12, 14, 12, 10, 8, 10, 12, 14, 12, 10, 8, 10, 12, 14 }, new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }), 14, MINUTE);
    }

    @Test
    public void PRICE_TRANSLATION_WITH_ZERO_VOLUME_variation1() {
        run(new SlopeChangeSwingDetector(2), seriesWithVolumes(new double[] { 10, 12, 14, 12, 10, 8, 10, 12, 14, 12, 10, 8, 10, 12, 14 }, new double[] { 1, 0, 3, 0, 5, 6, 0, 8, 9, 10, 0, 12, 13, 14, 0 }), 100, MINOR);
    }

    @Test
    public void TRANSLATION_PRESERVES_EMPTY_RESULT_variation1() {
        run(new SlopeChangeSwingDetector(3), series(repeat(10, 25.0)), 9, MINUTE);
    }
}
