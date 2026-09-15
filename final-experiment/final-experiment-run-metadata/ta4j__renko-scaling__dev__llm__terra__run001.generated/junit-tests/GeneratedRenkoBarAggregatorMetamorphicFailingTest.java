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

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private static void verify(RenkoBarAggregator aggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];

        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> sourceBars(double... closes) {
        return sourceBarsWithNullMetadata(closes, new boolean[closes.length], new boolean[closes.length]);
    }

    private static List<Bar> sourceBarsWithNullMetadata(
            double[] closes,
            boolean[] nullVolumes,
            boolean[] nullAmounts) {
        List<Bar> bars = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            Num close = DecimalNum.valueOf(closes[index]);
            Num volume = nullVolumes[index] ? null : DecimalNum.valueOf(10 + index);
            Num amount = nullAmounts[index] ? null : DecimalNum.valueOf(100 + index);
            Instant beginTime = BASE_TIME.plus(PERIOD.multipliedBy(index));
            Instant endTime = beginTime.plus(PERIOD);

            bars.add(new BaseBar(
                    PERIOD,
                    beginTime,
                    endTime,
                    close,
                    close,
                    close,
                    close,
                    volume,
                    amount,
                    index + 1L));
        }
        return bars;
    }
}
