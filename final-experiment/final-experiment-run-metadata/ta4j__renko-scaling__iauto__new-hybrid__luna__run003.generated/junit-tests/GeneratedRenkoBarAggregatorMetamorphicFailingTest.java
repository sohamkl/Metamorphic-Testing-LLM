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

    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");

    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static List<Bar> bars(double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            result.add(bar(closes[i], 1.0, 10.0, 1));
        }
        return result;
    }

    private static Bar bar(double close, Double volume, Double amount, long trades) {
        Instant begin = START.plus(PERIOD.multipliedBy(currentBarIndex));
        Instant end = begin.plus(PERIOD);
        Num price = number(close);
        Num volumeNum = volume == null ? null : number(volume);
        Num amountNum = amount == null ? null : number(amount);
        Bar result = new BaseBar(PERIOD, begin, end, price, price, price, price, volumeNum, amountNum, trades);
        currentBarIndex++;
        return result;
    }

    private static int currentBarIndex;

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        org.junit.jupiter.api.Assertions.assertEquals(sourceOutput.size(), followUpOutput.size(), "brick count");
        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);
            org.junit.jupiter.api.Assertions.assertEquals(source.getTimePeriod(), followUp.getTimePeriod());
            org.junit.jupiter.api.Assertions.assertEquals(source.getBeginTime(), followUp.getBeginTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getEndTime(), followUp.getEndTime());
            org.junit.jupiter.api.Assertions.assertEquals(source.getVolume(), followUp.getVolume());
            org.junit.jupiter.api.Assertions.assertEquals(source.getTrades(), followUp.getTrades());
            int sourceDirection = direction(source);
            int followUpDirection = direction(followUp);
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
            return;
        }
        Num expected = source.multipliedBy(source.getNumFactory().numOf(2.0));
        org.junit.jupiter.api.Assertions.assertTrue(followUp.isEqual(expected));
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

    private static Num number(double value) {
        return DecimalNum.valueOf(new BigDecimal(Double.toString(value)));
    }

    private static void run(RenkoBarAggregator aggregator, List<Bar> source) {
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
