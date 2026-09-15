import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    @Test
    public void EMPTY_INTERIOR_HALF_OPEN_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 6, 15),
                LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 6, 15),
                Period.ofDays(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACTLY_23_DAY_HALF_OPEN_RANGE_1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TWENTY_FOUR_DAY_HALF_OPEN_RANGE_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 25));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEAP_DAY_CROSSING_RANGE_1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 3, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PERIOD_ZERO_CREATES_EMPTY_RANGE_1() {
        LocalDateRange source = LocalDateRange.parse("2024-07-01/2024-07-01");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PERIOD_YEAR_RANGE_1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-01/P1Y");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_SINGLE_DATE_RANGE_1() {
        LocalDateRange source = LocalDateRange.parse("P0D/2024-05-10");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSED_MULTI_DAY_RANGE_1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 3, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MINIMUM_ALLOWED_END_BOUNDARY_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN,
                LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDED_RANGE_AFTER_MINIMUM_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(1),
                LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSED_DATE_OVER_DATE_1() {
        LocalDateRange source = LocalDateRange.parse("2024-04-01/2024-04-11");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSED_DATE_OVER_PERIOD_1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-01/P1M");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSED_PERIOD_OVER_DATE_1() {
        LocalDateRange source = LocalDateRange.parse("P10D/2024-05-11");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void PARSED_LOWERCASE_PERIOD_1() {
        LocalDateRange source = LocalDateRange.parse("2024-05-01/p10d");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_LOCAL_DATE_1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 8, 20));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_MONTH_1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_HALF_1() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_QUARTER_1() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FROM_YEAR_WEEK_1() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void MAXIMUM_SAFE_FINITE_LENGTH_1() {
        LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 23L);
        LocalDateRange source = LocalDateRange.of(start, end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void END_ADVANCE_REACHES_MAX_SENTINEL_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(25),
                LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FINITE_RANGE_NEAR_MAXIMUM_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(26),
                LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void UNBOUNDED_START_FACTORY_1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
