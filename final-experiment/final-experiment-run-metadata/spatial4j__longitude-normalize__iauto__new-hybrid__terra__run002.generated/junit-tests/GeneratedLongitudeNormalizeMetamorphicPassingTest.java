import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

    private void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
        double tolerance = 1e-9d;
        boolean sourceAtAntimeridian =
            Math.abs(Math.abs(sourceOutput) - 180.0d) <= tolerance;
        boolean followUpAtAntimeridian =
            Math.abs(Math.abs(followUpOutput) - 180.0d) <= tolerance;

        if (sourceAtAntimeridian && followUpAtAntimeridian) {
            return;
        }

        if (Math.abs(sourceOutput - followUpOutput) > tolerance) {
            throw new AssertionError(
                "Normalized source and follow-up longitudes do not represent the same location: "
                    + sourceOutput + " versus " + followUpOutput);
        }
    }

    @Test
    void IN_RANGE_INTERIOR_ordinaryFractionalLongitude() {
        double source = 12.5d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIGNED_ZERO_IN_RANGE_positiveZero() {
        double source = 0.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SIGNED_ZERO_IN_RANGE_negativeZero() {
        double source = -0.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INCLUSIVE_ANTIMERIDIAN_ENDPOINTS_negativeEndpoint() {
        double source = -180.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INCLUSIVE_ANTIMERIDIAN_ENDPOINTS_positiveEndpoint() {
        double source = 180.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JUST_BELOW_NEGATIVE_BOUNDARY_adjacentRepresentableValue() {
        double source = Math.nextDown(-180.0d);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JUST_ABOVE_POSITIVE_BOUNDARY_adjacentRepresentableValue() {
        double source = Math.nextUp(180.0d);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_OUTSIDE_NONEXACT_WRAP_negative181Degrees() {
        double source = -181.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_OUTSIDE_NONEXACT_WRAP_positive181Degrees() {
        double source = 181.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_OUTSIDE_WRAP_negativeFractionalLongitude() {
        double source = -180.5d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_OUTSIDE_WRAP_positiveFractionalLongitude() {
        double source = 180.5d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_EXACT_ANTIMERIDIAN_WRAP_negative540Degrees() {
        double source = -540.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_EXACT_ANTIMERIDIAN_WRAP_positive540Degrees() {
        double source = 540.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_EXACT_SHIFT_positiveTwoToThe52() {
        double source = 4503599627370496.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_EXACT_SHIFT_negativeTwoToThe52() {
        double source = -4503599627370496.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONFINITE_IEEE_SENTINELS_nan() {
        double source = Double.NaN;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONFINITE_IEEE_SENTINELS_positiveInfinity() {
        double source = Double.POSITIVE_INFINITY;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONFINITE_IEEE_SENTINELS_negativeInfinity() {
        double source = Double.NEGATIVE_INFINITY;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
