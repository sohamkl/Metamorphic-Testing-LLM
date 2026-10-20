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

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant START = Instant.parse("2024-01-01T00:00:00Z");
    private static final NumFactory NUM_FACTORY = DecimalNumFactory.getInstance();

    private static List<Bar> bars(double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            Num close = num(closes[index]);
            result.add(createBar(index, close, close, close, close, num(index + 1), num((index + 1) * 10),
                    index + 1L));
        }
        return result;
    }

    private static List<Bar> barsWithMetrics(double[] closes, String[] volumes, String[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            Num close = num(closes[index]);
            Num volume = volumes[index] == null ? null : NUM_FACTORY.numOf(volumes[index]);
            Num amount = amounts[index] == null ? null : NUM_FACTORY.numOf(amounts[index]);
            result.add(createBar(index, close, close, close, close, volume, amount, trades[index]));
        }
        return result;
    }

    private static List<Bar> barsWithOhlc(double[] opens, double[] highs, double[] lows, double[] closes) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            result.add(createBar(index, num(opens[index]), num(highs[index]), num(lows[index]), num(closes[index]),
                    num(index + 1), num((index + 1) * 10), index + 1L));
        }
        return result;
    }

    private static Bar createBar(int index, Num open, Num high, Num low, Num close, Num volume, Num amount,
            long trades) {
        Instant beginTime = START.plus(PERIOD.multipliedBy(index));
        Instant endTime = beginTime.plus(PERIOD);
        return new BaseBar(PERIOD, beginTime, endTime, open, high, low, close, volume, amount, trades);
    }

    private static Num num(double value) {
        return NUM_FACTORY.numOf(Double.toString(value));
    }

    private static Num num(long value) {
        return NUM_FACTORY.numOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> asBarList(Object value) {
        return (List<Bar>) value;
    }

    @Test
    public void EMPTY_SOURCE_LIST_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = List.of();

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_ANCHOR_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = bars(0.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_UP_MOVE_JUST_BELOW_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0);
        List<Bar> bars = bars(-10.0, -9.001);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_DOWN_MOVE_JUST_BELOW_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000_000_000L, 1);
        List<Bar> bars = bars(2_000_000_000_000.0, 1_000_000_000_001.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_UP_EXACT_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = bars(-1.0, 1.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_DOWN_EXACT_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 4);
        List<Bar> bars = bars(-2.0, -2.5);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_UP_MULTI_BRICK_JUMP_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.001);
        List<Bar> bars = bars(10.0, 10.003);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INITIAL_DOWN_MULTI_BRICK_JUMP_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000_000L, 2);
        List<Bar> bars = bars(1_000_000_000.0, -2_000_000_000.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FIRST_BAR_METRICS_PENDING_UNTIL_FIRST_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = barsWithMetrics(
                new double[] { -10.0, -8.0 },
                new String[] { "7", "3" },
                new String[] { "70", "30" },
                new long[] { 4, 2 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void QUIET_BARS_ACCUMULATE_PENDING_METRICS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> bars = barsWithMetrics(
                new double[] { 10.0, 10.1, 9.8, 10.5 },
                new String[] { "1", "2", "3", "4" },
                new String[] { "10", "20", "30", "40" },
                new long[] { 1, 2, 3, 4 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NULL_VOLUME_SENTINEL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.001);
        List<Bar> bars = barsWithMetrics(
                new double[] { -0.001, 0.001 },
                new String[] { null, "5" },
                new String[] { "2", "3" },
                new long[] { 1, 2 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NULL_AMOUNT_SENTINEL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000_000L, 4);
        List<Bar> bars = barsWithMetrics(
                new double[] { -2_000_000_000.0, -3_000_000_000.0 },
                new String[] { "8", "9" },
                new String[] { null, "90" },
                new long[] { 3, 4 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ALL_PENDING_VOLUME_AND_AMOUNT_NULL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = barsWithMetrics(
                new double[] { 4.0, 6.0 },
                new String[] { null, null },
                new String[] { null, null },
                new long[] { 1, 2 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXPLICIT_ZERO_METRICS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.25, 2);
        List<Bar> bars = barsWithMetrics(
                new double[] { -0.25, 0.0 },
                new String[] { "0", "0" },
                new String[] { "0", "0" },
                new long[] { 0, 0 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_DIRECTION_CONTINUATION_EXACT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.01);
        List<Bar> bars = bars(-2.0, -1.99, -1.98);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_DIRECTION_PAUSE_THEN_CONTINUE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000L, 1);
        List<Bar> bars = barsWithMetrics(
                new double[] { 5_000_000.0, 6_000_000.0, 6_500_000.0, 7_000_000.0 },
                new String[] { "1", "2", "3", "4" },
                new String[] { "10", "20", "30", "40" },
                new long[] { 1, 1, 2, 3 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_OPPOSITE_MOVE_BELOW_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = bars(-1.0, 1.0, -2.999);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_REVERSAL_EXACT_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 3);
        List<Bar> bars = bars(-3.0, -2.5, -4.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_REVERSAL_JUST_SHORT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.001);
        List<Bar> bars = bars(1.0, 1.001, 0.999001);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_REVERSAL_OVERSHOOT_MULTI_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000_000L, 2);
        List<Bar> bars = barsWithMetrics(
                new double[] { -1_000_000_000.0, 0.0, -4_000_000_000.0 },
                new String[] { "1", "2", "9" },
                new String[] { "10", "20", "90" },
                new long[] { 1, 2, 9 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_DIRECTION_CONTINUATION_EXACT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = bars(-2.0, -4.0, -6.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_DIRECTION_PAUSE_THEN_CONTINUE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> bars = barsWithMetrics(
                new double[] { 4.0, 3.5, 3.75, 3.0 },
                new String[] { "1", "2", "3", "4" },
                new String[] { "10", "20", "30", "40" },
                new long[] { 1, 2, 3, 4 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_OPPOSITE_MOVE_BELOW_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.001);
        List<Bar> bars = bars(0.001, 0.0, 0.001999);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_REVERSAL_EXACT_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000_000L, 3);
        List<Bar> bars = bars(-1_000_000_000.0, -2_000_000_000.0, 1_000_000_000.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_REVERSAL_JUST_SHORT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2, 2);
        List<Bar> bars = bars(5.0, 3.0, 6.999);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_REVERSAL_OVERSHOOT_MULTI_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = barsWithMetrics(
                new double[] { 0.5, 0.0, 2.0 },
                new String[] { "1", "2", "8" },
                new String[] { "10", "20", "80" },
                new long[] { 1, 2, 8 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_BOX_UP_TO_DOWN_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.01, 1);
        List<Bar> bars = bars(-1.0, -0.99, -1.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_BOX_DOWN_TO_UP_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000L, 1);
        List<Bar> bars = bars(2_000_000.0, 1_000_000.0, 2_000_000.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEFAULT_TWO_BRICK_CONSTRUCTOR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = bars(-1.0, 1.0, -3.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_REVERSAL_AMOUNT_GATE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 4);
        List<Bar> bars = barsWithMetrics(
                new double[] { -2.0, -1.5, -3.0, -3.5 },
                new String[] { "1", "2", "7", "8" },
                new String[] { "10", "20", "70", "80" },
                new long[] { 1, 2, 7, 8 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ALTERNATING_COMPLETE_REVERSALS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.001, 2);
        List<Bar> bars = bars(1.0, 1.001, 0.999, 1.001);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_BRICK_METRICS_ONLY_ON_FIRST_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000_000L, 2);
        List<Bar> bars = barsWithMetrics(
                new double[] { -1_000_000_000.0, 2_000_000_000.0 },
                new String[] { "5", "7" },
                new String[] { "50", "70" },
                new long[] { 3, 4 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_BRICK_NEXT_TIME_DOMINATES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = bars(-10.0, -4.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void QUIET_INTERVAL_SOURCE_TIME_DOMINATES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> bars = bars(10.0, 10.5, 10.6, 10.7, 11.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void END_TIME_EQUAL_TO_NEXT_SCHEDULE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.001);
        List<Bar> bars = bars(-0.001, 0.0, 0.001);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIRECTION_NONE_OSCILLATION_THEN_UP_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000L, 4);
        List<Bar> bars = barsWithMetrics(
                new double[] { -5_000_000.0, -4_500_000.0, -5_400_000.0, -4_700_000.0, -4_000_000.0 },
                new String[] { "1", "2", "3", "4", "5" },
                new String[] { "10", "20", "30", "40", "50" },
                new long[] { 1, 2, 3, 4, 5 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIRECTION_NONE_OSCILLATION_THEN_DOWN_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2, 1);
        List<Bar> bars = bars(10.0, 11.0, 9.5, 10.5, 8.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_THRESHOLD_AFTER_GRADUAL_CLOSES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = bars(-0.5, -0.4, -0.25, -0.1, 0.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PRICE_CROSSES_ZERO_UPWARD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.001);
        List<Bar> bars = bars(-0.0015, 0.0015);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PRICE_CROSSES_ZERO_DOWNWARD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000_000L, 1);
        List<Bar> bars = bars(1_500_000_000.0, -1_500_000_000.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ALL_PRICES_NEGATIVE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = bars(-10.0, -8.0, -6.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRACTIONAL_BOX_EXACT_BOUNDARIES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.25, 3);
        List<Bar> bars = bars(-2.0, -1.75, -1.5);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTEGRAL_NUMBER_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(Integer.valueOf(3), 1);
        List<Bar> bars = bars(6.0, 9.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SMALL_POSITIVE_FINITE_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.000001, 2);
        List<Bar> bars = bars(-0.000001, 0.0, 0.000001);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_POSITIVE_FINITE_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000_000_000L, 4);
        List<Bar> bars = bars(-2_000_000_000_000.0, -1_000_000_000_000.0);

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOURCE_OHLC_DIFFERS_FROM_CLOSE_PATH_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> bars = barsWithOhlc(
                new double[] { 9.5, 8.0, 12.0 },
                new double[] { 11.0, 12.5, 14.0 },
                new double[] { 9.0, 7.5, 10.5 },
                new double[] { 10.0, 10.5, 11.0 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PENDING_METRICS_SURVIVE_BLOCKED_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.001, 2);
        List<Bar> bars = barsWithMetrics(
                new double[] { -0.001, 0.0, -0.0015, -0.002 },
                new String[] { "1", "2", "7", "8" },
                new String[] { "10", "20", "70", "80" },
                new long[] { 1, 2, 7, 8 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTIPLE_SOURCE_BARS_ONE_BRICK_EACH_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000_000L, 4);
        List<Bar> bars = barsWithMetrics(
                new double[] { -4_000_000_000.0, -3_000_000_000.0, -2_000_000_000.0, -1_000_000_000.0 },
                new String[] { "1", "2", "3", "4" },
                new String[] { "10", "20", "30", "40" },
                new long[] { 1, 2, 3, 4 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void REVERSAL_BAR_WITH_NULL_METRICS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2, 2);
        List<Bar> bars = barsWithMetrics(
                new double[] { 10.0, 12.0, 8.0 },
                new String[] { "3", "4", null },
                new String[] { "30", "40", null },
                new long[] { 1, 2, 3 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LONG_ALTERNATION_WITH_QUIET_BARS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = barsWithMetrics(
                new double[] { -0.5, -0.25, 0.0, 0.2, 0.5, 0.1, -0.5, -0.25, 0.5, 0.75 },
                new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" },
                new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" },
                new long[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(asBarList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
