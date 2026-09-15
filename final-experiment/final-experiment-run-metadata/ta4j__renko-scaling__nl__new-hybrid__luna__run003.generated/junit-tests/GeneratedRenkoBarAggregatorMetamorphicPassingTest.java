import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
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

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofSeconds(1);
    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Num TWO = n("2");

    private static void assertMetamorphicRelationFor(RenkoBarAggregator sourceReceiver,
            List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceReceiver.aggregate(sourceBars);
        List<Bar> followUpOutput = followUpReceiver(sourceReceiver, sourceBars)
                .aggregate(generateFollowUp(sourceBars));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static RenkoBarAggregator followUpReceiver(RenkoBarAggregator source,
            List<Bar> ignored) {
        return new RenkoBarAggregator(
                sourceBoxSize(source).multiply(BigDecimal.valueOf(2)),
                sourceReversalAmount(source));
    }

    private static BigDecimal sourceBoxSize(RenkoBarAggregator source) {
        try {
            var field = RenkoBarAggregator.class.getDeclaredField("boxSize");
            field.setAccessible(true);
            return new BigDecimal(field.get(source).toString());
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError(exception);
        }
    }

    private static int sourceReversalAmount(RenkoBarAggregator source) {
        try {
            var field = RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            field.setAccessible(true);
            return field.getInt(source);
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError(exception);
        }
    }

    private static List<Bar> generateFollowUp(List<Bar> source) {
        List<Bar> result = new ArrayList<>();
        for (Bar bar : source) {
            result.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    scale(bar.getOpenPrice()),
                    scale(bar.getHighPrice()),
                    scale(bar.getLowPrice()),
                    scale(bar.getClosePrice()),
                    bar.getVolume(),
                    scaleNullable(bar.getAmount()),
                    bar.getTrades()));
        }
        return result;
    }

    private static void assertMetamorphicRelation(List<Bar> source, List<Bar> followUp) {
        assertEquals(source.size(), followUp.size());
        for (int i = 0; i < source.size(); i++) {
            Bar expected = source.get(i);
            Bar actual = followUp.get(i);
            assertEquals(expected.getBeginTime(), actual.getBeginTime());
            assertEquals(expected.getEndTime(), actual.getEndTime());
            assertEquals(expected.getTimePeriod(), actual.getTimePeriod());
            assertEquals(expected.getVolume(), actual.getVolume());
            assertEquals(expected.getTrades(), actual.getTrades());
            assertEquals(scale(expected.getOpenPrice()), actual.getOpenPrice());
            assertEquals(scale(expected.getHighPrice()), actual.getHighPrice());
            assertEquals(scale(expected.getLowPrice()), actual.getLowPrice());
            assertEquals(scale(expected.getClosePrice()), actual.getClosePrice());
            if (expected.getAmount() == null) {
                assertEquals(null, actual.getAmount());
            } else {
                assertEquals(scale(expected.getAmount()), actual.getAmount());
            }
            assertNotNull(expected.getOpenPrice());
            assertEquals(direction(expected), direction(actual));
        }
    }

    private static String direction(Bar bar) {
        int comparison = bar.getClosePrice().compareTo(bar.getOpenPrice());
        return comparison > 0 ? "UP" : comparison < 0 ? "DOWN" : "NONE";
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(TWO);
    }

    private static Num scaleNullable(Num value) {
        return value == null ? null : scale(value);
    }

    private static Num n(String value) {
        return DecimalNum.valueOf(new BigDecimal(value));
    }

    private static List<Bar> bars(String[] closes, int metadataMode, int amountMode) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            Num close = n(closes[i]);
            Num open = close.plus(n(i % 2 == 0 ? "0.25" : "-0.25"));
            Num high = close.plus(n("0.5"));
            Num low = close.minus(n("0.5"));

            Num volume = metadataMode == 2 || metadataMode == 3 ? null : n(String.valueOf(i + 1));
            Num amount = amountMode == 2 || metadataMode == 3
                    ? null
                    : n(String.valueOf((i + 1) * 10));

            result.add(new BaseBar(
                    PERIOD,
                    BASE.plus(PERIOD.multipliedBy(i)),
                    BASE.plus(PERIOD.multipliedBy(i + 1)),
                    open,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    i == 0 ? 0 : i + 1));
        }
        return result;
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(0.5, 1), List.of());
    }

    @Test
    void SINGLE_BAR_BASELINE_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(1, 2),
                bars(new String[] { "10" }, 0, 0));
    }

    @Test
    void QUIET_ACCUMULATION_BEFORE_FIRST_BRICK_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2, 3),
                bars(new String[] { "10", "10.5", "10.75", "12" }, 1, 0));
    }

    @Test
    void INITIAL_UP_EXACT_THRESHOLD_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2.5, 4),
                bars(new String[] { "10", "12.5" }, 3, 3));
    }

    @Test
    void INITIAL_DOWN_EXACT_THRESHOLD_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(10, 1),
                bars(new String[] { "40", "30" }, 4, 4));
    }

    @Test
    void INITIAL_UP_MULTI_BRICK_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(0.5, 2),
                bars(new String[] { "1", "2.5" }, 1, 0));
    }

    @Test
    void INITIAL_DOWN_MULTI_BRICK_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(1, 3),
                bars(new String[] { "10", "7" }, 2, 1));
    }

    @Test
    void UP_CONTINUATION_EXACT_BOX_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2, 4),
                bars(new String[] { "10", "12", "14" }, 1, 0));
    }

    @Test
    void UP_CONTINUATION_BELOW_BOX_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2.5, 1),
                bars(new String[] { "10", "12.5", "14" }, 0, 2));
    }

    @Test
    void UP_CONTINUATION_MULTI_BRICK_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(10, 2),
                bars(new String[] { "10", "20", "50" }, 1, 0));
    }

    @Test
    void UP_REVERSAL_EXACT_DISTANCE_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(0.5, 3),
                bars(new String[] { "2", "2.5", "1" }, 1, 0));
    }

    @Test
    void UP_REVERSAL_BELOW_DISTANCE_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(1, 4),
                bars(new String[] { "10", "11", "10" }, 0, 0));
    }

    @Test
    void UP_REVERSAL_MULTI_DOWN_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2, 1),
                bars(new String[] { "10", "12", "6" }, 2, 2));
    }

    @Test
    void DOWN_CONTINUATION_EXACT_BOX_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2.5, 2),
                bars(new String[] { "20", "17.5", "15" }, 1, 1));
    }

    @Test
    void DOWN_CONTINUATION_BELOW_BOX_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(10, 3),
                bars(new String[] { "40", "30", "25" }, 0, 0));
    }

    @Test
    void DOWN_CONTINUATION_MULTI_BRICK_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(0.5, 4),
                bars(new String[] { "3", "2.5", "1" }, 3, 1));
    }

    @Test
    void DOWN_REVERSAL_EXACT_DISTANCE_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(1, 1),
                bars(new String[] { "10", "9", "10" }, 1, 0));
    }

    @Test
    void DOWN_REVERSAL_BELOW_DISTANCE_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2, 2),
                bars(new String[] { "10", "8", "11" }, 0, 2));
    }

    @Test
    void DOWN_REVERSAL_MULTI_UP_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2.5, 3),
                bars(new String[] { "20", "17.5", "25" }, 2, 1));
    }

    @Test
    void REVERSAL_AMOUNT_ONE_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(10, 1),
                bars(new String[] { "100", "110", "100" }, 1, 1));
    }

    @Test
    void REVERSAL_AMOUNT_GREATER_THAN_TWO_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(0.5, 4),
                bars(new String[] { "5", "5.5", "4.5", "3.5" }, 2, 2));
    }

    @Test
    void PENDING_METADATA_ACCUMULATION_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(1, 2),
                bars(new String[] { "10", "10.25", "10.5", "11" }, 1, 0));
    }

    @Test
    void MULTI_BRICK_METADATA_ZEROING_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2, 3),
                bars(new String[] { "10", "18" }, 1, 1));
    }

    @Test
    void NULL_VOLUME_METADATA_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2.5, 4),
                bars(new String[] { "10", "12.5" }, 2, 3));
    }

    @Test
    void NULL_AMOUNT_METADATA_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(10, 1),
                bars(new String[] { "40", "30" }, 3, 2));
    }

    @Test
    void BOTH_NULL_METADATA_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(0.5, 2),
                bars(new String[] { "2", "3" }, 4, 4));
    }

    @Test
    void SYNTHETIC_END_TIME_FOR_MULTI_BRICKS_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(1, 3),
                bars(new String[] { "10", "14" }, 1, 0));
    }

    @Test
    void SOURCE_END_TIME_AFTER_PENDING_NEXT_TIME_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2, 4),
                bars(new String[] { "10", "10.5", "12" }, 0, 2));
    }

    @Test
    void CLOSE_DRIVEN_OHLC_VARIATION_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2.5, 1),
                bars(new String[] { "10", "12.5", "7.5" }, 4, 3));
    }

    @Test
    void FRACTIONAL_BOX_AND_PRICES_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(10, 2),
                bars(new String[] { "1.25", "11.25", "31.25" }, 2, 1));
    }

    @Test
    void NONZERO_AND_ZERO_TRADE_COUNTS_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(0.5, 3),
                bars(new String[] { "5", "5.25", "6.5" }, 5, 0));
    }

    @Test
    void DEFAULT_TWO_BRICK_CONFIGURATION_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(1),
                bars(new String[] { "10", "11", "10", "9" }, 1, 0));
    }

    @Test
    void EXPLICIT_TWO_BRICK_CONFIGURATION_variation1() {
        assertMetamorphicRelationFor(new RenkoBarAggregator(2, 2),
                bars(new String[] { "10", "10.5", "12", "8" }, 2, 1));
    }
}
