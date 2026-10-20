import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    private static void runMetamorphicRelation(double source) {
        double sourceOutput =
            org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
        double followUp =
            LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
        double followUpOutput =
            org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            double sourceOutput, double followUpOutput) {
        boolean sourceIsAntimeridian =
            Math.abs(Math.abs(sourceOutput) - 180.0d) <= 1e-9d;
        boolean followUpIsAntimeridian =
            Math.abs(Math.abs(followUpOutput) - 180.0d) <= 1e-9d;

        if (sourceIsAntimeridian && followUpIsAntimeridian) {
            return;
        }

        double difference = Math.abs(sourceOutput - followUpOutput);
        if (difference > 1e-9d) {
            throw new AssertionError(
                "Normalized longitude relation violated: sourceOutput="
                    + sourceOutput + ", followUpOutput=" + followUpOutput);
        }
    }
}
