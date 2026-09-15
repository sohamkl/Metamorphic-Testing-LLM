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

    private static final NumFactory NUM_FACTORY = DecimalNumFactory.getInstance();
    private static final Duration PERIOD = Duration.ofSeconds(60);
    private static final Instant START = Instant.parse("2024-01-01T00:00:00Z");

    private static List<Bar> bars(double[] closes, Double[] volumes, Double[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Num close = NUM_FACTORY.numOf(closes[i]);
            Num volume = volumes == null ? NUM_FACTORY.numOf(10.0) : numOrNull(volumes[i]);
            Num amount = amounts == null ? NUM_FACTORY.numOf(100.0) : numOrNull(amounts[i]);
            long tradeCount = trades == null ? 1L : trades[i];
            Instant begin = START.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            result.add(new BaseBar(PERIOD, begin, end, close, close, close, close, volume, amount, tradeCount));
        }
        return result;
    }

    private static Num numOrNull(Double value) {
        return value == null ? null : NUM_FACTORY.numOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> castBars(Object value) {
        return (List<Bar>) value;
    }

    @Test
    void EMPTY_LIST_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = List.of();
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_INITIALIZATION_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0}, new Double[] {0.0}, new Double[] {0.0}, new long[] {0});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWO_BARS_NO_CLOSE_MOVEMENT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 100.0}, new Double[] {null, null},
                new Double[] {100.0, 100.0}, new long[] {1, 1});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUB_BOX_UPWARD_MOVE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 100.5}, new Double[] {10.0, 10.0},
                new Double[] {null, null}, new long[] {1, 1});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUB_BOX_DOWNWARD_MOVE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 99.5}, new Double[] {null, null},
                new Double[] {null, null}, new long[] {2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_INITIAL_UP_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 101.0}, new Double[] {2.0, 7.0},
                new Double[] {20.0, 70.0}, new long[] {1, 4});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_INITIAL_DOWN_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 99.0}, new Double[] {3.0, 8.0},
                new Double[] {30.0, 80.0}, new long[] {2, 5});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MULTI_UP_FROM_ONE_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 103.0}, new Double[] {10.0, 10.0},
                new Double[] {100.0, 100.0}, new long[] {1, 1});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MULTI_DOWN_FROM_ONE_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 97.0}, new Double[] {0.0, 0.0},
                new Double[] {0.0, 0.0}, new long[] {0, 0});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_CONTINUATION_ON_LATER_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 101.0, 102.0}, new Double[] {4.0, 5.0, 6.0},
                new Double[] {40.0, 50.0, 60.0}, new long[] {1, 2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_CONTINUATION_ON_LATER_BAR_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 99.0, 98.0}, new Double[] {4.0, 5.0, 6.0},
                new Double[] {40.0, 50.0, 60.0}, new long[] {1, 2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_DIRECTION_SMALL_PULLBACK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 101.0, 100.0}, new Double[] {10.0, null, 8.0},
                new Double[] {100.0, null, 80.0}, new long[] {1, 2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_DIRECTION_SMALL_BOUNCE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 99.0, 100.0}, new Double[] {11.0, 12.0, 13.0},
                new Double[] {110.0, 120.0, 130.0}, new long[] {1, 1, 1});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_UP_TO_DOWN_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 101.0, 99.0}, new Double[] {2.0, 3.0, 4.0},
                new Double[] {20.0, 30.0, 40.0}, new long[] {2, 3, 4});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_DOWN_TO_UP_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 99.0, 101.0}, new Double[] {2.0, 3.0, 4.0},
                new Double[] {20.0, 30.0, 40.0}, new long[] {2, 3, 4});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_TO_DOWN_REVERSAL_EXCEEDING_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 101.0, 97.0}, new Double[] {0.0, 0.0, 0.0},
                new Double[] {0.0, 0.0, 0.0}, new long[] {0, 0, 0});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_TO_UP_REVERSAL_EXCEEDING_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 99.0, 103.0}, new Double[] {null, 4.0, 9.0},
                new Double[] {10.0, 20.0, 30.0}, new long[] {1, 2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_AMOUNT_ONE_UP_TO_DOWN_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> bars = bars(new double[] {100.0, 101.0, 100.0}, new Double[] {6.0, 7.0, 8.0},
                new Double[] {60.0, 70.0, 80.0}, new long[] {1, 2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_AMOUNT_ONE_DOWN_TO_UP_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> bars = bars(new double[] {100.0, 99.0, 100.0}, new Double[] {6.0, 7.0, 8.0},
                new Double[] {60.0, 70.0, 80.0}, new long[] {1, 2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_AMOUNT_THREE_NEAR_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> bars = bars(new double[] {100.0, 101.0, 99.0}, new Double[] {7.0, 8.0, 9.0},
                new Double[] {70.0, 80.0, 90.0}, new long[] {2, 2, 2});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_AMOUNT_THREE_EXACT_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> bars = bars(new double[] {100.0, 101.0, 98.0}, new Double[] {7.0, 8.0, 9.0},
                new Double[] {70.0, 80.0, 90.0}, new long[] {2, 2, 2});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_BOX_EXACT_UP_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = bars(new double[] {100.0, 100.5}, new Double[] {5.0, 5.0},
                new Double[] {50.0, 50.0}, new long[] {1, 1});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_BOX_EXACT_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = bars(new double[] {100.0, 100.5, 99.5}, new Double[] {5.0, 6.0, 7.0},
                new Double[] {50.0, 60.0, 70.0}, new long[] {1, 2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_BOX_SUBTHRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 101.0}, new Double[] {12.0, 13.0},
                new Double[] {120.0, 130.0}, new long[] {1, 1});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_BOX_EXACT_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 102.0}, new Double[] {12.0, 13.0},
                new Double[] {120.0, 130.0}, new long[] {1, 1});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METRICS_ACROSS_NON_EMITTING_BARS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 100.25, 100.5, 101.0},
                new Double[] {2.0, 3.0, 5.0, 7.0},
                new Double[] {20.0, 30.0, 50.0, 70.0},
                new long[] {1, 2, 3, 4});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_SOURCE_METRICS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 101.0}, new Double[] {0.0, 0.0},
                new Double[] {0.0, 0.0}, new long[] {0, 0});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_ACCUMULATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 101.0}, new Double[] {null, 7.0},
                new Double[] {40.0, 70.0}, new long[] {2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_AMOUNT_ACCUMULATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 101.0}, new Double[] {4.0, 7.0},
                new Double[] {null, 70.0}, new long[] {2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 101.0}, new Double[] {null, null},
                new Double[] {null, null}, new long[] {2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_NULL_METRICS_BEFORE_MULTI_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 103.0}, new Double[] {null, 6.0},
                new Double[] {40.0, null}, new long[] {2, 3});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DELAYED_EMISSION_USES_LATEST_SOURCE_END_TIME_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 100.2, 100.4, 101.0},
                new Double[] {10.0, 11.0, 12.0, 13.0},
                new Double[] {100.0, 110.0, 120.0, 130.0},
                new long[] {1, 1, 1, 1});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_BRICK_TIME_ADVANCEMENT_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> bars = bars(new double[] {100.0, 103.0}, new Double[] {11.0, 17.0},
                new Double[] {101.0, 107.0}, new long[] {4, 6});
        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        List<Bar> followUpOutput = ((RenkoBarAggregator) followUp[0]).aggregate(castBars(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
