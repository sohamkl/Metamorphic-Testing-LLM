import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private void verify(Temporal start, Temporal end) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0],
                        (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_EQUAL_ENDPOINTS_1() {
        LocalDate start = LocalDate.MIN.plusDays(50);
        LocalDate end = start;
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_ADJACENT_FORWARD_1() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        LocalDate end = start.plusDays(1);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_ADJACENT_REVERSE_1() {
        LocalDate start = LocalDate.of(2024, 1, 2);
        LocalDate end = start.minusDays(1);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_SMALL_POSITIVE_GAP_1() {
        LocalDate start = LocalDate.of(2024, 6, 10);
        LocalDate end = LocalDate.of(2024, 6, 15);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_SMALL_POSITIVE_GAP_2() {
        LocalDate start = LocalDate.of(2024, 7, 10);
        LocalDate end = LocalDate.of(2024, 7, 20);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_SMALL_NEGATIVE_GAP_1() {
        LocalDate start = LocalDate.of(2024, 7, 20);
        LocalDate end = LocalDate.of(2024, 7, 15);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_SMALL_NEGATIVE_GAP_2() {
        LocalDate start = LocalDate.of(2024, 8, 25);
        LocalDate end = LocalDate.of(2024, 8, 18);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_MONTH_BOUNDARY_FORWARD_1() {
        LocalDate start = LocalDate.of(2024, 4, 29);
        LocalDate end = LocalDate.of(2024, 5, 2);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_MONTH_BOUNDARY_FORWARD_2() {
        LocalDate start = LocalDate.of(2024, 8, 30);
        LocalDate end = LocalDate.of(2024, 9, 2);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_MONTH_BOUNDARY_REVERSE_1() {
        LocalDate start = LocalDate.of(2024, 5, 2);
        LocalDate end = LocalDate.of(2024, 4, 29);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_CROSSING_1() {
        LocalDate start = LocalDate.of(2024, 2, 27);
        LocalDate end = LocalDate.of(2024, 3, 2);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_CROSSING_2() {
        LocalDate start = LocalDate.of(2024, 3, 2);
        LocalDate end = LocalDate.of(2024, 2, 27);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_FORWARD_1() {
        LocalDate start = LocalDate.of(2023, 12, 30);
        LocalDate end = LocalDate.of(2024, 1, 2);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_FORWARD_2() {
        LocalDate start = LocalDate.of(2023, 12, 31);
        LocalDate end = LocalDate.of(2024, 1, 2);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_REVERSE_1() {
        LocalDate start = LocalDate.of(2024, 1, 3);
        LocalDate end = LocalDate.of(2023, 12, 29);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_NEAR_MINIMUM_RANGE_1() {
        LocalDate start = LocalDate.MIN.plusDays(40);
        LocalDate end = LocalDate.MIN.plusDays(44);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_NEAR_MAXIMUM_RANGE_1() {
        LocalDate start = LocalDate.MAX.minusDays(100);
        LocalDate end = LocalDate.MAX.minusDays(96);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MAXIMUM_DISTANCE_1() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MINIMUM_DISTANCE_1() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = start.plusDays((long) Integer.MIN_VALUE);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_EQUAL_LOCAL_DATE_1() {
        LocalDateTime start =
                LocalDateTime.of(2024, 9, 18, 13, 45, 12, 345678901);
        LocalDateTime end = start;
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_IDENTICAL_TIME_ONE_DAY_1() {
        LocalDateTime start =
                LocalDateTime.of(2024, 10, 10, 8, 5, 0, 123456789);
        LocalDateTime end = start.plusDays(1);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_MULTI_DAY_REVERSE_1() {
        LocalDateTime start =
                LocalDateTime.of(2024, 11, 20, 19, 30, 0, 456789123);
        LocalDateTime end = start.minusDays(5);
        verify(start, end);
    }

    @Test
    public void INSTANT_SUBDAY_FORWARD_1() {
        Instant start = Instant.parse("2024-05-10T00:00:00Z");
        Instant end = start.plusNanos(86399999999999L);
        verify(start, end);
    }

    @Test
    public void INSTANT_EXACT_MULTI_DAY_1() {
        Instant start = Instant.parse("2024-06-10T03:15:00Z");
        Instant end = start.plusSeconds(7L * 86400L);
        verify(start, end);
    }

    @Test
    public void INSTANT_SUBDAY_REVERSE_1() {
        Instant start = Instant.parse("2024-07-01T12:00:00Z");
        Instant end = start.minusNanos(86399999999999L);
        verify(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_FIXED_OFFSET_1() {
        OffsetDateTime start =
                OffsetDateTime.of(2024, 8, 5, 9, 0, 0, 0, ZoneOffset.ofHours(5));
        OffsetDateTime end = start.plusDays(4);
        verify(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_FIXED_OFFSET_2() {
        OffsetDateTime start =
                OffsetDateTime.of(
                        2024, 9, 12, 18, 20, 0, 0,
                        ZoneOffset.ofHoursMinutes(-3, -30));
        OffsetDateTime end = start.plusDays(3);
        verify(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_OFFSET_BOUNDARY_1() {
        OffsetDateTime start =
                OffsetDateTime.of(2023, 12, 30, 6, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime end =
                OffsetDateTime.of(2024, 1, 2, 6, 0, 0, 0, ZoneOffset.UTC);
        verify(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_STABLE_OFFSET_1() {
        ZonedDateTime start =
                ZonedDateTime.of(
                        LocalDateTime.of(2024, 10, 5, 11, 30),
                        ZoneOffset.UTC);
        ZonedDateTime end = start.plusDays(7);
        verify(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_UTC_BOUNDARY_1() {
        ZonedDateTime start =
                ZonedDateTime.of(
                        LocalDateTime.of(2024, 2, 27, 4, 0),
                        ZoneOffset.UTC);
        ZonedDateTime end =
                ZonedDateTime.of(
                        LocalDateTime.of(2024, 3, 2, 4, 0),
                        ZoneOffset.UTC);
        verify(start, end);
    }
}
