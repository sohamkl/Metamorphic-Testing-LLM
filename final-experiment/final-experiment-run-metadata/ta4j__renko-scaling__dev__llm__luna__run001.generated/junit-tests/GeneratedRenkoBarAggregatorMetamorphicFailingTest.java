import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DoubleNum;
import org.ta4j.core.num.Num;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static Num num(Number value) {
        return value == null ? null : DoubleNum.valueOf(value.doubleValue());
    }

    private static Bar bar(int index, Number close, Number volume, Number amount, long trades) {
        Instant end = BASE_TIME.plusSeconds((index + 1) * 60L);
        Instant begin = end.minus(PERIOD);
        Num price = num(close);
        return new BaseBar(PERIOD, begin, end, price, price, price, price, num(volume), num(amount),
                trades);
    }

    private static List<Bar> bars(Number... closes) {
        Bar[] result = new Bar[closes.length];
        for (int i = 0; i < closes.length; i++) {
            result[i] = bar(i, closes[i], 1.0, 10.0, 1L);
        }
        return List.of(result);
    }

}
