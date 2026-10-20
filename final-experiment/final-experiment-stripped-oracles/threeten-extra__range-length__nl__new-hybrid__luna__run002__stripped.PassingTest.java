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

    private static final int END_SHIFT_DAYS = 23;

    private static LocalDateRange generateFollowUp(LocalDateRange source) {
        return LocalDateRange.of(source.getStart(), source.getEnd().plusDays(END_SHIFT_DAYS));
    }

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        Assertions.assertEquals(sourceOutput + END_SHIFT_DAYS, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(LocalDateRange source) {
        Assertions.assertNotEquals(LocalDate.MAX, source.getEnd());
        LocalDateRange followUp = generateFollowUp(source);
        Assertions.assertEquals(source.getStart(), followUp.getStart());
        Assertions.assertEquals(source.getEnd().plusDays(END_SHIFT_DAYS), followUp.getEnd());
        assertMetamorphicRelation(source.lengthInDays(), followUp.lengthInDays());
    }

    @Test
    public void EMPTY_RANGE_STANDARD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 15));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ONE_DAY_HALF_OPEN_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ORDINARY_HALF_OPEN_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 3, 10), LocalDate.of(2023, 4, 5));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LEAP_DAY_CROSSING_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2024, 2, 27), Period.ofDays(4));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ZERO_PERIOD_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2022, 8, 10), Period.ZERO);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_PERIOD_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2021, 5, 1), Period.ofDays(40));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void MONTH_BASED_PERIOD_RANGE_variation1() {
        LocalDateRange source = LocalDateRange.of(LocalDate.of(2023, 1, 31), Period.ofMonths(1));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void CLOSED_RANGE_STANDARD_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 4, 10), LocalDate.of(2024, 4, 20));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void CLOSED_RANGE_LEAP_END_variation1() {
        LocalDateRange source = LocalDateRange.ofClosed(LocalDate.of(2024, 2, 27), LocalDate.of(2024, 2, 29));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FROM_LOCAL_DATE_variation1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 6, 1));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FROM_YEAR_variation1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FROM_YEAR_MONTH_variation1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FROM_YEAR_HALF_variation1() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2023, 1));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FROM_YEAR_QUARTER_variation1() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, 1));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FROM_YEAR_WEEK_variation1() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2024, 1));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void PARSED_DATE_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-05-10/2024-05-20");
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void PARSED_DATE_PERIOD_variation1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-15/P40D");
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void PARSED_PERIOD_DATE_variation1() {
        LocalDateRange source = LocalDateRange.parse("P40D/2024-03-01");
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void UPPER_FINITE_END_SHIFT_BOUNDARY_variation1() {
        LocalDate start = LocalDate.MAX.minusDays(30);
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.of(start, end);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void UPPER_START_NEAR_FORBIDDEN_BOUNDARY_variation1() {
        LocalDate start = LocalDate.MAX.minusDays(27);
        LocalDate end = LocalDate.MAX.minusDays(24);
        LocalDateRange source = LocalDateRange.of(start, end);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void EXCLUDED_UNBOUNDED_END_SENTINEL_variation1() {
        LocalDateRange source = LocalDateRange.ofUnboundedEnd(LocalDate.of(2024, 1, 1));
    }
}
