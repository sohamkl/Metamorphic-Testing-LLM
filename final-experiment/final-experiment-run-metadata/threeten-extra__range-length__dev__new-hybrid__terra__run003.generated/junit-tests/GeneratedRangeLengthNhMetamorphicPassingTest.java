import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    @Test
    void EMPTY_ORDINARY_DATE_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIRECT_ONE_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIRECT_TWENTY_THREE_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 24));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MONTH_END_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 1, 31), LocalDate.of(2023, 2, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_BOUNDARY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BCE_TO_CE_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(0, 12, 31), LocalDate.of(1, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LONG_FINITE_MULTI_CENTURY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(1900, 1, 1), LocalDate.of(2100, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEAR_LOCALDATE_MIN_BOUNDED_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEAR_LOCALDATE_MAX_FOLLOWUP_BECOMES_UNBOUNDED_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.of(end.minusDays(1), end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_MAX_MINUS_ONE_variation1() {
        LocalDate start = LocalDate.of(-999999999, 1, 3);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(Integer.MAX_VALUE - 1L));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_EXACTLY_MAX_variation1() {
        LocalDate start = LocalDate.of(-999999999, 1, 3);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(Integer.MAX_VALUE));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_GREATER_THAN_MAX_variation1() {
        LocalDate start = LocalDate.of(-999999999, 1, 3);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(Integer.MAX_VALUE + 1L));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_FINITE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2000, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_FOLLOWUP_UNBOUNDED_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ZERO_AT_ORDINARY_DATE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ONE_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), Period.ofDays(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_MONTH_END_ADDITION_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 1, 31), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_YEAR_ADDITION_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 3, 1), Period.ofYears(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLETON_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_TWO_INCLUDED_DATES_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 16));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_TO_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-06-15/2024-06-20");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_TO_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-01/P10D");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_PERIOD_TO_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("P10D/2024-01-11");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_TIME_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDateTime.of(2024, 6, 15, 23, 59, 59));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_COMMON_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_COMMON_FEBRUARY_YEAR_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2023, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_FEBRUARY_YEAR_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_MINIMUM_YEAR_UNBOUNDED_START_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(-999999999));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        int followUpOutput = ((LocalDateRange) followUpValues[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
