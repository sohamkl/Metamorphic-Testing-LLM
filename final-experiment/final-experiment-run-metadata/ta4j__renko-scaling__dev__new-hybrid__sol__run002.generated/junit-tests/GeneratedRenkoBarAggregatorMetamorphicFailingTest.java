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

    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration MINUTE = Duration.ofMinutes(1);
    private static final Duration NANOSECOND = Duration.ofNanos(1);
    private static final Duration MULTI_DAY = Duration.ofDays(3);

    private static void verify(RenkoBarAggregator sourceAggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        Object[] followUpInput =
                RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUpInput[0];

        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUpInput[1];

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> bars(Duration period, double... closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];

        for (int i = 0; i < closes.length; i++) {
            volumes[i] = (double) (i + 1);
            amounts[i] = (double) ((i + 1) * 2);
            trades[i] = i + 1L;
        }

        return barsWithData(period, closes, volumes, amounts, trades);
    }

    private static List<Bar> barsWithData(
            Duration period,
            double[] closes,
            Double[] volumes,
            Double[] amounts,
            long[] trades) {

        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = START.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = num(closes[i]);
            Num volume = volumes[i] == null ? null : num(volumes[i]);
            Num amount = amounts[i] == null ? null : num(amounts[i]);

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
                    trades[i]));
        }
        return result;
    }

    private static List<Bar> barsWithNullNonClosePrices(Duration period, double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = START.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = num(closes[i]);

            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    null,
                    null,
                    null,
                    close,
                    num(i + 1),
                    num((i + 1) * 2),
                    i + 1L));
        }
        return result;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }
}
