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

    private static void verify(Number boxSize, int reversalAmount, List<Bar> sourceBars) {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(boxSize, reversalAmount);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        FollowUpInput followUp = generateFollowUp(boxSize, reversalAmount, sourceBars);
        List<Bar> followUpOutput = followUp.aggregator.aggregate(followUp.bars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static FollowUpInput generateFollowUp(Number boxSize, int reversalAmount, List<Bar> sourceBars) {
        List<Bar> scaledBars = new ArrayList<>(sourceBars.size());
        for (Bar source : sourceBars) {
            scaledBars.add(new BaseBar(
                    source.getTimePeriod(),
                    source.getBeginTime(),
                    source.getEndTime(),
                    scale(source.getOpenPrice()),
                    scale(source.getHighPrice()),
                    scale(source.getLowPrice()),
                    scale(source.getClosePrice()),
                    source.getVolume(),
                    scale(source.getAmount()),
                    source.getTrades()));
        }
        return new FollowUpInput(
                new RenkoBarAggregator(boxSize.doubleValue() * 2.0, reversalAmount),
                scaledBars);
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(double... closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int index = 0; index < closes.length; index++) {
            volumes[index] = 1.0;
            amounts[index] = 2.0;
            trades[index] = 3L;
        }
        return barsWithMetadata(closes, volumes, amounts, trades);
    }

    private static List<Bar> barsWithMetadata(double[] closes, Double[] volumes, Double[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        Duration period = Duration.ofMinutes(1);
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        for (int index = 0; index < closes.length; index++) {
            Num close = num(closes[index]);
            Instant begin = start.plus(period.multipliedBy(index));
            Instant end = begin.plus(period);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    num(closes[index] + 1.0),
                    num(closes[index] - 1.0),
                    close,
                    volumes[index] == null ? null : num(volumes[index]),
                    amounts[index] == null ? null : num(amounts[index]),
                    trades[index]));
        }
        return result;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private static final class FollowUpInput {
        private final RenkoBarAggregator aggregator;
        private final List<Bar> bars;

        private FollowUpInput(RenkoBarAggregator aggregator, List<Bar> bars) {
            this.aggregator = aggregator;
            this.bars = bars;
        }
    }
}
