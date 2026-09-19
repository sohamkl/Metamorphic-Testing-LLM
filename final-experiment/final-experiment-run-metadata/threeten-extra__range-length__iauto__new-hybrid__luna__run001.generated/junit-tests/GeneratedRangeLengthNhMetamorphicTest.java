import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthNhMetamorphicTest {

    @Test
    void EMPTY_RANGE_VIA_OF_variation1() {
        LocalDate date = LocalDate.of(2024, 6, 10);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(date, date);
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_RANGE_VIA_OF_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 10);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, start.plusDays(1));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_MULTI_DAY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 2, 1),
                        LocalDate.of(2024, 2, 29));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_CROSSING_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 2, 28),
                        LocalDate.of(2024, 3, 2));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MONTH_BOUNDARY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2023, 1, 31),
                        LocalDate.of(2023, 3, 1));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_BOUNDARY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2023, 12, 31),
                        LocalDate.of(2024, 1, 2));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_PERIOD_RANGE_variation1() {
        LocalDate start = LocalDate.of(2024, 6, 10);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, Period.ZERO);
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_MONTH_RANGE_variation1() {
        LocalDate start = LocalDate.of(2024, 1, 15);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, Period.ofMonths(1));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLE_DAY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(2024, 6, 10),
                        LocalDate.of(2024, 6, 10));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_MULTI_DAY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(2024, 2, 27),
                        LocalDate.of(2024, 3, 1));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_DATE_DATE_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2024-04-01/2024-04-10");
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_DATE_PERIOD_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2024-04-01/P9D");
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_PERIOD_DATE_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("P9D/2024-04-10");
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(LocalDate.of(2024, 5, 20));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(Year.of(2024));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_MONTH_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_HALF_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearHalf.of(2024, 1));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_QUARTER_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearQuarter.of(2024, 1));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_WEEK_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearWeek.of(2024, 10));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_SENTINEL_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.of(2024, 1, 1));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOCAL_DATE_MIN_FINITE_NEAR_BOUNDARY_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MIN,
                        LocalDate.MIN.plusDays(2));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FOLLOW_UP_REACHES_UNBOUNDED_END_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 1, 1),
                        LocalDate.MAX.minusDays(23));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_BOUNDED_RANGE_WITH_INT_MAX_LENGTH_variation1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        start,
                        start.plusDays((long) Integer.MAX_VALUE));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_BOUNDED_RANGE_ABOVE_INT_MAX_LENGTH_variation1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        start,
                        start.plusDays((long) Integer.MAX_VALUE + 1000L));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_RANGE_NEAR_FOLLOW_UP_LIMIT_variation1() {
        LocalDate end = LocalDate.MAX.minusDays(24);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        end.minusDays(100), end);
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_RANGE_FROM_PARSE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2024-06-10/2024-06-10");
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FINITE_RANGE_FROM_LOCAL_DATE_TIME_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(
                        LocalDateTime.of(2024, 7, 4, 15, 30));
        int sourceOutput = org.threeten.extra.LocalDateRange.lengthInDays(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange)
                        RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = org.threeten.extra.LocalDateRange.lengthInDays(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            int sourceOutput,
            int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE
                || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        if (followUpOutput - sourceOutput != 23) {
            throw new AssertionError(
                    "The follow-up length must be exactly 23 days greater than "
                            + "the source length");
        }
    }
}
