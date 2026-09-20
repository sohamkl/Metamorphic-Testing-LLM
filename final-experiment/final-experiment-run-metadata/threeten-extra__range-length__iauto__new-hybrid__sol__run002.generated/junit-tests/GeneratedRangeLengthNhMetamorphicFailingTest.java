import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        if ((long) followUpOutput != (long) sourceOutput + 23L) {
            throw new AssertionError(
                    "Expected the follow-up length to be exactly 23 days greater than the source length, "
                            + "but source was " + sourceOutput + " and follow-up was " + followUpOutput);
        }
    }
}
