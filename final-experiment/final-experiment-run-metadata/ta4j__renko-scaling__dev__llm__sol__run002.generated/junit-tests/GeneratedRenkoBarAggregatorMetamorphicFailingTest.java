import java.time.Duration;
import java.time.Instant;
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

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant START = Instant.parse("2024-01-01T00:00:00Z");
    private static final NumFactory NUM_FACTORY = DecimalNumFactory.getInstance();

    private static Bar bar(int index, double close, Double volume, Double amount, long trades) {
        Num closeValue = num(close);
        return new BaseBar(
                PERIOD,
                begin(index),
                end(index),
                closeValue,
                closeValue,
                closeValue,
                closeValue,
                nullableNum(volume),
                nullableNum(amount),
                trades);
    }

    private static Bar barWithOhlc(int index, double open, double high, double low, double close,
            Double volume, Double amount, long trades) {
        return new BaseBar(
                PERIOD,
                begin(index),
                end(index),
                num(open),
                num(high),
                num(low),
                num(close),
                nullableNum(volume),
                nullableNum(amount),
                trades);
    }

    private static Instant begin(int index) {
        return START.plus(PERIOD.multipliedBy(index));
    }

    private static Instant end(int index) {
        return begin(index).plus(PERIOD);
    }

    private static Num num(double value) {
        return NUM_FACTORY.numOf(value);
    }

    private static Num nullableNum(Double value) {
        return value == null ? null : NUM_FACTORY.numOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> barList(Object value) {
        return (List<Bar>) value;
    }
}
