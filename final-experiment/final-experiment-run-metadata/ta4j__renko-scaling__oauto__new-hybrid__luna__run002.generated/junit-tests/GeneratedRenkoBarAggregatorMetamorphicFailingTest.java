import java.lang.reflect.Field;
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

    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private static void run(RenkoBarAggregator aggregator, List<Bar> source) {
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(RenkoBarAggregator aggregator, List<Bar> source) {
        Number boxSize = readBoxSize(aggregator);
        int reversalAmount = readReversalAmount(aggregator);
        List<Bar> scaledBars = new ArrayList<>(source.size());

        for (Bar bar : source) {
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
                scaledBars
        };
    }

    private static Number readBoxSize(RenkoBarAggregator aggregator) {
        try {
            Field field = RenkoBarAggregator.class.getDeclaredField("boxSize");
            field.setAccessible(true);
            return (Number) field.get(aggregator);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException(failure);
        }
    }

    private static int readReversalAmount(RenkoBarAggregator aggregator) {
        try {
            Field field = RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            field.setAccessible(true);
            return field.getInt(aggregator);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException(failure);
        }
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(double... closes) {
        Num[] volumes = new Num[closes.length];
        Num[] amounts = new Num[closes.length];
        long[] trades = new long[closes.length];

        for (int i = 0; i < closes.length; i++) {
            volumes[i] = n(10 + i);
            amounts[i] = n(100 + i);
            trades[i] = i + 1;
        }

        return barsWithMetadata(closes, volumes, amounts, trades);
    }

    private static List<Bar> barsWithMetadata(
            double[] closes, Num[] volumes, Num[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);

        for (int i = 0; i < closes.length; i++) {
            Instant begin = START.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            Num close = n(closes[i]);

            result.add(new BaseBar(
                    PERIOD,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    volumes[i],
                    amounts[i],
                    trades[i]));
        }

        return result;
    }

    private static Num n(double value) {
        return org.ta4j.core.num.DecimalNum.valueOf(value);
    }
}
