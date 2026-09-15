import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;

public class GeneratedRangeLengthMetamorphicPassingTest {

    @Test
    void EMPTY_FINITE_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 5, 10),
                        LocalDate.of(2024, 5, 10));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ONE_DAY_HALF_OPEN_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 5, 10),
                        LocalDate.of(2024, 5, 11));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_FINITE_HALF_OPEN_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2020, 1, 15),
                        LocalDate.of(2020, 3, 1));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEAP_DAY_CROSSING_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 2, 28),
                        LocalDate.of(2024, 3, 2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_SINGLE_DATE_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(2024, 6, 15),
                        LocalDate.of(2024, 6, 15));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CLOSED_MULTI_DAY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofClosed(
                        LocalDate.of(2023, 12, 29),
                        LocalDate.of(2024, 1, 2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_PERIOD_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2022, 8, 4),
                        Period.ZERO);

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PERIOD_CONSTRUCTED_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2021, 1, 31),
                        Period.ofMonths(1));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_DATE_DATE_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2024-04-01/2024-04-30");

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_DATE_PERIOD_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("2024-04-01/P23D");

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void PARSED_PERIOD_DATE_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.parse("P23D/2024-04-24");

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_LOCAL_DATE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(LocalDate.of(2024, 7, 8));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(Year.of(2024));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FROM_YEAR_MONTH_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.from(YearMonth.of(2024, 2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UNBOUNDED_START_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.of(2024, 1, 1));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EARLIEST_PERMITTED_UNBOUNDED_START_END_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.ofUnboundedStart(
                        LocalDate.MIN.plusDays(2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEAR_MINIMUM_FINITE_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.MIN,
                        LocalDate.MIN.plusDays(2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void END_AT_MAX_MINUS_24_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 1, 1),
                        LocalDate.MAX.minusDays(24));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FOLLOW_UP_BECOMES_UNBOUNDED_END_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2024, 1, 1),
                        LocalDate.MAX.minusDays(23));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAX_INT_RELATION_LENGTH_variation1() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDate end = start.plusDays((long) Integer.MAX_VALUE - 23);
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(start, end);

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TWENTY_THREE_DAY_BASELINE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2025, 1, 1),
                        LocalDate.of(2025, 1, 24));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void YEAR_BOUNDARY_RANGE_variation1() {
        org.threeten.extra.LocalDateRange source =
                org.threeten.extra.LocalDateRange.of(
                        LocalDate.of(2023, 12, 31),
                        LocalDate.of(2024, 1, 2));

        int sourceOutput = source.lengthInDays();
        Object[] followUpInputs = RangeLengthMetamorphicSpec.generateFollowUp(source);
        org.threeten.extra.LocalDateRange followUp =
                (org.threeten.extra.LocalDateRange) followUpInputs[0];
        int followUpOutput = followUp.lengthInDays();

        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
