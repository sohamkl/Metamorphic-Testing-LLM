import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
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

    private static final double TRANSLATION = 100.0;

    private static final double ABSOLUTE_TOLERANCE = 1.0e-9;

    private static final double RELATIVE_TOLERANCE = 1.0e-12;

    private static void exercise(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int requestedIndex, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, requestedIndex, degree);
        Object[] followUpInput = mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, requestedIndex, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUpInput[0];
        BarSeries followUpSeries = (BarSeries) followUpInput[1];
        int followUpIndex = ((Integer) followUpInput[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUpInput[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeriesBuilder().withName("empty-source").build();
    }

    private static BarSeries series(double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 1.0;
            lows[i] = closes[i] - 1.0;
        }
        return rangedSeries(closes, highs, lows);
    }

    private static BarSeries rangedSeries(double[] closes, double[] highs, double[] lows) {
        if (closes.length != highs.length || closes.length != lows.length) {
            throw new IllegalArgumentException("Price arrays must have equal length");
        }
        BarSeries series = new BaseBarSeriesBuilder().withName("deterministic-source").build();
        Instant start = Instant.parse("2020-01-01T09:30:00Z");
        Duration period = Duration.ofMinutes(1);
        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = Math.max(highs[i], close);
            double low = Math.min(lows[i], close);
            Instant begin = start.plus(Duration.ofMinutes(i));
            Instant end = begin.plus(period);
            Num openNum = series.numFactory().numOf(close);
            Num highNum = series.numFactory().numOf(high);
            Num lowNum = series.numFactory().numOf(low);
            Num closeNum = series.numFactory().numOf(close);
            Num volume = series.numFactory().numOf(10.0 + i);
            Num amount = closeNum.multipliedBy(volume);
            Bar bar = new BaseBar(period, begin, end, openNum, highNum, lowNum, closeNum, volume, amount, 1L);
            series.addBar(bar);
        }
        return series;
    }

    private static void assertMetamorphicRelation(SwingDetectorResult sourceOutput, SwingDetectorResult followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");
        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();
        if (sourcePivots.size() != followUpPivots.size()) {
            throw new AssertionError("Pivot counts differ: source=" + sourcePivots.size() + ", follow-up=" + followUpPivots.size());
        }
        for (int i = 0; i < sourcePivots.size(); i++) {
            SwingPivot sourcePivot = sourcePivots.get(i);
            SwingPivot followUpPivot = followUpPivots.get(i);
            if (sourcePivot.index() != followUpPivot.index()) {
                throw new AssertionError("Pivot index differs at position " + i);
            }
            if (!Objects.equals(sourcePivot.type(), followUpPivot.type())) {
                throw new AssertionError("Pivot type differs at position " + i);
            }
            assertTranslated(sourcePivot.price(), followUpPivot.price(), "pivot price", i);
        }
        List<ElliottSwing> sourceSwings = sourceOutput.swings();
        List<ElliottSwing> followUpSwings = followUpOutput.swings();
        if (sourceSwings.size() != followUpSwings.size()) {
            throw new AssertionError("Swing counts differ: source=" + sourceSwings.size() + ", follow-up=" + followUpSwings.size());
        }
        for (int i = 0; i < sourceSwings.size(); i++) {
            ElliottSwing sourceSwing = sourceSwings.get(i);
            ElliottSwing followUpSwing = followUpSwings.get(i);
            if (sourceSwing.fromIndex() != followUpSwing.fromIndex()) {
                throw new AssertionError("Swing start index differs at position " + i);
            }
            if (sourceSwing.toIndex() != followUpSwing.toIndex()) {
                throw new AssertionError("Swing end index differs at position " + i);
            }
            if (!Objects.equals(sourceSwing.degree(), followUpSwing.degree())) {
                throw new AssertionError("Swing degree differs at position " + i);
            }
            assertTranslated(sourceSwing.fromPrice(), followUpSwing.fromPrice(), "swing start price", i);
            assertTranslated(sourceSwing.toPrice(), followUpSwing.toPrice(), "swing end price", i);
        }
    }

    private static void assertTranslated(Num source, Num followUp, String field, int position) {
        double expected = source.doubleValue() + TRANSLATION;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(ABSOLUTE_TOLERANCE, Math.max(Math.abs(expected), Math.abs(actual)) * RELATIVE_TOLERANCE);
        if (!Double.isFinite(expected) || !Double.isFinite(actual) || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Translated " + field + " differs at position " + position + ": expected " + expected + " +/- " + tolerance + " but was " + actual);
        }
    }
}
