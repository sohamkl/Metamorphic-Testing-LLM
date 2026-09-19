import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static void assertMetamorphicRelation(
            org.threeten.extra.Days sourceOutput,
            org.threeten.extra.Days followUpOutput) {
        if (sourceOutput.getAmount() != followUpOutput.getAmount()) {
            throw new AssertionError(
                    "The source and follow-up day counts are not equal.");
        }
    }

    @Test
    public void LOCAL_DATE_ZERO_GAP_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = LocalDate.of(2024, 6, 15);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_FORWARD_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = LocalDate.of(2024, 6, 16);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_REVERSE_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 16);
        LocalDate end = LocalDate.of(2024, 6, 15);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MONTH_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2023, 1, 31);
        LocalDate end = LocalDate.of(2023, 2, 1);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2023, 12, 31);
        LocalDate end = LocalDate.of(2024, 1, 2);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_variation1() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDate end = LocalDate.of(2024, 3, 1);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_NON_LEAP_FEBRUARY_variation1() {
        LocalDate start = LocalDate.of(2023, 2, 28);
        LocalDate end = LocalDate.of(2023, 3, 1);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MULTI_DAY_NEGATIVE_variation1() {
        LocalDate start = LocalDate.of(2025, 1, 10);
        LocalDate end = LocalDate.of(2025, 1, 1);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_INT_MAX_DISTANCE_variation1() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN.plusDays(Integer.MAX_VALUE);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_INT_MIN_DISTANCE_variation1() {
        LocalDate start = LocalDate.MAX.minusDays(37);
        LocalDate end = start.minusDays(2147483648L);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MINIMUM_REGION_variation1() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN.plusDays(37);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MAXIMUM_REGION_variation1() {
        LocalDate start = LocalDate.MAX.minusDays(74);
        LocalDate end = LocalDate.MAX.minusDays(37);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_SUBDAY_FORWARD_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 9, 59);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_SUBDAY_REVERSE_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 16, 9, 59);
        LocalDateTime end = LocalDateTime.of(2024, 6, 15, 10, 0);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 10, 0);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_SUBDAY_INTERVAL_variation1() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = Instant.parse("2024-01-02T12:00:00Z");

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_variation1() {
        OffsetDateTime start =
                OffsetDateTime.parse("2024-01-01T00:00:00+02:00");
        OffsetDateTime end =
                OffsetDateTime.parse("2024-01-02T00:00:00-05:00");

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_DST_FORWARD_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start =
                ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone);
        ZonedDateTime end =
                ZonedDateTime.of(2024, 3, 11, 12, 0, 0, 0, zone);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_DST_REVERSE_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start =
                ZonedDateTime.of(2024, 11, 4, 12, 0, 0, 0, zone);
        ZonedDateTime end =
                ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, zone);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
