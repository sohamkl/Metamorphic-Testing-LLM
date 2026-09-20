import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static Object[] generateFollowUp(Temporal startDateInclusive, Temporal endDateExclusive) {
        return new Object[] {
                startDateInclusive.plus(37, ChronoUnit.DAYS),
                endDateExclusive.plus(37, ChronoUnit.DAYS)
        };
    }

    private static void verifyMetamorphicCase(Temporal startDateInclusive, Temporal endDateExclusive) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(startDateInclusive, endDateExclusive);

        Object[] followUp = generateFollowUp(startDateInclusive, endDateExclusive);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);

        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_EQUAL_ENDPOINTS_sameDate() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        verifyMetamorphicCase(date, date);
    }

    @Test
    void LOCAL_DATE_FORWARD_ONE_DAY_monthBoundary() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        verifyMetamorphicCase(start, start.plusDays(1));
    }

    @Test
    void LOCAL_DATE_BACKWARD_ONE_DAY_leapDayBoundary() {
        LocalDate start = LocalDate.of(2024, 3, 1);
        verifyMetamorphicCase(start, start.minusDays(1));
    }

    @Test
    void LOCAL_DATE_MONTH_BOUNDARY_twoDays() {
        verifyMetamorphicCase(LocalDate.of(2024, 1, 30), LocalDate.of(2024, 2, 1));
    }

    @Test
    void LOCAL_DATE_MONTH_BOUNDARY_threeDays() {
        verifyMetamorphicCase(LocalDate.of(2024, 1, 30), LocalDate.of(2024, 2, 2));
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_BOUNDARY_february28To29() {
        verifyMetamorphicCase(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 2, 29));
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_BOUNDARY_february28ToMarch1LeapYear() {
        verifyMetamorphicCase(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_BOUNDARY_february28ToMarch1CommonYear() {
        verifyMetamorphicCase(LocalDate.of(2023, 2, 28), LocalDate.of(2023, 3, 1));
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_forward() {
        verifyMetamorphicCase(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 2));
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_backward() {
        verifyMetamorphicCase(LocalDate.of(2024, 1, 2), LocalDate.of(2023, 12, 31));
    }

    @Test
    void INTEGER_MAX_DAY_GAP_upperIntBoundary() {
        LocalDate start = LocalDate.of(0, 1, 1);
        verifyMetamorphicCase(start, start.plusDays(Integer.MAX_VALUE));
    }

    @Test
    void INTEGER_MIN_DAY_GAP_lowerIntBoundary() {
        LocalDate start = LocalDate.of(0, 1, 1);
        verifyMetamorphicCase(start, start.minusDays(2147483648L));
    }

    @Test
    void LOCAL_DATE_TIME_EQUAL_INSTANTS_sameDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 5, 20, 13, 45, 30);
        verifyMetamorphicCase(dateTime, dateTime);
    }

    @Test
    void LOCAL_DATE_TIME_POSITIVE_PARTIAL_DAY_oneHour() {
        LocalDateTime start = LocalDateTime.of(2024, 5, 20, 10, 0);
        verifyMetamorphicCase(start, start.plusHours(1));
    }

    @Test
    void LOCAL_DATE_TIME_POSITIVE_PARTIAL_DAY_twentyThreeHoursFiftyNineMinutes() {
        LocalDateTime start = LocalDateTime.of(2024, 5, 20, 10, 0);
        verifyMetamorphicCase(start, start.plusHours(23).plusMinutes(59));
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_DAY_oneHour() {
        LocalDateTime start = LocalDateTime.of(2024, 5, 20, 10, 0);
        verifyMetamorphicCase(start, start.minusHours(1));
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_DAY_twentyThreeHoursFiftyNineMinutes() {
        LocalDateTime start = LocalDateTime.of(2024, 5, 20, 10, 0);
        verifyMetamorphicCase(start, start.minusHours(23).minusMinutes(59));
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_COMPLETE_DAY_twentyFourHours() {
        LocalDateTime start = LocalDateTime.of(2024, 8, 15, 7, 30);
        verifyMetamorphicCase(start, start.plusHours(24));
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_COMPLETE_DAY_twentyFiveHours() {
        LocalDateTime start = LocalDateTime.of(2024, 8, 15, 7, 30);
        verifyMetamorphicCase(start, start.minusHours(25));
    }

    @Test
    void LOCAL_DATE_TIME_MULTI_DAY_WITH_TIME_COMPONENT_fortySevenHours() {
        LocalDateTime start = LocalDateTime.of(2024, 9, 10, 8, 15);
        verifyMetamorphicCase(start, start.plusHours(47));
    }

    @Test
    void LOCAL_DATE_TIME_MULTI_DAY_WITH_TIME_COMPONENT_fortyNineHours() {
        LocalDateTime start = LocalDateTime.of(2024, 9, 10, 8, 15);
        verifyMetamorphicCase(start, start.plusHours(49));
    }

    @Test
    void ZONED_DATE_TIME_NORMAL_OFFSET_oneDayUtc() {
        ZonedDateTime start = ZonedDateTime.of(2024, 1, 10, 12, 0, 0, 0, ZoneOffset.UTC);
        verifyMetamorphicCase(start, start.plusDays(1));
    }

    @Test
    void ZONED_DATE_TIME_NORMAL_OFFSET_threeDaysUtc() {
        ZonedDateTime start = ZonedDateTime.of(2024, 1, 10, 12, 0, 0, 0, ZoneOffset.UTC);
        verifyMetamorphicCase(start, start.plusDays(3));
    }

    @Test
    void ZONED_DATE_TIME_DST_SPRING_FORWARD_oneCalendarDay() {
        ZoneId newYork = ZoneId.of("America/New_York");
        verifyMetamorphicCase(
                ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, newYork),
                ZonedDateTime.of(2024, 3, 10, 12, 0, 0, 0, newYork));
    }

    @Test
    void ZONED_DATE_TIME_DST_SPRING_FORWARD_twoCalendarDays() {
        ZoneId newYork = ZoneId.of("America/New_York");
        verifyMetamorphicCase(
                ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, newYork),
                ZonedDateTime.of(2024, 3, 11, 12, 0, 0, 0, newYork));
    }

    @Test
    void ZONED_DATE_TIME_DST_FALL_BACK_oneCalendarDay() {
        ZoneId newYork = ZoneId.of("America/New_York");
        verifyMetamorphicCase(
                ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, newYork),
                ZonedDateTime.of(2024, 11, 3, 12, 0, 0, 0, newYork));
    }

    @Test
    void ZONED_DATE_TIME_DST_FALL_BACK_twoCalendarDays() {
        ZoneId newYork = ZoneId.of("America/New_York");
        verifyMetamorphicCase(
                ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, newYork),
                ZonedDateTime.of(2024, 11, 4, 12, 0, 0, 0, newYork));
    }

    @Test
    void ZONED_DATE_TIME_DIFFERENT_ZONES_oneDay() {
        ZoneId newYork = ZoneId.of("America/New_York");
        ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        verifyMetamorphicCase(
                ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, newYork),
                ZonedDateTime.of(2024, 1, 2, 14, 0, 0, 0, tokyo));
    }

    @Test
    void ZONED_DATE_TIME_DIFFERENT_ZONES_twoDays() {
        ZoneId newYork = ZoneId.of("America/New_York");
        ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        verifyMetamorphicCase(
                ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, newYork),
                ZonedDateTime.of(2024, 1, 3, 14, 0, 0, 0, tokyo));
    }

    @Test
    void OFFSET_DATE_TIME_ENDPOINTS_thirtySixHours() {
        OffsetDateTime start = OffsetDateTime.of(2024, 4, 10, 6, 0, 0, 0, ZoneOffset.ofHours(5));
        verifyMetamorphicCase(start, start.plusHours(36));
    }

    @Test
    void OFFSET_DATE_TIME_ENDPOINTS_fortyEightHours() {
        OffsetDateTime start = OffsetDateTime.of(2024, 4, 10, 6, 0, 0, 0, ZoneOffset.ofHours(-4));
        verifyMetamorphicCase(start, start.plusHours(48));
    }

    @Test
    void INSTANT_ENDPOINTS_twentyFourHours() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        verifyMetamorphicCase(start, start.plus(24, ChronoUnit.HOURS));
    }

    @Test
    void INSTANT_ENDPOINTS_seventyTwoHours() {
        Instant start = Instant.parse("2024-06-01T12:30:00Z");
        verifyMetamorphicCase(start, start.plus(72, ChronoUnit.HOURS));
    }

    @Test
    void JAPANESE_DATE_ERA_BOUNDARY_oneDay() {
        verifyMetamorphicCase(JapaneseDate.of(2019, 4, 30), JapaneseDate.of(2019, 5, 1));
    }

    @Test
    void JAPANESE_DATE_ERA_BOUNDARY_twoDays() {
        verifyMetamorphicCase(JapaneseDate.of(2019, 4, 29), JapaneseDate.of(2019, 5, 1));
    }

    @Test
    void MINGUO_DATE_LEAP_BOUNDARY_forwardTwoDays() {
        verifyMetamorphicCase(MinguoDate.of(113, 2, 28), MinguoDate.of(113, 3, 1));
    }

    @Test
    void MINGUO_DATE_LEAP_BOUNDARY_reverseOneDay() {
        verifyMetamorphicCase(MinguoDate.of(113, 3, 1), MinguoDate.of(113, 2, 29));
    }

    @Test
    void THAI_BUDDHIST_DATE_YEAR_BOUNDARY_oneDay() {
        verifyMetamorphicCase(
                ThaiBuddhistDate.of(2567, 12, 31),
                ThaiBuddhistDate.of(2568, 1, 1));
    }

    @Test
    void THAI_BUDDHIST_DATE_YEAR_BOUNDARY_threeDays() {
        verifyMetamorphicCase(
                ThaiBuddhistDate.of(2567, 12, 30),
                ThaiBuddhistDate.of(2568, 1, 2));
    }

    @Test
    void LOCAL_DATE_TO_LOCAL_DATE_TIME_endDateComponentTwoDays() {
        verifyMetamorphicCase(
                LocalDate.of(2024, 1, 1),
                LocalDateTime.of(2024, 1, 3, 15, 45));
    }

    @Test
    void LOCAL_DATE_TO_LOCAL_DATE_TIME_endDateComponentThreeDays() {
        verifyMetamorphicCase(
                LocalDate.of(2024, 1, 1),
                LocalDateTime.of(2024, 1, 4, 2, 30));
    }

    @Test
    void LOCAL_DATE_TO_ZONED_DATE_TIME_utcEndDateTwoDays() {
        verifyMetamorphicCase(
                LocalDate.of(2024, 6, 1),
                ZonedDateTime.of(2024, 6, 3, 8, 0, 0, 0, ZoneOffset.UTC));
    }

    @Test
    void LOCAL_DATE_TO_ZONED_DATE_TIME_utcEndDateThreeDays() {
        verifyMetamorphicCase(
                LocalDate.of(2024, 6, 1),
                ZonedDateTime.of(2024, 6, 4, 20, 0, 0, 0, ZoneOffset.UTC));
    }
}
