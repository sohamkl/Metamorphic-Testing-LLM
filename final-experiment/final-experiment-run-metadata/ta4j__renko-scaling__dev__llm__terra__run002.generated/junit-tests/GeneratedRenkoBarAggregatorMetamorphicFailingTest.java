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

    private static final NumFactory NUM_FACTORY = DecimalNumFactory.getInstance();
    private static final Duration PERIOD = Duration.ofSeconds(60);
    private static final Instant START = Instant.parse("2024-01-01T00:00:00Z");

    private static List<Bar> bars(double[] closes, Double[] volumes, Double[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Num close = NUM_FACTORY.numOf(closes[i]);
            Num volume = volumes == null ? NUM_FACTORY.numOf(10.0) : numOrNull(volumes[i]);
            Num amount = amounts == null ? NUM_FACTORY.numOf(100.0) : numOrNull(amounts[i]);
            long tradeCount = trades == null ? 1L : trades[i];
            Instant begin = START.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            result.add(new BaseBar(PERIOD, begin, end, close, close, close, close, volume, amount, tradeCount));
        }
        return result;
    }

    private static Num numOrNull(Double value) {
        return value == null ? null : NUM_FACTORY.numOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> castBars(Object value) {
        return (List<Bar>) value;
    }
}
