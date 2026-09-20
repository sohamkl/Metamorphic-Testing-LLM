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

    private static CircleImpl circle(double x, double y, double radius) {
        SpatialContext context = new SpatialContext(false);
        Point point = new PointImpl(x, y, context);
        return new CircleImpl(point, radius, context);
    }

    private static CircleImpl emptyCircle() {
        SpatialContext context = new SpatialContext(false);
        Point point = new PointImpl(Double.NaN, Double.NaN, context);
        return new CircleImpl(point, 10.0, context);
    }

    private static Rectangle rectangle(CircleImpl circle, double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, circle.getContext());
    }

    private static Rectangle emptyRectangle(CircleImpl circle) {
        return new RectangleImpl(Double.NaN, Double.NaN, Double.NaN, Double.NaN, circle.getContext());
    }

    private static Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        SpatialContext context = circle.getContext();
        Point center = circle.getCenter();
        Point shiftedCenter = context.makePoint(center.getX() + SHIFT_X, center.getY() + SHIFT_Y);
        CircleImpl shiftedCircle = new CircleImpl(shiftedCenter, circle.getRadius(), context);
        Rectangle shiftedRectangle = new RectangleImpl(
                rectangle.getMinX() + SHIFT_X,
                rectangle.getMaxX() + SHIFT_X,
                rectangle.getMinY() + SHIFT_Y,
                rectangle.getMaxY() + SHIFT_Y,
                context);
        return new Object[]{shiftedCircle, shiftedRectangle};
    }

    @Test
    public void empty_circle_if_translatable_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = rectangle(sourceCircle, 20.0, 25.0, -2.0, 2.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void empty_rectangle_if_translatable_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = emptyRectangle(sourceCircle);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void bbox_disjoint_right_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 11.0, 15.0, -2.0, 2.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void bbox_within_strict_rectangle_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -11.0, 11.0, -11.0, 11.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void exact_bounding_box_sentinel_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -10.0, 10.0, -10.0, 10.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void phase2_diagonal_northeast_disjoint_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 8.0, 12.0, 8.0, 12.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void phase2_diagonal_northwest_disjoint_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -12.0, -8.0, 8.0, 12.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void phase2_diagonal_southeast_disjoint_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 8.0, 12.0, -12.0, -8.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void phase2_diagonal_southwest_disjoint_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -12.0, -8.0, -12.0, -8.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void phase2_x_span_y_above_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -2.5, 2.5, 8.0, 12.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void phase2_x_span_y_below_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -2.5, 2.5, -12.0, -8.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void phase2_y_span_x_right_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 8.0, 12.0, -2.5, 2.5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void phase2_y_span_x_left_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -12.0, -8.0, -2.5, 2.5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void phase2_both_axes_spanned_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -2.5, 12.0, -2.5, 2.5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void bbox_contains_rectangle_farthest_outside_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -8.0, 8.0, -8.0, 8.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void bbox_contains_corner_near_but_far_corner_outside_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 6.0, 9.0, 6.0, 9.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void bbox_contains_strictly_inside_rectangle_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -5.0, 5.0, -5.0, 5.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void bbox_contains_off_center_rectangle_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 2.0, 4.0, 2.0, 4.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void external_tangency_intersects_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 10.0, 12.5, -1.0, 1.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void zero_radius_strictly_within_rectangle_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 0.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -1.0, 1.0, -1.0, 1.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void zero_radius_exact_point_rectangle_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 0.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 0.0, 0.0, 0.0, 0.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void zero_radius_disjoint_point_rectangle_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 0.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 1.0, 1.0, 0.0, 0.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void vertical_line_strictly_contained_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 0.0, 0.0, -5.0, 5.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void horizontal_line_strictly_contained_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, -5.0, 5.0, 0.0, 0.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void point_rectangle_at_center_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 0.0, 0.0, 0.0, 0.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void point_rectangle_diagonally_outside_circle_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0);
        Rectangle sourceRectangle = rectangle(sourceCircle, 8.0, 8.0, 8.0, 8.0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
