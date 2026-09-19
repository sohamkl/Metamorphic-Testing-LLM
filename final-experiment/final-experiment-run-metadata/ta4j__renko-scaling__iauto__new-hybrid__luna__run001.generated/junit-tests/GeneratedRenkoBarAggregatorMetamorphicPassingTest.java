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

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static void run(RenkoBarAggregator receiver, List<Bar> source) {
        execute(receiver, source);
    }

    private static void run(RenkoBarAggregator receiver, List<Bar> source, int metadataMode) {
        execute(receiver, withMetadata(source, metadataMode));
    }

    private static void execute(RenkoBarAggregator receiver, List<Bar> source) {
        List<Bar> sourceOutput = receiver.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(receiver, source);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpSource);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> bars(double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            result.add(bar(closes[i], n(1.0), n(2.0), i + 1L));
        }
        return result;
    }

    private static List<Bar> withMetadata(List<Bar> source, int mode) {
        List<Bar> result = new ArrayList<>(source.size());
        for (int i = 0; i < source.size(); i++) {
            Bar original = source.get(i);
            Num volume = n(i + 1.0);
            Num amount = n((i + 1.0) * 2.0);
            long trades = i + 1L;
            if (mode == 1) {
                volume = n(0.0);
                amount = n(0.0);
                trades = 0L;
            } else if (mode == 2 || mode == 6) {
                volume = null;
            } else if (mode == 3) {
                amount = null;
            } else if (mode == 4) {
                volume = null;
                amount = null;
            } else if (mode == 5) {
                volume = n(0.0);
                amount = n(0.0);
                trades = 0L;
            }
            if (mode == 6 && i < source.size() - 1) {
                amount = null;
            }
            result.add(bar(original.getClosePrice().doubleValue(), volume, amount, trades));
        }
        return result;
    }

    private static Bar bar(double close, Num volume, Num amount, long trades) {
        Num price = n(close);
        return new BaseBar(PERIOD, BASE_TIME, BASE_TIME.plus(PERIOD), price, price, price, price, volume, amount, trades);
    }

    private static Num n(double value) {
        return DecimalNum.valueOf(value);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        if (sourceOutput.size() != followUpOutput.size()) {
            throw new AssertionError("Brick counts differ");
        }
        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);
            if (!Objects.equals(source.getTimePeriod(), followUp.getTimePeriod())) {
                throw new AssertionError("Time periods differ at brick " + i);
            }
            if (!Objects.equals(source.getBeginTime(), followUp.getBeginTime())) {
                throw new AssertionError("Begin times differ at brick " + i);
            }
            if (!Objects.equals(source.getEndTime(), followUp.getEndTime())) {
                throw new AssertionError("End times differ at brick " + i);
            }
            if (!equalNum(source.getVolume(), followUp.getVolume())) {
                throw new AssertionError("Volumes differ at brick " + i);
            }
            if (source.getTrades() != followUp.getTrades()) {
                throw new AssertionError("Trade counts differ at brick " + i);
            }
            if (direction(source) != direction(followUp)) {
                throw new AssertionError("Directions differ at brick " + i);
            }
            assertScaled(source.getOpenPrice(), followUp.getOpenPrice(), i);
            assertScaled(source.getHighPrice(), followUp.getHighPrice(), i);
            assertScaled(source.getLowPrice(), followUp.getLowPrice(), i);
            assertScaled(source.getClosePrice(), followUp.getClosePrice(), i);
            assertScaled(source.getAmount(), followUp.getAmount(), i);
        }
    }

    private static boolean equalNum(Num first, Num second) {
        if (first == null || second == null) {
            return first == second;
        }
        return first.isEqual(second);
    }

    private static void assertScaled(Num source, Num followUp, int index) {
        if (source == null || followUp == null) {
            if (source != followUp) {
                throw new AssertionError("Nullability differs at brick " + index);
            }
            return;
        }
        Num expected = source.multipliedBy(source.getNumFactory().numOf(2.0));
        if (!followUp.isEqual(expected)) {
            throw new AssertionError("Scaled numeric value differs at brick " + index);
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

    @Test
    void EMPTY_SOURCE_LIST_1() {
        run(new RenkoBarAggregator(0.25, 1), List.of());
    }

    @Test
    void SINGLE_BAR_NO_MOVEMENT_1() {
        run(new RenkoBarAggregator(1.0, 2), bars(100.0), 1);
    }
}
