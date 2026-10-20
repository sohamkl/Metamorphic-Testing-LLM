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
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static void exerciseRelation(Temporal start, Temporal end) {
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0],
                (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EQUAL_ENDPOINTS_ZERO_SENTINEL_localDate() {
        LocalDate value = LocalDate.of(2025, 6, 17);
        exerciseRelation(value, value);
    }

    @Test
    void EQUAL_ENDPOINTS_ZERO_SENTINEL_localDateTime() {
        LocalDateTime value = LocalDateTime.of(2024, 1, 31, 23, 45, 12);
        exerciseRelation(value, value);
    }

    @Test
    void EQUAL_ENDPOINTS_ZERO_SENTINEL_instant() {
        Instant value = Instant.parse("2023-12-31T23:59:59Z");
        exerciseRelation(value, value);
    }

    @Test
    void EXACTLY_ONE_DAY_FORWARD_offsetDateTime() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 2, 29, 8, 15, 0, 0, ZoneOffset.ofHours(2));
        Temporal end = start.plus(1, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void EXACTLY_ONE_DAY_FORWARD_zonedDateTime() {
        ZonedDateTime start = ZonedDateTime.of(
                2023, 2, 28, 14, 0, 0, 0, ZoneId.of("UTC"));
        Temporal end = start.plus(1, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void EXACTLY_ONE_DAY_REVERSE_japaneseDate() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2022, 8, 20));
        Temporal end = start.plus(-1, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void EXACTLY_ONE_DAY_REVERSE_localDate() {
        LocalDate start = LocalDate.of(2025, 4, 1);
        Temporal end = start.plus(-1, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void SHORT_POSITIVE_NON_SINGLETON_GAP_twoDaysLocalDateTime() {
        LocalDateTime start = LocalDateTime.of(2022, 12, 31, 7, 30);
        Temporal end = start.plus(2, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void SHORT_POSITIVE_NON_SINGLETON_GAP_sixDaysInstant() {
        Instant start = Instant.parse("2024-02-28T16:00:00Z");
        Temporal end = start.plus(6, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void SHORT_NEGATIVE_NON_SINGLETON_GAP_twoDaysOffsetDateTime() {
        OffsetDateTime start = OffsetDateTime.of(
                2023, 3, 2, 6, 10, 0, 0, ZoneOffset.ofHours(-6));
        Temporal end = start.plus(-2, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void SHORT_NEGATIVE_NON_SINGLETON_GAP_sixDaysZonedDateTime() {
        ZonedDateTime start = ZonedDateTime.of(
                2025, 7, 18, 11, 20, 0, 0, ZoneId.of("Asia/Tokyo"));
        Temporal end = start.plus(-6, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void WHOLE_WEEK_MAGNITUDES_positiveHijrahDate() {
        HijrahDate start = HijrahDate.from(LocalDate.of(2024, 5, 12));
        Temporal end = start.plus(7, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void WHOLE_WEEK_MAGNITUDES_negativeLocalDate() {
        LocalDate start = LocalDate.of(2025, 1, 8);
        Temporal end = start.plus(-14, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void MONTH_BOUNDARY_CROSSING_forward() {
        LocalDate start = LocalDate.of(2024, 1, 30);
        LocalDate end = LocalDate.of(2024, 2, 2);
        exerciseRelation(start, end);
    }

    @Test
    void MONTH_BOUNDARY_CROSSING_reverse() {
        LocalDate start = LocalDate.of(2025, 5, 2);
        LocalDate end = LocalDate.of(2025, 4, 29);
        exerciseRelation(start, end);
    }

    @Test
    void YEAR_BOUNDARY_CROSSING_forward() {
        LocalDate start = LocalDate.of(2023, 12, 30);
        LocalDate end = LocalDate.of(2024, 1, 2);
        exerciseRelation(start, end);
    }

    @Test
    void YEAR_BOUNDARY_CROSSING_reverse() {
        LocalDate start = LocalDate.of(2026, 1, 2);
        LocalDate end = LocalDate.of(2025, 12, 30);
        exerciseRelation(start, end);
    }

    @Test
    void LEAP_DAY_INTERVAL_forward() {
        LocalDate start = LocalDate.of(2024, 2, 27);
        LocalDate end = LocalDate.of(2024, 3, 1);
        exerciseRelation(start, end);
    }

    @Test
    void LEAP_DAY_INTERVAL_reverse() {
        LocalDate start = LocalDate.of(2020, 3, 1);
        LocalDate end = LocalDate.of(2020, 2, 27);
        exerciseRelation(start, end);
    }

    @Test
    void NON_LEAP_FEBRUARY_BOUNDARY_forward() {
        LocalDate start = LocalDate.of(2023, 2, 28);
        LocalDate end = LocalDate.of(2023, 3, 1);
        exerciseRelation(start, end);
    }

    @Test
    void NON_LEAP_FEBRUARY_BOUNDARY_reverse() {
        LocalDate start = LocalDate.of(2025, 3, 1);
        LocalDate end = LocalDate.of(2025, 2, 28);
        exerciseRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_POSITIVE_PARTIAL_DAY_twentyThreeHours() {
        LocalDateTime start = LocalDateTime.of(2024, 4, 30, 12, 0);
        Temporal end = start.plus(23, ChronoUnit.HOURS);
        exerciseRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_POSITIVE_PARTIAL_DAY_fortyNineHours() {
        LocalDateTime start = LocalDateTime.of(2023, 12, 30, 18, 30);
        Temporal end = start.plus(49, ChronoUnit.HOURS);
        exerciseRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_DAY_minusTwentyThreeHours() {
        LocalDateTime start = LocalDateTime.of(2024, 3, 1, 10, 15);
        Temporal end = start.plus(-23, ChronoUnit.HOURS);
        exerciseRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_DAY_minusTwentyFiveHours() {
        LocalDateTime start = LocalDateTime.of(2023, 3, 1, 0, 30);
        Temporal end = start.plus(-25, ChronoUnit.HOURS);
        exerciseRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_WHOLE_DAYS_positiveTen() {
        LocalDateTime start = LocalDateTime.of(2022, 10, 15, 9, 40, 20);
        Temporal end = start.plus(10, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_WHOLE_DAYS_negativeTen() {
        LocalDateTime start = LocalDateTime.of(2026, 2, 10, 21, 5, 45);
        Temporal end = start.plus(-10, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void INSTANT_PARTIAL_24_HOUR_UNITS_positiveTwentyThreeHours() {
        Instant start = Instant.parse("2024-01-01T01:30:00Z");
        Temporal end = start.plus(23, ChronoUnit.HOURS);
        exerciseRelation(start, end);
    }

    @Test
    void INSTANT_PARTIAL_24_HOUR_UNITS_negativeTwentyThreeHours() {
        Instant start = Instant.parse("2025-02-28T22:00:00Z");
        Temporal end = start.plus(-23, ChronoUnit.HOURS);
        exerciseRelation(start, end);
    }

    @Test
    void INSTANT_EXACT_MULTI_DAY_UNITS_positiveSeventyTwoHours() {
        Instant start = Instant.parse("2023-06-10T12:00:00Z");
        Temporal end = start.plus(72, ChronoUnit.HOURS);
        exerciseRelation(start, end);
    }

    @Test
    void INSTANT_EXACT_MULTI_DAY_UNITS_negativeSeventyTwoHours() {
        Instant start = Instant.parse("2024-08-20T04:15:00Z");
        Temporal end = start.plus(-72, ChronoUnit.HOURS);
        exerciseRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_positiveFiveDays() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 6, 28, 13, 20, 0, 0, ZoneOffset.ofHoursMinutes(5, 30));
        Temporal end = start.plus(5, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_negativeFiveDays() {
        OffsetDateTime start = OffsetDateTime.of(
                2025, 1, 3, 19, 45, 0, 0, ZoneOffset.ofHours(-4));
        Temporal end = start.plus(-5, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_positiveTwoDays() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 2, 28, 10, 0, 0, 0, ZoneOffset.ofHours(2));
        OffsetDateTime end = start.plusDays(2)
                .withOffsetSameInstant(ZoneOffset.ofHours(-5));
        exerciseRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_negativeTwoDays() {
        OffsetDateTime start = OffsetDateTime.of(
                2025, 3, 1, 8, 30, 0, 0, ZoneOffset.ofHoursMinutes(5, 30));
        OffsetDateTime end = start.minusDays(2)
                .withOffsetSameInstant(ZoneOffset.ofHours(-4));
        exerciseRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_ORDINARY_DAYS_positiveFour() {
        ZonedDateTime start = ZonedDateTime.of(
                2024, 1, 10, 15, 0, 0, 0, ZoneId.of("Europe/Paris"));
        Temporal end = start.plus(4, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_ORDINARY_DAYS_negativeFour() {
        ZonedDateTime start = ZonedDateTime.of(
                2025, 7, 20, 7, 25, 0, 0, ZoneId.of("Australia/Sydney"));
        Temporal end = start.plus(-4, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_DAYLIGHT_SAVING_GAP_forward() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                2024, 3, 9, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(
                2024, 3, 11, 12, 0, 0, 0, zone);
        exerciseRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_DAYLIGHT_SAVING_GAP_reverse() {
        ZoneId zone = ZoneId.of("Europe/Berlin");
        ZonedDateTime start = ZonedDateTime.of(
                2025, 3, 31, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(
                2025, 3, 29, 12, 0, 0, 0, zone);
        exerciseRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_DAYLIGHT_SAVING_OVERLAP_forward() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                2024, 11, 2, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(
                2024, 11, 4, 12, 0, 0, 0, zone);
        exerciseRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_DAYLIGHT_SAVING_OVERLAP_reverse() {
        ZoneId zone = ZoneId.of("Europe/London");
        ZonedDateTime start = ZonedDateTime.of(
                2025, 10, 27, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(
                2025, 10, 25, 12, 0, 0, 0, zone);
        exerciseRelation(start, end);
    }

    @Test
    void JAPANESE_CHRONOLOGY_DATES_positiveEight() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2021, 5, 20));
        Temporal end = start.plus(8, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void JAPANESE_CHRONOLOGY_DATES_negativeEight() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2025, 1, 3));
        Temporal end = start.plus(-8, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void HIJRAH_CHRONOLOGY_DATES_positiveNine() {
        HijrahDate start = HijrahDate.from(LocalDate.of(2024, 2, 20));
        Temporal end = start.plus(9, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void HIJRAH_CHRONOLOGY_DATES_negativeNine() {
        HijrahDate start = HijrahDate.from(LocalDate.of(2025, 8, 15));
        Temporal end = start.plus(-9, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    void COMPATIBLE_MIXED_CHRONOLOGY_PAIR_isoToJapanese() {
        LocalDate start = LocalDate.of(2023, 9, 10);
        JapaneseDate end = JapaneseDate.from(start.plusDays(12));
        exerciseRelation(start, end);
    }

    @Test
    void INTEGER_MAXIMUM_DAY_GAP_exactMaximum() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = start.plusDays(Integer.MAX_VALUE);
        exerciseRelation(start, end);
    }

    @Test
    void INTEGER_MINIMUM_DAY_GAP_exactMinimum() {
        LocalDate start = LocalDate.ofEpochDay(100);
        LocalDate end = start.plusDays(Integer.MIN_VALUE);
        exerciseRelation(start, end);
    }

    @Test
    void NEAR_INTEGER_DAY_LIMITS_positiveMaximumMinusOne() {
        LocalDate start = LocalDate.ofEpochDay(-500);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 1L);
        exerciseRelation(start, end);
    }

    @Test
    void NEAR_INTEGER_DAY_LIMITS_negativeMinimumPlusOne() {
        LocalDate start = LocalDate.ofEpochDay(500);
        LocalDate end = start.plusDays((long) Integer.MIN_VALUE + 1L);
        exerciseRelation(start, end);
    }
}
