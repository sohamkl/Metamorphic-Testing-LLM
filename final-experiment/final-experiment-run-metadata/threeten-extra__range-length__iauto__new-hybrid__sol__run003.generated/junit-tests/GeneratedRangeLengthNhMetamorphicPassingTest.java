import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        if (followUpOutput - sourceOutput != 23) {
            throw new AssertionError(
                    "Expected the follow-up length to be exactly 23 days greater, but source was "
                            + sourceOutput + " and follow-up was " + followUpOutput);
        }
    }

    @Test
    public void EMPTY_ORDINARY_DATE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_TWENTY_TWO_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(22));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EXACTLY_TWENTY_THREE_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_TWENTY_FOUR_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_FEBRUARY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 2, 28), LocalDate.of(2020, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_FEBRUARY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 2, 28), LocalDate.of(2021, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_END_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EPOCH_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1969, 12, 31), LocalDate.of(1970, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROLEPTIC_YEAR_ZERO_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(-1, 12, 31), LocalDate.of(0, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_BOUNDED_ONE_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_VALID_EMPTY_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_ORDINARY_END_variation1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_MINIMUM_END_variation1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void END_EXACTLY_MAX_MINUS_23_variation1() {
        LocalDate boundary = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.ofEmpty(boundary);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void END_EXACTLY_MAX_MINUS_24_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(25), LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UPPER_CHRONOLOGY_FINITE_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(400), LocalDate.MAX.minusDays(300));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_INT_MAX_MINUS_24_variation1() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(2147483623L));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_INT_MAX_MINUS_23_variation1() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(2147483624L));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_INT_MAX_MINUS_22_variation1() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(2147483625L));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FINITE_LENGTH_EQUALS_SENTINEL_variation1() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(2147483647L));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FINITE_LENGTH_INT_MAX_PLUS_ONE_variation1() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(2147483648L));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FINITE_LENGTH_TWO_TO_32_variation1() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(4294967296L));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FINITE_LENGTH_TWO_TO_32_PLUS_ONE_variation1() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(4294967297L));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_SINGLE_DATE_variation1() {
        LocalDate date = LocalDate.of(2024, 5, 10);
        LocalDateRange source = LocalDateRange.ofClosed(date, date);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_LEAP_MONTH_END_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2020, 1, 31), LocalDate.of(2020, 2, 29));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_COMMON_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2021, 1, 1), LocalDate.of(2021, 12, 31));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 31));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_UPPER_TRANSFORMATION_BOUNDARY_variation1() {
        LocalDate date = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.ofClosed(date, date);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PERIOD_ZERO_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2024, 4, 10), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PERIOD_EXACTLY_23_DAYS_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 4, 10), Period.ofDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PERIOD_ONE_MONTH_FROM_LEAP_JANUARY_END_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 31), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PERIOD_ONE_YEAR_FROM_LEAP_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 2, 29), Period.ofYears(1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PERIOD_COMPOSITE_MONTH_AND_DAYS_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 1, 30), Period.of(0, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PERIOD_FROM_MIN_TWO_DAYS_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.MIN, Period.ofDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source =
                LocalDateRange.from(LocalDate.of(2024, 7, 8));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LOCAL_DATE_TIME_variation1() {
        LocalDateRange source =
                LocalDateRange.from(LocalDateTime.of(2024, 7, 8, 23, 59));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LEAP_FEBRUARY_YEARMONTH_variation1() {
        LocalDateRange source =
                LocalDateRange.from(YearMonth.of(2020, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_COMMON_FEBRUARY_YEARMONTH_variation1() {
        LocalDateRange source =
                LocalDateRange.from(YearMonth.of(2021, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_COMMON_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2021));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2020));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_DATE_EMPTY_variation1() {
        LocalDateRange source =
                LocalDateRange.parse("2024-06-15/2024-06-15");
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_DATE_NONEMPTY_variation1() {
        LocalDateRange source =
                LocalDateRange.parse("2024-06-01/2024-06-24");
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.parse("2020-01-31/P1M");
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_PERIOD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("P1M/2020-03-31");
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_NEGATIVE_YEAR_DATES_variation1() {
        LocalDateRange source =
                LocalDateRange.parse("-0001-12-31/0000-01-02");
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
