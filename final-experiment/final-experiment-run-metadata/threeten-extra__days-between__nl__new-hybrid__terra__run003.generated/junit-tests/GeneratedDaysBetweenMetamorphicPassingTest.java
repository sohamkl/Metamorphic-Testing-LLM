import static java.time.temporal.ChronoUnit.DAYS;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input source(Temporal start, Temporal end) {
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(start, end);
    }

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input generateFollowUp(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(
                source.arg0().plus(37, DAYS),
                source.arg1().plus(37, DAYS));
    }

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(sourceOutput.getAmount(), followUpOutput.getAmount());
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {
        Days sourceOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(source);
        Days followUpOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(
                generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_SAME_DATE_ZERO_identicalDate() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        assertMetamorphicRelationFor(source(date, date));
    }

    @Test
    void LOCAL_DATE_FORWARD_ONE_DAY_consecutiveDates() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = LocalDate.of(2024, 6, 16);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_BACKWARD_ONE_DAY_reversedConsecutiveDates() {
        LocalDate start = LocalDate.of(2024, 6, 16);
        LocalDate end = LocalDate.of(2024, 6, 15);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_ORDINARY_WITHIN_MONTH_withinMonth() {
        LocalDate start = LocalDate.of(2024, 6, 3);
        LocalDate end = LocalDate.of(2024, 6, 27);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_ORDINARY_MONTH_BOUNDARY_crossesMonth() {
        LocalDate start = LocalDate.of(2023, 1, 31);
        LocalDate end = LocalDate.of(2023, 3, 1);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_LEAP_DAY_CROSSING_leapYear() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDate end = LocalDate.of(2024, 3, 1);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_NON_LEAP_FEBRUARY_CROSSING_commonYear() {
        LocalDate start = LocalDate.of(2023, 2, 28);
        LocalDate end = LocalDate.of(2023, 3, 1);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_FORWARD_newYear() {
        LocalDate start = LocalDate.of(2023, 12, 31);
        LocalDate end = LocalDate.of(2024, 1, 2);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_YEAR_BOUNDARY_BACKWARD_reverseNewYear() {
        LocalDate start = LocalDate.of(2024, 1, 2);
        LocalDate end = LocalDate.of(2023, 12, 31);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_PRE_EPOCH_beforeEpoch() {
        LocalDate start = LocalDate.of(1969, 12, 20);
        LocalDate end = LocalDate.of(1970, 1, 10);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_MINIMUM_SUPPORTED_DATE_lowerBoundary() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN.plusDays(1);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_NEAR_MAXIMUM_SUPPORTED_DATE_upperBoundary() {
        LocalDate start = LocalDate.MAX.minusDays(38);
        LocalDate end = LocalDate.MAX.minusDays(37);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_LARGE_REPRESENTABLE_SPAN_intRangeSpan() {
        LocalDate start = LocalDate.of(-5_800_000, 1, 1);
        LocalDate end = start.plus(Integer.MAX_VALUE, DAYS);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_TIME_EXACT_DAY_matchingTimes() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 10, 30);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 10, 30);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_TIME_FORWARD_PARTIAL_DAY_truncation() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 12, 0);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 11, 59, 59, 999999999);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_TIME_BACKWARD_PARTIAL_DAY_reverseTruncation() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 16, 11, 59, 59, 999999999);
        LocalDateTime end = LocalDateTime.of(2024, 6, 15, 12, 0);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void LOCAL_DATE_TIME_MULTI_DAY_WITH_NANOS_matchingNanoseconds() {
        LocalDateTime start = LocalDateTime.of(2024, 2, 27, 23, 59, 59, 1);
        LocalDateTime end = LocalDateTime.of(2024, 3, 3, 23, 59, 59, 1);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void INSTANT_EXACT_MULTIPLE_OF_DAYS_thirtySevenDays() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = Instant.parse("2024-02-07T00:00:00Z");
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void INSTANT_FORWARD_PARTIAL_DAY_twentyThreeHours() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = Instant.parse("2024-01-01T23:00:00Z");
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void INSTANT_BACKWARD_PARTIAL_DAY_reversedTwentyThreeHours() {
        Instant start = Instant.parse("2024-01-01T23:00:00Z");
        Instant end = Instant.parse("2024-01-01T00:00:00Z");
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void INSTANT_INTEGER_MAXIMUM_DAY_COUNT_maximumInt() {
        Instant start = Instant.EPOCH;
        Instant end = start.plus(Integer.MAX_VALUE, DAYS);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void INSTANT_INTEGER_MINIMUM_DAY_COUNT_minimumInt() {
        Instant start = Instant.EPOCH;
        Instant end = start.minus(2147483648L, DAYS);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void OFFSET_DATE_TIME_SAME_OFFSET_fixedOffset() {
        OffsetDateTime start = OffsetDateTime.parse("2024-06-01T10:00:00+05:30");
        OffsetDateTime end = OffsetDateTime.parse("2024-06-11T10:00:00+05:30");
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_SAME_INSTANT_normalizedInstant() {
        OffsetDateTime start = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        OffsetDateTime end = OffsetDateTime.parse("2024-01-01T01:00:00+01:00");
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_EXACT_DAY_normalizedDay() {
        OffsetDateTime start = OffsetDateTime.parse("2024-01-01T00:00:00Z");
        OffsetDateTime end = OffsetDateTime.parse("2024-01-02T01:00:00+01:00");
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void ZONED_DATE_TIME_SPRING_DST_DAY_newYorkTransition() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2024, 3, 10, 12, 0, 0, 0, zone);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void ZONED_DATE_TIME_AUTUMN_DST_DAY_newYorkTransition() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2024, 11, 3, 12, 0, 0, 0, zone);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void ZONED_DATE_TIME_MULTI_DAY_ZONE_RULES_paris() {
        ZoneId zone = ZoneId.of("Europe/Paris");
        ZonedDateTime start = ZonedDateTime.of(2024, 7, 1, 8, 45, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2024, 7, 15, 8, 45, 0, 0, zone);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void JAPANESE_DATE_LEAP_BOUNDARY_nonIsoDate() {
        JapaneseDate start = JapaneseDate.of(2024, 2, 28);
        JapaneseDate end = JapaneseDate.of(2024, 3, 1);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void JAPANESE_DATE_TIME_PARTIAL_DAY_nonIsoDateTime() {
        Temporal start = JapaneseDate.of(2024, 6, 15).atTime(LocalTime.of(12, 0));
        Temporal end = JapaneseDate.of(2024, 6, 16).atTime(LocalTime.of(11, 0));
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void THAI_BUDDHIST_DATE_FORWARD_sixDays() {
        ThaiBuddhistDate start = ThaiBuddhistDate.of(2567, 1, 30);
        ThaiBuddhistDate end = ThaiBuddhistDate.of(2567, 2, 5);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void MINGUO_DATE_BACKWARD_negativeInterval() {
        MinguoDate start = MinguoDate.of(113, 5, 20);
        MinguoDate end = MinguoDate.of(113, 5, 3);
        assertMetamorphicRelationFor(source(start, end));
    }

    @Test
    void HIJRAH_DATE_MONTH_TRANSITION_chronologyMonth() {
        HijrahDate start = HijrahDate.of(1445, 9, 1);
        HijrahDate end = start.plus(37, DAYS);
        assertMetamorphicRelationFor(source(start, end));
    }
}
