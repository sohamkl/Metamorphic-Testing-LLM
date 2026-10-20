import org.junit.jupiter.api.Test;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    @Test
    public void EMPTY_RANGE_FACTORY_ordinaryDate() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(java.time.LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_HALF_OPEN_RANGE_equalEndpoints() {
        java.time.LocalDate date = java.time.LocalDate.of(2000, 1, 1);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(date, date);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_RANGE_smallestNonEmpty() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2024, 4, 10),
                java.time.LocalDate.of(2024, 4, 11));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_SINGLE_DAY_RANGE_equalInclusiveEndpoints() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.ofClosed(
                java.time.LocalDate.of(2024, 4, 10),
                java.time.LocalDate.of(2024, 4, 10));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_JUST_BELOW_EXTRA_DAYS_twentyTwoDays() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 2, 1);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, start.plusDays(22));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EQUALS_EXTRA_DAYS_twentyThreeDays() {
        java.time.LocalDate start = java.time.LocalDate.of(-1, 6, 1);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, start.plusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_JUST_ABOVE_EXTRA_DAYS_twentyFourDays() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 7, 1);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, start.plusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_INCLUDED_crossesFebruaryTwentyNinth() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2020, 2, 28),
                java.time.LocalDate.of(2020, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_YEAR_FEBRUARY_noLeapDay() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2019, 2, 28),
                java.time.LocalDate.of(2019, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_BOUNDARY_januaryToFebruary() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2024, 1, 31),
                java.time.LocalDate.of(2024, 2, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_BOUNDARY_decemberToJanuary() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2023, 12, 31),
                java.time.LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROLEPTIC_NEGATIVE_YEAR_crossesYearZero() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(-1, 12, 31),
                java.time.LocalDate.of(0, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_PERIOD_RANGE_periodZero() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2024, 5, 20),
                java.time.Period.ZERO);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DAY_BASED_PERIOD_RANGE_tenDays() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2024, 5, 20),
                java.time.Period.ofDays(10));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_BASED_PERIOD_OVER_LEAP_FEBRUARY_oneMonth() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2020, 1, 29),
                java.time.Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_PERIOD_CONTAINING_LEAP_DAY_oneYear() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2019, 3, 1),
                java.time.Period.ofYears(1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_DAY_CLOSED_RANGE_tenIncludedDates() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.ofClosed(
                java.time.LocalDate.of(2024, 6, 1),
                java.time.LocalDate.of(2024, 6, 10));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSED_DATE_DATE_RANGE_dateDateSyntax() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2007-12-03/2007-12-04");
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSED_DATE_PERIOD_RANGE_datePeriodSyntax() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2024-01-01/P23D");
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSED_PERIOD_DATE_RANGE_periodDateSyntax() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("P10D/2024-01-11");
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LOCAL_DATE_TEMPORAL_singleDate() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(java.time.LocalDate.of(2024, 8, 5));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LEAP_YEAR_TEMPORAL_fullYear() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(java.time.Year.of(2020));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_COMMON_FEBRUARY_YEAR_MONTH_fullMonth() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(java.time.YearMonth.of(2019, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_HALF_TEMPORAL_secondHalf() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.from(
                org.threeten.extra.YearHalf.of(2020, org.threeten.extra.Half.H2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_QUARTER_TEMPORAL_firstQuarter() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.from(
                org.threeten.extra.YearQuarter.of(2021, org.threeten.extra.Quarter.Q1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_WEEK_TEMPORAL_crossYearWeek() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(org.threeten.extra.YearWeek.of(2020, 53));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOWEST_BOUNDED_START_oneDayNearMinimum() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.MIN.plusDays(1),
                java.time.LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_NEAR_MINIMUM_END_earliestAcceptedEnd() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(java.time.LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_ORDINARY_END_fixedOrdinaryEnd() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(java.time.LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGEST_SAFE_FINITE_SOURCE_LENGTH_maximumRepresentableFollowUp() {
        java.time.LocalDate start = java.time.LocalDate.of(0, 1, 1);
        java.time.LocalDate end = start.plusDays(Integer.MAX_VALUE - 23L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JUST_BELOW_LARGEST_SAFE_LENGTH_oneBelowMaximumSafe() {
        java.time.LocalDate start = java.time.LocalDate.of(1, 1, 1);
        java.time.LocalDate end = start.plusDays(Integer.MAX_VALUE - 24L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATEST_FINITE_FOLLOW_UP_END_nearMaximum() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.MAX.minusDays(25),
                java.time.LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FOLLOW_UP_BECOMES_UNBOUNDED_END_transformationBoundary() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.MAX.minusDays(24),
                java.time.LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp = (org.threeten.extra.LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
