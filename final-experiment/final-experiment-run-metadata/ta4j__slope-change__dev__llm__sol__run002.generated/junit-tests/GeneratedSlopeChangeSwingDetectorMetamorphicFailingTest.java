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

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

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
}
