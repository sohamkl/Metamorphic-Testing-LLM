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

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static final int SHIFT_DAYS = 23;

    private MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input(LocalDateRange receiver) {
        return new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(receiver);
    }

    private MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input generateFollowUp(MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input source) {
        LocalDateRange sourceRange = source.receiver();
        LocalDate shiftedEnd = sourceRange.getEnd().plusDays(SHIFT_DAYS);
        LocalDateRange followUpRange = LocalDateRange.of(sourceRange.getStart(), shiftedEnd);
        return input(followUpRange);
    }

    private void assertMetamorphicRelationFor(MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input source) {
        int sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(source);
        int followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        assertEquals(sourceOutput + SHIFT_DAYS, followUpOutput);
    }

    private void assertSourceOutputAndMetamorphicRelation(LocalDateRange sourceRange, int expectedSourceOutput) {
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input source = input(sourceRange);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void EMPTY_RANGE_VIA_OF_EMPTY_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 15));
        assertSourceOutputAndMetamorphicRelation(source, 0);
    }

    @Test
    public void EMPTY_RANGE_VIA_HALF_OPEN_OF_variation1() {
        LocalDate date = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(date, date);
        assertSourceOutputAndMetamorphicRelation(source, 0);
    }

    @Test
    public void EMPTY_RANGE_VIA_ZERO_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 3, 10), Period.ZERO);
        assertSourceOutputAndMetamorphicRelation(source, 0);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16));
        assertSourceOutputAndMetamorphicRelation(source, 1);
    }

    @Test
    public void LENGTH_JUST_BELOW_SHIFT_variation1() {
        LocalDate start = LocalDate.of(2024, 5, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(22));
        assertSourceOutputAndMetamorphicRelation(source, 22);
    }

    @Test
    public void LENGTH_EQUAL_TO_SHIFT_variation1() {
        LocalDateRange source = LocalDateRange.parse("P23D/2000-01-24");
        assertSourceOutputAndMetamorphicRelation(source, 23);
    }

    @Test
    public void LENGTH_JUST_ABOVE_SHIFT_variation1() {
        LocalDate start = LocalDate.of(1999, 11, 10);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(24));
        assertSourceOutputAndMetamorphicRelation(source, 24);
    }

    @Test
    public void SEVEN_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 7, 8), Period.ofWeeks(1));
        assertSourceOutputAndMetamorphicRelation(source, 7);
    }

    @Test
    public void FOURTEEN_DAY_PERIOD_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 4, 1), Period.ofWeeks(2));
        assertSourceOutputAndMetamorphicRelation(source, 14);
    }

    @Test
    public void CROSS_THIRTY_ONE_DAY_MONTH_END_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 30), LocalDate.of(2024, 2, 2));
        assertSourceOutputAndMetamorphicRelation(source, 3);
    }

    @Test
    public void CROSS_THIRTY_DAY_MONTH_END_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 4, 29), LocalDate.of(2024, 5, 2));
        assertSourceOutputAndMetamorphicRelation(source, 3);
    }

    @Test
    public void NON_LEAP_FEBRUARY_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 2, 28), LocalDate.of(2023, 3, 1));
        assertSourceOutputAndMetamorphicRelation(source, 1);
    }

    @Test
    public void LEAP_FEBRUARY_SPAN_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
        assertSourceOutputAndMetamorphicRelation(source, 2);
    }

    @Test
    public void LEAP_DAY_SINGLETON_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 29), Period.ofDays(1));
        assertSourceOutputAndMetamorphicRelation(source, 1);
    }

    @Test
    public void COMMON_YEAR_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 1));
        assertSourceOutputAndMetamorphicRelation(source, 1);
    }

    @Test
    public void PROLEPTIC_YEAR_ZERO_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(-1, 12, 31), LocalDate.of(0, 1, 1));
        assertSourceOutputAndMetamorphicRelation(source, 1);
    }

    @Test
    public void UNIX_EPOCH_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(1969, 12, 31), LocalDate.of(1970, 1, 2));
        assertSourceOutputAndMetamorphicRelation(source, 2);
    }

    @Test
    public void EARLIEST_BOUNDED_START_variation1() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange source = LocalDateRange.of(start, LocalDate.MIN.plusDays(2));
        assertSourceOutputAndMetamorphicRelation(source, 1);
    }

    @Test
    public void LATEST_ORDINARY_SHIFTABLE_END_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(25), end);
        assertSourceOutputAndMetamorphicRelation(source, 1);
    }

    @Test
    public void FOLLOW_UP_ENDS_AT_MAX_MINUS_ONE_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDate start = end.minusDays(24);
        LocalDateRange source = LocalDateRange.of(start, end);
        assertSourceOutputAndMetamorphicRelation(source, 24);
    }

    @Test
    public void LARGE_LENGTH_BELOW_INT_CEILING_variation1() {
        int sourceLength = Integer.MAX_VALUE - 24;
        LocalDate end = LocalDate.of(2100, 1, 1);
        LocalDate start = end.minusDays(sourceLength);
        LocalDateRange source = LocalDateRange.of(start, end);
        assertSourceOutputAndMetamorphicRelation(source, sourceLength);
    }

    @Test
    public void MAXIMUM_ADMISSIBLE_SOURCE_LENGTH_variation1() {
        int sourceLength = Integer.MAX_VALUE - 23;
        LocalDate end = LocalDate.of(2200, 6, 30);
        LocalDate start = end.minusDays(sourceLength);
        LocalDateRange source = LocalDateRange.of(start, end);
        assertSourceOutputAndMetamorphicRelation(source, sourceLength);
    }

    @Test
    public void EXACT_UNBOUNDED_END_SENTINEL_TRANSITION_variation1() {
        int sourceLength = Integer.MAX_VALUE - 23;
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDate start = end.minusDays(sourceLength);
        LocalDateRange source = LocalDateRange.of(start, end);
        assertSourceOutputAndMetamorphicRelation(source, sourceLength);
    }

    @Test
    public void CLOSED_SINGLE_DAY_RANGE_variation1() {
        LocalDate date = LocalDate.of(2024, 6, 1);
        LocalDateRange source = LocalDateRange.ofClosed(date, date);
        assertSourceOutputAndMetamorphicRelation(source, 1);
    }

    @Test
    public void CLOSED_FULL_MONTH_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 31));
        assertSourceOutputAndMetamorphicRelation(source, 31);
    }

    @Test
    public void CLOSED_LEAP_FEBRUARY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 29));
        assertSourceOutputAndMetamorphicRelation(source, 29);
    }

    @Test
    public void PERIOD_ONE_MONTH_FROM_MONTH_END_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 31), Period.ofMonths(1));
        assertSourceOutputAndMetamorphicRelation(source, 29);
    }

    @Test
    public void PERIOD_ONE_MONTH_FROM_SHORT_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 29), Period.ofMonths(1));
        assertSourceOutputAndMetamorphicRelation(source, 29);
    }

    @Test
    public void PERIOD_ONE_YEAR_FROM_LEAP_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 29), Period.ofYears(1));
        assertSourceOutputAndMetamorphicRelation(source, 365);
    }

    @Test
    public void FROM_LOCAL_DATE_QUERY_BRANCH_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 5, 20));
        assertSourceOutputAndMetamorphicRelation(source, 1);
    }

    @Test
    public void FROM_YEAR_COMMON_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        assertSourceOutputAndMetamorphicRelation(source, 365);
    }

    @Test
    public void FROM_YEAR_LEAP_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        assertSourceOutputAndMetamorphicRelation(source, 366);
    }

    @Test
    public void FROM_YEARMONTH_COMMON_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2023, 2));
        assertSourceOutputAndMetamorphicRelation(source, 28);
    }

    @Test
    public void FROM_YEARMONTH_LEAP_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        assertSourceOutputAndMetamorphicRelation(source, 29);
    }

    @Test
    public void FROM_YEARHALF_FIRST_LEAP_HALF_variation1() {
        YearHalf firstHalf = YearHalf.of(2024, Half.H1);
        LocalDateRange source = LocalDateRange.from(firstHalf);
        assertSourceOutputAndMetamorphicRelation(source, 182);
    }

    @Test
    public void FROM_YEARHALF_SECOND_HALF_variation1() {
        YearHalf secondHalf = YearHalf.of(2023, Half.H2);
        LocalDateRange source = LocalDateRange.from(secondHalf);
        assertSourceOutputAndMetamorphicRelation(source, 184);
    }

    @Test
    public void FROM_YEARQUARTER_LEAP_Q1_variation1() {
        YearQuarter firstQuarter = YearQuarter.of(2024, Quarter.Q1);
        LocalDateRange source = LocalDateRange.from(firstQuarter);
        assertSourceOutputAndMetamorphicRelation(source, 91);
    }

    @Test
    public void FROM_YEARQUARTER_THIRTY_DAY_MIX_variation1() {
        YearQuarter secondQuarter = YearQuarter.of(2024, Quarter.Q2);
        LocalDateRange source = LocalDateRange.from(secondQuarter);
        assertSourceOutputAndMetamorphicRelation(source, 91);
    }

    @Test
    public void FROM_YEARWEEK_variation1() {
        YearWeek yearWeek = YearWeek.of(2024, 20);
        LocalDateRange source = LocalDateRange.from(yearWeek);
        assertSourceOutputAndMetamorphicRelation(source, 7);
    }

    @Test
    public void PARSE_DATE_DATE_FORM_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-01/2024-01-10");
        assertSourceOutputAndMetamorphicRelation(source, 9);
    }

    @Test
    public void PARSE_DATE_PERIOD_FORM_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-31/P1M");
        assertSourceOutputAndMetamorphicRelation(source, 29);
    }

    @Test
    public void PARSE_PERIOD_DATE_FORM_variation1() {
        LocalDateRange source = LocalDateRange.parse("P1M/2024-03-31");
        assertSourceOutputAndMetamorphicRelation(source, 31);
    }

    @Test
    public void PARSE_EMPTY_DATE_DATE_FORM_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-08-20/2024-08-20");
        assertSourceOutputAndMetamorphicRelation(source, 0);
    }

    @Test
    public void NINETY_DAY_FINITE_RANGE_variation1() {
        LocalDate start = LocalDate.of(2021, 9, 17);
        LocalDateRange source = LocalDateRange.of(start, Period.ofDays(90));
        assertSourceOutputAndMetamorphicRelation(source, 90);
    }

    @Test
    public void ONE_HUNDRED_EIGHTY_ONE_DAY_RANGE_variation1() {
        LocalDate start = LocalDate.of(2019, 3, 15);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(181));
        assertSourceOutputAndMetamorphicRelation(source, 181);
    }

    @Test
    public void ORDINARY_COMMON_YEAR_LENGTH_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 1));
        assertSourceOutputAndMetamorphicRelation(source, 365);
    }

    @Test
    public void ORDINARY_LEAP_YEAR_LENGTH_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1));
        assertSourceOutputAndMetamorphicRelation(source, 366);
    }
}
