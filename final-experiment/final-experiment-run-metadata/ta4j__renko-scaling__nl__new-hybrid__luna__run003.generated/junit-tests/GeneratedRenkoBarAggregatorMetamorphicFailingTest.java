import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Duration PERIOD = Duration.ofSeconds(1);
    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Num TWO = n("2");

    private static void assertMetamorphicRelationFor(RenkoBarAggregator sourceReceiver,
            List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);
        List<Bar> followUpOutput = followUpReceiver(sourceReceiver, sourceBars)
                .aggregate(generateFollowUp(sourceBars));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static RenkoBarAggregator followUpReceiver(RenkoBarAggregator source,
            List<Bar> ignored) {
        return new RenkoBarAggregator(
                sourceBoxSize(source).multiply(BigDecimal.valueOf(2)),
                sourceReversalAmount(source));
    }

    private static BigDecimal sourceBoxSize(RenkoBarAggregator source) {
        try {
            var field = RenkoBarAggregator.class.getDeclaredField("boxSize");
            field.setAccessible(true);
            return new BigDecimal(field.get(source).toString());
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError(exception);
        }
    }

    private static int sourceReversalAmount(RenkoBarAggregator source) {
        try {
            var field = RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            field.setAccessible(true);
            return field.getInt(source);
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError(exception);
        }
    }

    private static List<Bar> generateFollowUp(List<Bar> source) {
        List<Bar> result = new ArrayList<>();
        for (Bar bar : source) {
            result.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    scale(bar.getOpenPrice()),
                    scale(bar.getHighPrice()),
                    scale(bar.getLowPrice()),
                    scale(bar.getClosePrice()),
                    bar.getVolume(),
                    scaleNullable(bar.getAmount()),
                    bar.getTrades()));
        }
        return result;
    }

    private static void assertMetamorphicRelation(List<Bar> source, List<Bar> followUp) {
        assertEquals(source.size(), followUp.size());
        for (int i = 0; i < source.size(); i++) {
            Bar expected = source.get(i);
            Bar actual = followUp.get(i);
            assertEquals(expected.getBeginTime(), actual.getBeginTime());
            assertEquals(expected.getEndTime(), actual.getEndTime());
            assertEquals(expected.getTimePeriod(), actual.getTimePeriod());
            assertEquals(expected.getVolume(), actual.getVolume());
            assertEquals(expected.getTrades(), actual.getTrades());
            assertEquals(scale(expected.getOpenPrice()), actual.getOpenPrice());
            assertEquals(scale(expected.getHighPrice()), actual.getHighPrice());
            assertEquals(scale(expected.getLowPrice()), actual.getLowPrice());
            assertEquals(scale(expected.getClosePrice()), actual.getClosePrice());
            if (expected.getAmount() == null) {
                assertEquals(null, actual.getAmount());
            } else {
                assertEquals(scale(expected.getAmount()), actual.getAmount());
            }
            assertNotNull(expected.getOpenPrice());
            assertEquals(direction(expected), direction(actual));
        }
    }

    private static String direction(Bar bar) {
        int comparison = bar.getClosePrice().compareTo(bar.getOpenPrice());
        return comparison > 0 ? "UP" : comparison < 0 ? "DOWN" : "NONE";
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(TWO);
    }

    private static Num scaleNullable(Num value) {
        return value == null ? null : scale(value);
    }

    private static Num n(String value) {
        return DecimalNum.valueOf(new BigDecimal(value));
    }

    private static List<Bar> bars(String[] closes, int metadataMode, int amountMode) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            Num close = n(closes[i]);
            Num open = close.plus(n(i % 2 == 0 ? "0.25" : "-0.25"));
            Num high = close.plus(n("0.5"));
            Num low = close.minus(n("0.5"));

            Num volume = metadataMode == 2 || metadataMode == 3 ? null : n(String.valueOf(i + 1));
            Num amount = amountMode == 2 || metadataMode == 3
                    ? null
                    : n(String.valueOf((i + 1) * 10));

            result.add(new BaseBar(
                    PERIOD,
                    BASE.plus(PERIOD.multipliedBy(i)),
                    BASE.plus(PERIOD.multipliedBy(i + 1)),
                    open,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    i == 0 ? 0 : i + 1));
        }
        return result;
    }
}
