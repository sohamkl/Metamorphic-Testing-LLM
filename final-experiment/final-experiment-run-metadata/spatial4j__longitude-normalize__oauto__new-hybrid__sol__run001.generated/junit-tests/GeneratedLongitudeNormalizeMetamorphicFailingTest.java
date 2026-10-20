import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    private static double generateFollowUp(double longitudeDeg) {
        return longitudeDeg + 360.0;
    }

    @Test
    public void LARGE_POSITIVE_POWER_OF_TWO_55_exactBinaryInteger() {
        double source = 0x1.0p55;
        double sourceOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = generateFollowUp(source);
        double followUpOutput =
                org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
