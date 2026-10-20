import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    private static void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
        boolean sourceIsAntimeridian =
                Math.abs(Math.abs(sourceOutput) - 180.0d) <= 1e-9d;
        boolean followUpIsAntimeridian =
                Math.abs(Math.abs(followUpOutput) - 180.0d) <= 1e-9d;

        if (sourceIsAntimeridian && followUpIsAntimeridian) {
            return;
        }

        if (Math.abs(sourceOutput - followUpOutput) > 1e-9d) {
            throw new AssertionError(
                    "Normalized outputs differ: source=" + sourceOutput
                            + ", follow-up=" + followUpOutput);
        }
    }
}
