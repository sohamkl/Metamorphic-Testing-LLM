import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private static org.threeten.extra.LocalDateRange generateFollowUp(
            org.threeten.extra.LocalDateRange source) {
        return org.threeten.extra.LocalDateRange.of(
                source.getStart(),
                source.getEnd().plusDays(23));
    }

}
