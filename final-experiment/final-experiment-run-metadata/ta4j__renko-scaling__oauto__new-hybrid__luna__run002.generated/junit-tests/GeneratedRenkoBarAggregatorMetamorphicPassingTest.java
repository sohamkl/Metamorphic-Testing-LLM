import java.lang.reflect.Field;
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

    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private static void run(RenkoBarAggregator aggregator, List<Bar> source) {
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(RenkoBarAggregator aggregator, List<Bar> source) {
        Number boxSize = readBoxSize(aggregator);
        int reversalAmount = readReversalAmount(aggregator);
        List<Bar> scaledBars = new ArrayList<>(source.size());

        for (Bar bar : source) {
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

        return new Object[] {
                new RenkoBarAggregator(boxSize.doubleValue() * 2.0, reversalAmount),
                scaledBars
        };
    }

    private static Number readBoxSize(RenkoBarAggregator aggregator) {
        try {
            Field field = RenkoBarAggregator.class.getDeclaredField("boxSize");
            field.setAccessible(true);
            return (Number) field.get(aggregator);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException(failure);
        }
    }

    private static int readReversalAmount(RenkoBarAggregator aggregator) {
        try {
            Field field = RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            field.setAccessible(true);
            return field.getInt(aggregator);
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException(failure);
        }
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(double... closes) {
        Num[] volumes = new Num[closes.length];
        Num[] amounts = new Num[closes.length];
        long[] trades = new long[closes.length];

        for (int i = 0; i < closes.length; i++) {
            volumes[i] = n(10 + i);
            amounts[i] = n(100 + i);
            trades[i] = i + 1;
        }

        return barsWithMetadata(closes, volumes, amounts, trades);
    }

    private static List<Bar> barsWithMetadata(
            double[] closes, Num[] volumes, Num[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);

        for (int i = 0; i < closes.length; i++) {
            Instant begin = START.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            Num close = n(closes[i]);

            result.add(new BaseBar(
                    PERIOD,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    volumes[i],
                    amounts[i],
                    trades[i]));
        }

        return result;
    }

    private static Num n(double value) {
        return org.ta4j.core.num.DecimalNum.valueOf(value);
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        run(new RenkoBarAggregator(2), List.of());
    }

    @Test
    void SINGLE_BAR_NO_MOVEMENT_variation1() {
        run(new RenkoBarAggregator(0.5, 1), bars(10));
    }

    @Test
    void MULTI_BAR_FLAT_CLOSES_variation1() {
        run(new RenkoBarAggregator(1.25, 2), bars(10, 10));
    }

    @Test
    void INITIAL_UP_EXACT_ONE_BOX_variation1() {
        run(new RenkoBarAggregator(2, 3), bars(10, 12, 12));
    }

    @Test
    void INITIAL_UP_JUST_BELOW_BOUNDARY_variation1() {
        run(new RenkoBarAggregator(0.5, 4), bars(10, 10.25, 10.3, 10.4, 10.45));
    }

    @Test
    void INITIAL_DOWN_EXACT_ONE_BOX_variation1() {
        run(new RenkoBarAggregator(1.25), bars(10, 8.75, 8.75));
    }

    @Test
    void INITIAL_UP_MULTIPLE_BRICKS_variation1() {
        run(new RenkoBarAggregator(2, 1), bars(10, 16));
    }

    @Test
    void INITIAL_DOWN_MULTIPLE_BRICKS_variation1() {
        run(new RenkoBarAggregator(0.5, 2), bars(10, 4));
    }

    @Test
    void UP_CONTINUATION_EXACT_BOUNDARY_variation1() {
        run(new RenkoBarAggregator(1.25, 3), bars(10, 11.25, 12.5, 12.5));
    }

    @Test
    void DOWN_CONTINUATION_EXACT_BOUNDARY_variation1() {
        run(new RenkoBarAggregator(2.5, 4), bars(10, 7.5, 5));
    }

    @Test
    void UP_REVERSAL_EXACT_THRESHOLD_variation1() {
        run(new RenkoBarAggregator(0.5), bars(10, 10.5, 9.5));
    }

    @Test
    void UP_REVERSAL_JUST_BELOW_THRESHOLD_variation1() {
        run(new RenkoBarAggregator(1.25, 1), bars(10, 11.25, 11));
    }

    @Test
    void DOWN_REVERSAL_EXACT_THRESHOLD_variation1() {
        run(new RenkoBarAggregator(2, 2), bars(10, 8, 12));
    }

    @Test
    void DOWN_REVERSAL_JUST_BELOW_THRESHOLD_variation1() {
        run(new RenkoBarAggregator(0.5, 3), bars(10, 9.5, 9.75));
    }

    @Test
    void REVERSAL_AMOUNT_ONE_variation1() {
        run(new RenkoBarAggregator(1.25, 1), bars(10, 11.25, 10));
    }

    @Test
    void REVERSAL_AMOUNT_GREATER_THAN_TWO_variation1() {
        run(new RenkoBarAggregator(2, 4), bars(10, 12, 4));
    }

    @Test
    void FLAT_CLOSE_WHILE_UP_variation1() {
        run(new RenkoBarAggregator(0.5, 1), bars(10, 10.5, 10.5));
    }

    @Test
    void FLAT_CLOSE_WHILE_DOWN_variation1() {
        run(new RenkoBarAggregator(1.25, 2), bars(10, 8.75, 8.75));
    }

    @Test
    void PENDING_METADATA_BEFORE_FIRST_BRICK_variation1() {
        run(new RenkoBarAggregator(2, 3), bars(10, 10.5, 11, 12));
    }

    @Test
    void MULTIPLE_BRICKS_METADATA_ZEROING_variation1() {
        run(new RenkoBarAggregator(0.5, 4), bars(10, 11.5));
    }

    @Test
    void NULL_VOLUME_METADATA_variation1() {
        run(new RenkoBarAggregator(1.25), barsWithMetadata(new double[] {10, 11.25},
                new Num[] {null, null}, new Num[] {n(3), n(4)}, new long[] {2, 3}));
    }

    @Test
    void NULL_AMOUNT_METADATA_variation1() {
        run(new RenkoBarAggregator(2, 1), barsWithMetadata(new double[] {10, 12},
                new Num[] {n(5), n(6)}, new Num[] {null, null}, new long[] {1, 2}));
    }

    @Test
    void ZERO_METADATA_AND_TRADES_variation1() {
        run(new RenkoBarAggregator(0.5, 2), barsWithMetadata(new double[] {10, 10.5},
                new Num[] {n(0), n(0)}, new Num[] {n(0), n(0)}, new long[] {0, 0}));
    }

    @Test
    void CONTIGUOUS_MULTI_BAR_TIMING_variation1() {
        run(new RenkoBarAggregator(1.25, 3), bars(10, 11.25, 11.25, 12.5));
    }

    @Test
    void MULTIPLE_BRICKS_SCHEDULED_AFTER_SOURCE_END_variation1() {
        run(new RenkoBarAggregator(2, 4), bars(10, 16, 16, 16, 16));
    }

    @Test
    void DEFAULT_TWO_BRICK_REVERSAL_variation1() {
        run(new RenkoBarAggregator(0.5), bars(10, 10.5, 9));
    }

    @Test
    void FRACTIONAL_BOX_SCALING_variation1() {
        run(new RenkoBarAggregator(0.25, 1), bars(10, 10.25));
    }

    @Test
    void MIXED_UP_DOWN_UP_PATH_variation1() {
        run(new RenkoBarAggregator(2, 2), bars(10, 12, 8, 6, 10));
    }

    @Test
    void LONG_NO_BRICK_PREFIX_THEN_REVERSAL_variation1() {
        run(new RenkoBarAggregator(0.5, 3), bars(10, 10.1, 10.2, 11.5, 10));
    }
}
