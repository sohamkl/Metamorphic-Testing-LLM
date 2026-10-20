import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);

    private static CircleImpl circle(double cx, double cy, double radius) {
        return new CircleImpl(CONTEXT.makePoint(cx, cy), radius, CONTEXT);
    }

    private static CircleImpl emptyCircle() {
        return new CircleImpl(
                CONTEXT.makePoint(Double.NaN, Double.NaN),
                5.0,
                CONTEXT);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static Rectangle emptyRectangle() {
        return new RectangleImpl(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN, CONTEXT);
    }

    private static void verify(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STRICT_RECTANGLE_CONTAINS_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(10.0, 20.0, 5.0);
        Rectangle sourceRectangle = rectangle(4.0, 16.0, 14.0, 26.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RECTANGLE_CONTAINS_CIRCLE_WITH_SIDE_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(-10.0, 15.0, 5.0);
        Rectangle sourceRectangle = rectangle(-15.0, -4.0, 9.0, 21.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RECTANGLE_CONTAINS_CIRCLE_WITH_MULTIPLE_TANGENCIES_variation1() {
        CircleImpl sourceCircle = circle(8.0, -12.0, 5.0);
        Rectangle sourceRectangle = rectangle(3.0, 14.0, -17.0, -6.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BOUNDING_BOX_variation1() {
        CircleImpl sourceCircle = circle(25.0, 30.0, 5.0);
        Rectangle sourceRectangle = rectangle(20.0, 30.0, 25.0, 35.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void HORIZONTAL_BOUNDING_BOX_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(6.0, 8.0, -1.0, 1.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void VERTICAL_BOUNDING_BOX_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(3.0, 4.0, 5.0);
        Rectangle sourceRectangle = rectangle(2.0, 4.0, 10.0, 12.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void DIAGONAL_BOUNDING_BOX_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(-20.0, -10.0, 5.0);
        Rectangle sourceRectangle = rectangle(-14.0, -12.0, -4.0, -2.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void NORTHEAST_CORNER_FALSE_POSITIVE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(4.0, 6.0, 4.0, 6.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void NORTHWEST_CORNER_FALSE_POSITIVE_variation1() {
        CircleImpl sourceCircle = circle(10.0, -5.0, 5.0);
        Rectangle sourceRectangle = rectangle(4.0, 6.0, -1.0, 1.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void SOUTHEAST_CORNER_FALSE_POSITIVE_variation1() {
        CircleImpl sourceCircle = circle(-5.0, 10.0, 5.0);
        Rectangle sourceRectangle = rectangle(-1.0, 1.0, 4.0, 6.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void SOUTHWEST_CORNER_FALSE_POSITIVE_variation1() {
        CircleImpl sourceCircle = circle(20.0, 20.0, 5.0);
        Rectangle sourceRectangle = rectangle(14.0, 16.0, 14.0, 16.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RIGHT_SIDE_EXTERNAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(5.0, 7.0, -1.0, 1.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void LEFT_SIDE_EXTERNAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(12.0, 8.0, 5.0);
        Rectangle sourceRectangle = rectangle(5.0, 7.0, 7.0, 9.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void TOP_SIDE_EXTERNAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(-8.0, 6.0, 5.0);
        Rectangle sourceRectangle = rectangle(-9.0, -7.0, 11.0, 13.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void BOTTOM_SIDE_EXTERNAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(15.0, -15.0, 5.0);
        Rectangle sourceRectangle = rectangle(14.0, 16.0, -22.0, -20.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RIGHT_SIDE_STRICT_PENETRATION_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(4.5, 7.0, -1.0, 1.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void THREE_FOUR_FIVE_CORNER_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(30.0, 10.0, 5.0);
        Rectangle sourceRectangle = rectangle(33.0, 36.0, 14.0, 16.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void CORNER_STRICT_PENETRATION_variation1() {
        CircleImpl sourceCircle = circle(-30.0, 10.0, 5.0);
        Rectangle sourceRectangle = rectangle(-27.0, -24.0, 13.75, 16.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void HORIZONTAL_SECANT_LINE_variation1() {
        CircleImpl sourceCircle = circle(5.0, 5.0, 5.0);
        Rectangle sourceRectangle = rectangle(-2.0, 12.0, 5.0, 5.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void VERTICAL_SECANT_LINE_variation1() {
        CircleImpl sourceCircle = circle(-5.0, -5.0, 5.0);
        Rectangle sourceRectangle = rectangle(-5.0, -5.0, -12.0, 2.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void TANGENT_HORIZONTAL_LINE_variation1() {
        CircleImpl sourceCircle = circle(10.0, 10.0, 5.0);
        Rectangle sourceRectangle = rectangle(8.0, 12.0, 15.0, 15.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void CENTERED_ASYMMETRIC_RECTANGLE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(-1.0, 3.0, -1.0, 2.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void CENTERED_EQUAL_SPAN_RECTANGLE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(18.0, -8.0, 5.0);
        Rectangle sourceRectangle = rectangle(16.0, 20.0, -10.0, -6.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void OFF_AXIS_RECTANGLE_INTERNAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(-12.0, -18.0, 5.0);
        Rectangle sourceRectangle = rectangle(-11.0, -9.0, -17.0, -14.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void SOUTHWEST_RECTANGLE_STRICTLY_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(7.0, 9.0, 5.0);
        Rectangle sourceRectangle = rectangle(4.0, 6.0, 6.0, 8.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_BUT_CIRCLE_DOES_NOT_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(-4.0, 4.0, -4.0, 4.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void QUADRANT_RECTANGLE_NEAR_CORNER_INSIDE_FAR_CORNER_OUTSIDE_variation1() {
        CircleImpl sourceCircle = circle(20.0, -20.0, 5.0);
        Rectangle sourceRectangle = rectangle(21.0, 24.0, -19.0, -16.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void X_SPAN_TIE_WITH_OUTSIDE_FARTHEST_CORNER_variation1() {
        CircleImpl sourceCircle = circle(-10.0, 25.0, 5.0);
        Rectangle sourceRectangle = rectangle(-14.75, -5.25, 23.0, 27.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void Y_SPAN_TIE_WITH_OUTSIDE_FARTHEST_CORNER_variation1() {
        CircleImpl sourceCircle = circle(25.0, -10.0, 5.0);
        Rectangle sourceRectangle = rectangle(23.0, 27.0, -14.75, -5.25);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void POINT_RECTANGLE_STRICTLY_INSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(1.0, 1.0, 1.0, 1.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void POINT_RECTANGLE_ON_CIRCLE_BOUNDARY_variation1() {
        CircleImpl sourceCircle = circle(13.0, 17.0, 5.0);
        Rectangle sourceRectangle = rectangle(16.0, 16.0, 21.0, 21.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void POINT_RECTANGLE_IN_BBOX_BUT_OUTSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(-13.0, 17.0, 5.0);
        Rectangle sourceRectangle = rectangle(-9.0, -9.0, 21.0, 21.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void HORIZONTAL_LINE_STRICTLY_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0);
        Rectangle sourceRectangle = rectangle(-3.0, 3.0, 1.0, 1.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void RADIAL_LINE_ENDPOINT_INTERNAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(11.0, -9.0, 5.0);
        Rectangle sourceRectangle = rectangle(11.0, 16.0, -9.0, -9.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void ZERO_RADIUS_POINT_WITHIN_AREA_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0.0, 0.0, 0.0);
        Rectangle sourceRectangle = rectangle(-1.0, 1.0, -1.0, 1.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void ZERO_RADIUS_EXACT_POINT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(22.0, 14.0, 0.0);
        Rectangle sourceRectangle = rectangle(22.0, 22.0, 14.0, 14.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_POINT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(-22.0, 14.0, 0.0);
        Rectangle sourceRectangle = rectangle(-21.0, -21.0, 14.0, 14.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void ZERO_RADIUS_POINT_WITHIN_LINE_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(4.0, -6.0, 0.0);
        Rectangle sourceRectangle = rectangle(2.0, 6.0, -6.0, -6.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void EMPTY_CIRCLE_NONEMPTY_RECTANGLE_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = rectangle(-2.0, 3.0, -4.0, 6.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void NONEMPTY_CIRCLE_EMPTY_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(9.0, 12.0, 5.0);
        Rectangle sourceRectangle = emptyRectangle();
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void BOTH_SHAPES_EMPTY_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = emptyRectangle();
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void SIGNED_ZERO_CENTER_AND_DEGENERATE_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(-0.0, +0.0, 5.0);
        Rectangle sourceRectangle = rectangle(+0.0, +0.0, -0.0, -0.0);
        verify(sourceCircle, sourceRectangle);
    }

    @Test
    public void VERY_SMALL_POSITIVE_CIRCLE_WITHIN_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(32.0, -24.0, 0.125);
        Rectangle sourceRectangle =
                rectangle(31.75, 32.25, -24.25, -23.75);
        verify(sourceCircle, sourceRectangle);
    }
}
