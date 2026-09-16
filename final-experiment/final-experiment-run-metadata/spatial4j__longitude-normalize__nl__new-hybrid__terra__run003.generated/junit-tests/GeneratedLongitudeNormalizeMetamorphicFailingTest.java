import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

  private static double generateFollowUp(double source) {
    return source + 360.0;
  }

  private static void assertMetamorphicRelationFor(double source) {
    double followUp = generateFollowUp(source);
    assertTrue(Double.isFinite(source));
    assertTrue(Double.isFinite(followUp));
    double sourceOutput = DistanceUtils.normLonDEG(source);
    double followUpOutput = DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  private static void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
    double wrappedDifference = DistanceUtils.normLonDEG(sourceOutput - followUpOutput);
    assertEquals(0.0, wrappedDifference, 1.0e-9);
  }

  @Test
  void POSITIVE_ROUNDED_ADDITION_AT_2_POW_60_positiveRoundedIncrement() {
    double source = 1.15292150460684698E18;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void NEGATIVE_ROUNDED_ADDITION_AT_2_POW_60_negativeRoundedIncrement() {
    double source = -1.15292150460684698E18;
    assertMetamorphicRelationFor(source);
  }
}
