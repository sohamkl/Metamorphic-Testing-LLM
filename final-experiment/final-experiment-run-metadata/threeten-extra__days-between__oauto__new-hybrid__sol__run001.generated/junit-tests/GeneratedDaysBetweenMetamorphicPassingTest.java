import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static Temporal[] generateFollowUp(Temporal startDateInclusive, Temporal endDateExclusive) {
        return new Temporal[]{
                startDateInclusive.plus(37, ChronoUnit.DAYS),
                endDateExclusive.plus(37, ChronoUnit.DAYS)
        };
    }

    private static void exerciseRelation(Temporal startDateInclusive, Temporal endDateExclusive) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);

        Temporal[] followUp = generateFollowUp(startDateInclusive, endDateExclusive);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUp[0], followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_LOCAL_DATE_variation1_equalOrdinaryDate() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        exerciseRelation(date, date);
    }

    @Test
    public void testZERO_LOCAL_DATE_TIME_variation1_equalDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 31, 23, 45, 30);
        exerciseRelation(dateTime, dateTime);
    }

    @Test
    public void testZERO_INSTANT_variation1_equalInstant() {
        Instant instant = Instant.parse("2023-02-28T12:34:56.123456789Z");
        exerciseRelation(instant, instant);
    }

    @Test
    public void testZERO_OFFSET_DATE_TIME_variation1_equalOffsetDateTime() {
        OffsetDateTime dateTime = OffsetDateTime.of(
                2024, 2, 29, 8, 15, 0, 0, ZoneOffset.ofHoursMinutes(5, 30));
        exerciseRelation(dateTime, dateTime);
    }

    @Test
    public void testZERO_ZONED_DATE_TIME_variation1_equalRegionDateTime() {
        ZonedDateTime dateTime = ZonedDateTime.of(
                2024, 12, 31, 18, 0, 0, 0, ZoneId.of("America/New_York"));
        exerciseRelation(dateTime, dateTime);
    }

    @Test
    public void testONE_LOCAL_DATE_variation1_adjacentDates() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDate end = start.plusDays(1);
        exerciseRelation(start, end);
    }

    @Test
    public void testONE_INSTANT_variation1_exactTwentyFourHours() {
        Instant start = Instant.parse("2000-01-01T00:00:00Z");
        Instant end = start.plus(86_400, ChronoUnit.SECONDS);
        exerciseRelation(start, end);
    }

    @Test
    public void testNEGATIVE_ONE_LOCAL_DATE_variation1_reverseAdjacentDates() {
        LocalDate start = LocalDate.of(2025, 8, 20);
        LocalDate end = start.minusDays(1);
        exerciseRelation(start, end);
    }

    @Test
    public void testNEGATIVE_ONE_INSTANT_variation1_reverseTwentyFourHours() {
        Instant start = Instant.parse("2024-04-01T06:30:00Z");
        Instant end = start.minus(86_400, ChronoUnit.SECONDS);
        exerciseRelation(start, end);
    }

    @Test
    public void testSHIFT_SIZED_POSITIVE_GAP_variation1_thirtySevenDaysForward() {
        LocalDate start = LocalDate.of(2021, 2, 10);
        LocalDate end = start.plusDays(37);
        exerciseRelation(start, end);
    }

    @Test
    public void testSHIFT_SIZED_NEGATIVE_GAP_variation1_thirtySevenDaysReverse() {
        LocalDate start = LocalDate.of(2024, 3, 20);
        LocalDate end = start.minusDays(37);
        exerciseRelation(start, end);
    }

    @Test
    public void testLARGE_POSITIVE_GAP_variation1_millionInstantDays() {
        Instant start = Instant.parse("2000-01-01T00:00:00Z");
        Instant end = start.plus(1_000_000L, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    public void testLARGE_NEGATIVE_GAP_variation1_reverseMillionInstantDays() {
        Instant start = Instant.parse("2000-01-01T00:00:00Z");
        Instant end = start.minus(1_000_000L, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    public void testMAXIMUM_INT_DAY_COUNT_variation1_largestAcceptedInstantSpan() {
        Instant start = Instant.EPOCH;
        Instant end = start.plus(Integer.MAX_VALUE, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    public void testMINIMUM_INT_DAY_COUNT_variation1_smallestAcceptedInstantSpan() {
        Instant start = Instant.EPOCH;
        Instant end = start.minus(2_147_483_648L, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    public void testLEAP_DAY_FORWARD_variation1_februaryTwentyNineCrossing() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDate end = LocalDate.of(2024, 3, 1);
        exerciseRelation(start, end);
    }

    @Test
    public void testLEAP_DAY_REVERSE_variation1_reverseFebruaryTwentyNineCrossing() {
        LocalDate start = LocalDate.of(2024, 3, 1);
        LocalDate end = LocalDate.of(2024, 2, 28);
        exerciseRelation(start, end);
    }

    @Test
    public void testCOMMON_FEBRUARY_BOUNDARY_variation1_nonLeapFebruary() {
        LocalDate start = LocalDate.of(2023, 2, 28);
        LocalDate end = LocalDate.of(2023, 3, 1);
        exerciseRelation(start, end);
    }

    @Test
    public void testTHIRTY_ONE_DAY_MONTH_BOUNDARY_variation1_januaryToFebruary() {
        LocalDate start = LocalDate.of(2025, 1, 31);
        LocalDate end = LocalDate.of(2025, 2, 2);
        exerciseRelation(start, end);
    }

    @Test
    public void testTHIRTY_DAY_MONTH_BOUNDARY_variation1_aprilToMay() {
        LocalDate start = LocalDate.of(2025, 4, 30);
        LocalDate end = LocalDate.of(2025, 5, 2);
        exerciseRelation(start, end);
    }

    @Test
    public void testYEAR_BOUNDARY_variation1_decemberToJanuary() {
        LocalDate start = LocalDate.of(2023, 12, 31);
        LocalDate end = LocalDate.of(2024, 1, 2);
        exerciseRelation(start, end);
    }

    @Test
    public void testISO_EPOCH_BOUNDARY_variation1_crossUnixEpoch() {
        LocalDate start = LocalDate.of(1969, 12, 31);
        LocalDate end = LocalDate.of(1970, 1, 2);
        exerciseRelation(start, end);
    }

    @Test
    public void testLOCAL_DATE_MAX_SHIFT_BOUNDARY_variation1_followUpEndsAtMaximum() {
        LocalDate start = LocalDate.MAX.minusDays(38);
        LocalDate end = LocalDate.MAX.minusDays(37);
        exerciseRelation(start, end);
    }

    @Test
    public void testLOCAL_DATE_MIN_BOUNDARY_variation1_startAtMinimum() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN.plusDays(1);
        exerciseRelation(start, end);
    }

    @Test
    public void testLOCAL_DATE_TIME_SUBDAY_POSITIVE_variation1_positiveHoursOnly() {
        LocalDateTime start = LocalDateTime.of(2024, 2, 29, 8, 0);
        LocalDateTime end = start.plusHours(15).plusMinutes(20);
        exerciseRelation(start, end);
    }

    @Test
    public void testLOCAL_DATE_TIME_EXACT_DAY_variation1_exactDateTimeDay() {
        LocalDateTime start = LocalDateTime.of(2024, 12, 30, 10, 15);
        LocalDateTime end = start.plusHours(24);
        exerciseRelation(start, end);
    }

    @Test
    public void testLOCAL_DATE_TIME_DAY_PLUS_FRACTION_variation1_dayAndSeveralHours() {
        LocalDateTime start = LocalDateTime.of(1970, 1, 1, 4, 0);
        LocalDateTime end = start.plusHours(36).plusMinutes(1);
        exerciseRelation(start, end);
    }

    @Test
    public void testLOCAL_DATE_TIME_REVERSE_SUBDAY_variation1_reverseSeveralHours() {
        LocalDateTime start = LocalDateTime.of(2025, 7, 10, 18, 30);
        LocalDateTime end = start.minusHours(9).minusMinutes(45);
        exerciseRelation(start, end);
    }

    @Test
    public void testLOCAL_DATE_TIME_REVERSE_DAY_PLUS_FRACTION_variation1_reverseThirtyHours() {
        LocalDateTime start = LocalDateTime.of(2025, 6, 10, 12, 0);
        LocalDateTime end = start.minusHours(30).minusMinutes(15);
        exerciseRelation(start, end);
    }

    @Test
    public void testMIDNIGHT_CROSSING_UNDER_ONE_DAY_variation1_twoHoursAcrossMidnight() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 31, 23, 0);
        LocalDateTime end = LocalDateTime.of(2024, 2, 1, 1, 0);
        exerciseRelation(start, end);
    }

    @Test
    public void testINSTANT_SUBDAY_POSITIVE_variation1_lessThanOneElapsedDay() {
        Instant start = Instant.parse("2023-02-28T00:00:00Z");
        Instant end = start.plusSeconds(86_399);
        exerciseRelation(start, end);
    }

    @Test
    public void testINSTANT_EXACT_DAY_variation1_exactElapsedDay() {
        Instant start = Instant.parse("2024-02-29T12:00:00Z");
        Instant end = start.plusSeconds(86_400);
        exerciseRelation(start, end);
    }

    @Test
    public void testINSTANT_REVERSE_SUBDAY_variation1_reverseLessThanDay() {
        Instant start = Instant.parse("2024-12-31T23:59:59Z");
        Instant end = start.minusSeconds(43_200);
        exerciseRelation(start, end);
    }

    @Test
    public void testOFFSET_DATE_TIME_SAME_OFFSET_variation1_fiveLocalDays() {
        ZoneOffset offset = ZoneOffset.ofHoursMinutes(5, 30);
        OffsetDateTime start = OffsetDateTime.of(2024, 9, 1, 9, 15, 0, 0, offset);
        OffsetDateTime end = start.plusDays(5);
        exerciseRelation(start, end);
    }

    @Test
    public void testOFFSET_DATE_TIME_DIFFERENT_OFFSETS_variation1_normalizedThirtyHours() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(2));
        OffsetDateTime end = OffsetDateTime.ofInstant(
                start.toInstant().plus(30, ChronoUnit.HOURS), ZoneOffset.ofHours(-5));
        exerciseRelation(start, end);
    }

    @Test
    public void testOFFSET_DATE_LINE_EXTREMES_variation1_plusFourteenToMinusTwelve() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 0, 0);
        OffsetDateTime start = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(14));
        OffsetDateTime end = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(-12));
        exerciseRelation(start, end);
    }

    @Test
    public void testZONED_ORDINARY_DAY_variation1_tokyoThreeDays() {
        ZoneId zone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime start = ZonedDateTime.of(2024, 5, 10, 11, 20, 0, 0, zone);
        ZonedDateTime end = start.plusDays(3);
        exerciseRelation(start, end);
    }

    @Test
    public void testZONED_SPRING_FORWARD_DAY_variation1_newYorkTransition() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2024, 3, 10, 12, 0, 0, 0, zone);
        exerciseRelation(start, end);
    }

    @Test
    public void testZONED_FALL_BACK_DAY_variation1_newYorkTransition() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2024, 11, 3, 12, 0, 0, 0, zone);
        exerciseRelation(start, end);
    }

    @Test
    public void testZONED_GAP_RESOLVED_SUBDAY_variation1_resolvedSpringGap() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(
                LocalDateTime.of(2024, 3, 10, 1, 30), zone);
        ZonedDateTime end = ZonedDateTime.of(
                LocalDateTime.of(2024, 3, 10, 2, 30), zone);
        exerciseRelation(start, end);
    }

    @Test
    public void testZONED_OVERLAP_SAME_LOCAL_TIME_variation1_earlierAndLaterOffsets() {
        ZoneId zone = ZoneId.of("America/New_York");
        LocalDateTime overlappedTime = LocalDateTime.of(2024, 11, 3, 1, 30);
        ZonedDateTime start = ZonedDateTime.ofLocal(
                overlappedTime, zone, ZoneOffset.ofHours(-4));
        ZonedDateTime end = ZonedDateTime.ofLocal(
                overlappedTime, zone, ZoneOffset.ofHours(-5));
        exerciseRelation(start, end);
    }

    @Test
    public void testZONED_DIFFERENT_REGIONS_variation1_londonToTokyoSubday() {
        ZonedDateTime start = ZonedDateTime.of(
                2024, 1, 1, 12, 0, 0, 0, ZoneId.of("Europe/London"));
        ZonedDateTime end = ZonedDateTime.of(
                2024, 1, 2, 5, 0, 0, 0, ZoneId.of("Asia/Tokyo"));
        exerciseRelation(start, end);
    }

    @Test
    public void testZONED_SAME_INSTANT_DIFFERENT_ZONES_variation1_dateLineLocalDates() {
        Instant sharedInstant = Instant.parse("2024-01-01T12:00:00Z");
        ZonedDateTime start = sharedInstant.atZone(ZoneId.of("Pacific/Kiritimati"));
        ZonedDateTime end = sharedInstant.atZone(ZoneId.of("America/Adak"));
        exerciseRelation(start, end);
    }

    @Test
    public void testJAPANESE_DATE_INTERVAL_variation1_tenChronologyDays() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2020, 1, 15));
        JapaneseDate end = start.plus(10, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    public void testHIJRAH_MONTH_BOUNDARY_variation1_lastDayPlusTwo() {
        HijrahDate month = HijrahDate.of(1445, 9, 1);
        HijrahDate start = month.plus(month.lengthOfMonth() - 1L, ChronoUnit.DAYS);
        HijrahDate end = start.plus(2, ChronoUnit.DAYS);
        exerciseRelation(start, end);
    }

    @Test
    public void testMIXED_CHRONOLOGY_SAME_EPOCH_DAY_variation1_japaneseToHijrah() {
        LocalDate isoDate = LocalDate.of(2024, 6, 1);
        JapaneseDate start = JapaneseDate.from(isoDate);
        HijrahDate end = HijrahDate.from(isoDate);
        exerciseRelation(start, end);
    }

    @Test
    public void testLOCAL_DATE_TO_LOCAL_DATE_TIME_variation1_twoDatesAndAfternoon() {
        LocalDate start = LocalDate.of(2024, 12, 30);
        LocalDateTime end = LocalDateTime.of(2025, 1, 1, 18, 45);
        exerciseRelation(start, end);
    }

    @Test
    public void testINSTANT_TO_OFFSET_DATE_TIME_variation1_exactElapsedDay() {
        Instant start = Instant.parse("1970-01-01T00:00:00Z");
        OffsetDateTime end = OffsetDateTime.ofInstant(
                start.plus(86_400, ChronoUnit.SECONDS), ZoneOffset.ofHours(9));
        exerciseRelation(start, end);
    }
}
