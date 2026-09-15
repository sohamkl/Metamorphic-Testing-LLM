import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static Bar bar(double close) {
        return bar(close, close - 0.25, close + 0.25, 10.0, 100.0, 1L);
    }

    private static Bar bar(double close, Double volume, Double amount, long trades) {
        return bar(close, close - 0.25, close + 0.25, volume, amount, trades);
    }

    private static Bar bar(double close, double open, double high, Double volume, Double amount, long trades) {
        return new BaseBar(
                PERIOD,
                BASE.plusSeconds(indexFor(close)),
                BASE.plusSeconds(indexFor(close) + 1),
                DecimalNum.valueOf(open),
                DecimalNum.valueOf(high),
                DecimalNum.valueOf(Math.min(open, close) - 0.1),
                DecimalNum.valueOf(close),
                volume == null ? null : DecimalNum.valueOf(volume),
                amount == null ? null : DecimalNum.valueOf(amount),
                trades);
    }

    private static Bar indexedBar(int index, double close, Double volume, Double amount, long trades) {
        double open = close - 0.25;
        return new BaseBar(
                PERIOD,
                BASE.plusSeconds(index),
                BASE.plusSeconds(index + 1),
                DecimalNum.valueOf(open),
                DecimalNum.valueOf(Math.max(open, close) + 0.25),
                DecimalNum.valueOf(Math.min(open, close) - 0.25),
                DecimalNum.valueOf(close),
                volume == null ? null : DecimalNum.valueOf(volume),
                amount == null ? null : DecimalNum.valueOf(amount),
                trades);
    }

    private static int indexFor(double ignored) {
        return 0;
    }

    private static List<Bar> bars(double... closes) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            result.add(indexedBar(i, closes[i], 10.0, 100.0, 1L));
        }
        return result;
    }

    private static List<Bar> barsWithMetadata(double[] closes, Double[] volumes, Double[] amounts,
            long[] trades) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            result.add(indexedBar(i, closes[i], volumes[i], amounts[i], trades[i]));
        }
        return result;
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> source = List.of();

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_BAR_INITIAL_BASELINE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.25);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EQUAL_CLOSE_SEQUENCE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 10.0},
                new Double[] {10.0, 10.0},
                new Double[] {null, null},
                new long[] {1L, 2L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_EXACT_UP_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.5, 1);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 12.5},
                new Double[] {null, null},
                new Double[] {null, null},
                new long[] {0L, 0L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_EXACT_DOWN_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 2);
        List<Bar> source = bars(100.0, 90.0);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_SUB_BOX_UP_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 3);
        List<Bar> source = bars(10.0, 10.25);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_SUB_BOX_DOWN_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> source = bars(10.0, 9.5);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_SOURCE_BAR_MULTIPLE_UP_BRICKS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 15.0},
                new Double[] {4.0, 6.0},
                new Double[] {40.0, 60.0},
                new long[] {2L, 3L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_SOURCE_BAR_MULTIPLE_DOWN_BRICKS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.5, 3);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 2.5},
                new Double[] {5.0, null},
                new Double[] {50.0, 25.0},
                new long[] {1L, 4L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METADATA_THEN_UP_EMISSION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 1);
        List<Bar> source = barsWithMetadata(
                new double[] {100.0, 104.0, 109.0, 110.0},
                new Double[] {2.0, 3.0, null, 4.0},
                new Double[] {20.0, 30.0, 40.0, null},
                new long[] {1L, 2L, 3L, 4L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METADATA_THEN_DOWN_EMISSION_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 10.2, 9.8, 9.5},
                new Double[] {1.0, 2.0, 3.0, 4.0},
                new Double[] {10.0, 20.0, 30.0, 40.0},
                new long[] {1L, 2L, 3L, 4L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_CONTINUATION_ONE_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> source = bars(10.0, 11.0, 12.0);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_CONTINUATION_ONE_BOX_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = bars(10.0, 8.0, 6.0);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_SUB_THRESHOLD_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.5, 2);
        List<Bar> source = bars(10.0, 12.5, 10.5);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_EXACT_REVERSAL_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 3);
        List<Bar> source = bars(100.0, 110.0, 80.0);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_MULTI_BOX_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 10.5, 8.0},
                new Double[] {null, null, null},
                new Double[] {null, null, null},
                new long[] {0L, 0L, 7L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_SUB_THRESHOLD_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);
        List<Bar> source = bars(10.0, 9.0, 10.5);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_EXACT_REVERSAL_THRESHOLD_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 3);
        List<Bar> source = bars(10.0, 8.0, 14.0);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_MULTI_BOX_REVERSAL_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.5, 1);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 7.5, 15.0},
                new Double[] {5.0, 5.0, 5.0},
                new Double[] {50.0, 50.0, 50.0},
                new long[] {1L, 1L, 1L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_AMOUNT_ONE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 1);
        List<Bar> source = bars(100.0, 110.0, 100.0);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_AMOUNT_THREE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 3);
        List<Bar> source = bars(10.0, 10.5, 9.5, 9.0, 8.0);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SOURCE_END_EQUALS_NEXT_BRICK_END_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 1);
        List<Bar> source = bars(10.0, 11.0);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SOURCE_END_AFTER_NEXT_BRICK_END_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 2);
        List<Bar> source = bars(10.0, 10.5, 12.0);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTIPLE_BRICKS_ADVANCE_SYNTHETIC_END_TIMES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.5, 3);
        List<Bar> source = bars(10.0, 20.0);

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_METADATA_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 1);
        List<Bar> source = barsWithMetadata(
                new double[] {100.0, 110.0},
                new Double[] {10.0, null},
                new Double[] {100.0, 110.0},
                new long[] {1L, 2L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_AMOUNT_METADATA_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 2);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 10.5},
                new Double[] {3.0, 4.0},
                new Double[] {null, null},
                new long[] {1L, 2L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_METADATA_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 3);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 11.0},
                new Double[] {10.0, null},
                new Double[] {100.0, null},
                new long[] {2L, 7L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TRADE_COUNT_ACCUMULATION_AND_ZEROING_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.0, 1);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 10.5, 11.0, 16.0},
                new Double[] {null, null, null, null},
                new Double[] {null, null, null, null},
                new long[] {2L, 3L, 4L, 5L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_TRADE_SOURCE_BARS_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.5, 2);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 15.0},
                new Double[] {1.0, 1.0},
                new Double[] {10.0, 10.0},
                new long[] {0L, 0L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_BOX_AND_PRICE_VALUES_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(10.0, 3);
        List<Bar> source = barsWithMetadata(
                new double[] {10.25, 20.25},
                new Double[] {2.5, 3.5},
                new Double[] {12.75, 17.25},
                new long[] {1L, 2L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTEGER_BOX_WITH_DECIMAL_METADATA_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(0.5, 1);
        List<Bar> source = barsWithMetadata(
                new double[] {10.0, 10.5},
                new Double[] {4.0, 5.0},
                new Double[] {10.25, 20.75},
                new long[] {1L, 1L});

        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        List<Bar> followUpOutput =
                ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_SOURCE_LIST_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(1.0, 2);

        Assertions.assertThrows(NullPointerException.class, () -> aggregator.aggregate(null));
        Assertions.assertThrows(
                NullPointerException.class,
                () -> RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, null));
    }

    @Test
    void NULL_CLOSE_PRICE_variation1() {
        RenkoBarAggregator aggregator = new RenkoBarAggregator(2.5, 1);
        List<Bar> source = new ArrayList<>();
        source.add(new BaseBar(
                PERIOD,
                BASE,
                BASE.plusSeconds(1),
                DecimalNum.valueOf(10.0),
                DecimalNum.valueOf(10.5),
                DecimalNum.valueOf(9.5),
                null,
                null,
                null,
                1L));

        Assertions.assertThrows(IllegalArgumentException.class, () -> aggregator.aggregate(source));
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ((RenkoBarAggregator) followUp[0]).aggregate((List<Bar>) followUp[1]));
    }
}
