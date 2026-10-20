import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    private static final double TOLERANCE_DEG = 1.0e-9;

    private void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
        boolean sourceAtAntimeridian =
                Math.abs(Math.abs(sourceOutput) - 180.0) <= TOLERANCE_DEG;
        boolean followUpAtAntimeridian =
                Math.abs(Math.abs(followUpOutput) - 180.0) <= TOLERANCE_DEG;
        boolean sameLongitude =
                Math.abs(sourceOutput - followUpOutput) <= TOLERANCE_DEG;

        org.junit.jupiter.api.Assertions.assertTrue(
                sameLongitude || (sourceAtAntimeridian && followUpAtAntimeridian),
                "Normalized source and follow-up longitudes must identify the same globe position: "
                        + sourceOutput + " versus " + followUpOutput);
    }

    @Test
    void INTERNAL_ADDITION_ROUNDING_STRESS_variation1_positiveLargePower() {
        double source = 36028797018963968.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERNAL_ADDITION_ROUNDING_STRESS_variation2_negativeLargePower() {
        double source = -72057594037927936.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
