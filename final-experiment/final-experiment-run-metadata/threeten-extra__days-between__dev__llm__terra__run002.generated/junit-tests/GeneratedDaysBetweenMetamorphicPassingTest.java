import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private void verifyRelation(Temporal startDateInclusive, Temporal endDateExclusive) {
        Days sourceOutput = org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(
                startDateInclusive, endDateExclusive);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_SAME_DAY_ZERO_identicalDates() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        verifyRelation(date, date);
    }

    @Test
    void LOCAL_DATE_NEXT_DAY_ONE_adjacentDates() {
        verifyRelation(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16));
    }

    @Test
    void LOCAL_DATE_NEGATIVE_ONE_DAY_reversedAdjacentDates() {
        verifyRelation(LocalDate.of(2024, 6, 16), LocalDate.of(2024, 6, 15));
    }

    @Test
    void LOCAL_DATE_MONTH_END_CROSSING_januaryToFebruary() {
        verifyRelation(LocalDate.of(2024, 1, 31), LocalDate.of(2024, 2, 1));
    }

    @Test
    void LOCAL_DATE_NON_LEAP_FEBRUARY_februaryToMarch() {
        verifyRelation(LocalDate.of(2023, 2, 28), LocalDate.of(2023, 3, 1));
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_includesLeapDay() {
        verifyRelation(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_newYearTransition() {
        verifyRelation(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 1));
    }

    @Test
    void LOCAL_DATE_MULTI_WEEK_POSITIVE_sixWeeks() {
        verifyRelation(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 2, 12));
    }

    @Test
    void LOCAL_DATE_MULTI_WEEK_NEGATIVE_reversedSixWeeks() {
        verifyRelation(LocalDate.of(2024, 2, 12), LocalDate.of(2024, 1, 1));
    }

    @Test
    void LOCAL_DATE_MINIMUM_SAFE_FOR_SHIFT_nearMinimum() {
        verifyRelation(LocalDate.MIN.plusDays(37), LocalDate.MIN.plusDays(38));
    }

    @Test
    void LOCAL_DATE_MAXIMUM_SAFE_FOR_SHIFT_nearMaximum() {
        verifyRelation(LocalDate.MAX.minusDays(38), LocalDate.MAX.minusDays(37));
    }

    @Test
    void LOCAL_DATE_TIME_SUBDAY_POSITIVE_twentyThreeHours() {
        verifyRelation(
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 1, 1, 23, 0));
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_twentyFourHours() {
        verifyRelation(
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 1, 2, 0, 0));
    }

    @Test
    void LOCAL_DATE_TIME_PARTIAL_CALENDAR_DAY_crossesMidnight() {
        verifyRelation(
                LocalDateTime.of(2024, 1, 1, 23, 0),
                LocalDateTime.of(2024, 1, 2, 22, 0));
    }

    @Test
    void LOCAL_DATE_TIME_SUBDAY_NEGATIVE_reversedPartialDay() {
        verifyRelation(
                LocalDateTime.of(2024, 1, 2, 0, 0),
                LocalDateTime.of(2024, 1, 1, 1, 0));
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_EXACT_DAY_reversedTwentyFourHours() {
        verifyRelation(
                LocalDateTime.of(2024, 1, 2, 0, 0),
                LocalDateTime.of(2024, 1, 1, 0, 0));
    }

    @Test
    void INSTANT_SUBDAY_POSITIVE_twentyThreeHours() {
        verifyRelation(
                Instant.parse("2024-01-01T00:00:00Z"),
                Instant.parse("2024-01-01T23:00:00Z"));
    }

    @Test
    void INSTANT_EXACT_DAY_twentyFourHours() {
        verifyRelation(
                Instant.parse("2024-01-01T00:00:00Z"),
                Instant.parse("2024-01-02T00:00:00Z"));
    }

    @Test
    void INSTANT_NEGATIVE_EXACT_DAY_reversedTwentyFourHours() {
        verifyRelation(
                Instant.parse("2024-01-02T00:00:00Z"),
                Instant.parse("2024-01-01T00:00:00Z"));
    }

    @Test
    void INSTANT_INTEGER_MAX_DAYS_maximumIntDistance() {
        verifyRelation(
                Instant.EPOCH,
                Instant.EPOCH.plus(Integer.MAX_VALUE, ChronoUnit.DAYS));
    }

    @Test
    void INSTANT_INTEGER_MIN_DAYS_minimumIntDistance() {
        verifyRelation(
                Instant.EPOCH,
                Instant.EPOCH.minus(2147483648L, ChronoUnit.DAYS));
    }

    @Test
    void OFFSET_DATE_TIME_EQUAL_INSTANT_ZERO_sameInstantDifferentOffset() {
        verifyRelation(
                OffsetDateTime.parse("2024-01-01T00:00:00+00:00"),
                OffsetDateTime.parse("2024-01-01T02:00:00+02:00"));
    }

    @Test
    void OFFSET_DATE_TIME_OFFSET_NORMALIZED_SUBDAY_twentyTwoHours() {
        verifyRelation(
                OffsetDateTime.parse("2024-01-01T00:00:00+00:00"),
                OffsetDateTime.parse("2024-01-02T00:00:00+02:00"));
    }

    @Test
    void OFFSET_DATE_TIME_OFFSET_NORMALIZED_OVERDAY_twentySixHours() {
        verifyRelation(
                OffsetDateTime.parse("2024-01-01T00:00:00+00:00"),
                OffsetDateTime.parse("2024-01-02T00:00:00-02:00"));
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_newYorkTransition() {
        verifyRelation(
                ZonedDateTime.parse("2024-03-09T12:00:00-05:00[America/New_York]"),
                ZonedDateTime.parse("2024-03-10T12:00:00-04:00[America/New_York]"));
    }

    @Test
    void ZONED_DATE_TIME_AUTUMN_DST_newYorkTransition() {
        verifyRelation(
                ZonedDateTime.parse("2024-11-02T12:00:00-04:00[America/New_York]"),
                ZonedDateTime.parse("2024-11-03T12:00:00-05:00[America/New_York]"));
    }

    @Test
    void ZONED_DATE_TIME_SAME_ZONE_MULTIDAY_tokyoInterval() {
        verifyRelation(
                ZonedDateTime.of(2024, 5, 1, 10, 0, 0, 0, ZoneId.of("Asia/Tokyo")),
                ZonedDateTime.of(2024, 5, 11, 10, 0, 0, 0, ZoneId.of("Asia/Tokyo")));
    }

    @Test
    void LOCAL_DATE_TO_LOCAL_DATE_TIME_mixedIsoTypes() {
        verifyRelation(
                LocalDate.of(2024, 1, 1),
                LocalDateTime.of(2024, 1, 3, 18, 0));
    }

    @Test
    void LOCAL_DATE_TO_JAPANESE_DATE_mixedChronologies() {
        verifyRelation(
                LocalDate.of(2024, 1, 1),
                JapaneseDate.from(LocalDate.of(2024, 1, 3)));
    }

    @Test
    void JAPANESE_DATE_TO_LOCAL_DATE_mixedChronologies() {
        verifyRelation(
                JapaneseDate.from(LocalDate.of(2024, 1, 1)),
                LocalDate.of(2024, 1, 3));
    }

    @Test
    void JAPANESE_ERA_TRANSITION_heiseiToReiwa() {
        verifyRelation(
                JapaneseDate.from(LocalDate.of(2019, 4, 30)),
                JapaneseDate.from(LocalDate.of(2019, 5, 1)));
    }

    @Test
    void JAPANESE_DATE_TIME_EXACT_DAY_noonToNoon() {
        verifyRelation(
                JapaneseDate.from(LocalDate.of(2024, 1, 1)).atTime(LocalTime.NOON),
                JapaneseDate.from(LocalDate.of(2024, 1, 2)).atTime(LocalTime.NOON));
    }

    @Test
    void MINGUO_DATE_MULTIDAY_tenDays() {
        verifyRelation(MinguoDate.of(113, 1, 1), MinguoDate.of(113, 1, 11));
    }

    @Test
    void THAI_BUDDHIST_DATE_NEGATIVE_reversedWeek() {
        verifyRelation(
                ThaiBuddhistDate.of(2567, 1, 10),
                ThaiBuddhistDate.of(2567, 1, 3));
    }

    @Test
    void HIJRAH_DATE_MULTIDAY_ramadanWeek() {
        verifyRelation(HijrahDate.of(1445, 9, 1), HijrahDate.of(1445, 9, 8));
    }
}
