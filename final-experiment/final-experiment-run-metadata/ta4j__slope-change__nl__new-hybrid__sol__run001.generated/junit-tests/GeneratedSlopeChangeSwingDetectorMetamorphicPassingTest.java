import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.analysis.elliott.swing.SwingPivot;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.NaN;
import org.ta4j.core.num.Num;

public class GeneratedSlopeChangeSwingDetectorMetamorphicPassingTest {

    private static final double TRANSLATION = 100.0;
    private static final double TOLERANCE = 1.0e-8;
    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static void check(Input source) {
        SwingDetectorResult sourceOutput = invoke(source);
        Input followUp = generateFollowUp(source);
        SwingDetectorResult followUpOutput = invoke(followUp);

        assertSame(source.receiver, followUp.receiver);
        assertSame(source.receiver.getConfig(), followUp.receiver.getConfig());
        assertEquals(source.index, followUp.index);
        assertSame(source.degree, followUp.degree);
        assertEquals(source.series.getBeginIndex(), followUp.series.getBeginIndex());
        assertEquals(source.series.getEndIndex(), followUp.series.getEndIndex());

        assertFollowUpSeriesRelation(source.series, followUp.series);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static SwingDetectorResult invoke(Input input) {
        return input.receiver.detect(input.series, input.index, input.degree);
    }

    private static Input generateFollowUp(Input source) {
        return input(source.receiver, translateSeries(source.series), source.index, source.degree);
    }

    private static void assertMetamorphicRelation(
            SwingDetectorResult sourceOutput,
            SwingDetectorResult followUpOutput) {

        assertEquals(sourceOutput.pivots().size(), followUpOutput.pivots().size());
        for (int i = 0; i < sourceOutput.pivots().size(); i++) {
            SwingPivot sourcePivot = sourceOutput.pivots().get(i);
            SwingPivot followUpPivot = followUpOutput.pivots().get(i);

            assertEquals(sourcePivot.index(), followUpPivot.index());
            assertEquals(sourcePivot.type(), followUpPivot.type());
            assertTranslated(sourcePivot.price(), followUpPivot.price());
        }

        assertEquals(sourceOutput.swings().size(), followUpOutput.swings().size());
        for (int i = 0; i < sourceOutput.swings().size(); i++) {
            var sourceSwing = sourceOutput.swings().get(i);
            var followUpSwing = followUpOutput.swings().get(i);

            assertEquals(sourceSwing.fromIndex(), followUpSwing.fromIndex());
            assertEquals(sourceSwing.toIndex(), followUpSwing.toIndex());
            assertSame(sourceSwing.degree(), followUpSwing.degree());
            assertTranslated(sourceSwing.fromPrice(), followUpSwing.fromPrice());
            assertTranslated(sourceSwing.toPrice(), followUpSwing.toPrice());
        }
    }

    private static void assertFollowUpSeriesRelation(BarSeries source, BarSeries followUp) {
        if (source.isEmpty()) {
            assertTrue(followUp.isEmpty());
            return;
        }

        assertEquals(source.numFactory().getClass(), followUp.numFactory().getClass());

        for (int i = source.getBeginIndex(); i <= source.getEndIndex(); i++) {
            Bar sourceBar = source.getBar(i);
            Bar followUpBar = followUp.getBar(i);

            assertEquals(sourceBar.getTimePeriod(), followUpBar.getTimePeriod());
            assertEquals(sourceBar.getBeginTime(), followUpBar.getBeginTime());
            assertEquals(sourceBar.getEndTime(), followUpBar.getEndTime());
            assertEquals(sourceBar.getTrades(), followUpBar.getTrades());
            assertNumEqual(sourceBar.getVolume(), followUpBar.getVolume());

            assertTranslated(sourceBar.getOpenPrice(), followUpBar.getOpenPrice());
            assertTranslated(sourceBar.getHighPrice(), followUpBar.getHighPrice());
            assertTranslated(sourceBar.getLowPrice(), followUpBar.getLowPrice());
            assertTranslated(sourceBar.getClosePrice(), followUpBar.getClosePrice());

            Num translation = source.numFactory().numOf(TRANSLATION);
            Num expectedAmount = sourceBar.getAmount()
                    .plus(translation.multipliedBy(sourceBar.getVolume()));
            assertNumEqual(expectedAmount, followUpBar.getAmount());
        }
    }

    private static BarSeries translateSeries(BarSeries source) {
        BaseBarSeries translated = new BaseBarSeries(source.getName() + "-translated", new ArrayList<>());
        if (source.isEmpty()) {
            return translated;
        }

        int beginIndex = source.getBeginIndex();
        int accessibleBarCount = source.getEndIndex() - beginIndex + 1;
        Num translation = source.numFactory().numOf(TRANSLATION);

        if (beginIndex > 0) {
            translated.setMaximumBarCount(accessibleBarCount);
            Bar first = source.getBar(beginIndex);

            for (int i = 0; i < beginIndex; i++) {
                Duration offset = first.getTimePeriod().multipliedBy(beginIndex - i);
                Instant dummyBegin = first.getBeginTime().minus(offset);
                Instant dummyEnd = first.getEndTime().minus(offset);
                translated.addBar(translateBar(first, dummyBegin, dummyEnd, translation));
            }
        }

        for (int i = beginIndex; i <= source.getEndIndex(); i++) {
            Bar sourceBar = source.getBar(i);
            translated.addBar(translateBar(
                    sourceBar,
                    sourceBar.getBeginTime(),
                    sourceBar.getEndTime(),
                    translation));
        }
        return translated;
    }

    private static Bar translateBar(
            Bar sourceBar,
            Instant beginTime,
            Instant endTime,
            Num translation) {

        Num open = sourceBar.getOpenPrice().plus(translation);
        Num high = sourceBar.getHighPrice().plus(translation);
        Num low = sourceBar.getLowPrice().plus(translation);
        Num close = sourceBar.getClosePrice().plus(translation);
        Num volume = sourceBar.getVolume();
        Num amount = sourceBar.getAmount().plus(translation.multipliedBy(volume));

        return new BaseBar(
                sourceBar.getTimePeriod(),
                beginTime,
                endTime,
                open,
                high,
                low,
                close,
                volume,
                amount,
                sourceBar.getTrades());
    }

    private static Input input(
            SlopeChangeSwingDetector receiver,
            BarSeries series,
            int index,
            ElliottDegree degree) {
        return new Input(receiver, series, index, degree);
    }

    private static SlopeChangeSwingDetector detector(
            int window,
            int confirmationBars,
            int atrPeriod,
            double minimumSlopeChange,
            double minimumAtrReversal) {
        return new SlopeChangeSwingDetector(new SlopeChangeConfig(
                window,
                confirmationBars,
                atrPeriod,
                minimumSlopeChange,
                minimumAtrReversal));
    }

    private static BarSeries emptySeries() {
        return new BaseBarSeries("empty", new ArrayList<>());
    }

    private static BarSeries series(double... closes) {
        return seriesWithExtremes(closes, null, null);
    }

    private static BarSeries seriesWithExtremes(
            double[] closes,
            double[] highs,
            double[] lows) {

        BaseBarSeries result = new BaseBarSeries("source", new ArrayList<>());
        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            double high = highs == null
                    ? finiteOrNaNOffset(close, 0.5)
                    : highs[i];
            double low = lows == null
                    ? finiteOrNaNOffset(close, -0.5)
                    : lows[i];

            addBar(result, i, close, high, low, 10.0 + i, 2L + i);
        }
        return result;
    }

