import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

    private static double generateFollowUp(double longitudeDeg) {
        return longitudeDeg + 360.0;
    }

    @Test
    void NEGATIVE_ZERO_DIRECT_RETURN_negativeZero() {
        double source = -0.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_ZERO_DIRECT_TO_MODULO_positiveZero() {
        double source = 0.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERIOR_NEGATIVE_DIRECT_TO_MODULO_negativeFraction() {
        double source = -73.25d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERIOR_POSITIVE_DIRECT_TO_MODULO_positiveFraction() {
        double source = 73.25d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIRECT_NEGATIVE_FRACTION_NEAR_ZERO_smallNegative() {
        double source = -1.0E-12d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIRECT_POSITIVE_FRACTION_NEAR_ZERO_smallPositive() {
        double source = 1.0E-12d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOWER_ENDPOINT_MINUS_180_exactLowerEndpoint() {
        double source = -180.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UPPER_ENDPOINT_PLUS_180_exactUpperEndpoint() {
        double source = 180.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JUST_INSIDE_LOWER_ENDPOINT_nextUpLowerEndpoint() {
        double source = Math.nextUp(-180.0d);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JUST_INSIDE_UPPER_ENDPOINT_nextDownUpperEndpoint() {
        double source = Math.nextDown(180.0d);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JUST_OUTSIDE_LOWER_ENDPOINT_nextDownLowerEndpoint() {
        double source = Math.nextDown(-180.0d);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void JUST_OUTSIDE_UPPER_ENDPOINT_nextUpUpperEndpoint() {
        double source = Math.nextUp(180.0d);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LOWER_ANTIMERIDIAN_TOLERANCE_BAND_withinTolerance() {
        double source = -180.0d + 5.0E-10d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void UPPER_ANTIMERIDIAN_TOLERANCE_BAND_withinTolerance() {
        double source = 180.0d - 5.0E-10d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void OUTSIDE_ANTIMERIDIAN_TOLERANCE_BAND_outsideTolerance() {
        double source = 180.0d - 2.0E-9d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_OUTSIDE_TO_DIRECT_FOLLOW_UP_negative270() {
        double source = -270.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_NEAR_LOWER_WRAP_TO_DIRECT_FOLLOW_UP_negative180Point25() {
        double source = -180.25d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_NEAR_FULL_REVOLUTION_WRAP_negative359Point75() {
        double source = -359.75d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_OUTSIDE_NONNEGATIVE_REMAINDER_positive270() {
        double source = 270.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_NEAR_UPPER_WRAP_positive180Point25() {
        double source = 180.25d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_EXACT_FULL_REVOLUTION_positive360() {
        double source = 360.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_EXACT_FULL_REVOLUTION_negative360() {
        double source = -360.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_EXACT_ANTIMERIDIAN_CYCLE_positive540() {
        double source = 540.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_EXACT_ANTIMERIDIAN_CYCLE_negative540() {
        double source = -540.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_MULTI_REVOLUTION_NONINTEGRAL_positive1092Point5() {
        double source = 1092.5d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_MULTI_REVOLUTION_NONINTEGRAL_negative1092Point5() {
        double source = -1092.5d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_POSITIVE_WITH_EFFECTIVE_SHIFT_powerOfTwo52() {
        double source = Math.scalb(1.0d, 52);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_FINITE_NEGATIVE_WITH_EFFECTIVE_SHIFT_negativePowerOfTwo52() {
        double source = -Math.scalb(1.0d, 52);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAX_FINITE_POSITIVE_ADDITION_NO_OP_maxValue() {
        double source = Double.MAX_VALUE;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void MAX_FINITE_NEGATIVE_ADDITION_NO_OP_negativeMaxValue() {
        double source = -Double.MAX_VALUE;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_MIN_SUBNORMAL_minValue() {
        double source = Double.MIN_VALUE;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_MIN_SUBNORMAL_negativeMinValue() {
        double source = -Double.MIN_VALUE;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_INFINITY_positiveInfinity() {
        double source = Double.POSITIVE_INFINITY;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_INFINITY_negativeInfinity() {
        double source = Double.NEGATIVE_INFINITY;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NAN_INPUT_nan() {
        double source = Double.NaN;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
