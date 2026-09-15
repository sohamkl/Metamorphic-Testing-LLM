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

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(1);

    private static Num n(double value) {
        return DecimalNum.valueOf(value);
    }

    private static List<Bar> bars(double... closes) {
        Double[] volumes = new Double[closes.length];
        Double[] amounts = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            volumes[i] = 0.0;
            amounts[i] = 0.0;
        }
        return bars(closes, volumes, amounts, trades);
    }

    private static List<Bar> bars(double[] closes, Double[] volumes, Double[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>(closes.length);
        for (int i = 0; i < closes.length; i++) {
            Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            Num close = n(closes[i]);
            Num volume = volumes[i] == null ? null : n(volumes[i]);
            Num amount = amounts[i] == null ? null : n(amounts[i]);
            result.add(new BaseBar(PERIOD, begin, end, close, close, close, close, volume, amount, trades[i]));
        }
        return result;
    }

    @Test
    public void EMPTY_SOURCE_LIST_1_empty() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> source = List.of();
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_BAR_ESTABLISHES_BASELINE_1_decimal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(100.25);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNCHANGED_CLOSES_1_zeroMetadata() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = bars(100, 100, 100);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MOVEMENT_STRICTLY_BELOW_BOX_1_decimal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 1);
        List<Bar> source = bars(100, 109.999, 109.5);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_ONE_BOX_UP_1_halfBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> source = bars(100, 100.5);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_ONE_BOX_DOWN_1_decimal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> source = bars(100, 99);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTIPLE_UP_BRICKS_FROM_ONE_BAR_1_nullVolume() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = bars(
                new double[] { 100, 107 },
                new Double[] { null, 5.0 },
                new Double[] { 0.0, 12.0 },
                new long[] { 0, 4 });
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTIPLE_DOWN_BRICKS_FROM_ONE_BAR_1_decimal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 2);
        List<Bar> source = bars(
                new double[] { 100, 69 },
                new Double[] { 1.5, 2.5 },
                new Double[] { null, 7.5 },
                new long[] { 1, 2 });
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_CONTINUATION_1_halfBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 3);
        List<Bar> source = bars(100, 100.5, 101.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_CONTINUATION_1_decimal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> source = bars(100, 98, 95);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_REVERSAL_EXACT_THRESHOLD_1_integer() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(100, 102, 104, 100);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UP_REVERSAL_BELOW_THRESHOLD_1_decimal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 3);
        List<Bar> source = bars(100, 110, 120, 100.001);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DOWN_REVERSAL_EXACT_THRESHOLD_1_halfBox() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> source = bars(100, 99.5, 99, 100);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_BOX_REVERSAL_1_decimal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> source = bars(100, 101, 100);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THREE_BOX_REVERSAL_DELAY_1_integer() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = bars(100, 102, 104, 101);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PENDING_METADATA_BEFORE_FIRST_BRICK_1_positive() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(
                new double[] { 100, 100.5, 102 },
                new Double[] { 3.0, 4.0, 1.0 },
                new Double[] { 30.0, 40.0, 10.0 },
                new long[] { 1, 2, 1 });
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NULL_METADATA_SKIPPED_1_nullFields() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> source = bars(
                new double[] { 100, 100.5 },
                new Double[] { null, 0.0 },
                new Double[] { 0.0, null },
                new long[] { 0, 0 });
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_METADATA_1_decimal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> source = bars(
                new double[] { 100, 101 },
                new Double[] { 0.0, 0.0 },
                new Double[] { 0.0, 0.0 },
                new long[] { 0, 0 });
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void METADATA_ZEROED_ON_EXTRA_BRICKS_1_integer() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = bars(
                new double[] { 100, 106 },
                new Double[] { 0.0, 9.0 },
                new Double[] { 0.0, 90.0 },
                new long[] { 0, 6 });
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BRICK_TIME_AT_SOURCE_END_1_minuteBars() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 2);
        List<Bar> source = bars(100, 110);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BRICK_TIME_SCHEDULE_ADVANCES_1_multipleBricks() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 3);
        List<Bar> source = bars(100, 102);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOURCE_END_OVERTAKES_SCHEDULE_1_continuation() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> source = bars(100, 104, 105);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FRACTIONAL_BOX_AND_DECIMAL_PRICES_1_decimalReversal() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> source = bars(10.0, 11.0, 10.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIGNED_METADATA_ACCUMULATION_1_signedValues() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 3);
        List<Bar> source = bars(
                new double[] { 100, 110 },
                new Double[] { -3.0, 5.0 },
                new Double[] { -30.0, 50.0 },
                new long[] { 2, -1 });
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_DIRECTION_METADATA_SEQUENCE_1_distinctMetadata() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> source = bars(
                new double[] { 100, 102, 106, 102 },
                new Double[] { 1.0, 2.0, 3.0, 4.0 },
                new Double[] { 10.0, 20.0, 30.0, 40.0 },
                new long[] { 1, 2, 3, 4 });
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpSource = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpSource);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
