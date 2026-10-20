import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.threeten.extra.Half;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.Quarter;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static LocalDateRange generateFollowUp(LocalDateRange source) {
        LocalDate followUpEnd = source.getEnd().plusDays(23);
        return LocalDateRange.of(source.getStart(), followUpEnd);
    }

    @Test
    public void EMPTY_INTERIOR_variation1_epochOfDates() {
        LocalDate date = LocalDate.of(1970, 1, 1);
        LocalDateRange source = LocalDateRange.of(date, date);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_INTERIOR_variation2_bceZeroPeriod() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(-44, 3, 15), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_EARLIEST_LEGAL_LOCATION_variation1_minPlusTwo() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_LATEST_TRANSFORMABLE_LOCATION_variation1_maxMinusTwentyThree() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_DAY_BOUNDED_variation1_halfOpen() {
        LocalDate start = LocalDate.of(2019, 7, 14);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_DAY_BOUNDED_variation2_fromDate() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 2, 29));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EXTRA_MINUS_ONE_variation1_twentyTwoDays() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDateRange source = LocalDateRange.parse(start + "/P22D");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EXACTLY_EXTRA_variation1_twentyThreeDays() {
        LocalDate end = LocalDate.of(1, 2, 1);
        LocalDateRange source = LocalDateRange.parse("P23D/" + end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EXTRA_PLUS_ONE_variation1_twentyFourDays() {
        LocalDate start = LocalDate.of(2021, 4, 10);
        LocalDateRange source = LocalDateRange.of(start, Period.ofDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_MULTI_MONTH_RANGE_variation1_ninetyDays() {
        LocalDate start = LocalDate.of(2018, 8, 20);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(90));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_MULTI_MONTH_RANGE_variation2_calendarMonths() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 1, 31), Period.ofMonths(3));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_MONTH_BOUNDARY_variation1_aprilToMay() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2021, 4, 25),
                LocalDate.of(2021, 5, 8));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_CROSSING_variation1_february2020() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 2, 20),
                LocalDate.of(2020, 3, 5));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_YEAR_BOUNDARY_variation1_2019To2020() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2019, 12, 15),
                LocalDate.of(2020, 1, 10));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_YEAR_BOUNDARY_variation1_2020To2021() {
        LocalDateRange source = LocalDateRange.parse("2020-02-01/2021-01-12");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BCE_DATE_RANGE_variation1_idesOfMarch() {
        LocalDateRange source = LocalDateRange.parse("-0044-03-01/P2M");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EPOCH_CROSSING_variation1_negativeToPositiveEpochDay() {
        LocalDateRange source = LocalDateRange.parse("P20D/1970-01-11");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_BOUNDED_START_variation1_minPlusOne() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(10));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_EARLIEST_END_variation1_minPlusTwoEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_ORDINARY_END_variation1_epochEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.EPOCH);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_ORDINARY_END_variation2_bceEnd() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN, LocalDate.of(-100, 6, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void END_MAX_MINUS_25_variation1_finiteFollowUp() {
        LocalDate end = LocalDate.MAX.minusDays(25);
        LocalDateRange source = LocalDateRange.of(end.minusDays(40), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void END_MAX_MINUS_24_variation1_finiteFollowUp() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.of(end.minusDays(1), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void END_MAX_MINUS_23_variation1_unboundedFollowUpEnd() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.parse(end.minusDays(17) + "/" + end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_INT_MAX_MINUS_24_variation1_largestCheckedWindow() {
        LocalDate end = LocalDate.of(2000, 1, 1);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE - 24L);
        LocalDateRange source = LocalDateRange.parse(start + "/P2147483623D");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_INT_MAX_MINUS_23_variation1_reachesSentinel() {
        LocalDate end = LocalDate.of(2000, 2, 1);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE - 23L);
        LocalDateRange source = LocalDateRange.parse(
                "P2147483624D/" + end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_NEAR_INT_MAX_WINDOW_variation1_maxMinusTwentyTwo() {
        LocalDate end = LocalDate.of(2010, 1, 1);
        long days = (long) Integer.MAX_VALUE - 22L;
        LocalDateRange source = LocalDateRange.of(end.minusDays(days), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_NEAR_INT_MAX_WINDOW_variation2_maxMinusOne() {
        LocalDate end = LocalDate.of(2012, 6, 30);
        long days = (long) Integer.MAX_VALUE - 1L;
        LocalDateRange source = LocalDateRange.of(end.minusDays(days), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EXACT_INT_MAX_variation1_exactIntLimit() {
        LocalDate end = LocalDate.of(2020, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays((long) Integer.MAX_VALUE),
                Period.ofDays(Integer.MAX_VALUE));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_ABOVE_INT_MAX_variation1_limitPlusOne() {
        LocalDate end = LocalDate.of(2030, 1, 1);
        long days = (long) Integer.MAX_VALUE + 1L;
        LocalDate start = end.minusDays(days);
        LocalDateRange source = LocalDateRange.ofClosed(start, end.minusDays(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_ABOVE_INT_MAX_variation2_wellAboveLimit() {
        LocalDate end = LocalDate.of(2040, 12, 31);
        long days = (long) Integer.MAX_VALUE + 100_000L;
        LocalDateRange source = LocalDateRange.of(end.minusDays(days), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_RANGE_CONSTRUCTION_variation1_singleIncludedDate() {
        LocalDate date = LocalDate.of(2001, 9, 9);
        LocalDateRange source = LocalDateRange.ofClosed(date, date);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_RANGE_CONSTRUCTION_variation2_multipleIncludedDates() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2022, 10, 20),
                LocalDate.of(2022, 12, 5));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_PERIOD_CONSTRUCTION_variation1_emptyPeriodRange() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 6, 1), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CALENDAR_PERIOD_CONSTRUCTION_variation1_monthFromJanuaryEnd() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 1, 31), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CALENDAR_PERIOD_CONSTRUCTION_variation2_yearMonthAndDays() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2019, 2, 28), Period.of(1, 2, 5));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_DATE_variation1_halfOpenText() {
        LocalDateRange source = LocalDateRange.parse(
                "1969-12-20/1970-02-10");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_PERIOD_variation1_dayPeriod() {
        LocalDateRange source = LocalDateRange.parse(
                "-0001-12-20/P20D");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_PERIOD_variation2_calendarPeriod() {
        LocalDateRange source = LocalDateRange.parse(
                "2020-01-31/P1M");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_PERIOD_DATE_variation1_calendarMonthBeforeEnd() {
        LocalDateRange source = LocalDateRange.parse(
                "P1M/2020-04-30");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_PERIOD_DATE_variation2_yearAndDaysBeforeEnd() {
        LocalDateRange source = LocalDateRange.parse(
                "P1Y10D/2022-03-15");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_DATE_BEARING_TEMPORAL_variation1_localDate() {
        LocalDateRange source = LocalDateRange.from(
                LocalDate.of(1999, 12, 31));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_variation1_commonYear() {
        LocalDateRange source = LocalDateRange.from(Year.of(2019));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_variation2_leapYear() {
        LocalDateRange source = LocalDateRange.from(Year.of(2020));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_MONTH_variation1_commonFebruary() {
        LocalDateRange source = LocalDateRange.from(
                YearMonth.of(2019, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_MONTH_variation2_leapFebruary() {
        LocalDateRange source = LocalDateRange.from(
                YearMonth.of(2020, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_SUPPORTED_EXTRA_TEMPORAL_variation1_yearHalf() {
        LocalDateRange source = LocalDateRange.from(
                YearHalf.of(2021, Half.H1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_SUPPORTED_EXTRA_TEMPORAL_variation2_yearQuarter() {
        LocalDateRange source = LocalDateRange.from(
                YearQuarter.of(2020, Quarter.Q1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_SUPPORTED_EXTRA_TEMPORAL_variation3_yearWeek() {
        LocalDateRange source = LocalDateRange.from(
                YearWeek.of(2022, 20));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
