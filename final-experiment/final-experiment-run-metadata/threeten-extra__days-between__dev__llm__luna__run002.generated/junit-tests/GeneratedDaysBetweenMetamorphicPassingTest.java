import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    @Test
    void LOCAL_DATE_EQUAL_1_ordinaryDate() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 15);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_ONE_DAY_FORWARD_1_leapTransition() {
        Temporal start = LocalDateTime.of(2020, 2, 28, 13, 45, 20);
        Temporal end = LocalDateTime.of(2020, 2, 29, 13, 45, 20);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_ONE_DAY_REVERSE_1_yearBoundary() {
        Temporal start =
                OffsetDateTime.of(2024, 1, 2, 10, 15, 0, 0, ZoneOffset.ofHours(2));
        Temporal end =
                OffsetDateTime.of(2024, 1, 1, 10, 15, 0, 0, ZoneOffset.ofHours(2));

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MULTI_DAY_FORWARD_1_ordinaryInterval() {
        Temporal start =
                ZonedDateTime.of(2024, 8, 10, 9, 30, 0, 0, ZoneOffset.UTC);
        Temporal end =
                ZonedDateTime.of(2024, 8, 22, 9, 30, 0, 0, ZoneOffset.UTC);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_EXACT_SHIFT_DISTANCE_1_instantInterval() {
        Temporal start = Instant.parse("2022-03-01T00:00:00Z");
        Temporal end = ((Instant) start).plusSeconds(37L * 24L * 60L * 60L);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_1_japaneseDate() {
        Temporal start = JapaneseDate.of(2020, 2, 28);
        Temporal end = JapaneseDate.of(2020, 3, 2);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_1_crossYear() {
        Temporal start = LocalDate.of(2023, 12, 30);
        Temporal end = LocalDate.of(2024, 1, 2);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MINIMUM_WITH_HEADROOM_1_minimumDate() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN.plusDays(1);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MAXIMUM_WITH_HEADROOM_1_maximumDate() {
        Temporal end = LocalDate.MAX.minusDays(37);
        Temporal start = ((LocalDate) end).minusDays(1);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_INTEGER_MAX_DISTANCE_1_largePositiveDistance() {
        Temporal start = LocalDate.of(2000, 1, 1);
        Temporal end = ((LocalDate) start).plusDays(Integer.MAX_VALUE);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_INTEGER_MIN_DISTANCE_1_largeNegativeDistance() {
        Temporal end = LocalDate.of(2000, 1, 1);
        Temporal start = ((LocalDate) end).plusDays(2147483648L);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_1_sameNanosecond() {
        Temporal start =
                LocalDateTime.of(2024, 5, 10, 23, 59, 58, 123456789);
        Temporal end = ((LocalDateTime) start).plusDays(1);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_ZERO_SUBDAY_INTERVAL_1_crossingMidnight() {
        Temporal start = LocalDateTime.of(2024, 7, 1, 23, 30);
        Temporal end = LocalDateTime.of(2024, 7, 2, 0, 15);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_WHOLE_DAY_1_twoDaysReverse() {
        Temporal start =
                LocalDateTime.of(2025, 1, 15, 8, 20, 10, 500);
        Temporal end = ((LocalDateTime) start).minusDays(2);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_1_fixedOffset() {
        Temporal start =
                OffsetDateTime.of(
                        2024, 9, 10, 11, 30, 0, 0,
                        ZoneOffset.ofHoursMinutes(5, 30));
        Temporal end = ((OffsetDateTime) start).plusDays(5);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERING_OFFSETS_1_actualWholeDays() {
        Temporal start =
                OffsetDateTime.of(
                        2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(1));
        Temporal end =
                OffsetDateTime.of(
                        2024, 1, 6, 1, 0, 0, 0, ZoneOffset.ofHours(2));

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_UTC_1_utcInterval() {
        Temporal start =
                ZonedDateTime.of(
                        2024, 3, 10, 6, 45, 0, 0, ZoneOffset.UTC);
        Temporal end = ((ZonedDateTime) start).plusDays(9);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_EXACT_DAY_1_threeDays() {
        Temporal start = Instant.parse("2024-04-01T12:34:56.123456789Z");
        Temporal end = ((Instant) start).plusSeconds(3L * 24L * 60L * 60L);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_SUBDAY_INTERVAL_1_lessThanOneDay() {
        Temporal start = Instant.parse("2024-11-01T23:30:00Z");
        Temporal end =
                ((Instant) start).plusSeconds(7L * 60L * 60L + 15L * 60L);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAPANESE_DATE_WHOLE_DAY_1_fourDays() {
        Temporal start = JapaneseDate.of(2021, 5, 10);
        Temporal end = JapaneseDate.of(2021, 5, 14);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAPANESE_DATE_REVERSE_INTERVAL_1_fourDaysReverse() {
        Temporal start = JapaneseDate.of(2022, 10, 20);
        Temporal end = JapaneseDate.of(2022, 10, 16);

        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
