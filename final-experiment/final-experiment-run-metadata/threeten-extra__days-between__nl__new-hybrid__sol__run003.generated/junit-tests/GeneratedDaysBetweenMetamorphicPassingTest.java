import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

public class GeneratedDaysBetweenMetamorphicPassingTest {

    private static final long FOLLOW_UP_SHIFT_DAYS = 37L;

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input generateFollowUp(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {

        Temporal shiftedStart = source.arg0().plus(FOLLOW_UP_SHIFT_DAYS, ChronoUnit.DAYS);
        Temporal shiftedEnd = source.arg1().plus(FOLLOW_UP_SHIFT_DAYS, ChronoUnit.DAYS);
        assertNotNull(shiftedStart);
        assertNotNull(shiftedEnd);
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(shiftedStart, shiftedEnd);
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedDaysBetweenInvocation1uknttg.Input source) {

        Days sourceOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(source);
        MtllmGeneratedDaysBetweenInvocation1uknttg.Input followUp = generateFollowUp(source);
        Days followUpOutput = MtllmGeneratedDaysBetweenInvocation1uknttg.invoke(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(Days sourceOutput, Days followUpOutput) {
        assertEquals(sourceOutput.getAmount(), followUpOutput.getAmount());
    }

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input input(
            Temporal start, Temporal end) {
        return new MtllmGeneratedDaysBetweenInvocation1uknttg.Input(start, end);
    }

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input dateGap(
            long startEpochDay, long gap) {
        LocalDate start = LocalDate.ofEpochDay(startEpochDay);
        LocalDate end = LocalDate.ofEpochDay(startEpochDay + gap);
        return input(start, end);
    }

    private MtllmGeneratedDaysBetweenInvocation1uknttg.Input dateTimeGap(
            LocalDateTime start, long days, long hours) {
        return input(start, start.plusDays(days).plusHours(hours));
    }

    @Test
    void SAME_REFERENCE_ZERO_variation1_localDate() {
        LocalDate endpoint = LocalDate.of(2000, 2, 29);
        assertMetamorphicRelationFor(input(endpoint, endpoint));
    }

    @Test
    void SAME_REFERENCE_ZERO_variation2_localDateTime() {
        LocalDateTime endpoint = LocalDateTime.of(1986, 7, 14, 23, 45, 12);
        assertMetamorphicRelationFor(input(endpoint, endpoint));
    }

    @Test
    void DISTINCT_ENDPOINTS_ZERO_WHOLE_DAYS_variation1_forwardHours() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 10, 8, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 10, 19, 30);
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void DISTINCT_ENDPOINTS_ZERO_WHOLE_DAYS_variation2_equalValues() {
        LocalDateTime start = LocalDateTime.of(1999, 12, 31, 23, 59);
        LocalDateTime end = LocalDateTime.of(
                LocalDate.of(1999, 12, 31), LocalTime.of(23, 59));
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void POSITIVE_ONE_DAY_variation1_localDate() {
        assertMetamorphicRelationFor(dateGap(0, 1));
    }

    @Test
    void POSITIVE_ONE_DAY_variation2_leapBoundary() {
        LocalDate start = LocalDate.of(2020, 2, 28);
        assertMetamorphicRelationFor(input(start, start.plusDays(1)));
    }

    @Test
    void POSITIVE_ONE_DAY_variation3_localDateTimeRemainder() {
        LocalDateTime start = LocalDateTime.of(2012, 6, 30, 3, 15);
        assertMetamorphicRelationFor(dateTimeGap(start, 1, 7));
    }

    @Test
    void NEGATIVE_ONE_DAY_variation1_localDate() {
        assertMetamorphicRelationFor(dateGap(500, -1));
    }

    @Test
    void NEGATIVE_ONE_DAY_variation2_yearBoundary() {
        LocalDate start = LocalDate.of(2021, 1, 1);
        assertMetamorphicRelationFor(input(start, start.minusDays(1)));
    }

    @Test
    void NEGATIVE_ONE_DAY_variation3_localDateTimeRemainder() {
        LocalDateTime start = LocalDateTime.of(2018, 9, 20, 18, 30);
        assertMetamorphicRelationFor(dateTimeGap(start, -1, -4));
    }

    @Test
    void SMALL_POSITIVE_GAP_variation1_twoDays() {
        assertMetamorphicRelationFor(dateGap(-100, 2));
    }

    @Test
    void SMALL_POSITIVE_GAP_variation2_threeDaysAcrossMonth() {
        LocalDate start = LocalDate.of(2023, 1, 30);
        assertMetamorphicRelationFor(input(start, start.plusDays(3)));
    }

    @Test
    void SMALL_POSITIVE_GAP_variation3_fiveDaysDateTime() {
        LocalDateTime start = LocalDateTime.of(2004, 2, 25, 6, 45);
        assertMetamorphicRelationFor(dateTimeGap(start, 5, 0));
    }

    @Test
    void SMALL_POSITIVE_GAP_variation4_sixDaysWithRemainder() {
        LocalDateTime start = LocalDateTime.of(1977, 11, 3, 1, 10);
        assertMetamorphicRelationFor(dateTimeGap(start, 6, 8));
    }

    @Test
    void SMALL_NEGATIVE_GAP_variation1_minusTwoDays() {
        assertMetamorphicRelationFor(dateGap(1000, -2));
    }

    @Test
    void SMALL_NEGATIVE_GAP_variation2_minusThreeAcrossYear() {
        LocalDate start = LocalDate.of(2001, 1, 2);
        assertMetamorphicRelationFor(input(start, start.minusDays(3)));
    }

    @Test
    void SMALL_NEGATIVE_GAP_variation3_minusFiveDateTime() {
        LocalDateTime start = LocalDateTime.of(2010, 8, 18, 12, 0);
        assertMetamorphicRelationFor(dateTimeGap(start, -5, 0));
    }

    @Test
    void SMALL_NEGATIVE_GAP_variation4_minusSixWithRemainder() {
        LocalDateTime start = LocalDateTime.of(1965, 5, 20, 15, 20);
        assertMetamorphicRelationFor(dateTimeGap(start, -6, -3));
    }

    @Test
    void POSITIVE_GAP_EQUALS_SHIFT_variation1_epochDates() {
        assertMetamorphicRelationFor(dateGap(0, 37));
    }

    @Test
    void POSITIVE_GAP_EQUALS_SHIFT_variation2_leapYear() {
        LocalDate start = LocalDate.of(2024, 2, 10);
        assertMetamorphicRelationFor(input(start, start.plusDays(37)));
    }

    @Test
    void POSITIVE_GAP_EQUALS_SHIFT_variation3_dateTimeRemainder() {
        LocalDateTime start = LocalDateTime.of(1990, 4, 3, 9, 5);
        assertMetamorphicRelationFor(dateTimeGap(start, 37, 2));
    }

    @Test
    void NEGATIVE_GAP_EQUALS_SHIFT_variation1_epochDates() {
        assertMetamorphicRelationFor(dateGap(200, -37));
    }

    @Test
    void NEGATIVE_GAP_EQUALS_SHIFT_variation2_centuryBoundary() {
        LocalDate start = LocalDate.of(2000, 1, 15);
        assertMetamorphicRelationFor(input(start, start.minusDays(37)));
    }

    @Test
    void NEGATIVE_GAP_EQUALS_SHIFT_variation3_dateTimeRemainder() {
        LocalDateTime start = LocalDateTime.of(1980, 10, 25, 20, 10);
        assertMetamorphicRelationFor(dateTimeGap(start, -37, -6));
    }

    @Test
    void MEDIUM_POSITIVE_GAP_variation1_sevenDays() {
        assertMetamorphicRelationFor(dateGap(-5000, 7));
    }

    @Test
    void MEDIUM_POSITIVE_GAP_variation2_thirtySixDays() {
        assertMetamorphicRelationFor(dateGap(12345, 36));
    }

    @Test
    void MEDIUM_POSITIVE_GAP_variation3_thirtyEightDays() {
        LocalDate start = LocalDate.of(2019, 12, 20);
        assertMetamorphicRelationFor(input(start, start.plusDays(38)));
    }

    @Test
    void MEDIUM_POSITIVE_GAP_variation4_threeHundredSixtyFiveWithRemainder() {
        LocalDateTime start = LocalDateTime.of(2016, 2, 29, 4, 30);
        assertMetamorphicRelationFor(dateTimeGap(start, 365, 5));
    }

    @Test
    void MEDIUM_NEGATIVE_GAP_variation1_minusSevenDays() {
        assertMetamorphicRelationFor(dateGap(7000, -7));
    }

    @Test
    void MEDIUM_NEGATIVE_GAP_variation2_minusThirtySixDays() {
        assertMetamorphicRelationFor(dateGap(-9000, -36));
    }

    @Test
    void MEDIUM_NEGATIVE_GAP_variation3_minusThirtyEightDays() {
        LocalDate start = LocalDate.of(2022, 3, 10);
        assertMetamorphicRelationFor(input(start, start.minusDays(38)));
    }

    @Test
    void MEDIUM_NEGATIVE_GAP_variation4_minusThreeHundredSixtyFiveWithRemainder() {
        LocalDateTime start = LocalDateTime.of(2017, 7, 1, 17, 40);
        assertMetamorphicRelationFor(dateTimeGap(start, -365, -2));
    }

    @Test
    void LARGE_POSITIVE_GAP_variation1_threeHundredSixtySixDays() {
        assertMetamorphicRelationFor(dateGap(25000, 366));
    }

    @Test
    void LARGE_POSITIVE_GAP_variation2_tenThousandDays() {
        assertMetamorphicRelationFor(dateGap(-1_000_000, 10_000));
    }

    @Test
    void LARGE_POSITIVE_GAP_variation3_oneBillionDays() {
        assertMetamorphicRelationFor(dateGap(-500_000_000L, 1_000_000_000L));
    }

    @Test
    void LARGE_NEGATIVE_GAP_variation1_minusThreeHundredSixtySixDays() {
        assertMetamorphicRelationFor(dateGap(35000, -366));
    }

    @Test
    void LARGE_NEGATIVE_GAP_variation2_minusTenThousandDays() {
        assertMetamorphicRelationFor(dateGap(1_000_000, -10_000));
    }

    @Test
    void LARGE_NEGATIVE_GAP_variation3_minusOneBillionDays() {
        assertMetamorphicRelationFor(dateGap(500_000_000L, -1_000_000_000L));
    }

    @Test
    void MAXIMUM_INT_DAY_COUNT_variation1_exactBoundary() {
        assertMetamorphicRelationFor(dateGap(-1_000L, Integer.MAX_VALUE));
    }

    @Test
    void MINIMUM_INT_DAY_COUNT_variation1_exactBoundary() {
        assertMetamorphicRelationFor(dateGap(1_000L, Integer.MIN_VALUE));
    }

    @Test
    void FORWARD_SUB_DAY_ZERO_variation1_oneSecond() {
        LocalDateTime start = LocalDateTime.of(2020, 5, 5, 10, 0, 0);
        LocalDateTime end = start.plusSeconds(1);
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void FORWARD_SUB_DAY_ZERO_variation2_twentyThreeHours() {
        LocalDateTime start = LocalDateTime.of(2020, 5, 5, 10, 0);
        LocalDateTime end = start.plusHours(23);
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void REVERSE_SUB_DAY_ZERO_variation1_minusOneSecond() {
        LocalDateTime start = LocalDateTime.of(2030, 6, 15, 12, 0, 0);
        LocalDateTime end = start.minusSeconds(1);
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void REVERSE_SUB_DAY_ZERO_variation2_minusTwentyThreeHours() {
        LocalDateTime start = LocalDateTime.of(2030, 6, 15, 12, 0);
        LocalDateTime end = start.minusHours(23);
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void POSITIVE_WHOLE_DAYS_WITH_REMAINDER_variation1_twoDaysOneMinute() {
        LocalDateTime start = LocalDateTime.of(2008, 1, 4, 7, 20);
        LocalDateTime end = start.plusDays(2).plusMinutes(1);
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void POSITIVE_WHOLE_DAYS_WITH_REMAINDER_variation2_twelveDaysHours() {
        LocalDateTime start = LocalDateTime.of(1998, 9, 9, 22, 15);
        LocalDateTime end = start.plusDays(12).plusHours(11);
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void NEGATIVE_WHOLE_DAYS_WITH_REMAINDER_variation1_minusTwoDaysOneMinute() {
        LocalDateTime start = LocalDateTime.of(2011, 11, 11, 11, 11);
        LocalDateTime end = start.minusDays(2).minusMinutes(1);
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void NEGATIVE_WHOLE_DAYS_WITH_REMAINDER_variation2_minusTwelveDaysHours() {
        LocalDateTime start = LocalDateTime.of(1988, 3, 22, 6, 0);
        LocalDateTime end = start.minusDays(12).minusHours(9);
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void COMPATIBLE_DISTINCT_TEMPORAL_CLASSES_variation1_zeroDays() {
        LocalDate start = LocalDate.of(2025, 4, 20);
        LocalDateTime end = LocalDateTime.of(2025, 4, 20, 18, 45);
        assertMetamorphicRelationFor(input(start, end));
    }

    @Test
    void COMPATIBLE_DISTINCT_TEMPORAL_CLASSES_variation2_thirtySevenDays() {
        LocalDate start = LocalDate.of(1975, 8, 6);
        LocalDateTime end = LocalDateTime.of(start.plusDays(37), LocalTime.of(13, 30));
        assertMetamorphicRelationFor(input(start, end));
    }
}
