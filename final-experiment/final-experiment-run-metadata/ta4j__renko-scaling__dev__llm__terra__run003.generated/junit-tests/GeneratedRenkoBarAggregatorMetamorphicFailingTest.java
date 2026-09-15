import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNumFactory;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.NumFactory;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Instant START = Instant.parse("2024-01-01T00:00:00Z");
    private static final NumFactory NUM_FACTORY = DecimalNumFactory.getInstance();

    private static void run(RenkoBarAggregator aggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> bars(Duration period, double[] closes, Double[] volumes, Double[] amounts,
            long[] trades, boolean distinctOhlc) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            Instant begin = START.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            double close = closes[i];
            Num open = n(distinctOhlc ? close - 2.0 : close);
            Num high = n(distinctOhlc ? close + 2.0 : close);
            Num low = n(distinctOhlc ? close - 3.0 : close);
            result.add(new BaseBar(period, begin, end, open, high, low, n(close),
                    volumes[i] == null ? null : n(volumes[i]),
                    amounts[i] == null ? null : n(amounts[i]), trades[i]));
        }
        return result;
    }

    private static Num n(double value) {
        return NUM_FACTORY.numOf(value);
    }
}
