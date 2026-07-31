package mtllm.examples.ta4j;

import java.util.List;
import java.util.Objects;

import org.ta4j.core.Bar;
import org.ta4j.core.aggregator.RenkoBarAggregator;

/**
 * Static adapter that makes all Renko aggregation input state explicit.
 */
public final class RenkoBarAggregatorSut {
    private RenkoBarAggregatorSut() {
    }

    public record RenkoCase(List<Bar> bars, double boxSize, int reversalAmount) {
        public RenkoCase {
            bars = List.copyOf(Objects.requireNonNull(bars, "bars"));
            if (!Double.isFinite(boxSize) || boxSize <= 0.0) {
                throw new IllegalArgumentException("boxSize must be finite and greater than zero");
            }
            if (reversalAmount <= 0) {
                throw new IllegalArgumentException("reversalAmount must be greater than zero");
            }
        }
    }

    public static List<Bar> aggregate(RenkoCase source) {
        Objects.requireNonNull(source, "source");
        return new RenkoBarAggregator(source.boxSize(), source.reversalAmount()).aggregate(source.bars());
    }
}
