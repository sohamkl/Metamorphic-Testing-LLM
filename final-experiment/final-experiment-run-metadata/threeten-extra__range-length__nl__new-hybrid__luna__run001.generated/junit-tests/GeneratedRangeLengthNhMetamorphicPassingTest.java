import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static LocalDateRange generateFollowUp(LocalDateRange source) {
        return LocalDateRange.of(source.getStart(), source.getEnd().plusDays(23));
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput + 23, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(LocalDateRange source) {
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_RANGE_OF_EMPTY_ordinary() {
        assertMetamorphicRelationFor(
                LocalDateRange.ofEmpty(LocalDate.of(2024, 1, 15)));
    }

    @Test
    public void EMPTY_RANGE_OF_EMPTY_leapYear() {
        assertMetamorphicRelationFor(
                LocalDateRange.ofEmpty(LocalDate.of(2020, 2, 20)));
    }

    @Test
    public void ONE_DAY_HALF_OPEN_ordinary() {
        LocalDate start = LocalDate.of(2024, 3, 10);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(1)));
    }

    @Test
    public void ONE_DAY_HALF_OPEN_monthBoundary() {
        LocalDate start = LocalDate.of(2023, 4, 30);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(1)));
    }

    @Test
    public void SHORT_LENGTHS_BELOW_RELATION_OFFSET_twoDays() {
        LocalDate start = LocalDate.of(2024, 1, 10);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(2)));
    }

    @Test
    public void SHORT_LENGTHS_BELOW_RELATION_OFFSET_tenDays() {
        LocalDate start = LocalDate.of(2024, 5, 10);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(10)));
    }

    @Test
    public void SHORT_LENGTHS_BELOW_RELATION_OFFSET_twentyTwoDays() {
        LocalDate start = LocalDate.of(2024, 11, 20);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(22)));
    }

    @Test
    public void EXACTLY_TWENTY_THREE_DAYS_calendarBoundary() {
        LocalDate start = LocalDate.of(2024, 2, 15);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(23)));
    }

    @Test
    public void EXACTLY_TWENTY_THREE_DAYS_yearBoundary() {
        LocalDate start = LocalDate.of(2023, 12, 15);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(23)));
    }

    @Test
    public void TWENTY_FOUR_DAY_RANGE_ordinary() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(24)));
    }

    @Test
    public void TWENTY_FOUR_DAY_RANGE_leapBoundary() {
        LocalDate start = LocalDate.of(2020, 2, 1);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(24)));
    }

    @Test
    public void MONTH_BOUNDARY_HALF_OPEN_monthEnd() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        LocalDate end = LocalDate.of(2024, 2, 2);
        assertMetamorphicRelationFor(LocalDateRange.of(start, end));
    }

    @Test
    public void MONTH_BOUNDARY_HALF_OPEN_longMonthCrossing() {
        LocalDate start = LocalDate.of(2023, 7, 31);
        LocalDate end = LocalDate.of(2023, 8, 12);
        assertMetamorphicRelationFor(LocalDateRange.of(start, end));
    }

    @Test
    public void LEAP_DAY_CROSSING_short() {
        LocalDate start = LocalDate.of(2020, 2, 27);
        LocalDate end = LocalDate.of(2020, 3, 2);
        assertMetamorphicRelationFor(LocalDateRange.of(start, end));
    }

    @Test
    public void LEAP_DAY_CROSSING_multiYear() {
        LocalDate start = LocalDate.of(2019, 12, 1);
        LocalDate end = LocalDate.of(2020, 3, 15);
        assertMetamorphicRelationFor(LocalDateRange.of(start, end));
    }

    @Test
    public void YEAR_BOUNDARY_CROSSING_short() {
        LocalDate start = LocalDate.of(2023, 12, 31);
        LocalDate end = LocalDate.of(2024, 1, 3);
        assertMetamorphicRelationFor(LocalDateRange.of(start, end));
    }

    @Test
    public void YEAR_BOUNDARY_CROSSING_multiYear() {
        LocalDate start = LocalDate.of(2022, 12, 20);
        LocalDate end = LocalDate.of(2024, 1, 10);
        assertMetamorphicRelationFor(LocalDateRange.of(start, end));
    }

    @Test
    public void PERIOD_FACTORY_ZERO_ordinary() {
        assertMetamorphicRelationFor(
                LocalDateRange.of(LocalDate.of(2024, 6, 15), Period.ZERO));
    }

    @Test
    public void PERIOD_FACTORY_ZERO_leapDay() {
        assertMetamorphicRelationFor(
                LocalDateRange.of(LocalDate.of(2020, 2, 29), Period.ZERO));
    }

    @Test
    public void PERIOD_FACTORY_MONTH_AND_DAY_monthPeriod() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        assertMetamorphicRelationFor(
                LocalDateRange.of(start, Period.ofMonths(1)));
    }

    @Test
    public void PERIOD_FACTORY_MONTH_AND_DAY_dayPeriod() {
        LocalDate start = LocalDate.of(2024, 3, 1);
        assertMetamorphicRelationFor(
                LocalDateRange.of(start, Period.ofDays(30)));
    }

    @Test
    public void CLOSED_RANGE_SINGLE_DAY_ordinary() {
        LocalDate date = LocalDate.of(2024, 7, 4);
        assertMetamorphicRelationFor(LocalDateRange.ofClosed(date, date));
    }

    @Test
    public void CLOSED_RANGE_SINGLE_DAY_yearEnd() {
        LocalDate date = LocalDate.of(2023, 12, 31);
        assertMetamorphicRelationFor(LocalDateRange.ofClosed(date, date));
    }

    @Test
    public void CLOSED_RANGE_LEAP_AND_YEAR_END_leapDay() {
        LocalDate start = LocalDate.of(2020, 2, 25);
        assertMetamorphicRelationFor(
                LocalDateRange.ofClosed(start, LocalDate.of(2020, 2, 29)));
    }

    @Test
    public void CLOSED_RANGE_LEAP_AND_YEAR_END_decemberEnd() {
        LocalDate start = LocalDate.of(2023, 12, 20);
        assertMetamorphicRelationFor(
                LocalDateRange.ofClosed(start, LocalDate.of(2023, 12, 31)));
    }

    @Test
    public void FROM_LOCAL_DATE_BRANCH_directDate() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(LocalDate.of(2024, 4, 15)));
    }

    @Test
    public void FROM_LOCAL_DATE_BRANCH_leapDate() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(LocalDate.of(2020, 2, 29)));
    }

    @Test
    public void FROM_YEAR_BRANCH_nonLeapYear() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(java.time.Year.of(2023)));
    }

    @Test
    public void FROM_YEAR_BRANCH_leapYear() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(java.time.Year.of(2020)));
    }

    @Test
    public void FROM_YEAR_MONTH_BRANCH_february() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(java.time.YearMonth.of(2024, 2)));
    }

    @Test
    public void FROM_YEAR_MONTH_BRANCH_thirtyDayMonth() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(java.time.YearMonth.of(2023, 4)));
    }

    @Test
    public void FROM_YEAR_HALF_BRANCH_firstHalf() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(YearHalf.of(2024, 1)));
    }

    @Test
    public void FROM_YEAR_HALF_BRANCH_secondHalf() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(YearHalf.of(2023, 2)));
    }

    @Test
    public void FROM_YEAR_QUARTER_BRANCH_firstQuarter() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(YearQuarter.of(2024, 1)));
    }

    @Test
    public void FROM_YEAR_QUARTER_BRANCH_secondQuarter() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(YearQuarter.of(2023, 2)));
    }

    @Test
    public void FROM_YEAR_QUARTER_BRANCH_fourthQuarter() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(YearQuarter.of(2023, 4)));
    }

    @Test
    public void FROM_YEAR_WEEK_BRANCH_monthCrossing() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(YearWeek.of(2024, 5)));
    }

    @Test
    public void FROM_YEAR_WEEK_BRANCH_yearCrossing() {
        assertMetamorphicRelationFor(
                LocalDateRange.from(YearWeek.of(2023, 52)));
    }

    @Test
    public void PARSE_DATE_DATE_shortRange() {
        assertMetamorphicRelationFor(
                LocalDateRange.parse("2024-01-15/2024-02-10"));
    }

    @Test
    public void PARSE_DATE_DATE_leapRange() {
        assertMetamorphicRelationFor(
                LocalDateRange.parse("2020-02-27/2020-03-03"));
    }

    @Test
    public void PARSE_DATE_PERIOD_dayPeriod() {
        assertMetamorphicRelationFor(
                LocalDateRange.parse("2024-01-31/P30D"));
    }

    @Test
    public void PARSE_DATE_PERIOD_monthPeriod() {
        assertMetamorphicRelationFor(
                LocalDateRange.parse("2023-12-15/P1M"));
    }

    @Test
    public void PARSE_PERIOD_DATE_periodBeforeDate() {
        assertMetamorphicRelationFor(
                LocalDateRange.parse("P23D/2024-03-15"));
    }

    @Test
    public void PARSE_PERIOD_DATE_monthPeriodBeforeDate() {
        assertMetamorphicRelationFor(
                LocalDateRange.parse("P1M/2024-03-31"));
    }

    @Test
    public void NEAR_MINIMUM_VALID_BOUNDED_DATES_short() {
        LocalDate start = LocalDate.MIN.plusDays(2);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(24)));
    }

    @Test
    public void NEAR_MINIMUM_VALID_BOUNDED_DATES_yearLikeLength() {
        LocalDate start = LocalDate.MIN.plusDays(2);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(366)));
    }

    @Test
    public void NEAR_MAXIMUM_FINITE_FOLLOW_UP_short() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        assertMetamorphicRelationFor(
                LocalDateRange.of(end.minusDays(30), end));
    }

    @Test
    public void NEAR_MAXIMUM_FINITE_FOLLOW_UP_large() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        assertMetamorphicRelationFor(
                LocalDateRange.of(end.minusDays(10000), end));
    }

    @Test
    public void MAXIMUM_INT_SAFE_LENGTH_exactLimit() {
        LocalDate start = LocalDate.MIN.plusDays(2);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 23);
        assertMetamorphicRelationFor(LocalDateRange.of(start, end));
    }

    @Test
    public void MAXIMUM_INT_SAFE_LENGTH_differentOrigin() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 23);
        assertMetamorphicRelationFor(LocalDateRange.of(start, end));
    }
}
