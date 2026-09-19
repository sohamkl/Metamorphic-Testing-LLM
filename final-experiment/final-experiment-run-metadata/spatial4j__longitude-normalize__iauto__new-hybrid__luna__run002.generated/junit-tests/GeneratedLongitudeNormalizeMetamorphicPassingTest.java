import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

    private static void assertMetamorphicRelation(
            double sourceOutput, double followUpOutput) {
        double sourceDistanceFromAntimeridian =
                Math.abs(Math.abs(sourceOutput) - 180.0d);
        double followUpDistanceFromAntimeridian =
                Math.abs(Math.abs(followUpOutput) - 180.0d);

        boolean sourceIsAntimeridian =
                sourceDistanceFromAntimeridian <= 1e-9d;
        boolean followUpIsAntimeridian =
                followUpDistanceFromAntimeridian <= 1e-9d;

        if (sourceIsAntimeridian && followUpIsAntimeridian) {
            return;
        }

        double difference = Math.abs(sourceOutput - followUpOutput);
        if (difference > 1e-9d) {
            throw new AssertionError(
                    "Normalized longitude outputs differ by "
                            + difference
                            + " degrees: source="
                            + sourceOutput
                            + ", follow-up="
                            + followUpOutput);
        }
    }

    @Test
    public void testINTERIOR_NEGATIVE_LONGITUDE_1() {
        double source = -73.25;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testINTERIOR_ZERO_LONGITUDE_1() {
        double source = 0.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testINTERIOR_POSITIVE_LONGITUDE_1() {
        double source = 73.25d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_ZERO_PRESERVATION_1() {
        double source = -0.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLOWER_ANTIMERIDIAN_ENDPOINT_1() {
        double source = -180.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testUPPER_ANTIMERIDIAN_ENDPOINT_1() {
        double source = 180.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testJUST_BELOW_LOWER_ANTIMERIDIAN_1() {
        double source = Math.nextDown(-180.0d);
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testJUST_ABOVE_UPPER_ANTIMERIDIAN_1() {
        double source = Math.nextUp(180.0d);
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOSITIVE_NONZERO_REMAINDER_1() {
        double source = 181.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_NONZERO_REMAINDER_1() {
        double source = -181.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOSITIVE_FULL_REVOLUTION_1() {
        double source = 360.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_FULL_REVOLUTION_1() {
        double source = -360.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOSITIVE_ANTIMERIDIAN_SENTINEL_1() {
        double source = 540.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_ANTIMERIDIAN_SENTINEL_1() {
        double source = -540.0d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testMULTIPLE_REVOLUTIONS_POSITIVE_1() {
        double source = 12345.678901d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testMULTIPLE_REVOLUTIONS_NEGATIVE_1() {
        double source = -12345.678901d;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLARGE_POSITIVE_FINITE_1() {
        double source = Double.MAX_VALUE;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testLARGE_NEGATIVE_FINITE_1() {
        double source = -Double.MAX_VALUE;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testPOSITIVE_INFINITY_INPUT_1() {
        double source = Double.POSITIVE_INFINITY;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNEGATIVE_INFINITY_INPUT_1() {
        double source = Double.NEGATIVE_INFINITY;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testNAN_INPUT_1() {
        double source = Double.NaN;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
                LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
