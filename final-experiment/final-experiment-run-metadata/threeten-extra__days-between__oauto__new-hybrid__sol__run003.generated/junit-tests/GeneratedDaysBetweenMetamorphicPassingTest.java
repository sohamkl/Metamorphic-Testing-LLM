import org.junit.jupiter.api.Test;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static Object[] generateFollowUp(
            java.time.temporal.Temporal startDateInclusive,
            java.time.temporal.Temporal endDateExclusive) {
        return new Object[]{
                startDateInclusive.plus(37, java.time.temporal.ChronoUnit.DAYS),
                endDateExclusive.plus(37, java.time.temporal.ChronoUnit.DAYS)
        };
    }

    @Test
    public void LOCAL_DATE_EQUAL_ZERO_SENTINEL_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 6, 15);
        java.time.LocalDate end = java.time.LocalDate.of(2024, 6, 15);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_FORWARD_ONE_SENTINEL_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 6, 15);
        java.time.LocalDate end = java.time.LocalDate.of(2024, 6, 16);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_REVERSE_ONE_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 6, 16);
        java.time.LocalDate end = java.time.LocalDate.of(2024, 6, 15);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MULTI_DAY_FORWARD_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 4, 3);
        java.time.LocalDate end = java.time.LocalDate.of(2024, 5, 11);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_FORWARD_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 2, 28);
        java.time.LocalDate end = java.time.LocalDate.of(2024, 3, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_REVERSE_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 3, 1);
        java.time.LocalDate end = java.time.LocalDate.of(2024, 2, 28);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2023, 12, 31);
        java.time.LocalDate end = java.time.LocalDate.of(2024, 1, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_PROLEPTIC_ERA_BOUNDARY_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(-1, 12, 31);
        java.time.LocalDate end = java.time.LocalDate.of(0, 1, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MAX_RESULT_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(0, 1, 1);
        java.time.LocalDate end = start.plusDays(Integer.MAX_VALUE);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MIN_RESULT_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(0, 1, 1);
        java.time.LocalDate end = start.plusDays((long) Integer.MIN_VALUE);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_UPPER_SHIFT_LIMIT_variation1() {
        java.time.LocalDate start = java.time.LocalDate.MAX.minusDays(37);
        java.time.LocalDate end = java.time.LocalDate.MAX.minusDays(37);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_LOWER_RANGE_NEIGHBORS_variation1() {
        java.time.LocalDate start = java.time.LocalDate.MIN;
        java.time.LocalDate end = java.time.LocalDate.MIN.plusDays(1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EQUAL_variation1() {
        java.time.LocalDateTime start = java.time.LocalDateTime.of(2024, 6, 15, 10, 20, 30);
        java.time.LocalDateTime end = java.time.LocalDateTime.of(2024, 6, 15, 10, 20, 30);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_SUBDAY_variation1() {
        java.time.LocalDateTime start = java.time.LocalDateTime.of(2024, 6, 15, 0, 0);
        java.time.LocalDateTime end = java.time.LocalDateTime.of(2024, 6, 15, 23, 59, 59);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_variation1() {
        java.time.LocalDateTime start = java.time.LocalDateTime.of(2024, 6, 15, 10, 20, 30);
        java.time.LocalDateTime end = java.time.LocalDateTime.of(2024, 6, 16, 10, 20, 30);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_DAY_MINUS_NANO_variation1() {
        java.time.LocalDateTime start = java.time.LocalDateTime.of(2024, 6, 15, 0, 0);
        java.time.LocalDateTime end = java.time.LocalDateTime.of(
                2024, 6, 15, 23, 59, 59, 999999999);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_DAY_PLUS_NANO_variation1() {
        java.time.LocalDateTime start = java.time.LocalDateTime.of(2024, 6, 15, 0, 0);
        java.time.LocalDateTime end = java.time.LocalDateTime.of(
                2024, 6, 16, 0, 0, 0, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_variation1() {
        java.time.LocalDateTime start = java.time.LocalDateTime.of(2024, 6, 16, 0, 0);
        java.time.LocalDateTime end = java.time.LocalDateTime.of(2024, 6, 15, 1, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_OVER_DAY_variation1() {
        java.time.LocalDateTime start = java.time.LocalDateTime.of(2024, 6, 16, 2, 0);
        java.time.LocalDateTime end = java.time.LocalDateTime.of(2024, 6, 15, 1, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_LEAP_MULTI_DAY_variation1() {
        java.time.LocalDateTime start = java.time.LocalDateTime.of(2024, 2, 28, 12, 0);
        java.time.LocalDateTime end = java.time.LocalDateTime.of(2024, 3, 1, 12, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_SAME_OFFSET_DAY_variation1() {
        java.time.OffsetDateTime start = java.time.OffsetDateTime.parse("2024-01-10T08:00:00+02:00");
        java.time.OffsetDateTime end = java.time.OffsetDateTime.parse("2024-01-11T08:00:00+02:00");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_EQUAL_INSTANT_DIFFERENT_OFFSETS_variation1() {
        java.time.OffsetDateTime start = java.time.OffsetDateTime.parse("2024-01-10T00:00:00Z");
        java.time.OffsetDateTime end = java.time.OffsetDateTime.parse("2024-01-10T01:00:00+01:00");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_CROSS_OFFSET_SUBDAY_variation1() {
        java.time.OffsetDateTime start = java.time.OffsetDateTime.parse("2024-01-10T12:00:00+02:00");
        java.time.OffsetDateTime end = java.time.OffsetDateTime.parse("2024-01-11T10:59:00+01:00");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_EXTREME_OFFSETS_variation1() {
        java.time.OffsetDateTime start = java.time.OffsetDateTime.parse("2024-01-01T00:00:00+14:00");
        java.time.OffsetDateTime end = java.time.OffsetDateTime.parse("2024-01-02T00:00:00-12:00");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_NEGATIVE_EXACT_variation1() {
        java.time.OffsetDateTime start = java.time.OffsetDateTime.parse("2024-01-12T08:00:00+05:30");
        java.time.OffsetDateTime end = java.time.OffsetDateTime.parse("2024-01-10T08:00:00+05:30");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_DAY_PLUS_SECOND_variation1() {
        java.time.OffsetDateTime start = java.time.OffsetDateTime.parse("2024-01-10T08:00:00Z");
        java.time.OffsetDateTime end = java.time.OffsetDateTime.parse("2024-01-11T08:00:01Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_NEGATIVE_SUBDAY_variation1() {
        java.time.OffsetDateTime start = java.time.OffsetDateTime.parse("2024-01-11T08:00:00Z");
        java.time.OffsetDateTime end = java.time.OffsetDateTime.parse("2024-01-10T08:00:01Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_SPRING_GAP_variation1() {
        java.time.ZonedDateTime start =
                java.time.ZonedDateTime.parse("2020-03-08T00:00:00-05:00[America/New_York]");
        java.time.ZonedDateTime end =
                java.time.ZonedDateTime.parse("2020-03-09T00:00:00-04:00[America/New_York]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_AUTUMN_OVERLAP_variation1() {
        java.time.ZonedDateTime start =
                java.time.ZonedDateTime.parse("2020-11-01T00:00:00-04:00[America/New_York]");
        java.time.ZonedDateTime end =
                java.time.ZonedDateTime.parse("2020-11-02T00:00:00-05:00[America/New_York]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_SPRING_SAME_LOCAL_TIME_variation1() {
        java.time.ZonedDateTime start =
                java.time.ZonedDateTime.parse("2020-03-08T01:30:00-05:00[America/New_York]");
        java.time.ZonedDateTime end =
                java.time.ZonedDateTime.parse("2020-03-09T01:30:00-04:00[America/New_York]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_OVERLAP_AMBIGUOUS_HOUR_variation1() {
        java.time.ZonedDateTime start =
                java.time.ZonedDateTime.parse("2020-11-01T01:30:00-04:00[America/New_York]");
        java.time.ZonedDateTime end =
                java.time.ZonedDateTime.parse("2020-11-02T01:30:00-05:00[America/New_York]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_EQUAL_INSTANT_CROSS_ZONE_variation1() {
        java.time.ZonedDateTime start =
                java.time.ZonedDateTime.parse("2024-01-10T07:00:00-05:00[America/New_York]");
        java.time.ZonedDateTime end =
                java.time.ZonedDateTime.parse("2024-01-10T12:00:00Z[UTC]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_SKIPPED_CIVIL_DATE_variation1() {
        java.time.ZonedDateTime start =
                java.time.ZonedDateTime.parse("2011-12-29T00:00:00-10:00[Pacific/Apia]");
        java.time.ZonedDateTime end =
                java.time.ZonedDateTime.parse("2011-12-31T00:00:00+14:00[Pacific/Apia]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_REVERSE_SPRING_variation1() {
        java.time.ZonedDateTime start =
                java.time.ZonedDateTime.parse("2020-03-09T00:00:00-04:00[America/New_York]");
        java.time.ZonedDateTime end =
                java.time.ZonedDateTime.parse("2020-03-08T00:00:00-05:00[America/New_York]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_EQUAL_variation1() {
        java.time.Instant start = java.time.Instant.parse("2024-01-10T00:00:00Z");
        java.time.Instant end = java.time.Instant.parse("2024-01-10T00:00:00Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_EXACT_DAY_variation1() {
        java.time.Instant start = java.time.Instant.parse("2024-01-10T00:00:00Z");
        java.time.Instant end = java.time.Instant.parse("2024-01-11T00:00:00Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_DAY_MINUS_NANO_variation1() {
        java.time.Instant start = java.time.Instant.parse("2024-01-10T00:00:00Z");
        java.time.Instant end = java.time.Instant.parse("2024-01-10T23:59:59.999999999Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_NEGATIVE_SUBDAY_variation1() {
        java.time.Instant start = java.time.Instant.parse("2024-01-10T00:00:00.000000001Z");
        java.time.Instant end = java.time.Instant.parse("2024-01-10T00:00:00Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_NEGATIVE_OVER_DAY_variation1() {
        java.time.Instant start = java.time.Instant.parse("2024-01-11T00:00:01Z");
        java.time.Instant end = java.time.Instant.parse("2024-01-10T00:00:00Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_INTEGER_MAX_DAYS_variation1() {
        java.time.Instant start = java.time.Instant.EPOCH;
        java.time.Instant end = java.time.Instant.EPOCH.plus(
                Integer.MAX_VALUE, java.time.temporal.ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JAPANESE_DATE_ERA_START_EQUAL_variation1() {
        java.time.chrono.JapaneseDate start = java.time.chrono.JapaneseDate.of(2019, 5, 1);
        java.time.chrono.JapaneseDate end = java.time.chrono.JapaneseDate.of(2019, 5, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JAPANESE_DATE_ERA_BOUNDARY_variation1() {
        java.time.chrono.JapaneseDate start = java.time.chrono.JapaneseDate.of(2019, 4, 30);
        java.time.chrono.JapaneseDate end = java.time.chrono.JapaneseDate.of(2019, 5, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HIJRAH_DATE_MONTH_BOUNDARY_variation1() {
        java.time.chrono.HijrahDate month =
                java.time.chrono.HijrahDate.of(1445, 9, 1);
        java.time.chrono.HijrahDate start = month.with(
                java.time.temporal.ChronoField.DAY_OF_MONTH,
                month.lengthOfMonth());
        java.time.chrono.HijrahDate end = start.plus(
                1, java.time.temporal.ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MINGUO_DATE_REVERSE_MULTI_DAY_variation1() {
        java.time.chrono.MinguoDate end = java.time.chrono.MinguoDate.of(113, 6, 15);
        java.time.chrono.MinguoDate start = end.plus(
                40, java.time.temporal.ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THAI_BUDDHIST_DATE_LEAP_CROSSING_variation1() {
        java.time.chrono.ThaiBuddhistDate start = java.time.chrono.ThaiBuddhistDate.from(
                java.time.LocalDate.of(2024, 2, 28));
        java.time.chrono.ThaiBuddhistDate end = java.time.chrono.ThaiBuddhistDate.from(
                java.time.LocalDate.of(2024, 3, 1));
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_LOCAL_DATE_TO_LOCAL_DATE_TIME_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 1, 10);
        java.time.LocalDateTime end = java.time.LocalDateTime.of(2024, 1, 12, 23, 59);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_LOCAL_DATE_TO_ZONED_DATE_TIME_variation1() {
        java.time.LocalDate start = java.time.LocalDate.of(2024, 1, 10);
        java.time.ZonedDateTime end =
                java.time.ZonedDateTime.parse("2024-01-13T01:00:00+14:00[Pacific/Kiritimati]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_JAPANESE_DATE_TO_LOCAL_DATE_variation1() {
        java.time.chrono.JapaneseDate start = java.time.chrono.JapaneseDate.from(
                java.time.LocalDate.of(2024, 1, 10));
        java.time.LocalDate end = java.time.LocalDate.of(2024, 1, 15);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_INSTANT_TO_OFFSET_DATE_TIME_variation1() {
        java.time.Instant start = java.time.Instant.parse("2024-01-10T00:00:00Z");
        java.time.OffsetDateTime end =
                java.time.OffsetDateTime.parse("2024-01-11T03:00:00+03:00");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (java.time.temporal.Temporal) followUp[0],
                (java.time.temporal.Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
