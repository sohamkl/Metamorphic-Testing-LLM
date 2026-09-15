import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

  @Test
  void additionQuantizedAtLargeMagnitude_negativePowerOfTwo() {
    double source = -Math.scalb(1.0d, 62);
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }
}
