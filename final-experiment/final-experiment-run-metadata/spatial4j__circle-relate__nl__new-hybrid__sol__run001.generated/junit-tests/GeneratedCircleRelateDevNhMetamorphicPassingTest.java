import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CARTESIAN_CONTEXT = new SpatialContext(false);
    private static final double TRANSLATE_X = 16.0;
    private static final double TRANSLATE_Y = -8.0;

    private static MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source(
            double centerX,
            double centerY,
            double radius,
            double minX,
            double maxX,
            double minY,
            double maxY) {

        Point center = CARTESIAN_CONTEXT.makePoint(centerX, centerY);
        CircleImpl circle = new CircleImpl(center, radius, CARTESIAN_CONTEXT);
        Rectangle rectangle =
                CARTESIAN_CONTEXT.makeRectangle(minX, maxX, minY, maxY);

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                circle, rectangle);
    }

    private static MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input generateFollowUp(
            MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source) {

        CircleImpl sourceCircle = source.receiver();
        Rectangle sourceRectangle = source.arg0();

        Point translatedCenter = CARTESIAN_CONTEXT.makePoint(
                sourceCircle.getCenter().getX() + TRANSLATE_X,
                sourceCircle.getCenter().getY() + TRANSLATE_Y);

        CircleImpl translatedCircle = new CircleImpl(
                translatedCenter,
                sourceCircle.getRadius(),
                CARTESIAN_CONTEXT);

        Rectangle translatedRectangle = CARTESIAN_CONTEXT.makeRectangle(
                sourceRectangle.getMinX() + TRANSLATE_X,
                sourceRectangle.getMaxX() + TRANSLATE_X,
                sourceRectangle.getMinY() + TRANSLATE_Y,
                sourceRectangle.getMaxY() + TRANSLATE_Y);

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle, translatedRectangle);
    }

    private static void assertMetamorphicRelationFor(
            MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source) {

        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input followUp =
                generateFollowUp(source);

        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {

        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    public void BBOX_DISJOINT_RIGHT_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 6, 9, -2, 2));
    }

    @Test
    public void BBOX_DISJOINT_ABOVE_variation1() {
        assertMetamorphicRelationFor(source(1, -1, 4, -1, 3, 4, 8));
    }

    @Test
    public void BBOX_DISJOINT_DIAGONAL_variation1() {
        assertMetamorphicRelationFor(source(-2, 1, 5, 4, 8, 7, 7));
    }

    @Test
    public void CIRCLE_BBOX_STRICTLY_WITHIN_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -6, 7, -8, 6));
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BOUNDING_BOX_variation1() {
        assertMetamorphicRelationFor(source(2, -3, 5, -3, 7, -8, 2));
    }

    @Test
    public void PHASE2_DIAGONAL_GAP_UPPER_RIGHT_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 4, 8, 4, 9));
    }

    @Test
    public void PHASE2_DIAGONAL_GAP_LOWER_LEFT_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -9, -4, -8, -4));
    }

    @Test
    public void PHASE2_DIAGONAL_GAP_UPPER_LEFT_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -8, -4, 4, 9));
    }

    @Test
    public void PHASE2_DIAGONAL_STRICT_INTERSECTION_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 7, 3, 8));
    }

    @Test
    public void PHASE2_DIAGONAL_EXTERNAL_TANGENCY_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 7, 4, 8));
    }

    @Test
    public void RIGHT_SIDE_AXIS_OVERLAP_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 7, -2, 2));
    }

    @Test
    public void UPPER_SIDE_AXIS_OVERLAP_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -2, 2, 3, 7));
    }

    @Test
    public void CENTER_INSIDE_RECTANGLE_WITH_PARTIAL_BBOX_OVERLAP_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -6, 3, -2, 2));
    }

    @Test
    public void SMALL_ASYMMETRIC_RECTANGLE_CONTAINED_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -2, 3, -1, 2));
    }

    @Test
    public void CENTERED_RECTANGLE_STRICTLY_CONTAINED_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -3, 3, -3, 3));
    }

    @Test
    public void CONTAINED_RECTANGLE_RIGHT_OF_CENTER_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 3, -1, 1));
    }

    @Test
    public void CONTAINED_RECTANGLE_LEFT_OF_CENTER_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -3, -1, -1, 1));
    }

    @Test
    public void CONTAINED_RECTANGLE_ABOVE_CENTER_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -1, 1, 1, 3));
    }

    @Test
    public void CONTAINED_RECTANGLE_BELOW_CENTER_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -1, 1, -3, -1));
    }

    @Test
    public void CONTAINED_RECTANGLE_DIAGONAL_FROM_CENTER_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 3, 1, 3));
    }

    @Test
    public void CENTERED_RECTANGLE_CORNERS_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -4, 4, -4, 4));
    }

    @Test
    public void RIGHT_OF_CENTER_FAR_CORNER_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 2, 5, -3, 3));
    }

    @Test
    public void LEFT_OF_CENTER_FAR_CORNER_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, -2, -3, 3));
    }

    @Test
    public void ABOVE_CENTER_FAR_CORNER_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -3, 3, 2, 5));
    }

    @Test
    public void BELOW_CENTER_FAR_CORNER_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -3, 3, -5, -2));
    }

    @Test
    public void DIAGONAL_NEAR_INSIDE_FAR_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 4, 1, 4));
    }

    @Test
    public void POINT_RECTANGLE_AT_CENTER_variation1() {
        assertMetamorphicRelationFor(source(3, -2, 5, 3, 3, -2, -2));
    }

    @Test
    public void POINT_RECTANGLE_STRICTLY_INSIDE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 1, 2, 2));
    }

    @Test
    public void POINT_RECTANGLE_ON_CIRCLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 3, 4, 4));
    }

    @Test
    public void POINT_RECTANGLE_IN_BBOX_CORNER_GAP_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 4, 4, 4, 4));
    }

    @Test
    public void VERTICAL_SEGMENT_CONTAINED_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 1, 1, -3, 3));
    }

    @Test
    public void HORIZONTAL_SEGMENT_CONTAINED_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -3, 3, 1, 1));
    }

    @Test
    public void VERTICAL_SEGMENT_CROSSES_CIRCLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 0, 0, -7, 7));
    }

    @Test
    public void HORIZONTAL_SEGMENT_CROSSES_CIRCLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -7, 7, 0, 0));
    }

    @Test
    public void VERTICAL_SEGMENT_TANGENT_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 5, 5, -2, 2));
    }

    @Test
    public void DEGENERATE_SEGMENT_OUTSIDE_BBOX_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 6, 6, -3, 4));
    }

    @Test
    public void ZERO_RADIUS_RECTANGLE_STRICTLY_SURROUNDS_CENTER_variation1() {
        assertMetamorphicRelationFor(source(2, 3, 0, 0, 5, 1, 6));
    }

    @Test
    public void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(-2, 4, 0, -2, -2, 4, 4));
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 0, 2, 5, -1, 1));
    }

    @Test
    public void POSITIVE_AREA_RECTANGLE_FAR_CORNER_TANGENT_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -1, 3, -1, 4));
    }

    @Test
    public void LOWER_RIGHT_EXTERNAL_CORNER_TANGENCY_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 6, -7, -4));
    }

    @Test
    public void SYMMETRIC_FARTHEST_Y_TIE_CONTAINED_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -2, 1, -4, 4));
    }

    @Test
    public void SYMMETRIC_FARTHEST_X_TIE_INTERSECTS_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -4, 4, -7, 3));
    }

    @Test
    public void CIRCLE_BBOX_WITHIN_RECTANGLE_SHARED_SINGLE_SIDE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, 7, -6, 8));
    }

    @Test
    public void CIRCLE_BBOX_WITHIN_RECTANGLE_SHARED_X_EXTENT_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, -5, 5, -7, 8));
    }

    @Test
    public void AXIS_TANGENT_POINT_WITH_OUTWARD_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 5, 8, -2, 2));
    }

    @Test
    public void RECTANGLE_TOUCHES_BBOX_BUT_MISSES_CIRCLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 5, 7, 5, 7));
    }

    @Test
    public void RECTANGLE_SHARES_BBOX_EDGE_AND_CUTS_CIRCLE_variation1() {
        assertMetamorphicRelationFor(source(0, 0, 5, 3, 5, -1, 2));
    }

    @Test
    public void THIN_POSITIVE_AREA_RECTANGLE_CONTAINED_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, 2, 2.015625, 1, 1.015625));
    }

    @Test
    public void THIN_POSITIVE_AREA_RECTANGLE_STRADDLES_BOUNDARY_variation1() {
        assertMetamorphicRelationFor(
                source(0, 0, 5, 4.984375, 5.015625, -0.015625, 0.015625));
    }
}
