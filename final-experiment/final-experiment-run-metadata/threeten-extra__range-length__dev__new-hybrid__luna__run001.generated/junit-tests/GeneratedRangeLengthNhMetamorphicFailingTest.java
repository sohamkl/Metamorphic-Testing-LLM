import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private static void exercise(org.threeten.extra.LocalDateRange source) {
        int sourceOutput = source.lengthInDays();
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

}
