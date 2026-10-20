import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static void assertMetamorphicRelation(
            org.threeten.extra.Days sourceOutput,
            org.threeten.extra.Days followUpOutput) {
        if (sourceOutput.getAmount() != followUpOutput.getAmount()) {
            throw new AssertionError("The day count changed after the follow-up transformation.");
        }
    }

    @Test
    void LOCAL_DATE_ZERO_DISTANCE_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = start;

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_ONE_DAY_FORWARD_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = start.plusDays(1);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_ONE_DAY_BACKWARD_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = start.minusDays(1);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TYPICAL_POSITIVE_DISTANCE_variation1() {
        LocalDate start = LocalDate.of(2023, 1, 10);
        LocalDate end = start.plusDays(37);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MONTH_AND_LEAP_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDate end = LocalDate.of(2024, 3, 2);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2023, 12, 31);
        LocalDate end = LocalDate.of(2024, 1, 2);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_INTEGER_MAXIMUM_DISTANCE_variation1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDate end = start.plusDays(Integer.MAX_VALUE);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_INTEGER_MINIMUM_DISTANCE_variation1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDate end = start.plusDays(Integer.MIN_VALUE);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_ZERO_DISTANCE_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 12, 34, 56);
        LocalDateTime end = start;

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_SUBDAY_POSITIVE_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 0, 0);
        LocalDateTime end = start.plusHours(23);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_ONE_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 6, 30);
        LocalDateTime end = start.plusHours(24);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_FORTY_SEVEN_HOURS_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 6, 30);
        LocalDateTime end = start.plusHours(47);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 23, 0);
        LocalDateTime end = start.minusHours(23);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_FULL_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 6, 30);
        LocalDateTime end = start.minusHours(25);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_FIXED_OFFSET_variation1() {
        OffsetDateTime start =
                OffsetDateTime.parse("2024-06-15T10:00:00+02:00");
        OffsetDateTime end = start.plusHours(36);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_SUBDAY_variation1() {
        OffsetDateTime start =
                OffsetDateTime.parse("2024-06-15T00:00:00+02:00");
        OffsetDateTime end =
                OffsetDateTime.parse("2024-06-15T21:00:00Z");

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_EXACT_TWO_DAYS_variation1() {
        Instant start = Instant.parse("2024-06-15T00:00:00Z");
        Instant end = start.plus(48, ChronoUnit.HOURS);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_SUBDAY_REMAINDER_variation1() {
        Instant start = Instant.parse("2024-06-15T00:00:00Z");
        Instant end = start.plus(47, ChronoUnit.HOURS);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FIXED_ZONE_ZONED_DATE_TIME_variation1() {
        ZonedDateTime start =
                ZonedDateTime.parse("2024-06-15T10:00:00Z[UTC]");
        ZonedDateTime end = start.plusDays(10);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FIXED_ZONE_ZONED_DATE_TIME_REVERSED_variation1() {
        ZonedDateTime start =
                ZonedDateTime.parse("2024-06-25T10:00:00Z[UTC]");
        ZonedDateTime end =
                ZonedDateTime.parse("2024-06-15T10:00:00Z[UTC]");

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUpValues =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Temporal followUpStart = (Temporal) followUpValues[0];
        Temporal followUpEnd = (Temporal) followUpValues[1];
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUpStart, followUpEnd);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
