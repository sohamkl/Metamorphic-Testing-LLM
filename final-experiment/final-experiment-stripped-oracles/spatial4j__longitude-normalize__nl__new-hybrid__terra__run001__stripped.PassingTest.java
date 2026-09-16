import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

    private double generateFollowUp(double source) {
        return source + 360.0d;
    }

    private void assertMetamorphicRelationFor(double source) {
        Assertions.assertTrue(Double.isFinite(source));
        double followUp = generateFollowUp(source);
        Assertions.assertEquals(360.0d, followUp - source);
        double sourceOutput = DistanceUtils.normLonDEG(source);
        double followUpOutput = DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
        boolean sameCircularLongitude = sourceOutput == followUpOutput || (sourceOutput == -180.0d && followUpOutput == 180.0d) || (sourceOutput == 180.0d && followUpOutput == -180.0d);
        Assertions.assertTrue(sameCircularLongitude, () -> "Expected circularly equivalent normalized longitudes but got " + sourceOutput + " and " + followUpOutput);
    }

    @Test
    void NEGATIVE_RANGE_ENDPOINT_fullTurn() {
        double source = -180.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_RANGE_ENDPOINT_fullTurn() {
        double source = 180.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_SIGNED_ZERO_fullTurn() {
        double source = -0.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_SIGNED_ZERO_fullTurn() {
        double source = 0.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void IN_RANGE_NEGATIVE_HALF_fullTurn() {
        double source = -0.5d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void IN_RANGE_POSITIVE_HALF_fullTurn() {
        double source = 0.5d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void IN_RANGE_NEAR_NEGATIVE_ENDPOINT_fullTurn() {
        double source = -179.5d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void IN_RANGE_NEAR_POSITIVE_ENDPOINT_fullTurn() {
        double source = 179.5d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_BELOW_NEGATIVE_RANGE_fullTurn() {
        double source = -180.5d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void JUST_ABOVE_POSITIVE_RANGE_fullTurn() {
        double source = 180.5d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_REMAINDER_NEAR_MINUS_360_fullTurn() {
        double source = -539.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_REMAINDER_NEAR_PLUS_360_fullTurn() {
        double source = 539.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_REMAINDER_MINUS_ONE_fullTurn() {
        double source = -541.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_REMAINDER_PLUS_ONE_fullTurn() {
        double source = 541.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_NEAR_FULL_TURN_fullTurn() {
        double source = -359.5d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_NEAR_FULL_TURN_fullTurn() {
        double source = 359.5d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_EXACT_FULL_TURN_fullTurn() {
        double source = -360.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_EXACT_FULL_TURN_fullTurn() {
        double source = 360.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_EXACT_180_WRAP_fullTurn() {
        double source = -540.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_EXACT_180_WRAP_SENTINEL_fullTurn() {
        double source = 540.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_HALF_AFTER_EXACT_WRAP_fullTurn() {
        double source = -540.5d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_HALF_AFTER_EXACT_WRAP_fullTurn() {
        double source = 540.5d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void NEGATIVE_MULTI_TURN_SENTINEL_fullTurn() {
        double source = -900.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void POSITIVE_MULTI_TURN_SENTINEL_fullTurn() {
        double source = 900.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LARGE_POSITIVE_EXACT_INCREMENT_fullTurn() {
        double source = 4503599627370496.0d;
        assertMetamorphicRelationFor(source);
    }

    @Test
    void LARGE_NEGATIVE_EXACT_INCREMENT_fullTurn() {
        double source = -4503599627370496.0d;
        assertMetamorphicRelationFor(source);
    }
}
