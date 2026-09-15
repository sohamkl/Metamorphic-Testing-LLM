import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

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
        return CONTEXT.makeRectangle(minX, maxX, minY, maxY);
    }

    private static Rectangle emptyRectangle() {
        return CONTEXT.makeRectangle(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    }

    @Test
    public void EMPTY_CIRCLE_SENTINEL_variation1_nonEmptyRectangle() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = rectangle(-2, 2, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EMPTY_RECTANGLE_SENTINEL_variation1_positiveRadiusCircle() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = emptyRectangle();
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOTH_SHAPES_EMPTY_variation1_emptySentinels() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = emptyRectangle();
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDING_BOX_STRICTLY_DISJOINT_variation1_right() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(6, 8, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDING_BOX_STRICTLY_DISJOINT_variation2_left() {
        CircleImpl sourceCircle = circle(10, 20, 5);
        Rectangle sourceRectangle = rectangle(2, 4, 19, 21);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDING_BOX_STRICTLY_DISJOINT_variation3_above() {
        CircleImpl sourceCircle = circle(-10, 8, 5);
        Rectangle sourceRectangle = rectangle(-11, -9, 14, 16);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDING_BOX_STRICTLY_DISJOINT_variation4_below() {
        CircleImpl sourceCircle = circle(4, -3, 5);
        Rectangle sourceRectangle = rectangle(3, 5, -11, -9);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CIRCLE_BOUNDING_BOX_WITHIN_RECTANGLE_variation1_strictEnclosure() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-6, 6, -6, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CIRCLE_BOUNDING_BOX_WITHIN_RECTANGLE_variation2_sharedLeftBoundary() {
        CircleImpl sourceCircle = circle(12, 7, 5);
        Rectangle sourceRectangle = rectangle(7, 18, 1, 13);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BOUNDING_BOX_variation1_exactSquare() {
        CircleImpl sourceCircle = circle(-4, 9, 5);
        Rectangle sourceRectangle = rectangle(-9, 1, 4, 14);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIAGONAL_BBOX_OVERLAP_BUT_GEOMETRIC_GAP_variation1_northEast() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(4, 6, 4, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIAGONAL_BBOX_OVERLAP_BUT_GEOMETRIC_GAP_variation2_northWest() {
        CircleImpl sourceCircle = circle(3, 2, 5);
        Rectangle sourceRectangle = rectangle(-3, -1, 6, 8);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIAGONAL_BBOX_OVERLAP_BUT_GEOMETRIC_GAP_variation3_southWest() {
        CircleImpl sourceCircle = circle(-7, 4, 5);
        Rectangle sourceRectangle = rectangle(-13, -11, -2, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DIAGONAL_BBOX_OVERLAP_BUT_GEOMETRIC_GAP_variation4_southEast() {
        CircleImpl sourceCircle = circle(8, -5, 5);
        Rectangle sourceRectangle = rectangle(12, 14, -11, -9);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_DIAGONAL_CORNER_TANGENCY_variation1_northEast() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(3, 6, 4, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_DIAGONAL_CORNER_TANGENCY_variation2_northWest() {
        CircleImpl sourceCircle = circle(2, 3, 5);
        Rectangle sourceRectangle = rectangle(-4, -1, 7, 9);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_DIAGONAL_CORNER_TANGENCY_variation3_southWest() {
        CircleImpl sourceCircle = circle(-3, 6, 5);
        Rectangle sourceRectangle = rectangle(-9, -6, 0, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_DIAGONAL_CORNER_TANGENCY_variation4_southEast() {
        CircleImpl sourceCircle = circle(7, -2, 5);
        Rectangle sourceRectangle = rectangle(10, 13, -8, -6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_SIDE_TANGENCY_variation1_right() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(5, 7, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_SIDE_TANGENCY_variation2_left() {
        CircleImpl sourceCircle = circle(8, 4, 5);
        Rectangle sourceRectangle = rectangle(1, 3, 3, 5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_SIDE_TANGENCY_variation3_top() {
        CircleImpl sourceCircle = circle(-6, 5, 5);
        Rectangle sourceRectangle = rectangle(-7, -5, 10, 12);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void EXACT_SIDE_TANGENCY_variation4_bottom() {
        CircleImpl sourceCircle = circle(3, -4, 5);
        Rectangle sourceRectangle = rectangle(2, 4, -11, -9);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIDE_PENETRATION_WITH_AXIS_SPANNING_variation1_right() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(4, 6, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIDE_PENETRATION_WITH_AXIS_SPANNING_variation2_left() {
        CircleImpl sourceCircle = circle(10, 5, 5);
        Rectangle sourceRectangle = rectangle(4, 6, 4, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIDE_PENETRATION_WITH_AXIS_SPANNING_variation3_top() {
        CircleImpl sourceCircle = circle(-5, 7, 5);
        Rectangle sourceRectangle = rectangle(-6, -4, 11, 13);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void SIDE_PENETRATION_WITH_AXIS_SPANNING_variation4_bottom() {
        CircleImpl sourceCircle = circle(6, -3, 5);
        Rectangle sourceRectangle = rectangle(5, 7, -9, -7);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_BUT_CIRCLE_DOES_NOT_variation1_northEast() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(3, 4, 3, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_BUT_CIRCLE_DOES_NOT_variation2_northWest() {
        CircleImpl sourceCircle = circle(5, 8, 5);
        Rectangle sourceRectangle = rectangle(1, 2, 11, 12);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_BUT_CIRCLE_DOES_NOT_variation3_southWest() {
        CircleImpl sourceCircle = circle(-8, 3, 5);
        Rectangle sourceRectangle = rectangle(-12, -11, -1, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_BUT_CIRCLE_DOES_NOT_variation4_southEast() {
        CircleImpl sourceCircle = circle(4, -7, 5);
        Rectangle sourceRectangle = rectangle(7, 8, -11, -10);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STRICT_RECTANGLE_CONTAINMENT_BY_CIRCLE_variation1_northEast() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(1, 2, 1, 3);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STRICT_RECTANGLE_CONTAINMENT_BY_CIRCLE_variation2_northWest() {
        CircleImpl sourceCircle = circle(6, 4, 5);
        Rectangle sourceRectangle = rectangle(4, 5, 5, 7);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STRICT_RECTANGLE_CONTAINMENT_BY_CIRCLE_variation3_southWest() {
        CircleImpl sourceCircle = circle(-5, 9, 5);
        Rectangle sourceRectangle = rectangle(-7, -6, 6, 8);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void STRICT_RECTANGLE_CONTAINMENT_BY_CIRCLE_variation4_southEast() {
        CircleImpl sourceCircle = circle(9, -6, 5);
        Rectangle sourceRectangle = rectangle(10, 11, -9, -7);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTAINED_RECTANGLE_WITH_BOUNDARY_CORNER_variation1_northEast() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(0, 3, 0, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTAINED_RECTANGLE_WITH_BOUNDARY_CORNER_variation2_northWest() {
        CircleImpl sourceCircle = circle(7, 2, 5);
        Rectangle sourceRectangle = rectangle(4, 7, 2, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTAINED_RECTANGLE_WITH_BOUNDARY_CORNER_variation3_southWest() {
        CircleImpl sourceCircle = circle(-4, 8, 5);
        Rectangle sourceRectangle = rectangle(-7, -4, 4, 8);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void CONTAINED_RECTANGLE_WITH_BOUNDARY_CORNER_variation4_southEast() {
        CircleImpl sourceCircle = circle(5, -5, 5);
        Rectangle sourceRectangle = rectangle(5, 8, -9, -5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOTH_AXES_CROSSED_AND_FARTHEST_SELECTION_variation1_tiedSides() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-2, 2, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BOTH_AXES_CROSSED_AND_FARTHEST_SELECTION_variation2_boundarySelection() {
        CircleImpl sourceCircle = circle(10, 10, 5);
        Rectangle sourceRectangle = rectangle(7, 12, 9, 14);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void AXIS_CROSSING_STRIP_INTERSECTION_variation1_horizontalStrip() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(-6, 6, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void AXIS_CROSSING_STRIP_INTERSECTION_variation2_verticalStrip() {
        CircleImpl sourceCircle = circle(6, 3, 5);
        Rectangle sourceRectangle = rectangle(5, 7, -3, 9);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_POINT_RECTANGLES_variation1_strictlyInside() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(1, 1, 1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_POINT_RECTANGLES_variation2_onBoundary() {
        CircleImpl sourceCircle = circle(4, 7, 5);
        Rectangle sourceRectangle = rectangle(7, 7, 11, 11);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_POINT_RECTANGLES_variation3_outsideCircleInsideBox() {
        CircleImpl sourceCircle = circle(-6, 2, 5);
        Rectangle sourceRectangle = rectangle(-2, -2, 6, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_LINE_RECTANGLES_variation1_containedVerticalLine() {
        CircleImpl sourceCircle = circle(0, 0, 5);
        Rectangle sourceRectangle = rectangle(1, 1, -2, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void DEGENERATE_LINE_RECTANGLES_variation2_crossingHorizontalLine() {
        CircleImpl sourceCircle = circle(8, -2, 5);
        Rectangle sourceRectangle = rectangle(2, 14, -2, -2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_CIRCLE_RELATIONS_variation1_enclosingRectangle() {
        CircleImpl sourceCircle = circle(0, 0, 0);
        Rectangle sourceRectangle = rectangle(-1, 1, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_CIRCLE_RELATIONS_variation2_identicalPointRectangle() {
        CircleImpl sourceCircle = circle(5, 6, 0);
        Rectangle sourceRectangle = rectangle(5, 5, 6, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ZERO_RADIUS_CIRCLE_RELATIONS_variation3_separatedRectangle() {
        CircleImpl sourceCircle = circle(-3, 4, 0);
        Rectangle sourceRectangle = rectangle(-2, -1, 3, 5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
