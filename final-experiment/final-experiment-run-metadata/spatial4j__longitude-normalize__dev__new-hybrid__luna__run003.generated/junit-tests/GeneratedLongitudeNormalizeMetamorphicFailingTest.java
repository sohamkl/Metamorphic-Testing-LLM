import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    @Test
    public void FOLLOW_UP_ROUNDS_TO_SAME_LARGE_VALUE_Variation1() {
        double source = 36028797018963968.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NEGATIVE_LARGE_NONOVERFLOWING_FOLLOW_UP_Variation1() {
        double source = -1152921504606846976.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
