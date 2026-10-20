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
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static void verify(RenkoBarAggregator sourceAggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        Object[] followUp =
                RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = castBars(followUp[1]);

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> bars(double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            result.add(bar(
                    index,
                    close,
                    close - 0.25,
                    close + 0.5,
                    close - 0.5,
                    index + 1.0,
                    (index + 1.0) * 10.0,
                    index + 1L));
        }
        return result;
    }

    private static List<Bar> barsWithMetadata(
            double[] closes,
            Double[] volumes,
            Double[] amounts,
            long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            result.add(bar(
                    index,
                    close,
                    close - 0.25,
                    close + 0.5,
                    close - 0.5,
                    volumes[index],
                    amounts[index],
                    trades[index]));
        }
        return result;
    }

    private static Bar bar(
            int index,
            double close,
            Double open,
            Double high,
            Double low,
            Double volume,
            Double amount,
            long trades) {
        Instant beginTime = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant endTime = beginTime.plus(PERIOD);
        return new BaseBar(
                PERIOD,
                beginTime,
                endTime,
                num(open),
                num(high),
                num(low),
                num(close),
                num(volume),
                num(amount),
                trades);
    }

    private static Num num(Double value) {
        return value == null ? null : DecimalNum.valueOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> castBars(Object value) {
        return (List<Bar>) value;
    }

    @Test
    void MULTI_BRICK_INITIAL_UP_MOVE_variation1() {
        double box = Math.scalb(1.0, 104);
        verify(new RenkoBarAggregator(box), bars(box, 4.0 * box));
    }

    @Test
    void THREE_BOX_REVERSAL_BLOCKED_variation1() {
        double box = Math.scalb(1.0, 102);
        verify(new RenkoBarAggregator(box, 3), bars(2.0 * box, 3.0 * box, box));
    }

    @Test
    void ALL_NULL_AMOUNTS_variation1() {
        double box = Math.scalb(1.0, 101);
        verify(new RenkoBarAggregator(box),
                barsWithMetadata(
                        new double[] { 2.0 * box, box },
                        new Double[] { 3.0, 4.0 },
                        new Double[] { null, null },
                        new long[] { 1L, 2L }));
    }

    @Test
    void LONG_NONEMITTING_GAP_AFTER_BRICK_variation1() {
        double box = Math.scalb(1.0, 100);
        verify(new RenkoBarAggregator(box),
                bars(-2.0 * box, -box, -0.75 * box, -0.5 * box, 0.0));
    }
}
