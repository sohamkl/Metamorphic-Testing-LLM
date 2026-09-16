import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.distance.DistanceUtils;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

  private double generateFollowUp(double source) {
    return source + 360.0d;
  }

  private void assertMetamorphicRelationFor(double source) {
    Assertions.assertTrue(Double.isFinite(source));
    double followUp = generateFollowUp(source);
    Assertions.assertEquals(360.0d, followUp - source);

    double sourceOutput = DistanceUtils.normLonDEG(source);
    double followUpOutput = DistanceUtils.normLonDEG(followUp);

    assertMetamorphicRelation(sourceOutput, followUpOutput);
  }

  private void assertMetamorphicRelation(double sourceOutput, double followUpOutput) {
    boolean sameCircularLongitude =
        sourceOutput == followUpOutput
            || (sourceOutput == -180.0d && followUpOutput == 180.0d)
            || (sourceOutput == 180.0d && followUpOutput == -180.0d);

    Assertions.assertTrue(
        sameCircularLongitude,
        () -> "Expected circularly equivalent normalized longitudes but got "
            + sourceOutput + " and " + followUpOutput);
  }

}
