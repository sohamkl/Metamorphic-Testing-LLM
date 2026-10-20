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

public class GeneratedRangeLengthMetamorphicPassingTest {

    @Test
    void ORDINARY_HALF_OPEN_RANGE_standardDates() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 10), LocalDate.of(2024, 6, 20));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_HALF_OPEN_RANGE_smallestNonEmpty() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_OF_EMPTY_FACTORY_permittedDate() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_HALF_OPEN_OF_FACTORY_equalBounds() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 5, 15), LocalDate.of(2024, 5, 15));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MONTH_END_CROSSING_januaryToFebruary() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 1, 30), LocalDate.of(2023, 2, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COMMON_YEAR_FEBRUARY_nonLeapMonth() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_YEAR_FEBRUARY_leapMonth() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 1), LocalDate.of(2024, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_BOUNDARY_RANGE_decemberToJanuary() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_ZERO_RANGE_prolepticBoundary() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(-1, 12, 31), LocalDate.of(0, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOWEST_FINITE_ONE_DAY_RANGE_aboveMinBoundary() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UPPER_FINITE_RANGE_WITH_ASSERTED_FOLLOW_UP_beforeMaxBoundary() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(25), LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FOLLOW_UP_BECOMES_UNBOUNDED_END_exactMaxExtension() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(24), LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_FINITE_END_finiteEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2000, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INT_MAX_MINUS_24_belowSaturation() {
        LocalDate anchor = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(anchor, anchor.plusDays(Integer.MAX_VALUE - 24L));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INT_MAX_MINUS_23_reachesSaturation() {
        LocalDate anchor = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(anchor, anchor.plusDays(Integer.MAX_VALUE - 23L));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INT_MAX_MINUS_22_exceedsSaturationAfterExtension() {
        LocalDate anchor = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(anchor, anchor.plusDays(Integer.MAX_VALUE - 22L));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_EXACTLY_INT_MAX_boundedSentinelValue() {
        LocalDate anchor = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(anchor, anchor.plusDays(Integer.MAX_VALUE));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_GREATER_THAN_INT_MAX_saturatedBoundedRange() {
        LocalDate anchor = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(anchor, anchor.plusDays(((long) Integer.MAX_VALUE) + 1L));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ZERO_emptyPeriodFactory() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 4, 10), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_DAYS_positiveDayPeriod() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 4, 10), Period.ofDays(17));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_MONTH_END_LEAP_YEAR_januaryThirtyFirst() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 31), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_YEAR_ACROSS_LEAP_DAY_leapDayStart() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 29), Period.ofYears(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLE_DAY_equalInclusiveDates() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 7, 4), LocalDate.of(2024, 7, 4));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_LEAP_DAY_INTERVAL_inclusiveLeapEnd() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 2, 29));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_DATE_dateDateBranch() {
        LocalDateRange source = LocalDateRange.parse("2024-08-01/2024-08-11");
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_PERIOD_datePeriodBranch() {
        LocalDateRange source = LocalDateRange.parse("2024-08-01/P10D");
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_PERIOD_DATE_periodDateBranch() {
        LocalDateRange source = LocalDateRange.parse("P10D/2024-08-11");
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_singleDateTemporal() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 9, 14));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_TIME_timeIgnored() {
        LocalDateRange source = LocalDateRange.from(LocalDateTime.of(2024, 9, 14, 18, 45, 30));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_COMMON_YEAR_wholeYear() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_YEAR_wholeLeapYear() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_COMMON_FEBRUARY_YEAR_MONTH_commonFebruary() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2023, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_FEBRUARY_YEAR_MONTH_leapFebruary() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_firstHalfOfLeapYear() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2024, Half.H1));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_firstQuarterOfLeapYear() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, Quarter.Q1));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_WEEK_isoWeekFiftyThree() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2020, 53));
        int sourceOutput = source.lengthInDays();
        Object[] followUp = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUp[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
