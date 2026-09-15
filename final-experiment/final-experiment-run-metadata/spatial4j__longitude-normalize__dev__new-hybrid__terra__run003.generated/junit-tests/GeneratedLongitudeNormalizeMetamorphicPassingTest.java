import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

  @Test
  void signedZero_positiveZero() {
    double source = 0.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void signedZero_negativeZero() {
    double source = -0.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void smallestSubnormalMagnitudes_positiveMinimum() {
    double source = Double.MIN_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void smallestSubnormalMagnitudes_negativeMinimum() {
    double source = -Double.MIN_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ordinaryInteriorFraction_fractionalLongitude() {
    double source = 45.25d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void negativeAntimeridianEndpoint_negative180() {
    double source = -180.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void positiveAntimeridianEndpoint_positive180() {
    double source = 180.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void justInsideEndpoints_aboveNegative180() {
    double source = Math.nextUp(-180.0d);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void justInsideEndpoints_belowPositive180() {
    double source = Math.nextDown(180.0d);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void justOutsideEndpoints_belowNegative180() {
    double source = Math.nextDown(-180.0d);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void justOutsideEndpoints_abovePositive180() {
    double source = Math.nextUp(180.0d);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void positiveOutOfRangeNonzeroRemainder_positive181() {
    double source = 181.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void negativeOutOfRangeNegativeRemainder_negative181() {
    double source = -181.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void positiveExactModuloZero_positive540() {
    double source = 540.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void negativeExactModuloZero_negative540() {
    double source = -540.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void exactFullRevolutions_positive360() {
    double source = 360.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void exactFullRevolutions_negative360() {
    double source = -360.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void multipleRevolutionsWithFractionalResidue_positive1081Point25() {
    double source = 1081.25d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void multipleRevolutionsWithFractionalResidue_negative1081Point25() {
    double source = -1081.25d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void additionQuantizedAtLargeMagnitude_positivePowerOfTwo() {
    double source = Math.scalb(1.0d, 62);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void maximumFiniteMagnitudes_positiveMaximum() {
    double source = Double.MAX_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void maximumFiniteMagnitudes_negativeMaximum() {
    double source = -Double.MAX_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void nanInput_notANumber() {
    double source = Double.NaN;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void infiniteInputs_positiveInfinity() {
    double source = Double.POSITIVE_INFINITY;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void infiniteInputs_negativeInfinity() {
    double source = Double.NEGATIVE_INFINITY;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }
}
