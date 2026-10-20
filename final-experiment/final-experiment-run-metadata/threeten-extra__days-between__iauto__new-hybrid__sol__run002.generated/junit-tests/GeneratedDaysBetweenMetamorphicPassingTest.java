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

import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        if (sourceOutput.getAmount() != followUpOutput.getAmount()) {
            throw new AssertionError(
                    "The day count changed after applying the follow-up transformation: "
                            + sourceOutput.getAmount() + " != " + followUpOutput.getAmount());
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
        Temporal start = LocalDate.of(2024, 5, 17);
        Temporal end = LocalDate.of(2024, 5, 18);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_ONE_DAY_BACKWARD_variation1() {
        Temporal start = LocalDate.of(2025, 1, 1);
        Temporal end = LocalDate.of(2024, 12, 31);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_GENERAL_POSITIVE_GAP_variation1() {
        Temporal start = LocalDate.of(2022, 7, 4);
        Temporal end = LocalDate.of(2022, 7, 4).plusDays(36);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_GENERAL_NEGATIVE_GAP_variation1() {
        Temporal start = LocalDate.of(2022, 9, 20);
        Temporal end = LocalDate.of(2022, 9, 20).minusDays(36);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MONTH_END_variation1() {
        Temporal start = LocalDate.of(2023, 1, 31);
        Temporal end = LocalDate.of(2023, 2, 1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_YEAR_END_variation1() {
        Temporal start = LocalDate.of(2023, 12, 31);
        Temporal end = LocalDate.of(2024, 1, 1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_LEAP_FEBRUARY_SPAN_variation1() {
        Temporal start = LocalDate.of(2024, 2, 28);
        Temporal end = LocalDate.of(2024, 3, 1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_COMMON_FEBRUARY_SPAN_variation1() {
        Temporal start = LocalDate.of(2023, 2, 28);
        Temporal end = LocalDate.of(2023, 3, 1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_FROM_LEAP_DAY_variation1() {
        Temporal start = LocalDate.of(2024, 2, 29);
        Temporal end = LocalDate.of(2024, 3, 1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_PROLEPTIC_ERA_BOUNDARY_variation1() {
        Temporal start = LocalDate.of(0, 12, 31);
        Temporal end = LocalDate.of(1, 1, 1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MAX_GAP_variation1() {
        LocalDate startDate = LocalDate.of(0, 1, 1);
        Temporal start = startDate;
        Temporal end = startDate.plusDays(Integer.MAX_VALUE);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_INTEGER_MIN_GAP_variation1() {
        LocalDate startDate = LocalDate.of(0, 1, 1);
        Temporal start = startDate;
        Temporal end = startDate.plusDays(Integer.MIN_VALUE);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_NEAR_MAXIMUM_SHIFT_LIMIT_variation1() {
        Temporal start = LocalDate.MAX.minusDays(38);
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
        Temporal end = LocalDate.MIN.plusDays(1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_MULTI_YEAR_FORWARD_variation1() {
        Temporal start = LocalDate.of(2018, 1, 1);
        Temporal end = LocalDate.of(2022, 1, 1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_REVERSE_LEAP_SPAN_variation1() {
        Temporal start = LocalDate.of(2024, 3, 1);
        Temporal end = LocalDate.of(2024, 2, 28);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_SHIFT_CROSSES_LEAP_REGION_variation1() {
        Temporal start = LocalDate.of(2019, 12, 31);
        Temporal end = LocalDate.of(2020, 3, 1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EQUAL_variation1() {
        Temporal start = LocalDateTime.of(2024, 6, 15, 13, 45, 20, 123456789);
        Temporal end = LocalDateTime.of(2024, 6, 15, 13, 45, 20, 123456789);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_POSITIVE_SUBDAY_variation1() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 1, 15, 8, 30);
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusHours(23)
                .plusMinutes(59)
                .plusSeconds(59)
                .plusNanos(999999999);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_DAY_variation1() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 4, 10, 6, 20, 15);
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusDays(1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_ONE_NANO_BELOW_DAY_variation1() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 2, 28, 23, 0);
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusDays(1).minusNanos(1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_NEGATIVE_SUBDAY_variation1() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 20, 12, 0);
        Temporal start = startDateTime;
        Temporal end = startDateTime.minusHours(23);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_EXACT_NEGATIVE_DAY_variation1() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 11, 5, 17, 10);
        Temporal start = startDateTime;
        Temporal end = startDateTime.minusDays(1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_SHORT_MIDNIGHT_CROSSING_variation1() {
        Temporal start = LocalDateTime.of(2024, 1, 31, 23, 30);
        Temporal end = LocalDateTime.of(2024, 2, 1, 0, 30);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LOCAL_DATE_TIME_MULTIDAY_REMAINDER_variation1() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 3, 10, 9, 15);
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusDays(2).plusHours(23);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_EQUAL_variation1() {
        Temporal start = Instant.parse("2020-06-15T12:34:56Z");
        Temporal end = Instant.parse("2020-06-15T12:34:56Z");

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_POSITIVE_SUBDAY_variation1() {
        Instant startInstant = Instant.parse("2021-01-01T00:00:00Z");
        Temporal start = startInstant;
        Temporal end = startInstant.plusSeconds(86399);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_EXACT_DAY_variation1() {
        Instant startInstant = Instant.parse("2022-03-04T05:06:07Z");
        Temporal start = startInstant;
        Temporal end = startInstant.plus(1, ChronoUnit.DAYS);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_NEGATIVE_SUBDAY_variation1() {
        Instant startInstant = Instant.parse("2023-08-20T20:00:00Z");
        Temporal start = startInstant;
        Temporal end = startInstant.minusSeconds(86399);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INSTANT_NEGATIVE_MULTIDAY_REMAINDER_variation1() {
        Instant startInstant = Instant.parse("2024-01-10T00:00:00Z");
        Temporal start = startInstant;
        Temporal end = startInstant.minusSeconds(2L * 86400L + 1L);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_EQUAL_variation1() {
        Temporal start = OffsetDateTime.of(
                2024, 7, 8, 9, 10, 11, 0, ZoneOffset.ofHoursMinutes(5, 30));
        Temporal end = OffsetDateTime.of(
                2024, 7, 8, 9, 10, 11, 0, ZoneOffset.ofHoursMinutes(5, 30));

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_SAME_OFFSET_DAY_variation1() {
        OffsetDateTime startDateTime = OffsetDateTime.of(
                2024, 9, 30, 14, 25, 0, 0, ZoneOffset.ofHours(-4));
        Temporal start = startDateTime;
        Temporal end = startDateTime.plusDays(1);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_FORWARD_SUBDAY_variation1() {
        Temporal start = OffsetDateTime.of(
                2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        Temporal end = OffsetDateTime.of(
                2020, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(2));

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFFSET_DATE_TIME_DIFFERENT_OFFSETS_BACKWARD_SUBDAY_variation1() {
        Temporal start = OffsetDateTime.of(
                2020, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(2));
        Temporal end = OffsetDateTime.of(
                2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_EQUAL_variation1() {
        ZoneId zone = ZoneId.of("Europe/Paris");
        Temporal start = ZonedDateTime.of(2024, 5, 20, 16, 40, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2024, 5, 20, 16, 40, 0, 0, zone);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_SPRING_GAP_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2020, 3, 7, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2020, 3, 8, 12, 0, 0, 0, zone);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_AUTUMN_OVERLAP_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2020, 10, 31, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2020, 11, 1, 12, 0, 0, 0, zone);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZONED_DATE_TIME_MULTIDAY_TRANSITION_variation1() {
        ZoneId zone = ZoneId.of("America/New_York");
        Temporal start = ZonedDateTime.of(2020, 3, 7, 12, 0, 0, 0, zone);
        Temporal end = ZonedDateTime.of(2020, 3, 10, 12, 0, 0, 0, zone);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void JAPANESE_DATE_FORWARD_variation1() {
        JapaneseDate startDate = JapaneseDate.from(LocalDate.of(2020, 6, 15));
        Temporal start = startDate;
        Temporal end = startDate.plus(10, ChronoUnit.DAYS);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HIJRAH_DATE_BACKWARD_variation1() {
        HijrahDate startDate = HijrahDate.from(LocalDate.of(2020, 6, 15));
        Temporal start = startDate;
        Temporal end = startDate.minus(10, ChronoUnit.DAYS);

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MINGUO_DATE_EQUAL_variation1() {
        Temporal start = MinguoDate.from(LocalDate.of(2021, 8, 9));
        Temporal end = MinguoDate.from(LocalDate.of(2021, 8, 9));

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THAI_BUDDHIST_DATE_MONTH_BOUNDARY_variation1() {
        Temporal start = ThaiBuddhistDate.from(LocalDate.of(2023, 1, 31));
        Temporal end = ThaiBuddhistDate.from(LocalDate.of(2023, 2, 1));

        Days sourceOutput = org.threeten.extra.Days.between(start, end);
        Object[] followUp = DaysBetweenMetamorphicSpec.generateFollowUp(start, end);
        Days followUpOutput = org.threeten.extra.Days.between(
                (Temporal) followUp[0], (Temporal) followUp[1]);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
