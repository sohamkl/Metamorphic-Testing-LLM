import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

  private static double generateFollowUp(double longitudeDeg) {
    return longitudeDeg + 360.0;
  }

  @Test
  void SIGNED_ZERO_FAST_PATH_positiveZero() {
    double source = 0.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void SIGNED_ZERO_FAST_PATH_negativeZero() {
    double source = -0.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ORDINARY_INTEGER_INTERIOR_FAST_PATH_negativeNinety() {
    double source = -90.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ORDINARY_INTEGER_INTERIOR_FAST_PATH_positiveNinety() {
    double source = 90.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ORDINARY_FRACTIONAL_INTERIOR_FAST_PATH_negativeFraction() {
    double source = -45.5;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ORDINARY_FRACTIONAL_INTERIOR_FAST_PATH_positiveFraction() {
    double source = 45.5;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void EXACT_ANTIMERIDIAN_FAST_PATH_negativeEndpoint() {
    double source = -180.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void EXACT_ANTIMERIDIAN_FAST_PATH_positiveEndpoint() {
    double source = 180.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_INSIDE_RANGE_ENDPOINTS_aboveNegativeEndpoint() {
    double source = Math.nextUp(-180.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_INSIDE_RANGE_ENDPOINTS_belowPositiveEndpoint() {
    double source = Math.nextDown(180.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_OUTSIDE_RANGE_ENDPOINTS_belowNegativeEndpoint() {
    double source = Math.nextDown(-180.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_OUTSIDE_RANGE_ENDPOINTS_abovePositiveEndpoint() {
    double source = Math.nextUp(180.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void FIRST_WHOLE_DEGREE_OUTSIDE_ENDPOINTS_negative() {
    double source = -181.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void FIRST_WHOLE_DEGREE_OUTSIDE_ENDPOINTS_positive() {
    double source = 181.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void EXACT_ONE_REVOLUTION_FROM_ZERO_negative() {
    double source = -360.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void EXACT_ONE_REVOLUTION_FROM_ZERO_positive() {
    double source = 360.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_TO_ONE_REVOLUTION_negativeBelow() {
    double source = Math.nextDown(-360.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_TO_ONE_REVOLUTION_negativeAbove() {
    double source = Math.nextUp(-360.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_TO_ONE_REVOLUTION_positiveBelow() {
    double source = Math.nextDown(360.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_TO_ONE_REVOLUTION_positiveAbove() {
    double source = Math.nextUp(360.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void EXACT_OUTER_ANTIMERIDIAN_WRAP_negative() {
    double source = -540.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void EXACT_OUTER_ANTIMERIDIAN_WRAP_positive() {
    double source = 540.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_TO_OUTER_ANTIMERIDIAN_WRAP_negativeBelow() {
    double source = Math.nextDown(-540.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_TO_OUTER_ANTIMERIDIAN_WRAP_negativeAbove() {
    double source = Math.nextUp(-540.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_TO_OUTER_ANTIMERIDIAN_WRAP_positiveBelow() {
    double source = Math.nextDown(540.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ADJACENT_TO_OUTER_ANTIMERIDIAN_WRAP_positiveAbove() {
    double source = Math.nextUp(540.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void FRACTIONAL_MULTI_REVOLUTION_VALUES_negative() {
    double source = -900.25;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void FRACTIONAL_MULTI_REVOLUTION_VALUES_positive() {
    double source = 900.25;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void LARGE_MODERATE_MAGNITUDE_FRACTIONAL_VALUES_negative() {
    double source = -1000000.25;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void LARGE_MODERATE_MAGNITUDE_FRACTIONAL_VALUES_positive() {
    double source = 1000000.25;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void SUBNORMAL_IN_RANGE_VALUES_positiveMinimum() {
    double source = Double.MIN_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void SUBNORMAL_IN_RANGE_VALUES_negativeMinimum() {
    double source = -Double.MIN_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void MINIMUM_NORMAL_IN_RANGE_VALUES_positive() {
    double source = Double.MIN_NORMAL;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void MINIMUM_NORMAL_IN_RANGE_VALUES_negative() {
    double source = -Double.MIN_NORMAL;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void LARGE_FINITE_EXACT_360_INCREMENT_AT_2_POW_52_positive() {
    double source = 4503599627370496.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void LARGE_FINITE_EXACT_360_INCREMENT_AT_2_POW_52_negative() {
    double source = -4503599627370496.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void LARGE_FINITE_EXACT_360_INCREMENT_AT_2_POW_53_positive() {
    double source = 9007199254740992.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void LARGE_FINITE_EXACT_360_INCREMENT_AT_2_POW_53_negative() {
    double source = -9007199254740992.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void MAXIMUM_FINITE_MAGNITUDE_VALUES_positive() {
    double source = Double.MAX_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void MAXIMUM_FINITE_MAGNITUDE_VALUES_negative() {
    double source = -Double.MAX_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void INFINITE_SOURCE_VALUES_positiveInfinity() {
    double source = Double.POSITIVE_INFINITY;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void INFINITE_SOURCE_VALUES_negativeInfinity() {
    double source = Double.NEGATIVE_INFINITY;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NAN_SOURCE_VALUE_nan() {
    double source = Double.NaN;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }
}
