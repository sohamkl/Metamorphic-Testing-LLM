import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthMetamorphicPassingTest {

    private static void exercise(LocalDateRange source) {
        int sourceOutput = source.lengthInDays();
        try {
            Object[] followUpArguments =
                    RangeLengthMetamorphicSpec.generateFollowUp(source);
            LocalDateRange followUp = (LocalDateRange) followUpArguments[0];
            int followUpOutput = followUp.lengthInDays();
            RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
        } catch (DateTimeException ex) {
        }
    }

    @Test
    public void EMPTY_MID_TIMELINE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 6, 10),
                LocalDate.of(2024, 6, 10));
        exercise(source);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        LocalDateRange source = LocalDateRange.of(start, Period.ofDays(1));
        exercise(source);
    }

    @Test
    public void EXACTLY_TWENTY_THREE_DAYS_variation1() {
        LocalDate start = LocalDate.of(2023, 12, 20);
        LocalDateRange source = LocalDateRange.ofClosed(
                start,
                start.plusDays(22));
        exercise(source);
    }

    @Test
    public void TWENTY_FOUR_DAYS_variation1() {
        LocalDate start = LocalDate.of(2024, 2, 27);
        LocalDateRange source = LocalDateRange.of(
                start,
                start.plusDays(24));
        exercise(source);
    }

    @Test
    public void LEAP_DAY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 2, 27),
                LocalDate.of(2020, 3, 5));
        exercise(source);
    }

    @Test
    public void MONTH_AND_YEAR_BOUNDARIES_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 11, 25),
                LocalDate.of(2024, 1, 10));
        exercise(source);
    }

    @Test
    public void MONTH_AND_YEAR_BOUNDARIES_variation2() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 2, 20),
                LocalDate.of(2024, 3, 15));
        exercise(source);
    }

    @Test
    public void CLOSED_SINGLE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 4, 30),
                LocalDate.of(2024, 4, 30));
        exercise(source);
    }

    @Test
    public void CLOSED_MULTI_DAY_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        exercise(source);
    }

    @Test
    public void PERIOD_ZERO_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 15),
                Period.ZERO);
        exercise(source);
    }

    @Test
    public void PERIOD_CALENDAR_DURATION_variation1() {
        LocalDateRange source = LocalDateRange.from(
                YearHalf.of(2024, 1));
        exercise(source);
    }

    @Test
    public void PERIOD_CALENDAR_DURATION_variation2() {
        LocalDateRange source = LocalDateRange.from(
                YearQuarter.of(2024, 2));
        exercise(source);
    }

    @Test
    public void PARSE_DATE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse(
                "2024-02-20/2024-03-05");
        exercise(source);
    }

    @Test
    public void PARSE_DATE_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.parse(
                "2024-01-15/P24D");
        exercise(source);
    }

    @Test
    public void PARSE_PERIOD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse(
                "P23D/2024-02-15");
        exercise(source);
    }

    @Test
    public void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(
                LocalDate.of(2024, 5, 31));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2020));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(
                YearMonth.of(2024, 2));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_HALF_variation1() {
        LocalDateRange source = LocalDateRange.from(
                YearHalf.of(2023, 2));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.from(
                YearQuarter.of(2024, 1));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_WEEK_variation1() {
        LocalDateRange source = LocalDateRange.from(
                YearWeek.of(2024, 10));
        exercise(source);
    }

    @Test
    public void NEAR_MINIMUM_UNBOUNDED_START_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.MIN.plusDays(2));
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_FINITE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.of(2024, 10, 1));
        exercise(source);
    }

    @Test
    public void FULLY_UNBOUNDED_variation1() {
        LocalDateRange source = LocalDateRange.ofUnbounded();
        exercise(source);
    }

    @Test
    public void UNBOUNDED_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedEnd(
                LocalDate.of(2024, 10, 1));
        exercise(source);
    }

    @Test
    public void FOLLOW_UP_BECOMES_UNBOUNDED_END_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays(100),
                end);
        exercise(source);
    }

    @Test
    public void FOLLOW_UP_END_OVERFLOW_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(10);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays(100),
                end);
        exercise(source);
    }

    @Test
    public void MAXIMUM_FINITE_INT_FOLLOW_UP_variation1() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 23L);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void FINITE_INT_MAXIMUM_LENGTH_variation1() {
        LocalDate start = LocalDate.of(1900, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE);
        LocalDateRange source = LocalDateRange.of(start, end);
        exercise(source);
    }

    @Test
    public void FINITE_LENGTH_EXCEEDS_INT_variation1() {
        LocalDateRange source = LocalDateRange.parse(
                LocalDate.MIN + "/1970-01-01");
        exercise(source);
    }

    @Test
    public void ORDINARY_LONG_FINITE_RANGE_variation1() {
        LocalDate start = LocalDate.of(1900, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                start,
                start.plusDays(1_000_000L));
        exercise(source);
    }

    @Test
    public void ORDINARY_LONG_FINITE_RANGE_variation2() {
        LocalDate end = LocalDate.of(2100, 1, 1);
        LocalDateRange source = LocalDateRange.parse(
                "P100000D/" + end);
        exercise(source);
    }
}
