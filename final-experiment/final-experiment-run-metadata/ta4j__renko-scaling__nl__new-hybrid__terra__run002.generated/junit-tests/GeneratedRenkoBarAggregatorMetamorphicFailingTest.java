import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private static final Duration PERIOD = Duration.ofSeconds(60);
    private static final Instant FIRST_END = Instant.parse("2020-01-01T00:01:00Z");

    private void assertMetamorphicRelationFor(Fixture source) {
        Fixture followUp = generateFollowUp(source);
        List<Bar> sourceOutput = source.receiver.aggregate(source.bars);
        List<Bar> followUpOutput = followUp.receiver.aggregate(followUp.bars);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private Fixture generateFollowUp(Fixture source) {
        List<Bar> scaledBars = new ArrayList<>();
        for (Bar bar : source.bars) {
            Num factor = bar.numFactory().numOf(2);
            Num volume = bar.getVolume();
            scaledBars.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    bar.getOpenPrice().multipliedBy(factor),
                    bar.getHighPrice().multipliedBy(factor),
                    bar.getLowPrice().multipliedBy(factor),
                    bar.getClosePrice().multipliedBy(factor),
                    volume,
                    bar.getAmount().multipliedBy(factor),
                    bar.getTrades()));
        }
        Number scaledBoxSize = BigDecimal.valueOf(source.boxSize.doubleValue()).multiply(BigDecimal.valueOf(2));
        RenkoBarAggregator receiver = source.defaultConstructor
                ? new RenkoBarAggregator(scaledBoxSize)
                : new RenkoBarAggregator(scaledBoxSize, source.reversalAmount);
        return new Fixture(receiver, scaledBars, scaledBoxSize, source.reversalAmount, source.defaultConstructor);
    }

    private void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        assertEquals(sourceOutput.size(), followUpOutput.size());
        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);
            Num factor = source.numFactory().numOf(2);

            assertEquals(source.getBeginTime(), followUp.getBeginTime());
            assertEquals(source.getEndTime(), followUp.getEndTime());
            assertEquals(source.getTimePeriod(), followUp.getTimePeriod());
            assertEquals(source.getVolume(), followUp.getVolume());
            assertEquals(source.getTrades(), followUp.getTrades());

            assertEquals(source.getOpenPrice().multipliedBy(factor), followUp.getOpenPrice());
            assertEquals(source.getHighPrice().multipliedBy(factor), followUp.getHighPrice());
            assertEquals(source.getLowPrice().multipliedBy(factor), followUp.getLowPrice());
            assertEquals(source.getClosePrice().multipliedBy(factor), followUp.getClosePrice());
            assertEquals(source.getAmount().multipliedBy(factor), followUp.getAmount());

            assertEquals(source.getClosePrice().isGreaterThan(source.getOpenPrice()),
                    followUp.getClosePrice().isGreaterThan(followUp.getOpenPrice()));
            assertTrue(source.getClosePrice().isGreaterThan(source.getOpenPrice())
                    || source.getClosePrice().isLessThan(source.getOpenPrice()));
        }
    }

    private Fixture fixture(double[] closes, Number boxSize, int reversalAmount, boolean defaultConstructor,
            Double[] volumes, double[] amounts, long[] trades) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            Num open = DecimalNum.valueOf(close - 1);
            Num high = DecimalNum.valueOf(close + 1);
            Num low = DecimalNum.valueOf(close - 2);
            Num closeNum = DecimalNum.valueOf(close);
            Num volume = volumes != null && volumes[i] == null ? null
                    : DecimalNum.valueOf(volumes == null ? i + 1 : volumes[i]);
            Num amount = DecimalNum.valueOf(amounts == null ? (i + 1) * 10 : amounts[i]);
            long tradeCount = trades == null ? i + 1 : trades[i];
            Instant end = FIRST_END.plus(PERIOD.multipliedBy(i));
            bars.add(new BaseBar(PERIOD, end.minus(PERIOD), end, open, high, low, closeNum, volume, amount, tradeCount));
        }
        RenkoBarAggregator receiver = defaultConstructor
                ? new RenkoBarAggregator(boxSize)
                : new RenkoBarAggregator(boxSize, reversalAmount);
        return new Fixture(receiver, bars, boxSize, reversalAmount, defaultConstructor);
    }

    private static final class Fixture {
        private final RenkoBarAggregator receiver;
        private final List<Bar> bars;
        private final Number boxSize;
        private final int reversalAmount;
        private final boolean defaultConstructor;

        private Fixture(RenkoBarAggregator receiver, List<Bar> bars, Number boxSize, int reversalAmount,
                boolean defaultConstructor) {
            this.receiver = receiver;
            this.bars = bars;
            this.boxSize = boxSize;
            this.reversalAmount = reversalAmount;
            this.defaultConstructor = defaultConstructor;
        }
    }
}
