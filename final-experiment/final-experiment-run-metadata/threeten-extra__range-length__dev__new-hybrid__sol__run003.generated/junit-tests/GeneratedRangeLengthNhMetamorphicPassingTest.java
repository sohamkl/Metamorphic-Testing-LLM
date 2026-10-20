import org.junit.jupiter.api.Test;
import org.threeten.extra.Half;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.Quarter;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static void exercise(LocalDateRange source) {
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_RANGE_OF_EMPTY_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(1987, 6, 15));
        exercise(source);
    }

    @Test
    public void EMPTY_RANGE_ZERO_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2019, 1, 31), Period.ZERO);
        exercise(source);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_variation1() {
        LocalDate start = LocalDate.of(2021, 2, 28);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(1));
        exercise(source);
    }

    @Test
    public void LENGTH_EXTRA_MINUS_ONE_variation1() {
        LocalDate start = LocalDate.of(2020, 2, 20);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(22));
        exercise(source);
    }

    @Test
    public void LENGTH_EQUAL_TO_EXTRA_variation1() {
        LocalDate start = LocalDate.of(1999, 12, 20);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));
        exercise(source);
    }

    @Test
    public void LENGTH_EXTRA_PLUS_ONE_variation1() {
        LocalDate start = LocalDate.of(1899, 12, 20);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(24));
        exercise(source);
    }

    @Test
    public void CROSS_THIRTY_DAY_MONTH_END_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2022, 4, 25),
                LocalDate.of(2022, 5, 8));
        exercise(source);
    }

    @Test
    public void CROSS_THIRTY_ONE_DAY_MONTH_END_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 27),
                LocalDate.of(2023, 2, 7));
        exercise(source);
    }

    @Test
    public void COMMON_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2019, 2, 1),
                LocalDate.of(2019, 3, 1));
        exercise(source);
    }

    @Test
    public void LEAP_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 2, 1),
                LocalDate.of(2020, 3, 1));
        exercise(source);
    }

    @Test
    public void LEAP_DAY_AS_START_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 29),
                LocalDate.of(2024, 3, 12));
        exercise(source);
    }

    @Test
    public void COMPLETE_COMMON_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2022, 1, 1));
        exercise(source);
    }

    @Test
    public void COMPLETE_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2025, 1, 1));
        exercise(source);
    }

    @Test
    public void NON_LEAP_CENTURY_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1900, 1, 1),
                LocalDate.of(1901, 1, 1));
        exercise(source);
    }

    @Test
    public void LEAP_FOUR_HUNDRED_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2001, 1, 1));
        exercise(source);
    }

    @Test
    public void CLOSED_SINGLETON_RANGE_variation1() {
        LocalDate date = LocalDate.of(2018, 12, 31);
        LocalDateRange source = LocalDateRange.ofClosed(date, date);
        exercise(source);
    }

    @Test
    public void CLOSED_RANGE_CROSSING_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2022, 8, 28),
                LocalDate.of(2022, 9, 4));
        exercise(source);
    }

    @Test
    public void PERIOD_OF_MONTHS_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 1, 31),
                Period.ofMonths(2));
        exercise(source);
    }

    @Test
    public void PERIOD_OF_YEARS_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2019, 3, 1),
                Period.ofYears(5));
        exercise(source);
    }

    @Test
    public void MIXED_PERIOD_COMPONENTS_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2016, 1, 31),
                Period.of(2, 3, 17));
        exercise(source);
    }

    @Test
    public void PARSE_DATE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse(
                "2017-06-12/2017-07-03");
        exercise(source);
    }

    @Test
    public void PARSE_DATE_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.parse(
                "2018-01-31/P2M5D");
        exercise(source);
    }

    @Test
    public void PARSE_PERIOD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse(
                "P1M10D/2020-05-20");
        exercise(source);
    }

    @Test
    public void PARSE_LOWERCASE_PERIOD_MARKER_variation1() {
        LocalDateRange source = LocalDateRange.parse(
                "2023-03-15/p1m6d");
        exercise(source);
    }

    @Test
    public void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(
                LocalDate.of(2005, 7, 9));
        exercise(source);
    }

    @Test
    public void FROM_TEMPORAL_WITH_TIME_variation1() {
        LocalDateTime temporal = LocalDateTime.of(
                2012, 10, 5, 23, 59, 58);
        LocalDateRange source = LocalDateRange.from(temporal);
        exercise(source);
    }

    @Test
    public void FROM_YEAR_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(
                YearMonth.of(2024, 2));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_HALF_variation1() {
        LocalDateRange source = LocalDateRange.from(
                YearHalf.of(2020, Half.H1));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.from(
                YearQuarter.of(2021, Quarter.Q4));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_WEEK_variation1() {
        LocalDateRange source = LocalDateRange.from(
                YearWeek.of(2020, 53));
        exercise(source);
    }

    @Test
    public void NEGATIVE_PROLEPTIC_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(-44, 2, 1),
                LocalDate.of(-44, 4, 1));
        exercise(source);
    }

    @Test
    public void CROSS_JAVA_EPOCH_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1969, 12, 20),
                LocalDate.of(1970, 1, 10));
        exercise(source);
    }

    @Test
    public void DISTANT_FUTURE_BOUNDED_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(500000, 6, 1),
                LocalDate.of(500001, 8, 17));
        exercise(source);
    }

    @Test
    public void EARLIEST_BOUNDED_START_variation1() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange source = LocalDateRange.of(
                start, start.plusDays(100));
        exercise(source);
    }

    @Test
    public void END_AT_TRANSFORMATION_LIMIT_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays(75), end);
        exercise(source);
    }

    @Test
    public void LENGTH_INTEGER_MAX_MINUS_24_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 24L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void LENGTH_INTEGER_MAX_MINUS_23_variation1() {
        LocalDate start = LocalDate.of(100, 5, 20);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 23L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void LENGTH_EXACT_INTEGER_MAX_variation1() {
        LocalDate start = LocalDate.of(-100, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void LENGTH_INTEGER_MAX_PLUS_ONE_variation1() {
        LocalDate start = LocalDate.of(-200, 7, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE + 1L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void VERY_LARGE_BOUNDED_RANGE_variation1() {
        LocalDate start = LocalDate.of(-10000000, 1, 1);
        LocalDate end = start.plusDays(5_000_000_000L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_ORDINARY_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.of(1980, 4, 17));
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_AT_END_LIMIT_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.MAX.minusDays(23));
        exercise(source);
    }

    @Test
    public void CLOSED_END_REACHES_TRANSFORMATION_LIMIT_variation1() {
        LocalDate inclusiveEnd = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.ofClosed(
                inclusiveEnd.minusDays(40), inclusiveEnd);
        exercise(source);
    }

    @Test
    public void PARSED_LEAP_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.parse(
                "2020-02-25/2020-03-04");
        exercise(source);
    }

    @Test
    public void MULTI_CENTURY_BOUNDED_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1600, 1, 1),
                LocalDate.of(2101, 1, 1));
        exercise(source);
    }

    @Test
    public void END_ON_YEAR_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2022, 11, 15),
                LocalDate.of(2023, 1, 1));
        exercise(source);
    }
}
