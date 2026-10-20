import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

  @Test
  void POSITIVE_ZERO_DIRECT_positiveZero() {
    double source = 0.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_ZERO_DIRECT_negativeZero() {
    double source = -0.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_INTERIOR_DIRECT_fractionalPositive() {
    double source = 12.5;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_INTERIOR_DIRECT_fractionalNegative() {
    double source = -12.5;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void LOWER_ENDPOINT_DIRECT_inclusiveLowerBound() {
    double source = -180.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void UPPER_ENDPOINT_DIRECT_inclusiveUpperBound() {
    double source = 180.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_INSIDE_LOWER_ENDPOINT_nextUp() {
    double source = Math.nextUp(-180.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_INSIDE_UPPER_ENDPOINT_nextDown() {
    double source = Math.nextDown(180.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_BELOW_LOWER_ENDPOINT_nextDown() {
    double source = Math.nextDown(-180.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_ABOVE_UPPER_ENDPOINT_nextUp() {
    double source = Math.nextUp(180.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_ONE_DEGREE_OUTSIDE_negative181() {
    double source = -181.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_ONE_DEGREE_OUTSIDE_positive181() {
    double source = 181.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_EXACT_WRAP_SENTINEL_positive540() {
    double source = 540.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_EXACT_WRAP_SEAM_negative540() {
    double source = -540.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_BELOW_POSITIVE_WRAP_SEAM_nextDown() {
    double source = Math.nextDown(540.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_ABOVE_POSITIVE_WRAP_SEAM_nextUp() {
    double source = Math.nextUp(540.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_BELOW_NEGATIVE_WRAP_SEAM_nextDown() {
    double source = Math.nextDown(-540.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_ABOVE_NEGATIVE_WRAP_SEAM_nextUp() {
    double source = Math.nextUp(-540.0);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_MIN_SUBNORMAL_smallestPositive() {
    double source = Double.MIN_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_MIN_SUBNORMAL_smallestNegative() {
    double source = -Double.MIN_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_2_POW_53_PRECISION_exactIntegerScale() {
    double source = Math.scalb(1.0, 53);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_2_POW_53_PRECISION_exactIntegerScale() {
    double source = -Math.scalb(1.0, 53);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_LARGE_FINITE_SHIFT_RESOLUTION_decimalLarge() {
    double source = 1.0E20;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_LARGE_FINITE_SHIFT_RESOLUTION_decimalLarge() {
    double source = -1.0E20;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_MAX_FINITE_maxValue() {
    double source = Double.MAX_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_MAX_FINITE_minFiniteValue() {
    double source = -Double.MAX_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NAN_INPUT_notANumber() {
    double source = Double.NaN;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_INFINITY_INPUT_positiveInfinity() {
    double source = Double.POSITIVE_INFINITY;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_INFINITY_INPUT_negativeInfinity() {
    double source = Double.NEGATIVE_INFINITY;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }
}
