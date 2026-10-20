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

@SuppressWarnings("unchecked")
public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_END = Instant.parse("2024-01-01T00:01:00Z");
    private static final NumFactory NUM_FACTORY = DecimalNumFactory.getInstance();

    private static List<Bar> series(double... closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = (double) (i + 1);
            amounts[i] = (double) ((i + 1) * 10);
            trades[i] = i + 1;
        }
        return metricSeries(closes, volumes, amounts, trades, false);
    }

    private static List<Bar> wildSeries(double... closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = (double) (i + 2);
            amounts[i] = (double) ((i + 2) * 8);
            trades[i] = i + 2;
        }
        return metricSeries(closes, volumes, amounts, trades, true);
    }

    private static List<Bar> metricSeries(double[] closes, Double[] volumes, Double[] amounts, long[] trades,
            boolean wildOhlc) {
        List<Bar> bars = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant end = BASE_END.plus(PERIOD.multipliedBy(i));
            Instant begin = end.minus(PERIOD);
            Num close = NUM_FACTORY.numOf(closes[i]);
            Num open;
            Num high;
            Num low;
            if (wildOhlc) {
                open = NUM_FACTORY.numOf(closes[i] + 8.0 + i);
                high = NUM_FACTORY.numOf(closes[i] + 16.0 + i);
                low = NUM_FACTORY.numOf(closes[i] - 16.0 - i);
            } else {
                open = close;
                high = close;
                low = close;
            }
            Num volume = volumes[i] == null ? null : NUM_FACTORY.numOf(volumes[i]);
            Num amount = amounts[i] == null ? null : NUM_FACTORY.numOf(amounts[i]);
            bars.add(new BaseBar(PERIOD, begin, end, open, high, low, close, volume, amount, trades[i]));
        }
        return bars;
    }
}
