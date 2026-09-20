import java.lang.reflect.Field;
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

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");

    private static void exercise(RenkoBarAggregator aggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);
        Object[] followUp = generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(RenkoBarAggregator aggregator, List<Bar> bars) {
        try {
            Field boxSizeField = RenkoBarAggregator.class.getDeclaredField("boxSize");
            Field reversalAmountField = RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            boxSizeField.setAccessible(true);
            reversalAmountField.setAccessible(true);
            Number boxSize = (Number) boxSizeField.get(aggregator);
            int reversalAmount = reversalAmountField.getInt(aggregator);

            List<Bar> scaledBars = new ArrayList<>(bars.size());
            for (Bar bar : bars) {
                scaledBars.add(new BaseBar(
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
            return new Object[] {
                    new RenkoBarAggregator(boxSize.doubleValue() * 2.0, reversalAmount),
                    scaledBars};
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException("Unable to create scaled Renko input", failure);
        }
    }

    private static List<Bar> bars(double[] closes, Double volume, Double amount, long trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            Instant begin = START.plus(PERIOD.multipliedBy(index));
            Instant end = begin.plus(PERIOD);
            Num close = num(closes[index]);
            result.add(new BaseBar(
                    PERIOD,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    volume == null ? null : num(volume),
                    amount == null ? null : num(amount),
                    trades));
        }
        return result;
    }

    private static List<Bar> coherentBars(double[][] ohlc, Double volume, Double amount, long trades) {
        List<Bar> result = new ArrayList<>(ohlc.length);
        for (int index = 0; index < ohlc.length; index++) {
            Instant begin = START.plus(PERIOD.multipliedBy(index));
            Instant end = begin.plus(PERIOD);
            result.add(new BaseBar(
                    PERIOD,
                    begin,
                    end,
                    num(ohlc[index][0]),
                    num(ohlc[index][1]),
                    num(ohlc[index][2]),
                    num(ohlc[index][3]),
                    volume == null ? null : num(volume),
                    amount == null ? null : num(amount),
                    trades));
        }
        return result;
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }
}
