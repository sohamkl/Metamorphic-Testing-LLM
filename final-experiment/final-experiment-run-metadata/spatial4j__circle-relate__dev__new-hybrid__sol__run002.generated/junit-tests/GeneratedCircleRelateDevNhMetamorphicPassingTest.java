import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);

    private static CircleImpl circle(double x, double y, double radius) {
        return new CircleImpl(CONTEXT.makePoint(x, y), radius, CONTEXT);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static void verify(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) sourceCircle)
                        .relate(sourceRectangle);

        Object[] followUp =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];

        SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.CircleImpl) followUpCircle)
                        .relate(followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_SENTINEL_variation1() {
        verify(circle(Double.NaN, Double.NaN, 10.0), rectangle(1.0, 3.0, 2.0, 4.0));
    }

    @Test
    public void EMPTY_RECTANGLE_SENTINEL_variation1() {
        verify(circle(0.0, 0.0, 10.0),
                rectangle(Double.NaN, Double.NaN, Double.NaN, Double.NaN));
    }

    @Test
    public void BBOX_DISJOINT_LEFT_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-16.0, -11.0, -2.0, 2.0));
    }

    @Test
    public void BBOX_DISJOINT_RIGHT_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(11.0, 16.0, -2.0, 2.0));
    }

    @Test
    public void BBOX_DISJOINT_ABOVE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-2.0, 2.0, 11.0, 16.0));
    }

    @Test
    public void BBOX_DISJOINT_BELOW_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-2.0, 2.0, -16.0, -11.0));
    }

    @Test
    public void STRICTLY_WITHIN_RECTANGLE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-12.0, 13.0, -14.0, 15.0));
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BBOX_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-10.0, 10.0, -10.0, 10.0));
    }

    @Test
    public void INTERNALLY_TANGENT_X_SIDE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-10.0, 13.0, -12.0, 12.0));
    }

    @Test
    public void INTERNALLY_TANGENT_Y_SIDE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-12.0, 12.0, -10.0, 13.0));
    }

    @Test
    public void PROPER_BBOX_CORNER_DISJOINT_NE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(8.0, 10.0, 8.0, 10.0));
    }

    @Test
    public void PROPER_BBOX_CORNER_DISJOINT_NW_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-10.0, -8.0, 8.0, 10.0));
    }

    @Test
    public void PROPER_BBOX_CORNER_DISJOINT_SE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(8.0, 10.0, -10.0, -8.0));
    }

    @Test
    public void PROPER_BBOX_CORNER_DISJOINT_SW_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-10.0, -8.0, -10.0, -8.0));
    }

    @Test
    public void INTERSECTING_BBOX_BUT_CIRCLE_DISJOINT_NE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(8.0, 12.0, 8.0, 12.0));
    }

    @Test
    public void INTERSECTING_BBOX_BUT_CIRCLE_DISJOINT_SW_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-12.0, -8.0, -12.0, -8.0));
    }

    @Test
    public void EXTERNAL_RIGHT_SIDE_TANGENCY_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(10.0, 14.0, -3.0, 3.0));
    }

    @Test
    public void EXTERNAL_LEFT_SIDE_TANGENCY_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-14.0, -10.0, -3.0, 3.0));
    }

    @Test
    public void EXTERNAL_TOP_SIDE_TANGENCY_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-3.0, 3.0, 10.0, 14.0));
    }

    @Test
    public void EXTERNAL_BOTTOM_SIDE_TANGENCY_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-3.0, 3.0, -14.0, -10.0));
    }

    @Test
    public void NORTHEAST_CORNER_TANGENCY_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(6.0, 8.0, 8.0, 9.0));
    }

    @Test
    public void SOUTHWEST_CORNER_TANGENCY_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-8.0, -6.0, -9.0, -8.0));
    }

    @Test
    public void NORTHEAST_CORNER_PENETRATION_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(5.0, 9.0, 5.0, 9.0));
    }

    @Test
    public void NORTHWEST_CORNER_PENETRATION_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-9.0, -5.0, 5.0, 9.0));
    }

    @Test
    public void RIGHT_SIDE_POSITIVE_OVERLAP_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(5.0, 15.0, -2.0, 2.0));
    }

    @Test
    public void TOP_SIDE_POSITIVE_OVERLAP_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-2.0, 2.0, 5.0, 15.0));
    }

    @Test
    public void WIDE_AXIS_CROSSING_RECTANGLE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-15.0, 15.0, -1.0, 1.0));
    }

    @Test
    public void TALL_AXIS_CROSSING_RECTANGLE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-1.0, 1.0, -15.0, 15.0));
    }

    @Test
    public void CENTERED_RECTANGLE_CONTAINED_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-2.5, 2.5, -2.5, 2.5));
    }

    @Test
    public void OFF_CENTER_RECTANGLE_CONTAINED_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(2.0, 6.0, 2.0, 6.0));
    }

    @Test
    public void FARTHEST_CORNER_ON_CIRCLE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(0.0, 6.0, 0.0, 8.0));
    }

    @Test
    public void CENTER_ON_RECTANGLE_MIN_X_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(0.0, 4.0, -3.0, 3.0));
    }

    @Test
    public void LARGE_INSCRIBED_BBOX_RECTANGLE_INTERSECTS_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-9.0, 9.0, -9.0, 9.0));
    }

    @Test
    public void UPPER_STRIP_WITH_OUTSIDE_CORNERS_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-9.0, 9.0, 7.0, 9.0));
    }

    @Test
    public void RIGHT_STRIP_WITH_OUTSIDE_CORNERS_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(7.0, 9.0, -9.0, 9.0));
    }

    @Test
    public void FARTHEST_X_DISTANCE_TIE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-6.0, 6.0, 1.0, 7.0));
    }

    @Test
    public void FARTHEST_Y_DISTANCE_TIE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(1.0, 7.0, -6.0, 6.0));
    }

    @Test
    public void POINT_RECTANGLE_AT_CENTER_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(0.0, 0.0, 0.0, 0.0));
    }

    @Test
    public void POINT_RECTANGLE_ON_CIRCUMFERENCE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(10.0, 10.0, 0.0, 0.0));
    }

    @Test
    public void POINT_RECTANGLE_INSIDE_BBOX_OUTSIDE_CIRCLE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(9.0, 9.0, 9.0, 9.0));
    }

    @Test
    public void DIAMETER_LINE_RECTANGLE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-10.0, 10.0, 0.0, 0.0));
    }

    @Test
    public void INTERNAL_CHORD_LINE_RECTANGLE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-5.0, 5.0, 5.0, 5.0));
    }

    @Test
    public void TANGENT_LINE_RECTANGLE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-5.0, 5.0, 10.0, 10.0));
    }

    @Test
    public void CROSSING_LINE_RECTANGLE_variation1() {
        verify(circle(0.0, 0.0, 10.0), rectangle(-15.0, 15.0, 0.0, 0.0));
    }

    @Test
    public void ZERO_RADIUS_INSIDE_RECTANGLE_variation1() {
        verify(circle(0.0, 0.0, 0.0), rectangle(-2.0, 3.0, -4.0, 5.0));
    }

    @Test
    public void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation1() {
        verify(circle(0.0, 0.0, 0.0), rectangle(0.0, 0.0, 0.0, 0.0));
    }

    @Test
    public void ZERO_RADIUS_OUTSIDE_RECTANGLE_variation1() {
        verify(circle(0.0, 0.0, 0.0), rectangle(2.0, 4.0, 2.0, 4.0));
    }

    @Test
    public void ZERO_RADIUS_ON_RECTANGLE_BOUNDARY_variation1() {
        verify(circle(0.0, 0.0, 0.0), rectangle(0.0, 5.0, -2.0, 2.0));
    }
}
