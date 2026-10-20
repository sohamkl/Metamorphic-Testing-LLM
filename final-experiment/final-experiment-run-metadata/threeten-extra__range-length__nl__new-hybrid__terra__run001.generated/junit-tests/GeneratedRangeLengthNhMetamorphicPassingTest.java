import static org.junit.jupiter.api.Assertions.assertEquals;

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

    private LocalDateRange generateFollowUp(LocalDateRange source) {
        return LocalDateRange.of(source.getStart(), source.getEnd().plusDays(23));
    }

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        assertEquals(sourceOutput + 23, followUpOutput);
    }

    private void assertMetamorphicRelationFor(LocalDateRange source) {
        LocalDateRange followUp = generateFollowUp(source);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_RANGE_DIRECT_FACTORY_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 1)));
    }

    @Test
    void ONE_DAY_HALF_OPEN_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 2)));
    }

    @Test
    void TWO_DAY_HALF_OPEN_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 3)));
    }

    @Test
    void LENGTH_TWENTY_TWO_BELOW_DELTA_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 23)));
    }

    @Test
    void LENGTH_TWENTY_THREE_EQUALS_DELTA_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 24)));
    }

    @Test
    void LENGTH_TWENTY_FOUR_ABOVE_DELTA_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 25)));
    }

    @Test
    void LOWER_DATE_LIMIT_FINITE_RANGE_variation1() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        assertMetamorphicRelationFor(LocalDateRange.of(start, start.plusDays(1)));
    }

    @Test
    void UPPER_DATE_LIMIT_FOLLOW_UP_BOUNDARY_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        assertMetamorphicRelationFor(LocalDateRange.of(end.minusDays(1), end));
    }

    @Test
    void MAXIMUM_SAFE_INT_SOURCE_LENGTH_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 23L);
        assertMetamorphicRelationFor(LocalDateRange.of(start, end));
    }

    @Test
    void EPOCH_CROSSING_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(1969, 12, 31), LocalDate.of(1970, 1, 2)));
    }

    @Test
    void BCE_TO_CE_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(-1, 12, 31), LocalDate.of(0, 1, 2)));
    }

    @Test
    void COMMON_YEAR_FEBRUARY_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2019, 2, 1), LocalDate.of(2019, 3, 1)));
    }

    @Test
    void LEAP_YEAR_FEBRUARY_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2020, 2, 1), LocalDate.of(2020, 3, 1)));
    }

    @Test
    void LEAP_DAY_CROSSING_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2020, 2, 28), LocalDate.of(2020, 3, 2)));
    }

    @Test
    void CENTURY_COMMON_YEAR_FEBRUARY_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(1900, 2, 1), LocalDate.of(1900, 3, 1)));
    }

    @Test
    void MONTH_END_TO_MONTH_END_31_DAY_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2021, 1, 31), LocalDate.of(2021, 3, 3)));
    }

    @Test
    void YEAR_BOUNDARY_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2020, 12, 31), LocalDate.of(2021, 1, 2)));
    }

    @Test
    void PERIOD_ZERO_EMPTY_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2000, 1, 1), Period.ZERO));
    }

    @Test
    void PERIOD_TWENTY_THREE_DAYS_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2000, 1, 1), Period.ofDays(23)));
    }

    @Test
    void PERIOD_ONE_MONTH_COMMON_FEBRUARY_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2019, 1, 31), Period.ofMonths(1)));
    }

    @Test
    void PERIOD_ONE_MONTH_LEAP_FEBRUARY_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2020, 1, 31), Period.ofMonths(1)));
    }

    @Test
    void PERIOD_ONE_YEAR_ACROSS_LEAP_DAY_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.of(
                LocalDate.of(2020, 2, 29), Period.ofYears(1)));
    }

    @Test
    void CLOSED_SINGLE_DAY_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.ofClosed(
                LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 1)));
    }

    @Test
    void CLOSED_MONTH_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.ofClosed(
                LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 30)));
    }

    @Test
    void EMPTY_FACTORY_RANGE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.ofEmpty(LocalDate.of(2000, 6, 15)));
    }

    @Test
    void PARSE_DATE_TO_DATE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.parse("2007-12-03/2007-12-04"));
    }

    @Test
    void PARSE_DATE_TO_PERIOD_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.parse("2020-02-01/P1M"));
    }

    @Test
    void PARSE_PERIOD_TO_DATE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.parse("P1M/2020-03-01"));
    }

    @Test
    void FROM_LOCAL_DATE_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(LocalDate.of(2020, 6, 15)));
    }

    @Test
    void FROM_LOCAL_DATE_TIME_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(
                LocalDateTime.of(2020, 6, 15, 23, 59, 59)));
    }

    @Test
    void FROM_COMMON_YEAR_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(Year.of(2019)));
    }

    @Test
    void FROM_LEAP_YEAR_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(Year.of(2020)));
    }

    @Test
    void FROM_COMMON_YEAR_FEBRUARY_MONTH_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(YearMonth.of(2019, 2)));
    }

    @Test
    void FROM_LEAP_YEAR_FEBRUARY_MONTH_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(YearMonth.of(2020, 2)));
    }

    @Test
    void FROM_YEAR_HALF_FIRST_HALF_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(YearHalf.of(2019, Half.H1)));
    }

    @Test
    void FROM_YEAR_HALF_SECOND_HALF_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(YearHalf.of(2019, Half.H2)));
    }

    @Test
    void FROM_YEAR_QUARTER_LEAP_FIRST_QUARTER_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(YearQuarter.of(2020, Quarter.Q1)));
    }

    @Test
    void FROM_YEAR_QUARTER_COMMON_SECOND_QUARTER_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(YearQuarter.of(2019, Quarter.Q2)));
    }

    @Test
    void FROM_YEAR_WEEK_variation1() {
        assertMetamorphicRelationFor(LocalDateRange.from(YearWeek.of(2019, 1)));
    }
}
