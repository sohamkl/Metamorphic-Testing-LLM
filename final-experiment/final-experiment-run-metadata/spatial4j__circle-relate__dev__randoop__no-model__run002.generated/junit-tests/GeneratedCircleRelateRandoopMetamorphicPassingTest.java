import org.junit.jupiter.api.Test;

public class GeneratedCircleRelateRandoopMetamorphicPassingTest {

    @Test
    public void testShape001() {
        org.locationtech.jts.geom.Coordinate coordinate2 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel9 = new org.locationtech.jts.geom.PrecisionModel();
        int int11 = 10;
        org.locationtech.jts.geom.Point point12 = new org.locationtech.jts.geom.Point(coordinate2, precisionModel9, 10);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext13 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint14 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point12, jtsSpatialContext13);
        char char15 = 'a';
        boolean boolean16 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext17 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl18 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint14, (double)'a', spatialContext17);
        double double20 = 1.0d;
        byte byte21 = (byte)-1;
        byte byte22 = (byte)-1;
        long long23 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext24 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl25 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext24);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext37 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl39 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl25, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext37);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input40 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl18, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl39);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input40);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input40);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape002() {
        org.locationtech.jts.geom.Coordinate coordinate37 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel48 = null;
        char char49 = 'a';
        org.locationtech.jts.geom.Point point50 = new org.locationtech.jts.geom.Point(coordinate37, precisionModel48, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext55 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint57 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point50, jtsSpatialContext55);
        short short58 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext63 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl65 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint57, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext63);
        double double68 = 100.0d;
        double double69 = 9.007199254740992E15d;
        byte byte70 = (byte)-1;
        double double71 = 1.0d;
        boolean boolean78 = true;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc79 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE;
        org.locationtech.spatial4j.shape.Rectangle rectangle80 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext81 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc79, rectangle80);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl83 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(100.0d, 9.007199254740992E15d, (double)(byte)-1, 1.0d, spatialContext81);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input84 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl65, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl83);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input84);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input84);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape003() {
        org.locationtech.jts.geom.Coordinate coordinate3 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel14 = null;
        char char15 = 'a';
        org.locationtech.jts.geom.Point point16 = new org.locationtech.jts.geom.Point(coordinate3, precisionModel14, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext21 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint23 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point16, jtsSpatialContext21);
        short short24 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext29 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl31 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint23, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext29);
        double double86 = 1.0d;
        byte byte87 = (byte)-1;
        byte byte88 = (byte)-1;
        long long89 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext90 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl91 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext90);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input92 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl31, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl91);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input92);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input92);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape004() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = null;
        char char12 = 'a';
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel11, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext18 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint20 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext18);
        short short21 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext26 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl28 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint20, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext26);
        double double31 = 100.0d;
        char char32 = '#';
        char char33 = '#';
        char char34 = '4';
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext35 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl36 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(100.0d, (double)'#', (double)'#', (double)'4', (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext35);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input37 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl28, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl36);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input37);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input37);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape005() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = null;
        char char12 = 'a';
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel11, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext18 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint20 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext18);
        int int29 = 10;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext34 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl36 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint20, (double)10, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext34);
        double double42 = 1.0d;
        byte byte43 = (byte)-1;
        byte byte44 = (byte)-1;
        long long45 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext46 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl47 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext46);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext53 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl54 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl47, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext53);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext81 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl84 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl54, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext81);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input85 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl36, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl84);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input85);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input85);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape006() {
        org.locationtech.jts.geom.Coordinate coordinate4 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = new org.locationtech.jts.geom.PrecisionModel();
        int int13 = 10;
        org.locationtech.jts.geom.Point point14 = new org.locationtech.jts.geom.Point(coordinate4, precisionModel11, 10);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext43 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint46 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point14, jtsSpatialContext43);
        double double52 = 1.0d;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext57 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl59 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint46, 1.0d, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext57);
        char char60 = ' ';
        char char61 = ' ';
        byte byte62 = (byte)1;
        char char63 = ' ';
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext82 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl85 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)' ', (double)' ', (double)(byte)1, (double)' ', (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext82);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input86 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl59, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl85);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input86);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input86);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape007() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = null;
        char char12 = 'a';
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel11, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext18 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint20 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext18);
        short short21 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext26 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl28 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint20, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext26);
        double double46 = 10.0d;
        float float47 = 1.0f;
        byte byte48 = (byte)0;
        long long49 = 0L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext54 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl56 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(10.0d, (double)1.0f, (double)(byte)0, (double)0L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext54);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input57 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl28, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl56);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input57);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input57);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape008() {
        short short0 = (short)10;
        short short1 = (short)-1;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext16 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl18 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)10, (double)(short)-1, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext16);
        float float19 = 10.0f;
        boolean boolean34 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext35 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl37 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl18, (double)10.0f, spatialContext35);
        double double39 = 1.0d;
        byte byte40 = (byte)-1;
        byte byte41 = (byte)-1;
        long long42 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext43 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl44 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext43);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input80 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl37, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl44);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input80);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input80);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape009() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = null;
        char char12 = 'a';
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel11, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext18 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint20 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext18);
        int int29 = 10;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext34 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl36 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint20, (double)10, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext34);
        double double73 = 100.0d;
        char char74 = '#';
        char char75 = '#';
        char char76 = '4';
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext77 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl78 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(100.0d, (double)'#', (double)'#', (double)'4', (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext77);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input85 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl36, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl78);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input85);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input85);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape010() {
        double double0 = (-1.0d);
        short short1 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext27 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl31 = new org.locationtech.spatial4j.shape.impl.PointImpl((-1.0d), (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext27);
        int int32 = 1;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext49 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl52 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl31, (double)1, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext49);
        double double54 = 1.0d;
        byte byte55 = (byte)-1;
        byte byte56 = (byte)-1;
        long long57 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext58 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl59 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext58);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext65 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl66 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl59, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext65);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input67 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl52, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl66);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input67);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input67);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape011() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = null;
        char char12 = 'a';
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel11, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext18 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint20 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext18);
        short short21 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext26 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl28 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint20, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext26);
        double double50 = 10.0d;
        short short51 = (short)-1;
        long long52 = 1L;
        float float53 = 100.0f;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext93 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl97 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(10.0d, (double)(short)-1, (double)1L, (double)100.0f, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext93);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input98 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl28, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl97);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input98);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input98);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape012() {
        org.locationtech.jts.geom.Coordinate coordinate5 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel16 = null;
        char char17 = 'a';
        org.locationtech.jts.geom.Point point18 = new org.locationtech.jts.geom.Point(coordinate5, precisionModel16, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext23 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint25 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point18, jtsSpatialContext23);
        int int34 = 10;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext39 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl41 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint25, (double)10, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext39);
        double double42 = 9.007199254740992E15d;
        char char43 = 'a';
        float float44 = 100.0f;
        double double45 = 100.0d;
        org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory jtsSpatialContextFactory89 = new org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory();
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext93 = new org.locationtech.spatial4j.context.jts.JtsSpatialContext(jtsSpatialContextFactory89);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl95 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(9.007199254740992E15d, (double)'a', (double)100.0f, 100.0d, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext93);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input96 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl41, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl95);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input96);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input96);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape013() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = null;
        char char12 = 'a';
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel11, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext18 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint20 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext18);
        short short21 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext26 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl28 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint20, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext26);
        float float51 = 0.0f;
        int int52 = 1;
        double double53 = (-1.0d);
        float float54 = 100.0f;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext84 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl87 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)0.0f, (double)1, (-1.0d), (double)100.0f, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext84);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input88 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl28, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl87);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input88);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input88);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape014() {
        long long0 = 10L;
        short short1 = (short)1;
        org.locationtech.spatial4j.context.SpatialContextFactory spatialContextFactory2 = new org.locationtech.spatial4j.context.SpatialContextFactory();
        org.locationtech.spatial4j.context.SpatialContext spatialContext4 = new org.locationtech.spatial4j.context.SpatialContext(spatialContextFactory2);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl5 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)10L, (double)(short)1, spatialContext4);
        long long43 = 1L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext70 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl73 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl5, (double)1L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext70);
        double double74 = 1.0d;
        byte byte75 = (byte)-1;
        byte byte76 = (byte)-1;
        long long77 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext78 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl79 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext78);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input80 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl73, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl79);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input80);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input80);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape015() {
        short short53 = (short)1;
        double double54 = 10.0d;
        boolean boolean55 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext56 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl57 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)1, 10.0d, spatialContext56);
        long long58 = 0L;
        boolean boolean59 = true;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc60 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE;
        org.locationtech.spatial4j.shape.Rectangle rectangle61 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext62 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc60, rectangle61);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl63 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl57, (double)0L, spatialContext62);
        double double77 = 1.0d;
        byte byte78 = (byte)-1;
        byte byte79 = (byte)-1;
        long long80 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext81 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl82 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext81);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input91 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl63, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl82);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input91);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input91);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape016() {
        char char0 = ' ';
        long long1 = 1L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext49 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl55 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)' ', (double)1L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext49);
        int int56 = 10;
        org.locationtech.spatial4j.context.SpatialContextFactory spatialContextFactory59 = new org.locationtech.spatial4j.context.SpatialContextFactory();
        org.locationtech.spatial4j.context.SpatialContext spatialContext61 = new org.locationtech.spatial4j.context.SpatialContext(spatialContextFactory59);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl63 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl55, (double)10, spatialContext61);
        double double65 = 1.0d;
        byte byte66 = (byte)-1;
        byte byte67 = (byte)-1;
        long long68 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext69 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl70 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext69);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext76 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl77 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl70, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext76);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input78 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl63, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl77);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input78);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input78);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape017() {
        short short0 = (short)1;
        double double1 = 10.0d;
        boolean boolean2 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext3 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl4 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)1, 10.0d, spatialContext3);
        double double18 = 9.007199254740992E15d;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext42 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl45 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl4, 9.007199254740992E15d, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext42);
        double double47 = 1.0d;
        byte byte48 = (byte)-1;
        byte byte49 = (byte)-1;
        long long50 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext51 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl52 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext51);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input67 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl45, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl52);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input67);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input67);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape018() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel7 = new org.locationtech.jts.geom.PrecisionModel();
        int int9 = 10;
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel7, 10);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        char char13 = 'a';
        boolean boolean14 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext15 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl16 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (double)'a', spatialContext15);
        float float41 = 100.0f;
        double double42 = (-1.0d);
        char char43 = '4';
        short short44 = (short)100;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext49 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl51 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)100.0f, (-1.0d), (double)'4', (double)(short)100, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext49);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input52 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl16, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl51);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input52);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input52);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape019() {
        org.locationtech.jts.geom.Coordinate coordinate23 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel34 = null;
        char char35 = 'a';
        org.locationtech.jts.geom.Point point36 = new org.locationtech.jts.geom.Point(coordinate23, precisionModel34, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext41 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint43 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point36, jtsSpatialContext41);
        short short44 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext49 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl51 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint43, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext49);
        float float76 = 10.0f;
        char char77 = '#';
        double double78 = 0.0d;
        byte byte79 = (byte)0;
        org.locationtech.spatial4j.context.SpatialContext spatialContext80 = org.locationtech.spatial4j.context.SpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl81 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)10.0f, (double)'#', 0.0d, (double)(byte)0, spatialContext80);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input82 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl51, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl81);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input82);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input82);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape020() {
        short short0 = (short)1;
        double double1 = 10.0d;
        boolean boolean2 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext3 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl4 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)1, 10.0d, spatialContext3);
        long long5 = 0L;
        boolean boolean6 = true;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc7 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE;
        org.locationtech.spatial4j.shape.Rectangle rectangle8 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext9 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc7, rectangle8);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl10 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl4, (double)0L, spatialContext9);
        byte byte17 = (byte)-1;
        double double18 = 0.0d;
        long long19 = 0L;
        double double20 = 100.0d;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext63 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl67 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)(byte)-1, 0.0d, (double)0L, 100.0d, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext63);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input68 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl10, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl67);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input68);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input68);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape021() {
        org.locationtech.jts.geom.Coordinate coordinate6 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel17 = null;
        char char18 = 'a';
        org.locationtech.jts.geom.Point point19 = new org.locationtech.jts.geom.Point(coordinate6, precisionModel17, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext24 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint26 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point19, jtsSpatialContext24);
        short short27 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext32 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl34 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint26, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext32);
        float float63 = 0.0f;
        byte byte64 = (byte)100;
        long long65 = 10L;
        int int66 = 10;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext67 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl68 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)0.0f, (double)(byte)100, (double)10L, (double)10, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext67);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input70 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl34, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl68);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input70);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input70);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape022() {
        org.locationtech.jts.geom.Coordinate coordinate4 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = new org.locationtech.jts.geom.PrecisionModel();
        int int13 = 10;
        org.locationtech.jts.geom.Point point14 = new org.locationtech.jts.geom.Point(coordinate4, precisionModel11, 10);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext43 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint46 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point14, jtsSpatialContext43);
        double double52 = Double.NaN;
        boolean boolean80 = true;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc81 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE;
        org.locationtech.spatial4j.shape.Rectangle rectangle82 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext83 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc81, rectangle82);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl87 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint46, Double.NaN, spatialContext83);
        double double88 = 1.0d;
        byte byte89 = (byte)-1;
        byte byte90 = (byte)-1;
        long long91 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext92 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl93 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext92);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input94 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl87, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl93);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input94);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input94);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape023() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel7 = new org.locationtech.jts.geom.PrecisionModel();
        int int9 = 10;
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel7, 10);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        byte byte27 = (byte)100;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext28 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl29 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (double)(byte)100, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext28);
        double double73 = 1.0d;
        byte byte74 = (byte)-1;
        byte byte75 = (byte)-1;
        long long76 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext77 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl78 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext77);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input94 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl29, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl78);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input94);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input94);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape024() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = null;
        char char12 = 'a';
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel11, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext18 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint20 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext18);
        short short21 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext26 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl28 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint20, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext26);
        double double38 = (-1.0d);
        short short39 = (short)-1;
        long long40 = 10L;
        char char41 = '4';
        org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory jtsSpatialContextFactory81 = new org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory();
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext85 = new org.locationtech.spatial4j.context.jts.JtsSpatialContext(jtsSpatialContextFactory81);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl86 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((-1.0d), (double)(short)-1, (double)10L, (double)'4', (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext85);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input87 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl28, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl86);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input87);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input87);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape025() {
        short short0 = (short)1;
        double double1 = 10.0d;
        boolean boolean2 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext3 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl4 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)1, 10.0d, spatialContext3);
        long long5 = 0L;
        boolean boolean6 = true;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc7 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE;
        org.locationtech.spatial4j.shape.Rectangle rectangle8 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext9 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc7, rectangle8);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl10 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl4, (double)0L, spatialContext9);
        double double17 = 100.0d;
        double double18 = 9.007199254740992E15d;
        byte byte19 = (byte)-1;
        double double20 = 1.0d;
        boolean boolean27 = true;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc28 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE;
        org.locationtech.spatial4j.shape.Rectangle rectangle29 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext30 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc28, rectangle29);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl32 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(100.0d, 9.007199254740992E15d, (double)(byte)-1, 1.0d, spatialContext30);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input62 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl10, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl32);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input62);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input62);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape026() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = null;
        char char12 = 'a';
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel11, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext18 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint20 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext18);
        short short21 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext26 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl28 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint20, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext26);
        byte byte40 = (byte)0;
        byte byte41 = (byte)0;
        double double42 = 0.0d;
        int int43 = 0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext75 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl79 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)(byte)0, (double)(byte)0, 0.0d, (double)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext75);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input80 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl28, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl79);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input80);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input80);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape027() {
        long long0 = 10L;
        short short1 = (short)1;
        org.locationtech.spatial4j.context.SpatialContextFactory spatialContextFactory2 = new org.locationtech.spatial4j.context.SpatialContextFactory();
        org.locationtech.spatial4j.context.SpatialContext spatialContext4 = new org.locationtech.spatial4j.context.SpatialContext(spatialContextFactory2);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl5 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)10L, (double)(short)1, spatialContext4);
        double double6 = 10.0d;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext25 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl28 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl5, 10.0d, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext25);
        double double30 = 1.0d;
        byte byte31 = (byte)-1;
        byte byte32 = (byte)-1;
        long long33 = 10L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext34 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl35 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(1.0d, (double)(byte)-1, (double)(byte)-1, (double)10L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext34);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext41 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl42 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl35, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext41);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input73 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl28, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl42);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input73);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input73);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape028() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = null;
        char char12 = 'a';
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel11, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext18 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint20 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext18);
        short short21 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext26 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl28 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint20, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext26);
        double double53 = 10.0d;
        short short54 = (short)100;
        long long55 = 0L;
        char char56 = 'a';
        org.locationtech.spatial4j.context.SpatialContext spatialContext57 = null;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl58 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(10.0d, (double)(short)100, (double)0L, (double)'a', spatialContext57);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input59 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl28, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl58);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input59);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input59);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape029() {
        short short0 = (short)1;
        double double1 = 10.0d;
        boolean boolean2 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext3 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl4 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)1, 10.0d, spatialContext3);
        long long5 = 0L;
        boolean boolean6 = true;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc7 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE;
        org.locationtech.spatial4j.shape.Rectangle rectangle8 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext9 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc7, rectangle8);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl10 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl4, (double)0L, spatialContext9);
        double double15 = 10.0d;
        short short16 = (short)-1;
        long long17 = 1L;
        float float18 = 100.0f;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext58 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl62 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(10.0d, (double)(short)-1, (double)1L, (double)100.0f, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext58);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input63 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl10, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl62);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input63);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input63);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape030() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = null;
        char char12 = 'a';
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel11, (int)'a');
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext18 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint20 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext18);
        short short21 = (short)0;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext26 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl28 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint20, (double)(short)0, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext26);
        char char51 = '#';
        int int52 = 10;
        float float53 = (-1.0f);
        long long54 = 100L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext71 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl74 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)'#', (double)10, (double)(-1.0f), (double)100L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext71);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input75 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl28, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl74);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input75);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input75);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape031() {
        float float0 = 100.0f;
        char char1 = 'a';
        boolean boolean16 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext17 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl19 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)100.0f, (double)'a', spatialContext17);
        int int20 = 0;
        boolean boolean23 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext24 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl26 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl19, (double)0, spatialContext24);
        char char31 = ' ';
        byte byte32 = (byte)100;
        byte byte33 = (byte)-1;
        double double34 = 1.0d;
        org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory jtsSpatialContextFactory74 = new org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory();
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext88 = new org.locationtech.spatial4j.context.jts.JtsSpatialContext(jtsSpatialContextFactory74);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl89 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)' ', (double)(byte)100, (double)(byte)-1, 1.0d, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext88);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input90 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl26, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl89);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input90);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input90);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape032() {
        short short0 = (short)1;
        double double1 = 10.0d;
        boolean boolean2 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext3 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl4 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)1, 10.0d, spatialContext3);
        long long5 = 0L;
        boolean boolean6 = true;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc7 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE;
        org.locationtech.spatial4j.shape.Rectangle rectangle8 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext9 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc7, rectangle8);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl10 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl4, (double)0L, spatialContext9);
        float float15 = (-1.0f);
        double double16 = 0.0d;
        double double17 = Double.NaN;
        double double18 = 0.0d;
        org.locationtech.spatial4j.context.SpatialContext spatialContext19 = null;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl20 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)(-1.0f), 0.0d, Double.NaN, 0.0d, spatialContext19);
        org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory jtsSpatialContextFactory38 = new org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory();
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext47 = new org.locationtech.spatial4j.context.jts.JtsSpatialContext(jtsSpatialContextFactory38);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl48 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl20, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext47);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input49 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl10, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl48);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input49);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input49);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape033() {
        short short0 = (short)1;
        double double1 = 10.0d;
        boolean boolean2 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext3 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl4 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)1, 10.0d, spatialContext3);
        long long5 = 0L;
        boolean boolean6 = true;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc7 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE;
        org.locationtech.spatial4j.shape.Rectangle rectangle8 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext9 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc7, rectangle8);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl10 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl4, (double)0L, spatialContext9);
        double double15 = 0.0d;
        float float16 = 10.0f;
        long long17 = (-1L);
        long long18 = (-1L);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext59 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl63 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(0.0d, (double)10.0f, (double)(-1L), (double)(-1L), (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext59);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input64 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl10, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl63);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input64);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input64);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape034() {
        short short0 = (short)1;
        double double1 = 10.0d;
        boolean boolean2 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext3 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl4 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)1, 10.0d, spatialContext3);
        long long5 = 0L;
        boolean boolean6 = true;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc7 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE;
        org.locationtech.spatial4j.shape.Rectangle rectangle8 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext9 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc7, rectangle8);
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl10 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl4, (double)0L, spatialContext9);
        char char15 = '#';
        byte byte16 = (byte)10;
        int int17 = 10;
        char char18 = ' ';
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext69 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl72 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)'#', (double)(byte)10, (double)10, (double)' ', (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext69);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input73 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl10, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl72);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input73);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input73);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape035() {
        short short5 = (short)1;
        double double6 = 10.0d;
        boolean boolean7 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext8 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl9 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)1, 10.0d, spatialContext8);
        double double23 = 9.007199254740992E15d;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext47 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl50 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)pointImpl9, 9.007199254740992E15d, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext47);
        byte byte78 = (byte)1;
        long long79 = 100L;
        int int80 = (-1);
        double double81 = 0.0d;
        boolean boolean86 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext87 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl90 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)(byte)1, (double)100L, (double)(-1), 0.0d, spatialContext87);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input91 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl50, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl90);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input91);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input91);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
