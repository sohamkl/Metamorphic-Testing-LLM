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

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration SHORT_PERIOD = Duration.ofMinutes(1);
    private static final Duration LONG_PERIOD = Duration.ofHours(1);

    private static final class SourceCase {
        private final BigDecimal boxSize;
        private final int reversalAmount;
        private final boolean oneArgumentConstructor;
        private final List<Bar> bars;

        private SourceCase(BigDecimal boxSize, int reversalAmount, boolean oneArgumentConstructor, List<Bar> bars) {
            this.boxSize = boxSize;
            this.reversalAmount = reversalAmount;
            this.oneArgumentConstructor = oneArgumentConstructor;
            this.bars = bars;
        }

        private BigDecimal boxSize() {
            return boxSize;
        }

        private int reversalAmount() {
            return reversalAmount;
        }

        private boolean oneArgumentConstructor() {
            return oneArgumentConstructor;
        }

        private List<Bar> bars() {
            return bars;
        }

        private RenkoBarAggregator receiver() {
            if (oneArgumentConstructor) {
                return new RenkoBarAggregator(boxSize);
            }
            return new RenkoBarAggregator(boxSize, reversalAmount);
        }
    }

    private static SourceCase source(String boxSize, int reversalAmount, boolean oneArgumentConstructor,
            Duration period, double... closes) {
        Double[] volumes = new Double[closes.length];
        double[] amounts = new double[closes.length];
        long[] trades = new long[closes.length];

        for (int i = 0; i < closes.length; i++) {
            volumes[i] = Double.valueOf(i + 1.0);
            amounts[i] = (i + 1) * 2.5;
            trades[i] = i + 1L;
        }

        return sourceWithMetrics(boxSize, reversalAmount, oneArgumentConstructor, period,
                closes, volumes, amounts, trades);
    }

    private static SourceCase sourceWithMetrics(String boxSize, int reversalAmount,
            boolean oneArgumentConstructor, Duration period, double[] closes, Double[] volumes,
            double[] amounts, long[] trades) {
        if (closes.length != volumes.length || closes.length != amounts.length
                || closes.length != trades.length) {
            throw new IllegalArgumentException("Metric arrays must have equal lengths");
        }

        double[] opens = new double[closes.length];
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];

        for (int i = 0; i < closes.length; i++) {
            opens[i] = closes[i] + (i % 2 == 0 ? -0.25 : 0.25);
            highs[i] = Math.max(opens[i], closes[i]) + 5.0 + i;
            lows[i] = Math.min(opens[i], closes[i]) - 5.0 - i;
        }

        return new SourceCase(new BigDecimal(boxSize), reversalAmount, oneArgumentConstructor,
                customBars(period, opens, highs, lows, closes, volumes, amounts, trades));
    }

    private static List<Bar> customBars(Duration period, double[] opens, double[] highs,
            double[] lows, double[] closes, Double[] volumes, double[] amounts, long[] trades) {
        int size = closes.length;
        if (opens.length != size || highs.length != size || lows.length != size
                || volumes.length != size || amounts.length != size || trades.length != size) {
            throw new IllegalArgumentException("Bar arrays must have equal lengths");
        }

        List<Bar> bars = new ArrayList<Bar>(size);
        for (int i = 0; i < size; i++) {
            Instant begin = BASE_TIME.plus(period.multipliedBy(i));
            Instant end = begin.plus(period);
            Num volume = volumes[i] == null ? null : num(volumes[i].doubleValue());

            bars.add(new BaseBar(
                    period,
                    begin,
                    end,
                    num(opens[i]),
                    num(highs[i]),
                    num(lows[i]),
                    num(closes[i]),
                    volume,
                    num(amounts[i]),
                    trades[i]));
        }
        return bars;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(BigDecimal.valueOf(value));
    }

    private static void assertMetamorphicRelationFor(SourceCase source) {
        SourceCase followUp = generateFollowUp(source);
        List<Bar> sourceOutput = source.receiver().aggregate(source.bars());
        List<Bar> followUpOutput = followUp.receiver().aggregate(followUp.bars());
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static SourceCase generateFollowUp(SourceCase source) {
        List<Bar> transformedBars = new ArrayList<Bar>(source.bars().size());

        for (Bar bar : source.bars()) {
            Num two = bar.numFactory().numOf(2);
            transformedBars.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    scale(bar.getOpenPrice(), two),
                    scale(bar.getHighPrice(), two),
                    scale(bar.getLowPrice(), two),
                    scale(bar.getClosePrice(), two),
                    bar.getVolume(),
                    scale(bar.getAmount(), two),
                    bar.getTrades()));
        }

        return new SourceCase(
                source.boxSize().multiply(BigDecimal.valueOf(2)),
                source.reversalAmount(),
                source.oneArgumentConstructor(),
                transformedBars);
    }

    private static Num scale(Num value, Num factor) {
        return value.multipliedBy(factor);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar sourceBrick = sourceOutput.get(i);
            Bar followUpBrick = followUpOutput.get(i);
            Num two = sourceBrick.numFactory().numOf(2);

            assertEquals(direction(sourceBrick), direction(followUpBrick));
            assertEquals(sourceBrick.getBeginTime(), followUpBrick.getBeginTime());
            assertEquals(sourceBrick.getEndTime(), followUpBrick.getEndTime());
            assertEquals(sourceBrick.getTimePeriod(), followUpBrick.getTimePeriod());
            assertNumEqual(sourceBrick.getVolume(), followUpBrick.getVolume());
            assertEquals(sourceBrick.getTrades(), followUpBrick.getTrades());

            assertNumEqual(scale(sourceBrick.getOpenPrice(), two), followUpBrick.getOpenPrice());
            assertNumEqual(scale(sourceBrick.getHighPrice(), two), followUpBrick.getHighPrice());
            assertNumEqual(scale(sourceBrick.getLowPrice(), two), followUpBrick.getLowPrice());
            assertNumEqual(scale(sourceBrick.getClosePrice(), two), followUpBrick.getClosePrice());
            assertNumEqual(scale(sourceBrick.getAmount(), two), followUpBrick.getAmount());
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

    private static void assertNumEqual(Num expected, Num actual) {
        assertTrue(expected.isEqual(actual),
                "Expected numerically equal values but got expected=" + expected + ", actual=" + actual);
    }
}
