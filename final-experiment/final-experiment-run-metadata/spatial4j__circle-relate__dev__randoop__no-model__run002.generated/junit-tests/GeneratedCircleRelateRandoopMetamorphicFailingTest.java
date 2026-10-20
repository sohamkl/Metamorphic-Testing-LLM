import org.junit.jupiter.api.Test;

public class GeneratedCircleRelateRandoopMetamorphicFailingTest {

    @Test
    public void testShape001() {
        org.locationtech.jts.geom.Coordinate coordinate0 = new org.locationtech.jts.geom.Coordinate();
        org.locationtech.jts.geom.PrecisionModel precisionModel7 = new org.locationtech.jts.geom.PrecisionModel();
        int int9 = 10;
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point(coordinate0, precisionModel7, 10);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        byte byte27 = (byte)100;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext28 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.CircleImpl circleImpl29 = new org.locationtech.spatial4j.shape.impl.CircleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (double)(byte)100, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext28);
        double double62 = 100.0d;
        char char63 = '#';
        char char64 = '#';
        char char65 = '4';
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext66 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl67 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(100.0d, (double)'#', (double)'#', (double)'4', (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext66);
        org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory jtsSpatialContextFactory86 = new org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory();
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext93 = new org.locationtech.spatial4j.context.jts.JtsSpatialContext(jtsSpatialContextFactory86);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl94 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl67, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext93);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input95 = new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circleImpl29, (org.locationtech.spatial4j.shape.Rectangle)rectangleImpl94);
        var followUp = MtllmGeneratedCircleImplRelateInvocation1qssgw4.generateFollowUp(input95);
        var sourceOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(input95);
        var followUpOutput = MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
