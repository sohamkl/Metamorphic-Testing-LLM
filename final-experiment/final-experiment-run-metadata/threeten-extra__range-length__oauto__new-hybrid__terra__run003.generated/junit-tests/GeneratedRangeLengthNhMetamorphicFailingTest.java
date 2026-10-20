import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private LocalDateRange generateFollowUp(LocalDateRange source) {
        LocalDate extendedEnd = source.getEnd().plusDays(23);
        return LocalDateRange.of(source.getStart(), extendedEnd);
    }

}
