import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

    private static double generateFollowUp(double source) {
        return source + 360.0;
    }

    private static void assertMetamorphicRelationFor(double source) {
        double followUp = generateFollowUp(source);

        Assertions.assertTrue(Double.isFinite(source));
        Assertions.assertTrue(Double.isFinite(followUp));
        Assertions.assertEquals(360.0, followUp - source, 0.0);

        double sourceOutput = DistanceUtils.normLonDEG(source);
        double followUpOutput = DistanceUtils.normLonDEG(followUp);

        Assertions.assertTrue(Double.isFinite(sourceOutput));
        Assertions.assertTrue(Double.isFinite(followUpOutput));
        Assertions.assertTrue(sourceOutput >= -180.0 && sourceOutput <= 180.0);
        Assertions.assertTrue(followUpOutput >= -180.0 && followUpOutput <= 180.0);

        assertMetamorphicRelation(
                source, followUp, sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            double source,
            double followUp,
            double sourceOutput,
            double followUpOutput) {

        double absoluteDifference = Math.abs(sourceOutput - followUpOutput);
        double circularDifference = Math.min(
                absoluteDifference,
                Math.abs(360.0 - absoluteDifference));

        double maximumUlp = Math.max(
                Math.max(Math.ulp(source), Math.ulp(followUp)),
                Math.max(Math.ulp(sourceOutput), Math.ulp(followUpOutput)));
        double tolerance = 8.0 * maximumUlp;

        Assertions.assertTrue(
                circularDifference <= tolerance,
                "Normalized longitudes differ circularly by "
                        + circularDifference
                        + ", exceeding tolerance "
                        + tolerance);
    }

    @Test
    public void SIGNED_ZERO_DIRECT_RETURN_variation1_positiveZero() {
        double lon = 0.0;
        double result = DistanceUtils.normLonDEG(lon);
        Assertions.assertEquals(
                Double.doubleToRawLongBits(lon),
                Double.doubleToRawLongBits(result));
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void SIGNED_ZERO_DIRECT_RETURN_variation2_negativeZero() {
        double lon = -0.0;
        double result = DistanceUtils.normLonDEG(lon);
        Assertions.assertEquals(
                Double.doubleToRawLongBits(lon),
                Double.doubleToRawLongBits(result));
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void LOWER_ANTIMERIDIAN_BOUNDARY_variation1_exactLowerEndpoint() {
        double lon = -180.0;
        Assertions.assertEquals(-180.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void UPPER_ANTIMERIDIAN_BOUNDARY_variation1_exactUpperEndpoint() {
        double lon = 180.0;
        Assertions.assertEquals(180.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void JUST_INSIDE_LOWER_BOUNDARY_variation1_nextUpFromLowerEndpoint() {
        double lon = Math.nextUp(-180.0);
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void JUST_INSIDE_UPPER_BOUNDARY_variation1_nextDownFromUpperEndpoint() {
        double lon = Math.nextDown(180.0);
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_IN_RANGE_INTERIOR_variation1_integerLongitude() {
        double lon = -120.0;
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_IN_RANGE_INTERIOR_variation2_binaryFraction() {
        double lon = -45.5;
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_IN_RANGE_INTERIOR_variation3_nearZeroInterior() {
        double lon = -0.125;
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_IN_RANGE_INTERIOR_variation1_integerLongitude() {
        double lon = 25.0;
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_IN_RANGE_INTERIOR_variation2_binaryFraction() {
        double lon = 90.5;
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_IN_RANGE_INTERIOR_variation3_nearUpperEndpoint() {
        double lon = 179.0;
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void FRACTIONAL_VALUES_AROUND_ZERO_variation1_positiveHalfDegree() {
        double lon = 0.5;
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void FRACTIONAL_VALUES_AROUND_ZERO_variation2_negativeThreeQuarters() {
        double lon = -0.75;
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void FRACTIONAL_VALUES_AROUND_ZERO_variation3_smallPositiveBinaryFraction() {
        double lon = Math.scalb(1.0, -20);
        Assertions.assertEquals(lon, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void JUST_BELOW_LOWER_BOUNDARY_variation1_firstRepresentableBelow() {
        double lon = Math.nextDown(-180.0);
        Assertions.assertTrue((lon + 180.0) % 360.0 < 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void JUST_BELOW_LOWER_BOUNDARY_variation2_secondRepresentableBelow() {
        double lon = Math.nextDown(Math.nextDown(-180.0));
        Assertions.assertTrue((lon + 180.0) % 360.0 < 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_FIRST_EXTERNAL_TURN_variation1_negativeNormalizedInterior() {
        double lon = -450.0;
        Assertions.assertTrue((lon + 180.0) % 360.0 < 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_FIRST_EXTERNAL_TURN_variation2_zeroNearNormalizedLocation() {
        double lon = -360.25;
        Assertions.assertTrue((lon + 180.0) % 360.0 < 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_FIRST_EXTERNAL_TURN_variation3_positiveNormalizedInterior() {
        double lon = -270.0;
        Assertions.assertTrue((lon + 180.0) % 360.0 < 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_FIRST_EXTERNAL_TURN_variation1_negativeNormalizedInterior() {
        double lon = 270.0;
        Assertions.assertTrue((lon + 180.0) % 360.0 > 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_FIRST_EXTERNAL_TURN_variation2_zeroNearNormalizedLocation() {
        double lon = 359.5;
        Assertions.assertTrue((lon + 180.0) % 360.0 > 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_FIRST_EXTERNAL_TURN_variation3_positiveNormalizedInterior() {
        double lon = 450.0;
        Assertions.assertTrue((lon + 180.0) % 360.0 > 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_ANTIMERIDIAN_SENTINELS_variation1_firstExternalSentinel() {
        double lon = 540.0;
        Assertions.assertEquals(180.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_ANTIMERIDIAN_SENTINELS_variation2_secondExternalSentinel() {
        double lon = 900.0;
        Assertions.assertEquals(180.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_ANTIMERIDIAN_SENTINELS_variation3_tenthExternalSentinel() {
        double lon = 3780.0;
        Assertions.assertEquals(180.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_ANTIMERIDIAN_SENTINELS_variation1_firstExternalSentinel() {
        double lon = -540.0;
        Assertions.assertEquals(-180.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_ANTIMERIDIAN_SENTINELS_variation2_secondExternalSentinel() {
        double lon = -900.0;
        Assertions.assertEquals(-180.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_ANTIMERIDIAN_SENTINELS_variation3_tenthExternalSentinel() {
        double lon = -3780.0;
        Assertions.assertEquals(-180.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_ZERO_MERIDIAN_MULTIPLES_variation1_oneFullTurn() {
        double lon = 360.0;
        Assertions.assertEquals(0.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_ZERO_MERIDIAN_MULTIPLES_variation2_twoFullTurns() {
        double lon = 720.0;
        Assertions.assertEquals(0.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_ZERO_MERIDIAN_MULTIPLES_variation3_tenFullTurns() {
        double lon = 3600.0;
        Assertions.assertEquals(0.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_ZERO_MERIDIAN_MULTIPLES_variation1_negativeOneFullTurn() {
        double lon = -360.0;
        Assertions.assertEquals(0.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_ZERO_MERIDIAN_MULTIPLES_variation2_negativeTwoFullTurns() {
        double lon = -720.0;
        Assertions.assertEquals(0.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_ZERO_MERIDIAN_MULTIPLES_variation3_negativeTenFullTurns() {
        double lon = -3600.0;
        Assertions.assertEquals(0.0, DistanceUtils.normLonDEG(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_GENERIC_MULTIPLE_TURNS_variation1_negativeNormalizedSign() {
        double lon = 600.0;
        Assertions.assertTrue(Math.abs(DistanceUtils.normLonDEG(lon)) < 180.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_GENERIC_MULTIPLE_TURNS_variation2_positiveNearZeroNormalizedSign() {
        double lon = 721.0;
        Assertions.assertTrue(Math.abs(DistanceUtils.normLonDEG(lon)) < 180.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void POSITIVE_GENERIC_MULTIPLE_TURNS_variation3_positiveNormalizedSign() {
        double lon = 1230.0;
        Assertions.assertTrue(Math.abs(DistanceUtils.normLonDEG(lon)) < 180.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_GENERIC_MULTIPLE_TURNS_variation1_positiveNormalizedSign() {
        double lon = -600.0;
        Assertions.assertTrue((lon + 180.0) % 360.0 < 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_GENERIC_MULTIPLE_TURNS_variation2_negativeNearZeroNormalizedSign() {
        double lon = -721.0;
        Assertions.assertTrue((lon + 180.0) % 360.0 < 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void NEGATIVE_GENERIC_MULTIPLE_TURNS_variation3_negativeNormalizedSign() {
        double lon = -1230.0;
        Assertions.assertTrue((lon + 180.0) % 360.0 < 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void LARGE_POSITIVE_REPRESENTABLE_TURNS_variation1_fractionalMillionScale() {
        double lon = 1_048_576.25;
        Assertions.assertTrue(Math.ulp(lon) < 8.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void LARGE_POSITIVE_REPRESENTABLE_TURNS_variation2_fractionalTwoMillionScale() {
        double lon = 2_097_275.5;
        Assertions.assertTrue(Math.ulp(lon) < 8.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void LARGE_NEGATIVE_REPRESENTABLE_TURNS_variation1_fractionalMillionScale() {
        double lon = -1_048_576.25;
        Assertions.assertTrue(Math.ulp(lon) < 8.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void LARGE_NEGATIVE_REPRESENTABLE_TURNS_variation2_fractionalTwoMillionScale() {
        double lon = -2_097_275.5;
        Assertions.assertTrue(Math.ulp(lon) < 8.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void ULP_EIGHT_TRANSFORMATION_FRONTIER_variation1_positivePowerOfTwo() {
        double lon = Math.scalb(1.0, 55);
        Assertions.assertEquals(8.0, Math.ulp(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void ULP_EIGHT_TRANSFORMATION_FRONTIER_variation2_positiveOffsetRemainder() {
        double lon = Math.scalb(1.0, 55) + 80.0;
        Assertions.assertEquals(8.0, Math.ulp(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void ULP_EIGHT_TRANSFORMATION_FRONTIER_variation3_negativeOffsetRemainder() {
        double lon = -Math.scalb(1.0, 55) - 40.0;
        Assertions.assertEquals(8.0, Math.ulp(lon), 0.0);
        assertMetamorphicRelationFor(lon);
    }
}
