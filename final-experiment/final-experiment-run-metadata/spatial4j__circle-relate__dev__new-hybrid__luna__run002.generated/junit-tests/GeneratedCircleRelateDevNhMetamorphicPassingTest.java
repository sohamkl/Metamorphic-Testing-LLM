import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;
import org.locationtech.spatial4j.shape.SpatialRelation;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static void verifyShiftInvariant(
            CircleImpl source,
            RectangleImpl rectangle) {
        SpatialRelation sourceOutput = source.relate(rectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(source, rectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[1];

        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_SENTINEL_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(Double.NaN, Double.NaN, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(0.0, 1.0, 0.0, 1.0, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void EMPTY_RECTANGLE_SENTINEL_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle = new RectangleImpl(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void BBOX_DISJOINT_X_SEPARATION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(2.0, 3.0, -0.5, 0.5, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void BBOX_DISJOINT_Y_SEPARATION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(-0.5, 0.5, 2.0, 3.0, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void BBOX_WITHIN_LARGE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(-2.0, 2.0, -2.0, 2.0, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void BBOX_IDENTITY_EDGE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(-1.0, 1.0, -1.0, 1.0, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void CIRCLE_CONTAINS_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(-0.5, 0.5, -0.5, 0.5, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void RECTANGLE_INSIDE_BBOX_BUT_CORNER_OUTSIDE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(-0.9, 0.9, -0.9, 0.9, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void PHASE_TWO_CORNER_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(0.8, 1.2, 0.8, 1.2, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void PHASE_TWO_CORNER_INTERSECTION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(0.7, 2.0, 0.7, 2.0, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void X_AXIS_LEFT_OF_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(0.5, 2.0, -0.2, 0.2, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void X_AXIS_RIGHT_OF_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(-2.0, -0.5, -0.2, 0.2, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void Y_AXIS_BELOW_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(-0.2, 0.2, 0.5, 2.0, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void Y_AXIS_ABOVE_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(-0.2, 0.2, -2.0, -0.5, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void SIDE_TANGENCY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(1.0, 2.0, -0.5, 0.5, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void CORNER_TANGENCY_variation1() {
        SpatialContext context = new SpatialContext(false);
        double tangent = 1.0 / Math.sqrt(2.0);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(tangent, 2.0, tangent, 2.0, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void ZERO_RADIUS_POINT_LIKE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 0.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(-1.0, 1.0, -1.0, 1.0, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void ZERO_WIDTH_RECTANGLE_INSIDE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(0.0, 0.0, -0.5, 0.5, context);

        verifyShiftInvariant(circle, rectangle);
    }

    @Test
    public void ZERO_HEIGHT_RECTANGLE_INSIDE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(0.0, 0.0, context), 1.0, context);
        RectangleImpl rectangle =
                new RectangleImpl(-0.5, 0.5, 0.0, 0.0, context);

        verifyShiftInvariant(circle, rectangle);
    }
}
