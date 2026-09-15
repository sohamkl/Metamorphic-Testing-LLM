import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    @Test
    void LOCAL_DATE_EQUAL_ENDPOINTS_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 15);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_ADJACENT_FORWARD_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 16);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_ADJACENT_REVERSE_variation1() {
        Temporal start = LocalDate.of(2024, 6, 16);
        Temporal end = LocalDate.of(2024, 6, 15);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MULTI_WEEK_FORWARD_variation1() {
        Temporal start = LocalDate.of(2023, 1, 1);
        Temporal end = LocalDate.of(2023, 2, 15);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MULTI_WEEK_REVERSE_variation1() {
        Temporal start = LocalDate.of(2023, 2, 15);
        Temporal end = LocalDate.of(2023, 1, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_INTERVAL_variation1() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MONTH_AND_YEAR_BOUNDARY_variation1() {
        Temporal start = LocalDate.of(2023, 12, 31);
        Temporal end = LocalDate.of(2024, 1, 2);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MINIMUM_SAFE_FOLLOW_UP_variation1() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN.plusDays(1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MAXIMUM_SAFE_FOLLOW_UP_variation1() {
        Temporal start = LocalDate.MAX.minusDays(74);
        Temporal end = LocalDate.MAX.minusDays(37);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_INTEGER_MAXIMUM_DIFFERENCE_variation1() {
        Temporal start = LocalDate.ofEpochDay(0);
        Temporal end = LocalDate.ofEpochDay(2147483647L);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_INTEGER_MINIMUM_DIFFERENCE_variation1() {
        Temporal start = LocalDate.ofEpochDay(0);
        Temporal end = LocalDate.ofEpochDay(-2147483648L);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 5, 10, 12, 30);
        Temporal end = LocalDateTime.of(2024, 5, 11, 12, 30);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_LESS_THAN_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 5, 10, 23, 30);
        Temporal end = LocalDateTime.of(2024, 5, 11, 23, 29);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_REVERSE_PARTIAL_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 5, 11, 23, 29);
        Temporal end = LocalDateTime.of(2024, 5, 10, 23, 30);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NANOSECOND_BOUNDARY_variation1() {
        Temporal start = LocalDateTime.of(2024, 5, 10, 0, 0, 0, 0);
        Temporal end = LocalDateTime.of(2024, 5, 10, 23, 59, 59, 999999999);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00+01:00");
        Temporal end = OffsetDateTime.parse("2024-01-03T00:00:00-05:00");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_ZERO_AFTER_OFFSET_ADJUSTMENT_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00+01:00");
        Temporal end = OffsetDateTime.parse("2024-01-01T23:00:00+00:00");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_FIXED_OFFSET_ZONE_variation1() {
        Temporal start = ZonedDateTime.parse("2024-01-01T00:00:00Z");
        Temporal end = ZonedDateTime.parse("2024-02-15T00:00:00Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_TRANSITION_variation1() {
        Temporal start = ZonedDateTime.of(
                2024, 3, 30, 12, 0, 0, 0, ZoneId.of("Europe/Paris"));
        Temporal end = ZonedDateTime.of(
                2024, 4, 2, 12, 0, 0, 0, ZoneId.of("Europe/Paris"));
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_AUTUMN_TRANSITION_variation1() {
        Temporal start = ZonedDateTime.of(
                2024, 10, 26, 12, 0, 0, 0, ZoneId.of("Europe/Paris"));
        Temporal end = ZonedDateTime.of(
                2024, 10, 29, 12, 0, 0, 0, ZoneId.of("Europe/Paris"));
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_SUBDAY_INTERVAL_variation1() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T23:59:59.999999999Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_LARGE_FORWARD_INTERVAL_variation1() {
        Temporal start = Instant.parse("1970-01-01T00:00:00Z");
        Temporal end = Instant.parse("2030-01-01T00:00:00Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAPANESE_DATE_LEAP_INTERVAL_variation1() {
        Temporal start = JapaneseDate.of(2024, 2, 28);
        Temporal end = JapaneseDate.of(2024, 3, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIJRAH_DATE_FORWARD_INTERVAL_variation1() {
        Temporal start = HijrahDate.of(1445, 9, 1);
        Temporal end = HijrahDate.of(1445, 9, 11);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NON_ISO_DATE_REVERSE_INTERVAL_variation1() {
        Temporal start = HijrahDate.of(1445, 10, 11);
        Temporal end = HijrahDate.of(1445, 10, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_SHIFT_CROSSES_YEAR_variation1() {
        Temporal start = LocalDate.of(2023, 12, 20);
        Temporal end = LocalDate.of(2023, 12, 25);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
