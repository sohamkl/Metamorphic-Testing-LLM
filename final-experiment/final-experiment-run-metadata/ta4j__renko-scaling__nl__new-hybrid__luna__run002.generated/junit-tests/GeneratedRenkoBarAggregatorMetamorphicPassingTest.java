import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.Num;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static void assertMetamorphicRelationFor(double boxSize, int reversalAmount,
            List<Bar> sourceBars, int metadataMode) {
        RenkoBarAggregator sourceReceiver =
                new RenkoBarAggregator(BigDecimal.valueOf(boxSize), reversalAmount);
        RenkoBarAggregator followUpReceiver =
                new RenkoBarAggregator(BigDecimal.valueOf(boxSize).multiply(BigDecimal.valueOf(2)),
                        reversalAmount);

        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);
        List<Bar> followUpOutput =
                followUpReceiver.aggregate(generateFollowUp(sourceBars));

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> generateFollowUp(List<Bar> source) {
        List<Bar> result = new ArrayList<>();
        for (Bar bar : source) {
            result.add(new BaseBar(
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
        return result;
    }

    private static void assertMetamorphicRelation(List<Bar> source, List<Bar> followUp) {
        assertEquals(source.size(), followUp.size());

        for (int i = 0; i < source.size(); i++) {
            Bar left = source.get(i);
            Bar right = followUp.get(i);

            assertEquals(left.getBeginTime(), right.getBeginTime());
            assertEquals(left.getEndTime(), right.getEndTime());
            assertEquals(left.getTimePeriod(), right.getTimePeriod());
            assertEquals(left.getTrades(), right.getTrades());

            assertSameNum(left.getVolume(), right.getVolume());
            assertScaled(left.getOpenPrice(), right.getOpenPrice());
            assertScaled(left.getHighPrice(), right.getHighPrice());
            assertScaled(left.getLowPrice(), right.getLowPrice());
            assertScaled(left.getClosePrice(), right.getClosePrice());
            assertScaled(left.getAmount(), right.getAmount());

            boolean leftUp = left.getClosePrice().isGreaterThan(left.getOpenPrice());
            boolean rightUp = right.getClosePrice().isGreaterThan(right.getOpenPrice());
            assertEquals(leftUp, rightUp);
        }
    }

    private static void assertScaled(Num source, Num followUp) {
        if (source == null) {
            assertEquals(null, followUp);
        } else {
            assertNotNull(followUp);
            assertEquals(0, scale(source).compareTo(followUp));
        }
    }

    private static void assertSameNum(Num source, Num followUp) {
        if (source == null) {
            assertEquals(null, followUp);
        } else {
            assertNotNull(followUp);
            assertEquals(0, source.compareTo(followUp));
        }
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(decimal(2));
    }

    private static List<Bar> bars(double[] closes, int mode) {
        List<Bar> result = new ArrayList<>();
        Num previous = null;

        for (int i = 0; i < closes.length; i++) {
            Num close = decimal(closes[i]);
            Num open = previous == null ? close : previous;
            Num high = open.max(close);
            Num low = open.min(close);

            Num volume = mode == 3 ? decimal(0) : decimal(i + 1);
            Num amount = decimal((i + 1) * 10);
            long trades = mode == 3 ? 0L : i + 1L;

            if (mode == 1 && i == closes.length - 1) {
                volume = null;
            }
            if (mode == 2 && i == 1) {
                amount = null;
            }

            result.add(new BaseBar(
                    PERIOD,
                    BASE.plus(PERIOD.multipliedBy(i)),
                    BASE.plus(PERIOD.multipliedBy(i + 1)),
                    open,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    trades));
            previous = close;
        }
        return result;
    }

    private static Num decimal(double value) {
        return org.ta4j.core.num.DecimalNum.valueOf(BigDecimal.valueOf(value));
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        assertMetamorphicRelationFor(0.5, 1, Collections.emptyList(), 0);
    }

    @Test
    void SINGLE_BAR_BASELINE_variation1() {
        assertMetamorphicRelationFor(1, 2, bars(new double[] { 100 }, 0), 0);
    }

    @Test
    void MOVEMENT_BELOW_FIRST_THRESHOLD_variation1() {
        assertMetamorphicRelationFor(2, 3, bars(new double[] { 100, 101 }, 1), 1);
    }

    @Test
    void INITIAL_UPWARD_EXACT_THRESHOLD_variation1() {
        assertMetamorphicRelationFor(10, 1, bars(new double[] { 100, 110, 110 }, 0), 0);
    }

    @Test
    void INITIAL_DOWNWARD_EXACT_THRESHOLD_variation1() {
        assertMetamorphicRelationFor(0.5, 2, bars(new double[] { 100, 99.5, 99.5 }, 0), 0);
    }

    @Test
    void INITIAL_UPWARD_MULTI_BRICK_variation1() {
        assertMetamorphicRelationFor(1, 3, bars(new double[] { 100, 104 }, 0), 0);
    }

    @Test
    void INITIAL_DOWNWARD_MULTI_BRICK_variation1() {
        assertMetamorphicRelationFor(2, 1, bars(new double[] { 100, 92 }, 0), 0);
    }

    @Test
    void CONTINUED_UP_DIRECTION_NO_EMISSION_variation1() {
        assertMetamorphicRelationFor(10, 2, bars(new double[] { 100, 110, 115 }, 0), 0);
    }

    @Test
    void CONTINUED_DOWN_DIRECTION_NO_EMISSION_variation1() {
        assertMetamorphicRelationFor(0.5, 3, bars(new double[] { 100, 99.5, 99.25 }, 0), 0);
    }

    @Test
    void UP_TO_DOWN_EXACT_REVERSAL_variation1() {
        assertMetamorphicRelationFor(1, 2, bars(new double[] { 100, 101, 99 }, 0), 0);
    }

    @Test
    void UP_TO_DOWN_BELOW_REVERSAL_variation1() {
        assertMetamorphicRelationFor(2, 2, bars(new double[] { 100, 104, 101 }, 0), 0);
    }

    @Test
    void DOWN_TO_UP_EXACT_REVERSAL_variation1() {
        assertMetamorphicRelationFor(10, 3, bars(new double[] { 100, 70, 130 }, 0), 0);
    }

    @Test
    void DOWN_TO_UP_BELOW_REVERSAL_variation1() {
        assertMetamorphicRelationFor(0.5, 3, bars(new double[] { 100, 99.5, 100.25 }, 0), 0);
    }

    @Test
    void ONE_BOX_REVERSAL_CONFIGURATION_variation1() {
        assertMetamorphicRelationFor(1, 1, bars(new double[] { 100, 101, 100 }, 0), 0);
    }

    @Test
    void MULTI_BOX_REVERSAL_WITH_CONTINUATION_variation1() {
        assertMetamorphicRelationFor(2, 3, bars(new double[] { 100, 108, 94, 90 }, 0), 0);
    }

    @Test
    void PENDING_METADATA_ACROSS_NONEMITTING_BARS_variation1() {
        assertMetamorphicRelationFor(10, 1, bars(new double[] { 100, 105, 110 }, 0), 0);
    }

    @Test
    void NULL_VOLUME_ON_EMITTING_INPUT_variation1() {
        assertMetamorphicRelationFor(0.5, 2, bars(new double[] { 100, 100.25, 101 }, 1), 1);
    }

    @Test
    void NULL_AMOUNT_ON_NONEMITTING_BAR_variation1() {
        assertMetamorphicRelationFor(1, 3, bars(new double[] { 100, 100.5, 101, 103 }, 2), 2);
    }

    @Test
    void MULTIPLE_BRICKS_TIMESTAMP_CLAMP_variation1() {
        assertMetamorphicRelationFor(2, 1, bars(new double[] { 100, 108 }, 0), 0);
    }

    @Test
    void LATER_SOURCE_END_TIME_SELECTION_variation1() {
        assertMetamorphicRelationFor(10, 2, bars(new double[] { 100, 120, 140 }, 0), 0);
    }

    @Test
    void MIXED_UP_DOWN_SEQUENCE_variation1() {
        assertMetamorphicRelationFor(10, 1, bars(new double[] { 100, 110, 90, 80, 100 }, 0), 0);
    }

    @Test
    void FRACTIONAL_BOX_AND_PRICE_SCALE_variation1() {
        assertMetamorphicRelationFor(0.5, 1,
                bars(new double[] { 10.25, 10.75, 10.99, 11.25 }, 0), 0);
    }

    @Test
    void ZERO_TRADE_AND_ZERO_VOLUME_METADATA_variation1() {
        assertMetamorphicRelationFor(2, 2, bars(new double[] { 100, 104 }, 3), 3);
    }

    @Test
    void LARGE_SINGLE_BAR_EMISSION_variation1() {
        assertMetamorphicRelationFor(10, 3, bars(new double[] { 100, 160 }, 0), 0);
    }

    @Test
    void ALTERNATING_NONEMITTING_AND_EMITTING_BARS_variation1() {
        assertMetamorphicRelationFor(0.5, 1,
                bars(new double[] { 100, 100.25, 100.5, 100.25, 99.5, 100 }, 0), 0);
    }
}
