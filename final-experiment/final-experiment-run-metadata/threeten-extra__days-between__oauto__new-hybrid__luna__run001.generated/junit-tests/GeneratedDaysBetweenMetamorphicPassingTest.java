import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static final int SHIFT_DAYS = 37;

    private static Object[] generateFollowUp(
            Temporal startDateInclusive,
            Temporal endDateExclusive) {
        return new Object[]{
                startDateInclusive.plus(SHIFT_DAYS, ChronoUnit.DAYS),
                endDateExclusive.plus(SHIFT_DAYS, ChronoUnit.DAYS)
        };
    }

    private static void exercise(
            Temporal startDateInclusive,
            Temporal endDateExclusive) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(
                        startDateInclusive,
                        endDateExclusive);

        Object[] followUp = generateFollowUp(
                startDateInclusive,
                endDateExclusive);

        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(
                        (Temporal) followUp[0],
                        (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(
                sourceOutput,
                followUpOutput);
    }

    @Test
    public void testLOCAL_DATE_ZERO_GAP_1() {
        LocalDate date = LocalDate.of(2000, 6, 15);
        exercise(date, date);
    }

    @Test
    public void testLOCAL_DATE_ONE_DAY_1() {
        LocalDate start = LocalDate.of(2020, 1, 31);
        LocalDate end = start.plusDays(1);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_POSITIVE_ORDINARY_GAP_1() {
        Instant start = Instant.parse("2020-01-01T12:00:00Z");
        Instant end = start.plus(42, ChronoUnit.DAYS);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_NEGATIVE_ORDINARY_GAP_1() {
        OffsetDateTime start = OffsetDateTime.of(
                LocalDateTime.of(2020, 2, 28, 8, 0),
                ZoneOffset.ofHours(5));
        OffsetDateTime end = start.minusDays(42);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_MONTH_BOUNDARY_1() {
        ZonedDateTime start = ZonedDateTime.of(
                LocalDateTime.of(2021, 12, 31, 10, 0),
                ZoneOffset.UTC);
        ZonedDateTime end = start.plusDays(3);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_LEAP_DAY_BOUNDARY_1() {
        JapaneseDate start = JapaneseDate.of(2020, 2, 28);
        JapaneseDate end = start.plus(2, ChronoUnit.DAYS);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_YEAR_BOUNDARY_1() {
        LocalDate start = LocalDate.of(2021, 12, 31);
        LocalDate end = LocalDate.of(2022, 1, 2);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_MINIMUM_HEADROOM_1() {
        LocalDateTime start = LocalDate.MIN.plusDays(37).atTime(6, 15);
        LocalDateTime end = LocalDate.MIN.plusDays(100).atTime(6, 15);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_MAXIMUM_HEADROOM_1() {
        Instant start = LocalDate.MAX.minusDays(100)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();
        Instant end = LocalDate.MAX.minusDays(37)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_INTEGER_MAXIMUM_GAP_1() {
        LocalDate start = LocalDate.of(1, 1, 1);
        LocalDate end = start.plusDays(Integer.MAX_VALUE);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_INTEGER_MINIMUM_GAP_1() {
        LocalDate end = LocalDate.of(1, 1, 1);
        LocalDate start = end.plusDays(2_147_483_648L);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_TIME_EXACT_DAYS_1() {
        JapaneseDate start = JapaneseDate.of(2018, 7, 10);
        JapaneseDate end = start.plus(5, ChronoUnit.DAYS);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_TIME_POSITIVE_TRUNCATION_1() {
        LocalDateTime start = LocalDateTime.of(2022, 3, 10, 9, 0);
        LocalDateTime end = start.plusDays(2).minusHours(1);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_TIME_NEGATIVE_TRUNCATION_1() {
        LocalDateTime start = LocalDateTime.of(2022, 3, 12, 9, 0);
        LocalDateTime end = start.minusDays(2).plusHours(1);
        exercise(start, end);
    }

    @Test
    public void testINSTANT_ZERO_AND_SUBDAY_1() {
        Instant start = Instant.parse("2021-05-10T12:00:00Z");
        Instant end = start.plus(23, ChronoUnit.HOURS);
        exercise(start, end);
    }

    @Test
    public void testINSTANT_NEGATIVE_EXACT_DAY_1() {
        Instant start = Instant.parse("2023-08-15T14:30:00Z");
        Instant end = start.minus(3, ChronoUnit.DAYS);
        exercise(start, end);
    }

    @Test
    public void testOFFSET_DATE_TIME_FIXED_OFFSET_1() {
        OffsetDateTime start = OffsetDateTime.of(
                LocalDateTime.of(2024, 4, 20, 11, 0),
                ZoneOffset.ofHoursMinutes(5, 30));
        OffsetDateTime end = start.plusDays(7);
        exercise(start, end);
    }

    @Test
    public void testZONED_DATE_TIME_UTC_1() {
        ZonedDateTime start = ZonedDateTime.of(
                LocalDateTime.of(2019, 11, 5, 10, 0),
                ZoneOffset.UTC);
        ZonedDateTime end = start.plusDays(10);
        exercise(start, end);
    }

    @Test
    public void testJAPANESE_DATE_CALENDAR_1() {
        JapaneseDate start = JapaneseDate.of(2020, 6, 15);
        JapaneseDate end = start.plus(4, ChronoUnit.DAYS);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_SAME_SHIFTED_CALENDAR_POSITION_1() {
        LocalDateTime start = LocalDateTime.of(2021, 1, 25, 16, 45);
        LocalDateTime end = start.plusDays(14);
        exercise(start, end);
    }

    @Test
    public void testLOCAL_DATE_REVERSED_BOUNDARY_INTERVAL_1() {
        Instant start = Instant.parse("2021-01-02T00:00:00Z");
        Instant end = Instant.parse("2020-12-31T00:00:00Z");
        exercise(start, end);
    }
}
