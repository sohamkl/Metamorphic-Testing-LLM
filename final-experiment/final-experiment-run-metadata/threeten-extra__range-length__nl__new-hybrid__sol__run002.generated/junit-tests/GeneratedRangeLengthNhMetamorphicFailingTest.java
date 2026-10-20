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

    private LocalDateRange generateFollowUp(LocalDateRange source) {
        return source.withEnd(source.getEnd().plusDays(23));
    }

    private void assertMetamorphicRelationFor(LocalDateRange source, int expectedSourceLength) {
        Assertions.assertNotEquals(LocalDate.MIN, source.getStart());
        Assertions.assertNotEquals(LocalDate.MAX, source.getEnd());
        Assertions.assertFalse(source.getEnd().isAfter(LocalDate.MAX.minusDays(24)));

        long endpointDifference =
                source.getEnd().toEpochDay() - source.getStart().toEpochDay();
        Assertions.assertTrue(endpointDifference >= 0L);
        Assertions.assertTrue(endpointDifference <= 2_147_483_624L);

        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input sourceInput =
                new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(source);
        int sourceOutput =
                MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(sourceInput);

        Assertions.assertEquals(expectedSourceLength, sourceOutput);
        Assertions.assertEquals(endpointDifference, sourceOutput);

        LocalDateRange followUp = generateFollowUp(source);
        Assertions.assertEquals(source.getStart(), followUp.getStart());
        Assertions.assertEquals(source.getEnd().plusDays(23), followUp.getEnd());

        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input followUpInput =
                new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(followUp);
        int followUpOutput =
                MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUpInput);

        Assertions.assertEquals(endpointDifference + 23L, followUpOutput);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals((long) sourceOutput + 23L, (long) followUpOutput);
    }

}
