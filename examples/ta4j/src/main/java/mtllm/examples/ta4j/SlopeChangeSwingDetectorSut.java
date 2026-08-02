package mtllm.examples.ta4j;

import java.util.Objects;

import org.ta4j.core.BarSeries;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeConfig;
import org.ta4j.core.analysis.elliott.swing.SlopeChangeSwingDetector;
import org.ta4j.core.analysis.elliott.swing.SwingDetectorResult;
import org.ta4j.core.indicators.elliott.ElliottDegree;

/** Static adapter that exposes all slope-detector inputs as one Randoop-friendly value. */
public final class SlopeChangeSwingDetectorSut {
    private SlopeChangeSwingDetectorSut() {
    }

    public record SlopeChangeCase(
            BarSeries series,
            int index,
            SlopeChangeConfig config,
            ElliottDegree degree) {

        public SlopeChangeCase {
            Objects.requireNonNull(series, "series");
            Objects.requireNonNull(config, "config");
            Objects.requireNonNull(degree, "degree");
        }
    }

    public static SwingDetectorResult detect(SlopeChangeCase source) {
        Objects.requireNonNull(source, "source");
        return new SlopeChangeSwingDetector(source.config())
                .detect(source.series(), source.index(), source.degree());
    }
}
