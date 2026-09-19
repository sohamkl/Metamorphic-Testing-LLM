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

    private static Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        SpatialContext context = circle.getContext();
        Point center = circle.getCenter();

        Point shiftedCenter = context.makePoint(
                center.getX() + SHIFT_X,
                center.getY() + SHIFT_Y);

        CircleImpl shiftedCircle = new CircleImpl(
                shiftedCenter,
                circle.getRadius(),
                context);

        RectangleImpl shiftedRectangle = new RectangleImpl(
                rectangle.getMinX() + SHIFT_X,
                rectangle.getMaxX() + SHIFT_X,
                rectangle.getMinY() + SHIFT_Y,
                rectangle.getMaxY() + SHIFT_Y,
                context);

        return new Object[] { shiftedCircle, shiftedRectangle };
    }

    @Test
    public void scenario_BBOX_DISJOINT_HORIZONTAL_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                6.0, 8.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_BBOX_WITHIN_LARGE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                -10.0, 10.0, -10.0, 10.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_BBOX_IDENTITY_EDGE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                -5.0, 5.0, -5.0, 5.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_RECTANGLE_STRICTLY_CONTAINED_BY_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                -3.0, 3.0, -3.0, 3.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_BBOX_CONTAINS_RECTANGLE_CORNER_OUTSIDE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                -4.0, 4.0, -4.0, 4.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_PHASE2_DIAGONAL_GAP_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                4.0, 6.0, 4.0, 6.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_PHASE2_X_AXIS_OUTSIDE_TANGENT_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                5.0, 7.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_PHASE2_Y_AXIS_OUTSIDE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                -1.0, 1.0, 4.0, 6.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_PHASE2_BOTH_AXES_INSIDE_INTERSECTION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        double halfWidth = 4.0;
        Rectangle rectangle = new RectangleImpl(
                -halfWidth, halfWidth, -halfWidth, halfWidth, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_FARTHEST_CORNER_STRICTLY_INSIDE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                -2.0, 2.0, -2.0, 2.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_FARTHEST_POINT_ON_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                -5.0, 0.0, 0.0, 0.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_ZERO_RADIUS_INSIDE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 0.0, context);
        Rectangle rectangle = new RectangleImpl(
                -2.0, 2.0, -2.0, 2.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_ZERO_RADIUS_OUTSIDE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 0.0, context);
        Rectangle rectangle = new RectangleImpl(
                2.0, 3.0, 2.0, 3.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_ZERO_RADIUS_ON_RECTANGLE_BOUNDARY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 0.0, context);
        Rectangle rectangle = new RectangleImpl(
                0.0, 2.0, 0.0, 2.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_POINT_RECTANGLE_INSIDE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                3.0, 3.0, 4.0, 4.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_POINT_RECTANGLE_OUTSIDE_CIRCLE_WITHIN_BBOX_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 5.0, context);
        Rectangle rectangle = new RectangleImpl(
                4.0, 4.0, 4.0, 4.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void scenario_FARTHEST_AXIS_TIE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 6.0, context);
        Rectangle rectangle = new RectangleImpl(
                -5.0, 5.0, -5.0, 5.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
