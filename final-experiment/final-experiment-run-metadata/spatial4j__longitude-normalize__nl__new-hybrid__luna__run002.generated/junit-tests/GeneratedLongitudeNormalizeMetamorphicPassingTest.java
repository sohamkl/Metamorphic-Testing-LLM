import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

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

  @Test
  void normLonDEG_IN_RANGE_INTERIOR_VALUES_negativeInterior() {
    assertMetamorphicRelationFor(-179.0);
  }

  @Test
  void normLonDEG_IN_RANGE_INTERIOR_VALUES_negativeSmallInterior() {
    assertMetamorphicRelationFor(-45.0);
  }

  @Test
  void normLonDEG_IN_RANGE_INTERIOR_VALUES_positiveZeroInterior() {
    assertMetamorphicRelationFor(0.0);
  }

  @Test
  void normLonDEG_IN_RANGE_INTERIOR_VALUES_positiveSmallInterior() {
    assertMetamorphicRelationFor(45.0);
  }

  @Test
  void normLonDEG_IN_RANGE_INTERIOR_VALUES_positiveInterior() {
    assertMetamorphicRelationFor(179.0);
  }

  @Test
  void normLonDEG_EXACT_ENDPOINT_MINUS_180() {
    assertMetamorphicRelationFor(-180.0);
  }

  @Test
  void normLonDEG_EXACT_ENDPOINT_PLUS_180() {
    assertMetamorphicRelationFor(180.0);
  }

  @Test
  void normLonDEG_JUST_BELOW_LOWER_ENDPOINT() {
    assertMetamorphicRelationFor(-180.0 - Math.ulp(180.0));
  }

  @Test
  void normLonDEG_JUST_ABOVE_UPPER_ENDPOINT() {
    assertMetamorphicRelationFor(180.0 + Math.ulp(180.0));
  }

  @Test
  void normLonDEG_POSITIVE_REMAINDER_OUTSIDE_181() {
    assertMetamorphicRelationFor(181.0);
  }

  @Test
  void normLonDEG_POSITIVE_REMAINDER_OUTSIDE_270() {
    assertMetamorphicRelationFor(270.0);
  }

  @Test
  void normLonDEG_POSITIVE_REMAINDER_OUTSIDE_359() {
    assertMetamorphicRelationFor(359.0);
  }

  @Test
  void normLonDEG_NEGATIVE_REMAINDER_OUTSIDE_negative181() {
    assertMetamorphicRelationFor(-181.0);
  }

  @Test
  void normLonDEG_NEGATIVE_REMAINDER_OUTSIDE_negative270() {
    assertMetamorphicRelationFor(-270.0);
  }

  @Test
  void normLonDEG_NEGATIVE_REMAINDER_OUTSIDE_negative359() {
    assertMetamorphicRelationFor(-359.0);
  }

  @Test
  void normLonDEG_POSITIVE_SEAM_REMAINDER_ZERO_540() {
    assertMetamorphicRelationFor(540.0);
  }

  @Test
  void normLonDEG_POSITIVE_SEAM_REMAINDER_ZERO_900() {
    assertMetamorphicRelationFor(900.0);
  }

  @Test
  void normLonDEG_POSITIVE_SEAM_REMAINDER_ZERO_largePositiveSeam() {
    assertMetamorphicRelationFor(360180.0);
  }

  @Test
  void normLonDEG_NEGATIVE_SEAM_AND_NEGATIVE_MULTIPLE_PATHS_negative540() {
    assertMetamorphicRelationFor(-540.0);
  }

  @Test
  void normLonDEG_NEGATIVE_SEAM_AND_NEGATIVE_MULTIPLE_PATHS_negative360() {
    assertMetamorphicRelationFor(-360.0);
  }

  @Test
  void normLonDEG_NEGATIVE_SEAM_AND_NEGATIVE_MULTIPLE_PATHS_negative720() {
    assertMetamorphicRelationFor(-720.0);
  }

  @Test
  void normLonDEG_SIGNED_ZERO_negativeZero() {
    assertMetamorphicRelationFor(-0.0);
  }

  @Test
  void normLonDEG_SIGNED_ZERO_positiveZero() {
    assertMetamorphicRelationFor(+0.0);
  }

  @Test
  void normLonDEG_MULTI_TURN_FINITE_MAGNITUDES_positive1080Point5() {
    assertMetamorphicRelationFor(1080.5);
  }

  @Test
  void normLonDEG_MULTI_TURN_FINITE_MAGNITUDES_negative1080Point5() {
    assertMetamorphicRelationFor(-1080.5);
  }

  @Test
  void normLonDEG_MULTI_TURN_FINITE_MAGNITUDES_positiveMillionPoint25() {
    assertMetamorphicRelationFor(1000000.25);
  }

  @Test
  void normLonDEG_MULTI_TURN_FINITE_MAGNITUDES_negativeMillionPoint25() {
    assertMetamorphicRelationFor(-1000000.25);
  }

  @Test
  void normLonDEG_LARGE_FINITE_PRECISION_VALUES_positiveTwoToThe53() {
    assertMetamorphicRelationFor(9007199254740992.0);
  }

  @Test
  void normLonDEG_LARGE_FINITE_PRECISION_VALUES_negativeTwoToThe53() {
    assertMetamorphicRelationFor(-9007199254740992.0);
  }

  @Test
  void normLonDEG_LARGE_FINITE_PRECISION_VALUES_positiveMaxValue() {
    assertMetamorphicRelationFor(Double.MAX_VALUE);
  }

  @Test
  void normLonDEG_LARGE_FINITE_PRECISION_VALUES_negativeMaxValue() {
    assertMetamorphicRelationFor(-Double.MAX_VALUE);
  }
}
