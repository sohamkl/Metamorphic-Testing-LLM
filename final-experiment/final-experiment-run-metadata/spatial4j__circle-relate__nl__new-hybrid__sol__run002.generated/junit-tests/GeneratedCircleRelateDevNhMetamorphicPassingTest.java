import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final SpatialContext CARTESIAN_CONTEXT = new SpatialContext(false);

    private static final class Fixture {
        private final MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source;
        private final double dx;
        private final double dy;

        private Fixture(
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source,
                double dx,
                double dy) {
            this.source = source;
            this.dx = dx;
            this.dy = dy;
        }
    }

    private Fixture fixture(
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
                new PointImpl(cx, cy, CARTESIAN_CONTEXT),
                radius,
                CARTESIAN_CONTEXT);

        Rectangle rectangle = new RectangleImpl(
                minX, maxX, minY, maxY, CARTESIAN_CONTEXT);

        return new Fixture(
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(circle, rectangle),
                dx,
                dy);
    }

    private MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input generateFollowUp(
            Fixture fixture) {

        CircleImpl sourceCircle = fixture.source.receiver();
        Rectangle sourceRectangle = fixture.source.arg0();

        double translatedCenterX = sourceCircle.getCenter().getX() + fixture.dx;
        double translatedCenterY = sourceCircle.getCenter().getY() + fixture.dy;

        CircleImpl translatedCircle = new CircleImpl(
                new PointImpl(
                        translatedCenterX,
                        translatedCenterY,
                        CARTESIAN_CONTEXT),
                sourceCircle.getRadius(),
                CARTESIAN_CONTEXT);

        Rectangle translatedRectangle = new RectangleImpl(
                sourceRectangle.getMinX() + fixture.dx,
                sourceRectangle.getMaxX() + fixture.dx,
                sourceRectangle.getMinY() + fixture.dy,
                sourceRectangle.getMaxY() + fixture.dy,
                CARTESIAN_CONTEXT);

        Assertions.assertEquals(
                sourceRectangle.getWidth(),
                translatedRectangle.getWidth(),
                0.0);
        Assertions.assertEquals(
                sourceRectangle.getHeight(),
                translatedRectangle.getHeight(),
                0.0);

        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle,
                translatedRectangle);
    }

    private void assertMetamorphicRelationFor(Fixture fixture) {
        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(fixture.source);

        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input followUp =
                generateFollowUp(fixture);

        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(followUp);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    public void BOUNDING_BOX_STRICTLY_DISJOINT_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-20, -20, 3, -10, -8, -12, -9, 8, 0));
    }

    @Test
    public void CIRCLE_STRICTLY_WITHIN_RECTANGLE_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 2, -5, 5, -6, 6, 0, 9));
    }

    @Test
    public void CIRCLE_WITHIN_TOUCHING_ONE_RECTANGLE_SIDE_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(8, 7, 2, 6, 13, 3, 11, -3, 5));
    }

    @Test
    public void CIRCLE_WITHIN_TOUCHING_RECTANGLE_CORNER_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-3, 4, 2, -5, 2, 2, 9, 11, 0));
    }

    @Test
    public void RECTANGLE_EQUALS_CIRCLE_BOUNDING_BOX_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-8, -6, 3, -11, -5, -9, -3, 0, 7));
    }

    @Test
    public void DIAGONAL_DISJOINT_NORTHEAST_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 5, 4, 7, 4, 8, 3, -4));
    }

    @Test
    public void DIAGONAL_DISJOINT_NORTHWEST_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(10, 10, 5, 3, 6, 14, 18, -6, 0));
    }

    @Test
    public void DIAGONAL_DISJOINT_SOUTHEAST_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-5, 5, 5, -1, 3, -3, 1, 0, -8));
    }

    @Test
    public void DIAGONAL_DISJOINT_SOUTHWEST_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-10, -10, 5, -18, -14, -18, -14, 5, 6));
    }

    @Test
    public void EXTERNAL_TANGENCY_RIGHT_SIDE_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 5, 5, 9, -2, 3, 12, 0));
    }

    @Test
    public void EXTERNAL_TANGENCY_LEFT_SIDE_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(8, 6, 5, -1, 3, 3, 9, 0, -7));
    }

    @Test
    public void EXTERNAL_TANGENCY_TOP_SIDE_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-4, 3, 5, -7, 0, 8, 12, 6, -5));
    }

    @Test
    public void EXTERNAL_TANGENCY_BOTTOM_SIDE_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-9, -4, 5, -12, -6, -12, -9, 10, 0));
    }

    @Test
    public void PENETRATING_FROM_RIGHT_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 5, 2, 8, -3, 3, 0, 11));
    }

    @Test
    public void PENETRATING_FROM_LEFT_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(10, 5, 5, 1, 8, 2, 9, -4, 6));
    }

    @Test
    public void PENETRATING_FROM_TOP_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-5, 2, 5, -8, -2, 5, 10, 9, 0));
    }

    @Test
    public void PENETRATING_FROM_BOTTOM_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-8, -3, 5, -11, -5, -11, -5, 0, 8));
    }

    @Test
    public void CORNER_TANGENCY_NORTHEAST_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 5, 3, 7, 4, 8, 4, -3));
    }

    @Test
    public void CORNER_TANGENCY_NORTHWEST_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(9, 1, 5, 2, 6, 5, 9, -8, 0));
    }

    @Test
    public void CORNER_TANGENCY_SOUTHEAST_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-2, 7, 5, 1, 5, -1, 3, 0, -9));
    }

    @Test
    public void CORNER_TANGENCY_SOUTHWEST_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-7, -6, 5, -14, -10, -14, -10, 5, 4));
    }

    @Test
    public void CENTERED_RECTANGLE_STRICTLY_CONTAINED_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 5, -2, 2, -2, 2, 13, 0));
    }

    @Test
    public void NORTHEAST_RECTANGLE_STRICTLY_CONTAINED_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(6, 4, 5, 7, 8, 5, 6, 0, -6));
    }

    @Test
    public void NORTHWEST_RECTANGLE_STRICTLY_CONTAINED_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-4, 5, 5, -6, -5, 6, 7, 7, -4));
    }

    @Test
    public void SOUTHEAST_RECTANGLE_STRICTLY_CONTAINED_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-6, -2, 5, -5, -4, -4, -3, 10, 0));
    }

    @Test
    public void SOUTHWEST_RECTANGLE_STRICTLY_CONTAINED_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(5, -5, 5, 3, 4, -7, -6, 0, 12));
    }

    @Test
    public void CONTAINED_RECTANGLE_STRADDLES_X_AXIS_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 5, 1, 2, -1, 2, -4, 7));
    }

    @Test
    public void CONTAINED_RECTANGLE_STRADDLES_Y_AXIS_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(7, -3, 5, 6, 9, -2, -1, -11, 0));
    }

    @Test
    public void FARTHEST_CORNER_ON_CIRCUMFERENCE_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-3, 6, 5, -3, 0, 6, 10, 0, -8));
    }

    @Test
    public void CENTERED_RECTANGLE_CORNERS_OUTSIDE_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 5, -4, 4, -4, 4, 6, 5));
    }

    @Test
    public void NORTHEAST_RECTANGLE_FARTHEST_CORNER_OUTSIDE_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(8, 8, 5, 9, 12, 9, 12, -7, 0));
    }

    @Test
    public void NORTHWEST_RECTANGLE_FARTHEST_CORNER_OUTSIDE_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-5, 4, 5, -9, -6, 5, 8, 0, 9));
    }

    @Test
    public void SOUTHEAST_RECTANGLE_FARTHEST_CORNER_OUTSIDE_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-7, -4, 5, -6, -3, -8, -5, 5, 7));
    }

    @Test
    public void SOUTHWEST_RECTANGLE_FARTHEST_CORNER_OUTSIDE_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(6, -6, 5, 2, 5, -10, -7, 8, 0));
    }

    @Test
    public void WIDE_AXIS_CROSSING_RECTANGLE_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 5, -7, 7, -1, 1, 0, -12));
    }

    @Test
    public void TALL_AXIS_CROSSING_RECTANGLE_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(5, 3, 5, 4, 6, -4, 10, -3, 6));
    }

    @Test
    public void DEGENERATE_POINT_AT_CENTER_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-8, -7, 5, -8, -8, -7, -7, 15, 0));
    }

    @Test
    public void DEGENERATE_POINT_ON_CIRCUMFERENCE_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 5, 5, 5, 0, 0, 0, 13));
    }

    @Test
    public void DEGENERATE_POINT_INSIDE_BOX_OUTSIDE_CIRCLE_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(7, 6, 5, 11, 11, 10, 10, -5, -4));
    }

    @Test
    public void DEGENERATE_CHORD_SEGMENT_CONTAINED_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-4, 3, 5, -7, -1, 3, 3, 9, 0));
    }

    @Test
    public void DEGENERATE_SECANT_SEGMENT_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(6, -5, 5, -1, 13, -5, -5, 0, 10));
    }

    @Test
    public void DEGENERATE_TANGENT_SEGMENT_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(0, 0, 5, 5, 5, -3, 4, 4, 6));
    }

    @Test
    public void ZERO_RADIUS_STRICTLY_INSIDE_RECTANGLE_variation1_horizontalTranslation() {
        assertMetamorphicRelationFor(
                fixture(8, 9, 0, 5, 12, 6, 13, -10, 0));
    }

    @Test
    public void ZERO_RADIUS_EQUALS_POINT_RECTANGLE_variation1_verticalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-6, 4, 0, -6, -6, 4, 4, 0, -7));
    }

    @Test
    public void ZERO_RADIUS_DISJOINT_FROM_RECTANGLE_variation1_diagonalTranslation() {
        assertMetamorphicRelationFor(
                fixture(-9, -8, 0, -5, -2, -4, -1, 6, 5));
    }
}
