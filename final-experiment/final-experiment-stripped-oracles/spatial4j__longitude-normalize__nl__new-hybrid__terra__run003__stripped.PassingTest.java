import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

    private static double generateFollowUp(double source) {
        return source + 360.0;
    }

    private static void assertMetamorphicRelationFor(double source) {
        double followUp = generateFollowUp(source);
        assertTrue(Double.isFinite(source));
        assertTrue(Double.isFinite(followUp));
        double sourceOutput = DistanceUtils.normLonDEG(source);
        double followUpOutput = DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
        double wrappedDifference = DistanceUtils.normLonDEG(sourceOutput - followUpOutput);
        assertEquals(0.0, wrappedDifference, 1.0e-9);
    }

    @Test
    void NEGATIVE_ZERO_IDENTITY_negativeSignedZero() {
        double source = -0.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_ZERO_IDENTITY_positiveSignedZero() {
        double source = 0.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_SUBNORMAL_IN_RANGE_smallestPositiveDouble() {
        double source = Double.MIN_VALUE;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_SUBNORMAL_IN_RANGE_smallestNegativeDouble() {
        double source = -Double.MIN_VALUE;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_INTERIOR_IDENTITY_negativeFraction() {
        double source = -12.5;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_INTERIOR_IDENTITY_positiveFraction() {
        double source = 12.5;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LOWER_INCLUSIVE_ENDPOINT_negativeAntimeridian() {
        double source = -180.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void UPPER_INCLUSIVE_ENDPOINT_positiveAntimeridian() {
        double source = 180.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_BELOW_LOWER_ENDPOINT_adjacentOutsideRange() {
        double source = Math.nextDown(-180.0);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_ABOVE_UPPER_ENDPOINT_adjacentOutsideRange() {
        double source = Math.nextUp(180.0);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_ONE_TURN_negativeFullCycle() {
        double source = -360.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_ONE_TURN_positiveFullCycle() {
        double source = 360.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_BELOW_POSITIVE_ONE_TURN_adjacentBelowCycle() {
        double source = Math.nextDown(360.0);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_ABOVE_POSITIVE_ONE_TURN_adjacentAboveCycle() {
        double source = Math.nextUp(360.0);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_ANTIMERIDIAN_MULTI_TURN_negativeCycleSentinel() {
        double source = -540.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_ANTIMERIDIAN_MULTI_TURN_positiveCycleSentinel() {
        double source = 540.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_BELOW_POSITIVE_ANTIMERIDIAN_CYCLE_adjacentBelowSentinel() {
        double source = Math.nextDown(540.0);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_ABOVE_POSITIVE_ANTIMERIDIAN_CYCLE_adjacentAboveSentinel() {
        double source = Math.nextUp(540.0);
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_MULTI_TURN_FRACTIONAL_RESIDUE_positiveFractionalTurns() {
        double source = 1234.5;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_MULTI_TURN_FRACTIONAL_RESIDUE_negativeFractionalTurns() {
        double source = -1234.5;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_EXACT_ADDITION_AT_2_POW_52_positiveLargeExactIncrement() {
        double source = 4503599627370496.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_EXACT_ADDITION_AT_2_POW_52_negativeLargeExactIncrement() {
        double source = -4503599627370496.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_TWO_UNIT_ULP_AT_2_POW_53_positiveTwoUnitSpacing() {
        double source = 9007199254740992.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_TWO_UNIT_ULP_AT_2_POW_53_negativeTwoUnitSpacing() {
        double source = -9007199254740992.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MAX_FINITE_POSITIVE_NOOP_ADDITION_largestFinitePositive() {
        double source = Double.MAX_VALUE;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void MAX_FINITE_NEGATIVE_NOOP_ADDITION_largestFiniteNegativeMagnitude() {
        double source = -Double.MAX_VALUE;
        assertMetamorphicRelationFor(source);
    }
}
