import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        if (sourceOutput.getAmount() != followUpOutput.getAmount()) {
            throw new AssertionError(
                    "The day count changed from " + sourceOutput.getAmount()
                            + " to " + followUpOutput.getAmount());
        }
    }

    @Test
    public void LOCAL_DATE_EQUAL_ENDPOINTS_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 15);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_FORWARD_variation1() {
        Temporal start = LocalDate.of(2024, 6, 15);
        Temporal end = LocalDate.of(2024, 6, 16);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_REVERSE_variation1() {
        Temporal start = LocalDate.of(2024, 6, 16);
        Temporal end = LocalDate.of(2024, 6, 15);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MONTH_END_variation1() {
        Temporal start = LocalDate.of(2024, 1, 31);
        Temporal end = LocalDate.of(2024, 2, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_LEAP_DAY_INCLUDED_variation1() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_NON_LEAP_FEBRUARY_variation1() {
        Temporal start = LocalDate.of(2023, 2, 28);
        Temporal end = LocalDate.of(2023, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_FULL_LEAP_YEAR_variation1() {
        Temporal start = LocalDate.of(2024, 1, 1);
        Temporal end = LocalDate.of(2025, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_REVERSE_ACROSS_LEAP_DAY_variation1() {
        Temporal start = LocalDate.of(2024, 3, 1);
        Temporal end = LocalDate.of(2024, 2, 28);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_PROLEPTIC_YEAR_BOUNDARY_variation1() {
        Temporal start = LocalDate.of(-1, 12, 31);
        Temporal end = LocalDate.of(0, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_NEAR_MAXIMUM_variation1() {
        Temporal start = LocalDate.MAX.minusDays(100);
        Temporal end = LocalDate.MAX.minusDays(37);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_NEAR_MINIMUM_variation1() {
        Temporal start = LocalDate.MIN;
        Temporal end = LocalDate.MIN.plusDays(63);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MAXIMUM_DISTANCE_variation1() {
        LocalDate base = LocalDate.of(0, 1, 1);
        Temporal start = base;
        Temporal end = base.plusDays(Integer.MAX_VALUE);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MINIMUM_DISTANCE_variation1() {
        LocalDate base = LocalDate.of(0, 1, 1);
        Temporal start = base.plusDays(2147483648L);
        Temporal end = base;
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_SHIFT_SIZED_INTERVAL_variation1() {
        LocalDate base = LocalDate.of(2024, 4, 10);
        Temporal start = base;
        Temporal end = base.plusDays(37);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EQUAL_ENDPOINTS_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 30);
        Temporal end = LocalDateTime.of(2024, 6, 15, 12, 30);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_SAME_DATE_PARTIAL_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 1, 0);
        Temporal end = LocalDateTime.of(2024, 6, 15, 23, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_CROSS_MIDNIGHT_PARTIAL_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 23, 0);
        Temporal end = LocalDateTime.of(2024, 6, 16, 1, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 12, 30);
        Temporal end = LocalDateTime.of(2024, 6, 16, 12, 30);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_TRUNCATION_variation1() {
        Temporal start = LocalDateTime.of(2024, 1, 1, 12, 0);
        Temporal end = LocalDateTime.of(2024, 1, 3, 11, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_TRUNCATION_variation1() {
        Temporal start = LocalDateTime.of(2024, 1, 3, 11, 0);
        Temporal end = LocalDateTime.of(2024, 1, 1, 12, 0);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_NANOSECOND_ALIGNED_DAY_variation1() {
        Temporal start = LocalDateTime.of(2024, 1, 1, 0, 0, 0, 500000000);
        Temporal end = LocalDateTime.of(2024, 1, 2, 0, 0, 0, 500000000);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_LEAP_BOUNDARY_variation1() {
        Temporal start = LocalDateTime.of(2024, 2, 28, 6, 45);
        Temporal end = LocalDateTime.of(2024, 3, 1, 6, 45);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_EQUAL_ENDPOINTS_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH;
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_JUST_UNDER_ONE_DAY_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plusSeconds(86399);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_EXACT_ONE_DAY_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plusSeconds(86400);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_POSITIVE_PARTIAL_SECOND_DAY_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plusSeconds(90000);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_NEGATIVE_PARTIAL_SECOND_DAY_variation1() {
        Temporal start = Instant.EPOCH.plusSeconds(90000);
        Temporal end = Instant.EPOCH;
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_INTEGER_MAXIMUM_DISTANCE_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = Instant.EPOCH.plus(Integer.MAX_VALUE, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_INTEGER_MINIMUM_DISTANCE_variation1() {
        Temporal start = Instant.EPOCH.plus(2147483648L, ChronoUnit.DAYS);
        Temporal end = Instant.EPOCH;
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_EQUAL_ENDPOINTS_variation1() {
        Temporal start = OffsetDateTime.of(
                2024, 6, 15, 12, 0, 0, 0, ZoneOffset.UTC);
        Temporal end = OffsetDateTime.of(
                2024, 6, 15, 12, 0, 0, 0, ZoneOffset.UTC);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_SAME_OFFSET_DAY_variation1() {
        ZoneOffset offset = ZoneOffset.ofHours(-5);
        Temporal start = OffsetDateTime.of(2024, 6, 15, 12, 0, 0, 0, offset);
        Temporal end = OffsetDateTime.of(2024, 6, 16, 12, 0, 0, 0, offset);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_MAXIMUM_POSITIVE_OFFSET_variation1() {
        ZoneOffset offset = ZoneOffset.ofHours(18);
        Temporal start = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, offset);
        Temporal end = OffsetDateTime.of(2024, 1, 2, 0, 0, 0, 0, offset);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERING_OFFSETS_FORWARD_variation1() {
        Temporal start = OffsetDateTime.of(
                2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(14));
        Temporal end = OffsetDateTime.of(
                2024, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(-10));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERING_OFFSETS_REVERSE_variation1() {
        Temporal start = OffsetDateTime.of(
                2024, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(-10));
        Temporal end = OffsetDateTime.of(
                2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(14));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_UTC_DAY_variation1() {
        Temporal start = ZonedDateTime.of(
                2024, 6, 15, 12, 0, 0, 0, ZoneOffset.UTC);
        Temporal end = ZonedDateTime.of(
                2024, 6, 16, 12, 0, 0, 0, ZoneOffset.UTC);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_SPRING_DST_SPAN_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 3, 9, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 3, 11, 12, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_FALL_DST_SPAN_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 11, 2, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 11, 4, 12, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_OVERLAP_SAME_LOCAL_TIME_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        LocalDateTime overlap = LocalDateTime.of(2024, 11, 3, 1, 30);
        Temporal start = ZonedDateTime.ofLocal(overlap, zone, ZoneOffset.ofHours(-4));
        Temporal end = ZonedDateTime.ofLocal(overlap, zone, ZoneOffset.ofHours(-5));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_AFTER_SPRING_GAP_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2024, 3, 10, 1, 30, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 3, 11, 1, 30, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_FIXED_CROSS_ZONE_variation1() {
        Temporal start = ZonedDateTime.of(
                2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        Temporal end = ZonedDateTime.of(
                2024, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(2));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_NON_DST_YEAR_BOUNDARY_variation1() {
        ZoneId zone = ZoneId.of("Asia/Tokyo");
        Temporal start = ZonedDateTime.of(2024, 12, 31, 8, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2025, 1, 1, 8, 0, 0, 0, zone);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JAPANESE_DATE_LEAP_BOUNDARY_variation1() {
        Temporal start = JapaneseDate.from(LocalDate.of(2024, 2, 28));
        Temporal end = JapaneseDate.from(LocalDate.of(2024, 3, 1));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HIJRAH_DATE_CONSECUTIVE_DAYS_variation1() {
        HijrahDate base = HijrahDate.of(1445, 9, 1);
        Temporal start = base;
        Temporal end = base.plus(1, ChronoUnit.DAYS);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THAI_BUDDHIST_DATE_LEAP_BOUNDARY_variation1() {
        Temporal start = ThaiBuddhistDate.of(2567, 2, 28);
        Temporal end = ThaiBuddhistDate.of(2567, 3, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MINGUO_DATE_YEAR_BOUNDARY_variation1() {
        Temporal start = MinguoDate.of(113, 12, 31);
        Temporal end = MinguoDate.of(114, 1, 1);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_LOCAL_DATE_TO_LOCAL_DATE_TIME_variation1() {
        Temporal start = LocalDate.of(2024, 1, 1);
        Temporal end = LocalDateTime.of(2024, 1, 3, 23, 59);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_INSTANT_TO_OFFSET_DATE_TIME_variation1() {
        Temporal start = Instant.EPOCH;
        Temporal end = OffsetDateTime.of(
                1970, 1, 2, 12, 0, 0, 0, ZoneOffset.UTC);
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MIXED_ISO_TO_JAPANESE_DATE_variation1() {
        Temporal start = LocalDate.of(2024, 1, 1);
        Temporal end = JapaneseDate.from(LocalDate.of(2024, 1, 3));
        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
