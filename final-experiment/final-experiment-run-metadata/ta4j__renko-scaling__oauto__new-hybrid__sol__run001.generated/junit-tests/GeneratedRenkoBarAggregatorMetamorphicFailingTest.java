import java.lang.reflect.Field;
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

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");

    private static void verifyRelation(
            RenkoBarAggregator receiver, List<Bar> bars) {
        List<Bar> sourceOutput = receiver.aggregate(bars);
        FollowUp followUp = generateFollowUp(receiver, bars);
        List<Bar> followUpOutput =
                followUp.getReceiver().aggregate(followUp.getBars());
        RenkoBarAggregatorMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static FollowUp generateFollowUp(
            RenkoBarAggregator receiver, List<Bar> bars) {
        try {
            Field boxSizeField =
                    RenkoBarAggregator.class.getDeclaredField("boxSize");
            Field reversalAmountField =
                    RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            boxSizeField.setAccessible(true);
            reversalAmountField.setAccessible(true);

            Number boxSize = (Number) boxSizeField.get(receiver);
            int reversalAmount = reversalAmountField.getInt(receiver);

            RenkoBarAggregator scaledReceiver =
                    new RenkoBarAggregator(
                            boxSize.doubleValue() * 2.0,
                            reversalAmount);

            List<Bar> scaledBars = new ArrayList<Bar>(bars.size());
            for (Bar source : bars) {
                scaledBars.add(new BaseBar(
                        source.getTimePeriod(),
                        source.getBeginTime(),
                        source.getEndTime(),
                        scale(source.getOpenPrice()),
                        scale(source.getHighPrice()),
                        scale(source.getLowPrice()),
                        scale(source.getClosePrice()),
                        source.getVolume(),
                        scale(source.getAmount()),
                        source.getTrades()));
            }

            return new FollowUp(scaledReceiver, scaledBars);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException(
                    "Unable to create scaled Renko follow-up input",
                    failure);
        }
    }

    private static Num scale(Num value) {
        if (value == null) {
            return null;
        }
        return value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(Duration period, double... closes) {
        List<Bar> result = new ArrayList<Bar>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            result.add(createBar(
                    period,
                    index,
                    close,
                    close + 0.1,
                    close - 0.1,
                    close,
                    Double.valueOf(index + 1.0),
                    Double.valueOf(close * (index + 1.0)),
                    index + 1L));
        }
        return result;
    }

    private static List<Bar> wideBars(Duration period, double... closes) {
        List<Bar> result = new ArrayList<Bar>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            result.add(createBar(
                    period,
                    index,
                    close + 10.0,
                    close + 5000.0,
                    close - 5000.0,
                    close,
                    Double.valueOf(index + 2.0),
                    Double.valueOf(close * (index + 2.0)),
                    index + 2L));
        }
        return result;
    }

    private static List<Bar> metricBars(
            Duration period,
            double[] closes,
            Double[] volumes,
            Double[] amounts,
            long[] trades) {
        List<Bar> result = new ArrayList<Bar>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            result.add(createBar(
                    period,
                    index,
                    close,
                    close + 0.1,
                    close - 0.1,
                    close,
                    volumes[index],
                    amounts[index],
                    trades[index]));
        }
        return result;
    }

    private static Bar createBar(
            Duration period,
            int index,
            double open,
            double high,
            double low,
            double close,
            Double volume,
            Double amount,
            long trades) {
        Instant begin = BASE_TIME.plus(period.multipliedBy(index));
        Instant end = begin.plus(period);
        return new BaseBar(
                period,
                begin,
                end,
                num(open),
                num(high),
                num(low),
                num(close),
                volume == null ? null : num(volume.doubleValue()),
                amount == null ? null : num(amount.doubleValue()),
                trades);
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private static final class FollowUp {
        private final RenkoBarAggregator receiver;
        private final List<Bar> bars;

        private FollowUp(
                RenkoBarAggregator receiver,
                List<Bar> bars) {
            this.receiver = receiver;
            this.bars = bars;
        }

        private RenkoBarAggregator getReceiver() {
            return receiver;
        }

        private List<Bar> getBars() {
            return bars;
        }
    }
}
