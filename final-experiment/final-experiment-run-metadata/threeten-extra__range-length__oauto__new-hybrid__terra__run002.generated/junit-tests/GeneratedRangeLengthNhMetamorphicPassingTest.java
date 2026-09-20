import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.Half;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.Quarter;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private LocalDateRange generateFollowUp(LocalDateRange range) {
        LocalDate extendedEnd = range.getEnd().plusDays(23);
        return LocalDateRange.of(range.getStart(), extendedEnd);
    }

    @Test
    void ORDINARY_DIRECT_ONE_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 10), LocalDate.of(2024, 6, 11));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_DIRECT_MULTI_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 10), LocalDate.of(2024, 6, 20));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_RANGE_VIA_OF_EMPTY_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 10));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_RANGE_VIA_OF_variation1() {
        LocalDate date = LocalDate.of(2024, 6, 10);
        LocalDateRange source = LocalDateRange.of(date, date);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MONTH_END_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 1, 30), LocalDate.of(2023, 2, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_BOUNDARY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLETON_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 6, 10), LocalDate.of(2024, 6, 10));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_LEAP_DAY_PAIR_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 2, 29));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ZERO_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 10), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_MONTH_FROM_MONTH_END_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 31), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-06-10/2024-06-15");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-31/P1M");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_PERIOD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("P1M/2024-03-31");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 6, 10));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_variation1() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2024, Half.H1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, Quarter.Q1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_WEEK_variation1() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_ORDINARY_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 6, 10));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_MINIMUM_VALID_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIRECT_UNBOUNDED_START_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN, LocalDate.of(2024, 6, 10));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EARLIEST_VALID_BOUNDED_ONE_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN.plusDays(2), LocalDate.MIN.plusDays(3));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EARLIEST_VALID_EMPTY_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LATEST_FOLLOWUP_BECOMES_UNBOUNDED_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(24), LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UPPER_BOUNDARY_BOUNDED_FOLLOWUP_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(25), LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_UPPER_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.MAX.minusDays(25), LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTEGER_MAX_MINUS_TWENTY_THREE_LENGTH_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE - 23L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTEGER_MAX_MINUS_TWENTY_FOUR_LENGTH_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE - 24L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTEGER_MAX_EXACT_FINITE_LENGTH_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_ABOVE_INTEGER_MAX_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE + 1L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEAR_FULL_FINITE_LOCALDATE_DOMAIN_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN.plusDays(2), LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
