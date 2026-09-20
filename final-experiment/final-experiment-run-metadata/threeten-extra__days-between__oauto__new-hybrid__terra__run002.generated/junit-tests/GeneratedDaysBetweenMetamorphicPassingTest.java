import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static Temporal[] generateFollowUp(Temporal startDateInclusive, Temporal endDateExclusive) {
        return new Temporal[] {
                startDateInclusive.plus(37, ChronoUnit.DAYS),
                endDateExclusive.plus(37, ChronoUnit.DAYS)
        };
    }

    private static void exercise(Temporal startDateInclusive, Temporal endDateExclusive) {
        Days sourceOutput = org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);
        Temporal[] followUpInput = generateFollowUp(startDateInclusive, endDateExclusive);
        Days followUpOutput = org.threeten.extra.Days.between(followUpInput[0], followUpInput[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_EQUAL_ZERO_sameDate() {
        exercise(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
    }

    @Test
    void LOCAL_DATE_ONE_DAY_SINGLETON_consecutiveDates() {
        exercise(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16));
    }

    @Test
    void LOCAL_DATE_ORDINARY_POSITIVE_weekInterval() {
        exercise(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 22));
    }

    @Test
    void LOCAL_DATE_ORDINARY_NEGATIVE_reversedWeekInterval() {
        exercise(LocalDate.of(2024, 6, 22), LocalDate.of(2024, 6, 15));
    }

    @Test
    void LOCAL_DATE_MONTH_BOUNDARY_januaryToFebruary() {
        exercise(LocalDate.of(2024, 1, 31), LocalDate.of(2024, 2, 1));
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_februaryLeapDay() {
        exercise(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
    }

    @Test
    void LOCAL_DATE_NON_LEAP_FEBRUARY_februaryToMarch() {
        exercise(LocalDate.of(2023, 2, 28), LocalDate.of(2023, 3, 1));
    }

    @Test
    void LOCAL_DATE_NON_LEAP_CENTURY_year1900() {
        exercise(LocalDate.of(1900, 2, 28), LocalDate.of(1900, 3, 1));
    }

    @Test
    void LOCAL_DATE_LEAP_CENTURY_year2000() {
        exercise(LocalDate.of(2000, 2, 28), LocalDate.of(2000, 3, 1));
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_newYear() {
        exercise(LocalDate.of(2024, 12, 31), LocalDate.of(2025, 1, 1));
    }

    @Test
    void LOCAL_DATE_INT_MAX_RESULT_maximumIntGap() {
        LocalDate start = LocalDate.of(-900000000, 1, 1);
        exercise(start, start.plusDays(2147483647L));
    }

    @Test
    void LOCAL_DATE_INT_MIN_RESULT_minimumIntGap() {
        LocalDate start = LocalDate.of(900000000, 1, 1);
        exercise(start, start.plusDays(-2147483648L));
    }

    @Test
    void LOCAL_DATE_NEAR_MIN_FOLLOW_UP_BOUNDARY_minDate() {
        exercise(LocalDate.MIN, LocalDate.MIN.plusDays(1));
    }

    @Test
    void LOCAL_DATE_NEAR_MAX_FOLLOW_UP_BOUNDARY_maxDate() {
        exercise(LocalDate.MAX.minusDays(38), LocalDate.MAX.minusDays(37));
    }

    @Test
    void LOCAL_DATE_TIME_SUBDAY_POSITIVE_almostFullDay() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 0, 0),
                LocalDateTime.of(2024, 6, 15, 23, 59, 59, 999999999));
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_twentyFourHours() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 12, 0),
                LocalDateTime.of(2024, 6, 16, 12, 0));
    }

    @Test
    void LOCAL_DATE_TIME_NEAR_TWO_DAYS_oneNanosecondShort() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 0, 0),
                LocalDateTime.of(2024, 6, 16, 23, 59, 59, 999999999));
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_reverseSameDate() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 23, 59, 59, 999999999),
                LocalDateTime.of(2024, 6, 15, 0, 0));
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_EXACT_DAY_reverseTwentyFourHours() {
        exercise(
                LocalDateTime.of(2024, 6, 16, 12, 0),
                LocalDateTime.of(2024, 6, 15, 12, 0));
    }

    @Test
    void LOCAL_DATE_TIME_NANO_FRACTION_AFTER_DAY_oneNanosecondAfterDay() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 0, 0),
                LocalDateTime.of(2024, 6, 16, 0, 0, 0, 1));
    }

    @Test
    void OFFSET_DATE_TIME_SAME_INSTANT_differentOffsets() {
        exercise(
                OffsetDateTime.parse("2024-06-15T12:00:00+00:00"),
                OffsetDateTime.parse("2024-06-15T14:00:00+02:00"));
    }

    @Test
    void OFFSET_DATE_TIME_TWENTY_THREE_HOURS_subdayTimelineGap() {
        exercise(
                OffsetDateTime.parse("2024-06-15T00:00:00+00:00"),
                OffsetDateTime.parse("2024-06-15T23:00:00+00:00"));
    }

    @Test
    void OFFSET_DATE_TIME_EXACT_TWENTY_FOUR_HOURS_exactDayTimelineGap() {
        exercise(
                OffsetDateTime.parse("2024-06-15T00:00:00+00:00"),
                OffsetDateTime.parse("2024-06-16T00:00:00+00:00"));
    }

    @Test
    void OFFSET_DATE_TIME_NEGATIVE_EXACT_TWENTY_FOUR_HOURS_reverseExactDay() {
        exercise(
                OffsetDateTime.parse("2024-06-16T00:00:00+00:00"),
                OffsetDateTime.parse("2024-06-15T00:00:00+00:00"));
    }

    @Test
    void INSTANT_SUBDAY_twentyThreeHours() {
        exercise(
                Instant.parse("2024-06-15T00:00:00Z"),
                Instant.parse("2024-06-15T23:00:00Z"));
    }

    @Test
    void INSTANT_EXACT_DAY_eightySixThousandFourHundredSeconds() {
        exercise(
                Instant.parse("2024-06-15T00:00:00Z"),
                Instant.parse("2024-06-16T00:00:00Z"));
    }

    @Test
    void INSTANT_NEGATIVE_MULTI_DAY_reverseThreeDays() {
        exercise(
                Instant.parse("2024-06-18T00:00:00Z"),
                Instant.parse("2024-06-15T00:00:00Z"));
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_newYorkTransition() {
        exercise(
                ZonedDateTime.parse("2024-03-09T12:00:00-05:00[America/New_York]"),
                ZonedDateTime.parse("2024-03-10T12:00:00-04:00[America/New_York]"));
    }

    @Test
    void ZONED_DATE_TIME_AUTUMN_DST_newYorkTransition() {
        exercise(
                ZonedDateTime.parse("2024-11-02T12:00:00-04:00[America/New_York]"),
                ZonedDateTime.parse("2024-11-03T12:00:00-05:00[America/New_York]"));
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_MULTI_DAY_multiDayTransition() {
        exercise(
                ZonedDateTime.parse("2024-03-08T06:30:00-05:00[America/New_York]"),
                ZonedDateTime.parse("2024-03-12T06:30:00-04:00[America/New_York]"));
    }

    @Test
    void ZONED_DATE_TIME_DIFFERENT_ZONES_SAME_INSTANT_utcParis() {
        exercise(
                ZonedDateTime.parse("2024-06-15T12:00:00Z[UTC]"),
                ZonedDateTime.parse("2024-06-15T14:00:00+02:00[Europe/Paris]"));
    }

    @Test
    void ZONED_DATE_TIME_DIFFERENT_ZONES_EXACT_DAY_utcParisTimelineDay() {
        exercise(
                ZonedDateTime.parse("2024-01-15T12:00:00Z[UTC]"),
                ZonedDateTime.parse("2024-01-16T13:00:00+01:00[Europe/Paris]"));
    }

    @Test
    void LOCAL_DATE_TO_LOCAL_DATE_TIME_COMPATIBLE_laterDateTime() {
        exercise(
                LocalDate.of(2024, 6, 15),
                LocalDateTime.of(2024, 6, 18, 23, 59, 59, 999999999));
    }

    @Test
    void LOCAL_DATE_TO_LOCAL_DATE_TIME_SAME_DATE_laterTimeSameDate() {
        exercise(
                LocalDate.of(2024, 6, 15),
                LocalDateTime.of(2024, 6, 15, 23, 59, 59, 999999999));
    }
}
