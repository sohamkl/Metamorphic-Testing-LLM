import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private LocalDateRange generateFollowUp(LocalDateRange source) {
        LocalDate extendedEnd = source.getEnd().plusDays(23);
        return LocalDateRange.of(source.getStart(), extendedEnd);
    }

    @Test
    void HALF_OPEN_EMPTY_ORDINARY_DATE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HALF_OPEN_ONE_DAY_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 15);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(1));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HALF_OPEN_LEAP_DAY_CROSSING_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 28), LocalDate.of(2024, 3, 1));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HALF_OPEN_YEAR_BOUNDARY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 2));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_ZERO_EMPTY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 6, 15), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_MONTH_ACROSS_LEAP_FEBRUARY_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 31), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_MULTI_DATE_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 6, 10), LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OF_EMPTY_FACTORY_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-06-10/2024-06-15");
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_DATE_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-02-28/P3D");
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSE_PERIOD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("P3D/2024-03-02");
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_TIME_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDateTime.of(2024, 6, 15, 13, 45));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2024));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LEAP_FEBRUARY_YEAR_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_variation1() {
        LocalDateRange source = LocalDateRange.from(YearHalf.parse("2024-H1"));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.parse("2024-Q1"));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_ISO_WEEK_ACROSS_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(YearWeek.parse("2020-W53"));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_NEAR_MINIMUM_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_ORDINARY_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 6, 15));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_HIGHEST_FOLLOWUP_SAFE_END_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_RANGE_ADJACENT_TO_LOCALDATE_MIN_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HIGHEST_SAFE_END_ONE_DAY_RANGE_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.of(end.minusDays(1), end);
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_INTEGER_MAX_MINUS_24_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE - 24L));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_INTEGER_MAX_MINUS_23_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE - 23L));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_INTEGER_MAX_MINUS_22_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE - 22L));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_INTEGER_MAX_EXACT_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LENGTH_GREATER_THAN_INTEGER_MAX_variation1() {
        LocalDate start = LocalDate.of(0, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE + 1L));
        int sourceOutput = source.lengthInDays();
        int followUpOutput = generateFollowUp(source).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
