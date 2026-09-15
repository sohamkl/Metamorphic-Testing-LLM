import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static SpatialContext context() {
        return new SpatialContext(false);
    }

    private static CircleImpl circle(double x, double y, double radius) {
        SpatialContext context = context();
        return new CircleImpl(context.makePoint(x, y), radius, context);
    }

    private static CircleImpl emptyCircle() {
        SpatialContext context = context();
        return new CircleImpl(context.makePoint(Double.NaN, Double.NaN), 0.0, context);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context());
    }

    private static void check(CircleImpl source, Rectangle rectangle) {
        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                source.relate(rectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);

        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate(
                        (Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_DISJOINT_FAR_RECTANGLE_1() {
        CircleImpl source = circle(0, 0, 2);
        Rectangle rectangle = rectangle(10, 12, 10, 12);
        check(source, rectangle);
    }

    @Test
    public void BBOX_DISJOINT_FAR_RECTANGLE_2() {
        CircleImpl source = circle(-20, 15, 0);
        Rectangle rectangle = rectangle(-2, 2, 18, 22);
        check(source, rectangle);
    }

    @Test
    public void BBOX_WITHIN_LARGE_RECTANGLE_1() {
        CircleImpl source = circle(0, 0, 2);
        Rectangle rectangle = rectangle(-3, 3, -3, 3);
        check(source, rectangle);
    }

    @Test
    public void BBOX_WITHIN_LARGE_RECTANGLE_2() {
        CircleImpl source = circle(4, -3, 1);
        Rectangle rectangle = rectangle(2, 6, -5, -1);
        check(source, rectangle);
    }

    @Test
    public void BBOX_EQUALITY_IDENTITY_SENTINEL_1() {
        CircleImpl source = circle(0, 0, 2);
        Rectangle rectangle = rectangle(-2, 2, -2, 2);
        check(source, rectangle);
    }

    @Test
    public void BBOX_EQUALITY_IDENTITY_SENTINEL_2() {
        CircleImpl source = circle(7, -4, 3);
        Rectangle rectangle = rectangle(4, 10, -7, -1);
        check(source, rectangle);
    }

    @Test
    public void BBOX_EQUALITY_IDENTITY_SENTINEL_3() {
        CircleImpl source = circle(-6, 8, 1.5);
        Rectangle rectangle = rectangle(-7.5, -4.5, 6.5, 9.5);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_CONTAINS_SMALL_RECTANGLE_1() {
        CircleImpl source = circle(0, 0, 10);
        Rectangle rectangle = rectangle(-1, 1, -1, 1);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_CONTAINS_SMALL_RECTANGLE_2() {
        CircleImpl source = circle(2, -3, 8);
        Rectangle rectangle = rectangle(1, 3, -4, -2);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_CONTAINS_SMALL_RECTANGLE_3() {
        CircleImpl source = circle(-4, 5, 12);
        Rectangle rectangle = rectangle(-5, -3, 4, 6);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_CONTAINS_FARTHEST_MAX_X_1() {
        CircleImpl source = circle(0, 0, 10);
        Rectangle rectangle = rectangle(-1, 3, -1, 1);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_CONTAINS_FARTHEST_MAX_X_2() {
        CircleImpl source = circle(5, -2, 6);
        Rectangle rectangle = rectangle(4, 7, -3, 0);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_CONTAINS_FARTHEST_MIN_X_1() {
        CircleImpl source = circle(0, 0, 10);
        Rectangle rectangle = rectangle(-3, 1, -1, 1);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_CONTAINS_FARTHEST_MIN_X_2() {
        CircleImpl source = circle(-3, 4, 7);
        Rectangle rectangle = rectangle(-6, -2, 3, 5);
        check(source, rectangle);
    }

    @Test
    public void BBOX_CONTAINS_CORNER_OUTSIDE_1() {
        CircleImpl source = circle(0, 0, 5);
        Rectangle rectangle = rectangle(-4, 4, -4, 4);
        check(source, rectangle);
    }

    @Test
    public void BBOX_CONTAINS_CORNER_OUTSIDE_2() {
        CircleImpl source = circle(3, -2, 6);
        Rectangle rectangle = rectangle(0, 6, -5, 1);
        check(source, rectangle);
    }

    @Test
    public void BBOX_CONTAINS_CORNER_OUTSIDE_3() {
        CircleImpl source = circle(-2, 3, 4);
        Rectangle rectangle = rectangle(-5, 1, 0, 6);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_INTERSECTS_CORNER_OVERLAP_1() {
        CircleImpl source = circle(0, 0, 5);
        Rectangle rectangle = rectangle(4, 6, 0, 1);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_INTERSECTS_CORNER_OVERLAP_2() {
        CircleImpl source = circle(-3, 2, 5);
        Rectangle rectangle = rectangle(1, 5, 1, 4);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_DISJOINT_BOTH_AXES_1() {
        CircleImpl source = circle(0, 0, 5);
        Rectangle rectangle = rectangle(4, 6, 4, 6);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_DISJOINT_BOTH_AXES_2() {
        CircleImpl source = circle(2, -3, 5);
        Rectangle rectangle = rectangle(5, 8, 1, 4);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_DISJOINT_BOTH_AXES_3() {
        CircleImpl source = circle(-4, 5, 3);
        Rectangle rectangle = rectangle(-1, 2, 8, 11);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_EXTERNAL_TANGENCY_CORNER_1() {
        CircleImpl source = circle(0, 0, 5);
        Rectangle rectangle = rectangle(3, 4, 4, 5);
        check(source, rectangle);
    }

    @Test
    public void PHASE2_EXTERNAL_TANGENCY_CORNER_2() {
        CircleImpl source = circle(2, -1, 5);
        Rectangle rectangle = rectangle(5, 6, 2, 3);
        check(source, rectangle);
    }

    @Test
    public void X_AXIS_INSIDE_Y_AXIS_OUTSIDE_1() {
        CircleImpl source = circle(0, 0, 5);
        Rectangle rectangle = rectangle(-1, 1, 4, 6);
        check(source, rectangle);
    }

    @Test
    public void X_AXIS_INSIDE_Y_AXIS_OUTSIDE_2() {
        CircleImpl source = circle(3, -4, 6);
        Rectangle rectangle = rectangle(2, 4, 0, 3);
        check(source, rectangle);
    }

    @Test
    public void X_AXIS_OUTSIDE_Y_AXIS_INSIDE_1() {
        CircleImpl source = circle(0, 0, 5);
        Rectangle rectangle = rectangle(4, 6, -1, 1);
        check(source, rectangle);
    }

    @Test
    public void X_AXIS_OUTSIDE_Y_AXIS_INSIDE_2() {
        CircleImpl source = circle(-2, 4, 7);
        Rectangle rectangle = rectangle(3, 6, 2, 6);
        check(source, rectangle);
    }

    @Test
    public void SIDE_TANGENCY_WITH_SPANNED_AXIS_1() {
        CircleImpl source = circle(0, 0, 5);
        Rectangle rectangle = rectangle(5, 6, -1, 1);
        check(source, rectangle);
    }

    @Test
    public void SIDE_TANGENCY_WITH_SPANNED_AXIS_2() {
        CircleImpl source = circle(4, -2, 3);
        Rectangle rectangle = rectangle(7, 9, -4, 1);
        check(source, rectangle);
    }

    @Test
    public void ZERO_RADIUS_POINT_WITHIN_RECTANGLE_1() {
        CircleImpl source = circle(0, 0, 0);
        Rectangle rectangle = rectangle(-1, 1, -1, 1);
        check(source, rectangle);
    }

    @Test
    public void ZERO_RADIUS_POINT_WITHIN_RECTANGLE_2() {
        CircleImpl source = circle(3, -2, 0);
        Rectangle rectangle = rectangle(2, 4, -3, 0);
        check(source, rectangle);
    }

    @Test
    public void ZERO_RADIUS_POINT_OUTSIDE_RECTANGLE_1() {
        CircleImpl source = circle(0, 0, 0);
        Rectangle rectangle = rectangle(2, 3, 2, 3);
        check(source, rectangle);
    }

    @Test
    public void ZERO_RADIUS_POINT_OUTSIDE_RECTANGLE_2() {
        CircleImpl source = circle(-4, 5, 0);
        Rectangle rectangle = rectangle(-1, 1, 7, 9);
        check(source, rectangle);
    }

    @Test
    public void ZERO_AREA_RECTANGLE_POINT_INSIDE_CIRCLE_1() {
        CircleImpl source = circle(0, 0, 5);
        Rectangle rectangle = rectangle(1, 1, 1, 1);
        check(source, rectangle);
    }

    @Test
    public void ZERO_AREA_RECTANGLE_POINT_INSIDE_CIRCLE_2() {
        CircleImpl source = circle(-3, 4, 6);
        Rectangle rectangle = rectangle(-2, 0, 5, 5);
        check(source, rectangle);
    }

    @Test
    public void ZERO_AREA_RECTANGLE_ON_CIRCLE_BOUNDARY_1() {
        CircleImpl source = circle(0, 0, 5);
        Rectangle rectangle = rectangle(5, 5, 0, 0);
        check(source, rectangle);
    }

    @Test
    public void ZERO_AREA_RECTANGLE_ON_CIRCLE_BOUNDARY_2() {
        CircleImpl source = circle(2, -3, 4);
        Rectangle rectangle = rectangle(6, 6, -3, -3);
        check(source, rectangle);
    }

    @Test
    public void EMPTY_CENTER_SENTINEL_1() {
        CircleImpl source = emptyCircle();
        Rectangle rectangle = rectangle(-3, 3, -2, 2);
        check(source, rectangle);
    }
}
