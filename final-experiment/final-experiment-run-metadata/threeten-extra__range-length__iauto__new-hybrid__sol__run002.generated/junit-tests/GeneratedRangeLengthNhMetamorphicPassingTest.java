import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        if ((long) followUpOutput != (long) sourceOutput + 23L) {
            throw new AssertionError(
                    "Expected the follow-up length to be exactly 23 days greater than the source length, "
                            + "but source was " + sourceOutput + " and follow-up was " + followUpOutput);
        }
    }

    @Test
    public void EMPTY_BOUNDED_RANGE_variation1_nearMinimum() {
        LocalDate date = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.ofEmpty(date);

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BOUNDED_RANGE_variation2_ordinaryDate() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 17));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_BOUNDED_RANGE_variation3_followUpBecomesUnbounded() {
        LocalDate date = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.ofEmpty(date);

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_DAY_BOUNDED_RANGE_variation1_nearMinimum() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(1));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_DAY_BOUNDED_RANGE_variation2_leapDay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 29),
                LocalDate.of(2024, 3, 1));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_JUST_BELOW_EXTRA_DAYS_variation1() {
        LocalDate start = LocalDate.of(1999, 12, 20);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(22));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_EQUAL_TO_EXTRA_DAYS_variation1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LENGTH_JUST_ABOVE_EXTRA_DAYS_variation1() {
        LocalDate start = LocalDate.of(2035, 8, 25);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(24));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_BOUNDARY_RANGES_variation1_commonFebruary() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2019, 2, 20),
                LocalDate.of(2019, 3, 5));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_BOUNDARY_RANGES_variation2_leapFebruary() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 2, 20),
                LocalDate.of(2020, 3, 5));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_BOUNDARY_RANGES_variation3_thirtyDayMonth() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 4, 25),
                LocalDate.of(2023, 5, 5));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MONTH_BOUNDARY_RANGES_variation4_thirtyOneDayMonth() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 25),
                LocalDate.of(2023, 2, 5));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_BOUNDARY_RANGES_variation1_oneDay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2022, 12, 31),
                LocalDate.of(2023, 1, 1));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_BOUNDARY_RANGES_variation2_twentyThreeDays() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 12, 20),
                LocalDate.of(2022, 1, 12));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_BOUNDARY_RANGES_variation3_multiWeek() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2018, 12, 1),
                LocalDate.of(2019, 1, 20));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_RELATIONSHIPS_variation1_endingOnLeapDay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 20),
                LocalDate.of(2024, 2, 29));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_RELATIONSHIPS_variation2_startingOnLeapDay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 29),
                LocalDate.of(2024, 3, 10));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_RELATIONSHIPS_variation3_enclosingLeapDay() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 25),
                LocalDate.of(2024, 3, 5));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_RELATIONSHIPS_variation4_oneDayLeapDate() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2000, 2, 29),
                LocalDate.of(2000, 3, 1));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_LEAP_FEBRUARY_BOUNDARY_variation1_ordinaryYear() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 2, 27),
                LocalDate.of(2023, 3, 2));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_LEAP_FEBRUARY_BOUNDARY_variation2_nonLeapCentury() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1900, 2, 27),
                LocalDate.of(1900, 3, 2));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_YEAR_RULE_VARIANTS_variation1_divisibleByFour() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 28),
                LocalDate.of(2024, 3, 1));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_YEAR_RULE_VARIANTS_variation2_centuryNotDivisibleByFourHundred() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2100, 2, 28),
                LocalDate.of(2100, 3, 1));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_YEAR_RULE_VARIANTS_variation3_divisibleByFourHundred() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2000, 2, 28),
                LocalDate.of(2000, 3, 1));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROLEPTIC_YEAR_ZERO_TRANSITIONS_variation1_negativeOneToZero() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(-1, 12, 20),
                LocalDate.of(0, 1, 12));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PROLEPTIC_YEAR_ZERO_TRANSITIONS_variation2_zeroToOne() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(0, 12, 1),
                LocalDate.of(1, 1, 15));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_YEAR_BOUNDED_RANGES_variation1_twoYears() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2018, 1, 1),
                LocalDate.of(2020, 1, 1));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_YEAR_BOUNDED_RANGES_variation2_decade() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1990, 6, 15),
                LocalDate.of(2000, 6, 15));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MULTI_YEAR_BOUNDED_RANGES_variation3_multipleLeapYears() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1900, 1, 1),
                LocalDate.of(1950, 1, 1));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WEEK_BOUNDARY_RANGES_variation1_sixDays() {
        LocalDate start = LocalDate.of(2025, 5, 5);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(6));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WEEK_BOUNDARY_RANGES_variation2_sevenDays() {
        LocalDate start = LocalDate.of(2025, 6, 9);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(7));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WEEK_BOUNDARY_RANGES_variation3_eightDays() {
        LocalDate start = LocalDate.of(2025, 7, 14);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(8));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_BOUNDED_ONE_DAY_RANGE_variation1() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDate end = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.of(start, end);

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_LEGAL_EMPTY_RANGE_variation1() {
        LocalDate date = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.of(date, date);

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATEST_ORDINARY_BOUNDED_FOLLOW_UP_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.of(end.minusDays(1), end);

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FOLLOW_UP_BECOMES_UNBOUNDED_END_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.of(end.minusDays(1), end);

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MAXIMUM_NON_SENTINEL_FOLLOW_UP_LENGTH_variation1() {
        LocalDate end = LocalDate.of(3000, 1, 1);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE - 24L);
        LocalDateRange source = LocalDateRange.of(start, end);

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDED_SENTINEL_VALUE_COLLISION_variation1() {
        LocalDate end = LocalDate.of(3000, 6, 1);
        LocalDate start = end.minusDays((long) Integer.MAX_VALUE - 23L);
        LocalDateRange source = LocalDateRange.of(start, end);

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_UNBOUNDED_START_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.MIN.plusDays(2));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_UNBOUNDED_START_RANGES_variation1_beforeYearZero() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.of(-25, 11, 20));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_UNBOUNDED_START_RANGES_variation2_modernEra() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.of(2026, 9, 20));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_UNBOUNDED_START_RANGES_variation3_afterLeapBoundary() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.of(2400, 3, 2));

        int sourceOutput = source.lengthInDays();
        Object[] transformed = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) transformed[0];
        int followUpOutput = followUp.lengthInDays();

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
