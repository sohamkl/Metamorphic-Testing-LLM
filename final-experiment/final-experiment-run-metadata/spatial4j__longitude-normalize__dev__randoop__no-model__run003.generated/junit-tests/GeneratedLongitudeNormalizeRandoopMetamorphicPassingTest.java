import org.junit.jupiter.api.Test;

public class GeneratedLongitudeNormalizeRandoopMetamorphicPassingTest {

    @Test
    public void testShape001() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.DEG_90_AS_RADS;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape002() {
        double double0 = (-1.0d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape003() {
        short short0 = (short)0;
        double double1 = org.locationtech.spatial4j.distance.DistanceUtils.toRadians((double) (short)0);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double1);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double1);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape004() {
        float float0 = 100.0f;
        double double1 = 1.5707963267948966d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.degrees2Dist((double) 100.0f, 1.5707963267948966d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape005() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.SIN_45_AS_RADS;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape006() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.KM_TO_MILES;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape007() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.DEG_180_AS_RADS;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape008() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.RADIANS_TO_DEGREES;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape009() {
        float float0 = 1.0f;
        double double1 = org.locationtech.spatial4j.distance.DistanceUtils.normLatDEG((double) 1.0f);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double1);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double1);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape010() {
        double double0 = 1.0d;
        long long1 = 10L;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.dist2Degrees(1.0d, (double) 10L);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape011() {
        byte byte0 = (byte)0;
        double double1 = 3.141592653589793d;
        double double2 = 0.621371192d;
        long long3 = 100L;
        double double4 = org.locationtech.spatial4j.distance.DistanceUtils.distVincentyRAD((double) (byte)0, 3.141592653589793d, 0.621371192d, (double) 100L);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double4);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double4);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape012() {
        long long0 = 100L;
        byte byte1 = (byte)0;
        double double2 = 2.3477844865449944d;
        double double3 = (-1.0d);
        double double4 = org.locationtech.spatial4j.distance.DistanceUtils.distHaversineRAD((double) 100L, (double) (byte)0, 2.3477844865449944d, (-1.0d));
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double4);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double4);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape013() {
        int int0 = 100;
        double double1 = org.locationtech.spatial4j.distance.DistanceUtils.toDegrees((double) 100);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double1);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double1);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape014() {
        double double0 = 3.141592653589793d;
        float float1 = 100.0f;
        short short2 = (short)100;
        double double3 = 2.741556778080377d;
        double double4 = org.locationtech.spatial4j.distance.DistanceUtils.distVincentyRAD(3.141592653589793d, (double) 100.0f, (double) (short)100, 2.741556778080377d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double4);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double4);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape015() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.DEG_45_AS_RADS;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape016() {
        byte byte0 = (byte)-1;
        byte byte1 = (byte)1;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.degrees2Dist((double) (byte)-1, (double) (byte)1);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape017() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.MILES_TO_KM;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape018() {
        double double0 = 100.0d;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape019() {
        double double0 = 100.0d;
        double double1 = 1.0d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.calcBoxByDistFromPt_deltaLonDEG(100.0d, 1.0d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape020() {
        short short0 = (short)1;
        double double1 = 2.3477844865449944d;
        short short2 = (short)-1;
        double double3 = org.locationtech.spatial4j.distance.DistanceUtils.calcBoxByDistFromPt_latHorizAxisDEG((double) (short)1, 2.3477844865449944d, (double) (short)-1);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double3);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double3);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape021() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.DEGREES_TO_RADIANS;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape022() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.EARTH_MEAN_RADIUS_MI;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape023() {
        long long0 = 1L;
        double double1 = 1.6093440006146922d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.dist2Degrees((double) 1L, 1.6093440006146922d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape024() {
        double double0 = 0.017453292519943295d;
        int int1 = 0;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.dist2Radians(0.017453292519943295d, (double) 0);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape025() {
        short short0 = (short)-1;
        double double1 = 0.0d;
        float float2 = 100.0f;
        double double3 = org.locationtech.spatial4j.distance.DistanceUtils.calcBoxByDistFromPt_latHorizAxisDEG((double) (short)-1, 0.0d, (double) 100.0f);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double3);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double3);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape026() {
        double double7 = (-0.017453292519943295d);
        double double8 = 90.0d;
        double double9 = 90.0d;
        double[] doubleArray10 = new double[] { (-0.017453292519943295d), 90.0d, 90.0d };
        double double11 = 1.0001523435165864d;
        byte byte12 = (byte)0;
        double double13 = 1.0d;
        int int14 = (-1);
        double[] doubleArray15 = new double[] { 1.0001523435165864d, (byte)0, 1.0d, (-1) };
        double double16 = org.locationtech.spatial4j.distance.DistanceUtils.distSquaredCartesian(doubleArray10, doubleArray15);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double16);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double16);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape027() {
        double double0 = 3958.7613145272735d;
        double double1 = 2.329141438490134d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.calcBoxByDistFromPt_deltaLonDEG(3958.7613145272735d, 2.329141438490134d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape028() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.EARTH_EQUATORIAL_RADIUS_KM;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape029() {
        byte byte0 = (byte)10;
        double double1 = (-1.0d);
        double double2 = (-0.017453292519943295d);
        double double3 = org.locationtech.spatial4j.distance.DistanceUtils.calcBoxByDistFromPt_latHorizAxisDEG((double) (byte)10, (-1.0d), (-0.017453292519943295d));
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double3);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double3);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape030() {
        double double0 = 2.3477844865449944d;
        double double1 = org.locationtech.spatial4j.distance.DistanceUtils.toRadians(2.3477844865449944d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double1);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double1);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape031() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.KM_TO_DEG;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape032() {
        double double3 = 76.12768733172109d;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double3);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double3);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape033() {
        double double0 = 35.60194681261314d;
        double double1 = 2.741556778080377d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.calcLonDegreesAtLat(35.60194681261314d, 2.741556778080377d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape034() {
        short short0 = (short)-1;
        double double1 = 0.0d;
        double double2 = 35.60194681261314d;
        double double3 = 5.729577951308233d;
        double double4 = org.locationtech.spatial4j.distance.DistanceUtils.distLawOfCosinesRAD((double) (short)-1, 0.0d, 35.60194681261314d, 5.729577951308233d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double4);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double4);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape035() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.EARTH_MEAN_RADIUS_KM;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape036() {
        double double0 = 316.7597849742581d;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape037() {
        double double0 = 316.7597849742581d;
        double double1 = 316.7597849742581d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.radians2Dist(316.7597849742581d, 316.7597849742581d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape038() {
        int int0 = (-1);
        float float1 = 10.0f;
        byte byte2 = (byte)1;
        double double3 = (-1.0d);
        double double4 = org.locationtech.spatial4j.distance.DistanceUtils.distVincentyRAD((double) (-1), (double) 10.0f, (double) (byte)1, (-1.0d));
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double4);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double4);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape039() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.DEG_225_AS_RADS;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape040() {
        short short0 = (short)-1;
        double double1 = 1.5707963267948966d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.dist2Radians((double) (short)-1, 1.5707963267948966d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape041() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.DEG_270_AS_RADS;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape042() {
        float float0 = 10.0f;
        double double1 = 2.3296861501571087d;
        double double2 = 57.29577951308232d;
        int int3 = (-1);
        double double4 = org.locationtech.spatial4j.distance.DistanceUtils.distHaversineRAD((double) 10.0f, 2.3296861501571087d, 57.29577951308232d, (double) (-1));
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double4);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double4);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape043() {
        double double3 = 10.0d;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double3);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double3);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape044() {
        double double0 = 0.008993203677616635d;
        double double1 = 3.141592653589793d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.degrees2Dist(0.008993203677616635d, 3.141592653589793d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape045() {
        int int0 = (-1);
        double double1 = 4.931075699805452E-4d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.radians2Dist((double) (-1), 4.931075699805452E-4d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape046() {
        byte byte0 = (byte)100;
        double double1 = 2.3296861501571087d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.calcBoxByDistFromPt_deltaLonDEG((double) (byte)100, 2.3296861501571087d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape047() {
        double double0 = 76.12768733172109d;
        byte byte1 = (byte)100;
        double double2 = 2.3296861501571087d;
        int int3 = 10;
        double double4 = org.locationtech.spatial4j.distance.DistanceUtils.distLawOfCosinesRAD(76.12768733172109d, (double) (byte)100, 2.3296861501571087d, (double) 10);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double4);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double4);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape048() {
        double double0 = org.locationtech.spatial4j.distance.DistanceUtils.DEG_TO_KM;
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double0);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double0);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape049() {
        byte byte0 = (byte)100;
        short short1 = (short)100;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.radians2Dist((double) (byte)100, (double) (short)100);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape050() {
        double double0 = 76.12768733172109d;
        double double1 = 2.329141438490134d;
        double double2 = org.locationtech.spatial4j.distance.DistanceUtils.calcLonDegreesAtLat(76.12768733172109d, 2.329141438490134d);
        var followUp = LongitudeNormalizeMetamorphicSpec.generateFollowUp(double2);
        var sourceOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(double2);
        var followUpOutput = org.locationtech.spatial4j.distance.DistanceUtils.normLonDEG(followUp);
        LongitudeNormalizeMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
