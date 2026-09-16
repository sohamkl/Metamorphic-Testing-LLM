import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.NaN;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final double TRANSLATION = 100.0;
    private static final double TOLERANCE = 1.0e-8;
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static void check(Input source) {
        SwingDetectorResult sourceOutput = invoke(source);
        Input followUp = generateFollowUp(source);
        SwingDetectorResult followUpOutput = invoke(followUp);

        assertSame(source.receiver, followUp.receiver);
        assertSame(source.receiver.getConfig(), followUp.receiver.getConfig());
        assertEquals(source.index, followUp.index);
        assertSame(source.degree, followUp.degree);
        assertEquals(source.series.getBeginIndex(), followUp.series.getBeginIndex());
        assertEquals(source.series.getEndIndex(), followUp.series.getEndIndex());

        assertFollowUpSeriesRelation(source.series, followUp.series);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static SwingDetectorResult invoke(Input input) {
        return input.receiver.detect(input.series, input.index, input.degree);
    }

    private static Input generateFollowUp(Input source) {
        return input(source.receiver, translateSeries(source.series), source.index, source.degree);
    }

    private static void assertMetamorphicRelation(
            SwingDetectorResult sourceOutput,
            SwingDetectorResult followUpOutput) {

        assertEquals(sourceOutput.pivots().size(), followUpOutput.pivots().size());
        for (int i = 0; i < sourceOutput.pivots().size(); i++) {
            SwingPivot sourcePivot = sourceOutput.pivots().get(i);
            SwingPivot followUpPivot = followUpOutput.pivots().get(i);

            assertEquals(sourcePivot.index(), followUpPivot.index());
            assertEquals(sourcePivot.type(), followUpPivot.type());
            assertTranslated(sourcePivot.price(), followUpPivot.price());
        }

        assertEquals(sourceOutput.swings().size(), followUpOutput.swings().size());
        for (int i = 0; i < sourceOutput.swings().size(); i++) {
            var sourceSwing = sourceOutput.swings().get(i);
            var followUpSwing = followUpOutput.swings().get(i);

            assertEquals(sourceSwing.fromIndex(), followUpSwing.fromIndex());
            assertEquals(sourceSwing.toIndex(), followUpSwing.toIndex());
            assertSame(sourceSwing.degree(), followUpSwing.degree());
            assertTranslated(sourceSwing.fromPrice(), followUpSwing.fromPrice());
            assertTranslated(sourceSwing.toPrice(), followUpSwing.toPrice());
        }
    }

    private static void assertFollowUpSeriesRelation(BarSeries source, BarSeries followUp) {
        if (source.isEmpty()) {
            assertTrue(followUp.isEmpty());
            return;
        }

        assertEquals(source.numFactory().getClass(), followUp.numFactory().getClass());

        for (int i = source.getBeginIndex(); i <= source.getEndIndex(); i++) {
            Bar sourceBar = source.getBar(i);
            Bar followUpBar = followUp.getBar(i);

            assertEquals(sourceBar.getTimePeriod(), followUpBar.getTimePeriod());
            assertEquals(sourceBar.getBeginTime(), followUpBar.getBeginTime());
            assertEquals(sourceBar.getEndTime(), followUpBar.getEndTime());
            assertEquals(sourceBar.getTrades(), followUpBar.getTrades());
            assertNumEqual(sourceBar.getVolume(), followUpBar.getVolume());

            assertTranslated(sourceBar.getOpenPrice(), followUpBar.getOpenPrice());
            assertTranslated(sourceBar.getHighPrice(), followUpBar.getHighPrice());
            assertTranslated(sourceBar.getLowPrice(), followUpBar.getLowPrice());
            assertTranslated(sourceBar.getClosePrice(), followUpBar.getClosePrice());

            Num translation = source.numFactory().numOf(TRANSLATION);
            Num expectedAmount = sourceBar.getAmount()
                    .plus(translation.multipliedBy(sourceBar.getVolume()));
            assertNumEqual(expectedAmount, followUpBar.getAmount());
        }
    }

    private static BarSeries translateSeries(BarSeries source) {
        BaseBarSeries translated = new BaseBarSeries(source.getName() + "-translated", new ArrayList<>());
        if (source.isEmpty()) {
            return translated;
        }

        int beginIndex = source.getBeginIndex();
        int accessibleBarCount = source.getEndIndex() - beginIndex + 1;
        Num translation = source.numFactory().numOf(TRANSLATION);

        if (beginIndex > 0) {
            translated.setMaximumBarCount(accessibleBarCount);
            Bar first = source.getBar(beginIndex);

            for (int i = 0; i < beginIndex; i++) {
                Duration offset = first.getTimePeriod().multipliedBy(beginIndex - i);
                Instant dummyBegin = first.getBeginTime().minus(offset);
                Instant dummyEnd = first.getEndTime().minus(offset);
                translated.addBar(translateBar(first, dummyBegin, dummyEnd, translation));
            }
        }

        for (int i = beginIndex; i <= source.getEndIndex(); i++) {
            Bar sourceBar = source.getBar(i);
            translated.addBar(translateBar(
                    sourceBar,
                    sourceBar.getBeginTime(),
                    sourceBar.getEndTime(),
                    translation));
        }
        return translated;
    }

    private static Bar translateBar(
            Bar sourceBar,
            Instant beginTime,
            Instant endTime,
            Num translation) {

        Num open = sourceBar.getOpenPrice().plus(translation);
        Num high = sourceBar.getHighPrice().plus(translation);
        Num low = sourceBar.getLowPrice().plus(translation);
        Num close = sourceBar.getClosePrice().plus(translation);
        Num volume = sourceBar.getVolume();
        Num amount = sourceBar.getAmount().plus(translation.multipliedBy(volume));

        return new BaseBar(
                sourceBar.getTimePeriod(),
                beginTime,
                endTime,
                open,
                high,
                low,
                close,
                volume,
                amount,
                sourceBar.getTrades());
    }

    private static Input input(
            SlopeChangeSwingDetector receiver,
            BarSeries series,
            int index,
            ElliottDegree degree) {
        return new Input(receiver, series, index, degree);
    }

    private static SlopeChangeSwingDetector detector(
            int window,
            int confirmationBars,
            int atrPeriod,
            double minimumSlopeChange,
            double minimumAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(
                window,
                confirmationBars,
                atrPeriod,
                minimumSlopeChange,
                minimumAtrReversal));
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeries("empty", new ArrayList<>());
    }

    private static BarSeries series(double... closes) {
        return seriesWithExtremes(closes, null, null);
    }

    private static BarSeries seriesWithExtremes(
            double[] closes,
            double[] highs,
            double[] lows) {

        BaseBarSeries result = new BaseBarSeries("source", new ArrayList<>());
        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = highs == null
                    ? finiteOrNaNOffset(close, 0.5)
                    : highs[i];
            double low = lows == null
                    ? finiteOrNaNOffset(close, -0.5)
                    : lows[i];

            addBar(result, i, close, high, low, 10.0 + i, 2L + i);
        }
        return result;
    }

    private static BarSeries truncatedSeries(int maximumBarCount, double... closes) {
        BaseBarSeries result = new BaseBarSeries("truncated", new ArrayList<>());
        result.setMaximumBarCount(maximumBarCount);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            addBar(
                    result,
                    i,
                    close,
                    finiteOrNaNOffset(close, 0.5),
                    finiteOrNaNOffset(close, -0.5),
                    10.0 + i,
                    1L + i);
        }
        return result;
    }

    private static double finiteOrNaNOffset(double value, double offset) {
        return Double.isNaN(value) ? Double.NaN : value + offset;
    }

    private static BarSeries oscillatingSeries() {
        return series(10.0, 13.0, 12.0, 9.0, 11.0, 14.0, 12.0, 8.0, 10.0, 15.0, 13.0);
    }

    private static BarSeries narrowOscillatingSeries() {
        double[] closes = { 10.0, 13.0, 12.0, 9.0, 11.0, 14.0, 12.0, 8.0, 10.0, 15.0, 13.0 };
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];

        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 0.25;
            lows[i] = closes[i] - 0.25;
        }
        return seriesWithExtremes(closes, highs, lows);
    }

    private static BarSeries wideOscillatingSeries() {
        double[] closes = { 10.0, 13.0, 12.0, 9.0, 11.0, 14.0, 12.0, 8.0, 10.0, 15.0, 13.0 };
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];

        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 8.0;
            lows[i] = closes[i] - 8.0;
        }
        return seriesWithExtremes(closes, highs, lows);
    }

    private static void addBar(
            BaseBarSeries series,
            int ordinal,
            double close,
            double high,
            double low,
            double volume,
            long trades) {

        Num closeNum = numOfAllowingNaN(series, close);
        Num highNum = numOfAllowingNaN(series, high);
        Num lowNum = numOfAllowingNaN(series, low);
        Num volumeNum = series.numFactory().numOf(volume);
        Num amountNum = closeNum.multipliedBy(volumeNum);

        Instant beginTime = BASE_TIME.plus(BAR_DURATION.multipliedBy(ordinal));
        Instant endTime = beginTime.plus(BAR_DURATION);

        series.addBar(new BaseBar(
                BAR_DURATION,
                beginTime,
                endTime,
                closeNum,
                highNum,
                lowNum,
                closeNum,
                volumeNum,
                amountNum,
                trades));
    }

    private static Num numOfAllowingNaN(BaseBarSeries series, double value) {
        return Double.isNaN(value) ? NaN.NaN : series.numFactory().numOf(value);
    }

    private static void assertTranslated(Num source, Num followUp) {
        double sourceValue = source.doubleValue();
        double followUpValue = followUp.doubleValue();

        if (Double.isNaN(sourceValue)) {
            assertTrue(Double.isNaN(followUpValue));
        } else if (Double.isInfinite(sourceValue)) {
            assertEquals(sourceValue, followUpValue);
        } else {
            assertEquals(sourceValue + TRANSLATION, followUpValue, TOLERANCE);
        }
    }

    private static void assertNumEqual(Num expected, Num actual) {
        double expectedValue = expected.doubleValue();
        double actualValue = actual.doubleValue();

        if (Double.isNaN(expectedValue)) {
            assertTrue(Double.isNaN(actualValue));
        } else if (Double.isInfinite(expectedValue)) {
            assertEquals(expectedValue, actualValue);
        } else {
            assertEquals(expectedValue, actualValue, TOLERANCE);
        }
    }

    private static final class Input {

        private final SlopeChangeSwingDetector receiver;
        private final BarSeries series;
        private final int index;
        private final ElliottDegree degree;

        private Input(
                SlopeChangeSwingDetector receiver,
                BarSeries series,
                int index,
                ElliottDegree degree) {
            this.receiver = receiver;
            this.series = series;
            this.index = index;
            this.degree = degree;
        }
    }
}
