import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

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

    private static void assertMetamorphicRelation(
            double sourceOutput, double followUpOutput) {
        if (Math.abs(sourceOutput) == 180.0
                && Math.abs(followUpOutput) == 180.0) {
            Assertions.assertEquals(180.0, Math.abs(followUpOutput), 0.0);
        } else {
            Assertions.assertEquals(sourceOutput, followUpOutput, 0.0);
        }
    }

    @Test
    public void LARGE_POSITIVE_GENERIC_variation2() {
        double source = Math.scalb(1.0, 55);
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void GREATEST_EXACT_TURN_SOURCE_variation1() {
        double source = Math.nextDown(Math.scalb(1.0, 56));
        assertMetamorphicRelationFor(source);
    }

    @Test
    public void LEAST_EXACT_TURN_SOURCE_variation1() {
        double source = -Math.scalb(1.0, 56) - 352.0;
        assertMetamorphicRelationFor(source);
    }
}
