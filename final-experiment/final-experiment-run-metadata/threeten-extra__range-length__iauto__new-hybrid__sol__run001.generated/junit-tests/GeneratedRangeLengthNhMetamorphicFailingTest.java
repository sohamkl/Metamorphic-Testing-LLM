import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        if (followUpOutput - sourceOutput != 23) {
            throw new AssertionError(
                    "The follow-up length must be exactly 23 days greater than the source length");
        }
    }

}
