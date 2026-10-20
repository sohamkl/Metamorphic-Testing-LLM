import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private LocalDateRange generateFollowUp(LocalDateRange source) {
        return LocalDateRange.of(source.getStart(), source.getEnd().plusDays(23));
    }

    private int invokeLength(LocalDateRange range) {
        return MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(range));
    }

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput + 23, followUpOutput);
    }

    private void assertMetamorphicRelationFor(LocalDateRange source, int expectedSourceLength) {
        LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = invokeLength(source);
        int followUpOutput = invokeLength(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_RANGE_FACTORY_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 15));
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    void DIRECT_ONE_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 2));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void DIRECT_TWENTY_TWO_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 23));
        assertMetamorphicRelationFor(source, 22);
    }

    @Test
    void DIRECT_TWENTY_THREE_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 24));
        assertMetamorphicRelationFor(source, 23);
    }

    @Test
    void DIRECT_TEN_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 7, 10), LocalDate.of(2024, 7, 20));
        assertMetamorphicRelationFor(source, 10);
    }

    @Test
    void DIRECT_MONTH_BOUNDARY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 31), LocalDate.of(2024, 2, 2));
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void DIRECT_LEAP_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void DIRECT_NON_LEAP_FEBRUARY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 2, 28), LocalDate.of(2023, 3, 1));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void DIRECT_YEAR_BOUNDARY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 1));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void DIRECT_PROLEPTIC_YEAR_ZERO_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(0, 12, 31), LocalDate.of(1, 1, 2));
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    void DIRECT_NEAR_MIN_VALID_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void DIRECT_NEAR_MAX_FOLLOWUP_SAFE_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(25), LocalDate.MAX.minusDays(24));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void DIRECT_MAX_FINITE_RELATION_LENGTH_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(Integer.MAX_VALUE - 23L));
        assertMetamorphicRelationFor(source, Integer.MAX_VALUE - 23);
    }

    @Test
    void PERIOD_ZERO_LENGTH_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), Period.ZERO);
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    void PERIOD_DAY_LENGTH_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), Period.ofDays(10));
        assertMetamorphicRelationFor(source, 10);
    }

    @Test
    void PERIOD_MONTH_FROM_JANUARY_31_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 31), Period.ofMonths(1));
        assertMetamorphicRelationFor(source, 29);
    }

    @Test
    void PERIOD_YEAR_ACROSS_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 3, 1), Period.ofYears(1));
        assertMetamorphicRelationFor(source, 366);
    }

    @Test
    void CLOSED_SINGLE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void CLOSED_LEAP_DAY_INTERVAL_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    void PARSE_DATE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-06-01/2024-06-11");
        assertMetamorphicRelationFor(source, 10);
    }

    @Test
    void PARSE_DATE_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-06-01/P10D");
        assertMetamorphicRelationFor(source, 10);
    }

    @Test
    void PARSE_PERIOD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("P10D/2024-06-11");
        assertMetamorphicRelationFor(source, 10);
    }

    @Test
    void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 6, 15));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void FROM_LOCAL_DATE_TIME_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDateTime.of(2024, 6, 15, 23, 59, 59));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    void FROM_LEAP_YEAR_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        assertMetamorphicRelationFor(source, 29);
    }

    @Test
    void FROM_NON_LEAP_YEAR_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2023, 2));
        assertMetamorphicRelationFor(source, 28);
    }

    @Test
    void FROM_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        assertMetamorphicRelationFor(source, 366);
    }

    @Test
    void FROM_NON_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        assertMetamorphicRelationFor(source, 365);
    }
}
