import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static final class Input {
        private final Temporal start;
        private final Temporal end;

        private Input(Temporal start, Temporal end) {
            this.start = start;
            this.end = end;
        }
    }

    private static Input generateFollowUp(Input source) {
        return new Input(
                source.start.plus(37, ChronoUnit.DAYS),
                source.end.plus(37, ChronoUnit.DAYS));
    }

    private static void assertMetamorphicRelationFor(Input source) {
        org.threeten.extra.Days sourceOutput =
                org.threeten.extra.Days.between(source.start, source.end);
        Input followUp = generateFollowUp(source);
        org.threeten.extra.Days followUpOutput =
                org.threeten.extra.Days.between(followUp.start, followUp.end);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            org.threeten.extra.Days sourceOutput,
            org.threeten.extra.Days followUpOutput) {
        Assertions.assertEquals(sourceOutput.getAmount(), followUpOutput.getAmount());
    }

    @Test
    public void LOCAL_DATE_ZERO_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = LocalDate.of(2024, 6, 15);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void LOCAL_DATE_POSITIVE_ONE_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDate end = LocalDate.of(2024, 6, 16);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void LOCAL_DATE_NEGATIVE_ONE_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 16);
        LocalDate end = LocalDate.of(2024, 6, 15);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void LOCAL_DATE_POSITIVE_MULTIPLE_variation1() {
        LocalDate start = LocalDate.of(2023, 1, 10);
        LocalDate end = LocalDate.of(2024, 2, 20);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void LOCAL_DATE_NEGATIVE_MULTIPLE_variation1() {
        LocalDate start = LocalDate.of(2024, 2, 20);
        LocalDate end = LocalDate.of(2023, 1, 10);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_CROSSING_variation1() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDate end = LocalDate.of(2024, 3, 1);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void LOCAL_DATE_NEAR_MAXIMUM_variation1() {
        LocalDate start = LocalDate.of(999999998, 12, 1);
        LocalDate end = LocalDate.of(999999998, 12, 10);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void LOCAL_DATE_NEAR_MINIMUM_variation1() {
        LocalDate start = LocalDate.of(-999999998, 1, 10);
        LocalDate end = LocalDate.of(-999999998, 1, 20);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void LOCAL_DATETIME_EXACT_WHOLE_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 8, 30);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 8, 30);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void LOCAL_DATETIME_POSITIVE_SUBDAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 8, 30);
        LocalDateTime end = LocalDateTime.of(2024, 6, 15, 20, 29, 59);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void LOCAL_DATETIME_NEGATIVE_SUBDAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 20, 29, 59);
        LocalDateTime end = LocalDateTime.of(2024, 6, 15, 8, 30);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void local_datetime_negative_exact_day_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 16, 8, 30);
        LocalDateTime end = LocalDateTime.of(2024, 6, 15, 8, 30);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void INSTANT_POSITIVE_SUBDAY_variation1() {
        Instant start = Instant.parse("2024-06-15T00:00:00Z");
        Instant end = Instant.parse("2024-06-15T23:59:59Z");
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void INSTANT_LARGE_POSITIVE_variation1() {
        Instant start = Instant.EPOCH;
        Instant end = start.plus(2_147_483_647L, ChronoUnit.DAYS);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void INSTANT_LARGE_NEGATIVE_variation1() {
        Instant start = Instant.EPOCH.plus(2_147_483_648L, ChronoUnit.DAYS);
        Instant end = Instant.EPOCH;
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void OFFSET_DATETIME_EQUAL_OFFSET_variation1() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 6, 15, 10, 0, 0, 0, ZoneOffset.ofHours(2));
        OffsetDateTime end = OffsetDateTime.of(
                2024, 6, 18, 10, 0, 0, 0, ZoneOffset.ofHours(2));
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void OFFSET_DATETIME_DIFFERING_OFFSETS_variation1() {
        OffsetDateTime start = OffsetDateTime.of(
                2024, 6, 15, 12, 0, 0, 0, ZoneOffset.ofHours(2));
        OffsetDateTime end = OffsetDateTime.of(
                2024, 6, 17, 12, 0, 0, 0, ZoneOffset.UTC);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void ZONED_DATETIME_UTC_variation1() {
        ZonedDateTime start = ZonedDateTime.of(
                2024, 6, 15, 10, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime end = ZonedDateTime.of(
                2024, 6, 20, 10, 0, 0, 0, ZoneId.of("UTC"));
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void ZONED_DATETIME_FIXED_OFFSET_variation1() {
        ZoneId zone = ZoneOffset.ofHours(5);
        ZonedDateTime start = ZonedDateTime.of(
                2024, 6, 15, 10, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(
                2024, 6, 16, 10, 0, 0, 0, zone);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void ZONED_DATETIME_NEGATIVE_variation1() {
        ZonedDateTime start = ZonedDateTime.of(
                2024, 6, 20, 10, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime end = ZonedDateTime.of(
                2024, 6, 15, 10, 0, 0, 0, ZoneId.of("UTC"));
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void JAPANESE_DATE_POSITIVE_variation1() {
        JapaneseDate start = JapaneseDate.of(2024, 6, 15);
        JapaneseDate end = JapaneseDate.of(2024, 6, 18);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void JAPANESE_DATE_NEGATIVE_variation1() {
        JapaneseDate start = JapaneseDate.of(2024, 6, 18);
        JapaneseDate end = JapaneseDate.of(2024, 6, 15);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void THAI_BUDDHIST_DATE_ZERO_variation1() {
        ThaiBuddhistDate start = ThaiBuddhistDate.of(2567, 6, 15);
        ThaiBuddhistDate end = ThaiBuddhistDate.of(2567, 6, 15);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void THAI_BUDDHIST_DATE_LEAP_CROSSING_variation1() {
        ThaiBuddhistDate start = ThaiBuddhistDate.of(2567, 2, 28);
        ThaiBuddhistDate end = ThaiBuddhistDate.of(2567, 3, 1);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void MIXED_SCALE_POSITIVE_MULTI_DAY_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 6, 0);
        LocalDateTime end = LocalDateTime.of(2024, 7, 22, 6, 0);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void SAME_SHIFT_AT_MONTH_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 31);
        LocalDate end = LocalDate.of(2024, 2, 29);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void ZERO_AFTER_DATE_TIME_TRUNCATION_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 23, 30);
        LocalDateTime end = LocalDateTime.of(2024, 6, 16, 0, 30);
        assertMetamorphicRelationFor(new Input(start, end));
    }

    @Test
    public void POSITIVE_TWO_DAY_DATE_TIME_variation1() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 15, 23, 30);
        LocalDateTime end = LocalDateTime.of(2024, 6, 17, 23, 30);
        assertMetamorphicRelationFor(new Input(start, end));
    }
}
