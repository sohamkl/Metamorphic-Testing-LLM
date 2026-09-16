import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static final long SHIFT_DAYS = 37L;

    private static final class TemporalPair {
        private final Temporal start;
        private final Temporal end;

        private TemporalPair(Temporal start, Temporal end) {
            this.start = start;
            this.end = end;
        }
    }

    private TemporalPair generateFollowUp(TemporalPair source) {
        return new TemporalPair(
                source.start.plus(SHIFT_DAYS, ChronoUnit.DAYS),
                source.end.plus(SHIFT_DAYS, ChronoUnit.DAYS));
    }

    private Days runSut(TemporalPair input) {
        return Days.between(input.start, input.end);
    }

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(sourceOutput.getAmount(), followUpOutput.getAmount());
    }

    private void assertMetamorphicRelationFor(TemporalPair source, int expectedSourceAmount) {
        Days sourceOutput = runSut(source);
        Assertions.assertEquals(expectedSourceAmount, sourceOutput.getAmount());
        Days followUpOutput = runSut(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_EQUAL_ZERO_sameIsoDate() {
        LocalDate date = LocalDate.of(2024, 6, 15);
        assertMetamorphicRelationFor(new TemporalPair(date, date), 0);
    }

    @Test
    public void LOCAL_DATE_EXACTLY_ONE_DAY_adjacentDates() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 6, 15),
                LocalDate.of(2024, 6, 16));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void LOCAL_DATE_CROSS_MONTH_januaryToFebruary() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 1, 30),
                LocalDate.of(2024, 2, 3));
        assertMetamorphicRelationFor(source, 4);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_februaryToMarch() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 2, 28),
                LocalDate.of(2024, 3, 1));
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    public void LOCAL_DATE_REVERSED_NEGATIVE_descendingDates() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 6, 15));
        assertMetamorphicRelationFor(source, -5);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MAXIMUM_largestIntRange() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDate end = start.plusDays(Integer.MAX_VALUE);
        assertMetamorphicRelationFor(new TemporalPair(start, end), Integer.MAX_VALUE);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MINIMUM_smallestIntRange() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDate end = start.minusDays(2147483648L);
        assertMetamorphicRelationFor(new TemporalPair(start, end), Integer.MIN_VALUE);
    }

    @Test
    public void LOCAL_DATE_TIME_SAME_DATE_SUBDAY_nearlyFullDay() {
        TemporalPair source = new TemporalPair(
                LocalDateTime.of(2024, 6, 15, 0, 0),
                LocalDateTime.of(2024, 6, 15, 23, 59, 59, 999999999));
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_sameLocalTime() {
        TemporalPair source = new TemporalPair(
                LocalDateTime.of(2024, 6, 15, 12, 0),
                LocalDateTime.of(2024, 6, 16, 12, 0));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_RESIDUAL_twoDaysAndHour() {
        TemporalPair source = new TemporalPair(
                LocalDateTime.of(2024, 6, 15, 12, 0),
                LocalDateTime.of(2024, 6, 17, 13, 0));
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_reversePartialDay() {
        TemporalPair source = new TemporalPair(
                LocalDateTime.of(2024, 6, 16, 0, 0),
                LocalDateTime.of(2024, 6, 15, 1, 0));
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_MULTIDAY_reverseWithResidual() {
        TemporalPair source = new TemporalPair(
                LocalDateTime.of(2024, 6, 18, 13, 0),
                LocalDateTime.of(2024, 6, 16, 12, 0));
        assertMetamorphicRelationFor(source, -2);
    }

    @Test
    public void INSTANT_SUBDAY_twentyThreeHours() {
        TemporalPair source = new TemporalPair(
                Instant.parse("2024-06-15T00:00:00Z"),
                Instant.parse("2024-06-15T23:00:00Z"));
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void INSTANT_EXACT_MULTIDAY_threeWholeDays() {
        TemporalPair source = new TemporalPair(
                Instant.parse("2024-06-15T00:00:00Z"),
                Instant.parse("2024-06-18T00:00:00Z"));
        assertMetamorphicRelationFor(source, 3);
    }

    @Test
    public void OFFSET_DATE_TIME_CHANGED_OFFSET_SUBDAY_offsetNormalized() {
        TemporalPair source = new TemporalPair(
                OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2024, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(1)));
        assertMetamorphicRelationFor(source, 0);
    }

    @Test
    public void OFFSET_DATE_TIME_REVERSED_OFFSET_INTERVAL_reverseElapsedDays() {
        TemporalPair source = new TemporalPair(
                OffsetDateTime.of(2024, 1, 3, 0, 0, 0, 0, ZoneOffset.ofHours(1)),
                OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        assertMetamorphicRelationFor(source, -1);
    }

    @Test
    public void ZONED_DATE_TIME_DST_SPRING_FORWARD_newYorkTransition() {
        ZoneId newYork = ZoneId.of("America/New_York");
        TemporalPair source = new TemporalPair(
                ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, newYork),
                ZonedDateTime.of(2024, 3, 10, 12, 0, 0, 0, newYork));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void ZONED_DATE_TIME_DST_AUTUMN_BACK_newYorkTransition() {
        ZoneId newYork = ZoneId.of("America/New_York");
        TemporalPair source = new TemporalPair(
                ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, newYork),
                ZonedDateTime.of(2024, 11, 3, 12, 0, 0, 0, newYork));
        assertMetamorphicRelationFor(source, 1);
    }

    @Test
    public void MIXED_LOCAL_DATE_TO_LOCAL_DATE_TIME_dateToDateTime() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 6, 15),
                LocalDateTime.of(2024, 6, 17, 23, 59));
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    public void JAPANESE_DATE_ERA_TRANSITION_heiseiToReiwa() {
        TemporalPair source = new TemporalPair(
                JapaneseDate.from(LocalDate.of(2019, 4, 30)),
                JapaneseDate.from(LocalDate.of(2019, 5, 2)));
        assertMetamorphicRelationFor(source, 2);
    }

    @Test
    public void HIJRAH_DATE_MONTH_TRANSITION_twoDaysAcrossMonth() {
        HijrahDate start = HijrahDate.from(LocalDate.of(2024, 3, 10));
        HijrahDate end = start.plus(2, ChronoUnit.DAYS);
        assertMetamorphicRelationFor(new TemporalPair(start, end), 2);
    }

    @Test
    public void THAI_BUDDHIST_DATE_REVERSED_descendingDates() {
        TemporalPair source = new TemporalPair(
                ThaiBuddhistDate.from(LocalDate.of(2024, 6, 20)),
                ThaiBuddhistDate.from(LocalDate.of(2024, 6, 15)));
        assertMetamorphicRelationFor(source, -5);
    }
}
