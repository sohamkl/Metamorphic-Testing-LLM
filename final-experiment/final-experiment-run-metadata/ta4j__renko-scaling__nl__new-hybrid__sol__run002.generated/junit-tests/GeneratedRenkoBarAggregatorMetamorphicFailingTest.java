import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final DecimalNum TWO = DecimalNum.valueOf(2);

    private enum Shape {
        FLAT, WIDE, VARIED
    }

    private static final class SourceCase {
        private final BigDecimal boxSize;
        private final int reversalAmount;
        private final boolean useDefaultConstructor;
        private final List<Bar> bars;

        private SourceCase(String boxSize, int reversalAmount, boolean useDefaultConstructor, List<Bar> bars) {
            this.boxSize = new BigDecimal(boxSize);
            this.reversalAmount = reversalAmount;
            this.useDefaultConstructor = useDefaultConstructor;
            this.bars = bars;
        }

        private RenkoBarAggregator receiver() {
            return useDefaultConstructor
                    ? new RenkoBarAggregator(boxSize)
                    : new RenkoBarAggregator(boxSize, reversalAmount);
        }
    }

    private static SourceCase source(String boxSize, int reversalAmount,
            boolean useDefaultConstructor, String... closes) {
        String[] volumes = new String[closes.length];
        String[] amounts = new String[closes.length];
        long[] trades = new long[closes.length];

        for (int i = 0; i < closes.length; i++) {
            volumes[i] = Integer.toString(i + 1);
            amounts[i] = new BigDecimal(i + 1).add(new BigDecimal("0.25")).toPlainString();
            trades[i] = i + 1L;
        }

        return sourceWithMetadata(boxSize, reversalAmount, useDefaultConstructor,
                Shape.FLAT, closes, volumes, amounts, trades);
    }

    private static SourceCase sourceWithMetadata(String boxSize, int reversalAmount,
            boolean useDefaultConstructor, Shape shape, String[] closes,
            String[] volumes, String[] amounts, long[] trades) {
        assertEquals(closes.length, volumes.length);
        assertEquals(closes.length, amounts.length);
        assertEquals(closes.length, trades.length);

        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            BigDecimal close = new BigDecimal(closes[i]);
            BigDecimal open;
            BigDecimal high;
            BigDecimal low;

            if (shape == Shape.FLAT) {
                open = close;
                high = close;
                low = close;
            } else if (shape == Shape.WIDE) {
                BigDecimal spread = new BigDecimal("5000000").add(BigDecimal.valueOf(i));
                open = close;
                high = close.add(spread);
                low = close.subtract(spread);
            } else {
                BigDecimal offset = new BigDecimal("0.125").multiply(BigDecimal.valueOf(i + 1L));
                open = (i & 1) == 0 ? close.subtract(offset) : close.add(offset);
                high = open.max(close).add(offset);
                low = open.min(close).subtract(offset);
            }

            Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            Num volume = volumes[i] == null ? null : decimal(volumes[i]);

            bars.add(new BaseBar(
                    PERIOD,
                    begin,
                    end,
                    decimal(open),
                    decimal(high),
                    decimal(low),
                    decimal(close),
                    volume,
                    decimal(amounts[i]),
                    trades[i]));
        }

        return new SourceCase(boxSize, reversalAmount, useDefaultConstructor, bars);
    }

    private static SourceCase generateFollowUp(SourceCase source) {
        List<Bar> scaledBars = new ArrayList<>(source.bars.size());

        for (Bar bar : source.bars) {
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

        return new SourceCase(
                source.boxSize.multiply(BigDecimal.valueOf(2L)).toPlainString(),
                source.reversalAmount,
                source.useDefaultConstructor,
                scaledBars);
    }

    private static void assertMetamorphicRelationFor(SourceCase source) {
        SourceCase followUp = generateFollowUp(source);

        List<Bar> sourceOutput = source.receiver().aggregate(source.bars);
        List<Bar> followUpOutput = followUp.receiver().aggregate(followUp.bars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        assertNotNull(sourceOutput);
        assertNotNull(followUpOutput);
        assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar sourceBrick = sourceOutput.get(i);
            Bar followUpBrick = followUpOutput.get(i);

            assertEquals(sourceBrick.getBeginTime(), followUpBrick.getBeginTime());
            assertEquals(sourceBrick.getEndTime(), followUpBrick.getEndTime());
            assertEquals(sourceBrick.getTimePeriod(), followUpBrick.getTimePeriod());
            assertNumEquals(sourceBrick.getVolume(), followUpBrick.getVolume());
            assertEquals(sourceBrick.getTrades(), followUpBrick.getTrades());

            assertNumEquals(scale(sourceBrick.getOpenPrice()), followUpBrick.getOpenPrice());
            assertNumEquals(scale(sourceBrick.getHighPrice()), followUpBrick.getHighPrice());
            assertNumEquals(scale(sourceBrick.getLowPrice()), followUpBrick.getLowPrice());
            assertNumEquals(scale(sourceBrick.getClosePrice()), followUpBrick.getClosePrice());
            assertNumEquals(scale(sourceBrick.getAmount()), followUpBrick.getAmount());

            assertEquals(direction(sourceBrick), direction(followUpBrick));
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

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(TWO);
    }

    private static void assertNumEquals(Num expected, Num actual) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
        } else {
            assertEquals(0, expected.compareTo(actual));
        }
    }

    private static DecimalNum decimal(String value) {
        return DecimalNum.valueOf(new BigDecimal(value));
    }

    private static DecimalNum decimal(BigDecimal value) {
        return DecimalNum.valueOf(value);
    }
}
