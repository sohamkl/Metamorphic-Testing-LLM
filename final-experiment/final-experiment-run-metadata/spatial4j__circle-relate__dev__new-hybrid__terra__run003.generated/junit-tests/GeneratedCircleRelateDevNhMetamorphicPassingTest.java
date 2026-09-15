import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private void exercise(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);
        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(sourceCircle, sourceRectangle);
        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);
        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_DISJOINT_HORIZONTAL_GAP_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(6.0, 7.0, -1.0, 1.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void BBOX_DISJOINT_VERTICAL_GAP_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(-1.0, 1.0, 6.0, 7.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void CIRCLE_BBOX_STRICTLY_WITHIN_RECTANGLE_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(-6.0, 6.0, -6.0, 6.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void EXACT_ENCLOSING_BOX_EQUALITY_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(-5.0, 5.0, -5.0, 5.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void PHASE2_DIAGONAL_NEAREST_CORNER_OUTSIDE_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(4.0, 6.0, 4.0, 6.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void PHASE2_DIAGONAL_NEAREST_CORNER_TANGENT_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(3.0, 4.0, 4.0, 5.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void PHASE2_HORIZONTAL_AXIS_SPAN_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(4.0, 6.0, -1.0, 1.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void PHASE2_VERTICAL_AXIS_SPAN_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(-1.0, 1.0, 4.0, 6.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void PHASE2_RECTANGLE_SPANS_BOTH_AXES_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(-1.0, 6.0, -1.0, 1.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void PHASE2_CENTER_ON_RECTANGLE_MIN_BOUNDARIES_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(0.0, 6.0, 0.0, 6.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_WITH_OUTSIDE_FARTHEST_CORNER_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(3.0, 5.0, 3.0, 5.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_TOUCHING_BBOX_SIDE_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(4.0, 5.0, -1.0, 1.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_ALL_CORNERS_INTERIOR_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(-3.0, 3.0, -3.0, 3.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void FARTHEST_X_TIE_AND_BOUNDARY_CONTAINMENT_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(-3.0, 3.0, -4.0, 0.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void CONTAINED_VERTICAL_LINE_RECTANGLE_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(1.0, 1.0, -1.0, 1.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void TANGENT_POINT_RECTANGLE_CONTAINED_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = new RectangleImpl(5.0, 5.0, 0.0, 0.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void ZERO_RADIUS_CIRCLE_WITHIN_AREA_RECTANGLE_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 0.0, context);
        Rectangle rectangle = new RectangleImpl(-1.0, 1.0, -1.0, 1.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void ZERO_RADIUS_CIRCLE_DISJOINT_FROM_RECTANGLE_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 0.0, context);
        Rectangle rectangle = new RectangleImpl(1.0, 2.0, 1.0, 2.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void ZERO_RADIUS_EXACT_POINT_RECTANGLE_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 0.0, context);
        Rectangle rectangle = new RectangleImpl(0.0, 0.0, 0.0, 0.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void EMPTY_CIRCLE_WITH_NONEMPTY_RECTANGLE_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        Point emptyPoint = context.makePoint(Double.NaN, Double.NaN);
        CircleImpl circle = new CircleImpl(emptyPoint, 5.0, context);
        Rectangle rectangle = new RectangleImpl(-1.0, 1.0, -1.0, 1.0, context);
        exercise(circle, rectangle);
    }

    @Test
    void NONEMPTY_CIRCLE_WITH_EMPTY_RECTANGLE_translationInvariant() {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(context.makePoint(0.0, 0.0), 5.0, context);
        Rectangle rectangle = context.makeRectangle(Double.NaN, Double.NaN, Double.NaN, Double.NaN);
        exercise(circle, rectangle);
    }
}
