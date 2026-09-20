import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);

    private static CircleImpl circle(double radius) {
        return new CircleImpl(CONTEXT.makePoint(0.0, 0.0), radius, CONTEXT);
    }

    private static CircleImpl emptyCircle() {
        return new CircleImpl(
                CONTEXT.makePoint(Double.NaN, Double.NaN),
                10.0,
                CONTEXT);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static Rectangle emptyRectangle() {
        return rectangle(
                Double.NaN, Double.NaN,
                Double.NaN, Double.NaN);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Translating the circle and rectangle together changed their spatial relation: "
                            + sourceOutput + " -> " + followUpOutput);
        }
    }

    @Test
    public void EMPTY_CIRCLE_NONEMPTY_RECTANGLE_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = rectangle(-1.0, 1.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONEMPTY_CIRCLE_EMPTY_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = emptyRectangle();

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOTH_SHAPES_EMPTY_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = emptyRectangle();

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STRICT_BOUNDING_BOX_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(11.0, 12.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CIRCLE_BOUNDING_BOX_STRICTLY_WITHIN_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-11.0, 11.0, -11.0, 11.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDING_BOX_WITHIN_WITH_SHARED_EDGE_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-10.0, 12.0, -12.0, 12.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BOUNDING_BOX_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-10.0, 10.0, -10.0, 10.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_POINT_INSIDE_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0.0);
        Rectangle sourceRectangle = rectangle(-1.0, 1.0, -1.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0.0);
        Rectangle sourceRectangle = rectangle(0.0, 0.0, 0.0, 0.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_POINT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0.0);
        Rectangle sourceRectangle = rectangle(1.0, 1.0, 0.0, 0.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTHEAST_CLOSEST_CORNER_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(8.0, 12.0, 8.0, 12.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NORTHWEST_CLOSEST_CORNER_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-12.0, -8.0, 8.0, 12.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTHEAST_CLOSEST_CORNER_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(8.0, 12.0, -12.0, -8.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SOUTHWEST_CLOSEST_CORNER_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-12.0, -8.0, -12.0, -8.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXTERNAL_CORNER_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(6.0, 12.0, 8.0, 12.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CORNER_OVERLAP_WITH_RECTANGLE_EXTENDING_OUTSIDE_BBOX_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(6.0, 12.0, 6.0, 12.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void VERTICAL_SLAB_SPANS_HORIZONTAL_AXIS_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(9.0, 12.0, -2.0, 2.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void HORIZONTAL_SLAB_SPANS_VERTICAL_AXIS_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-2.0, 2.0, 9.0, 12.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIDE_TANGENCY_WHILE_SPANNING_AXIS_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(10.0, 12.0, -2.0, 2.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STRICT_RECTANGLE_CONTAINED_BY_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(1.0, 3.0, 2.0, 4.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void FARTHEST_CORNER_TANGENT_CONTAINMENT_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(0.0, 6.0, 0.0, 8.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CENTRAL_RECTANGLE_CORNERS_OUTSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-8.0, 8.0, -8.0, 8.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ASYMMETRIC_AXIS_CROSSING_FARTHEST_CORNER_OUTSIDE_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-2.0, 7.0, -9.0, 1.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EQUAL_DISTANCE_TIES_AT_TANGENT_CORNERS_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-6.0, 6.0, -8.0, 8.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POINT_RECTANGLE_AT_CIRCLE_CENTER_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(0.0, 0.0, 0.0, 0.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POINT_RECTANGLE_ON_CIRCUMFERENCE_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(10.0, 10.0, 0.0, 0.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void POINT_RECTANGLE_INSIDE_BBOX_OUTSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(9.0, 9.0, 9.0, 9.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIAMETER_LINE_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-10.0, 10.0, 0.0, 0.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LINE_CROSSES_CIRCLE_WITH_ENDPOINTS_OUTSIDE_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(-8.0, 8.0, 8.0, 8.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void VERTICAL_CHORD_WITH_TANGENT_ENDPOINTS_variation1() {
        CircleImpl sourceCircle = circle(10.0);
        Rectangle sourceRectangle = rectangle(6.0, 6.0, -8.0, 8.0);

        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
