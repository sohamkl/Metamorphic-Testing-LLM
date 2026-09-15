import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

import java.time.LocalDate;
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
    public void ORDINARY_MULTI_DAY_HALF_OPEN_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 10),
                LocalDate.of(2020, 2, 15));
        exercise(source);
    }

    @Test
    public void ORDINARY_ONE_DAY_HALF_OPEN_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 10),
                Period.ofDays(1));
        exercise(source);
    }

    @Test
    public void ORDINARY_EMPTY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2020, 1, 10));
        exercise(source);
    }

    @Test
    public void PERIOD_ZERO_EMPTY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 10),
                Period.ZERO);
        exercise(source);
    }

    @Test
    public void PERIOD_POSITIVE_DAYS_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 10),
                Period.ofDays(40));
        exercise(source);
    }

    @Test
    public void PERIOD_MONTH_ACROSS_LEAP_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 31),
                Period.ofMonths(1));
        exercise(source);
    }

    @Test
    public void CLOSED_SINGLE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2020, 1, 10),
                LocalDate.of(2020, 1, 10));
        exercise(source);
    }

    @Test
    public void CLOSED_LEAP_DAY_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2020, 2, 28),
                LocalDate.of(2020, 2, 29));
        exercise(source);
    }

    @Test
    public void PARSE_DATE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("2021-03-01/2021-03-15");
        exercise(source);
    }

    @Test
    public void PARSE_DATE_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.parse("2021-03-01/P10D");
        exercise(source);
    }

    @Test
    public void PARSE_PERIOD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("P10D/2021-03-11");
        exercise(source);
    }

    @Test
    public void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2021, 3, 1));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2020));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2020, 2));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_HALF_variation1() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2020, 1));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2020, 2));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_WEEK_variation1() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2020, 10));
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_FACTORY_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2020, 1, 10));
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_WITH_MINIMUM_VALID_END_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN,
                LocalDate.MIN.plusDays(2));
        exercise(source);
    }

    @Test
    public void MINIMUM_FINITE_ONE_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(2),
                Period.ofDays(1));
        exercise(source);
    }

    @Test
    public void MINIMUM_FINITE_EMPTY_ALLOWED_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.MIN.plusDays(2));
        exercise(source);
    }

    @Test
    public void FOLLOW_UP_END_BECOMES_MAX_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.of(end.minusDays(1), end);
        exercise(source);
    }

    @Test
    public void NEAR_MAX_FINITE_FOLLOW_UP_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(25),
                LocalDate.MAX.minusDays(24));
        exercise(source);
    }

    @Test
    public void FINITE_LENGTH_JUST_BELOW_INT_MAX_variation1() {
        LocalDate end = LocalDate.ofEpochDay(0);
        LocalDate start = LocalDate.ofEpochDay(-2147483624L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void FINITE_LENGTH_EXACTLY_INT_MAX_variation1() {
        LocalDate end = LocalDate.ofEpochDay(0);
        LocalDate start = LocalDate.ofEpochDay(-2147483647L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void FINITE_LENGTH_JUST_ABOVE_INT_MAX_variation1() {
        LocalDate end = LocalDate.ofEpochDay(0);
        LocalDate start = LocalDate.ofEpochDay(-2147483648L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void FINITE_LENGTH_EXACT_INT_WRAP_variation1() {
        LocalDate end = LocalDate.ofEpochDay(0);
        LocalDate start = LocalDate.ofEpochDay(-4294967296L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void FINITE_LENGTH_BEYOND_INT_WRAP_variation1() {
        LocalDate end = LocalDate.ofEpochDay(0);
        LocalDate start = LocalDate.ofEpochDay(-4294967297L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void PARSE_EMPTY_DATE_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.parse("2021-03-01/2021-03-01");
        exercise(source);
    }

    @Test
    public void ORDINARY_RANGE_STARTING_AT_LOCAL_DATE_MIN_PLUS_ONE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(1),
                LocalDate.MIN.plusDays(2));
        exercise(source);
    }

    @Test
    public void CLOSED_RANGE_NEAR_FOLLOW_UP_LIMIT_variation1() {
        LocalDate inclusiveEnd = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.ofClosed(
                inclusiveEnd.minusDays(1),
                inclusiveEnd);
        exercise(source);
    }

    @Test
    public void FINITE_RANGE_WITH_ZERO_NARROWED_OUTPUT_variation1() {
        LocalDate end = LocalDate.ofEpochDay(100);
        LocalDate start = LocalDate.ofEpochDay(-4294967196L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void FINITE_RANGE_WITH_NEGATIVE_NARROWED_OUTPUT_variation1() {
        LocalDate end = LocalDate.ofEpochDay(0);
        LocalDate start = LocalDate.ofEpochDay(-2147483649L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }
}
