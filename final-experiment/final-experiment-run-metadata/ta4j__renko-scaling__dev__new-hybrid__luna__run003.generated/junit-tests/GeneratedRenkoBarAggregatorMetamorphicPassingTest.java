import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofMillis(100);
    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private static List<Bar> bars(double... closes) {
        return bars(num(1), num(1), 1L, closes);
    }

    private static List<Bar> bars(Num volume, Num amount, long trades, double... closes) {
        Bar[] result = new Bar[closes.length];
        for (int i = 0; i < closes.length; i++) {
            Num close = num(closes[i]);
            Instant begin = START.plusMillis(i * 100L);
            Instant end = START.plusMillis((i + 1L) * 100L);
            result[i] = new BaseBar(PERIOD, begin, end, close, close, close, close, volume, amount, trades);
        }
        return List.of(result);
    }

    private static List<Bar> barsWithMetadata(double[] closes, Num[] volumes, Num[] amounts, long[] trades) {
        Bar[] result = new Bar[closes.length];
        for (int i = 0; i < closes.length; i++) {
            Num close = num(closes[i]);
            Instant begin = START.plusMillis(i * 100L);
            Instant end = START.plusMillis((i + 1L) * 100L);
            result[i] = new BaseBar(PERIOD, begin, end, close, close, close, close,
                    volumes[i], amounts[i], trades[i]);
        }
        return List.of(result);
    }

    @SuppressWarnings("unchecked")
    private static void verify(RenkoBarAggregator aggregator, List<Bar> source) {
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_SOURCE_LIST_variation1() {
        verify(new RenkoBarAggregator(0.5, 1), List.of());
    }

    @Test
    public void SINGLE_BAR_ANCHOR_variation1() {
        verify(new RenkoBarAggregator(1.0, 2),
                bars(null, num(2.5), 3L, -4.25));
    }

    @Test
    public void FLAT_CLOSE_SEQUENCE_variation1() {
        verify(new RenkoBarAggregator(2.0, 3),
                bars(num(1), null, 2L, 0, 0, 0));
    }

    @Test
    public void INITIAL_UP_EXACT_BOX_variation1() {
        verify(new RenkoBarAggregator(2.5, 1),
                bars(null, null, 4L, 5.0, 7.5));
    }

    @Test
    public void INITIAL_DOWN_EXACT_BOX_variation1() {
        verify(new RenkoBarAggregator(0.5, 2),
                bars(num(0), num(0), 0L, -5.0, -5.5));
    }

    @Test
    public void INITIAL_MOVE_JUST_BELOW_BOX_variation1() {
        verify(new RenkoBarAggregator(1.0, 3),
                bars(num(1.25), num(2.5), 1L, 0.0, 0.75, 0.75));
    }

    @Test
    public void UP_CONTINUATION_ONE_BRICK_PER_BAR_variation1() {
        verify(new RenkoBarAggregator(2.0, 1),
                bars(num(10), num(12), 2L, 10, 12, 14, 16));
    }

    @Test
    public void DOWN_CONTINUATION_ONE_BRICK_PER_BAR_variation1() {
        verify(new RenkoBarAggregator(2.5, 2),
                bars(num(2), num(3), 2L, -2, -4.5, -7, -9.5));
    }

    @Test
    public void MULTIPLE_UP_BRICKS_FROM_ONE_BAR_variation1() {
        verify(new RenkoBarAggregator(0.5, 3),
                bars(num(7), num(11), 5L, 0, 1.5));
    }

    @Test
    public void MULTIPLE_DOWN_BRICKS_FROM_ONE_BAR_variation1() {
        verify(new RenkoBarAggregator(1.0, 1),
                bars(num(8), num(13), 6L, 4, 1));
    }

    @Test
    public void UP_MOVE_BELOW_REVERSAL_THRESHOLD_variation1() {
        verify(new RenkoBarAggregator(2.0, 2),
                bars(num(1), num(2), 1L, 0, 2, -1.5));
    }

    @Test
    public void UP_MOVE_EXACT_REVERSAL_THRESHOLD_variation1() {
        verify(new RenkoBarAggregator(2.5, 3),
                bars(null, num(3), 2L, 0, 2.5, -5.0));
    }

    @Test
    public void DOWN_MOVE_EXACT_REVERSAL_THRESHOLD_variation1() {
        verify(new RenkoBarAggregator(0.5, 1),
                bars(null, num(2), 3L, 2, 1.5, 2.0));
    }

    @Test
    public void SINGLE_BOX_REVERSAL_CONFIGURATION_variation1() {
        verify(new RenkoBarAggregator(1.0, 1),
                bars(null, null, 1L, -3.5, -2.5, -3.5));
    }

    @Test
    public void MULTI_BRICK_REVERSAL_FROM_ONE_BAR_variation1() {
        verify(new RenkoBarAggregator(2.0, 2),
                bars(num(4), num(6), 3L, 0, 2, -6));
    }

    @Test
    public void PENDING_METADATA_ACROSS_NONEMITTING_BARS_variation1() {
        verify(new RenkoBarAggregator(2.5, 1),
                bars(num(2), num(3), 2L, 10, 12, 12.5));
    }

    @Test
    public void NULL_VOLUME_METADATA_PATH_variation1() {
        verify(new RenkoBarAggregator(0.5, 2),
                bars(null, num(4), 3L, -4, -4, -2.5, -2));
    }

    @Test
    public void NULL_AMOUNT_METADATA_PATH_variation1() {
        verify(new RenkoBarAggregator(1.0, 3),
                bars(num(5), null, 2L, 0, 0.5, 1));
    }

    @Test
    public void ZERO_METADATA_VALUES_variation1() {
        verify(new RenkoBarAggregator(2.0, 1),
                bars(num(0), num(0), 0L, 4, 8));
    }

    @Test
    public void SOURCE_END_TIME_SELECTED_variation1() {
        verify(new RenkoBarAggregator(2.5, 2),
                bars(num(1), num(1), 1L, -10, -10, -7.5));
    }

    @Test
    public void NEXT_SCHEDULED_END_TIME_SELECTED_variation1() {
        verify(new RenkoBarAggregator(0.5, 3),
                bars(num(2), num(3), 2L, 1, 2.5));
    }

    @Test
    public void NONUNIT_BOX_SIZE_AND_REVERSAL_variation1() {
        verify(new RenkoBarAggregator(2.5, 3),
                bars(null, num(1.5), 1L, 10, 12.5, 5));
    }

    @Test
    public void ZERO_CROSSING_PRICES_variation1() {
        verify(new RenkoBarAggregator(1.0, 2),
                bars(null, num(2), 1L, 1, 0, -1));
    }

    @Test
    public void NEGATIVE_PRICE_UPWARD_BRICKS_variation1() {
        verify(new RenkoBarAggregator(2.0, 3),
                bars(num(1), null, 1L, -10, -8, -6));
    }

    @Test
    public void FRACTIONAL_CLOSE_MOVEMENT_variation1() {
        verify(new RenkoBarAggregator(0.5, 1),
                bars(num(0), num(0), 0L, 1.25, 1.75, 2.25));
    }

    @Test
    public void EMISSION_AFTER_PRIOR_BRICK_METADATA_RESET_variation1() {
        verify(new RenkoBarAggregator(1.0, 2),
                barsWithMetadata(
                        new double[] {-5, -4, -3},
                        new Num[] {num(1), num(2), num(7)},
                        new Num[] {num(10), num(20), num(70)},
                        new long[] {1L, 2L, 7L}));
    }
}
