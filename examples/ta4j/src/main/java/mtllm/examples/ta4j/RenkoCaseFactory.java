package mtllm.examples.ta4j;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.num.DoubleNumFactory;
import org.ta4j.core.num.NumFactory;

import mtllm.examples.ta4j.RenkoBarAggregatorSut.RenkoCase;

/**
 * Randoop-facing construction helpers for valid, chronological Renko inputs.
 *
 * <p>The fixed-arity methods let Randoop combine primitive LLM seeds without having to discover
 * Ta4j's complete {@code Bar} object graph itself.</p>
 */
public final class RenkoCaseFactory {
    private static final Instant BASE_TIME = Instant.parse("2023-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofMinutes(1);
    private static final NumFactory NUMS = DoubleNumFactory.getInstance();

    private RenkoCaseFactory() {
    }

    public static RenkoCase twoBars(
            double firstClose, double secondClose, double boxSize, int reversalAmount) {
        return fromCloses(boxSize, reversalAmount, firstClose, secondClose);
    }

    public static RenkoCase threeBars(
            double firstClose, double secondClose, double thirdClose,
            double boxSize, int reversalAmount) {
        return fromCloses(boxSize, reversalAmount, firstClose, secondClose, thirdClose);
    }

    public static RenkoCase fourBars(
            double firstClose, double secondClose, double thirdClose, double fourthClose,
            double boxSize, int reversalAmount) {
        return fromCloses(boxSize, reversalAmount, firstClose, secondClose, thirdClose, fourthClose);
    }

    private static RenkoCase fromCloses(double boxSize, int reversalAmount, double... closes) {
        if (closes.length < 2) {
            throw new IllegalArgumentException("At least two close prices are required");
        }

        List<Bar> bars = new ArrayList<>(closes.length);
        for (int index = 0; index < closes.length; index++) {
            double close = closes[index];
            if (!Double.isFinite(close) || close <= 0.0) {
                throw new IllegalArgumentException("Close prices must be finite and positive");
            }

            Instant beginTime = BASE_TIME.plus(PERIOD.multipliedBy(index));
            double volume = index + 1.0;
            bars.add(new BaseBar(
                    PERIOD,
                    beginTime,
                    beginTime.plus(PERIOD),
                    NUMS.numOf(close),
                    NUMS.numOf(close),
                    NUMS.numOf(close),
                    NUMS.numOf(close),
                    NUMS.numOf(volume),
                    NUMS.numOf(close * volume),
                    index + 1L));
        }
        return new RenkoCase(bars, boxSize, reversalAmount);
    }
}
