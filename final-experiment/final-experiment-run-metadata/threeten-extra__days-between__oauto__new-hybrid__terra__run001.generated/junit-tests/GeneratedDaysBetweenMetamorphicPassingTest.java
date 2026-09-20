import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private Temporal[] generateFollowUp(Temporal startDateInclusive, Temporal endDateExclusive) {
        return new Temporal[] {
                startDateInclusive.plus(37, ChronoUnit.DAYS),
                endDateExclusive.plus(37, ChronoUnit.DAYS)
        };
    }

    @Test
    void testLOCAL_DATE_EQUAL_ZERO_identicalDate() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 15);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_ONE_DAY_SINGLETON_consecutiveDates() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 16);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_FORWARD_MULTI_DAY_januaryToFebruary() {
        Temporal start = LocalDate.of(2024, 1, 10);
        Temporal end = LocalDate.of(2024, 2, 20);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_REVERSE_MULTI_DAY_februaryToJanuary() {
        Temporal start = LocalDate.of(2024, 2, 20);
        Temporal end = LocalDate.of(2024, 1, 10);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_MONTH_END_januaryBoundary() {
        Temporal start = LocalDate.of(2024, 1, 31);
        Temporal end = LocalDate.of(2024, 2, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_YEAR_END_calendarBoundary() {
        Temporal start = LocalDate.of(2023, 12, 31);
        Temporal end = LocalDate.of(2024, 1, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_LEAP_DAY_februaryProgression() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_YEAR_ZERO_CROSSING_prolepticBoundary() {
        Temporal start = LocalDate.of(-1, 12, 31);
        Temporal end = LocalDate.of(0, 1, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_MIN_SAFE_FOR_SHIFT_minimumRange() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN.plusDays(1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_MAX_SAFE_FOR_SHIFT_maximumRange() {
        Temporal start = LocalDate.MAX.minusDays(38);
        Temporal end = LocalDate.MAX.minusDays(37);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_TIME_EQUAL_identicalDateTime() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_TIME_FORWARD_UNDER_DAY_shorterThanDay() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 11, 59, 59);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_TIME_EXACT_DAY_twentyFourHours() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 12, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_TIME_OVER_DAY_dayAndHour() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 13, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_TIME_REVERSE_UNDER_DAY_shortNegativeInterval() {
        Temporal start = LocalDateTime.of(2024, 6, 16, 11, 59, 59);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testLOCAL_DATE_TIME_REVERSE_EXACT_DAY_negativeTwentyFourHours() {
        Temporal start = LocalDateTime.of(2024, 6, 16, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testZONED_DATE_TIME_NORMAL_DAY_newYorkWinter() {
        ZonedDateTime start = ZonedDateTime.of(2024, 1, 10, 12, 0, 0, 0, ZoneId.of("America/New_York"));
        Temporal end = start.plusDays(5);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testZONED_DATE_TIME_SPRING_DST_DAY_newYorkTransition() {
        Temporal start = ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, ZoneId.of("America/New_York"));
        Temporal end = ZonedDateTime.of(2024, 3, 10, 12, 0, 0, 0, ZoneId.of("America/New_York"));
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testZONED_DATE_TIME_FALL_DST_DAY_newYorkTransition() {
        Temporal start = ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, ZoneId.of("America/New_York"));
        Temporal end = ZonedDateTime.of(2024, 11, 3, 12, 0, 0, 0, ZoneId.of("America/New_York"));
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testZONED_DATE_TIME_UNDER_LOCAL_DAY_parisShortInterval() {
        Temporal start = ZonedDateTime.of(2024, 1, 10, 12, 0, 0, 0, ZoneId.of("Europe/Paris"));
        Temporal end = ZonedDateTime.of(2024, 1, 11, 11, 59, 59, 0, ZoneId.of("Europe/Paris"));
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testZONED_DATE_TIME_SAME_INSTANT_DIFFERENT_ZONE_utcAndNewYork() {
        Temporal start = ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        Temporal end = ZonedDateTime.of(2023, 12, 31, 19, 0, 0, 0, ZoneId.of("America/New_York"));
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testOFFSET_DATE_TIME_SAME_OFFSET_DAY_indiaOffset() {
        Temporal start = OffsetDateTime.parse("2024-06-15T12:00:00+05:30");
        Temporal end = OffsetDateTime.parse("2024-06-16T12:00:00+05:30");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testOFFSET_DATE_TIME_OFFSET_NORMALIZATION_UNDER_DAY_twentyThreeHours() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        Temporal end = OffsetDateTime.parse("2024-01-02T00:00:00+01:00");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testOFFSET_DATE_TIME_SAME_INSTANT_DIFFERENT_OFFSET_extremeOffset() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00+18:00");
        Temporal end = OffsetDateTime.parse("2023-12-31T06:00:00Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testINSTANT_EQUAL_ZERO_epoch() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH;
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testINSTANT_UNDER_DAY_oneSecondShort() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plusSeconds(86399);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testINSTANT_EXACT_DAY_twentyFourHours() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plusSeconds(86400);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testINSTANT_NEGATIVE_EXACT_DAY_negativeTwentyFourHours() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.minusSeconds(86400);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testINSTANT_NANOSECOND_BELOW_DAY_nearCompleteDay() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plusSeconds(86399).plusNanos(999999999);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testINSTANT_INTEGER_MAX_DAYS_largestIntAmount() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plus(2147483647L, ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testINSTANT_INTEGER_MIN_DAYS_smallestIntAmount() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.minus(2147483648L, ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testJAPANESE_DATE_NORMAL_INTERVAL_juneRange() {
        Temporal start = JapaneseDate.of(2024, 6, 1);
        Temporal end = JapaneseDate.of(2024, 6, 11);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testJAPANESE_DATE_ERA_TRANSITION_heiseiToReiwa() {
        Temporal start = JapaneseDate.of(2019, 4, 30);
        Temporal end = JapaneseDate.of(2019, 5, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testTHAI_BUDDHIST_DATE_LEAP_YEAR_februaryProgression() {
        Temporal start = ThaiBuddhistDate.of(2567, 2, 28);
        Temporal end = ThaiBuddhistDate.of(2567, 3, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testMINGUO_DATE_YEAR_BOUNDARY_newYear() {
        Temporal start = MinguoDate.of(112, 12, 31);
        Temporal end = MinguoDate.of(113, 1, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void testHIJRAH_DATE_FORWARD_INTERVAL_ramadanRange() {
        HijrahDate start = HijrahDate.of(1445, 9, 1);
        Temporal end = start.plus(29, ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
