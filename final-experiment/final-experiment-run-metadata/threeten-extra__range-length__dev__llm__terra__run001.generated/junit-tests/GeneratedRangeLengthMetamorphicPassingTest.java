import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.junit.jupiter.api.Test;
import org.threeten.extra.Half;
import org.threeten.extra.LocalDateRange;
import org.threeten.extra.Quarter;
import org.threeten.extra.YearHalf;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;

public class GeneratedRangeLengthMetamorphicPassingTest {

    @Test
    void EMPTY_FINITE_INTERIOR_minPlusTwo() {
        LocalDateRange r = LocalDateRange.ofEmpty(LocalDate.MIN.plusDays(2));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void EMPTY_FINITE_INTERIOR_ordinaryDate() {
        LocalDateRange r = LocalDateRange.ofEmpty(LocalDate.of(2000, 1, 1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void EMPTY_FINITE_INTERIOR_maxMinus24() {
        LocalDateRange r = LocalDateRange.ofEmpty(LocalDate.MAX.minusDays(24));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void HALF_OPEN_ORDINARY_CALENDAR_LENGTHS_nonLeapFebruary() {
        LocalDateRange r = LocalDateRange.of(LocalDate.of(2019, 2, 28), LocalDate.of(2019, 3, 1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void HALF_OPEN_ORDINARY_CALENDAR_LENGTHS_leapFebruary() {
        LocalDateRange r = LocalDateRange.of(LocalDate.of(2020, 2, 28), LocalDate.of(2020, 3, 1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void HALF_OPEN_ORDINARY_CALENDAR_LENGTHS_yearBoundary() {
        LocalDateRange r = LocalDateRange.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 1, 1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void HALF_OPEN_ORDINARY_CALENDAR_LENGTHS_twentyThreeDays() {
        LocalDateRange r = LocalDateRange.of(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 24));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void HALF_OPEN_ORDINARY_CALENDAR_LENGTHS_monthSpan() {
        LocalDateRange r = LocalDateRange.of(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 2, 1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void CLOSED_RANGE_CONVERSION_sameDay() {
        LocalDateRange r = LocalDateRange.ofClosed(LocalDate.of(2021, 6, 15), LocalDate.of(2021, 6, 15));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void CLOSED_RANGE_CONVERSION_leapDayInclusive() {
        LocalDateRange r = LocalDateRange.ofClosed(LocalDate.of(2020, 2, 28), LocalDate.of(2020, 2, 29));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void CLOSED_RANGE_CONVERSION_yearBoundaryInclusive() {
        LocalDateRange r = LocalDateRange.ofClosed(LocalDate.of(2021, 12, 30), LocalDate.of(2022, 1, 1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void PERIOD_CONSTRUCTION_zero() {
        LocalDateRange r = LocalDateRange.of(LocalDate.of(2021, 1, 1), Period.ZERO);
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void PERIOD_CONSTRUCTION_oneDay() {
        LocalDateRange r = LocalDateRange.of(LocalDate.of(2021, 1, 1), Period.ofDays(1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void PERIOD_CONSTRUCTION_monthFromJanuaryEnd() {
        LocalDateRange r = LocalDateRange.of(LocalDate.of(2020, 1, 31), Period.ofMonths(1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void PERIOD_CONSTRUCTION_yearAcrossLeapDay() {
        LocalDateRange r = LocalDateRange.of(LocalDate.of(2019, 3, 1), Period.ofYears(1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void PARSED_DATE_AND_PERIOD_FORMS_dateDate() {
        LocalDateRange r = LocalDateRange.parse("2021-01-01/2021-01-02");
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void PARSED_DATE_AND_PERIOD_FORMS_dateDateLeap() {
        LocalDateRange r = LocalDateRange.parse("2020-02-28/2020-03-01");
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void PARSED_DATE_AND_PERIOD_FORMS_datePeriodDays() {
        LocalDateRange r = LocalDateRange.parse("2021-01-01/P23D");
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void PARSED_DATE_AND_PERIOD_FORMS_datePeriodMonth() {
        LocalDateRange r = LocalDateRange.parse("2020-01-31/P1M");
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void PARSED_DATE_AND_PERIOD_FORMS_periodDate() {
        LocalDateRange r = LocalDateRange.parse("P1M/2020-03-31");
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void TEMPORAL_FROM_SUPPORTED_TYPES_localDate() {
        LocalDateRange r = LocalDateRange.from(LocalDate.of(2021, 6, 15));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void TEMPORAL_FROM_SUPPORTED_TYPES_localDateTime() {
        LocalDateRange r = LocalDateRange.from(LocalDateTime.of(2021, 6, 15, 12, 0));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void TEMPORAL_FROM_SUPPORTED_TYPES_commonYear() {
        LocalDateRange r = LocalDateRange.from(java.time.Year.of(2021));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void TEMPORAL_FROM_SUPPORTED_TYPES_leapYear() {
        LocalDateRange r = LocalDateRange.from(java.time.Year.of(2020));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void TEMPORAL_FROM_SUPPORTED_TYPES_leapFebruaryMonth() {
        LocalDateRange r = LocalDateRange.from(java.time.YearMonth.of(2020, 2));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void TEMPORAL_FROM_SUPPORTED_TYPES_firstHalf() {
        LocalDateRange r = LocalDateRange.from(YearHalf.of(2020, Half.H1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void TEMPORAL_FROM_SUPPORTED_TYPES_firstQuarter() {
        LocalDateRange r = LocalDateRange.from(YearQuarter.of(2021, Quarter.Q1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void TEMPORAL_FROM_SUPPORTED_TYPES_week() {
        LocalDateRange r = LocalDateRange.from(YearWeek.of(2021, 1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void LOWER_ENDPOINT_VALIDITY_BOUNDARY_oneDay() {
        LocalDateRange r = LocalDateRange.of(LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(2));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void LOWER_ENDPOINT_VALIDITY_BOUNDARY_twentyThreeDays() {
        LocalDateRange r = LocalDateRange.of(LocalDate.MIN.plusDays(1), LocalDate.MIN.plusDays(24));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void UPPER_TRANSFORMABLE_ENDPOINTS_endMaxMinus24() {
        LocalDateRange r = LocalDateRange.of(LocalDate.MAX.minusDays(25), LocalDate.MAX.minusDays(24));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void UPPER_TRANSFORMABLE_ENDPOINTS_endMaxMinus23() {
        LocalDateRange r = LocalDateRange.of(LocalDate.MAX.minusDays(24), LocalDate.MAX.minusDays(23));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void UPPER_TRANSFORMABLE_ENDPOINTS_emptyAtMaxMinus23() {
        LocalDateRange r = LocalDateRange.ofEmpty(LocalDate.MAX.minusDays(23));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void INTEGER_MAX_LENGTH_THRESHOLDS_maxMinus24() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange r = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE - 24));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void INTEGER_MAX_LENGTH_THRESHOLDS_maxMinus23() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange r = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE - 23));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void INTEGER_MAX_LENGTH_THRESHOLDS_maxMinus22() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange r = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE - 22));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void INTEGER_MAX_LENGTH_THRESHOLDS_maxMinusOne() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange r = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE - 1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void INTEGER_MAX_LENGTH_THRESHOLDS_exactMax() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange r = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void INTEGER_MAX_LENGTH_THRESHOLDS_aboveMax() {
        LocalDate start = LocalDate.MIN.plusDays(1);
        LocalDateRange r = LocalDateRange.of(start, start.plusDays((long) Integer.MAX_VALUE + 1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void UNBOUNDED_START_WITH_TRANSFORMABLE_END_nearMinimum() {
        LocalDateRange r = LocalDateRange.ofUnboundedStart(LocalDate.MIN.plusDays(2));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void UNBOUNDED_START_WITH_TRANSFORMABLE_END_ordinaryEnd() {
        LocalDateRange r = LocalDateRange.ofUnboundedStart(LocalDate.of(2021, 1, 1));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }

    @Test
    void UNBOUNDED_START_WITH_TRANSFORMABLE_END_endMaxMinus23() {
        LocalDateRange r = LocalDateRange.ofUnboundedStart(LocalDate.MAX.minusDays(23));
        Object[] f = RangeLengthMetamorphicSpec.generateFollowUp(r);
        int s = r.lengthInDays(), u = ((LocalDateRange) f[0]).lengthInDays();
        RangeLengthMetamorphicSpec.assertRelation(s, u);
    }
}
