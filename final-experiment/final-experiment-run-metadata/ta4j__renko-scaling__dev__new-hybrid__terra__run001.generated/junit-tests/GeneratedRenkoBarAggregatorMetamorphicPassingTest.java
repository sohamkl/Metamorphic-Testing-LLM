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

    private static void exercise(RenkoBarAggregator aggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = aggregator.aggregate(sourceBars);

        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static List<Bar> series(double... closes) {
        List<Bar> bars = new ArrayList<>();
        for (int index = 0; index < closes.length; index++) {
            bars.add(bar(index, closes[index], (double) (index + 1), (double) ((index + 1) * 10), index + 1));
        }
        return bars;
    }

    private static Bar bar(int index, double close, Double volume, Double amount, long trades) {
        Duration period = Duration.ofMinutes(1);
        Instant beginTime = Instant.EPOCH.plus(period.multipliedBy(index));
        Instant endTime = beginTime.plus(period);
        Num price = DecimalNum.valueOf(close);
        Num barVolume = volume == null ? null : DecimalNum.valueOf(volume);
        Num barAmount = amount == null ? null : DecimalNum.valueOf(amount);
        return new BaseBar(
                period,
                beginTime,
                endTime,
                price,
                price,
                price,
                price,
                barVolume,
                barAmount,
                trades);
    }

    @Test
    void emptySourceList_variation1() {
        exercise(new RenkoBarAggregator(2), List.of());
    }

    @Test
    void singleBaselineBar_variation1() {
        exercise(new RenkoBarAggregator(2), series(100));
    }

    @Test
    void singleSubBoxMove_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 101));
    }

    @Test
    void initialUpExactOneBox_variation1() {
        exercise(new RenkoBarAggregator(2, 3), series(100, 102));
    }

    @Test
    void initialUpMultipleBoxes_variation1() {
        exercise(new RenkoBarAggregator(2), series(100, 106));
    }

    @Test
    void initialDownExactOneBox_variation1() {
        exercise(new RenkoBarAggregator(2), series(100, 98));
    }

    @Test
    void initialDownMultipleBoxes_variation1() {
        exercise(new RenkoBarAggregator(2, 1), series(100, 94));
    }

    @Test
    void upwardContinuation_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 102, 106));
    }

    @Test
    void downwardContinuation_variation1() {
        exercise(new RenkoBarAggregator(2, 3), series(100, 98, 94));
    }

    @Test
    void upDirectionFlatBar_variation1() {
        exercise(new RenkoBarAggregator(2), series(100, 102, 102));
    }

    @Test
    void downDirectionFlatBar_variation1() {
        exercise(new RenkoBarAggregator(2), series(100, 98, 98));
    }

    @Test
    void upPullbackBelowReversal_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 104, 101));
    }

    @Test
    void upPullbackThenContinuation_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 104, 101, 106));
    }

    @Test
    void upToDownExactReversal_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 104, 100));
    }

    @Test
    void upToDownReversalOvershoot_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 104, 98));
    }

    @Test
    void downPullbackBelowReversal_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 96, 99));
    }

    @Test
    void downPullbackThenContinuation_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 96, 99, 94));
    }

    @Test
    void downToUpExactReversal_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 96, 100));
    }

    @Test
    void downToUpReversalOvershoot_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 96, 102));
    }

    @Test
    void oneBoxReversalConfiguration_variation1() {
        exercise(new RenkoBarAggregator(2, 1), series(100, 104, 102));
    }

    @Test
    void threeBoxReversalNotReached_variation1() {
        exercise(new RenkoBarAggregator(2, 3), series(100, 104, 99));
    }

    @Test
    void threeBoxReversalExact_variation1() {
        exercise(new RenkoBarAggregator(2, 3), series(100, 104, 98));
    }

    @Test
    void defaultConstructorTwoBoxReversal_variation1() {
        exercise(new RenkoBarAggregator(2), series(100, 104, 100));
    }

    @Test
    void fractionalBoxSizeExactThresholds_variation1() {
        exercise(new RenkoBarAggregator(0.5, 2), series(10, 11.5));
    }

    @Test
    void pendingMetricsBeforeFirstBrick_variation1() {
        List<Bar> source = List.of(
                bar(0, 100, 3.0, 30.0, 2),
                bar(1, 101, 5.0, 50.0, 3),
                bar(2, 102, 7.0, 70.0, 4));
        exercise(new RenkoBarAggregator(2), source);
    }

    @Test
    void multiBrickMetricsFirstOnly_variation1() {
        List<Bar> source = List.of(
                bar(0, 100, 2.0, 20.0, 1),
                bar(1, 106, 9.0, 90.0, 6));
        exercise(new RenkoBarAggregator(2), source);
    }

    @Test
    void metricsResetAfterEmission_variation1() {
        List<Bar> source = List.of(
                bar(0, 100, 1.0, 10.0, 1),
                bar(1, 102, 2.0, 20.0, 2),
                bar(2, 103, 3.0, 30.0, 3),
                bar(3, 104, 4.0, 40.0, 4));
        exercise(new RenkoBarAggregator(2), source);
    }

    @Test
    void nullVolumeAccumulation_variation1() {
        List<Bar> source = List.of(
                bar(0, 100, null, 10.0, 1),
                bar(1, 101, 4.0, 20.0, 2),
                bar(2, 102, null, 30.0, 3));
        exercise(new RenkoBarAggregator(2), source);
    }

    @Test
    void nullAmountAccumulation_variation1() {
        List<Bar> source = List.of(
                bar(0, 100, 2.0, null, 1),
                bar(1, 101, 3.0, 20.0, 2),
                bar(2, 102, 4.0, null, 3));
        exercise(new RenkoBarAggregator(2), source);
    }

    @Test
    void explicitZeroSourceMetrics_variation1() {
        List<Bar> source = List.of(
                bar(0, 100, 0.0, 0.0, 0),
                bar(1, 102, 0.0, 0.0, 0));
        exercise(new RenkoBarAggregator(2), source);
    }

    @Test
    void delayedFirstBrickTime_variation1() {
        exercise(new RenkoBarAggregator(2), series(100, 101, 102));
    }

    @Test
    void equalNextBrickEndTime_variation1() {
        exercise(new RenkoBarAggregator(2), series(100, 102, 104));
    }

    @Test
    void sourceEndBehindNextBrickTime_variation1() {
        exercise(new RenkoBarAggregator(2, 2), series(100, 106, 108));
    }
}
