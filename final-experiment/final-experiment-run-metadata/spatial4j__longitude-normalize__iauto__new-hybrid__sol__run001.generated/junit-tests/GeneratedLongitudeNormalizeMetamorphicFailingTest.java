import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    private static void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
        double tolerance = 1.0e-9d;
        boolean sourceAtAntimeridian =
                Math.abs(Math.abs(sourceOutput) - 180.0d) <= tolerance;
        boolean followUpAtAntimeridian =
                Math.abs(Math.abs(followUpOutput) - 180.0d) <= tolerance;

        if (sourceAtAntimeridian && followUpAtAntimeridian) {
            return;
        }

        if (Math.abs(sourceOutput - followUpOutput) > tolerance) {
            throw new AssertionError(
                    "Normalized longitudes do not represent the same globe position: "
                            + sourceOutput + " and " + followUpOutput);
        }
    }

    @Test
    public void LARGE_POSITIVE_FINITE_PRECISION_power56Band() {
        double source = 0x1.0p56 + 64.0d;
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LARGE_NEGATIVE_FINITE_PRECISION_power56Band() {
        double source = -(0x1.0p56 + 64.0d);
        double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
