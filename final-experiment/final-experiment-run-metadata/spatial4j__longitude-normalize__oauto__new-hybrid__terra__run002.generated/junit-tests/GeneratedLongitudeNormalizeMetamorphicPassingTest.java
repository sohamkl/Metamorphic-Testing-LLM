import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

    private static double generateFollowUp(double sourceLongitude) {
        return sourceLongitude + 360.0;
    }

    @Test
    void NEGATIVE_ANTIMERIDIAN_IN_RANGE_inclusiveLowerBoundary() {
        double sourceLongitude = -180.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_ANTIMERIDIAN_IN_RANGE_inclusiveUpperBoundary() {
        double sourceLongitude = 180.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JUST_INSIDE_NEGATIVE_BOUNDARY_nextUp() {
        double sourceLongitude = Math.nextUp(-180.0);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JUST_INSIDE_POSITIVE_BOUNDARY_nextDown() {
        double sourceLongitude = Math.nextDown(180.0);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_ZERO_FAST_RETURN_positiveZero() {
        double sourceLongitude = 0.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_ZERO_FAST_RETURN_negativeZero() {
        double sourceLongitude = -0.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUBNORMAL_VALUES_IN_RANGE_smallestPositive() {
        double sourceLongitude = Double.MIN_VALUE;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SUBNORMAL_VALUES_IN_RANGE_smallestNegative() {
        double sourceLongitude = -Double.MIN_VALUE;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_FRACTIONAL_IN_RANGE_VALUES_positiveFraction() {
        double sourceLongitude = 12.5;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_FRACTIONAL_IN_RANGE_VALUES_negativeFraction() {
        double sourceLongitude = -12.5;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ANTIMERIDIAN_WITHIN_RELATION_TOLERANCE_positiveSide() {
        double sourceLongitude = 180.0 - 5.0e-10;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ANTIMERIDIAN_WITHIN_RELATION_TOLERANCE_negativeSide() {
        double sourceLongitude = -180.0 + 5.0e-10;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ANTIMERIDIAN_OUTSIDE_RELATION_TOLERANCE_positiveSide() {
        double sourceLongitude = 180.0 - 2.0e-9;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ANTIMERIDIAN_OUTSIDE_RELATION_TOLERANCE_negativeSide() {
        double sourceLongitude = -180.0 + 2.0e-9;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JUST_BELOW_NEGATIVE_RANGE_nextDown() {
        double sourceLongitude = Math.nextDown(-180.0);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JUST_ABOVE_POSITIVE_RANGE_nextUp() {
        double sourceLongitude = Math.nextUp(180.0);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FIRST_NEGATIVE_WRAP_NON_ENDPOINT_negativeOneEightyOne() {
        double sourceLongitude = -181.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FIRST_POSITIVE_WRAP_NON_ENDPOINT_positiveOneEightyOne() {
        double sourceLongitude = 181.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_EXACT_WRAP_ENDPOINT_negativeFiveForty() {
        double sourceLongitude = -540.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_EXACT_WRAP_ENDPOINT_positiveFiveForty() {
        double sourceLongitude = 540.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTIPLE_NEGATIVE_REVOLUTIONS_NON_ENDPOINT_negativeNineHundredOnePointTwoFive() {
        double sourceLongitude = -901.25;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MULTIPLE_POSITIVE_REVOLUTIONS_NON_ENDPOINT_positiveNineHundredOnePointTwoFive() {
        double sourceLongitude = 901.25;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_EXACT_INCREMENT_twoToTheFiftySecond() {
        double sourceLongitude = 0x1.0p52;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAXIMUM_POSITIVE_FINITE_doubleMaxValue() {
        double sourceLongitude = Double.MAX_VALUE;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAXIMUM_NEGATIVE_FINITE_negativeDoubleMaxValue() {
        double sourceLongitude = -Double.MAX_VALUE;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NAN_INPUT_notANumber() {
        double sourceLongitude = Double.NaN;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_INFINITY_INPUT_positiveInfinity() {
        double sourceLongitude = Double.POSITIVE_INFINITY;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_INFINITY_INPUT_negativeInfinity() {
        double sourceLongitude = Double.NEGATIVE_INFINITY;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
