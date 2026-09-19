import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static SpatialContext geo() {
        return new SpatialContext(true);
    }

    private static RectangleImpl rectangle(SpatialContext context,
                                           double minX, double maxX,
                                           double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    private static Point point(SpatialContext context, double x, double y) {
        return new PointImpl(x, y, context);
    }

    private static void verify(RectangleImpl sourceRectangle, Point sourcePoint) {
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

    private static void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                                   SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "The point/box relation changed from " + sourceOutput
                            + " to " + followUpOutput + ".");
        }
    }

    @Test
    public void NONWRAPPING_INTERIOR_DIRECT_CONTAINS_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, 10.0, 5.0));
    }

    @Test
    public void NONWRAPPING_LEFT_LONGITUDE_DISJOINT_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, -100.0, 0.0));
    }

    @Test
    public void NONWRAPPING_RIGHT_LONGITUDE_DISJOINT_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, 100.0, 0.0));
    }

    @Test
    public void RECTANGLE_LONGITUDE_BOUNDARY_CONTAINS_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, -40.0, 0.0));
    }

    @Test
    public void RECTANGLE_LONGITUDE_BOUNDARY_CONTAINS_2() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, 40.0, 0.0));
    }

    @Test
    public void LATITUDE_BELOW_DISJOINT_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, 0.0, -20.0000000001));
    }

    @Test
    public void LATITUDE_ABOVE_DISJOINT_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, 0.0, 20.0000000001));
    }

    @Test
    public void LATITUDE_BOUNDARIES_AND_LONGITUDE_INTERIOR_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, 0.0, -20.0));
    }

    @Test
    public void LATITUDE_BOUNDARIES_AND_LONGITUDE_INTERIOR_2() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, 0.0, 20.0));
    }

    @Test
    public void DATELINE_WRAP_DIRECT_CONTAINS_NEAR_EAST_EDGE_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, 170.0, -170.0, -20.0, 20.0),
                point(context, 175.0, 0.0));
    }

    @Test
    public void DATELINE_WRAP_LEFT_SHIFT_CONTAINS_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, 170.0, -170.0, -20.0, 20.0),
                point(context, -175.0, 0.0));
    }

    @Test
    public void DATELINE_WRAP_LONGITUDE_GAP_DISJOINT_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, 170.0, -170.0, -20.0, 20.0),
                point(context, 0.0, 0.0));
    }

    @Test
    public void DATELINE_WRAP_BOUNDARY_CONTAINS_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, 170.0, -170.0, -20.0, 20.0),
                point(context, 170.0, 0.0));
    }

    @Test
    public void DATELINE_WRAP_BOUNDARY_CONTAINS_2() {
        SpatialContext context = geo();
        verify(
                rectangle(context, 170.0, -170.0, -20.0, 20.0),
                point(context, -170.0, 0.0));
    }

    @Test
    public void DATELINE_WRAP_RIGHT_SHIFT_DISJOINT_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, 170.0, -170.0, -20.0, 20.0),
                point(context, 10.0, 0.0));
    }

    @Test
    public void ROTATION_PRESERVES_NONWRAPPING_CONTAINMENT_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -35.0, 45.0, -20.0, 20.0),
                point(context, 10.0, 5.0));
    }

    @Test
    public void ROTATION_PRESERVES_DATELINE_DISJOINTNESS_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, 165.0, -165.0, -20.0, 20.0),
                point(context, 0.0, 0.0));
    }

    @Test
    public void ROTATION_CROSSES_DATELINE_FROM_ORDINARY_BOX_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, 20.0, 80.0, -10.0, 10.0),
                point(context, 50.0, 0.0));
    }

    @Test
    public void ROTATION_REMOVES_DATELINE_WRAP_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -170.0, -100.0, -10.0, 10.0),
                point(context, -130.0, 0.0));
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_LINE_CONTAINS_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, 30.0, 30.0, -20.0, 20.0),
                point(context, 30.0, 0.0));
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_LINE_DISJOINT_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, 30.0, 30.0, -20.0, 20.0),
                point(context, 30.0000000001, 0.0));
    }

    @Test
    public void EMPTY_NAN_MIN_X_SENTINEL_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, Double.NaN, 40.0, -20.0, 20.0),
                point(context, 0.0, 0.0));
    }

    @Test
    public void NAN_POINT_LONGITUDE_SENTINEL_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, Double.NaN, 0.0));
    }

    @Test
    public void NAN_POINT_LATITUDE_SENTINEL_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -40.0, 40.0, -20.0, 20.0),
                point(context, 0.0, Double.NaN));
    }

    @Test
    public void MINUS_180_PLUS_180_ENDPOINTS_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -180.0, -179.0, -90.0, 90.0),
                point(context, -180.0, -90.0));
    }

    @Test
    public void MINUS_180_PLUS_180_ENDPOINTS_2() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -180.0, -179.0, -90.0, 90.0),
                point(context, -179.0, 90.0));
    }

    @Test
    public void FULL_LATITUDE_RANGE_INTERIOR_LONGITUDE_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -10.0, 10.0, -90.0, 90.0),
                point(context, 0.0, 90.0));
    }

    @Test
    public void FULL_LATITUDE_RANGE_LONGITUDE_GAP_1() {
        SpatialContext context = geo();
        verify(
                rectangle(context, -10.0, 10.0, -90.0, 90.0),
                point(context, 20.0, -90.0));
    }
}
