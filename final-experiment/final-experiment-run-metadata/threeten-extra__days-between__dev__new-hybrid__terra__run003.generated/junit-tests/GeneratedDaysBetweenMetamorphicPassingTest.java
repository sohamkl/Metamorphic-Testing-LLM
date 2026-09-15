import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    @Test
    void LOCAL_DATE_EQUAL_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 15);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_FORWARD_ONE_DAY_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 16);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_REVERSE_ONE_DAY_variation1() {
        Temporal start = LocalDate.of(2024, 6, 16);
        Temporal end = LocalDate.of(2024, 6, 15);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MONTH_END_variation1() {
        Temporal start = LocalDate.of(2024, 1, 31);
        Temporal end = LocalDate.of(2024, 2, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_YEAR_END_variation1() {
        Temporal start = LocalDate.of(2023, 12, 31);
        Temporal end = LocalDate.of(2024, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_SPAN_variation1() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_NON_LEAP_FEBRUARY_variation1() {
        Temporal start = LocalDate.of(2023, 2, 28);
        Temporal end = LocalDate.of(2023, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_ERA_BOUNDARY_variation1() {
        Temporal start = LocalDate.of(-1, 12, 31);
        Temporal end = LocalDate.of(0, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MIN_SAFE_FOR_SHIFT_variation1() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN.plusDays(1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MAX_SAFE_FOR_SHIFT_variation1() {
        Temporal start = LocalDate.MAX.minusDays(38);
        Temporal end = LocalDate.MAX.minusDays(37);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_SUBDAY_FORWARD_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 11, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 12, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_MULTI_DAY_WITH_REMAINDER_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 8, 0);
        Temporal end = LocalDateTime.of(2024, 6, 17, 9, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_SUBDAY_REVERSE_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 16, 11, 0);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 3, 10, 12, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_FALL_DST_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 11, 3, 12, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_SKIPPED_CIVIL_DATE_variation1() {
        ZoneId zone = ZoneId.of("Pacific/Apia");
        Temporal start = ZonedDateTime.of(2011, 12, 29, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2011, 12, 31, 12, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_INSTANT_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        Temporal end = OffsetDateTime.parse("2024-01-01T01:00:00+01:00");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_SUBDAY_FORWARD_variation1() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T23:00:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_EXACT_DAY_REVERSE_variation1() {
        Temporal start = Instant.parse("2024-01-02T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T00:00:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_INT_MAX_DAY_COUNT_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plus((long) Integer.MAX_VALUE, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_INT_MIN_DAY_COUNT_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plus((long) Integer.MIN_VALUE, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAPANESE_DATE_ERA_CHANGE_variation1() {
        Temporal start = JapaneseDate.of(2019, 4, 30);
        Temporal end = JapaneseDate.of(2019, 5, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MINGUO_DATE_YEAR_CHANGE_variation1() {
        Temporal start = MinguoDate.of(112, 12, 31);
        Temporal end = MinguoDate.of(113, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIJRAH_DATE_DAY_INTERVAL_variation1() {
        Temporal start = HijrahDate.of(1445, 9, 1);
        Temporal end = start.plus(30, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TO_LOCAL_DATE_TIME_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDateTime.of(2024, 6, 17, 23, 59);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
