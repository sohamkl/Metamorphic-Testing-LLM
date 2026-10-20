import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static void exercise(int index, ElliottDegree degree, double... closes) {
        BarSeries sourceSeries = series(closes);
        SlopeChangeSwingDetector sourceDetector = new SlopeChangeSwingDetector(3);
        SwingDetectorResult sourceOutput = sourceDetector.detect(sourceSeries, index, degree);
        FollowUp followUp = generateFollowUp(sourceDetector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = followUp.detector.detect(followUp.series, followUp.index, followUp.degree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder().withName(sourceSeries.getName() + "-translated").withNumFactory(sourceSeries.numFactory()).build();
        Num translation = sourceSeries.numFactory().numOf(100.0);
        for (int barIndex = sourceSeries.getBeginIndex(); barIndex <= sourceSeries.getEndIndex(); barIndex++) {
            Bar sourceBar = sourceSeries.getBar(barIndex);
            Num translatedAmount = sourceBar.getAmount().plus(translation.multipliedBy(sourceBar.getVolume()));
            translatedSeries.addBar(new BaseBar(sourceBar.getTimePeriod(), sourceBar.getBeginTime(), sourceBar.getEndTime(), sourceBar.getOpenPrice().plus(translation), sourceBar.getHighPrice().plus(translation), sourceBar.getLowPrice().plus(translation), sourceBar.getClosePrice().plus(translation), sourceBar.getVolume(), translatedAmount, sourceBar.getTrades()));
        }
        return new FollowUp(new SlopeChangeSwingDetector(detector.getConfig()), translatedSeries, index, degree);
    }

    private static BarSeries series(double... closes) {
        BarSeries result = new BaseBarSeriesBuilder().withName("source").build();
        Instant base = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < closes.length; i++) {
            Num close = result.numFactory().numOf(closes[i]);
            Num range = result.numFactory().numOf(2);
            Num volume = result.numFactory().numOf(10);
            Instant begin = base.plusSeconds(i * 60L);
            result.addBar(new BaseBar(Duration.ofMinutes(1), begin, begin.plusSeconds(60), close, close.plus(range), close.minus(range), close, volume, close.multipliedBy(volume), 1L));
        }
        return result;
    }

    private static double[] highTurn() {
        return new double[] { 100, 110, 120, 110, 100, 90, 80 };
    }

    private static double[] lowTurn() {
        return new double[] { 120, 110, 100, 110, 120, 130, 140 };
    }

    private static final class FollowUp {

        private final SlopeChangeSwingDetector detector;

        private final BarSeries series;

        private final int index;

        private final ElliottDegree degree;

        private FollowUp(SlopeChangeSwingDetector detector, BarSeries series, int index, ElliottDegree degree) {
            this.detector = detector;
            this.series = series;
            this.index = index;
            this.degree = degree;
        }
    }

    @Test
    void single_bar_1() {
        exercise(0, ElliottDegree.MINUTE, 100);
    }

    @Test
    void index_below_begin_1() {
        exercise(-1, ElliottDegree.MINUETTE, highTurn());
    }

    @Test
    void integer_min_index_1() {
        exercise(Integer.MIN_VALUE, ElliottDegree.SUB_MINUETTE, 100, 110, 120, 110, 100, 90, 80);
    }

    @Test
    void index_at_begin_1() {
        exercise(0, ElliottDegree.MINOR, highTurn());
    }

    @Test
    void series_shorter_than_first_candidate_end_1() {
        exercise(5, ElliottDegree.INTERMEDIATE, 100, 101, 102, 103, 104, 105);
    }

    @Test
    void exactly_one_candidate_length_1() {
        exercise(6, ElliottDegree.PRIMARY, 100, 110, 120, 130, 140, 150, 160);
    }

    @Test
    void index_before_last_candidate_1() {
        exercise(5, ElliottDegree.CYCLE, 100, 110, 120, 110, 100, 90, 80, 70);
    }

    @Test
    void index_at_last_candidate_boundary_1() {
        exercise(6, ElliottDegree.SUPER_CYCLE, highTurn());
    }

    @Test
    void index_greater_than_end_1() {
        exercise(100, ElliottDegree.GRAND_SUPERCYCLE, highTurn());
    }

    @Test
    void integer_max_index_1() {
        exercise(Integer.MAX_VALUE, ElliottDegree.MINUTE, lowTurn());
    }

    @Test
    void flat_closes_1() {
        exercise(8, ElliottDegree.MINOR, 100, 100, 100, 100, 100, 100, 100, 100, 100);
    }

    @Test
    void monotonic_uptrend_1() {
        exercise(8, ElliottDegree.INTERMEDIATE, 100, 105, 110, 115, 120, 125, 130, 135, 140);
    }

    @Test
    void monotonic_downtrend_1() {
        exercise(8, ElliottDegree.PRIMARY, 140, 135, 130, 125, 120, 115, 110, 105, 100);
    }

    @Test
    void positive_to_less_positive_1() {
        exercise(6, ElliottDegree.CYCLE, 100, 120, 140, 145, 150, 155, 160);
    }

    @Test
    void negative_to_less_negative_1() {
        exercise(6, ElliottDegree.SUPER_CYCLE, 160, 140, 120, 115, 110, 105, 100);
    }

    @Test
    void reversal_below_min_slope_change_1() {
        exercise(6, ElliottDegree.GRAND_SUPERCYCLE, 100, 101, 102, 101.9, 101.8, 101.7, 101.6);
    }

    @Test
    void reversal_at_min_slope_change_1() {
        exercise(6, ElliottDegree.MINUTE, highTurn());
    }

    @Test
    void high_persistence_failure_on_first_bar_1() {
        exercise(6, ElliottDegree.MINUETTE, 100, 110, 120, 110, 100, 110, 120);
    }

    @Test
    void high_persistence_failure_late_1() {
        exercise(7, ElliottDegree.SUB_MINUETTE, 100, 110, 120, 110, 100, 90, 100, 110);
    }

    @Test
    void low_persistence_failure_1() {
        exercise(6, ElliottDegree.MINOR, 120, 110, 100, 110, 120, 110, 100);
    }

    @Test
    void confirmed_high_first_pivot_1() {
        exercise(6, ElliottDegree.INTERMEDIATE, highTurn());
    }

    @Test
    void confirmed_low_first_pivot_1() {
        exercise(6, ElliottDegree.PRIMARY, lowTurn());
    }

    @Test
    void high_extreme_not_at_candidate_1() {
        exercise(6, ElliottDegree.CYCLE, 100, 110, 120, 125, 100, 90, 80);
    }

    @Test
    void low_extreme_not_at_candidate_1() {
        exercise(6, ElliottDegree.SUPER_CYCLE, 140, 130, 120, 115, 140, 150, 160);
    }

    @Test
    void high_extreme_tie_1() {
        exercise(6, ElliottDegree.GRAND_SUPERCYCLE, 100, 110, 120, 120, 100, 90, 80);
    }

    @Test
    void low_extreme_tie_1() {
        exercise(6, ElliottDegree.MINUTE, 140, 130, 120, 120, 140, 150, 160);
    }

    @Test
    void alternating_high_low_append_1() {
        exercise(14, ElliottDegree.MINUETTE, 100, 110, 120, 110, 100, 90, 80, 90, 100, 110, 120, 110, 100, 90, 80);
    }

    @Test
    void alternating_low_high_append_1() {
        exercise(14, ElliottDegree.SUB_MINUETTE, 140, 130, 120, 130, 140, 150, 160, 150, 140, 130, 120, 130, 140, 150, 160);
    }

    @Test
    void atr_magnitude_rejection_1() {
        exercise(12, ElliottDegree.MINOR, 100, 110, 120, 110, 100, 90, 80, 90, 95, 90, 85, 80, 75);
    }

    @Test
    void atr_magnitude_equality_acceptance_1() {
        exercise(10, ElliottDegree.INTERMEDIATE, 100, 110, 120, 110, 100, 90, 80, 90, 100, 110, 120);
    }

    @Test
    void zero_atr_multiplier_bypass_1() {
        exercise(10, ElliottDegree.PRIMARY, 120, 110, 100, 110, 120, 130, 140, 130, 120, 110, 100);
    }

    @Test
    void same_high_replacement_1() {
        exercise(14, ElliottDegree.CYCLE, 100, 110, 120, 110, 100, 90, 80, 100, 120, 140, 130, 120, 110, 100, 90);
    }

    @Test
    void same_low_replacement_1() {
        exercise(14, ElliottDegree.SUPER_CYCLE, 160, 150, 140, 150, 160, 170, 180, 160, 140, 120, 130, 140, 150, 160, 170);
    }

    @Test
    void same_high_non_replacement_1() {
        exercise(12, ElliottDegree.GRAND_SUPERCYCLE, 100, 110, 120, 110, 100, 90, 80, 90, 100, 105, 100, 95, 90);
    }

    @Test
    void same_low_non_replacement_1() {
        exercise(12, ElliottDegree.MINUTE, 150, 140, 130, 140, 150, 160, 170, 160, 150, 145, 150, 155, 160);
    }

    @Test
    void three_alternating_pivots_1() {
        exercise(18, ElliottDegree.MINOR, 100, 110, 120, 110, 100, 90, 80, 90, 100, 110, 120, 110, 100, 90, 80, 90, 100, 110, 120);
    }
}
