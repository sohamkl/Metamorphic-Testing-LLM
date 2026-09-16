import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

  private static double generateFollowUp(double source) {
    return source + 360.0;
  }

  private static void assertMetamorphicRelationFor(double source) {
    Assertions.assertTrue(Double.isFinite(source));

    double followUp = generateFollowUp(source);
    double sourceOutput = DistanceUtils.normLonDEG(source);
    double followUpOutput = DistanceUtils.normLonDEG(followUp);

    Assertions.assertTrue(Double.isFinite(sourceOutput));
    Assertions.assertTrue(Double.isFinite(followUpOutput));
    Assertions.assertTrue(sourceOutput >= -180.0 && sourceOutput <= 180.0);
    Assertions.assertTrue(followUpOutput >= -180.0 && followUpOutput <= 180.0);

    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  private static void assertMetamorphicRelation(
      double sourceOutput, double followUpOutput) {
    double separation = Math.abs(sourceOutput - followUpOutput);
    Assertions.assertTrue(
        sourceOutput == followUpOutput || separation == 360.0,
        () -> "Normalized longitudes are not circularly equivalent: "
            + sourceOutput + " and " + followUpOutput);
  }

  @Test
  public void IMMEDIATELY_INSIDE_DATELINE_ENDPOINTS_upperNeighbor() {
    assertMetamorphicRelationFor(Math.nextDown(180.0));
  }

  @Test
  public void TINY_FINITE_LONGITUDES_positiveSubnormal() {
    assertMetamorphicRelationFor(Double.MIN_VALUE);
  }

  @Test
  public void TINY_FINITE_LONGITUDES_negativeSubnormal() {
    assertMetamorphicRelationFor(-Double.MIN_VALUE);
  }

  @Test
  public void NEIGHBORS_OF_OUT_OF_RANGE_DATELINE_SENTINELS_belowPositiveSentinel() {
    assertMetamorphicRelationFor(Math.nextDown(540.0));
  }

  @Test
  public void NEIGHBORS_OF_OUT_OF_RANGE_DATELINE_SENTINELS_abovePositiveSentinel() {
    assertMetamorphicRelationFor(Math.nextUp(540.0));
  }

  @Test
  public void LARGE_ROUNDED_FOLLOW_UP_INCREMENT_positivePowerOfTwo() {
    double source = Math.scalb(1.0, 60);
    Assertions.assertNotEquals(source, generateFollowUp(source));
    Assertions.assertNotEquals(360.0, generateFollowUp(source) - source);
    assertMetamorphicRelationFor(source);
  }

  @Test
  public void LARGE_ROUNDED_FOLLOW_UP_INCREMENT_negativePowerOfTwo() {
    double source = -Math.scalb(1.0, 60);
    Assertions.assertNotEquals(source, generateFollowUp(source));
    Assertions.assertNotEquals(360.0, generateFollowUp(source) - source);
    assertMetamorphicRelationFor(source);
  }

  @Test
  public void LARGE_ROUNDED_FOLLOW_UP_INCREMENT_coarserPositiveSpacing() {
    double source = Math.scalb(1.0, 61);
    Assertions.assertNotEquals(source, generateFollowUp(source));
    Assertions.assertNotEquals(360.0, generateFollowUp(source) - source);
    assertMetamorphicRelationFor(source);
  }
}
