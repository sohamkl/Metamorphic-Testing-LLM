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
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final double TRANSLATION = 100.0;
    private static final double TOLERANCE = 1.0e-8;
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static void assertMetamorphicRelationFor(TestCase source) {
        SwingDetectorResult sourceResult =
                source.detector.detect(source.series, source.index, source.degree);
        TestCase followUp = generateFollowUp(source);
        SwingDetectorResult followUpResult =
                followUp.detector.detect(followUp.series, followUp.index, followUp.degree);
        assertMetamorphicRelation(sourceResult, followUpResult);
    }

    private static TestCase generateFollowUp(TestCase source) {
        Num translation = source.series.numFactory().numOf(TRANSLATION);
        List<Bar> bars = new ArrayList<>(source.series.getBarCount());

        for (int i = 0; i < source.series.getBarCount(); i++) {
            Bar original = source.series.getBar(i);
            Num volume = original.getVolume();
            Num amount = original.getAmount().plus(translation.multipliedBy(volume));

            bars.add(new BaseBar(
                    original.getTimePeriod(),
                    original.getBeginTime(),
                    original.getEndTime(),
                    original.getOpenPrice().plus(translation),
                    original.getHighPrice().plus(translation),
                    original.getLowPrice().plus(translation),
                    original.getClosePrice().plus(translation),
                    volume,
                    amount,
                    original.getTrades()));
        }

        return new TestCase(
                new BaseBarSeries("translated", bars),
                new SlopeChangeSwingDetector(source.detector.getConfig()),
                source.index,
                source.degree);
    }

    private static void assertMetamorphicRelation(
            SwingDetectorResult source,
            SwingDetectorResult followUp) {
        List<?> sourcePivots = listProperty(source, "getPivots", "pivots");
        List<?> followUpPivots = listProperty(followUp, "getPivots", "pivots");
        List<?> sourceSwings = listProperty(source, "getSwings", "swings");
        List<?> followUpSwings = listProperty(followUp, "getSwings", "swings");

        assertEquals(sourcePivots.size(), followUpPivots.size());
        assertEquals(sourceSwings.size(), followUpSwings.size());

        for (int i = 0; i < sourcePivots.size(); i++) {
            Object left = sourcePivots.get(i);
            Object right = followUpPivots.get(i);

            assertEquals(property(left, "index", "getIndex"),
                    property(right, "index", "getIndex"));
            assertEquals(property(left, "type", "getType"),
                    property(right, "type", "getType"));
            assertEquals(
                    numericProperty(left, "price", "getPrice") + TRANSLATION,
                    numericProperty(right, "price", "getPrice"),
                    TOLERANCE);
        }

        for (int i = 0; i < sourceSwings.size(); i++) {
            Object left = sourceSwings.get(i);
            Object right = followUpSwings.get(i);

            assertEquals(property(left, "fromIndex", "getFromIndex"),
                    property(right, "fromIndex", "getFromIndex"));
            assertEquals(property(left, "toIndex", "getToIndex"),
                    property(right, "toIndex", "getToIndex"));
            assertEquals(property(left, "degree", "getDegree"),
                    property(right, "degree", "getDegree"));
            assertEquals(
                    numericProperty(left, "fromPrice", "getFromPrice") + TRANSLATION,
                    numericProperty(right, "fromPrice", "getFromPrice"),
                    TOLERANCE);
            assertEquals(
                    numericProperty(left, "toPrice", "getToPrice") + TRANSLATION,
                    numericProperty(right, "toPrice", "getToPrice"),
                    TOLERANCE);
        }
    }

    private static List<?> listProperty(Object object, String... names) {
        return (List<?>) property(object, names);
    }

    private static double numericProperty(Object object, String... names) {
        Object value = property(object, names);
        if (value instanceof Num) {
            return ((Num) value).doubleValue();
        }
        return ((Number) value).doubleValue();
    }

    private static Object property(Object object, String... names) {
        for (String name : names) {
            try {
                Method method = object.getClass().getMethod(name);
                return method.invoke(object);
            } catch (ReflectiveOperationException ignored) {
            }
        }
        throw new AssertionError("No readable property found on " + object.getClass().getName());
    }

    private static TestCase testCase(
            int length,
            int window,
            int index,
            int pattern,
            int atrPeriod,
            double minSlopeChange,
            double minAtrReversal,
            ElliottDegree degree) {
        List<Bar> bars = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            double close = closeValue(i, pattern);
            double open = close - 0.25;
            double high = close + 0.75;
            double low = close - 0.75;
            double volume = 10.0 + (i % 3);
            double amount = close * volume;

            bars.add(new BaseBar(
                    Duration.ofMinutes(1),
                    BASE_TIME.plusSeconds(i * 60L),
                    BASE_TIME.plusSeconds((i + 1L) * 60L),
                    decimal(open),
                    decimal(high),
                    decimal(low),
                    decimal(close),
                    decimal(volume),
                    decimal(amount),
                    i + 1L));
        }

        SlopeChangeConfig config = new SlopeChangeConfig(
                window,
                1,
                atrPeriod,
                minSlopeChange,
                minAtrReversal);

        return new TestCase(
                new BaseBarSeries("source", bars),
                new SlopeChangeSwingDetector(config),
                index,
                degree);
    }

    private static DecimalNum decimal(double value) {
        return DecimalNum.valueOf(Double.toString(value));
    }

    private static double closeValue(int index, int pattern) {
        switch (pattern) {
        case 1:
            return index;
        case 2:
            return index < 16 ? index : 32.0 - index;
        case 3:
            return index < 16 ? 32.0 - index : index - 16.0;
        case 4:
            return index % 8 == 0 ? 20.0 : index * 0.5;
        case 5:
            return 10.0;
        case 6:
            return index * 0.01;
        case 7:
            return index < 10 ? index : 10.0;
        case 8:
            return index < 10 ? 10.0 - index : 0.0;
        case 9:
            return index % 10 < 5 ? index % 10 : 10.0 - (index % 10);
        case 10:
            return index % 12 < 6 ? index % 12 : 12.0 - (index % 12);
        case 11:
            return index % 16 < 8 ? index % 16 : 16.0 - (index % 16);
        case 12:
            return index % 20 < 10 ? index % 20 : 20.0 - (index % 20);
        case 13:
            return index % 18 < 9 ? 18.0 - (index % 18) : index % 18;
        case 14:
            return index % 15 < 7 ? 15.0 - (index % 15) : index % 15;
        case 15:
            return index % 14 < 7 ? index % 14 : 14.0 - (index % 14);
        case 16:
            return index % 12 < 6 ? 30.0 : 10.0;
        case 17:
            return index % 12 < 6 ? 5.0 : 5.0;
        case 18:
            return 1.0e12 + (index % 9 < 4 ? index : 8.0 - index);
        default:
            return 10.0 + index * 0.1;
        }
    }

    private static final class TestCase {
        private final BarSeries series;
        private final SlopeChangeSwingDetector detector;
        private final int index;
        private final ElliottDegree degree;

        private TestCase(
                BarSeries series,
                SlopeChangeSwingDetector detector,
                int index,
                ElliottDegree degree) {
            this.series = series;
            this.detector = detector;
            this.index = index;
            this.degree = degree;
        }
    }

    @Test
    void EMPTY_SERIES_1_empty() {
        assertMetamorphicRelationFor(testCase(0, 3, -1, 0, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void TOO_SHORT_FOR_ANY_CANDIDATE_1_short() {
        assertMetamorphicRelationFor(testCase(1, 3, 0, 1, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMP_1_lowerClamp() {
        assertMetamorphicRelationFor(testCase(20, 3, -10, 2, 1, 0.0, 0.0, ElliottDegree.INTERMEDIATE));
    }

    @Test
    void INDEX_ABOVE_END_CLAMP_1_upperClamp() {
        assertMetamorphicRelationFor(testCase(40, 3, 1000, 3, 1, 0.0, 0.0, ElliottDegree.PRIMARY));
    }

    @Test
    void EXACT_CANDIDATE_BOUNDARIES_1_firstCandidate() {
        assertMetamorphicRelationFor(testCase(8, 3, 7, 2, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void EXACT_CANDIDATE_BOUNDARIES_2_multipleCandidates() {
        assertMetamorphicRelationFor(testCase(24, 3, 23, 3, 2, 0.0, 0.0, ElliottDegree.INTERMEDIATE));
    }

    @Test
    void NONFINITE_SLOPE_GUARD_1_finiteFixture() {
        assertMetamorphicRelationFor(testCase(18, 4, 17, 4, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void SLOPE_CHANGE_BELOW_THRESHOLD_1_threshold() {
        assertMetamorphicRelationFor(testCase(24, 4, 23, 6, 1, 1000.0, 0.0, ElliottDegree.PRIMARY));
    }

    @Test
    void SAME_SIGN_OR_ZERO_SLOPES_1_sameSign() {
        assertMetamorphicRelationFor(testCase(30, 3, 29, 1, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void SAME_SIGN_OR_ZERO_SLOPES_2_zeroSlope() {
        assertMetamorphicRelationFor(testCase(30, 3, 29, 5, 1, 0.0, 0.0, ElliottDegree.INTERMEDIATE));
    }

    @Test
    void CONFIRMED_HIGH_REVERSAL_1_highTurn() {
        assertMetamorphicRelationFor(testCase(48, 3, 47, 2, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void CONFIRMED_HIGH_REVERSAL_2_highTurnLong() {
        assertMetamorphicRelationFor(testCase(80, 4, 79, 11, 1, 0.0, 0.0, ElliottDegree.PRIMARY));
    }

    @Test
    void CONFIRMED_LOW_REVERSAL_1_lowTurn() {
        assertMetamorphicRelationFor(testCase(48, 3, 47, 3, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void CONFIRMED_LOW_REVERSAL_2_lowTurnLong() {
        assertMetamorphicRelationFor(testCase(80, 4, 79, 13, 1, 0.0, 0.0, ElliottDegree.INTERMEDIATE));
    }

    @Test
    void PERSISTENCE_REJECTED_HIGH_1_persistence() {
        assertMetamorphicRelationFor(testCase(32, 5, 31, 7, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void PERSISTENCE_REJECTED_LOW_1_persistence() {
        assertMetamorphicRelationFor(testCase(32, 5, 31, 8, 1, 0.0, 0.0, ElliottDegree.PRIMARY));
    }

    @Test
    void NONFINITE_EXTREME_VALUE_1_finiteFixture() {
        assertMetamorphicRelationFor(testCase(36, 3, 35, 9, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_1_firstPivot() {
        assertMetamorphicRelationFor(testCase(48, 3, 47, 3, 1, 0.0, 1000.0, ElliottDegree.MINOR));
    }

    @Test
    void FIRST_PIVOT_BYPASSES_MAGNITUDE_2_firstHigh() {
        assertMetamorphicRelationFor(testCase(48, 3, 47, 2, 1, 0.0, 1000.0, ElliottDegree.PRIMARY));
    }

    @Test
    void ZERO_ATR_MULTIPLIER_ACCEPTS_LATER_PIVOT_1_zeroMultiplier() {
        assertMetamorphicRelationFor(testCase(80, 3, 79, 11, 1, 0.0, 0.0, ElliottDegree.INTERMEDIATE));
    }

    @Test
    void INSUFFICIENT_ATR_REVERSAL_1_filtered() {
        assertMetamorphicRelationFor(testCase(80, 3, 79, 9, 1, 0.0, 100.0, ElliottDegree.MINOR));
    }

    @Test
    void NONFINITE_ATR_REJECTS_LATER_PIVOT_1_finiteFixture() {
        assertMetamorphicRelationFor(testCase(80, 3, 79, 10, 1, 0.0, 10.0, ElliottDegree.PRIMARY));
    }

    @Test
    void ALTERNATING_PIVOTS_AND_SWINGS_1_wave() {
        assertMetamorphicRelationFor(testCase(96, 3, 95, 11, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void ALTERNATING_PIVOTS_AND_SWINGS_2_waveVariant() {
        assertMetamorphicRelationFor(testCase(120, 4, 119, 12, 1, 0.0, 0.0, ElliottDegree.INTERMEDIATE));
    }

    @Test
    void REPLACE_WEAKER_HIGH_WITH_STRONGER_HIGH_1_highReplacement() {
        assertMetamorphicRelationFor(testCase(100, 3, 99, 14, 1, 0.0, 0.0, ElliottDegree.PRIMARY));
    }

    @Test
    void REPLACE_WEAKER_LOW_WITH_STRONGER_LOW_1_lowReplacement() {
        assertMetamorphicRelationFor(testCase(100, 3, 99, 15, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void SAME_TYPE_NONIMPROVING_PIVOT_RETAINED_1_retainedLow() {
        assertMetamorphicRelationFor(testCase(80, 4, 79, 15, 1, 0.0, 0.0, ElliottDegree.INTERMEDIATE));
    }

    @Test
    void SAME_TYPE_NONIMPROVING_PIVOT_RETAINED_2_retainedHigh() {
        assertMetamorphicRelationFor(testCase(80, 3, 79, 14, 1, 0.0, 0.0, ElliottDegree.PRIMARY));
    }

    @Test
    void EQUAL_EXTREME_RETains_FIRST_INDEX_1_equalLow() {
        assertMetamorphicRelationFor(testCase(40, 3, 39, 17, 1, 0.0, 0.0, ElliottDegree.MINOR));
    }

    @Test
    void EQUAL_EXTREME_RETains_FIRST_INDEX_2_equalHigh() {
        assertMetamorphicRelationFor(testCase(64, 4, 63, 16, 1, 0.0, 0.0, ElliottDegree.INTERMEDIATE));
    }

    @Test
    void NUMERIC_BOUNDARY_TRANSLATION_1_boundaryScale() {
        assertMetamorphicRelationFor(testCase(64, 3, 63, 18, 1, 0.0, 0.0, ElliottDegree.PRIMARY));
    }
}
