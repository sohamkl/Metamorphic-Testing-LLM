import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static void verify(Temporal startDateInclusive, Temporal endDateExclusive) {
        Days sourceOutput =
                org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(startDateInclusive, endDateExclusive);
        Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0],
                        (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EQUAL_LOCAL_DATES_sameOrdinaryDate() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        verify(date, date);
    }

    @Test
    public void ADJACENT_FORWARD_LOCAL_DATES_oneDayForward() {
        LocalDate start = LocalDate.of(2024, 7, 20);
        LocalDate end = start.plusDays(1);
        verify(start, end);
    }

    @Test
    public void ADJACENT_REVERSE_LOCAL_DATES_oneDayBackward() {
        LocalDate start = LocalDate.of(2024, 7, 20);
        LocalDate end = start.minusDays(1);
        verify(start, end);
    }

    @Test
    public void POSITIVE_MULTI_DAY_LOCAL_DATE_GAPS_twoDays() {
        LocalDate start = LocalDate.of(2024, 2, 10);
        LocalDate end = start.plusDays(2);
        verify(start, end);
    }

    @Test
    public void POSITIVE_MULTI_DAY_LOCAL_DATE_GAPS_thirtySevenDays() {
        LocalDate start = LocalDate.of(2024, 3, 1);
        LocalDate end = start.plusDays(37);
        verify(start, end);
    }

    @Test
    public void NEGATIVE_MULTI_DAY_LOCAL_DATE_GAPS_twoDaysBackward() {
        LocalDate start = LocalDate.of(2024, 8, 20);
        LocalDate end = start.minusDays(2);
        verify(start, end);
    }

    @Test
    public void NEGATIVE_MULTI_DAY_LOCAL_DATE_GAPS_thirtySevenDaysBackward() {
        LocalDate start = LocalDate.of(2024, 9, 30);
        LocalDate end = start.minusDays(37);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_MONTH_BOUNDARIES_januaryToFebruary() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        LocalDate end = LocalDate.of(2024, 2, 1);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_MONTH_BOUNDARIES_aprilToMay() {
        LocalDate start = LocalDate.of(2024, 4, 30);
        LocalDate end = LocalDate.of(2024, 5, 1);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARIES_2023To2024() {
        LocalDate start = LocalDate.of(2023, 12, 31);
        LocalDate end = LocalDate.of(2024, 1, 1);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARIES_2024To2025() {
        LocalDate start = LocalDate.of(2024, 12, 31);
        LocalDate end = LocalDate.of(2025, 1, 1);
        verify(start, end);
    }

    @Test
    public void LEAP_DAY_SPANS_twoDayLeapSpan() {
        LocalDate start = LocalDate.of(2020, 2, 28);
        LocalDate end = LocalDate.of(2020, 3, 1);
        verify(start, end);
    }

    @Test
    public void LEAP_DAY_SPANS_fromLeapDay() {
        LocalDate start = LocalDate.of(2020, 2, 29);
        LocalDate end = LocalDate.of(2020, 3, 1);
        verify(start, end);
    }

    @Test
    public void NON_LEAP_CENTURY_BOUNDARY_year1900() {
        LocalDate start = LocalDate.of(1900, 2, 28);
        LocalDate end = LocalDate.of(1900, 3, 1);
        verify(start, end);
    }

    @Test
    public void LEAP_CENTURY_BOUNDARY_year2000() {
        LocalDate start = LocalDate.of(2000, 2, 28);
        LocalDate end = LocalDate.of(2000, 3, 1);
        verify(start, end);
    }

    @Test
    public void MAXIMUM_INT_DAY_COUNT_exactMaximum() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDate end = start.plusDays(Integer.MAX_VALUE);
        verify(start, end);
    }

    @Test
    public void MINIMUM_INT_DAY_COUNT_exactMinimum() {
        LocalDate start = LocalDate.of(6_000_000, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MIN_VALUE);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_SUBDAY_oneNanosecond() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 15, 12, 0);
        LocalDateTime end = start.plusNanos(1);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_SUBDAY_nearlyTwentyFourHours() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 15, 12, 0);
        LocalDateTime end = start.plusHours(23).plusMinutes(59).plusSeconds(59);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_oneNanosecond() {
        LocalDateTime start = LocalDateTime.of(2024, 2, 15, 12, 0);
        LocalDateTime end = start.minusNanos(1);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_nearlyTwentyFourHours() {
        LocalDateTime start = LocalDateTime.of(2024, 2, 15, 12, 0);
        LocalDateTime end = start.minusHours(23).minusMinutes(59).minusSeconds(59);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_DIRECTIONS_forward() {
        LocalDateTime start = LocalDateTime.of(2024, 3, 10, 8, 30);
        LocalDateTime end = start.plusDays(1);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_DIRECTIONS_reverse() {
        LocalDateTime start = LocalDateTime.of(2024, 3, 10, 8, 30);
        LocalDateTime end = start.minusDays(1);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_CALENDAR_BOUNDARIES_monthBoundary() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 31, 12, 0);
        LocalDateTime end = LocalDateTime.of(2024, 2, 1, 12, 0);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_CALENDAR_BOUNDARIES_yearBoundary() {
        LocalDateTime start = LocalDateTime.of(2024, 12, 31, 12, 0);
        LocalDateTime end = LocalDateTime.of(2025, 1, 1, 12, 0);
        verify(start, end);
    }

    @Test
    public void INSTANT_SUBDAY_TRUNCATION_positive86399Seconds() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = start.plusSeconds(86_399);
        verify(start, end);
    }

    @Test
    public void INSTANT_SUBDAY_TRUNCATION_negative86399Seconds() {
        Instant start = Instant.parse("2024-01-02T00:00:00Z");
        Instant end = start.minusSeconds(86_399);
        verify(start, end);
    }

    @Test
    public void INSTANT_EXACT_POSITIVE_DAYS_oneDay() {
        Instant start = Instant.parse("2024-02-01T06:00:00Z");
        Instant end = start.plusSeconds(86_400);
        verify(start, end);
    }

    @Test
    public void INSTANT_EXACT_POSITIVE_DAYS_thirtySevenDays() {
        Instant start = Instant.parse("2024-02-01T06:00:00Z");
        Instant end = start.plusSeconds(37L * 86_400L);
        verify(start, end);
    }

    @Test
    public void INSTANT_EXACT_NEGATIVE_DAYS_oneDay() {
        Instant start = Instant.parse("2024-06-01T18:00:00Z");
        Instant end = start.minusSeconds(86_400);
        verify(start, end);
    }

    @Test
    public void INSTANT_EXACT_NEGATIVE_DAYS_thirtySevenDays() {
        Instant start = Instant.parse("2024-06-01T18:00:00Z");
        Instant end = start.minusSeconds(37L * 86_400L);
        verify(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_SAME_OFFSET_exactDay() {
        ZoneOffset offset = ZoneOffset.ofHoursMinutes(5, 30);
        OffsetDateTime start = OffsetDateTime.of(2024, 4, 10, 9, 15, 0, 0, offset);
        OffsetDateTime end = start.plusDays(1);
        verify(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_SAME_OFFSET_partialDay() {
        ZoneOffset offset = ZoneOffset.ofHours(-4);
        OffsetDateTime start = OffsetDateTime.of(2024, 4, 10, 9, 15, 0, 0, offset);
        OffsetDateTime end = start.plusHours(12);
        verify(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_SAME_INSTANT_utcFirst() {
        OffsetDateTime start = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        OffsetDateTime end = OffsetDateTime.parse("2024-01-01T01:00:00+01:00");
        verify(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_SAME_INSTANT_positiveOffsetFirst() {
        OffsetDateTime start = OffsetDateTime.parse("2024-01-01T01:00:00+01:00");
        OffsetDateTime end = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        verify(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_NORMALIZED_WHOLE_DAYS_forward() {
        OffsetDateTime start = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        OffsetDateTime end = OffsetDateTime.parse("2024-01-02T02:00:00+02:00");
        verify(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_NORMALIZED_WHOLE_DAYS_reverse() {
        OffsetDateTime start = OffsetDateTime.parse("2024-01-02T02:00:00+02:00");
        OffsetDateTime end = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        verify(start, end);
    }

    @Test
    public void ZONED_SPRING_TRANSITION_DAYS_newYork() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = LocalDate.of(2024, 3, 10).atStartOfDay(zone);
        ZonedDateTime end = LocalDate.of(2024, 3, 11).atStartOfDay(zone);
        verify(start, end);
    }

    @Test
    public void ZONED_SPRING_TRANSITION_DAYS_berlin() {
        ZoneId zone = ZoneId.of("Europe/Berlin");
        ZonedDateTime start = LocalDate.of(2024, 3, 31).atStartOfDay(zone);
        ZonedDateTime end = LocalDate.of(2024, 4, 1).atStartOfDay(zone);
        verify(start, end);
    }

    @Test
    public void ZONED_AUTUMN_TRANSITION_DAYS_newYork() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = LocalDate.of(2024, 11, 3).atStartOfDay(zone);
        ZonedDateTime end = LocalDate.of(2024, 11, 4).atStartOfDay(zone);
        verify(start, end);
    }

    @Test
    public void ZONED_AUTUMN_TRANSITION_DAYS_berlin() {
        ZoneId zone = ZoneId.of("Europe/Berlin");
        ZonedDateTime start = LocalDate.of(2024, 10, 27).atStartOfDay(zone);
        ZonedDateTime end = LocalDate.of(2024, 10, 28).atStartOfDay(zone);
        verify(start, end);
    }

    @Test
    public void ZONED_OVERLAP_SAME_LOCAL_TIME_newYork() {
        ZoneId zone = ZoneId.of("America/New_York");
        LocalDateTime repeatedTime = LocalDateTime.of(2024, 11, 3, 1, 30);
        ZonedDateTime start =
                ZonedDateTime.ofLocal(repeatedTime, zone, ZoneOffset.ofHours(-4));
        ZonedDateTime end =
                ZonedDateTime.ofLocal(repeatedTime, zone, ZoneOffset.ofHours(-5));
        verify(start, end);
    }

    @Test
    public void ZONED_OVERLAP_SAME_LOCAL_TIME_berlin() {
        ZoneId zone = ZoneId.of("Europe/Berlin");
        LocalDateTime repeatedTime = LocalDateTime.of(2024, 10, 27, 2, 30);
        ZonedDateTime start =
                ZonedDateTime.ofLocal(repeatedTime, zone, ZoneOffset.ofHours(2));
        ZonedDateTime end =
                ZonedDateTime.ofLocal(repeatedTime, zone, ZoneOffset.ofHours(1));
        verify(start, end);
    }

    @Test
    public void NON_ISO_CHRONOLOGY_ORDINARY_DAYS_minguoTwoDays() {
        MinguoDate start = MinguoDate.from(LocalDate.of(2024, 5, 10));
        MinguoDate end = start.plus(2, ChronoUnit.DAYS);
        verify(start, end);
    }

    @Test
    public void NON_ISO_CHRONOLOGY_ORDINARY_DAYS_thaiBuddhistThirtySevenDays() {
        ThaiBuddhistDate start = ThaiBuddhistDate.from(LocalDate.of(2024, 6, 1));
        ThaiBuddhistDate end = start.plus(37, ChronoUnit.DAYS);
        verify(start, end);
    }

    @Test
    public void JAPANESE_ERA_BOUNDARY_heiseiToReiwa() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2019, 4, 30));
        JapaneseDate end = JapaneseDate.from(LocalDate.of(2019, 5, 1));
        verify(start, end);
    }

    @Test
    public void HIJRAH_MONTH_BOUNDARY_adjacentAcrossMonth() {
        HijrahDate firstOfMonth = HijrahDate.of(1445, 9, 1);
        HijrahDate start =
                firstOfMonth.plus(firstOfMonth.lengthOfMonth() - 1L, ChronoUnit.DAYS);
        HijrahDate end = start.plus(1, ChronoUnit.DAYS);
        verify(start, end);
    }

    @Test
    public void CHRONO_LOCAL_DATE_TIME_PARTIAL_DAY_japaneseChronology() {
        JapaneseDate startDate = JapaneseDate.from(LocalDate.of(2021, 7, 10));
        JapaneseDate endDate = startDate.plus(1, ChronoUnit.DAYS);
        Temporal start = startDate.atTime(LocalTime.of(23, 0));
        Temporal end = endDate.atTime(LocalTime.of(22, 0));
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_SUPPORTED_RANGE_EDGES_minimumBoundary() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN.plusDays(3);
        verify(start, end);
    }

    @Test
    public void LOCAL_DATE_SUPPORTED_RANGE_EDGES_maximumFollowUpBoundary() {
        LocalDate start = LocalDate.MAX.minusDays(37);
        LocalDate end = LocalDate.MAX.minusDays(40);
        verify(start, end);
    }
}
