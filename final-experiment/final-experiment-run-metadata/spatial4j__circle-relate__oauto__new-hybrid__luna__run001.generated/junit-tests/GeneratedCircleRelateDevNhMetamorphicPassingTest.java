import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final double SHIFT_X = 17.5;
    private static final double SHIFT_Y = -9.25;

    private static void check(
            double centerX,
            double centerY,
            double radius,
            double minX,
            double maxX,
            double minY,
            double maxY) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                context.makePoint(centerX, centerY), radius, context);
        Rectangle rectangle = new RectangleImpl(
                minX, maxX, minY, maxY, context);
        checkRelation(circle, rectangle);
    }

    private static void checkEmpty(
            double minX,
            double maxX,
            double minY,
            double maxY) {
        SpatialContext context = new SpatialContext(false);
        Point empty = new PointImpl(Double.NaN, Double.NaN, context);
        CircleImpl circle = new CircleImpl(empty, 0.0, context);
        Rectangle rectangle = new RectangleImpl(
                minX, maxX, minY, maxY, context);
        checkRelation(circle, rectangle);
    }

    private static void checkRelation(
            CircleImpl sourceCircle,
            Rectangle sourceRectangle) {
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);

        SpatialContext context = sourceCircle.getContext();
        Point sourceCenter = sourceCircle.getCenter();
        Point shiftedCenter = context.makePoint(
                sourceCenter.getX() + SHIFT_X,
                sourceCenter.getY() + SHIFT_Y);
        CircleImpl shiftedCircle = new CircleImpl(
                shiftedCenter, sourceCircle.getRadius(), context);

        Rectangle shiftedRectangle = new RectangleImpl(
                sourceRectangle.getMinX() + SHIFT_X,
                sourceRectangle.getMaxX() + SHIFT_X,
                sourceRectangle.getMinY() + SHIFT_Y,
                sourceRectangle.getMaxY() + SHIFT_Y,
                context);

        SpatialRelation followUpOutput =
                shiftedCircle.relate(shiftedRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    void EMPTY_CIRCLE_SENTINEL_1() {
        checkEmpty(0.0, 1.0, 0.0, 1.0);
    }

    @Test
    void BBOX_DISJOINT_WITH_AXIS_SEPARATION_1() {
        check(0.0, 0.0, 2.0, 3.0, 4.0, -1.0, 1.0);
    }

    @Test
    void BBOX_DISJOINT_WITH_AXIS_SEPARATION_2() {
        check(-1.0, 2.0, 1.0, 1.5, 2.5, 1.0, 3.0);
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_1() {
        check(0.0, 0.0, 2.0, -3.0, 3.0, -4.0, 4.0);
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_2() {
        check(4.0, -2.0, 5.0, -2.0, 10.0, -9.0, 5.0);
    }

    @Test
    void BBOX_EQUALITY_IDENTITY_EDGE_1() {
        check(0.0, 0.0, 2.0, -2.0, 2.0, -2.0, 2.0);
    }

    @Test
    void BBOX_EQUALITY_IDENTITY_EDGE_2() {
        check(-3.0, 4.0, 1.0, -4.0, -2.0, 3.0, 5.0);
    }

    @Test
    void BBOX_EQUALITY_IDENTITY_EDGE_3() {
        check(5.0, -3.0, 2.0, 3.0, 7.0, -5.0, -1.0);
    }

    @Test
    void PHASE_TWO_CORNER_DISJOINT_1() {
        check(0.0, 0.0, 2.0, 1.5, 3.0, 1.5, 3.0);
    }

    @Test
    void PHASE_TWO_CORNER_DISJOINT_2() {
        check(-2.0, 1.0, 2.0, -0.4, 1.0, 2.6, 4.0);
    }

    @Test
    void PHASE_TWO_CORNER_DISJOINT_3() {
        check(3.0, -2.0, 1.0, 4.0, 5.5, -0.5, 1.0);
    }

    @Test
    void PHASE_TWO_CORNER_TANGENCY_1() {
        check(0.0, 0.0, 2.0, 2.0, 3.0, 0.0, 1.0);
    }

    @Test
    void PHASE_TWO_CORNER_TANGENCY_2() {
        check(-2.0, 1.0, 2.0, 0.0, 1.0, 1.0, 2.0);
    }

    @Test
    void PHASE_TWO_CORNER_TANGENCY_3() {
        check(4.0, -3.0, 3.0, 7.0, 8.0, -3.0, -1.0);
    }

    @Test
    void PHASE_TWO_BOTH_AXES_OUTSIDE_INTERSECTION_1() {
        check(0.0, 0.0, 2.0, 1.0, 3.0, 0.5, 1.5);
    }

    @Test
    void PHASE_TWO_BOTH_AXES_OUTSIDE_INTERSECTION_2() {
        check(-3.0, 2.0, 3.0, -1.0, 1.0, 3.0, 4.0);
    }

    @Test
    void PHASE_TWO_BOTH_AXES_OUTSIDE_INTERSECTION_3() {
        check(2.0, -2.0, 2.0, 3.0, 5.0, -0.5, 1.0);
    }

    @Test
    void X_OUTSIDE_Y_INSIDE_INTERSECTION_1() {
        check(0.0, 0.0, 2.0, 1.0, 3.0, -1.0, 1.0);
    }

    @Test
    void X_OUTSIDE_Y_INSIDE_INTERSECTION_2() {
        check(-3.0, 2.0, 3.0, -1.0, 1.0, 1.0, 3.0);
    }

    @Test
    void X_INSIDE_Y_OUTSIDE_INTERSECTION_1() {
        check(0.0, 0.0, 2.0, -1.0, 1.0, 1.0, 3.0);
    }

    @Test
    void X_INSIDE_Y_OUTSIDE_INTERSECTION_2() {
        check(4.0, -3.0, 3.0, 3.0, 5.0, 0.0, 2.0);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_CONTAINED_1() {
        check(0.0, 0.0, 2.0, -1.0, 1.0, -1.0, 1.0);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_CONTAINED_2() {
        check(-3.0, 4.0, 3.0, -4.0, -2.0, 3.0, 5.0);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_CONTAINED_3() {
        check(5.0, -3.0, 4.0, 4.0, 6.0, -4.0, -2.0);
    }

    @Test
    void BBOX_CONTAINS_CORNER_OUTSIDE_1() {
        check(0.0, 0.0, 2.0, -1.9, 1.9, -1.9, 1.9);
    }

    @Test
    void BBOX_CONTAINS_CORNER_OUTSIDE_2() {
        check(4.0, -3.0, 3.0, 2.2, 5.8, -4.8, -1.2);
    }

    @Test
    void BBOX_CONTAINS_CORNER_OUTSIDE_3() {
        check(-3.0, 4.0, 2.0, -4.9, -1.1, 2.1, 5.9);
    }

    @Test
    void FARTHEST_CORNER_RADIUS_BOUNDARY_1() {
        check(0.0, 0.0, 2.0, 0.0, 2.0, 0.0, 0.0);
    }

    @Test
    void FARTHEST_CORNER_RADIUS_BOUNDARY_2() {
        check(-3.0, 4.0, 3.0, -3.0, 0.0, 4.0, 4.0);
    }

    @Test
    void AXIS_ON_RECTANGLE_BOUNDARY_1() {
        check(0.0, 0.0, 2.0, 0.0, 1.0, 0.0, 1.0);
    }

    @Test
    void AXIS_ON_RECTANGLE_BOUNDARY_2() {
        check(-2.0, 3.0, 3.0, -2.0, -1.0, 3.0, 4.0);
    }

    @Test
    void ZERO_RADIUS_RECTANGLE_CONTAINS_POINT_1() {
        check(0.0, 0.0, 0.0, -1.0, 1.0, -1.0, 1.0);
    }

    @Test
    void ZERO_RADIUS_RECTANGLE_CONTAINS_POINT_2() {
        check(-4.0, 3.0, 0.0, -5.0, -3.0, 2.0, 4.0);
    }

    @Test
    void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_1() {
        check(5.0, -3.0, 0.0, 5.0, 5.0, -3.0, -3.0);
    }

    @Test
    void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_2() {
        check(-4.0, 6.0, 0.0, -4.0, -4.0, 6.0, 6.0);
    }

    @Test
    void ZERO_RADIUS_POINT_RECTANGLE_DISJOINT_1() {
        check(0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0);
    }

    @Test
    void ZERO_RADIUS_POINT_RECTANGLE_DISJOINT_2() {
        check(-3.0, 4.0, 0.0, -1.0, -1.0, 4.0, 4.0);
    }

    @Test
    void ZERO_WIDTH_RECTANGLE_INTERSECTION_1() {
        check(0.0, 0.0, 2.0, 1.0, 1.0, -1.5, 1.5);
    }

    @Test
    void ZERO_WIDTH_RECTANGLE_INTERSECTION_2() {
        check(-3.0, 4.0, 3.0, -1.0, -1.0, 2.5, 5.5);
    }

    @Test
    void ZERO_HEIGHT_RECTANGLE_INTERSECTION_1() {
        check(0.0, 0.0, 2.0, -1.5, 1.5, 1.0, 1.0);
    }

    @Test
    void ZERO_HEIGHT_RECTANGLE_INTERSECTION_2() {
        check(4.0, -3.0, 3.0, 2.5, 5.5, -2.0, -2.0);
    }
}
