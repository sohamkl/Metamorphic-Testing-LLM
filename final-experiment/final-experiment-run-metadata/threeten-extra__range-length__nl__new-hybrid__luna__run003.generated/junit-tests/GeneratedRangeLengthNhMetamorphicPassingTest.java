import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

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

    private static void assertMetamorphicRelation(
            int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput + 23, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(LocalDateRange source) {
        LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_HALF_OPEN_ORDINARY_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 3, 10),
                LocalDate.of(2020, 3, 17));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_HALF_OPEN_EMPTY_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 4, 15),
                LocalDate.of(2020, 4, 15));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_HALF_OPEN_ONE_DAY_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_HALF_OPEN_EXACTLY_23_DAYS_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 2, 1),
                LocalDate.of(2021, 2, 24));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_HALF_OPEN_LEAP_DAY_CROSSING_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 2, 27),
                LocalDate.of(2020, 3, 2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_HALF_OPEN_NON_LEAP_FEBRUARY_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 2, 27),
                LocalDate.of(2021, 3, 2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_HALF_OPEN_YEAR_BOUNDARY_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 12, 30),
                LocalDate.of(2022, 1, 3));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_PERIOD_ZERO_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2022, 5, 10), Period.ZERO);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_PERIOD_ONE_DAY_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2022, 5, 10), Period.ofDays(1));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_PERIOD_MONTH_END_ADJUSTMENT_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 1, 31), Period.ofMonths(1));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_CLOSED_ORDINARY_RANGE_1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2020, 3, 10),
                LocalDate.of(2020, 3, 16));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_CLOSED_SINGLE_DAY_1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2020, 6, 15),
                LocalDate.of(2020, 6, 15));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_EMPTY_FACTORY_1() {
        LocalDateRange source = LocalDateRange.ofEmpty(
                LocalDate.of(2023, 7, 10));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_FROM_LOCAL_DATE_1() {
        LocalDateRange source = LocalDateRange.from(
                LocalDate.of(2022, 8, 10));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_FROM_YEAR_1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2021));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_FROM_YEAR_MONTH_LEAP_FEBRUARY_1() {
        LocalDateRange source = LocalDateRange.from(
                YearMonth.of(2020, 2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_FROM_YEAR_HALF_1() {
        LocalDateRange source = LocalDateRange.from(
                YearHalf.of(2021, 2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_FROM_YEAR_QUARTER_1() {
        LocalDateRange source = LocalDateRange.from(
                YearQuarter.of(2020, 1));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_FROM_YEAR_WEEK_1() {
        LocalDateRange source = LocalDateRange.from(
                YearWeek.of(2021, 10));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_PARSE_DATE_DATE_1() {
        LocalDateRange source = LocalDateRange.parse(
                "2020-03-10/2020-03-17");
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_PARSE_DATE_PERIOD_1() {
        LocalDateRange source = LocalDateRange.parse(
                "2020-02-01/P1M");
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_PARSE_PERIOD_DATE_1() {
        LocalDateRange source = LocalDateRange.parse(
                "P23D/2020-03-24");
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_NEAR_LOCAL_DATE_MINIMUM_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(2),
                LocalDate.MIN.plusDays(3));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_NEAR_LOCAL_DATE_MAXIMUM_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(100),
                LocalDate.MAX.minusDays(24));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void test_LARGE_REPRESENTABLE_FINITE_RANGE_1() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDate start = end.minusDays(2_147_483_624L);
        LocalDateRange source = LocalDateRange.of(start, end);
        assertMetamorphicRelationFor(source);
    }
}
