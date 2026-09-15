import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

  private static double generateFollowUp(double source) {
    return source + 360.0;
  }

  private static void assertMetamorphicRelationFor(double source) {
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(
        generateFollowUp(source));
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  private static void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
    Assertions.assertTrue(Double.isFinite(sourceOutput));
    Assertions.assertTrue(Double.isFinite(followUpOutput));
    double difference = Math.abs(sourceOutput - followUpOutput);
    double circularDifference = Math.min(difference, Math.abs(360.0 - difference));
    Assertions.assertTrue(
        circularDifference <= 1.0e-9,
        () -> "Outputs do not identify the same longitude: "
            + sourceOutput + " versus " + followUpOutput);
  }

}
