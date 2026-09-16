import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.Half;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.Quarter;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private LocalDateRange generateFollowUp(LocalDateRange source) {
        return LocalDateRange.of(source.getStart(), source.getEnd().plusDays(23));
    }

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        assertEquals(sourceOutput + 23, followUpOutput);
    }

    private void assertMetamorphicRelationFor(LocalDateRange source) {
        LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
