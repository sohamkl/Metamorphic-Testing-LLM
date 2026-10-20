import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static void verifyRelation(Temporal startDateInclusive, Temporal endDateExclusive) {
        Days sourceOutput = org.threeten.extra.Days.between(
                startDateInclusive, endDateExclusive);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(
                startDateInclusive, endDateExclusive);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_EQUAL_INTERIOR_variation1() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        verifyRelation(date, date);
    }

    @Test
    void LOCAL_DATE_POSITIVE_ONE_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = LocalDate.of(2024, 6, 16);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_NEGATIVE_ONE_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 16);
        LocalDate end = LocalDate.of(2024, 6, 15);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_POSITIVE_SHIFT_SIZED_GAP_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 2, 7);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_NEGATIVE_SHIFT_SIZED_GAP_variation1() {
        LocalDate start = LocalDate.of(2024, 2, 7);
        LocalDate end = LocalDate.of(2024, 1, 1);
        verifyRelation(start, end);
    }

    @Test
    void INTEGER_MAXIMUM_DAY_COUNT_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDate end = start.plusDays(Integer.MAX_VALUE);
        verifyRelation(start, end);
    }

    @Test
    void INTEGER_MINIMUM_DAY_COUNT_variation1() {
        LocalDate end = LocalDate.of(0, 1, 1);
        LocalDate start = end.plusDays(2147483648L);
        verifyRelation(start, end);
    }

    @Test
    void LEAP_YEAR_ENTER_LEAP_DAY_variation1() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDate end = LocalDate.of(2024, 2, 29);
        verifyRelation(start, end);
    }

    @Test
    void LEAP_YEAR_EXIT_LEAP_DAY_variation1() {
        LocalDate start = LocalDate.of(2024, 2, 29);
        LocalDate end = LocalDate.of(2024, 3, 1);
        verifyRelation(start, end);
    }

    @Test
    void NON_LEAP_FEBRUARY_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2023, 2, 28);
        LocalDate end = LocalDate.of(2023, 3, 1);
        verifyRelation(start, end);
    }

    @Test
    void THIRTY_DAY_MONTH_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2024, 4, 30);
        LocalDate end = LocalDate.of(2024, 5, 1);
        verifyRelation(start, end);
    }

    @Test
    void THIRTY_ONE_DAY_MONTH_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        LocalDate end = LocalDate.of(2024, 2, 1);
        verifyRelation(start, end);
    }

    @Test
    void CALENDAR_YEAR_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2023, 12, 31);
        LocalDate end = LocalDate.of(2024, 1, 1);
        verifyRelation(start, end);
    }

    @Test
    void PROLEPTIC_YEAR_ZERO_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(-1, 12, 31);
        LocalDate end = LocalDate.of(0, 1, 1);
        verifyRelation(start, end);
    }

    @Test
    void UNIX_EPOCH_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(1969, 12, 31);
        LocalDate end = LocalDate.of(1970, 1, 1);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_MINIMUM_ENDPOINT_variation1() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN.plusDays(1);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_MAXIMUM_FOLLOW_UP_REACHED_variation1() {
        LocalDate start = LocalDate.of(999999999, 11, 23);
        LocalDate end = LocalDate.of(999999999, 11, 24);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_LONG_LEAP_SPANNING_INTERVAL_variation1() {
        LocalDate start = LocalDate.of(2019, 3, 1);
        LocalDate end = LocalDate.of(2025, 3, 1);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_EQUAL_variation1() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 6, 15, 10, 30);
        verifyRelation(dateTime, dateTime);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 10, 30);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 10, 30);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_JUST_UNDER_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 0, 0);
        LocalDateTime end = LocalDateTime.of(
                2024, 6, 15, 23, 59, 59, 999999999);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_NEXT_DATE_UNDER_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 12, 0);
        LocalDateTime end = LocalDateTime.of(
                2024, 6, 16, 11, 59, 59, 999999999);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_JUST_OVER_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 0, 0);
        LocalDateTime end = LocalDateTime.of(
                2024, 6, 16, 0, 0, 0, 1);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 16, 0, 0);
        LocalDateTime end = LocalDateTime.of(
                2024, 6, 15, 0, 0, 0, 1);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_OVER_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(
                2024, 6, 16, 0, 0, 0, 1);
        LocalDateTime end = LocalDateTime.of(2024, 6, 15, 0, 0);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_LEAP_DAY_WITH_TIME_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 2, 28, 23, 30);
        LocalDateTime end = LocalDateTime.of(2024, 2, 29, 23, 30);
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_EQUAL_variation1() {
        Instant instant = Instant.parse("2024-06-15T00:00:00Z");
        verifyRelation(instant, instant);
    }

    @Test
    void INSTANT_EXACT_DAY_variation1() {
        Instant start = Instant.parse("2024-06-15T00:00:00Z");
        Instant end = Instant.parse("2024-06-16T00:00:00Z");
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_JUST_UNDER_DAY_variation1() {
        Instant start = Instant.parse("2024-06-15T00:00:00Z");
        Instant end = Instant.parse("2024-06-15T23:59:59.999999999Z");
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_JUST_OVER_DAY_variation1() {
        Instant start = Instant.parse("2024-06-15T00:00:00Z");
        Instant end = Instant.parse("2024-06-16T00:00:00.000000001Z");
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_NEGATIVE_PARTIAL_DAY_variation1() {
        Instant start = Instant.parse("2024-06-16T00:00:00Z");
        Instant end = Instant.parse("2024-06-15T00:00:00.000000001Z");
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_NEGATIVE_OVER_DAY_variation1() {
        Instant start = Instant.parse("2024-06-16T00:00:00.000000001Z");
        Instant end = Instant.parse("2024-06-15T00:00:00Z");
        verifyRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_DAY_variation1() {
        OffsetDateTime start = OffsetDateTime.parse("2024-06-15T10:00:00+02:00");
        OffsetDateTime end = OffsetDateTime.parse("2024-06-16T10:00:00+02:00");
        verifyRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_INSTANT_DIFFERENT_OFFSET_variation1() {
        OffsetDateTime start = OffsetDateTime.parse("2024-06-15T10:00:00+02:00");
        OffsetDateTime end = OffsetDateTime.parse("2024-06-15T08:00:00Z");
        verifyRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_INSTANT_DIFFERENT_LOCAL_DATE_variation1() {
        OffsetDateTime start = OffsetDateTime.parse("2024-06-16T00:30:00+14:00");
        OffsetDateTime end = OffsetDateTime.parse("2024-06-15T10:30:00Z");
        verifyRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_OFFSET_CHANGE_WHOLE_DAY_variation1() {
        OffsetDateTime start = OffsetDateTime.parse("2024-06-15T10:00:00+02:00");
        OffsetDateTime end = OffsetDateTime.parse("2024-06-16T09:00:00+01:00");
        verifyRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_REVERSE_OFFSET_CHANGE_variation1() {
        OffsetDateTime start = OffsetDateTime.parse("2024-06-16T09:00:00+01:00");
        OffsetDateTime end = OffsetDateTime.parse("2024-06-15T10:00:00+02:00");
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_UTC_DAY_variation1() {
        ZonedDateTime start = ZonedDateTime.of(
                LocalDateTime.of(2024, 6, 15, 12, 0), ZoneId.of("UTC"));
        ZonedDateTime end = ZonedDateTime.of(
                LocalDateTime.of(2024, 6, 16, 12, 0), ZoneId.of("UTC"));
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_GAP_SAME_LOCAL_TIME_variation1() {
        ZonedDateTime start = ZonedDateTime.parse(
                "2024-03-09T12:00:00-05:00[America/New_York]");
        ZonedDateTime end = ZonedDateTime.parse(
                "2024-03-10T12:00:00-04:00[America/New_York]");
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_FALL_OVERLAP_SAME_LOCAL_TIME_variation1() {
        ZonedDateTime start = ZonedDateTime.parse(
                "2024-11-02T12:00:00-04:00[America/New_York]");
        ZonedDateTime end = ZonedDateTime.parse(
                "2024-11-03T12:00:00-05:00[America/New_York]");
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_LATER_LOCAL_TIME_variation1() {
        ZonedDateTime start = ZonedDateTime.parse(
                "2024-03-09T12:00:00-05:00[America/New_York]");
        ZonedDateTime end = ZonedDateTime.parse(
                "2024-03-10T13:00:00-04:00[America/New_York]");
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_FALL_EARLIER_LOCAL_TIME_variation1() {
        ZonedDateTime start = ZonedDateTime.parse(
                "2024-11-02T12:00:00-04:00[America/New_York]");
        ZonedDateTime end = ZonedDateTime.parse(
                "2024-11-03T11:00:00-05:00[America/New_York]");
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_SAME_INSTANT_DIFFERENT_ZONES_variation1() {
        ZonedDateTime start = ZonedDateTime.parse(
                "2024-06-15T12:00:00Z[UTC]");
        ZonedDateTime end = ZonedDateTime.parse(
                "2024-06-15T08:00:00-04:00[America/New_York]");
        verifyRelation(start, end);
    }

    @Test
    void JAPANESE_DATE_ERA_BOUNDARY_variation1() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2019, 4, 30));
        JapaneseDate end = JapaneseDate.from(LocalDate.of(2019, 5, 1));
        verifyRelation(start, end);
    }

    @Test
    void HIJRAH_DATE_MONTH_BOUNDARY_variation1() {
        HijrahDate start = HijrahDate.of(1445, 9, 1)
                .with(TemporalAdjusters.lastDayOfMonth());
        HijrahDate end = start.plus(1, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    void THAI_BUDDHIST_DATE_YEAR_BOUNDARY_variation1() {
        ThaiBuddhistDate start =
                ThaiBuddhistDate.from(LocalDate.of(2023, 12, 31));
        ThaiBuddhistDate end =
                ThaiBuddhistDate.from(LocalDate.of(2024, 1, 1));
        verifyRelation(start, end);
    }

    @Test
    void MINGUO_DATE_YEAR_BOUNDARY_variation1() {
        MinguoDate start = MinguoDate.from(LocalDate.of(2023, 12, 31));
        MinguoDate end = MinguoDate.from(LocalDate.of(2024, 1, 1));
        verifyRelation(start, end);
    }

    @Test
    void MIXED_LOCAL_DATE_TO_JAPANESE_DATE_EQUAL_variation1() {
        LocalDate start = LocalDate.of(2019, 5, 1);
        JapaneseDate end = JapaneseDate.from(LocalDate.of(2019, 5, 1));
        verifyRelation(start, end);
    }

    @Test
    void MIXED_LOCAL_DATE_TO_JAPANESE_DATE_NEXT_DAY_variation1() {
        LocalDate start = LocalDate.of(2019, 4, 30);
        JapaneseDate end = JapaneseDate.from(LocalDate.of(2019, 5, 1));
        verifyRelation(start, end);
    }

    @Test
    void MIXED_LOCAL_DATE_TO_LOCAL_DATE_TIME_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 23, 59, 59);
        verifyRelation(start, end);
    }
}
