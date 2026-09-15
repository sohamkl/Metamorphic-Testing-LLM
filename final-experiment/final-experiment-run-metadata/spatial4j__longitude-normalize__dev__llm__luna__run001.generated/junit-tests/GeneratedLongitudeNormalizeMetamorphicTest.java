import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicTest {

    private static void exercise(double source) {
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void INTERIOR_IN_RANGE_VALUES_1_negativeFraction() {
        double source = -90.5;
        exercise(source);
    }

    @Test
    public void INTERIOR_IN_RANGE_VALUES_2_positiveFraction() {
        double source = 0.25;
        exercise(source);
    }

    @Test
    public void INTERIOR_IN_RANGE_VALUES_3_nearPositiveEndpoint() {
        double source = 179.999;
        exercise(source);
    }

    @Test
    public void INTERIOR_IN_RANGE_VALUES_4_nearNegativeEndpoint() {
        double source = -179.5;
        exercise(source);
    }

    @Test
    public void CLOSED_RANGE_ENDPOINTS_1_negativeEndpoint() {
        double source = -180.0;
        exercise(source);
    }

    @Test
    public void CLOSED_RANGE_ENDPOINTS_2_positiveEndpoint() {
        double source = 180.0;
        exercise(source);
    }

    @Test
    public void SIGNED_ZERO_IN_RANGE_1_negativeZero() {
        double source = -0.0;
        exercise(source);
    }

    @Test
    public void SIGNED_ZERO_IN_RANGE_2_positiveZero() {
        double source = 0.0;
        exercise(source);
    }

    @Test
    public void JUST_OUTSIDE_POSITIVE_BOUND_1_fractionalDegree() {
        double source = 180.5;
        exercise(source);
    }

    @Test
    public void JUST_OUTSIDE_POSITIVE_BOUND_2_subDegreeOffset() {
        double source = 180.0 + 1e-12;
        exercise(source);
    }

    @Test
    public void JUST_OUTSIDE_POSITIVE_BOUND_3_smallOffset() {
        double source = 180.0 + 1e-6;
        exercise(source);
    }

    @Test
    public void JUST_OUTSIDE_POSITIVE_BOUND_4_nearNextDegree() {
        double source = 180.999999;
        exercise(source);
    }

    @Test
    public void JUST_OUTSIDE_NEGATIVE_BOUND_1_fractionalDegree() {
        double source = -180.5;
        exercise(source);
    }

    @Test
    public void JUST_OUTSIDE_NEGATIVE_BOUND_2_subDegreeOffset() {
        double source = -180.0 - 1e-12;
        exercise(source);
    }

    @Test
    public void JUST_OUTSIDE_NEGATIVE_BOUND_3_smallOffset() {
        double source = -180.0 - 1e-6;
        exercise(source);
    }

    @Test
    public void JUST_OUTSIDE_NEGATIVE_BOUND_4_nearNextDegree() {
        double source = -180.999999;
        exercise(source);
    }

    @Test
    public void POSITIVE_EXACT_REVOLUTION_SENTINELS_1_oneRevolution() {
        double source = 360.0;
        exercise(source);
    }

    @Test
    public void POSITIVE_EXACT_REVOLUTION_SENTINELS_2_twoRevolutions() {
        double source = 720.0;
        exercise(source);
    }

    @Test
    public void POSITIVE_EXACT_REVOLUTION_SENTINELS_3_threeRevolutions() {
        double source = 1080.0;
        exercise(source);
    }

    @Test
    public void NEGATIVE_EXACT_REVOLUTION_SENTINELS_1_oneNegativeRevolution() {
        double source = -360.0;
        exercise(source);
    }

    @Test
    public void NEGATIVE_EXACT_REVOLUTION_SENTINELS_2_twoNegativeRevolutions() {
        double source = -720.0;
        exercise(source);
    }

    @Test
    public void NEGATIVE_EXACT_REVOLUTION_SENTINELS_3_threeNegativeRevolutions() {
        double source = -1080.0;
        exercise(source);
    }

    @Test
    public void POSITIVE_NONMULTIPLE_REMAINDERS_1_smallRemainder() {
        double source = 181.0;
        exercise(source);
    }

    @Test
    public void POSITIVE_NONMULTIPLE_REMAINDERS_2_revolutionPlusFraction() {
        double source = 540.5;
        exercise(source);
    }

    @Test
    public void POSITIVE_NONMULTIPLE_REMAINDERS_3_trillionDegreeScale() {
        double source = 1_000_000_000_000.5;
        exercise(source);
    }

    @Test
    public void POSITIVE_NONMULTIPLE_REMAINDERS_4_largeFiniteMagnitude() {
        double source = 1_234_567_890_123.25;
        exercise(source);
    }

    @Test
    public void NEGATIVE_NONMULTIPLE_REMAINDERS_1_smallRemainder() {
        double source = -181.0;
        exercise(source);
    }

    @Test
    public void NEGATIVE_NONMULTIPLE_REMAINDERS_2_revolutionMinusFraction() {
        double source = -540.5;
        exercise(source);
    }

    @Test
    public void NEGATIVE_NONMULTIPLE_REMAINDERS_3_trillionDegreeScale() {
        double source = -1_000_000_000_000.5;
        exercise(source);
    }

    @Test
    public void NEGATIVE_NONMULTIPLE_REMAINDERS_4_largeFiniteMagnitude() {
        double source = -1_234_567_890_123.25;
        exercise(source);
    }

    @Test
    public void ANTIMeridian_REPRESENTATION_CROSSINGS_1_negativeEndpoint() {
        double source = -180.0;
        exercise(source);
    }

    @Test
    public void ANTIMeridian_REPRESENTATION_CROSSINGS_2_negativeThreeSixty() {
        double source = -540.0;
        exercise(source);
    }

    @Test
    public void ANTIMeridian_REPRESENTATION_CROSSINGS_3_positiveEndpoint() {
        double source = 180.0;
        exercise(source);
    }

    @Test
    public void ANTIMeridian_REPRESENTATION_CROSSINGS_4_positiveThreeSixty() {
        double source = 540.0;
        exercise(source);
    }

    @Test
    public void SUBDEGREE_BOUNDARY_OFFSETS_1_positiveTinyOffset() {
        double source = 180.0 + 1e-12;
        exercise(source);
    }

    @Test
    public void SUBDEGREE_BOUNDARY_OFFSETS_2_negativeTinyOffset() {
        double source = -180.0 - 1e-12;
        exercise(source);
    }

    @Test
    public void SUBDEGREE_BOUNDARY_OFFSETS_3_positiveRevolutionOffset() {
        double source = 360.0 + 1e-6;
        exercise(source);
    }

    @Test
    public void SUBDEGREE_BOUNDARY_OFFSETS_4_negativeRevolutionOffset() {
        double source = -360.0 - 1e-3;
        exercise(source);
    }

    @Test
    public void ULPS_NEAR_ANTIMERIDIAN_1_abovePositiveEndpoint() {
        double source = Math.nextUp(180.0);
        exercise(source);
    }

    @Test
    public void ULPS_NEAR_ANTIMERIDIAN_2_belowPositiveEndpoint() {
        double source = Math.nextDown(180.0);
        exercise(source);
    }

    @Test
    public void ULPS_NEAR_ANTIMERIDIAN_3_aboveNegativeEndpoint() {
        double source = Math.nextUp(-180.0);
        exercise(source);
    }

    @Test
    public void ULPS_NEAR_ANTIMERIDIAN_4_belowNegativeEndpoint() {
        double source = Math.nextDown(-180.0);
        exercise(source);
    }

    @Test
    public void LARGE_FINITE_MAGNITUDES_1_positiveTwoToFortyScale() {
        double source = Math.scalb(1.0, 40) + 0.25;
        exercise(source);
    }

    @Test
    public void LARGE_FINITE_MAGNITUDES_2_negativeTwoToFortyScale() {
        double source = -Math.scalb(1.0, 40) - 0.25;
        exercise(source);
    }

    @Test
    public void LARGE_FINITE_MAGNITUDES_3_positiveTwoToFiftyTwoScale() {
        double source = Math.scalb(1.0, 52) + 1024.0;
        exercise(source);
    }

    @Test
    public void LARGE_FINITE_MAGNITUDES_4_negativeTwoToFiftyThreeScale() {
        double source = -Math.scalb(1.0, 53);
        exercise(source);
    }

    @Test
    public void POSITIVE_FOLLOWUP_OVERFLOW_1_maximumFiniteSource() {
        double source = Double.MAX_VALUE;
        exercise(source);
    }
}
