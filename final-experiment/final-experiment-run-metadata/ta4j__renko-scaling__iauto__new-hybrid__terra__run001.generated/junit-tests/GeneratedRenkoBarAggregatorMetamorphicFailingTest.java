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

    private static final Duration PERIOD = Duration.ofSeconds(60);
    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");

    private static List<Bar> bars(double... closes) {
        Double[] metrics = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            metrics[i] = 1.0;
            trades[i] = 1L;
        }
        return barsWithMetadata(closes, metrics, metrics, trades);
    }

    private static List<Bar> barsWithMetadata(double[] closes, Double[] volumes, Double[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            Instant begin = START.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            Num close = num(closes[i]);
            Num volume = volumes[i] == null ? null : num(volumes[i]);
            Num amount = amounts[i] == null ? null : num(amounts[i]);
            result.add(new BaseBar(PERIOD, begin, end, close, close, close, close, volume, amount, trades[i]));
        }
        return result;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> castBars(Object value) {
        return (List<Bar>) value;
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        if (sourceOutput.size() != followUpOutput.size()) {
            throw new AssertionError("Source and follow-up produced different brick counts");
        }
        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);

            if (!Objects.equals(source.getTimePeriod(), followUp.getTimePeriod())) {
                throw new AssertionError("Different time period at brick " + i);
            }
            if (!Objects.equals(source.getBeginTime(), followUp.getBeginTime())) {
                throw new AssertionError("Different begin time at brick " + i);
            }
            if (!Objects.equals(source.getEndTime(), followUp.getEndTime())) {
                throw new AssertionError("Different end time at brick " + i);
            }
            if (!Objects.equals(source.getVolume(), followUp.getVolume())) {
                throw new AssertionError("Different volume at brick " + i);
            }
            if (source.getTrades() != followUp.getTrades()) {
                throw new AssertionError("Different trade count at brick " + i);
            }

            int sourceDirection = source.getClosePrice().isGreaterThan(source.getOpenPrice()) ? 1
                    : source.getClosePrice().isLessThan(source.getOpenPrice()) ? -1 : 0;
            int followUpDirection = followUp.getClosePrice().isGreaterThan(followUp.getOpenPrice()) ? 1
                    : followUp.getClosePrice().isLessThan(followUp.getOpenPrice()) ? -1 : 0;
            if (sourceDirection != followUpDirection) {
                throw new AssertionError("Different direction at brick " + i);
            }

            assertScaled(source.getOpenPrice(), followUp.getOpenPrice(), "open", i);
            assertScaled(source.getHighPrice(), followUp.getHighPrice(), "high", i);
            assertScaled(source.getLowPrice(), followUp.getLowPrice(), "low", i);
            assertScaled(source.getClosePrice(), followUp.getClosePrice(), "close", i);
            assertScaled(source.getAmount(), followUp.getAmount(), "amount", i);
        }
    }

    private static void assertScaled(Num source, Num followUp, String field, int index) {
        if (source == null || followUp == null) {
            if (source != followUp) {
                throw new AssertionError("Mismatched null " + field + " at brick " + index);
            }
            return;
        }
        Num expected = source.multipliedBy(source.getNumFactory().numOf(2.0));
        if (!followUp.isEqual(expected)) {
            throw new AssertionError("Follow-up " + field + " is not doubled at brick " + index);
        }
    }
}
