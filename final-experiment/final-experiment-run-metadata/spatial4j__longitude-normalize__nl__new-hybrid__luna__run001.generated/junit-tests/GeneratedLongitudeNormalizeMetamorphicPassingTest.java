import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

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
  void POSITIVE_INTERIOR_variation1() {
    double source = 37.25;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void NEGATIVE_INTERIOR_variation1() {
    double source = -37.25;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void POSITIVE_ZERO_variation1() {
    double source = 0.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void NEGATIVE_ZERO_variation1() {
    double source = Double.longBitsToDouble(0x8000000000000000L);
    assertMetamorphicRelationFor(source);
  }

  @Test
  void EXACT_NEGATIVE_ENDPOINT_variation1() {
    double source = -180.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void EXACT_POSITIVE_ENDPOINT_variation1() {
    double source = 180.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void JUST_BELOW_NEGATIVE_ENDPOINT_variation1() {
    double source = -180.0d - Math.ulp(180.0d);
    assertMetamorphicRelationFor(source);
  }

  @Test
  void JUST_ABOVE_POSITIVE_ENDPOINT_variation1() {
    double source = 180.0d + Math.ulp(180.0d);
    assertMetamorphicRelationFor(source);
  }

  @Test
  void NEGATIVE_ONE_DEGREE_OUT_variation1() {
    double source = -181.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void POSITIVE_ONE_DEGREE_OUT_variation1() {
    double source = 181.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void POSITIVE_FULL_TURN_variation1() {
    double source = 360.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void NEGATIVE_FULL_TURN_variation1() {
    double source = -360.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void POSITIVE_HALF_TURN_MULTIPLE_variation1() {
    double source = 540.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void NEGATIVE_HALF_TURN_MULTIPLE_variation1() {
    double source = -540.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void POSITIVE_FRACTIONAL_MULTI_TURN_variation1() {
    double source = 721.5d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void NEGATIVE_FRACTIONAL_MULTI_TURN_variation1() {
    double source = -721.5d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void POSITIVE_REMAINDER_NEAR_ZERO_variation1() {
    double source = 360.0d + Math.ulp(360.0d);
    assertMetamorphicRelationFor(source);
  }

  @Test
  void NEGATIVE_REMAINDER_NEAR_ZERO_variation1() {
    double source = -360.0d - Math.ulp(360.0d);
    assertMetamorphicRelationFor(source);
  }

  @Test
  void FOLLOWUP_FROM_NEGATIVE_ENDPOINT_variation1() {
    double source = Math.copySign(180.0d, -1.0d);
    assertMetamorphicRelationFor(source);
  }

  @Test
  void FOLLOWUP_FROM_POSITIVE_ENDPOINT_variation1() {
    double source = 360.0d / 2.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void FOLLOWUP_FROM_ZERO_variation1() {
    double source = Math.copySign(0.0d, 1.0d);
    assertMetamorphicRelationFor(source);
  }

  @Test
  void LARGE_POSITIVE_DISTINCT_FOLLOWUP_variation1() {
    double source = 1.0e12;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void LARGE_NEGATIVE_DISTINCT_FOLLOWUP_variation1() {
    double source = -1.0e12;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void POSITIVE_DOUBLE_PRECISION_LIMIT_variation1() {
    double source = 9007199254740992.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void NEGATIVE_DOUBLE_PRECISION_LIMIT_variation1() {
    double source = -9007199254740992.0d;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void MAX_FINITE_POSITIVE_variation1() {
    double source = Double.MAX_VALUE;
    assertMetamorphicRelationFor(source);
  }

  @Test
  void MAX_FINITE_NEGATIVE_variation1() {
    double source = -Double.MAX_VALUE;
    assertMetamorphicRelationFor(source);
  }
}
