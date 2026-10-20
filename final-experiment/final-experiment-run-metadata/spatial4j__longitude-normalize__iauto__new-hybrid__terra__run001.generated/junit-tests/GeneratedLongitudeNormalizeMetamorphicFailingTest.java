import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    private static final double TOLERANCE_DEG = 1e-9;

    private void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
        boolean sourceAtAntimeridian =
            Math.abs(Math.abs(sourceOutput) - 180.0) <= TOLERANCE_DEG;
        boolean followUpAtAntimeridian =
            Math.abs(Math.abs(followUpOutput) - 180.0) <= TOLERANCE_DEG;

        if (sourceAtAntimeridian && followUpAtAntimeridian) {
            return;
        }

        if (Math.abs(sourceOutput - followUpOutput) > TOLERANCE_DEG) {
            throw new AssertionError(
                "Normalized longitudes should identify the same globe position: "
                    + sourceOutput + " versus " + followUpOutput);
        }
    }

    @Test
    void LARGE_EXACTLY_SHIFTABLE_POSITIVE_2_POW_55_variation1() {
        double source = 0x1.0p55;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POSITIVE_BINade_CROSSING_EXACT_SHIFT_variation1() {
        double source = 0x1.0p55 - 360.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NEGATIVE_BINade_CROSSING_EXACT_SHIFT_variation1() {
        double source = -0x1.0p55 - 360.0;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
