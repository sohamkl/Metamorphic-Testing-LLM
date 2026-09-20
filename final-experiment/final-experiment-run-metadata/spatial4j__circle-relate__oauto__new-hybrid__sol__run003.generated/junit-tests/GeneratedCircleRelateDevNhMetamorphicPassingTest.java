import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);
    private static final double SHIFT_X = 17.5;
    private static final double SHIFT_Y = -9.25;

    private static CircleImpl circle(double x, double y, double radius) {
        return new CircleImpl(CONTEXT.makePoint(x, y), radius, CONTEXT);
    }

    private static RectangleImpl rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static CircleImpl emptyCircle() {
        Point emptyPoint = CONTEXT.makePoint(Double.NaN, Double.NaN);
        return new CircleImpl(emptyPoint, 1.0, CONTEXT);
    }

    private static RectangleImpl emptyRectangle() {
        return rectangle(Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    }

    private static Object[] generateFollowUp(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialContext context = sourceCircle.getContext();
        Point shiftedCenter = context.makePoint(
                sourceCircle.getCenter().getX() + SHIFT_X,
                sourceCircle.getCenter().getY() + SHIFT_Y);
        CircleImpl shiftedCircle =
                new CircleImpl(shiftedCenter, sourceCircle.getRadius(), context);
        RectangleImpl shiftedRectangle = new RectangleImpl(
                sourceRectangle.getMinX() + SHIFT_X,
                sourceRectangle.getMaxX() + SHIFT_X,
                sourceRectangle.getMinY() + SHIFT_Y,
                sourceRectangle.getMaxY() + SHIFT_Y,
                context);
        return new Object[]{shiftedCircle, shiftedRectangle};
    }

    private static void verify(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) sourceCircle)
                        .relate(sourceRectangle);

        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) followUpCircle)
                        .relate(followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_DISJOINT_HORIZONTAL_GAP_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(6.0, 9.0, -2.0, 2.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void BBOX_DISJOINT_VERTICAL_GAP_variation1() {
        CircleImpl sourceCircle = circle(12.0, -4.0, 0.5);
        RectangleImpl sourceRectangle = rectangle(11.8, 12.2, -3.0, -1.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void CIRCLE_STRICTLY_WITHIN_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(-3.0, 4.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(-9.0, 3.0, -2.0, 10.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BOUNDING_BOX_variation1() {
        CircleImpl sourceCircle = circle(1000.0, -2000.0, 50.0);
        Rectangle boundingBox = sourceCircle.getBoundingBox();
        RectangleImpl sourceRectangle = rectangle(
                boundingBox.getMinX(),
                boundingBox.getMaxX(),
                boundingBox.getMinY(),
                boundingBox.getMaxY());
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void CIRCLE_WITHIN_RECTANGLE_SHARED_EDGE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(-5.0, 7.0, -6.0, 6.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void PHASE2_UPPER_RIGHT_CORNER_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(10.0, 20.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(14.0, 16.0, 24.0, 26.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void PHASE2_UPPER_LEFT_CORNER_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(-2.0, 3.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(-8.0, -6.0, 7.0, 9.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RIGHT_SIDE_AXIAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(500.0, -700.0, 25.0);
        RectangleImpl sourceRectangle = rectangle(525.0, 540.0, -705.0, -695.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void TOP_SIDE_AXIAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(-2.0, 2.0, 5.0, 8.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void EXACT_CORNER_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(8.0, -6.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(11.0, 15.0, -2.0, 2.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void CORNER_POSITIVE_OVERLAP_variation1() {
        CircleImpl sourceCircle = circle(-4.0, 2.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(-1.0, 3.0, 5.0, 9.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RECTANGLE_SPANS_BOTH_CENTER_AXES_variation1() {
        CircleImpl sourceCircle = circle(100.0, 100.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(94.0, 106.0, 98.0, 102.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RECTANGLE_SPANS_Y_AXIS_FROM_RIGHT_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(3.0, 7.0, -2.0, 2.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RECTANGLE_STRICTLY_CONTAINED_CENTERED_variation1() {
        CircleImpl sourceCircle = circle(9.0, 7.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(7.0, 11.0, 5.0, 9.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RECTANGLE_STRICTLY_CONTAINED_OFF_CENTER_variation1() {
        CircleImpl sourceCircle = circle(-5.0, 1.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(-4.0, -2.0, 2.0, 3.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void CONTAINED_RECTANGLE_FARTHEST_CORNER_TANGENT_variation1() {
        CircleImpl sourceCircle = circle(1000.0, 1000.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(999.0, 1003.0, 996.0, 1000.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_BUT_CIRCLE_DOES_NOT_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(3.0, 5.0, 3.0, 5.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void SYMMETRIC_FARTHEST_CORNER_TIE_variation1() {
        CircleImpl sourceCircle = circle(13.0, -8.0, 6.0);
        RectangleImpl sourceRectangle = rectangle(10.0, 16.0, -12.0, -4.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void ASYMMETRIC_FARTHEST_CORNER_SELECTION_variation1() {
        CircleImpl sourceCircle = circle(-3.0, 6.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(-4.0, 1.0, 2.0, 7.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void POINT_RECTANGLE_INSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(10000.0, -10000.0, 50.0);
        RectangleImpl sourceRectangle =
                rectangle(10002.0, 10002.0, -9999.0, -9999.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void POINT_RECTANGLE_ON_CIRCUMFERENCE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(3.0, 3.0, 4.0, 4.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void POINT_RECTANGLE_OUTSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(7.0, 8.0, 0.75);
        RectangleImpl sourceRectangle = rectangle(8.0, 8.0, 8.0, 8.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void VERTICAL_LINE_RECTANGLE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(-2.0, 3.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(0.0, 0.0, 0.0, 6.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void HORIZONTAL_LINE_RECTANGLE_CROSSES_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(250.0, -300.0, 20.0);
        RectangleImpl sourceRectangle =
                rectangle(220.0, 280.0, -300.0, -300.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void VERTICAL_LINE_TANGENT_ONLY_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(5.0, 5.0, -3.0, 3.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void ZERO_RADIUS_CENTER_INSIDE_AREA_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(12.0, -7.0, 0.0);
        RectangleImpl sourceRectangle = rectangle(11.0, 13.0, -8.0, -6.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void ZERO_RADIUS_CENTER_OUTSIDE_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(-5.0, 4.0, 0.0);
        RectangleImpl sourceRectangle = rectangle(-3.0, -1.0, 6.0, 8.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(5000.0, -6000.0, 0.0);
        RectangleImpl sourceRectangle =
                rectangle(5000.0, 5000.0, -6000.0, -6000.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void INSCRIBED_RECTANGLE_ALL_CORNERS_ON_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(-3.0, 3.0, -4.0, 4.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void THIN_RECTANGLE_STRADDLING_CIRCUMFERENCE_variation1() {
        CircleImpl sourceCircle = circle(20.0, 10.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(24.9, 25.1, 9.8, 10.2);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void WIDE_FLAT_RECTANGLE_CROSSES_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(-6.0, 2.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(-13.0, 1.0, 1.0, 3.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void BBOX_CORNER_SLIVER_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(1000.0, -1000.0, 50.0);
        RectangleImpl sourceRectangle =
                rectangle(1040.0, 1050.0, -960.0, -950.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void BBOX_EDGE_SLIVER_INTERSECTS_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(4.9, 5.2, -1.0, 1.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void NON_ORIGIN_MIXED_SIGN_GEOMETRY_variation1() {
        CircleImpl sourceCircle = circle(-3.0, 4.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(-2.0, -1.0, 3.0, 5.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void LARGE_MAGNITUDE_CONTAINMENT_variation1() {
        CircleImpl sourceCircle = circle(1000000.0, -2000000.0, 100000.0);
        RectangleImpl sourceRectangle =
                rectangle(999000.0, 1001000.0, -2001000.0, -1999000.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void SMALL_POSITIVE_RADIUS_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(2.0, -3.0, 0.25);
        RectangleImpl sourceRectangle = rectangle(2.5, 2.7, -3.1, -2.9);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RECTANGLE_SHARES_BBOX_CORNER_AND_EXTENDS_OUTWARD_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(5.0, 7.0, 5.0, 7.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RECTANGLE_CROSSES_ONE_BBOX_BOUNDARY_variation1() {
        CircleImpl sourceCircle = circle(30.0, -20.0, 5.0);
        RectangleImpl sourceRectangle = rectangle(34.0, 36.0, -21.0, -19.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void EMPTY_CIRCLE_NONEMPTY_RECTANGLE_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        RectangleImpl sourceRectangle = rectangle(-2.0, 2.0, -3.0, 3.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void NONEMPTY_CIRCLE_EMPTY_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(1000.0, -2000.0, 50.0);
        RectangleImpl sourceRectangle = emptyRectangle();
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void BOTH_SHAPES_EMPTY_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        RectangleImpl sourceRectangle = emptyRectangle();
        verify(sourceCircle, sourceRectangle);
    }
}
