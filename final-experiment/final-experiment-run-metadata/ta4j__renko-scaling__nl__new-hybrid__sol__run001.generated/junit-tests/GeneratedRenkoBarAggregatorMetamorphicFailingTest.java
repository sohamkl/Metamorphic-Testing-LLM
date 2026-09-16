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

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_END = Instant.parse("2020-01-01T00:01:00Z");
    private static final BigDecimal SCALE = new BigDecimal("2");

    private static final class SourceCase {
        private final RenkoBarAggregator receiver;
        private final List<Bar> bars;
        private final BigDecimal boxSize;
        private final int reversalAmount;
        private final boolean defaultConstructor;

        private SourceCase(RenkoBarAggregator receiver, List<Bar> bars, BigDecimal boxSize, int reversalAmount,
                boolean defaultConstructor) {
            this.receiver = receiver;
            this.bars = bars;
            this.boxSize = boxSize;
            this.reversalAmount = reversalAmount;
            this.defaultConstructor = defaultConstructor;
        }
    }

    private static SourceCase sourceCase(boolean defaultConstructor, String boxSize, int reversalAmount,
            double[] closes) {
        return sourceCase(defaultConstructor, boxSize, reversalAmount, closes, standardVolumes(closes.length),
                standardAmounts(closes.length), standardTrades(closes.length), 0);
    }

    private static SourceCase sourceCase(boolean defaultConstructor, String boxSize, int reversalAmount,
            double[] closes, int ohlcStyle) {
        return sourceCase(defaultConstructor, boxSize, reversalAmount, closes, standardVolumes(closes.length),
                standardAmounts(closes.length), standardTrades(closes.length), ohlcStyle);
    }

    private static SourceCase sourceCase(boolean defaultConstructor, String boxSize, int reversalAmount,
            double[] closes, Double[] volumes, double[] amounts, long[] trades, int ohlcStyle) {
        Assertions.assertEquals(closes.length, volumes.length);
        Assertions.assertEquals(closes.length, amounts.length);
        Assertions.assertEquals(closes.length, trades.length);

        BigDecimal box = new BigDecimal(boxSize);
        int effectiveReversalAmount = defaultConstructor ? 2 : reversalAmount;
        RenkoBarAggregator receiver = defaultConstructor
                ? new RenkoBarAggregator(box)
                : new RenkoBarAggregator(box, reversalAmount);

        List<Bar> bars = new ArrayList<Bar>();
        for (int i = 0; i < closes.length; i++) {
            bars.add(createBar(i, closes[i], volumes[i], amounts[i], trades[i], ohlcStyle));
        }

        return new SourceCase(receiver, bars, box, effectiveReversalAmount, defaultConstructor);
    }

    private static Bar createBar(int index, double close, Double volume, double amount, long trades, int style) {
        double open;
        double high;
        double low;

        if (style == 0) {
            open = close;
            high = close;
            low = close;
        } else if (style == 1) {
            open = close - 0.25;
            high = close + 1.0;
            low = close - 1.0;
        } else {
            open = close + 0.25;
            high = close + 2.0;
            low = close - 2.0;
        }

        Instant endTime = BASE_END.plus(PERIOD.multipliedBy(index));
        Instant beginTime = endTime.minus(PERIOD);
        Num volumeNum = volume == null ? null : num(volume.doubleValue());

        return new BaseBar(PERIOD, beginTime, endTime, num(open), num(high), num(low), num(close), volumeNum,
                num(amount), trades);
    }

    private static Double[] standardVolumes(int length) {
        Double[] values = new Double[length];
        for (int i = 0; i < length; i++) {
            values[i] = Double.valueOf(i + 1.0);
        }
        return values;
    }

    private static double[] standardAmounts(int length) {
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = 10.0 + i * 3.0;
        }
        return values;
    }

    private static long[] standardTrades(int length) {
        long[] values = new long[length];
        for (int i = 0; i < length; i++) {
            values[i] = i + 1L;
        }
        return values;
    }

    private static DecimalNum num(double value) {
        return DecimalNum.valueOf(BigDecimal.valueOf(value));
    }

    private static SourceCase generateFollowUp(SourceCase source) {
        BigDecimal scaledBoxSize = source.boxSize.multiply(SCALE);
        RenkoBarAggregator followUpReceiver = source.defaultConstructor
                ? new RenkoBarAggregator(scaledBoxSize)
                : new RenkoBarAggregator(scaledBoxSize, source.reversalAmount);

        List<Bar> scaledBars = new ArrayList<Bar>();
        for (Bar bar : source.bars) {
            Num factor = bar.numFactory().numOf(SCALE);
            Num scaledAmount = bar.getAmount().multipliedBy(factor);

            scaledBars.add(new BaseBar(bar.getTimePeriod(), bar.getBeginTime(), bar.getEndTime(),
                    bar.getOpenPrice().multipliedBy(factor),
                    bar.getHighPrice().multipliedBy(factor),
                    bar.getLowPrice().multipliedBy(factor),
                    bar.getClosePrice().multipliedBy(factor),
                    bar.getVolume(),
                    scaledAmount,
                    bar.getTrades()));
        }

        return new SourceCase(followUpReceiver, scaledBars, scaledBoxSize, source.reversalAmount,
                source.defaultConstructor);
    }

    private static void assertMetamorphicRelationFor(SourceCase source) {
        SourceCase followUp = generateFollowUp(source);
        List<Bar> sourceOutput = source.receiver.aggregate(source.bars);
        List<Bar> followUpOutput = followUp.receiver.aggregate(followUp.bars);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        Assertions.assertEquals(sourceOutput.size(), followUpOutput.size(), "Renko brick count differs");

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar sourceBrick = sourceOutput.get(i);
            Bar followUpBrick = followUpOutput.get(i);
            Num factor = sourceBrick.numFactory().numOf(SCALE);

            Assertions.assertEquals(sourceBrick.getTimePeriod(), followUpBrick.getTimePeriod(),
                    "Time period mismatch at brick " + i);
            Assertions.assertEquals(sourceBrick.getBeginTime(), followUpBrick.getBeginTime(),
                    "Begin time mismatch at brick " + i);
            Assertions.assertEquals(sourceBrick.getEndTime(), followUpBrick.getEndTime(),
                    "End time mismatch at brick " + i);
            Assertions.assertEquals(sourceBrick.getTrades(), followUpBrick.getTrades(),
                    "Trade count mismatch at brick " + i);

            assertNumericallyEqual(sourceBrick.getVolume(), followUpBrick.getVolume(), "volume", i);
            assertNumericallyEqual(sourceBrick.getOpenPrice().multipliedBy(factor), followUpBrick.getOpenPrice(),
                    "open", i);
            assertNumericallyEqual(sourceBrick.getHighPrice().multipliedBy(factor), followUpBrick.getHighPrice(),
                    "high", i);
            assertNumericallyEqual(sourceBrick.getLowPrice().multipliedBy(factor), followUpBrick.getLowPrice(),
                    "low", i);
            assertNumericallyEqual(sourceBrick.getClosePrice().multipliedBy(factor), followUpBrick.getClosePrice(),
                    "close", i);
            assertNumericallyEqual(sourceBrick.getAmount().multipliedBy(factor), followUpBrick.getAmount(),
                    "amount", i);

            int sourceDirection = sourceBrick.getClosePrice().compareTo(sourceBrick.getOpenPrice());
            int followUpDirection = followUpBrick.getClosePrice().compareTo(followUpBrick.getOpenPrice());
            Assertions.assertEquals(Integer.signum(sourceDirection), Integer.signum(followUpDirection),
                    "Direction mismatch at brick " + i);
        }
    }

    private static void assertNumericallyEqual(Num expected, Num actual, String field, int index) {
        if (expected == null || actual == null) {
            Assertions.assertEquals(expected, actual, field + " nullability mismatch at brick " + index);
        } else {
            Assertions.assertTrue(expected.isEqual(actual),
                    field + " mismatch at brick " + index + ": expected " + expected + " but was " + actual);
        }
    }
}
