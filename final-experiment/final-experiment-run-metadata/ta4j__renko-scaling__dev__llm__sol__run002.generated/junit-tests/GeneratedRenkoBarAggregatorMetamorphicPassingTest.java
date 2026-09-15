import java.time.Duration;
import java.time.Instant;
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

    private static Bar bar(int index, double close, Double volume, Double amount, long trades) {
        Num closeValue = num(close);
        return new BaseBar(
                PERIOD,
                begin(index),
                end(index),
                closeValue,
                closeValue,
                closeValue,
                closeValue,
                nullableNum(volume),
                nullableNum(amount),
                trades);
    }

    private static Bar barWithOhlc(int index, double open, double high, double low, double close,
            Double volume, Double amount, long trades) {
        return new BaseBar(
                PERIOD,
                begin(index),
                end(index),
                num(open),
                num(high),
                num(low),
                num(close),
                nullableNum(volume),
                nullableNum(amount),
                trades);
    }

    private static Instant begin(int index) {
        return START.plus(PERIOD.multipliedBy(index));
    }

    private static Instant end(int index) {
        return begin(index).plus(PERIOD);
    }

    private static Num num(double value) {
        return NUM_FACTORY.numOf(value);
    }

    private static Num nullableNum(Double value) {
        return value == null ? null : NUM_FACTORY.numOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> barList(Object value) {
        return (List<Bar>) value;
    }

    @Test
    void EMPTY_SOURCE_LIST_integralDefaultConstructor() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = List.of();

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLETON_ANCHOR_ONLY_fractionalAnchor() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = List.of(bar(0, 0.0, null, 3.0, 1));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_BAR_FLAT_CLOSES_fourQuietBars() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.01);
        List<Bar> bars = List.of(
                bar(0, -0.005, 1.0, null, 1),
                bar(1, -0.005, 2.0, null, 2),
                bar(2, -0.005, 3.0, null, 3),
                bar(3, -0.005, 4.0, null, 4));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_JUST_BELOW_BOX_largeBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1000.0, 5);
        List<Bar> bars = List.of(
                bar(0, 5000.0, null, null, 1),
                bar(1, 5999.0, null, null, 2));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_JUST_BELOW_BOX_negativePrices() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = List.of(
                bar(0, -10.0, 0.0, 0.0, 0),
                bar(1, -11.999, 0.0, 0.0, 0));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_EXACT_BOX_fractionalBoundary() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.25, 2);
        List<Bar> bars = List.of(
                bar(0, 10.0, 2.0, 20.0, 1),
                bar(1, 10.25, 3.0, 30.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_EXACT_BOX_smallBoundary() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.125);
        List<Bar> bars = List.of(
                bar(0, 0.0, 1.0, 2.0, 3),
                bar(1, -0.125, 4.0, 5.0, 6));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_FRACTIONAL_OVERSHOOT_largeScaleSafeBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(100_000.0, 5);
        List<Bar> bars = List.of(
                bar(0, -50_000.0, null, 7.0, 1),
                bar(1, 75_000.0, 8.0, 9.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_FRACTIONAL_OVERSHOOT_integralBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(4);
        List<Bar> bars = List.of(
                bar(0, 6.0, 1.0, null, 1),
                bar(1, 0.5, 2.0, 4.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MULTI_BRICK_UP_threeFractionalBricks() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = List.of(
                bar(0, -10.0, null, null, 1),
                bar(1, -8.25, null, null, 2));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MULTI_BRICK_DOWN_threeSmallBricks() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.01);
        List<Bar> bars = List.of(
                bar(0, 2.0, 0.0, 0.0, 0),
                bar(1, 1.965, 0.0, 0.0, 0));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_DIRECTION_ONE_BRICK_CONTINUATION_largeIntegralMovement() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1000.0, 5);
        List<Bar> bars = List.of(
                bar(0, 0.0, 1.0, 10.0, 1),
                bar(1, 1000.0, 2.0, 20.0, 2),
                bar(2, 2000.0, 3.0, 30.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_DIRECTION_ONE_BRICK_CONTINUATION_crossesZero() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = List.of(
                bar(0, 3.0, 1.0, 3.0, 1),
                bar(1, 1.0, 2.0, 2.0, 2),
                bar(2, -1.0, 3.0, 1.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_DIRECTION_MULTI_BRICK_CONTINUATION_fractionalBurst() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.25, 2);
        List<Bar> bars = List.of(
                bar(0, -1.0, null, 1.0, 1),
                bar(1, -0.75, 2.0, 2.0, 2),
                bar(2, 0.125, null, 3.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_DIRECTION_MULTI_BRICK_CONTINUATION_negativeRange() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.1);
        List<Bar> bars = List.of(
                bar(0, -2.0, 1.0, null, 1),
                bar(1, -2.1, 2.0, null, 2),
                bar(2, -2.45, 3.0, null, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_PULLBACK_BELOW_REVERSAL_largeReversalGuard() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(100.0, 5);
        List<Bar> bars = List.of(
                bar(0, 1000.0, null, null, 1),
                bar(1, 1100.0, 2.0, 20.0, 2),
                bar(2, 700.0, 3.0, 30.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_EXACT_THRESHOLD_defaultTwoBrickReversal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = List.of(
                bar(0, 0.0, 0.0, 0.0, 0),
                bar(1, 2.0, 0.0, 0.0, 0),
                bar(2, -2.0, 0.0, 0.0, 0));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_BEYOND_THRESHOLD_fractionalThreeBrickReversal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = List.of(
                bar(0, -1.0, 1.0, 1.0, 1),
                bar(1, -0.5, 2.0, 2.0, 2),
                bar(2, -2.1, 3.0, 3.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REBOUND_BELOW_REVERSAL_defaultGuard() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.25);
        List<Bar> bars = List.of(
                bar(0, 1.0, 1.0, 10.0, 1),
                bar(1, 0.75, 2.0, 20.0, 2),
                bar(2, 1.0, 3.0, 30.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_EXACT_THRESHOLD_fiveBrickReversal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1000.0, 5);
        List<Bar> bars = List.of(
                bar(0, -10_000.0, null, 10.0, 1),
                bar(1, -11_000.0, 2.0, 20.0, 2),
                bar(2, -6_000.0, null, 30.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_BEYOND_THRESHOLD_defaultThreeUpBricks() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = List.of(
                bar(0, 10.0, 1.0, null, 1),
                bar(1, 8.0, 2.0, null, 2),
                bar(2, 14.5, 3.0, null, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BOX_UP_TO_DOWN_REVERSAL_fractionalOneBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> bars = List.of(
                bar(0, 0.0, null, null, 1),
                bar(1, 0.5, 2.0, 3.0, 2),
                bar(2, 0.0, 4.0, 5.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BOX_DOWN_TO_UP_REVERSAL_smallOneBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.125, 1);
        List<Bar> bars = List.of(
                bar(0, 0.125, 0.0, 0.0, 0),
                bar(1, 0.0, 0.0, 0.0, 0),
                bar(2, 0.125, 0.0, 0.0, 0));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_REVERSAL_AMOUNT_BOUNDARY_sixBoxThreshold() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(100.0, 6);
        List<Bar> bars = List.of(
                bar(0, 1000.0, 1.0, 10.0, 1),
                bar(1, 1100.0, 2.0, 20.0, 2),
                bar(2, 600.0, 3.0, 30.0, 3),
                bar(3, 500.0, 4.0, 40.0, 4));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWO_SIDED_WHIPSAW_defaultReversalBothDirections() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1);
        List<Bar> bars = List.of(
                bar(0, -5.0, 1.0, 1.0, 1),
                bar(1, -4.0, 2.0, 2.0, 2),
                bar(2, -6.0, 3.0, 3.0, 3),
                bar(3, -4.0, 4.0, 4.0, 4),
                bar(4, -4.0, 5.0, 5.0, 5),
                bar(5, -4.0, 6.0, 6.0, 6));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METRICS_ACROSS_QUIET_BARS_distinguishableMetrics() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = List.of(
                bar(0, 10.0, 1.0, 10.0, 1),
                bar(1, 10.1, 2.0, 20.0, 2),
                bar(2, 10.2, 4.0, 40.0, 4),
                bar(3, 10.5, 8.0, 80.0, 8));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_SENTINEL_nullSkippedBeforeEmission() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.1);
        List<Bar> bars = List.of(
                bar(0, 0.0, null, 1.0, 1),
                bar(1, 0.05, 2.0, 2.0, 2),
                bar(2, 0.1, null, 3.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_AMOUNT_SENTINEL_nullSkippedBeforeDownBrick() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(100.0, 5);
        List<Bar> bars = List.of(
                bar(0, 1000.0, 1.0, null, 1),
                bar(1, 950.0, 2.0, 20.0, 2),
                bar(2, 900.0, 3.0, null, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOTH_METRICS_NULL_tradesStillContribute() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = List.of(
                bar(0, 5.0, null, null, 3),
                bar(1, 5.5, 2.0, 20.0, 4),
                bar(2, 7.0, null, null, 5));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_BRICK_METRIC_ZEROING_threeBrickBurst() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = List.of(
                bar(0, -10.0, 1.0, 10.0, 1),
                bar(1, -9.9, 2.0, 20.0, 2),
                bar(2, -8.25, 8.0, 80.0, 8));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void METRICS_PENDING_DURING_FAILED_REVERSAL_consumedAtContinuation() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.1);
        List<Bar> bars = List.of(
                bar(0, 1.0, 1.0, 10.0, 1),
                bar(1, 1.1, 2.0, 20.0, 2),
                bar(2, 1.0, 4.0, 40.0, 4),
                bar(3, 1.2, 8.0, 80.0, 8));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SOURCE_END_TIME_SELECTED_emissionAfterQuietBars() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1000.0, 5);
        List<Bar> bars = List.of(
                bar(0, 0.0, null, 10.0, 1),
                bar(1, 100.0, 2.0, 20.0, 2),
                bar(2, 1000.0, 3.0, 30.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCHEDULED_END_TIME_EQUAL_SOURCE_END_consecutiveEmissions() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1);
        List<Bar> bars = List.of(
                bar(0, -1.0, 1.0, null, 1),
                bar(1, 0.0, 2.0, null, 2),
                bar(2, 1.0, 3.0, null, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCHEDULED_END_TIME_AHEAD_AFTER_BURST_laterContinuation() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = List.of(
                bar(0, -2.0, null, null, 1),
                bar(1, -0.5, null, null, 2),
                bar(2, 0.0, null, null, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_PRICE_ANCHOR_exactPositiveBoundary() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.01);
        List<Bar> bars = List.of(
                bar(0, 0.0, 0.0, 0.0, 0),
                bar(1, 0.01, 0.0, 0.0, 0));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UPWARD_CROSSING_OF_ZERO_threeUpBricks() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(100.0, 5);
        List<Bar> bars = List.of(
                bar(0, -150.0, 1.0, 10.0, 1),
                bar(1, 150.0, 2.0, 20.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWNWARD_CROSSING_OF_ZERO_threeDownBricks() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1);
        List<Bar> bars = List.of(
                bar(0, 1.5, 1.0, 1.0, 1),
                bar(1, -1.5, 2.0, 2.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void STRICTLY_NEGATIVE_PRICE_RANGE_fractionalDownBrick() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.25, 2);
        List<Bar> bars = List.of(
                bar(0, -5.0, null, 1.0, 1),
                bar(1, -5.25, 2.0, 2.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_BOX_EXACT_BOUNDARIES_continuationAndReversal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.125);
        List<Bar> bars = List.of(
                bar(0, 0.375, 1.0, null, 1),
                bar(1, 0.5, 2.0, null, 2),
                bar(2, 0.625, 3.0, null, 3),
                bar(3, 0.375, 4.0, null, 4));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_SCALE_SAFE_BOX_AND_PRICES_exactLargeBoundary() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1_000_000_000.0, 5);
        List<Bar> bars = List.of(
                bar(0, 1_000_000_000_000.0, null, null, 1),
                bar(1, 1_001_000_000_000.0, null, null, 2));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_CONSTRUCTOR_TWO_BRICK_REVERSAL_oneThenTwoBoxPullback() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2);
        List<Bar> bars = List.of(
                bar(0, 10.0, 0.0, 0.0, 0),
                bar(1, 12.0, 0.0, 0.0, 0),
                bar(2, 10.0, 0.0, 0.0, 0),
                bar(3, 8.0, 0.0, 0.0, 0));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSE_ONLY_DECISION_WITH_ALTERNATE_OHLC_distinctNonCloseFields() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> bars = List.of(
                barWithOhlc(0, 100.0, 103.0, 95.0, 0.0, 1.0, 10.0, 1),
                barWithOhlc(1, -20.0, 50.0, -30.0, 0.5, 2.0, 20.0, 2),
                barWithOhlc(2, 999.0, 1200.0, -500.0, -0.5, 3.0, 30.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, bars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpAggregator.aggregate(barList(followUp[1]));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
