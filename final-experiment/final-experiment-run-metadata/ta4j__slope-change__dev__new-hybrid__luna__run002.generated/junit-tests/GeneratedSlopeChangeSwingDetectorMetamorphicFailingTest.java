import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;
import mtllm.examples.ta4j.SlopeChangeSwingDetectorMetamorphicSpec;

public class GeneratedSlopeChangeSwingDetectorMetamorphicFailingTest {

    private static void run(SlopeChangeSwingDetector detector, BarSeries sourceSeries, int index, String degreeName) {
        ElliottDegree degree = ElliottDegree.valueOf(degreeName);
        SwingDetectorResult sourceOutput = detector.detect(sourceSeries, index, degree);
        Object[] followUp = SlopeChangeSwingDetectorMetamorphicSpec.generateFollowUp(detector, sourceSeries, index, degree);
        SlopeChangeSwingDetector followUpDetector = (SlopeChangeSwingDetector) followUp[0];
        BarSeries followUpSeries = (BarSeries) followUp[1];
        int followUpIndex = ((Integer) followUp[2]).intValue();
        ElliottDegree followUpDegree = (ElliottDegree) followUp[3];
        SwingDetectorResult followUpOutput = followUpDetector.detect(followUpSeries, followUpIndex, followUpDegree);
        SlopeChangeSwingDetectorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static BarSeries series(int length, int pattern) {
        BarSeries result = new BaseBarSeriesBuilder().withName("slope-change-" + pattern).build();
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        for (int i = 0; i < length; i++) {
            double close = closeValue(i, pattern);
            double open = close - 0.25;
            double high = Math.max(open, close) + highOffset(i, pattern);
            double low = Math.min(open, close) - lowOffset(i, pattern);
            Instant begin = start.plusSeconds(i * 60L);
            Instant end = begin.plusSeconds(60L);
            result.addBar(new BaseBar(Duration.ofMinutes(1), begin, end, result.numFactory().numOf(open), result.numFactory().numOf(high), result.numFactory().numOf(low), result.numFactory().numOf(close), result.numFactory().numOf(1.0), result.numFactory().numOf(1.0), 1L));
        }
        return result;
    }

    private static double closeValue(int i, int pattern) {
        switch(pattern) {
            case 0:
                return 100.0;
            case 1:
                return 100.0 + i;
            case 2:
                return 200.0 - i;
            case 3:
                return 100.0 + 0.01 * i;
            case 4:
                return i < 10 ? 100.0 + i : 110.0 - (i - 10);
            case 5:
                return i < 10 ? 120.0 - i : 110.0 - 0.75 * (i - 10);
            case 6:
                return i < 10 ? 80.0 + i : 90.0 + 0.75 * (i - 10);
            case 7:
                return i < 9 ? 100.0 + i : 109.0 + (i % 2 == 0 ? 1.0 : -1.0);
            case 8:
                return i < 9 ? 120.0 - i : 111.0 + (i % 3 == 0 ? -1.0 : 1.0);
            case 9:
                return 100.0 + (i < 12 ? i : 12.0 - (i - 12));
            case 10:
                return 180.0 - (i < 12 ? i : 12.0 - (i - 12));
            case 11:
                return i < 12 ? 100.0 + i : 112.0 - (i - 12);
            case 12:
                return 100.0 + 4.0 * Math.sin(i * Math.PI / 3.0);
            case 13:
                return 100.0 + 8.0 * Math.sin(i * Math.PI / 4.0);
            case 14:
                return 100.0 + 5.0 * Math.sin(i * Math.PI / 5.0) + (i > 18 ? i - 18 : 0.0);
            case 15:
                return 120.0 - 5.0 * Math.sin(i * Math.PI / 5.0) - (i > 18 ? i - 18 : 0.0);
            case 16:
                return 100.0 + 7.0 * Math.sin(i * Math.PI / 4.0);
            case 17:
                return 100.0 - 7.0 * Math.sin(i * Math.PI / 4.0);
            default:
                return 100.0 + 6.0 * Math.sin(i * Math.PI / 3.0);
        }
    }

    private static double highOffset(int i, int pattern) {
        if (pattern == 9 || pattern == 11 || pattern == 14) {
            return i % 5 == 2 ? 8.0 : 1.0;
        }
        if (pattern == 16) {
            return i % 4 == 1 ? 5.0 : 1.0;
        }
        return 1.0 + (i % 3) * 0.1;
    }

    private static double lowOffset(int i, int pattern) {
        if (pattern == 10 || pattern == 12 || pattern == 15 || pattern == 17) {
            return i % 5 == 2 ? 8.0 : 1.0;
        }
        return 1.0 + (i % 3) * 0.1;
    }

    @Test
    void DEGREE_PROPAGATION_variation2() {
        run(new SlopeChangeSwingDetector(new SlopeChangeConfig(3, 1, 2, 0.0, 0.0)), series(48, 13), 47, "PRIMARY");
    }
}
