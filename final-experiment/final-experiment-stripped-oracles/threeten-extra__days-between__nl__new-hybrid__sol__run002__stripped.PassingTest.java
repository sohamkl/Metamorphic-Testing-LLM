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
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static MtllmGeneratedDaysBetweenInvocation1uknttg.Input input(Temporal start, Temporal end) {
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(start, end);
    }

    private static MtllmGeneratedDaysBetweenInvocation1uknttg.Input generateFollowUp(MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {
        Temporal shiftedStart = source.arg0().plus(37, ChronoUnit.DAYS);
        Temporal shiftedEnd = source.arg1().plus(37, ChronoUnit.DAYS);
        return input(shiftedStart, shiftedEnd);
    }

    private static void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        Assertions.assertEquals(sourceOutput.getAmount(), followUpOutput.getAmount());
    }

    private static void assertMetamorphicRelationFor(MtllmGeneratedDaysBetweenInvocation1uknttg.Input source, int expectedSourceAmount) {
        Days sourceOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(source);
        MtllmGeneratedDaysBetweenInvocation1uknttg.Input followUp = generateFollowUp(source);
        Days followUpOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_LENGTH_v1_localDate() {
        LocalDate value = LocalDate.of(2024, 5, 17);
        assertMetamorphicRelationFor(input(value, value), 0);
    }

    @Test
    public void ZERO_LENGTH_v2_localDateTime() {
        LocalDateTime value = LocalDateTime.of(2023, 12, 31, 23, 45, 12, 345);
        assertMetamorphicRelationFor(input(value, value), 0);
    }

    @Test
    public void ZERO_LENGTH_v3_instant() {
        Instant value = Instant.parse("2020-02-29T12:34:56.123456789Z");
        assertMetamorphicRelationFor(input(value, value), 0);
    }

    @Test
    public void EXACTLY_ONE_DAY_FORWARD_v1_offsetDateTime() {
        OffsetDateTime start = OffsetDateTime.of(2023, 12, 31, 9, 30, 0, 0, ZoneOffset.ofHours(5));
        assertMetamorphicRelationFor(input(start, start.plusDays(1)), 1);
    }

    @Test
    public void EXACTLY_ONE_DAY_FORWARD_v2_fixedOffsetZonedDateTime() {
        ZonedDateTime start = ZonedDateTime.of(2024, 2, 28, 16, 20, 0, 0, ZoneId.of("+03:00"));
        assertMetamorphicRelationFor(input(start, start.plusDays(1)), 1);
    }

    @Test
    public void EXACTLY_ONE_DAY_REVERSE_v1_variableOffsetZonedDateTime() {
        ZonedDateTime start = ZonedDateTime.of(2024, 7, 15, 11, 0, 0, 0, ZoneId.of("America/New_York"));
        assertMetamorphicRelationFor(input(start, start.minusDays(1)), -1);
    }

    @Test
    public void EXACTLY_ONE_DAY_REVERSE_v2_nonIsoDate() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2024, 6, 10));
        assertMetamorphicRelationFor(input(start, start.minus(1, ChronoUnit.DAYS)), -1);
    }

    @Test
    public void SMALL_POSITIVE_GAPS_v1_mixedDateAndDateTimeTwoDays() {
        LocalDate start = LocalDate.of(2024, 1, 10);
        LocalDateTime end = LocalDateTime.of(2024, 1, 12, 22, 15);
        assertMetamorphicRelationFor(input(start, end), 2);
    }

    @Test
    public void SMALL_POSITIVE_GAPS_v2_localDateSixDays() {
        LocalDate start = LocalDate.of(2023, 3, 27);
        assertMetamorphicRelationFor(input(start, start.plusDays(6)), 6);
    }

    @Test
    public void SMALL_POSITIVE_GAPS_v3_localDateTimeThirtySixDays() {
        LocalDateTime start = LocalDateTime.of(2023, 11, 25, 8, 30);
        assertMetamorphicRelationFor(input(start, start.plusDays(36)), 36);
    }

    @Test
    public void SMALL_NEGATIVE_GAPS_v1_instantMinusTwoDays() {
        Instant start = Instant.parse("2024-03-10T05:30:00Z");
        assertMetamorphicRelationFor(input(start, start.minus(2, ChronoUnit.DAYS)), -2);
    }

    @Test
    public void SMALL_NEGATIVE_GAPS_v2_offsetDateTimeMinusSixDays() {
        OffsetDateTime start = OffsetDateTime.of(2025, 1, 5, 7, 45, 0, 0, ZoneOffset.ofHoursMinutes(-3, -30));
        assertMetamorphicRelationFor(input(start, start.minusDays(6)), -6);
    }

    @Test
    public void SMALL_NEGATIVE_GAPS_v3_fixedOffsetZonedMinusThirtySixDays() {
        ZonedDateTime start = ZonedDateTime.of(2024, 8, 20, 14, 0, 0, 0, ZoneId.of("-07:00"));
        assertMetamorphicRelationFor(input(start, start.minusDays(36)), -36);
    }

    @Test
    public void EXACT_TRANSFORMATION_DISTANCE_v1_variableZoneForward() {
        ZonedDateTime start = ZonedDateTime.of(2024, 7, 1, 10, 15, 0, 0, ZoneId.of("America/New_York"));
        assertMetamorphicRelationFor(input(start, start.plusDays(37)), 37);
    }

    @Test
    public void EXACT_TRANSFORMATION_DISTANCE_v2_nonIsoReverse() {
        ThaiBuddhistDate start = ThaiBuddhistDate.from(LocalDate.of(2024, 9, 20));
        assertMetamorphicRelationFor(input(start, start.minus(37, ChronoUnit.DAYS)), -37);
    }

    @Test
    public void MONTH_BOUNDARY_CROSSINGS_v1_nonLeapFebruary() {
        LocalDate start = LocalDate.of(2023, 2, 27);
        LocalDate end = LocalDate.of(2023, 3, 2);
        assertMetamorphicRelationFor(input(start, end), 3);
    }

    @Test
    public void MONTH_BOUNDARY_CROSSINGS_v2_thirtyDayMonth() {
        LocalDate start = LocalDate.of(2024, 4, 29);
        LocalDate end = LocalDate.of(2024, 5, 2);
        assertMetamorphicRelationFor(input(start, end), 3);
    }

    @Test
    public void MONTH_BOUNDARY_CROSSINGS_v3_thirtyOneDayMonth() {
        LocalDate start = LocalDate.of(2024, 1, 30);
        LocalDate end = LocalDate.of(2024, 2, 2);
        assertMetamorphicRelationFor(input(start, end), 3);
    }

    @Test
    public void YEAR_BOUNDARY_CROSSINGS_v1_forward() {
        LocalDate start = LocalDate.of(2023, 12, 29);
        LocalDate end = LocalDate.of(2024, 1, 2);
        assertMetamorphicRelationFor(input(start, end), 4);
    }

    @Test
    public void YEAR_BOUNDARY_CROSSINGS_v2_reverse() {
        LocalDate start = LocalDate.of(2025, 1, 3);
        LocalDate end = LocalDate.of(2024, 12, 30);
        assertMetamorphicRelationFor(input(start, end), -4);
    }

    @Test
    public void LEAP_DAY_TOPOLOGY_v1_february28To29() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDate end = LocalDate.of(2024, 2, 29);
        assertMetamorphicRelationFor(input(start, end), 1);
    }

    @Test
    public void LEAP_DAY_TOPOLOGY_v2_february29ToMarch1() {
        LocalDate start = LocalDate.of(2024, 2, 29);
        LocalDate end = LocalDate.of(2024, 3, 1);
        assertMetamorphicRelationFor(input(start, end), 1);
    }

    @Test
    public void LEAP_DAY_TOPOLOGY_v3_february28ToMarch1() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDate end = LocalDate.of(2024, 3, 1);
        assertMetamorphicRelationFor(input(start, end), 2);
    }

    @Test
    public void LEAP_DAY_TOPOLOGY_v4_reverseAcrossLeapDay() {
        LocalDate start = LocalDate.of(2024, 3, 1);
        LocalDate end = LocalDate.of(2024, 2, 28);
        assertMetamorphicRelationFor(input(start, end), -2);
    }

    @Test
    public void LONG_MULTIYEAR_GAPS_v1_forward() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = LocalDate.of(2005, 1, 1);
        assertMetamorphicRelationFor(input(start, end), 1827);
    }

    @Test
    public void LONG_MULTIYEAR_GAPS_v2_reverse() {
        LocalDate start = LocalDate.of(2010, 1, 1);
        LocalDate end = LocalDate.of(2004, 1, 1);
        assertMetamorphicRelationFor(input(start, end), -2192);
    }

    @Test
    public void INTEGER_MAXIMUM_RESULT_v1_instantBoundary() {
        Instant start = Instant.parse("2000-01-01T00:00:00Z");
        Instant end = start.plus(Integer.MAX_VALUE, ChronoUnit.DAYS);
        assertMetamorphicRelationFor(input(start, end), Integer.MAX_VALUE);
    }

    @Test
    public void INTEGER_MINIMUM_RESULT_v1_offsetDateTimeBoundary() {
        OffsetDateTime start = OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(2));
        OffsetDateTime end = start.minusDays(2147483648L);
        assertMetamorphicRelationFor(input(start, end), Integer.MIN_VALUE);
    }

    @Test
    public void LOCALDATE_REPRESENTATION_BOUNDARIES_v1_minimum() {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN.plusDays(2);
        assertMetamorphicRelationFor(input(start, end), 2);
    }

    @Test
    public void LOCALDATE_REPRESENTATION_BOUNDARIES_v2_maximumShiftRoom() {
        LocalDate start = LocalDate.MAX.minusDays(40);
        LocalDate end = LocalDate.MAX.minusDays(37);
        assertMetamorphicRelationFor(input(start, end), 3);
    }

    @Test
    public void LOCALDATETIME_SUBDAY_TRUNCATION_v1_forwardTwentyThreeHoursFiftyNineMinutes() {
        LocalDateTime start = LocalDateTime.of(2024, 5, 10, 12, 0);
        LocalDateTime end = start.plusHours(23).plusMinutes(59);
        assertMetamorphicRelationFor(input(start, end), 0);
    }

    @Test
    public void LOCALDATETIME_SUBDAY_TRUNCATION_v2_reverseTwentyThreeHoursFiftyNineMinutes() {
        LocalDateTime start = LocalDateTime.of(2024, 6, 10, 12, 0);
        LocalDateTime end = start.minusHours(23).minusMinutes(59);
        assertMetamorphicRelationFor(input(start, end), 0);
    }

    @Test
    public void LOCALDATETIME_SUBDAY_TRUNCATION_v3_exactTwentyFourHours() {
        LocalDateTime start = LocalDateTime.of(2024, 7, 20, 8, 15, 30);
        LocalDateTime end = start.plusHours(24);
        assertMetamorphicRelationFor(input(start, end), 1);
    }

    @Test
    public void LOCALDATETIME_SUBDAY_TRUNCATION_v4_dayPlusRemainder() {
        LocalDateTime start = LocalDateTime.of(2024, 11, 30, 23, 50);
        LocalDateTime end = start.plusDays(1).plusHours(2).plusNanos(17);
        assertMetamorphicRelationFor(input(start, end), 1);
    }

    @Test
    public void INSTANT_ELAPSED_DAY_COUNTS_v1_exactElapsedDay() {
        Instant start = Instant.parse("2022-04-01T10:20:30Z");
        Instant end = start.plusSeconds(86400);
        assertMetamorphicRelationFor(input(start, end), 1);
    }

    @Test
    public void INSTANT_ELAPSED_DAY_COUNTS_v2_positiveSubday() {
        Instant start = Instant.parse("2022-05-01T23:59:59Z");
        Instant end = start.plusSeconds(86399);
        assertMetamorphicRelationFor(input(start, end), 0);
    }

    @Test
    public void INSTANT_ELAPSED_DAY_COUNTS_v3_negativeBetweenOneAndTwoDays() {
        Instant start = Instant.parse("2022-06-15T12:00:00Z");
        Instant end = start.minusSeconds(90000);
        assertMetamorphicRelationFor(input(start, end), -1);
    }

    @Test
    public void OFFSETDATETIME_FIXED_OFFSET_v1_positiveExactDays() {
        OffsetDateTime start = OffsetDateTime.of(2024, 3, 5, 9, 10, 0, 0, ZoneOffset.ofHoursMinutes(5, 30));
        assertMetamorphicRelationFor(input(start, start.plusDays(4)), 4);
    }

    @Test
    public void OFFSETDATETIME_FIXED_OFFSET_v2_negativeExactDays() {
        OffsetDateTime start = OffsetDateTime.of(2024, 8, 19, 18, 40, 0, 0, ZoneOffset.ofHours(-4));
        assertMetamorphicRelationFor(input(start, start.minusDays(3)), -3);
    }

    @Test
    public void OFFSETDATETIME_FIXED_OFFSET_v3_subdayAcrossLocalDate() {
        OffsetDateTime start = OffsetDateTime.of(2024, 10, 10, 23, 30, 0, 0, ZoneOffset.ofHours(9));
        OffsetDateTime end = start.plusHours(2);
        assertMetamorphicRelationFor(input(start, end), 0);
    }

    @Test
    public void ZONEDDATETIME_FIXED_OFFSET_ZONE_v1_forward() {
        ZonedDateTime start = ZonedDateTime.of(2024, 1, 20, 6, 30, 0, 0, ZoneId.of("+05:45"));
        assertMetamorphicRelationFor(input(start, start.plusDays(18)), 18);
    }

    @Test
    public void ZONEDDATETIME_FIXED_OFFSET_ZONE_v2_reverse() {
        ZonedDateTime start = ZonedDateTime.of(2024, 9, 15, 21, 10, 0, 0, ZoneId.of("-02:00"));
        assertMetamorphicRelationFor(input(start, start.minusDays(12)), -12);
    }

    @Test
    public void ZONEDDATETIME_STABLE_ENDPOINT_OFFSETS_v1_forwardAcrossSpringTransition() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2024, 1, 10, 12, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2024, 5, 10, 12, 0, 0, 0, zone);
        assertMetamorphicRelationFor(input(start, end), 121);
    }

    @Test
    public void ZONEDDATETIME_STABLE_ENDPOINT_OFFSETS_v2_reverseAcrossAutumnTransition() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2024, 12, 1, 15, 0, 0, 0, zone);
        ZonedDateTime end = ZonedDateTime.of(2024, 8, 1, 15, 0, 0, 0, zone);
        assertMetamorphicRelationFor(input(start, end), -122);
    }

    @Test
    public void NON_ISO_CHRONOLOGY_DATES_v1_japaneseForward() {
        JapaneseDate start = JapaneseDate.from(LocalDate.of(2021, 4, 10));
        JapaneseDate end = start.plus(12, ChronoUnit.DAYS);
        assertMetamorphicRelationFor(input(start, end), 12);
    }

    @Test
    public void NON_ISO_CHRONOLOGY_DATES_v2_thaiBuddhistReverse() {
        ThaiBuddhistDate start = ThaiBuddhistDate.from(LocalDate.of(2022, 10, 25));
        ThaiBuddhistDate end = start.minus(20, ChronoUnit.DAYS);
        assertMetamorphicRelationFor(input(start, end), -20);
    }

    @Test
    public void COMPATIBLE_MIXED_TEMPORAL_DIRECTION_v1_forwardDateComponent() {
        LocalDate start = LocalDate.of(2024, 5, 1);
        LocalDateTime end = LocalDateTime.of(2024, 5, 10, 23, 59, 59);
        assertMetamorphicRelationFor(input(start, end), 9);
    }

    @Test
    public void COMPATIBLE_MIXED_TEMPORAL_DIRECTION_v2_reverseDateComponent() {
        LocalDate start = LocalDate.of(2024, 5, 20);
        LocalDateTime end = LocalDateTime.of(2024, 5, 14, 1, 15);
        assertMetamorphicRelationFor(input(start, end), -6);
    }
}
