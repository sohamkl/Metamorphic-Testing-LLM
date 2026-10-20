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
        Assertions.assertTrue(Double.isFinite(followUp));
        double sourceOutput = DistanceUtils.normLonDEG(source);
        double followUpOutput = DistanceUtils.normLonDEG(followUp);
        assertNormalizedOutput(sourceOutput);
        assertNormalizedOutput(followUpOutput);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertNormalizedOutput(double output) {
        Assertions.assertTrue(Double.isFinite(output));
        Assertions.assertTrue(output >= -180.0 && output <= 180.0);
    }

    private static void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
        if (Math.abs(sourceOutput) == 180.0 && Math.abs(followUpOutput) == 180.0) {
            Assertions.assertEquals(180.0, Math.abs(followUpOutput), 0.0);
        } else {
            Assertions.assertEquals(sourceOutput, followUpOutput, 0.0);
        }
    }

    @Test
    public void LOWER_ENDPOINT_PASS_THROUGH_variation1() {
        double source = -180.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void UPPER_ENDPOINT_PASS_THROUGH_variation1() {
        double source = 180.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_ZERO_PASS_THROUGH_variation1() {
        double source = 0.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_ZERO_PASS_THROUGH_variation1() {
        double source = -0.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LOWER_INTERIOR_EXACT_NEIGHBORHOOD_variation1() {
        double source = -180.0 + Math.scalb(1.0, -40);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void UPPER_INTERIOR_EXACT_NEIGHBORHOOD_variation1() {
        double source = 180.0 - Math.scalb(1.0, -40);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_INTERIOR_COMMON_CASE_variation1() {
        double source = -45.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_INTERIOR_COMMON_CASE_variation2() {
        double source = -179.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_INTERIOR_COMMON_CASE_variation1() {
        double source = 45.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_INTERIOR_COMMON_CASE_variation2() {
        double source = 100.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void JUST_BELOW_LOWER_BOUNDARY_variation1() {
        double source = -180.0 - Math.scalb(1.0, -40);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void JUST_ABOVE_UPPER_BOUNDARY_variation1() {
        double source = 180.0 + Math.scalb(1.0, -40);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_FULL_TURN_ZERO_variation1() {
        double source = -360.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_FULL_TURN_ZERO_variation1() {
        double source = 360.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_ONE_AND_HALF_TURNS_variation1() {
        double source = -540.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_ONE_AND_HALF_TURNS_variation1() {
        double source = 540.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FIRST_NEGATIVE_WRAP_GENERIC_variation1() {
        double source = -181.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FIRST_NEGATIVE_WRAP_GENERIC_variation2() {
        double source = -539.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FIRST_POSITIVE_WRAP_GENERIC_variation1() {
        double source = 181.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FIRST_POSITIVE_WRAP_GENERIC_variation2() {
        double source = 539.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FEW_TURN_NEGATIVE_GENERIC_variation1() {
        double source = -541.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FEW_TURN_NEGATIVE_GENERIC_variation2() {
        double source = -721.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FEW_TURN_NEGATIVE_GENERIC_variation3() {
        double source = -1000.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FEW_TURN_NEGATIVE_ENDPOINT_SENTINEL_variation1() {
        double source = -180.0 - 360.0 * 2.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FEW_TURN_NEGATIVE_ENDPOINT_SENTINEL_variation2() {
        int completedTurns = 2;
        double source = -180.0 - 360.0 * completedTurns;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FEW_TURN_POSITIVE_ENDPOINT_SENTINEL_variation1() {
        double source = 180.0 + 360.0 * 2.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FEW_TURN_POSITIVE_ENDPOINT_SENTINEL_variation2() {
        int completedTurns = 2;
        double source = 180.0 + 360.0 * completedTurns;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FEW_TURN_POSITIVE_GENERIC_variation1() {
        double source = 541.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FEW_TURN_POSITIVE_GENERIC_variation2() {
        double source = 721.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void FEW_TURN_POSITIVE_GENERIC_variation3() {
        double source = 1000.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_ENDPOINT_APPROACH_FROM_BELOW_variation1() {
        double endpoint = -180.0 - 360.0 * 2.0;
        double source = Math.nextDown(endpoint);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void NEGATIVE_ENDPOINT_APPROACH_FROM_ABOVE_variation1() {
        double endpoint = -180.0 - 360.0 * 2.0;
        double source = Math.nextUp(endpoint);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_ENDPOINT_APPROACH_FROM_BELOW_variation1() {
        double endpoint = 180.0 + 360.0 * 2.0;
        double source = Math.nextDown(endpoint);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void POSITIVE_ENDPOINT_APPROACH_FROM_ABOVE_variation1() {
        double endpoint = 180.0 + 360.0 * 2.0;
        double source = Math.nextUp(endpoint);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void EXACT_BINARY_FRACTION_INTERIOR_variation1() {
        double source = -0.5;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void EXACT_BINARY_FRACTION_INTERIOR_variation2() {
        double source = 0.5;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void MODERATE_POSITIVE_GENERIC_variation1() {
        double source = 2048.5;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void MODERATE_POSITIVE_GENERIC_variation2() {
        double source = 500000.25;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void MODERATE_NEGATIVE_GENERIC_variation1() {
        double source = -4096.5;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void MODERATE_NEGATIVE_GENERIC_variation2() {
        double source = -500000.25;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LARGE_POSITIVE_GENERIC_variation1() {
        double source = Math.scalb(1.0, 20) + 1.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LARGE_NEGATIVE_GENERIC_variation1() {
        double source = -Math.scalb(1.0, 20) - 1.0;
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LARGE_NEGATIVE_GENERIC_variation2() {
        double source = -Math.scalb(1.0, 55);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LARGE_POSITIVE_ENDPOINT_SENTINEL_variation1() {
        double source = 180.0 + 360.0 * Math.scalb(1.0, 20);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LARGE_NEGATIVE_ENDPOINT_SENTINEL_variation1() {
        double source = -180.0 - 360.0 * Math.scalb(1.0, 20);
        assertMetamorphicRelationFor(source);
    }
}
