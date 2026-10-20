import org.junit.jupiter.api.Test;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    @Test
    public void INTERIOR_EMPTY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(java.time.LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2024, 6, 15),
                java.time.LocalDate.of(2024, 6, 16));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_JUST_BELOW_EXTRA_DAYS_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2023, 2, 10);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, start.plusDays(22));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EQUALS_EXTRA_DAYS_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 2, 20);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, java.time.Period.ofDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_JUST_ABOVE_EXTRA_DAYS_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(1900, 2, 20);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, start.plusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_SINGLE_DATE_RANGE_variation1() {
        java.time.LocalDate date = java.time.LocalDate.of(2024, 7, 10);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(date, date);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_MULTI_DATE_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.ofClosed(
                java.time.LocalDate.of(2024, 7, 10),
                java.time.LocalDate.of(2024, 7, 14));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_PERIOD_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2024, 8, 1),
                java.time.Period.ZERO);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_DAY_PERIOD_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2024, 8, 1),
                java.time.Period.ofDays(10));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CALENDAR_MONTH_PERIOD_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2024, 1, 31),
                java.time.Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_DATE_FORM_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2024-03-01/2024-03-11");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_DATE_PERIOD_FORM_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2024-03-01/P10D");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSE_PERIOD_DATE_FORM_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("P10D/2024-03-11");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LOCAL_DATE_TEMPORAL_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(java.time.LocalDate.of(2024, 5, 20));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_COMMON_YEAR_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(java.time.Year.of(2023));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LEAP_YEAR_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(java.time.Year.of(2024));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_COMMON_FEBRUARY_YEARMONTH_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(java.time.YearMonth.of(2023, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LEAP_FEBRUARY_YEARMONTH_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(java.time.YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_HALF_variation1() {
        org.threeten.extra.YearHalf temporal =
                org.threeten.extra.YearHalf.of(2024, org.threeten.extra.Half.H1);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(temporal);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_QUARTER_variation1() {
        org.threeten.extra.YearQuarter temporal =
                org.threeten.extra.YearQuarter.of(2023, org.threeten.extra.Quarter.Q2);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(temporal);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_WEEK_CROSSING_YEAR_variation1() {
        org.threeten.extra.YearWeek temporal = org.threeten.extra.YearWeek.of(2020, 53);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(temporal);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THIRTY_ONE_DAY_MONTH_BOUNDARY_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2024, 1, 30),
                java.time.LocalDate.of(2024, 2, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_FEBRUARY_BOUNDARY_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2023, 2, 28),
                java.time.LocalDate.of(2023, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_INCLUDED_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2024, 2, 28),
                java.time.LocalDate.of(2024, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_LEAP_CENTURY_FEBRUARY_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(1900, 2, 28),
                java.time.LocalDate.of(1900, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_FOUR_HUNDRED_YEAR_FEBRUARY_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(2000, 2, 28),
                java.time.LocalDate.of(2000, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNIX_EPOCH_CROSSING_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(1969, 12, 31),
                java.time.LocalDate.of(1970, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROLEPTIC_ERA_BOUNDARY_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.of(-1, 12, 31),
                java.time.LocalDate.of(0, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_BOUNDED_START_variation1() {
        org.threeten.extra.LocalDateRange source = org.threeten.extra.LocalDateRange.of(
                java.time.LocalDate.MIN.plusDays(1),
                java.time.LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_VALID_EMPTY_LOCATION_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(java.time.LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATEST_END_WITH_BOUNDED_FOLLOW_UP_variation1() {
        java.time.LocalDate end = java.time.LocalDate.MAX.minusDays(24);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(end.minusDays(1), end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FOLLOW_UP_BECOMES_UNBOUNDED_variation1() {
        java.time.LocalDate end = java.time.LocalDate.MAX.minusDays(23);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(end.minusDays(1), end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_INTEGER_MAX_MINUS_23_variation1() {
        java.time.LocalDate start = java.time.LocalDate.ofEpochDay(0);
        java.time.LocalDate end = start.plusDays(2147483624L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FOLLOW_UP_CROSSES_SIGNED_INT_LIMIT_variation1() {
        java.time.LocalDate start = java.time.LocalDate.ofEpochDay(-1000);
        java.time.LocalDate end = start.plusDays(2147483625L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDED_LENGTH_EQUALS_SENTINEL_variation1() {
        java.time.LocalDate start = java.time.LocalDate.ofEpochDay(-2000);
        java.time.LocalDate end = start.plusDays(2147483647L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDED_LENGTH_INTEGER_MAX_PLUS_ONE_variation1() {
        java.time.LocalDate start = java.time.LocalDate.ofEpochDay(-3000);
        java.time.LocalDate end = start.plusDays(2147483648L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_MULTIPLE_OF_UINT_RANGE_variation1() {
        java.time.LocalDate start = java.time.LocalDate.ofEpochDay(-4000);
        java.time.LocalDate end = start.plusDays(4294967296L);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_TYPICAL_END_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        java.time.LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_EARLIEST_END_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        java.time.LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_MAXIMUM_TRANSFORMABLE_END_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        java.time.LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
