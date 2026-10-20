import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private static LocalDateRange generateFollowUp(LocalDateRange source) {
        LocalDate extendedEnd = source.getEnd().plusDays(23);
        return LocalDateRange.of(source.getStart(), extendedEnd);
    }

    @Test
    public void ORDINARY_EMPTY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 5, 10));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_ALLOWED_EMPTY_RANGE_variation1() {
        LocalDate date = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.ofEmpty(date);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_JUST_BELOW_EXTRA_DAYS_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 15);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(22));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EQUAL_TO_EXTRA_DAYS_variation1() {
        LocalDate start = LocalDate.of(1900, 2, 15);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_JUST_ABOVE_EXTRA_DAYS_variation1() {
        LocalDate start = LocalDate.of(2000, 2, 20);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CROSS_LEAP_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 28),
                LocalDate.of(2024, 3, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_LEAP_CENTURY_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1900, 2, 28),
                LocalDate.of(1900, 3, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_CENTURY_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2000, 2, 28),
                LocalDate.of(2000, 3, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SHORT_MONTH_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 4, 29),
                LocalDate.of(2023, 5, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 12, 30),
                LocalDate.of(2024, 1, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EPOCH_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1969, 12, 31),
                LocalDate.of(1970, 1, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROLEPTIC_YEAR_ZERO_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(-1, 12, 31),
                LocalDate.of(0, 1, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_PERIOD_CONSTRUCTION_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 7, 1),
                Period.ZERO);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DAY_PERIOD_CONSTRUCTION_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 7, 1),
                Period.ofDays(10));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_PERIOD_FROM_MONTH_END_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 31),
                Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_PERIOD_ACROSS_LEAP_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 29),
                Period.ofYears(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_SINGLETON_RANGE_variation1() {
        LocalDate date = LocalDate.of(2024, 8, 8);
        LocalDateRange source = LocalDateRange.ofClosed(date, date);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_MULTI_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 8, 8),
                LocalDate.of(2024, 8, 12));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_RANGE_AT_MAXIMUM_TRANSFORMABLE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.MAX.minusDays(25),
                LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_DATE_FORM_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-03-01/2024-03-11");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_PERIOD_FORM_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-03-01/P10D");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_PERIOD_DATE_FORM_variation1() {
        LocalDateRange source = LocalDateRange.parse("P10D/2024-03-11");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_LOWERCASE_PERIOD_DISCRIMINATOR_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-04-01/p5d");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LOCAL_DATE_TEMPORAL_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 9, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_DATE_BEARING_TIME_TEMPORAL_variation1() {
        LocalDateTime temporal = LocalDateTime.of(2024, 9, 15, 13, 45, 30);
        LocalDateRange source = LocalDateRange.from(temporal);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LEAP_FEBRUARY_YEARMONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_COMMON_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_HALF_variation1() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2024, Half.H1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, Quarter.Q1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_WEEK_CROSSING_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2020, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MINIMUM_UNBOUNDED_START_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_UNBOUNDED_START_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_AT_TRANSFORMATION_LIMIT_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_BOUNDED_ONE_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(1),
                LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOURCE_END_AT_MAXIMUM_TRANSFORMABLE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(24),
                LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOURCE_END_ONE_DAY_BEFORE_TRANSFORMATION_LIMIT_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(25),
                LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_BELOW_INT_SENTINEL_THRESHOLD_variation1() {
        long length = ((long) Integer.MAX_VALUE) - 24L;
        LocalDate end = LocalDate.of(5_000_000, 1, 1);
        LocalDateRange source = LocalDateRange.of(end.minusDays(length), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FOLLOW_UP_REACHES_INT_MAX_EXACTLY_variation1() {
        long length = ((long) Integer.MAX_VALUE) - 23L;
        LocalDate end = LocalDate.of(5_100_000, 6, 15);
        LocalDateRange source = LocalDateRange.of(end.minusDays(length), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FOLLOW_UP_EXCEEDS_INT_MAX_BY_ONE_variation1() {
        long length = ((long) Integer.MAX_VALUE) - 22L;
        LocalDate end = LocalDate.of(5_200_000, 12, 1);
        LocalDateRange source = LocalDateRange.of(end.minusDays(length), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FINITE_LENGTH_EXACTLY_INT_MAX_variation1() {
        long length = Integer.MAX_VALUE;
        LocalDate end = LocalDate.of(5_300_000, 3, 10);
        LocalDateRange source = LocalDateRange.of(end.minusDays(length), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FINITE_LENGTH_JUST_ABOVE_INT_MAX_variation1() {
        long length = ((long) Integer.MAX_VALUE) + 1L;
        LocalDate end = LocalDate.of(5_400_000, 7, 20);
        LocalDateRange source = LocalDateRange.of(end.minusDays(length), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void VERY_LARGE_FINITE_RANGE_variation1() {
        long length = 3_000_000_000L;
        LocalDate end = LocalDate.of(6_000_000, 1, 1);
        LocalDateRange source = LocalDateRange.of(end.minusDays(length), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_CENTURY_FINITE_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1600, 1, 1),
                LocalDate.of(2400, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
