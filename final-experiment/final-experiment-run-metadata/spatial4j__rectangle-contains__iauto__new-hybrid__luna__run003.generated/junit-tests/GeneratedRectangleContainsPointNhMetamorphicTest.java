import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicTest {

    private static void verify(
            SpatialContext context,
            double minX,
            double maxX,
            double minY,
            double maxY,
            double pointX,
            double pointY) {
        RectangleImpl sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput =
                sourceRectangle.relate(sourcePoint);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(
                        sourceRectangle, sourcePoint);

        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput =
                followUpRectangle.relate(followUpPoint);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "The source and follow-up relations differ: "
                            + sourceOutput + " versus " + followUpOutput);
        }
    }

    @Test
    void GEO_INTERIOR_CONTAINS_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, -40.0, 40.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    void GEO_LATITUDE_BELOW_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(false);
        verify(context, -100.0, -50.0, -20.0, 20.0, -50.0, 20.000001);
    }

    @Test
    void GEO_LATITUDE_ABOVE_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, 170.0, -170.0, -10.0, 10.0, 0.0, -10.0);
    }

    @Test
    void GEO_LATITUDE_LOWER_EDGE_CONTAINS_variation1() {
        SpatialContext context = new SpatialContext(false);
        verify(context, -15.0, 15.0, -10.0, 10.0, 179.0, 10.0);
    }

    @Test
    void GEO_LATITUDE_UPPER_EDGE_CONTAINS_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, -40.0, 40.0, -20.0, 20.0, -45.0, 0.0);
    }

    @Test
    void GEO_LONGITUDE_ENDPOINTS_CONTAIN_variation1() {
        SpatialContext context = new SpatialContext(false);
        verify(context, -120.0, -80.0, Double.NaN, 20.0, -70.0, 0.0);
    }

    @Test
    void GEO_LONGITUDE_ENDPOINTS_CONTAIN_variation2() {
        SpatialContext context = new SpatialContext(true);
        verify(context, -5.0, 5.0, -20.0, 20.0, 0.0, -25.0);
    }

    @Test
    void GEO_DATELINE_INTERIOR_CONTAINS_variation1() {
        SpatialContext context = new SpatialContext(false);
        verify(context, Double.NaN, 40.0, -10.0, 10.0, 0.0, 20.0);
    }

    @Test
    void GEO_DATELINE_OUTSIDE_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, -40.0, 40.0, -10.0, 10.0, Double.NaN, -10.0);
    }

    @Test
    void GEO_ROTATION_CREATES_DATELINE_WRAP_variation1() {
        SpatialContext context = new SpatialContext(false);
        verify(context, -20.0, 40.0, -10.0, 10.0, 0.0, 10.0);
    }

    @Test
    void GEO_ROTATION_REMOVES_DATELINE_WRAP_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, 170.0, -170.0, -10.0, 10.0, -170.0, 0.0);
    }

    @Test
    void GEO_DATELINE_SHIFTED_OUTSIDE_variation1() {
        SpatialContext context = new SpatialContext(false);
        verify(context, -100.0, -50.0, Double.NaN, 20.0, -40.0, 0.0);
    }

    @Test
    void GEO_ZERO_WIDTH_LONGITUDE_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, 30.0, 30.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    void GEO_ZERO_WIDTH_LONGITUDE_variation2() {
        SpatialContext context = new SpatialContext(false);
        verify(context, -75.0, -75.0, -20.0, 20.0, -74.999999, 20.000001);
    }

    @Test
    void GEO_DATELINE_LONGITUDE_EDGES_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, 170.0, -170.0, -10.0, 10.0, 0.0, -10.0);
    }

    @Test
    void GEO_DATELINE_LONGITUDE_EDGES_variation2() {
        SpatialContext context = new SpatialContext(false);
        verify(context, 25.0, 25.0, -15.0, 15.0, 25.0, 15.0);
    }

    @Test
    void PLANAR_INTERIOR_CONTAINS_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, Double.NaN, 40.0, -20.0, 20.0, 10.0, 5.0);
    }

    @Test
    void PLANAR_LONGITUDE_OUTSIDE_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(false);
        verify(context, -100.0, -50.0, Double.NaN, 20.0, Double.NaN, 0.0);
    }

    @Test
    void PLANAR_LATITUDE_EDGE_AND_OUTSIDE_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, -40.0, 40.0, -20.0, 20.0, 0.0, -20.000001);
    }

    @Test
    void PLANAR_LATITUDE_EDGE_AND_OUTSIDE_variation2() {
        SpatialContext context = new SpatialContext(false);
        verify(context, -100.0, -50.0, -20.0, 20.0, -50.0, 20.000001);
    }

    @Test
    void PLANAR_LATITUDE_EDGE_AND_OUTSIDE_variation3() {
        SpatialContext context = new SpatialContext(true);
        verify(context, -20.0, 20.0, -10.0, 10.0, 30.0, -10.0);
    }

    @Test
    void PLANAR_ZERO_WIDTH_LONGITUDE_variation1() {
        SpatialContext context = new SpatialContext(false);
        verify(context, -75.0, -75.0, -20.0, 20.0, -75.0, 20.0);
    }

    @Test
    void PLANAR_ZERO_WIDTH_LONGITUDE_variation2() {
        SpatialContext context = new SpatialContext(true);
        verify(context, 30.0, 30.0, -10.0, 10.0, -330.0, 0.0);
    }

    @Test
    void NAN_MINX_SENTINEL_variation1() {
        SpatialContext context = new SpatialContext(false);
        verify(context, Double.NaN, 40.0, Double.NaN, 10.0, 50.0, 0.0);
    }

    @Test
    void NAN_MINY_SENTINEL_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, 15.0, 15.0, Double.NaN, 20.0, 15.0, -30.0);
    }

    @Test
    void NAN_POINT_LONGITUDE_variation1() {
        SpatialContext context = new SpatialContext(false);
        verify(context, Double.NaN, 40.0, -10.0, 10.0, 0.0, 20.0);
    }

    @Test
    void NAN_POINT_LATITUDE_variation1() {
        SpatialContext context = new SpatialContext(true);
        verify(context, -40.0, 40.0, -10.0, 10.0, Double.NaN, -10.0);
    }
}
