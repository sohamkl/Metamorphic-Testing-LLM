import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static void runCase(RenkoBarAggregator sourceReceiver, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);
        FollowUp followUp = generateFollowUp(sourceReceiver, sourceBars);
        List<Bar> followUpOutput = followUp.receiver.aggregate(followUp.bars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(RenkoBarAggregator sourceReceiver, List<Bar> sourceBars) {
        if (sourceReceiver == null) {
            throw new NullPointerException("sourceReceiver");
        }
        if (sourceBars == null) {
            throw new NullPointerException("sourceBars");
        }

        RenkoConfiguration configuration = readConfiguration(sourceReceiver);
        RenkoBarAggregator followUpReceiver = new RenkoBarAggregator(
                configuration.boxSize.doubleValue() * 2.0,
                configuration.reversalAmount);

        List<Bar> followUpBars = new ArrayList<Bar>(sourceBars.size());
        for (Bar sourceBar : sourceBars) {
            if (sourceBar == null) {
                throw new NullPointerException("source bars must not contain null");
            }
            followUpBars.add(new BaseBar(
                    sourceBar.getTimePeriod(),
                    sourceBar.getBeginTime(),
                    sourceBar.getEndTime(),
                    scale(sourceBar.getOpenPrice()),
                    scale(sourceBar.getHighPrice()),
                    scale(sourceBar.getLowPrice()),
                    scale(sourceBar.getClosePrice()),
                    sourceBar.getVolume(),
                    scale(sourceBar.getAmount()),
                    sourceBar.getTrades()));
        }

        return new FollowUp(followUpReceiver, followUpBars);
    }

    private static RenkoConfiguration readConfiguration(RenkoBarAggregator sourceReceiver) {
        try {
            Field boxSizeField = RenkoBarAggregator.class.getDeclaredField("boxSize");
            Field reversalAmountField = RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            boxSizeField.setAccessible(true);
            reversalAmountField.setAccessible(true);
            Number boxSize = (Number) boxSizeField.get(sourceReceiver);
            int reversalAmount = reversalAmountField.getInt(sourceReceiver);
            return new RenkoConfiguration(boxSize, reversalAmount);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException("Unable to read Renko configuration", failure);
        }
    }

    private static Num scale(Num value) {
        if (value == null) {
            return null;
        }
        return value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(Duration period, double... closes) {
        return buildBars(period, closes, MetricMode.STANDARD, false);
    }

    private static List<Bar> barsWithNullMetrics(Duration period, double... closes) {
        return buildBars(period, closes, MetricMode.NULL_MIXED, false);
    }

    private static List<Bar> barsZeroMetrics(Duration period, double... closes) {
        return buildBars(period, closes, MetricMode.ZERO, false);
    }

    private static List<Bar> barsWithNullOhlc(Duration period, double... closes) {
        return buildBars(period, closes, MetricMode.STANDARD, true);
    }

    private static List<Bar> buildBars(
            Duration period,
            double[] closes,
            MetricMode metricMode,
            boolean nullableOhlc) {

        List<Bar> result = new ArrayList<Bar>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            Instant endTime = BASE_TIME.plus(period.multipliedBy(index + 1L));
            Instant beginTime = endTime.minus(period);
            Num close = num(closes[index]);

            Num open = close;
            Num high = close.plus(num(0.125));
            Num low = close.minus(num(0.125));

            if (nullableOhlc) {
                if (index % 2 == 0) {
                    open = null;
                }
                if (index % 3 == 0) {
                    high = null;
                }
                if (index % 2 != 0) {
                    low = null;
                }
            }

            Num volume;
            Num amount;
            long trades;

            if (metricMode == MetricMode.ZERO) {
                volume = num(0.0);
                amount = num(0.0);
                trades = 0L;
            } else if (metricMode == MetricMode.NULL_MIXED) {
                volume = index % 2 == 0 ? null : num(index + 1.0);
                amount = index % 3 == 0 ? null : num((index + 1.0) * 10.0);
                trades = index + 1L;
            } else {
                volume = num(index + 1.0);
                amount = num((index + 1.0) * 10.0);
                trades = index + 1L;
            }

            result.add(new BaseBar(
                    period,
                    beginTime,
                    endTime,
                    open,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    trades));
        }
        return result;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private enum MetricMode {
        STANDARD,
        NULL_MIXED,
        ZERO
    }

    private static final class FollowUp {
        private final RenkoBarAggregator receiver;
        private final List<Bar> bars;

        private FollowUp(RenkoBarAggregator receiver, List<Bar> bars) {
            this.receiver = receiver;
            this.bars = bars;
        }
    }

    private static final class RenkoConfiguration {
        private final Number boxSize;
        private final int reversalAmount;

        private RenkoConfiguration(Number boxSize, int reversalAmount) {
            this.boxSize = boxSize;
            this.reversalAmount = reversalAmount;
        }
    }
}
