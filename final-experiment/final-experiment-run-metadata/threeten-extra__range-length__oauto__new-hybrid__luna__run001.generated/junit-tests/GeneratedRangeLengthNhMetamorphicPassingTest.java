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

    private static LocalDateRange generateFollowUp(LocalDateRange range) {
        return LocalDateRange.of(
                range.getStart(),
                range.getEnd().plusDays(23));
    }

    @Test
    void EMPTY_RANGE_1_ofDateDate() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_HALF_OPEN_1_ofDateDate() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWO_DAY_PERIOD_1_ofPeriod() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 1),
                Period.ofDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWENTY_TWO_DAY_HALF_OPEN_1_ofDateDate() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWENTY_THREE_DAY_CLOSED_1_ofClosed() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWENTY_FOUR_DAY_PARSED_DATE_PERIOD_1_parseDatePeriod() {
        LocalDateRange source = LocalDateRange.parse("2021-01-01/P24D");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void THIRTY_ONE_DAY_DATE_DATE_1_parseDateDate() {
        LocalDateRange source = LocalDateRange.parse("2021-01-01/2021-02-01");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWENTY_EIGHT_DAY_PERIOD_DATE_1_parsePeriodDate() {
        LocalDateRange source = LocalDateRange.parse("P28D/2021-03-01");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NINETY_DAY_RANGE_1_ofDateDate() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 1, 15),
                LocalDate.of(2021, 4, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void THREE_HUNDRED_SIXTY_FIVE_DAY_PERIOD_1_ofPeriod() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2019, 1, 1),
                Period.ofDays(365));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_YEAR_THREE_HUNDRED_SIXTY_SIX_DAYS_1_ofDateDate() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2021, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_1_fromLocalDate() {
        LocalDateRange source = LocalDateRange.from(
                LocalDate.of(2022, 6, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_1_fromYear() {
        LocalDateRange source = LocalDateRange.from(Year.of(2021));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_MONTH_1_fromYearMonth() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2020, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_1_fromYearHalf() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2021, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_1_fromYearQuarter() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2021, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_WEEK_1_fromYearWeek() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2021, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MINIMUM_FINITE_END_1_ofDateDate() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN.plusDays(2),
                LocalDate.MIN.plusDays(3));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_SENTINEL_1_unboundedStart() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(
                LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_NEAR_INT_LIMIT_1_ofDateDate() {
        LocalDate start = LocalDate.of(1900, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                start,
                start.plusDays(Integer.MAX_VALUE - 25L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAXIMUM_ASSERTABLE_FINITE_LENGTH_1_ofDateDate() {
        LocalDate start = LocalDate.of(1900, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                start,
                start.plusDays(Integer.MAX_VALUE - 24L));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEAR_MAX_FOLLOWUP_FINITE_1_ofDateDate() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(25),
                LocalDate.MAX.minusDays(24));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FOLLOWUP_UNBOUNDED_END_SENTINEL_1_ofDateDate() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(24),
                LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = generateFollowUp(source);
        int followUpOutput = followUp.lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
