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

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static void verify(Number boxSize, int reversalAmount, List<Bar> sourceBars) {
        RenkoBarAggregator sourceAggregator = new RenkoBarAggregator(boxSize, reversalAmount);
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);

        FollowUpInput followUp = generateFollowUp(boxSize, reversalAmount, sourceBars);
        List<Bar> followUpOutput = followUp.aggregator.aggregate(followUp.bars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static FollowUpInput generateFollowUp(Number boxSize, int reversalAmount, List<Bar> sourceBars) {
        List<Bar> scaledBars = new ArrayList<>(sourceBars.size());
        for (Bar source : sourceBars) {
            scaledBars.add(new BaseBar(
                    source.getTimePeriod(),
                    source.getBeginTime(),
                    source.getEndTime(),
                    scale(source.getOpenPrice()),
                    scale(source.getHighPrice()),
                    scale(source.getLowPrice()),
                    scale(source.getClosePrice()),
                    source.getVolume(),
                    scale(source.getAmount()),
                    source.getTrades()));
        }
        return new FollowUpInput(
                new RenkoBarAggregator(boxSize.doubleValue() * 2.0, reversalAmount),
                scaledBars);
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(value.getNumFactory().numOf(2.0));
    }

    private static List<Bar> bars(double... closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int index = 0; index < closes.length; index++) {
            volumes[index] = 1.0;
            amounts[index] = 2.0;
            trades[index] = 3L;
        }
        return barsWithMetadata(closes, volumes, amounts, trades);
    }

    private static List<Bar> barsWithMetadata(double[] closes, Double[] volumes, Double[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        Duration period = Duration.ofMinutes(1);
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        for (int index = 0; index < closes.length; index++) {
            Num close = num(closes[index]);
            Instant begin = start.plus(period.multipliedBy(index));
            Instant end = begin.plus(period);
            result.add(new BaseBar(
                    period,
                    begin,
                    end,
                    close,
                    num(closes[index] + 1.0),
                    num(closes[index] - 1.0),
                    close,
                    volumes[index] == null ? null : num(volumes[index]),
                    amounts[index] == null ? null : num(amounts[index]),
                    trades[index]));
        }
        return result;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    private static final class FollowUpInput {
        private final RenkoBarAggregator aggregator;
        private final List<Bar> bars;

        private FollowUpInput(RenkoBarAggregator aggregator, List<Bar> bars) {
            this.aggregator = aggregator;
            this.bars = bars;
        }
    }

    @Test
    void EMPTY_SOURCE_variation1() {
        verify(0.5, 1, List.of());
    }

    @Test
    void SINGLE_SOURCE_BAR_variation1() {
        verify(1, 2, bars(10));
    }

    @Test
    void MULTI_BAR_FLAT_CLOSE_variation1() {
        verify(2.5, 3, bars(10, 10, 10, 10));
    }

    @Test
    void SUB_BOX_UPWARD_MOVE_variation1() {
        verify(1.25, 4, bars(10, 11.249));
    }

    @Test
    void SUB_BOX_DOWNWARD_MOVE_variation1() {
        verify(0.5, 1, bars(10, 9.501));
    }

    @Test
    void INITIAL_UP_EXACTLY_ONE_BOX_variation1() {
        verify(1, 2, bars(10, 11));
    }

    @Test
    void INITIAL_UP_MULTIPLE_BOXES_variation1() {
        verify(1, 3, bars(10, 13));
    }

    @Test
    void INITIAL_DOWN_EXACTLY_ONE_BOX_variation1() {
        verify(1, 4, bars(10, 9));
    }

    @Test
    void INITIAL_DOWN_MULTIPLE_BOXES_variation1() {
        verify(1, 1, bars(10, 7));
    }

    @Test
    void DEFERRED_UPWARD_THRESHOLD_variation1() {
        verify(1, 2, bars(10, 10.4, 10.8, 11));
    }

    @Test
    void DEFERRED_DOWNWARD_THRESHOLD_variation1() {
        verify(1, 2, bars(10, 9.6, 9.2, 9));
    }

    @Test
    void UP_CONTINUATION_EXACT_BOX_variation1() {
        verify(1, 4, bars(10, 11, 12));
    }

    @Test
    void UP_CONTINUATION_MULTIPLE_BOXES_variation1() {
        verify(1, 2, bars(10, 11, 14));
    }

    @Test
    void UP_PULLBACK_WITHIN_ONE_BOX_variation1() {
        verify(1, 2, bars(10, 11, 10.001));
    }

    @Test
    void UP_REVERSAL_JUST_SHORT_OF_TWO_BOXES_variation1() {
        verify(1, 2, bars(10, 11, 9.001));
    }

    @Test
    void UP_REVERSAL_EXACTLY_TWO_BOXES_variation1() {
        verify(1, 2, bars(10, 11, 9));
    }

    @Test
    void UP_REVERSAL_OVERSHOOTS_THRESHOLD_variation1() {
        verify(1, 2, bars(10, 11, 8));
    }

    @Test
    void DOWN_CONTINUATION_EXACT_BOX_variation1() {
        verify(1, 2, bars(10, 9, 8));
    }

    @Test
    void DOWN_CONTINUATION_MULTIPLE_BOXES_variation1() {
        verify(1, 3, bars(10, 9, 6));
    }

    @Test
    void DOWN_BOUNCE_WITHIN_ONE_BOX_variation1() {
        verify(1, 2, bars(10, 9, 9.999));
    }

    @Test
    void DOWN_REVERSAL_JUST_SHORT_OF_TWO_BOXES_variation1() {
        verify(1, 2, bars(10, 9, 10.999));
    }

    @Test
    void DOWN_REVERSAL_EXACTLY_TWO_BOXES_variation1() {
        verify(1, 2, bars(10, 9, 11));
    }

    @Test
    void DOWN_REVERSAL_OVERSHOOTS_THRESHOLD_variation1() {
        verify(1, 2, bars(10, 9, 12));
    }

    @Test
    void ONE_BOX_REVERSAL_FROM_UP_variation1() {
        verify(1, 1, bars(10, 11, 10));
    }

    @Test
    void ONE_BOX_REVERSAL_FROM_DOWN_variation1() {
        verify(1, 1, bars(10, 9, 10));
    }

    @Test
    void THREE_BOX_REVERSAL_JUST_SHORT_variation1() {
        verify(1, 3, bars(10, 11, 8.001));
    }

    @Test
    void THREE_BOX_REVERSAL_EXACT_variation1() {
        verify(1, 3, bars(10, 11, 8));
    }

    @Test
    void FOUR_BOX_REVERSAL_EXACT_variation1() {
        verify(1, 4, bars(10, 11, 7));
    }

    @Test
    void DECIMAL_BOX_EXACT_UPWARD_variation1() {
        verify(2.5, 2, bars(10, 12.5));
    }

    @Test
    void HALF_BOX_MULTIPLE_UPWARD_variation1() {
        verify(0.5, 2, bars(10, 11.5));
    }

    @Test
    void FIRST_OF_MULTIPLE_BRICKS_GETS_METADATA_variation1() {
        verify(1, 2, barsWithMetadata(
                new double[] {10, 13},
                new Double[] {1.0, 7.0},
                new Double[] {2.0, 11.0},
                new long[] {3, 13}));
    }

    @Test
    void ACCUMULATED_METADATA_ON_DELAYED_FIRST_BRICK_variation1() {
        verify(1, 2, barsWithMetadata(
                new double[] {10, 10.25, 10.75, 11},
                new Double[] {1.0, 2.0, 3.0, 4.0},
                new Double[] {2.0, 4.0, 6.0, 8.0},
                new long[] {3, 5, 7, 9}));
    }

    @Test
    void PENDING_METADATA_RESTARTS_AFTER_EMISSION_variation1() {
        verify(1, 2, barsWithMetadata(
                new double[] {10, 11, 11.5, 12},
                new Double[] {1.0, 2.0, 3.0, 4.0},
                new Double[] {2.0, 3.0, 5.0, 7.0},
                new long[] {1, 2, 3, 4}));
    }

    @Test
    void NULL_VOLUME_IS_IGNORED_variation1() {
        verify(1, 2, barsWithMetadata(
                new double[] {10, 11},
                new Double[] {null, 7.0},
                new Double[] {2.0, 2.0},
                new long[] {3, 3}));
    }

    @Test
    void NULL_AMOUNT_IS_IGNORED_variation1() {
        verify(1, 2, barsWithMetadata(
                new double[] {10, 11},
                new Double[] {1.0, 1.0},
                new Double[] {null, 7.0},
                new long[] {3, 3}));
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_variation1() {
        verify(1, 2, barsWithMetadata(
                new double[] {10, 11},
                new Double[] {null, null},
                new Double[] {null, null},
                new long[] {3, 4}));
    }

    @Test
    void ZERO_METADATA_variation1() {
        verify(1, 2, barsWithMetadata(
                new double[] {10, 11},
                new Double[] {0.0, 0.0},
                new Double[] {0.0, 0.0},
                new long[] {0, 0}));
    }

    @Test
    void MIXED_NULL_AND_NON_NULL_METADATA_BEFORE_EMISSION_variation1() {
        verify(1, 2, barsWithMetadata(
                new double[] {10, 10.5, 11},
                new Double[] {2.0, null, 7.0},
                new Double[] {null, 5.0, 11.0},
                new long[] {1, 2, 3}));
    }

    @Test
    void FIRST_BRICK_ENDS_AT_LATER_SOURCE_END_variation1() {
        verify(1, 2, barsWithMetadata(
                new double[] {10, 11},
                new Double[] {4.0, 5.0},
                new Double[] {6.0, 7.0},
                new long[] {8, 9}));
    }

    @Test
    void DELAYED_FIRST_BRICK_TIME_variation1() {
        verify(1, 2, bars(10, 10.25, 11));
    }

    @Test
    void EQUAL_SCHEDULED_AND_SOURCE_END_TIME_variation1() {
        verify(1, 2, bars(10, 11, 12));
    }

    @Test
    void SAME_SOURCE_SECOND_BRICK_USES_FUTURE_SCHEDULED_TIME_variation1() {
        verify(1, 2, barsWithMetadata(
                new double[] {10, 12},
                new Double[] {1.0, 9.0},
                new Double[] {2.0, 10.0},
                new long[] {3, 11}));
    }

    @Test
    void ZERO_PRICE_LEVEL_variation1() {
        verify(1, 2, bars(0, 1));
    }

    @Test
    void NEGATIVE_PRICE_LEVEL_variation1() {
        verify(1, 2, bars(-2, -1));
    }

    @Test
    void NEGATIVE_TO_POSITIVE_MULTIBRICK_MOVE_variation1() {
        verify(1, 2, bars(-1, 2));
    }
}
