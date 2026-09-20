import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

    private static void assertMetamorphicRelation(
            double sourceOutput, double followUpOutput) {
        double absoluteDifference = Math.abs(sourceOutput - followUpOutput);
        double circularDifference = Math.min(
                absoluteDifference,
                Math.abs(360.0 - absoluteDifference));

        if (!(circularDifference <= 1e-9)) {
            throw new AssertionError(
                    "Normalized longitudes do not identify the same location: "
                            + sourceOutput + " and " + followUpOutput);
        }
    }

}
