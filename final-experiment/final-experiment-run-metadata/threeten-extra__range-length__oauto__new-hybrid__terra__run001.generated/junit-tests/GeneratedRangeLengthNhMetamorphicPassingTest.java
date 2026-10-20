import java.time.LocalDate;
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

    private LocalDateRange generateFollowUp(LocalDateRange range) {
        return LocalDateRange.of(range.getStart(), range.getEnd().plusDays(23));
    }

    @Test
    void EMPTY_RANGE_OF_EMPTY_ordinaryDate() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_RANGE_OF_EQUAL_ENDPOINTS_sameDate() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_RANGE_ZERO_PERIOD_zeroDays() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SINGLE_DAY_HALF_OPEN_adjacentDates() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_CROSSING_februaryToMarch() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COMMON_YEAR_FEBRUARY_CROSSING_februaryToMarch() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 2, 28), LocalDate.of(2023, 3, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_BOUNDARY_HALF_OPEN_newYear() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLE_DATE_oneInclusiveDay() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_LEAP_MONTH_END_inclusiveLeapDay() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 29));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ONE_MONTH_FROM_MONTH_END_leapFebruary() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 31), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ONE_YEAR_FROM_LEAP_DAY_commonYearNormalization() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 29), Period.ofYears(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_TO_DATE_standardRange() {
        LocalDateRange source = LocalDateRange.parse("2024-04-01/2024-04-11");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_TO_PERIOD_monthEnd() {
        LocalDateRange source = LocalDateRange.parse("2024-01-31/P1M");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_PERIOD_TO_DATE_leapMonth() {
        LocalDateRange source = LocalDateRange.parse("P1M/2024-02-29");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_leapDay() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 2, 29));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_YEAR_completeYear() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_COMMON_YEAR_completeYear() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_FEBRUARY_YEAR_MONTH_leapMonth() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_COMMON_FEBRUARY_YEAR_MONTH_commonMonth() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2023, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_firstHalfOfLeapYear() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2024, Half.H1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_firstQuarterOfLeapYear() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, Quarter.Q1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_WEEK_CROSSING_YEAR_isoWeekOne() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2020, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_ORDINARY_END_finiteEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_NEAR_MINIMUM_END_earliestAllowedEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MINIMUM_SIDE_BOUNDED_ONE_DAY_minimumAdjacent() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTEGER_MAX_MINUS_TWENTY_THREE_followUpSaturates() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(Integer.MAX_VALUE - 23L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTEGER_MAX_EXACT_BOUNDED_saturationBoundary() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTEGER_MAX_PLUS_ONE_BOUNDED_beyondIntCapacity() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(((long) Integer.MAX_VALUE) + 1L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void VERY_LARGE_BOUNDED_RANGE_farBeyondIntCapacity() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(((long) Integer.MAX_VALUE) + 1_000_000L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAXIMUM_SIDE_ASSERTED_BOUNDARY_finiteFollowUp() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(47), LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAXIMUM_SIDE_FOLLOW_UP_UNBOUNDED_reachesMaximum() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(24), LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
