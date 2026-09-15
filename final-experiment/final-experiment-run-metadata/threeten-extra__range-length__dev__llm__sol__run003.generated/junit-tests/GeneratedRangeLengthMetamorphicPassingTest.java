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

public class GeneratedRangeLengthMetamorphicPassingTest {

    @Test
    void EMPTY_INTERIOR_OF_EMPTY_variation1_ordinaryDate() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 5, 15));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_INTERIOR_OF_EMPTY_variation2_monthBoundaryDate() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(1999, 1, 31));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_HALF_OPEN_variation1_commonFebruary() {
        LocalDate start = LocalDate.of(2023, 2, 14);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(1));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_HALF_OPEN_variation2_leapDay() {
        LocalDate start = LocalDate.of(2024, 2, 29);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(1));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_EQUAL_TO_EXTENSION_variation1_yearBoundary() {
        LocalDate start = LocalDate.of(2022, 12, 20);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_EQUAL_TO_EXTENSION_variation2_prolepticYearZero() {
        LocalDate start = LocalDate.of(0, 6, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CROSS_ORDINARY_MONTH_END_variation1_januaryToFebruary() {
        LocalDateRange source = LocalDateRange.parse("2023-01-28/P6D");
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CROSS_ORDINARY_MONTH_END_variation2_aprilToMay() {
        LocalDateRange source = LocalDateRange.parse("P7D/2021-05-04");
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COMMON_YEAR_FEBRUARY_variation1_fullFebruary() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 3, 1));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_CROSSING_variation1_2020LeapDay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 2, 27),
                LocalDate.of(2020, 3, 3));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_CROSSING_variation2_2000LeapDay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2000, 2, 29),
                Period.ofDays(2));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_BOUNDARY_CROSSING_variation1_commonYearTransition() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2021, 12, 29),
                LocalDate.of(2022, 1, 4));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_BOUNDARY_CROSSING_variation2_negativeToYearZero() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(-1, 12, 28),
                LocalDate.of(0, 1, 5));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROLEPTIC_YEAR_ZERO_OR_NEGATIVE_variation1_yearZero() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(0, 2, 20),
                LocalDate.of(0, 3, 5));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PROLEPTIC_YEAR_ZERO_OR_NEGATIVE_variation2_negativeYear() {
        LocalDateRange source = LocalDateRange.parse("-0044-03-10/-0044-03-20");
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_RANGE_SINGLE_DAY_variation1_negativeYear() {
        LocalDate date = LocalDate.of(-20, 7, 11);
        LocalDateRange source = LocalDateRange.ofClosed(date, date);
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_RANGE_SINGLE_DAY_variation2_minimumNeighborhood() {
        LocalDate date = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.ofClosed(date, date);
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ZERO_RANGE_variation1_interiorDate() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2030, 8, 17),
                Period.ZERO);
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_MONTH_BASED_RANGE_variation1_endOfMonthAdjustment() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 31),
                Period.ofMonths(1));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_MONTH_BASED_RANGE_variation2_yearComponent() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 2, 29),
                Period.ofYears(1));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_TEMPORAL_variation1_fixedDate() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2022, 11, 9));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_COMMON_AND_LEAP_variation1_commonYear() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_COMMON_AND_LEAP_variation2_leapYear() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_MONTH_variation1_commonFebruary() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2019, 2));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_MONTH_variation2_leapFebruary() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2000, 2));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_variation1_firstHalfCommonYear() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2023, Half.H1));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_variation2_secondHalfLeapYear() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2024, Half.H2));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_variation1_firstQuarterLeapYear() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, Quarter.Q1));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_variation2_fourthQuarter() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2021, Quarter.Q4));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_WEEK_variation1_firstIsoWeek() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2024, 1));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_WEEK_variation2_weekFiftyThree() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2020, 53));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_DATE_variation1_yearBoundary() {
        LocalDateRange source = LocalDateRange.parse("2020-12-25/2021-01-08");
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_DATE_variation2_prolepticYearZero() {
        LocalDateRange source = LocalDateRange.parse("0000-09-01/0000-10-01");
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_PERIOD_variation1_dayPeriod() {
        LocalDateRange source = LocalDateRange.parse("-0100-06-10/P40D");
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_PERIOD_variation2_monthPeriod() {
        LocalDateRange source = LocalDateRange.parse("2018-01-31/P2M");
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_PERIOD_DATE_variation1_monthPeriod() {
        LocalDateRange source = LocalDateRange.parse("P1M/2022-06-30");
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_PERIOD_DATE_variation2_dayPeriod() {
        LocalDateRange source = LocalDateRange.parse("P1D/1995-08-22");
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EARLIEST_BOUNDED_RANGE_variation1_minimumNeighborhood() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(1),
                LocalDate.MIN.plusDays(2));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_EARLIEST_END_variation1_minimumAllowedEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_ORDINARY_END_variation1_ordinaryFiniteEnd() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(1986, 4, 12));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LATEST_PRODUCTIVE_BOUNDED_END_variation1_maximumNeighborhood() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.of(end.minusDays(1), end);
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FOLLOW_UP_BECOMES_UNBOUNDED_END_variation1_latestTransformableEnd() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.parse(end.minusDays(1) + "/" + end);
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_INTEGER_MAX_MINUS_24_variation1_largeBoundedRange() {
        long length = (long) Integer.MAX_VALUE - 24L;
        LocalDate end = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.parse(end.minusDays(length) + "/P" + length + "D");
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_INTEGER_MAX_MINUS_23_variation1_extensionReachesSentinel() {
        long length = (long) Integer.MAX_VALUE - 23L;
        LocalDate end = LocalDate.of(2001, 2, 3);
        LocalDateRange source = LocalDateRange.parse("P" + length + "D/" + end);
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_INTEGER_MAX_MINUS_22_variation1_extensionExceedsThreshold() {
        long length = (long) Integer.MAX_VALUE - 22L;
        LocalDate end = LocalDate.of(2002, 3, 4);
        LocalDateRange source = LocalDateRange.of(end.minusDays(length), end);
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_INTEGER_MAX_MINUS_1_variation1_oneBelowSentinel() {
        long length = (long) Integer.MAX_VALUE - 1L;
        LocalDate end = LocalDate.of(2003, 4, 5);
        LocalDateRange source = LocalDateRange.of(end.minusDays(length), end);
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOUNDED_LENGTH_EXACTLY_INTEGER_MAX_variation1_exactSaturationBoundary() {
        long length = Integer.MAX_VALUE;
        LocalDate end = LocalDate.of(2004, 5, 6);
        LocalDateRange source = LocalDateRange.of(end.minusDays(length), Period.ofDays(Integer.MAX_VALUE));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOUNDED_LENGTH_ABOVE_INTEGER_MAX_variation1_oneAboveSaturationBoundary() {
        long length = (long) Integer.MAX_VALUE + 1L;
        LocalDate end = LocalDate.of(2005, 6, 7);
        LocalDateRange source = LocalDateRange.ofClosed(
                end.minusDays(length),
                end.minusDays(1));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HUGE_BOUNDED_SATURATED_RANGE_variation1_multiBillionDaySpan() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(-10_000_000, 2, 29),
                LocalDate.of(10_000_000, 3, 1));
        int sourceOutput = org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpArgs[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
