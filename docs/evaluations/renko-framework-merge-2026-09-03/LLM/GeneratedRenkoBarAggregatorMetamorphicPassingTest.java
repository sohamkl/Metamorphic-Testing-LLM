import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.DoubleNum;
import org.ta4j.core.num.Num;
import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    // Helper for fixed time base
    private static final Instant BASE_TIME = Instant.parse("2023-01-01T00:00:00Z");

    // Helper to create a BaseBar with DecimalNum
    private static Bar bar(Duration period, Instant begin, Instant end, double open, double high, double low, double close, double volume, double amount, long trades) {
        NumFactory factory = DecimalNum::valueOf;
        return new BaseBar(
                period,
                begin,
                end,
                factory.numOf(open),
                factory.numOf(high),
                factory.numOf(low),
                factory.numOf(close),
                factory.numOf(volume),
                factory.numOf(amount),
                trades
        );
    }

    // Helper to create a BaseBar with DoubleNum
    private static Bar barDouble(Duration period, Instant begin, Instant end, double open, double high, double low, double close, double volume, double amount, long trades) {
        NumFactory factory = DoubleNum::valueOf;
        return new BaseBar(
                period,
                begin,
                end,
                factory.numOf(open),
                factory.numOf(high),
                factory.numOf(low),
                factory.numOf(close),
                factory.numOf(volume),
                factory.numOf(amount),
                trades
        );
    }

    private interface NumFactory {
        Num numOf(double value);
    }

    @Test
    public void test_SINGLE_BAR_NO_MOVE_variation1() {
        // BarCount=1, BarPricePattern=flat, BarVolumeAmount=zero, ReversalAmount=1, BoxSize=small
        Duration period = Duration.ofMinutes(1);
        Instant t0 = BASE_TIME;
        Bar bar0 = bar(period, t0.minus(period), t0, 10.0, 10.0, 10.0, 10.0, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);

        List<Bar> sourceBars = List.of(bar0);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_SINGLE_BAR_ONE_BRICK_UP_variation1() {
        // BarCount=2, BarPricePattern=up, BarVolumeAmount=positive, ReversalAmount=2, BoxSize=medium
        Duration period = Duration.ofMinutes(5);
        Instant t0 = BASE_TIME;
        Bar bar0 = bar(period, t0.minus(period), t0, 20.0, 20.0, 20.0, 20.0, 100.0, 200.0, 10L);
        // close = open + boxSize = 20.0 + 2.0 = 22.0
        Bar bar1 = bar(period, t0, t0.plus(period), 22.0, 22.0, 22.0, 22.0, 150.0, 300.0, 15L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);

        List<Bar> sourceBars = List.of(bar0, bar1);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_SINGLE_BAR_ONE_BRICK_DOWN_variation1() {
        // BarCount=3, BarPricePattern=down, BarVolumeAmount=mixed, ReversalAmount=3, BoxSize=large
        Duration period = Duration.ofMinutes(10);
        Instant t0 = BASE_TIME;
        Bar bar0 = bar(period, t0.minus(period), t0, 100.0, 100.0, 100.0, 100.0, 50.0, 500.0, 5L);
        // close = open - boxSize = 100.0 - 10.0 = 90.0
        Bar bar1 = bar(period, t0, t0.plus(period), 90.0, 90.0, 90.0, 90.0, 0.0, 0.0, 0L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 90.0, 90.0, 90.0, 90.0, 25.0, 250.0, 3L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 3);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_NO_BRICKS_variation1() {
        // BarCount=4, BarPricePattern=up-then-down, BarVolumeAmount=zero, ReversalAmount=1, BoxSize=small
        Duration period = Duration.ofMinutes(2);
        Instant t0 = BASE_TIME;
        double boxSize = 0.2;
        Bar bar0 = bar(period, t0.minus(period), t0, 5.0, 5.0, 5.0, 5.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 5.1, 5.1, 5.1, 5.1, 0.0, 0.0, 0L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 5.0, 5.0, 5.0, 5.0, 0.0, 0.0, 0L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 4.9, 4.9, 4.9, 4.9, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 1);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_SINGLE_UP_BRICK_variation1() {
        // BarCount=5, BarPricePattern=down-then-up, BarVolumeAmount=positive, ReversalAmount=2, BoxSize=medium
        Duration period = Duration.ofMinutes(3);
        Instant t0 = BASE_TIME;
        double boxSize = 1.5;
        Bar bar0 = bar(period, t0.minus(period), t0, 10.0, 10.0, 10.0, 10.0, 10.0, 15.0, 1L);
        Bar bar1 = bar(period, t0, t0.plus(period), 9.5, 9.5, 9.5, 9.5, 11.0, 16.0, 2L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 10.0, 10.0, 10.0, 10.0, 12.0, 17.0, 3L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 10.5, 10.5, 10.5, 10.5, 13.0, 18.0, 4L);
        Bar bar4 = bar(period, t0.plus(period.multipliedBy(3)), t0.plus(period.multipliedBy(4)), 11.5, 11.5, 11.5, 11.5, 14.0, 19.0, 5L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 2);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3, bar4);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_MULTI_BRICK_UP_variation1() {
        // BarCount=1, BarPricePattern=multi-brick, BarVolumeAmount=mixed, ReversalAmount=3, BoxSize=large
        Duration period = Duration.ofMinutes(15);
        Instant t0 = BASE_TIME;
        double boxSize = 5.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 50.0, 50.0, 50.0, 50.0, 100.0, 1000.0, 10L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 3);

        List<Bar> sourceBars = List.of(bar0);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_MULTI_BRICK_DOWN_variation1() {
        // BarCount=2, BarPricePattern=reversal, BarVolumeAmount=zero, ReversalAmount=1, BoxSize=small
        Duration period = Duration.ofMinutes(4);
        Instant t0 = BASE_TIME;
        double boxSize = 0.3;
        Bar bar0 = bar(period, t0.minus(period), t0, 8.0, 8.0, 8.0, 8.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 7.0, 7.0, 7.0, 7.0, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 1);

        List<Bar> sourceBars = List.of(bar0, bar1);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_REVERSAL_UP_TO_DOWN_variation1() {
        // BarCount=3, BarPricePattern=threshold, BarVolumeAmount=positive, ReversalAmount=2, BoxSize=medium
        Duration period = Duration.ofMinutes(6);
        Instant t0 = BASE_TIME;
        double boxSize = 2.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 30.0, 30.0, 30.0, 30.0, 10.0, 100.0, 2L);
        Bar bar1 = bar(period, t0, t0.plus(period), 34.0, 34.0, 34.0, 34.0, 20.0, 200.0, 3L);
        // Reversal threshold: last up brick close - reversalAmount*boxSize = 34.0 - 4.0 = 30.0
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 30.0, 30.0, 30.0, 30.0, 30.0, 300.0, 4L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 2);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_REVERSAL_DOWN_TO_UP_variation1() {
        // BarCount=4, BarPricePattern=no-move, BarVolumeAmount=mixed, ReversalAmount=3, BoxSize=large
        Duration period = Duration.ofMinutes(12);
        Instant t0 = BASE_TIME;
        double boxSize = 8.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 80.0, 80.0, 80.0, 80.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 72.0, 72.0, 72.0, 72.0, 10.0, 80.0, 1L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 96.0, 96.0, 96.0, 96.0, 20.0, 160.0, 2L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 96.0, 96.0, 96.0, 96.0, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 3);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_REVERSAL_AT_THRESHOLD_variation1() {
        // BarCount=5, BarPricePattern=flat, BarVolumeAmount=zero, ReversalAmount=1, BoxSize=small
        Duration period = Duration.ofMinutes(1);
        Instant t0 = BASE_TIME;
        double boxSize = 0.1;
        Bar bar0 = bar(period, t0.minus(period), t0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 1.1, 1.1, 1.1, 1.1, 0.0, 0.0, 0L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 1.2, 1.2, 1.2, 1.2, 0.0, 0.0, 0L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 1.1, 1.1, 1.1, 1.1, 0.0, 0.0, 0L);
        Bar bar4 = bar(period, t0.plus(period.multipliedBy(3)), t0.plus(period.multipliedBy(4)), 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 1);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3, bar4);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_MIXED_VOLUME_AMOUNT_variation1() {
        // BarCount=1, BarPricePattern=up, BarVolumeAmount=positive, ReversalAmount=2, BoxSize=medium
        Duration period = Duration.ofMinutes(5);
        Instant t0 = BASE_TIME;
        double boxSize = 2.5;
        Bar bar0 = bar(period, t0.minus(period), t0, 15.0, 15.0, 15.0, 17.5, 5.0, 12.5, 2L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 2);

        List<Bar> sourceBars = List.of(bar0);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_TRADES_ACCUMULATION_variation1() {
        // BarCount=2, BarPricePattern=down, BarVolumeAmount=mixed, ReversalAmount=3, BoxSize=large
        Duration period = Duration.ofMinutes(10);
        Instant t0 = BASE_TIME;
        double boxSize = 12.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 120.0, 120.0, 120.0, 108.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 108.0, 108.0, 108.0, 96.0, 10.0, 100.0, 5L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 3);

        List<Bar> sourceBars = List.of(bar0, bar1);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_EVENLY_SPACED_PERIODS_variation1() {
        // BarCount=3, BarPricePattern=up-then-down, BarVolumeAmount=zero, ReversalAmount=1, BoxSize=small
        Duration period = Duration.ofMinutes(7);
        Instant t0 = BASE_TIME;
        double boxSize = 0.4;
        Bar bar0 = bar(period, t0.minus(period), t0, 2.0, 2.0, 2.0, 2.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 2.3, 2.3, 2.3, 2.3, 0.0, 0.0, 0L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 2.0, 2.0, 2.0, 2.0, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 1);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_NONZERO_START_PRICE_variation1() {
        // BarCount=4, BarPricePattern=down-then-up, BarVolumeAmount=positive, ReversalAmount=2, BoxSize=medium
        Duration period = Duration.ofMinutes(8);
        Instant t0 = BASE_TIME;
        double boxSize = 4.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 40.0, 40.0, 40.0, 40.0, 10.0, 20.0, 1L);
        Bar bar1 = bar(period, t0, t0.plus(period), 36.0, 36.0, 36.0, 36.0, 11.0, 22.0, 2L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 40.0, 40.0, 40.0, 40.0, 12.0, 24.0, 3L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 44.0, 44.0, 44.0, 44.0, 13.0, 26.0, 4L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 2);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_HIGH_LOW_VARIATION_variation1() {
        // BarCount=5, BarPricePattern=multi-brick, BarVolumeAmount=mixed, ReversalAmount=3, BoxSize=large
        Duration period = Duration.ofMinutes(20);
        Instant t0 = BASE_TIME;
        double boxSize = 10.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 100.0, 110.0, 95.0, 105.0, 10.0, 100.0, 1L);
        Bar bar1 = bar(period, t0, t0.plus(period), 105.0, 115.0, 100.0, 110.0, 0.0, 0.0, 0L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 110.0, 120.0, 105.0, 115.0, 20.0, 200.0, 2L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 115.0, 125.0, 110.0, 120.0, 0.0, 0.0, 0L);
        Bar bar4 = bar(period, t0.plus(period.multipliedBy(3)), t0.plus(period.multipliedBy(4)), 120.0, 130.0, 115.0, 125.0, 30.0, 300.0, 3L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 3);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3, bar4);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_MIXED_DIRECTION_variation1() {
        // BarCount=1, BarPricePattern=reversal, BarVolumeAmount=zero, ReversalAmount=1, BoxSize=small
        Duration period = Duration.ofMinutes(2);
        Instant t0 = BASE_TIME;
        double boxSize = 0.5;
        Bar bar0 = bar(period, t0.minus(period), t0, 10.0, 10.0, 10.0, 10.0, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 1);

        List<Bar> sourceBars = List.of(bar0);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_EXACTLY_AT_BRICK_EDGE_variation1() {
        // BarCount=2, BarPricePattern=threshold, BarVolumeAmount=positive, ReversalAmount=2, BoxSize=medium
        Duration period = Duration.ofMinutes(5);
        Instant t0 = BASE_TIME;
        double boxSize = 2.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 20.0, 20.0, 20.0, 20.0, 10.0, 20.0, 1L);
        Bar bar1 = bar(period, t0, t0.plus(period), 22.0, 22.0, 22.0, 22.0, 15.0, 30.0, 2L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 2);

        List<Bar> sourceBars = List.of(bar0, bar1);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_MULTI_BRICK_FROM_SINGLE_BAR_variation1() {
        // BarCount=3, BarPricePattern=no-move, BarVolumeAmount=mixed, ReversalAmount=3, BoxSize=large
        Duration period = Duration.ofMinutes(30);
        Instant t0 = BASE_TIME;
        double boxSize = 20.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 200.0, 200.0, 200.0, 200.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 260.0, 260.0, 260.0, 260.0, 50.0, 500.0, 5L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 260.0, 260.0, 260.0, 260.0, 25.0, 250.0, 3L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 3);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_LONG_FLAT_THEN_MOVE_variation1() {
        // BarCount=4, BarPricePattern=flat, BarVolumeAmount=zero, ReversalAmount=1, BoxSize=small
        Duration period = Duration.ofMinutes(1);
        Instant t0 = BASE_TIME;
        double boxSize = 0.2;
        Bar bar0 = bar(period, t0.minus(period), t0, 5.0, 5.0, 5.0, 5.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 5.0, 5.0, 5.0, 5.0, 0.0, 0.0, 0L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 5.0, 5.0, 5.0, 5.0, 0.0, 0.0, 0L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 5.3, 5.3, 5.3, 5.3, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 1);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_ZERO_VOLUME_AMOUNT_variation1() {
        // BarCount=5, BarPricePattern=up, BarVolumeAmount=positive, ReversalAmount=2, BoxSize=medium
        Duration period = Duration.ofMinutes(5);
        Instant t0 = BASE_TIME;
        double boxSize = 2.5;
        Bar bar0 = bar(period, t0.minus(period), t0, 10.0, 10.0, 10.0, 12.5, 0.0, 0.0, 1L);
        Bar bar1 = bar(period, t0, t0.plus(period), 12.5, 12.5, 12.5, 15.0, 0.0, 0.0, 2L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 15.0, 15.0, 15.0, 17.5, 0.0, 0.0, 3L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 17.5, 17.5, 17.5, 20.0, 0.0, 0.0, 4L);
        Bar bar4 = bar(period, t0.plus(period.multipliedBy(3)), t0.plus(period.multipliedBy(4)), 20.0, 20.0, 20.0, 22.5, 0.0, 0.0, 5L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 2);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3, bar4);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_ZERO_TRADES_variation1() {
        // BarCount=1, BarPricePattern=down, BarVolumeAmount=mixed, ReversalAmount=3, BoxSize=large
        Duration period = Duration.ofMinutes(10);
        Instant t0 = BASE_TIME;
        double boxSize = 15.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 150.0, 150.0, 150.0, 135.0, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 3);

        List<Bar> sourceBars = List.of(bar0);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_LARGE_REVERSAL_AMOUNT_variation1() {
        // BarCount=2, BarPricePattern=up-then-down, BarVolumeAmount=zero, ReversalAmount=1, BoxSize=small
        Duration period = Duration.ofMinutes(2);
        Instant t0 = BASE_TIME;
        double boxSize = 0.2;
        Bar bar0 = bar(period, t0.minus(period), t0, 5.0, 5.0, 5.0, 5.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 5.1, 5.1, 5.1, 5.1, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 1);

        List<Bar> sourceBars = List.of(bar0, bar1);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_SMALL_BOX_SIZE_variation1() {
        // BarCount=3, BarPricePattern=down-then-up, BarVolumeAmount=positive, ReversalAmount=2, BoxSize=medium
        Duration period = Duration.ofMinutes(3);
        Instant t0 = BASE_TIME;
        double boxSize = 0.0001;
        Bar bar0 = bar(period, t0.minus(period), t0, 1.0000, 1.0000, 1.0000, 1.0000, 10.0, 10.0, 1L);
        Bar bar1 = bar(period, t0, t0.plus(period), 0.9999, 0.9999, 0.9999, 0.9999, 11.0, 11.0, 2L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 1.0001, 1.0001, 1.0001, 1.0001, 12.0, 12.0, 3L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 2);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_LARGE_BOX_SIZE_variation1() {
        // BarCount=4, BarPricePattern=multi-brick, BarVolumeAmount=mixed, ReversalAmount=3, BoxSize=large
        Duration period = Duration.ofMinutes(30);
        Instant t0 = BASE_TIME;
        double boxSize = 1000.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 5000.0, 5000.0, 5000.0, 5000.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 6000.0, 6000.0, 6000.0, 6000.0, 10.0, 100.0, 1L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 7000.0, 7000.0, 7000.0, 7000.0, 20.0, 200.0, 2L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 8000.0, 8000.0, 8000.0, 8000.0, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 3);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_NON_INTEGER_BOX_SIZE_variation1() {
        // BarCount=5, BarPricePattern=reversal, BarVolumeAmount=zero, ReversalAmount=1, BoxSize=small
        Duration period = Duration.ofMinutes(2);
        Instant t0 = BASE_TIME;
        double boxSize = 1.5;
        Bar bar0 = bar(period, t0.minus(period), t0, 10.0, 10.0, 10.0, 10.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 11.5, 11.5, 11.5, 11.5, 0.0, 0.0, 0L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 13.0, 13.0, 13.0, 13.0, 0.0, 0.0, 0L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 11.5, 11.5, 11.5, 11.5, 0.0, 0.0, 0L);
        Bar bar4 = bar(period, t0.plus(period.multipliedBy(3)), t0.plus(period.multipliedBy(4)), 10.0, 10.0, 10.0, 10.0, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 1);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3, bar4);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_NON_INTEGER_PRICES_variation1() {
        // BarCount=1, BarPricePattern=threshold, BarVolumeAmount=positive, ReversalAmount=2, BoxSize=medium
        Duration period = Duration.ofMinutes(5);
        Instant t0 = BASE_TIME;
        double boxSize = 1.5;
        Bar bar0 = bar(period, t0.minus(period), t0, 10.25, 10.75, 10.0, 11.75, 5.0, 12.5, 2L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 2);

        List<Bar> sourceBars = List.of(bar0);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_DIFFERENT_NUM_IMPLEMENTATION_variation1() {
        // BarCount=2, BarPricePattern=no-move, BarVolumeAmount=mixed, ReversalAmount=3, BoxSize=large
        Duration period = Duration.ofMinutes(10);
        Instant t0 = BASE_TIME;
        double boxSize = 20.0;
        Bar bar0 = barDouble(period, t0.minus(period), t0, 100.0, 100.0, 100.0, 100.0, 0.0, 0.0, 0L);
        Bar bar1 = barDouble(period, t0, t0.plus(period), 100.0, 100.0, 100.0, 100.0, 10.0, 100.0, 5L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 3);

        List<Bar> sourceBars = List.of(bar0, bar1);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_TIME_GAP_EDGE_variation1() {
        // BarCount=3, BarPricePattern=flat, BarVolumeAmount=zero, ReversalAmount=1, BoxSize=small
        Duration period = Duration.ofNanos(1);
        Instant t0 = BASE_TIME;
        double boxSize = 0.1;
        Bar bar0 = bar(period, t0.minus(period), t0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0L);
        Bar bar1 = bar(period, t0, t0.plus(period), 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 1);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MULTI_BAR_MAX_BAR_COUNT_variation1() {
        // BarCount=4, BarPricePattern=up, BarVolumeAmount=positive, ReversalAmount=2, BoxSize=medium
        Duration period = Duration.ofMinutes(5);
        Instant t0 = BASE_TIME;
        double boxSize = 2.0;
        Bar bar0 = bar(period, t0.minus(period), t0, 10.0, 10.0, 10.0, 12.0, 10.0, 20.0, 1L);
        Bar bar1 = bar(period, t0, t0.plus(period), 12.0, 12.0, 12.0, 14.0, 11.0, 22.0, 2L);
        Bar bar2 = bar(period, t0.plus(period), t0.plus(period.multipliedBy(2)), 14.0, 14.0, 14.0, 16.0, 12.0, 24.0, 3L);
        Bar bar3 = bar(period, t0.plus(period.multipliedBy(2)), t0.plus(period.multipliedBy(3)), 16.0, 16.0, 16.0, 18.0, 13.0, 26.0, 4L);
        RenkoBarAggregator aggregator = new RenkoBarAggregator(boxSize, 2);

        List<Bar> sourceBars = List.of(bar0, bar1, bar2, bar3);
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
