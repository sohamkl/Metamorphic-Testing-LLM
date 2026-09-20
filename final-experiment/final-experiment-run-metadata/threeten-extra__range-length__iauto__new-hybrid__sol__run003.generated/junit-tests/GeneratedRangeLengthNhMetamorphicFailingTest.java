import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        if (followUpOutput - sourceOutput != 23) {
            throw new AssertionError(
                    "Expected the follow-up length to be exactly 23 days greater, but source was "
                            + sourceOutput + " and follow-up was " + followUpOutput);
        }
    }

}
