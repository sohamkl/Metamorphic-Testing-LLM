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
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        if (sourceOutput.getAmount() != followUpOutput.getAmount()) {
            throw new AssertionError(
                    "The day count changed from " + sourceOutput.getAmount()
                            + " to " + followUpOutput.getAmount());
        }
    }

    @Test
    void EQUAL_LOCAL_DATES_ZERO_ordinaryDate() {
        Temporal start = LocalDate.of(2024, 1, 15);
        Temporal end = LocalDate.of(2024, 1, 15);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EQUAL_LOCAL_DATES_ZERO_leapDate() {
        Temporal start = LocalDate.of(2024, 2, 29);
        Temporal end = LocalDate.of(2024, 2, 29);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EQUAL_INSTANTS_ZERO_fixedInstant() {
        Temporal start = Instant.parse("2023-06-15T10:20:30.123456789Z");
        Temporal end = Instant.parse("2023-06-15T10:20:30.123456789Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_FORWARD_offsetDateTime() {
        Temporal start = OffsetDateTime.parse("2021-02-28T08:30:00+05:30");
        Temporal end = OffsetDateTime.parse("2021-03-01T08:30:00+05:30");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_FORWARD_zonedSydneySpringTransition() {
        ZoneId zone = ZoneId.of("Australia/Sydney");
        Temporal start = ZonedDateTime.of(2024, 10, 5, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 10, 6, 12, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_BACKWARD_japaneseDates() {
        Temporal start = JapaneseDate.from(LocalDate.of(2022, 1, 1));
        Temporal end = JapaneseDate.from(LocalDate.of(2021, 12, 31));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_BACKWARD_hijrahDates() {
        Temporal start = HijrahDate.of(1445, 8, 15);
        Temporal end = start.minus(1, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void GAP_EQUALS_METAMORPHIC_SHIFT_positiveMinguo() {
        Temporal start = MinguoDate.from(LocalDate.of(2020, 5, 10));
        Temporal end = start.plus(37, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void GAP_EQUALS_METAMORPHIC_SHIFT_negativeThaiBuddhist() {
        Temporal start = ThaiBuddhistDate.from(LocalDate.of(2020, 8, 20));
        Temporal end = start.minus(37, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void GENERAL_POSITIVE_GAP_oneHundredTwentyThreeDays() {
        Temporal start = LocalDate.of(2018, 4, 12);
        Temporal end = start.plus(123, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void GENERAL_POSITIVE_GAP_fourHundredDays() {
        Temporal start = LocalDate.of(1999, 11, 3);
        Temporal end = start.plus(400, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void GENERAL_NEGATIVE_GAP_localDateTime() {
        Temporal start = LocalDateTime.of(2025, 1, 20, 17, 45);
        Temporal end = start.minus(12, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void GENERAL_NEGATIVE_GAP_instantFortyFiveDays() {
        Temporal start = Instant.parse("2024-07-01T00:00:00Z");
        Temporal end = start.minus(45, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_WEEK_MULTIPLE_twoWeeks() {
        Temporal start = LocalDate.of(2023, 7, 5);
        Temporal end = start.plus(14, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MONTH_END_CROSSING_augustToSeptember() {
        Temporal start = LocalDate.of(2023, 8, 31);
        Temporal end = LocalDate.of(2023, 9, 3);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MONTH_END_CROSSING_aprilToMay() {
        Temporal start = LocalDate.of(2022, 4, 30);
        Temporal end = LocalDate.of(2022, 5, 2);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COMMON_YEAR_FEBRUARY_BOUNDARY_crossIntoMarch() {
        Temporal start = LocalDate.of(2023, 2, 27);
        Temporal end = LocalDate.of(2023, 3, 2);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_INTERVAL_februaryTwentyEightToMarch() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_INTERVAL_endpointOnLeapDay() {
        Temporal start = LocalDate.of(2020, 2, 29);
        Temporal end = LocalDate.of(2020, 3, 3);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_END_CROSSING_adjacentYears() {
        Temporal start = LocalDate.of(2021, 12, 30);
        Temporal end = LocalDate.of(2022, 1, 2);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_SUBDAY_SAME_DATE_forwardHours() {
        Temporal start = LocalDateTime.of(2024, 5, 4, 8, 15);
        Temporal end = LocalDateTime.of(2024, 5, 4, 22, 45);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_SUBDAY_SAME_DATE_backwardHours() {
        Temporal start = LocalDateTime.of(2024, 6, 18, 20, 0);
        Temporal end = LocalDateTime.of(2024, 6, 18, 7, 30);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_CROSS_MIDNIGHT_SUBDAY_twoHours() {
        Temporal start = LocalDateTime.of(2024, 1, 10, 23, 0);
        Temporal end = LocalDateTime.of(2024, 1, 11, 1, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_CROSS_MIDNIGHT_SUBDAY_twentyThreeHours() {
        Temporal start = LocalDateTime.of(2023, 9, 14, 12, 30);
        Temporal end = LocalDateTime.of(2023, 9, 15, 11, 30);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_matchingTimes() {
        Temporal start = LocalDateTime.of(2022, 10, 8, 6, 12, 34);
        Temporal end = start.plus(1, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_DAY_WITH_REMAINDER_oneDayFiveHours() {
        Temporal start = LocalDateTime.of(2020, 7, 8, 10, 0);
        Temporal end = LocalDateTime.of(2020, 7, 9, 15, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_DAY_WITH_REMAINDER_twoDaysTwentyHours() {
        Temporal start = LocalDateTime.of(2020, 11, 2, 4, 30);
        Temporal end = LocalDateTime.of(2020, 11, 5, 0, 30);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_TRUNCATION_belowOneDay() {
        Temporal start = LocalDateTime.of(2024, 3, 5, 18, 0);
        Temporal end = LocalDateTime.of(2024, 3, 5, 1, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_TRUNCATION_dayPlusRemainder() {
        Temporal start = LocalDateTime.of(2024, 3, 8, 18, 0);
        Temporal end = LocalDateTime.of(2024, 3, 7, 12, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NANOSECOND_BELOW_WHOLE_DAY_localDateTime() {
        LocalDateTime startValue = LocalDateTime.of(2024, 8, 1, 0, 0);
        Temporal start = startValue;
        Temporal end = startValue.plusDays(1).minusNanos(1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_SUBDAY_twelveHours() {
        Temporal start = Instant.parse("2021-04-03T00:00:00Z");
        Temporal end = start.plus(12, ChronoUnit.HOURS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_EXACT_86400_SECONDS_oneTimelineDay() {
        Temporal start = Instant.parse("2019-12-31T23:00:00Z");
        Temporal end = start.plus(86400, ChronoUnit.SECONDS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_NEGATIVE_MULTI_DAY_fiveDays() {
        Temporal start = Instant.parse("2022-02-20T05:00:00Z");
        Temporal end = start.minus(5, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_exactWholeDays() {
        ZoneOffset offset = ZoneOffset.ofHoursMinutes(5, 30);
        Temporal start = OffsetDateTime.of(2023, 2, 4, 9, 10, 0, 0, offset);
        Temporal end = start.plus(3, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_partialDayRemainder() {
        ZoneOffset offset = ZoneOffset.ofHours(-4);
        OffsetDateTime startValue =
                OffsetDateTime.of(2023, 6, 1, 7, 0, 0, 0, offset);
        Temporal start = startValue;
        Temporal end = startValue.plusDays(2).plusHours(6);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_SAME_INSTANT_fixedOffsets() {
        Instant instant = Instant.parse("2024-01-02T10:00:00Z");
        Temporal start = instant.atOffset(ZoneOffset.ofHours(2));
        Temporal end = instant.atOffset(ZoneOffset.ofHours(-7));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_LOCAL_DIFFERENT_OFFSETS_fiveHourDisplacement() {
        LocalDateTime local = LocalDateTime.of(2024, 4, 7, 12, 0);
        Temporal start = OffsetDateTime.of(local, ZoneOffset.ofHours(2));
        Temporal end = OffsetDateTime.of(local, ZoneOffset.ofHours(-3));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_ORDINARY_DAY_utcZone() {
        ZoneId zone = ZoneId.of("UTC");
        Temporal start = ZonedDateTime.of(2024, 5, 8, 15, 30, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 5, 9, 15, 30, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_newYork() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 3, 10, 12, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_berlin() {
        ZoneId zone = ZoneId.of("Europe/Berlin");
        Temporal start = ZonedDateTime.of(2024, 3, 30, 11, 15, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 3, 31, 11, 15, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_AUTUMN_DST_newYork() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 11, 3, 12, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_AUTUMN_DST_berlin() {
        ZoneId zone = ZoneId.of("Europe/Berlin");
        Temporal start = ZonedDateTime.of(2024, 10, 26, 9, 45, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 10, 27, 9, 45, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIFFERENT_ZONES_SAME_INSTANT_utcAndTokyo() {
        Instant instant = Instant.parse("2023-08-12T16:30:00Z");
        Temporal start = instant.atZone(ZoneId.of("UTC"));
        Temporal end = instant.atZone(ZoneId.of("Asia/Tokyo"));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAPANESE_DATE_GAP_twentyDays() {
        Temporal start = JapaneseDate.from(LocalDate.of(2019, 5, 2));
        Temporal end = start.plus(20, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIJRAH_DATE_MONTH_BOUNDARY_twoDaysAcrossMonth() {
        Temporal start = HijrahDate.of(1445, 9, 29);
        Temporal end = start.plus(2, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MINGUO_DATE_GAP_fiftyDays() {
        Temporal start = MinguoDate.from(LocalDate.of(2021, 3, 1));
        Temporal end = start.plus(50, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void THAI_BUDDHIST_DATE_NEGATIVE_GAP_twentyFiveDays() {
        Temporal start = ThaiBuddhistDate.from(LocalDate.of(2022, 9, 30));
        Temporal end = start.minus(25, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void COMPATIBLE_MIXED_CHRONOLOGY_SAME_DAY_japaneseAndMinguo() {
        LocalDate isoDate = LocalDate.of(2023, 10, 17);
        Temporal start = JapaneseDate.from(isoDate);
        Temporal end = MinguoDate.from(isoDate);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAXIMUM_INT_DAY_COUNT_epochBasedLocalDates() {
        Temporal start = LocalDate.ofEpochDay(0);
        Temporal end = LocalDate.ofEpochDay(Integer.MAX_VALUE);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MINIMUM_INT_DAY_COUNT_epochBasedLocalDates() {
        Temporal start = LocalDate.ofEpochDay(0);
        Temporal end = LocalDate.ofEpochDay(Integer.MIN_VALUE);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
