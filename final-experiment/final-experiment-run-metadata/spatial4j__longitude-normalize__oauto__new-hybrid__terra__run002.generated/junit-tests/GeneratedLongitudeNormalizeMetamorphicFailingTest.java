import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    private static double generateFollowUp(double sourceLongitude) {
        return sourceLongitude + 360.0;
    }

    @Test
    void LARGE_POSITIVE_FOLLOW_UP_ROUNDING_twoToTheSixtieth() {
        double sourceLongitude = 0x1.0p60;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LARGE_NEGATIVE_FOLLOW_UP_ROUNDING_negativeTwoToTheSixtieth() {
        double sourceLongitude = -0x1.0p60;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(sourceLongitude);
        double followUpLongitude = generateFollowUp(sourceLongitude);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUpLongitude);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
