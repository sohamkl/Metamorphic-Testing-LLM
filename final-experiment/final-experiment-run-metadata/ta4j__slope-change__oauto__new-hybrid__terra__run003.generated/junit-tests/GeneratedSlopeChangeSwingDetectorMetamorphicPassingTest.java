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

    private static void verify(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder().withName(sourceSeries.getName() + "-translated").withNumFactory(sourceSeries.numFactory()).build();
        Num translation = sourceSeries.numFactory().numOf(100.0);
        for (int barIndex = sourceSeries.getBeginIndex(); barIndex <= sourceSeries.getEndIndex(); barIndex++) {
            Bar sourceBar = sourceSeries.getBar(barIndex);
            translatedSeries.addBar(new BaseBar(sourceBar.getTimePeriod(), sourceBar.getBeginTime(), sourceBar.getEndTime(), sourceBar.getOpenPrice().plus(translation), sourceBar.getHighPrice().plus(translation), sourceBar.getLowPrice().plus(translation), sourceBar.getClosePrice().plus(translation), sourceBar.getVolume(), sourceBar.getAmount().plus(translation.multipliedBy(sourceBar.getVolume())), sourceBar.getTrades()));
        }
        return new Object[] { new SlopeChangeSwingDetector(detector.getConfig()), translatedSeries, index, degree };
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries series(double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 1.0;
            lows[i] = closes[i] - 1.0;
        }
        return series(closes, highs, lows);
    }

    private static BarSeries series(double[] closes, double[] highs, double[] lows) {
        BarSeries result = new BaseBarSeriesBuilder().withName("source").build();
        for (int i = 0; i < closes.length; i++) {
            Instant begin = Instant.ofEpochSecond(i * 60L);
            Instant end = begin.plusSeconds(60L);
            Num open = result.numFactory().numOf(closes[i]);
            Num high = result.numFactory().numOf(highs[i]);
            Num low = result.numFactory().numOf(lows[i]);
            Num close = result.numFactory().numOf(closes[i]);
            Num volume = result.numFactory().numOf(2.0);
            Num amount = close.multipliedBy(volume);
            result.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, open, high, low, close, volume, amount, 0L));
        }
        return result;
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMPS_WITHOUT_CANDIDATE_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(10.0, 11.0), -1, ElliottDegree.MINUTE);
    }

    @Test
    void ONE_BAR_SHORT_OF_FIRST_CANDIDATE_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0), 2, ElliottDegree.MINOR);
    }

    @Test
    void EXACT_MINIMUM_LENGTH_SINGLE_HIGH_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0), 3, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void EXACT_MINIMUM_LENGTH_SINGLE_LOW_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(10.0, 0.0, 10.0, 20.0), 3, ElliottDegree.PRIMARY);
    }

    @Test
    void SAME_DIRECTION_SLOPES_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(1.0, 2.0, 3.0, 4.0, 5.0), 4, ElliottDegree.CYCLE);
    }

    @Test
    void SLOPE_CHANGE_STRICTLY_BELOW_THRESHOLD_variation1() {
        verify(detector(2, 1, 1, 100.0, 0.0), series(0.0, 1.0, 0.0, -1.0), 9, ElliottDegree.MINUETTE);
    }

    @Test
    void HIGH_PERSISTENCE_FAILURE_variation1() {
        verify(detector(2, 2, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0, 0.0), 4, ElliottDegree.MINOR);
    }

    @Test
    void LOW_PERSISTENCE_FAILURE_variation1() {
        verify(detector(2, 2, 1, 0.0, 0.0), series(10.0, 0.0, 10.0, 20.0, 10.0), 4, ElliottDegree.MINUTE);
    }

    @Test
    void HIGH_INTERIOR_EXTREME_variation1() {
        verify(detector(3, 1, 1, 0.0, 0.0), series(new double[] { 0.0, 10.0, 20.0, 10.0, 0.0, -10.0 }, new double[] { 1.0, 11.0, 21.0, 30.0, 1.0, -9.0 }, new double[] { -1.0, 9.0, 19.0, 9.0, -1.0, -11.0 }), 5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void LOW_INTERIOR_EXTREME_variation1() {
        verify(detector(3, 1, 1, 0.0, 0.0), series(new double[] { 20.0, 10.0, 0.0, 10.0, 20.0, 30.0 }, new double[] { 21.0, 11.0, 1.0, 11.0, 21.0, 31.0 }, new double[] { 19.0, 9.0, -1.0, -20.0, 19.0, 29.0 }), 99, ElliottDegree.PRIMARY);
    }

    @Test
    void HIGH_TIED_EXTREME_EARLIEST_INDEX_variation1() {
        verify(detector(3, 1, 1, 0.0, 0.0), series(new double[] { 0.0, 10.0, 20.0, 10.0, 0.0, -10.0 }, new double[] { 1.0, 11.0, 30.0, 30.0, 1.0, -9.0 }, new double[] { -1.0, 9.0, 19.0, 9.0, -1.0, -11.0 }), 5, ElliottDegree.MINOR);
    }

    @Test
    void LOW_TIED_EXTREME_EARLIEST_INDEX_variation1() {
        verify(detector(3, 1, 1, 0.0, 0.0), series(new double[] { 20.0, 10.0, 0.0, 10.0, 20.0, 30.0 }, new double[] { 21.0, 11.0, 1.0, 11.0, 21.0, 31.0 }, new double[] { 19.0, 9.0, -30.0, -30.0, 19.0, 29.0 }), 5, ElliottDegree.MINUTE);
    }

    @Test
    void ALTERNATING_HIGH_LOW_CREATES_SWING_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0, 0.0, 10.0), 5, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void THREE_ALTERNATING_PIVOTS_CREATE_TWO_SWINGS_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0, 0.0, 10.0, 0.0, -10.0), 99, ElliottDegree.PRIMARY);
    }

    @Test
    void STRONGER_CONSECUTIVE_HIGH_REPLACES_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0, 0.0, 20.0, 0.0, -20.0), 7, ElliottDegree.CYCLE);
    }

    @Test
    void WEAKER_CONSECUTIVE_HIGH_IS_RETAINED_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 20.0, 0.0, -10.0, 0.0, 10.0, 0.0, -20.0), 7, ElliottDegree.MINOR);
    }

    @Test
    void STRONGER_CONSECUTIVE_LOW_REPLACES_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(20.0, 0.0, 20.0, 30.0, 10.0, -10.0, 10.0, 20.0), 7, ElliottDegree.MINUTE);
    }

    @Test
    void WEAKER_CONSECUTIVE_LOW_IS_RETAINED_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(20.0, 0.0, 20.0, 30.0, 20.0, 10.0, 20.0, 30.0), 99, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void ATR_FILTER_REJECTS_SECOND_PIVOT_variation1() {
        verify(detector(2, 1, 1, 0.0, 100.0), series(0.0, 10.0, 0.0, -10.0, 0.0, 10.0, 0.0, -10.0), 7, ElliottDegree.PRIMARY);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASSES_FILTER_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 1.0, 0.0, -1.0, 0.0, 1.0), 5, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    void INDEX_ABOVE_END_CLAMPS_TO_FULL_SERIES_variation1() {
        verify(detector(2, 1, 1, 0.0, 0.0), series(0.0, 10.0, 0.0, -10.0), 1000, ElliottDegree.MINOR);
    }
}
