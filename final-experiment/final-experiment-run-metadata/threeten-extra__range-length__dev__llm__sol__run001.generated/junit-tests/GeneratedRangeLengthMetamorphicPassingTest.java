import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

public class GeneratedRangeLengthMetamorphicPassingTest {

    @Test
    public void EMPTY_VIA_OF_EMPTY_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(LocalDate.of(2020, 6, 15));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_VIA_HALF_OPEN_OF_variation1() {
        LocalDate date = LocalDate.of(2021, 1, 10);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(date, date);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_variation1() {
        LocalDate start = LocalDate.of(2023, 7, 14);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, start.plusDays(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_SINGLE_DAY_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(2022, 8, 9), LocalDate.of(2022, 8, 9));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_22_DAYS_variation1() {
        LocalDate start = LocalDate.of(2022, 2, 10);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, start.plusDays(22));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EXACTLY_EXTRA_DAYS_variation1() {
        LocalDate start = LocalDate.of(2021, 12, 20);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, start.plusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_24_DAYS_variation1() {
        LocalDate start = LocalDate.of(2023, 5, 29);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, start.plusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_WEEK_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2023, 3, 1), LocalDate.of(2023, 4, 10));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THIRTY_DAY_MONTH_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2021, 4, 1), LocalDate.of(2021, 5, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THIRTY_ONE_DAY_MONTH_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2021, 1, 1), LocalDate.of(2021, 2, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_FEBRUARY_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2019, 2, 1), LocalDate.of(2019, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_FEBRUARY_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 2, 1), LocalDate.of(2020, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CROSS_LEAP_DAY_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 2, 28), LocalDate.of(2020, 3, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_YEAR_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_YEAR_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_PERIOD_CONSTRUCTION_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 5, 20), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DAY_PERIOD_CONSTRUCTION_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 5, 20), Period.ofDays(17));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_PERIOD_CONSTRUCTION_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 1, 15), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_PERIOD_CROSSES_LEAP_DAY_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2019, 3, 1), Period.ofYears(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LOCAL_DATE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(LocalDate.of(2024, 4, 5));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_TEMPORAL_WITH_LOCAL_DATE_QUERY_variation1() {
        LocalDateTime temporal = LocalDateTime.of(2024, 4, 5, 16, 30, 45);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(temporal);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_COMMON_YEAR_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(Year.of(2021));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LEAP_YEAR_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(Year.of(2020));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEARMONTH_28_DAYS_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearMonth.of(2019, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEARMONTH_29_DAYS_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearMonth.of(2020, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEARMONTH_30_DAYS_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearMonth.of(2021, 4));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEARMONTH_31_DAYS_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearMonth.of(2021, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_HALF_variation1() {
        org.threeten.extra.YearHalf temporal =
                org.threeten.extra.YearHalf.parse("2020-H1");
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(temporal);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_QUARTER_variation1() {
        org.threeten.extra.YearQuarter temporal =
                org.threeten.extra.YearQuarter.parse("2021-Q2");
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(temporal);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_WEEK_variation1() {
        org.threeten.extra.YearWeek temporal =
                org.threeten.extra.YearWeek.parse("2020-W53");
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(temporal);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_DATE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2020-01-10/2020-02-10");
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_PERIOD_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2020-01-10/P23D");
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_PERIOD_DATE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("P23D/2020-02-02");
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_EARLIEST_LEGAL_END_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_ORDINARY_END_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.of(2020, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_END_EXACT_HEADROOM_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_BOUNDED_ONE_DAY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_LEGAL_EMPTY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void END_WITH_24_DAYS_HEADROOM_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MAX.minusDays(47), LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void END_WITH_EXACTLY_23_DAYS_HEADROOM_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MAX.minusDays(46), LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATEST_TRANSFORMABLE_EMPTY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(
                        LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_RANGE_REACHING_EXACT_HEADROOM_END_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.MAX.minusDays(29), LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_INTEGER_MAX_MINUS_24_variation1() {
        LocalDate end = LocalDate.of(2000, 1, 1);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE - 24L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_INTEGER_MAX_MINUS_23_variation1() {
        LocalDate end = LocalDate.of(2000, 2, 1);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE - 23L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_INTEGER_MAX_MINUS_1_variation1() {
        LocalDate end = LocalDate.of(2000, 3, 1);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE - 1L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EXACT_INTEGER_MAX_variation1() {
        LocalDate end = LocalDate.of(2000, 4, 1);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_ABOVE_INTEGER_MAX_variation1() {
        LocalDate end = LocalDate.of(2000, 5, 1);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE + 1L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void VERY_LARGE_BOUNDED_TIMELINE_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MIN.plusDays(1), LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_RANGE_ENDING_BEFORE_TRANSFORM_LIMIT_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.MAX.minusDays(30), LocalDate.MAX.minusDays(25));
        int sourceOutput = source.lengthInDays();
        Object[] followUpArgs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArgs[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
