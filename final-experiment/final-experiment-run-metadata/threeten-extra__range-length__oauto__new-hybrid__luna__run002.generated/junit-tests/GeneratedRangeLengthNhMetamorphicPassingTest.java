import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static org.threeten.extra.LocalDateRange generateFollowUp(
            org.threeten.extra.LocalDateRange source) {
        return org.threeten.extra.LocalDateRange.of(
                source.getStart(),
                source.getEnd().plusDays(23));
    }

    @Test
    public void test_HALF_OPEN_EMPTY_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(LocalDate.of(2020, 1, 15));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_HALF_OPEN_ONE_DAY_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 1, 2));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_HALF_OPEN_TWENTY_THREE_DAY_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2021, 3, 1),
                        LocalDate.of(2021, 3, 24));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_HALF_OPEN_TWENTY_FOUR_DAY_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2021, 3, 1),
                        LocalDate.of(2021, 3, 25));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_LEAP_DAY_CROSSING_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 2, 28),
                        LocalDate.of(2020, 3, 2));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_NON_LEAP_FEBRUARY_BOUNDARY_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2021, 2, 28),
                        LocalDate.of(2021, 3, 2));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_YEAR_BOUNDARY_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2019, 12, 31),
                        LocalDate.of(2020, 1, 2));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_PERIOD_ZERO_CONSTRUCTION_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2022, 6, 15), Period.ZERO);
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_PERIOD_MONTH_CONSTRUCTION_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2021, 1, 31), Period.ofMonths(1));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_CLOSED_SINGLE_DATE_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(2022, 5, 10),
                        LocalDate.of(2022, 5, 10));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_CLOSED_LEAP_DAY_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(2020, 2, 28),
                        LocalDate.of(2020, 2, 29));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_FROM_LOCAL_DATE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(LocalDate.of(2023, 7, 4));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_FROM_YEAR_COMMON_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(Year.of(2021));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_FROM_YEAR_LEAP_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(Year.of(2020));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_FROM_YEAR_MONTH_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearMonth.of(2021, 4));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_FROM_YEAR_HALF_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearHalf.of(2021, 1));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_FROM_YEAR_QUARTER_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearQuarter.of(2021, 2));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_FROM_YEAR_WEEK_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearWeek.of(2021, 1));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_PARSE_DATE_DATE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2022-01-10/2022-02-10");
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_PARSE_DATE_PERIOD_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2022-01-10/P1M");
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_PARSE_PERIOD_DATE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("P10D/2022-01-20");
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_UNBOUNDED_START_ORDINARY_END_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.of(2020, 1, 1));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_UNBOUNDED_START_NEAR_MINIMUM_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MIN,
                        LocalDate.MIN.plusDays(2));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_MAXIMUM_REPRESENTABLE_FOLLOW_UP_LENGTH_1() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE - 23L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_LARGE_REPRESENTABLE_RANGE_1() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE - 46L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_FOLLOW_UP_BECOMES_UNBOUNDED_1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDate start = end.minusDays(100);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_ORDINARY_MONTH_END_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2022, 1, 25),
                        LocalDate.of(2022, 2, 1));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void test_LONG_ORDINARY_BOUNDED_RANGE_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2010, 1, 1),
                        LocalDate.of(2020, 1, 1));
        org.threeten.extra.LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
