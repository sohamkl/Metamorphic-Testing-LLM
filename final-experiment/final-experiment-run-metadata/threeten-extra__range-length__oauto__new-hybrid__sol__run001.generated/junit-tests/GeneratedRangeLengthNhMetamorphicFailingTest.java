import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.threeten.extra.Half;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.Quarter;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private static LocalDateRange generateFollowUp(LocalDateRange source) {
        LocalDate followUpEnd = source.getEnd().plusDays(23);
        return LocalDateRange.of(source.getStart(), followUpEnd);
    }

}
