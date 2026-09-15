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

    private static void exercise(RenkoBarAggregator aggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> series(double... closes) {
        List<Bar> bars = new ArrayList<>();
        for (int index = 0; index < closes.length; index++) {
            bars.add(bar(index, closes[index], (double) (index + 1), (double) ((index + 1) * 10), index + 1));
        }
        return bars;
    }

    private static Bar bar(int index, double close, Double volume, Double amount, long trades) {
        Duration period = Duration.ofMinutes(1);
        Instant beginTime = Instant.EPOCH.plus(period.multipliedBy(index));
        Instant endTime = beginTime.plus(period);
        Num price = DecimalNum.valueOf(close);
        Num barVolume = volume == null ? null : DecimalNum.valueOf(volume);
        Num barAmount = amount == null ? null : DecimalNum.valueOf(amount);
        return new BaseBar(
                period,
                beginTime,
                endTime,
                price,
                price,
                price,
                price,
                barVolume,
                barAmount,
                trades);
    }
}
