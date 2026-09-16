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

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static final int END_SHIFT_DAYS = 23;

    private static MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input(LocalDateRange receiver) {
        return new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(receiver);
    }

    private static MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input generateFollowUp(MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input source) {
        LocalDateRange sourceRange = source.receiver();
        LocalDate shiftedEnd = sourceRange.getEnd().plusDays(END_SHIFT_DAYS);
        LocalDateRange followUpRange = sourceRange.withEnd(shiftedEnd);
        return input(followUpRange);
    }

    private static void assertMetamorphicRelationFor(MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input source) {
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input followUp = generateFollowUp(source);
        int sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(source);
        int followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput + END_SHIFT_DAYS, followUpOutput);
    }

    @Test
    public void ORDINARY_HALF_OPEN_RANGE_variation1_tenDays() {
        LocalDate start = LocalDate.of(2023, 3, 10);
        LocalDateRange range = LocalDateRange.of(start, start.plusDays(10));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void ORDINARY_HALF_OPEN_RANGE_variation2_sixtyDays() {
        LocalDate start = LocalDate.of(2023, 6, 1);
        LocalDateRange range = LocalDateRange.of(start, start.plusDays(60));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void EMPTY_RANGE_VIA_OF_EMPTY_variation1_ordinaryDate() {
        LocalDateRange range = LocalDateRange.ofEmpty(LocalDate.of(2022, 12, 31));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void EMPTY_RANGE_VIA_HALF_OPEN_FACTORY_variation1_equalEndpoints() {
        LocalDate date = LocalDate.of(2024, 2, 29);
        LocalDateRange range = LocalDateRange.of(date, date);
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void SINGLE_DAY_HALF_OPEN_RANGE_variation1_oneDay() {
        LocalDate start = LocalDate.of(0, 1, 5);
        LocalDateRange range = LocalDateRange.of(start, start.plusDays(1));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void LENGTH_JUST_BELOW_SHIFT_variation1_twentyTwoDays() {
        LocalDateRange range = LocalDateRange.parse("2021-04-01/P22D");
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void LENGTH_EQUALS_SHIFT_variation1_twentyThreeDays() {
        LocalDate start = LocalDate.of(2025, 8, 10);
        LocalDateRange range = LocalDateRange.of(start, start.plusDays(23));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void LENGTH_JUST_ABOVE_SHIFT_variation1_twentyFourDays() {
        LocalDate start = LocalDate.of(2020, 10, 3);
        LocalDateRange range = LocalDateRange.of(start, Period.ofDays(24));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void CLOSED_SINGLE_DATE_RANGE_variation1_singleInclusiveDate() {
        LocalDate date = LocalDate.of(2018, 5, 31);
        LocalDateRange range = LocalDateRange.ofClosed(date, date);
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void CLOSED_MULTI_DATE_RANGE_variation1_multipleInclusiveDates() {
        LocalDateRange range = LocalDateRange.ofClosed(LocalDate.of(2022, 11, 25), LocalDate.of(2022, 12, 4));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void ZERO_PERIOD_RANGE_variation1_periodZero() {
        LocalDateRange range = LocalDateRange.of(LocalDate.of(2017, 7, 14), Period.ZERO);
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void MONTH_PERIOD_VARIABLE_DAY_COUNT_variation1_monthEnd() {
        LocalDateRange range = LocalDateRange.of(LocalDate.of(2024, 1, 31), Period.ofMonths(1));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void MONTH_PERIOD_VARIABLE_DAY_COUNT_variation2_nonMonthEnd() {
        LocalDateRange range = LocalDateRange.of(LocalDate.of(2023, 5, 15), Period.ofMonths(1));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void YEAR_PERIOD_OVER_LEAP_CYCLE_variation1_spansLeapFebruary() {
        LocalDateRange range = LocalDateRange.of(LocalDate.of(2019, 3, 1), Period.ofYears(1));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_LOCAL_DATE_variation1_directLocalDate() {
        LocalDateRange range = LocalDateRange.from(LocalDate.of(2020, 6, 20));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_DATE_TIME_LOCAL_DATE_QUERY_variation1_timeIgnored() {
        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 30, 23, 59, 58);
        LocalDateRange range = LocalDateRange.from(dateTime);
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_COMMON_YEAR_variation1_year2023() {
        LocalDateRange range = LocalDateRange.from(Year.of(2023));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_LEAP_YEAR_variation1_year2024() {
        LocalDateRange range = LocalDateRange.from(Year.of(2024));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_LEAP_FEBRUARY_variation1_february2024() {
        LocalDateRange range = LocalDateRange.from(YearMonth.of(2024, 2));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_THIRTY_DAY_MONTH_variation1_april2023() {
        LocalDateRange range = LocalDateRange.from(YearMonth.of(2023, 4));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_YEAR_HALF_variation1_leapFirstHalf() {
        LocalDateRange range = LocalDateRange.from(YearHalf.of(2024, Half.H1));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_YEAR_HALF_variation2_commonSecondHalf() {
        LocalDateRange range = LocalDateRange.from(YearHalf.of(2023, Half.H2));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_YEAR_QUARTER_variation1_leapFirstQuarter() {
        LocalDateRange range = LocalDateRange.from(YearQuarter.of(2024, Quarter.Q1));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_YEAR_QUARTER_variation2_thirdQuarter() {
        LocalDateRange range = LocalDateRange.from(YearQuarter.of(2023, Quarter.Q3));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void FROM_YEAR_WEEK_variation1_completeIsoWeek() {
        LocalDateRange range = LocalDateRange.from(YearWeek.of(2022, 40));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void PARSE_DATE_DATE_variation1_ordinaryDates() {
        LocalDateRange range = LocalDateRange.parse("2022-03-04/2022-03-19");
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void PARSE_DATE_DATE_variation2_crossMonth() {
        LocalDateRange range = LocalDateRange.parse("2021-09-21/2021-11-03");
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void PARSE_DATE_PERIOD_variation1_monthPeriod() {
        LocalDateRange range = LocalDateRange.parse("2020-01-31/P1M");
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void PARSE_DATE_PERIOD_variation2_dayPeriod() {
        LocalDateRange range = LocalDateRange.parse("2019-08-17/P45D");
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void PARSE_PERIOD_DATE_variation1_twentyDayPeriod() {
        LocalDateRange range = LocalDateRange.parse("P20D/2022-06-10");
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void PARSE_PERIOD_DATE_variation2_twoMonthPeriod() {
        LocalDateRange range = LocalDateRange.parse("P2M/2023-05-31");
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void PARSE_LOWERCASE_PERIOD_PREFIX_variation1_lowercasePrefix() {
        LocalDateRange range = LocalDateRange.parse("2024-02-10/p23d");
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void CROSS_LEAP_DAY_variation1_shorterThanShift() {
        LocalDateRange range = LocalDateRange.of(LocalDate.of(2024, 2, 20), LocalDate.of(2024, 3, 5));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void CROSS_LEAP_DAY_variation2_longerThanShift() {
        LocalDateRange range = LocalDateRange.of(LocalDate.of(2024, 1, 31), LocalDate.of(2024, 3, 10));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void CROSS_NON_LEAP_FEBRUARY_variation1_februaryIntoMarch() {
        LocalDateRange range = LocalDateRange.of(LocalDate.of(2023, 2, 16), LocalDate.of(2023, 3, 12));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void CROSS_CALENDAR_YEAR_variation1_decemberIntoJanuary() {
        LocalDateRange range = LocalDateRange.parse("2020-12-18/2021-01-14");
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void CROSS_BCE_CE_BOUNDARY_variation1_negativeYearToYearZero() {
        LocalDateRange range = LocalDateRange.of(LocalDate.of(-1, 12, 15), LocalDate.of(0, 1, 15));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void CROSS_DIFFERENT_MONTH_LENGTHS_variation1_acrossThirtyDayApril() {
        LocalDateRange range = LocalDateRange.of(LocalDate.of(2022, 3, 25), Period.ofDays(41));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void CROSS_DIFFERENT_MONTH_LENGTHS_variation2_acrossThirtyOneDayJuly() {
        LocalDateRange range = LocalDateRange.ofClosed(LocalDate.of(2022, 7, 20), LocalDate.of(2022, 8, 12));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void LOWEST_BOUNDED_START_variation1_minimumPlusOneDay() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange range = LocalDateRange.of(start, start.plusDays(1));
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void LATEST_END_ALLOWING_BOUNDED_FOLLOW_UP_variation1_maximumMinusTwentyFourDays() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDateRange range = LocalDateRange.of(end.minusDays(100), end);
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void LENGTH_ONE_BELOW_INT_RELATION_LIMIT_variation1_largeParsedRange() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 24L);
        LocalDateRange range = LocalDateRange.parse(start + "/" + end);
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void LENGTH_AT_INT_RELATION_LIMIT_variation1_largestAdmittedLength() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 23L);
        LocalDateRange range = LocalDateRange.of(start, end);
        assertMetamorphicRelationFor(input(range));
    }

    @Test
    public void LARGE_MULTI_MILLENNIAL_RANGE_variation1_fourMillennia() {
        LocalDate start = LocalDate.of(1000, 1, 1);
        LocalDate end = LocalDate.of(5000, 1, 1);
        Period period = Period.between(start, end);
        LocalDateRange range = LocalDateRange.of(start, period);
        assertMetamorphicRelationFor(input(range));
    }
}
