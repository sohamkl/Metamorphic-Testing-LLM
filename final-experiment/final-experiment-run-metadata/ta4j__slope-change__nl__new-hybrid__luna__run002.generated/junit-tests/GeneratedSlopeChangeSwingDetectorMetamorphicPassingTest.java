import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final double TRANSLATION = 100.0;

    private static final double EPSILON = 1.0e-8;

    private static SlopeChangeSwingDetector detector() {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.01, 0.0));
    }

    private static BaseBarSeries emptySeries() {
        return new BaseBarSeries("empty", new ArrayList<>());
    }

    private static BaseBarSeries series(double[] closes) {
        List<Bar> bars = new ArrayList<>();
        Instant firstEnd = Instant.parse("2020-01-01T00:01:00Z");
        Duration period = Duration.ofMinutes(1);
        for (int i = 0; i < closes.length; i++) {
            Num close = null;
            double value = closes[i];
            double openValue = value - 0.25;
            double highValue = value + 0.5;
            double lowValue = value - 0.5;
            BaseBarSeries factorySource = new BaseBarSeries("factory", new ArrayList<>());
            close = factorySource.numFactory().numOf(value);
            Num open = factorySource.numFactory().numOf(openValue);
            Num high = factorySource.numFactory().numOf(highValue);
            Num low = factorySource.numFactory().numOf(lowValue);
            Num volume = factorySource.numFactory().numOf(100.0);
            Num amount = factorySource.numFactory().numOf(10000.0);
            Instant end = firstEnd.plus(period.multipliedBy(i));
            Instant begin = end.minus(period);
            bars.add(new BaseBar(period, begin, end, open, high, low, close, volume, amount, 1));
        }
        return new BaseBarSeries("source", bars);
    }

    private static BaseBarSeries oscillatingSeries(int size) {
        double[] pattern = { 10, 12, 14, 13, 11, 9, 10, 12, 15, 13, 10, 8 };
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = pattern[i % pattern.length];
        }
        return series(values);
    }

    private static BaseBarSeries descendingOscillatingSeries(int size) {
        double[] pattern = { 20, 18, 16, 17, 19, 21, 20, 18, 15, 17, 20, 22 };
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = pattern[i % pattern.length];
        }
        return series(values);
    }

    private static void assertMetamorphicRelationFor(SlopeChangeSwingDetector detector, BarSeries source, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(source, index, degree);
        SwingDetectorResult followUpOutput = detector.detect(generateFollowUp(source), index, degree);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static BarSeries generateFollowUp(BarSeries source) {
        List<Bar> bars = new ArrayList<>();
        for (int i = source.getBeginIndex(); i <= source.getEndIndex(); i++) {
            Bar bar = source.getBar(i);
            Num shift = source.numFactory().numOf(TRANSLATION);
            Num open = bar.getOpenPrice().plus(shift);
            Num high = bar.getHighPrice().plus(shift);
            Num low = bar.getLowPrice().plus(shift);
            Num close = bar.getClosePrice().plus(shift);
            Num amount = bar.getAmount().plus(shift.multipliedBy(bar.getVolume()));
            Instant end = bar.getEndTime();
            Instant begin = end.minus(bar.getTimePeriod());
            bars.add(new BaseBar(bar.getTimePeriod(), begin, end, open, high, low, close, bar.getVolume(), amount, bar.getTrades()));
        }
        BaseBarSeries result = new BaseBarSeries(source.getName(), bars);
        if (source.getMaximumBarCount() != Integer.MAX_VALUE) {
            result.setMaximumBarCount(source.getMaximumBarCount());
        }
        return result;
    }

    private static void assertMetamorphicRelation(SwingDetectorResult source, SwingDetectorResult followUp) {
        List<?> sourcePivots = listProperty(source, "pivots", "getPivots");
        List<?> followUpPivots = listProperty(followUp, "pivots", "getPivots");
        List<?> sourceSwings = listProperty(source, "swings", "getSwings");
        List<?> followUpSwings = listProperty(followUp, "swings", "getSwings");
        assertEquals(sourcePivots.size(), followUpPivots.size());
        assertEquals(sourceSwings.size(), followUpSwings.size());
        for (int i = 0; i < sourcePivots.size(); i++) {
            Object left = sourcePivots.get(i);
            Object right = followUpPivots.get(i);
            assertEquals(numberProperty(left, "index", "getIndex").intValue(), numberProperty(right, "index", "getIndex").intValue());
            assertEquals(property(left, "type", "getType"), property(right, "type", "getType"));
            assertClose(numberProperty(left, "price", "getPrice").doubleValue() + TRANSLATION, numberProperty(right, "price", "getPrice").doubleValue());
        }
        for (int i = 0; i < sourceSwings.size(); i++) {
            Object left = sourceSwings.get(i);
            Object right = followUpSwings.get(i);
            assertEquals(numberProperty(left, "fromIndex", "getFromIndex").intValue(), numberProperty(right, "fromIndex", "getFromIndex").intValue());
            assertEquals(numberProperty(left, "toIndex", "getToIndex").intValue(), numberProperty(right, "toIndex", "getToIndex").intValue());
            assertEquals(property(left, "degree", "getDegree"), property(right, "degree", "getDegree"));
            assertClose(numberProperty(left, "fromPrice", "getFromPrice").doubleValue() + TRANSLATION, numberProperty(right, "fromPrice", "getFromPrice").doubleValue());
            assertClose(numberProperty(left, "toPrice", "getToPrice").doubleValue() + TRANSLATION, numberProperty(right, "toPrice", "getToPrice").doubleValue());
        }
    }

    private static List<?> listProperty(Object target, String... names) {
        Object value = property(target, names);
        assertTrue(value instanceof List<?>);
        return (List<?>) value;
    }

    private static Number numberProperty(Object target, String... names) {
        Object value = property(target, names);
        if (value instanceof Num) {
            return ((Num) value).doubleValue();
        }
        return (Number) value;
    }

    private static Object property(Object target, String... names) {
        for (String name : names) {
            try {
                Method method = target.getClass().getMethod(name);
                return method.invoke(target);
            } catch (ReflectiveOperationException ignored) {
            }
        }
        throw new AssertionError("No readable property on " + target.getClass());
    }

    private static void assertClose(double expected, double actual) {
        if (Double.isNaN(expected) || Double.isNaN(actual)) {
            assertEquals(Double.doubleToLongBits(expected), Double.doubleToLongBits(actual));
        } else {
            assertEquals(expected, actual, EPSILON);
        }
    }

    @Test
    void SHORT_SERIES_HAS_NO_CANDIDATE_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.01, 0.0)), series(new double[] { 10, 11, 10, 11, 10 }), 0, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void EXACT_MINIMUM_CANDIDATE_RANGE_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 3, 0.01, 0.0)), series(new double[] { 10, 12, 14, 13, 11 }), 3, ElliottDegree.MINOR);
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMP_1() {
        assertMetamorphicRelationFor(detector(), oscillatingSeries(36), -20, ElliottDegree.PRIMARY);
    }

    @Test
    void INDEX_ABOVE_END_CLAMP_1() {
        assertMetamorphicRelationFor(detector(), oscillatingSeries(36), 1000, ElliottDegree.MINUTE);
    }

    @Test
    void NONFINITE_CLOSE_REJECTS_SLOPE_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(4, 1, 3, 1000.0, 0.0)), series(new double[] { 10, 11, 12, 13, 14, 15, 16, 17 }), 7, ElliottDegree.MINOR);
    }

    @Test
    void WEAK_SLOPE_CHANGE_REJECTED_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 1000.0, 0.5)), series(new double[] { 10, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7 }), 7, ElliottDegree.MINOR);
    }

    @Test
    void SAME_DIRECTION_SLOPES_REJECTED_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.01, 0.5)), series(new double[] { 10, 11, 12, 13, 14, 15, 16, 17 }), 7, ElliottDegree.MINOR);
    }

    @Test
    void HIGH_REVERSAL_FAILS_PERSISTENCE_1() {
        assertMetamorphicRelationFor(detector(), series(new double[] { 10, 11, 12, 13, 12, 13, 14, 15, 16, 17 }), 9, ElliottDegree.MINOR);
    }

    @Test
    void LOW_REVERSAL_FAILS_PERSISTENCE_1() {
        assertMetamorphicRelationFor(detector(), series(new double[] { 17, 16, 15, 14, 15, 14, 13, 12, 11, 10 }), 9, ElliottDegree.MINOR);
    }

    @Test
    void CONFIRMED_HIGH_UNIQUE_EXTREME_1() {
        assertMetamorphicRelationFor(detector(), series(new double[] { 10, 11, 12, 14, 13, 12, 11, 10, 11, 12, 13 }), 10, ElliottDegree.MINOR);
    }

    @Test
    void CONFIRMED_LOW_UNIQUE_EXTREME_1() {
        assertMetamorphicRelationFor(detector(), series(new double[] { 15, 14, 13, 11, 12, 13, 14, 15, 14, 13, 12 }), 10, ElliottDegree.MINOR);
    }

    @Test
    void EXTREME_TIE_RETains_EARLIEST_INDEX_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 3, 0.01, 0.0)), series(new double[] { 10, 12, 14, 14, 12, 11, 10, 11 }), 7, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void NONFINITE_EXTREME_REJECTED_1() {
        assertMetamorphicRelationFor(detector(), series(new double[] { 10, 11, 12, 13, 12, 11, 10, 11, 12, 13 }), 9, ElliottDegree.MINOR);
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_FILTER_1() {
        assertMetamorphicRelationFor(detector(), oscillatingSeries(40), 39, ElliottDegree.PRIMARY);
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASSES_MAGNITUDE_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.01, 0.0)), oscillatingSeries(48), 47, ElliottDegree.MINOR);
    }

    @Test
    void NONFINITE_ATR_REJECTS_LATER_PIVOT_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 50, 0.01, 0.5)), oscillatingSeries(40), 39, ElliottDegree.MINOR);
    }

    @Test
    void MAGNITUDE_THRESHOLD_REJECTS_WEAK_REVERSAL_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.01, 10.0)), oscillatingSeries(40), 39, ElliottDegree.MINOR);
    }

    @Test
    void MAGNITUDE_THRESHOLD_INCLUSIVE_ACCEPTANCE_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 3, 0.01, 0.5)), oscillatingSeries(40), 39, ElliottDegree.CYCLE);
    }

    @Test
    void OPPOSITE_TYPE_PIVOT_APPENDED_1() {
        assertMetamorphicRelationFor(detector(), oscillatingSeries(60), 59, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SAME_TYPE_HIGH_REPLACED_BY_HIGHER_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 2, 0.01, 0.0)), oscillatingSeries(48), 47, ElliottDegree.MINOR);
    }

    @Test
    void SAME_TYPE_LOW_REPLACED_BY_LOWER_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 2, 0.01, 0.0)), descendingOscillatingSeries(48), 47, ElliottDegree.MINOR);
    }

    @Test
    void SAME_TYPE_NONIMPROVING_PIVOT_IGNORED_1() {
        assertMetamorphicRelationFor(detector(), series(new double[] { 10, 11, 12, 14, 13, 12, 11, 12, 13, 14, 13, 12, 11, 10, 11 }), 14, ElliottDegree.MINUTE);
    }

    @Test
    void MULTIPLE_ORDERED_SWINGS_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 2, 0.01, 0.0)), series(new double[] { 10, 12, 14, 13, 11, 9, 10, 12, 15, 13, 10, 8, 9, 12, 14, 13, 10, 7 }), 17, ElliottDegree.PRIMARY);
    }

    @Test
    void DEGREE_PRESERVED_IN_SWINGS_1() {
        assertMetamorphicRelationFor(new SlopeChangeSwingDetector(new SlopeChangeConfig(2, 1, 2, 0.01, 0.0)), oscillatingSeries(48), 47, ElliottDegree.valueOf("GRAND_SUPERCYCLE"));
    }

    @Test
    void NUM_IMPLEMENTATION_TRANSLATION_1() {
        assertMetamorphicRelationFor(detector(), oscillatingSeries(32), 31, ElliottDegree.MINOR);
    }
}
