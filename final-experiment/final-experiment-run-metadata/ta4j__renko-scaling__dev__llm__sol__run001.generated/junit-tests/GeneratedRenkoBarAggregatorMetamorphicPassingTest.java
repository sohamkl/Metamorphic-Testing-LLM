import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNumFactory;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.NumFactory;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

@SuppressWarnings("unchecked")
public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_END = Instant.parse("2024-01-01T00:01:00Z");
    private static final NumFactory NUM_FACTORY = DecimalNumFactory.getInstance();

    private static List<Bar> series(double... closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = (double) (i + 1);
            amounts[i] = (double) ((i + 1) * 10);
            trades[i] = i + 1;
        }
        return metricSeries(closes, volumes, amounts, trades, false);
    }

    private static List<Bar> wildSeries(double... closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = (double) (i + 2);
            amounts[i] = (double) ((i + 2) * 8);
            trades[i] = i + 2;
        }
        return metricSeries(closes, volumes, amounts, trades, true);
    }

    private static List<Bar> metricSeries(double[] closes, Double[] volumes, Double[] amounts, long[] trades,
            boolean wildOhlc) {
        List<Bar> bars = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant end = BASE_END.plus(PERIOD.multipliedBy(i));
            Instant begin = end.minus(PERIOD);
            Num close = NUM_FACTORY.numOf(closes[i]);
            Num open;
            Num high;
            Num low;
            if (wildOhlc) {
                open = NUM_FACTORY.numOf(closes[i] + 8.0 + i);
                high = NUM_FACTORY.numOf(closes[i] + 16.0 + i);
                low = NUM_FACTORY.numOf(closes[i] - 16.0 - i);
            } else {
                open = close;
                high = close;
                low = close;
            }
            Num volume = volumes[i] == null ? null : NUM_FACTORY.numOf(volumes[i]);
            Num amount = amounts[i] == null ? null : NUM_FACTORY.numOf(amounts[i]);
            bars.add(new BaseBar(PERIOD, begin, end, open, high, low, close, volume, amount, trades[i]));
        }
        return bars;
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = List.of();
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_BAR_ANCHOR_ONLY_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> bars = wildSeries(0.0);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_BAR_FLAT_PATH_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4, 2);
        List<Bar> bars = metricSeries(new double[]{-1, -1, -1}, new Double[]{null, 2.0, null},
                new Double[]{4.0, 6.0, 8.0}, new long[]{1, 2, 3}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MOVE_BELOW_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 4);
        List<Bar> bars = metricSeries(new double[]{-4096, -3584}, new Double[]{3.0, 5.0},
                new Double[]{null, null}, new long[]{2, 4}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MOVE_EXACT_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = series(10.0, 10.5);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MOVE_JUST_OVER_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> bars = wildSeries(0.0, 1.5);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MOVE_BELOW_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4, 2);
        List<Bar> bars = series(1.0, -1.0);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MOVE_EXACT_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 4);
        List<Bar> bars = metricSeries(new double[]{-1024, -2048}, new Double[]{null, 7.0},
                new Double[]{8.0, 12.0}, new long[]{1, 5}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MOVE_JUST_OVER_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = metricSeries(new double[]{4.0, 3.25}, new Double[]{2.0, 3.0},
                new Double[]{null, 5.0}, new long[]{1, 2}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_MULTI_BRICK_JUMP_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> bars = metricSeries(new double[]{-1, 3}, new Double[]{2.0, 5.0},
                new Double[]{4.0, 9.0}, new long[]{3, 7}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_MULTI_BRICK_JUMP_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4, 2);
        List<Bar> bars = series(6.0, -6.0);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_DIRECTION_SINGLE_CONTINUATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 5);
        List<Bar> bars = wildSeries(-4096, -3072, -2048);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_DIRECTION_SINGLE_CONTINUATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = metricSeries(new double[]{3.0, 2.5, 2.0}, new Double[]{null, 2.0, 3.0},
                new Double[]{4.0, 6.0, 8.0}, new long[]{1, 2, 3}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_DIRECTION_MULTI_BRICK_CONTINUATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> bars = metricSeries(new double[]{0, 1, 4}, new Double[]{2.0, 3.0, 5.0},
                new Double[]{4.0, null, null}, new long[]{1, 2, 4}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_DIRECTION_MULTI_BRICK_CONTINUATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4, 2);
        List<Bar> bars = series(6, 2, -10);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_TREND_SUBBOX_ADVANCE_THEN_EMIT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 4);
        List<Bar> bars = wildSeries(-4096, -3072, -2560, -2048);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_TREND_SUBBOX_ADVANCE_THEN_EMIT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = series(5.0, 4.5, 4.25, 4.0);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_JUST_BELOW_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> bars = metricSeries(new double[]{0, 1, -1.5}, new Double[]{null, 2.0, null},
                new Double[]{3.0, 4.0, 5.0}, new long[]{1, 2, 3}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_EXACT_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4, 2);
        List<Bar> bars = metricSeries(new double[]{-2, 2, -6}, new Double[]{2.0, 3.0, 4.0},
                new Double[]{null, 6.0, 8.0}, new long[]{1, 2, 3}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_BEYOND_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 3);
        List<Bar> bars = wildSeries(-2048, -1024, -5120);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_JUST_BELOW_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = series(2.0, 1.5, 2.25);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_EXACT_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = wildSeries(0, -1, 1);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_BEYOND_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4, 2);
        List<Bar> bars = metricSeries(new double[]{2, -2, 10}, new Double[]{null, 3.0, 5.0},
                new Double[]{4.0, 6.0, 8.0}, new long[]{1, 2, 4}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BOX_UP_TO_DOWN_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 1);
        List<Bar> bars = metricSeries(new double[]{-2048, -1024, -2048}, new Double[]{2.0, 3.0, 4.0},
                new Double[]{null, 5.0, null}, new long[]{1, 2, 3}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BOX_DOWN_TO_UP_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> bars = series(3.0, 2.5, 3.0);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_REVERSAL_AMOUNT_BLOCKS_OPPOSING_MOVE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 5);
        List<Bar> bars = wildSeries(0, 1, -2);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BLOCKED_REVERSAL_METRICS_RELEASED_LATER_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4, 2);
        List<Bar> bars = metricSeries(new double[]{0, 4, 0, -4}, new Double[]{1.0, 2.0, 7.0, 11.0},
                new Double[]{2.0, 4.0, 14.0, 22.0}, new long[]{1, 2, 7, 11}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METRICS_ACROSS_INITIAL_NO_EMISSION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 4);
        List<Bar> bars = metricSeries(new double[]{-4096, -3584, -3072}, new Double[]{null, 3.0, 5.0},
                new Double[]{2.0, 4.0, 8.0}, new long[]{1, 2, 4}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_SENTINEL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = metricSeries(new double[]{4.0, 4.25, 4.5}, new Double[]{null, 3.0, null},
                new Double[]{2.0, 4.0, 8.0}, new long[]{1, 2, 3}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_AMOUNT_SENTINEL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> bars = metricSeries(new double[]{0, 0.5, 1.0}, new Double[]{2.0, 3.0, 5.0},
                new Double[]{null, 7.0, null}, new long[]{1, 2, 4}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALL_NULL_METRICS_BECOME_ZERO_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4, 2);
        List<Bar> bars = metricSeries(new double[]{2, 6}, new Double[]{null, null},
                new Double[]{null, null}, new long[]{0, 0}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_METRICS_AND_ZERO_TRADES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 4);
        List<Bar> bars = metricSeries(new double[]{-4096, -1024}, new Double[]{0.0, 0.0},
                new Double[]{0.0, 0.0}, new long[]{0, 0}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONZERO_AMOUNT_SCALING_OBSERVABLE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = metricSeries(new double[]{2.0, 2.5}, new Double[]{null, 4.0},
                new Double[]{3.0, 7.0}, new long[]{1, 2}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_BRICK_METRIC_ZEROING_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> bars = metricSeries(new double[]{0, 4}, new Double[]{2.0, 5.0},
                new Double[]{3.0, 7.0}, new long[]{2, 5}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SOURCE_END_TIME_SELECTED_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4, 2);
        List<Bar> bars = series(-2, 2);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SYNTHETIC_FUTURE_END_TIMES_WITHIN_ONE_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 4);
        List<Bar> bars = wildSeries(-4096, 0);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEXT_SOURCE_EMISSION_STILL_USES_SYNTHETIC_TIME_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = series(2.0, 3.5, 4.0);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALTERNATING_EXACT_REVERSALS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = metricSeries(new double[]{0, 1, -1, 1, -1}, new Double[]{null, 2.0, 3.0, 5.0, 7.0},
                new Double[]{2.0, 4.0, 6.0, 8.0, 10.0}, new long[]{1, 2, 3, 4, 5}, true);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_CONTINUATION_BLOCKED_REVERSAL_AND_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4, 2);
        List<Bar> bars = metricSeries(new double[]{-4, 0, 4, 2, -4}, new Double[]{1.0, 2.0, 3.0, 11.0, 13.0},
                new Double[]{2.0, 4.0, 6.0, null, 26.0}, new long[]{1, 2, 3, 11, 13}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PRICE_PATH_CROSSES_ZERO_UPWARD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 4);
        List<Bar> bars = wildSeries(-1536, 1536);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PRICE_PATH_CROSSES_ZERO_DOWNWARD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = series(0.75, -0.75);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_BOX_EXACT_BOUNDARIES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.25, 2);
        List<Bar> bars = wildSeries(0, 0.25, -0.25, 0.25);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_CONSTRUCTOR_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4);
        List<Bar> bars = metricSeries(new double[]{-2, 2, -6}, new Double[]{null, 2.0, 3.0},
                new Double[]{4.0, 6.0, 8.0}, new long[]{1, 2, 3}, false);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NON_CLOSE_FIELDS_DO_NOT_DRIVE_BRICKS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1024.0, 4);
        List<Bar> bars = wildSeries(-4096, -3840, -4352);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NON_CLOSE_FIELDS_WITH_EMISSION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> bars = wildSeries(4.0, 5.0);
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
