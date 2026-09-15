import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;

import java.time.LocalDate;

public class GeneratedRangeLengthMetamorphicPassingTest {

    private static LocalDateRange endingWithLength(LocalDate endExclusive, long lengthInDays) {
        return LocalDateRange.of(endExclusive.minusDays(lengthInDays), endExclusive);
    }

    private static void exercise(LocalDateRange source) {
        int sourceOutput =
                org.threeten.extra.LocalDateRange.class.cast(source).lengthInDays();
        Object[] followUpArguments =
                RangeLengthMetamorphicSpec.generateFollowUp(source);
        LocalDateRange followUp =
                (LocalDateRange) followUpArguments[0];
        int followUpOutput =
                org.threeten.extra.LocalDateRange.class.cast(followUp).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_ORDINARY_variation1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2000, 1, 1));
        exercise(source);
    }

    @Test
    public void EMPTY_NEAR_MINIMUM_variation1() {
        LocalDate date = LocalDate.MIN.plusDays(2);
        LocalDateRange source = LocalDateRange.ofEmpty(date);
        exercise(source);
    }

    @Test
    public void ONE_DAY_ORDINARY_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2001, 3, 10), LocalDate.of(2001, 3, 11));
        exercise(source);
    }

    @Test
    public void ONE_DAY_ORDINARY_variation2() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2012, 8, 5), LocalDate.of(2012, 8, 6));
        exercise(source);
    }

    @Test
    public void LENGTH_EXTRA_MINUS_ONE_variation1() {
        LocalDate start = LocalDate.of(2005, 4, 3);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(22));
        exercise(source);
    }

    @Test
    public void LENGTH_EXTRA_MINUS_ONE_variation2() {
        LocalDate start = LocalDate.of(2018, 9, 7);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(22));
        exercise(source);
    }

    @Test
    public void LENGTH_EQUALS_EXTRA_variation1() {
        LocalDate start = LocalDate.of(1995, 5, 12);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));
        exercise(source);
    }

    @Test
    public void LENGTH_EQUALS_EXTRA_variation2() {
        LocalDate start = LocalDate.of(2022, 10, 2);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));
        exercise(source);
    }

    @Test
    public void LENGTH_EXTRA_PLUS_ONE_variation1() {
        LocalDate start = LocalDate.of(1986, 6, 4);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(24));
        exercise(source);
    }

    @Test
    public void LENGTH_EXTRA_PLUS_ONE_variation2() {
        LocalDate start = LocalDate.of(2031, 2, 3);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(24));
        exercise(source);
    }

    @Test
    public void MEDIUM_BOUNDED_RANGES_variation1() {
        LocalDate start = LocalDate.of(2000, 5, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(100));
        exercise(source);
    }

    @Test
    public void MEDIUM_BOUNDED_RANGES_variation2() {
        LocalDate start = LocalDate.of(2010, 1, 15);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(1000));
        exercise(source);
    }

    @Test
    public void THIRTY_ONE_DAY_MONTH_CROSSING_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2021, 1, 5), LocalDate.of(2021, 1, 20));
        exercise(source);
    }

    @Test
    public void THIRTY_ONE_DAY_MONTH_CROSSING_variation2() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2017, 7, 2), LocalDate.of(2017, 7, 20));
        exercise(source);
    }

    @Test
    public void THIRTY_DAY_MONTH_CROSSING_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 20));
        exercise(source);
    }

    @Test
    public void THIRTY_DAY_MONTH_CROSSING_variation2() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2018, 9, 4), LocalDate.of(2018, 9, 20));
        exercise(source);
    }

    @Test
    public void COMMON_FEBRUARY_CROSSING_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2019, 1, 25), LocalDate.of(2019, 2, 15));
        exercise(source);
    }

    @Test
    public void COMMON_FEBRUARY_CROSSING_variation2() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 20));
        exercise(source);
    }

    @Test
    public void LEAP_FEBRUARY_CROSSING_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2020, 1, 24), LocalDate.of(2020, 2, 15));
        exercise(source);
    }

    @Test
    public void LEAP_FEBRUARY_CROSSING_variation2() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2000, 2, 1), LocalDate.of(2000, 2, 20));
        exercise(source);
    }

    @Test
    public void END_ON_COMMON_FEBRUARY_LAST_DAY_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2019, 2, 1), LocalDate.of(2019, 2, 28));
        exercise(source);
    }

    @Test
    public void END_ON_LEAP_DAY_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2020, 2, 1), LocalDate.of(2020, 2, 29));
        exercise(source);
    }

    @Test
    public void YEAR_BOUNDARY_EXTENSION_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2018, 11, 20), LocalDate.of(2018, 12, 15));
        exercise(source);
    }

    @Test
    public void YEAR_BOUNDARY_EXTENSION_variation2() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 31));
        exercise(source);
    }

    @Test
    public void FULL_COMMON_AND_LEAP_YEARS_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2019, 1, 1), LocalDate.of(2020, 1, 1));
        exercise(source);
    }

    @Test
    public void FULL_COMMON_AND_LEAP_YEARS_variation2() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1));
        exercise(source);
    }

    @Test
    public void UNIX_EPOCH_CROSSING_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(1969, 12, 20), LocalDate.of(1970, 1, 10));
        exercise(source);
    }

    @Test
    public void UNIX_EPOCH_CROSSING_variation2() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(1968, 6, 1), LocalDate.of(1971, 7, 1));
        exercise(source);
    }

    @Test
    public void NEGATIVE_PROLEPTIC_YEAR_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(-44, 2, 1), LocalDate.of(-44, 10, 20));
        exercise(source);
    }

    @Test
    public void PROLEPTIC_YEAR_ZERO_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(-1, 12, 20), LocalDate.of(0, 1, 20));
        exercise(source);
    }

    @Test
    public void EARLIEST_BOUNDED_START_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_MINIMUM_END_variation1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_ORDINARY_END_variation1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.of(1900, 6, 15));
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_ORDINARY_END_variation2() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.of(2500, 11, 3));
        exercise(source);
    }

    @Test
    public void UNBOUNDED_START_LATEST_SAFE_END_variation1() {
        LocalDateRange source =
                LocalDateRange.ofUnboundedStart(LocalDate.MAX.minusDays(23));
        exercise(source);
    }

    @Test
    public void EMPTY_AT_LATEST_SAFE_DATE_variation1() {
        LocalDateRange source =
                LocalDateRange.ofEmpty(LocalDate.MAX.minusDays(23));
        exercise(source);
    }

    @Test
    public void ONE_DAY_TO_LATEST_SAFE_END_variation1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.MAX.minusDays(24),
                        LocalDate.MAX.minusDays(23));
        exercise(source);
    }

    @Test
    public void MEDIUM_RANGE_TO_LATEST_SAFE_END_variation1() {
        LocalDateRange source =
                LocalDateRange.of(
                        LocalDate.MAX.minusDays(100),
                        LocalDate.MAX.minusDays(23));
        exercise(source);
    }

    @Test
    public void LENGTH_INT_MAX_MINUS_24_variation1() {
        LocalDate end = LocalDate.of(3000, 1, 1);
        LocalDateRange source =
                endingWithLength(end, (long) Integer.MAX_VALUE - 24L);
        exercise(source);
    }

    @Test
    public void LENGTH_INT_MAX_MINUS_23_variation1() {
        LocalDate end = LocalDate.of(4000, 2, 1);
        LocalDateRange source =
                endingWithLength(end, (long) Integer.MAX_VALUE - 23L);
        exercise(source);
    }

    @Test
    public void LENGTH_INT_MAX_MINUS_22_variation1() {
        LocalDate end = LocalDate.of(5000, 3, 1);
        LocalDateRange source =
                endingWithLength(end, (long) Integer.MAX_VALUE - 22L);
        exercise(source);
    }

    @Test
    public void LENGTH_INT_MAX_MINUS_1_variation1() {
        LocalDate end = LocalDate.of(6000, 4, 1);
        LocalDateRange source =
                endingWithLength(end, (long) Integer.MAX_VALUE - 1L);
        exercise(source);
    }

    @Test
    public void LENGTH_EQUALS_INT_MAX_variation1() {
        LocalDate end = LocalDate.of(7000, 5, 1);
        LocalDateRange source =
                endingWithLength(end, (long) Integer.MAX_VALUE);
        exercise(source);
    }

    @Test
    public void LENGTH_INT_MAX_PLUS_ONE_variation1() {
        LocalDate end = LocalDate.of(8000, 6, 1);
        LocalDateRange source =
                endingWithLength(end, (long) Integer.MAX_VALUE + 1L);
        exercise(source);
    }

    @Test
    public void VERY_LARGE_BOUNDED_LENGTHS_variation1() {
        LocalDate end = LocalDate.of(100_000_000, 1, 1);
        LocalDateRange source =
                endingWithLength(end, 10L * (long) Integer.MAX_VALUE);
        exercise(source);
    }

    @Test
    public void VERY_LARGE_BOUNDED_LENGTHS_variation2() {
        LocalDate end = LocalDate.of(900_000_000, 1, 1);
        LocalDateRange source =
                endingWithLength(end, 100L * (long) Integer.MAX_VALUE);
        exercise(source);
    }

    @Test
    public void WEEK_ALIGNED_RANGES_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 8));
        exercise(source);
    }

    @Test
    public void WEEK_ALIGNED_RANGES_variation2() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2019, 7, 1), LocalDate.of(2019, 7, 8));
        exercise(source);
    }

    @Test
    public void QUARTER_AND_HALF_YEAR_RANGES_variation1() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 4, 1));
        exercise(source);
    }

    @Test
    public void QUARTER_AND_HALF_YEAR_RANGES_variation2() {
        LocalDateRange source =
                LocalDateRange.of(LocalDate.of(2020, 7, 1), LocalDate.of(2021, 1, 1));
        exercise(source);
    }
}
