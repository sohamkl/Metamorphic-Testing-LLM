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

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final DecimalNum TWO = DecimalNum.valueOf(2);

    private enum Shape {
        FLAT, WIDE, VARIED
    }

    private static final class SourceCase {
        private final BigDecimal boxSize;
        private final int reversalAmount;
        private final boolean useDefaultConstructor;
        private final List<Bar> bars;

        private SourceCase(String boxSize, int reversalAmount, boolean useDefaultConstructor, List<Bar> bars) {
            this.boxSize = new BigDecimal(boxSize);
            this.reversalAmount = reversalAmount;
            this.useDefaultConstructor = useDefaultConstructor;
            this.bars = bars;
        }

        private RenkoBarAggregator receiver() {
            return useDefaultConstructor
                    ? new RenkoBarAggregator(boxSize)
                    : new RenkoBarAggregator(boxSize, reversalAmount);
        }
    }

    private static SourceCase source(String boxSize, int reversalAmount,
            boolean useDefaultConstructor, String... closes) {
        String[] volumes = new String[closes.length];
        String[] amounts = new String[closes.length];
        long[] trades = new long[closes.length];

        for (int i = 0; i < closes.length; i++) {
            volumes[i] = Integer.toString(i + 1);
            amounts[i] = new BigDecimal(i + 1).add(new BigDecimal("0.25")).toPlainString();
            trades[i] = i + 1L;
        }

        return sourceWithMetadata(boxSize, reversalAmount, useDefaultConstructor,
                Shape.FLAT, closes, volumes, amounts, trades);
    }

    private static SourceCase sourceWithMetadata(String boxSize, int reversalAmount,
            boolean useDefaultConstructor, Shape shape, String[] closes,
            String[] volumes, String[] amounts, long[] trades) {
        assertEquals(closes.length, volumes.length);
        assertEquals(closes.length, amounts.length);
        assertEquals(closes.length, trades.length);

        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            BigDecimal close = new BigDecimal(closes[i]);
            BigDecimal open;
            BigDecimal high;
            BigDecimal low;

            if (shape == Shape.FLAT) {
                open = close;
                high = close;
                low = close;
            } else if (shape == Shape.WIDE) {
                BigDecimal spread = new BigDecimal("5000000").add(BigDecimal.valueOf(i));
                open = close;
                high = close.add(spread);
                low = close.subtract(spread);
            } else {
                BigDecimal offset = new BigDecimal("0.125").multiply(BigDecimal.valueOf(i + 1L));
                open = (i & 1) == 0 ? close.subtract(offset) : close.add(offset);
                high = open.max(close).add(offset);
                low = open.min(close).subtract(offset);
            }

            Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            Num volume = volumes[i] == null ? null : decimal(volumes[i]);

            bars.add(new BaseBar(
                    PERIOD,
                    begin,
                    end,
                    decimal(open),
                    decimal(high),
                    decimal(low),
                    decimal(close),
                    volume,
                    decimal(amounts[i]),
                    trades[i]));
        }

        return new SourceCase(boxSize, reversalAmount, useDefaultConstructor, bars);
    }

    private static SourceCase generateFollowUp(SourceCase source) {
        List<Bar> scaledBars = new ArrayList<>(source.bars.size());

        for (Bar bar : source.bars) {
            scaledBars.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    scale(bar.getOpenPrice()),
                    scale(bar.getHighPrice()),
                    scale(bar.getLowPrice()),
                    scale(bar.getClosePrice()),
                    bar.getVolume(),
                    scale(bar.getAmount()),
                    bar.getTrades()));
        }

        return new SourceCase(
                source.boxSize.multiply(BigDecimal.valueOf(2L)).toPlainString(),
                source.reversalAmount,
                source.useDefaultConstructor,
                scaledBars);
    }

    private static void assertMetamorphicRelationFor(SourceCase source) {
        SourceCase followUp = generateFollowUp(source);

        List<Bar> sourceOutput = source.receiver().aggregate(source.bars);
        List<Bar> followUpOutput = followUp.receiver().aggregate(followUp.bars);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput, List<Bar> followUpOutput) {
        assertNotNull(sourceOutput);
        assertNotNull(followUpOutput);
        assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar sourceBrick = sourceOutput.get(i);
            Bar followUpBrick = followUpOutput.get(i);

            assertEquals(sourceBrick.getBeginTime(), followUpBrick.getBeginTime());
            assertEquals(sourceBrick.getEndTime(), followUpBrick.getEndTime());
            assertEquals(sourceBrick.getTimePeriod(), followUpBrick.getTimePeriod());
            assertNumEquals(sourceBrick.getVolume(), followUpBrick.getVolume());
            assertEquals(sourceBrick.getTrades(), followUpBrick.getTrades());

            assertNumEquals(scale(sourceBrick.getOpenPrice()), followUpBrick.getOpenPrice());
            assertNumEquals(scale(sourceBrick.getHighPrice()), followUpBrick.getHighPrice());
            assertNumEquals(scale(sourceBrick.getLowPrice()), followUpBrick.getLowPrice());
            assertNumEquals(scale(sourceBrick.getClosePrice()), followUpBrick.getClosePrice());
            assertNumEquals(scale(sourceBrick.getAmount()), followUpBrick.getAmount());

            assertEquals(direction(sourceBrick), direction(followUpBrick));
        }
    }

    private static int direction(Bar bar) {
        if (bar.getClosePrice().isGreaterThan(bar.getOpenPrice())) {
            return 1;
        }
        if (bar.getClosePrice().isLessThan(bar.getOpenPrice())) {
            return -1;
        }
        return 0;
    }

    private static Num scale(Num value) {
        return value == null ? null : value.multipliedBy(TWO);
    }

    private static void assertNumEquals(Num expected, Num actual) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
        } else {
            assertEquals(0, expected.compareTo(actual));
        }
    }

    private static DecimalNum decimal(String value) {
        return DecimalNum.valueOf(new BigDecimal(value));
    }

    private static DecimalNum decimal(BigDecimal value) {
        return DecimalNum.valueOf(value);
    }

    @Test
    void EMPTY_LIST_EARLY_RETURN_variation1() {
        SourceCase source = source("10", 2, true);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SINGLE_ANCHOR_BAR_variation1() {
        SourceCase source = source("2.5", 1, false, "100.25");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MULTI_BAR_FLAT_CLOSES_variation1() {
        SourceCase source = source("0.5", 2, false, "10", "10", "10", "10");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INITIAL_UP_JUST_BELOW_BOX_variation1() {
        SourceCase source = source("1000000", 4, false,
                "1000000000", "1000500000", "1000999999");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INITIAL_UP_EXACT_BOX_variation1() {
        SourceCase source = source("10", 2, true, "100", "105", "109", "110", "110", "110");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INITIAL_UP_BETWEEN_ONE_AND_TWO_BOXES_variation1() {
        SourceCase source = source("2.5", 1, false, "20", "23.75");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INITIAL_DOWN_JUST_ABOVE_BOX_variation1() {
        SourceCase source = source("0.1", 2, false, "10", "9.91");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INITIAL_DOWN_EXACT_BOX_variation1() {
        SourceCase source = source("1000000", 4, false, "1000000000", "999000000");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INITIAL_DOWN_BETWEEN_ONE_AND_TWO_BOXES_variation1() {
        SourceCase source = source("10", 2, true, "100", "85", "85");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INITIAL_MULTI_BRICK_UP_variation1() {
        SourceCase source = source("1.25", 1, false,
                "10", "10.5", "13.75", "13.75", "13.75", "13.75");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void INITIAL_MULTI_BRICK_DOWN_variation1() {
        SourceCase source = source("0.2", 2, false, "5", "4.4");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ESTABLISHED_UP_EXACT_CONTINUATION_variation1() {
        SourceCase source = source("1000000", 4, false,
                "1000000000", "1001000000", "1002000000");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ESTABLISHED_DOWN_EXACT_CONTINUATION_variation1() {
        SourceCase source = source("10", 2, true, "100", "90", "80");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ESTABLISHED_UP_MULTI_CONTINUATION_variation1() {
        SourceCase source = source("2.5", 1, false, "100", "102.5", "110");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ESTABLISHED_DOWN_MULTI_CONTINUATION_variation1() {
        SourceCase source = source("0.1", 2, false,
                "10", "9.9", "9.4", "9.4", "9.4", "9.4");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void UP_PULLBACK_BELOW_REVERSAL_variation1() {
        SourceCase source = source("10", 3, false, "100", "110", "81");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void UP_REVERSAL_EXACT_DISTANCE_variation1() {
        SourceCase source = source("10", 2, true, "100", "110", "90");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void UP_REVERSAL_BEYOND_DISTANCE_variation1() {
        SourceCase source = source("2.5", 1, false, "100", "102.5", "95");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DOWN_BOUNCE_BELOW_REVERSAL_variation1() {
        SourceCase source = source("0.1", 2, false, "10", "9.9", "10.09");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DOWN_REVERSAL_EXACT_DISTANCE_variation1() {
        SourceCase source = source("1000000", 4, false,
                "1000000000", "999000000", "1003000000", "1003000000",
                "1003000000", "1003000000");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DOWN_REVERSAL_BEYOND_DISTANCE_variation1() {
        SourceCase source = source("10", 2, true, "100", "90", "120");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ONE_BOX_UP_TO_DOWN_REVERSAL_variation1() {
        SourceCase source = source("1.5", 1, false, "10", "11.5", "10");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ONE_BOX_DOWN_TO_UP_REVERSAL_variation1() {
        SourceCase source = source("0.25", 1, false, "10", "9.75", "10");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LARGE_REVERSAL_BLOCKS_TWO_BOX_PULLBACK_variation1() {
        SourceCase source = source("1000000", 4, false,
                "1000000000", "1001000000", "999000000", "999000000");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LARGE_REVERSAL_EXACT_THRESHOLD_variation1() {
        SourceCase source = source("10", 3, false,
                "100", "110", "80", "80", "80", "80");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DEFAULT_CONSTRUCTOR_TWO_BRICK_REVERSAL_variation1() {
        SourceCase source = source("2.5", 2, true, "50", "52.5", "47.5");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void QUIET_BARS_ACCUMULATE_METADATA_variation1() {
        SourceCase source = sourceWithMetadata("0.5", 2, false, Shape.FLAT,
                new String[] { "10", "10.1", "10.25", "10.5" },
                new String[] { "1", "2", "3", "4" },
                new String[] { "0.25", "0.5", "0.75", "1.25" },
                new long[] { 1, 2, 3, 4 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SAME_SOURCE_EXTRA_BRICKS_ZERO_METADATA_variation1() {
        SourceCase source = sourceWithMetadata("1000000", 4, false, Shape.WIDE,
                new String[] { "1000000000", "1003000000" },
                new String[] { "7", "11" },
                new String[] { "100.5", "200.25" },
                new long[] { 3, 5 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void METADATA_RESET_BETWEEN_SOURCE_BARS_variation1() {
        SourceCase source = sourceWithMetadata("10", 2, true, Shape.FLAT,
                new String[] { "100", "110", "120" },
                new String[] { "1", "2", "7" },
                new String[] { "10", "20", "70" },
                new long[] { 1, 2, 7 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NULL_VOLUME_IGNORED_variation1() {
        SourceCase source = sourceWithMetadata("1.25", 1, false, Shape.VARIED,
                new String[] { "10", "10.5", "11.25", "11.25", "11.25", "11.25" },
                new String[] { null, "2", null, "4", "5", "6" },
                new String[] { "0.5", "0.75", "1.25", "1", "1", "1" },
                new long[] { 1, 2, 3, 4, 5, 6 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ZERO_METADATA_EMISSION_variation1() {
        SourceCase source = sourceWithMetadata("0.1", 2, false, Shape.FLAT,
                new String[] { "10", "10.1" },
                new String[] { "0", "0" },
                new String[] { "0", "0" },
                new long[] { 0, 0 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_FRACTIONAL_AMOUNT_SCALING_variation1() {
        SourceCase source = sourceWithMetadata("1000000", 4, false, Shape.WIDE,
                new String[] { "1000000000", "1000500000", "1001000000" },
                new String[] { "1.5", "2.5", "3.5" },
                new String[] { "1000000000.125", "2000000000.25", "3000000000.375" },
                new long[] { 1, 2, 3 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SOURCE_END_AFTER_NEXT_BRICK_TIME_variation1() {
        SourceCase source = source("10", 2, true, "100", "103", "107", "110");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SOURCE_END_EQUALS_NEXT_BRICK_TIME_variation1() {
        SourceCase source = source("2.5", 1, false, "100", "102.5", "105");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEXT_BRICK_TIME_AFTER_SOURCE_END_variation1() {
        SourceCase source = source("0.1", 2, false,
                "10", "10.5", "10.5", "10.5", "10.5", "10.5");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void FINAL_PENDING_METADATA_DROPPED_variation1() {
        SourceCase source = sourceWithMetadata("1000000", 4, false, Shape.WIDE,
                new String[] { "1000000000", "1001000000", "1001200000", "1001300000" },
                new String[] { "1", "2", "100", "200" },
                new String[] { "10", "20", "1000", "2000" },
                new long[] { 1, 2, 100, 200 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void ALTERNATING_EXACT_REVERSALS_variation1() {
        SourceCase source = source("10", 2, true, "100", "110", "90", "110");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void DOWN_THEN_UP_CONTINUATION_AFTER_REVERSAL_variation1() {
        SourceCase source = source("1.5", 1, false, "10", "8.5", "10", "11.5");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void CLOSE_EQUALS_LAST_BRICK_CLOSE_variation1() {
        SourceCase source = sourceWithMetadata("0.1", 2, false, Shape.VARIED,
                new String[] { "10", "9.9", "9.9", "9.9" },
                new String[] { null, "2", "3", "4" },
                new String[] { "1", "2", "3", "4" },
                new long[] { 1, 2, 3, 4 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void SOURCE_OHLC_DOES_NOT_CONTROL_DIRECTION_variation1() {
        SourceCase source = sourceWithMetadata("1000000", 4, false, Shape.WIDE,
                new String[] {
                        "1000000000", "1000100000", "999900000",
                        "1000200000", "999800000", "1000000000"
                },
                new String[] { "1", "1", "1", "1", "1", "1" },
                new String[] { "1", "1", "1", "1", "1", "1" },
                new long[] { 1, 1, 1, 1, 1, 1 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void VARIED_SOURCE_OHLC_WITH_CLOSE_TRIGGER_variation1() {
        SourceCase source = sourceWithMetadata("10", 2, true, Shape.VARIED,
                new String[] { "100", "104", "110" },
                new String[] { "1", "2", "3" },
                new String[] { "10", "20", "30" },
                new long[] { 1, 2, 3 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void FRACTIONAL_BOX_EXACT_BOUNDARIES_variation1() {
        SourceCase source = source("0.25", 1, false, "10", "10.25", "10.5");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LARGE_FINITE_SAFE_VALUES_variation1() {
        SourceCase source = sourceWithMetadata("1000000000000", 2, false, Shape.FLAT,
                new String[] { "1000000000000000", "1001000000000000" },
                new String[] { "1000000000", "2000000000" },
                new String[] { "4000000000000.5", "5000000000000.25" },
                new long[] { 100, 200 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void FIVE_OR_MORE_BRICKS_ONE_BAR_variation1() {
        SourceCase source = sourceWithMetadata("1000000", 4, false, Shape.WIDE,
                new String[] { "1000000000", "1006000000", "1006000000" },
                new String[] { "2", "3", "4" },
                new String[] { "20", "30", "40" },
                new long[] { 2, 3, 4 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void QUIET_BARS_BETWEEN_CONTINUATIONS_variation1() {
        SourceCase source = sourceWithMetadata("10", 2, true, Shape.FLAT,
                new String[] { "100", "110", "112", "115", "119", "120" },
                new String[] { "1", "2", "3", "4", "5", "6" },
                new String[] { "1", "2", "3", "4", "5", "6" },
                new long[] { 1, 2, 3, 4, 5, 6 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void REVERSAL_TRIGGER_AFTER_QUIET_PULLBACKS_variation1() {
        SourceCase source = sourceWithMetadata("2.5", 2, false, Shape.VARIED,
                new String[] { "100", "102.5", "101", "99", "97.5" },
                new String[] { "1", "2", "3", "4", "5" },
                new String[] { "0.25", "0.5", "0.75", "1.25", "1.5" },
                new long[] { 1, 2, 3, 4, 5 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MIXED_SINGLE_AND_MULTI_SOURCE_EMISSIONS_variation1() {
        SourceCase source = sourceWithMetadata("0.1", 2, false, Shape.VARIED,
                new String[] { "10", "10.1", "10.15", "10.4" },
                new String[] { "1", "2", "3", "4" },
                new String[] { "1.25", "2.5", "3.75", "4.125" },
                new long[] { 1, 2, 3, 4 });
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NON_DEFAULT_REVERSAL_GREATER_THAN_THREE_variation1() {
        SourceCase source = source("1000000", 5, false,
                "1000000000", "1001000000", "997000000", "996000000");
        assertMetamorphicRelationFor(source);
    }

    @Test
    void BOTH_DIRECTIONS_WITH_FRACTIONAL_METADATA_variation1() {
        SourceCase source = sourceWithMetadata("10", 2, true, Shape.FLAT,
                new String[] { "100", "110", "105", "90" },
                new String[] { "1.5", "2.25", "3.5", "4.75" },
                new String[] { "0.125", "0.375", "0.625", "0.875" },
                new long[] { 1, 2, 3, 4 });
        assertMetamorphicRelationFor(source);
    }
}
