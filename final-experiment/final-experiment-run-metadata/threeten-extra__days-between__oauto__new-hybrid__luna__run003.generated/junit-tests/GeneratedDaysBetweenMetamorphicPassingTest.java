import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static final long SHIFT_DAYS = 37L;

    private static Object[] generateFollowUp(
            Temporal startDateInclusive,
            Temporal endDateExclusive) {
        return new Object[] {
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
    void LOCAL_DATE_ZERO_GAP_variation1() {
        LocalDate date = LocalDate.of(2024, 3, 15);
        exercise(date, date);
    }

    @Test
    void LOCAL_DATE_ONE_DAY_FORWARD_variation1() {
        LocalDate start = LocalDate.of(2024, 3, 15);
        exercise(start, start.plusDays(1));
    }

    @Test
    void LOCAL_DATE_ONE_DAY_REVERSE_variation1() {
        LocalDate start = LocalDate.of(2024, 3, 16);
        exercise(start, start.minusDays(1));
    }

    @Test
    void LOCAL_DATE_SMALL_POSITIVE_NONUNIT_variation1() {
        LocalDate start = LocalDate.of(2024, 4, 10);
        exercise(start, start.plusDays(2));
    }

    @Test
    void LOCAL_DATE_SMALL_NEGATIVE_NONUNIT_variation1() {
        LocalDate start = LocalDate.of(2024, 5, 20);
        exercise(start, start.minusDays(2));
    }

    @Test
    void LOCAL_DATE_SHIFT_SIZED_GAP_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 1);
        exercise(start, start.plusDays(37));
    }

    @Test
    void LOCAL_DATE_NEAR_MINIMUM_BOUNDARY_variation1() {
        LocalDate start = LocalDate.MIN.plusDays(37);
        exercise(start, start.plusDays(2));
    }

    @Test
    void LOCAL_DATE_NEAR_MAXIMUM_BOUNDARY_variation1() {
        LocalDate start = LocalDate.MAX.minusDays(100);
        exercise(start, start.plusDays(50));
    }

    @Test
    void LOCAL_DATE_INTEGER_MAXIMUM_GAP_variation1() {
        LocalDate start = LocalDate.MIN.plusDays(37);
        exercise(start, start.plusDays(Integer.MAX_VALUE));
    }

    @Test
    void LOCAL_DATE_INTEGER_MINIMUM_GAP_variation1() {
        LocalDate start = LocalDate.MAX.minusDays(37);
        exercise(start, start.minusDays(2147483648L));
    }

    @Test
    void LOCAL_DATE_TIME_SAME_DATE_DIFFERENT_TIMES_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 7, 10, 8, 0);
        LocalDateTime end = LocalDateTime.of(2024, 7, 10, 17, 0);
        exercise(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_FRACTIONAL_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 7, 10, 23, 0);
        LocalDateTime end = LocalDateTime.of(2024, 7, 11, 22, 0);
        exercise(start, end);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 10, 26, 12, 30);
        exercise(start, start.plusDays(1));
    }

    @Test
    void INSTANT_SUBDAY_INTERVAL_variation1() {
        Instant start = Instant.parse("2024-06-01T09:00:00Z");
        exercise(start, start.plus(23, ChronoUnit.HOURS));
    }

    @Test
    void INSTANT_EXACT_DAY_INTERVAL_variation1() {
        Instant start = Instant.parse("2024-06-01T09:00:00Z");
        exercise(start, start.plus(1, ChronoUnit.DAYS));
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_variation1() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 2, 1, 8, 0, 0, 0, ZoneOffset.ofHours(-8));
        OffsetDateTime end = OffsetDateTime.of(
                2024, 2, 4, 13, 0, 0, 0, ZoneOffset.ofHours(1));
        exercise(start, end);
    }

    @Test
    void ZONED_DATE_TIME_DST_FORWARD_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                2024, 3, 8, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(
                2024, 3, 12, 12, 0, 0, 0, zone);
        exercise(start, end);
    }

    @Test
    void ZONED_DATE_TIME_DST_BACKWARD_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                2024, 11, 1, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(
                2024, 11, 4, 12, 0, 0, 0, zone);
        exercise(start, end);
    }

    @Test
    void JAPANESE_DATE_NON_ISO_variation1() {
        JapaneseDate start = JapaneseDate.of(2019, 5, 1);
        exercise(start, start.plus(12, ChronoUnit.DAYS));
    }

    @Test
    void HIJRAH_DATE_NON_ISO_variation1() {
        HijrahDate start = HijrahDate.of(1445, 9, 1);
        exercise(start, start.plus(19, ChronoUnit.DAYS));
    }

    @Test
    void MINGUO_DATE_NON_ISO_variation1() {
        MinguoDate start = MinguoDate.of(2020, 1, 1);
        exercise(start, start.plus(8, ChronoUnit.DAYS));
    }

    @Test
    void THAI_BUDDHIST_DATE_NON_ISO_variation1() {
        ThaiBuddhistDate start = ThaiBuddhistDate.of(2024, 8, 20);
        exercise(start, start.minus(37, ChronoUnit.DAYS));
    }

    @Test
    void CROSS_TYPE_DATE_TIME_PAIR_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDateTime end = LocalDateTime.of(2024, 1, 3, 12, 30);
        exercise(start, end);
    }
}