    private static BarSeries truncatedSeries(int maximumBarCount, double... closes) {
        BaseBarSeries result = new BaseBarSeries("truncated", new ArrayList<>());
        result.setMaximumBarCount(maximumBarCount);

        for (int i = 0; i < closes.length; i++) {
            double close = closes[i];
            addBar(
                    result,
                    i,
                    close,
                    finiteOrNaNOffset(close, 0.5),
                    finiteOrNaNOffset(close, -0.5),
                    10.0 + i,
                    1L + i);
        }
        return result;
    }

    private static double finiteOrNaNOffset(double value, double offset) {
        return Double.isNaN(value) ? Double.NaN : value + offset;
    }

    private static BarSeries oscillatingSeries() {
        return series(10.0, 13.0, 12.0, 9.0, 11.0, 14.0, 12.0, 8.0, 10.0, 15.0, 13.0);
    }

    private static BarSeries narrowOscillatingSeries() {
        double[] closes = { 10.0, 13.0, 12.0, 9.0, 11.0, 14.0, 12.0, 8.0, 10.0, 15.0, 13.0 };
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];

        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 0.25;
            lows[i] = closes[i] - 0.25;
        }
        return seriesWithExtremes(closes, highs, lows);
    }

    private static BarSeries wideOscillatingSeries() {
        double[] closes = { 10.0, 13.0, 12.0, 9.0, 11.0, 14.0, 12.0, 8.0, 10.0, 15.0, 13.0 };
        double[] highs = new double[closes.length];
        double[] lows = new double[closes.length];

        for (int i = 0; i < closes.length; i++) {
            highs[i] = closes[i] + 8.0;
            lows[i] = closes[i] - 8.0;
        }
        return seriesWithExtremes(closes, highs, lows);
    }

    private static void addBar(
            BaseBarSeries series,
            int ordinal,
            double close,
            double high,
            double low,
            double volume,
            long trades) {

        Num closeNum = numOfAllowingNaN(series, close);
        Num highNum = numOfAllowingNaN(series, high);
        Num lowNum = numOfAllowingNaN(series, low);
        Num volumeNum = series.numFactory().numOf(volume);
        Num amountNum = closeNum.multipliedBy(volumeNum);

        Instant beginTime = BASE_TIME.plus(BAR_DURATION.multipliedBy(ordinal));
        Instant endTime = beginTime.plus(BAR_DURATION);

        series.addBar(new BaseBar(
                BAR_DURATION,
                beginTime,
                endTime,
                closeNum,
                highNum,
                lowNum,
                closeNum,
                volumeNum,
                amountNum,
                trades));
    }

    private static Num numOfAllowingNaN(BaseBarSeries series, double value) {
        return Double.isNaN(value) ? NaN.NaN : series.numFactory().numOf(value);
    }

    private static void assertTranslated(Num source, Num followUp) {
        double sourceValue = source.doubleValue();
        double followUpValue = followUp.doubleValue();

        if (Double.isNaN(sourceValue)) {
            assertTrue(Double.isNaN(followUpValue));
        } else if (Double.isInfinite(sourceValue)) {
            assertEquals(sourceValue, followUpValue);
        } else {
            assertEquals(sourceValue + TRANSLATION, followUpValue, TOLERANCE);
        }
    }

    private static void assertNumEqual(Num expected, Num actual) {
        double expectedValue = expected.doubleValue();
        double actualValue = actual.doubleValue();

        if (Double.isNaN(expectedValue)) {
            assertTrue(Double.isNaN(actualValue));
        } else if (Double.isInfinite(expectedValue)) {
            assertEquals(expectedValue, actualValue);
        } else {
            assertEquals(expectedValue, actualValue, TOLERANCE);
        }
    }

    private static final class Input {

        private final SlopeChangeSwingDetector receiver;
        private final BarSeries series;
        private final int index;
        private final ElliottDegree degree;

        private Input(
                SlopeChangeSwingDetector receiver,
                BarSeries series,
                int index,
                ElliottDegree degree) {
            this.receiver = receiver;
            this.series = series;
            this.index = index;
            this.degree = degree;
        }
    }

    @Test
    public void EMPTY_SERIES_EARLY_RETURN_integerConstructor() {
        check(input(new SlopeChangeSwingDetector(3), emptySeries(), -1, ElliottDegree.MINOR));
    }

    @Test
    public void NONEMPTY_INSUFFICIENT_HISTORY_suppliedConfigTwoBars() {
        check(input(detector(3, 1, 3, 0.0, 0.0), series(10.0, 11.0), 0, ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void NONEMPTY_INSUFFICIENT_HISTORY_integerConstructorThreeBars() {
        check(input(new SlopeChangeSwingDetector(4), series(15.0, 14.0, 13.0), 2, ElliottDegree.MINOR));
    }

    @Test
    public void EVALUATION_INDEX_BELOW_BEGIN_truncatedSeries() {
        BarSeries source = truncatedSeries(4, 10.0, 11.0, 12.0, 11.0, 10.0, 9.0);
        check(input(detector(2, 1, 2, 0.0, 0.0), source, source.getBeginIndex() - 1,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void ONE_BAR_SHORT_OF_FIRST_CANDIDATE_nonzeroBeginIndex() {
        BarSeries source = truncatedSeries(4, 20.0, 19.0, 10.0, 12.0, 11.0, 10.0);
        check(input(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), ElliottDegree.MINOR));
    }

    @Test
    public void EXACT_FIRST_EVALUABLE_CANDIDATE_confirmedHigh() {
        check(input(detector(2, 1, 3, 0.0, 0.5), series(10.0, 12.0, 11.0, 9.0), 3,
                ElliottDegree.MINOR));
    }

    @Test
    public void EXACT_FIRST_EVALUABLE_CANDIDATE_confirmedLow() {
        check(input(new SlopeChangeSwingDetector(2), series(12.0, 10.0, 11.0, 13.0, 14.0), 4,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void EVALUATION_INDEX_ABOVE_END_clampsToEnd() {
        BarSeries source = series(10.0, 13.0, 12.0, 10.0, 9.0);
        check(input(detector(2, 1, 2, 0.0, 0.0), source, source.getEndIndex() + 20, ElliottDegree.MINOR));
    }

    @Test
    public void EVALUATION_INDEX_EXACTLY_END_confirmedTurn() {
        BarSeries source = series(14.0, 11.0, 12.0, 14.0, 15.0);
        check(input(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void NONZERO_SERIES_BEGIN_INDEX_absolutePivotIndices() {
        BarSeries source = truncatedSeries(6, 20.0, 23.0, 22.0, 19.0, 18.0, 20.0, 22.0);
        check(input(detector(2, 1, 3, 0.0, 0.0), source, source.getEndIndex(), ElliottDegree.MINOR));
    }

    @Test
    public void NONFINITE_BEFORE_SLOPE_nanCloseInBeforeWindow() {
        check(input(new SlopeChangeSwingDetector(2), series(10.0, Double.NaN, 9.0, 8.0), 3,
                ElliottDegree.PRIMARY));
    }

    @Test
    public void NONFINITE_AFTER_SLOPE_nanCloseInAfterWindow() {
        check(input(detector(2, 1, 2, 0.0, 0.0), series(10.0, 12.0, Double.NaN, 9.0), 3,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void SLOPE_CHANGE_BELOW_MINIMUM_smallOppositeSlopes() {
        check(input(detector(2, 1, 2, 2.0, 0.0), series(10.0, 10.5, 10.25, 10.0), 3,
                ElliottDegree.MINOR));
    }

    @Test
    public void SLOPE_CHANGE_EXACTLY_MINIMUM_positiveToNegative() {
        check(input(detector(2, 1, 2, 2.0, 0.0), series(10.0, 11.0, 10.0, 9.0), 3,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void SLOPE_CHANGE_EXACTLY_MINIMUM_negativeToPositive() {
        check(input(new SlopeChangeSwingDetector(
                new SlopeChangeConfig(2, 1, 2, 4.0, 0.0)),
                series(12.0, 10.0, 12.0, 14.0), 3, ElliottDegree.MINOR));
    }

    @Test
    public void POSITIVE_TO_POSITIVE_NO_REVERSAL_differentPositiveSlopes() {
        check(input(detector(2, 1, 2, 1.0, 0.0), series(10.0, 13.0, 14.0, 15.0), 3,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void NEGATIVE_TO_NEGATIVE_NO_REVERSAL_differentNegativeSlopes() {
        check(input(new SlopeChangeSwingDetector(2), series(15.0, 12.0, 11.0, 10.0, 9.0), 4,
                ElliottDegree.MINOR));
    }

    @Test
    public void ZERO_SLOPE_NO_REVERSAL_zeroBeforeSlope() {
        check(input(detector(2, 1, 2, 0.0, 0.0), series(10.0, 10.0, 9.0, 8.0), 3,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void ZERO_SLOPE_NO_REVERSAL_zeroAfterSlope() {
        check(input(new SlopeChangeSwingDetector(2), series(10.0, 12.0, 11.0, 11.0, 11.0), 4,
                ElliottDegree.MINOR));
    }

    @Test
    public void FIRST_CONFIRMATION_SLOPE_FAILS_highBecomesFlat() {
        check(input(detector(2, 1, 2, 0.0, 0.0), series(10.0, 12.0, 11.0, 11.0), 3,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void FIRST_CONFIRMATION_SLOPE_FAILS_lowBecomesFlat() {
        check(input(new SlopeChangeSwingDetector(
                new SlopeChangeConfig(2, 1, 2, 0.0, 0.0)),
                series(12.0, 10.0, 11.0, 11.0), 3, ElliottDegree.MINOR));
    }

    @Test
    public void LATER_CONFIRMATION_SLOPE_FAILS_highThenWrongSign() {
        check(input(detector(2, 2, 2, 0.0, 0.0), series(10.0, 12.0, 11.0, 10.0, 12.0), 4,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void LATER_CONFIRMATION_SLOPE_FAILS_lowThenWrongSign() {
        check(input(new SlopeChangeSwingDetector(
                new SlopeChangeConfig(2, 2, 2, 0.0, 0.0)),
                series(12.0, 10.0, 11.0, 12.0, 10.0), 4, ElliottDegree.MINOR));
    }

    @Test
    public void DEGREE_VARIATION_WITH_CONFIRMED_SWING_primaryDegree() {
        check(input(detector(2, 1, 2, 0.0, 0.0), oscillatingSeries(), 10, ElliottDegree.PRIMARY));
    }

    @Test
    public void HIGH_EXTREME_LOCATION_uniqueInteriorHigh() {
        double[] closes = { 10.0, 12.0, 14.0, 13.0, 11.0, 9.0 };
        double[] highs = { 10.5, 12.5, 14.5, 18.0, 11.5, 9.5 };
        check(input(new SlopeChangeSwingDetector(3), seriesWithExtremes(closes, highs, null), 5,
                ElliottDegree.MINOR));
    }

    @Test
    public void HIGH_EXTREME_LOCATION_uniqueTransitionEndHigh() {
        double[] closes = { 10.0, 12.0, 14.0, 13.0, 11.0, 9.0 };
        double[] highs = { 10.5, 12.5, 14.5, 15.0, 19.0, 9.5 };
        check(input(detector(3, 1, 2, 0.0, 0.0), seriesWithExtremes(closes, highs, null), 5,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void LOW_EXTREME_LOCATION_uniqueInteriorLow() {
        double[] closes = { 15.0, 13.0, 11.0, 12.0, 14.0, 16.0 };
        double[] lows = { 14.5, 12.5, 10.5, 7.0, 13.5, 15.5 };
        check(input(new SlopeChangeSwingDetector(3), seriesWithExtremes(closes, null, lows), 5,
                ElliottDegree.MINOR));
    }

    @Test
    public void LOW_EXTREME_LOCATION_uniqueTransitionEndLow() {
        double[] closes = { 15.0, 13.0, 11.0, 12.0, 14.0, 16.0 };
        double[] lows = { 14.5, 12.5, 10.5, 9.0, 6.0, 15.5 };
        check(input(detector(3, 1, 2, 0.0, 0.0), seriesWithExtremes(closes, null, lows), 5,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void TIED_EXTREME_KEEPS_EARLIEST_highTie() {
        double[] closes = { 10.0, 13.0, 12.0, 9.0 };
        double[] highs = { 10.5, 16.0, 16.0, 9.5 };
        check(input(new SlopeChangeSwingDetector(2), seriesWithExtremes(closes, highs, null), 3,
                ElliottDegree.MINOR));
    }

    @Test
    public void TIED_EXTREME_KEEPS_EARLIEST_lowTie() {
        double[] closes = { 14.0, 11.0, 12.0, 15.0 };
        double[] lows = { 13.5, 7.0, 7.0, 14.5 };
        check(input(detector(2, 1, 2, 0.0, 0.0), seriesWithExtremes(closes, null, lows), 3,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void NONFINITE_EXTREME_REJECTS_CANDIDATE_nanHigh() {
        double[] closes = { 10.0, 13.0, 12.0, 9.0 };
        double[] highs = { 10.5, 13.5, Double.NaN, 9.5 };
        check(input(new SlopeChangeSwingDetector(2), seriesWithExtremes(closes, highs, null), 3,
                ElliottDegree.MINOR));
    }

    @Test
    public void NONFINITE_EXTREME_REJECTS_CANDIDATE_nanLow() {
        double[] closes = { 14.0, 11.0, 12.0, 15.0 };
        double[] lows = { 13.5, 10.5, Double.NaN, 14.5 };
        check(input(detector(2, 1, 2, 0.0, 0.0), seriesWithExtremes(closes, null, lows), 3,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_FILTER_largeMultiplierHigh() {
        check(input(new SlopeChangeSwingDetector(
                new SlopeChangeConfig(2, 1, 1, 0.0, 1000.0)),
                series(10.0, 13.0, 12.0, 9.0), 3, ElliottDegree.MINOR));
    }

    @Test
    public void FIRST_PIVOT_BYPASSES_ATR_FILTER_largeMultiplierLow() {
        check(input(detector(2, 1, 1, 0.0, 500.0), series(14.0, 11.0, 12.0, 15.0), 3,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void ZERO_ATR_MULTIPLIER_BYPASS_twoAlternatingPivots() {
        check(input(new SlopeChangeSwingDetector(
                new SlopeChangeConfig(2, 1, 3, 0.0, 0.0)),
                oscillatingSeries(), 7, ElliottDegree.MINOR));
    }

    @Test
    public void ZERO_ATR_MULTIPLIER_BYPASS_longerAlternatingSequence() {
        check(input(detector(2, 1, 1, 0.0, 0.0), oscillatingSeries(), 10, ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void NONFINITE_ATR_REJECTS_SUBSEQUENT_PIVOT_nanHighAtLowPivot() {
        double[] closes = { 10.0, 13.0, 12.0, 9.0, 11.0, 14.0 };
        double[] highs = { 10.5, 13.5, 12.5, Double.NaN, 11.5, 14.5 };
        double[] lows = { 9.5, 12.5, 11.5, 8.5, 10.5, 13.5 };
        check(input(new SlopeChangeSwingDetector(
                new SlopeChangeConfig(2, 1, 1, 0.0, 1.0)),
                seriesWithExtremes(closes, highs, lows), 5, ElliottDegree.MINOR));
    }

    @Test
    public void ATR_REVERSAL_BELOW_THRESHOLD_highMultiplier() {
        check(input(detector(2, 1, 1, 0.0, 20.0), oscillatingSeries(), 7,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void ATR_REVERSAL_BELOW_THRESHOLD_wideBars() {
        check(input(new SlopeChangeSwingDetector(
                new SlopeChangeConfig(2, 1, 1, 0.0, 5.0)),
                wideOscillatingSeries(), 10, ElliottDegree.MINOR));
    }

    @Test
    public void ATR_REVERSAL_EXACTLY_THRESHOLD_unitTrueRange() {
        check(input(detector(2, 1, 1, 0.0, 3.0), narrowOscillatingSeries(), 7,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void ATR_REVERSAL_EXACTLY_THRESHOLD_alternateDegree() {
        check(input(new SlopeChangeSwingDetector(
                new SlopeChangeConfig(2, 1, 1, 0.0, 2.0)),
                narrowOscillatingSeries(), 10, ElliottDegree.MINUTE));
    }

    @Test
    public void ATR_REVERSAL_ABOVE_THRESHOLD_smallMultiplier() {
        check(input(detector(2, 1, 2, 0.0, 0.1), oscillatingSeries(), 10,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void MULTIPLE_ALTERNATING_PIVOTS_twoPivotPrefix() {
        check(input(new SlopeChangeSwingDetector(2), oscillatingSeries(), 5, ElliottDegree.MINOR));
    }

    @Test
    public void MULTIPLE_ALTERNATING_PIVOTS_threePivotPrefix() {
        check(input(detector(2, 1, 2, 0.0, 0.0), oscillatingSeries(), 7,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void MULTIPLE_ALTERNATING_PIVOTS_fullSequence() {
        BarSeries source = truncatedSeries(11,
                20.0, 21.0, 10.0, 13.0, 12.0, 9.0, 11.0, 14.0, 12.0, 8.0, 10.0, 15.0, 13.0);
        check(input(new SlopeChangeSwingDetector(2), source, source.getEndIndex(), ElliottDegree.MINOR));
    }

    @Test
    public void SAME_TYPE_HIGH_REPLACED_laterHigherHigh() {
        double[] closes = { 10.0, 13.0, 12.0, 10.0, 11.0, 14.0, 13.0, 11.0, 10.0 };
        double[] highs = { 10.5, 13.5, 13.0, 10.5, 11.5, 18.0, 17.0, 11.5, 10.5 };
        check(input(detector(2, 1, 2, 0.0, 0.0), seriesWithExtremes(closes, highs, null), 8,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void SAME_TYPE_LOW_REPLACED_laterLowerLow() {
        double[] closes = { 15.0, 12.0, 13.0, 15.0, 14.0, 11.0, 12.0, 14.0, 15.0 };
        double[] lows = { 14.5, 11.5, 12.5, 14.5, 13.5, 7.0, 8.0, 13.5, 14.5 };
        check(input(new SlopeChangeSwingDetector(2), seriesWithExtremes(closes, null, lows), 8,
                ElliottDegree.MINOR));
    }

    @Test
    public void SAME_TYPE_LESS_EXTREME_RETAINED_laterLowerHigh() {
        double[] closes = { 10.0, 14.0, 12.0, 10.0, 11.0, 13.0, 12.0, 10.0, 9.0 };
        double[] highs = { 10.5, 18.0, 17.0, 10.5, 11.5, 15.0, 14.0, 10.5, 9.5 };
        check(input(detector(2, 1, 2, 0.0, 0.0), seriesWithExtremes(closes, highs, null), 8,
                ElliottDegree.INTERMEDIATE));
    }

    @Test
    public void SAME_TYPE_LESS_EXTREME_RETAINED_laterHigherLow() {
        double[] closes = { 15.0, 11.0, 13.0, 15.0, 14.0, 12.0, 13.0, 15.0, 16.0 };
        double[] lows = { 14.5, 7.0, 8.0, 14.5, 13.5, 10.0, 11.0, 14.5, 15.5 };
        check(input(new SlopeChangeSwingDetector(2), seriesWithExtremes(closes, null, lows), 8,
                ElliottDegree.MINOR));
    }

    @Test
    public void SAME_TYPE_EQUAL_PRICE_RETAINED_equalHighPrice() {
        double[] closes = { 10.0, 14.0, 12.0, 10.0, 11.0, 14.0, 12.0, 10.0, 9.0 };
        double[] highs = { 10.5, 18.0, 18.0, 10.5, 11.5, 18.0, 18.0, 10.5, 9.5 };
        check(input(detector(2, 1, 2, 0.0, 0.0), seriesWithExtremes(closes, highs, null), 8,
                ElliottDegree.INTERMEDIATE));
    }
}
