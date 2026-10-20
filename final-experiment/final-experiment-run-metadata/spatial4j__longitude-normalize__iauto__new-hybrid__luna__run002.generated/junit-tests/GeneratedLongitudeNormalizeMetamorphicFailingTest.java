import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    private static void assertMetamorphicRelation(
            double sourceOutput, double followUpOutput) {
        double sourceDistanceFromAntimeridian =
                Math.abs(Math.abs(sourceOutput) - 180.0d);
        double followUpDistanceFromAntimeridian =
                Math.abs(Math.abs(followUpOutput) - 180.0d);

        boolean sourceIsAntimeridian =
                sourceDistanceFromAntimeridian <= 1e-9d;
        boolean followUpIsAntimeridian =
                followUpDistanceFromAntimeridian <= 1e-9d;

        if (sourceIsAntimeridian && followUpIsAntimeridian) {
            return;
        }

        double difference = Math.abs(sourceOutput - followUpOutput);
        if (difference > 1e-9d) {
            throw new AssertionError(
                    "Normalized longitude outputs differ by "
                            + difference
                            + " degrees: source="
                            + sourceOutput
                            + ", follow-up="
                            + followUpOutput);
        }
    }
}
