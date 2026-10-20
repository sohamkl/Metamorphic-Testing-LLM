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
        List<Bar> result = new ArrayList<>();
        for (int index = 0; index < closes.length; index++) {
            result.add(bar(index, closes[index], index + 1.0, (index + 1.0) * 10.0, index + 1L));
        }
        return result;
    }

    private static List<Bar> bars(Bar... sourceBars) {
        return List.of(sourceBars);
    }

    private static Bar bar(int index, double close, Number volume, Number amount, long trades) {
        Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        Num closePrice = num(close);

        return new BaseBar(
                PERIOD,
                begin,
                end,
                closePrice,
                closePrice,
                closePrice,
                closePrice,
                volume == null ? null : num(volume.doubleValue()),
                amount == null ? null : num(amount.doubleValue()),
                trades);
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }
}
