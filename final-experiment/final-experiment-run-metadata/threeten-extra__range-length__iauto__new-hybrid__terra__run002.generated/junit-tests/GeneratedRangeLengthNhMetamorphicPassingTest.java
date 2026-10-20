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

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        Assertions.assertEquals(23, followUpOutput - sourceOutput);
    }

    @Test
    void EMPTY_RANGE_STANDARD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_RANGE_MINIMUM_PERMITTED_LOCATION_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_DAY_HALF_OPEN_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 11));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_EXTRA_DAYS_LENGTH_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 24));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_CROSSING_DIRECT_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 2));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_BOUNDARY_DIRECT_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 12, 30), LocalDate.of(2024, 1, 3));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_YEAR_DIRECT_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(-1, 12, 30), LocalDate.of(0, 1, 3));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIN_NEIGHBORHOOD_BOUNDED_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(2), LocalDate.MIN.plusDays(4));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAX_MINUS_TWENTY_FOUR_FINITE_FOLLOW_UP_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(25), LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAX_MINUS_TWENTY_THREE_FOLLOW_UP_SENTINEL_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(24), LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAXIMUM_FINITE_RELATION_LENGTH_variation1() {
        LocalDate start = LocalDate.MIN.plusDays(2);
        LocalDate end = start.plusDays(Integer.MAX_VALUE - 23L);
        LocalDateRange source = LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ZERO_CONSTRUCTION_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 5, 20), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ONE_MONTH_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 1), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLETON_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 7, 4), LocalDate.of(2024, 7, 4));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_MONTH_END_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 1, 30), LocalDate.of(2024, 2, 2));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_DATE_FORMAT_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-04-10/2024-04-17");
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_PERIOD_FORMAT_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-31/P1M");
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_PERIOD_DATE_FORMAT_variation1() {
        LocalDateRange source = LocalDateRange.parse("P1W/2024-03-10");
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 8, 12));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_FEBRUARY_YEARMONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_variation1() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_WEEK_variation1() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_FINITE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_MAXIMUM_GENERATABLE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
