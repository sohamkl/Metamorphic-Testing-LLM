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

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

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

    @Test
    public void EMPTY_SERIES_variation1() {
        assertMetamorphicRelationFor(input(Shape.EMPTY, config(3, 2, 3, 0.0, 0.5), -20, 0, 1));
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMP_variation1() {
        assertMetamorphicRelationFor(input(Shape.SHORT_RISING, config(3, 2, 3, 0.0, 0.0), 3, 5, 2));
    }

    @Test
    public void INDEX_ABOVE_END_CLAMP_variation1() {
        assertMetamorphicRelationFor(input(Shape.HIGH, config(3, 2, 3, 0.0, 0.5), 1_000, 0, 3));
    }

    @Test
    public void CANDIDATE_RANGE_JUST_EMPTY_variation1() {
        assertMetamorphicRelationFor(input(Shape.JUST_EMPTY_HIGH, config(3, 2, 3, 0.0, 0.5), 5, 0, 4));
    }

    @Test
    public void CANDIDATE_RANGE_JUST_EMPTY_variation2() {
        assertMetamorphicRelationFor(input(Shape.JUST_EMPTY_HIGH, config(3, 2, 5, 1.0, 0.0), 9, 4, 5));
    }

    @Test
    public void EXACTLY_ONE_CANDIDATE_variation1() {
        assertMetamorphicRelationFor(input(Shape.SAME_SIGN_POSITIVE, config(3, 2, 3, 0.25, 0.5), 6, 0, 6));
    }

    @Test
    public void EXACTLY_ONE_CANDIDATE_variation2() {
        assertMetamorphicRelationFor(input(Shape.HIGH, config(3, 2, 3, 0.0, 0.5), 6, 0, 7));
    }

    @Test
    public void POSITIVE_BEGIN_INDEX_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE, config(3, 2, 3, 0.0, 0.0), 23, 5, 8));
    }

    @Test
    public void NONFINITE_BEFORE_SLOPE_variation1() {
        assertMetamorphicRelationFor(input(Shape.JUST_EMPTY_HIGH, config(3, 2, 3, 0.0, 0.5), 5, 0, 9));
    }

    @Test
    public void NONFINITE_AFTER_SLOPE_variation1() {
        assertMetamorphicRelationFor(input(Shape.SHORT_RISING, config(3, 2, 3, 0.0, 0.5), 3, 0, 10));
    }

    @Test
    public void SLOPE_CHANGE_BELOW_MINIMUM_variation1() {
        assertMetamorphicRelationFor(input(Shape.BELOW_THRESHOLD_HIGH, config(3, 2, 3, 10.0, 0.5), 6, 0, 11));
    }

    @Test
    public void SLOPE_CHANGE_BELOW_MINIMUM_variation2() {
        assertMetamorphicRelationFor(input(Shape.SAME_SIGN_POSITIVE, config(3, 2, 3, 5.0, 0.5), 6, 0, 12));
    }

    @Test
    public void SLOPE_CHANGE_EXACT_THRESHOLD_variation1() {
        assertMetamorphicRelationFor(input(Shape.HIGH, config(3, 2, 3, 4.0, 0.5), 6, 0, 13));
    }

    @Test
    public void SLOPE_CHANGE_EXACT_THRESHOLD_variation2() {
        assertMetamorphicRelationFor(input(Shape.LOW, config(3, 2, 3, 4.0, 0.5), 6, 0, 14));
    }

    @Test
    public void FINITE_SLOPES_WITHOUT_REVERSAL_variation1() {
        assertMetamorphicRelationFor(input(Shape.SAME_SIGN_POSITIVE, config(3, 2, 3, 0.5, 0.5), 6, 0, 15));
    }

    @Test
    public void FINITE_SLOPES_WITHOUT_REVERSAL_variation2() {
        assertMetamorphicRelationFor(input(Shape.SAME_SIGN_NEGATIVE, config(3, 2, 3, 0.5, 0.5), 6, 0, 16));
    }

    @Test
    public void ZERO_SLOPE_DIRECTION_SENTINEL_variation1() {
        assertMetamorphicRelationFor(input(Shape.ZERO_BEFORE, config(3, 2, 3, 0.5, 0.5), 6, 0, 17));
    }

    @Test
    public void ZERO_SLOPE_DIRECTION_SENTINEL_variation2() {
        assertMetamorphicRelationFor(input(Shape.ZERO_AFTER, config(3, 2, 3, 0.5, 0.5), 6, 0, 18));
    }

    @Test
    public void CONFIRMED_HIGH_FIRST_PIVOT_variation1() {
        assertMetamorphicRelationFor(input(Shape.HIGH, config(3, 2, 3, 0.0, 0.5), 6, 0, 19));
    }

    @Test
    public void CONFIRMED_HIGH_FIRST_PIVOT_variation2() {
        assertMetamorphicRelationFor(input(Shape.HIGH_EXTREME_INTERIOR, config(3, 2, 4, 1.0, 0.0), 6, 0, 20));
    }

    @Test
    public void CONFIRMED_LOW_FIRST_PIVOT_variation1() {
        assertMetamorphicRelationFor(input(Shape.LOW, config(3, 2, 3, 0.0, 0.5), 6, 0, 21));
    }

    @Test
    public void CONFIRMED_LOW_FIRST_PIVOT_variation2() {
        assertMetamorphicRelationFor(input(Shape.LOW_EXTREME_INTERIOR, config(3, 2, 4, 1.0, 0.0), 6, 0, 22));
    }

    @Test
    public void PERSISTENCE_FAILS_FIRST_BAR_variation1() {
        assertMetamorphicRelationFor(input(Shape.ZERO_AFTER, config(3, 2, 3, 0.0, 0.5), 6, 0, 23));
    }

    @Test
    public void PERSISTENCE_FAILS_LATER_BAR_variation1() {
        assertMetamorphicRelationFor(input(Shape.PERSISTENCE_FAIL_LATER_HIGH, config(3, 2, 3, 0.0, 0.5), 6, 0, 24));
    }

    @Test
    public void PERSISTENCE_FAILS_LATER_BAR_variation2() {
        assertMetamorphicRelationFor(input(Shape.PERSISTENCE_FAIL_LATER_LOW, config(3, 3, 3, 0.0, 0.5), 7, 0, 25));
    }

    @Test
    public void HIGH_EXTREME_AT_FIRST_BAR_variation1() {
        assertMetamorphicRelationFor(input(Shape.HIGH_EXTREME_FIRST, config(3, 2, 3, 0.0, 0.0), 6, 0, 26));
    }

    @Test
    public void HIGH_EXTREME_AT_INTERIOR_BAR_variation1() {
        assertMetamorphicRelationFor(input(Shape.HIGH_EXTREME_INTERIOR, config(3, 2, 3, 0.0, 0.0), 6, 0, 27));
    }

    @Test
    public void HIGH_EXTREME_AT_LAST_BAR_variation1() {
        assertMetamorphicRelationFor(input(Shape.HIGH_EXTREME_LAST, config(3, 2, 3, 0.0, 0.0), 6, 0, 28));
    }

    @Test
    public void HIGH_EXTREME_TIE_KEEPS_EARLIEST_variation1() {
        assertMetamorphicRelationFor(input(Shape.HIGH_EXTREME_TIE, config(3, 2, 3, 0.0, 0.0), 6, 0, 29));
    }

    @Test
    public void LOW_EXTREME_LOCATION_COVERAGE_variation1() {
        assertMetamorphicRelationFor(input(Shape.LOW_EXTREME_FIRST, config(3, 2, 3, 0.0, 0.0), 6, 0, 30));
    }

    @Test
    public void LOW_EXTREME_LOCATION_COVERAGE_variation2() {
        assertMetamorphicRelationFor(input(Shape.LOW_EXTREME_INTERIOR, config(3, 2, 3, 0.0, 0.0), 6, 0, 31));
    }

    @Test
    public void LOW_EXTREME_LOCATION_COVERAGE_variation3() {
        assertMetamorphicRelationFor(input(Shape.LOW_EXTREME_LAST, config(3, 2, 3, 0.0, 0.0), 6, 0, 32));
    }

    @Test
    public void NONFINITE_EXTREME_VALUE_variation1() {
        assertMetamorphicRelationFor(input(Shape.JUST_EMPTY_HIGH, config(3, 2, 3, 0.0, 0.5), 5, 0, 33));
    }

    @Test
    public void NONFINITE_EXTREME_VALUE_variation2() {
        assertMetamorphicRelationFor(input(Shape.SHORT_RISING, config(3, 2, 3, 0.0, 0.5), 4, 0, 34));
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_variation1() {
        assertMetamorphicRelationFor(input(Shape.HIGH_EXTREME_INTERIOR, config(3, 2, 20, 0.0, 3.0), 6, 0, 35));
    }

    @Test
    public void ZERO_ATR_MULTIPLIER_BYPASS_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE, config(3, 2, 3, 0.0, 0.0), 18, 0, 36));
    }

    @Test
    public void ZERO_ATR_MULTIPLIER_BYPASS_variation2() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE_SMALL, config(3, 2, 5, 0.0, 0.0), 18, 0, 37));
    }

    @Test
    public void NONFINITE_ATR_REJECTS_LATER_PIVOT_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE, config(3, 2, 50, 0.0, 2.0), 18, 0, 38));
    }

    @Test
    public void ATR_REVERSAL_BELOW_THRESHOLD_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE_SMALL, config(3, 2, 3, 0.0, 20.0), 18, 0, 39));
    }

    @Test
    public void ATR_REVERSAL_EXACT_THRESHOLD_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE, config(3, 2, 1, 0.0, 1.0), 18, 0, 40));
    }

    @Test
    public void ATR_REVERSAL_EXACT_THRESHOLD_variation2() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE_SMALL, config(3, 2, 1, 0.0, 1.0), 18, 0, 41));
    }

    @Test
    public void OPPOSITE_TYPES_APPEND_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE, config(3, 2, 3, 0.0, 0.0), 11, 0, 42));
    }

    @Test
    public void OPPOSITE_TYPES_APPEND_variation2() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE_SMALL, config(3, 2, 4, 0.0, 0.0), 14, 0, 43));
    }

    @Test
    public void SAME_TYPE_HIGH_REPLACED_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE, config(3, 2, 3, 0.0, 0.0), 21, 3, 44));
    }

    @Test
    public void SAME_TYPE_HIGH_NOT_REPLACED_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE_SMALL, config(3, 2, 3, 0.0, 0.0), 18, 0, 45));
    }

    @Test
    public void SAME_TYPE_LOW_REPLACED_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE, config(3, 2, 3, 0.0, 0.0), 22, 4, 46));
    }

    @Test
    public void SAME_TYPE_LOW_NOT_REPLACED_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE_SMALL, config(3, 2, 3, 0.0, 0.0), 20, 2, 47));
    }

    @Test
    public void MULTIPLE_PIVOTS_BUILD_ORDERED_SWINGS_variation1() {
        assertMetamorphicRelationFor(input(Shape.MULTI_WAVE, config(3, 2, 3, 0.0, 0.0), 23, 5, 48));
    }

    @Test
    public void EVALUATION_PREFIX_EXCLUDES_LATER_REVERSAL_variation1() {
        assertMetamorphicRelationFor(input(Shape.PREFIX_WAVE, config(3, 2, 3, 0.0, 0.0), 10, 0, 49));
    }
}
