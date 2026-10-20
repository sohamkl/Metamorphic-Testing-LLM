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
          "Normalized longitudes should represent the same globe position: "
              + sourceOutput + " versus " + followUpOutput);
    }
  }

  @Test
  void POSITIVE_FOLLOW_UP_ROUNDING_THRESHOLD_largePositiveUlp() {
    double source = Math.scalb(1.0d, 61);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_FOLLOW_UP_ROUNDING_THRESHOLD_largeNegativeUlp() {
    double source = -Math.scalb(1.0d, 61);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }
}
