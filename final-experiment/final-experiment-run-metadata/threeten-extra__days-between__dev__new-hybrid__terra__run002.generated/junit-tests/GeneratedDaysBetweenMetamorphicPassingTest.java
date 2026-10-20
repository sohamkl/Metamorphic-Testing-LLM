import org.junit.jupiter.api.Test;

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

    private void exercise(Temporal startDateInclusive, Temporal endDateExclusive) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);
        Object[] followUp =
                DaysBetweenMetamorphicSpec.generateFollowUp(startDateInclusive, endDateExclusive);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_SAME_DAY_ZERO_identicalDate() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        exercise(date, date);
    }

    @Test
    void LOCAL_DATE_ADJACENT_FORWARD_ONE_nextDate() {
        exercise(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16));
    }

    @Test
    void LOCAL_DATE_ADJACENT_BACKWARD_MINUS_ONE_previousDate() {
        exercise(LocalDate.of(2024, 6, 16), LocalDate.of(2024, 6, 15));
    }

    @Test
    void LOCAL_DATE_ORDINARY_POSITIVE_GAP_juneInterval() {
        exercise(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 20));
    }

    @Test
    void LOCAL_DATE_ORDINARY_NEGATIVE_GAP_reverseJuneInterval() {
        exercise(LocalDate.of(2024, 6, 20), LocalDate.of(2024, 6, 1));
    }

    @Test
    void LOCAL_DATE_MONTH_END_CROSSING_januaryToFebruary() {
        exercise(LocalDate.of(2024, 1, 30), LocalDate.of(2024, 2, 2));
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_leapYearFebruary() {
        exercise(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
    }

    @Test
    void LOCAL_DATE_NON_LEAP_CENTURY_CROSSING_year1900() {
        exercise(LocalDate.of(1900, 2, 28), LocalDate.of(1900, 3, 1));
    }

    @Test
    void LOCAL_DATE_LEAP_CENTURY_CROSSING_year2000() {
        exercise(LocalDate.of(2000, 2, 28), LocalDate.of(2000, 3, 1));
    }

    @Test
    void LOCAL_DATE_YEAR_END_CROSSING_newYear() {
        exercise(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 2));
    }

    @Test
    void LOCAL_DATE_MINIMUM_REPRESENTABLE_START_minimumDate() {
        LocalDate start = LocalDate.MIN;
        exercise(start, start.plusDays(2));
    }

    @Test
    void LOCAL_DATE_NEAR_MAXIMUM_REPRESENTABLE_END_shiftHeadroom() {
        exercise(LocalDate.MAX.minusDays(38), LocalDate.MAX.minusDays(37));
    }

    @Test
    void LOCAL_DATE_INTEGER_MAXIMUM_DISTANCE_intMaximum() {
        LocalDate start = LocalDate.of(0, 1, 1);
        exercise(start, start.plusDays(2147483647L));
    }

    @Test
    void LOCAL_DATE_INTEGER_MINIMUM_DISTANCE_intMinimum() {
        LocalDate start = LocalDate.of(0, 1, 1);
        exercise(start, start.plusDays(-2147483648L));
    }

    @Test
    void LOCAL_DATE_TIME_SAME_LOCAL_DATETIME_identicalTimestamp() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 6, 15, 12, 30);
        exercise(dateTime, dateTime);
    }

    @Test
    void LOCAL_DATE_TIME_POSITIVE_SUBDAY_almostOneDay() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 0, 0),
                LocalDateTime.of(2024, 6, 15, 23, 59, 59));
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_twentyFourHours() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 8, 45),
                LocalDateTime.of(2024, 6, 16, 8, 45));
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_reverseAlmostOneDay() {
        exercise(
                LocalDateTime.of(2024, 6, 15, 23, 59, 59),
                LocalDateTime.of(2024, 6, 15, 0, 0));
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_EXACT_DAY_reverseTwentyFourHours() {
        exercise(
                LocalDateTime.of(2024, 6, 16, 8, 45),
                LocalDateTime.of(2024, 6, 15, 8, 45));
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_fixedIndianOffset() {
        OffsetDateTime start = OffsetDateTime.parse("2024-06-01T10:00:00+05:30");
        exercise(start, start.plusDays(12));
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_fixedOffsetPair() {
        exercise(
                OffsetDateTime.parse("2024-01-01T00:00:00+02:00"),
                OffsetDateTime.parse("2024-01-02T00:00:00Z"));
    }

    @Test
    void ZONED_DATE_TIME_UTC_utcInterval() {
        ZonedDateTime start = ZonedDateTime.parse("2024-06-01T12:00:00Z[UTC]");
        exercise(start, start.plusDays(9));
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_newYorkSpringTransition() {
        ZonedDateTime start =
                ZonedDateTime.parse("2024-03-09T12:00:00-05:00[America/New_York]");
        exercise(start, start.plusDays(2));
    }

    @Test
    void ZONED_DATE_TIME_FALL_DST_newYorkFallTransition() {
        ZonedDateTime start =
                ZonedDateTime.parse("2024-11-02T12:00:00-04:00[America/New_York]");
        exercise(start, start.plusDays(2));
    }

    @Test
    void INSTANT_POSITIVE_SUBDAY_almostTwentyFourHours() {
        exercise(
                Instant.parse("2024-06-01T00:00:00Z"),
                Instant.parse("2024-06-01T23:59:59Z"));
    }

    @Test
    void INSTANT_EXACT_MULTIPLE_DAYS_fifteenDays() {
        Instant start = Instant.parse("2024-06-01T00:00:00Z");
        exercise(start, start.plus(15, ChronoUnit.DAYS));
    }

    @Test
    void INSTANT_NEGATIVE_EXACT_MULTIPLE_DAYS_reverseFifteenDays() {
        exercise(
                Instant.parse("2024-06-16T00:00:00Z"),
                Instant.parse("2024-06-01T00:00:00Z"));
    }

    @Test
    void JAPANESE_DATE_ERA_BOUNDARY_heiseiToReiwa() {
        exercise(
                JapaneseDate.from(LocalDate.of(2019, 4, 30)),
                JapaneseDate.from(LocalDate.of(2019, 5, 2)));
    }

    @Test
    void HIJRAH_DATE_MONTH_BOUNDARY_ramadanInterval() {
        HijrahDate start = HijrahDate.of(1445, 9, 1);
        exercise(start, start.plus(10, ChronoUnit.DAYS));
    }

    @Test
    void MINGUO_DATE_YEAR_BOUNDARY_newYear() {
        exercise(MinguoDate.of(112, 12, 31), MinguoDate.of(113, 1, 2));
    }

    @Test
    void THAI_BUDDHIST_DATE_LEAP_BOUNDARY_leapDay() {
        exercise(
                ThaiBuddhistDate.from(LocalDate.of(2024, 2, 28)),
                ThaiBuddhistDate.from(LocalDate.of(2024, 3, 1)));
    }

    @Test
    void ISO_START_JAPANESE_END_compatibleChronologies() {
        exercise(
                LocalDate.of(2024, 6, 15),
                JapaneseDate.from(LocalDate.of(2024, 6, 16)));
    }

    @Test
    void ISO_DATE_START_LOCAL_DATE_TIME_END_mixedPrecision() {
        exercise(
                LocalDate.of(2024, 6, 15),
                LocalDateTime.of(2024, 6, 16, 23, 0));
    }
}
