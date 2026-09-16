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

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

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

    @Test
    void EMPTY_LIST_variation1() {
        run(1.0, 2, new double[0]);
    }

    @Test
    void SINGLE_ANCHOR_BAR_variation1() {
        run(1.0, 2, new double[] { 10.0 });
    }

    @Test
    void MULTI_BAR_NO_THRESHOLD_variation1() {
        run(1.0, 1, new double[] { 10.0, 10.4, 9.6, 10.2 });
    }

    @Test
    void MULTI_BAR_NO_THRESHOLD_variation2() {
        run(0.5, 3, new double[] { -2.0, -1.7, -2.3, -1.8 });
    }

    @Test
    void INITIAL_UP_EXACTLY_ONE_BOX_variation1() {
        run(1.0, 2, new double[] { 10.0, 11.0 });
    }

    @Test
    void INITIAL_UP_EXACTLY_ONE_BOX_variation2() {
        run(0.5, 3, new double[] { -1.0, -0.5 });
    }

    @Test
    void INITIAL_UP_JUST_BELOW_BOX_variation1() {
        run(1.0, 2, new double[] { 10.0, 10.999 });
    }

    @Test
    void INITIAL_UP_MULTIPLE_BRICKS_variation1() {
        run(1.0, 2, new double[] { 5.0, 8.0 });
    }

    @Test
    void INITIAL_UP_MULTIPLE_BRICKS_variation2() {
        run(0.5, 1, new double[] { -2.0, 0.0 });
    }

    @Test
    void INITIAL_DOWN_EXACTLY_ONE_BOX_variation1() {
        run(2.5, 2, new double[] { 10.0, 7.5 });
    }

    @Test
    void INITIAL_DOWN_EXACTLY_ONE_BOX_variation2() {
        run(1.0, 3, new double[] { -3.0, -4.0 });
    }

    @Test
    void INITIAL_DOWN_MULTIPLE_BRICKS_variation1() {
        run(0.5, 2, new double[] { 4.0, 2.0 });
    }

    @Test
    void INITIAL_DOWN_MULTIPLE_BRICKS_variation2() {
        run(2.5, 1, new double[] { 10.0, 2.5 });
    }

    @Test
    void UP_CONTINUATION_EXACT_BOX_variation1() {
        run(1.0, 2, new double[] { 10.0, 11.0, 12.0 });
    }

    @Test
    void UP_CONTINUATION_MULTIPLE_BOXES_variation1() {
        run(0.5, 3, new double[] { 1.0, 1.5, 3.0 });
    }

    @Test
    void UP_CONTINUATION_MULTIPLE_BOXES_variation2() {
        run(2.5, 2, new double[] { -5.0, -2.5, 5.0 });
    }

    @Test
    void UP_PULLBACK_BELOW_REVERSAL_variation1() {
        run(1.0, 2, new double[] { 10.0, 11.0, 10.0 });
    }

    @Test
    void UP_REVERSAL_AT_EXACT_DISTANCE_variation1() {
        run(0.5, 2, new double[] { 0.0, 0.5, -0.5 });
    }

    @Test
    void UP_REVERSAL_AT_EXACT_DISTANCE_variation2() {
        run(2.5, 3, new double[] { 20.0, 22.5, 15.0 });
    }

    @Test
    void UP_REVERSAL_BEYOND_DISTANCE_variation1() {
        run(1.0, 2, new double[] { 10.0, 11.0, 7.0 });
    }

    @Test
    void DOWN_CONTINUATION_EXACT_BOX_variation1() {
        run(0.5, 2, new double[] { 3.0, 2.5, 2.0 });
    }

    @Test
    void DOWN_CONTINUATION_MULTIPLE_BOXES_variation1() {
        run(1.0, 3, new double[] { 8.0, 7.0, 4.0 });
    }

    @Test
    void DOWN_CONTINUATION_MULTIPLE_BOXES_variation2() {
        run(2.5, 2, new double[] { 5.0, 2.5, -2.5 });
    }

    @Test
    void DOWN_BOUNCE_BELOW_REVERSAL_variation1() {
        run(1.0, 3, new double[] { 10.0, 9.0, 11.0 });
    }

    @Test
    void DOWN_REVERSAL_AT_EXACT_DISTANCE_variation1() {
        run(2.5, 2, new double[] { 10.0, 7.5, 12.5 });
    }

    @Test
    void DOWN_REVERSAL_AT_EXACT_DISTANCE_variation2() {
        run(0.5, 3, new double[] { 0.0, -0.5, 1.0 });
    }

    @Test
    void DOWN_REVERSAL_BEYOND_DISTANCE_variation1() {
        run(1.0, 2, new double[] { 5.0, 4.0, 8.0 });
    }

    @Test
    void ONE_BOX_REVERSAL_FROM_UP_variation1() {
        run(1.0, 1, new double[] { 2.0, 3.0, 2.0 });
    }

    @Test
    void ONE_BOX_REVERSAL_FROM_DOWN_variation1() {
        run(2.5, 1, new double[] { 5.0, 2.5, 5.0 });
    }

    @Test
    void THREE_BOX_REVERSAL_NOT_REACHED_variation1() {
        run(0.5, 3, new double[] { 0.0, 0.5, -0.5 });
    }

    @Test
    void THREE_BOX_REVERSAL_EXACTLY_REACHED_variation1() {
        run(1.0, 3, new double[] { 10.0, 11.0, 8.0 });
    }

    @Test
    void PENDING_METADATA_ACROSS_NON_EMITTING_BARS_variation1() {
        run(1.0, 2,
                new double[] { 10.0, 10.3, 10.7, 11.0 },
                new Double[] { 2.0, 3.0, 4.0, 5.0 },
                new Double[] { 20.0, 30.0, 40.0, 50.0 },
                new long[] { 1, 2, 3, 4 });
    }

    @Test
    void PENDING_METADATA_ACROSS_NON_EMITTING_BARS_variation2() {
        run(0.5, 1,
                new double[] { -1.0, -0.8, -0.6, -0.5 },
                new Double[] { 1.0, 0.0, 2.0, 3.0 },
                new Double[] { 4.0, 5.0, 0.0, 7.0 },
                new long[] { 2, 1, 4, 3 });
    }

    @Test
    void MULTI_BRICK_METADATA_ZEROING_variation1() {
        run(1.0, 2,
                new double[] { 1.0, 4.0 },
                new Double[] { 5.0, 7.0 },
                new Double[] { 50.0, 70.0 },
                new long[] { 3, 4 });
    }

    @Test
    void MULTI_BRICK_METADATA_ZEROING_variation2() {
        run(0.5, 1,
                new double[] { -2.0, 0.0 },
                new Double[] { 0.0, 9.0 },
                new Double[] { 0.0, 90.0 },
                new long[] { 2, 5 });
    }

    @Test
    void NULL_VOLUME_WITH_EMISSION_variation1() {
        run(0.5, 2,
                new double[] { 1.0, 1.2, 1.5 },
                new Double[] { null, 4.0, null },
                new Double[] { 10.0, 20.0, 30.0 },
                new long[] { 1, 2, 3 });
    }

    @Test
    void NULL_AMOUNT_WITH_EMISSION_variation1() {
        run(1.0, 2,
                new double[] { 2.0, 2.4, 3.0 },
                new Double[] { 2.0, 3.0, 4.0 },
                new Double[] { null, 30.0, null },
                new long[] { 4, 3, 2 });
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_WITH_EMISSION_variation1() {
        run(2.5, 2,
                new double[] { 0.0, 2.5 },
                new Double[] { null, null },
                new Double[] { null, null },
                new long[] { 2, 5 });
    }

    @Test
    void ZERO_METADATA_VALUES_variation1() {
        run(0.5, 3,
                new double[] { 4.0, 4.5 },
                new Double[] { 0.0, 0.0 },
                new Double[] { 0.0, 0.0 },
                new long[] { 3, 7 });
    }

    @Test
    void DELAYED_FIRST_EMISSION_TIMESTAMP_variation1() {
        run(1.0, 2,
                new double[] { 10.0, 10.2, 10.8, 11.0 },
                new Double[] { 1.0, 2.0, 3.0, 4.0 },
                new Double[] { 10.0, 20.0, 30.0, 40.0 },
                new long[] { 1, 1, 1, 1 });
    }

    @Test
    void SYNTHETIC_TIMESTAMPS_AFTER_MULTI_BRICK_BAR_variation1() {
        run(1.0, 2,
                new double[] { 0.0, 3.0, 4.0 },
                new Double[] { 1.0, 2.0, 3.0 },
                new Double[] { 10.0, 20.0, 30.0 },
                new long[] { 1, 2, 3 });
    }

    @Test
    void NEGATIVE_PRICE_ANCHOR_variation1() {
        run(0.5, 2, new double[] { -3.0, -2.0, -3.0 });
    }

    @Test
    void FRACTIONAL_BOX_EXACT_MULTIPLES_variation1() {
        run(0.5, 2, new double[] { 1.0, 2.5 });
    }

    @Test
    void FRACTIONAL_BOX_EXACT_MULTIPLES_variation2() {
        run(2.5, 1, new double[] { -5.0, -12.5 });
    }
}
