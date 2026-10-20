import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static void exercise(Temporal start, Temporal end) {
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SAME_LOCAL_DATE_ZERO_1() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        exercise(date, date);
    }

    @Test
    public void SAME_LOCAL_DATE_ZERO_2() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 31, 12, 0);
        exercise(start, start.plusDays(1));
    }

    @Test
    public void ADJACENT_LOCAL_DATE_FORWARD_AND_REVERSE_1() {
        Instant end = Instant.parse("2024-01-01T00:00:00Z");
        exercise(end.plusSeconds(86_399), end);
    }

    @Test
    public void ADJACENT_LOCAL_DATE_FORWARD_AND_REVERSE_2() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 2, 28, 8, 0, 0, 0, ZoneOffset.ofHours(2));
        exercise(start, start);
    }

    @Test
    public void MULTI_DAY_LOCAL_DATE_FORWARD_AND_REVERSE_1() {
        ZonedDateTime start = ZonedDateTime.of(
                2023, 2, 20, 10, 0, 0, 0, ZoneId.of("Europe/Paris"));
        exercise(start, start.plusDays(7));
    }

    @Test
    public void MULTI_DAY_LOCAL_DATE_FORWARD_AND_REVERSE_2() {
        JapaneseDate start = JapaneseDate.of(2020, 3, 20);
        exercise(start, start.minus(12, java.time.temporal.ChronoUnit.DAYS));
    }

    @Test
    public void LOCAL_DATE_MONTH_BOUNDARY_1() {
        HijrahDate date = HijrahDate.of(1445, 8, 29);
        exercise(date, date);
    }

    @Test
    public void LOCAL_DATE_MONTH_BOUNDARY_2() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        exercise(start, LocalDate.of(2024, 2, 1));
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_1() {
        LocalDateTime start = LocalDateTime.of(2025, 1, 2, 6, 30);
        exercise(start, LocalDateTime.of(2024, 12, 31, 6, 30));
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_2() {
        Instant start = Instant.parse("2024-12-31T00:00:00Z");
        exercise(start, start);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_1() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 2, 28, 12, 0, 0, 0, ZoneOffset.UTC);
        exercise(start, OffsetDateTime.of(
                2024, 3, 1, 12, 0, 0, 0, ZoneOffset.UTC));
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_2() {
        ZonedDateTime start = ZonedDateTime.of(
                2024, 3, 1, 9, 0, 0, 0, ZoneId.of("UTC"));
        exercise(start, ZonedDateTime.of(
                2024, 2, 28, 9, 0, 0, 0, ZoneId.of("UTC")));
    }

    @Test
    public void LOCAL_DATE_NON_LEAP_FEBRUARY_1() {
        JapaneseDate start = JapaneseDate.of(2019, 2, 28);
        exercise(start, start);
    }

    @Test
    public void LOCAL_DATE_NON_LEAP_FEBRUARY_2() {
        HijrahDate start = HijrahDate.of(1445, 9, 1);
        exercise(start, start.plus(5, java.time.temporal.ChronoUnit.DAYS));
    }

    @Test
    public void LOCAL_DATE_INT_RESULT_LIMITS_1() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = LocalDate.ofEpochDay(Integer.MAX_VALUE);
        exercise(start, end);
    }

    @Test
    public void LOCAL_DATE_INT_RESULT_LIMITS_2() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = LocalDate.ofEpochDay(Integer.MIN_VALUE);
        exercise(start, end);
    }

    @Test
    public void LOCAL_DATETIME_EXACT_WHOLE_DAYS_1() {
        Instant start = Instant.parse("2024-01-01T12:00:00Z");
        exercise(start, start.plusSeconds(2L * 86_400));
    }

    @Test
    public void LOCAL_DATETIME_EXACT_WHOLE_DAYS_2() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 3, 3, 15, 0, 0, 0, ZoneOffset.ofHours(5));
        exercise(start, start.minusDays(14));
    }

    @Test
    public void LOCAL_DATETIME_FRACTIONAL_DAY_TRUNCATION_1() {
        ZonedDateTime start = ZonedDateTime.of(
                2024, 2, 1, 10, 0, 0, 0, ZoneId.of("Europe/London"));
        exercise(start, start.plusDays(2).minusHours(1));
    }

    @Test
    public void LOCAL_DATETIME_FRACTIONAL_DAY_TRUNCATION_2() {
        JapaneseDate start = JapaneseDate.of(2024, 3, 1);
        exercise(start, start.plus(3, java.time.temporal.ChronoUnit.DAYS));
    }

    @Test
    public void LOCAL_DATETIME_SUBDAY_ZERO_1() {
        HijrahDate start = HijrahDate.of(1445, 10, 1);
        exercise(start, start.minus(1, java.time.temporal.ChronoUnit.DAYS));
    }

    @Test
    public void LOCAL_DATETIME_SUBDAY_ZERO_2() {
        LocalDate start = LocalDate.of(2024, 7, 4);
        exercise(start, start);
    }

    @Test
    public void INSTANT_EXACT_AND_FRACTIONAL_DAYS_1() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 31, 8, 0);
        exercise(start, start.plusDays(1));
    }

    @Test
    public void INSTANT_EXACT_AND_FRACTIONAL_DAYS_2() {
        Instant start = Instant.parse("2024-12-31T18:00:00Z");
        exercise(start, start.minusSeconds(86_400 + 3_600));
    }

    @Test
    public void OFFSET_DATETIME_SAME_OFFSET_1() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 2, 29, 10, 0, 0, 0, ZoneOffset.ofHours(1));
        exercise(start, start);
    }

    @Test
    public void OFFSET_DATETIME_SAME_OFFSET_2() {
        ZonedDateTime start = ZonedDateTime.of(
                2024, 2, 20, 10, 0, 0, 0, ZoneId.of("UTC"));
        exercise(start, start.plusDays(8));
    }

    @Test
    public void OFFSET_DATETIME_DIFFERENT_OFFSETS_1() {
        JapaneseDate start = JapaneseDate.of(2024, 3, 10);
        exercise(start, start.minus(4, java.time.temporal.ChronoUnit.DAYS));
    }

    @Test
    public void OFFSET_DATETIME_DIFFERENT_OFFSETS_2() {
        HijrahDate start = HijrahDate.of(1445, 11, 1);
        exercise(start, start);
    }

    @Test
    public void ZONED_DATETIME_ORDINARY_ZONE_1() {
        LocalDate start = LocalDate.of(2024, 6, 10);
        exercise(start, start.plusDays(5));
    }

    @Test
    public void ZONED_DATETIME_ORDINARY_ZONE_2() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 31, 10, 0);
        exercise(start, start.minusHours(25));
    }

    @Test
    public void ZONED_DATETIME_SPRING_TRANSITION_1() {
        Instant start = Instant.parse("2024-03-31T00:00:00Z");
        exercise(start, start);
    }

    @Test
    public void ZONED_DATETIME_SPRING_TRANSITION_2() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 3, 30, 12, 0, 0, 0, ZoneOffset.ofHours(1));
        exercise(start, start.plusDays(2));
    }

    @Test
    public void ZONED_DATETIME_AUTUMN_TRANSITION_1() {
        ZonedDateTime start = ZonedDateTime.of(
                2024, 10, 28, 9, 0, 0, 0, ZoneId.of("Europe/Paris"));
        exercise(start, start.minusDays(3));
    }

    @Test
    public void ZONED_DATETIME_AUTUMN_TRANSITION_2() {
        JapaneseDate start = JapaneseDate.of(2024, 3, 10);
        exercise(start, start);
    }

    @Test
    public void JAPANESE_DATE_SUPPORTED_CHRONOLOGY_1() {
        HijrahDate start = HijrahDate.of(1445, 12, 1);
        exercise(start, start.plus(9, java.time.temporal.ChronoUnit.DAYS));
    }

    @Test
    public void JAPANESE_DATE_SUPPORTED_CHRONOLOGY_2() {
        LocalDate start = LocalDate.of(2024, 8, 1);
        exercise(start, start.minusDays(6));
    }

    @Test
    public void HIJRAH_DATE_SUPPORTED_CHRONOLOGY_1() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 31, 23, 0);
        exercise(start, start);
    }

    @Test
    public void HIJRAH_DATE_SUPPORTED_CHRONOLOGY_2() {
        Instant start = Instant.parse("2024-12-31T00:00:00Z");
        exercise(start, start.plusSeconds(2L * 86_400));
    }
}
