import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = (double) (i + 1);
            amounts[i] = (double) ((i + 1) * 10);
            trades[i] = i + 1L;
        }
        return barsWithMetadata(closes, volumes, amounts, trades);
    }

    private static List<Bar> barsWithMetadata(double[] closes, Double[] volumes, Double[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            Num close = num(closes[i]);
            Num volume = volumes[i] == null ? null : num(volumes[i]);
            Num amount = amounts[i] == null ? null : num(amounts[i]);
            result.add(new BaseBar(PERIOD, begin, end, close, close, close, close, volume, amount, trades[i]));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> castBars(Object value) {
        return (List<Bar>) value;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        org.junit.jupiter.api.Assertions.assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);

            org.junit.jupiter.api.Assertions.assertEquals(direction(source), direction(followUp));
            org.junit.jupiter.api.Assertions.assertEquals(source.getBeginTime(), followUp.getBeginTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getEndTime(), followUp.getEndTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getTimePeriod(), followUp.getTimePeriod());
            org.junit.jupiter.api.Assertions.assertTrue(Objects.equals(source.getVolume(), followUp.getVolume()));
            org.junit.jupiter.api.Assertions.assertEquals(source.getTrades(), followUp.getTrades());

            assertScaledByTwo(source.getOpenPrice(), followUp.getOpenPrice());
            assertScaledByTwo(source.getHighPrice(), followUp.getHighPrice());
            assertScaledByTwo(source.getLowPrice(), followUp.getLowPrice());
            assertScaledByTwo(source.getClosePrice(), followUp.getClosePrice());
            assertScaledByTwo(source.getAmount(), followUp.getAmount());
        }
    }

    private static int direction(Bar bar) {
        if (bar.getClosePrice().isGreaterThan(bar.getOpenPrice())) {
            return 1;
        }
        if (bar.getClosePrice().isLessThan(bar.getOpenPrice())) {
            return -1;
        }
        return 0;
    }

    private static void assertScaledByTwo(Num source, Num followUp) {
        if (source == null || followUp == null) {
            org.junit.jupiter.api.Assertions.assertTrue(source == null && followUp == null);
            return;
        }
        Num expected = source.multipliedBy(source.getNumFactory().numOf(2));
        org.junit.jupiter.api.Assertions.assertTrue(followUp.isEqual(expected));
    }
}
