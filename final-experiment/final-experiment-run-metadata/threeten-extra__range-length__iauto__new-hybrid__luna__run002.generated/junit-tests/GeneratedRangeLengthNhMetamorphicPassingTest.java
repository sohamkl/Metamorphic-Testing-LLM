import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratedRangeLengthNhMetamorphicPassingTest {

    private static void assertMetamorphicRelation(int sourceOutput, int followUpOutput) {
        if (sourceOutput == Integer.MAX_VALUE || followUpOutput == Integer.MAX_VALUE) {
            return;
        }
        assertEquals(23, followUpOutput - sourceOutput);
    }

    @Test
    void emptyRangeViaOf_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 15), LocalDate.of(2020, 1, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void emptyRangeViaOfEmpty_1() {
        LocalDateRange source = LocalDateRange.ofEmpty(LocalDate.of(2020, 2, 29));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void oneDayHalfOpenRange_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 3, 10), LocalDate.of(2021, 3, 11));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void shortSpansBelowExtraDays_1() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void shortSpansBelowExtraDays_2() {
        LocalDate start = LocalDate.of(2024, 2, 28);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(22));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void shortSpansBelowExtraDays_3() {
        LocalDate start = LocalDate.of(2023, 12, 15);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ordinaryMultiDayRange_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2024, 4, 1), LocalDate.of(2024, 5, 15));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void leapDayCrossingRange_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 2, 28), LocalDate.of(2020, 3, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void monthAndYearBoundaryRanges_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 1, 31), LocalDate.of(2021, 2, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void monthAndYearBoundaryRanges_2() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2021, 12, 31), LocalDate.of(2022, 1, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void closedOneDateRange_1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2022, 6, 30), LocalDate.of(2022, 6, 30));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void closedLeapAndYearRanges_1() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2020, 2, 28), LocalDate.of(2020, 3, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void closedLeapAndYearRanges_2() {
        LocalDateRange source = LocalDateRange.ofClosed(
                LocalDate.of(2021, 12, 30), LocalDate.of(2022, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void zeroPeriodRange_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 5, 17), Period.ZERO);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void periodDaysRange_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2023, 5, 17), Period.ofDays(10));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void periodMonthsAndYearsRange_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2020, 1, 31), Period.ofMonths(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void periodMonthsAndYearsRange_2() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(2019, 1, 1), Period.ofYears(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void fromLocalDate_1() {
        LocalDateRange source = LocalDateRange.from(LocalDate.of(2024, 7, 4));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void fromYear_1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2023));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void fromLeapYear_1() {
        LocalDateRange source = LocalDateRange.from(Year.of(2020));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void fromYearMonth_1() {
        LocalDateRange source = LocalDateRange.from(YearMonth.of(2024, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void fromYearHalf_1() {
        LocalDateRange source = LocalDateRange.from(YearHalf.of(2023, 2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void fromYearQuarter_1() {
        LocalDateRange source = LocalDateRange.from(YearQuarter.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void fromYearWeek_1() {
        LocalDateRange source = LocalDateRange.from(YearWeek.of(2024, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void parseDateDate_1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-01/2024-01-10");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void parseDatePeriod_1() {
        LocalDateRange source = LocalDateRange.parse("2024-01-01/P10D");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void parsePeriodDate_1() {
        LocalDateRange source = LocalDateRange.parse("P10D/2024-01-11");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void unboundedStartSentinel_1() {
        LocalDateRange source = LocalDateRange.ofUnboundedStart(LocalDate.of(2024, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void minimumStartPlusOne_1() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange source = LocalDateRange.of(start, start.plusDays(1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void minimumStartWithNearBoundaryEnd_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MIN, LocalDate.MIN.plusDays(2));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void maximumSideFiniteStart_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.MAX.minusDays(25), LocalDate.MAX.minusDays(23));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void endPlus23ReachesMax_1() {
        LocalDate end = LocalDate.MAX.minusDays(23);
        LocalDateRange source = LocalDateRange.of(end.minusDays(2), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void intMaxMinus23Length_1() {
        LocalDate end = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays((long) Integer.MAX_VALUE - 23L), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void intMaxMinus22Length_1() {
        LocalDate end = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays((long) Integer.MAX_VALUE - 22L), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void intMaxMinus1Length_1() {
        LocalDate end = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays((long) Integer.MAX_VALUE - 1L), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void finiteLengthEqualsIntMax_1() {
        LocalDate end = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays((long) Integer.MAX_VALUE), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void lengthGreaterThanIntMax_1() {
        LocalDate end = LocalDate.of(2000, 1, 1);
        LocalDateRange source = LocalDateRange.of(
                end.minusDays((long) Integer.MAX_VALUE + 1000L), end);
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void finiteRangeFromBoundarySafeEnd_1() {
        LocalDateRange source = LocalDateRange.of(
                LocalDate.of(1900, 1, 1), LocalDate.of(1901, 1, 1));
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void finiteRangeFromBoundarySafeEnd_2() {
        LocalDateRange source = LocalDateRange.parse("2000-01-01/2025-01-01");
        int sourceOutput = source.lengthInDays();
        LocalDateRange followUp = (LocalDateRange) RangeLengthMetamorphicSpec.generateFollowUp(source)[0];
        int followUpOutput = followUp.lengthInDays();
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
