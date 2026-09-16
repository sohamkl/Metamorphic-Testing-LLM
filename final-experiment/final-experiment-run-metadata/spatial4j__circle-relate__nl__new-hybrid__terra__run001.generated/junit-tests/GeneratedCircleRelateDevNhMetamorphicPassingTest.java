import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CONTEXT = new SpatialContext(false);
    private static final double DX = 11.0;
    private static final double DY = -7.0;

    private MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source(
            double cx, double cy, double radius,
            double minX, double maxX, double minY, double maxY) {
        CircleImpl circle = new CircleImpl(new PointImpl(cx, cy, CONTEXT), radius, CONTEXT);
        Rectangle rectangle = new RectangleImpl(minX, maxX, minY, maxY, CONTEXT);
        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circle, rectangle);
    }

    private MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input generateFollowUp(
            MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source) {
        CircleImpl originalCircle = source.receiver();
        Rectangle originalRectangle = source.arg0();

        CircleImpl translatedCircle = new CircleImpl(
                new PointImpl(
                        originalCircle.getCenter().getX() + DX,
                        originalCircle.getCenter().getY() + DY,
                        CONTEXT),
                originalCircle.getRadius(),
                CONTEXT);

        Rectangle translatedRectangle = new RectangleImpl(
                originalRectangle.getMinX() + DX,
                originalRectangle.getMaxX() + DX,
                originalRectangle.getMinY() + DY,
                originalRectangle.getMaxY() + DY,
                CONTEXT);

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle, translatedRectangle);
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                           SpatialRelation followUpOutput) {
        assertEquals(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input sourceInput) {
        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(sourceInput);
        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(generateFollowUp(sourceInput));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_DISJOINT_HORIZONTAL_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 6, 7, -1, 1));
    }

    @Test
    void BBOX_WITHIN_LARGE_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -6, 6, -6, 6));
    }

    @Test
    void BBOX_EQUALS_RECTANGLE_SPECIAL_WITHIN_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, 5, -5, 5));
    }

    @Test
    void PHASE2_DIAGONAL_DISJOINT_NORTHEAST_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 4, 5, 4, 5));
    }

    @Test
    void PHASE2_DIAGONAL_DISJOINT_NORTHWEST_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, -4, 4, 5));
    }

    @Test
    void PHASE2_DIAGONAL_DISJOINT_SOUTHWEST_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, -4, -5, -4));
    }

    @Test
    void PHASE2_DIAGONAL_DISJOINT_SOUTHEAST_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 4, 5, -5, -4));
    }

    @Test
    void PHASE2_HORIZONTAL_AXIS_SPAN_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -1, 1, 4, 6));
    }

    @Test
    void PHASE2_VERTICAL_AXIS_SPAN_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 4, 6, -1, 1));
    }

    @Test
    void PHASE2_BOTH_AXES_SPANNED_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -6, 6, -1, 1));
    }

    @Test
    void PHASE2_EXTERNAL_TANGENCY_NORTHEAST_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 4, 4, 5));
    }

    @Test
    void PHASE2_EXTERNAL_TANGENCY_SOUTHWEST_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -4, -3, -5, -4));
    }

    @Test
    void CONTAINS_STRICT_INTERIOR_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -1, 1, -1, 1));
    }

    @Test
    void CONTAINS_FARTHEST_CORNER_TANGENT_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -3, 3, -4, 4));
    }

    @Test
    void BBOX_CONTAINS_BUT_CORNER_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -4, 4, -4, 4));
    }

    @Test
    void CONTAINS_CENTER_LEFT_OF_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 2, -1, 1));
    }

    @Test
    void CONTAINS_CENTER_RIGHT_OF_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -2, -1, -1, 1));
    }

    @Test
    void CONTAINS_CENTER_BELOW_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -1, 1, 1, 2));
    }

    @Test
    void CONTAINS_CENTER_ABOVE_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -1, 1, -2, -1));
    }

    @Test
    void CONTAINS_CENTER_DIAGONALLY_OUTSIDE_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 2, 1, 2));
    }

    @Test
    void POINT_RECTANGLE_AT_CENTER_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 0, 0, 0, 0));
    }

    @Test
    void POINT_RECTANGLE_ON_CIRCLE_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 5, 5, 0, 0));
    }

    @Test
    void ZERO_HEIGHT_DIAMETER_LINE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -6, 6, 0, 0));
    }

    @Test
    void ZERO_WIDTH_DIAMETER_LINE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 0, 0, -6, 6));
    }

    @Test
    void ZERO_RADIUS_WITHIN_NON_EQUAL_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 0, -1, 1, -1, 1));
    }

    @Test
    void ZERO_RADIUS_EQUAL_POINT_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    void ZERO_RADIUS_DISJOINT_POINT_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 0, 1, 1, 0, 0));
    }
}
