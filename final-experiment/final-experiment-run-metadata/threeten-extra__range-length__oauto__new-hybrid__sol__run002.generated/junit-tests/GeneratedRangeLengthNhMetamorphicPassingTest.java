import org.junit.jupiter.api.Test;
import org.threeten.extra.Half;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.Quarter;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static LocalDateRange generateFollowUp(LocalDateRange source) {
        LocalDate extendedEnd = source.getEnd().plusDays(23);
        return LocalDateRange.of(source.getStart(), extendedEnd);
    }

    @Test
    public void EMPTY_RANGE_CONSTRUCTIONS_variation1_ofDateDate() {
        LocalDate date = LocalDate.of(2023, 5, 10);
        LocalDateRange source = LocalDateRange.of(date, date);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_RANGE_CONSTRUCTIONS_variation2_ofZeroPeriod() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1970, 1, 1), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_RANGE_CONSTRUCTIONS_variation3_ofEmpty() {
        LocalDateRange source = LocalDateRange.ofEmpty(
                LocalDate.of(2000, 2, 29));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_DAY_RANGES_variation1_ordinary() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2023-06-14"), LocalDate.parse("2023-06-15"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_DAY_RANGES_variation2_monthBoundary() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2023-01-31"), LocalDate.parse("2023-02-01"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_DAY_RANGES_variation3_leapDay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2024-02-29"), LocalDate.parse("2024-03-01"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_JUST_BELOW_EXTRA_DAYS_variation1_twentyTwoDays() {
        LocalDateRange source = LocalDateRange.parse("1970-01-01/P22D");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EQUAL_TO_EXTRA_DAYS_variation1_twentyThreeDays() {
        LocalDateRange source = LocalDateRange.parse("P23D/2023-08-24");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_JUST_ABOVE_EXTRA_DAYS_variation1_twentyFourDays() {
        LocalDate start = LocalDate.of(2023, 4, 7);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_END_TRANSITIONS_variation1_januaryToFebruary() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2023-01-29"), LocalDate.parse("2023-02-03"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_END_TRANSITIONS_variation2_aprilToMay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2023-04-20"), Period.ofDays(17));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_END_TRANSITIONS_variation3_leapFebruaryToMarch() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.parse("2024-02-01"), LocalDate.parse("2024-02-29"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_END_TRANSITIONS_variation1_singleDay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2023-12-31"), LocalDate.parse("2024-01-01"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_END_TRANSITIONS_variation2_annual() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2023-01-01"), LocalDate.parse("2024-01-01"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_END_TRANSITIONS_variation3_multiYear() {
        LocalDateRange source = LocalDateRange.parse("2023-07-01/2025-01-01");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_PLACEMENT_variation1_endsOnLeapDay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2024-02-28"), LocalDate.parse("2024-02-29"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_PLACEMENT_variation2_startsOnLeapDay() {
        LocalDateRange source = LocalDateRange.parse("2024-02-29/2024-03-02");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_PLACEMENT_variation3_spansLeapDay() {
        LocalDateRange source = LocalDateRange.parse("P3D/2024-03-02");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CENTURY_LEAP_RULES_variation1_nonLeapCentury1900() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("1900-01-01"), LocalDate.parse("1901-01-01"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CENTURY_LEAP_RULES_variation2_leapCentury2000() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2000-01-01"), Period.ofYears(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EPOCH_RELATIVE_RANGES_variation1_beforeEpoch() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("1969-12-01"), LocalDate.parse("1969-12-31"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EPOCH_RELATIVE_RANGES_variation2_acrossEpoch() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.parse("1969-12-31"), LocalDate.parse("1970-01-01"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EPOCH_RELATIVE_RANGES_variation3_afterEpoch() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.EPOCH, LocalDate.parse("1970-02-10"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CALENDAR_PERIOD_CONSTRUCTION_variation1_nonLeapMonthAddition() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2023-01-31"), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CALENDAR_PERIOD_CONSTRUCTION_variation2_leapMonthAddition() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2024-01-31"), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CALENDAR_PERIOD_CONSTRUCTION_variation3_leapDayYearAddition() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.parse("2024-02-29"), Period.ofYears(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_RANGE_NORMALIZATION_variation1_singleDate() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.parse("2023-05-10"), LocalDate.parse("2023-05-10"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_RANGE_NORMALIZATION_variation2_twentyTwoDates() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.parse("2023-05-10"), LocalDate.parse("2023-05-31"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TEMPORAL_CONVERSION_BRANCHES_variation1_year() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TEMPORAL_CONVERSION_BRANCHES_variation2_yearMonth() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TEMPORAL_CONVERSION_BRANCHES_variation3_yearHalf() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2024, Half.H1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TEMPORAL_CONVERSION_BRANCHES_variation4_yearQuarter() {
        LocalDateRange source = LocalDateRange.from(
                YearQuarter.of(2023, Quarter.Q4));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TEMPORAL_CONVERSION_BRANCHES_variation5_yearWeek() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2020, 53));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_GRAMMAR_BRANCHES_variation1_dateDate() {
        LocalDateRange source = LocalDateRange.parse(
                "2007-12-03/2007-12-31");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_GRAMMAR_BRANCHES_variation2_datePeriod() {
        LocalDateRange source = LocalDateRange.parse("2024-02-01/P1M");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_GRAMMAR_BRANCHES_variation3_periodDate() {
        LocalDateRange source = LocalDateRange.parse("P1M/2024-03-31");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_FINITE_ENDPOINTS_variation1_firstFiniteRange() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_FINITE_ENDPOINTS_variation2_earliestEmptyRange() {
        LocalDateRange source = LocalDateRange.ofEmpty(
                LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATEST_TRANSFORMABLE_ENDPOINTS_variation1_endMaxMinus24() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.of(end.minusDays(23), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATEST_TRANSFORMABLE_ENDPOINTS_variation2_endMaxMinus23() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.of(end.minusDays(23), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MAXIMUM_ASSERTABLE_INT_LENGTHS_variation1_maxMinus25() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                start, start.plusDays((long) Integer.MAX_VALUE - 25L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MAXIMUM_ASSERTABLE_INT_LENGTHS_variation2_maxMinus24() {
        LocalDate start = LocalDate.of(-1000, 6, 15);
        LocalDateRange source = LocalDateRange.of(
                start, start.plusDays((long) Integer.MAX_VALUE - 24L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MAXIMUM_ASSERTABLE_INT_LENGTHS_variation3_maxMinus23() {
        LocalDate start = LocalDate.of(2000, 2, 29);
        LocalDateRange source = LocalDateRange.of(
                start, start.plusDays((long) Integer.MAX_VALUE - 23L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_SENTINEL_PATHS_variation1_earliestEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_SENTINEL_PATHS_variation2_epochEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.EPOCH);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_SENTINEL_PATHS_variation3_leapDayEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.parse("2000-02-29"));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_SENTINEL_PATHS_variation4_latestEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_FINITE_SPANS_variation1_tenThousandDays() {
        LocalDate start = LocalDate.of(1900, 1, 1);
        LocalDateRange source = LocalDateRange.ofClosed(
                start, start.plusDays(9_999L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_FINITE_SPANS_variation2_oneMillionDays() {
        LocalDate start = LocalDate.of(-10000, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                start, Period.ofDays(1_000_000));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_FINITE_SPANS_variation3_oneHundredMillionDays() {
        LocalDate start = LocalDate.of(-500000, 12, 31);
        LocalDateRange source = LocalDateRange.of(
                start, start.plusDays(100_000_000L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
