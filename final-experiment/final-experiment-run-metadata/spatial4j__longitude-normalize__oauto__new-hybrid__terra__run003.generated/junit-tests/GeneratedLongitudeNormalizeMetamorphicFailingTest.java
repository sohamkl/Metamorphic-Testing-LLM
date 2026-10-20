import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeMetamorphicFailingTest {

  private static double generateFollowUp(double longitudeDeg) {
    return longitudeDeg + 360.0;
  }

  @Test
  void ROUNDED_FOLLOW_UP_INCREMENT_AT_2_POW_59_positive() {
    double source = 576460752303423488.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ROUNDED_FOLLOW_UP_INCREMENT_AT_2_POW_59_negative() {
    double source = -576460752303423488.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ROUNDED_FOLLOW_UP_INCREMENT_AT_2_POW_60_positive() {
    double source = 1152921504606846976.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ROUNDED_FOLLOW_UP_INCREMENT_AT_2_POW_60_negative() {
    double source = -1152921504606846976.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ROUNDED_FOLLOW_UP_INCREMENT_AT_2_POW_61_positive() {
    double source = 2305843009213693952.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }

  @Test
  void ROUNDED_FOLLOW_UP_INCREMENT_AT_2_POW_61_negative() {
    double source = -2305843009213693952.0;
    double sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(source);
    double followUp = generateFollowUp(source);
    double followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
    LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
  }
}
