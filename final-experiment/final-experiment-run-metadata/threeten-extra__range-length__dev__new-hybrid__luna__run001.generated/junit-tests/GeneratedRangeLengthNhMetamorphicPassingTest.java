import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static void exercise(org.threeten.extra.LocalDateRange source) {
        int sourceOutput = source.lengthInDays();
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_FINITE_HALF_OPEN_RANGE_1_shortRange() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 1, 15),
                        LocalDate.of(2020, 1, 18));
        exercise(source);
    }

    @Test
    public void ORDINARY_FINITE_HALF_OPEN_RANGE_2_oneDayPeriod() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 2, 28),
                        Period.ofDays(1));
        exercise(source);
    }

    @Test
    public void ORDINARY_FINITE_HALF_OPEN_RANGE_3_closedConversion() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(2023, 12, 30),
                        LocalDate.of(2024, 1, 2));
        exercise(source);
    }

    @Test
    public void EMPTY_RANGE_ZERO_LENGTH_1_emptyFactory() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(
                        LocalDate.of(2020, 12, 31));
        exercise(source);
    }

    @Test
    public void EMPTY_RANGE_ZERO_LENGTH_2_fromLocalDate() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        LocalDate.of(9999, 12, 1));
        exercise(source);
    }

    @Test
    public void ONE_DAY_FINITE_RANGE_1_fromYear() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(Year.of(2020));
        exercise(source);
    }

    @Test
    public void ONE_DAY_FINITE_RANGE_2_fromYearMonth() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        YearMonth.of(2021, 11));
        exercise(source);
    }

    @Test
    public void LEAP_DAY_CROSSING_RANGE_1_fromYearHalf() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        YearHalf.of(2024, 1));
        exercise(source);
    }

    @Test
    public void LEAP_DAY_CROSSING_RANGE_2_fromYearQuarter() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        YearQuarter.of(2024, 1));
        exercise(source);
    }

    @Test
    public void MONTH_AND_YEAR_BOUNDARY_RANGE_1_fromYearWeek() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        YearWeek.of(2020, 1));
        exercise(source);
    }

    @Test
    public void MONTH_AND_YEAR_BOUNDARY_RANGE_2_parsedDateDate() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse(
                        "2023-12-20/2024-01-10");
        exercise(source);
    }

    @Test
    public void PERIOD_ZERO_CONSTRUCTION_1_parsedDatePeriod() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse(
                        "2021-12-31/P0D");
        exercise(source);
    }

    @Test
    public void PERIOD_DAY_CONSTRUCTION_1_parsedPeriodDate() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse(
                        "P7D/2022-03-15");
        exercise(source);
    }

    @Test
    public void PERIOD_DAY_CONSTRUCTION_2_unboundedStart() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.of(1970, 1, 2));
        exercise(source);
    }

    @Test
    public void PERIOD_MONTH_OR_YEAR_CONSTRUCTION_1_dateRange() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2018, 1, 31),
                        LocalDate.of(2019, 2, 1));
        exercise(source);
    }

    @Test
    public void PERIOD_MONTH_OR_YEAR_CONSTRUCTION_2_monthPeriod() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 1, 31),
                        Period.ofMonths(2));
        exercise(source);
    }

    @Test
    public void CLOSED_RANGE_CONVERSION_1_reachesMaximum() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(2020, 6, 1),
                        LocalDate.of(2020, 6, 30));
        exercise(source);
    }

    @Test
    public void CLOSED_RANGE_CONVERSION_2_longClosedRange() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(1900, 1, 1),
                        LocalDate.of(2000, 12, 31));
        exercise(source);
    }

    @Test
    public void PARSED_DATE_DATE_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse(
                        "2020-02-28/2020-03-05");
        exercise(source);
    }

    @Test
    public void PARSED_DATE_PERIOD_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse(
                        "2020-12-31/P40D");
        exercise(source);
    }

    @Test
    public void PARSED_PERIOD_DATE_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse(
                        "P1M/2021-03-31");
        exercise(source);
    }

    @Test
    public void FROM_LOCAL_DATE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        LocalDate.of(2024, 2, 28));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        Year.of(2024));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_MONTH_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        YearMonth.of(2024, 2));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_HALF_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        YearHalf.of(2024, 2));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_QUARTER_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        YearQuarter.of(2023, 4));
        exercise(source);
    }

    @Test
    public void FROM_YEAR_WEEK_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        YearWeek.of(2023, 52));
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_SENTINEL_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.of(2024, 2, 29));
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_SENTINEL_2_minimumStart() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MIN,
                        LocalDate.of(1970, 1, 1));
        exercise(source);
    }

    @Test
    public void START_AT_MIN_PLUS_ONE_1() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        start,
                        start.plusDays(2));
        exercise(source);
    }

    @Test
    public void NEAR_MAX_FINITE_END_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MAX.minusDays(40),
                        LocalDate.MAX.minusDays(24));
        exercise(source);
    }

    @Test
    public void FOLLOWUP_REACHES_MAX_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MAX.minusDays(40),
                        LocalDate.MAX.minusDays(23));
        exercise(source);
    }

    @Test
    public void VERY_LONG_FINITE_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(-500000000, 1, 1),
                        LocalDate.of(1970, 1, 1));
        exercise(source);
    }

    @Test
    public void VERY_LONG_FINITE_RANGE_2() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(-999999999, 1, 1),
                        LocalDate.of(2000, 1, 1));
        exercise(source);
    }

    @Test
    public void LENGTH_INT_BOUNDARY_1_belowMaximum() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        start,
                        start.plusDays((long) Integer.MAX_VALUE - 24));
        exercise(source);
    }

    @Test
    public void LENGTH_INT_BOUNDARY_2_atMaximum() {
        LocalDate start = LocalDate.of(1970, 1, 2);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        start,
                        start.plusDays((long) Integer.MAX_VALUE));
        exercise(source);
    }

    @Test
    public void LENGTH_INT_BOUNDARY_3_aboveMaximum() {
        LocalDate start = LocalDate.of(1970, 1, 3);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        start,
                        start.plusDays((long) Integer.MAX_VALUE + 24));
        exercise(source);
    }
}
