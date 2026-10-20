import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CTX = new SpatialContext(false);

    private static Rectangle rectangle(double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, CTX);
    }

    private static void exercise(CircleImpl sourceCircle, Rectangle sourceRectangle) {
        SpatialRelation sourceOutput = sourceCircle.relate(sourceRectangle);

        Object[] followUp = CircleRelateDevMetamorphicSpec.generateFollowUp(
                sourceCircle, sourceRectangle);

        CircleImpl followUpCircle = (CircleImpl) followUp[0];
        Rectangle followUpRectangle = (Rectangle) followUp[1];
        SpatialRelation followUpOutput = followUpCircle.relate(followUpRectangle);

        CircleRelateDevMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_DISJOINT_BOTH_AXES_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(20, 22, 20, 22));
    }

    @Test
    public void PHASE2_CORNER_DISJOINT_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(4.5, 6, 4.5, 6));
    }

    @Test
    public void PHASE2_X_OUTSIDE_Y_INSIDE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(4, 6, -1, 1));
    }

    @Test
    public void PHASE2_X_INSIDE_Y_OUTSIDE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(-1, 1, 4, 6));
    }

    @Test
    public void BBOX_WITHIN_LARGER_RECTANGLE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(-6, 6, -6, 6));
    }

    @Test
    public void BBOX_EQUALS_RECTANGLE_IDENTITY_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(-5, 5, -5, 5));
    }

    @Test
    public void BBOX_INTERSECTS_SIDE_OVERLAP_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(3, 6, -1, 1));
    }

    @Test
    public void BBOX_CONTAINS_RECTANGLE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(-2, 2, -2, 2));
    }

    @Test
    public void BBOX_CONTAINS_BUT_FARTHEST_OUTSIDE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(-4, 4, -4, 4));
    }

    @Test
    public void SIDE_TANGENCY_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(5, 7, -1, 1));
    }

    @Test
    public void CORNER_TANGENCY_WITH_FARTHER_OUTSIDE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(3, 5, 4, 6));
    }

    @Test
    public void FARTHEST_CORNER_TANGENCY_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(0, 3, 4, 4));
    }

    @Test
    public void AXES_EXACTLY_ON_RECTANGLE_MINIMUMS_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(0, 2, 0, 2));
    }

    @Test
    public void AXES_EXACTLY_ON_RECTANGLE_MAXIMUMS_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(-2, 0, -2, 0));
    }

    @Test
    public void ZERO_WIDTH_RECTANGLE_INSIDE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(1, 1, -2, 2));
    }

    @Test
    public void ZERO_HEIGHT_RECTANGLE_INSIDE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(-2, 2, 1, 1));
    }

    @Test
    public void POINT_RECTANGLE_ON_CIRCLE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(3, 3, 4, 4));
    }

    @Test
    public void POINT_RECTANGLE_OUTSIDE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(0, 0), 5, CTX);
        exercise(circle, rectangle(6, 6, 0, 0));
    }

    @Test
    public void ZERO_RADIUS_RECTANGLE_CONTAINS_POINT_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(2, -3), 0, CTX);
        exercise(circle, rectangle(1, 3, -4, -2));
    }

    @Test
    public void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(-2, 3), 0, CTX);
        exercise(circle, rectangle(-2, -2, 3, 3));
    }

    @Test
    public void ZERO_RADIUS_POINT_RECTANGLE_OUTSIDE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(2, -3), 0, CTX);
        exercise(circle, rectangle(5, 5, -3, -3));
    }

    @Test
    public void NEGATIVE_COORDINATE_CONTAINS_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(-20, 15), 3, CTX);
        exercise(circle, rectangle(-22, -18, 13, 17));
    }

    @Test
    public void NEGATIVE_COORDINATE_PHASE2_DISJOINT_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(-20, 15), 3, CTX);
        exercise(circle, rectangle(-17, -14, 17, 20));
    }

    @Test
    public void ASYMMETRIC_FARTHEST_SELECTION_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(10, -10), 8, CTX);
        exercise(circle, rectangle(7, 16, -15, -5));
    }

    @Test
    public void ASYMMETRIC_FARTHEST_OUTSIDE_variation1() {
        CircleImpl circle = new CircleImpl(CTX.makePoint(10, -10), 8, CTX);
        exercise(circle, rectangle(4, 17, -14, -3));
    }
}
