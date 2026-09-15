import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final Instant BASE_TIME = Instant.parse("2020-01-01T00:00:00Z");

    private static List<Bar> bars(double... closes) {
        return barsWith(0, 0, closes);
    }

    private static List<Bar> barsWith(int metricMode, int nonCloseMode, double... closes) {
        Bar[] result = new Bar[closes.length];
        for (int i = 0; i < closes.length; i++) {
            Num close = DecimalNum.valueOf(closes[i]);
            Num open;
            Num high;
            Num low;

            if (nonCloseMode == 2) {
                open = null;
                high = null;
                low = null;
            } else if (nonCloseMode == 1) {
                open = i % 2 == 0 ? null : DecimalNum.valueOf(closes[i] + 7.25);
                high = DecimalNum.valueOf(closes[i] + 20.5);
                low = i % 3 == 0 ? null : DecimalNum.valueOf(closes[i] - 15.75);
            } else {
                open = DecimalNum.valueOf(closes[i] - 0.25);
                high = DecimalNum.valueOf(closes[i] + 0.5);
                low = DecimalNum.valueOf(closes[i] - 0.5);
            }

            Num volume;
            Num amount;
            long trades;
            if (metricMode == 1) {
                volume = DecimalNum.valueOf(0);
                amount = DecimalNum.valueOf(0);
                trades = 0L;
            } else if (metricMode == 2) {
                volume = null;
                amount = null;
                trades = i + 1L;
            } else if (metricMode == 3) {
                volume = i % 2 == 0 ? null : DecimalNum.valueOf(i + 1);
                amount = i % 2 == 0 ? DecimalNum.valueOf((i + 1) * 10) : null;
                trades = i + 1L;
            } else {
                volume = DecimalNum.valueOf(i + 1);
                amount = DecimalNum.valueOf((i + 1) * 10);
                trades = i + 1L;
            }

            Instant begin = BASE_TIME.plus(PERIOD.multipliedBy(i));
            Instant end = begin.plus(PERIOD);
            result[i] = new BaseBar(PERIOD, begin, end, open, high, low, close, volume, amount, trades);
        }
        return List.of(result);
    }

    @SuppressWarnings("unchecked")
    private static List<Bar> followUpBars(Object[] followUp) {
        return (List<Bar>) followUp[1];
    }

    @Test
    void EMPTY_SOURCE_LIST_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.5);
        List<Bar> bars = List.of();
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_BAR_ANCHOR_ONLY_variation1_zeroAnchor() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 2);
        List<Bar> bars = barsWith(1, 1, 0);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_BAR_ANCHOR_ONLY_variation2_positiveAnchor() {
        RenkoBarAggregator source = new RenkoBarAggregator(3);
        List<Bar> bars = barsWith(2, 2, 12.3);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_BAR_FLAT_CLOSES_variation1_negativeLargeBox() {
        RenkoBarAggregator source = new RenkoBarAggregator(1_000_000, 8);
        List<Bar> bars = barsWith(0, 0, -2_000_000, -2_000_000, -2_000_000);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_BAR_FLAT_CLOSES_variation2_fractionalAnchor() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.25);
        List<Bar> bars = barsWith(0, 1, 0.5, 0.5, 0.5, 0.5);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOTH_SIDES_WITHIN_FIRST_BOX_variation1_positivePrices() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 2);
        List<Bar> bars = barsWith(1, 2, 10, 10.4, 9.3, 10.8, 9.6, 10.1);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOTH_SIDES_WITHIN_FIRST_BOX_variation2_negativePrices() {
        RenkoBarAggregator source = new RenkoBarAggregator(3);
        List<Bar> bars = barsWith(2, 0, -20, -18, -22, -19, -21, -17.5, -22.5, -20.5, -19.5, -21.5, -18.5, -20);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_INITIAL_UP_THRESHOLD_variation1_largeBox() {
        RenkoBarAggregator source = new RenkoBarAggregator(1_000_000, 8);
        List<Bar> bars = barsWith(0, 1, -500_000, 500_000);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_INITIAL_UP_THRESHOLD_variation2_fractionalBox() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.1);
        List<Bar> bars = barsWith(0, 2, 2.3, 2.4);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_UP_BETWEEN_ONE_AND_TWO_BOXES_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 2);
        List<Bar> bars = barsWith(1, 0, -10, -8.5);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_INITIAL_DOWN_THRESHOLD_variation1_defaultConstructor() {
        RenkoBarAggregator source = new RenkoBarAggregator(2);
        List<Bar> bars = barsWith(2, 1, 1, 1.5, -1);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_INITIAL_DOWN_THRESHOLD_variation2_largePositiveRegion() {
        RenkoBarAggregator source = new RenkoBarAggregator(1_000_000, 8);
        List<Bar> bars = barsWith(0, 2, 5_000_000, 5_250_000, 4_750_000, 4_000_000);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_DOWN_BETWEEN_ONE_AND_TWO_BOXES_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.5);
        List<Bar> bars = barsWith(0, 0, -4, -4.1, -4.2, -4.75, -4.7);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MULTI_BRICK_UP_BURST_variation1_twoBricks() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 2);
        List<Bar> bars = barsWith(1, 1, 0, 0.2, 2);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MULTI_BRICK_UP_BURST_variation2_fourBricks() {
        RenkoBarAggregator source = new RenkoBarAggregator(3);
        List<Bar> bars = barsWith(2, 2, 5, 17);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MULTI_BRICK_DOWN_BURST_variation1_fourBricks() {
        RenkoBarAggregator source = new RenkoBarAggregator(1_000_000, 8);
        List<Bar> bars = barsWith(0, 0, -2_000_000, -6_000_000);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INITIAL_MULTI_BRICK_DOWN_BURST_variation2_twoFractionalBricks() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.25);
        List<Bar> bars = barsWith(0, 1, 0.5, 0);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ESTABLISHED_UP_CONTINUATION_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 2);
        List<Bar> bars = barsWith(1, 2, 10, 11, 12.5);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ESTABLISHED_DOWN_CONTINUATION_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2);
        List<Bar> bars = barsWith(2, 0, -10, -12, -14.5, -14.5);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_PULLBACK_SHORT_OF_REVERSAL_variation1_largeReversal() {
        RenkoBarAggregator source = new RenkoBarAggregator(1_000_000, 8);
        List<Bar> bars = barsWith(0, 1, 0, 1_000_000, -6_500_000);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UP_PULLBACK_SHORT_OF_REVERSAL_variation2_defaultTwoBoxes() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.1);
        List<Bar> bars = barsWith(0, 2, 2, 2.1, 1.95);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_BOUNCE_SHORT_OF_REVERSAL_variation1_twoBoxes() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 2);
        List<Bar> bars = barsWith(1, 0, -5, -6, -4.5);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DOWN_BOUNCE_SHORT_OF_REVERSAL_variation2_threeBoxes() {
        RenkoBarAggregator source = new RenkoBarAggregator(3, 3);
        List<Bar> bars = barsWith(2, 1, 1, -2, 4.5);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_UP_TO_DOWN_REVERSAL_variation1_eightBoxes() {
        RenkoBarAggregator source = new RenkoBarAggregator(1_000_000, 8);
        List<Bar> bars = barsWith(0, 2, 5_000_000, 6_000_000, -2_000_000);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_UP_TO_DOWN_REVERSAL_variation2_defaultTwoBoxes() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.5);
        List<Bar> bars = barsWith(0, 0, -3, -2.5, -3.5);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_DOWN_TO_UP_REVERSAL_variation1_twoBoxes() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 2);
        List<Bar> bars = barsWith(1, 1, 0, -1, 1, 1);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_DOWN_TO_UP_REVERSAL_variation2_threeBoxes() {
        RenkoBarAggregator source = new RenkoBarAggregator(2, 3);
        List<Bar> bars = barsWith(2, 2, 10, 8, 14, 14, 14, 14);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_THRESHOLD_OVERSHOOT_variation1_upToDown() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 8);
        List<Bar> bars = barsWith(0, 0, -2, -1, 0, 1, -9);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void REVERSAL_THRESHOLD_OVERSHOOT_variation2_downToUp() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.25, 2);
        List<Bar> bars = barsWith(0, 1, 0.5, 0.25, 1.25);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BOX_REVERSAL_AMOUNT_variation1_upToDown() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 1);
        List<Bar> bars = barsWith(1, 2, 5, 6, 5);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_BOX_REVERSAL_AMOUNT_variation2_downToUp() {
        RenkoBarAggregator source = new RenkoBarAggregator(3, 1);
        List<Bar> bars = barsWith(2, 0, -10, -13, -10);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_REVERSAL_SUPPRESSES_OPPOSITE_MOVE_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(1_000_000, 8);
        List<Bar> bars = barsWith(0, 1, 0, 1_000_000, -6_000_000);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALTERNATING_CONFIRMED_REVERSALS_variation1_defaultTwoBoxes() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.5);
        List<Bar> bars = barsWith(0, 2, 2, 2.5, 1.5, 2.5);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ALTERNATING_CONFIRMED_REVERSALS_variation2_explicitThreeBoxes() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 3);
        List<Bar> bars = barsWith(1, 0, -5, -4, -7, -4, -4);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PENDING_METRICS_ACROSS_QUIET_BARS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2);
        List<Bar> bars = barsWith(0, 1, 0, 0.5, -0.5, 2, 2, 2, 2, 2, 2, 2);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BURST_METRIC_ZERO_SENTINELS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(1_000_000, 8);
        List<Bar> bars = barsWith(0, 2, 2_000_000, 2_250_000, 6_000_000);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_INPUTS_variation1_allNull() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.5);
        List<Bar> bars = barsWith(2, 0, -2, -1.5);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NULL_VOLUME_AND_AMOUNT_INPUTS_variation2_mixedNull() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 2);
        List<Bar> bars = barsWith(3, 1, 0, 0.25, 1);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXPLICIT_ZERO_METRICS_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2);
        List<Bar> bars = barsWith(1, 2, 10, 8, 6);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DELAYED_FIRST_EMISSION_TIME_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(1_000_000, 8);
        List<Bar> bars = barsWith(0, 0, -3_000_000, -2_750_000, -3_250_000, -2_000_000);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTI_BRICK_FUTURE_TIME_SCHEDULING_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.25);
        List<Bar> bars = barsWith(0, 1, 0, 0.1, 1, 1, 1, 1);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SCHEDULED_END_CARRIES_TO_LATER_BAR_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 2);
        List<Bar> bars = barsWith(1, 2, 10, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void STRICTLY_NEGATIVE_PRICE_REGION_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2);
        List<Bar> bars = barsWith(2, 0, -100, -98, -96);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PRICE_PATH_CROSSES_ZERO_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(1, 8);
        List<Bar> bars = barsWith(0, 1, -3, 3);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_BOX_AND_NUMERIC_REPRESENTATION_variation1_binaryFraction() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.25);
        List<Bar> bars = barsWith(0, 2, 1, 1.25, 2);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_BOX_AND_NUMERIC_REPRESENTATION_variation2_decimalFraction() {
        RenkoBarAggregator source = new RenkoBarAggregator(0.1, 2);
        List<Bar> bars = barsWith(1, 0, -1, -0.9, -0.6);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DEFAULT_TWO_BRICK_CONSTRUCTOR_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(2);
        List<Bar> bars = barsWith(2, 1, 0, 4, 1, 0);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NON_CLOSE_FIELDS_IGNORED_FOR_DIRECTION_variation1() {
        RenkoBarAggregator source = new RenkoBarAggregator(1_000_000, 8);
        List<Bar> bars = barsWith(3, 1, 3_000_000, 3_250_000, 5_000_000, 4_500_000, 4_000_000, 4_000_000);
        List<Bar> sourceOutput = source.aggregate(bars);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(source, bars);
        RenkoBarAggregator followUpReceiver = (RenkoBarAggregator) followUp[0];
        List<Bar> followUpOutput = followUpReceiver.aggregate(followUpBars(followUp));
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
