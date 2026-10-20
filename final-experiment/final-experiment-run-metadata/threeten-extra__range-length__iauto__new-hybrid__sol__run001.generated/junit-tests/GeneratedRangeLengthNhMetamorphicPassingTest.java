import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        if (followUpOutput - sourceOutput != 23) {
            throw new AssertionError(
                    "The follow-up length must be exactly 23 days greater than the source length");
        }
    }

    @Test
    public void ORDINARY_EMPTY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 5, 15));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_LEGAL_EMPTY_RANGE_variation1() {
        LocalDate date = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.of(date, date);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UPPER_LIMIT_EMPTY_RANGE_variation1() {
        LocalDate date = LocalDate.MAX.minusDays(25);
        LocalDateRange source = LocalDateRange.ofEmpty(date);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_ONE_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 5, 15),
                LocalDate.of(2024, 5, 16));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EARLIEST_FINITE_ONE_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(1),
                LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SEVEN_DAY_RANGE_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 10);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(7));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_BELOW_EXTRA_DAYS_variation1() {
        LocalDate start = LocalDate.of(2022, 8, 5);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(22));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_EXTRA_DAYS_variation1() {
        LocalDate start = LocalDate.of(2022, 9, 7);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_ABOVE_EXTRA_DAYS_variation1() {
        LocalDate start = LocalDate.of(2022, 10, 9);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_LEAP_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 1),
                LocalDate.of(2024, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THIRTY_DAY_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 5, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void THIRTY_ONE_DAY_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 2, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2025, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SHORT_YEAR_BOUNDARY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 12, 30),
                LocalDate.of(2024, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EPOCH_BOUNDARY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1969, 12, 31),
                LocalDate.of(1970, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BCE_TO_YEAR_ZERO_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(-1, 12, 31),
                LocalDate.of(0, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NON_LEAP_CENTURY_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1900, 2, 1),
                LocalDate.of(1900, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_CENTURY_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2000, 2, 1),
                LocalDate.of(2000, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_YEAR_FIRST_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 4, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_YEAR_FIRST_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 4, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NINETY_TWO_DAY_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024, 10, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void COMMON_FIRST_HALF_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 7, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_FIRST_HALF_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 7, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_HUNDRED_EIGHTY_THREE_DAY_SPAN_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 4, 1),
                LocalDate.of(2023, 10, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SECOND_HALF_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TWO_YEARS_INCLUDING_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2025, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void GREGORIAN_FOUR_HUNDRED_YEAR_CYCLE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2400, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void YEAR_ZERO_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(0, 1, 1),
                LocalDate.of(1, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_AS_RANGE_START_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 29),
                LocalDate.of(2024, 3, 2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_AS_LAST_INCLUDED_DATE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 28),
                LocalDate.of(2024, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_LENGTH_BELOW_INT_FOLLOWUP_BOUNDARY_variation1() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 25L);
        LocalDateRange source = LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGEST_NON_SENTINEL_FOLLOWUP_LENGTH_variation1() {
        LocalDate start = LocalDate.of(3000, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 24L);
        LocalDateRange source = LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_INT_MAX_FOLLOWUP_COLLISION_variation1() {
        LocalDate start = LocalDate.of(4000, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 23L);
        LocalDateRange source = LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UPPER_END_TWO_DAYS_INSIDE_TRANSFORM_LIMIT_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(26),
                LocalDate.MAX.minusDays(25));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UPPER_END_ONE_DAY_INSIDE_TRANSFORM_LIMIT_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(25),
                LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGEST_TRANSFORMABLE_END_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(24),
                LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEAR_MIN_TWENTY_THREE_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(1),
                LocalDate.MIN.plusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_EARLIEST_END_variation1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_ORDINARY_END_variation1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_NEAR_MAX_END_variation1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_TO_LARGEST_TRANSFORMABLE_END_variation1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] values = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) values[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
