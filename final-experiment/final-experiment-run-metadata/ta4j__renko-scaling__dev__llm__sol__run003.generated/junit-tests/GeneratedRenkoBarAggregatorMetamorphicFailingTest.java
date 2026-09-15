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

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant START = Instant.parse("2024-01-01T00:00:00Z");
    private static final NumFactory NUM_FACTORY = DecimalNumFactory.getInstance();

    private static List<Bar> bars(double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            Num close = num(closes[index]);
            result.add(createBar(index, close, close, close, close, num(index + 1), num((index + 1) * 10),
                    index + 1L));
        }
        return result;
    }

    private static List<Bar> barsWithMetrics(double[] closes, String[] volumes, String[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            Num close = num(closes[index]);
            Num volume = volumes[index] == null ? null : NUM_FACTORY.numOf(volumes[index]);
            Num amount = amounts[index] == null ? null : NUM_FACTORY.numOf(amounts[index]);
            result.add(createBar(index, close, close, close, close, volume, amount, trades[index]));
        }
        return result;
    }

    private static List<Bar> barsWithOhlc(double[] opens, double[] highs, double[] lows, double[] closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            result.add(createBar(index, num(opens[index]), num(highs[index]), num(lows[index]), num(closes[index]),
                    num(index + 1), num((index + 1) * 10), index + 1L));
        }
        return result;
    }

    private static Bar createBar(int index, Num open, Num high, Num low, Num close, Num volume, Num amount,
            long trades) {
        Instant beginTime = START.plus(PERIOD.multipliedBy(index));
        Instant endTime = beginTime.plus(PERIOD);
        return new BaseBar(PERIOD, beginTime, endTime, open, high, low, close, volume, amount, trades);
    }

    private static Num num(double value) {
        return NUM_FACTORY.numOf(Double.toString(value));
    }

    private static Num num(long value) {
        return NUM_FACTORY.numOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> asBarList(Object value) {
        return (List<Bar>) value;
    }
}
