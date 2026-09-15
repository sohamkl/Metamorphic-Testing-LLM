import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    @Test
    void LOCAL_DATE_EQUAL_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 15);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_FORWARD_ONE_DAY_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 16);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_BACKWARD_ONE_DAY_variation1() {
        Temporal start = LocalDate.of(2024, 6, 16);
        Temporal end = LocalDate.of(2024, 6, 15);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_POSITIVE_MULTI_DAY_variation1() {
        Temporal start = LocalDate.of(2024, 4, 3);
        Temporal end = LocalDate.of(2024, 4, 15);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_NEGATIVE_MULTI_DAY_variation1() {
        Temporal start = LocalDate.of(2024, 4, 15);
        Temporal end = LocalDate.of(2024, 4, 3);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_SHORT_MONTH_BOUNDARY_variation1() {
        Temporal start = LocalDate.of(2024, 4, 30);
        Temporal end = LocalDate.of(2024, 5, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_variation1() {
        Temporal start = LocalDate.of(2023, 12, 31);
        Temporal end = LocalDate.of(2024, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_INCLUDED_variation1() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_COMMON_YEAR_FEBRUARY_variation1() {
        Temporal start = LocalDate.of(2023, 2, 28);
        Temporal end = LocalDate.of(2023, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_NON_LEAP_CENTURY_variation1() {
        Temporal start = LocalDate.of(2100, 2, 28);
        Temporal end = LocalDate.of(2100, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_PROLEPTIC_YEAR_ZERO_variation1() {
        Temporal start = LocalDate.of(0, 2, 28);
        Temporal end = LocalDate.of(0, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_BCE_CE_BOUNDARY_variation1() {
        Temporal start = LocalDate.of(0, 12, 31);
        Temporal end = LocalDate.of(1, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MINIMUM_SENTINEL_variation1() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN;
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MAXIMUM_FOLLOW_UP_SAFE_variation1() {
        Temporal start = LocalDate.MAX.minusDays(37);
        Temporal end = LocalDate.MAX.minusDays(37);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_INTEGER_MAX_RESULT_variation1() {
        LocalDate anchor = LocalDate.of(0, 1, 1);
        Temporal start = anchor;
        Temporal end = anchor.plusDays(2147483647L);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_INTEGER_MIN_RESULT_variation1() {
        LocalDate anchor = LocalDate.of(0, 1, 1);
        Temporal start = anchor.plusDays(2147483648L);
        Temporal end = anchor;
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_REVERSE_LEAP_SPAN_variation1() {
        Temporal start = LocalDate.of(2024, 3, 1);
        Temporal end = LocalDate.of(2024, 2, 28);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MULTI_YEAR_SPAN_variation1() {
        Temporal start = LocalDate.of(2019, 1, 1);
        Temporal end = LocalDate.of(2025, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_SAME_INSTANT_FIELDS_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 30, 45, 123456789);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 30, 45, 123456789);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_POSITIVE_UNDER_ONE_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 0, 0);
        Temporal end = LocalDateTime.of(2024, 6, 15, 23, 59, 59, 999999999);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_ONE_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 12, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_POSITIVE_PARTIAL_SECOND_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 0, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 23, 59, 59, 999999999);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_UNDER_ONE_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 23, 0);
        Temporal end = LocalDateTime.of(2024, 6, 15, 0, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_NEGATIVE_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 16, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_MIDNIGHT_CROSSING_PARTIAL_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 23, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 1, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_MAXIMUM_FOLLOW_UP_SAFE_variation1() {
        Temporal start = LocalDateTime.MAX.minusDays(37);
        Temporal end = LocalDateTime.MAX.minusDays(37);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_EQUAL_variation1() {
        Temporal start = Instant.parse("2024-06-15T12:00:00Z");
        Temporal end = Instant.parse("2024-06-15T12:00:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_NANOSECOND_BELOW_DAY_variation1() {
        Instant base = Instant.parse("2024-06-15T00:00:00Z");
        Temporal start = base;
        Temporal end = base.plus(1, ChronoUnit.DAYS).minusNanos(1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_EXACT_DAY_variation1() {
        Temporal start = Instant.parse("2024-06-15T00:00:00Z");
        Temporal end = Instant.parse("2024-06-16T00:00:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_NEGATIVE_PARTIAL_DAY_variation1() {
        Temporal start = Instant.parse("2024-06-15T12:00:00Z");
        Temporal end = Instant.parse("2024-06-15T00:00:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_MAXIMUM_FOLLOW_UP_SAFE_variation1() {
        Temporal start = Instant.MAX.minus(37, ChronoUnit.DAYS);
        Temporal end = Instant.MAX.minus(37, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_EQUAL_OFFSET_DAY_variation1() {
        Temporal start = OffsetDateTime.parse("2024-06-15T12:00:00+05:30");
        Temporal end = OffsetDateTime.parse("2024-06-16T12:00:00+05:30");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_SAME_INSTANT_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        Temporal end = OffsetDateTime.parse("2023-12-31T19:00:00-05:00");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_EXTREME_OFFSET_POSITIVE_DAY_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00+14:00");
        Temporal end = OffsetDateTime.parse("2024-01-01T00:00:00-12:00");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_EXTREME_OFFSET_NEGATIVE_DAY_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00-12:00");
        Temporal end = OffsetDateTime.parse("2024-01-01T00:00:00+14:00");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_UTC_EXACT_DAY_variation1() {
        Temporal start = ZonedDateTime.parse("2024-06-15T12:00:00Z[UTC]");
        Temporal end = ZonedDateTime.parse("2024-06-16T12:00:00Z[UTC]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_FORWARD_DAY_variation1() {
        Temporal start = ZonedDateTime.parse(
                "2024-03-09T12:00:00-05:00[America/New_York]");
        Temporal end = ZonedDateTime.parse(
                "2024-03-10T12:00:00-04:00[America/New_York]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_FALL_BACK_DAY_variation1() {
        Temporal start = ZonedDateTime.parse(
                "2024-11-02T12:00:00-04:00[America/New_York]");
        Temporal end = ZonedDateTime.parse(
                "2024-11-03T12:00:00-05:00[America/New_York]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_OVERLAP_REPEATED_LOCAL_TIME_variation1() {
        Temporal start = ZonedDateTime.parse(
                "2024-11-03T01:30:00-04:00[America/New_York]");
        Temporal end = ZonedDateTime.parse(
                "2024-11-03T01:30:00-05:00[America/New_York]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_NEGATIVE_ACROSS_SPRING_variation1() {
        Temporal start = ZonedDateTime.parse(
                "2024-03-10T12:00:00-04:00[America/New_York]");
        Temporal end = ZonedDateTime.parse(
                "2024-03-09T12:00:00-05:00[America/New_York]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAPANESE_DATE_ERA_TRANSITION_variation1() {
        Temporal start = JapaneseDate.from(LocalDate.of(2019, 4, 30));
        Temporal end = JapaneseDate.from(LocalDate.of(2019, 5, 1));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void THAI_BUDDHIST_DATE_LEAP_SPAN_variation1() {
        Temporal start = ThaiBuddhistDate.from(LocalDate.of(2024, 2, 28));
        Temporal end = ThaiBuddhistDate.from(LocalDate.of(2024, 3, 1));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MINGUO_DATE_NEGATIVE_INTERVAL_variation1() {
        Temporal start = MinguoDate.from(LocalDate.of(2024, 1, 10));
        Temporal end = MinguoDate.from(LocalDate.of(2024, 1, 1));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIJRAH_DATE_EPOCH_DAY_INTERVAL_variation1() {
        Temporal start = HijrahDate.from(LocalDate.of(2024, 5, 1));
        Temporal end = HijrahDate.from(LocalDate.of(2024, 5, 11));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
