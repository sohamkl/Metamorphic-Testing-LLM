import java.lang.reflect.Proxy;
import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static final double TRANSLATION = 100.0;

    private static final class FollowUp {

        private final SlopeChangeSwingDetector detector;

        private final BarSeries series;

        private final int index;

        private final ElliottDegree degree;

        private FollowUp(SlopeChangeSwingDetector detector, BarSeries series, int index, ElliottDegree degree) {
            this.detector = detector;
            this.series = series;
            this.index = index;
            this.degree = degree;
        }
    }

    private static FollowUp generateFollowUp(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        BarSeries translatedSeries = new BaseBarSeriesBuilder().withName(sourceSeries.getName() + "-translated").withNumFactory(sourceSeries.numFactory()).build();
        Num translation = sourceSeries.numFactory().numOf(TRANSLATION);
        for (int barIndex = sourceSeries.getBeginIndex(); barIndex <= sourceSeries.getEndIndex(); barIndex++) {
            Bar sourceBar = sourceSeries.getBar(barIndex);
            Num translatedAmount = sourceBar.getAmount().plus(translation.multipliedBy(sourceBar.getVolume()));
            translatedSeries.addBar(new BaseBar(sourceBar.getTimePeriod(), sourceBar.getBeginTime(), sourceBar.getEndTime(), sourceBar.getOpenPrice().plus(translation), sourceBar.getHighPrice().plus(translation), sourceBar.getLowPrice().plus(translation), sourceBar.getClosePrice().plus(translation), sourceBar.getVolume(), translatedAmount, sourceBar.getTrades()));
        }
        return new FollowUp(new SlopeChangeSwingDetector(detector.getConfig()), translatedSeries, index, degree);
    }

    private static void verify(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        FollowUp followUp = generateFollowUp(detector, sourceSeries, index, degree);
        SwingDetectorResult followUpOutput = followUp.detector.detect(followUp.series, followUp.index, followUp.degree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static ElliottDegree degree(int index) {
        ElliottDegree[] values = ElliottDegree.values();
        return values[Math.floorMod(index, values.length)];
    }

    private static SlopeChangeSwingDetector detector(int window, int confirmationBars, int atrPeriod, double minSlopeChange, double minAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(window, confirmationBars, atrPeriod, minSlopeChange, minAtrReversal));
    }

    private static BarSeries emptyBeginZeroSeries(final String name) {
        final BarSeries factorySource = new BaseBarSeriesBuilder().withName(name + "-factory").build();
        return (BarSeries) Proxy.newProxyInstance(BarSeries.class.getClassLoader(), new Class<?>[] { BarSeries.class }, (proxy, method, args) -> {
            String methodName = method.getName();
            if ("isEmpty".equals(methodName)) {
                return true;
            }
            if ("getBeginIndex".equals(methodName)) {
                return 0;
            }
            if ("getEndIndex".equals(methodName)) {
                return -1;
            }
            if ("getName".equals(methodName)) {
                return name;
            }
            if ("numFactory".equals(methodName)) {
                return factorySource.numFactory();
            }
            if ("getBarCount".equals(methodName)) {
                return 0;
            }
            if ("toString".equals(methodName)) {
                return name;
            }
            if ("hashCode".equals(methodName)) {
                return System.identityHashCode(proxy);
            }
            if ("equals".equals(methodName)) {
                return proxy == args[0];
            }
            throw new UnsupportedOperationException(methodName);
        });
    }

    private static BarSeries series(double... closes) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];
        double[] volumes = new double[closes.length];
        double[] amounts = new double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 0.25;
            lows[i] = closes[i] - 0.25;
            volumes[i] = i % 3 == 0 ? 0.0 : i + 1.0;
            amounts[i] = closes[i] * volumes[i] + i;
            trades[i] = i + 1L;
        }
        return series("prices", closes, highs, lows, volumes, amounts, trades);
    }

    private static BarSeries series(String name, double[] closes, double[] highs, double[] lows, double[] volumes, double[] amounts, long[] trades) {
        BarSeries result = new BaseBarSeriesBuilder().withName(name).build();
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);
            Num close = result.numFactory().numOf(closes[i]);
            Num high = result.numFactory().numOf(highs[i]);
            Num low = result.numFactory().numOf(lows[i]);
            Num volume = result.numFactory().numOf(volumes[i]);
            Num amount = result.numFactory().numOf(amounts[i]);
            result.addBar(new BaseBar(BAR_DURATION, begin, end, close, high, low, close, volume, amount, trades[i]));
        }
        return result;
    }

    private static BarSeries custom(double[] closes, double[] highs, double[] lows) {
        double[] volumes = new double[closes.length];
        double[] amounts = new double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = i % 2 == 0 ? 0.0 : 10.0 + i;
            amounts[i] = volumes[i] * closes[i] + i * 0.5;
            trades[i] = 2L * i + 1L;
        }
        return series("custom", closes, highs, lows, volumes, amounts, trades);
    }

    @Test
    void SINGLE_BAR_NO_CANDIDATE_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(0.0), 0, degree(1));
    }

    @Test
    void HISTORY_ONE_BAR_SHORT_OF_FIRST_CANDIDATE_variation1() {
        verify(new SlopeChangeSwingDetector(2), series(-3.0, -2.0, -1.0, 0.0), 3, degree(2));
    }

    @Test
    void EXACTLY_ONE_CONFIRMED_HIGH_CANDIDATE_variation1() {
        BarSeries source = custom(new double[] { -103.0, -99.0, -101.0, -104.0 }, new double[] { -102.0, -97.0, -97.0, -103.0 }, new double[] { -104.0, -100.0, -102.0, -105.0 });
        verify(detector(2, 1, 3, 0.0, 0.5), source, 3, degree(3));
    }

    @Test
    void EXACTLY_ONE_CONFIRMED_LOW_CANDIDATE_variation1() {
        verify(new SlopeChangeSwingDetector(2), series(4.0, 2.0, 3.0, 5.0, 7.0), 4, degree(4));
    }

    @Test
    void INDEX_BELOW_BEGIN_CLAMPS_TO_ZERO_variation1() {
        verify(detector(2, 2, 3, 0.0, 0.0), series(0.0, 2.0, 1.0, 0.0, -1.0, 1.0), Integer.MIN_VALUE, degree(5));
    }

    @Test
    void INDEX_ABOVE_END_CLAMPS_TO_END_variation1() {
        verify(new SlopeChangeSwingDetector(2), series(-4.0, -2.0, -3.0, -5.0, -6.0), Integer.MAX_VALUE, degree(6));
    }

    @Test
    void CAUSAL_PREFIX_IGNORES_LATER_REVERSAL_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(0.0, 1.0, 2.0, 4.0, 3.0, 1.0, 0.0), 3, degree(7));
    }

    @Test
    void SLOPE_CHANGE_JUST_BELOW_THRESHOLD_variation1() {
        verify(detector(2, 1, 3, 2.01, 0.0), series(0.0, 1.0, 0.0, -1.0), 3, degree(10));
    }

    @Test
    void SLOPE_CHANGE_EXACTLY_AT_THRESHOLD_variation1() {
        verify(detector(2, 1, 3, 2.0, 0.0), series(0.0, 1.0, 0.0, -1.0), 3, degree(11));
    }

    @Test
    void SAME_SIGN_SLOPES_NOT_A_REVERSAL_variation1() {
        verify(detector(2, 1, 3, 1.0, 0.0), series(0.0, 4.0, 5.0, 6.0), 3, degree(12));
    }

    @Test
    void ZERO_SLOPE_SIDE_NOT_DIRECTIONAL_variation1() {
        verify(detector(2, 1, 3, 1.0, 0.0), series(2.0, 2.0, 3.0, 4.0), 3, degree(13));
    }

    @Test
    void PERSISTENCE_FAILS_ON_FIRST_CHECK_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(0.0, 2.0, 1.0, 1.0), 3, degree(16));
    }

    @Test
    void PERSISTENCE_FAILS_AFTER_INITIAL_SUCCESS_variation1() {
        verify(detector(2, 2, 3, 0.0, 0.0), series(0.0, 2.0, 1.0, 0.0, 2.0), 4, degree(17));
    }

    @Test
    void EQUAL_HIGH_EXTREMES_KEEP_EARLIEST_variation1() {
        BarSeries source = custom(new double[] { 0.0, 2.0, 1.0, 0.0 }, new double[] { 0.5, 5.0, 5.0, 0.5 }, new double[] { -0.5, 1.5, 0.5, -0.5 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 3, degree(21));
    }

    @Test
    void EQUAL_LOW_EXTREMES_KEEP_EARLIEST_variation1() {
        BarSeries source = custom(new double[] { 4.0, 2.0, 3.0, 5.0 }, new double[] { 4.5, 2.5, 3.5, 5.5 }, new double[] { 3.5, -1.0, -1.0, 4.5 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 3, degree(22));
    }

    @Test
    void WICK_EXTREME_DIFFERS_FROM_CLOSE_TURN_variation1() {
        BarSeries source = custom(new double[] { 0.0, 3.0, 2.0, 0.0 }, new double[] { 0.5, 3.5, 9.0, 0.5 }, new double[] { -0.5, 2.5, 1.5, -0.5 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 3, degree(23));
    }

    @Test
    void ZERO_ATR_MULTIPLIER_BYPASSES_LATER_FILTER_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(0.0, 2.0, 1.0, 0.0, 1.0, 2.0), 5, degree(24));
    }

    @Test
    void REVERSAL_DISTANCE_BELOW_ATR_THRESHOLD_variation1() {
        verify(detector(2, 1, 1, 0.0, 10.0), series(0.0, 2.0, 1.0, 0.0, 1.0, 2.0), 5, degree(26));
    }

    @Test
    void REVERSAL_DISTANCE_EXACTLY_AT_ATR_THRESHOLD_variation1() {
        BarSeries source = custom(new double[] { 0.0, 2.0, 1.0, 0.0, 1.0, 2.0 }, new double[] { 0.0, 2.0, 1.0, 2.0, 1.0, 2.0 }, new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 2.0 });
        verify(detector(2, 1, 1, 0.0, 1.0), source, 5, degree(27));
    }

    @Test
    void REVERSAL_DISTANCE_ABOVE_ATR_THRESHOLD_variation1() {
        BarSeries source = custom(new double[] { 0.0, 5.0, 3.0, 0.0, 2.0, 4.0 }, new double[] { 0.1, 5.1, 3.1, 0.1, 2.1, 4.1 }, new double[] { -0.1, 4.9, 2.9, -0.1, 1.9, 3.9 });
        verify(detector(2, 1, 1, 0.0, 0.25), source, 5, degree(28));
    }

    @Test
    void ALTERNATING_HIGH_LOW_PAIR_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(0.0, 3.0, 2.0, 0.0, 1.0, 3.0), 5, degree(29));
    }

    @Test
    void ALTERNATING_LOW_HIGH_PAIR_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(3.0, 0.0, 1.0, 3.0, 2.0, 0.0), 5, degree(30));
    }

    @Test
    void THREE_ALTERNATING_PIVOTS_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(0.0, 2.0, 1.0, 0.0, 1.0, 2.0, 1.0, 0.0), 7, degree(31));
    }

    @Test
    void LATER_HIGH_REPLACES_PREVIOUS_HIGH_variation1() {
        BarSeries source = custom(new double[] { 0.0, 2.0, 1.0, 0.0, 0.0, 0.0, 2.0, 4.0, 3.0, 2.0 }, new double[] { 0.5, 2.5, 1.5, 0.5, 0.5, 0.5, 2.5, 7.0, 3.5, 2.5 }, new double[] { -0.5, 1.5, 0.5, -0.5, -0.5, -0.5, 1.5, 3.5, 2.5, 1.5 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 9, degree(32));
    }

    @Test
    void LOWER_LATER_HIGH_RETAINS_PREVIOUS_HIGH_variation1() {
        BarSeries source = custom(new double[] { 0.0, 4.0, 3.0, 2.0, 2.0, 2.0, 3.0, 4.0, 3.0, 2.0 }, new double[] { 0.5, 8.0, 3.5, 2.5, 2.5, 2.5, 3.5, 5.0, 3.5, 2.5 }, new double[] { -0.5, 3.5, 2.5, 1.5, 1.5, 1.5, 2.5, 3.5, 2.5, 1.5 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 9, degree(33));
    }

    @Test
    void LATER_LOW_REPLACES_PREVIOUS_LOW_variation1() {
        BarSeries source = custom(new double[] { 5.0, 2.0, 3.0, 4.0, 4.0, 4.0, 2.0, 0.0, 1.0, 2.0 }, new double[] { 5.5, 2.5, 3.5, 4.5, 4.5, 4.5, 2.5, 0.5, 1.5, 2.5 }, new double[] { 4.5, 1.5, 2.5, 3.5, 3.5, 3.5, 1.5, -3.0, 0.5, 1.5 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 9, degree(34));
    }

    @Test
    void HIGHER_LATER_LOW_RETAINS_PREVIOUS_LOW_variation1() {
        BarSeries source = custom(new double[] { 5.0, 0.0, 1.0, 2.0, 2.0, 2.0, 1.0, 0.0, 1.0, 2.0 }, new double[] { 5.5, 0.5, 1.5, 2.5, 2.5, 2.5, 1.5, 0.5, 1.5, 2.5 }, new double[] { 4.5, -4.0, 0.5, 1.5, 1.5, 1.5, 0.5, -1.0, 0.5, 1.5 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 9, degree(35));
    }

    @Test
    void SAME_TYPE_EQUAL_PRICE_IS_NOT_REPLACED_variation1() {
        BarSeries source = custom(new double[] { 0.0, 2.0, 1.0, 0.0, 0.0, 0.0, 1.0, 2.0, 1.0, 0.0 }, new double[] { 0.5, 5.0, 1.5, 0.5, 0.5, 0.5, 1.5, 5.0, 1.5, 0.5 }, new double[] { -0.5, 1.5, 0.5, -0.5, -0.5, -0.5, 0.5, 1.5, 0.5, -0.5 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 9, degree(36));
    }

    @Test
    void FLAT_CLOSE_SERIES_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0), 6, degree(37));
    }

    @Test
    void MONOTONIC_CLOSE_SERIES_variation1() {
        verify(new SlopeChangeSwingDetector(2), series(-6.0, -5.0, -4.0, -3.0, -2.0, -1.0, 0.0, 1.0), Integer.MAX_VALUE, degree(38));
    }

    @Test
    void MIN_SLOPE_CHANGE_ZERO_DIRECTIONAL_REVERSAL_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.5), series(0.0, 1.0, 0.5, 0.0), 3, degree(40));
    }

    @Test
    void NEGATIVE_PRICES_CROSS_TRANSLATION_ZERO_variation1() {
        BarSeries source = custom(new double[] { -104.0, -98.0, -101.0, -105.0 }, new double[] { -103.0, -96.0, -99.0, -104.0 }, new double[] { -105.0, -100.0, -103.0, -106.0 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 3, degree(41));
    }

    @Test
    void ZERO_AND_POSITIVE_VOLUME_FIELDS_variation1() {
        double[] closes = { 0.0, 3.0, 2.0, 0.0 };
        double[] highs = { 0.5, 3.5, 2.5, 0.5 };
        double[] lows = { -0.5, 2.5, 1.5, -0.5 };
        double[] volumes = { 0.0, 5.0, 0.0, 8.0 };
        double[] amounts = { 7.0, 15.0, 9.0, 12.0 };
        long[] trades = { 0L, 2L, 5L, 9L };
        verify(detector(2, 1, 3, 0.0, 0.0), series("volume-fields", closes, highs, lows, volumes, amounts, trades), 3, degree(42));
    }

    @Test
    void DEGREE_PROPAGATED_TO_MULTIPLE_SWINGS_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(0.0, 3.0, 2.0, 0.0, 2.0, 4.0, 2.0, 0.0), 7, degree(43));
    }

    @Test
    void INDEX_INCREMENT_REVEALS_FIRST_CONFIRMABLE_PIVOT_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(0.0, 2.0, 1.0, 0.0), 3, degree(44));
    }

    @Test
    void LATE_PREFIX_ADDS_SECOND_PIVOT_AND_SWING_variation1() {
        verify(detector(2, 1, 3, 0.0, 0.0), series(0.0, 3.0, 2.0, 0.0, 1.0, 3.0, 4.0, 5.0, 6.0), 5, degree(45));
    }

    @Test
    void WIDE_RANGE_ATR_FILTERS_WEAK_SECOND_REVERSAL_variation1() {
        BarSeries source = custom(new double[] { 0.0, 2.0, 1.0, 0.0, 1.0, 2.0 }, new double[] { 20.0, 22.0, 21.0, 20.0, 21.0, 22.0 }, new double[] { -20.0, -18.0, -19.0, -20.0, -19.0, -18.0 });
        verify(detector(2, 1, 1, 0.0, 1.0), source, 5, degree(46));
    }

    @Test
    void NARROW_RANGE_ATR_ACCEPTS_SECOND_REVERSAL_variation1() {
        BarSeries source = custom(new double[] { 0.0, 4.0, 2.0, 0.0, 2.0, 4.0 }, new double[] { 0.01, 4.01, 2.01, 0.01, 2.01, 4.01 }, new double[] { -0.01, 3.99, 1.99, -0.01, 1.99, 3.99 });
        verify(detector(2, 1, 1, 0.0, 0.5), source, Integer.MAX_VALUE, degree(47));
    }

    @Test
    void PIVOT_EXTREME_AT_TRANSITION_START_variation1() {
        BarSeries source = custom(new double[] { 0.0, 3.0, 2.0, 0.0 }, new double[] { 0.5, 8.0, 2.5, 0.5 }, new double[] { -0.5, 2.5, 1.5, -0.5 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 3, degree(48));
    }

    @Test
    void PIVOT_EXTREME_AT_TRANSITION_END_variation1() {
        BarSeries source = custom(new double[] { 0.0, 3.0, 2.0, 0.0 }, new double[] { 0.5, 3.5, 9.0, 0.5 }, new double[] { -0.5, 2.5, 1.5, -0.5 });
        verify(detector(2, 1, 3, 0.0, 0.0), source, 3, degree(49));
    }
}
