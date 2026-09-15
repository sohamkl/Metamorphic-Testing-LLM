import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;
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

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration BAR_DURATION = Duration.ofDays(1);

    private static void execute(SlopeChangeSwingDetector detector, BarSeries sourceSeries,
            int index, ElliottDegree degree) {
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);

        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                detector, sourceSeries, index, degree);

        SlopeChangeSwingDetector followUpDetector =
                (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput = followUpDetector.detect(
                followUpSeries, followUpIndex, followUpDegree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static ElliottDegree degree(int ordinal) {
        ElliottDegree[] values = ElliottDegree.values();
        return ElliottDegree.valueOf(
                values[Math.floorMod(ordinal, values.length)].name());
    }

    private static BarSeries series(double[] closes, double volume,
            double amount, long trades) {
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];

        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 1.0;
            lows[i] = closes[i] - 1.0;
        }

        return series(closes, highs, lows, volume, amount, trades);
    }

    private static BarSeries series(double[] closes, double[] highs,
            double[] lows, double volume, double amount, long trades) {
        if (closes.length != highs.length || closes.length != lows.length) {
            throw new IllegalArgumentException("OHLC arrays must have equal lengths");
        }

        BarSeries result = new BaseBarSeriesBuilder()
                .withName("source-series")
                .build();

        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);

            Num openPrice = result.numFactory().numOf(closes[i]);
            Num highPrice = result.numFactory().numOf(highs[i]);
            Num lowPrice = result.numFactory().numOf(lows[i]);
            Num closePrice = result.numFactory().numOf(closes[i]);
            Num barVolume = result.numFactory().numOf(volume);
            Num barAmount = result.numFactory().numOf(amount);

            result.addBar(new BaseBar(
                    BAR_DURATION,
                    begin,
                    end,
                    openPrice,
                    highPrice,
                    lowPrice,
                    closePrice,
                    barVolume,
                    barAmount,
                    trades));
        }

        return result;
    }

    private static SlopeChangeConfig config(int window, int confirmationBars,
            int atrPeriod, double minSlopeChange, double minAtrReversal) {
        try {
            RecordComponent[] components = SlopeChangeConfig.class.getRecordComponents();
            if (components == null) {
                throw new IllegalStateException("SlopeChangeConfig is not a record");
            }

            Class<?>[] parameterTypes = new Class<?>[components.length];
            Object[] arguments = new Object[components.length];

            for (int i = 0; i < components.length; i++) {
                RecordComponent component = components[i];
                parameterTypes[i] = component.getType();

                switch (component.getName()) {
                    case "window":
                        arguments[i] = window;
                        break;
                    case "confirmationBars":
                        arguments[i] = confirmationBars;
                        break;
                    case "atrPeriod":
                        arguments[i] = atrPeriod;
                        break;
                    case "minSlopeChange":
                        arguments[i] = numericArgument(
                                component.getType(), minSlopeChange);
                        break;
                    case "minAtrReversal":
                        arguments[i] = numericArgument(
                                component.getType(), minAtrReversal);
                        break;
                    default:
                        throw new IllegalStateException(
                                "Unsupported SlopeChangeConfig component: "
                                        + component.getName());
                }
            }

            Constructor<SlopeChangeConfig> constructor =
                    SlopeChangeConfig.class.getConstructor(parameterTypes);
            return constructor.newInstance(arguments);
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException(
                    "Unable to construct SlopeChangeConfig", exception);
        }
    }

    private static Object numericArgument(Class<?> type, double value) {
        if (type == double.class || type == Double.class) {
            return value;
        }
        if (type == float.class || type == Float.class) {
            return (float) value;
        }
        if (type == int.class || type == Integer.class) {
            return (int) value;
        }
        if (type == long.class || type == Long.class) {
            return (long) value;
        }
        throw new IllegalStateException(
                "Unsupported numeric configuration type: " + type.getName());
    }

    @Test
    public void SINGLE_BAR_NO_CANDIDATE_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 3, 0.0, 0.0)),
                series(new double[] { -150.0 }, 0.0, 0.0, 0), 0, degree(0));
    }

    @Test
    public void HORIZON_ONE_BAR_SHORT_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                series(new double[] { -104, -103, -102, -101, -100 }, 2.0, 50.0, 1),
                4, degree(1));
    }

    @Test
    public void EXACT_FIRST_CANDIDATE_HORIZON_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 2, 3, 0.0, 0.0)),
                series(new double[] { -2, -1, 0, -1, -2, -3, -4 }, 1.0, 0.0, 2),
                6, degree(2));
    }

    @Test
    public void INDEX_BELOW_BEGIN_CLAMP_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 3, 0.0, 0.0)),
                series(new double[] { 10, 15, 9, 5, 12, 17 }, 1.0, 20.0, 3),
                Integer.MIN_VALUE, degree(3));
    }

    @Test
    public void INDEX_ABOVE_END_CLAMP_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.1)),
                series(new double[] {
                        1.0e9, 1.0e9 + 20, 1.0e9 + 40, 1.0e9 + 20,
                        1.0e9, 1.0e9 - 20, 1.0e9, 1.0e9 + 20
                }, 0.0, 100.0, 0),
                Integer.MAX_VALUE, degree(4));
    }

    @Test
    public void INTERNAL_INDEX_IGNORES_LATER_TURN_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 2, 3, 0.1, 0.5)),
                series(new double[] {
                        -160, -159, -158, -157, -156, -150,
                        -145, -140, -150, -160, -170
                }, 1.0, 15.0, 4),
                4, degree(5));
    }

    @Test
    public void FLAT_CLOSE_WINDOWS_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(new double[] { -100, -100, -100, -100, -100, -100 },
                        0.0, 0.0, 0),
                Integer.MAX_VALUE, degree(6));
    }

    @Test
    public void SLOPE_CHANGE_BELOW_MINIMUM_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 10.0, 0.0)),
                series(new double[] { 0, 1, 2, 1, 0, -1 }, 2.0, 20.0, 1),
                5, degree(7));
    }

    @Test
    public void SLOPE_CHANGE_EXACT_MINIMUM_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 2.0, 0.5)),
                series(new double[] { 10, 11, 10, 9 }, 1.0, 0.0, 2),
                3, degree(8));
    }

    @Test
    public void SLOPE_CHANGE_ABOVE_MINIMUM_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 1.0, 0.0)),
                series(new double[] {
                        1.0e8, 1.0e8 - 10, 1.0e8, 1.0e8 + 10,
                        1.0e8 + 20
                }, 3.0, 80.0, 2),
                4, degree(0));
    }

    @Test
    public void BOTH_SLOPES_POSITIVE_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.5, 0.0)),
                series(new double[] { -150, -149, -148, -146, -143, -139 },
                        0.0, 5.0, 0),
                5, degree(1));
    }

    @Test
    public void BOTH_SLOPES_NEGATIVE_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 2, 3, 0.5, 0.5)),
                series(new double[] { -90, -91, -92, -94, -97, -101, -106 },
                        1.0, 9.0, 4),
                Integer.MAX_VALUE, degree(2));
    }

    @Test
    public void ZERO_BEFORE_SLOPE_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 1.0, 0.0)),
                series(new double[] { 0, 0, -2, -4 }, 0.0, 0.0, 0),
                3, degree(3));
    }

    @Test
    public void ZERO_AFTER_SLOPE_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 1.0, 0.0)),
                series(new double[] { 10, 12, 12, 12 }, 2.0, 30.0, 1),
                Integer.MAX_VALUE, degree(4));
    }

    @Test
    public void NONFINITE_BEFORE_SLOPE_SENTINEL_variation1() {
        execute(new SlopeChangeSwingDetector(config(4, 2, 4, 0.1, 0.5)),
                series(new double[] {
                        1.0e12, 1.0e12, 1.0e12, 1.0e12, 1.0e12,
                        1.0e12, 1.0e12, 1.0e12, 1.0e12
                }, 1.0, 0.0, 2),
                8, degree(5));
    }

    @Test
    public void NONFINITE_AFTER_SLOPE_SENTINEL_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.1, 0.0)),
                series(new double[] { -160, -159, -159, -159 },
                        1.0, 20.0, 2),
                3, degree(6));
    }

    @Test
    public void CONFIRMED_HIGH_TURN_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 1.0)),
                series(new double[] { -105, -103, -101, -102, -104, -106 },
                        0.0, 8.0, 0),
                5, degree(7));
    }

    @Test
    public void CONFIRMED_LOW_TURN_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 2, 3, 0.0, 0.5)),
                series(new double[] { 3, 2, 1, 2, 3, 4, 5 },
                        1.0, 10.0, 3),
                6, degree(8));
    }

    @Test
    public void HIGH_CONFIRMATION_WRONG_SIGN_FIRST_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(new double[] { 10, 12, 11, 11 },
                        0.0, 0.0, 0),
                3, degree(0));
    }

    @Test
    public void LOW_CONFIRMATION_WRONG_SIGN_FIRST_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(new double[] { 20, 18, 19, 19 },
                        2.0, 25.0, 1),
                3, degree(1));
    }

    @Test
    public void LATE_CONFIRMATION_FAILURE_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 2, 2, 0.0, 0.5)),
                series(new double[] { -150, -145, -146, -147, -146 },
                        1.0, 0.0, 2),
                4, degree(2));
    }

    @Test
    public void ZERO_CONFIRMATION_BARS_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(new double[] { -102, -104, -103, -102 },
                        1.0, 12.0, 2),
                3, degree(3));
    }

    @Test
    public void HIGH_EXTREME_AT_TRANSITION_START_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.1)),
                series(
                        new double[] { 0, 2, 4, 3, 2, 1 },
                        new double[] { 1, 3, 20, 4, 3, 2 },
                        new double[] { -1, 1, 3, 2, 1, 0 },
                        1.0, 20.0, 0),
                5, degree(4));
    }

    @Test
    public void HIGH_EXTREME_AT_TRANSITION_END_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 2, 3, 0.0, 0.0)),
                series(
                        new double[] { 10, 12, 14, 13, 12, 11, 10 },
                        new double[] { 11, 13, 15, 16, 30, 12, 11 },
                        new double[] { 9, 11, 13, 12, 11, 10, 9 },
                        1.0, 14.0, 3),
                6, degree(5));
    }

    @Test
    public void HIGH_EQUAL_EXTREME_TIE_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(
                        new double[] { 1000, 1010, 1005, 1000 },
                        new double[] { 1001, 1020, 1020, 1001 },
                        new double[] { 999, 1009, 1004, 999 },
                        0.0, 0.0, 0),
                3, degree(6));
    }

    @Test
    public void LOW_EXTREME_AT_TRANSITION_START_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                series(
                        new double[] { -150, -152, -154, -153, -152, -151 },
                        new double[] { -149, -151, -153, -152, -151, -150 },
                        new double[] { -151, -153, -180, -154, -153, -152 },
                        2.0, 30.0, 1),
                5, degree(7));
    }

    @Test
    public void LOW_EXTREME_AT_TRANSITION_END_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 2, 3, 0.0, 0.5)),
                series(
                        new double[] { -95, -97, -99, -98, -97, -96, -95 },
                        new double[] { -94, -96, -98, -97, -96, -95, -94 },
                        new double[] { -96, -98, -100, -99, -130, -97, -96 },
                        1.0, 0.0, 2),
                6, degree(8));
    }

    @Test
    public void LOW_EQUAL_EXTREME_TIE_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(
                        new double[] { 2, 0, 1, 2 },
                        new double[] { 3, 1, 2, 3 },
                        new double[] { 1, -5, -5, 1 },
                        1.0, 16.0, 2),
                Integer.MAX_VALUE, degree(0));
    }

    @Test
    public void NONFINITE_INITIAL_EXTREME_SENTINEL_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.2)),
                series(new double[] { 20, 22, 24, 23, 22, 21 },
                        0.0, 7.0, 0),
                5, degree(1));
    }

    @Test
    public void NONFINITE_LATER_EXTREME_SENTINEL_variation1() {
        execute(new SlopeChangeSwingDetector(config(4, 2, 4, 0.0, 0.5)),
                series(new double[] {
                        1.0e9, 1.0e9 - 3, 1.0e9 - 6, 1.0e9 - 9,
                        1.0e9 - 6, 1.0e9 - 3, 1.0e9, 1.0e9 + 3,
                        1.0e9 + 6
                }, 1.0, 11.0, 3),
                8, degree(2));
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_MAGNITUDE_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 20, 0.0, 10.0)),
                series(new double[] { -160, -162, -161, -160 },
                        0.0, 0.0, 0),
                3, degree(3));
    }

    @Test
    public void ZERO_ATR_MULTIPLIER_BYPASS_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(new double[] {
                        -103.0, -101.0, -102.0, -103.0, -102.5, -102.0
                }, 2.0, 40.0, 1),
                5, degree(4));
    }

    @Test
    public void ATR_REVERSAL_BELOW_THRESHOLD_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 100.0)),
                series(new double[] { 0, 3, 6, 3, 0, -3, -2, -1, 0 },
                        1.0, 0.0, 2),
                8, degree(5));
    }

    @Test
    public void ATR_REVERSAL_EXACT_THRESHOLD_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 1, 0.0, 1.0)),
                series(new double[] { 10, 12, 11, 10, 11, 12 },
                        1.0, 18.0, 2),
                5, degree(6));
    }

    @Test
    public void ATR_REVERSAL_ABOVE_THRESHOLD_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.1)),
                series(new double[] {
                        1.0e9, 1.0e9 + 100, 1.0e9 + 200,
                        1.0e9 + 100, 1.0e9, 1.0e9 - 100,
                        1.0e9, 1.0e9 + 100, 1.0e9 + 200
                }, 0.0, 90.0, 0),
                Integer.MAX_VALUE, degree(7));
    }

    @Test
    public void NONFINITE_ATR_SENTINEL_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 2, 100, 0.0, 1.0)),
                series(new double[] {
                        -170, -165, -160, -165, -170,
                        -175, -170, -165, -160
                }, 1.0, 12.0, 4),
                8, degree(8));
    }

    @Test
    public void SAME_TYPE_HIGH_REPLACES_PREVIOUS_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(
                        new double[] { -110, -105, -107, -106, -104, -108, -109 },
                        new double[] { -109, -100, -106, -105, -90, -107, -108 },
                        new double[] { -111, -106, -108, -107, -105, -109, -110 },
                        0.0, 0.0, 0),
                6, degree(0));
    }

    @Test
    public void SAME_TYPE_HIGH_NOT_STRONGER_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(
                        new double[] { 0, 4, 2, 1, 3, 5, 2, 1 },
                        new double[] { 1, 10, 3, 2, 4, 8, 3, 2 },
                        new double[] { -1, 3, 1, 0, 2, 4, 1, 0 },
                        2.0, 35.0, 1),
                7, degree(1));
    }

    @Test
    public void SAME_TYPE_LOW_REPLACES_PREVIOUS_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                series(
                        new double[] { 20, 15, 10, 15, 20, 18, 14, 8, 12, 16 },
                        new double[] { 21, 16, 11, 16, 21, 19, 15, 9, 13, 17 },
                        new double[] { 19, 9, 9, 14, 19, 17, 13, 0, 11, 15 },
                        1.0, 0.0, 2),
                9, degree(2));
    }

    @Test
    public void SAME_TYPE_LOW_NOT_STRONGER_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(
                        new double[] {
                                1.0e8, 1.0e8 - 10, 1.0e8 - 5,
                                1.0e8 - 8, 1.0e8 - 4, 1.0e8
                        },
                        new double[] {
                                1.0e8 + 1, 1.0e8 - 9, 1.0e8 - 4,
                                1.0e8 - 7, 1.0e8 - 3, 1.0e8 + 1
                        },
                        new double[] {
                                1.0e8 - 1, 1.0e8 - 20, 1.0e8 - 6,
                                1.0e8 - 15, 1.0e8 - 5, 1.0e8 - 1
                        },
                        1.0, 25.0, 2),
                Integer.MAX_VALUE, degree(3));
    }

    @Test
    public void ALTERNATING_HIGH_LOW_PAIR_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.1)),
                series(new double[] { -160, -150, -155, -160, -155, -150 },
                        0.0, 4.0, 0),
                5, degree(4));
    }

    @Test
    public void ALTERNATING_LOW_HIGH_PAIR_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 2, 3, 0.0, 0.0)),
                series(new double[] {
                        -95, -100, -105, -100, -95,
                        -90, -95, -100, -105
                }, 1.0, 15.0, 3),
                Integer.MAX_VALUE, degree(5));
    }

    @Test
    public void MULTIPLE_ALTERNATING_SWINGS_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(new double[] {
                        0, 5, 2, -1, 3, 7, 4, 0, 4, 8, 5, 1
                }, 0.0, 0.0, 0),
                11, degree(6));
    }

    @Test
    public void CLOSE_TURN_WITH_WICK_SELECTED_EXTREME_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                series(
                        new double[] { 10, 12, 14, 13, 12, 11 },
                        new double[] { 11, 13, 15, 40, 13, 12 },
                        new double[] { 9, 11, 13, 12, 11, 10 },
                        2.0, 70.0, 1),
                5, degree(7));
    }

    @Test
    public void PRICE_AT_NEGATIVE_TRANSLATION_BOUNDARY_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 2, 3, 0.0, 0.0)),
                series(
                        new double[] { -110, -106, -102, -104, -108, -112, -116 },
                        new double[] { -109, -105, -100, -103, -107, -111, -115 },
                        new double[] { -111, -107, -103, -105, -109, -113, -117 },
                        1.0, 0.0, 2),
                6, degree(8));
    }

    @Test
    public void PRICES_BELOW_NEGATIVE_TRANSLATION_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(
                        new double[] { -200, -180, -190, -210, -200, -185 },
                        new double[] { -199, -175, -189, -209, -199, -184 },
                        new double[] { -201, -181, -191, -220, -201, -186 },
                        1.0, 25.0, 2),
                5, degree(0));
    }

    @Test
    public void LARGE_FINITE_PRICE_LEVEL_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 1.0, 0.1)),
                series(new double[] {
                        1.0e14, 1.0e14 + 1000, 1.0e14 + 2000,
                        1.0e14 + 1000, 1.0e14, 1.0e14 - 1000
                }, 0.0, 9.0, 0),
                Integer.MAX_VALUE, degree(1));
    }

    @Test
    public void NONZERO_VOLUME_AMOUNT_TRANSLATION_variation1() {
        execute(new SlopeChangeSwingDetector(config(3, 2, 3, 0.0, 0.0)),
                series(new double[] { -2, 0, 2, 1, 0, -1, -2 },
                        5.0, 125.0, 4),
                6, degree(2));
    }

    @Test
    public void ZERO_VOLUME_AND_AMOUNT_variation1() {
        execute(new SlopeChangeSwingDetector(config(2, 1, 2, 0.0, 0.0)),
                series(new double[] { 10, 15, 12, 8, 11, 14 },
                        0.0, 0.0, 0),
                Integer.MAX_VALUE, degree(3));
    }

    @Test
    public void DEGREE_PROPAGATION_WITH_SWING_variation1() {
        ElliottDegree selectedDegree = ElliottDegree.valueOf(
                ElliottDegree.values()[4 % ElliottDegree.values().length].name());
        execute(new SlopeChangeSwingDetector(config(3, 1, 3, 0.0, 0.0)),
                series(new double[] {
                        1.0e9, 1.0e9 - 30, 1.0e9 - 60,
                        1.0e9 - 30, 1.0e9, 1.0e9 + 30,
                        1.0e9, 1.0e9 - 30, 1.0e9 - 60
                }, 3.0, 90.0, 2),
                8, selectedDegree);
    }
}
