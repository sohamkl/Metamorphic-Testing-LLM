import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    @Test
    public void LOCAL_DATE_ZERO_GAP_ordinaryDate() {
        Temporal start = LocalDate.of(2024, 1, 15);
        Temporal end = LocalDate.of(2024, 1, 15);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_leapBoundary() {
        Temporal start = LocalDate.of(2024, 1, 15);
        Temporal end = LocalDate.of(2024, 1, 16);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TWO_DAY_FORWARD_yearBoundary() {
        Temporal start = LocalDate.of(2024, 1, 15);
        Temporal end = LocalDate.of(2024, 1, 17);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_REVERSE_negativeGap() {
        Temporal start = LocalDate.of(2024, 1, 16);
        Temporal end = LocalDate.of(2024, 1, 15);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_WEEK_GAP_wholeWeek() {
        Temporal start = LocalDate.of(2024, 2, 1);
        Temporal end = LocalDate.of(2024, 2, 8);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_CROSSING_leapDay() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 2);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_crossYear() {
        Temporal start = LocalDate.of(2023, 12, 31);
        Temporal end = LocalDate.of(2024, 1, 2);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MAX_INT_FORWARD_largePositive() {
        LocalDate startDate = LocalDate.of(1970, 1, 1);
        Temporal start = startDate;
        Temporal end = startDate.plusDays(Integer.MAX_VALUE);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MAX_INT_REVERSE_largeNegative() {
        LocalDate base = LocalDate.of(1970, 1, 1);
        Temporal start = base.plusDays(Integer.MAX_VALUE);
        Temporal end = base;

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_NEAR_MINIMUM_lowerBoundary() {
        LocalDate startDate = LocalDate.MIN.plusDays(37);
        Temporal start = startDate;
        Temporal end = startDate.plusDays(1);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_NEAR_MAXIMUM_upperBoundary() {
        Temporal start = LocalDate.MAX.minusDays(38);
        Temporal end = LocalDate.MAX.minusDays(37);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_exactTwentyFourHours() {
        Temporal start = LocalDateTime.of(2024, 5, 1, 12, 30);
        Temporal end = LocalDateTime.of(2024, 5, 2, 12, 30);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_SUBDAY_FORWARD_shortInterval() {
        Temporal start = LocalDateTime.of(2024, 5, 1, 23, 30);
        Temporal end = LocalDateTime.of(2024, 5, 2, 0, 15);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERING_OFFSETS_fixedOffsets() {
        Temporal start = OffsetDateTime.parse("2024-06-01T00:00:00+01:00");
        Temporal end = OffsetDateTime.parse("2024-06-03T00:00:00+03:00");

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_DST_CROSSING_parisTransition() {
        Temporal start = ZonedDateTime.parse("2024-03-29T12:00:00+01:00[Europe/Paris]");
        Temporal end = ZonedDateTime.parse("2024-04-02T12:00:00+02:00[Europe/Paris]");

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_EXACT_UTC_DAYS_exactMultiple() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-04T00:00:00Z");

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_SUBDAY_REVERSE_negativeShortInterval() {
        Temporal start = Instant.parse("2024-01-02T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T12:00:00Z");

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JAPANESE_DATE_FORWARD_multiDayGap() {
        Temporal start = JapaneseDate.from(LocalDate.of(2024, 1, 10));
        Temporal end = JapaneseDate.from(LocalDate.of(2024, 1, 20));

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HIJRAH_DATE_REVERSE_negativeGap() {
        Temporal start = HijrahDate.from(LocalDate.of(2024, 2, 10));
        Temporal end = HijrahDate.from(LocalDate.of(2024, 2, 1));

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_THIRTY_SEVEN_DAY_SHIFT_ALIGNMENT_matchingShift() {
        Temporal start = LocalDate.of(2025, 1, 1);
        Temporal end = LocalDate.of(2025, 2, 7);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_LARGE_NORMAL_GAP_twoCenturies() {
        Temporal start = LocalDateTime.of(1900, 1, 1, 6, 0);
        Temporal end = LocalDateTime.of(2100, 1, 1, 6, 0);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MONTH_END_ALIGNMENT_variableMonthLength() {
        Temporal start = LocalDate.of(2024, 1, 31);
        Temporal end = LocalDate.of(2024, 3, 1);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_ZERO_AFTER_SHIFT_repeatedZero() {
        Temporal start = LocalDateTime.of(2024, 6, 30, 8, 0);
        Temporal end = LocalDateTime.of(2024, 6, 30, 8, 0);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_NEGATIVE_WEEK_GAP_reverseWeek() {
        Temporal start = OffsetDateTime.parse("2024-08-08T10:00:00+02:00");
        Temporal end = OffsetDateTime.parse("2024-08-01T10:00:00+02:00");

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_ZERO_GAP_identicalOffsetValues() {
        Temporal start = OffsetDateTime.parse("2024-07-01T10:15:00+05:30");
        Temporal end = OffsetDateTime.parse("2024-07-01T10:15:00+05:30");

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_YEAR_BOUNDARY_parisYearBoundary() {
        Temporal start = ZonedDateTime.parse("2023-12-30T09:00:00+01:00[Europe/Paris]");
        Temporal end = ZonedDateTime.parse("2024-01-03T09:00:00+01:00[Europe/Paris]");

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_EXACT_DAY_reverseExactDay() {
        Temporal start = LocalDateTime.of(2024, 9, 2, 8, 45);
        Temporal end = LocalDateTime.of(2024, 9, 1, 8, 45);

        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
