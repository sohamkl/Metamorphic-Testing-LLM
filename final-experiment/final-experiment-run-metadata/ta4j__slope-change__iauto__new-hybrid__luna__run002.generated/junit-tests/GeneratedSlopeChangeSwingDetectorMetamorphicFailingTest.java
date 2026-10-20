import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.indicators.elliott.ElliottSwing;
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static void verify(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(SwingDetectorResult source, SwingDetectorResult followUp) {
        if (source.pivots().size() != followUp.pivots().size()) {
            throw new AssertionError("Pivot counts differ");
        }
        for (int i = 0; i < source.pivots().size(); i++) {
            SwingPivot sourcePivot = source.pivots().get(i);
            SwingPivot followUpPivot = followUp.pivots().get(i);
            if (sourcePivot.index() != followUpPivot.index()) {
                throw new AssertionError("Pivot indices differ at position " + i);
            }
            if (sourcePivot.type() != followUpPivot.type()) {
                throw new AssertionError("Pivot types differ at position " + i);
            }
            assertTranslated(sourcePivot.price(), followUpPivot.price(), "pivot price", i);
        }
        if (source.swings().size() != followUp.swings().size()) {
            throw new AssertionError("Swing counts differ");
        }
        for (int i = 0; i < source.swings().size(); i++) {
            ElliottSwing sourceSwing = source.swings().get(i);
            ElliottSwing followUpSwing = followUp.swings().get(i);
            if (sourceSwing.fromIndex() != followUpSwing.fromIndex()) {
                throw new AssertionError("Swing start indices differ at position " + i);
            }
            if (sourceSwing.toIndex() != followUpSwing.toIndex()) {
                throw new AssertionError("Swing end indices differ at position " + i);
            }
            if (sourceSwing.degree() != followUpSwing.degree()) {
                throw new AssertionError("Swing degrees differ at position " + i);
            }
            assertTranslated(sourceSwing.fromPrice(), followUpSwing.fromPrice(), "swing start price", i);
            assertTranslated(sourceSwing.toPrice(), followUpSwing.toPrice(), "swing end price", i);
        }
    }

    private static void assertTranslated(Num source, Num followUp, String field, int position) {
        double expected = source.doubleValue() + 100.0;
        double actual = followUp.doubleValue();
        double tolerance = Math.max(1.0e-9, Math.max(Math.abs(expected), Math.abs(actual)) * 1.0e-12);
        if (!Double.isFinite(expected) || !Double.isFinite(actual) || Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Translated " + field + " differs at position " + position + ": expected " + expected + " but was " + actual);
        }
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeriesBuilder().withName("empty").build();
    }

    private static BarSeries risingSeries(int length) {
        BaseBarSeries series = new BaseBarSeriesBuilder().withName("rising").build();
        for (int i = 0; i < length; i++) {
            addBar(series, 10.0 + i, 1.0 + (i % 3));
        }
        return series;
    }

    private static BarSeries patternedSeries(int length, int variation) {
        BaseBarSeries series = new BaseBarSeriesBuilder().withName("pattern-" + variation).build();
        double[] wave = { 1.0, 2.0, 3.0, 4.0, 3.0, 2.0, 1.0, 2.0, 3.0, 5.0, 3.0, 2.0, 1.0, 3.0, 4.0, 2.0 };
        for (int i = 0; i < length; i++) {
            double close = wave[(i + variation) % wave.length] + variation * 0.01;
            double volume = 1.0 + (i % 4);
            addBar(series, close, volume);
        }
        return series;
    }

    private static BarSeries tiedSeries(int length) {
        BaseBarSeries series = new BaseBarSeriesBuilder().withName("tied").build();
        double[] wave = { 1.0, 2.0, 3.0, 4.0, 3.0, 2.0, 1.0, 2.0, 3.0, 4.0, 3.0, 2.0 };
        for (int i = 0; i < length; i++) {
            double close = wave[i % wave.length];
            double high = close + ((i % 6 == 2 || i % 6 == 3) ? 2.0 : 1.0);
            double low = close - 1.0;
            addBar(series, close, 1.0, high, low);
        }
        return series;
    }

    private static BarSeries variedVolumeSeries(int length) {
        BaseBarSeries series = new BaseBarSeriesBuilder().withName("varied-volume").build();
        double[] wave = { 1.0, 2.0, 3.0, 5.0, 3.0, 2.0, 1.0, 2.0, 4.0, 6.0, 3.0, 2.0 };
        for (int i = 0; i < length; i++) {
            double close = wave[i % wave.length];
            double volume = 0.5 + (i % 5) * 1.25;
            addBar(series, close, volume);
        }
        return series;
    }

    private static void addBar(BaseBarSeries series, double close, double volume) {
        addBar(series, close, volume, close + 1.0, close - 1.0);
    }

    private static void addBar(BaseBarSeries series, double close, double volume, double high, double low) {
        Instant begin = Instant.parse("2020-01-01T00:00:00Z").plus(Duration.ofMinutes(series.getBarCount()));
        Instant end = begin.plus(Duration.ofMinutes(1));
        Num openPrice = series.numFactory().numOf(close);
        Num highPrice = series.numFactory().numOf(high);
        Num lowPrice = series.numFactory().numOf(low);
        Num closePrice = series.numFactory().numOf(close);
        Num volumeValue = series.numFactory().numOf(volume);
        Num amountValue = series.numFactory().numOf(close * volume);
        Bar bar = new BaseBar(Duration.ofMinutes(1), begin, end, openPrice, highPrice, lowPrice, closePrice, volumeValue, amountValue, 1);
        series.addBar(bar);
    }
}
