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
                CONTEXT.makePoint(Double.NaN, Double.NaN), 5.0, CONTEXT);
    }

    private static Rectangle rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
    }

    private static Rectangle emptyRectangle() {
        return new RectangleImpl(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN, CONTEXT);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Common translation changed the spatial relation from "
                            + sourceOutput + " to " + followUpOutput);
        }
    }

    @Test
    void EMPTY_CIRCLE_NONEMPTY_RECTANGLE_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = rectangle(-2, 2, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NONEMPTY_CIRCLE_EMPTY_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = emptyRectangle();
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOTH_SHAPES_EMPTY_variation1() {
        CircleImpl sourceCircle = emptyCircle();
        Rectangle sourceRectangle = emptyRectangle();
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CIRCLE_STRICTLY_WITHIN_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-6, 6, -6, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CIRCLE_WITHIN_RECTANGLE_TOUCHING_ONE_BOX_SIDE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-5, 6, -6, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CIRCLE_WITHIN_RECTANGLE_TOUCHING_BOX_CORNER_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-5, 6, -5, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RECTANGLE_EQUALS_CIRCLE_BOUNDING_BOX_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-5, 5, -5, 5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_POINT_WITHIN_LARGER_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0);
        Rectangle sourceRectangle = rectangle(-1, 1, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0);
        Rectangle sourceRectangle = rectangle(0, 0, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_DISJOINT_POINT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(0);
        Rectangle sourceRectangle = rectangle(1, 1, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOUNDING_BOX_DISJOINT_LEFT_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-8, -6, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOUNDING_BOX_DISJOINT_RIGHT_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(6, 8, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOUNDING_BOX_DISJOINT_BELOW_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-1, 1, -8, -6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOUNDING_BOX_DISJOINT_ABOVE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-1, 1, 6, 8);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RIGHT_SIDE_STRICT_OVERLAP_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(4, 7, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEFT_SIDE_STRICT_OVERLAP_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-7, -4, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TOP_SIDE_STRICT_OVERLAP_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-1, 1, 4, 7);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOTTOM_SIDE_STRICT_OVERLAP_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-1, 1, -7, -4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RIGHT_CARDINAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(5, 7, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LEFT_CARDINAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-7, -5, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void TOP_CARDINAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-1, 1, 5, 7);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BOTTOM_CARDINAL_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-1, 1, -7, -5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIAGONAL_BOX_OVERLAP_BUT_CIRCLE_DISJOINT_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(4, 6, 4, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIAGONAL_CLOSEST_CORNER_TANGENCY_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(3, 6, 4, 7);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIAGONAL_CLOSEST_CORNER_INSIDE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(3, 6, 3, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_UPPER_RIGHT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(1, 2, 1, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_UPPER_LEFT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-2, -1, 1, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_LOWER_LEFT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-2, -1, -2, -1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_LOWER_RIGHT_RECTANGLE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(1, 2, -2, -1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FARTHEST_CORNER_ON_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(0, 3, 0, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_BUT_CIRCLE_ONLY_INTERSECTS_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(3, 5, 3, 5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void AXIS_SPANNING_FARTHEST_RIGHT_TOP_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-1, 3, -1, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void AXIS_SPANNING_FARTHEST_LEFT_TOP_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-3, 1, -1, 2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void AXIS_SPANNING_FARTHEST_LEFT_BOTTOM_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-3, 1, -2, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void AXIS_SPANNING_FARTHEST_RIGHT_BOTTOM_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-1, 3, -2, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void SYMMETRIC_AXIS_SPANNING_TIE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-3, 3, -3, 3);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CENTER_POINT_RECTANGLE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(0, 0, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CIRCUMFERENCE_POINT_RECTANGLE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(3, 3, 4, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POINT_INSIDE_BBOX_OUTSIDE_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(4, 4, 4, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HORIZONTAL_DIAMETER_LINE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-5, 5, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void VERTICAL_CHORD_LINE_CONTAINED_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(3, 3, -4, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HORIZONTAL_LINE_EXTENDS_BEYOND_CIRCLE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(-7, 7, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void VERTICAL_TANGENT_LINE_SEGMENT_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(5, 5, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void VERTICAL_LINE_PARTLY_INSIDE_PARTLY_OUTSIDE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(0, 0, 4, 7);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HORIZONTAL_LINE_PARTLY_INSIDE_PARTLY_OUTSIDE_variation1() {
        CircleImpl sourceCircle = circle(5);
        Rectangle sourceRectangle = rectangle(4, 7, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        SpatialRelation followUpOutput = ((CircleImpl) followUp[0]).relate((Rectangle) followUp[1]);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }
}
