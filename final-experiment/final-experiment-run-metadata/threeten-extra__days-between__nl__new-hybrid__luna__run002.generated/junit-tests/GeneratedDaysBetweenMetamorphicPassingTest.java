import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static final class TemporalPair {
        private final Temporal start;
        private final Temporal end;

        private TemporalPair(Temporal start, Temporal end) {
            this.start = start;
            this.end = end;
        }
    }

    private static TemporalPair generateFollowUp(TemporalPair source) {
        return new TemporalPair(
                source.start.plus(37, ChronoUnit.DAYS),
                source.end.plus(37, ChronoUnit.DAYS));
    }

    private static void assertMetamorphicRelation(
            Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(
                sourceOutput.getAmount(),
                followUpOutput.getAmount());
    }

    private static void assertMetamorphicRelationFor(TemporalPair source) {
        TemporalPair followUp = generateFollowUp(source);
        Days sourceOutput = Days.between(source.start, source.end);
        Days followUpOutput = Days.between(followUp.start, followUp.end);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertOverflowRelation(TemporalPair source) {
        TemporalPair followUp = generateFollowUp(source);
        ArithmeticException sourceFailure = Assertions.assertThrows(
                ArithmeticException.class,
                () -> Days.between(source.start, source.end));
        ArithmeticException followUpFailure = Assertions.assertThrows(
                ArithmeticException.class,
                () -> Days.between(followUp.start, followUp.end));
        Assertions.assertEquals(
                sourceFailure.getClass(),
                followUpFailure.getClass());
    }

    @Test
    public void LOCAL_DATE_ZERO_GAP_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 1, 15),
                LocalDate.of(2024, 1, 15));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_FORWARD_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 1, 15),
                LocalDate.of(2024, 1, 16));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_BACKWARD_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 1, 16),
                LocalDate.of(2024, 1, 15));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_POSITIVE_MULTI_DAY_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 3, 10),
                LocalDate.of(2024, 3, 17));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_NEGATIVE_MULTI_DAY_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 3, 17),
                LocalDate.of(2024, 3, 10));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_EXACT_SHIFT_DISTANCE_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 2, 7));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_MONTH_BOUNDARY_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2023, 1, 31),
                LocalDate.of(2023, 2, 2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_YEAR_BOUNDARY_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2023, 12, 30),
                LocalDate.of(2024, 1, 2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_BOUNDARY_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.of(2024, 2, 28),
                LocalDate.of(2024, 3, 2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_MINIMUM_REPRESENTABLE_SHIFT_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.MIN,
                LocalDate.MIN.plusDays(100));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_MAXIMUM_FOLLOWUP_BOUNDARY_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.MAX.minusDays(100),
                LocalDate.MAX.minusDays(37));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MAXIMUM_RESULT_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.MIN,
                LocalDate.MIN.plusDays(2_147_483_647L));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MINIMUM_RESULT_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.MIN.plusDays(2_147_483_648L),
                LocalDate.MIN);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_RESULT_INT_OVERFLOW_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDate.MIN,
                LocalDate.MIN.plusDays(2_147_483_648L));
        assertOverflowRelation(source);
    }

    @Test
    public void LOCAL_DATE_TIME_WHOLE_DAY_TRUNCATION_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 3, 11, 0));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_INTERVAL_variation1() {
        TemporalPair source = new TemporalPair(
                LocalDateTime.of(2024, 6, 1, 8, 30),
                LocalDateTime.of(2024, 6, 4, 8, 30));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FIXED_OFFSET_DATETIME_INTERVAL_variation1() {
        ZoneOffset offset = ZoneOffset.ofHours(2);
        TemporalPair source = new TemporalPair(
                OffsetDateTime.of(2024, 1, 1, 10, 0, 0, 0, offset),
                OffsetDateTime.of(2024, 1, 5, 10, 0, 0, 0, offset));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FIXED_OFFSET_DATETIME_NEGATIVE_INTERVAL_variation1() {
        ZoneOffset offset = ZoneOffset.ofHours(-5);
        TemporalPair source = new TemporalPair(
                OffsetDateTime.of(2024, 5, 10, 18, 0, 0, 0, offset),
                OffsetDateTime.of(2024, 5, 7, 18, 0, 0, 0, offset));
        assertMetamorphicRelationFor(source);
    }
}
