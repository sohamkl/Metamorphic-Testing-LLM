import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

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
  void IN_RANGE_VALUES_ADJACENT_TO_ENDPOINTS_belowPositiveEndpoint() {
    assertMetamorphicRelationFor(Math.nextDown(180.0d));
  }

  @Test
  void IN_RANGE_VALUES_ADJACENT_TO_ENDPOINTS_aboveNegativeEndpoint() {
    assertMetamorphicRelationFor(Math.nextUp(-180.0d));
  }

  @Test
  void LARGE_VALUES_WITH_ROUNDED_FOLLOWUP_INCREMENT_positiveTwoPow61() {
    assertMetamorphicRelationFor(2305843009213693952.0d);
  }

  @Test
  void LARGE_VALUES_WITH_ROUNDED_FOLLOWUP_INCREMENT_negativeTwoPow61() {
    assertMetamorphicRelationFor(-2305843009213693952.0d);
  }

  @Test
  void LARGE_VALUES_WITH_UNCHANGED_FOLLOWUP_negativeTwoPow62() {
    assertMetamorphicRelationFor(-4611686018427387904.0d);
  }
}
