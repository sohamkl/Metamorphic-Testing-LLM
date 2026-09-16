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

    private LocalDateRange generateFollowUp(LocalDateRange source) {
        return LocalDateRange.of(source.getStart(), source.getEnd().plusDays(23));
    }

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput + 23, followUpOutput);
    }

    private void assertMetamorphicRelationFor(LocalDateRange source, int expectedSourceLength) {
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OF_EMPTY_EQUAL_DATES_equalDates() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void OF_EMPTY_FACTORY_dedicatedFactory() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 15));
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void OF_SINGLE_DAY_ordinaryDates() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void OF_EXACTLY_TWENTY_THREE_DAYS_matchingShiftAmount() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 24));
        assertMetamorphicRelationFor(source, 23);
    }

    @Test
    public void OF_MONTH_END_ONE_DAY_crossesMonthBoundary() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 31), LocalDate.of(2024, 2, 1));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void OF_LEAP_DAY_CROSSING_crossesFebruaryTwentyNinth() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    public void OF_YEAR_BOUNDARY_crossesNewYear() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 2));
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    public void OF_MIN_ADJACENT_BOUNDED_nearMinimumDate() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN.plusDays(2), LocalDate.MIN.plusDays(3));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void OF_MAX_ADJACENT_SAFE_FOLLOW_UP_nearMaximumDate() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(25), LocalDate.MAX.minusDays(24));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void OF_MAXIMUM_EXACT_INT_FOLLOW_UP_largestNonSaturatingSource() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDate end = start.plusDays(2147483624L);
        LocalDateRange source = LocalDateRange.of(start, end);
        assertMetamorphicRelationFor(source, 2147483624);
    }

    @Test
    public void OF_CLOSED_SINGLE_DATE_closedSingleton() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void OF_CLOSED_MONTH_BOUNDARY_closedAcrossMonth() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 1, 31), LocalDate.of(2024, 2, 1));
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    public void OF_CLOSED_LEAP_INTERVAL_closedLeapDayInterval() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    public void OF_CLOSED_MIN_ADJACENT_closedNearMinimumDate() {
        LocalDate date = LocalDate.MIN.plusDays(1);
        LocalDateRange source = LocalDateRange.ofClosed(date, date);
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void OF_PERIOD_ZERO_zeroPeriod() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), Period.ZERO);
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void OF_PERIOD_ONE_DAY_oneDayPeriod() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), Period.ofDays(1));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void OF_PERIOD_TWENTY_THREE_DAYS_dayPeriodMatchingShift() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 1), Period.ofDays(23));
        assertMetamorphicRelationFor(source, 23);
    }

    @Test
    public void OF_PERIOD_MONTH_END_LEAP_YEAR_monthPeriodFromJanuaryEnd() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 31), Period.ofMonths(1));
        assertMetamorphicRelationFor(source, 29);
    }

    @Test
    public void OF_PERIOD_LEAP_DAY_PLUS_YEAR_yearPeriodFromLeapDay() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 29), Period.ofYears(1));
        assertMetamorphicRelationFor(source, 365);
    }

    @Test
    public void OF_PERIOD_MONTH_AND_DAY_mixedCalendarPeriod() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 31), Period.of(0, 1, 1));
        assertMetamorphicRelationFor(source, 30);
    }

    @Test
    public void PARSE_DATE_DATE_EMPTY_parsedEmptyRange() {
        LocalDateRange source = LocalDateRange.parse("2024-06-15/2024-06-15");
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void PARSE_DATE_DATE_LEAP_CROSSING_parsedLeapCrossing() {
        LocalDateRange source = LocalDateRange.parse("2024-02-28/2024-03-01");
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    public void PARSE_DATE_PERIOD_ZERO_parsedZeroPeriod() {
        LocalDateRange source = LocalDateRange.parse("2024-06-15/P0D");
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void PARSE_DATE_PERIOD_MONTH_END_parsedMonthPeriod() {
        LocalDateRange source = LocalDateRange.parse("2024-01-31/P1M");
        assertMetamorphicRelationFor(source, 29);
    }

    @Test
    public void PARSE_PERIOD_DATE_DAYS_parsedLeadingDayPeriod() {
        LocalDateRange source = LocalDateRange.parse("P23D/2024-01-24");
        assertMetamorphicRelationFor(source, 23);
    }

    @Test
    public void PARSE_PERIOD_DATE_MONTH_END_parsedLeadingMonthPeriod() {
        LocalDateRange source = LocalDateRange.parse("P1M/2024-02-29");
        assertMetamorphicRelationFor(source, 31);
    }

    @Test
    public void FROM_LOCAL_DATE_localDateTemporalQuery() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 6, 15));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void FROM_LOCAL_DATE_TIME_timeComponentIgnored() {
        LocalDateRange source = LocalDateRange.from(LocalDateTime.of(2024, 6, 15, 23, 59, 59));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void FROM_YEAR_NON_LEAP_nonLeapYearBranch() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        assertMetamorphicRelationFor(source, 365);
    }

    @Test
    public void FROM_YEAR_LEAP_leapYearBranch() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        assertMetamorphicRelationFor(source, 366);
    }

    @Test
    public void FROM_YEAR_MONTH_NON_LEAP_FEBRUARY_nonLeapFebruaryBranch() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2023, 2));
        assertMetamorphicRelationFor(source, 28);
    }

    @Test
    public void FROM_YEAR_MONTH_LEAP_FEBRUARY_leapFebruaryBranch() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        assertMetamorphicRelationFor(source, 29);
    }

    @Test
    public void FROM_YEAR_HALF_FIRST_LEAP_firstHalfBranch() {
        YearHalf temporal = YearHalf.of(2024, Half.H1);
        LocalDateRange source = LocalDateRange.from(temporal);
        assertMetamorphicRelationFor(source, 182);
    }

    @Test
    public void FROM_YEAR_HALF_SECOND_LEAP_secondHalfBranch() {
        YearHalf temporal = YearHalf.of(2024, Half.H2);
        LocalDateRange source = LocalDateRange.from(temporal);
        assertMetamorphicRelationFor(source, 184);
    }

    @Test
    public void FROM_YEAR_QUARTER_FIRST_LEAP_firstQuarterBranch() {
        YearQuarter temporal = YearQuarter.of(2024, Quarter.Q1);
        LocalDateRange source = LocalDateRange.from(temporal);
        assertMetamorphicRelationFor(source, 91);
    }

    @Test
    public void FROM_YEAR_QUARTER_FOURTH_LEAP_fourthQuarterBranch() {
        YearQuarter temporal = YearQuarter.of(2024, Quarter.Q4);
        LocalDateRange source = LocalDateRange.from(temporal);
        assertMetamorphicRelationFor(source, 92);
    }

    @Test
    public void FROM_YEAR_WEEK_completeIsoWeekBranch() {
        YearWeek temporal = YearWeek.of(2024, 20);
        LocalDateRange source = LocalDateRange.from(temporal);
        assertMetamorphicRelationFor(source, 7);
    }

    @Test
    public void FROM_HIGH_YEAR_yearNearMaximumDate() {
        LocalDateRange source = LocalDateRange.from(Year.of(999999998));
        assertMetamorphicRelationFor(source, 365);
    }

    @Test
    public void FROM_HIGH_YEAR_MONTH_monthNearMaximumDate() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(999999999, 11));
        assertMetamorphicRelationFor(source, 30);
    }
}
