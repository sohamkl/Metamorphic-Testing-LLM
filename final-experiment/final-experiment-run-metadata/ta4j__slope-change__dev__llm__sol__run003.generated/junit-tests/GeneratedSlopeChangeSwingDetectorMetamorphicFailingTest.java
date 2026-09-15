import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import org.ta4j.core.num.Num;

import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static final Instant BASE_TIME = Instant.parse("2024-01-01T00:00:00Z");
    private static final Duration BAR_DURATION = Duration.ofMinutes(1);

    private static SlopeChangeSwingDetector configuredDetector(int window) {
        return new SlopeChangeSwingDetector(SlopeChangeConfig.defaults(window));
    }

    private static int firstCandidateBoundary(SlopeChangeSwingDetector detector) {
        return 2 * detector.getConfig().window()
                + detector.getConfig().confirmationBars() - 2;
    }

    private static double smallChange(SlopeChangeSwingDetector detector) {
        double threshold = detector.getConfig().minSlopeChange();
        return threshold > 0.0 ? threshold / 8.0 : 1.0e-8;
    }

    private static ElliottDegree degree(int offset) {
        ElliottDegree[] values = ElliottDegree.values();
        return values[Math.floorMod(offset, values.length)];
    }

    private static double[] alternating() {
        return new double[] {
                0.0, 6.0, 12.0, 6.0, 0.0, -6.0, 0.0, 6.0,
                12.0, 6.0, 0.0, -6.0, 0.0, 6.0, 12.0, 6.0,
                0.0, -6.0, 0.0, 6.0
        };
    }

    private static double[] constantExtras(int length, double value) {
        double[] extras = new double[length];
        for (int i = 0; i < extras.length; i++) {
            extras[i] = value;
        }
        return extras;
    }

    private static BarSeries series(double baseline, double volume, double[] closeOffsets) {
        return series(baseline, volume, closeOffsets, null, null);
    }

    private static BarSeries series(
            double baseline,
            double volume,
            double[] closeOffsets,
            double[] highExtras,
            double[] lowExtras) {

        BarSeries result = new BaseBarSeriesBuilder()
                .withName("slope-change-source")
                .build();

        for (int i = 0; i < closeOffsets.length; i++) {
            double close = baseline + closeOffsets[i];
            double highExtra = highExtras == null ? 0.0 : highExtras[i];
            double lowExtra = lowExtras == null ? 0.0 : lowExtras[i];
            double high = close + 1.0 + highExtra;
            double low = close - 1.0 - lowExtra;

            Num openNum = result.numFactory().numOf(close);
            Num highNum = result.numFactory().numOf(high);
            Num lowNum = result.numFactory().numOf(low);
            Num closeNum = result.numFactory().numOf(close);
            Num volumeNum = result.numFactory().numOf(volume);
            Num amountNum = result.numFactory().numOf(close * volume);

            Instant begin = BASE_TIME.plus(BAR_DURATION.multipliedBy(i));
            Instant end = begin.plus(BAR_DURATION);
            result.addBar(new BaseBar(
                    BAR_DURATION,
                    begin,
                    end,
                    openNum,
                    highNum,
                    lowNum,
                    closeNum,
                    volumeNum,
                    amountNum,
                    1L));
        }
        return result;
    }

    private static void exercise(
            SlopeChangeSwingDetector detector,
            BarSeries sourceSeries,
            int index,
            ElliottDegree degree) {

        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);

        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(
                detector, sourceSeries, index, degree);

        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = (Integer) followUp[2];
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];

        SwingDetectorResult followUpOutput = followUpDetector.detect(
                followUpSeries, followUpIndex, followUpDegree);

        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
