import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

    private static double generateFollowUp(double longitude) {
        return longitude + 360.0d;
    }

    @Test
    public void testIN_RANGE_NEGATIVE_INTERIOR_1() {
        double source = -179.999999999d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testIN_RANGE_POSITIVE_INTERIOR_1() {
        double source = 179.999999999d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSIGNED_ZERO_NEGATIVE_1() {
        double source = -0.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSIGNED_ZERO_POSITIVE_1() {
        double source = 0.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLOWER_ANTIMERIDIAN_ENDPOINT_1() {
        double source = -180.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testUPPER_ANTIMERIDIAN_ENDPOINT_1() {
        double source = 180.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testJUST_BELOW_LOWER_ENDPOINT_1() {
        double source = -180.000000001d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testJUST_ABOVE_UPPER_ENDPOINT_1() {
        double source = 180.000000001d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_REMAINDER_NONZERO_1() {
        double source = -181.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOSITIVE_REMAINDER_NONZERO_1() {
        double source = 181.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOSITIVE_EXACT_REVOLUTION_1() {
        double source = 360.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_EXACT_REVOLUTION_1() {
        double source = -360.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOSITIVE_ODD_HALF_REVOLUTION_1() {
        double source = 540.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_ZERO_REMAINDER_1() {
        double source = -540.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testSMALL_FRACTIONAL_WRAP_1() {
        double source = 721.25d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_FRACTIONAL_WRAP_1() {
        double source = -721.25d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLARGE_POSITIVE_FINITE_1() {
        double source = 1.0e16d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLARGE_NEGATIVE_FINITE_1() {
        double source = -1.0e16d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testMAX_FINITE_POSITIVE_1() {
        double source = Double.MAX_VALUE;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testMAX_FINITE_NEGATIVE_1() {
        double source = -Double.MAX_VALUE;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNAN_INPUT_1() {
        double source = Double.NaN;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOSITIVE_INFINITY_INPUT_1() {
        double source = Double.POSITIVE_INFINITY;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_INFINITY_INPUT_1() {
        double source = Double.NEGATIVE_INFINITY;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
