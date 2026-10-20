import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Duration PERIOD = Duration.ofMillis(100);
    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private static List<Bar> bars(double... closes) {
        return bars(num(1), num(1), 1L, closes);
    }

    private static List<Bar> bars(Num volume, Num amount, long trades, double... closes) {
        Bar[] result = new Bar[closes.length];
        for (int i = 0; i < closes.length; i++) {
            Num close = num(closes[i]);
            Instant begin = START.plusMillis(i * 100L);
            Instant end = START.plusMillis((i + 1L) * 100L);
            result[i] = new BaseBar(PERIOD, begin, end, close, close, close, close, volume, amount, trades);
        }
        return List.of(result);
    }

    private static List<Bar> barsWithMetadata(double[] closes, Num[] volumes, Num[] amounts, long[] trades) {
        Bar[] result = new Bar[closes.length];
        for (int i = 0; i < closes.length; i++) {
            Num close = num(closes[i]);
            Instant begin = START.plusMillis(i * 100L);
            Instant end = START.plusMillis((i + 1L) * 100L);
            result[i] = new BaseBar(PERIOD, begin, end, close, close, close, close,
                    volumes[i], amounts[i], trades[i]);
        }
        return List.of(result);
    }

    @SuppressWarnings("unchecked")
    private static void verify(RenkoBarAggregator aggregator, List<Bar> source) {
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
