import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static void check(RenkoBarAggregator sourceAggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(sourceAggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        if (sourceOutput.size() != followUpOutput.size()) {
            throw new AssertionError("Source and follow-up must produce the same number of bricks");
        }

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);

            assertEqual(source.getTimePeriod(), followUp.getTimePeriod(), "time period", i);
            assertEqual(source.getBeginTime(), followUp.getBeginTime(), "begin time", i);
            assertEqual(source.getEndTime(), followUp.getEndTime(), "end time", i);
            assertEqual(source.getVolume(), followUp.getVolume(), "volume", i);

            if (source.getTrades() != followUp.getTrades()) {
                throw new AssertionError("Trade count differs at brick " + i);
            }

            if (direction(source) != direction(followUp)) {
                throw new AssertionError("Direction differs at brick " + i);
            }

            assertScaled(source.getOpenPrice(), followUp.getOpenPrice(), "open price", i);
            assertScaled(source.getHighPrice(), followUp.getHighPrice(), "high price", i);
            assertScaled(source.getLowPrice(), followUp.getLowPrice(), "low price", i);
            assertScaled(source.getClosePrice(), followUp.getClosePrice(), "close price", i);
            assertScaled(source.getAmount(), followUp.getAmount(), "amount", i);
        }
    }

    private static void assertScaled(Num source, Num followUp, String field, int index) {
        if (source == null || followUp == null) {
            if (source != followUp) {
                throw new AssertionError("Null mismatch for " + field + " at brick " + index);
            }
            return;
        }

        Num expected = source.multipliedBy(source.getNumFactory().numOf(2.0));
        if (!followUp.isEqual(expected)) {
            throw new AssertionError("Scaled value mismatch for " + field + " at brick " + index);
        }
    }

    private static void assertEqual(Object source, Object followUp, String field, int index) {
        if (!Objects.equals(source, followUp)) {
            throw new AssertionError("Mismatch for " + field + " at brick " + index);
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

    private static List<Bar> series(double initialClose, double boxSize, Payload payload, double... offsets) {
        return offsetSeries(Duration.ofSeconds(60), initialClose, boxSize, payload, offsets);
    }

    private static List<Bar> offsetSeries(Duration period, double initialClose, double boxSize, Payload payload,
            double... offsets) {
        double[] closes = new double[offsets.length];
        for (int i = 0; i < offsets.length; i++) {
            closes[i] = initialClose + offsets[i] * boxSize;
        }
        return directSeries(period, payload, closes);
    }

    private static List<Bar> directSeries(Duration period, Payload payload, double... closes) {
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        List<Bar> bars = new ArrayList<>(closes.length);

        for (int i = 0; i < closes.length; i++) {
            Num price = num(closes[i]);
            Num volume = payload.nullVolume ? null : num(payload.zero ? 0.0 : i + 1.0);
            Num amount = payload.nullAmount ? null : num(payload.zero ? 0.0 : (i + 1.0) * 10.0);
            long trades = payload.zero ? 0L : i + 1L;
            Instant begin = start.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);

            bars.add(new BaseBar(period, begin, end, price, price, price, price, volume, amount, trades));
        }

        return bars;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private enum Payload {
        STANDARD(false, false, false),
        ZERO(false, false, true),
        NULL_VOLUME(true, false, false),
        NULL_AMOUNT(false, true, false),
        NULL_VOLUME_AND_AMOUNT(true, true, false);

        private final boolean nullVolume;
        private final boolean nullAmount;
        private final boolean zero;

        Payload(boolean nullVolume, boolean nullAmount, boolean zero) {
            this.nullVolume = nullVolume;
            this.nullAmount = nullAmount;
            this.zero = zero;
        }
    }
}
