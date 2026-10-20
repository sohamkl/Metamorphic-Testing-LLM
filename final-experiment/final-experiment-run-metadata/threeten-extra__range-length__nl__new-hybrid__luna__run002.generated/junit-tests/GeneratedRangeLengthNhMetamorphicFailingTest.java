import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private static final int END_SHIFT_DAYS = 23;

    private static LocalDateRange generateFollowUp(LocalDateRange source) {
        return LocalDateRange.of(
                source.getStart(),
                source.getEnd().plusDays(END_SHIFT_DAYS));
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput + END_SHIFT_DAYS, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(LocalDateRange source) {
        Assertions.assertNotEquals(LocalDate.MAX, source.getEnd());
        LocalDateRange followUp = generateFollowUp(source);
        Assertions.assertEquals(source.getStart(), followUp.getStart());
        Assertions.assertEquals(
                source.getEnd().plusDays(END_SHIFT_DAYS),
                followUp.getEnd());
        assertMetamorphicRelation(source.lengthInDays(), followUp.lengthInDays());
    }

    @Test
    public void MINIMUM_START_FINITE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.MIN,
                LocalDate.MIN.plusDays(1));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void MINIMUM_ADMISSIBLE_FINITE_END_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN,
                LocalDate.MIN.plusDays(2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void UNBOUNDED_START_FINITE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.of(2000, 1, 1));
        assertMetamorphicRelationFor(source);
    }
}
