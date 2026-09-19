import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static SpatialContext context() {
        return new SpatialContext(false);
    }

    private static CircleImpl circle(double x, double y, double radius, SpatialContext context) {
        Point point = new PointImpl(x, y, context);
        return new CircleImpl(point, radius, context);
    }

    private static CircleImpl emptyCircle(SpatialContext context) {
        Point point = new PointImpl(Double.NaN, Double.NaN, context);
        return new CircleImpl(point, 0.0, context);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY, SpatialContext context) {
        return new RectangleImpl(minX, maxX, minY, maxY, context);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "The spatial relation changed after translating both shapes: "
                            + sourceOutput + " -> " + followUpOutput);
        }
    }

    @Test
    public void EMPTY_CENTER_SENTINEL_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = emptyCircle(context);
        Rectangle sourceRectangle = rectangle(10.0, 12.0, 10.0, 12.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POSITIVE_CIRCLE_BBOX_DISJOINT_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 2.0, context);
        Rectangle sourceRectangle = rectangle(10.0, 12.0, 10.0, 12.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_POINT_BBOX_DISJOINT_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 0.0, context);
        Rectangle sourceRectangle = rectangle(2.0, 3.0, 2.0, 3.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CIRCLE_BBOX_WITHIN_LARGE_RECTANGLE_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 2.0, context);
        Rectangle sourceRectangle = rectangle(-10.0, 10.0, -10.0, 10.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_RECTANGLE_CONTAINS_CENTER_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(1.0, 1.0, 0.0, context);
        Rectangle sourceRectangle = rectangle(-2.0, 4.0, -3.0, 5.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ENCLOSING_BOX_EQUAL_RECTANGLE_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 4.0, context);
        Rectangle sourceRectangle = rectangle(-4.0, 4.0, -4.0, 4.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_CIRCLE_CONTAINS_ALL_CORNERS_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 10.0, context);
        Rectangle sourceRectangle = rectangle(-2.0, 2.0, -2.0, 2.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_FARTHEST_CORNER_OUTSIDE_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0, context);
        Rectangle sourceRectangle = rectangle(-4.0, 4.0, -4.0, 4.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void AXES_OUTSIDE_BBOX_OVERLAP_BUT_CORNER_DISJOINT_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 2.0, context);
        Rectangle sourceRectangle = rectangle(1.9, 3.0, 1.9, 3.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void X_AXIS_LEFT_Y_AXIS_INSIDE_INTERSECTION_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 3.0, context);
        Rectangle sourceRectangle = rectangle(2.0, 4.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void X_AXIS_RIGHT_Y_AXIS_INSIDE_INTERSECTION_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 3.0, context);
        Rectangle sourceRectangle = rectangle(-4.0, -2.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void Y_AXIS_BELOW_X_AXIS_INSIDE_INTERSECTION_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 3.0, context);
        Rectangle sourceRectangle = rectangle(-1.0, 1.0, 2.0, 4.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void Y_AXIS_ABOVE_X_AXIS_INSIDE_INTERSECTION_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 3.0, context);
        Rectangle sourceRectangle = rectangle(-1.0, 1.0, -4.0, -2.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CIRCLE_CENTER_INSIDE_RECTANGLE_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0, context);
        Rectangle sourceRectangle = rectangle(-3.0, 3.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CLOSEST_POINT_TANGENT_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0, context);
        Rectangle sourceRectangle = rectangle(3.0, 4.0, 4.0, 5.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FARTHEST_POINT_TANGENT_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0, context);
        Rectangle sourceRectangle = rectangle(-3.0, 3.0, -4.0, 4.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_ZERO_WIDTH_RECTANGLE_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 3.0, context);
        Rectangle sourceRectangle = rectangle(2.0, 2.0, -1.0, 1.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_ZERO_HEIGHT_RECTANGLE_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 3.0, context);
        Rectangle sourceRectangle = rectangle(-1.0, 1.0, 2.0, 2.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SINGLE_POINT_RECTANGLE_ON_CIRCLE_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 5.0, context);
        Rectangle sourceRectangle = rectangle(3.0, 3.0, 4.0, 4.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_SINGLE_POINT_RECTANGLE_SAME_POINT_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(2.0, -1.0, 0.0, context);
        Rectangle sourceRectangle = rectangle(2.0, 2.0, -1.0, -1.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RECTANGLE_STRADDLES_CIRCLE_X_AXIS_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(0.0, 0.0, 6.0, context);
        Rectangle sourceRectangle = rectangle(-2.0, 2.0, -5.0, 5.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void TRANSLATION_CROSSES_NO_COORDINATE_LIMIT_variation1() {
        SpatialContext context = context();
        CircleImpl sourceCircle = circle(1000.25, -800.5, 7.5, context);
        Rectangle sourceRectangle = rectangle(995.0, 1005.0, -804.0, -796.0, context);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
