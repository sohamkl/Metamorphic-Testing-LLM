import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

public class GeneratedRangeLengthNhMetamorphicFailingTest {

    private LocalDateRange generateFollowUp(LocalDateRange source) {
        return LocalDateRange.of(source.getStart(), source.getEnd().plusDays(23));
    }

    private int invokeLength(LocalDateRange range) {
        return MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(
                new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(range));
    }

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput + 23, followUpOutput);
    }

    private void assertMetamorphicRelationFor(LocalDateRange source, int expectedSourceLength) {
        LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = invokeLength(source);
        int followUpOutput = invokeLength(followUp);
        Assertions.assertEquals(expectedSourceLength, sourceOutput);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

}
