import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        assertEquals(23, followUpOutput - sourceOutput);
    }
}
