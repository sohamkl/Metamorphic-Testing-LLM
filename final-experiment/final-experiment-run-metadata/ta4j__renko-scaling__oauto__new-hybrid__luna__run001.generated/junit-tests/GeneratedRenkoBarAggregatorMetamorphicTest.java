import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.DecimalNum;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicTest {

    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);
    private static final double SCALE_FACTOR = 2.0;

    @Test
    void EMPTY_SOURCE_LIST_1_variation1() {
        run(new RenkoBarAggregator(1.0), List.of());
    }

    @Test
    void SINGLE_BAR_NO_MOVEMENT_1_variation1() {
        run(new RenkoBarAggregator(2.0), barsWith(false, false, 10.0));
    }

    @Test
    void SINGLE_BAR_EXACT_UPWARD_BOX_1_variation1() {
        run(new RenkoBarAggregator(0.5, 2), barsWith(false, true, 10.0, 10.5));
    }

    @Test
    void SINGLE_BAR_EXACT_DOWNWARD_BOX_1_variation1() {
        run(new RenkoBarAggregator(1.0, 1), barsWith(true, true, 10.0, 9.0, 8.0));
    }

    @Test
    void NO_EMISSION_BELOW_UP_THRESHOLD_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 10.4, 10.8));
    }

    @Test
    void NO_EMISSION_BELOW_DOWN_THRESHOLD_1_variation1() {
        run(new RenkoBarAggregator(2.0, 2), barsWith(true, false, 10.0, 9.0, 8.5));
    }

    @Test
    void SINGLE_BAR_MULTI_UP_BRICKS_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, true, 10.0, 14.0));
    }

    @Test
    void SINGLE_BAR_MULTI_DOWN_BRICKS_1_variation1() {
        run(new RenkoBarAggregator(2.0, 3), barsWith(false, false, 12.0, 4.0));
    }

    @Test
    void UP_CONTINUATION_EXACT_THRESHOLD_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 11.0, 12.0));
    }

    @Test
    void UP_CONTINUATION_SUB_BOX_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 11.0, 11.5, 12.0));
    }

    @Test
    void DOWN_CONTINUATION_EXACT_THRESHOLD_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 9.0, 8.0));
    }

    @Test
    void DOWN_CONTINUATION_SUB_BOX_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 9.0, 8.5, 8.0));
    }

    @Test
    void UP_REVERSAL_ONE_BOX_THRESHOLD_1_variation1() {
        run(new RenkoBarAggregator(1.0, 1), barsWith(false, false, 10.0, 11.0, 10.0));
    }

    @Test
    void UP_REVERSAL_EXACT_MULTI_BOX_THRESHOLD_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 11.0, 9.0));
    }

    @Test
    void UP_REVERSAL_JUST_BELOW_THRESHOLD_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 11.0, 9.5, 8.0));
    }

    @Test
    void UP_REVERSAL_MULTI_BRICKS_1_variation1() {
        run(new RenkoBarAggregator(1.0, 1), barsWith(false, false, 10.0, 12.0, 8.0));
    }

    @Test
    void DOWN_REVERSAL_ONE_BOX_THRESHOLD_1_variation1() {
        run(new RenkoBarAggregator(1.0, 1), barsWith(false, false, 10.0, 9.0, 10.0));
    }

    @Test
    void DOWN_REVERSAL_EXACT_MULTI_BOX_THRESHOLD_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 9.0, 11.0));
    }

    @Test
    void DOWN_REVERSAL_JUST_BELOW_THRESHOLD_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 9.0, 10.5, 12.0));
    }

    @Test
    void DOWN_REVERSAL_MULTI_BRICKS_1_variation1() {
        run(new RenkoBarAggregator(1.0, 1), barsWith(false, false, 10.0, 8.0, 12.0));
    }

    @Test
    void PENDING_METRICS_THEN_EMISSION_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 10.25, 10.5, 11.0));
    }

    @Test
    void METRICS_AFTER_PRIOR_EMISSION_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 11.0, 11.25, 12.0));
    }

    @Test
    void NULL_VOLUME_SOURCE_FIELDS_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(true, false, 10.0, 10.5, 11.0));
    }

    @Test
    void NULL_AMOUNT_SOURCE_FIELDS_1_variation1() {
        run(new RenkoBarAggregator(1.0, 3), barsWith(false, true, 10.0, 10.5, 11.0, 13.0));
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(true, true, 10.0, 12.0));
    }

    @Test
    void ZERO_AND_NONZERO_TRADES_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 10.2, 11.0, 13.0));
    }

    @Test
    void BRICK_END_TIME_EQUAL_SOURCE_END_1_variation1() {
        run(new RenkoBarAggregator(0.5, 2), barsWith(false, false, 10.0, 10.5));
    }

    @Test
    void BRICK_END_TIME_SOURCE_AFTER_SCHEDULE_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 11.0, 13.0));
    }

    @Test
    void MANY_BRICKS_SINGLE_INTERVAL_1_variation1() {
        run(new RenkoBarAggregator(0.5, 2), barsWith(false, false, 10.0, 12.0));
    }

    @Test
    void DEFAULT_TWO_BOX_REVERSAL_1_variation1() {
        run(new RenkoBarAggregator(1.0), barsWith(false, false, 10.0, 11.0, 9.0));
    }

    @Test
    void HALF_UNIT_BOX_SCALING_1_variation1() {
        run(new RenkoBarAggregator(0.5, 2), barsWith(false, true, 5.0, 5.25, 5.5, 6.5));
    }

    @Test
    void LONGER_ALTERNATING_DIRECTION_SEQUENCE_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 12.0, 8.0, 6.0, 10.0));
    }

    @Test
    void NONZERO_SOURCE_METADATA_WITH_MULTI_OUTPUT_1_variation1() {
        run(new RenkoBarAggregator(0.5, 2), barsWith(false, false, 10.0, 12.0));
    }

    @Test
    void EXACT_NO_REVERSAL_WITH_EQUAL_CLOSE_1_variation1() {
        run(new RenkoBarAggregator(1.0, 2), barsWith(false, false, 10.0, 11.0, 11.0, 12.0));
    }

    @Test
    void POSITIVE_PRICE_AND_BOX_PAIR_1_variation1() {
        run(new RenkoBarAggregator(2.0, 2), barsWith(false, false, 20.0, 21.0, 22.0));
    }

    private static void run(RenkoBarAggregator sourceAggregator, List<Bar> sourceBars) {
        List<Bar> sourceOutput = sourceAggregator.aggregate(sourceBars);
        Object[] followUp = generateFollowUp(sourceAggregator, sourceBars);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];

        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];

        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(RenkoBarAggregator aggregator, List<Bar> bars) {
        Objects.requireNonNull(aggregator, "aggregator");
        Objects.requireNonNull(bars, "bars");

        try {
            Field boxSizeField = RenkoBarAggregator.class.getDeclaredField("boxSize");
            Field reversalAmountField = RenkoBarAggregator.class.getDeclaredField("reversalAmount");
            boxSizeField.setAccessible(true);
            reversalAmountField.setAccessible(true);

            Number boxSize = (Number) boxSizeField.get(aggregator);
            int reversalAmount = reversalAmountField.getInt(aggregator);

            RenkoBarAggregator scaledAggregator = new RenkoBarAggregator(
                    boxSize.doubleValue() * SCALE_FACTOR, reversalAmount);

            List<Bar> scaledBars = new ArrayList<>(bars.size());
            for (Bar source : bars) {
                scaledBars.add(scaleBar(Objects.requireNonNull(source, "source bars must not contain null")));
            }

            return new Object[] { scaledAggregator, scaledBars };
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException("Unable to read Renko configuration", failure);
        }
    }

    private static Bar scaleBar(Bar source) {
        return new BaseBar(
                source.getTimePeriod(),
                source.getBeginTime(),
                source.getEndTime(),
                scale(source.getOpenPrice()),
                scale(source.getHighPrice()),
                scale(source.getLowPrice()),
                scale(source.getClosePrice()),
                source.getVolume(),
                scale(source.getAmount()),
                source.getTrades());
    }

    private static Num scale(Num value) {
        if (value == null) {
            return null;
        }
        return value.multipliedBy(value.getNumFactory().numOf(SCALE_FACTOR));
    }

    private static List<Bar> barsWith(boolean nullVolume, boolean nullAmount, double... closes) {
        List<Bar> result = new ArrayList<>(closes.length);

        for (int index = 0; index < closes.length; index++) {
            Num close = DecimalNum.valueOf(closes[index]);
            Num volume = nullVolume ? null : DecimalNum.valueOf(index + 1.0);
            Num amount = nullAmount ? null : DecimalNum.valueOf((index + 1.0) * 10.0);

            Instant begin = BASE_TIME.plusSeconds(index);
            Instant end = begin.plus(PERIOD);

            result.add(new BaseBar(
                    PERIOD,
                    begin,
                    end,
                    close,
                    close,
                    close,
                    close,
                    volume,
                    amount,
                    index));
        }

        return result;
    }
}
