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
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final double TRANSLATION = 100.0d;

    private static final double TOLERANCE = 1.0e-8d;

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static void check(SlopeChangeSwingDetector detector, BarSeries series, int index, ElliottDegree degree) {
        assertMetamorphicRelationFor(detector, series, index, degree);
    }

    private static SlopeChangeSwingDetector detector() {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 1, 0.0d, 0.0d));
    }

    private static SlopeChangeSwingDetector strictDetector(double minimumSlopeChange) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 1, minimumSlopeChange, 0.0d));
    }

    private static SlopeChangeSwingDetector atrDetector(double multiplier) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 1, 0.0d, multiplier));
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeries("empty", new ArrayList<>());
    }

    private static BarSeries prices(double... values) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Num price = DecimalNum.valueOf(values[i]);
            Instant end = BASE_TIME.plus(Duration.ofMinutes(i + 1L));
            Instant begin = end.minus(Duration.ofMinutes(1));
            bars.add(new BaseBar(Duration.ofMinutes(1), begin, end, price, price.plus(DecimalNum.valueOf(1)), price.minus(DecimalNum.valueOf(1)), price, DecimalNum.valueOf(10), price.multipliedBy(DecimalNum.valueOf(10)), 1L));
        }
        return new BaseBarSeries("fixture", bars);
    }

    private static BarSeries generateFollowUp(BarSeries source) {
        List<Bar> translated = new ArrayList<>();
        Num translation = source.numFactory().numOf(TRANSLATION);
        for (int index = source.getBeginIndex(); index <= source.getEndIndex(); index++) {
            Bar bar = source.getBar(index);
            translated.add(new BaseBar(bar.getTimePeriod(), bar.getBeginTime(), bar.getEndTime(), bar.getOpenPrice().plus(translation), bar.getHighPrice().plus(translation), bar.getLowPrice().plus(translation), bar.getClosePrice().plus(translation), bar.getVolume(), bar.getAmount().plus(translation.multipliedBy(bar.getVolume())), bar.getTrades()));
        }
        return new BaseBarSeries("translated-fixture", translated);
    }

    private static void assertMetamorphicRelationFor(SlopeChangeSwingDetector detector, BarSeries source, int index, ElliottDegree degree) {
        SwingDetectorResult sourceResult = detector.detect(source, index, degree);
        SwingDetectorResult followUpResult = detector.detect(generateFollowUp(source), index, degree);
        assertMetamorphicRelation(sourceResult, followUpResult);
    }

    private static void assertMetamorphicRelation(SwingDetectorResult source, SwingDetectorResult followUp) {
        List<?> sourcePivots = listProperty(source, "pivots", "getPivots");
        List<?> followUpPivots = listProperty(followUp, "pivots", "getPivots");
        List<?> sourceSwings = listProperty(source, "swings", "getSwings");
        List<?> followUpSwings = listProperty(followUp, "swings", "getSwings");
        assertEquals(sourcePivots.size(), followUpPivots.size());
        assertEquals(sourceSwings.size(), followUpSwings.size());
        for (int i = 0; i < sourcePivots.size(); i++) {
            Object sourcePivot = sourcePivots.get(i);
            Object followUpPivot = followUpPivots.get(i);
            assertEquals(property(sourcePivot, "index", "getIndex"), property(followUpPivot, "index", "getIndex"));
            assertEquals(property(sourcePivot, "type", "getType"), property(followUpPivot, "type", "getType"));
            assertTranslated((Num) property(sourcePivot, "price", "getPrice"), (Num) property(followUpPivot, "price", "getPrice"));
        }
        for (int i = 0; i < sourceSwings.size(); i++) {
            Object sourceSwing = sourceSwings.get(i);
            Object followUpSwing = followUpSwings.get(i);
            assertEquals(property(sourceSwing, "fromIndex", "getFromIndex", "startIndex", "getStartIndex"), property(followUpSwing, "fromIndex", "getFromIndex", "startIndex", "getStartIndex"));
            assertEquals(property(sourceSwing, "toIndex", "getToIndex", "endIndex", "getEndIndex"), property(followUpSwing, "toIndex", "getToIndex", "endIndex", "getEndIndex"));
            assertEquals(property(sourceSwing, "degree", "getDegree"), property(followUpSwing, "degree", "getDegree"));
            assertTranslated((Num) property(sourceSwing, "fromPrice", "getFromPrice", "startPrice", "getStartPrice"), (Num) property(followUpSwing, "fromPrice", "getFromPrice", "startPrice", "getStartPrice"));
            assertTranslated((Num) property(sourceSwing, "toPrice", "getToPrice", "endPrice", "getEndPrice"), (Num) property(followUpSwing, "toPrice", "getToPrice", "endPrice", "getEndPrice"));
        }
    }

    private static void assertTranslated(Num source, Num followUp) {
        double difference = followUp.minus(source).doubleValue();
        assertTrue(Math.abs(difference - TRANSLATION) <= TOLERANCE, () -> "expected translated value " + TRANSLATION + " but was " + difference);
    }

    @SuppressWarnings("unchecked")
    private static List<?> listProperty(Object target, String... names) {
        return (List<?>) property(target, names);
    }

    private static Object property(Object target, String... names) {
        for (String name : names) {
            try {
                Method method = target.getClass().getMethod(name);
                return method.invoke(target);
            } catch (ReflectiveOperationException ignored) {
                // Try the next supported accessor form.
            }
        }
        throw new AssertionError("No supported accessor found on " + target.getClass().getName());
    }

    @Test
    void SINGLE_BAR_SERIES_variation1() {
        check(detector(), prices(10), -1, ElliottDegree.MINUTE);
    }

    @Test
    void JUST_BELOW_MINIMUM_CANDIDATE_LENGTH_variation1() {
        check(detector(), prices(1, 2, 3, 4, 5), 100, ElliottDegree.MINUETTE);
    }

    @Test
    void EXACT_MINIMUM_CANDIDATE_LENGTH_variation1() {
        check(detector(), prices(1, 2, 3, 4, 5, 6), 100, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMP_variation1() {
        check(detector(), prices(0, 10, 20, 10, 0, -10, -20), -100, ElliottDegree.MINOR);
    }

    @Test
    void INDEX_AT_BEGIN_CLAMP_variation1() {
        check(detector(), prices(2, 4, 8, 4, 2, 1), 0, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void INDEX_ABOVE_END_CLAMP_variation1() {
        check(detector(), prices(0, 10, 20, 10, 0, -10), 1000, ElliottDegree.PRIMARY);
    }

    @Test
    void INTERIOR_INDEX_EXCLUDES_LATER_TURN_variation1() {
        check(detector(), prices(0, 10, 20, 10, 0, -10, -5, 0, 10, 0, -10, -20), 5, ElliottDegree.CYCLE);
    }

    @Test
    void NONZERO_BEGIN_INDEX_variation1() {
        check(detector(), prices(30, 20, 10, 20, 30, 40), 100, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    void SLOPE_CHANGE_BELOW_THRESHOLD_variation1() {
        check(strictDetector(10.0d), prices(1, 2, 3, 4, 5, 6, 7), 100, ElliottDegree.MINOR);
    }

    @Test
    void SAME_POSITIVE_DIRECTION_variation1() {
        check(detector(), prices(1, 2, 4, 7, 11, 16, 22), 100, ElliottDegree.MINUTE);
    }

    @Test
    void SAME_NEGATIVE_DIRECTION_variation1() {
        check(detector(), prices(30, 25, 19, 12, 4, -5, -15), 100, ElliottDegree.MINUETTE);
    }

    @Test
    void ZERO_SLOPE_DIRECTION_REJECTION_variation1() {
        check(detector(), prices(10, 10, 10, 12, 14, 16), 100, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    void NONFINITE_CLOSE_IN_BEFORE_OR_AFTER_WINDOW_variation1() {
        check(detector(), prices(5, 5, 5, 5, 5, 5), 100, ElliottDegree.MINOR);
    }

    @Test
    void HIGH_TURN_CONFIRMATION_FAILURE_variation1() {
        check(detector(), prices(0, 10, 20, 10, 0, 15, 30), 100, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void LOW_TURN_CONFIRMATION_FAILURE_variation1() {
        check(detector(), prices(30, 20, 10, 20, 30, 15, 0), 100, ElliottDegree.PRIMARY);
    }

    @Test
    void FIRST_CONFIRMED_HIGH_variation1() {
        check(detector(), prices(0, 8, 20, 12, 3, -8), 100, ElliottDegree.CYCLE);
    }

    @Test
    void FIRST_CONFIRMED_LOW_variation1() {
        check(detector(), prices(20, 10, 0, 8, 18, 28), 100, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    void HIGH_EXTREME_TIE_USES_EARLIEST_INDEX_variation1() {
        check(detector(), prices(0, 10, 20, 20, 5, -10), 100, ElliottDegree.MINOR);
    }

    @Test
    void LOW_EXTREME_TIE_USES_EARLIEST_INDEX_variation1() {
        check(detector(), prices(20, 10, 0, 0, 15, 30), 100, ElliottDegree.MINUTE);
    }

    @Test
    void NONFINITE_HIGH_PREVENTS_HIGH_PIVOT_variation1() {
        check(detector(), prices(0, 9, 18, 8, 0, -9), 100, ElliottDegree.MINUETTE);
    }

    @Test
    void NONFINITE_LOW_PREVENTS_LOW_PIVOT_variation1() {
        check(detector(), prices(18, 9, 0, 10, 20, 30), 100, ElliottDegree.SUB_MINUETTE);
    }

    @Test
    void ALTERNATING_HIGH_THEN_LOW_variation1() {
        check(detector(), prices(0, 10, 20, 10, 0, -10, -20, -10, 0, 10, 20), 100, ElliottDegree.MINOR);
    }

    @Test
    void ALTERNATING_LOW_THEN_HIGH_variation1() {
        check(detector(), prices(20, 10, 0, 10, 20, 30, 40, 30, 20, 10, 0), 100, ElliottDegree.INTERMEDIATE);
    }

    @Test
    void SAME_TYPE_HIGH_REPLACED_BY_HIGHER_HIGH_variation1() {
        check(detector(), prices(0, 10, 20, 10, 0, -10, 0, 15, 30, 15, 0, -15), 100, ElliottDegree.PRIMARY);
    }

    @Test
    void SAME_TYPE_HIGH_DOES_NOT_REPLACE_variation1() {
        check(detector(), prices(0, 15, 30, 15, 0, -10, 0, 8, 16, 8, 0, -8), 100, ElliottDegree.CYCLE);
    }

    @Test
    void SAME_TYPE_LOW_REPLACED_BY_LOWER_LOW_variation1() {
        check(detector(), prices(30, 15, 0, 15, 30, 40, 25, 10, -10, 5, 20, 30), 100, ElliottDegree.SUPER_CYCLE);
    }

    @Test
    void SAME_TYPE_LOW_DOES_NOT_REPLACE_variation1() {
        check(detector(), prices(30, 15, 0, 15, 30, 40, 25, 15, 5, 18, 30, 40), 100, ElliottDegree.MINOR);
    }

    @Test
    void ATR_MAGNITUDE_REJECTS_SECOND_PIVOT_variation1() {
        check(atrDetector(50.0d), prices(0, 10, 20, 10, 0, -2, -4, -2, 0, 2, 4), 100, ElliottDegree.MINUTE);
    }

    @Test
    void ATR_MAGNITUDE_ACCEPTS_SECOND_PIVOT_variation1() {
        check(atrDetector(0.1d), prices(0, 10, 30, 10, 0, -20, -40, -20, 0, 20, 40), 100, ElliottDegree.MINUETTE);
    }

    @Test
    void NONFINITE_ATR_REJECTS_SECOND_PIVOT_variation1() {
        check(atrDetector(1.0d), prices(0, 12, 24, 12, 0, -12, -24, -12, 0, 12, 24), 100, ElliottDegree.SUB_MINUETTE);
    }
}
