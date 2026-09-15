import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static SpatialContext flatContext() {
        return new SpatialContext(false);
    }

    private static CircleImpl circle(
            SpatialContext context, double centerX, double centerY, double radius) {
        return new CircleImpl(context.makePoint(centerX, centerY), radius, context);
    }

    private static CircleImpl emptyCircle(SpatialContext context) {
        return new CircleImpl(
                context.makePoint(Double.NaN, Double.NaN),
                0.0,
                context);
    }

    private static Rectangle rectangle(
            SpatialContext context,
            double minX,
            double maxX,
            double minY,
            double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    private static Rectangle emptyRectangle(SpatialContext context) {
        return context.makeRectangle(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    }

    private static void exercise(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);

        Object[] followUpValues =
                CircleRelateDevMetamorphicSpec.generateFollowUp(
                        sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUpValues[0];
        Rectangle followUpRectangle = (Rectangle) followUpValues[1];

        SpatialRelation followUpOutput =
                followUpCircle.relate(followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_NONEMPTY_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                emptyCircle(context),
                rectangle(context, -8.0, -2.0, -3.0, 4.0));
    }

    @Test
    public void NONEMPTY_CIRCLE_EMPTY_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 1.5, -2.25, 2.0),
                emptyRectangle(context));
    }

    @Test
    public void BOTH_SHAPES_EMPTY_variation1() {
        SpatialContext context = flatContext();
        exercise(emptyCircle(context), emptyRectangle(context));
    }

    @Test
    public void BBOX_DISJOINT_RECTANGLE_LEFT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 10.0, 5.0, 3.0),
                rectangle(context, 1.0, 6.0, 3.0, 7.0));
    }

    @Test
    public void BBOX_DISJOINT_RECTANGLE_RIGHT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, -4.0, 2.0, 3.0),
                rectangle(context, 0.0, 4.0, 0.0, 5.0));
    }

    @Test
    public void BBOX_DISJOINT_RECTANGLE_ABOVE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 4.0),
                rectangle(context, -2.0, 2.0, 5.0, 9.0));
    }

    @Test
    public void BBOX_DISJOINT_RECTANGLE_BELOW_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 2.1, 3.2, 2.5),
                rectangle(context, 1.0, 3.0, -2.0, 0.5));
    }

    @Test
    public void CIRCLE_BBOX_STRICTLY_WITHIN_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -7.0, 8.0, -9.0, 6.0));
    }

    @Test
    public void CIRCLE_BBOX_WITHIN_WITH_SHARED_SIDE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 2.0, -1.0, 4.0),
                rectangle(context, -2.0, 8.0, -7.0, 5.0));
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BBOX_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 1.5, -2.5, 3.5),
                rectangle(context, -2.0, 5.0, -6.0, 1.0));
    }

    @Test
    public void EXTERNAL_RIGHT_SIDE_TANGENCY_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 5.0, 8.0, -2.0, 2.0));
    }

    @Test
    public void EXTERNAL_LEFT_SIDE_TANGENCY_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -8.0, -5.0, -3.0, 1.0));
    }

    @Test
    public void EXTERNAL_TOP_SIDE_TANGENCY_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -2.0, 3.0, 5.0, 8.0));
    }

    @Test
    public void EXTERNAL_BOTTOM_SIDE_TANGENCY_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -3.0, 2.0, -8.0, -5.0));
    }

    @Test
    public void EXTERNAL_CORNER_TANGENCY_3_4_5_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 3.0, 7.0, 4.0, 8.0));
    }

    @Test
    public void UPPER_RIGHT_CLOSEST_CORNER_DISJOINT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 4.0, 5.0, 4.0, 5.0));
    }

    @Test
    public void UPPER_LEFT_CLOSEST_CORNER_DISJOINT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -5.0, -4.0, 4.0, 5.0));
    }

    @Test
    public void LOWER_RIGHT_CLOSEST_CORNER_DISJOINT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 4.0, 5.0, -5.0, -4.0));
    }

    @Test
    public void LOWER_LEFT_CLOSEST_CORNER_DISJOINT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -5.0, -4.0, -5.0, -4.0));
    }

    @Test
    public void UPPER_RIGHT_NEAR_IN_FAR_OUT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 3.0, 4.0, 3.0, 4.0));
    }

    @Test
    public void UPPER_LEFT_NEAR_IN_FAR_OUT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -4.0, -3.0, 3.0, 4.0));
    }

    @Test
    public void LOWER_RIGHT_NEAR_IN_FAR_OUT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 3.0, 4.0, -4.0, -3.0));
    }

    @Test
    public void LOWER_LEFT_NEAR_IN_FAR_OUT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -4.0, -3.0, -4.0, -3.0));
    }

    @Test
    public void RIGHT_RECTANGLE_SPANS_Y_AXIS_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 4.0, 6.0, -1.0, 1.0));
    }

    @Test
    public void LEFT_RECTANGLE_SPANS_Y_AXIS_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -6.0, -4.0, -1.5, 1.5));
    }

    @Test
    public void TOP_RECTANGLE_SPANS_X_AXIS_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -1.0, 1.0, 4.0, 6.0));
    }

    @Test
    public void BOTTOM_RECTANGLE_SPANS_X_AXIS_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -1.5, 1.5, -6.0, -4.0));
    }

    @Test
    public void SMALL_CENTERED_RECTANGLE_CONTAINED_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 1000000.0, -1000000.0, 5.0),
                rectangle(
                        context,
                        999998.0,
                        1000002.0,
                        -1000002.0,
                        -999998.0));
    }

    @Test
    public void SMALL_OFFSET_RECTANGLE_CONTAINED_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 1.0, 2.0, 1.0, 2.0));
    }

    @Test
    public void INTERNAL_3_4_5_CORNER_CONTAINMENT_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 0.0, 3.0, 0.0, 4.0));
    }

    @Test
    public void SYMMETRIC_FARTHEST_TIE_ON_BOUNDARY_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -3.0, 3.0, -4.0, 4.0));
    }

    @Test
    public void HORIZONTAL_DIAMETER_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 12.0, -7.0, 6.0),
                rectangle(context, 6.0, 18.0, -7.0, -7.0));
    }

    @Test
    public void VERTICAL_DIAMETER_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, -3.0, 4.0, 5.0),
                rectangle(context, -3.0, -3.0, -1.0, 9.0));
    }

    @Test
    public void CENTER_POINT_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 2.5, -1.5, 4.0),
                rectangle(context, 2.5, 2.5, -1.5, -1.5));
    }

    @Test
    public void CIRCUMFERENCE_POINT_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 3.0, 3.0, 4.0, 4.0));
    }

    @Test
    public void POINT_INSIDE_BBOX_OUTSIDE_CIRCLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, 4.0, 4.0, 4.0, 4.0));
    }

    @Test
    public void LARGE_INSCRIBING_SQUARE_CORNERS_OUTSIDE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -4.0, 4.0, -4.0, 4.0));
    }

    @Test
    public void STRIP_EXTENDS_BEYOND_BBOX_BOTH_SIDES_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 5.0),
                rectangle(context, -7.0, 8.0, -1.0, 1.0));
    }

    @Test
    public void ZERO_RADIUS_STRICTLY_WITHIN_AREA_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 1.25, -2.5, 0.0),
                rectangle(context, 0.0, 3.0, -4.0, 0.0));
    }

    @Test
    public void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, -4.5, 7.25, 0.0),
                rectangle(context, -4.5, -4.5, 7.25, 7.25));
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_POINT_RECTANGLE_variation1() {
        SpatialContext context = flatContext();
        exercise(
                circle(context, 0.0, 0.0, 0.0),
                rectangle(context, 3.0, 3.0, -2.0, -2.0));
    }

    @Test
    public void NON_DYADIC_COORDINATES_WITH_STRICT_MARGIN_variation1() {
        SpatialContext context = flatContext();
        double centerX = 0.1;
        double centerY = 0.2;
        exercise(
                circle(context, centerX, centerY, 5.1),
                rectangle(
                        context,
                        centerX - 1.1,
                        centerX + 1.2,
                        centerY - 1.3,
                        centerY + 1.4));
    }

    @Test
    public void LARGE_MAGNITUDE_TRANSLATION_SAFE_WITHIN_variation1() {
        SpatialContext context = flatContext();
        double centerX = 1000000000.0;
        double centerY = -1000000000.0;
        exercise(
                circle(context, centerX, centerY, 100.0),
                rectangle(
                        context,
                        centerX - 200.0,
                        centerX + 200.0,
                        centerY - 200.0,
                        centerY + 200.0));
    }
}
