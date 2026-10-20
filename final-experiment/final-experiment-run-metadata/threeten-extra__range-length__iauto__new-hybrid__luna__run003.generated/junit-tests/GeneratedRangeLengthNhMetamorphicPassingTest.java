import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static void assertMetamorphicRelation(
            int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE
                || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        if (followUpOutput - sourceOutput != 23) {
            throw new AssertionError(
                    "The follow-up length must be exactly 23 days greater than the source length");
        }
    }

    @Test
    void EMPTY_ORDINARY_RANGE_1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 1, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_HALF_OPEN_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2024, 1, 1), Period.ofDays(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWENTY_TWO_DAY_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWENTY_THREE_DAY_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWENTY_FOUR_DAY_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 25));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MONTH_BOUNDARY_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 31), LocalDate.of(2024, 2, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_BOUNDARY_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_PERIOD_CONSTRUCTION_1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2024, 5, 10), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MONTH_PERIOD_CONSTRUCTION_1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2024, 1, 31), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLE_DATE_1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_MULTI_DAY_RANGE_1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 30));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_DATE_DATE_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.parse("2024-03-01/2024-03-10");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_DATE_PERIOD_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.parse("2024-03-01/P10D");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_PERIOD_DATE_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.parse("P10D/2024-03-11");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_1() {
        LocalDateRange source =
                LocalDateRange.from(LocalDate.of(2024, 7, 4));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_MONTH_1() {
        LocalDateRange source =
                LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_1() {
        LocalDateRange source =
                LocalDateRange.from(YearHalf.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_1() {
        LocalDateRange source =
                LocalDateRange.from(YearQuarter.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_WEEK_1() {
        LocalDateRange source =
                LocalDateRange.from(YearWeek.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_NEAR_MINIMUM_1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_ORDINARY_END_1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_BELOW_INT_LIMIT_1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDate start = LocalDate.ofEpochDay(
                end.toEpochDay() - ((long) Integer.MAX_VALUE - 23L));
        LocalDateRange source = LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_AT_INT_LIMIT_1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDate start = LocalDate.ofEpochDay(
                end.toEpochDay() - (long) Integer.MAX_VALUE);
        LocalDateRange source = LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_ABOVE_INT_LIMIT_1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDate start = LocalDate.ofEpochDay(
                end.toEpochDay() - ((long) Integer.MAX_VALUE + 100L));
        LocalDateRange source = LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FOLLOW_UP_REACHES_MAX_SENTINEL_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(24), LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_NEAR_MAX_WITH_FINITE_FOLLOW_UP_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(47), LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MIN_START_WITH_LONG_FINITE_END_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_ZERO_PERIOD_RANGE_1() {
        LocalDateRange source =
                LocalDateRange.parse("2024-08-01/P0D");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_LEAP_MONTH_PERIOD_1() {
        LocalDateRange source =
                LocalDateRange.parse("2024-01-31/P1M");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_YEAR_BOUNDARY_1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2023, 12, 30), LocalDate.of(2023, 12, 31));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_AFTER_DIRECT_OF_1() {
        LocalDate date = LocalDate.of(2025, 1, 1);
        LocalDateRange source = LocalDateRange.of(date, date);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SHORT_RANGE_AT_MINIMUM_START_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN, LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp =
                (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
