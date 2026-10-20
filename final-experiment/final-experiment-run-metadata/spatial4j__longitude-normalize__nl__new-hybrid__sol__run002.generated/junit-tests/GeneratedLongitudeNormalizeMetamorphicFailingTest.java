import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

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
    public void JUST_ABOVE_UPPER_BOUNDARY_variation1_firstRepresentableAbove() {
        double lon = Math.nextUp(180.0);
        Assertions.assertTrue((lon + 180.0) % 360.0 > 0.0);
        assertMetamorphicRelationFor(lon);
    }

    @Test
    public void JUST_ABOVE_UPPER_BOUNDARY_variation2_secondRepresentableAbove() {
        double lon = Math.nextUp(Math.nextUp(180.0));
        Assertions.assertTrue((lon + 180.0) % 360.0 > 0.0);
        assertMetamorphicRelationFor(lon);
    }
}
