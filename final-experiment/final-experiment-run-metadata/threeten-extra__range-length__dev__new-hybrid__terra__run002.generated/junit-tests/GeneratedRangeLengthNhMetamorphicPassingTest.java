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

    @Test
    void EMPTY_AT_LOWEST_VALID_EMPTY_POSITION_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_AT_LOW_DATE_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_HALF_OPEN_TWENTY_THREE_DAYS_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_CROSSING_DATE_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_CONSTRUCTION_ONE_MONTH_IN_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 1), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLETON_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_DATE_FORMAT_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-04-01/2024-04-11");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_PERIOD_FORMAT_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-05-01/P7D");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_PERIOD_DATE_FORMAT_variation1() {
        LocalDateRange source = LocalDateRange.parse("P10D/2024-03-11");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 7, 4));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_FEBRUARY_YEARMONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_YEAR_FIRST_HALF_variation1() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2024, Half.H1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_YEAR_FIRST_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, Quarter.Q1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_ISO_YEAR_WEEK_variation1() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_WITH_FINITE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2000, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIGH_DATE_FOLLOW_UP_STAYS_BOUNDED_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(25),
                LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIGH_DATE_FOLLOW_UP_BECOMES_UNBOUNDED_END_variation1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(24),
                LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INTEGER_MAX_MINUS_TWENTY_FOUR_variation1() {
        LocalDate base = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.of(base, base.plusDays((long) Integer.MAX_VALUE - 24L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INTEGER_MAX_MINUS_TWENTY_THREE_variation1() {
        LocalDate base = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.of(base, base.plusDays((long) Integer.MAX_VALUE - 23L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INTEGER_MAX_MINUS_ONE_variation1() {
        LocalDate base = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.of(base, base.plusDays((long) Integer.MAX_VALUE - 1L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_INTEGER_MAX_VALUE_variation1() {
        LocalDate base = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.of(base, base.plusDays((long) Integer.MAX_VALUE));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_LENGTH_ABOVE_INTEGER_MAX_VALUE_variation1() {
        LocalDate base = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.of(base, base.plusDays((long) Integer.MAX_VALUE + 1L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
