import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static Num n(double value) {
        return org.ta4j.core.num.DecimalNum.valueOf(value);
    }

    private static Bar bar(int index, double close) {
        return bar(index, close, n(10), n(close * 10), 2L);
    }

    private static Bar bar(int index, double close, Num volume, Num amount, long trades) {
        Instant end = BASE.plus(PERIOD.multipliedBy(index + 1L));
        Instant begin = end.minus(PERIOD);
        Num closeNum = n(close);
        return new BaseBar(
                PERIOD,
                begin,
                end,
                n(close - 0.2),
                n(close + 0.3),
                n(close - 0.4),
                closeNum,
                volume,
                amount,
                trades);
    }

    private static List<Bar> bars(Bar... values) {
        return List.of(values);
    }

    private static void verify(
            RenkoBarAggregator aggregator,
            List<Bar> source,
            Number boxSize,
            int reversalAmount) {
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = generateFollowUp(boxSize, reversalAmount, source);
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(
            Number boxSize,
            int reversalAmount,
            List<Bar> source) {
        List<Bar> scaled = new ArrayList<>(source.size());
        for (Bar sourceBar : source) {
            Num factor = sourceBar.getClosePrice().getNumFactory().numOf(2.0);
            Num open = sourceBar.getOpenPrice() == null
                    ? null : sourceBar.getOpenPrice().multipliedBy(factor);
            Num high = sourceBar.getHighPrice() == null
                    ? null : sourceBar.getHighPrice().multipliedBy(factor);
            Num low = sourceBar.getLowPrice() == null
                    ? null : sourceBar.getLowPrice().multipliedBy(factor);
            Num close = sourceBar.getClosePrice().multipliedBy(factor);
            Num amount = sourceBar.getAmount() == null
                    ? null : sourceBar.getAmount().multipliedBy(factor);
            scaled.add(new BaseBar(
                    sourceBar.getTimePeriod(),
                    sourceBar.getBeginTime(),
                    sourceBar.getEndTime(),
                    open,
                    high,
                    low,
                    close,
                    sourceBar.getVolume(),
                    amount,
                    sourceBar.getTrades()));
        }
        return new Object[] {
                new RenkoBarAggregator(boxSize.doubleValue() * 2.0, reversalAmount),
                scaled
        };
    }

    @Test
    void EMPTY_SOURCE_LIST_1_empty() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        verify(aggregator, List.of(), 0.5, 1);
    }

    @Test
    void SINGLE_BAR_NO_MOVEMENT_1_single() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        verify(aggregator, bars(bar(0, 10.0)), 1.0, 2);
    }

    @Test
    void INITIAL_EXACT_UPWARD_BOX_1_boundary() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        verify(aggregator, bars(bar(0, 10.0), bar(1, 12.0)), 2.0, 3);
    }

    @Test
    void INITIAL_EXACT_DOWNWARD_BOX_1_boundary() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(3.5, 1);
        verify(aggregator, bars(bar(0, 20.0), bar(1, 16.5)), 3.5, 1);
    }

    @Test
    void INITIAL_MULTI_BRICK_UPWARD_MOVE_1_multi() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        verify(aggregator, bars(
                bar(0, 10.0, n(3), n(30), 3),
                bar(1, 11.5, n(5), n(55), 7)), 0.5, 2);
    }

    @Test
    void INITIAL_MULTI_BRICK_DOWNWARD_MOVE_1_multi() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 6.0, n(4), n(24), 5)), 1.0, 3);
    }

    @Test
    void FLAT_BARS_BEFORE_UPWARD_INITIALIZATION_1_flat() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 1);
        verify(aggregator, bars(
                bar(0, 10.0, n(1), n(10), 1),
                bar(1, 10.0, n(2), n(20), 2),
                bar(2, 12.0, n(3), n(36), 3)), 2.0, 1);
    }

    @Test
    void FLAT_BARS_BEFORE_DOWNWARD_INITIALIZATION_1_flat() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(3.5, 2);
        verify(aggregator, bars(
                bar(0, 20.0, n(1), n(20), 1),
                bar(1, 20.0, n(2), n(40), 2),
                bar(2, 16.5, n(3), n(49.5), 3)), 3.5, 2);
    }

    @Test
    void UP_SUB_BOX_CONTINUATION_1_subBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 3);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 10.5),
                bar(2, 10.7, n(4), n(42.8), 4)), 0.5, 3);
    }

    @Test
    void UP_EXACT_CONTINUATION_1_exact() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 11.0),
                bar(2, 12.0, n(2), n(24), 2)), 1.0, 1);
    }

    @Test
    void UP_MULTI_BRICK_CONTINUATION_1_multi() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 12.0),
                bar(2, 18.0, n(6), n(108), 6)), 2.0, 2);
    }

    @Test
    void DOWN_SUB_BOX_CONTINUATION_1_subBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(3.5, 3);
        verify(aggregator, bars(
                bar(0, 20.0),
                bar(1, 16.5),
                bar(2, 15.0, n(2), n(30), 2)), 3.5, 3);
    }

    @Test
    void DOWN_EXACT_CONTINUATION_1_exact() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 9.5),
                bar(2, 9.0, n(2), n(18), 2)), 0.5, 1);
    }

    @Test
    void DOWN_MULTI_BRICK_CONTINUATION_1_multi() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 9.0),
                bar(2, 5.0, n(5), n(25), 5)), 1.0, 2);
    }

    @Test
    void UP_EXACT_REVERSAL_THRESHOLD_1_boundary() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 12.0),
                bar(2, 6.0, n(3), n(18), 3)), 2.0, 3);
    }

    @Test
    void DOWN_EXACT_REVERSAL_THRESHOLD_1_boundary() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(3.5, 1);
        verify(aggregator, bars(
                bar(0, 20.0),
                bar(1, 16.5),
                bar(2, 20.0, n(3), n(60), 3)), 3.5, 1);
    }

    @Test
    void UP_REVERSAL_BEYOND_THRESHOLD_1_edge() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 10.5),
                bar(2, 8.0, n(4), n(32), 4)), 0.5, 2);
    }

    @Test
    void DOWN_REVERSAL_BEYOND_THRESHOLD_1_edge() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 9.0),
                bar(2, 14.0, n(4), n(56), 4)), 1.0, 3);
    }

    @Test
    void REVERSAL_AMOUNT_ONE_1_oneBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 1);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 12.0),
                bar(2, 10.0, n(2), n(20), 2)), 2.0, 1);
    }

    @Test
    void REVERSAL_AMOUNT_GREATER_THAN_ONE_1_delayed() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(3.5, 2);
        verify(aggregator, bars(
                bar(0, 20.0),
                bar(1, 23.5),
                bar(2, 20.0),
                bar(3, 16.5),
                bar(4, 13.5, n(2), n(27), 2)), 3.5, 2);
    }

    @Test
    void DEFAULT_TWO_BRICK_REVERSAL_CONFIGURATION_1_default() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 10.5),
                bar(2, 10.0),
                bar(3, 9.0, n(2), n(18), 2)), 0.5, 2);
    }

    @Test
    void PENDING_METADATA_ACROSS_NONEMITTING_BARS_1_pending() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        verify(aggregator, bars(
                bar(0, 10.0, n(1), n(10), 1),
                bar(1, 10.2, n(2), n(20), 2),
                bar(2, 10.7, n(3), n(30), 3),
                bar(3, 11.0, n(4), n(44), 4)), 1.0, 1);
    }

    @Test
    void NULL_VOLUME_METADATA_1_nullVolume() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        verify(aggregator, bars(
                bar(0, 10.0, null, n(10), 1),
                bar(1, 12.0, n(5), n(60), 5)), 2.0, 2);
    }

    @Test
    void NULL_AMOUNT_METADATA_1_nullAmount() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(3.5, 3);
        verify(aggregator, bars(
                bar(0, 20.0, n(2), null, 2),
                bar(1, 16.5, n(3), n(49.5), 3)), 3.5, 3);
    }

    @Test
    void ZERO_METADATA_1_zero() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        verify(aggregator, bars(
                bar(0, 10.0, n(0), n(0), 0),
                bar(1, 10.5, n(0), n(0), 0)), 0.5, 1);
    }

    @Test
    void BRICK_END_TIME_EQUALS_NEXT_END_1_equal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 11.0),
                bar(2, 12.0)), 1.0, 2);
    }

    @Test
    void BRICK_END_TIME_BEFORE_NEXT_END_1_before() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 16.0, n(4), n(64), 4)), 2.0, 3);
    }

    @Test
    void BRICK_END_TIME_AFTER_NEXT_END_1_after() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(3.5, 1);
        verify(aggregator, bars(
                bar(0, 20.0),
                bar(1, 27.0, n(3), n(81), 3),
                bar(2, 27.5),
                bar(3, 31.5, n(4), n(126), 4)), 3.5, 1);
    }

    @Test
    void NONTRIVIAL_SOURCE_OHLC_1_ohlc() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        verify(aggregator, bars(
                bar(0, 10.0, n(2), n(20), 2),
                bar(1, 11.0, n(3), n(33), 3),
                bar(2, 12.0, n(4), n(48), 4)), 0.5, 2);
    }

    @Test
    void ALTERNATING_DIRECTION_PHASES_1_alternating() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        verify(aggregator, bars(
                bar(0, 10.0),
                bar(1, 13.0),
                bar(2, 12.0, n(1), n(12), 1),
                bar(3, 10.0, n(2), n(20), 2),
                bar(4, 9.0, n(3), n(27), 3)), 1.0, 3);
    }
}
