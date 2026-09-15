import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.Num;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static void assertMetamorphicRelationFor(double boxSize, int reversalAmount,
            List<Bar> sourceBars, int metadataMode) {
        RenkoBarAggregator sourceReceiver =
                new RenkoBarAggregator(BigDecimal.valueOf(boxSize), reversalAmount);
        RenkoBarAggregator followUpReceiver =
                new RenkoBarAggregator(BigDecimal.valueOf(boxSize).multiply(BigDecimal.valueOf(2)),
                        reversalAmount);

        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);
        List<Bar> followUpOutput =
                followUpReceiver.aggregate(generateFollowUp(sourceBars));

        assertMetamorphicRelation(sourceOutput, followUpOutput);
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
                    scale(bar.getAmount()),
                    bar.getTrades()));
        }
        return result;
    }

    private static void assertMetamorphicRelation(List<Bar> source, List<Bar> followUp) {
        assertEquals(source.size(), followUp.size());

        for (int i = 0; i < source.size(); i++) {
            Bar left = source.get(i);
            Bar right = followUp.get(i);

            assertEquals(left.getBeginTime(), right.getBeginTime());
            assertEquals(left.getEndTime(), right.getEndTime());
            assertEquals(left.getTimePeriod(), right.getTimePeriod());
            assertEquals(left.getTrades(), right.getTrades());

            assertSameNum(left.getVolume(), right.getVolume());
            assertScaled(left.getOpenPrice(), right.getOpenPrice());
            assertScaled(left.getHighPrice(), right.getHighPrice());
            assertScaled(left.getLowPrice(), right.getLowPrice());
            assertScaled(left.getClosePrice(), right.getClosePrice());
            assertScaled(left.getAmount(), right.getAmount());

            boolean leftUp = left.getClosePrice().isGreaterThan(left.getOpenPrice());
            boolean rightUp = right.getClosePrice().isGreaterThan(right.getOpenPrice());
            assertEquals(leftUp, rightUp);
        }
    }

    private static void assertScaled(Num source, Num followUp) {
        if (source == null) {
            assertEquals(null, followUp);
        } else {
            assertNotNull(followUp);
            assertEquals(0, scale(source).compareTo(followUp));
        }
    }

    private static void assertSameNum(Num source, Num followUp) {
        if (source == null) {
            assertEquals(null, followUp);
        } else {
            assertNotNull(followUp);
            assertEquals(0, source.compareTo(followUp));
        }
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(decimal(2));
    }

    private static List<Bar> bars(double[] closes, int mode) {
        List<Bar> result = new ArrayList<>();
        Num previous = null;

        for (int i = 0; i < closes.length; i++) {
            Num close = decimal(closes[i]);
            Num open = previous == null ? close : previous;
            Num high = open.max(close);
            Num low = open.min(close);

            Num volume = mode == 3 ? decimal(0) : decimal(i + 1);
            Num amount = decimal((i + 1) * 10);
            long trades = mode == 3 ? 0L : i + 1L;

            if (mode == 1 && i == closes.length - 1) {
                volume = null;
            }
            if (mode == 2 && i == 1) {
                amount = null;
            }

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
                    trades));
            previous = close;
        }
        return result;
    }

    private static Num decimal(double value) {
        return org.ta4j.core.num.DecimalNum.valueOf(BigDecimal.valueOf(value));
    }
}
