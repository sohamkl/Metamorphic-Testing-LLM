import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

  private static double generateFollowUp(double source) {
    return source + 360.0;
  }

  private static void assertMetamorphicRelationFor(double source) {
    double sourceOutput = DistanceUtils.normLonDEG(source);
    double followUpOutput = DistanceUtils.normLonDEG(generateFollowUp(source));
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  private static void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
    boolean numericallyEqual = sourceOutput == followUpOutput;
    boolean equivalentEndpoints =
        (sourceOutput == -180.0 && followUpOutput == 180.0)
            || (sourceOutput == 180.0 && followUpOutput == -180.0);
    Assertions.assertTrue(
        numericallyEqual || equivalentEndpoints,
        () -> "Normalized longitudes are not globe-equivalent: "
            + sourceOutput + " and " + followUpOutput);
  }

  @Test
  void SMALLEST_POSITIVE_SUBNORMAL_variation1() {
    double source = Double.longBitsToDouble(0x0000000000000001L);
    assertMetamorphicRelationFor(source);
  }

  @Test
  void SMALLEST_NEGATIVE_SUBNORMAL_variation1() {
    double source = -Double.longBitsToDouble(0x0000000000000001L);
    assertMetamorphicRelationFor(source);
  }
}
