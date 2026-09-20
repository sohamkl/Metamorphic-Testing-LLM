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

import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        if (sourceOutput.getAmount() != followUpOutput.getAmount()) {
            throw new AssertionError(
                    "Shifting both endpoints forward by the same number of days changed the day count");
        }
    }

    private void verifyRelation(Temporal start, Temporal end) {
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0],
                (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_SAME_DAY_ZERO_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 15);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_ADJACENT_FORWARD_ONE_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 16);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_ADJACENT_REVERSE_MINUS_ONE_variation1() {
        Temporal start = LocalDate.of(2024, 6, 16);
        Temporal end = LocalDate.of(2024, 6, 15);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_variation1() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_NON_LEAP_FEBRUARY_variation1() {
        Temporal start = LocalDate.of(2023, 2, 28);
        Temporal end = LocalDate.of(2023, 3, 1);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_MONTH_BOUNDARY_variation1() {
        Temporal start = LocalDate.of(2024, 1, 31);
        Temporal end = LocalDate.of(2024, 2, 2);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_variation1() {
        Temporal start = LocalDate.of(2023, 12, 31);
        Temporal end = LocalDate.of(2024, 1, 2);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_LARGE_POSITIVE_YEAR_SPAN_variation1() {
        Temporal start = LocalDate.of(2020, 1, 1);
        Temporal end = LocalDate.of(2021, 1, 1);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_MINIMUM_FOLLOW_UP_SAFE_variation1() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN.plus(1, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_MAXIMUM_FOLLOW_UP_SAFE_variation1() {
        Temporal start = LocalDate.MAX.minus(38, ChronoUnit.DAYS);
        Temporal end = LocalDate.MAX.minus(37, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_IDENTICAL_ZERO_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 30);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 30);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_POSITIVE_SUBDAY_ZERO_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 11, 59, 59, 999_999_999);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_ZERO_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 16, 11, 59, 59, 999_999_999);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 0);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_ONE_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 12, 0);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_TIME_OF_DAY_REDUCES_DATE_GAP_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 18, 0);
        Temporal end = LocalDateTime.of(2024, 6, 17, 6, 0);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_MULTI_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 20, 8, 0);
        Temporal end = LocalDateTime.of(2024, 6, 17, 8, 0);
        verifyRelation(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_NANOSECOND_BOUNDARY_variation1() {
        Temporal start = LocalDateTime.of(2024, 1, 1, 0, 0);
        Temporal end = LocalDateTime.of(2024, 1, 1, 23, 59, 59, 999_999_999);
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_EXACT_DAY_ONE_variation1() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-02T00:00:00Z");
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_POSITIVE_SUBDAY_ZERO_variation1() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T23:59:59Z");
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_NEGATIVE_EXACT_DAY_variation1() {
        Temporal start = Instant.parse("2024-01-02T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T00:00:00Z");
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_INTEGER_MAXIMUM_DAY_GAP_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plus(Integer.MAX_VALUE, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_INTEGER_MINIMUM_DAY_GAP_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plus(Integer.MIN_VALUE, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_MINIMUM_FOLLOW_UP_SAFE_variation1() {
        Temporal start = Instant.MIN;
        Temporal end = Instant.MIN.plus(1, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    void INSTANT_MAXIMUM_FOLLOW_UP_SAFE_variation1() {
        Temporal start = Instant.MAX.minus(38, ChronoUnit.DAYS);
        Temporal end = Instant.MAX.minus(37, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_DAY_variation1() {
        Temporal start = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(2));
        Temporal end = OffsetDateTime.of(2024, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(2));
        verifyRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSET_SUBDAY_variation1() {
        Temporal start = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        Temporal end = OffsetDateTime.of(2024, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(1));
        verifyRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSET_FULL_DAY_variation1() {
        Temporal start = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(1));
        Temporal end = OffsetDateTime.of(2024, 1, 2, 1, 0, 0, 0, ZoneOffset.ofHours(2));
        verifyRelation(start, end);
    }

    @Test
    void OFFSET_DATE_TIME_REVERSED_PARTIAL_ZERO_variation1() {
        Temporal start = OffsetDateTime.of(2024, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(1));
        Temporal end = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_TWO_CALENDAR_DAYS_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 3, 11, 12, 0, 0, 0, zone);
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_AUTUMN_DST_TWO_CALENDAR_DAYS_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 11, 4, 12, 0, 0, 0, zone);
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_SUBDAY_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 3, 10, 11, 0, 0, 0, zone);
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_FIXED_OFFSET_ZONE_variation1() {
        ZoneId zone = ZoneId.of("UTC");
        Temporal start = ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 1, 4, 0, 0, 0, 0, zone);
        verifyRelation(start, end);
    }

    @Test
    void ZONED_DATE_TIME_REVERSED_DST_RANGE_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 3, 11, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone);
        verifyRelation(start, end);
    }

    @Test
    void JAPANESE_DATE_ERA_BOUNDARY_variation1() {
        Temporal start = JapaneseDate.from(LocalDate.of(2019, 4, 30));
        Temporal end = JapaneseDate.from(LocalDate.of(2019, 5, 1));
        verifyRelation(start, end);
    }

    @Test
    void JAPANESE_DATE_MULTI_DAY_variation1() {
        Temporal start = JapaneseDate.from(LocalDate.of(2019, 4, 29));
        Temporal end = JapaneseDate.from(LocalDate.of(2019, 5, 2));
        verifyRelation(start, end);
    }

    @Test
    void HIJRAH_DATE_CONSECUTIVE_DAYS_variation1() {
        Temporal start = HijrahDate.of(1445, 9, 29);
        Temporal end = start.plus(1, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    void HIJRAH_DATE_MONTH_BOUNDARY_variation1() {
        Temporal start = HijrahDate.of(1445, 9, 29);
        Temporal end = start.plus(3, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    void MIXED_LOCAL_DATE_TO_LOCAL_DATE_TIME_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDateTime.of(2024, 6, 16, 23, 59, 59, 999_999_999);
        verifyRelation(start, end);
    }

    @Test
    void MIXED_LOCAL_DATE_TO_LOCAL_DATE_TIME_SAME_DATE_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDateTime.of(2024, 6, 15, 23, 59, 59, 999_999_999);
        verifyRelation(start, end);
    }
}
