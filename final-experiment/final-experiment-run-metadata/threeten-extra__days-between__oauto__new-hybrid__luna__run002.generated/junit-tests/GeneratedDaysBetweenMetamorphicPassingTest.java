import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static Temporal[] generateFollowUp(Temporal startDateInclusive, Temporal endDateExclusive) {
        return new Temporal[]{
                startDateInclusive.plus(37, ChronoUnit.DAYS),
                endDateExclusive.plus(37, ChronoUnit.DAYS)
        };
    }

    private static void exercise(Temporal startDateInclusive, Temporal endDateExclusive) {
        Temporal[] followUp = generateFollowUp(startDateInclusive, endDateExclusive);
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EQUAL_LOCAL_DATES_variation1() {
        exercise(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
    }

    @Test
    public void EQUAL_LOCAL_DATES_variation2() {
        exercise(LocalDate.of(2020, 2, 29), LocalDate.of(2020, 2, 29));
    }

    @Test
    public void EQUAL_LOCAL_DATES_variation3() {
        exercise(LocalDate.of(2023, 12, 31), LocalDate.of(2023, 12, 31));
    }

    @Test
    public void SMALL_POSITIVE_LOCAL_DATE_GAPS_variation1() {
        exercise(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 2, 29));
    }

    @Test
    public void SMALL_POSITIVE_LOCAL_DATE_GAPS_variation2() {
        exercise(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 3));
    }

    @Test
    public void SMALL_POSITIVE_LOCAL_DATE_GAPS_variation3() {
        exercise(LocalDate.of(2024, 5, 25), LocalDate.of(2024, 5, 31));
    }

    @Test
    public void SMALL_POSITIVE_LOCAL_DATE_GAPS_variation4() {
        exercise(LocalDate.of(2024, 12, 20), LocalDate.of(2025, 1, 2));
    }

    @Test
    public void SMALL_NEGATIVE_LOCAL_DATE_GAPS_variation1() {
        exercise(LocalDate.of(2024, 4, 2), LocalDate.of(2024, 4, 1));
    }

    @Test
    public void SMALL_NEGATIVE_LOCAL_DATE_GAPS_variation2() {
        exercise(LocalDate.of(2024, 4, 10), LocalDate.of(2024, 4, 8));
    }

    @Test
    public void SMALL_NEGATIVE_LOCAL_DATE_GAPS_variation3() {
        exercise(LocalDate.of(2024, 6, 20), LocalDate.of(2024, 6, 14));
    }

    @Test
    public void SMALL_NEGATIVE_LOCAL_DATE_GAPS_variation4() {
        exercise(LocalDate.of(2025, 1, 2), LocalDate.of(2024, 12, 20));
    }

    @Test
    public void LOCAL_DATETIME_EXACT_DAY_BOUNDARIES_variation1() {
        exercise(
                LocalDateTime.of(2024, 2, 29, 9, 30),
                LocalDateTime.of(2024, 3, 1, 9, 30));
    }

    @Test
    public void LOCAL_DATETIME_EXACT_DAY_BOUNDARIES_variation2() {
        exercise(
                LocalDateTime.of(2024, 1, 1, 9, 30),
                LocalDateTime.of(2024, 1, 8, 9, 30));
    }

    @Test
    public void LOCAL_DATETIME_EXACT_DAY_BOUNDARIES_variation3() {
        exercise(
                LocalDateTime.of(2024, 7, 8, 9, 30),
                LocalDateTime.of(2024, 6, 1, 9, 30));
    }

    @Test
    public void LOCAL_DATETIME_PARTIAL_DAY_BOUNDARIES_variation1() {
        exercise(
                LocalDateTime.of(2024, 1, 10, 12, 0),
                LocalDateTime.of(2024, 1, 11, 11, 59, 59));
    }

    @Test
    public void LOCAL_DATETIME_PARTIAL_DAY_BOUNDARIES_variation2() {
        exercise(
                LocalDateTime.of(2024, 1, 11, 11, 59, 59),
                LocalDateTime.of(2024, 1, 10, 12, 0));
    }

    @Test
    public void LOCAL_DATETIME_PARTIAL_DAY_BOUNDARIES_variation3() {
        exercise(
                LocalDateTime.of(2024, 1, 10, 12, 0),
                LocalDateTime.of(2024, 1, 10, 12, 0, 1));
    }

    @Test
    public void OFFSET_DATETIME_SAME_OFFSET_variation1() {
        exercise(
                OffsetDateTime.of(2024, 3, 10, 12, 0, 0, 0, ZoneOffset.ofHours(2)),
                OffsetDateTime.of(2024, 3, 9, 13, 0, 0, 0, ZoneOffset.ofHours(2)));
    }

    @Test
    public void OFFSET_DATETIME_SAME_OFFSET_variation2() {
        exercise(
                OffsetDateTime.of(2024, 6, 1, 8, 0, 0, 0, ZoneOffset.ofHours(2)),
                OffsetDateTime.of(2024, 6, 8, 8, 0, 0, 0, ZoneOffset.ofHours(2)));
    }

    @Test
    public void OFFSET_DATETIME_SAME_OFFSET_variation3() {
        exercise(
                OffsetDateTime.of(2024, 6, 15, 12, 0, 0, 0, ZoneOffset.ofHours(2)),
                OffsetDateTime.of(2024, 6, 16, 11, 59, 59, 0, ZoneOffset.ofHours(2)));
    }

    @Test
    public void OFFSET_DATETIME_DIFFERING_OFFSETS_variation1() {
        exercise(
                OffsetDateTime.of(2024, 3, 10, 12, 0, 0, 0, ZoneOffset.ofHours(14)),
                OffsetDateTime.of(2024, 3, 9, 12, 0, 0, 0, ZoneOffset.ofHours(-12)));
    }

    @Test
    public void OFFSET_DATETIME_DIFFERING_OFFSETS_variation2() {
        exercise(
                OffsetDateTime.of(2024, 3, 9, 12, 0, 0, 0, ZoneOffset.ofHours(-12)),
                OffsetDateTime.of(2024, 3, 10, 12, 0, 0, 0, ZoneOffset.ofHours(14)));
    }

    @Test
    public void OFFSET_DATETIME_DIFFERING_OFFSETS_variation3() {
        exercise(
                OffsetDateTime.of(2024, 12, 31, 23, 0, 0, 0, ZoneOffset.ofHours(14)),
                OffsetDateTime.of(2025, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(-12)));
    }

    @Test
    public void ZONED_DATETIME_ORDINARY_ZONE_variation1() {
        exercise(
                ZonedDateTime.of(2024, 5, 20, 8, 0, 0, 0, ZoneId.of("Europe/Paris")),
                ZonedDateTime.of(2024, 5, 19, 8, 0, 0, 0, ZoneId.of("Europe/Paris")));
    }

    @Test
    public void ZONED_DATETIME_ORDINARY_ZONE_variation2() {
        exercise(
                ZonedDateTime.of(2024, 6, 1, 8, 0, 0, 0, ZoneId.of("America/New_York")),
                ZonedDateTime.of(2024, 6, 1, 8, 0, 0, 0, ZoneId.of("America/New_York")));
    }

    @Test
    public void ZONED_DATETIME_ORDINARY_ZONE_variation3() {
        exercise(
                ZonedDateTime.of(2024, 8, 20, 8, 0, 0, 0, ZoneId.of("America/New_York")),
                ZonedDateTime.of(2024, 8, 27, 8, 0, 0, 0, ZoneId.of("America/New_York")));
    }

    @Test
    public void ZONED_DATETIME_DST_VICINITY_variation1() {
        exercise(
                ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, ZoneId.of("America/New_York")),
                ZonedDateTime.of(2024, 3, 10, 12, 0, 0, 0, ZoneId.of("America/New_York")));
    }

    @Test
    public void ZONED_DATETIME_DST_VICINITY_variation2() {
        exercise(
                ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, ZoneId.of("America/New_York")),
                ZonedDateTime.of(2024, 11, 4, 12, 0, 0, 0, ZoneId.of("America/New_York")));
    }

    @Test
    public void ZONED_DATETIME_DST_VICINITY_variation3() {
        exercise(
                ZonedDateTime.of(2024, 3, 10, 1, 30, 0, 0, ZoneId.of("America/New_York")),
                ZonedDateTime.of(2024, 3, 10, 3, 30, 0, 0, ZoneId.of("America/New_York")));
    }

    @Test
    public void ZONED_DATETIME_DST_VICINITY_variation4() {
        exercise(
                ZonedDateTime.of(2024, 11, 4, 12, 0, 0, 0, ZoneId.of("America/New_York")),
                ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, ZoneId.of("America/New_York")));
    }

    @Test
    public void MONTH_AND_YEAR_BOUNDARIES_variation1() {
        exercise(LocalDate.of(2024, 1, 31), LocalDate.of(2024, 2, 1));
    }

    @Test
    public void MONTH_AND_YEAR_BOUNDARIES_variation2() {
        exercise(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 1));
    }

    @Test
    public void MONTH_AND_YEAR_BOUNDARIES_variation3() {
        exercise(LocalDate.of(2024, 4, 30), LocalDate.of(2024, 5, 31));
    }

    @Test
    public void LEAP_DAY_BOUNDARIES_variation1() {
        exercise(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
    }

    @Test
    public void LEAP_DAY_BOUNDARIES_variation2() {
        exercise(LocalDate.of(2024, 2, 29), LocalDate.of(2024, 3, 1));
    }

    @Test
    public void LEAP_DAY_BOUNDARIES_variation3() {
        exercise(LocalDate.of(2023, 2, 28), LocalDate.of(2023, 3, 1));
    }

    @Test
    public void LOCAL_DATE_NEAR_MINIMUM_variation1() {
        exercise(LocalDate.MIN, LocalDate.MIN.plusDays(1));
    }

    @Test
    public void LOCAL_DATE_NEAR_MINIMUM_variation2() {
        exercise(LocalDate.MIN, LocalDate.MIN.plusDays(37));
    }

    @Test
    public void LOCAL_DATE_NEAR_MAXIMUM_variation1() {
        exercise(LocalDate.MAX.minusDays(100), LocalDate.MAX.minusDays(37));
    }

    @Test
    public void LOCAL_DATE_NEAR_MAXIMUM_variation2() {
        exercise(LocalDate.MAX.minusDays(37), LocalDate.MAX.minusDays(37));
    }

    @Test
    public void INTEGER_MAXIMUM_DAY_COUNT_variation1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        exercise(start, start.plusDays(Integer.MAX_VALUE));
    }

    @Test
    public void INTEGER_MINIMUM_DAY_COUNT_variation1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        exercise(start, start.minusDays(2_147_483_648L));
    }

    @Test
    public void JAPANESE_DATE_SUPPORTED_CHRONOLOGY_variation1() {
        exercise(
                JapaneseDate.of(2024, 1, 1),
                JapaneseDate.of(2024, 1, 1));
    }

    @Test
    public void JAPANESE_DATE_SUPPORTED_CHRONOLOGY_variation2() {
        exercise(
                JapaneseDate.of(2024, 2, 28),
                JapaneseDate.of(2024, 2, 29));
    }

    @Test
    public void LARGE_BUT_NON_BOUNDARY_LOCAL_DATE_GAPS_variation1() {
        exercise(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 1).plusDays(1_000_000L));
    }

    @Test
    public void LARGE_BUT_NON_BOUNDARY_LOCAL_DATE_GAPS_variation2() {
        exercise(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 1).minusDays(1_000_000L));
    }
}
