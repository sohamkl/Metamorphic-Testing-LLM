import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    @Test
    void HIGH_MAGNITUDE_SHIFT_FIDELITY_LIMIT_positivePowerOfTwo() {
        double source = Math.scalb(1.0d, 55);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
