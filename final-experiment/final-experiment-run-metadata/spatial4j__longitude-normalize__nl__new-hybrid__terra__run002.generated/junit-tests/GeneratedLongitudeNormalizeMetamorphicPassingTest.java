import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicPassingTest {

  private double generateFollowUp(double source) {
    return source + 360.0d;
  }

  private void assertMetamorphicRelationFor(double source) {
    Assertions.assertTrue(Double.isFinite(source));
    double sourceOutput = DistanceUtils.normLonDEG(source);
    double followUpOutput = DistanceUtils.normLonDEG(generateFollowUp(source));
    Assertions.assertTrue(Double.isFinite(sourceOutput));
    Assertions.assertTrue(Double.isFinite(followUpOutput));
    Assertions.assertTrue(sourceOutput >= -180.0d && sourceOutput <= 180.0d);
    Assertions.assertTrue(followUpOutput >= -180.0d && followUpOutput <= 180.0d);
    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  private void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
    Assertions.assertEquals(canonicalGeographicLongitude(sourceOutput),
        canonicalGeographicLongitude(followUpOutput));
  }

  private double canonicalGeographicLongitude(double longitude) {
    if (longitude == 0.0d) {
      return 0.0d;
    }
    if (longitude == -180.0d || longitude == 180.0d) {
      return 180.0d;
    }
    return longitude;
  }

  @Test
  void SIGNED_ZERO_positiveZero() {
    assertMetamorphicRelationFor(0.0d);
  }

  @Test
  void SIGNED_ZERO_negativeZero() {
    assertMetamorphicRelationFor(-0.0d);
  }

  @Test
  void ORDINARY_INTERIOR_INTEGERS_negative179() {
    assertMetamorphicRelationFor(-179.0d);
  }

  @Test
  void ORDINARY_INTERIOR_INTEGERS_negative1() {
    assertMetamorphicRelationFor(-1.0d);
  }

  @Test
  void ORDINARY_INTERIOR_INTEGERS_positive1() {
    assertMetamorphicRelationFor(1.0d);
  }

  @Test
  void ORDINARY_INTERIOR_INTEGERS_positive179() {
    assertMetamorphicRelationFor(179.0d);
  }

  @Test
  void ORDINARY_INTERIOR_FRACTIONS_negative123Point75() {
    assertMetamorphicRelationFor(-123.75d);
  }

  @Test
  void ORDINARY_INTERIOR_FRACTIONS_negativeHalf() {
    assertMetamorphicRelationFor(-0.5d);
  }

  @Test
  void ORDINARY_INTERIOR_FRACTIONS_positiveHalf() {
    assertMetamorphicRelationFor(0.5d);
  }

  @Test
  void ORDINARY_INTERIOR_FRACTIONS_positive123Point75() {
    assertMetamorphicRelationFor(123.75d);
  }

  @Test
  void EXACT_NORMALIZATION_ENDPOINTS_negative180() {
    assertMetamorphicRelationFor(-180.0d);
  }

  @Test
  void EXACT_NORMALIZATION_ENDPOINTS_positive180() {
    assertMetamorphicRelationFor(180.0d);
  }

  @Test
  void IMMEDIATELY_OUTSIDE_NORMALIZATION_ENDPOINTS_abovePositiveEndpoint() {
    assertMetamorphicRelationFor(Math.nextUp(180.0d));
  }

  @Test
  void IMMEDIATELY_OUTSIDE_NORMALIZATION_ENDPOINTS_belowNegativeEndpoint() {
    assertMetamorphicRelationFor(Math.nextDown(-180.0d));
  }

  @Test
  void POSITIVE_FIRST_TURN_NON_SEAM_181() {
    assertMetamorphicRelationFor(181.0d);
  }

  @Test
  void POSITIVE_FIRST_TURN_NON_SEAM_360() {
    assertMetamorphicRelationFor(360.0d);
  }

  @Test
  void POSITIVE_FIRST_TURN_NON_SEAM_539() {
    assertMetamorphicRelationFor(539.0d);
  }

  @Test
  void POSITIVE_EXACT_180_SEAMS_540() {
    assertMetamorphicRelationFor(540.0d);
  }

  @Test
  void POSITIVE_EXACT_180_SEAMS_900() {
    assertMetamorphicRelationFor(900.0d);
  }

  @Test
  void POSITIVE_POST_SEAM_VALUES_541() {
    assertMetamorphicRelationFor(541.0d);
  }

  @Test
  void POSITIVE_POST_SEAM_VALUES_901() {
    assertMetamorphicRelationFor(901.0d);
  }

  @Test
  void NEGATIVE_FIRST_TURN_VALUES_negative181() {
    assertMetamorphicRelationFor(-181.0d);
  }

  @Test
  void NEGATIVE_FIRST_TURN_VALUES_negative360() {
    assertMetamorphicRelationFor(-360.0d);
  }

  @Test
  void NEGATIVE_FIRST_TURN_VALUES_negative539() {
    assertMetamorphicRelationFor(-539.0d);
  }

  @Test
  void NEGATIVE_EXACT_180_SEAMS_negative540() {
    assertMetamorphicRelationFor(-540.0d);
  }

  @Test
  void NEGATIVE_EXACT_180_SEAMS_negative900() {
    assertMetamorphicRelationFor(-900.0d);
  }

  @Test
  void NEGATIVE_POST_SEAM_VALUES_negative541() {
    assertMetamorphicRelationFor(-541.0d);
  }

  @Test
  void NEGATIVE_POST_SEAM_VALUES_negative901() {
    assertMetamorphicRelationFor(-901.0d);
  }

  @Test
  void MULTI_TURN_POSITIVE_FRACTIONS_12345Point678() {
    assertMetamorphicRelationFor(12345.678d);
  }

  @Test
  void MULTI_TURN_POSITIVE_FRACTIONS_987654Point125() {
    assertMetamorphicRelationFor(987654.125d);
  }

  @Test
  void MULTI_TURN_NEGATIVE_FRACTIONS_negative12345Point678() {
    assertMetamorphicRelationFor(-12345.678d);
  }

  @Test
  void MULTI_TURN_NEGATIVE_FRACTIONS_negative987654Point125() {
    assertMetamorphicRelationFor(-987654.125d);
  }

  @Test
  void LARGE_VALUES_WITH_EXACTLY_REPRESENTABLE_INCREMENT_positiveTwoPow52() {
    assertMetamorphicRelationFor(4503599627370496.0d);
  }

  @Test
  void LARGE_VALUES_WITH_EXACTLY_REPRESENTABLE_INCREMENT_negativeTwoPow52() {
    assertMetamorphicRelationFor(-4503599627370496.0d);
  }

  @Test
  void LARGE_VALUES_WITH_UNCHANGED_FOLLOWUP_positiveTwoPow62() {
    assertMetamorphicRelationFor(4611686018427387904.0d);
  }

  @Test
  void MAXIMUM_FINITE_MAGNITUDES_positiveMaximum() {
    assertMetamorphicRelationFor(Double.MAX_VALUE);
  }

  @Test
  void MAXIMUM_FINITE_MAGNITUDES_negativeMaximum() {
    assertMetamorphicRelationFor(-Double.MAX_VALUE);
  }
}
