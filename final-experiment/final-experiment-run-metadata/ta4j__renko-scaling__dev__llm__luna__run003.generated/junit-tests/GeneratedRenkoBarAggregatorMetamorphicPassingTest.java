import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DoubleNum;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofHours(1);
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static DoubleNum num(double value) {
        return DoubleNum.valueOf(value);
    }

    private static Bar bar(int index, double close, Double volume, Double amount, long trades) {
        Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(index));
        Instant end = begin.plus(PERIOD);
        DoubleNum price = num(close);
        return new BaseBar(
                PERIOD,
                begin,
                end,
                price,
                price,
                price,
                price,
                volume == null ? null : num(volume),
                amount == null ? null : num(amount),
                trades);
    }

    @Test
    public void emptySourceListVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 1);
        List<Bar> source = List.of();

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void singleBaselineBarVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2, 2);
        List<Bar> source = List.of(bar(0, 100, 1.0, 1.0, 1));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void upExactlyOneBoxVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 101, 2.0, 3.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void upBelowBoxThresholdVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 100.99, 2.0, 3.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void upMultipleBoxesSingleBarVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 10.0, 1),
                bar(1, 103, 10.0, 30.0, 4));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void downExactlyOneBoxFromNoneVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 99, 2.0, 3.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void downMultipleBoxesSingleBarVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 10.0, 1),
                bar(1, 97, 8.0, 24.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void upContinuationWithoutReversalVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 101, 2.0, 3.0, 2),
                bar(2, 100.5, 3.0, 4.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void upReversalAtExactThresholdVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 101, 2.0, 3.0, 2),
                bar(2, 99, 3.0, 4.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void upReversalBeyondThresholdVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 101, 2.0, 3.0, 2),
                bar(2, 97, 3.0, 4.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void downContinuationVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 99, 2.0, 3.0, 2),
                bar(2, 98, 3.0, 4.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void downReversalAtExactThresholdVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 99, 2.0, 3.0, 2),
                bar(2, 101, 3.0, 4.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void reversalAmountOneVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 1);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 101, 2.0, 3.0, 2),
                bar(2, 100, 3.0, 4.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void reversalAmountThreeRequiresLargerMoveVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 3);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 2.0, 1),
                bar(1, 101, 2.0, 3.0, 2),
                bar(2, 99, 3.0, 4.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void pendingMetricsAccumulateBeforeEmissionVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 10.0, 1),
                bar(1, 100.25, 2.0, 20.0, 2),
                bar(2, 100.5, 3.0, 30.0, 3),
                bar(3, 101, 4.0, 40.0, 4));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void nullVolumeAndAmountComponentsVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, null, 10.0, 1),
                bar(1, 101, 2.0, null, 2));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void zeroTradeMetricsVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 10.0, 0),
                bar(1, 102, 2.0, 20.0, 0));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void multipleBricksAdvanceBrickTimesVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 10.0, 1),
                bar(1, 103, 2.0, 20.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void negativeAndZeroPriceLevelsVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, -1, 1.0, 2.0, 1),
                bar(1, 0, 2.0, 3.0, 2),
                bar(2, 1, 3.0, 4.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void sourceMetricsWithNoBricksVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 10.0, 1),
                bar(1, 100.2, 2.0, 20.0, 2),
                bar(2, 100.4, 3.0, 30.0, 3));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void boxSizeTwoIntegerScalingVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 10.0, 1),
                bar(1, 104, 2.0, 20.0, 2));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void mixedEmissionAndPendingSourceBarsVariation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1, 2);
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 10.0, 1),
                bar(1, 101, 2.0, 20.0, 2),
                bar(2, 101.5, 3.0, 30.0, 3),
                bar(3, 103, 4.0, 40.0, 4));

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);

        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
