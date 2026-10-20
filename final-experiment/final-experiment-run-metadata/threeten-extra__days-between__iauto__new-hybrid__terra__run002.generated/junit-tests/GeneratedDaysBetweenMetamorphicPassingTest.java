import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        if (sourceOutput.getAmount() != followUpOutput.getAmount()) {
            throw new AssertionError(
                    "Shifting both endpoints by the developer-provided number of days changed the day count");
        }
    }

    @Test
    void LOCAL_DATE_EQUAL_ENDPOINTS_variation1() {
        Temporal start = LocalDate.of(2024, 1, 1);
        Temporal end = LocalDate.of(2024, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_EQUAL_ENDPOINTS_variation2() {
        Temporal start = LocalDate.of(2024, 2, 29);
        Temporal end = LocalDate.of(2024, 2, 29);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_FORWARD_ADJACENT_AND_MULTI_DAY_variation1() {
        Temporal start = LocalDate.of(2024, 1, 1);
        Temporal end = LocalDate.of(2024, 1, 2);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_FORWARD_ADJACENT_AND_MULTI_DAY_variation2() {
        Temporal start = LocalDate.of(2024, 1, 1);
        Temporal end = LocalDate.of(2024, 1, 11);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_REVERSE_ADJACENT_AND_MULTI_DAY_variation1() {
        Temporal start = LocalDate.of(2024, 1, 2);
        Temporal end = LocalDate.of(2024, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_REVERSE_ADJACENT_AND_MULTI_DAY_variation2() {
        Temporal start = LocalDate.of(2024, 1, 11);
        Temporal end = LocalDate.of(2024, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_variation1() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 2, 29);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_variation2() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MONTH_AND_YEAR_TRANSITIONS_variation1() {
        Temporal start = LocalDate.of(2024, 1, 31);
        Temporal end = LocalDate.of(2024, 2, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MONTH_AND_YEAR_TRANSITIONS_variation2() {
        Temporal start = LocalDate.of(2023, 12, 31);
        Temporal end = LocalDate.of(2024, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MINIMUM_SIDE_WITH_FOLLOW_UP_HEADROOM_variation1() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN.plusDays(1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MAXIMUM_SIDE_WITH_FOLLOW_UP_HEADROOM_variation1() {
        Temporal start = LocalDate.MAX.minusDays(38);
        Temporal end = LocalDate.MAX.minusDays(37);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EQUAL_AND_SAME_DATE_PARTIAL_variation1() {
        Temporal start = LocalDateTime.of(2024, 1, 1, 12, 0);
        Temporal end = LocalDateTime.of(2024, 1, 1, 12, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EQUAL_AND_SAME_DATE_PARTIAL_variation2() {
        Temporal start = LocalDateTime.of(2024, 1, 1, 0, 0);
        Temporal end = LocalDateTime.of(2024, 1, 1, 23, 59);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_CROSS_MIDNIGHT_UNDER_ONE_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 1, 1, 23, 0);
        Temporal end = LocalDateTime.of(2024, 1, 2, 1, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_CROSS_MIDNIGHT_UNDER_ONE_DAY_variation2() {
        Temporal start = LocalDateTime.of(2024, 2, 28, 23, 30);
        Temporal end = LocalDateTime.of(2024, 2, 29, 0, 30);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_AND_REMAINDER_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 1, 1, 8, 0);
        Temporal end = LocalDateTime.of(2024, 1, 2, 8, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_AND_REMAINDER_DAY_variation2() {
        Temporal start = LocalDateTime.of(2024, 1, 1, 8, 0);
        Temporal end = LocalDateTime.of(2024, 1, 2, 9, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_AND_EXACT_variation1() {
        Temporal start = LocalDateTime.of(2024, 1, 2, 1, 0);
        Temporal end = LocalDateTime.of(2024, 1, 1, 23, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_AND_EXACT_variation2() {
        Temporal start = LocalDateTime.of(2024, 1, 2, 8, 0);
        Temporal end = LocalDateTime.of(2024, 1, 1, 8, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_LEAP_AND_YEAR_BOUNDARIES_variation1() {
        Temporal start = LocalDateTime.of(2024, 2, 28, 6, 0);
        Temporal end = LocalDateTime.of(2024, 2, 29, 6, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_LEAP_AND_YEAR_BOUNDARIES_variation2() {
        Temporal start = LocalDateTime.of(2023, 12, 31, 6, 0);
        Temporal end = LocalDateTime.of(2024, 1, 1, 6, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_INSTANT_DIFFERENT_OFFSETS_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00Z");
        Temporal end = OffsetDateTime.parse("2024-01-01T01:00+01:00");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_INSTANT_DIFFERENT_OFFSETS_variation2() {
        Temporal start = OffsetDateTime.parse("2024-06-01T12:00+02:00");
        Temporal end = OffsetDateTime.parse("2024-06-01T10:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_EXACT_ELAPSED_DAYS_ACROSS_OFFSETS_variation1() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00Z");
        Temporal end = OffsetDateTime.parse("2024-01-02T01:00+01:00");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_EXACT_ELAPSED_DAYS_ACROSS_OFFSETS_variation2() {
        Temporal start = OffsetDateTime.parse("2024-01-03T00:00+02:00");
        Temporal end = OffsetDateTime.parse("2024-01-01T00:00+02:00");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_DST_SPRING_FORWARD_variation1() {
        Temporal start = ZonedDateTime.parse("2024-03-30T12:00+01:00[Europe/Paris]");
        Temporal end = ZonedDateTime.parse("2024-03-31T12:00+02:00[Europe/Paris]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_DST_AUTUMN_FALL_BACK_variation1() {
        Temporal start = ZonedDateTime.parse("2024-10-26T12:00+02:00[Europe/Paris]");
        Temporal end = ZonedDateTime.parse("2024-10-27T12:00+01:00[Europe/Paris]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_SAME_INSTANT_DIFFERENT_ZONES_variation1() {
        Temporal start = ZonedDateTime.parse("2024-01-01T00:00Z[UTC]");
        Temporal end = ZonedDateTime.parse("2024-01-01T01:00+01:00[Europe/Paris]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_SAME_INSTANT_DIFFERENT_ZONES_variation2() {
        Temporal start = ZonedDateTime.parse("2024-07-01T12:00Z[UTC]");
        Temporal end = ZonedDateTime.parse("2024-07-01T08:00-04:00[America/New_York]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_PARTIAL_DAY_ACROSS_LOCAL_DATE_variation1() {
        Temporal start = ZonedDateTime.parse("2024-01-01T23:00Z[UTC]");
        Temporal end = ZonedDateTime.parse("2024-01-02T01:00Z[UTC]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_PARTIAL_DAY_ACROSS_LOCAL_DATE_variation2() {
        Temporal start = ZonedDateTime.parse("2024-01-01T01:00+09:00[Asia/Tokyo]");
        Temporal end = ZonedDateTime.parse("2024-01-01T23:00+09:00[Asia/Tokyo]");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_ZERO_AND_SUBDAY_variation1() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T00:00:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_ZERO_AND_SUBDAY_variation2() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T23:59:59Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_EXACT_AND_MULTI_DAY_variation1() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-02T00:00:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_EXACT_AND_MULTI_DAY_variation2() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-11T00:00:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_NEGATIVE_EXACT_AND_PARTIAL_variation1() {
        Temporal start = Instant.parse("2024-01-02T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T00:00:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_NEGATIVE_EXACT_AND_PARTIAL_variation2() {
        Temporal start = Instant.parse("2024-01-02T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T01:00:00Z");
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_INT_MAXIMUM_DAY_COUNT_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plus(2147483647L, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_INT_MINIMUM_DAY_COUNT_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.minus(2147483648L, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAPANESE_DATE_ERA_TRANSITION_variation1() {
        Temporal start = JapaneseDate.of(2019, 4, 30);
        Temporal end = JapaneseDate.of(2019, 5, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void THAI_BUDDHIST_DATE_LEAP_DAY_variation1() {
        Temporal start = ThaiBuddhistDate.of(2567, 2, 28);
        Temporal end = ThaiBuddhistDate.of(2567, 2, 29);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MINGUO_DATE_YEAR_TRANSITION_variation1() {
        Temporal start = MinguoDate.of(113, 12, 31);
        Temporal end = MinguoDate.of(114, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIJRAH_DATE_MULTI_DAY_variation1() {
        Temporal start = HijrahDate.of(1445, 9, 1);
        Temporal end = start.plus(2, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_START_LOCAL_DATE_TIME_END_variation1() {
        Temporal start = LocalDate.of(2024, 1, 1);
        Temporal end = LocalDateTime.of(2024, 1, 3, 0, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_START_LOCAL_DATE_TIME_END_variation2() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDateTime.of(2024, 3, 1, 23, 59);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
