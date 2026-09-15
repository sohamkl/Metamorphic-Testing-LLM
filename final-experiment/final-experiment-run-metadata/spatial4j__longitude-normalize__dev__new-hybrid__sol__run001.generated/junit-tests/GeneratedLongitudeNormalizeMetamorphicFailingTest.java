import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    @Test
    public void LARGE_EXACT_SHIFT_AT_TWO_POW_55_positivePower() {
        double source = 0x1.0p55;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEGATIVE_BINade_BOUNDARY_EXACT_SHIFT_negativeTwoPow56() {
        double source = -0x1.0p56;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
