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

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static List<Bar> bars(double... closes) {
        return barsWith(0, 0, closes);
    }

    private static List<Bar> barsWith(int metricMode, int nonCloseMode, double... closes) {
        Bar[] result = new Bar[closes.length];
        for (int i = 0; i < closes.length; i++) {
            Num close = DecimalNum.valueOf(closes[i]);
            Num open;
            Num high;
            Num low;

            if (nonCloseMode == 2) {
                open = null;
                high = null;
                low = null;
            } else if (nonCloseMode == 1) {
                open = i % 2 == 0 ? null : DecimalNum.valueOf(closes[i] + 7.25);
                high = DecimalNum.valueOf(closes[i] + 20.5);
                low = i % 3 == 0 ? null : DecimalNum.valueOf(closes[i] - 15.75);
            } else {
                open = DecimalNum.valueOf(closes[i] - 0.25);
                high = DecimalNum.valueOf(closes[i] + 0.5);
                low = DecimalNum.valueOf(closes[i] - 0.5);
            }

            Num volume;
            Num amount;
            long trades;
            if (metricMode == 1) {
                volume = DecimalNum.valueOf(0);
                amount = DecimalNum.valueOf(0);
                trades = 0L;
            } else if (metricMode == 2) {
                volume = null;
                amount = null;
                trades = i + 1L;
            } else if (metricMode == 3) {
                volume = i % 2 == 0 ? null : DecimalNum.valueOf(i + 1);
                amount = i % 2 == 0 ? DecimalNum.valueOf((i + 1) * 10) : null;
                trades = i + 1L;
            } else {
                volume = DecimalNum.valueOf(i + 1);
                amount = DecimalNum.valueOf((i + 1) * 10);
                trades = i + 1L;
            }

            Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            result[i] = new BaseBar(PERIOD, begin, end, open, high, low, close, volume, amount, trades);
        }
        return List.of(result);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> followUpBars(Object[] followUp) {
        return (List<Bar>) followUp[1];
    }

}
