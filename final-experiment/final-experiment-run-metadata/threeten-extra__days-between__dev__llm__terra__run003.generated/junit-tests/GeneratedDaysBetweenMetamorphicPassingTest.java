import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static void exercise(Temporal startDateInclusive, Temporal endDateExclusive) {
        Days sourceOutput = org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(startDateInclusive, endDateExclusive);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0],
                (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_SAME_DAY_ZERO_identicalDate() {
        exercise(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
    }

    @Test
    void LOCAL_DATE_ADJACENT_DAY_ONE_consecutiveDates() {
        exercise(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16));
    }

    @Test
    void LOCAL_DATE_FORWARD_MULTI_DAY_ordinaryInterval() {
        exercise(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 25));
    }

    @Test
    void LOCAL_DATE_REVERSE_MULTI_DAY_reverseInterval() {
        exercise(LocalDate.of(2024, 6, 25), LocalDate.of(2024, 6, 15));
    }

    @Test
    void LOCAL_DATE_MONTH_END_CROSSING_januaryToFebruary() {
        exercise(LocalDate.of(2024, 1, 31), LocalDate.of(2024, 2, 2));
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_leapYear() {
        exercise(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
    }

    @Test
    void LOCAL_DATE_NON_LEAP_CENTURY_CROSSING_year1900() {
        exercise(LocalDate.of(1900, 2, 28), LocalDate.of(1900, 3, 1));
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_newYear() {
        exercise(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 2));
    }

    @Test
    void LOCAL_DATE_TIME_SUBDAY_ZERO_lessThanDay() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 12, 0),
                LocalDateTime.of(2024, 6, 16, 11, 59));
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_equalTimes() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 12, 0),
                LocalDateTime.of(2024, 6, 16, 12, 0));
    }

    @Test
    void LOCAL_DATE_TIME_NONINTEGRAL_MULTI_DAY_partialSecondDay() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 10, 0),
                LocalDateTime.of(2024, 6, 17, 9, 0));
    }

    @Test
    void LOCAL_DATE_TIME_REVERSE_EXACT_DAYS_reverseExactInterval() {
        exercise(
                LocalDateTime.of(2024, 6, 17, 12, 0),
                LocalDateTime.of(2024, 6, 15, 12, 0));
    }

    @Test
    void LOCAL_DATE_TO_LOCAL_DATE_TIME_mixedTemporalTypes() {
        exercise(
                LocalDate.of(2024, 6, 15),
                LocalDateTime.of(2024, 6, 17, 18, 30));
    }

    @Test
    void INSTANT_SUBDAY_ZERO_twentyThreeHours() {
        exercise(
                Instant.parse("2024-06-15T00:00:00Z"),
                Instant.parse("2024-06-15T23:00:00Z"));
    }

    @Test
    void INSTANT_EXACT_DAY_twentyFourHours() {
        exercise(
                Instant.parse("2024-06-15T00:00:00Z"),
                Instant.parse("2024-06-16T00:00:00Z"));
    }

    @Test
    void INSTANT_NONINTEGRAL_MULTI_DAY_fortySevenHours() {
        exercise(
                Instant.parse("2024-06-15T00:00:00Z"),
                Instant.parse("2024-06-16T23:00:00Z"));
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_sameElapsedDay() {
        exercise(
                OffsetDateTime.parse("2024-06-15T00:00:00Z"),
                OffsetDateTime.parse("2024-06-16T01:00:00+01:00"));
    }

    @Test
    void OFFSET_DATE_TIME_SAME_INSTANT_differentOffsetRepresentations() {
        exercise(
                OffsetDateTime.parse("2024-06-15T00:00:00Z"),
                OffsetDateTime.parse("2024-06-15T02:00:00+02:00"));
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_TWO_LOCAL_DAYS_newYorkGap() {
        exercise(
                ZonedDateTime.parse("2024-03-09T12:00:00-05:00[America/New_York]"),
                ZonedDateTime.parse("2024-03-11T12:00:00-04:00[America/New_York]"));
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_ONE_LOCAL_DAY_newYorkAdjacentDates() {
        exercise(
                ZonedDateTime.parse("2024-03-09T12:00:00-05:00[America/New_York]"),
                ZonedDateTime.parse("2024-03-10T12:00:00-04:00[America/New_York]"));
    }

    @Test
    void ZONED_DATE_TIME_FALL_DST_TWO_LOCAL_DAYS_newYorkOverlap() {
        exercise(
                ZonedDateTime.parse("2024-11-02T12:00:00-04:00[America/New_York]"),
                ZonedDateTime.parse("2024-11-04T12:00:00-05:00[America/New_York]"));
    }

    @Test
    void ZONED_DATE_TIME_DIFFERENT_ZONES_SAME_INSTANT_coastalZones() {
        exercise(
                ZonedDateTime.parse("2024-06-15T00:00:00-04:00[America/New_York]"),
                ZonedDateTime.parse("2024-06-14T21:00:00-07:00[America/Los_Angeles]"));
    }

    @Test
    void JAPANESE_DATE_FORWARD_reiwaDates() {
        JapaneseDate start = JapaneseDate.of(JapaneseEra.REIWA, 6, 6, 15);
        exercise(start, start.plus(10, ChronoUnit.DAYS));
    }

    @Test
    void MINGUO_DATE_FORWARD_minguoDates() {
        MinguoDate start = MinguoDate.of(113, 6, 15);
        exercise(start, start.plus(10, ChronoUnit.DAYS));
    }

    @Test
    void THAI_BUDDHIST_DATE_FORWARD_thaiBuddhistDates() {
        ThaiBuddhistDate start = ThaiBuddhistDate.of(2567, 6, 15);
        exercise(start, start.plus(10, ChronoUnit.DAYS));
    }

    @Test
    void HIJRAH_DATE_FORWARD_lunarCalendarDates() {
        HijrahDate start = HijrahDate.of(1445, 9, 1);
        exercise(start, start.plus(10, ChronoUnit.DAYS));
    }

    @Test
    void LOCAL_DATE_TO_JAPANESE_DATE_mixedChronologies() {
        exercise(
                LocalDate.of(2024, 6, 15),
                JapaneseDate.of(JapaneseEra.REIWA, 6, 6, 17));
    }

    @Test
    void INSTANT_INTEGER_MAX_DAY_COUNT_largestIntResult() {
        Instant start = Instant.EPOCH;
        exercise(start, start.plus(2147483647L, ChronoUnit.DAYS));
    }

    @Test
    void INSTANT_INTEGER_MIN_DAY_COUNT_smallestIntResult() {
        Instant start = Instant.EPOCH;
        exercise(start, start.minus(2147483648L, ChronoUnit.DAYS));
    }
}
