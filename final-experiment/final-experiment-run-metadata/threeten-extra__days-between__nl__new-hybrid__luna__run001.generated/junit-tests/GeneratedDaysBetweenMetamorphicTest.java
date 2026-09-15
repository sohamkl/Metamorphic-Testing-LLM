import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicTest {

    private static final long FOLLOW_UP_SHIFT_DAYS = 37L;

    private static final class Input {
        private final Temporal start;
        private final Temporal end;

        private Input(Temporal start, Temporal end) {
            this.start = start;
            this.end = end;
        }
    }

    private static Input source(Temporal start, Temporal end) {
        return new Input(start, end);
    }

    private static Input generateFollowUp(Input input) {
        return new Input(
                input.start.plus(FOLLOW_UP_SHIFT_DAYS, ChronoUnit.DAYS),
                input.end.plus(FOLLOW_UP_SHIFT_DAYS, ChronoUnit.DAYS));
    }

    private static void assertMetamorphicRelation(
            Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(
                sourceOutput.getAmount(), followUpOutput.getAmount());
    }

    private static void assertMetamorphicRelationFor(
            Input input, int expectedAmount) {
        Days sourceOutput = Days.between(input.start, input.end);
        Input followUp = generateFollowUp(input);
        Days followUpOutput = Days.between(followUp.start, followUp.end);

        Assertions.assertEquals(expectedAmount, sourceOutput.getAmount());
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZERO_DAY_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2024, 6, 15),
                        LocalDate.of(2024, 6, 15)),
                0);
    }

    @Test
    public void testONE_DAY_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2024, 6, 15),
                        LocalDate.of(2024, 6, 16)),
                1);
    }

    @Test
    public void testNEGATIVE_ONE_DAY_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2024, 6, 16),
                        LocalDate.of(2024, 6, 15)),
                -1);
    }

    @Test
    public void testSMALL_POSITIVE_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2024, 1, 10),
                        LocalDate.of(2024, 1, 17)),
                7);
    }

    @Test
    public void testSMALL_NEGATIVE_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2024, 1, 17),
                        LocalDate.of(2024, 1, 10)),
                -7);
    }

    @Test
    public void testMONTH_BOUNDARY_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2024, 1, 31),
                        LocalDate.of(2024, 2, 1)),
                1);
    }

    @Test
    public void testYEAR_BOUNDARY_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2023, 12, 31),
                        LocalDate.of(2024, 1, 2)),
                2);
    }

    @Test
    public void testLEAP_DAY_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2024, 2, 28),
                        LocalDate.of(2024, 3, 1)),
                2);
    }

    @Test
    public void testNON_LEAP_FEBRUARY_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2023, 2, 28),
                        LocalDate.of(2023, 3, 1)),
                1);
    }

    @Test
    public void testLOCAL_DATETIME_SAME_DAY_SUBDAY_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDateTime.of(2024, 6, 15, 8, 0),
                        LocalDateTime.of(2024, 6, 15, 23, 59, 59)),
                0);
    }

    @Test
    public void testLOCAL_DATETIME_TWENTY_FIVE_HOURS_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDateTime.of(2024, 6, 15, 23, 0),
                        LocalDateTime.of(2024, 6, 17, 0, 0)),
                1);
    }

    @Test
    public void testLOCAL_DATETIME_REVERSE_SUBDAY_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDateTime.of(2024, 6, 16, 8, 0),
                        LocalDateTime.of(2024, 6, 15, 23, 59, 59)),
                0);
    }

    @Test
    public void testINSTANT_EXACT_MULTIPLE_DAYS_1() {
        assertMetamorphicRelationFor(
                source(
                        Instant.parse("2024-01-01T00:00:00Z"),
                        Instant.parse("2024-01-04T00:00:00Z")),
                3);
    }

    @Test
    public void testINSTANT_SUBDAY_TRUNCATION_1() {
        assertMetamorphicRelationFor(
                source(
                        Instant.parse("2024-01-01T00:00:00Z"),
                        Instant.parse("2024-01-02T12:00:00Z")),
                1);
    }

    @Test
    public void testOFFSET_DATETIME_FIXED_OFFSET_1() {
        assertMetamorphicRelationFor(
                source(
                        OffsetDateTime.parse("2024-03-01T10:15:00+02:00"),
                        OffsetDateTime.parse("2024-03-06T10:15:00+02:00")),
                5);
    }

    @Test
    public void testZONED_DATETIME_SPRING_TRANSITION_1() {
        ZoneId zone = ZoneId.of("America/New_York");
        assertMetamorphicRelationFor(
                source(
                        ZonedDateTime.of(
                                LocalDateTime.of(2024, 3, 9, 12, 0), zone),
                        ZonedDateTime.of(
                                LocalDateTime.of(2024, 3, 11, 12, 0), zone)),
                2);
    }

    @Test
    public void testZONED_DATETIME_AUTUMN_TRANSITION_1() {
        ZoneId zone = ZoneId.of("America/New_York");
        assertMetamorphicRelationFor(
                source(
                        ZonedDateTime.of(
                                LocalDateTime.of(2024, 11, 2, 12, 0), zone),
                        ZonedDateTime.of(
                                LocalDateTime.of(2024, 11, 4, 12, 0), zone)),
                2);
    }

    @Test
    public void testJAPANESE_DATE_CHRONOLOGY_1() {
        assertMetamorphicRelationFor(
                source(
                        JapaneseDate.of(2024, 1, 1),
                        JapaneseDate.of(2024, 1, 11)),
                10);
    }

    @Test
    public void testLOCAL_DATE_MINIMUM_RANGE_INTERIOR_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(-999999999, 1, 1),
                        LocalDate.of(-999999999, 1, 11)),
                10);
    }

    @Test
    public void testLOCAL_DATE_MAXIMUM_RANGE_INTERIOR_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(999999999, 11, 20),
                        LocalDate.of(999999999, 11, 30)),
                10);
    }

    @Test
    public void testINTEGER_MAXIMUM_RESULT_1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDate end = start.plusDays(Integer.MAX_VALUE);

        assertMetamorphicRelationFor(source(start, end), Integer.MAX_VALUE);
    }

    @Test
    public void testINTEGER_MINIMUM_RESULT_1() {
        LocalDate end = LocalDate.of(1970, 1, 1);
        LocalDate start = end.plusDays(2147483648L);

        assertMetamorphicRelationFor(source(start, end), Integer.MIN_VALUE);
    }

    @Test
    public void testMULTI_YEAR_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2000, 1, 1),
                        LocalDate.of(2025, 1, 1)),
                9132);
    }

    @Test
    public void testNEGATIVE_MULTI_YEAR_LOCAL_DATE_1() {
        assertMetamorphicRelationFor(
                source(
                        LocalDate.of(2025, 1, 1),
                        LocalDate.of(2000, 1, 1)),
                -9132);
    }
}
