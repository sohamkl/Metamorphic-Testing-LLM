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

    private void assertMetamorphicRelation(
            org.threeten.extra.Days sourceOutput,
            org.threeten.extra.Days followUpOutput) {
        if (sourceOutput.getAmount() != followUpOutput.getAmount()) {
            throw new AssertionError(
                    "The day count changed from " + sourceOutput.getAmount()
                            + " to " + followUpOutput.getAmount());
        }
    }

    @Test
    void LOCAL_DATE_SAME_DATE_ZERO_equalDates() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 15);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_ADJACENT_FORWARD_ONE_consecutiveDates() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 16);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_ADJACENT_REVERSE_MINUS_ONE_consecutiveReverseDates() {
        Temporal start = LocalDate.of(2024, 6, 16);
        Temporal end = LocalDate.of(2024, 6, 15);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MONTH_END_CROSSING_januaryToFebruary() {
        Temporal start = LocalDate.of(2024, 1, 31);
        Temporal end = LocalDate.of(2024, 2, 2);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_februaryToMarch() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_YEAR_END_CROSSING_decemberToJanuary() {
        Temporal start = LocalDate.of(2023, 12, 31);
        Temporal end = LocalDate.of(2024, 1, 2);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_NEAR_MINIMUM_RANGE_minimumWithShiftRoom() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN.plusDays(37);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_NEAR_MAXIMUM_RANGE_maximumWithShiftRoom() {
        Temporal start = LocalDate.MAX.minusDays(38);
        Temporal end = LocalDate.MAX.minusDays(37);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_INCOMPLETE_FORWARD_DAY_crossingMidnight() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 11, 59, 59);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_FORWARD_DAY_sameLocalTime() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 12, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_INCOMPLETE_REVERSE_DAY_crossingMidnightReverse() {
        Temporal start = LocalDateTime.of(2024, 6, 16, 11, 59, 59);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_REVERSE_DAY_sameLocalTimeReverse() {
        Temporal start = LocalDateTime.of(2024, 6, 16, 12, 0);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 0);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_DAY_utcEndpoints() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        Temporal end = OffsetDateTime.parse("2024-01-02T00:00:00Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSET_INCOMPLETE_DAY_offsetNormalized() {
        Temporal start = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        Temporal end = OffsetDateTime.parse("2024-01-02T00:00:00+01:00");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_DAY_newYorkTransition() {
        Temporal start = ZonedDateTime.parse("2024-03-09T12:00:00-05:00[America/New_York]");
        Temporal end = ZonedDateTime.parse("2024-03-10T12:00:00-04:00[America/New_York]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZONED_DATE_TIME_FALL_DST_DAY_newYorkTransition() {
        Temporal start = ZonedDateTime.parse("2024-11-02T12:00:00-04:00[America/New_York]");
        Temporal end = ZonedDateTime.parse("2024-11-03T12:00:00-05:00[America/New_York]");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_SUBDAY_DURATION_lessThan24Hours() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-01T23:59:59Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_EXACT_24_HOUR_DURATION_oneDayDuration() {
        Temporal start = Instant.parse("2024-01-01T00:00:00Z");
        Temporal end = Instant.parse("2024-01-02T00:00:00Z");
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_INTEGER_MAXIMUM_DAYS_upperIntBoundary() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plus(2147483647L, ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INSTANT_INTEGER_MINIMUM_DAYS_lowerIntBoundary() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.minus(2147483648L, ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JAPANESE_DATE_LEAP_BOUNDARY_leapYearDates() {
        Temporal start = JapaneseDate.of(2024, 2, 28);
        Temporal end = JapaneseDate.of(2024, 3, 1);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIJRAH_DATE_DAY_INCREMENT_twoDayAdjustment() {
        Temporal start = HijrahDate.of(1445, 9, 1);
        Temporal end = start.plus(2, ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MINGUO_DATE_REVERSE_INCREMENT_threeDayReverseAdjustment() {
        Temporal end = MinguoDate.of(113, 6, 15);
        Temporal start = end.plus(3, ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void THAI_BUDDHIST_DATE_WEEK_LENGTH_sevenDayAdjustment() {
        Temporal start = ThaiBuddhistDate.of(2567, 6, 15);
        Temporal end = start.plus(7, ChronoUnit.DAYS);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIXED_ISO_AND_JAPANESE_DATE_consecutiveTimelineDates() {
        Temporal start = LocalDate.of(2024, 1, 1);
        Temporal end = JapaneseDate.of(2024, 1, 2);
        org.threeten.extra.Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between((Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
