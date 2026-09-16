import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final double TRANSLATION = 100.0;

    private static final double TOLERANCE = 1.0e-8;

    private static final Duration DEFAULT_DURATION = Duration.ofMinutes(1);

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private enum Shape {

        EMPTY,
        SHORT_RISING,
        JUST_EMPTY_HIGH,
        HIGH,
        LOW,
        BELOW_THRESHOLD_HIGH,
        SAME_SIGN_POSITIVE,
        SAME_SIGN_NEGATIVE,
        ZERO_BEFORE,
        ZERO_AFTER,
        PERSISTENCE_FAIL_LATER_HIGH,
        PERSISTENCE_FAIL_LATER_LOW,
        HIGH_EXTREME_FIRST,
        HIGH_EXTREME_INTERIOR,
        HIGH_EXTREME_LAST,
        HIGH_EXTREME_TIE,
        LOW_EXTREME_FIRST,
        LOW_EXTREME_INTERIOR,
        LOW_EXTREME_LAST,
        MULTI_WAVE,
        MULTI_WAVE_SMALL,
        PREFIX_WAVE,
        METADATA_WAVE
    }

    private static final class Input {

        private final SlopeChangeSwingDetector receiver;

        private final BarSeries series;

        private final int index;

        private final ElliottDegree degree;

        private Input(SlopeChangeSwingDetector receiver, BarSeries series, int index, ElliottDegree degree) {
            this.receiver = receiver;
            this.series = series;
            this.index = index;
            this.degree = degree;
        }
    }

    private static Input input(Shape shape, SlopeChangeConfig config, int requestedIndex, int positiveBeginIndex, int fixtureId) {
        BarSeries series = createSeries(shape, positiveBeginIndex, fixtureId);
        SlopeChangeSwingDetector detector = new SlopeChangeSwingDetector(config);
        ElliottDegree[] degrees = ElliottDegree.values();
        ElliottDegree degree = degrees[Math.floorMod(fixtureId, degrees.length)];
        return new Input(detector, series, requestedIndex, degree);
    }

    private static SlopeChangeConfig config(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal);
    }

    private static BarSeries createSeries(Shape shape, int positiveBeginIndex, int fixtureId) {
        double[] closes = closesFor(shape);
        if (closes.length == 0) {
            return new BaseBarSeries("empty-" + fixtureId, new ArrayList<>());
        }
        List<Bar> retainedBars = new ArrayList<>();
        for (int offset = 0; offset < closes.length; offset++) {
            retainedBars.add(createSourceBar(shape, fixtureId, offset, closes[offset]));
        }
        if (positiveBeginIndex <= 0) {
            return new BaseBarSeries("series-" + fixtureId, retainedBars);
        }
        BaseBarSeries series = new BaseBarSeries("retained-" + fixtureId, new ArrayList<>());
        series.setMaximumBarCount(retainedBars.size());
        for (int i = 0; i < positiveBeginIndex; i++) {
            double close = 80.0 + i * 0.1;
            Instant endTime = BASE_TIME.minus(Duration.ofDays(20)).plus(Duration.ofMinutes((long) fixtureId * 10L + i + 1L));
            series.addBar(createOrdinaryBar(endTime, DEFAULT_DURATION, close, close + 1.0, close - 1.0, close, 1.0, close, 1L));
        }
        for (Bar bar : retainedBars) {
            series.addBar(bar);
        }
        return series;
    }

    private static Bar createSourceBar(Shape shape, int fixtureId, int offset, double close) {
        double open = close + ((offset % 3) - 1) * 0.1;
        double high = Math.max(open, close) + 1.0;
        double low = Math.min(open, close) - 1.0;
        if (shape == Shape.HIGH_EXTREME_FIRST && offset >= 2 && offset <= 4) {
            high = new double[] { 112.0, 109.0, 107.0 }[offset - 2];
        } else if (shape == Shape.HIGH_EXTREME_INTERIOR && offset >= 2 && offset <= 4) {
            high = new double[] { 108.0, 113.0, 109.0 }[offset - 2];
        } else if (shape == Shape.HIGH_EXTREME_LAST && offset >= 2 && offset <= 4) {
            high = new double[] { 108.0, 110.0, 114.0 }[offset - 2];
        } else if (shape == Shape.HIGH_EXTREME_TIE && offset >= 2 && offset <= 4) {
            high = new double[] { 113.0, 109.0, 113.0 }[offset - 2];
        }
        if (shape == Shape.LOW_EXTREME_FIRST && offset >= 2 && offset <= 4) {
            low = new double[] { 88.0, 91.0, 93.0 }[offset - 2];
        } else if (shape == Shape.LOW_EXTREME_INTERIOR && offset >= 2 && offset <= 4) {
            low = new double[] { 92.0, 87.0, 91.0 }[offset - 2];
        } else if (shape == Shape.LOW_EXTREME_LAST && offset >= 2 && offset <= 4) {
            low = new double[] { 93.0, 91.0, 86.0 }[offset - 2];
        }
        Duration duration;
        double volume;
        double amount;
        long trades;
        long spacingMinutes;
        if (shape == Shape.METADATA_WAVE) {
            duration = Duration.ofSeconds(30L + (offset % 4) * 15L);
            volume = offset % 4 == 0 ? 0.0 : 0.5 + offset * 0.25;
            amount = 7.5 + close * volume;
            trades = offset % 5;
            spacingMinutes = offset * 2L + (offset % 3) + 1L;
        } else {
            duration = DEFAULT_DURATION;
            volume = offset % 3 == 0 ? 0.0 : 1.0 + offset * 0.25;
            amount = 2.0 + close * volume;
            trades = offset % 4;
            spacingMinutes = offset + 1L;
        }
        Instant endTime = BASE_TIME.plus(Duration.ofDays(fixtureId)).plus(Duration.ofMinutes(spacingMinutes));
        return createOrdinaryBar(endTime, duration, open, high, low, close, volume, amount, trades);
    }

    private static Bar createOrdinaryBar(Instant endTime, Duration duration, double open, double high, double low, double close, double volume, double amount, long trades) {
        Instant beginTime = endTime.minus(duration);
        return new BaseBar(duration, beginTime, endTime, DecimalNum.valueOf(open), DecimalNum.valueOf(high), DecimalNum.valueOf(low), DecimalNum.valueOf(close), DecimalNum.valueOf(volume), DecimalNum.valueOf(amount), trades);
    }

    private static double[] closesFor(Shape shape) {
        switch(shape) {
            case EMPTY:
                return new double[0];
            case SHORT_RISING:
                return new double[] { 100.0, 101.0, 102.0, 103.0 };
            case JUST_EMPTY_HIGH:
                return new double[] { 100.0, 102.0, 104.0, 102.0, 100.0, 98.0 };
            case HIGH:
            case HIGH_EXTREME_FIRST:
            case HIGH_EXTREME_INTERIOR:
            case HIGH_EXTREME_LAST:
            case HIGH_EXTREME_TIE:
                return new double[] { 100.0, 102.0, 104.0, 102.0, 100.0, 98.0, 96.0 };
            case LOW:
            case LOW_EXTREME_FIRST:
            case LOW_EXTREME_INTERIOR:
            case LOW_EXTREME_LAST:
                return new double[] { 104.0, 102.0, 100.0, 102.0, 104.0, 106.0, 108.0 };
            case BELOW_THRESHOLD_HIGH:
                return new double[] { 100.0, 100.2, 100.4, 100.2, 100.0, 99.8, 99.6 };
            case SAME_SIGN_POSITIVE:
                return new double[] { 100.0, 101.0, 102.0, 104.0, 106.0, 108.0, 110.0 };
            case SAME_SIGN_NEGATIVE:
                return new double[] { 110.0, 108.0, 106.0, 105.0, 104.0, 103.0, 102.0 };
            case ZERO_BEFORE:
                return new double[] { 100.0, 100.0, 100.0, 102.0, 104.0, 106.0, 108.0 };
            case ZERO_AFTER:
                return new double[] { 100.0, 102.0, 104.0, 103.0, 103.0, 103.0, 103.0 };
            case PERSISTENCE_FAIL_LATER_HIGH:
                return new double[] { 100.0, 102.0, 104.0, 102.0, 100.0, 98.0, 105.0 };
            case PERSISTENCE_FAIL_LATER_LOW:
                return new double[] { 104.0, 102.0, 100.0, 102.0, 104.0, 106.0, 99.0, 98.0 };
            case MULTI_WAVE:
            case PREFIX_WAVE:
            case METADATA_WAVE:
                return new double[] { 100.0, 102.0, 104.0, 102.0, 100.0, 98.0, 96.0, 98.0, 100.0, 102.0, 104.0, 102.0, 100.0, 98.0, 96.0, 98.0, 100.0, 102.0, 104.0 };
            case MULTI_WAVE_SMALL:
                return new double[] { 100.0, 100.5, 101.0, 100.5, 100.0, 99.5, 99.0, 99.5, 100.0, 100.5, 101.0, 100.5, 100.0, 99.5, 99.0, 99.5, 100.0, 100.5, 101.0 };
            default:
                throw new IllegalArgumentException("Unhandled shape: " + shape);
        }
    }

    private static void assertMetamorphicRelationFor(Input source) {
        Input followUp = generateFollowUp(source);
        SwingDetectorResult sourceOutput = source.receiver.detect(source.series, source.index, source.degree);
        SwingDetectorResult followUpOutput = followUp.receiver.detect(followUp.series, followUp.index, followUp.degree);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static Input generateFollowUp(Input source) {
        BarSeries translated = translateSeries(source.series, TRANSLATION);
        assertTranslatedSeriesMetadata(source.series, translated, TRANSLATION);
        return new Input(source.receiver, translated, source.index, source.degree);
    }

    private static BarSeries translateSeries(BarSeries source, double translation) {
        if (source.isEmpty()) {
            return new BaseBarSeries(source.getName() + "-translated", new ArrayList<>());
        }
        int beginIndex = source.getBeginIndex();
        int retainedCount = source.getEndIndex() - beginIndex + 1;
        BaseBarSeries translated = new BaseBarSeries(source.getName() + "-translated", new ArrayList<>());
        if (beginIndex > 0) {
            translated.setMaximumBarCount(retainedCount);
            for (int i = 0; i < beginIndex; i++) {
                Instant endTime = BASE_TIME.minus(Duration.ofDays(60)).plus(Duration.ofMinutes(i + 1L));
                Instant beginTime = endTime.minus(DEFAULT_DURATION);
                Num close = source.numFactory().numOf(150.0 + i * 0.1);
                Num one = source.numFactory().one();
                Num zero = source.numFactory().zero();
                translated.addBar(new BaseBar(DEFAULT_DURATION, beginTime, endTime, close, close.plus(one), close.minus(one), close, zero, zero, 0L));
            }
        }
        Num shift = source.numFactory().numOf(translation);
        for (int index = source.getBeginIndex(); index <= source.getEndIndex(); index++) {
            Bar bar = source.getBar(index);
            Num translatedAmount = bar.getAmount().plus(shift.multipliedBy(bar.getVolume()));
            translated.addBar(new BaseBar(bar.getTimePeriod(), bar.getBeginTime(), bar.getEndTime(), bar.getOpenPrice().plus(shift), bar.getHighPrice().plus(shift), bar.getLowPrice().plus(shift), bar.getClosePrice().plus(shift), bar.getVolume(), translatedAmount, bar.getTrades()));
        }
        return translated;
    }

    private static void assertTranslatedSeriesMetadata(BarSeries source, BarSeries followUp, double translation) {
        assertEquals(source.isEmpty(), followUp.isEmpty(), "series emptiness");
        assertEquals(source.getBeginIndex(), followUp.getBeginIndex(), "series begin index");
        assertEquals(source.getEndIndex(), followUp.getEndIndex(), "series end index");
        if (source.isEmpty()) {
            return;
        }
        Num shift = source.numFactory().numOf(translation);
        for (int index = source.getBeginIndex(); index <= source.getEndIndex(); index++) {
            Bar sourceBar = source.getBar(index);
            Bar followUpBar = followUp.getBar(index);
            assertEquals(sourceBar.getTimePeriod(), followUpBar.getTimePeriod(), "duration at " + index);
            assertEquals(sourceBar.getBeginTime(), followUpBar.getBeginTime(), "begin time at " + index);
            assertEquals(sourceBar.getEndTime(), followUpBar.getEndTime(), "end time at " + index);
            assertEquals(sourceBar.getTrades(), followUpBar.getTrades(), "trades at " + index);
            assertNumEquals(sourceBar.getVolume(), followUpBar.getVolume(), "volume at " + index);
            assertNumEquals(sourceBar.getOpenPrice().plus(shift), followUpBar.getOpenPrice(), "open at " + index);
            assertNumEquals(sourceBar.getHighPrice().plus(shift), followUpBar.getHighPrice(), "high at " + index);
            assertNumEquals(sourceBar.getLowPrice().plus(shift), followUpBar.getLowPrice(), "low at " + index);
            assertNumEquals(sourceBar.getClosePrice().plus(shift), followUpBar.getClosePrice(), "close at " + index);
            assertNumEquals(sourceBar.getAmount().plus(shift.multipliedBy(sourceBar.getVolume())), followUpBar.getAmount(), "amount at " + index);
        }
    }

    private static void assertMetamorphicRelation(SwingDetectorResult sourceOutput, SwingDetectorResult followUpOutput) {
        List<SwingPivot> sourcePivots = sourceOutput.pivots();
        List<SwingPivot> followUpPivots = followUpOutput.pivots();
        assertEquals(sourcePivots.size(), followUpPivots.size(), "pivot count");
        for (int i = 0; i < sourcePivots.size(); i++) {
            SwingPivot sourcePivot = sourcePivots.get(i);
            SwingPivot followUpPivot = followUpPivots.get(i);
            assertEquals(sourcePivot.index(), followUpPivot.index(), "pivot index at " + i);
            assertEquals(sourcePivot.type(), followUpPivot.type(), "pivot type at " + i);
            assertTranslatedNum(sourcePivot.price(), followUpPivot.price(), "pivot price at " + i);
        }
        List<?> sourceSwings = sourceOutput.swings();
        List<?> followUpSwings = followUpOutput.swings();
        assertEquals(sourceSwings.size(), followUpSwings.size(), "swing count");
        for (int i = 0; i < sourceSwings.size(); i++) {
            Object sourceSwing = sourceSwings.get(i);
            Object followUpSwing = followUpSwings.get(i);
            assertEquals(access(sourceSwing, "fromIndex"), access(followUpSwing, "fromIndex"), "swing fromIndex at " + i);
            assertEquals(access(sourceSwing, "toIndex"), access(followUpSwing, "toIndex"), "swing toIndex at " + i);
            assertEquals(access(sourceSwing, "degree"), access(followUpSwing, "degree"), "swing degree at " + i);
            Num sourceFromPrice = (Num) access(sourceSwing, "fromPrice");
            Num followUpFromPrice = (Num) access(followUpSwing, "fromPrice");
            Num sourceToPrice = (Num) access(sourceSwing, "toPrice");
            Num followUpToPrice = (Num) access(followUpSwing, "toPrice");
            assertTranslatedNum(sourceFromPrice, followUpFromPrice, "swing fromPrice at " + i);
            assertTranslatedNum(sourceToPrice, followUpToPrice, "swing toPrice at " + i);
        }
    }

    private static Object access(Object target, String accessorName) {
        try {
            Method method = target.getClass().getMethod(accessorName);
            return method.invoke(target);
        } catch (ReflectiveOperationException failure) {
            throw new AssertionError("Unable to read " + accessorName + " from " + target.getClass().getName(), failure);
        }
    }

    private static void assertTranslatedNum(Num source, Num followUp, String message) {
        double expected = source.doubleValue() + TRANSLATION;
        double actual = followUp.doubleValue();
        double scale = Math.max(1.0, Math.max(Math.abs(expected), Math.abs(actual)));
        assertEquals(expected, actual, TOLERANCE * scale, message);
    }

    private static void assertNumEquals(Num expected, Num actual, String message) {
        double expectedValue = expected.doubleValue();
        double actualValue = actual.doubleValue();
        double scale = Math.max(1.0, Math.max(Math.abs(expectedValue), Math.abs(actualValue)));
        assertEquals(expectedValue, actualValue, TOLERANCE * scale, message);
    }
}
