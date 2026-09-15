import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Duration PERIOD = Duration.ofMillis(100);
    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");
    private static final BigDecimal SCALE = new BigDecimal("2");
    private static final Num NUM_SCALE = n("2");

    private static void assertRelation(Number boxSize, int reversalAmount,
            List<Bar> source) {
        assertRelation(new RenkoBarAggregator(boxSize, reversalAmount),
                boxSize, reversalAmount, source);
    }

    private static void assertRelation(RenkoBarAggregator sourceAggregator,
            Number boxSize, int reversalAmount, List<Bar> source) {
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Number scaledBoxSize = new BigDecimal(boxSize.toString()).multiply(SCALE);
        RenkoBarAggregator followUpAggregator =
                new RenkoBarAggregator(scaledBoxSize, reversalAmount);
        List<Bar> followUpOutput =
                followUpAggregator.aggregate(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput,
            List<Bar> followUpOutput) {
        Assertions.assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);

            Assertions.assertEquals(source.getBeginTime(), followUp.getBeginTime());
            Assertions.assertEquals(source.getEndTime(), followUp.getEndTime());
            Assertions.assertEquals(source.getTimePeriod(), followUp.getTimePeriod());
            Assertions.assertEquals(source.getVolume(), followUp.getVolume());
            Assertions.assertEquals(source.getTrades(), followUp.getTrades());

            Assertions.assertEquals(source.getOpenPrice().multipliedBy(NUM_SCALE),
                    followUp.getOpenPrice());
            Assertions.assertEquals(source.getHighPrice().multipliedBy(NUM_SCALE),
                    followUp.getHighPrice());
            Assertions.assertEquals(source.getLowPrice().multipliedBy(NUM_SCALE),
                    followUp.getLowPrice());
            Assertions.assertEquals(source.getClosePrice().multipliedBy(NUM_SCALE),
                    followUp.getClosePrice());
            Assertions.assertEquals(source.getAmount().multipliedBy(NUM_SCALE),
                    followUp.getAmount());
            Assertions.assertEquals(direction(source), direction(followUp));
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

    private static List<Bar> generateFollowUp(List<Bar> source) {
        List<Bar> result = new ArrayList<>();
        for (Bar bar : source) {
            result.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    bar.getOpenPrice().multipliedBy(NUM_SCALE),
                    bar.getHighPrice().multipliedBy(NUM_SCALE),
                    bar.getLowPrice().multipliedBy(NUM_SCALE),
                    bar.getClosePrice().multipliedBy(NUM_SCALE),
                    bar.getVolume(),
                    bar.getAmount() == null
                            ? null
                            : bar.getAmount().multipliedBy(NUM_SCALE),
                    bar.getTrades()));
        }
        return result;
    }

    private static List<Bar> bars(String[] closes) {
        return bars(closes, n("5"), n("10"), 2);
    }

    private static List<Bar> bars(String[] closes, Num volume, Num amount,
            long trades) {
        List<Bar> result = new ArrayList<>();
        Num previous = n(closes[0]);

        for (int i = 0; i < closes.length; i++) {
            Num close = n(closes[i]);
            Num open = i == 0 ? close : previous;
            Num high = open.max(close).plus(n("0.1"));
            Num low = open.min(close).minus(n("0.1"));

            result.add(new BaseBar(
                    PERIOD,
                    START.plus(PERIOD.multipliedBy(i)),
                    START.plus(PERIOD.multipliedBy(i + 1)),
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

    private static List<Bar> decimalBars(String... closes) {
        List<Bar> result = new ArrayList<>();
        Num previous = n(closes[0]);

        for (int i = 0; i < closes.length; i++) {
            Num close = n(closes[i]);
            Num open = i == 0 ? close : previous;

            result.add(new BaseBar(
                    PERIOD,
                    START.plus(PERIOD.multipliedBy(i)),
                    START.plus(PERIOD.multipliedBy(i + 1)),
                    open,
                    open.max(close),
                    open.min(close),
                    close,
                    n("3.25"),
                    n("7.50"),
                    4));
            previous = close;
        }
        return result;
    }

    private static Num n(String value) {
        return DecimalNum.valueOf(new BigDecimal(value));
    }
}
