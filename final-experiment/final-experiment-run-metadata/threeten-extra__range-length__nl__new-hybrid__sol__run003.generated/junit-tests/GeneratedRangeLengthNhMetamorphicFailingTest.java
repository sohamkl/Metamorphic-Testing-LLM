import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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

    private static final int SHIFT_DAYS = 23;

    private MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input(
            LocalDateRange receiver) {
        return new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(receiver);
    }

    private MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input generateFollowUp(
            MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input source) {
        LocalDateRange sourceRange = source.receiver();
        LocalDate shiftedEnd = sourceRange.getEnd().plusDays(SHIFT_DAYS);
        LocalDateRange followUpRange =
                LocalDateRange.of(sourceRange.getStart(), shiftedEnd);
        return input(followUpRange);
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input source) {
        int sourceOutput =
                MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(source);
        int followUpOutput =
                MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(
                        generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        assertEquals(sourceOutput + SHIFT_DAYS, followUpOutput);
    }

    private void assertSourceOutputAndMetamorphicRelation(
            LocalDateRange sourceRange, int expectedSourceOutput) {
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input source =
                input(sourceRange);
        assertEquals(
                expectedSourceOutput,
                MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(source));
        assertMetamorphicRelationFor(source);
    }

}
