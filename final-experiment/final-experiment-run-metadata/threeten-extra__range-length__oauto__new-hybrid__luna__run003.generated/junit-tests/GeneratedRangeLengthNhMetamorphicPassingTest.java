import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static LocalDateRange generateFollowUp(LocalDateRange source) {
        return LocalDateRange.of(source.getStart(), source.getEnd().plusDays(23));
    }

    private static void verifyRelation(LocalDateRange source) {
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_RANGE_ZERO_LENGTH_1() {
        LocalDateRange source =
                LocalDateRange.ofEmpty(LocalDate.of(2020, 1, 15));
        verifyRelation(source);
    }

    @Test
    void ONE_DAY_HALF_OPEN_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 1, 2));
        verifyRelation(source);
    }

    @Test
    void TWENTY_TWO_DAY_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 1, 23));
        verifyRelation(source);
    }

    @Test
    void TWENTY_THREE_DAY_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 1, 24));
        verifyRelation(source);
    }

    @Test
    void TWENTY_FOUR_DAY_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 1, 25));
        verifyRelation(source);
    }

    @Test
    void MONTH_BOUNDARY_CROSSING_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2021, 1, 25),
                        LocalDate.of(2021, 1, 31));
        verifyRelation(source);
    }

    @Test
    void NON_LEAP_FEBRUARY_CROSSING_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2021, 2, 20),
                        LocalDate.of(2021, 3, 5));
        verifyRelation(source);
    }

    @Test
    void LEAP_DAY_CROSSING_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2020, 2, 27),
                        LocalDate.of(2020, 3, 3));
        verifyRelation(source);
    }

    @Test
    void YEAR_BOUNDARY_CROSSING_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2020, 12, 20),
                        LocalDate.of(2021, 1, 10));
        verifyRelation(source);
    }

    @Test
    void LONG_FINITE_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2010, 1, 1),
                        LocalDate.of(2020, 1, 1));
        verifyRelation(source);
    }

    @Test
    void CLOSED_SINGLE_DATE_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.ofClosed(
                        LocalDate.of(2020, 5, 10),
                        LocalDate.of(2020, 5, 10));
        verifyRelation(source);
    }

    @Test
    void CLOSED_MULTI_DAY_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.ofClosed(
                        LocalDate.of(2020, 5, 10),
                        LocalDate.of(2020, 5, 20));
        verifyRelation(source);
    }

    @Test
    void ZERO_PERIOD_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2020, 6, 1),
                        Period.ZERO);
        verifyRelation(source);
    }

    @Test
    void DAY_PERIOD_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2020, 6, 1),
                        Period.ofDays(1));
        verifyRelation(source);
    }

    @Test
    void MONTH_PERIOD_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2020, 1, 31),
                        Period.ofMonths(1));
        verifyRelation(source);
    }

    @Test
    void UNBOUNDED_START_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.of(2020, 1, 1));
        verifyRelation(source);
    }

    @Test
    void LOCAL_DATE_FROM_TEMPORAL_1() {
        LocalDateRange source =
                LocalDateRange.from(LocalDate.of(2020, 7, 15));
        verifyRelation(source);
    }

    @Test
    void YEAR_FROM_TEMPORAL_1() {
        LocalDateRange source =
                LocalDateRange.from(Year.of(2020));
        verifyRelation(source);
    }

    @Test
    void YEAR_MONTH_FROM_TEMPORAL_1() {
        LocalDateRange source =
                LocalDateRange.from(YearMonth.of(2020, 2));
        verifyRelation(source);
    }

    @Test
    void YEAR_HALF_FROM_TEMPORAL_1() {
        LocalDateRange source =
                LocalDateRange.from(YearHalf.of(2020, 1));
        verifyRelation(source);
    }

    @Test
    void YEAR_QUARTER_FROM_TEMPORAL_1() {
        LocalDateRange source =
                LocalDateRange.from(YearQuarter.of(2020, 1));
        verifyRelation(source);
    }

    @Test
    void YEAR_WEEK_FROM_TEMPORAL_1() {
        LocalDateRange source =
                LocalDateRange.from(YearWeek.of(2020, 1));
        verifyRelation(source);
    }

    @Test
    void PARSE_DATE_DATE_1() {
        LocalDateRange source =
                LocalDateRange.parse("2020-01-01/2020-01-10");
        verifyRelation(source);
    }

    @Test
    void PARSE_DATE_PERIOD_1() {
        LocalDateRange source =
                LocalDateRange.parse("2020-01-01/P10D");
        verifyRelation(source);
    }

    @Test
    void PARSE_PERIOD_DATE_1() {
        LocalDateRange source =
                LocalDateRange.parse("P10D/2020-01-11");
        verifyRelation(source);
    }

    @Test
    void MAX_INT_MINUS_EXTENSION_LENGTH_1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDate end = start.plusDays(2_147_483_624L);
        LocalDateRange source = LocalDateRange.of(start, end);
        verifyRelation(source);
    }

    @Test
    void LARGE_FINITE_RANGE_NEAR_INT_LIMIT_1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDate end = start.plusDays(2_147_483_645L);
        LocalDateRange source = LocalDateRange.of(start, end);
        verifyRelation(source);
    }

    @Test
    void GREATER_THAN_INT_MAX_FINITE_RANGE_1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDate end = start.plusDays(2_147_483_648L);
        LocalDateRange source = LocalDateRange.of(start, end);
        verifyRelation(source);
    }

    @Test
    void FOLLOW_UP_REACHES_LOCAL_DATE_MAX_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2000, 1, 1),
                        LocalDate.MAX.minusDays(23));
        verifyRelation(source);
    }

    @Test
    void EARLIEST_PERMITTED_UNBOUNDED_START_END_1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        verifyRelation(source);
    }

    @Test
    void LOCAL_DATE_MIN_SENTINEL_START_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.MIN,
                        LocalDate.of(1970, 1, 1));
        verifyRelation(source);
    }

    @Test
    void FAR_FUTURE_FINITE_END_1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.of(2000, 1, 1),
                        LocalDate.MAX.minusDays(24));
        verifyRelation(source);
    }
}
