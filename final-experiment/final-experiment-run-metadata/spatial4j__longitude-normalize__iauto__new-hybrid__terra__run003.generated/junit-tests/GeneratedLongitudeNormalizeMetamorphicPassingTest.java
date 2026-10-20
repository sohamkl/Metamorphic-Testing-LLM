import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

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
  void POSITIVE_ZERO_FAST_RETURN_positiveZero() {
    double source = 0.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_ZERO_FAST_RETURN_negativeZero() {
    double source = -0.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void INTERIOR_FRACTIONAL_LONGITUDE_fractionalInterior() {
    double source = 17.25d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void MINIMUM_POSITIVE_SUBNORMAL_smallestPositive() {
    double source = Double.MIN_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void MINIMUM_NEGATIVE_SUBNORMAL_smallestNegative() {
    double source = -Double.MIN_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_INSIDE_NEGATIVE_BOUND_nextUpNegative180() {
    double source = Math.nextUp(-180.0d);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_INSIDE_POSITIVE_BOUND_nextDownPositive180() {
    double source = Math.nextDown(180.0d);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void EXACT_NEGATIVE_ANTIMERIDIAN_negativeEndpoint() {
    double source = -180.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void EXACT_POSITIVE_ANTIMERIDIAN_positiveEndpoint() {
    double source = 180.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_OUTSIDE_NEGATIVE_BOUND_nextDownNegative180() {
    double source = Math.nextDown(-180.0d);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void JUST_OUTSIDE_POSITIVE_BOUND_nextUpPositive180() {
    double source = Math.nextUp(180.0d);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_ONE_DEGREE_WRAP_negative181() {
    double source = -181.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_ONE_DEGREE_WRAP_positive181() {
    double source = 181.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_FULL_REVOLUTION_negative360() {
    double source = -360.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_FULL_REVOLUTION_positive360() {
    double source = 360.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_EXACT_ANTIMERIDIAN_RESIDUE_negative540() {
    double source = -540.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_EXACT_ANTIMERIDIAN_RESIDUE_positive540() {
    double source = 540.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_NONZERO_MULTIPLE_CYCLE_negative541() {
    double source = -541.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_NONZERO_MULTIPLE_CYCLE_positive541() {
    double source = 541.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void LARGE_POSITIVE_MANY_REVOLUTIONS_largePositiveResidue() {
    double source = 360000181.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void LARGE_NEGATIVE_MANY_REVOLUTIONS_largeNegativeResidue() {
    double source = -360000181.0d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_ANTIMERIDIAN_TOLERANCE_BAND_insidePositiveBand() {
    double source = 179.9999999995d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_ANTIMERIDIAN_TOLERANCE_BAND_insideNegativeBand() {
    double source = -179.9999999995d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_OUTSIDE_ANTIMERIDIAN_TOLERANCE_outsidePositiveBand() {
    double source = 179.999999998d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_OUTSIDE_ANTIMERIDIAN_TOLERANCE_outsideNegativeBand() {
    double source = -179.999999998d;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void POSITIVE_MAX_FINITE_LONGITUDE_maxFinite() {
    double source = Double.MAX_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  @Test
  void NEGATIVE_MAX_FINITE_LONGITUDE_minFinite() {
    double source = -Double.MAX_VALUE;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }
}
