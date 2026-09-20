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

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private static Bar bar(int index, double close, double volume, double amount, long trades) {
        return barWithNullableFields(index, close, volume, amount, trades);
    }

    private static Bar barWithNullableFields(int index, double close, Double volume, Double amount, long trades) {
        Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        Num price = DecimalNum.valueOf(close);
        Num volumeNum = volume == null ? null : DecimalNum.valueOf(volume);
        Num amountNum = amount == null ? null : DecimalNum.valueOf(amount);
        return new BaseBar(PERIOD, begin, end, price, price, price, price, volumeNum, amountNum, trades);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        if (sourceOutput.size() != followUpOutput.size()) {
            throw new AssertionError("Renko brick counts differ: source=" + sourceOutput.size()
                    + ", follow-up=" + followUpOutput.size());
        }

        for (int index = 0; index < sourceOutput.size(); index++) {
            Bar source = sourceOutput.get(index);
            Bar followUp = followUpOutput.get(index);

            if (!Objects.equals(source.getBeginTime(), followUp.getBeginTime())
                    || !Objects.equals(source.getEndTime(), followUp.getEndTime())
                    || !Objects.equals(source.getTimePeriod(), followUp.getTimePeriod())
                    || !Objects.equals(source.getVolume(), followUp.getVolume())
                    || source.getTrades() != followUp.getTrades()) {
                throw new AssertionError("Non-price brick properties differ at index " + index);
            }

            int sourceDirection = source.getClosePrice().isGreaterThan(source.getOpenPrice()) ? 1
                    : source.getClosePrice().isLessThan(source.getOpenPrice()) ? -1 : 0;
            int followUpDirection = followUp.getClosePrice().isGreaterThan(followUp.getOpenPrice()) ? 1
                    : followUp.getClosePrice().isLessThan(followUp.getOpenPrice()) ? -1 : 0;
            if (sourceDirection != followUpDirection) {
                throw new AssertionError("Brick directions differ at index " + index);
            }

            assertScaledValue(source.getOpenPrice(), followUp.getOpenPrice(), "open", index);
            assertScaledValue(source.getHighPrice(), followUp.getHighPrice(), "high", index);
            assertScaledValue(source.getLowPrice(), followUp.getLowPrice(), "low", index);
            assertScaledValue(source.getClosePrice(), followUp.getClosePrice(), "close", index);
            assertScaledValue(source.getAmount(), followUp.getAmount(), "amount", index);
        }
    }

    private static void assertScaledValue(Num source, Num followUp, String field, int index) {
        if (source == null || followUp == null) {
            if (source != followUp) {
                throw new AssertionError("Null mismatch for " + field + " at index " + index);
            }
            return;
        }

        Num expected = source.multipliedBy(source.getNumFactory().numOf(2.0));
        if (!followUp.isEqual(expected)) {
            throw new AssertionError("Scaled " + field + " differs at index " + index);
        }
    }
}
