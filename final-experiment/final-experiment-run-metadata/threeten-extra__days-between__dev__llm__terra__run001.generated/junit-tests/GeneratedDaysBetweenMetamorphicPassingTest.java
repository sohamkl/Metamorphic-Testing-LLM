import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    @Test
    void LOCAL_DATE_SAME_DATE_ZERO_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 15);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_NEXT_DAY_POSITIVE_ONE_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 16);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_PREVIOUS_DAY_NEGATIVE_ONE_variation1() {
        Temporal start = LocalDate.of(2024, 6, 16);
        Temporal end = LocalDate.of(2024, 6, 15);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_SIX_DAY_POSITIVE_GAP_variation1() {
        Temporal start = LocalDate.of(2024, 6, 10);
        Temporal end = LocalDate.of(2024, 6, 16);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_SIX_DAY_NEGATIVE_GAP_variation1() {
        Temporal start = LocalDate.of(2024, 6, 16);
        Temporal end = LocalDate.of(2024, 6, 10);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MONTH_END_TO_NEXT_MONTH_variation1() {
        Temporal start = LocalDate.of(2024, 1, 31);
        Temporal end = LocalDate.of(2024, 2, 1);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_variation1() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_NON_LEAP_FEBRUARY_CROSSING_variation1() {
        Temporal start = LocalDate.of(2023, 2, 28);
        Temporal end = LocalDate.of(2023, 3, 1);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_variation1() {
        Temporal start = LocalDate.of(2023, 12, 31);
        Temporal end = LocalDate.of(2024, 1, 1);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_EXACT_SHIFT_SIZED_GAP_variation1() {
        Temporal start = LocalDate.of(2024, 1, 1);
        Temporal end = LocalDate.of(2024, 2, 7);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MAXIMUM_VICINITY_variation1() {
        Temporal start = LocalDate.MAX.minusDays(38);
        Temporal end = LocalDate.MAX.minusDays(37);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MINIMUM_VICINITY_variation1() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN.plusDays(1);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 10, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 10, 0);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_POSITIVE_PARTIAL_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 10, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 9, 0);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_POSITIVE_DAY_PLUS_HOUR_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 10, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 11, 0);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_EXACT_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 16, 10, 0);
        Temporal end = LocalDateTime.of(2024, 6, 15, 10, 0);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 16, 9, 0);
        Temporal end = LocalDateTime.of(2024, 6, 15, 10, 0);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NANO_BOUNDARY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 0, 0, 0, 1);
        Temporal end = LocalDateTime.of(2024, 6, 16, 0, 0, 0, 0);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_MAXIMUM_VICINITY_variation1() {
        Temporal start = LocalDateTime.MAX.minusDays(38);
        Temporal end = LocalDateTime.MAX.minusDays(37);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_EXACT_UTC_DAY_variation1() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-02T00:00:00Z");
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_POSITIVE_PARTIAL_DAY_variation1() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T23:00:00Z");
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_NEGATIVE_PARTIAL_DAY_variation1() {
        Temporal start = Instant.parse("2024-01-01T23:00:00Z");
        Temporal end = Instant.parse("2024-01-01T00:00:00Z");
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_INTEGER_MAXIMUM_DAY_COUNT_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plus(Integer.MAX_VALUE, ChronoUnit.DAYS);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_INTEGER_MINIMUM_DAY_COUNT_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plus(Integer.MIN_VALUE, ChronoUnit.DAYS);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00+05:30");
        Temporal end = OffsetDateTime.parse("2024-01-03T00:00:00+05:30");
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_PARTIAL_DAY_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00+02:00");
        Temporal end = OffsetDateTime.parse("2024-01-02T00:00:00+01:00");
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_NEGATIVE_WHOLE_DAY_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-02T12:00:00-04:00");
        Temporal end = OffsetDateTime.parse("2024-01-01T12:00:00-04:00");
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_variation1() {
        Temporal start = ZonedDateTime.parse("2024-03-30T12:00:00+01:00[Europe/Paris]");
        Temporal end = ZonedDateTime.parse("2024-04-01T12:00:00+02:00[Europe/Paris]");
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_AUTUMN_DST_variation1() {
        Temporal start = ZonedDateTime.parse("2024-10-26T12:00:00+02:00[Europe/Paris]");
        Temporal end = ZonedDateTime.parse("2024-10-28T12:00:00+01:00[Europe/Paris]");
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_NEGATIVE_DST_CROSSING_variation1() {
        Temporal start = ZonedDateTime.parse("2024-04-01T12:00:00+02:00[Europe/Paris]");
        Temporal end = ZonedDateTime.parse("2024-03-30T12:00:00+01:00[Europe/Paris]");
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_FIXED_UTC_variation1() {
        Temporal start = ZonedDateTime.parse("2024-06-15T10:00:00Z[UTC]");
        Temporal end = ZonedDateTime.parse("2024-06-16T10:00:00Z[UTC]");
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAPANESE_DATE_WHOLE_DAY_GAP_variation1() {
        Temporal start = JapaneseDate.of(2019, 4, 30);
        Temporal end = JapaneseDate.of(2019, 5, 1);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAPANESE_DATE_TIME_PARTIAL_DAY_variation1() {
        Temporal start = JapaneseDate.of(2019, 5, 1).atTime(LocalTime.of(10, 0));
        Temporal end = JapaneseDate.of(2019, 5, 2).atTime(LocalTime.of(9, 0));
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIJRAH_DATE_WHOLE_DAY_GAP_variation1() {
        Temporal start = HijrahDate.of(1445, 9, 1);
        Temporal end = HijrahDate.of(1445, 9, 2);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIJRAH_DATE_MONTH_BOUNDARY_variation1() {
        Temporal start = HijrahDate.of(1445, 9, 29);
        Temporal end = HijrahDate.of(1445, 10, 1);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MINGUO_DATE_WHOLE_DAY_GAP_variation1() {
        Temporal start = MinguoDate.of(113, 1, 1);
        Temporal end = MinguoDate.of(113, 1, 2);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void THAI_BUDDHIST_DATE_WHOLE_DAY_GAP_variation1() {
        Temporal start = ThaiBuddhistDate.of(2567, 1, 1);
        Temporal end = ThaiBuddhistDate.of(2567, 1, 2);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_ISO_LOCAL_DATE_TO_LOCAL_DATE_TIME_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDateTime.of(2024, 6, 16, 23, 59);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_ISO_TO_JAPANESE_DATE_variation1() {
        Temporal start = LocalDate.of(2019, 4, 30);
        Temporal end = JapaneseDate.of(2019, 5, 1);
        Days sourceOutput = Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        DaysBetweenMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
