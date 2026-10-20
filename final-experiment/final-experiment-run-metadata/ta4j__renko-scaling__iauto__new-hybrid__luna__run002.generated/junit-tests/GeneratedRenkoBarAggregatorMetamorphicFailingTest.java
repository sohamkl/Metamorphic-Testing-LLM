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

    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static Bar bar(int index, double close, boolean metadata) {
        Num price = n(close);
        Num volume = metadata ? n(index + 1.0) : n(0.0);
        Num amount = metadata ? n((index + 1.0) * 10.0) : n(0.0);
        return new BaseBar(
                PERIOD,
                BASE.plusSeconds(index),
                BASE.plusSeconds(index + 1L),
                price,
                price,
                price,
                price,
                volume,
                amount,
                metadata ? index + 1L : 0L);
    }

    private static Bar barWithNullOptionalFields(int index, double close) {
        return new BaseBar(
                PERIOD,
                BASE.plusSeconds(index),
                BASE.plusSeconds(index + 1L),
                null,
                null,
                null,
                n(close),
                null,
                null,
                0L);
    }

    private static Num n(double value) {
        return DecimalNum.valueOf(value);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        org.junit.jupiter.api.Assertions.assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int index = 0; index < sourceOutput.size(); index++) {
            Bar source = sourceOutput.get(index);
            Bar followUp = followUpOutput.get(index);

            org.junit.jupiter.api.Assertions.assertEquals(source.getTimePeriod(), followUp.getTimePeriod());
            org.junit.jupiter.api.Assertions.assertEquals(source.getBeginTime(), followUp.getBeginTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getEndTime(), followUp.getEndTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getVolume(), followUp.getVolume());
            org.junit.jupiter.api.Assertions.assertEquals(source.getTrades(), followUp.getTrades());

            int sourceDirection = Integer.compare(
                    source.getClosePrice().compareTo(source.getOpenPrice()), 0);
            int followUpDirection = Integer.compare(
                    followUp.getClosePrice().compareTo(followUp.getOpenPrice()), 0);
            org.junit.jupiter.api.Assertions.assertEquals(sourceDirection, followUpDirection);

            assertScaled(source.getOpenPrice(), followUp.getOpenPrice());
            assertScaled(source.getHighPrice(), followUp.getHighPrice());
            assertScaled(source.getLowPrice(), followUp.getLowPrice());
            assertScaled(source.getClosePrice(), followUp.getClosePrice());
            assertScaled(source.getAmount(), followUp.getAmount());
        }
    }

    private static void assertScaled(Num source, Num followUp) {
        if (source == null || followUp == null) {
            org.junit.jupiter.api.Assertions.assertSame(source, followUp);
        } else {
            Num expected = source.multipliedBy(source.getNumFactory().numOf(2.0));
            org.junit.jupiter.api.Assertions.assertTrue(followUp.isEqual(expected));
        }
    }
}
