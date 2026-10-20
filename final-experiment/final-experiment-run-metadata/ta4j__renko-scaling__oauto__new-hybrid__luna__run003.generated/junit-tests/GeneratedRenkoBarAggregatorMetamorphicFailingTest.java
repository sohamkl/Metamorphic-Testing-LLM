import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static Num n(double value) {
        return org.ta4j.core.num.DecimalNum.valueOf(value);
    }

    private static Bar bar(int index, double close) {
        return bar(index, close, n(10), n(close * 10), 2L);
    }

    private static Bar bar(int index, double close, Num volume, Num amount, long trades) {
        Instant end = BASE.plus(PERIOD.multipliedBy(index + 1L));
        Instant begin = end.minus(PERIOD);
        Num closeNum = n(close);
        return new BaseBar(
                PERIOD,
                begin,
                end,
                n(close - 0.2),
                n(close + 0.3),
                n(close - 0.4),
                closeNum,
                volume,
                amount,
                trades);
    }

    private static List<Bar> bars(Bar... values) {
        return List.of(values);
    }

    private static void verify(
            RenkoBarAggregator aggregator,
            List<Bar> source,
            Number boxSize,
            int reversalAmount) {
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = generateFollowUp(boxSize, reversalAmount, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(
            Number boxSize,
            int reversalAmount,
            List<Bar> source) {
        List<Bar> scaled = new ArrayList<>(source.size());
        for (Bar sourceBar : source) {
            Num factor = sourceBar.getClosePrice().getNumFactory().numOf(2.0);
            Num open = sourceBar.getOpenPrice() == null
                    ? null : sourceBar.getOpenPrice().multipliedBy(factor);
            Num high = sourceBar.getHighPrice() == null
                    ? null : sourceBar.getHighPrice().multipliedBy(factor);
            Num low = sourceBar.getLowPrice() == null
                    ? null : sourceBar.getLowPrice().multipliedBy(factor);
            Num close = sourceBar.getClosePrice().multipliedBy(factor);
            Num amount = sourceBar.getAmount() == null
                    ? null : sourceBar.getAmount().multipliedBy(factor);
            scaled.add(new BaseBar(
                    sourceBar.getTimePeriod(),
                    sourceBar.getBeginTime(),
                    sourceBar.getEndTime(),
                    open,
                    high,
                    low,
                    close,
                    sourceBar.getVolume(),
                    amount,
                    sourceBar.getTrades()));
        }
        return new Object[] {
                new RenkoBarAggregator(boxSize.doubleValue() * 2.0, reversalAmount),
                scaled
        };
    }

}
