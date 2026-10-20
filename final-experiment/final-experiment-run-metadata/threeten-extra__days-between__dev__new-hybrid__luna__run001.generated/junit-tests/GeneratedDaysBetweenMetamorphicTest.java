import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicTest {

    private static void check(Temporal start, Temporal end) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(start, end);

        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);

        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private static void checkOverflow(Temporal start, Temporal end) {
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(start, end);

        try {
            org.threeten.extra.Days.between(start, end);
            throw new AssertionError("Expected source invocation to overflow");
        } catch (ArithmeticException expected) {
        }

        try {
            org.threeten.extra.Days.between(
                    (Temporal) followUp[0], (Temporal) followUp[1]);
            throw new AssertionError("Expected follow-up invocation to overflow");
        } catch (ArithmeticException expected) {
        }
    }

    @Test
    void EQUAL_LOCAL_DATES_ZERO_1() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        check(date, date);
    }

    @Test
    void ONE_DAY_FORWARD_LOCAL_DATE_1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        check(start, start.plusDays(1));
    }

    @Test
    void ONE_DAY_BACKWARD_LOCAL_DATE_1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        check(start, start.minusDays(1));
    }

    @Test
    void SMALL_POSITIVE_MULTI_DAY_LOCAL_DATE_1() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        check(start, start.plusDays(2));
    }

    @Test
    void SMALL_POSITIVE_MULTI_DAY_LOCAL_DATE_2() {
        LocalDate start = LocalDate.of(2024, 3, 1);
        check(start, start.plusDays(7));
    }

    @Test
    void SMALL_POSITIVE_MULTI_DAY_LOCAL_DATE_3() {
        LocalDate start = LocalDate.of(2024, 5, 20);
        check(start, start.plusDays(10));
    }

    @Test
    void SMALL_POSITIVE_MULTI_DAY_LOCAL_DATE_4() {
        LocalDate start = LocalDate.of(2024, 7, 1);
        check(start, start.plusDays(31));
    }

    @Test
    void SMALL_NEGATIVE_MULTI_DAY_LOCAL_DATE_1() {
        LocalDate start = LocalDate.of(2024, 3, 1);
        check(start, start.minusDays(2));
    }

    @Test
    void SMALL_NEGATIVE_MULTI_DAY_LOCAL_DATE_2() {
        LocalDate start = LocalDate.of(2024, 3, 8);
        check(start, start.minusDays(7));
    }

    @Test
    void SMALL_NEGATIVE_MULTI_DAY_LOCAL_DATE_3() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        check(start, start.minusDays(10));
    }

    @Test
    void SMALL_NEGATIVE_MULTI_DAY_LOCAL_DATE_4() {
        LocalDate start = LocalDate.of(2024, 8, 31);
        check(start, start.minusDays(31));
    }

    @Test
    void MONTH_AND_YEAR_BOUNDARIES_1() {
        check(LocalDate.of(2023, 1, 31), LocalDate.of(2023, 2, 1));
    }

    @Test
    void MONTH_AND_YEAR_BOUNDARIES_2() {
        check(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
    }

    @Test
    void MONTH_AND_YEAR_BOUNDARIES_3() {
        check(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 1));
    }

    @Test
    void LEAP_DAY_BOUNDARIES_1() {
        check(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
    }

    @Test
    void LEAP_DAY_BOUNDARIES_2() {
        check(LocalDate.of(2024, 2, 29), LocalDate.of(2024, 3, 1));
    }

    @Test
    void LEAP_DAY_BOUNDARIES_3() {
        check(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 2, 28));
    }

    @Test
    void LOCAL_DATE_INT_MAX_DISTANCE_1() {
        LocalDate start = LocalDate.ofEpochDay(0);
        check(start, start.plusDays(Integer.MAX_VALUE));
    }

    @Test
    void LOCAL_DATE_INT_MIN_DISTANCE_1() {
        LocalDate end = LocalDate.ofEpochDay(0);
        LocalDate start = LocalDate.ofEpochDay(2_147_483_648L);
        check(start, end);
    }

    @Test
    void LOCAL_DATE_DISTANCE_OVER_INT_MAX_1() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = start.plusDays(2_147_483_648L);
        checkOverflow(start, end);
    }

    @Test
    void LOCAL_DATE_DISTANCE_BELOW_INT_MIN_1() {
        LocalDate end = LocalDate.ofEpochDay(0);
        LocalDate start = LocalDate.ofEpochDay(2_147_483_649L);
        checkOverflow(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_WHOLE_DAY_1() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 31, 12, 0);
        check(start, start.plusDays(1));
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_WHOLE_DAY_2() {
        LocalDateTime start = LocalDateTime.of(2024, 4, 10, 12, 0);
        check(start, start.plusDays(2));
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_WHOLE_DAY_3() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 20, 12, 0);
        check(start, start.plusDays(37));
    }

    @Test
    void LOCAL_DATE_TIME_SUBDAY_ZERO_1() {
        check(
                LocalDateTime.of(2024, 6, 15, 12, 0),
                LocalDateTime.of(2024, 6, 16, 11, 59, 59));
    }

    @Test
    void LOCAL_DATE_TIME_FRACTIONAL_POSITIVE_1() {
        check(
                LocalDateTime.of(2024, 6, 15, 12, 0),
                LocalDateTime.of(2024, 6, 17, 11, 59, 59));
    }

    @Test
    void LOCAL_DATE_TIME_FRACTIONAL_NEGATIVE_1() {
        check(
                LocalDateTime.of(2024, 6, 17, 11, 59, 59),
                LocalDateTime.of(2024, 6, 16, 12, 0));
    }

    @Test
    void INSTANT_EXACT_AND_FRACTIONAL_DAYS_1() {
        Instant start = Instant.parse("2024-02-28T12:00:00Z");
        check(start, start.plusSeconds(86_400L));
    }

    @Test
    void INSTANT_EXACT_AND_FRACTIONAL_DAYS_2() {
        Instant start = Instant.parse("2024-04-15T12:00:00Z");
        check(start, start.plusSeconds(2 * 86_400L));
    }

    @Test
    void INSTANT_EXACT_AND_FRACTIONAL_DAYS_3() {
        Instant start = Instant.parse("2024-08-01T12:00:00Z");
        check(start, start.plusSeconds(86_399L));
    }

    @Test
    void OFFSET_DATE_TIME_FIXED_OFFSET_1() {
        OffsetDateTime date =
                OffsetDateTime.of(2024, 1, 1, 12, 0, 0, 0, ZoneOffset.ofHours(2));
        check(date, date);
    }

    @Test
    void OFFSET_DATE_TIME_FIXED_OFFSET_2() {
        OffsetDateTime start =
                OffsetDateTime.of(2024, 1, 31, 12, 0, 0, 0, ZoneOffset.ofHours(2));
        check(start, start.plusDays(1));
    }

    @Test
    void OFFSET_DATE_TIME_FIXED_OFFSET_3() {
        OffsetDateTime start =
                OffsetDateTime.of(2024, 1, 1, 12, 0, 0, 0, ZoneOffset.ofHours(2));
        check(start, start.minusDays(3));
    }

    @Test
    void ZONED_DATE_TIME_SPRING_TRANSITION_1() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone);
        check(start, start.plusDays(1));
    }

    @Test
    void ZONED_DATE_TIME_SPRING_TRANSITION_2() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2024, 3, 8, 12, 0, 0, 0, zone);
        check(start, start.plusDays(2));
    }

    @Test
    void ZONED_DATE_TIME_AUTUMN_TRANSITION_1() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, zone);
        check(start, start.plusDays(1));
    }

    @Test
    void ZONED_DATE_TIME_AUTUMN_TRANSITION_2() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2024, 11, 4, 12, 0, 0, 0, zone);
        check(start, start.minusDays(2));
    }

    @Test
    void JAPANESE_DATE_SAME_CHRONOLOGY_1() {
        JapaneseDate date = JapaneseDate.of(2024, 6, 20);
        check(date, date);
    }

    @Test
    void JAPANESE_DATE_SAME_CHRONOLOGY_2() {
        JapaneseDate start = JapaneseDate.of(2024, 6, 20);
        check(start, start.plusDays(1));
    }

    @Test
    void JAPANESE_DATE_SAME_CHRONOLOGY_3() {
        JapaneseDate start = JapaneseDate.of(2024, 8, 1);
        check(start, start.minusDays(10));
    }
}
