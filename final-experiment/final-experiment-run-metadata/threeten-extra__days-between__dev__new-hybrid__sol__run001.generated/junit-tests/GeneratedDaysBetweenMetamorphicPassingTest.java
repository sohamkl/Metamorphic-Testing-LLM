import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private void verifyRelation(Temporal start, Temporal end) {
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_EQUAL_ENDPOINTS_variation1_ordinaryDate() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = start;
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_EQUAL_ENDPOINTS_variation2_nearMaximum() {
        LocalDate start = LocalDate.MAX.minusDays(37);
        LocalDate end = start;
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_POSITIVE_ONE_DAY_variation1_ordinaryDate() {
        LocalDate start = LocalDate.of(2023, 8, 14);
        LocalDate end = start.plusDays(1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_POSITIVE_ONE_DAY_variation2_yearBoundary() {
        LocalDate start = LocalDate.of(1999, 12, 31);
        LocalDate end = start.plusDays(1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_NEGATIVE_ONE_DAY_variation1_ordinaryDate() {
        LocalDate start = LocalDate.of(2022, 5, 20);
        LocalDate end = start.minusDays(1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_NEGATIVE_ONE_DAY_variation2_monthBoundary() {
        LocalDate start = LocalDate.of(2021, 3, 1);
        LocalDate end = start.minusDays(1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_MULTI_DAY_FORWARD_variation1_thirtySevenDays() {
        LocalDate start = LocalDate.of(2020, 1, 15);
        LocalDate end = start.plusDays(37);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_MULTI_DAY_FORWARD_variation2_fourHundredDays() {
        LocalDate start = LocalDate.of(2018, 11, 3);
        LocalDate end = start.plusDays(400);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_MULTI_DAY_REVERSE_variation1_negativeThirtySevenDays() {
        LocalDate start = LocalDate.of(2020, 7, 10);
        LocalDate end = start.minusDays(37);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_MULTI_DAY_REVERSE_variation2_negativeFourHundredDays() {
        LocalDate start = LocalDate.of(2016, 10, 2);
        LocalDate end = start.minusDays(400);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_CROSSING_variation1_year2020() {
        LocalDate start = LocalDate.of(2020, 2, 28);
        LocalDate end = LocalDate.of(2020, 3, 1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_CROSSING_variation2_year2000() {
        LocalDate start = LocalDate.of(2000, 2, 28);
        LocalDate end = LocalDate.of(2000, 3, 1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_NON_LEAP_FEBRUARY_variation1_year2019() {
        LocalDate start = LocalDate.of(2019, 2, 28);
        LocalDate end = LocalDate.of(2019, 3, 1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_MONTH_AND_YEAR_BOUNDARIES_variation1_monthBoundary() {
        LocalDate start = LocalDate.of(2021, 1, 31);
        LocalDate end = LocalDate.of(2021, 2, 1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_MONTH_AND_YEAR_BOUNDARIES_variation2_yearBoundary() {
        LocalDate start = LocalDate.of(2020, 12, 31);
        LocalDate end = LocalDate.of(2021, 1, 1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_MAXIMUM_INT_GAP_variation1_integerMaximum() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = LocalDate.ofEpochDay(Integer.MAX_VALUE);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_MINIMUM_INT_GAP_variation1_integerMinimum() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = LocalDate.ofEpochDay(Integer.MIN_VALUE);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_MINIMUM_DATE_ENDPOINT_variation1_minimumStart() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN.plusDays(1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_SHIFT_REACHES_MAXIMUM_variation1_exactHeadroom() {
        LocalDate start = LocalDate.MAX.minusDays(37);
        LocalDate end = LocalDate.MAX.minusDays(37);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_SUBDAY_variation1_oneSecond() {
        LocalDateTime start = LocalDateTime.of(2023, 4, 10, 12, 30);
        LocalDateTime end = start.plusSeconds(1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_SUBDAY_variation2_almostOneDay() {
        LocalDateTime start = LocalDateTime.of(2023, 4, 10, 12, 30);
        LocalDateTime end = start.plusHours(23).plusMinutes(59).plusSeconds(59);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_variation1_twentyFourHours() {
        LocalDateTime start = LocalDateTime.of(2020, 2, 28, 8, 45, 20);
        LocalDateTime end = start.plusDays(1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_DAY_PLUS_FRACTION_variation1_dayAndSecond() {
        LocalDateTime start = LocalDateTime.of(2022, 9, 12, 6, 0);
        LocalDateTime end = start.plusDays(1).plusSeconds(1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_DAY_PLUS_FRACTION_variation2_almostTwoDays() {
        LocalDateTime start = LocalDateTime.of(2022, 9, 12, 6, 0);
        LocalDateTime end = start.plusHours(47).plusMinutes(59).plusSeconds(59);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_variation1_negativeOneSecond() {
        LocalDateTime start = LocalDateTime.of(2021, 6, 3, 10, 15);
        LocalDateTime end = start.minusSeconds(1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_variation2_negativeAlmostDay() {
        LocalDateTime start = LocalDateTime.of(2021, 6, 3, 10, 15);
        LocalDateTime end = start.minusHours(23).minusMinutes(59).minusSeconds(59);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_WHOLE_DAY_variation1_negativeExactDay() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 20, 19, 10);
        LocalDateTime end = start.minusDays(1);
        verifyRelation(start, end);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_WHOLE_DAY_variation2_negativeDayAndSecond() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 20, 19, 10);
        LocalDateTime end = start.minusDays(1).minusSeconds(1);
        verifyRelation(start, end);
    }

    @Test
    public void INSTANT_POSITIVE_SUBDAY_variation1_oneSecond() {
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        Instant end = start.plusSeconds(1);
        verifyRelation(start, end);
    }

    @Test
    public void INSTANT_POSITIVE_SUBDAY_variation2_eightySixThousandThreeHundredNinetyNineSeconds() {
        Instant start = Instant.parse("2020-01-01T00:00:00Z");
        Instant end = start.plusSeconds(86_399);
        verifyRelation(start, end);
    }

    @Test
    public void INSTANT_EXACT_WHOLE_DAYS_variation1_oneDay() {
        Instant start = Instant.parse("2001-02-03T04:05:06Z");
        Instant end = start.plusSeconds(86_400);
        verifyRelation(start, end);
    }

    @Test
    public void INSTANT_EXACT_WHOLE_DAYS_variation2_tenDays() {
        Instant start = Instant.parse("2001-02-03T04:05:06Z");
        Instant end = start.plusSeconds(864_000);
        verifyRelation(start, end);
    }

    @Test
    public void INSTANT_NEGATIVE_TRUNCATION_variation1_negativeAlmostDay() {
        Instant start = Instant.parse("2010-07-08T09:10:11Z");
        Instant end = start.minusSeconds(86_399);
        verifyRelation(start, end);
    }

    @Test
    public void INSTANT_NEGATIVE_TRUNCATION_variation2_negativeExactDay() {
        Instant start = Instant.parse("2010-07-08T09:10:11Z");
        Instant end = start.minusSeconds(86_400);
        verifyRelation(start, end);
    }

    @Test
    public void INSTANT_INT_RESULT_LIMITS_variation1_integerMaximumDays() {
        Instant start = Instant.EPOCH;
        Instant end = start.plusSeconds((long) Integer.MAX_VALUE * 86_400L);
        verifyRelation(start, end);
    }

    @Test
    public void INSTANT_INT_RESULT_LIMITS_variation2_integerMinimumDays() {
        Instant start = Instant.EPOCH;
        Instant end = start.plusSeconds((long) Integer.MIN_VALUE * 86_400L);
        verifyRelation(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_EQUAL_variation1_fixedPositiveOffset() {
        OffsetDateTime start = OffsetDateTime.of(
                2022, 3, 5, 14, 20, 30, 0, ZoneOffset.ofHoursMinutes(5, 30));
        OffsetDateTime end = start;
        verifyRelation(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_EXACT_FORWARD_AND_REVERSE_variation1_forwardSevenDays() {
        OffsetDateTime start = OffsetDateTime.of(
                2021, 8, 9, 7, 30, 0, 0, ZoneOffset.ofHours(-4));
        OffsetDateTime end = start.plusDays(7);
        verifyRelation(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_EXACT_FORWARD_AND_REVERSE_variation2_reverseSevenDays() {
        OffsetDateTime start = OffsetDateTime.of(
                2021, 8, 9, 7, 30, 0, 0, ZoneOffset.ofHours(9));
        OffsetDateTime end = start.minusDays(7);
        verifyRelation(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_SUBDAY_TRUNCATION_variation1_positiveTwentyThreeHours() {
        OffsetDateTime start = OffsetDateTime.of(
                2019, 11, 10, 3, 0, 0, 0, ZoneOffset.ofHours(2));
        OffsetDateTime end = start.plusHours(23);
        verifyRelation(start, end);
    }

    @Test
    public void OFFSET_DATE_TIME_SUBDAY_TRUNCATION_variation2_negativeTwentyThreeHours() {
        OffsetDateTime start = OffsetDateTime.of(
                2019, 11, 10, 3, 0, 0, 0, ZoneOffset.ofHours(-7));
        OffsetDateTime end = start.minusHours(23);
        verifyRelation(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_SPRING_TRANSITION_variation1_newYork() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2021, 3, 13, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2021, 3, 14, 12, 0, 0, 0, zone);
        verifyRelation(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_SPRING_TRANSITION_variation2_berlin() {
        ZoneId zone = ZoneId.of("Europe/Berlin");
        ZonedDateTime start = ZonedDateTime.of(2021, 3, 27, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2021, 3, 28, 12, 0, 0, 0, zone);
        verifyRelation(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_AUTUMN_TRANSITION_variation1_newYork() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2021, 11, 6, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2021, 11, 7, 12, 0, 0, 0, zone);
        verifyRelation(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_AUTUMN_TRANSITION_variation2_berlin() {
        ZoneId zone = ZoneId.of("Europe/Berlin");
        ZonedDateTime start = ZonedDateTime.of(2021, 10, 30, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2021, 10, 31, 12, 0, 0, 0, zone);
        verifyRelation(start, end);
    }

    @Test
    public void ZONED_DATE_TIME_REVERSE_MULTI_DAY_variation1_utc() {
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime start = ZonedDateTime.of(2023, 7, 20, 16, 40, 0, 0, zone);
        ZonedDateTime end = start.minusDays(10);
        verifyRelation(start, end);
    }

    @Test
    public void JAPANESE_DATE_ERA_BOUNDARY_variation1_heiseiToReiwa() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2019, 4, 30));
        JapaneseDate end = JapaneseDate.from(LocalDate.of(2019, 5, 2));
        verifyRelation(start, end);
    }

    @Test
    public void HIJRAH_DATE_MONTH_BOUNDARY_variation1_lastDayOfMonth() {
        HijrahDate monthStart = HijrahDate.of(1442, 8, 1);
        HijrahDate start = HijrahDate.of(1442, 8, monthStart.lengthOfMonth());
        HijrahDate end = start.plus(2, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    public void THAI_BUDDHIST_DATE_DIRECTIONS_variation1_forwardThirtyDays() {
        ThaiBuddhistDate start = ThaiBuddhistDate.from(LocalDate.of(2020, 5, 10));
        ThaiBuddhistDate end = start.plus(30, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }

    @Test
    public void THAI_BUDDHIST_DATE_DIRECTIONS_variation2_reverseThirtyDays() {
        ThaiBuddhistDate start = ThaiBuddhistDate.from(LocalDate.of(2020, 5, 10));
        ThaiBuddhistDate end = start.minus(30, ChronoUnit.DAYS);
        verifyRelation(start, end);
    }
}
