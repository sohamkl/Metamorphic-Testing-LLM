import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicTest {

    private static double generateFollowUp(double source) {
        return source + 360.0d;
    }

    private static void assertMetamorphicRelationFor(double source) {
        Assertions.assertTrue(Double.isFinite(source));
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(
                        generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            double sourceOutput, double followUpOutput) {
        Assertions.assertTrue(sourceOutput >= -180.0d && sourceOutput <= 180.0d);
        Assertions.assertTrue(followUpOutput >= -180.0d && followUpOutput <= 180.0d);

        double difference = Math.abs(sourceOutput - followUpOutput);
        if (difference > 180.0d) {
            difference = 360.0d - difference;
        }
        Assertions.assertEquals(0.0d, difference, 1.0e-9d);
    }

    @Test
    public void INTERIOR_LONGITUDES_EARLY_RETURN_variation1() {
        assertMetamorphicRelationFor(-120.5d);
    }

    @Test
    public void INTERIOR_LONGITUDES_EARLY_RETURN_variation2() {
        assertMetamorphicRelationFor(-90.0d);
    }

    @Test
    public void INTERIOR_LONGITUDES_EARLY_RETURN_variation3() {
        assertMetamorphicRelationFor(0.0d);
    }

    @Test
    public void INTERIOR_LONGITUDES_EARLY_RETURN_variation4() {
        assertMetamorphicRelationFor(45.25d);
    }

    @Test
    public void INTERIOR_LONGITUDES_EARLY_RETURN_variation5() {
        assertMetamorphicRelationFor(179.25d);
    }

    @Test
    public void INCLUSIVE_ENDPOINT_MINUS_180_variation1() {
        assertMetamorphicRelationFor(-180.0d);
    }

    @Test
    public void INCLUSIVE_ENDPOINT_PLUS_180_variation1() {
        assertMetamorphicRelationFor(180.0d);
    }

    @Test
    public void JUST_BELOW_LOWER_ENDPOINT_variation1() {
        assertMetamorphicRelationFor(Math.nextDown(-180.0d));
    }

    @Test
    public void JUST_BELOW_LOWER_ENDPOINT_variation2() {
        assertMetamorphicRelationFor(-180.0000001d);
    }

    @Test
    public void JUST_ABOVE_UPPER_ENDPOINT_variation1() {
        assertMetamorphicRelationFor(Math.nextUp(180.0d));
    }

    @Test
    public void JUST_ABOVE_UPPER_ENDPOINT_variation2() {
        assertMetamorphicRelationFor(180.0000001d);
    }

    @Test
    public void ORDINARY_NEGATIVE_REMAINDER_variation1() {
        assertMetamorphicRelationFor(-540.5d);
    }

    @Test
    public void ORDINARY_NEGATIVE_REMAINDER_variation2() {
        assertMetamorphicRelationFor(-721.25d);
    }

    @Test
    public void ORDINARY_NEGATIVE_REMAINDER_variation3() {
        assertMetamorphicRelationFor(-1000.75d);
    }

    @Test
    public void ORDINARY_NEGATIVE_REMAINDER_variation4() {
        assertMetamorphicRelationFor(-100000.125d);
    }

    @Test
    public void ORDINARY_POSITIVE_REMAINDER_variation1() {
        assertMetamorphicRelationFor(540.5d);
    }

    @Test
    public void ORDINARY_POSITIVE_REMAINDER_variation2() {
        assertMetamorphicRelationFor(721.25d);
    }

    @Test
    public void ORDINARY_POSITIVE_REMAINDER_variation3() {
        assertMetamorphicRelationFor(1000.75d);
    }

    @Test
    public void ORDINARY_POSITIVE_REMAINDER_variation4() {
        assertMetamorphicRelationFor(100000.125d);
    }

    @Test
    public void POSITIVE_EXACT_TURN_SENTINEL_variation1() {
        assertMetamorphicRelationFor(360.0d);
    }

    @Test
    public void POSITIVE_EXACT_TURN_SENTINEL_variation2() {
        assertMetamorphicRelationFor(720.0d);
    }

    @Test
    public void POSITIVE_EXACT_TURN_SENTINEL_variation3() {
        assertMetamorphicRelationFor(3600.0d);
    }

    @Test
    public void NEGATIVE_EXACT_TURN_SENTINEL_variation1() {
        assertMetamorphicRelationFor(-360.0d);
    }

    @Test
    public void NEGATIVE_EXACT_TURN_SENTINEL_variation2() {
        assertMetamorphicRelationFor(-720.0d);
    }

    @Test
    public void NEGATIVE_EXACT_TURN_SENTINEL_variation3() {
        assertMetamorphicRelationFor(-3600.0d);
    }

    @Test
    public void ZERO_AND_SIGNED_ZERO_variation1() {
        assertMetamorphicRelationFor(0.0d);
    }

    @Test
    public void ZERO_AND_SIGNED_ZERO_variation2() {
        assertMetamorphicRelationFor(-0.0d);
    }

    @Test
    public void SUBNORMAL_LONGITUDES_variation1() {
        assertMetamorphicRelationFor(Double.MIN_VALUE);
    }

    @Test
    public void SUBNORMAL_LONGITUDES_variation2() {
        assertMetamorphicRelationFor(-Double.MIN_VALUE);
    }

    @Test
    public void MULTIPLE_TURNS_WITH_FRACTION_variation1() {
        assertMetamorphicRelationFor(1080.125d);
    }

    @Test
    public void MULTIPLE_TURNS_WITH_FRACTION_variation2() {
        assertMetamorphicRelationFor(-1080.125d);
    }

    @Test
    public void MULTIPLE_TURNS_WITH_FRACTION_variation3() {
        assertMetamorphicRelationFor(1000000.5d);
    }

    @Test
    public void MULTIPLE_TURNS_WITH_FRACTION_variation4() {
        assertMetamorphicRelationFor(-1000000.5d);
    }

    @Test
    public void LARGE_FINITE_REMAINDER_VALUES_variation1() {
        assertMetamorphicRelationFor(1.0e20d + 17.0d);
    }

    @Test
    public void LARGE_FINITE_REMAINDER_VALUES_variation2() {
        assertMetamorphicRelationFor(-(1.0e20d + 17.0d));
    }

    @Test
    public void LARGE_FINITE_REMAINDER_VALUES_variation3() {
        assertMetamorphicRelationFor(1.0e200d);
    }

    @Test
    public void LARGE_FINITE_REMAINDER_VALUES_variation4() {
        assertMetamorphicRelationFor(-1.0e200d);
    }

    @Test
    public void NEAR_LARGE_FINITE_LIMIT_variation1() {
        assertMetamorphicRelationFor(1.0e300d);
    }

    @Test
    public void NEAR_LARGE_FINITE_LIMIT_variation2() {
        assertMetamorphicRelationFor(-1.0e300d);
    }

    @Test
    public void NEAR_LARGE_FINITE_LIMIT_variation3() {
        assertMetamorphicRelationFor(Double.MAX_VALUE);
    }

    @Test
    public void FOLLOWUP_CROSSES_ZERO_REPRESENTATION_variation1() {
        assertMetamorphicRelationFor(-360.0000001d);
    }

    @Test
    public void FOLLOWUP_CROSSES_ZERO_REPRESENTATION_variation2() {
        assertMetamorphicRelationFor(-719.9999999d);
    }

    @Test
    public void FOLLOWUP_CROSSES_ZERO_REPRESENTATION_variation3() {
        assertMetamorphicRelationFor(-1080.0000001d);
    }

    @Test
    public void FOLLOWUP_CROSSES_ANTIMERIDIAN_REPRESENTATION_variation1() {
        assertMetamorphicRelationFor(540.0000001d);
    }

    @Test
    public void FOLLOWUP_CROSSES_ANTIMERIDIAN_REPRESENTATION_variation2() {
        assertMetamorphicRelationFor(-539.9999999d);
    }

    @Test
    public void FOLLOWUP_CROSSES_ANTIMERIDIAN_REPRESENTATION_variation3() {
        assertMetamorphicRelationFor(900.0000001d);
    }
}
