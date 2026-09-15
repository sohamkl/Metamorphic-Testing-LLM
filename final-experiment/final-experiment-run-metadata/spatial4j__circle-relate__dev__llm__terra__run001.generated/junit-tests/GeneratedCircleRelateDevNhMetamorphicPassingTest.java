import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    @Test
    void BBOX_DISJOINT_RIGHT_OF_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(6, 7, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_DISJOINT_ABOVE_CIRCLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-1, 1, 6, 7);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void STRICT_BBOX_WITHIN_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-6, 6, -6, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void EQUAL_ENCLOSING_BOX_SENTINEL_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-5, 5, -5, 5);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_OVERLAP_DIAGONAL_ACTUAL_DISJOINT_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(4, 6, 4, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void DIAGONAL_CORNER_TANGENCY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(3, 6, 4, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void HORIZONTAL_AXIS_SPANNING_INTERSECTION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(4, 6, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void VERTICAL_AXIS_SPANNING_INTERSECTION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-1, 1, 4, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void RECTANGLE_CROSSES_CENTER_AND_EXITS_BBOX_VERTICALLY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-1, 1, -6, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void STRICT_CIRCLE_CONTAINS_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-3, 4, -2, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_CONTAINS_BUT_CIRCLE_INTERSECTS_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-4, 4, -4, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void CONTAINS_WITH_FARTHEST_CORNER_TANGENT_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(0, 3, 0, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void X_FARTHEST_MAX_SELECTION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-1, 4, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void X_FARTHEST_MIN_SELECTION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-4, 1, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void Y_FARTHEST_MAX_SELECTION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-1, 1, -1, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void Y_FARTHEST_MIN_SELECTION_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-1, 1, -4, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void X_FARTHEST_DISTANCE_TIE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(-3, 3, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POINT_RECTANGLE_AT_CIRCLE_CENTER_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(0, 0, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POINT_RECTANGLE_ON_CIRCLE_BOUNDARY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(5, 5, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void POINT_RECTANGLE_IN_BBOX_OUTSIDE_DISK_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(4, 4, 4, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void VERTICAL_LINE_RECTANGLE_CROSSING_DISK_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(0, 0, -6, 6);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void VERTICAL_LINE_RECTANGLE_INSIDE_DISK_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(0, 0, -3, 3);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_WITH_SURROUNDING_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 0, context);
        Rectangle sourceRectangle = context.makeRectangle(-1, 1, -1, 1);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 0, context);
        Rectangle sourceRectangle = context.makeRectangle(0, 0, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ZERO_RADIUS_DISJOINT_POINT_RECTANGLE_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 0, context);
        Rectangle sourceRectangle = context.makeRectangle(1, 1, 0, 0);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void NON_ORIGIN_STRICT_CONTAINMENT_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(2, -3), 5, context);
        Rectangle sourceRectangle = context.makeRectangle(0, 4, -5, -2);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void FRACTIONAL_RADIUS_CORNER_TANGENCY_variation1() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl sourceCircle = new CircleImpl(context.makePoint(0, 0), 2.5, context);
        Rectangle sourceRectangle = context.makeRectangle(1.5, 4, 2, 4);
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
