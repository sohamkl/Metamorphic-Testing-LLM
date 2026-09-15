import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofMillis(100);
    private static final Instant START = Instant.parse("2020-01-01T00:00:00Z");
    private static final BigDecimal SCALE = new BigDecimal("2");
    private static final Num NUM_SCALE = n("2");

    private static void assertRelation(Number boxSize, int reversalAmount,
            List<Bar> source) {
        assertRelation(new RenkoBarAggregator(boxSize, reversalAmount),
                boxSize, reversalAmount, source);
    }

    private static void assertRelation(RenkoBarAggregator sourceAggregator,
            Number boxSize, int reversalAmount, List<Bar> source) {
        List<Bar> sourceOutput = sourceAggregator.aggregate(source);
        Number scaledBoxSize = new BigDecimal(boxSize.toString()).multiply(SCALE);
        RenkoBarAggregator followUpAggregator =
                new RenkoBarAggregator(scaledBoxSize, reversalAmount);
        List<Bar> followUpOutput =
                followUpAggregator.aggregate(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(List<Bar> sourceOutput,
            List<Bar> followUpOutput) {
        Assertions.assertEquals(sourceOutput.size(), followUpOutput.size());

        for (int i = 0; i < sourceOutput.size(); i++) {
            Bar source = sourceOutput.get(i);
            Bar followUp = followUpOutput.get(i);

            Assertions.assertEquals(source.getBeginTime(), followUp.getBeginTime());
            Assertions.assertEquals(source.getEndTime(), followUp.getEndTime());
            Assertions.assertEquals(source.getTimePeriod(), followUp.getTimePeriod());
            Assertions.assertEquals(source.getVolume(), followUp.getVolume());
            Assertions.assertEquals(source.getTrades(), followUp.getTrades());

            Assertions.assertEquals(source.getOpenPrice().multipliedBy(NUM_SCALE),
                    followUp.getOpenPrice());
            Assertions.assertEquals(source.getHighPrice().multipliedBy(NUM_SCALE),
                    followUp.getHighPrice());
            Assertions.assertEquals(source.getLowPrice().multipliedBy(NUM_SCALE),
                    followUp.getLowPrice());
            Assertions.assertEquals(source.getClosePrice().multipliedBy(NUM_SCALE),
                    followUp.getClosePrice());
            Assertions.assertEquals(source.getAmount().multipliedBy(NUM_SCALE),
                    followUp.getAmount());
            Assertions.assertEquals(direction(source), direction(followUp));
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

    private static List<Bar> generateFollowUp(List<Bar> source) {
        List<Bar> result = new ArrayList<>();
        for (Bar bar : source) {
            result.add(new BaseBar(
                    bar.getTimePeriod(),
                    bar.getBeginTime(),
                    bar.getEndTime(),
                    bar.getOpenPrice().multipliedBy(NUM_SCALE),
                    bar.getHighPrice().multipliedBy(NUM_SCALE),
                    bar.getLowPrice().multipliedBy(NUM_SCALE),
                    bar.getClosePrice().multipliedBy(NUM_SCALE),
                    bar.getVolume(),
                    bar.getAmount() == null
                            ? null
                            : bar.getAmount().multipliedBy(NUM_SCALE),
                    bar.getTrades()));
        }
        return result;
    }

    private static List<Bar> bars(String[] closes) {
        return bars(closes, n("5"), n("10"), 2);
    }

    private static List<Bar> bars(String[] closes, Num volume, Num amount,
            long trades) {
        List<Bar> result = new ArrayList<>();
        Num previous = n(closes[0]);

        for (int i = 0; i < closes.length; i++) {
            Num close = n(closes[i]);
            Num open = i == 0 ? close : previous;
            Num high = open.max(close).plus(n("0.1"));
            Num low = open.min(close).minus(n("0.1"));

            result.add(new BaseBar(
                    PERIOD,
                    START.plus(PERIOD.multipliedBy(i)),
                    START.plus(PERIOD.multipliedBy(i + 1)),
                    open,
                    high,
                    low,
                    close,
                    volume,
                    amount,
                    trades));
            previous = close;
        }
        return result;
    }

    private static List<Bar> decimalBars(String... closes) {
        List<Bar> result = new ArrayList<>();
        Num previous = n(closes[0]);

        for (int i = 0; i < closes.length; i++) {
            Num close = n(closes[i]);
            Num open = i == 0 ? close : previous;

            result.add(new BaseBar(
                    PERIOD,
                    START.plus(PERIOD.multipliedBy(i)),
                    START.plus(PERIOD.multipliedBy(i + 1)),
                    open,
                    open.max(close),
                    open.min(close),
                    close,
                    n("3.25"),
                    n("7.50"),
                    4));
            previous = close;
        }
        return result;
    }

    private static Num n(String value) {
        return DecimalNum.valueOf(new BigDecimal(value));
    }

    @Test
    void EMPTY_SOURCE_SENTINEL_variation1() {
        assertRelation(2, 1, List.of());
    }

    @Test
    void SINGLE_BAR_NO_MOVEMENT_variation1() {
        assertRelation(2, 2, bars(new String[] {"100"}));
    }

    @Test
    void FLAT_CLOSE_SEQUENCE_variation1() {
        assertRelation(2, 3, bars(new String[] {"100", "100", "100"}, n("5"), null, 2));
    }

    @Test
    void INITIAL_UP_JUST_BELOW_BOX_variation1() {
        assertRelation(2, 1, bars(new String[] {"100", "101.9", "101.5"}, null, null, 2));
    }

    @Test
    void INITIAL_UP_EXACT_BOX_variation1() {
        assertRelation(2, 2, bars(new String[] {"100", "102"}, n("5"), n("10"), 0));
    }

    @Test
    void INITIAL_UP_MULTIPLE_BOXES_ONE_BAR_variation1() {
        assertRelation(2, 3, bars(new String[] {"100", "108"}));
    }

    @Test
    void INITIAL_DOWN_JUST_BELOW_BOX_variation1() {
        assertRelation(2, 1, bars(new String[] {"100", "98.1"}));
    }

    @Test
    void INITIAL_DOWN_EXACT_BOX_variation1() {
        assertRelation(2, 2, bars(new String[] {"100", "98"}, null, n("10"), 2));
    }

    @Test
    void INITIAL_DOWN_MULTIPLE_BOXES_ONE_BAR_variation1() {
        assertRelation(2, 3, bars(new String[] {"100", "92"}));
    }

    @Test
    void UP_CONTINUATION_EXACT_THRESHOLD_variation1() {
        assertRelation(2, 2, bars(new String[] {"100", "102", "104"}));
    }

    @Test
    void UP_CONTINUATION_MULTIPLE_THRESHOLDS_variation1() {
        assertRelation(2, 2, bars(new String[] {"100", "102", "108"}, n("7"), n("11"), 4));
    }

    @Test
    void UP_REVERSAL_ONE_BOX_BELOW_variation1() {
        assertRelation(2, 3, bars(new String[] {"100", "102", "100"}));
    }

    @Test
    void UP_REVERSAL_EXACT_CONFIGURED_DISTANCE_variation1() {
        assertRelation(2, 2, bars(new String[] {"100", "102", "98"}));
    }

    @Test
    void UP_REVERSAL_BEYOND_CONFIGURED_DISTANCE_variation1() {
        assertRelation(2, 2, bars(new String[] {"100", "104", "96"}));
    }

    @Test
    void REVERSAL_AMOUNT_ONE_UP_TO_DOWN_variation1() {
        assertRelation(2, 1, bars(new String[] {"100", "102", "100"}));
    }

    @Test
    void DOWN_CONTINUATION_EXACT_THRESHOLD_variation1() {
        assertRelation(2, 2, bars(new String[] {"100", "98", "96"}));
    }

    @Test
    void DOWN_CONTINUATION_MULTIPLE_THRESHOLDS_variation1() {
        assertRelation(2, 2, bars(new String[] {"100", "98", "92"}));
    }

    @Test
    void DOWN_REVERSAL_ONE_BOX_ABOVE_variation1() {
        assertRelation(2, 3, bars(new String[] {"100", "98", "100"}));
    }

    @Test
    void DOWN_REVERSAL_EXACT_CONFIGURED_DISTANCE_variation1() {
        assertRelation(2, 2, bars(new String[] {"100", "98", "102"}));
    }

    @Test
    void DOWN_REVERSAL_BEYOND_CONFIGURED_DISTANCE_variation1() {
        assertRelation(2, 2, bars(new String[] {"100", "96", "104"}));
    }

    @Test
    void MULTI_BAR_PENDING_METADATA_BEFORE_EMISSION_variation1() {
        assertRelation(2, 2,
                bars(new String[] {"100", "100.5", "101", "102"}, n("3"), n("7"), 2));
    }

    @Test
    void NULL_VOLUME_ON_EMITTING_SOURCE_variation1() {
        assertRelation(2, 2,
                bars(new String[] {"100", "102"}, null, n("12"), 3));
    }

    @Test
    void NULL_AMOUNT_ON_EMITTING_SOURCE_variation1() {
        assertRelation(2, 2,
                bars(new String[] {"100", "102"}, n("7"), null, 3));
    }

    @Test
    void BOTH_OPTIONAL_AMOUNTS_NULL_variation1() {
        assertRelation(2, 2,
                bars(new String[] {"100", "104"}, null, null, 0));
    }

    @Test
    void ZERO_TRADES_AND_NONZERO_TRADES_variation1() {
        assertRelation(2, 2,
                bars(new String[] {"100", "102", "106"}, n("5"), n("8"), 0));
    }

    @Test
    void ZERO_TRADES_AND_NONZERO_TRADES_variation2() {
        assertRelation(2, 2,
                bars(new String[] {"100", "102", "106"}, n("5"), n("8"), 9));
    }

    @Test
    void DELAYED_EMISSION_USES_SOURCE_END_TIME_variation1() {
        assertRelation(2, 2,
                bars(new String[] {"100", "100.5", "102"}));
    }

    @Test
    void MULTIPLE_BRICKS_USE_SCHEDULED_END_TIMES_variation1() {
        assertRelation(2, 2,
                bars(new String[] {"100", "108"}));
    }

    @Test
    void DEFAULT_AND_EXPLICIT_TWO_REVERSAL_CONFIGURATION_variation1() {
        assertRelation(new RenkoBarAggregator(2),
                2, 2, bars(new String[] {"100", "102", "98"}));
    }

    @Test
    void DEFAULT_AND_EXPLICIT_TWO_REVERSAL_CONFIGURATION_variation2() {
        assertRelation(new RenkoBarAggregator(2, 2),
                2, 2, bars(new String[] {"100", "102", "98"}));
    }

    @Test
    void NUM_IMPLEMENTATION_AND_DECIMAL_SCALE_variation1() {
        assertRelation(new RenkoBarAggregator(new BigDecimal("0.30"), 1),
                new BigDecimal("0.30"), 1,
                decimalBars("10.00", "10.30", "10.90"));
    }

    @Test
    void MIXED_DIRECTION_SEQUENCE_WITH_METADATA_variation1() {
        assertRelation(2, 2,
                bars(new String[] {"100", "104", "100", "96", "100"},
                        n("4"), n("9"), 3));
    }

    @Test
    void SOURCE_BAR_WITH_NO_EMISSION_BETWEEN_DIRECTIONS_variation1() {
        assertRelation(2, 2,
                bars(new String[] {"100", "102", "102.5", "104"},
                        n("6"), n("13"), 2));
    }

    @Test
    void SAME_SOURCE_REVERSAL_MULTIPLE_BRICKS_variation1() {
        assertRelation(2, 1,
                bars(new String[] {"100", "104", "96"}, null, null, 5));
    }
}
