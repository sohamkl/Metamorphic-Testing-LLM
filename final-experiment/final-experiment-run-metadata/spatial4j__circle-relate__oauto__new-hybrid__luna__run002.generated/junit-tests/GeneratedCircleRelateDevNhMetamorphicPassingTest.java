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

        return new Object[]{shiftedCircle, shiftedRectangle};
    }

    private static void verifyTranslated(
            CircleImpl source,
            Rectangle rectangle) {
        SpatialRelation sourceOutput = source.relate(rectangle);

        Object[] followUp = generateFollowUp(source, rectangle);
        CircleImpl shiftedCircle = (CircleImpl) followUp[0];
        Rectangle shiftedRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                shiftedCircle.relate(shiftedRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput,
                followUpOutput);
    }

    private static CircleImpl circle(
            SpatialContext context,
            double x,
            double y,
            double radius) {
        return new CircleImpl(context.makePoint(x, y), radius, context);
    }

    private static Rectangle rectangle(
            SpatialContext context,
            double minX,
            double maxX,
            double minY,
            double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    @Test
    public void EMPTY_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        Point empty = new PointImpl(Double.NaN, Double.NaN, context);
        CircleImpl source = new CircleImpl(empty, 3.0, context);
        Rectangle rectangle = rectangle(context, 6.0, 7.0, -1.0, 1.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void BBOX_DISJOINT_HORIZONTAL_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, 6.0, 7.0, -1.0, 1.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void BBOX_DISJOINT_VERTICAL_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -1.0, 1.0, 6.0, 7.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void BBOX_WITHIN_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -6.0, 6.0, -6.0, 6.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void EQUAL_ENCLOSING_BOX_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -5.0, 5.0, -5.0, 5.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_AND_CIRCLE_CONTAINS_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -1.0, 1.0, -1.0, 1.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_BUT_CORNER_OUTSIDE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -4.0, 4.0, -4.0, 4.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void AXIS_LEFT_OVERLAPPING_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, 4.0, 6.0, -1.0, 1.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void AXIS_RIGHT_OVERLAPPING_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -6.0, -4.0, -1.0, 1.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void AXIS_BELOW_OVERLAPPING_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -1.0, 1.0, 4.0, 6.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void AXIS_ABOVE_OVERLAPPING_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -1.0, 1.0, -6.0, -4.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void BOTH_AXES_OUTSIDE_DISJOINT_CORNER_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, 4.0, 6.0, 4.0, 6.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void BOTH_AXES_OUTSIDE_CORNER_INTERSECTION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, 3.0, 6.0, 3.0, 6.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void CLOSEST_CORNER_TANGENCY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, 3.0, 4.0, 4.0, 5.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void FAR_CORNER_STRICTLY_INSIDE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -3.0, 3.0, -3.0, 3.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void FAR_CORNER_TANGENCY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -3.0, 4.0, -3.0, 4.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void FARTHEST_CORNER_OUTSIDE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -3.0, 4.1, -3.0, 4.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void FARTHEST_AXIS_TIE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -4.0, 4.0, -1.0, 1.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void ZERO_RADIUS_POINT_WITHIN_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 0.0);
        Rectangle rectangle = rectangle(context, -1.0, 1.0, -1.0, 1.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void ZERO_RADIUS_EQUAL_DEGENERATE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 0.0);
        Rectangle rectangle = rectangle(context, 0.0, 0.0, 0.0, 0.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void DEGENERATE_VERTICAL_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, 3.0, 3.0, -1.0, 1.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void DEGENERATE_HORIZONTAL_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 0.0, 0.0, 5.0);
        Rectangle rectangle = rectangle(context, -1.0, 1.0, 3.0, 3.0);

        verifyTranslated(source, rectangle);
    }

    @Test
    public void TRANSLATED_FINITE_EXTREME_COORDINATES_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl source = circle(context, 100000.0, -100000.0, 5.0);
        Rectangle rectangle = rectangle(
                context,
                100004.0,
                100006.0,
                -100001.0,
                -99999.0);

        verifyTranslated(source, rectangle);
    }
}
