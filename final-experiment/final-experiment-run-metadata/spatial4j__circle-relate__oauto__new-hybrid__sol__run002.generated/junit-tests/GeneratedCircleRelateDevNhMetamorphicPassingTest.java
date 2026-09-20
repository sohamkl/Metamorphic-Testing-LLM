import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);
    private static final double SHIFT_X = 17.5;
    private static final double SHIFT_Y = -9.25;

    private static CircleImpl circle(double x, double y, double radius) {
        return new CircleImpl(CONTEXT.makePoint(x, y), radius, CONTEXT);
    }

    private static CircleImpl emptyCircle() {
        Point emptyPoint = new PointImpl(Double.NaN, Double.NaN, CONTEXT);
        return new CircleImpl(emptyPoint, 0.0, CONTEXT);
    }

    private static Rectangle rectangle(double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static Rectangle emptyRectangle() {
        return new RectangleImpl(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN, CONTEXT);
    }

    private static Object[] generateFollowUp(CircleImpl circle, Rectangle rectangle) {
        SpatialContext context = circle.getContext();
        Point center = circle.getCenter();
        Point shiftedCenter = context.makePoint(
                center.getX() + SHIFT_X,
                center.getY() + SHIFT_Y);

        CircleImpl shiftedCircle =
                new CircleImpl(shiftedCenter, circle.getRadius(), context);
        Rectangle shiftedRectangle = new RectangleImpl(
                rectangle.getMinX() + SHIFT_X,
                rectangle.getMaxX() + SHIFT_X,
                rectangle.getMinY() + SHIFT_Y,
                rectangle.getMaxY() + SHIFT_Y,
                context);

        return new Object[]{shiftedCircle, shiftedRectangle};
    }

    @Test
    public void BBOX_DISJOINT_RIGHT_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(6, 8, -2, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_DISJOINT_LEFT_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-8, -6, -2, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_DISJOINT_ABOVE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-2, 2, 6, 8);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_DISJOINT_BELOW_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-2, 2, -8, -6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CIRCLE_WITHIN_ROOMY_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-6, 7, -8, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_ENCLOSING_BOX_IDENTITY_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-5, 5, -5, 5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CIRCLE_WITHIN_RECTANGLE_WITH_SHARED_SIDE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-5, 7, -6, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTHEAST_CORNER_GAP_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(4, 7, 4, 8);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTHWEST_CORNER_GAP_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-7, -4, 4, 8);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTHEAST_CORNER_GAP_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(4, 7, -8, -4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTHWEST_CORNER_GAP_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-7, -4, -8, -4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTHEAST_CORNER_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(3, 7, 4, 8);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTHWEST_CORNER_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-7, -3, 4, 8);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTHEAST_CORNER_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(3, 7, -8, -4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTHWEST_CORNER_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-7, -3, -8, -4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RIGHT_SIDE_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(5, 7, -2, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LEFT_SIDE_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-7, -5, -2, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TOP_SIDE_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-2, 2, 5, 7);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOTTOM_SIDE_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-2, 2, -7, -5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RIGHT_SIDE_AREA_OVERLAP_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(4, 7, -2, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TOP_SIDE_AREA_OVERLAP_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-2, 2, 4, 7);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CENTERED_RECTANGLE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-2, 2, -2, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void OFF_CENTER_RECTANGLE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-1, 3, -2, 3);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTHEAST_RECTANGLE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(1, 3, 1, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTHWEST_RECTANGLE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-3, -1, -4, -1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FARTHEST_CORNER_ON_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(0, 3, 0, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EQUAL_DISTANCE_FARTHEST_TIE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-3, 3, -4, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_BUT_CIRCLE_DOES_NOT_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-1, 4, -1, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_POINT_INSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(1, 1, 2, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_POINT_ON_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(3, 3, 4, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_POINT_OUTSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(6, 6, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIAMETER_LINE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-5, 5, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LINE_CROSSES_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-7, 7, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_INSIDE_AREA_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(1, 2, 0);
        Rectangle sourceRectangle = rectangle(0, 3, 0, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_EXACT_POINT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(1, 2, 0);
        Rectangle sourceRectangle = rectangle(1, 1, 2, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_OUTSIDE_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 0);
        Rectangle sourceRectangle = rectangle(1, 3, 1, 3);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_CIRCLE_NORMAL_RECTANGLE_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = rectangle(-2, 2, -2, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORMAL_CIRCLE_EMPTY_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = emptyRectangle();
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOTH_SHAPES_EMPTY_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = emptyRectangle();
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput =
                ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
