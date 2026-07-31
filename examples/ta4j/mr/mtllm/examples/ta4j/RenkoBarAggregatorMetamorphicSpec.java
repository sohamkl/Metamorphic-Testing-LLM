package mtllm.examples.ta4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorSut.RenkoCase;

/**
 * Positive price-and-box scaling relation for Renko aggregation.
 */
public final class RenkoBarAggregatorMetamorphicSpec {
    private static final double SCALE_FACTOR = 2.0;

    private RenkoBarAggregatorMetamorphicSpec() {
    }

    public static RenkoCase generateFollowUp(RenkoCase source) {
        Objects.requireNonNull(source, "source");
        List<Bar> scaledBars = new ArrayList<>(source.bars().size());
        for (Bar bar : source.bars()) {
            scaledBars.add(scaleBar(Objects.requireNonNull(bar, "source bars must not contain null")));
        }
        return new RenkoCase(scaledBars, source.boxSize() * SCALE_FACTOR, source.reversalAmount());
    }

    public static void assertRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        Objects.requireNonNull(sourceOutput, "sourceOutput");
        Objects.requireNonNull(followUpOutput, "followUpOutput");
        if (sourceOutput.size() != followUpOutput.size()) {
            throw new AssertionError("Expected the same number of Renko bricks, but source produced "
                    + sourceOutput.size() + " and follow-up produced " + followUpOutput.size());
        }

        for (int index = 0; index < sourceOutput.size(); index++) {
            Bar source = sourceOutput.get(index);
            Bar followUp = followUpOutput.get(index);
            assertEqual(source.getTimePeriod(), followUp.getTimePeriod(), "time period", index);
            assertEqual(source.getBeginTime(), followUp.getBeginTime(), "begin time", index);
            assertEqual(source.getEndTime(), followUp.getEndTime(), "end time", index);
            assertEqual(source.getVolume(), followUp.getVolume(), "volume", index);
            if (source.getTrades() != followUp.getTrades()) {
                throw new AssertionError("Expected equal trade count at brick " + index + ", but source was "
                        + source.getTrades() + " and follow-up was " + followUp.getTrades());
            }
            if (direction(source) != direction(followUp)) {
                throw new AssertionError("Expected equal direction at brick " + index);
            }

            assertScaled(source.getOpenPrice(), followUp.getOpenPrice(), "open", index);
            assertScaled(source.getHighPrice(), followUp.getHighPrice(), "high", index);
            assertScaled(source.getLowPrice(), followUp.getLowPrice(), "low", index);
            assertScaled(source.getClosePrice(), followUp.getClosePrice(), "close", index);
            assertScaled(source.getAmount(), followUp.getAmount(), "amount", index);
        }
    }

    private static Bar scaleBar(Bar source) {
        return new BaseBar(
                source.getTimePeriod(),
                source.getBeginTime(),
                source.getEndTime(),
                scale(source.getOpenPrice()),
                scale(source.getHighPrice()),
                scale(source.getLowPrice()),
                scale(source.getClosePrice()),
                source.getVolume(),
                scale(source.getAmount()),
                source.getTrades());
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(value.getNumFactory().numOf(SCALE_FACTOR));
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

    private static void assertScaled(Num source, Num followUp, String field, int index) {
        if (source == null || followUp == null) {
            if (source != followUp) {
                throw new AssertionError("Expected matching null " + field + " values at brick " + index);
            }
            return;
        }
        Num expected = scale(source);
        if (!followUp.isEqual(expected)) {
            throw new AssertionError("Expected scaled " + field + " at brick " + index + " to be " + expected
                    + ", but was " + followUp);
        }
    }

    private static void assertEqual(Object source, Object followUp, String field, int index) {
        if (!Objects.equals(source, followUp)) {
            throw new AssertionError("Expected equal " + field + " at brick " + index + ", but source was "
                    + source + " and follow-up was " + followUp);
        }
    }
}
