import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
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
                source.arg0().plus(37, ChronoUnit.DAYS),
                source.arg1().plus(37, ChronoUnit.DAYS));
    }

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(sourceOutput.getAmount(), followUpOutput.getAmount());
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source,
            int expectedSourceAmount) {
        Days sourceOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(source);
        Days followUpOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(generateFollowUp(source));
        Assertions.assertEquals(expectedSourceAmount, sourceOutput.getAmount());
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_ZERO_GAP_sameDate() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        assertMetamorphicRelationFor(source(date, date), 0);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_GAP_consecutiveDates() {
        assertMetamorphicRelationFor(
                source(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16)),
                1);
    }

    @Test
    public void LOCAL_DATE_TWO_DAY_GAP_positivePair() {
        assertMetamorphicRelationFor(
                source(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 17)),
                2);
    }

    @Test
    public void LOCAL_DATE_NEGATIVE_ONE_DAY_GAP_reversedDates() {
        assertMetamorphicRelationFor(
                source(LocalDate.of(2024, 6, 16), LocalDate.of(2024, 6, 15)),
                -1);
    }

    @Test
    public void LOCAL_DATE_EXACT_WEEK_GAP_sevenDays() {
        assertMetamorphicRelationFor(
                source(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 8)),
                7);
    }

    @Test
    public void LOCAL_DATE_MONTH_BOUNDARY_januaryToFebruary() {
        assertMetamorphicRelationFor(
                source(LocalDate.of(2024, 1, 30), LocalDate.of(2024, 2, 2)),
                3);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_CROSSING_februaryToMarch() {
        assertMetamorphicRelationFor(
                source(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1)),
                2);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_newYear() {
        assertMetamorphicRelationFor(
                source(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 2)),
                2);
    }

    @Test
    public void LOCAL_DATE_INT_MAXIMUM_GAP_largestIntAmount() {
        LocalDate start = LocalDate.of(-999999999, 1, 1);
        LocalDate end = start.plusDays(2147483647L);
        assertMetamorphicRelationFor(source(start, end), Integer.MAX_VALUE);
    }

    @Test
    public void LOCAL_DATE_INT_MINIMUM_GAP_smallestIntAmount() {
        LocalDate start = LocalDate.of(-990000000, 1, 1);
        LocalDate end = start.minusDays(2147483648L);
        assertMetamorphicRelationFor(source(start, end), Integer.MIN_VALUE);
    }

    @Test
    public void LOCAL_DATE_NEAR_MINIMUM_SUPPORTED_DATE_minimumBoundary() {
        LocalDate start = LocalDate.MIN;
        assertMetamorphicRelationFor(source(start, start.plusDays(1)), 1);
    }

    @Test
    public void LOCAL_DATE_NEAR_MAXIMUM_SUPPORTED_DATE_maximumBoundary() {
        LocalDate start = LocalDate.MAX.minusDays(38);
        LocalDate end = LocalDate.MAX.minusDays(37);
        assertMetamorphicRelationFor(source(start, end), 1);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_matchingTimes() {
        assertMetamorphicRelationFor(
                source(
                        LocalDateTime.of(2024, 6, 15, 10, 30),
                        LocalDateTime.of(2024, 6, 16, 10, 30)),
                1);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_PARTIAL_DAY_shortInterval() {
        assertMetamorphicRelationFor(
                source(
                        LocalDateTime.of(2024, 6, 15, 10, 30),
                        LocalDateTime.of(2024, 6, 16, 10, 29)),
                0);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_PARTIAL_DAY_reversedShortInterval() {
        assertMetamorphicRelationFor(
                source(
                        LocalDateTime.of(2024, 6, 16, 10, 29),
                        LocalDateTime.of(2024, 6, 15, 10, 30)),
                0);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_MULTI_DAY_reversedWithRemainder() {
        assertMetamorphicRelationFor(
                source(
                        LocalDateTime.of(2024, 6, 20, 12, 0),
                        LocalDateTime.of(2024, 6, 17, 11, 0)),
                -3);
    }

    @Test
    public void INSTANT_EXACT_24_HOUR_DAY_utcMidnight() {
        assertMetamorphicRelationFor(
                source(
                        Instant.parse("2024-01-01T00:00:00Z"),
                        Instant.parse("2024-01-02T00:00:00Z")),
                1);
    }

    @Test
    public void INSTANT_PARTIAL_24_HOUR_INTERVAL_lessThanDay() {
        assertMetamorphicRelationFor(
                source(
                        Instant.parse("2024-01-01T00:00:00Z"),
                        Instant.parse("2024-01-01T23:59:59Z")),
                0);
    }

    @Test
    public void INSTANT_NEGATIVE_MULTI_DAY_INTERVAL_reversedTwoDays() {
        assertMetamorphicRelationFor(
                source(
                        Instant.parse("2024-01-03T00:00:00Z"),
                        Instant.parse("2024-01-01T00:00:00Z")),
                -2);
    }

    @Test
    public void ZONED_DATE_TIME_SPRING_DST_TRANSITION_newYorkNoon() {
        ZoneId zone = ZoneId.of("America/New_York");
        assertMetamorphicRelationFor(
                source(
                        ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone),
                        ZonedDateTime.of(2024, 3, 10, 12, 0, 0, 0, zone)),
                1);
    }

    @Test
    public void ZONED_DATE_TIME_FALL_DST_TRANSITION_newYorkNoon() {
        ZoneId zone = ZoneId.of("America/New_York");
        assertMetamorphicRelationFor(
                source(
                        ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, zone),
                        ZonedDateTime.of(2024, 11, 3, 12, 0, 0, 0, zone)),
                1);
    }

    @Test
    public void OFFSET_DATE_TIME_OFFSET_ADJUSTED_PARTIAL_DAY_differentOffsets() {
        assertMetamorphicRelationFor(
                source(
                        OffsetDateTime.parse("2024-01-01T00:00:00+00:00"),
                        OffsetDateTime.parse("2024-01-02T00:00:00+01:00")),
                0);
    }

    @Test
    public void JAPANESE_DATE_ERA_BOUNDARY_heiseiToReiwa() {
        assertMetamorphicRelationFor(
                source(JapaneseDate.of(2019, 4, 30), JapaneseDate.of(2019, 5, 2)),
                2);
    }

    @Test
    public void THAI_BUDDHIST_DATE_NEGATIVE_GAP_reversedChronologyDates() {
        assertMetamorphicRelationFor(
                source(ThaiBuddhistDate.of(2567, 1, 10), ThaiBuddhistDate.of(2567, 1, 7)),
                -3);
    }

    @Test
    public void MIXED_LOCAL_DATE_TO_LOCAL_DATE_TIME_compatibleTypes() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2024, 6, 15),
                        LocalDateTime.of(2024, 6, 17, 23, 59)),
                2);
    }
}
