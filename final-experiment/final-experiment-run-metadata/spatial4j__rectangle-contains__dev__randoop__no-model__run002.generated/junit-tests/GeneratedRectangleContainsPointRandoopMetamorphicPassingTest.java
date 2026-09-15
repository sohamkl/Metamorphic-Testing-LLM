import org.junit.jupiter.api.Test;

public class GeneratedRectangleContainsPointRandoopMetamorphicPassingTest {

    @Test
    public void testShape001() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray2 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char3 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence4 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray2, (int)' ');
        int int5 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel6 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int7 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory8 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory9 = new org.locationtech.jts.geom.GeometryFactory(precisionModel6, 1, coordinateSequenceFactory8);
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence4, geometryFactory9);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        org.locationtech.jts.geom.Coordinate[] coordinateArray16 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char17 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence18 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray16, (int)' ');
        int int19 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel20 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int21 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory22 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory23 = new org.locationtech.jts.geom.GeometryFactory(precisionModel20, 1, coordinateSequenceFactory22);
        org.locationtech.jts.geom.Point point24 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence18, geometryFactory23);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext25 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint26 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point24, jtsSpatialContext25);
        boolean boolean30 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext31 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl33 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (org.locationtech.spatial4j.shape.Point)jtsPoint26, spatialContext31);
        byte byte34 = (byte)0;
        char char35 = ' ';
        boolean boolean36 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext37 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl38 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(byte)0, (double)' ', spatialContext37);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input45 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl33, (org.locationtech.spatial4j.shape.Point)pointImpl38);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input45);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input45);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape002() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray3 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char4 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence5 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray3, (int)' ');
        int int6 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel7 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int8 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory9 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory10 = new org.locationtech.jts.geom.GeometryFactory(precisionModel7, 1, coordinateSequenceFactory9);
        org.locationtech.jts.geom.Point point11 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence5, geometryFactory10);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext12 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint13 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point11, jtsSpatialContext12);
        org.locationtech.jts.geom.Coordinate[] coordinateArray17 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char18 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence19 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray17, (int)' ');
        int int20 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel21 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int22 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory23 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory24 = new org.locationtech.jts.geom.GeometryFactory(precisionModel21, 1, coordinateSequenceFactory23);
        org.locationtech.jts.geom.Point point25 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence19, geometryFactory24);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext26 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint27 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point25, jtsSpatialContext26);
        boolean boolean31 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext32 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl34 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint13, (org.locationtech.spatial4j.shape.Point)jtsPoint27, spatialContext32);
        org.locationtech.jts.geom.Coordinate[] coordinateArray37 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char38 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence39 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray37, (int)' ');
        int int40 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel41 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int42 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory43 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory44 = new org.locationtech.jts.geom.GeometryFactory(precisionModel41, 1, coordinateSequenceFactory43);
        org.locationtech.jts.geom.Point point45 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence39, geometryFactory44);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext46 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint47 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point45, jtsSpatialContext46);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input69 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl34, (org.locationtech.spatial4j.shape.Point)jtsPoint47);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input69);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input69);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape003() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray2 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char3 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence4 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray2, (int)' ');
        int int5 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel6 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int7 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory8 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory9 = new org.locationtech.jts.geom.GeometryFactory(precisionModel6, 1, coordinateSequenceFactory8);
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence4, geometryFactory9);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        org.locationtech.jts.geom.Coordinate[] coordinateArray16 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char17 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence18 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray16, (int)' ');
        int int19 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel20 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int21 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory22 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory23 = new org.locationtech.jts.geom.GeometryFactory(precisionModel20, 1, coordinateSequenceFactory22);
        org.locationtech.jts.geom.Point point24 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence18, geometryFactory23);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext25 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint26 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point24, jtsSpatialContext25);
        boolean boolean30 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext31 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl33 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (org.locationtech.spatial4j.shape.Point)jtsPoint26, spatialContext31);
        org.locationtech.jts.geom.Coordinate[] coordinateArray40 = new org.locationtech.jts.geom.Coordinate[] {  };
        byte byte42 = (byte)100;
        byte byte43 = (byte)0;
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence44 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray40, (int)(byte)100, (int)(byte)0);
        int int46 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel47 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int53 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel54 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        org.locationtech.jts.geom.PrecisionModel precisionModel55 = org.locationtech.jts.geom.PrecisionModel.mostPrecise(precisionModel47, precisionModel54);
        int int56 = 1;
        org.locationtech.jts.geom.GeometryFactory geometryFactory57 = new org.locationtech.jts.geom.GeometryFactory(precisionModel55, 1);
        org.locationtech.jts.geom.Point point58 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence44, geometryFactory57);
        org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory jtsSpatialContextFactory67 = new org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory();
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext69 = new org.locationtech.spatial4j.context.jts.JtsSpatialContext(jtsSpatialContextFactory67);
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint71 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point58, jtsSpatialContext69);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input72 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl33, (org.locationtech.spatial4j.shape.Point)jtsPoint71);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input72);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input72);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape004() {
        char char0 = ' ';
        short short1 = (short)0;
        byte byte2 = (byte)-1;
        int int3 = 100;
        boolean boolean52 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext53 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl56 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)' ', (double)(short)0, (double)(byte)-1, (double)100, spatialContext53);
        org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory jtsSpatialContextFactory57 = new org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory();
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext58 = new org.locationtech.spatial4j.context.jts.JtsSpatialContext(jtsSpatialContextFactory57);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl59 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl56, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext58);
        org.locationtech.jts.geom.Coordinate[] coordinateArray65 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char66 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence67 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray65, (int)' ');
        int int68 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel69 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int70 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory71 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory72 = new org.locationtech.jts.geom.GeometryFactory(precisionModel69, 1, coordinateSequenceFactory71);
        org.locationtech.jts.geom.Point point73 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence67, geometryFactory72);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext74 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint75 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point73, jtsSpatialContext74);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input85 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl59, (org.locationtech.spatial4j.shape.Point)jtsPoint75);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input85);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input85);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape005() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray7 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char8 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence9 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray7, (int)' ');
        int int10 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int12 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory13 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory14 = new org.locationtech.jts.geom.GeometryFactory(precisionModel11, 1, coordinateSequenceFactory13);
        org.locationtech.jts.geom.Point point15 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence9, geometryFactory14);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext16 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint17 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point15, jtsSpatialContext16);
        org.locationtech.jts.geom.Coordinate[] coordinateArray21 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char22 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence23 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray21, (int)' ');
        int int24 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel25 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int26 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory27 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory28 = new org.locationtech.jts.geom.GeometryFactory(precisionModel25, 1, coordinateSequenceFactory27);
        org.locationtech.jts.geom.Point point29 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence23, geometryFactory28);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext30 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint31 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point29, jtsSpatialContext30);
        boolean boolean35 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext36 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl38 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint17, (org.locationtech.spatial4j.shape.Point)jtsPoint31, spatialContext36);
        short short45 = (short)100;
        byte byte46 = (byte)0;
        boolean boolean47 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext48 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl49 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)100, (double)(byte)0, spatialContext48);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input50 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl38, (org.locationtech.spatial4j.shape.Point)pointImpl49);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input50);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input50);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape006() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray2 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char3 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence4 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray2, (int)' ');
        int int5 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel6 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int7 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory8 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory9 = new org.locationtech.jts.geom.GeometryFactory(precisionModel6, 1, coordinateSequenceFactory8);
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence4, geometryFactory9);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        org.locationtech.jts.geom.Coordinate[] coordinateArray16 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char17 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence18 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray16, (int)' ');
        int int19 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel20 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int21 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory22 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory23 = new org.locationtech.jts.geom.GeometryFactory(precisionModel20, 1, coordinateSequenceFactory22);
        org.locationtech.jts.geom.Point point24 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence18, geometryFactory23);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext25 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint26 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point24, jtsSpatialContext25);
        boolean boolean30 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext31 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl33 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (org.locationtech.spatial4j.shape.Point)jtsPoint26, spatialContext31);
        boolean boolean47 = false;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc48 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE_SQUARED;
        org.locationtech.spatial4j.shape.Rectangle rectangle49 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext50 = new org.locationtech.spatial4j.context.SpatialContext(false, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc48, rectangle49);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl51 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl33, spatialContext50);
        int int57 = 0;
        double double58 = 0.0d;
        boolean boolean59 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext60 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl61 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)0, 0.0d, spatialContext60);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input70 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl51, (org.locationtech.spatial4j.shape.Point)pointImpl61);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input70);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input70);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape007() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray7 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char8 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence9 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray7, (int)' ');
        int int10 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel11 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int12 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory13 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory14 = new org.locationtech.jts.geom.GeometryFactory(precisionModel11, 1, coordinateSequenceFactory13);
        org.locationtech.jts.geom.Point point15 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence9, geometryFactory14);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext16 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint17 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point15, jtsSpatialContext16);
        org.locationtech.jts.geom.Coordinate[] coordinateArray21 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char22 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence23 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray21, (int)' ');
        int int24 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel25 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int26 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory27 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory28 = new org.locationtech.jts.geom.GeometryFactory(precisionModel25, 1, coordinateSequenceFactory27);
        org.locationtech.jts.geom.Point point29 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence23, geometryFactory28);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext30 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint31 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point29, jtsSpatialContext30);
        boolean boolean35 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext36 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl38 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint17, (org.locationtech.spatial4j.shape.Point)jtsPoint31, spatialContext36);
        double double51 = 1.0d;
        byte byte52 = (byte)10;
        byte byte53 = (byte)1;
        org.locationtech.jts.geom.Coordinate coordinate54 = new org.locationtech.jts.geom.Coordinate(1.0d, (double)(byte)10, (double)(byte)1);
        int int67 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel68 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        org.locationtech.jts.geom.PrecisionModel precisionModel69 = new org.locationtech.jts.geom.PrecisionModel(precisionModel68);
        int int73 = 0;
        org.locationtech.jts.geom.Point point74 = new org.locationtech.jts.geom.Point(coordinate54, precisionModel69, 0);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext86 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint89 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point74, jtsSpatialContext86);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input90 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl38, (org.locationtech.spatial4j.shape.Point)jtsPoint89);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input90);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input90);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape008() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray2 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char3 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence4 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray2, (int)' ');
        int int5 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel6 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int7 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory8 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory9 = new org.locationtech.jts.geom.GeometryFactory(precisionModel6, 1, coordinateSequenceFactory8);
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence4, geometryFactory9);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        org.locationtech.jts.geom.Coordinate[] coordinateArray16 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char17 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence18 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray16, (int)' ');
        int int19 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel20 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int21 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory22 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory23 = new org.locationtech.jts.geom.GeometryFactory(precisionModel20, 1, coordinateSequenceFactory22);
        org.locationtech.jts.geom.Point point24 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence18, geometryFactory23);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext25 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint26 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point24, jtsSpatialContext25);
        boolean boolean30 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext31 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl33 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (org.locationtech.spatial4j.shape.Point)jtsPoint26, spatialContext31);
        char char83 = '#';
        long long84 = 0L;
        boolean boolean85 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext86 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl87 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)'#', (double)0L, spatialContext86);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input88 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl33, (org.locationtech.spatial4j.shape.Point)pointImpl87);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input88);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input88);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape009() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray2 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char3 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence4 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray2, (int)' ');
        int int5 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel6 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int7 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory8 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory9 = new org.locationtech.jts.geom.GeometryFactory(precisionModel6, 1, coordinateSequenceFactory8);
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence4, geometryFactory9);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        org.locationtech.jts.geom.Coordinate[] coordinateArray16 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char17 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence18 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray16, (int)' ');
        int int19 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel20 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int21 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory22 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory23 = new org.locationtech.jts.geom.GeometryFactory(precisionModel20, 1, coordinateSequenceFactory22);
        org.locationtech.jts.geom.Point point24 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence18, geometryFactory23);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext25 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint26 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point24, jtsSpatialContext25);
        boolean boolean30 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext31 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl33 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (org.locationtech.spatial4j.shape.Point)jtsPoint26, spatialContext31);
        boolean boolean60 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext61 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl65 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl33, spatialContext61);
        char char66 = '4';
        char char67 = '4';
        boolean boolean68 = true;
        org.locationtech.spatial4j.distance.GeodesicSphereDistCalc.LawOfCosines lawOfCosines69 = new org.locationtech.spatial4j.distance.GeodesicSphereDistCalc.LawOfCosines();
        org.locationtech.spatial4j.shape.Rectangle rectangle70 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext71 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)lawOfCosines69, rectangle70);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl72 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)'4', (double)'4', spatialContext71);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input73 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl65, (org.locationtech.spatial4j.shape.Point)pointImpl72);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input73);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input73);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape010() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray2 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char3 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence4 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray2, (int)' ');
        int int5 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel6 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int7 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory8 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory9 = new org.locationtech.jts.geom.GeometryFactory(precisionModel6, 1, coordinateSequenceFactory8);
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence4, geometryFactory9);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        char char34 = '#';
        char char35 = 'a';
        org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory jtsSpatialContextFactory36 = new org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory();
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext37 = new org.locationtech.spatial4j.context.jts.JtsSpatialContext(jtsSpatialContextFactory36);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl38 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)'#', (double)'a', (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext37);
        org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory jtsSpatialContextFactory51 = new org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory();
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext52 = new org.locationtech.spatial4j.context.jts.JtsSpatialContext(jtsSpatialContextFactory51);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl54 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (org.locationtech.spatial4j.shape.Point)pointImpl38, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext52);
        short short79 = (short)0;
        float float80 = 10.0f;
        boolean boolean87 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext88 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl91 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)0, (double)10.0f, spatialContext88);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input92 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl54, (org.locationtech.spatial4j.shape.Point)pointImpl91);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input92);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input92);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape011() {
        char char0 = ' ';
        short short1 = (short)0;
        byte byte2 = (byte)-1;
        int int3 = 100;
        boolean boolean52 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext53 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl56 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)' ', (double)(short)0, (double)(byte)-1, (double)100, spatialContext53);
        float float72 = 100.0f;
        long long73 = 1L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext83 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl85 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)100.0f, (double)1L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext83);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input86 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl56, (org.locationtech.spatial4j.shape.Point)pointImpl85);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input86);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input86);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape012() {
        double double2 = 0.0d;
        int int3 = 2;
        long long4 = (-1L);
        double double5 = 10.0d;
        boolean boolean12 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext13 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl16 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(0.0d, (double)2, (double)(-1L), 10.0d, spatialContext13);
        char char18 = '#';
        float float19 = 0.0f;
        double double20 = 100.0d;
        org.locationtech.jts.geom.Coordinate coordinate21 = new org.locationtech.jts.geom.Coordinate((double)'#', (double)0.0f, 100.0d);
        int int30 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel31 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        org.locationtech.jts.geom.PrecisionModel precisionModel32 = new org.locationtech.jts.geom.PrecisionModel(precisionModel31);
        short short34 = (short)-1;
        org.locationtech.jts.geom.Point point35 = new org.locationtech.jts.geom.Point(coordinate21, precisionModel32, (int)(short)-1);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext53 = null;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint54 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point35, jtsSpatialContext53);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input84 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl16, (org.locationtech.spatial4j.shape.Point)jtsPoint54);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input84);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input84);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape013() {
        float float0 = 1.0f;
        double double1 = 0.0d;
        long long2 = (-1L);
        double double3 = 0.0d;
        boolean boolean6 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext7 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl9 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)1.0f, 0.0d, (double)(-1L), 0.0d, spatialContext7);
        char char10 = 'a';
        int int11 = (-1);
        boolean boolean16 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext17 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl20 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)'a', (double)(-1), spatialContext17);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input21 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl9, (org.locationtech.spatial4j.shape.Point)pointImpl20);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input21);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input21);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape014() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray5 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char6 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence7 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray5, (int)' ');
        int int8 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel9 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int10 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory11 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory12 = new org.locationtech.jts.geom.GeometryFactory(precisionModel9, 1, coordinateSequenceFactory11);
        org.locationtech.jts.geom.Point point13 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence7, geometryFactory12);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext14 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint15 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point13, jtsSpatialContext14);
        org.locationtech.jts.geom.Coordinate[] coordinateArray19 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char20 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence21 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray19, (int)' ');
        int int22 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel23 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int24 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory25 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory26 = new org.locationtech.jts.geom.GeometryFactory(precisionModel23, 1, coordinateSequenceFactory25);
        org.locationtech.jts.geom.Point point27 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence21, geometryFactory26);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext28 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint29 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point27, jtsSpatialContext28);
        boolean boolean33 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext34 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl36 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint15, (org.locationtech.spatial4j.shape.Point)jtsPoint29, spatialContext34);
        boolean boolean50 = false;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc51 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE_SQUARED;
        org.locationtech.spatial4j.shape.Rectangle rectangle52 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext53 = new org.locationtech.spatial4j.context.SpatialContext(false, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc51, rectangle52);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl54 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl36, spatialContext53);
        short short81 = (short)0;
        int int82 = 3;
        boolean boolean85 = true;
        org.locationtech.spatial4j.distance.GeodesicSphereDistCalc.LawOfCosines lawOfCosines86 = new org.locationtech.spatial4j.distance.GeodesicSphereDistCalc.LawOfCosines();
        org.locationtech.spatial4j.shape.Rectangle rectangle87 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext88 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)lawOfCosines86, rectangle87);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl90 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)0, (double)3, spatialContext88);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input91 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl54, (org.locationtech.spatial4j.shape.Point)pointImpl90);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input91);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input91);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape015() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray2 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char3 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence4 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray2, (int)' ');
        int int5 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel6 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int7 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory8 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory9 = new org.locationtech.jts.geom.GeometryFactory(precisionModel6, 1, coordinateSequenceFactory8);
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence4, geometryFactory9);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        org.locationtech.jts.geom.Coordinate[] coordinateArray16 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char17 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence18 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray16, (int)' ');
        int int19 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel20 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int21 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory22 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory23 = new org.locationtech.jts.geom.GeometryFactory(precisionModel20, 1, coordinateSequenceFactory22);
        org.locationtech.jts.geom.Point point24 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence18, geometryFactory23);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext25 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint26 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point24, jtsSpatialContext25);
        boolean boolean30 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext31 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl33 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (org.locationtech.spatial4j.shape.Point)jtsPoint26, spatialContext31);
        long long52 = 0L;
        byte byte53 = (byte)-1;
        boolean boolean54 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext55 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl56 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)0L, (double)(byte)-1, spatialContext55);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input67 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl33, (org.locationtech.spatial4j.shape.Point)pointImpl56);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input67);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input67);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape016() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray2 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char3 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence4 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray2, (int)' ');
        int int5 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel6 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int7 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory8 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory9 = new org.locationtech.jts.geom.GeometryFactory(precisionModel6, 1, coordinateSequenceFactory8);
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence4, geometryFactory9);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        org.locationtech.jts.geom.Coordinate[] coordinateArray16 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char17 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence18 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray16, (int)' ');
        int int19 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel20 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int21 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory22 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory23 = new org.locationtech.jts.geom.GeometryFactory(precisionModel20, 1, coordinateSequenceFactory22);
        org.locationtech.jts.geom.Point point24 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence18, geometryFactory23);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext25 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint26 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point24, jtsSpatialContext25);
        boolean boolean30 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext31 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl33 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (org.locationtech.spatial4j.shape.Point)jtsPoint26, spatialContext31);
        short short71 = (short)100;
        byte byte72 = (byte)10;
        org.locationtech.jts.geom.Coordinate coordinate73 = new org.locationtech.jts.geom.Coordinate((double)(short)100, (double)(byte)10);
        org.locationtech.jts.geom.Coordinate coordinate75 = null;
        int int76 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel77 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        short short81 = (short)-1;
        org.locationtech.jts.geom.Point point82 = new org.locationtech.jts.geom.Point(coordinate75, precisionModel77, (int)(short)-1);
        org.locationtech.jts.geom.Point point83 = org.locationtech.jts.geom.GeometryFactory.createPointFromInternalCoord(coordinate73, (org.locationtech.jts.geom.Geometry) point82);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext84 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint85 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point83, jtsSpatialContext84);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input86 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl33, (org.locationtech.spatial4j.shape.Point)jtsPoint85);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input86);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input86);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape017() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray2 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char3 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence4 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray2, (int)' ');
        int int5 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel6 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int7 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory8 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory9 = new org.locationtech.jts.geom.GeometryFactory(precisionModel6, 1, coordinateSequenceFactory8);
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence4, geometryFactory9);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        org.locationtech.jts.geom.Coordinate[] coordinateArray16 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char17 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence18 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray16, (int)' ');
        int int19 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel20 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int21 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory22 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory23 = new org.locationtech.jts.geom.GeometryFactory(precisionModel20, 1, coordinateSequenceFactory22);
        org.locationtech.jts.geom.Point point24 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence18, geometryFactory23);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext25 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint26 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point24, jtsSpatialContext25);
        boolean boolean30 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext31 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl33 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (org.locationtech.spatial4j.shape.Point)jtsPoint26, spatialContext31);
        char char52 = '#';
        float float53 = 0.0f;
        double double54 = 100.0d;
        org.locationtech.jts.geom.Coordinate coordinate55 = new org.locationtech.jts.geom.Coordinate((double)'#', (double)0.0f, 100.0d);
        int int64 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel65 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        org.locationtech.jts.geom.PrecisionModel precisionModel66 = new org.locationtech.jts.geom.PrecisionModel(precisionModel65);
        short short68 = (short)-1;
        org.locationtech.jts.geom.Point point69 = new org.locationtech.jts.geom.Point(coordinate55, precisionModel66, (int)(short)-1);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext87 = null;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint88 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point69, jtsSpatialContext87);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input89 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl33, (org.locationtech.spatial4j.shape.Point)jtsPoint88);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input89);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input89);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape018() {
        int int0 = 10;
        char char1 = '4';
        int int2 = 0;
        double double3 = 0.0d;
        org.locationtech.spatial4j.context.SpatialContext spatialContext4 = null;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl5 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)10, (double)'4', (double)0, 0.0d, spatialContext4);
        boolean boolean8 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext9 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl11 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl5, spatialContext9);
        int int12 = 0;
        double double13 = 0.0d;
        boolean boolean14 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext15 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl16 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)0, 0.0d, spatialContext15);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input25 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl11, (org.locationtech.spatial4j.shape.Point)pointImpl16);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input25);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input25);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape019() {
        char char6 = ' ';
        char char7 = ' ';
        float float8 = 1.0f;
        int int9 = 1;
        org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory jtsSpatialContextFactory10 = new org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory();
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = new org.locationtech.spatial4j.context.jts.JtsSpatialContext(jtsSpatialContextFactory10);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl12 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)' ', (double)' ', (double)1.0f, (double)1, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext11);
        short short15 = (short)0;
        double double16 = 0.0d;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext19 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl21 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)0, 0.0d, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext19);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input39 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl12, (org.locationtech.spatial4j.shape.Point)pointImpl21);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input39);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input39);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape020() {
        float float0 = 0.0f;
        byte byte1 = (byte)100;
        float float2 = 10.0f;
        byte byte3 = (byte)10;
        boolean boolean12 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext13 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl17 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)0.0f, (double)(byte)100, (double)10.0f, (double)(byte)10, spatialContext13);
        short short51 = (short)0;
        int int52 = 3;
        boolean boolean55 = true;
        org.locationtech.spatial4j.distance.GeodesicSphereDistCalc.LawOfCosines lawOfCosines56 = new org.locationtech.spatial4j.distance.GeodesicSphereDistCalc.LawOfCosines();
        org.locationtech.spatial4j.shape.Rectangle rectangle57 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext58 = new org.locationtech.spatial4j.context.SpatialContext(true, (org.locationtech.spatial4j.distance.DistanceCalculator)lawOfCosines56, rectangle57);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl60 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)0, (double)3, spatialContext58);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input66 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl17, (org.locationtech.spatial4j.shape.Point)pointImpl60);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input66);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input66);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape021() {
        byte byte0 = (byte)10;
        byte byte1 = (byte)0;
        char char2 = ' ';
        long long3 = 100L;
        boolean boolean8 = false;
        org.locationtech.spatial4j.context.SpatialContext spatialContext9 = new org.locationtech.spatial4j.context.SpatialContext(false);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl12 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((double)(byte)10, (double)(byte)0, (double)' ', (double)100L, spatialContext9);
        short short13 = (short)0;
        float float14 = 10.0f;
        boolean boolean21 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext22 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl25 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)(short)0, (double)10.0f, spatialContext22);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input26 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl12, (org.locationtech.spatial4j.shape.Point)pointImpl25);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input26);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input26);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape022() {
        org.locationtech.jts.geom.Coordinate[] coordinateArray2 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char3 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence4 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray2, (int)' ');
        int int5 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel6 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int7 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory8 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory9 = new org.locationtech.jts.geom.GeometryFactory(precisionModel6, 1, coordinateSequenceFactory8);
        org.locationtech.jts.geom.Point point10 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence4, geometryFactory9);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext11 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint12 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point10, jtsSpatialContext11);
        org.locationtech.jts.geom.Coordinate[] coordinateArray16 = new org.locationtech.jts.geom.Coordinate[] {  };
        char char17 = ' ';
        org.locationtech.jts.geom.impl.CoordinateArraySequence coordinateArraySequence18 = new org.locationtech.jts.geom.impl.CoordinateArraySequence(coordinateArray16, (int)' ');
        int int19 = 10;
        org.locationtech.jts.geom.PrecisionModel precisionModel20 = new org.locationtech.jts.geom.PrecisionModel((double)10);
        int int21 = 1;
        org.locationtech.jts.geom.CoordinateSequenceFactory coordinateSequenceFactory22 = null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory23 = new org.locationtech.jts.geom.GeometryFactory(precisionModel20, 1, coordinateSequenceFactory22);
        org.locationtech.jts.geom.Point point24 = new org.locationtech.jts.geom.Point((org.locationtech.jts.geom.CoordinateSequence)coordinateArraySequence18, geometryFactory23);
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext25 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.jts.JtsPoint jtsPoint26 = new org.locationtech.spatial4j.shape.jts.JtsPoint(point24, jtsSpatialContext25);
        boolean boolean30 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext31 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl33 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Point)jtsPoint12, (org.locationtech.spatial4j.shape.Point)jtsPoint26, spatialContext31);
        boolean boolean48 = true;
        org.locationtech.spatial4j.context.SpatialContext spatialContext49 = new org.locationtech.spatial4j.context.SpatialContext(true);
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl51 = new org.locationtech.spatial4j.shape.impl.RectangleImpl((org.locationtech.spatial4j.shape.Rectangle)rectangleImpl33, spatialContext49);
        char char81 = ' ';
        float float82 = (-1.0f);
        boolean boolean83 = false;
        org.locationtech.spatial4j.distance.CartesianDistCalc cartesianDistCalc84 = org.locationtech.spatial4j.distance.CartesianDistCalc.INSTANCE_SQUARED;
        org.locationtech.spatial4j.shape.Rectangle rectangle85 = null;
        org.locationtech.spatial4j.context.SpatialContext spatialContext86 = new org.locationtech.spatial4j.context.SpatialContext(false, (org.locationtech.spatial4j.distance.DistanceCalculator)cartesianDistCalc84, rectangle85);
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl87 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)' ', (double)(-1.0f), spatialContext86);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input88 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl51, (org.locationtech.spatial4j.shape.Point)pointImpl87);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input88);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input88);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape023() {
        double double0 = Double.NaN;
        double double1 = 9.007199254740992E15d;
        long long2 = 1L;
        char char3 = 'a';
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext13 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangleImpl15 = new org.locationtech.spatial4j.shape.impl.RectangleImpl(Double.NaN, 9.007199254740992E15d, (double)1L, (double)'a', (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext13);
        float float16 = 100.0f;
        long long17 = 1L;
        org.locationtech.spatial4j.context.jts.JtsSpatialContext jtsSpatialContext27 = org.locationtech.spatial4j.context.jts.JtsSpatialContext.GEO;
        org.locationtech.spatial4j.shape.impl.PointImpl pointImpl29 = new org.locationtech.spatial4j.shape.impl.PointImpl((double)100.0f, (double)1L, (org.locationtech.spatial4j.context.SpatialContext)jtsSpatialContext27);
        MtllmGeneratedRectangleImplRelateInvocationyms78s.Input input30 = new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangleImpl15, (org.locationtech.spatial4j.shape.Point)pointImpl29);
        var followUp = MtllmGeneratedRectangleImplRelateInvocationyms78s.generateFollowUp(input30);
        var sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(input30);
        var followUpOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(followUp);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
