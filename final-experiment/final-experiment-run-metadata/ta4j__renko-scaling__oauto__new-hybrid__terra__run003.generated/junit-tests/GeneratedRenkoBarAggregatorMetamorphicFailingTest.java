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

    private static void exercise(double boxSize, int reversalAmount, List<Bar> sourceBars) {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(boxSize, reversalAmount);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        Object[] followUp = generateFollowUp(boxSize, reversalAmount, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static void exerciseDefault(double boxSize, List<Bar> sourceBars) {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(boxSize);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        Object[] followUp = generateFollowUp(boxSize, 2, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(double boxSize, int reversalAmount, List<Bar> bars) {
        RenkoBarAggregator scaledAggregator = new RenkoBarAggregator(boxSize * 2.0, reversalAmount);
        List<Bar> scaledBars = new ArrayList<>(bars.size());

        for (Bar bar : bars) {
            scaledBars.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    scale(bar.getOpenPrice()),
                    scale(bar.getHighPrice()),
                    scale(bar.getLowPrice()),
                    scale(bar.getClosePrice()),
                    bar.getVolume(),
                    scale(bar.getAmount()),
                    bar.getTrades()));
        }

        return new Object[] { scaledAggregator, scaledBars };
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(double... closes) {
        return createBars(closes, MetricMode.POSITIVE);
    }

    private static List<Bar> nullMetricBars(double... closes) {
        return createBars(closes, MetricMode.NULL);
    }

    private static List<Bar> zeroMetricBars(double... closes) {
        return createBars(closes, MetricMode.ZERO);
    }

    private static List<Bar> nullThenPresentBars(double... closes) {
        List<Bar> result = new ArrayList<>();
        Instant start = Instant.parse("2021-02-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int index = 0; index < closes.length; index++) {
            Num close = DecimalNum.valueOf(closes[index]);
            boolean finalBar = index == closes.length - 1;
            Instant begin = start.plus(period.multipliedBy(index));
            Instant end = begin.plus(period);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    finalBar ? DecimalNum.valueOf(7.0) : null,
                    finalBar ? DecimalNum.valueOf(70.0) : null,
                    finalBar ? 7L : 0L));
        }

        return result;
    }

    private static List<Bar> createBars(double[] closes, MetricMode mode) {
        List<Bar> result = new ArrayList<>();
        Instant start = Instant.parse("2021-01-01T00:00:00Z");
        Duration period = Duration.ofMinutes(1);

        for (int index = 0; index < closes.length; index++) {
            Num close = DecimalNum.valueOf(closes[index]);
            Instant begin = start.plus(period.multipliedBy(index));
            Instant end = begin.plus(period);

            Num volume;
            Num amount;
            long trades;
            if (mode == MetricMode.NULL) {
                volume = null;
                amount = null;
                trades = index + 1L;
            } else if (mode == MetricMode.ZERO) {
                volume = DecimalNum.valueOf(0.0);
                amount = DecimalNum.valueOf(0.0);
                trades = 0L;
            } else {
                volume = DecimalNum.valueOf(index + 1.0);
                amount = DecimalNum.valueOf((index + 1.0) * 10.0);
                trades = index + 1L;
            }

            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    volume,
                    amount,
                    trades));
        }

        return result;
    }

    private enum MetricMode {
        POSITIVE,
        ZERO,
        NULL
    }
}
