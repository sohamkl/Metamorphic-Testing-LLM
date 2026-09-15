import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.SpatialContextFactory;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CONTEXT = createContext();

    private static SpatialContext createContext() {
        SpatialContextFactory factory = new SpatialContextFactory();
        factory.geo = false;
        return factory.newSpatialContext();
    }

    private static CircleImpl circle(double x, double y, double radius) {
        Point center = CONTEXT.makePoint(x, y);
        return new CircleImpl(center, radius, CONTEXT);
    }

    private static CircleImpl emptyCircle() {
        return new CircleImpl(CONTEXT.makePoint(Double.NaN, Double.NaN), 0.0, CONTEXT);
    }

    private static Rectangle rectangle(double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static Rectangle emptyRectangle() {
        return new RectangleImpl(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN, CONTEXT);
    }

    private static void exercise(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) sourceCircle)
                        .relate(sourceRectangle);
        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) followUpCircle)
                        .relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_NONEMPTY_RECTANGLE_variation1() {
        exercise(emptyCircle(), rectangle(-2.0, 3.0, -1.0, 4.0));
    }

    @Test
    public void NONEMPTY_CIRCLE_EMPTY_RECTANGLE_variation1() {
        exercise(circle(1.0, 2.0, 3.0), emptyRectangle());
    }

    @Test
    public void BOTH_SHAPES_EMPTY_variation1() {
        exercise(emptyCircle(), emptyRectangle());
    }

    @Test
    public void STRICT_HORIZONTAL_BBOX_SEPARATION_variation1_right() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(6.0, 9.0, -2.0, 2.0));
    }

    @Test
    public void STRICT_HORIZONTAL_BBOX_SEPARATION_variation2_left() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-9.0, -6.0, -3.0, 1.0));
    }

    @Test
    public void STRICT_VERTICAL_BBOX_SEPARATION_variation1_above() {
        exercise(circle(2.0, -1.0, 4.0), rectangle(0.0, 4.0, 4.0, 7.0));
    }

    @Test
    public void STRICT_VERTICAL_BBOX_SEPARATION_variation2_below() {
        exercise(circle(2.0, -1.0, 4.0), rectangle(1.0, 5.0, -8.0, -6.0));
    }

    @Test
    public void CIRCLE_BBOX_STRICTLY_WITHIN_RECTANGLE_variation1() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-7.0, 8.0, -9.0, 6.0));
    }

    @Test
    public void CIRCLE_BBOX_WITHIN_WITH_SHARED_EDGE_variation1_sharedMinX() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-5.0, 8.0, -7.0, 9.0));
    }

    @Test
    public void CIRCLE_BBOX_WITHIN_WITH_SHARED_EDGE_variation2_sharedMaxY() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-8.0, 7.0, -9.0, 5.0));
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BOUNDING_BOX_variation1() {
        exercise(circle(0.25, -0.75, 2.5), rectangle(-2.25, 2.75, -3.25, 1.75));
    }

    @Test
    public void VERTICAL_SIDE_TANGENCY_variation1_right() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(5.0, 7.0, -1.0, 1.0));
    }

    @Test
    public void VERTICAL_SIDE_TANGENCY_variation2_left() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-7.0, -5.0, -1.0, 1.0));
    }

    @Test
    public void HORIZONTAL_SIDE_TANGENCY_variation1_top() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-1.0, 1.0, 5.0, 7.0));
    }

    @Test
    public void HORIZONTAL_SIDE_TANGENCY_variation2_bottom() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-1.0, 1.0, -7.0, -5.0));
    }

    @Test
    public void DIAGONAL_BBOX_OVERLAP_BUT_GEOMETRIC_GAP_variation1_upperRight() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(4.0, 6.0, 4.0, 6.0));
    }

    @Test
    public void DIAGONAL_BBOX_OVERLAP_BUT_GEOMETRIC_GAP_variation2_lowerLeft() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-6.0, -4.0, -6.0, -4.0));
    }

    @Test
    public void DIAGONAL_CORNER_TANGENCY_variation1_upperRight() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(3.0, 6.0, 4.0, 6.0));
    }

    @Test
    public void DIAGONAL_CORNER_TANGENCY_variation2_lowerLeft() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-6.0, -3.0, -6.0, -4.0));
    }

    @Test
    public void DIAGONAL_POSITIVE_PENETRATION_variation1_upperRight() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(2.0, 6.0, 4.0, 6.0));
    }

    @Test
    public void DIAGONAL_POSITIVE_PENETRATION_variation2_lowerLeft() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-6.0, -2.0, -6.0, -4.0));
    }

    @Test
    public void CONTAINED_RECTANGLE_IN_EACH_QUADRANT_variation1_upperRight() {
        exercise(circle(0.0, 0.0, 3.0), rectangle(1.0, 2.0, 1.0, 2.0));
    }

    @Test
    public void CONTAINED_RECTANGLE_IN_EACH_QUADRANT_variation2_upperLeft() {
        exercise(circle(0.0, 0.0, 3.0), rectangle(-2.0, -1.0, 1.0, 2.0));
    }

    @Test
    public void CONTAINED_RECTANGLE_IN_EACH_QUADRANT_variation3_lowerLeft() {
        exercise(circle(0.0, 0.0, 3.0), rectangle(-2.0, -1.0, -2.0, -1.0));
    }

    @Test
    public void CONTAINED_RECTANGLE_IN_EACH_QUADRANT_variation4_lowerRight() {
        exercise(circle(0.0, 0.0, 3.0), rectangle(1.0, 2.0, -2.0, -1.0));
    }

    @Test
    public void CONTAINED_RECTANGLE_CROSSES_BOTH_AXES_variation1() {
        exercise(circle(0.0, 0.0, 3.0), rectangle(-1.0, 2.0, -1.0, 1.0));
    }

    @Test
    public void FARTHEST_CORNER_EXACTLY_ON_CIRCUMFERENCE_variation1() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-3.0, 3.0, -4.0, 4.0));
    }

    @Test
    public void BBOX_CONTAINS_BUT_CIRCLE_DOES_NOT_variation1() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-4.0, 4.0, -4.0, 4.0));
    }

    @Test
    public void VERTICAL_STRIP_CROSSES_Y_AXIS_variation1_right() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(4.0, 6.0, -2.0, 2.0));
    }

    @Test
    public void VERTICAL_STRIP_CROSSES_Y_AXIS_variation2_left() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-6.0, -4.0, -2.0, 2.0));
    }

    @Test
    public void HORIZONTAL_STRIP_CROSSES_X_AXIS_variation1_above() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-2.0, 2.0, 4.0, 6.0));
    }

    @Test
    public void HORIZONTAL_STRIP_CROSSES_X_AXIS_variation2_below() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-2.0, 2.0, -6.0, -4.0));
    }

    @Test
    public void POINT_RECTANGLE_AT_CIRCLE_CENTER_variation1() {
        exercise(circle(1.5, -2.5, 4.0), rectangle(1.5, 1.5, -2.5, -2.5));
    }

    @Test
    public void POINT_RECTANGLE_ON_CIRCUMFERENCE_variation1() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(3.0, 3.0, 4.0, 4.0));
    }

    @Test
    public void POINT_RECTANGLE_OUTSIDE_CIRCLE_INSIDE_BBOX_variation1() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(4.0, 4.0, 4.0, 4.0));
    }

    @Test
    public void DIAMETER_LINE_RECTANGLE_variation1() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(-5.0, 5.0, 0.0, 0.0));
    }

    @Test
    public void TANGENT_LINE_WITH_OUTSIDE_ENDPOINTS_variation1() {
        exercise(circle(0.0, 0.0, 5.0), rectangle(5.0, 5.0, -2.0, 2.0));
    }

    @Test
    public void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation1() {
        exercise(circle(1.25, -3.5, 0.0), rectangle(1.25, 1.25, -3.5, -3.5));
    }

    @Test
    public void ZERO_RADIUS_STRICTLY_INSIDE_RECTANGLE_variation1() {
        exercise(circle(0.0, 0.0, 0.0), rectangle(-2.0, 3.0, -1.0, 4.0));
    }

    @Test
    public void ZERO_RADIUS_ON_RECTANGLE_BOUNDARY_variation1() {
        exercise(circle(0.0, 0.0, 0.0), rectangle(0.0, 2.0, -1.0, 1.0));
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_POINT_variation1() {
        exercise(circle(0.0, 0.0, 0.0), rectangle(1.0, 1.0, 0.0, 0.0));
    }

    @Test
    public void NON_ORIGIN_DECIMAL_CONTAINMENT_variation1() {
        double cx = 0.1;
        double cy = -0.3;
        exercise(
                circle(cx, cy, 2.75),
                rectangle(cx + 0.25, cx + 0.75, cy - 0.5, cy + 0.5));
    }

    @Test
    public void LARGE_FINITE_COORDINATE_TANGENCY_variation1() {
        double cx = Math.scalb(1.0, 40);
        double cy = -Math.scalb(1.0, 40);
        exercise(
                circle(cx, cy, 100.0),
                rectangle(cx + 60.0, cx + 140.0, cy + 80.0, cy + 140.0));
    }
}
