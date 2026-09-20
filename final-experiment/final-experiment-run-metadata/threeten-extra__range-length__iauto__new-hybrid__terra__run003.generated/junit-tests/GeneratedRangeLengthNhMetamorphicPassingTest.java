import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        org.junit.jupiter.api.Assertions.assertEquals(sourceOutput + 23, followUpOutput);
    }

    @Test
    void MINIMUM_VALID_BOUNDED_ONE_DAY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_INTERIOR_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HALF_OPEN_ONE_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 11));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_EXTRA_DAYS_LENGTH_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 24));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NON_LEAP_CENTURY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(1900, 2, 28), LocalDate.of(1900, 3, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_CENTURY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2000, 2, 28), LocalDate.of(2000, 3, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MONTH_END_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 1, 31), LocalDate.of(2023, 2, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_END_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BCE_TO_CE_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(-1, 12, 31), LocalDate.of(0, 1, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLETON_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 5, 5), LocalDate.of(2024, 5, 5));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_LEAP_MONTH_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 29));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ZERO_EMPTY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 7, 1), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_MONTH_END_CLAMP_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 1, 31), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_MIXED_COMPONENTS_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2021, 1, 15), Period.of(1, 2, 3));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCALDATE_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 8, 20));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCALDATETIME_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDateTime.of(2024, 8, 20, 23, 59, 59));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_COMMON_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_FEBRUARY_YEARMONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-03-10/2024-03-15");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-31/P1M");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_PERIOD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("P10D/2024-03-01");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_FINITE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAX_ADJACENT_FOLLOWUP_REMAINS_BOUNDED_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(25), LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAX_ADJACENT_FOLLOWUP_BECOMES_UNBOUNDED_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MAX.minusDays(24), LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INTEGER_MAX_MINUS_24_variation1() {
        LocalDate base = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(base, base.plusDays(Integer.MAX_VALUE - 24L));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INTEGER_MAX_MINUS_23_variation1() {
        LocalDate base = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(base, base.plusDays(Integer.MAX_VALUE - 23L));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INTEGER_MAX_MINUS_22_variation1() {
        LocalDate base = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(base, base.plusDays(Integer.MAX_VALUE - 22L));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INTEGER_MAX_variation1() {
        LocalDate base = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(base, base.plusDays(Integer.MAX_VALUE));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_ABOVE_INTEGER_MAX_variation1() {
        LocalDate base = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(base, base.plusDays(Integer.MAX_VALUE + 1L));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
