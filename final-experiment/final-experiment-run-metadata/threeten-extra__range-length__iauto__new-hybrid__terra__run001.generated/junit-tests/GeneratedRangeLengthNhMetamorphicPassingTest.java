import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        Assertions.assertEquals(23, followUpOutput - sourceOutput);
    }

    @Test
    void EMPTY_RANGE_FACTORY_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 5, 10));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_HALF_OPEN_RANGE_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 15);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOWER_ENDPOINT_BOUNDED_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(1),
                LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLE_DAY_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 7, 4),
                LocalDate.of(2024, 7, 4));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ZERO_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 8, 20),
                Period.ZERO);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_MONTH_END_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 31),
                Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_LEAP_DAY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 2, 28),
                LocalDate.of(2024, 2, 29));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIRECT_RANGE_ACROSS_YEAR_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 12, 20),
                LocalDate.of(2024, 1, 10));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_DATE_DATE_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-03-01/2024-03-24");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_DATE_PERIOD_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-02-01/P1M");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_PERIOD_DATE_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.parse("P1M/2024-03-01");
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 6, 15));
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
    void FROM_LEAP_FEBRUARY_YEAR_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_variation1() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_ISO_YEAR_WEEK_variation1() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2020, 53));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_WITH_FINITE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_LENGTH_BELOW_INT_MAX_variation1() {
        LocalDate end = LocalDate.of(999999999, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays((long) Integer.MAX_VALUE - 24L),
                end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_SOURCE_FOLLOW_UP_UNBOUNDED_END_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.of(end.minusDays(1), end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EXACT_INTEGER_MAX_FINITE_LENGTH_variation1() {
        LocalDate end = LocalDate.of(999999999, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays((long) Integer.MAX_VALUE),
                end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_ABOVE_INT_MAX_variation1() {
        LocalDate end = LocalDate.of(999999999, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays((long) Integer.MAX_VALUE + 1L),
                end);
        int sourceOutput = source.lengthInDays();
        Object[] followUpValues = RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp = (LocalDateRange) followUpValues[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
