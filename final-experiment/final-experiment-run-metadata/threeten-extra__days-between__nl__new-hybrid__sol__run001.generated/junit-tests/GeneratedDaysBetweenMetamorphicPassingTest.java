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
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static final long SHIFT_DAYS = 37L;

    private static MtllmGeneratedDaysBetweenInvocation1uknttg.Input input(
            Temporal start, Temporal end) {
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(start, end);
    }

    private static MtllmGeneratedDaysBetweenInvocation1uknttg.Input generateFollowUp(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {
        Temporal shiftedStart = source.arg0().plus(SHIFT_DAYS, ChronoUnit.DAYS);
        Temporal shiftedEnd = source.arg1().plus(SHIFT_DAYS, ChronoUnit.DAYS);
        return input(shiftedStart, shiftedEnd);
    }

    private static void assertMetamorphicRelation(
            Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(
                sourceOutput.getAmount(),
                followUpOutput.getAmount());
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {
        Days sourceOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(source);
        Days followUpOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(
                generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertSourceAndMetamorphicRelation(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source,
            int expectedSourceAmount) {
        Days sourceOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(source);
        Assertions.assertEquals(expectedSourceAmount, sourceOutput.getAmount());

        Days followUpOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(
                generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EQUAL_LOCAL_DATES_ZERO_variation1_equalEndpoints() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        assertSourceAndMetamorphicRelation(input(date, date), 0);
    }

    @Test
    public void CONSECUTIVE_LOCAL_DATES_ONE_variation1_nextDate() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        LocalDate end = start.plusDays(1);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void REVERSED_CONSECUTIVE_LOCAL_DATES_variation1_previousDate() {
        LocalDate start = LocalDate.of(2024, 7, 20);
        LocalDate end = start.minusDays(1);
        assertSourceAndMetamorphicRelation(input(start, end), -1);
    }

    @Test
    public void ORDINARY_POSITIVE_MULTI_DAY_GAP_variation1_thirtySixDays() {
        LocalDate start = LocalDate.of(2023, 5, 10);
        LocalDate end = start.plusDays(36);
        assertSourceAndMetamorphicRelation(input(start, end), 36);
    }

    @Test
    public void ORDINARY_NEGATIVE_MULTI_DAY_GAP_variation1_reversedThirtySixDays() {
        LocalDate start = LocalDate.of(2023, 8, 20);
        LocalDate end = start.minusDays(36);
        assertSourceAndMetamorphicRelation(input(start, end), -36);
    }

    @Test
    public void GAP_EQUAL_TO_SHIFT_DISTANCE_variation1_thirtySevenDays() {
        LocalDate start = LocalDate.of(2022, 3, 5);
        LocalDate end = start.plusDays(37);
        assertSourceAndMetamorphicRelation(input(start, end), 37);
    }

    @Test
    public void REVERSED_GAP_EQUAL_TO_SHIFT_DISTANCE_variation1_negativeThirtySevenDays() {
        LocalDate start = LocalDate.of(2022, 9, 5);
        LocalDate end = start.minusDays(37);
        assertSourceAndMetamorphicRelation(input(start, end), -37);
    }

    @Test
    public void LEAP_DAY_INCLUDED_variation1_february2024() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDate end = LocalDate.of(2024, 3, 1);
        assertSourceAndMetamorphicRelation(input(start, end), 2);
    }

    @Test
    public void ORDINARY_NON_LEAP_FEBRUARY_variation1_february2023() {
        LocalDate start = LocalDate.of(2023, 2, 28);
        LocalDate end = LocalDate.of(2023, 3, 1);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void NON_LEAP_CENTURY_1900_variation1_centuryMonthEnd() {
        LocalDate start = LocalDate.of(1900, 2, 28);
        LocalDate end = LocalDate.of(1900, 3, 1);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void LEAP_CENTURY_2000_variation1_centuryLeapDay() {
        LocalDate start = LocalDate.of(2000, 2, 28);
        LocalDate end = LocalDate.of(2000, 3, 1);
        assertSourceAndMetamorphicRelation(input(start, end), 2);
    }

    @Test
    public void THIRTY_ONE_DAY_MONTH_END_variation1_januaryToFebruary() {
        LocalDate start = LocalDate.of(2025, 1, 31);
        LocalDate end = LocalDate.of(2025, 2, 1);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void YEAR_END_BOUNDARY_variation1_decemberToJanuary() {
        LocalDate start = LocalDate.of(2023, 12, 31);
        LocalDate end = LocalDate.of(2024, 1, 1);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void PROLEPTIC_YEAR_ZERO_BOUNDARY_variation1_negativeOneToZero() {
        LocalDate start = LocalDate.of(-1, 12, 31);
        LocalDate end = LocalDate.of(0, 1, 1);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_variation1_equalLocalTimes() {
        LocalDateTime start = LocalDateTime.of(2024, 4, 10, 13, 45);
        LocalDateTime end = start.plusDays(1);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_SUBDAY_variation1_oneSecondShort() {
        LocalDateTime start = LocalDateTime.of(2024, 4, 10, 12, 0);
        LocalDateTime end = start.plusHours(23).plusMinutes(59).plusSeconds(59);
        assertSourceAndMetamorphicRelation(input(start, end), 0);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_PARTIAL_TEN_DATES_variation1_earlierEndTime() {
        LocalDateTime start = LocalDateTime.of(2024, 5, 1, 18, 30);
        LocalDateTime end = LocalDateTime.of(2024, 5, 11, 8, 15);
        assertSourceAndMetamorphicRelation(input(start, end), 9);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_variation1_reversedHours() {
        LocalDateTime start = LocalDateTime.of(2024, 4, 10, 12, 0);
        LocalDateTime end = start.minusHours(23);
        assertSourceAndMetamorphicRelation(input(start, end), 0);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_TEN_DATES_variation1_laterEndTime() {
        LocalDateTime start = LocalDateTime.of(2024, 5, 20, 8, 15);
        LocalDateTime end = LocalDateTime.of(2024, 5, 10, 18, 30);
        assertSourceAndMetamorphicRelation(input(start, end), -9);
    }

    @Test
    public void LOCAL_DATE_TIME_MIDNIGHT_BOUNDARY_variation1_straddlesMidnight() {
        LocalDateTime start = LocalDateTime.of(2024, 8, 10, 23, 59);
        LocalDateTime end = LocalDateTime.of(2024, 8, 11, 0, 1);
        assertSourceAndMetamorphicRelation(input(start, end), 0);
    }

    @Test
    public void OFFSET_DATE_TIME_SAME_OFFSET_variation1_consecutiveDates() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 3, 10, 9, 30, 0, 0, ZoneOffset.ofHours(3));
        OffsetDateTime end = start.plusDays(1);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_SUBDAY_variation1_twentyTwoHours() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime end = OffsetDateTime.of(
                2024, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(2));
        assertSourceAndMetamorphicRelation(input(start, end), 0);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_FULL_DAY_variation1_twentyEightHours() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(2));
        OffsetDateTime end = OffsetDateTime.of(
                2024, 1, 2, 2, 0, 0, 0, ZoneOffset.UTC);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void OFFSET_DATE_TIME_REVERSED_DIFFERENT_OFFSETS_variation1_negativeWholeDay() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 1, 2, 2, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime end = OffsetDateTime.of(
                2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(2));
        assertSourceAndMetamorphicRelation(input(start, end), -1);
    }

    @Test
    public void INSTANT_EXACT_DAY_variation1_exactSeconds() {
        Instant start = Instant.parse("2024-01-15T12:00:00Z");
        Instant end = start.plus(1, ChronoUnit.DAYS);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void INSTANT_POSITIVE_PARTIAL_DAY_variation1_86399Seconds() {
        Instant start = Instant.parse("2024-01-15T12:00:00Z");
        Instant end = start.plusSeconds(86_399);
        assertSourceAndMetamorphicRelation(input(start, end), 0);
    }

    @Test
    public void INSTANT_NEGATIVE_PARTIAL_DAY_variation1_negative86399Seconds() {
        Instant start = Instant.parse("2024-01-15T12:00:00Z");
        Instant end = start.minusSeconds(86_399);
        assertSourceAndMetamorphicRelation(input(start, end), 0);
    }

    @Test
    public void INSTANT_MULTI_DAY_WITH_REMAINDER_variation1_fiveDaysAndSecond() {
        Instant start = Instant.parse("2024-02-01T00:00:00Z");
        Instant end = start.plus(5, ChronoUnit.DAYS).plusSeconds(1);
        assertSourceAndMetamorphicRelation(input(start, end), 5);
    }

    @Test
    public void ZONED_DATE_TIME_FIXED_ZONE_variation1_utcCalendarDay() {
        ZonedDateTime start = ZonedDateTime.of(
                LocalDate.of(2024, 2, 10),
                LocalTime.of(10, 15),
                ZoneOffset.UTC);
        ZonedDateTime end = start.plusDays(1);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void ZONED_DATE_TIME_SPRING_GAP_variation1_newYorkTransition() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                LocalDate.of(2024, 3, 9), LocalTime.NOON, zone);
        ZonedDateTime end = ZonedDateTime.of(
                LocalDate.of(2024, 3, 10), LocalTime.NOON, zone);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void ZONED_DATE_TIME_AUTUMN_OVERLAP_variation1_newYorkTransition() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                LocalDate.of(2024, 11, 2), LocalTime.NOON, zone);
        ZonedDateTime end = ZonedDateTime.of(
                LocalDate.of(2024, 11, 3), LocalTime.NOON, zone);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void ZONED_DATE_TIME_DIFFERENT_ZONES_variation1_parisToNewYork() {
        ZonedDateTime start = ZonedDateTime.of(
                LocalDate.of(2024, 1, 1),
                LocalTime.MIDNIGHT,
                ZoneId.of("Europe/Paris"));
        ZonedDateTime end = ZonedDateTime.of(
                LocalDate.of(2024, 1, 2),
                LocalTime.MIDNIGHT,
                ZoneId.of("America/New_York"));
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void ZONED_DATE_TIME_REVERSED_DIFFERENT_ZONES_variation1_newYorkToParis() {
        ZonedDateTime start = ZonedDateTime.of(
                LocalDate.of(2024, 1, 2),
                LocalTime.MIDNIGHT,
                ZoneId.of("America/New_York"));
        ZonedDateTime end = ZonedDateTime.of(
                LocalDate.of(2024, 1, 1),
                LocalTime.MIDNIGHT,
                ZoneId.of("Europe/Paris"));

        int expected = Math.toIntExact(ChronoUnit.DAYS.between(start, end));
        Assertions.assertTrue(expected < 0);
        assertSourceAndMetamorphicRelation(input(start, end), expected);
    }

    @Test
    public void JAPANESE_ERA_TRANSITION_variation1_heiseiToReiwa() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2019, 4, 30));
        JapaneseDate end = JapaneseDate.from(LocalDate.of(2019, 5, 1));
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void JAPANESE_ERA_TRANSITION_REVERSED_variation1_reiwaToHeisei() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2019, 5, 1));
        JapaneseDate end = JapaneseDate.from(LocalDate.of(2019, 4, 30));
        assertSourceAndMetamorphicRelation(input(start, end), -1);
    }

    @Test
    public void MINGUO_MONTH_BOUNDARY_variation1_januaryToFebruary() {
        MinguoDate start = MinguoDate.from(LocalDate.of(2024, 1, 31));
        MinguoDate end = MinguoDate.from(LocalDate.of(2024, 2, 1));

        Assertions.assertNotEquals(
                start.getLong(ChronoField.MONTH_OF_YEAR),
                end.getLong(ChronoField.MONTH_OF_YEAR));
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void THAI_BUDDHIST_YEAR_BOUNDARY_variation1_newYear() {
        ThaiBuddhistDate start =
                ThaiBuddhistDate.from(LocalDate.of(2023, 12, 31));
        ThaiBuddhistDate end =
                ThaiBuddhistDate.from(LocalDate.of(2024, 1, 1));

        Assertions.assertNotEquals(
                start.getLong(ChronoField.YEAR),
                end.getLong(ChronoField.YEAR));
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void HIJRAH_MONTH_BOUNDARY_variation1_consecutiveHijrahDates() {
        HijrahDate end = HijrahDate.of(1445, 2, 1);
        HijrahDate start = HijrahDate.from(
                end.minus(1, ChronoUnit.DAYS));

        Assertions.assertNotEquals(
                start.getLong(ChronoField.MONTH_OF_YEAR),
                end.getLong(ChronoField.MONTH_OF_YEAR));
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void COMPATIBLE_LOCAL_DATE_TO_LOCAL_DATE_TIME_variation1_mixedRuntimeTypes() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDateTime end = LocalDateTime.of(2024, 1, 2, 23, 0);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void INTEGER_MAXIMUM_DAY_COUNT_variation1_exactUpperIntBoundary() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = LocalDate.ofEpochDay(Integer.MAX_VALUE);
        assertSourceAndMetamorphicRelation(
                input(start, end), Integer.MAX_VALUE);
    }

    @Test
    public void INTEGER_MINIMUM_DAY_COUNT_variation1_exactLowerIntBoundary() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = LocalDate.ofEpochDay(Integer.MIN_VALUE);
        assertSourceAndMetamorphicRelation(
                input(start, end), Integer.MIN_VALUE);
    }

    @Test
    public void LOCAL_DATE_EXACT_SHIFT_TO_MAXIMUM_variation1_reachesMaximum() {
        LocalDate date = LocalDate.MAX.minusDays(SHIFT_DAYS);
        MtllmGeneratedDaysBetweenInvocation1uknttg.Input source =
                input(date, date);

        MtllmGeneratedDaysBetweenInvocation1uknttg.Input followUp =
                generateFollowUp(source);
        Assertions.assertEquals(
                LocalDate.MAX,
                LocalDate.from(followUp.arg0()));
        Assertions.assertEquals(
                LocalDate.MAX,
                LocalDate.from(followUp.arg1()));

        assertSourceAndMetamorphicRelation(source, 0);
    }

    @Test
    public void LOCAL_DATE_NEAR_MINIMUM_variation1_minimumAndNextDay() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN.plusDays(1);
        assertSourceAndMetamorphicRelation(input(start, end), 1);
    }

    @Test
    public void INSTANT_EXACT_SHIFT_TO_MAXIMUM_variation1_reachesMaximum() {
        Instant instant = Instant.MAX.minus(SHIFT_DAYS, ChronoUnit.DAYS);
        MtllmGeneratedDaysBetweenInvocation1uknttg.Input source =
                input(instant, instant);

        MtllmGeneratedDaysBetweenInvocation1uknttg.Input followUp =
                generateFollowUp(source);
        Assertions.assertEquals(
                Instant.MAX,
                Instant.from(followUp.arg0()));
        Assertions.assertEquals(
                Instant.MAX,
                Instant.from(followUp.arg1()));

        assertSourceAndMetamorphicRelation(source, 0);
    }

    @Test
    public void LARGE_NON_BOUNDARY_POSITIVE_COUNT_variation1_billionDays() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = LocalDate.ofEpochDay(1_000_000_000L);
        assertSourceAndMetamorphicRelation(
                input(start, end), 1_000_000_000);
    }

    @Test
    public void LARGE_NON_BOUNDARY_NEGATIVE_COUNT_variation1_negativeBillionDays() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = LocalDate.ofEpochDay(-1_000_000_000L);
        assertSourceAndMetamorphicRelation(
                input(start, end), -1_000_000_000);
    }
}
