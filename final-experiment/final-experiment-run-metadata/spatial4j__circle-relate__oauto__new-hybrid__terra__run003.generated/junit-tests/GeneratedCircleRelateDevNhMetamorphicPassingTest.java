import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        SpatialContext context = circle.getContext();
        Point center = circle.getCenter();

        CircleImpl shiftedCircle = new CircleImpl(
                context.makePoint(center.getX() + 17.5, center.getY() - 9.25),
                circle.getRadius(),
                context);

        Rectangle shiftedRectangle = new RectangleImpl(
                rectangle.getMinX() + 17.5,
                rectangle.getMaxX() + 17.5,
                rectangle.getMinY() - 9.25,
                rectangle.getMaxY() - 9.25,
                context);

        return new Object[] { shiftedCircle, shiftedRectangle };
    }

    @Test
    public void BBOX_DISJOINT_TO_EAST_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(5.0, 7.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_WITHIN_LARGE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(-6.0, 6.0, -6.0, 6.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENCLOSING_BOX_EQUAL_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(-4.0, 4.0, -4.0, 4.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OVERLAPPING_BBOXES_BUT_CORNER_DISJOINT_NORTHEAST_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(3.0, 4.0, 3.0, 4.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CORNER_TANGENCY_SOUTHWEST_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(-4.0, -2.4, -3.2, -2.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EAST_RECTANGLE_SPANS_HORIZONTAL_AXIS_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(3.0, 5.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void WEST_RECTANGLE_SPANS_HORIZONTAL_AXIS_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(-5.0, -3.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTH_RECTANGLE_SPANS_VERTICAL_AXIS_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(-1.0, 1.0, 3.0, 5.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTH_RECTANGLE_SPANS_VERTICAL_AXIS_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(-1.0, 1.0, -5.0, -3.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STRICT_RECTANGLE_CONTAINMENT_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(-1.0, 1.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_BUT_CIRCLE_INTERSECTS_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(-3.0, 3.0, -3.0, 3.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FARTHEST_AXIS_TIE_INTERSECTS_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(-4.0, 4.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_FACE_TOUCH_EAST_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(4.0, 6.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_POINT_WITHIN_LARGE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 0.0, context);
        Rectangle rectangle = new RectangleImpl(-1.0, 1.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 0.0, context);
        Rectangle rectangle = new RectangleImpl(0.0, 0.0, 0.0, 0.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LINE_RECTANGLE_STRICTLY_CONTAINED_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 4.0, context);
        Rectangle rectangle = new RectangleImpl(-1.0, 1.0, 0.0, 0.0, context);

        SpatialRelation sourceOutput = circle.relate(rectangle);
        Object[] followUp = generateFollowUp(circle, rectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
