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
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static Temporal[] generateFollowUp(Temporal startDateInclusive, Temporal endDateExclusive) {
        return new Temporal[]{
                startDateInclusive.plus(37, ChronoUnit.DAYS),
                endDateExclusive.plus(37, ChronoUnit.DAYS)
        };
    }

    @Test
    public void EQUAL_LOCAL_DATES_ZERO_variation1_ordinaryDate() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 15);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ADJACENT_FORWARD_LOCAL_DATES_ONE_variation1_monthBoundary() {
        Temporal start = LocalDate.of(2023, 1, 31);
        Temporal end = LocalDate.of(2023, 2, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ADJACENT_REVERSE_LOCAL_DATES_MINUS_ONE_variation1_reverseMonthBoundary() {
        Temporal start = LocalDate.of(2024, 3, 1);
        Temporal end = LocalDate.of(2024, 2, 29);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SHORT_FORWARD_LOCAL_DATE_GAP_variation1_twoDays() {
        Temporal start = LocalDate.of(2022, 5, 10);
        Temporal end = LocalDate.of(2022, 5, 12);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SHORT_FORWARD_LOCAL_DATE_GAP_variation2_sixDaysAcrossMonth() {
        Temporal start = LocalDate.of(2022, 8, 29);
        Temporal end = LocalDate.of(2022, 9, 4);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SHORT_REVERSE_LOCAL_DATE_GAP_variation1_negativeTwoDays() {
        Temporal start = LocalDate.of(2022, 7, 12);
        Temporal end = LocalDate.of(2022, 7, 10);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SHORT_REVERSE_LOCAL_DATE_GAP_variation2_negativeSixAcrossYear() {
        Temporal start = LocalDate.of(2023, 1, 3);
        Temporal end = LocalDate.of(2022, 12, 28);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_WEEK_DIRECTED_GAPS_variation1_forwardWeek() {
        Temporal start = LocalDate.of(2020, 2, 25);
        Temporal end = LocalDate.of(2020, 3, 3);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_WEEK_DIRECTED_GAPS_variation2_reverseWeek() {
        Temporal start = LocalDate.of(2021, 1, 5);
        Temporal end = LocalDate.of(2020, 12, 29);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THIRTY_ONE_DAY_MONTH_SPAN_variation1_january() {
        Temporal start = LocalDate.of(2023, 1, 1);
        Temporal end = LocalDate.of(2023, 2, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THIRTY_ONE_DAY_MONTH_SPAN_variation2_july() {
        Temporal start = LocalDate.of(2024, 7, 1);
        Temporal end = LocalDate.of(2024, 8, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THIRTY_DAY_MONTH_SPAN_variation1_april() {
        Temporal start = LocalDate.of(2023, 4, 1);
        Temporal end = LocalDate.of(2023, 5, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THIRTY_DAY_MONTH_SPAN_variation2_september() {
        Temporal start = LocalDate.of(2024, 9, 1);
        Temporal end = LocalDate.of(2024, 10, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_YEAR_FEBRUARY_SPAN_variation1_year2019() {
        Temporal start = LocalDate.of(2019, 2, 1);
        Temporal end = LocalDate.of(2019, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_YEAR_FEBRUARY_SPAN_variation2_year2100() {
        Temporal start = LocalDate.of(2100, 2, 1);
        Temporal end = LocalDate.of(2100, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_YEAR_FEBRUARY_SPAN_variation1_year2020() {
        Temporal start = LocalDate.of(2020, 2, 1);
        Temporal end = LocalDate.of(2020, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_YEAR_FEBRUARY_SPAN_variation2_year2400() {
        Temporal start = LocalDate.of(2400, 2, 1);
        Temporal end = LocalDate.of(2400, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMPLETE_COMMON_ISO_YEAR_variation1_year2021() {
        Temporal start = LocalDate.of(2021, 1, 1);
        Temporal end = LocalDate.of(2022, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMPLETE_LEAP_ISO_YEAR_variation1_year2024() {
        Temporal start = LocalDate.of(2024, 1, 1);
        Temporal end = LocalDate.of(2025, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void GREGORIAN_CENTURY_1900_variation1_nonLeapCentury() {
        Temporal start = LocalDate.of(1900, 1, 1);
        Temporal end = LocalDate.of(1901, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void GREGORIAN_CENTURY_2000_variation1_leapCentury() {
        Temporal start = LocalDate.of(2000, 1, 1);
        Temporal end = LocalDate.of(2001, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROLEPTIC_YEAR_ZERO_variation1_forwardLeapYearZero() {
        Temporal start = LocalDate.of(0, 1, 1);
        Temporal end = LocalDate.of(1, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROLEPTIC_YEAR_ZERO_variation2_reverseLeapYearZero() {
        Temporal start = LocalDate.of(1, 1, 1);
        Temporal end = LocalDate.of(0, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MINIMUM_BOUNDARY_variation1_minimumAndNextDay() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN.plusDays(1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MAXIMUM_SHIFT_BOUNDARY_variation1_followUpReachesMaximum() {
        Temporal start = LocalDate.MAX.minusDays(38);
        Temporal end = LocalDate.MAX.minusDays(37);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MAXIMUM_INT_DAY_RESULT_variation1_exactMaximumInteger() {
        LocalDate base = LocalDate.ofEpochDay(0);
        Temporal start = base;
        Temporal end = base.plusDays(Integer.MAX_VALUE);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MINIMUM_INT_DAY_RESULT_variation1_exactMinimumInteger() {
        LocalDate base = LocalDate.ofEpochDay(0);
        Temporal start = base;
        Temporal end = base.plusDays(Integer.MIN_VALUE);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_POSITIVE_DAYS_variation1_twoDays() {
        Temporal start = LocalDateTime.of(2023, 3, 10, 14, 25, 30, 123456789);
        Temporal end = LocalDateTime.of(2023, 3, 12, 14, 25, 30, 123456789);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_POSITIVE_DAYS_variation2_fortyDays() {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 10, 20, 6, 5, 4, 3);
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusDays(40);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_NEGATIVE_DAYS_variation1_negativeTwoDays() {
        Temporal start = LocalDateTime.of(2023, 6, 12, 22, 15, 10, 700);
        Temporal end = LocalDateTime.of(2023, 6, 10, 22, 15, 10, 700);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_NEGATIVE_DAYS_variation2_negativeThirtyNineDays() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 2, 20, 3, 2, 1, 999);
        Temporal start = startDateTime;
        Temporal end = startDateTime.minusDays(39);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_SUBDAY_TRUNCATION_variation1_twelveHours() {
        LocalDateTime startDateTime = LocalDateTime.of(2022, 4, 10, 8, 0);
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusHours(12);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_SUBDAY_TRUNCATION_variation2_oneNanosecondShort() {
        LocalDateTime startDateTime = LocalDateTime.of(2022, 5, 20, 11, 30);
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusDays(1).minusNanos(1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_TRUNCATION_variation1_negativeSixHours() {
        LocalDateTime startDateTime = LocalDateTime.of(2022, 6, 20, 18, 0);
        Temporal start = startDateTime;
        Temporal end = startDateTime.minusHours(6);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_TRUNCATION_variation2_oneNanosecondShort() {
        LocalDateTime startDateTime = LocalDateTime.of(2022, 7, 20, 9, 45);
        Temporal start = startDateTime;
        Temporal end = startDateTime.minusDays(1).plusNanos(1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_JUST_OVER_POSITIVE_DAY_variation1_oneNanosecondOver() {
        LocalDateTime startDateTime = LocalDateTime.of(2021, 8, 1, 12, 0);
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusDays(1).plusNanos(1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_JUST_OVER_POSITIVE_DAY_variation2_thirtyHours() {
        LocalDateTime startDateTime = LocalDateTime.of(2021, 9, 10, 4, 30);
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusHours(30);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_JUST_OVER_NEGATIVE_DAY_variation1_oneNanosecondOver() {
        LocalDateTime startDateTime = LocalDateTime.of(2021, 10, 15, 12, 0);
        Temporal start = startDateTime;
        Temporal end = startDateTime.minusDays(1).minusNanos(1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_JUST_OVER_NEGATIVE_DAY_variation2_negativeThirtyHours() {
        LocalDateTime startDateTime = LocalDateTime.of(2021, 11, 20, 16, 45);
        Temporal start = startDateTime;
        Temporal end = startDateTime.minusHours(30);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_EXACT_DAY_MULTIPLES_variation1_positiveFiveDays() {
        Instant base = Instant.parse("2020-01-15T12:30:45Z");
        Temporal start = base;
        Temporal end = base.plus(5, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_EXACT_DAY_MULTIPLES_variation2_negativeNineDays() {
        Instant base = Instant.parse("2020-05-20T03:04:05.000000006Z");
        Temporal start = base;
        Temporal end = base.minus(9, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_SUBDAY_REMAINDERS_variation1_positiveRemainder() {
        Instant base = Instant.parse("2021-01-01T00:00:00Z");
        Temporal start = base;
        Temporal end = base.plusSeconds(86399);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_SUBDAY_REMAINDERS_variation2_negativeRemainder() {
        Instant base = Instant.parse("2021-02-01T00:00:00Z");
        Temporal start = base;
        Temporal end = base.minusSeconds(43201);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_FIXED_OFFSET_variation1_positiveOffsetForward() {
        OffsetDateTime startDateTime =
                OffsetDateTime.of(2022, 3, 5, 10, 20, 30, 400, ZoneOffset.ofHoursMinutes(5, 30));
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusDays(12);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_FIXED_OFFSET_variation2_negativeOffsetReverse() {
        OffsetDateTime startDateTime =
                OffsetDateTime.of(2022, 4, 15, 23, 10, 5, 0, ZoneOffset.ofHours(-7));
        Temporal start = startDateTime;
        Temporal end = startDateTime.minusDays(8);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_FIXED_ZONE_variation1_fixedOffsetZone() {
        ZonedDateTime startDateTime =
                ZonedDateTime.of(2020, 6, 10, 14, 0, 0, 0, ZoneId.of("+05:45"));
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusDays(17);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_SPRING_DST_TRANSITION_variation1_newYorkSpringForward() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2021, 3, 13, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2021, 3, 15, 12, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_AUTUMN_DST_TRANSITION_variation1_newYorkFallBack() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2021, 11, 6, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2021, 11, 8, 12, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JAPANESE_CHRONOLOGY_ERA_BOUNDARY_variation1_heiseiToReiwa() {
        Temporal start = JapaneseDate.from(LocalDate.of(2019, 4, 30));
        Temporal end = JapaneseDate.from(LocalDate.of(2019, 5, 1));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HIJRAH_CHRONOLOGY_MONTH_BOUNDARY_variation1_completeSupportedMonth() {
        HijrahDate firstOfMonth = HijrahDate.of(1445, 1, 1);
        Temporal start = firstOfMonth;
        Temporal end = firstOfMonth.plus(1, ChronoUnit.MONTHS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Temporal[] followUp = generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(followUp[0], followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
