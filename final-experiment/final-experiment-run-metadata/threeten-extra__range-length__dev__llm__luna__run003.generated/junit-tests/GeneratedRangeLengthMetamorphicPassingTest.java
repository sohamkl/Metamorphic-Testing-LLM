import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

public class GeneratedRangeLengthMetamorphicPassingTest {

    @Test
    public void testTypicalHalfOpenRange_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 1, 10),
                        LocalDate.of(2024, 2, 10));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testEmptyRangeStandardLocation_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(LocalDate.of(2024, 6, 15));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testOneDayHalfOpenRange_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 6, 15),
                        LocalDate.of(2024, 6, 16));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLeapDayCrossingRange_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 2, 28),
                        LocalDate.of(2024, 3, 2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testYearBoundaryRange_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2023, 12, 31),
                        LocalDate.of(2024, 1, 2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testZeroPeriodConstruction_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 6, 15),
                        Period.ZERO);

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testMonthPeriodConstruction_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 1, 31),
                        Period.ofMonths(1));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testClosedSingleDate_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(2024, 6, 15),
                        LocalDate.of(2024, 6, 15));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testFromLocalDate_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(LocalDate.of(2024, 6, 15));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testFromYear_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(Year.of(2024));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testFromYearMonth_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearMonth.of(2024, 2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testParsedDateDate_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2024-04-01/2024-04-11");

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testParsedDatePeriod_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2024-04-01/P23D");

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testParsedPeriodDate_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("P23D/2024-04-24");

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testUnboundedStartSentinel_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.of(2024, 6, 15));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testMinAdjacentFiniteRange_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MIN.plusDays(1),
                        LocalDate.MIN.plusDays(2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testUnboundedStartNearMin_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.MIN.plusDays(2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testFiniteRangeEndMaxMinus24_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MAX.minusDays(25),
                        LocalDate.MAX.minusDays(24));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testFollowUpReachesMaxEnd_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MAX.minusDays(46),
                        LocalDate.MAX.minusDays(23));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testClosedRangeNearMax_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.MAX.minusDays(25),
                        LocalDate.MAX.minusDays(24));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLargeFiniteRange_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(1900, 1, 1),
                        LocalDate.of(4637, 11, 25));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testEmptyRangeAfterMinBoundary_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(
                        LocalDate.MIN.plusDays(2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testEmptyRangeBeforeMaxBoundary_1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofEmpty(
                        LocalDate.MAX.minusDays(23));

        int sourceOutput = source.lengthInDays();
        Object[] followUpArguments = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpArguments[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
