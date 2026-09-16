import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Half;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.Quarter;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private static final int END_SHIFT_DAYS = 23;

    private static MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input(
            LocalDateRange receiver) {
        return new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(receiver);
    }

    private static MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input generateFollowUp(
            MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input source) {
        LocalDateRange sourceRange = source.receiver();
        LocalDate shiftedEnd = sourceRange.getEnd().plusDays(END_SHIFT_DAYS);
        LocalDateRange followUpRange = sourceRange.withEnd(shiftedEnd);
        return input(followUpRange);
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input source) {
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input followUp =
                generateFollowUp(source);

        int sourceOutput =
                MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(source);
        int followUpOutput =
                MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput + END_SHIFT_DAYS, followUpOutput);
    }

}
