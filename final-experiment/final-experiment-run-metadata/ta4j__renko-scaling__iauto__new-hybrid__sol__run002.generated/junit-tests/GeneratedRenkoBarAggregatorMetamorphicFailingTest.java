import java.time.Duration;
import java.time.Instant;
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
    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");

    private static Num n(double value) {
        return DecimalNum.valueOf(value);
    }

    private static Bar bar(int index, double close) {
        return bar(index, close, close, close, close, 1.0 + index, 10.0 + index, index + 1L);
    }

    private static Bar bar(int index, double close, Double volume, Double amount, long trades) {
        return bar(index, close, close, close, close, volume, amount, trades);
    }

    private static Bar bar(int index, double open, double high, double low, double close,
            Double volume, Double amount, long trades) {
        Instant begin = BASE.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        return new BaseBar(PERIOD, begin, end, n(open), n(high), n(low), n(close),
                volume == null ? null : n(volume),
                amount == null ? null : n(amount),
                trades);
    }

    @SafeVarargs
    private static List<Bar> bars(Bar... values) {
        return List.of(values);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        if (sourceOutput.size() != followUpOutput.size()) {
            throw new AssertionError("Renko brick counts differ: source=" + sourceOutput.size()
                    + ", follow-up=" + followUpOutput.size());
        }

        for (int index = 0; index < sourceOutput.size(); index++) {
            Bar source = sourceOutput.get(index);
            Bar followUp = followUpOutput.get(index);

            if (!Objects.equals(source.getTimePeriod(), followUp.getTimePeriod())) {
                throw new AssertionError("Time periods differ at brick " + index);
            }
            if (!Objects.equals(source.getBeginTime(), followUp.getBeginTime())) {
                throw new AssertionError("Begin times differ at brick " + index);
            }
            if (!Objects.equals(source.getEndTime(), followUp.getEndTime())) {
                throw new AssertionError("End times differ at brick " + index);
            }
            if (!Objects.equals(source.getVolume(), followUp.getVolume())) {
                throw new AssertionError("Volumes differ at brick " + index);
            }
            if (source.getTrades() != followUp.getTrades()) {
                throw new AssertionError("Trade counts differ at brick " + index);
            }

            int sourceDirection = source.getClosePrice().isGreaterThan(source.getOpenPrice()) ? 1
                    : source.getClosePrice().isLessThan(source.getOpenPrice()) ? -1 : 0;
            int followUpDirection = followUp.getClosePrice().isGreaterThan(followUp.getOpenPrice()) ? 1
                    : followUp.getClosePrice().isLessThan(followUp.getOpenPrice()) ? -1 : 0;
            if (sourceDirection != followUpDirection) {
                throw new AssertionError("Directions differ at brick " + index);
            }

            Num factor = source.getOpenPrice().getNumFactory().numOf(2.0);
            if (!followUp.getOpenPrice().isEqual(source.getOpenPrice().multipliedBy(factor))) {
                throw new AssertionError("Open price is not scaled at brick " + index);
            }
            if (!followUp.getHighPrice().isEqual(source.getHighPrice().multipliedBy(factor))) {
                throw new AssertionError("High price is not scaled at brick " + index);
            }
            if (!followUp.getLowPrice().isEqual(source.getLowPrice().multipliedBy(factor))) {
                throw new AssertionError("Low price is not scaled at brick " + index);
            }
            if (!followUp.getClosePrice().isEqual(source.getClosePrice().multipliedBy(factor))) {
                throw new AssertionError("Close price is not scaled at brick " + index);
            }

            if (source.getAmount() == null || followUp.getAmount() == null) {
                if (source.getAmount() != followUp.getAmount()) {
                    throw new AssertionError("Amount nullability differs at brick " + index);
                }
            } else if (!followUp.getAmount().isEqual(source.getAmount().multipliedBy(factor))) {
                throw new AssertionError("Amount is not scaled at brick " + index);
            }
        }
    }
}
