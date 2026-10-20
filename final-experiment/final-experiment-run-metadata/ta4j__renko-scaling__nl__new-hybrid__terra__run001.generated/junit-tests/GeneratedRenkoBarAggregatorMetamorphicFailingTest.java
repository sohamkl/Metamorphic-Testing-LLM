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
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static void assertMetamorphicRelationFor(int boxSize, int reversalAmount, List<Bar> sourceBars) {
        RenkoBarAggregator sourceReceiver = new RenkoBarAggregator(boxSize, reversalAmount);
        RenkoBarAggregator followUpReceiver = new RenkoBarAggregator(boxSize * 2, reversalAmount);
        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);
        List<Bar> followUpOutput = followUpReceiver.aggregate(generateFollowUp(sourceBars));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationForDefaultReversal(int boxSize, List<Bar> sourceBars) {
        RenkoBarAggregator sourceReceiver = new RenkoBarAggregator(boxSize);
        RenkoBarAggregator followUpReceiver = new RenkoBarAggregator(boxSize * 2);
        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);
        List<Bar> followUpOutput = followUpReceiver.aggregate(generateFollowUp(sourceBars));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> generateFollowUp(List<Bar> sourceBars) {
        List<Bar> followUp = new ArrayList<>();
        for (Bar source : sourceBars) {
            Num factor = source.numFactory().numOf(2);
            followUp.add(new BaseBar(
                    source.getTimePeriod(),
                    source.getBeginTime(),
                    source.getEndTime(),
                    source.getOpenPrice().multipliedBy(factor),
                    source.getHighPrice().multipliedBy(factor),
                    source.getLowPrice().multipliedBy(factor),
                    source.getClosePrice().multipliedBy(factor),
                    source.getVolume(),
                    source.getAmount().multipliedBy(factor),
                    source.getTrades()));
        }
        return followUp;
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        Assertions.assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar sourceBrick = sourceOutput.get(i);
            Bar followUpBrick = followUpOutput.get(i);
            Num factor = sourceBrick.numFactory().numOf(2);

            Assertions.assertEquals(sourceBrick.getBeginTime(), followUpBrick.getBeginTime());
            Assertions.assertEquals(sourceBrick.getEndTime(), followUpBrick.getEndTime());
            Assertions.assertEquals(sourceBrick.getTimePeriod(), followUpBrick.getTimePeriod());
            Assertions.assertEquals(sourceBrick.getVolume(), followUpBrick.getVolume());
            Assertions.assertEquals(sourceBrick.getTrades(), followUpBrick.getTrades());

            Assertions.assertEquals(sourceBrick.getOpenPrice().multipliedBy(factor), followUpBrick.getOpenPrice());
            Assertions.assertEquals(sourceBrick.getHighPrice().multipliedBy(factor), followUpBrick.getHighPrice());
            Assertions.assertEquals(sourceBrick.getLowPrice().multipliedBy(factor), followUpBrick.getLowPrice());
            Assertions.assertEquals(sourceBrick.getClosePrice().multipliedBy(factor), followUpBrick.getClosePrice());
            Assertions.assertEquals(sourceBrick.getAmount().multipliedBy(factor), followUpBrick.getAmount());

            Assertions.assertEquals(
                    sourceBrick.getClosePrice().isGreaterThan(sourceBrick.getOpenPrice()),
                    followUpBrick.getClosePrice().isGreaterThan(followUpBrick.getOpenPrice()));
        }
    }

    private static List<Bar> bars(int[] closes) {
        Integer[] volumes = new Integer[closes.length];
        Integer[] amounts = new Integer[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = i + 1;
            amounts[i] = (i + 1) * 10;
            trades[i] = i + 1;
        }
        return bars(closes, volumes, amounts, trades);
    }

    private static List<Bar> bars(int[] closes, Integer[] volumes, Integer[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            result.add(makeBar(i, closes[i], closes[i] + 1, closes[i] - 1, closes[i],
                    volumes[i], amounts[i], trades[i]));
        }
        return result;
    }

    private static Bar makeBar(int index, int open, int high, int low, int close,
            Integer volume, Integer amount, long trades) {
        Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        return new BaseBar(
                PERIOD,
                begin,
                end,
                DecimalNum.valueOf(open),
                DecimalNum.valueOf(high),
                DecimalNum.valueOf(low),
                DecimalNum.valueOf(close),
                volume == null ? null : DecimalNum.valueOf(volume),
                DecimalNum.valueOf(amount),
                trades);
    }
}
