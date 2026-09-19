import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

    private static void runMetamorphicRelation(double source) {
        double sourceOutput =
            org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
            LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
            org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            double sourceOutput, double followUpOutput) {
        boolean sourceIsAntimeridian =
            Math.abs(Math.abs(sourceOutput) - 180.0d) <= 1e-9d;
        boolean followUpIsAntimeridian =
            Math.abs(Math.abs(followUpOutput) - 180.0d) <= 1e-9d;

        if (sourceIsAntimeridian && followUpIsAntimeridian) {
            return;
        }

        double difference = Math.abs(sourceOutput - followUpOutput);
        if (difference > 1e-9d) {
            throw new AssertionError(
                "Normalized longitude relation violated: sourceOutput="
                    + sourceOutput + ", followUpOutput=" + followUpOutput);
        }
    }

    @Test
    void IN_RANGE_ZERO_AND_SIGNED_ZERO_positiveZero() {
        runMetamorphicRelation(0.0d);
    }

    @Test
    void IN_RANGE_ZERO_AND_SIGNED_ZERO_negativeZero() {
        runMetamorphicRelation(-0.0d);
    }

    @Test
    void IN_RANGE_INTERIOR_POSITIVE_AND_NEGATIVE_negativeInterior() {
        runMetamorphicRelation(-45.5d);
    }

    @Test
    void IN_RANGE_INTERIOR_POSITIVE_AND_NEGATIVE_positiveInterior() {
        runMetamorphicRelation(45.5d);
    }

    @Test
    void ANTIMEDERIDIAN_ENDPOINTS_negativeEndpoint() {
        runMetamorphicRelation(-180.0d);
    }

    @Test
    void ANTIMEDERIDIAN_ENDPOINTS_positiveEndpoint() {
        runMetamorphicRelation(180.0d);
    }

    @Test
    void IMMEDIATE_INTERIOR_BOUNDARIES_negativeNearEndpoint() {
        runMetamorphicRelation(-179.999999d);
    }

    @Test
    void IMMEDIATE_INTERIOR_BOUNDARIES_negativeVeryNearEndpoint() {
        runMetamorphicRelation(-179.9999999999d);
    }

    @Test
    void IMMEDIATE_INTERIOR_BOUNDARIES_positiveNearEndpoint() {
        runMetamorphicRelation(179.999999d);
    }

    @Test
    void IMMEDIATE_INTERIOR_BOUNDARIES_positiveVeryNearEndpoint() {
        runMetamorphicRelation(179.9999999999d);
    }

    @Test
    void IMMEDIATE_EXTERIOR_BOUNDARIES_negativeExterior() {
        runMetamorphicRelation(-180.0000000001d);
    }

    @Test
    void IMMEDIATE_EXTERIOR_BOUNDARIES_positiveExterior() {
        runMetamorphicRelation(180.0000000001d);
    }

    @Test
    void POSITIVE_NONZERO_REMAINDER_smallPositiveWrap() {
        runMetamorphicRelation(181.0d);
    }

    @Test
    void POSITIVE_NONZERO_REMAINDER_fractionalPositiveWrap() {
        runMetamorphicRelation(359.5d);
    }

    @Test
    void POSITIVE_NONZERO_REMAINDER_multiplePositiveWraps() {
        runMetamorphicRelation(721.25d);
    }

    @Test
    void NEGATIVE_REMAINDER_BRANCH_smallNegativeWrap() {
        runMetamorphicRelation(-181.0d);
    }

    @Test
    void NEGATIVE_REMAINDER_BRANCH_fractionalNegativeWrap() {
        runMetamorphicRelation(-359.5d);
    }

    @Test
    void NEGATIVE_REMAINDER_BRANCH_multipleNegativeWraps() {
        runMetamorphicRelation(-721.25d);
    }

    @Test
    void POSITIVE_EXACT_REMAINDER_ZERO_firstPositiveMultiple() {
        runMetamorphicRelation(540.0d);
    }

    @Test
    void POSITIVE_EXACT_REMAINDER_ZERO_secondPositiveMultiple() {
        runMetamorphicRelation(900.0d);
    }

    @Test
    void POSITIVE_EXACT_REMAINDER_ZERO_thirdPositiveMultiple() {
        runMetamorphicRelation(1260.0d);
    }

    @Test
    void NONPOSITIVE_EXACT_REMAINDER_ZERO_firstNegativeMultiple() {
        runMetamorphicRelation(-540.0d);
    }

    @Test
    void NONPOSITIVE_EXACT_REMAINDER_ZERO_secondNegativeMultiple() {
        runMetamorphicRelation(-900.0d);
    }

    @Test
    void FULL_REVOLUTION_ZERO_REMAINDER_TRANSITIONS_positiveOneRevolution() {
        runMetamorphicRelation(360.0d);
    }

    @Test
    void FULL_REVOLUTION_ZERO_REMAINDER_TRANSITIONS_negativeOneRevolution() {
        runMetamorphicRelation(-360.0d);
    }

    @Test
    void FULL_REVOLUTION_ZERO_REMAINDER_TRANSITIONS_positiveTwoRevolutions() {
        runMetamorphicRelation(720.0d);
    }

    @Test
    void FULL_REVOLUTION_ZERO_REMAINDER_TRANSITIONS_negativeTwoRevolutions() {
        runMetamorphicRelation(-720.0d);
    }

    @Test
    void MULTIPLE_REVOLUTIONS_FRACTIONAL_positiveFraction() {
        runMetamorphicRelation(1080.25d);
    }

    @Test
    void MULTIPLE_REVOLUTIONS_FRACTIONAL_negativeFraction() {
        runMetamorphicRelation(-1080.25d);
    }

    @Test
    void MULTIPLE_REVOLUTIONS_FRACTIONAL_largerPositiveFraction() {
        runMetamorphicRelation(1440.75d);
    }

    @Test
    void VALUES_ADJACENT_TO_WRAP_LATTICE_belowPositiveLattice() {
        runMetamorphicRelation(359.9999999999d);
    }

    @Test
    void VALUES_ADJACENT_TO_WRAP_LATTICE_abovePositiveLattice() {
        runMetamorphicRelation(360.0000000001d);
    }

    @Test
    void VALUES_ADJACENT_TO_WRAP_LATTICE_aboveNegativeLattice() {
        runMetamorphicRelation(-359.9999999999d);
    }

    @Test
    void VALUES_ADJACENT_TO_WRAP_LATTICE_belowNegativeLattice() {
        runMetamorphicRelation(-360.0000000001d);
    }

    @Test
    void LARGE_FINITE_VALUES_oneTrillionPositive() {
        runMetamorphicRelation(1.0e12d);
    }

    @Test
    void LARGE_FINITE_VALUES_oneTrillionNegative() {
        runMetamorphicRelation(-1.0e12d);
    }

    @Test
    void LARGE_FINITE_VALUES_oneQuadrillionPositive() {
        runMetamorphicRelation(1.0e15d);
    }

    @Test
    void NEAR_MAXIMUM_FINITE_MAGNITUDE_maximumPositive() {
        runMetamorphicRelation(Double.MAX_VALUE);
    }

    @Test
    void NEAR_MAXIMUM_FINITE_MAGNITUDE_maximumNegative() {
        runMetamorphicRelation(-Double.MAX_VALUE);
    }

    @Test
    void SUBNORMAL_FINITE_INPUTS_minimumPositive() {
        runMetamorphicRelation(Double.MIN_VALUE);
    }

    @Test
    void SUBNORMAL_FINITE_INPUTS_minimumNegative() {
        runMetamorphicRelation(-Double.MIN_VALUE);
    }

    @Test
    void NONFINITE_SENTINEL_INPUTS_positiveInfinity() {
        runMetamorphicRelation(Double.POSITIVE_INFINITY);
    }

    @Test
    void NONFINITE_SENTINEL_INPUTS_negativeInfinity() {
        runMetamorphicRelation(Double.NEGATIVE_INFINITY);
    }

    @Test
    void NONFINITE_SENTINEL_INPUTS_nan() {
        runMetamorphicRelation(Double.NaN);
    }
}
