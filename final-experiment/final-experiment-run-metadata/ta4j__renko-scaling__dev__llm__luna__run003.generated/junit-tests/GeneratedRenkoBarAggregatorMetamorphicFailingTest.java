import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DoubleNum;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Duration PERIOD = Duration.ofHours(1);
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static DoubleNum num(double value) {
        return DoubleNum.valueOf(value);
    }

    private static Bar bar(int index, double close, Double volume, Double amount, long trades) {
        Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        DoubleNum price = num(close);
        return new BaseBar(
                PERIOD,
                begin,
                end,
                price,
                price,
                price,
                price,
                volume == null ? null : num(volume),
                amount == null ? null : num(amount),
                trades);
    }

}
