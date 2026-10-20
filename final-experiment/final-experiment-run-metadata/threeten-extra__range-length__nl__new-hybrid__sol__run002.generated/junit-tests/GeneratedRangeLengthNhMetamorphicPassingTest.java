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

    @Test
    public void EMPTY_ORDINARY_variation1() {
        LocalDateRange source =
                LocalDateRange.ofEmpty(LocalDate.of(2023, 6, 15));
        Assertions.assertTrue(source.isEmpty());
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void EMPTY_EARLIEST_OFEMPTY_LOCATION_variation1() {
        LocalDate date = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.ofEmpty(date);
        Assertions.assertEquals(source.getStart(), source.getEnd());
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void EMPTY_LATEST_TRANSFORMABLE_END_variation1() {
        LocalDateRange source =
                LocalDateRange.ofEmpty(LocalDate.MAX.minusDays(24));
        Assertions.assertTrue(source.isEmpty());
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_variation1() {
        LocalDate start = LocalDate.of(2023, 4, 12);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(1));
        Assertions.assertFalse(source.isEmpty());
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void LENGTH_22_BELOW_SHIFT_variation1() {
        LocalDate start = LocalDate.of(2024, 2, 10);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(22));
        assertMetamorphicRelationFor(source, 22);
    }

    @Test
    public void LENGTH_EXACTLY_23_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));
        assertMetamorphicRelationFor(source, 23);
    }

    @Test
    public void LENGTH_24_ABOVE_SHIFT_variation1() {
        LocalDate start = LocalDate.of(2023, 10, 3);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(24));
        assertMetamorphicRelationFor(source, 24);
    }

    @Test
    public void LENGTH_INT_LIMIT_MINUS_24_variation1() {
        LocalDate end = LocalDate.of(2023, 1, 1);
        LocalDate start = end.minusDays(2_147_483_623L);
        LocalDateRange source = LocalDateRange.of(start, end);
        assertMetamorphicRelationFor(source, 2_147_483_623);
    }

    @Test
    public void LENGTH_INT_LIMIT_MINUS_23_variation1() {
        LocalDate end = LocalDate.of(2023, 1, 1);
        LocalDate start = end.minusDays(2_147_483_624L);
        LocalDateRange source = LocalDateRange.of(start, end);
        assertMetamorphicRelationFor(source, 2_147_483_624);
    }

    @Test
    public void EARLIEST_BOUNDED_START_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(1),
                LocalDate.MIN.plusDays(2));
        Assertions.assertFalse(source.isUnboundedStart());
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void LATEST_TRANSFORMABLE_EXCLUSIVE_END_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.of(end.minusDays(1), end);
        Assertions.assertFalse(source.isUnboundedEnd());
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void CLOSED_SINGLETON_variation1() {
        LocalDate date = LocalDate.of(2023, 8, 10);
        LocalDateRange source = LocalDateRange.ofClosed(date, date);
        Assertions.assertEquals(date.plusDays(1), source.getEnd());
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void CLOSED_RANGE_ACROSS_LEAP_DAY_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 2, 28),
                LocalDate.of(2024, 3, 1));
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    public void ZERO_PERIOD_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 5, 20),
                Period.ZERO);
        Assertions.assertTrue(source.isEmpty());
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void PERIOD_OF_DAYS_23_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 5, 20),
                Period.ofDays(23));
        assertMetamorphicRelationFor(source, 23);
    }

    @Test
    public void PERIOD_OF_TWO_WEEKS_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 5, 20),
                Period.ofWeeks(2));
        assertMetamorphicRelationFor(source, 14);
    }

    @Test
    public void ONE_MONTH_FROM_JANUARY_31_COMMON_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 31),
                Period.ofMonths(1));
        Assertions.assertEquals(LocalDate.of(2023, 2, 28), source.getEnd());
        assertMetamorphicRelationFor(source, 28);
    }

    @Test
    public void ONE_MONTH_FROM_JANUARY_31_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 31),
                Period.ofMonths(1));
        Assertions.assertEquals(LocalDate.of(2024, 2, 29), source.getEnd());
        assertMetamorphicRelationFor(source, 29);
    }

    @Test
    public void ONE_YEAR_FROM_COMMON_YEAR_START_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 1),
                Period.ofYears(1));
        assertMetamorphicRelationFor(source, 365);
    }

    @Test
    public void ONE_YEAR_FROM_LEAP_YEAR_START_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 1),
                Period.ofYears(1));
        assertMetamorphicRelationFor(source, 366);
    }

    @Test
    public void MIXED_MONTH_AND_DAY_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 30),
                Period.of(0, 1, 2));
        Assertions.assertEquals(LocalDate.of(2023, 3, 2), source.getEnd());
        assertMetamorphicRelationFor(source, 31);
    }

    @Test
    public void FROM_LOCAL_DATE_variation1() {
        LocalDate date = LocalDate.of(2023, 7, 12);
        LocalDateRange source = LocalDateRange.from(date);
        Assertions.assertEquals(date, source.getStart());
        Assertions.assertEquals(date.plusDays(1), source.getEnd());
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void FROM_TEMPORAL_WITH_DATE_AND_TIME_variation1() {
        LocalDateTime dateTime =
                LocalDateTime.of(2023, 7, 12, 18, 45, 30);
        LocalDateRange source = LocalDateRange.from(dateTime);
        Assertions.assertEquals(dateTime.toLocalDate(), source.getStart());
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void FROM_COMMON_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        Assertions.assertEquals(LocalDate.of(2023, 1, 1), source.getStart());
        Assertions.assertEquals(LocalDate.of(2024, 1, 1), source.getEnd());
        assertMetamorphicRelationFor(source, 365);
    }

    @Test
    public void FROM_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        Assertions.assertEquals(LocalDate.of(2024, 1, 1), source.getStart());
        Assertions.assertEquals(LocalDate.of(2025, 1, 1), source.getEnd());
        assertMetamorphicRelationFor(source, 366);
    }

    @Test
    public void FROM_28_DAY_YEARMONTH_variation1() {
        LocalDateRange source =
                LocalDateRange.from(YearMonth.of(2023, 2));
        assertMetamorphicRelationFor(source, 28);
    }

    @Test
    public void FROM_29_DAY_YEARMONTH_variation1() {
        LocalDateRange source =
                LocalDateRange.from(YearMonth.of(2024, 2));
        assertMetamorphicRelationFor(source, 29);
    }

    @Test
    public void FROM_30_DAY_YEARMONTH_variation1() {
        LocalDateRange source =
                LocalDateRange.from(YearMonth.of(2023, 4));
        assertMetamorphicRelationFor(source, 30);
    }

    @Test
    public void FROM_31_DAY_YEARMONTH_variation1() {
        LocalDateRange source =
                LocalDateRange.from(YearMonth.of(2023, 7));
        assertMetamorphicRelationFor(source, 31);
    }

    @Test
    public void FROM_FIRST_HALF_LEAP_YEAR_variation1() {
        YearHalf firstHalf = YearHalf.of(2024, Half.H1);
        LocalDateRange source = LocalDateRange.from(firstHalf);
        Assertions.assertEquals(LocalDate.of(2024, 1, 1), source.getStart());
        Assertions.assertEquals(LocalDate.of(2024, 7, 1), source.getEnd());
        assertMetamorphicRelationFor(source, 182);
    }

    @Test
    public void FROM_SECOND_HALF_COMMON_YEAR_variation1() {
        YearHalf secondHalf = YearHalf.of(2023, Half.H2);
        LocalDateRange source = LocalDateRange.from(secondHalf);
        Assertions.assertEquals(LocalDate.of(2023, 7, 1), source.getStart());
        Assertions.assertEquals(LocalDate.of(2024, 1, 1), source.getEnd());
        assertMetamorphicRelationFor(source, 184);
    }

    @Test
    public void FROM_90_DAY_QUARTER_variation1() {
        YearQuarter quarter = YearQuarter.of(2023, Quarter.Q1);
        LocalDateRange source = LocalDateRange.from(quarter);
        assertMetamorphicRelationFor(source, 90);
    }

    @Test
    public void FROM_91_DAY_QUARTER_variation1() {
        YearQuarter quarter = YearQuarter.of(2023, Quarter.Q2);
        LocalDateRange source = LocalDateRange.from(quarter);
        assertMetamorphicRelationFor(source, 91);
    }

    @Test
    public void FROM_92_DAY_QUARTER_variation1() {
        YearQuarter quarter = YearQuarter.of(2023, Quarter.Q3);
        LocalDateRange source = LocalDateRange.from(quarter);
        assertMetamorphicRelationFor(source, 92);
    }

    @Test
    public void FROM_ORDINARY_YEAR_WEEK_variation1() {
        YearWeek week = YearWeek.of(2023, 20);
        LocalDateRange source = LocalDateRange.from(week);
        Assertions.assertEquals(
                source.getStart().plusDays(7),
                source.getEnd());
        assertMetamorphicRelationFor(source, 7);
    }

    @Test
    public void FROM_CROSS_YEAR_WEEK_variation1() {
        YearWeek week = YearWeek.of(2020, 53);
        LocalDateRange source = LocalDateRange.from(week);
        Assertions.assertNotEquals(
                source.getStart().getYear(),
                source.getEndInclusive().getYear());
        assertMetamorphicRelationFor(source, 7);
    }

    @Test
    public void PARSE_DATE_DATE_EMPTY_variation1() {
        LocalDateRange source =
                LocalDateRange.parse("2023-06-15/2023-06-15");
        Assertions.assertTrue(source.isEmpty());
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void PARSE_DATE_DATE_CROSS_YEAR_variation1() {
        LocalDateRange source =
                LocalDateRange.parse("2023-12-20/2024-01-10");
        assertMetamorphicRelationFor(source, 21);
    }

    @Test
    public void PARSE_DATE_UPPERCASE_PERIOD_variation1() {
        LocalDateRange source =
                LocalDateRange.parse("2024-02-01/P1M");
        Assertions.assertEquals(LocalDate.of(2024, 3, 1), source.getEnd());
        assertMetamorphicRelationFor(source, 29);
    }

    @Test
    public void PARSE_DATE_LOWERCASE_PERIOD_variation1() {
        LocalDateRange source =
                LocalDateRange.parse("2023-02-01/p1m");
        Assertions.assertEquals(LocalDate.of(2023, 3, 1), source.getEnd());
        assertMetamorphicRelationFor(source, 28);
    }

    @Test
    public void PARSE_UPPERCASE_PERIOD_DATE_variation1() {
        LocalDateRange source =
                LocalDateRange.parse("P1M/2023-03-31");
        Assertions.assertEquals(LocalDate.of(2023, 2, 28), source.getStart());
        assertMetamorphicRelationFor(source, 31);
    }

    @Test
    public void PARSE_LOWERCASE_PERIOD_DATE_variation1() {
        LocalDateRange source =
                LocalDateRange.parse("p23d/2023-02-01");
        assertMetamorphicRelationFor(source, 23);
    }

    @Test
    public void CROSS_COMMON_FEBRUARY_BY_DATES_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 2, 27),
                LocalDate.of(2023, 3, 2));
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    public void CROSS_LEAP_FEBRUARY_BY_DATES_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 27),
                LocalDate.of(2024, 3, 2));
        assertMetamorphicRelationFor(source, 4);
    }

    @Test
    public void CROSS_BCE_CE_YEAR_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(-1, 12, 31),
                LocalDate.of(0, 1, 2));
        Assertions.assertTrue(source.getStart().getYear() < 0);
        Assertions.assertEquals(0, source.getEnd().getYear());
        assertMetamorphicRelationFor(source, 2);
    }
}
