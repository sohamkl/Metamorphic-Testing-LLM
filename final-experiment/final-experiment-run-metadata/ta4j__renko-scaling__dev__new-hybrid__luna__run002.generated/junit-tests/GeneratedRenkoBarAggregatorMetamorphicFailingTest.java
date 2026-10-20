import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.aggregator.RenkoBarAggregator;
import org.ta4j.core.num.DecimalNum;

import mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec;

public class GeneratedRenkoBarAggregatorMetamorphicFailingTest {

    private static final Instant BASE = Instant.parse("2020-01-01T00:00:00Z");
    private static final Duration PERIOD = Duration.ofSeconds(1);

    private static DecimalNum n(double value) {
        return DecimalNum.valueOf(value);
    }

    private static List<Bar> bars(double[] closes, int metadataMode) {
        List<Bar> result = new ArrayList<>();
        for (int i = 0; i < closes.length; i++) {
            Instant end = BASE.plusSeconds(i + 1L);
            DecimalNum volume = metadataMode == 1 || metadataMode == 3 ? null : n(i + 1);
            DecimalNum amount = metadataMode == 2 || metadataMode == 3 ? null : n((i + 1) * 10);
            long trades = metadataMode == 4 ? (i + 1L) * 3L : i + 1L;
            DecimalNum close = n(closes[i]);
            result.add(new BaseBar(
                    PERIOD,
                    end.minus(PERIOD),
                    end,
                    close,
                    close,
                    close,
                    close,
                    volume,
                    amount,
                    trades));
        }
        return result;
    }

    private static void exercise(RenkoBarAggregator aggregator, List<Bar> source) {
        List<Bar> sourceOutput = aggregator.aggregate(source);
        Object[] followUp = RenkoBarAggregatorMetamorphicSpec.generateFollowUp(aggregator, source);
        RenkoBarAggregator followUpAggregator = (RenkoBarAggregator) followUp[0];
        @SuppressWarnings("unchecked")
        List<Bar> followUpBars = (List<Bar>) followUp[1];
        List<Bar> followUpOutput = followUpAggregator.aggregate(followUpBars);
        RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
