import java.lang.reflect.Field;
import java.math.MathContext;
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

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration DEFAULT_PERIOD = Duration.ofMinutes(1);

    private static void exercise(
            RenkoBarAggregator sourceReceiver, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);
        FollowUp followUp = generateFollowUp(sourceReceiver, sourceBars);
        List<Bar> followUpOutput = followUp.receiver.aggregate(followUp.bars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(
            RenkoBarAggregator sourceReceiver, List<Bar> sourceBars) {
        try {
            Field boxSizeField =
                    RenkoBarAggregator.class.getDeclaredField("boxSize");
            Field reversalAmountField =
                    RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            boxSizeField.setAccessible(true);
            reversalAmountField.setAccessible(true);

            Number sourceBoxSize = (Number) boxSizeField.get(sourceReceiver);
            int reversalAmount = reversalAmountField.getInt(sourceReceiver);

            RenkoBarAggregator followUpReceiver = new RenkoBarAggregator(
                    sourceBoxSize.doubleValue() * 2.0,
                    reversalAmount);

            List<Bar> followUpBars = new ArrayList<>(sourceBars.size());
            for (Bar sourceBar : sourceBars) {
                followUpBars.add(new BaseBar(
                        sourceBar.getTimePeriod(),
                        sourceBar.getBeginTime(),
                        sourceBar.getEndTime(),
                        scale(sourceBar.getOpenPrice()),
                        scale(sourceBar.getHighPrice()),
                        scale(sourceBar.getLowPrice()),
                        scale(sourceBar.getClosePrice()),
                        sourceBar.getVolume(),
                        scale(sourceBar.getAmount()),
                        sourceBar.getTrades()));
            }
            return new FollowUp(followUpReceiver, followUpBars);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException(
                    "Unable to construct scaled Renko follow-up input",
                    failure);
        }
    }

    private static Num scale(Num value) {
        return value == null
                ? null
                : value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(double... closes) {
        return bars(DEFAULT_PERIOD, closes);
    }

    private static List<Bar> bars(Duration period, double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = DecimalNum.valueOf(closes[i]);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    DecimalNum.valueOf(i + 1),
                    DecimalNum.valueOf((i + 1) * 10),
                    i + 1L));
        }
        return result;
    }

    private static List<Bar> customBars(
            Duration period,
            Double[] closes,
            Double[] volumes,
            Double[] amounts,
            long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = number(closes[i]);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    number(volumes[i]),
                    number(amounts[i]),
                    trades[i]));
        }
        return result;
    }

    private static List<Bar> barsWithNullOhl(
            Duration period, double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = DecimalNum.valueOf(closes[i]);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    null,
                    i == 0 ? null : close,
                    null,
                    close,
                    DecimalNum.valueOf(i + 1),
                    DecimalNum.valueOf((i + 1) * 10),
                    i + 1L));
        }
        return result;
    }

    private static List<Bar> barsWithExtremeOhl(
            Duration period, double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num close = DecimalNum.valueOf(closes[i]);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    DecimalNum.valueOf(1000.0 + i),
                    DecimalNum.valueOf(10000.0 + i),
                    DecimalNum.valueOf(-10000.0 - i),
                    close,
                    DecimalNum.valueOf(i + 1),
                    DecimalNum.valueOf((i + 1) * 10),
                    i + 1L));
        }
        return result;
    }

    private static List<Bar> mixedFactoryBars(double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            MathContext context =
                    i % 2 == 0 ? MathContext.DECIMAL64 : MathContext.DECIMAL128;
            Instant begin = BASE_TIME.plus(DEFAULT_PERIOD.multipliedBy(i));
            Instant end = begin.plus(DEFAULT_PERIOD);
            Num close = DecimalNum.valueOf(closes[i], context);
            result.add(new BaseBar(
                    DEFAULT_PERIOD,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    DecimalNum.valueOf(i + 1, context),
                    DecimalNum.valueOf((i + 1) * 10, context),
                    i + 1L));
        }
        return result;
    }

    private static Num number(Double value) {
        return value == null ? null : DecimalNum.valueOf(value);
    }

    private static final class FollowUp {
        private final RenkoBarAggregator receiver;
        private final List<Bar> bars;

        private FollowUp(
                RenkoBarAggregator receiver, List<Bar> bars) {
            this.receiver = receiver;
            this.bars = bars;
        }
    }
}
