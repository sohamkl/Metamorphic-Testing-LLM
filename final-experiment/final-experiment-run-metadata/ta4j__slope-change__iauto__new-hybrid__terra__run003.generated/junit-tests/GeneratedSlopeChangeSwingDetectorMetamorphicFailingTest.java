import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.indicators.elliott.ElliottSwing;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private void exercise(double[] closes, int index, ElliottDegree degree, SlopeChangeConfig configuration) {
        exerciseWithWicks(closes, closes, closes, index, degree, configuration);
    }

    private void exerciseWithWicks(double[] closes, double[] highs, double[] lows, int index, ElliottDegree degree, SlopeChangeConfig configuration) {
        BarSeries sourceSeries = series(closes, highs, lows);
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(configuration);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private SlopeChangeConfig config(int window, int confirmationBars, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeConfig(window, confirmationBars, 1, minSlopeChange, minAtrReversal);
    }

    private BarSeries series(double[] closes, double[] highs, double[] lows) {
        if (closes.length != highs.length || closes.length != lows.length) {
            throw new IllegalArgumentException("OHLC fixture arrays must have the same length");
        }
        BarSeries series = new BaseBarSeriesBuilder().withName("metamorphic-source").build();
        Instant base = Instant.parse("2020-01-01T09:00:00Z");
        Duration period = Duration.ofMinutes(1);
        for (int i = 0; i < closes.length; i++) {
            Num close = series.numFactory().numOf(closes[i]);
            Num high = series.numFactory().numOf(highs[i]);
            Num low = series.numFactory().numOf(lows[i]);
            Instant begin = base.plusSeconds(60L * i);
            Instant end = begin.plusSeconds(60L);
            series.addBar(new BaseBar(period, begin, end, close, high, low, close, series.numFactory().one(), close, 1L));
        }
        return series;
    }

    private void assertMetamorphicRelation(SwingDetectorResult sourceOutput, SwingDetectorResult followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");
        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();
        if (sourcePivots.size() != followUpPivots.size()) {
            throw new AssertionError("Pivot counts differ: source=" + sourcePivots.size() + ", follow-up=" + followUpPivots.size());
        }
        for (int i = 0; i < sourcePivots.size(); i++) {
            SwingPivot source = sourcePivots.get(i);
            SwingPivot followUp = followUpPivots.get(i);
            if (source.index() != followUp.index() || source.type() != followUp.type()) {
                throw new AssertionError("Corresponding pivot index or type differs at position " + i);
            }
            assertTranslated(source.price(), followUp.price(), "pivot price", i);
        }
        List<ElliottSwing> sourceSwings = sourceOutput.swings();
        List<ElliottSwing> followUpSwings = followUpOutput.swings();
        if (sourceSwings.size() != followUpSwings.size()) {
            throw new AssertionError("Swing counts differ: source=" + sourceSwings.size() + ", follow-up=" + followUpSwings.size());
        }
        for (int i = 0; i < sourceSwings.size(); i++) {
            ElliottSwing source = sourceSwings.get(i);
            ElliottSwing followUp = followUpSwings.get(i);
            if (source.fromIndex() != followUp.fromIndex() || source.toIndex() != followUp.toIndex() || !Objects.equals(source.degree(), followUp.degree())) {
                throw new AssertionError("Corresponding swing metadata differs at position " + i);
            }
            assertTranslated(source.fromPrice(), followUp.fromPrice(), "swing from price", i);
            assertTranslated(source.toPrice(), followUp.toPrice(), "swing to price", i);
        }
    }

    private void assertTranslated(Num source, Num followUp, String field, int position) {
        double expected = source.doubleValue() + 100.0;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(1.0e-9, Math.max(Math.abs(expected), Math.abs(actual)) * 1.0e-12);
        if (!Double.isFinite(expected) || !Double.isFinite(actual) || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Translated " + field + " differs at position " + position + ": expected " + expected + " +/- " + tolerance + ", actual " + actual);
        }
    }
}
