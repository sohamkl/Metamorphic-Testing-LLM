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

    @Test
    void EMPTY_SOURCE_LIST_defaultConstructor() {
        check(new RenkoBarAggregator(2.0), List.of());
    }

    @Test
    void SINGLE_BASELINE_BAR_reversalOneZeroPayload() {
        check(new RenkoBarAggregator(2.0, 1), series(100.0, 2.0, Payload.ZERO, 0.0));
    }

    @Test
    void INITIAL_UPWARD_MOVE_BELOW_BOX_nullVolume() {
        check(new RenkoBarAggregator(2.0, 2), series(100.0, 2.0, Payload.NULL_VOLUME, 0.0, 0.5));
    }

    @Test
    void INITIAL_DOWNWARD_MOVE_BELOW_BOX_nullAmount() {
        check(new RenkoBarAggregator(2.0, 3), series(100.0, 2.0, Payload.NULL_AMOUNT, 0.0, -0.5));
    }

    @Test
    void INITIAL_EXACT_UPWARD_BOX_standardPayload() {
        check(new RenkoBarAggregator(2.0, 2), series(100.0, 2.0, Payload.STANDARD, 0.0, 1.0));
    }

    @Test
    void INITIAL_EXACT_DOWNWARD_BOX_pendingPayload() {
        check(new RenkoBarAggregator(2.0), series(100.0, 2.0, Payload.STANDARD, 0.0, -1.0));
    }

    @Test
    void PENDING_PAYLOAD_ACROSS_SUBTHRESHOLD_BARS_distinctPayloads() {
        check(new RenkoBarAggregator(2.0, 1),
                series(100.0, 2.0, Payload.STANDARD, 0.0, 0.25, 0.75, 1.0));
    }

    @Test
    void INITIAL_MULTI_BOX_UPWARD_MOVE_threeBricks() {
        check(new RenkoBarAggregator(2.0, 2), series(100.0, 2.0, Payload.STANDARD, 0.0, 3.0));
    }

    @Test
    void INITIAL_MULTI_BOX_DOWNWARD_MOVE_threeBricks() {
        check(new RenkoBarAggregator(2.0, 3), series(100.0, 2.0, Payload.STANDARD, 0.0, -3.0));
    }

    @Test
    void UPWARD_CONTINUATION_AFTER_UP_DIRECTION_fractionalBox() {
        check(new RenkoBarAggregator(0.5, 2), series(100.0, 0.5, Payload.STANDARD, 0.0, 1.0, 2.0));
    }

    @Test
    void DOWNWARD_CONTINUATION_AFTER_DOWN_DIRECTION_defaultConfiguration() {
        check(new RenkoBarAggregator(2.0), series(100.0, 2.0, Payload.STANDARD, 0.0, -1.0, -2.0));
    }

    @Test
    void UPWARD_DIRECTION_REVERSAL_JUST_SHORT_twoBoxConfiguration() {
        check(new RenkoBarAggregator(2.0, 2), series(100.0, 2.0, Payload.STANDARD, 0.0, 1.0, -0.9));
    }

    @Test
    void UPWARD_DIRECTION_EXACT_TWO_BOX_REVERSAL_standardPayload() {
        check(new RenkoBarAggregator(2.0, 2), series(100.0, 2.0, Payload.STANDARD, 0.0, 1.0, -1.0));
    }

    @Test
    void UPWARD_DIRECTION_REVERSAL_OVERSHOOT_fourBricks() {
        check(new RenkoBarAggregator(2.0, 2), series(100.0, 2.0, Payload.ZERO, 0.0, 1.0, -3.0));
    }

    @Test
    void DOWNWARD_DIRECTION_REVERSAL_JUST_SHORT_twoBoxConfiguration() {
        check(new RenkoBarAggregator(2.0, 2), series(100.0, 2.0, Payload.STANDARD, 0.0, -1.0, 0.9));
    }

    @Test
    void DOWNWARD_DIRECTION_EXACT_TWO_BOX_REVERSAL_defaultConfiguration() {
        check(new RenkoBarAggregator(2.0), series(100.0, 2.0, Payload.STANDARD, 0.0, -1.0, 1.0));
    }

    @Test
    void DOWNWARD_DIRECTION_REVERSAL_OVERSHOOT_fourBricks() {
        check(new RenkoBarAggregator(2.0, 2), series(100.0, 2.0, Payload.NULL_VOLUME_AND_AMOUNT, 0.0, -1.0, 3.0));
    }

    @Test
    void ONE_BOX_REVERSAL_CONFIGURATION_exactOppositeBox() {
        check(new RenkoBarAggregator(2.0, 1), series(100.0, 2.0, Payload.STANDARD, 0.0, 1.0, 0.0));
    }

    @Test
    void THREE_BOX_REVERSAL_JUST_SHORT_halfBoxBeforeThreshold() {
        check(new RenkoBarAggregator(2.0, 3), series(100.0, 2.0, Payload.STANDARD, 0.0, 1.0, -1.5));
    }

    @Test
    void THREE_BOX_REVERSAL_EXACT_thresholdReached() {
        check(new RenkoBarAggregator(2.0, 3), series(100.0, 2.0, Payload.ZERO, 0.0, 1.0, -2.0));
    }

    @Test
    void DEFAULT_CONSTRUCTOR_CONFIGURATION_twoBoxReversal() {
        check(new RenkoBarAggregator(2.0), series(100.0, 2.0, Payload.NULL_VOLUME, 0.0, 1.0, -1.0));
    }

    @Test
    void NULL_VOLUME_WITH_NON_NULL_AMOUNT_amountPreserved() {
        check(new RenkoBarAggregator(2.0, 1), series(100.0, 2.0, Payload.NULL_VOLUME, 0.0, 1.0));
    }

    @Test
    void NULL_AMOUNT_WITH_NON_NULL_VOLUME_volumePreserved() {
        check(new RenkoBarAggregator(2.0, 2), series(100.0, 2.0, Payload.NULL_AMOUNT, 0.0, 1.0));
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_tradesRemainPresent() {
        check(new RenkoBarAggregator(2.0, 3), series(100.0, 2.0, Payload.NULL_VOLUME_AND_AMOUNT, 0.0, 1.0));
    }

    @Test
    void ZERO_PAYLOAD_BRICK_explicitZeros() {
        check(new RenkoBarAggregator(0.5, 2), series(100.0, 0.5, Payload.ZERO, 0.0, 1.0));
    }

    @Test
    void NEGATIVE_PRICE_LEVELS_upwardThenDownwardReversal() {
        check(new RenkoBarAggregator(2.0), series(-100.0, 2.0, Payload.ZERO, 0.0, 1.0, -1.0));
    }

    @Test
    void FRACTIONAL_POSITIVE_BOX_SIZE_halfUnitBricks() {
        List<Bar> bars = directSeries(Duration.ofSeconds(60), Payload.NULL_VOLUME,
                100.0, 100.5, 99.5);
        check(new RenkoBarAggregator(0.5, 2), bars);
    }

    @Test
    void MULTI_BRICK_TIME_EQUAL_NEXT_END_queuedTimestamp() {
        check(new RenkoBarAggregator(2.0, 2), series(100.0, 2.0, Payload.NULL_AMOUNT, 0.0, 3.0, 4.0));
    }

    @Test
    void DELAYED_SOURCE_END_OVERRIDES_QUEUED_TIME_laterSourceEnd() {
        check(new RenkoBarAggregator(2.0, 3),
                series(100.0, 2.0, Payload.NULL_VOLUME_AND_AMOUNT, 0.0, 3.0, 3.0, 3.0, 3.0, 4.0));
    }

    @Test
    void NON_SECOND_SOURCE_PERIOD_ninetySecondBars() {
        List<Bar> bars = offsetSeries(Duration.ofSeconds(90), 100.0, 2.0, Payload.STANDARD, 0.0, 1.0, 2.0);
        check(new RenkoBarAggregator(2.0, 2), bars);
    }
}
