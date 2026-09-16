import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final double TRANSLATION = 100.0;
    private static final double TOLERANCE = 1.0e-9;
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private static void verify(double[] closes, int index, ElliottDegree degree) {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(3), series(closes), index, degree);
    }

    private static void assertMetamorphicRelationFor(SlopeChangeSwingDetector detector, BarSeries source, int index,
            ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(source, index, degree);
        SwingDetectorResult followUpOutput = detector.detect(generateFollowUp(source), index, degree);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(SwingDetectorResult sourceOutput,
            SwingDetectorResult followUpOutput) {
        assertEquals(sourceOutput.pivots().size(), followUpOutput.pivots().size());
        assertEquals(sourceOutput.swings().size(), followUpOutput.swings().size());

        for (int i = 0; i < sourceOutput.pivots().size(); i++) {
            SwingPivot sourcePivot = sourceOutput.pivots().get(i);
            SwingPivot followUpPivot = followUpOutput.pivots().get(i);
            assertEquals(sourcePivot.index(), followUpPivot.index());
            assertEquals(sourcePivot.type(), followUpPivot.type());
            assertEquals(sourcePivot.price().doubleValue() + TRANSLATION, followUpPivot.price().doubleValue(),
                    TOLERANCE);
        }

        for (int i = 0; i < sourceOutput.swings().size(); i++) {
            var sourceSwing = sourceOutput.swings().get(i);
            var followUpSwing = followUpOutput.swings().get(i);
            assertEquals(sourceSwing.fromIndex(), followUpSwing.fromIndex());
            assertEquals(sourceSwing.toIndex(), followUpSwing.toIndex());
            assertEquals(sourceSwing.degree(), followUpSwing.degree());
            assertEquals(sourceSwing.fromPrice().doubleValue() + TRANSLATION,
                    followUpSwing.fromPrice().doubleValue(), TOLERANCE);
            assertEquals(sourceSwing.toPrice().doubleValue() + TRANSLATION,
                    followUpSwing.toPrice().doubleValue(), TOLERANCE);
        }
    }

    private static BarSeries generateFollowUp(BarSeries source) {
        if (source.isEmpty()) {
            return emptySeries();
        }

        List<Bar> translatedBars = new ArrayList<>();
        Num shift = source.numFactory().numOf(TRANSLATION);
        for (int index = source.getBeginIndex(); index <= source.getEndIndex(); index++) {
            Bar bar = source.getBar(index);
            Num volume = bar.getVolume();
            translatedBars.add(new BaseBar(bar.getTimePeriod(), bar.getBeginTime(), bar.getEndTime(),
                    bar.getOpenPrice().plus(shift), bar.getHighPrice().plus(shift), bar.getLowPrice().plus(shift),
                    bar.getClosePrice().plus(shift), volume,
                    bar.getAmount().plus(shift.multipliedBy(volume)), bar.getTrades()));
        }
        return new BaseBarSeries("translated", translatedBars);
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeries("empty", new ArrayList<>());
    }

    private static BarSeries trimmedSeries(double[] closes) {
        BaseBarSeries result = (BaseBarSeries) series(closes);
        result.setMaximumBarCount(7);
        return result;
    }

    private static BarSeries series(double[] closes) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            Num close = DecimalNum.valueOf(closes[i]);
            Num open = DecimalNum.valueOf(closes[i] - 0.1);
            Num high = DecimalNum.valueOf(closes[i] + 0.5);
            Num low = DecimalNum.valueOf(closes[i] - 0.5);
            Num volume = DecimalNum.valueOf(10 + i);
            Instant end = BASE_TIME.plus(PERIOD.multipliedBy(i + 1L));
            Instant begin = end.minus(PERIOD);
            bars.add(new BaseBar(PERIOD, begin, end, open, high, low, close, volume,
                    close.multipliedBy(volume), i + 1L));
        }
        return new BaseBarSeries("source", bars);
    }

    @Test
    void NONZERO_SERIES_BEGIN_INDEX_variation1() {
        BarSeries source = trimmedSeries(new double[] { 20, 21, 22, 23, 24, 2, 4, 6, 4, 2, 0, -2 });
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(3), source, source.getEndIndex(),
                ElliottDegree.SUPER_CYCLE);
    }
}
