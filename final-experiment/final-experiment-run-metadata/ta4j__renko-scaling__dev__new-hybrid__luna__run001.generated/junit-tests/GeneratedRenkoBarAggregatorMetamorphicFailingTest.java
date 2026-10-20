import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static Bar bar(double close) {
        return bar(close, close - 0.25, close + 0.25, 10.0, 100.0, 1L);
    }

    private static Bar bar(double close, Double volume, Double amount, long trades) {
        return bar(close, close - 0.25, close + 0.25, volume, amount, trades);
    }

    private static Bar bar(double close, double open, double high, Double volume, Double amount, long trades) {
        return new BaseBar(
                PERIOD,
                BASE.plusSeconds(indexFor(close)),
                BASE.plusSeconds(indexFor(close) + 1),
                DecimalNum.valueOf(open),
                DecimalNum.valueOf(high),
                DecimalNum.valueOf(Math.min(open, close) - 0.1),
                DecimalNum.valueOf(close),
                volume == null ? null : DecimalNum.valueOf(volume),
                amount == null ? null : DecimalNum.valueOf(amount),
                trades);
    }

    private static Bar indexedBar(int index, double close, Double volume, Double amount, long trades) {
        double open = close - 0.25;
        return new BaseBar(
                PERIOD,
                BASE.plusSeconds(index),
                BASE.plusSeconds(index + 1),
                DecimalNum.valueOf(open),
                DecimalNum.valueOf(Math.max(open, close) + 0.25),
                DecimalNum.valueOf(Math.min(open, close) - 0.25),
                DecimalNum.valueOf(close),
                volume == null ? null : DecimalNum.valueOf(volume),
                amount == null ? null : DecimalNum.valueOf(amount),
                trades);
    }

    private static int indexFor(double ignored) {
        return 0;
    }

    private static List<Bar> bars(double... closes) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            result.add(indexedBar(i, closes[i], 10.0, 100.0, 1L));
        }
        return result;
    }

    private static List<Bar> barsWithMetadata(double[] closes, Double[] volumes, Double[] amounts,
            long[] trades) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            result.add(indexedBar(i, closes[i], volumes[i], amounts[i], trades[i]));
        }
        return result;
    }

    @Test
    void NULL_SOURCE_BAR_ELEMENT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = new ArrayList<>();
        source.add(null);

        Assertions.assertThrows(NullPointerException.class, () -> aggregator.aggregate(source));
        Assertions.assertThrows(
                NullPointerException.class,
                () -> RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source));
    }
}
