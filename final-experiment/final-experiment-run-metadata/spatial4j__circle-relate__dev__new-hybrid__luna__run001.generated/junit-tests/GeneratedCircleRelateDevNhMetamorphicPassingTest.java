import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static void check(double x, double y, double radius,
                              double minX, double maxX,
                              double minY, double maxY) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(x, y, context), radius, context);
        RectangleImpl rectangle = new RectangleImpl(
                minX, maxX, minY, maxY, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);

        Object[] followUpValues =
                CircleRelateDevMetamorphicSpec.generateFollowUp(circle, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        org.locationtech.spatial4j.shape.Rectangle followUpRectangle =
                (org.locationtech.spatial4j.shape.Rectangle) followUpValues[1];

        SpatialRelation followUpOutput =
                followUpCircle.relate(followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_DISJOINT_HORIZONTAL_variation1() {
        check(0, 0, 2, 10, 12, 0, 1);
    }

    @Test
    void BBOX_DISJOINT_HORIZONTAL_variation2() {
        check(-8, 6, 3, 10, 12, 6, 7);
    }

    @Test
    void BBOX_DISJOINT_VERTICAL_variation1() {
        check(-8, 6, 3, -10, -7, 12, 14);
    }

    @Test
    void BBOX_DISJOINT_VERTICAL_variation2() {
        check(8, -6, 2, -10, -7, 2, 4);
    }

    @Test
    void BBOX_WITHIN_STRICT_variation1() {
        check(0, 0, 5, -6, 7, -8, 6);
    }

    @Test
    void BBOX_WITHIN_STRICT_variation2() {
        check(-10, 8, 3, -14, -6, 4, 12);
    }

    @Test
    void BBOX_WITHIN_ON_BOUNDARY_variation1() {
        check(2, -3, 4, -2, 6, -5, 3);
    }

    @Test
    void BBOX_WITHIN_ON_BOUNDARY_variation2() {
        check(-5, 4, 2, -7, -3, 2, 6);
    }

    @Test
    void EQUAL_BBOX_IDENTITY_EDGE_variation1() {
        check(1, 1, 5, -4, 6, -4, 6);
    }

    @Test
    void EQUAL_BBOX_IDENTITY_EDGE_variation2() {
        check(-6, 3, 4, -10, -2, -1, 7);
    }

    @Test
    void RECTANGLE_STRICTLY_INSIDE_CIRCLE_variation1() {
        check(0, 0, 10, -2, 2, -3, 3);
    }

    @Test
    void RECTANGLE_STRICTLY_INSIDE_CIRCLE_variation2() {
        check(-12, 7, 8, -14, -10, 4, 10);
    }

    @Test
    void RECTANGLE_INSIDE_CIRCLE_CORNER_BOUNDARY_variation1() {
        check(0, 0, 5, -3, 3, -4, 4);
    }

    @Test
    void RECTANGLE_INSIDE_CIRCLE_CORNER_BOUNDARY_variation2() {
        check(4, -5, 5, 1, 7, -9, -1);
    }

    @Test
    void BBOX_CONTAINS_BUT_CORNER_OUTSIDE_variation1() {
        check(0, 0, 10, -9, 9, -5, 5);
    }

    @Test
    void BBOX_CONTAINS_BUT_CORNER_OUTSIDE_variation2() {
        check(-15, 8, 7, -21, -9, 3, 13);
    }

    @Test
    void PHASE_TWO_CORNER_DISJOINT_variation1() {
        check(0, 0, 5, 4, 6, 4, 6);
    }

    @Test
    void PHASE_TWO_CORNER_DISJOINT_variation2() {
        check(-8, 6, 4, -4, -2, 10, 12);
    }

    @Test
    void PHASE_TWO_CORNER_INTERSECTION_variation1() {
        check(0, 0, 5, 3, 6, 3, 6);
    }

    @Test
    void PHASE_TWO_CORNER_INTERSECTION_variation2() {
        check(9, -7, 6, 12, 15, -4, -1);
    }

    @Test
    void X_AXIS_SPANS_RECTANGLE_variation1() {
        check(0, 0, 5, -6, 6, 4, 6);
    }

    @Test
    void X_AXIS_SPANS_RECTANGLE_variation2() {
        check(-10, 5, 4, -16, -4, 9, 11);
    }

    @Test
    void Y_AXIS_SPANS_RECTANGLE_variation1() {
        check(0, 0, 5, 4, 6, -6, 6);
    }

    @Test
    void Y_AXIS_SPANS_RECTANGLE_variation2() {
        check(8, -6, 5, 12, 14, -11, -1);
    }

    @Test
    void BOTH_AXES_INTERIOR_PHASE_TWO_variation1() {
        check(0, 0, 5, -4, 4, -6, 6);
    }

    @Test
    void BOTH_AXES_INTERIOR_PHASE_TWO_variation2() {
        check(-9, 7, 6, -13, -5, 1, 13);
    }

    @Test
    void EXTERNAL_CORNER_TANGENCY_variation1() {
        check(0, 0, 5, 3, 6, 4, 6);
    }

    @Test
    void EXTERNAL_CORNER_TANGENCY_variation2() {
        check(10, -8, 5, 13, 16, -5, -2);
    }

    @Test
    void HORIZONTAL_DEGENERATE_RECTANGLE_variation1() {
        check(0, 0, 5, -6, 6, 4, 4);
    }

    @Test
    void HORIZONTAL_DEGENERATE_RECTANGLE_variation2() {
        check(-7, 6, 4, -12, -2, 9, 9);
    }

    @Test
    void VERTICAL_DEGENERATE_RECTANGLE_variation1() {
        check(0, 0, 5, 4, 4, -6, 6);
    }

    @Test
    void VERTICAL_DEGENERATE_RECTANGLE_variation2() {
        check(8, -5, 4, 12, 12, -9, -1);
    }

    @Test
    void POINT_RECTANGLE_INSIDE_CIRCLE_variation1() {
        check(0, 0, 5, 0, 0, 0, 0);
    }

    @Test
    void POINT_RECTANGLE_INSIDE_CIRCLE_variation2() {
        check(-6, 4, 5, -6, -6, 4, 4);
    }

    @Test
    void POINT_RECTANGLE_OUTSIDE_WITH_BBOX_DISJOINT_variation1() {
        check(-2, 3, 1, 4, 4, -1, -1);
    }

    @Test
    void POINT_RECTANGLE_OUTSIDE_WITH_BBOX_DISJOINT_variation2() {
        check(7, -6, 2, -4, -4, 5, 5);
    }

    @Test
    void ZERO_RADIUS_POINT_CONTAINED_variation1() {
        check(2, -1, 0, 1, 3, -2, 0);
    }

    @Test
    void ZERO_RADIUS_POINT_CONTAINED_variation2() {
        check(-6, 5, 0, -8, -4, 3, 7);
    }

    @Test
    void ZERO_RADIUS_POINT_DISJOINT_variation1() {
        check(-3, 4, 0, 1, 2, 0, 1);
    }

    @Test
    void ZERO_RADIUS_POINT_DISJOINT_variation2() {
        check(8, -7, 0, -2, 0, 2, 4);
    }

    @Test
    void NEGATIVE_AND_POSITIVE_COORDINATES_variation1() {
        check(-20, 15, 6, -18, -8, 10, 20);
    }

    @Test
    void NEGATIVE_AND_POSITIVE_COORDINATES_variation2() {
        check(-11, 9, 5, -8, 1, 5, 13);
    }

    @Test
    void TRANSLATION_CROSSES_ORIGIN_variation1() {
        check(-10, 8, 2, -1, 1, -2, -1);
    }

    @Test
    void TRANSLATION_CROSSES_ORIGIN_variation2() {
        check(-16, 10, 3, -4, -2, -5, -3);
    }
}
