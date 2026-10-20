import static org.junit.jupiter.api.Assertions.assertEquals;

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

    private static final Instant BASE = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private void run(double boxSize, int reversalAmount, double[] closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = (double) (i + 1);
            amounts[i] = (double) ((i + 1) * 10);
            trades[i] = i + 1;
        }
        run(boxSize, reversalAmount, closes, volumes, amounts, trades);
    }

    private void run(double boxSize, int reversalAmount, double[] closes, Double[] volumes, Double[] amounts,
            long[] trades) {
        List<Bar> source = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            source.add(bar(i, closes[i], volumes[i], amounts[i], trades[i]));
        }
        assertMetamorphicRelationFor(source, boxSize, reversalAmount);
    }

    private void assertMetamorphicRelationFor(List<Bar> source, double boxSize, int reversalAmount) {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(boxSize, reversalAmount);
        FollowUp followUp = generateFollowUp(source, boxSize, reversalAmount);
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        List<Bar> followUpOutput = followUp.aggregator.aggregate(followUp.bars);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private FollowUp generateFollowUp(List<Bar> source, double boxSize, int reversalAmount) {
        List<Bar> scaledBars = new ArrayList<>();
        for (Bar bar : source) {
            Num two = bar.numFactory().numOf(2);
            scaledBars.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    scaled(bar.getOpenPrice(), two),
                    scaled(bar.getHighPrice(), two),
                    scaled(bar.getLowPrice(), two),
                    scaled(bar.getClosePrice(), two),
                    bar.getVolume(),
                    scaled(bar.getAmount(), two),
                    bar.getTrades()));
        }
        return new FollowUp(new RenkoBarAggregator(boxSize * 2.0, reversalAmount), scaledBars);
    }

    private void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        assertEquals(sourceOutput.size(), followUpOutput.size());
        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar sourceBrick = sourceOutput.get(i);
            Bar followUpBrick = followUpOutput.get(i);
            Num two = sourceBrick.numFactory().numOf(2);

            assertEquals(sourceBrick.getEndTime(), followUpBrick.getEndTime());
            assertEquals(sourceBrick.getTimePeriod(), followUpBrick.getTimePeriod());
            assertEquals(sourceBrick.getVolume(), followUpBrick.getVolume());
            assertEquals(sourceBrick.getTrades(), followUpBrick.getTrades());

            assertEquals(scaled(sourceBrick.getOpenPrice(), two), followUpBrick.getOpenPrice());
            assertEquals(scaled(sourceBrick.getHighPrice(), two), followUpBrick.getHighPrice());
            assertEquals(scaled(sourceBrick.getLowPrice(), two), followUpBrick.getLowPrice());
            assertEquals(scaled(sourceBrick.getClosePrice(), two), followUpBrick.getClosePrice());
            assertEquals(scaled(sourceBrick.getAmount(), two), followUpBrick.getAmount());

            assertEquals(
                    sourceBrick.getClosePrice().isGreaterThan(sourceBrick.getOpenPrice()),
                    followUpBrick.getClosePrice().isGreaterThan(followUpBrick.getOpenPrice()));
            assertEquals(
                    sourceBrick.getClosePrice().isLessThan(sourceBrick.getOpenPrice()),
                    followUpBrick.getClosePrice().isLessThan(followUpBrick.getOpenPrice()));
        }
    }

    private Bar bar(int index, double close, Double volume, Double amount, long trades) {
        Instant begin = BASE.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        Num price = DecimalNum.valueOf(close);
        return new BaseBar(
                PERIOD,
                begin,
                end,
                price,
                price,
                price,
                price,
                volume == null ? null : DecimalNum.valueOf(volume),
                amount == null ? null : DecimalNum.valueOf(amount),
                trades);
    }

    private Num scaled(Num value, Num two) {
        return value == null ? null : value.multipliedBy(two);
    }

    private static final class FollowUp {
        private final RenkoBarAggregator aggregator;
        private final List<Bar> bars;

        private FollowUp(RenkoBarAggregator aggregator, List<Bar> bars) {
            this.aggregator = aggregator;
            this.bars = bars;
        }
    }
}
