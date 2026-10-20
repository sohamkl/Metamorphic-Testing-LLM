import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicTest {

    private static final double SHIFT_X = 17.5;
    private static final double SHIFT_Y = -9.25;

    private Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        SpatialContext context = circle.getContext();
        Point center = circle.getCenter();

        CircleImpl shiftedCircle = new CircleImpl(
                context.makePoint(center.getX() + SHIFT_X, center.getY() + SHIFT_Y),
                circle.getRadius(),
                context);

        Rectangle shiftedRectangle = new RectangleImpl(
                rectangle.getMinX() + SHIFT_X,
                rectangle.getMaxX() + SHIFT_X,
                rectangle.getMinY() + SHIFT_Y,
                rectangle.getMaxY() + SHIFT_Y,
                context);

        return new Object[]{shiftedCircle, shiftedRectangle};
    }

    private void run(double cx, double cy, double radius,
                     double minX, double maxX, double minY, double maxY) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(
                new PointImpl(cx, cy, context), radius, context);
        Rectangle sourceRectangle = new RectangleImpl(
                minX, maxX, minY, maxY, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private void runEmptyCircle(double radius,
                                double minX, double maxX, double minY, double maxY) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(
                new PointImpl(Double.NaN, Double.NaN, context), radius, context);
        Rectangle sourceRectangle = new RectangleImpl(
                minX, maxX, minY, maxY, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    private void runEmptyRectangle(double cx, double cy, double radius) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(
                new PointImpl(cx, cy, context), radius, context);
        Rectangle sourceRectangle = new RectangleImpl(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_WITH_FINITE_RECTANGLE_variation1() {
        runEmptyCircle(4.0, -4.0, 3.0, -2.0, 5.0);
    }

    @Test
    public void EMPTY_CIRCLE_WITH_FINITE_RECTANGLE_variation2() {
        runEmptyCircle(0.0, 6.0, 6.0, -3.0, -3.0);
    }

    @Test
    public void FINITE_CIRCLE_WITH_EMPTY_RECTANGLE_variation1() {
        runEmptyRectangle(0.0, 0.0, 5.0);
    }

    @Test
    public void FINITE_CIRCLE_WITH_EMPTY_RECTANGLE_variation2() {
        runEmptyRectangle(40.0, 30.0, 3.0);
    }

    @Test
    public void BBOX_DISJOINT_LEFT_variation1() {
        run(0.0, 0.0, 5.0, -12.0, -8.0, -2.0, 2.0);
    }

    @Test
    public void BBOX_DISJOINT_LEFT_variation2() {
        run(30.0, -10.0, 4.0, 15.0, 24.0, -14.0, -6.0);
    }

    @Test
    public void BBOX_DISJOINT_RIGHT_variation1() {
        run(0.0, 0.0, 5.0, 8.0, 12.0, -2.0, 2.0);
    }

    @Test
    public void BBOX_DISJOINT_RIGHT_variation2() {
        run(-20.0, 15.0, 2.0, -15.0, -10.0, 10.0, 20.0);
    }

    @Test
    public void BBOX_DISJOINT_BELOW_variation1() {
        run(0.0, 0.0, 5.0, -2.0, 2.0, -12.0, -8.0);
    }

    @Test
    public void BBOX_DISJOINT_BELOW_variation2() {
        run(20.0, 20.0, 3.0, 16.0, 24.0, 10.0, 14.0);
    }

    @Test
    public void BBOX_DISJOINT_ABOVE_variation1() {
        run(0.0, 0.0, 5.0, -2.0, 2.0, 8.0, 12.0);
    }

    @Test
    public void BBOX_DISJOINT_ABOVE_variation2() {
        run(-25.0, -10.0, 3.0, -30.0, -20.0, -4.0, 3.0);
    }

    @Test
    public void CIRCLE_BBOX_STRICTLY_WITHIN_RECTANGLE_variation1() {
        run(0.0, 0.0, 5.0, -8.0, 8.0, -9.0, 9.0);
    }

    @Test
    public void CIRCLE_BBOX_STRICTLY_WITHIN_RECTANGLE_variation2() {
        run(30.0, -20.0, 2.0, 25.0, 35.0, -25.0, -15.0);
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BBOX_variation1() {
        run(0.0, 0.0, 5.0, -5.0, 5.0, -5.0, 5.0);
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BBOX_variation2() {
        run(14.0, -7.0, 3.0, 11.0, 17.0, -10.0, -4.0);
    }

    @Test
    public void PHASE2_X_OUTSIDE_Y_SPANNING_variation1() {
        run(0.0, 0.0, 5.0, 2.0, 4.0, -6.0, 2.0);
    }

    @Test
    public void PHASE2_X_OUTSIDE_Y_SPANNING_variation2() {
        run(20.0, 10.0, 6.0, 14.0, 17.0, 3.0, 13.0);
    }

    @Test
    public void PHASE2_Y_OUTSIDE_X_SPANNING_variation1() {
        run(0.0, 0.0, 5.0, -6.0, 2.0, 2.0, 4.0);
    }

    @Test
    public void PHASE2_Y_OUTSIDE_X_SPANNING_variation2() {
        run(-10.0, 20.0, 4.0, -14.0, -7.0, 15.0, 18.0);
    }

    @Test
    public void CORNER_BBOX_OVERLAP_BUT_CIRCLE_DISJOINT_variation1() {
        run(0.0, 0.0, 5.0, 4.0, 5.0, 4.0, 5.0);
    }

    @Test
    public void CORNER_BBOX_OVERLAP_BUT_CIRCLE_DISJOINT_variation2() {
        run(10.0, -10.0, 4.0, 13.0, 14.0, -7.0, -6.0);
    }

    @Test
    public void CORNER_TANGENCY_variation1() {
        run(0.0, 0.0, 5.0, 3.0, 4.0, 4.0, 5.0);
    }

    @Test
    public void CORNER_TANGENCY_variation2() {
        run(20.0, 10.0, 10.0, 26.0, 28.0, 18.0, 19.0);
    }

    @Test
    public void CORNER_OVERLAP_variation1() {
        run(0.0, 0.0, 6.0, 3.0, 5.0, 3.0, 5.0);
    }

    @Test
    public void CORNER_OVERLAP_variation2() {
        run(-20.0, 10.0, 5.0, -23.0, -21.0, 12.0, 14.0);
    }

    @Test
    public void RECTANGLE_STRICTLY_CONTAINED_BY_CIRCLE_variation1() {
        run(0.0, 0.0, 5.0, -2.0, 2.0, -2.0, 2.0);
    }

    @Test
    public void RECTANGLE_STRICTLY_CONTAINED_BY_CIRCLE_variation2() {
        run(30.0, 30.0, 8.0, 27.0, 32.0, 28.0, 33.0);
    }

    @Test
    public void RECTANGLE_FARTHEST_CORNER_TANGENT_variation1() {
        run(0.0, 0.0, 5.0, 0.0, 3.0, 0.0, 4.0);
    }

    @Test
    public void RECTANGLE_FARTHEST_CORNER_TANGENT_variation2() {
        run(10.0, -10.0, 10.0, 10.0, 16.0, -10.0, -2.0);
    }

    @Test
    public void RECTANGLE_INSIDE_BBOX_BUT_NOT_CIRCLE_variation1() {
        run(0.0, 0.0, 5.0, -4.0, 4.0, -4.0, 4.0);
    }

    @Test
    public void RECTANGLE_INSIDE_BBOX_BUT_NOT_CIRCLE_variation2() {
        run(-15.0, -15.0, 6.0, -20.0, -11.0, -20.0, -11.0);
    }

    @Test
    public void CENTER_INSIDE_RECTANGLE_CORNERS_OUTSIDE_CIRCLE_variation1() {
        run(0.0, 0.0, 5.0, -4.0, 4.0, -4.0, 4.0);
    }

    @Test
    public void CENTER_INSIDE_RECTANGLE_CORNERS_OUTSIDE_CIRCLE_variation2() {
        run(50.0, 20.0, 8.0, 43.0, 57.0, 13.0, 27.0);
    }

    @Test
    public void POSITIVE_RADIUS_POINT_AT_CENTER_variation1() {
        run(0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Test
    public void POSITIVE_RADIUS_POINT_AT_CENTER_variation2() {
        run(-12.0, 18.0, 2.0, -12.0, -12.0, 18.0, 18.0);
    }

    @Test
    public void POSITIVE_RADIUS_POINT_ON_BOUNDARY_variation1() {
        run(0.0, 0.0, 5.0, 5.0, 5.0, 0.0, 0.0);
    }

    @Test
    public void POSITIVE_RADIUS_POINT_ON_BOUNDARY_variation2() {
        run(20.0, -5.0, 3.0, 20.0, 20.0, -2.0, -2.0);
    }

    @Test
    public void POSITIVE_RADIUS_POINT_IN_BBOX_CORNER_OUTSIDE_CIRCLE_variation1() {
        run(0.0, 0.0, 5.0, 4.0, 4.0, 4.0, 4.0);
    }

    @Test
    public void POSITIVE_RADIUS_POINT_IN_BBOX_CORNER_OUTSIDE_CIRCLE_variation2() {
        run(25.0, 25.0, 6.0, 30.0, 30.0, 30.0, 30.0);
    }

    @Test
    public void VERTICAL_LINE_STRICTLY_INSIDE_CIRCLE_variation1() {
        run(0.0, 0.0, 5.0, 0.0, 0.0, -3.0, 3.0);
    }

    @Test
    public void VERTICAL_LINE_STRICTLY_INSIDE_CIRCLE_variation2() {
        run(-20.0, 20.0, 6.0, -22.0, -22.0, 17.0, 23.0);
    }

    @Test
    public void VERTICAL_LINE_CROSSES_CIRCLE_variation1() {
        run(0.0, 0.0, 5.0, 0.0, 0.0, -6.0, 6.0);
    }

    @Test
    public void VERTICAL_LINE_CROSSES_CIRCLE_variation2() {
        run(30.0, -20.0, 4.0, 30.0, 30.0, -26.0, -14.0);
    }

    @Test
    public void HORIZONTAL_LINE_STRICTLY_INSIDE_CIRCLE_variation1() {
        run(0.0, 0.0, 5.0, -3.0, 3.0, 0.0, 0.0);
    }

    @Test
    public void HORIZONTAL_LINE_STRICTLY_INSIDE_CIRCLE_variation2() {
        run(12.0, 16.0, 7.0, 7.0, 17.0, 18.0, 18.0);
    }

    @Test
    public void ZERO_RADIUS_WITH_STRICTLY_CONTAINING_RECTANGLE_variation1() {
        run(0.0, 0.0, 0.0, -1.0, 1.0, -1.0, 1.0);
    }

    @Test
    public void ZERO_RADIUS_WITH_STRICTLY_CONTAINING_RECTANGLE_variation2() {
        run(33.0, -14.0, 0.0, 30.0, 36.0, -17.0, -11.0);
    }

    @Test
    public void ZERO_RADIUS_WITH_EQUAL_POINT_RECTANGLE_variation1() {
        run(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Test
    public void ZERO_RADIUS_WITH_EQUAL_POINT_RECTANGLE_variation2() {
        run(-8.0, 11.0, 0.0, -8.0, -8.0, 11.0, 11.0);
    }
}
