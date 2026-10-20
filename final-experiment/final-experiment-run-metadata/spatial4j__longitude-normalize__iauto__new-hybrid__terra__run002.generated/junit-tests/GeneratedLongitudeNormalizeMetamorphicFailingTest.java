import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    private void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
        double tolerance = 1e-9d;
        boolean sourceAtAntimeridian =
            Math.abs(Math.abs(sourceOutput) - 180.0d) <= tolerance;
        boolean followUpAtAntimeridian =
            Math.abs(Math.abs(followUpOutput) - 180.0d) <= tolerance;

        if (sourceAtAntimeridian && followUpAtAntimeridian) {
            return;
        }

        if (Math.abs(sourceOutput - followUpOutput) > tolerance) {
            throw new AssertionError(
                "Normalized source and follow-up longitudes do not represent the same location: "
                    + sourceOutput + " versus " + followUpOutput);
        }
    }
}
