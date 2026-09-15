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
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private void exerciseMetamorphicRelation(Temporal startDateInclusive, Temporal endDateExclusive) {
        Days sourceOutput = org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(
                startDateInclusive, endDateExclusive);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private HijrahDate findHijrahMonthBoundaryStart() {
        HijrahDate candidate = HijrahDate.from(LocalDate.of(2024, 1, 1));
        for (int i = 0; i < 400; i++) {
            HijrahDate next = candidate.plus(1, ChronoUnit.DAYS);
            if (candidate.get(ChronoField.MONTH_OF_YEAR) != next.get(ChronoField.MONTH_OF_YEAR)) {
                return candidate;
            }
            candidate = next;
        }
        throw new IllegalStateException("No Hijrah month boundary found");
    }

    @Test
    public void LOCAL_DATE_EQUAL_ENDPOINTS_ZERO_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = LocalDate.of(2024, 6, 15);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_SENTINEL_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = LocalDate.of(2024, 6, 16);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_REVERSED_ONE_DAY_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 16);
        LocalDate end = LocalDate.of(2024, 6, 15);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_POSITIVE_GENERAL_GAP_variation1() {
        LocalDate start = LocalDate.of(2024, 4, 1);
        LocalDate end = LocalDate.of(2024, 5, 7);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_GAP_EQUALS_SHIFT_variation1() {
        LocalDate start = LocalDate.of(2024, 4, 1);
        LocalDate end = LocalDate.of(2024, 5, 8);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_NEGATIVE_GENERAL_GAP_variation1() {
        LocalDate start = LocalDate.of(2024, 5, 8);
        LocalDate end = LocalDate.of(2024, 4, 1);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_INCLUDED_variation1() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDate end = LocalDate.of(2024, 3, 1);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_NON_LEAP_FEBRUARY_variation1() {
        LocalDate start = LocalDate.of(2023, 2, 28);
        LocalDate end = LocalDate.of(2023, 3, 1);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_VARIABLE_MONTH_LENGTH_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        LocalDate end = LocalDate.of(2024, 2, 29);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2023, 12, 31);
        LocalDate end = LocalDate.of(2024, 1, 1);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_CENTURY_NON_LEAP_variation1() {
        LocalDate start = LocalDate.of(1900, 2, 28);
        LocalDate end = LocalDate.of(1900, 3, 1);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_CENTURY_LEAP_variation1() {
        LocalDate start = LocalDate.of(2000, 2, 28);
        LocalDate end = LocalDate.of(2000, 3, 1);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MAXIMUM_RESULT_variation1() {
        LocalDate start = LocalDate.of(-5_000_000, 1, 1);
        LocalDate end = start.plusDays(Integer.MAX_VALUE);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MINIMUM_RESULT_variation1() {
        LocalDate start = LocalDate.of(5_000_000, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MIN_VALUE);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_UPPER_SHIFT_HEADROOM_variation1() {
        LocalDate start = LocalDate.MAX.minusDays(38);
        LocalDate end = LocalDate.MAX.minusDays(37);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_LOWER_REPRESENTABLE_BOUNDARY_variation1() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN.plusDays(1);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_IDENTICAL_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 10, 30, 45);
        LocalDateTime end = LocalDateTime.of(2024, 6, 15, 10, 30, 45);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_PARTIAL_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 12, 0);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 11, 59);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 12, 0);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 12, 0);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_NANO_BELOW_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 12, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 11, 59, 59, 999_999_999);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_DAY_PLUS_NANO_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 12, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 12, 0, 0, 1);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 16, 12, 0);
        LocalDateTime end = LocalDateTime.of(2024, 6, 15, 12, 1);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_EXACT_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 16, 12, 0);
        LocalDateTime end = LocalDateTime.of(2024, 6, 15, 12, 0);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_LEAP_BOUNDARY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 2, 28, 23, 30);
        LocalDateTime end = LocalDateTime.of(2024, 3, 1, 23, 30);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void INSTANT_IDENTICAL_variation1() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = Instant.parse("2024-01-01T00:00:00Z");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void INSTANT_EXACT_86400_SECONDS_variation1() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = Instant.parse("2024-01-02T00:00:00Z");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void INSTANT_ONE_NANO_BELOW_DAY_variation1() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = Instant.parse("2024-01-01T23:59:59.999999999Z");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void INSTANT_ONE_NANO_ABOVE_DAY_variation1() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = Instant.parse("2024-01-02T00:00:00.000000001Z");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void INSTANT_NEGATIVE_PARTIAL_DAY_variation1() {
        Instant start = Instant.parse("2024-01-02T00:00:00Z");
        Instant end = Instant.parse("2024-01-01T00:00:00.000000001Z");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void INSTANT_NEGATIVE_EXACT_DAY_variation1() {
        Instant start = Instant.parse("2024-01-02T00:00:00Z");
        Instant end = Instant.parse("2024-01-01T00:00:00Z");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void INSTANT_MULTI_DAY_WITH_REMAINDER_variation1() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = Instant.parse("2024-01-04T12:00:00Z");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_EQUAL_OFFSET_variation1() {
        OffsetDateTime start = OffsetDateTime.parse("2024-01-01T08:00:00+05:30");
        OffsetDateTime end = OffsetDateTime.parse("2024-01-03T08:00:00+05:30");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_PARTIAL_DAY_variation1() {
        OffsetDateTime start = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        OffsetDateTime end = OffsetDateTime.parse("2024-01-02T00:00:00+02:00");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_OVER_DAY_variation1() {
        OffsetDateTime start = OffsetDateTime.parse("2024-01-01T00:00:00+02:00");
        OffsetDateTime end = OffsetDateTime.parse("2024-01-02T00:00:00+01:00");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_EXTREME_OFFSETS_ZERO_variation1() {
        OffsetDateTime start = OffsetDateTime.of(
                LocalDateTime.of(2024, 6, 15, 12, 0), ZoneOffset.MAX);
        OffsetDateTime end = OffsetDateTime.of(
                LocalDateTime.of(2024, 6, 15, 12, 0), ZoneOffset.MIN);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_REVERSED_OFFSETS_variation1() {
        OffsetDateTime start = OffsetDateTime.parse("2024-01-03T00:00:00Z");
        OffsetDateTime end = OffsetDateTime.parse("2024-01-01T00:00:00+02:00");
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_SPRING_DST_DAY_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                LocalDateTime.of(2024, 3, 9, 12, 0), zone);
        ZonedDateTime end = ZonedDateTime.of(
                LocalDateTime.of(2024, 3, 10, 12, 0), zone);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_AUTUMN_DST_DAY_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                LocalDateTime.of(2024, 11, 2, 12, 0), zone);
        ZonedDateTime end = ZonedDateTime.of(
                LocalDateTime.of(2024, 11, 3, 12, 0), zone);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_GAP_ADJACENT_PARTIAL_DAY_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                LocalDateTime.of(2024, 3, 10, 1, 30), zone);
        ZonedDateTime end = ZonedDateTime.of(
                LocalDateTime.of(2024, 3, 10, 3, 30), zone);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_OVERLAP_OCCURRENCES_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        LocalDateTime overlapTime = LocalDateTime.of(2024, 11, 3, 1, 30);
        ZonedDateTime start = ZonedDateTime.ofLocal(
                overlapTime, zone, ZoneOffset.of("-04:00"));
        ZonedDateTime end = ZonedDateTime.ofLocal(
                overlapTime, zone, ZoneOffset.of("-05:00"));
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_MULTI_MONTH_DST_SPAN_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                LocalDateTime.of(2024, 1, 15, 9, 0), zone);
        ZonedDateTime end = ZonedDateTime.of(
                LocalDateTime.of(2024, 5, 15, 9, 0), zone);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void JAPANESE_DATE_ERA_BOUNDARY_variation1() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2019, 4, 30));
        JapaneseDate end = JapaneseDate.from(LocalDate.of(2019, 5, 1));
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void HIJRAH_DATE_MONTH_BOUNDARY_variation1() {
        HijrahDate start = findHijrahMonthBoundaryStart();
        HijrahDate end = start.plus(1, ChronoUnit.DAYS);
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void THAI_BUDDHIST_DATE_LEAP_CROSSING_variation1() {
        ThaiBuddhistDate start = ThaiBuddhistDate.from(LocalDate.of(2024, 2, 28));
        ThaiBuddhistDate end = ThaiBuddhistDate.from(LocalDate.of(2024, 3, 1));
        exerciseMetamorphicRelation(start, end);
    }

    @Test
    public void MINGUO_DATE_NEGATIVE_GAP_variation1() {
        MinguoDate start = MinguoDate.from(LocalDate.of(2024, 6, 20));
        MinguoDate end = start.minus(10, ChronoUnit.DAYS);
        exerciseMetamorphicRelation(start, end);
    }
}
