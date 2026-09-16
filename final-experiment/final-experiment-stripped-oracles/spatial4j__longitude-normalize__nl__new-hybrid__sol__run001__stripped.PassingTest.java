import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

    private static double generateFollowUp(double source) {
        return source + 360.0;
    }

    private static void assertMetamorphicRelationFor(double source) {
        Assertions.assertTrue(Double.isFinite(source));
        double followUp = generateFollowUp(source);
        double sourceOutput = DistanceUtils.normLonDEG(source);
        double followUpOutput = DistanceUtils.normLonDEG(followUp);
        Assertions.assertTrue(Double.isFinite(sourceOutput));
        Assertions.assertTrue(Double.isFinite(followUpOutput));
        Assertions.assertTrue(sourceOutput >= -180.0 && sourceOutput <= 180.0);
        Assertions.assertTrue(followUpOutput >= -180.0 && followUpOutput <= 180.0);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
        double separation = Math.abs(sourceOutput - followUpOutput);
        Assertions.assertTrue(sourceOutput == followUpOutput || separation == 360.0, () -> "Normalized longitudes are not circularly equivalent: " + sourceOutput + " and " + followUpOutput);
    }

    @Test
    public void EXACT_LOWER_DATELINE_ENDPOINT_variation1() {
        assertMetamorphicRelationFor(-180.0);
    }

    @Test
    public void EXACT_UPPER_DATELINE_ENDPOINT_variation1() {
        assertMetamorphicRelationFor(180.0);
    }

    @Test
    public void IMMEDIATELY_INSIDE_DATELINE_ENDPOINTS_lowerNeighbor() {
        assertMetamorphicRelationFor(Math.nextUp(-180.0));
    }

    @Test
    public void SIGNED_ZERO_DIRECT_RETURN_positiveZero() {
        double source = 0.0;
        double sourceOutput = DistanceUtils.normLonDEG(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void SIGNED_ZERO_DIRECT_RETURN_negativeZero() {
        double source = -0.0;
        double sourceOutput = DistanceUtils.normLonDEG(source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void ORDINARY_NEGATIVE_INTERIOR_unitNegative() {
        assertMetamorphicRelationFor(-1.0);
    }

    @Test
    public void ORDINARY_NEGATIVE_INTERIOR_binaryFraction() {
        assertMetamorphicRelationFor(-13.5);
    }

    @Test
    public void ORDINARY_POSITIVE_INTERIOR_smallInteger() {
        assertMetamorphicRelationFor(10.0);
    }

    @Test
    public void ORDINARY_POSITIVE_INTERIOR_binaryFraction() {
        assertMetamorphicRelationFor(76.125);
    }

    @Test
    public void IMMEDIATELY_OUTSIDE_DATELINE_ENDPOINTS_belowLowerEndpoint() {
        assertMetamorphicRelationFor(Math.nextDown(-180.0));
    }

    @Test
    public void IMMEDIATELY_OUTSIDE_DATELINE_ENDPOINTS_aboveUpperEndpoint() {
        assertMetamorphicRelationFor(Math.nextUp(180.0));
    }

    @Test
    public void POSITIVE_NONSENTINEL_MODULO_firstTurnBand() {
        assertMetamorphicRelationFor(200.25);
    }

    @Test
    public void POSITIVE_NONSENTINEL_MODULO_secondTurnBand() {
        assertMetamorphicRelationFor(560.5);
    }

    @Test
    public void POSITIVE_NONSENTINEL_MODULO_fourthTurnBand() {
        assertMetamorphicRelationFor(1281.25);
    }

    @Test
    public void POSITIVE_NONSENTINEL_MODULO_largeTurnBand() {
        assertMetamorphicRelationFor(1_000_000.5);
    }

    @Test
    public void NEGATIVE_NONSENTINEL_MODULO_firstNegativeTurnBand() {
        assertMetamorphicRelationFor(-200.25);
    }

    @Test
    public void NEGATIVE_NONSENTINEL_MODULO_secondNegativeTurnBand() {
        assertMetamorphicRelationFor(-560.5);
    }

    @Test
    public void NEGATIVE_NONSENTINEL_MODULO_fourthNegativeTurnBand() {
        assertMetamorphicRelationFor(-1281.25);
    }

    @Test
    public void NEGATIVE_NONSENTINEL_MODULO_largeNegativeTurnBand() {
        assertMetamorphicRelationFor(-1_000_000.5);
    }

    @Test
    public void POSITIVE_DATELINE_SENTINELS_oneTurnBeyond() {
        assertMetamorphicRelationFor(540.0);
    }

    @Test
    public void POSITIVE_DATELINE_SENTINELS_twoTurnsBeyond() {
        assertMetamorphicRelationFor(900.0);
    }

    @Test
    public void POSITIVE_DATELINE_SENTINELS_threeTurnsBeyond() {
        assertMetamorphicRelationFor(1260.0);
    }

    @Test
    public void NEGATIVE_DATELINE_SENTINELS_oneTurnBelow() {
        assertMetamorphicRelationFor(-540.0);
    }

    @Test
    public void NEGATIVE_DATELINE_SENTINELS_twoTurnsBelow() {
        assertMetamorphicRelationFor(-900.0);
    }

    @Test
    public void NEGATIVE_DATELINE_SENTINELS_threeTurnsBelow() {
        assertMetamorphicRelationFor(-1260.0);
    }

    @Test
    public void FULL_TURN_CONGRUENT_TO_ZERO_positiveOneTurn() {
        assertMetamorphicRelationFor(360.0);
    }

    @Test
    public void FULL_TURN_CONGRUENT_TO_ZERO_negativeOneTurn() {
        assertMetamorphicRelationFor(-360.0);
    }

    @Test
    public void FULL_TURN_CONGRUENT_TO_ZERO_positiveTwoTurns() {
        assertMetamorphicRelationFor(720.0);
    }

    @Test
    public void FULL_TURN_CONGRUENT_TO_ZERO_negativeThreeTurns() {
        assertMetamorphicRelationFor(-1080.0);
    }

    @Test
    public void NEIGHBORS_OF_OUT_OF_RANGE_DATELINE_SENTINELS_belowNegativeSentinel() {
        assertMetamorphicRelationFor(Math.nextDown(-540.0));
    }

    @Test
    public void NEIGHBORS_OF_OUT_OF_RANGE_DATELINE_SENTINELS_aboveNegativeSentinel() {
        assertMetamorphicRelationFor(Math.nextUp(-540.0));
    }

    @Test
    public void LARGE_EXACT_FULL_TURN_ADDITION_positiveMillionScale() {
        double source = 1_000_001.25;
        Assertions.assertEquals(360.0, generateFollowUp(source) - source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LARGE_EXACT_FULL_TURN_ADDITION_negativeMillionScale() {
        double source = -1_000_001.25;
        Assertions.assertEquals(360.0, generateFollowUp(source) - source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LARGE_EXACT_FULL_TURN_ADDITION_positiveBillionScale() {
        double source = 1_000_000_000.5;
        Assertions.assertEquals(360.0, generateFollowUp(source) - source);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_FOLLOW_UP_ROUNDS_TO_SOURCE_powerOfTwo64() {
        double source = Math.scalb(1.0, 64);
        Assertions.assertEquals(source, generateFollowUp(source));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_FOLLOW_UP_ROUNDS_TO_SOURCE_powerOfTwo100() {
        double source = Math.scalb(1.0, 100);
        Assertions.assertEquals(source, generateFollowUp(source));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_FOLLOW_UP_ROUNDS_TO_SOURCE_belowFiniteMaximum() {
        double source = Math.nextDown(Double.MAX_VALUE);
        Assertions.assertEquals(source, generateFollowUp(source));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_FOLLOW_UP_ROUNDS_TO_SOURCE_powerOfTwo64() {
        double source = -Math.scalb(1.0, 64);
        Assertions.assertEquals(source, generateFollowUp(source));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_FOLLOW_UP_ROUNDS_TO_SOURCE_powerOfTwo100() {
        double source = -Math.scalb(1.0, 100);
        Assertions.assertEquals(source, generateFollowUp(source));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_FOLLOW_UP_ROUNDS_TO_SOURCE_aboveNegativeFiniteMaximum() {
        double source = -Math.nextDown(Double.MAX_VALUE);
        Assertions.assertEquals(source, generateFollowUp(source));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FINITE_DOUBLE_EXTREMA_positiveMaximum() {
        assertMetamorphicRelationFor(Double.MAX_VALUE);
    }

    @Test
    public void FINITE_DOUBLE_EXTREMA_negativeMaximum() {
        assertMetamorphicRelationFor(-Double.MAX_VALUE);
    }
}
