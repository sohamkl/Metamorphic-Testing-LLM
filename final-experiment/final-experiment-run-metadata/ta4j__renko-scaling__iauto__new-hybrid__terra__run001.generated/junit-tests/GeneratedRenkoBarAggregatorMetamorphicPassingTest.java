import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofSeconds(60);
    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");

    private static List<Bar> bars(double... closes) {
        Double[] metrics = new Double[closes.length];
        long[] trades = new long[closes.length];
        for (int i = 0; i < closes.length; i++) {
            metrics[i] = 1.0;
            trades[i] = 1L;
        }
        return barsWithMetadata(closes, metrics, metrics, trades);
    }

    private static List<Bar> barsWithMetadata(double[] closes, Double[] volumes, Double[] amounts, long[] trades) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            Instant begin = START.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            Num close = num(closes[i]);
            Num volume = volumes[i] == null ? null : num(volumes[i]);
            Num amount = amounts[i] == null ? null : num(amounts[i]);
            result.add(new BaseBar(PERIOD, begin, end, close, close, close, close, volume, amount, trades[i]));
        }
        return result;
    }

    private static Num num(double value) {
        return DecimalNum.valueOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> castBars(Object value) {
        return (List<Bar>) value;
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        if (sourceOutput.size() != followUpOutput.size()) {
            throw new AssertionError("Source and follow-up produced different brick counts");
        }
        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);

            if (!Objects.equals(source.getTimePeriod(), followUp.getTimePeriod())) {
                throw new AssertionError("Different time period at brick " + i);
            }
            if (!Objects.equals(source.getBeginTime(), followUp.getBeginTime())) {
                throw new AssertionError("Different begin time at brick " + i);
            }
            if (!Objects.equals(source.getEndTime(), followUp.getEndTime())) {
                throw new AssertionError("Different end time at brick " + i);
            }
            if (!Objects.equals(source.getVolume(), followUp.getVolume())) {
                throw new AssertionError("Different volume at brick " + i);
            }
            if (source.getTrades() != followUp.getTrades()) {
                throw new AssertionError("Different trade count at brick " + i);
            }

            int sourceDirection = source.getClosePrice().isGreaterThan(source.getOpenPrice()) ? 1
                    : source.getClosePrice().isLessThan(source.getOpenPrice()) ? -1 : 0;
            int followUpDirection = followUp.getClosePrice().isGreaterThan(followUp.getOpenPrice()) ? 1
                    : followUp.getClosePrice().isLessThan(followUp.getOpenPrice()) ? -1 : 0;
            if (sourceDirection != followUpDirection) {
                throw new AssertionError("Different direction at brick " + i);
            }

            assertScaled(source.getOpenPrice(), followUp.getOpenPrice(), "open", i);
            assertScaled(source.getHighPrice(), followUp.getHighPrice(), "high", i);
            assertScaled(source.getLowPrice(), followUp.getLowPrice(), "low", i);
            assertScaled(source.getClosePrice(), followUp.getClosePrice(), "close", i);
            assertScaled(source.getAmount(), followUp.getAmount(), "amount", i);
        }
    }

    private static void assertScaled(Num source, Num followUp, String field, int index) {
        if (source == null || followUp == null) {
            if (source != followUp) {
                throw new AssertionError("Mismatched null " + field + " at brick " + index);
            }
            return;
        }
        Num expected = source.multipliedBy(source.getNumFactory().numOf(2.0));
        if (!followUp.isEqual(expected)) {
            throw new AssertionError("Follow-up " + field + " is not doubled at brick " + index);
        }
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = List.of();
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_BAR_INITIAL_REFERENCE_ONLY_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FLAT_MULTI_BAR_NO_BRICKS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 10.0, 10.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUB_BOX_MOVE_NO_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 10.5);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_EXACT_UP_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 11.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_EXACT_DOWN_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 9.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MULTI_UP_FROM_NONE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 13.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MULTI_DOWN_FROM_NONE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 7.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_CONTINUATION_EXACT_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 11.0, 12.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_DIRECTION_SMALL_PULLBACK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 11.0, 10.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_AT_TWO_BOX_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 11.0, 9.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_REVERSAL_OVERSHOOT_MULTI_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 11.0, 7.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_CONTINUATION_EXACT_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 9.0, 8.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_DIRECTION_SMALL_RALLY_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 9.0, 10.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_AT_TWO_BOX_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 9.0, 11.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_REVERSAL_OVERSHOOT_MULTI_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 9.0, 13.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BOX_REVERSAL_CONFIGURATION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> source = bars(10.0, 11.0, 10.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void THREE_BOX_REVERSAL_NOT_YET_REACHED_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> source = bars(10.0, 11.0, 9.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void THREE_BOX_REVERSAL_EXACTLY_REACHED_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> source = bars(10.0, 11.0, 8.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_CONSTRUCTOR_AND_HALF_BOX_LATTICE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5);
        List<Bar> source = bars(10.0, 10.5, 9.5);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METADATA_ACCUMULATED_BEFORE_FIRST_BRICK_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 10.0, 11.0},
                new Double[] {2.0, 3.0, 5.0},
                new Double[] {20.0, 30.0, 50.0},
                new long[] {1L, 2L, 3L});
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_BRICK_CURRENT_BAR_METADATA_ZEROED_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 13.0},
                new Double[] {2.0, 7.0},
                new Double[] {20.0, 70.0},
                new long[] {1L, 4L});
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_TREATED_AS_NO_CONTRIBUTION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 11.0},
                new Double[] {null, null},
                new Double[] {null, null},
                new long[] {2L, 3L});
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_METADATA_VALUES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 11.0},
                new Double[] {0.0, 0.0},
                new Double[] {0.0, 0.0},
                new long[] {0L, 0L});
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DELAYED_EMISSION_USES_NEXT_BRICK_END_TIME_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 13.0, 14.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DELAYED_EMISSION_USES_SOURCE_BAR_END_TIME_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 13.0, 13.0, 13.0, 13.0, 14.0);
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] transformed = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput = ((RenkoBarAggregator) transformed[0]).aggregate(castBars(transformed[1]));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
