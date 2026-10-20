import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthMetamorphicFailingTest {

    private static void exercise(LocalDateRange source) {
        int sourceOutput = source.lengthInDays();
        try {
            Object[] followUpArguments =
                    RangeLengthMetamorphicSpec.generateFollowUp(source);
            LocalDateRange followUp = (LocalDateRange) followUpArguments[0];
            int followUpOutput = followUp.lengthInDays();
            RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
        } catch (DateTimeException ex) {
        }
    }

}
