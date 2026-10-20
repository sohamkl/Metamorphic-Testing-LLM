import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);

    private static final class TranslationCase {
        private final MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input;
        private final double dx;
        private final double dy;

        private TranslationCase(
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input,
                double dx,
                double dy) {
            this.input = input;
            this.dx = dx;
            this.dy = dy;
        }
    }

    private TranslationCase source(
            double cx,
            double cy,
            double radius,
            double minX,
            double maxX,
            double minY,
            double maxY,
            double dx,
            double dy) {

        CircleImpl circle = new CircleImpl(
                CONTEXT.makePoint(cx, cy),
                radius,
                CONTEXT);
        Rectangle rectangle = CONTEXT.makeRectangle(minX, maxX, minY, maxY);

        return new TranslationCase(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circle, rectangle),
                dx,
                dy);
    }

    private MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input generateFollowUp(
            TranslationCase source) {

        Assertions.assertTrue(source.dx != 0.0 || source.dy != 0.0);

        CircleImpl originalCircle = source.input.receiver();
        Rectangle originalRectangle = source.input.arg0();

        CircleImpl translatedCircle = new CircleImpl(
                CONTEXT.makePoint(
                        originalCircle.getCenter().getX() + source.dx,
                        originalCircle.getCenter().getY() + source.dy),
                originalCircle.getRadius(),
                CONTEXT);

        Rectangle translatedRectangle = CONTEXT.makeRectangle(
                originalRectangle.getMinX() + source.dx,
                originalRectangle.getMaxX() + source.dx,
                originalRectangle.getMinY() + source.dy,
                originalRectangle.getMaxY() + source.dy);

        Assertions.assertEquals(originalCircle.getRadius(), translatedCircle.getRadius());
        Assertions.assertEquals(
                originalRectangle.getWidth(),
                translatedRectangle.getWidth());
        Assertions.assertEquals(
                originalRectangle.getHeight(),
                translatedRectangle.getHeight());

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle,
                translatedRectangle);
    }

    private void assertMetamorphicRelationFor(TranslationCase source) {
        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(source.input);
        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_DISJOINT_HORIZONTAL_variation1_left() {
        assertMetamorphicRelationFor(source(0, 0, 5, -9, -6, -2, 2, 8, 0));
    }

    @Test
    public void BBOX_DISJOINT_HORIZONTAL_variation2_right() {
        assertMetamorphicRelationFor(source(0, 0, 5, 6, 9, -3, 4, 0, -6));
    }

    @Test
    public void BBOX_DISJOINT_VERTICAL_variation1_below() {
        assertMetamorphicRelationFor(source(0, 0, 5, -3, 2, -9, -6, 7, -9));
    }

    @Test
    public void BBOX_DISJOINT_VERTICAL_variation2_above() {
        assertMetamorphicRelationFor(source(0, 0, 5, -2, 4, 6, 10, 8, 0));
    }

    @Test
    public void DIAGONAL_CORNER_MISS_INSIDE_BBOX_variation1_northeast() {
        assertMetamorphicRelationFor(source(0, 0, 5, 4, 5, 4, 5, 0, -6));
    }

    @Test
    public void DIAGONAL_CORNER_MISS_INSIDE_BBOX_variation2_northwest() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, -4, 4, 5, 7, -9));
    }

    @Test
    public void DIAGONAL_CORNER_MISS_INSIDE_BBOX_variation3_southeast() {
        assertMetamorphicRelationFor(source(0, 0, 5, 4, 5, -5, -4, 8, 0));
    }

    @Test
    public void DIAGONAL_CORNER_MISS_INSIDE_BBOX_variation4_southwest() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, -4, -5, -4, 0, -6));
    }

    @Test
    public void DIAGONAL_CLOSEST_CORNER_TANGENCY_variation1_northeast() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 4, 4, 5, 7, -9));
    }

    @Test
    public void DIAGONAL_CLOSEST_CORNER_TANGENCY_variation2_northwest() {
        assertMetamorphicRelationFor(source(0, 0, 5, -4, -3, 4, 5, 8, 0));
    }

    @Test
    public void DIAGONAL_CLOSEST_CORNER_TANGENCY_variation3_southeast() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 4, -5, -4, 0, -6));
    }

    @Test
    public void DIAGONAL_CLOSEST_CORNER_TANGENCY_variation4_southwest() {
        assertMetamorphicRelationFor(source(0, 0, 5, -4, -3, -5, -4, 7, -9));
    }

    @Test
    public void BBOX_STRICTLY_WITHIN_RECTANGLE_variation1_surrounded() {
        assertMetamorphicRelationFor(source(0, 0, 5, -7, 8, -6, 9, 8, 0));
    }

    @Test
    public void BBOX_WITHIN_WITH_SHARED_BOUNDARIES_variation1_singleSide() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, 7, -7, 8, 0, -6));
    }

    @Test
    public void BBOX_WITHIN_WITH_SHARED_BOUNDARIES_variation2_adjacentSides() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, 8, -5, 7, 7, -9));
    }

    @Test
    public void BBOX_WITHIN_WITH_SHARED_BOUNDARIES_variation3_oppositeSides() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, 5, -8, 7, 8, 0));
    }

    @Test
    public void RECTANGLE_EQUALS_ENCLOSING_BOX_variation1_exactEquality() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, 5, -5, 5, 0, -6));
    }

    @Test
    public void ZERO_RADIUS_STRICTLY_INSIDE_AREA_RECTANGLE_variation1_interior() {
        assertMetamorphicRelationFor(source(2, -1, 0, -2, 6, -4, 3, 7, -9));
    }

    @Test
    public void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_variation1_samePoint() {
        assertMetamorphicRelationFor(source(3, 2, 0, 3, 3, 2, 2, 8, 0));
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_variation1_positiveGap() {
        assertMetamorphicRelationFor(source(0, 0, 0, 4, 7, 2, 6, 0, -6));
    }

    @Test
    public void VERTICAL_CUT_THROUGH_CIRCLE_variation1_containsCenterAxis() {
        assertMetamorphicRelationFor(source(0, 0, 5, -1, 1, -7, 8, 7, -9));
    }

    @Test
    public void VERTICAL_CUT_THROUGH_CIRCLE_variation2_rightSideStrip() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 4, -8, 7, 8, 0));
    }

    @Test
    public void HORIZONTAL_CUT_THROUGH_CIRCLE_variation1_containsCenterAxis() {
        assertMetamorphicRelationFor(source(0, 0, 5, -8, 7, -1, 1, 0, -6));
    }

    @Test
    public void HORIZONTAL_CUT_THROUGH_CIRCLE_variation2_aboveCenter() {
        assertMetamorphicRelationFor(source(0, 0, 5, -7, 8, 3, 4, 7, -9));
    }

    @Test
    public void X_AXIS_OUTSIDE_Y_AXIS_SPANNED_variation1_leftSide() {
        assertMetamorphicRelationFor(source(0, 0, 5, -8, -3, -2, 2, 8, 0));
    }

    @Test
    public void X_AXIS_OUTSIDE_Y_AXIS_SPANNED_variation2_rightSide() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 8, -1, 3, 0, -6));
    }

    @Test
    public void Y_AXIS_OUTSIDE_X_AXIS_SPANNED_variation1_belowCenter() {
        assertMetamorphicRelationFor(source(0, 0, 5, -2, 2, -8, -3, 7, -9));
    }

    @Test
    public void Y_AXIS_OUTSIDE_X_AXIS_SPANNED_variation2_aboveCenter() {
        assertMetamorphicRelationFor(source(0, 0, 5, -3, 1, 3, 8, 8, 0));
    }

    @Test
    public void CENTERED_AREA_RECTANGLE_STRICTLY_CONTAINED_variation1_centered() {
        assertMetamorphicRelationFor(source(0, 0, 5, -2, 2, -2, 2, 0, -6));
    }

    @Test
    public void OFF_AXIS_QUADRANT_RECTANGLE_STRICTLY_CONTAINED_variation1_northeast() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 2, 1, 2, 7, -9));
    }

    @Test
    public void OFF_AXIS_QUADRANT_RECTANGLE_STRICTLY_CONTAINED_variation2_northwest() {
        assertMetamorphicRelationFor(source(0, 0, 5, -2, -1, 1, 2, 8, 0));
    }

    @Test
    public void OFF_AXIS_QUADRANT_RECTANGLE_STRICTLY_CONTAINED_variation3_southeast() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 2, -2, -1, 0, -6));
    }

    @Test
    public void OFF_AXIS_QUADRANT_RECTANGLE_STRICTLY_CONTAINED_variation4_southwest() {
        assertMetamorphicRelationFor(source(0, 0, 5, -2, -1, -2, -1, 7, -9));
    }

    @Test
    public void FARTHEST_CORNER_EXACTLY_ON_CIRCLE_variation1_northeast() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 3, 1, 4, 8, 0));
    }

    @Test
    public void FARTHEST_CORNER_EXACTLY_ON_CIRCLE_variation2_northwest() {
        assertMetamorphicRelationFor(source(0, 0, 5, -3, -1, 1, 4, 0, -6));
    }

    @Test
    public void FARTHEST_CORNER_EXACTLY_ON_CIRCLE_variation3_southeast() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 3, -4, -1, 7, -9));
    }

    @Test
    public void FARTHEST_CORNER_EXACTLY_ON_CIRCLE_variation4_southwest() {
        assertMetamorphicRelationFor(source(0, 0, 5, -3, -1, -4, -1, 8, 0));
    }

    @Test
    public void CENTERED_FARTHEST_CORNER_REJECTION_variation1_symmetricTies() {
        assertMetamorphicRelationFor(source(0, 0, 5, -4, 4, -4, 4, 0, -6));
    }

    @Test
    public void OFF_AXIS_FARTHEST_CORNER_REJECTION_variation1_northeast() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 4, 1, 4, 7, -9));
    }

    @Test
    public void OFF_AXIS_FARTHEST_CORNER_REJECTION_variation2_northwest() {
        assertMetamorphicRelationFor(source(0, 0, 5, -4, -1, 1, 4, 8, 0));
    }

    @Test
    public void OFF_AXIS_FARTHEST_CORNER_REJECTION_variation3_southeast() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 4, -4, -1, 0, -6));
    }

    @Test
    public void OFF_AXIS_FARTHEST_CORNER_REJECTION_variation4_southwest() {
        assertMetamorphicRelationFor(source(0, 0, 5, -4, -1, -4, -1, 7, -9));
    }

    @Test
    public void INTERIOR_POINT_RECTANGLE_CONTAINED_variation1_centerPoint() {
        assertMetamorphicRelationFor(source(0, 0, 5, 0, 0, 0, 0, 8, 0));
    }

    @Test
    public void INTERIOR_POINT_RECTANGLE_CONTAINED_variation2_nonCenterPoint() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 1, 2, 2, 0, -6));
    }

    @Test
    public void BOUNDARY_POINT_RECTANGLE_CONTAINED_variation1_axisAligned() {
        assertMetamorphicRelationFor(source(0, 0, 5, 5, 5, 0, 0, 7, -9));
    }

    @Test
    public void BOUNDARY_POINT_RECTANGLE_CONTAINED_variation2_nonAxisAligned() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 3, 4, 4, 8, 0));
    }

    @Test
    public void CONTAINED_ZERO_WIDTH_OR_HEIGHT_SEGMENT_variation1_vertical() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 1, -2, 2, 0, -6));
    }

    @Test
    public void CONTAINED_ZERO_WIDTH_OR_HEIGHT_SEGMENT_variation2_horizontal() {
        assertMetamorphicRelationFor(source(0, 0, 5, -2, 2, 1, 1, 7, -9));
    }

    @Test
    public void INTERSECTING_DEGENERATE_SEGMENT_variation1_boundaryVertical() {
        assertMetamorphicRelationFor(source(0, 0, 5, 5, 5, 0, 2, 8, 0));
    }

    @Test
    public void INTERSECTING_DEGENERATE_SEGMENT_variation2_centerChord() {
        assertMetamorphicRelationFor(source(0, 0, 5, -7, 7, 0, 0, 0, -6));
    }
}
