import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.CircleImpl;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedCircleRelateDevNhMetamorphicPassingTest {

    private static final class Fixture {
        final SpatialContext context;
        final MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source;
        final double dx;
        final double dy;

        Fixture(SpatialContext context,
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input source,
                double dx, double dy) {
            this.context = context;
            this.source = source;
            this.dx = dx;
            this.dy = dy;
        }
    }

    private static Fixture fixture(double cx, double cy, double radius,
                                   double minX, double maxX,
                                   double minY, double maxY,
                                   double dx, double dy) {
        SpatialContext context = new SpatialContext(false);
        CircleImpl circle = new CircleImpl(
                new PointImpl(cx, cy, context), radius, context);
        Rectangle rectangle = new RectangleImpl(
                minX, maxX, minY, maxY, context);
        MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input input =
                new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                        circle, rectangle);
        return new Fixture(context, input, dx, dy);
    }

    private static MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input generateFollowUp(
            Fixture fixture) {
        CircleImpl sourceCircle = fixture.source.receiver();
        Rectangle sourceRectangle = fixture.source.arg0();
        SpatialContext context = fixture.context;
        CircleImpl translatedCircle = new CircleImpl(
                new PointImpl(
                        sourceCircle.getCenter().getX() + fixture.dx,
                        sourceCircle.getCenter().getY() + fixture.dy,
                        context),
                sourceCircle.getRadius(), context);
        Rectangle translatedRectangle = new RectangleImpl(
                sourceRectangle.getMinX() + fixture.dx,
                sourceRectangle.getMaxX() + fixture.dx,
                sourceRectangle.getMinY() + fixture.dy,
                sourceRectangle.getMaxY() + fixture.dy,
                context);
        return new MtllmGeneratedCircleImplRelateInvocation1qssgw4.Input(
                translatedCircle, translatedRectangle);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelationFor(Fixture fixture) {
        SpatialRelation sourceOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(fixture.source);
        SpatialRelation followUpOutput =
                MtllmGeneratedCircleImplRelateInvocation1qssgw4.invoke(
                        generateFollowUp(fixture));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void BBOX_DISJOINT_EARLY_RETURN_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 2, 10, 14, 10, 14, 7, -5));
    }

    @Test
    void BBOX_DISJOINT_EARLY_RETURN_variation2() {
        assertMetamorphicRelationFor(fixture(20, 20, 0, -5, -5, -4, 4, -11, 8));
    }

    @Test
    void BBOX_DISJOINT_EARLY_RETURN_variation3() {
        assertMetamorphicRelationFor(fixture(0, 0, 3, 0, 0, 0, 0, 12, 13));
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_EARLY_RETURN_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 2, -5, 5, -4, 4, 6, -7));
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_EARLY_RETURN_variation2() {
        assertMetamorphicRelationFor(fixture(8, 8, 1, 0, 8, 0, 8, -13, 9));
    }

    @Test
    void BBOX_WITHIN_RECTANGLE_EARLY_RETURN_variation3() {
        assertMetamorphicRelationFor(fixture(0, 0, 0, -2, 2, -3, 3, 10, -10));
    }

    @Test
    void BBOX_EQUALS_RECTANGLE_IDENTITY_EDGE_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 2, -2, 2, -2, 2, 4, 5));
    }

    @Test
    void BBOX_EQUALS_RECTANGLE_IDENTITY_EDGE_variation2() {
        assertMetamorphicRelationFor(fixture(3, -1, 0, 3, 3, -1, -1, -8, 6));
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_AND_ALL_CORNERS_INSIDE_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 10, -3, 3, -2, 4, 9, -8));
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_AND_ALL_CORNERS_INSIDE_variation2() {
        assertMetamorphicRelationFor(fixture(5, 5, 4, 4, 6, 4, 6, -12, 11));
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_AND_ALL_CORNERS_INSIDE_variation3() {
        assertMetamorphicRelationFor(fixture(0, 0, 5, -1, 1, 0, 0, 14, 3));
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_PARTIAL_CIRCLE_OVERLAP_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 5, -5, 5, 1, 2, -6, 7));
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_PARTIAL_CIRCLE_OVERLAP_variation2() {
        assertMetamorphicRelationFor(fixture(0, 0, 3, -2, 4, -1, 3, 8, -9));
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_PARTIAL_CIRCLE_OVERLAP_variation3() {
        assertMetamorphicRelationFor(fixture(6, 6, 2, 5, 8, 5, 8, -15, 4));
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_BUT_NO_OVERLAP_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 5, 4, 5, 4, 5, 7, 7));
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_BUT_NO_OVERLAP_variation2() {
        assertMetamorphicRelationFor(fixture(0, 0, 2, 3, 4, 3, 4, -9, 10));
    }

    @Test
    void BBOX_CONTAINS_RECTANGLE_BUT_NO_OVERLAP_variation3() {
        assertMetamorphicRelationFor(fixture(10, 10, 3, 8, 8, 8, 8, 13, -12));
    }

    @Test
    void BBOX_INTERSECTS_WITH_CLOSEST_CORNER_INSIDE_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 5, 4, 7, -1, 1, -8, 9));
    }

    @Test
    void BBOX_INTERSECTS_WITH_CLOSEST_CORNER_INSIDE_variation2() {
        assertMetamorphicRelationFor(fixture(0, 0, 3, 2, 5, 2, 5, 11, -6));
    }

    @Test
    void BBOX_INTERSECTS_WITH_CLOSEST_CORNER_INSIDE_variation3() {
        assertMetamorphicRelationFor(fixture(5, 5, 2, 3, 7, 3, 7, -14, 12));
    }

    @Test
    void BBOX_INTERSECTS_WITH_CLOSEST_CORNER_OUTSIDE_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 5, 4, 7, 4, 7, 8, -11));
    }

    @Test
    void BBOX_INTERSECTS_WITH_CLOSEST_CORNER_OUTSIDE_variation2() {
        assertMetamorphicRelationFor(fixture(0, 0, 2, 2, 4, 2, 4, -7, 13));
    }

    @Test
    void BBOX_INTERSECTS_WITH_CLOSEST_CORNER_OUTSIDE_variation3() {
        assertMetamorphicRelationFor(fixture(10, 10, 4, 6, 8, 6, 8, 15, -14));
    }

    @Test
    void X_AXIS_LEFT_OF_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 5, 3, 6, -2, 4, 10, 5));
    }

    @Test
    void X_AXIS_LEFT_OF_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(fixture(-2, 1, 4, 0, 3, -1, 4, -9, -8));
    }

    @Test
    void X_AXIS_RIGHT_OF_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(fixture(8, 0, 5, 2, 5, -2, 2, 7, 6));
    }

    @Test
    void X_AXIS_RIGHT_OF_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(fixture(10, 2, 3, 0, 4, 0, 4, -12, 9));
    }

    @Test
    void Y_AXIS_BELOW_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(fixture(0, -6, 5, -3, 4, -2, 3, 13, 8));
    }

    @Test
    void Y_AXIS_BELOW_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(fixture(2, -5, 4, -1, 5, 0, 4, -8, -12));
    }

    @Test
    void Y_AXIS_ABOVE_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(fixture(0, 7, 5, -3, 4, -2, 3, 11, -9));
    }

    @Test
    void Y_AXIS_ABOVE_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(fixture(2, 8, 4, -1, 5, 0, 4, -10, 14));
    }

    @Test
    void BOTH_AXES_WITHIN_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(fixture(1, 2, 4, -3, 5, -4, 6, 9, -7));
    }

    @Test
    void BOTH_AXES_WITHIN_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(fixture(0, 0, 2, -4, 6, -3, 5, -11, 10));
    }

    @Test
    void BOTH_AXES_WITHIN_RECTANGLE_variation3() {
        assertMetamorphicRelationFor(fixture(3, -1, 0, 0, 7, -4, 2, 16, 13));
    }

    @Test
    void FARTHEST_SIDE_DISTANCE_TIE_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 3, -2, 2, -2, 2, 6, 7));
    }

    @Test
    void FARTHEST_SIDE_DISTANCE_TIE_variation2() {
        assertMetamorphicRelationFor(fixture(1, 1, 5, -3, 5, -4, 6, -8, 12));
    }

    @Test
    void FARTHEST_SIDE_DISTANCE_TIE_variation3() {
        assertMetamorphicRelationFor(fixture(0, 0, 1, -1, 1, -1, 1, 15, -15));
    }

    @Test
    void EXACT_CIRCLE_BOUNDARY_CONTACT_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 5, 5, 6, -1, 1, -9, 4));
    }

    @Test
    void EXACT_CIRCLE_BOUNDARY_CONTACT_variation2() {
        assertMetamorphicRelationFor(fixture(0, 0, 3, -3, 0, -1, 1, 8, -6));
    }

    @Test
    void EXACT_CIRCLE_BOUNDARY_CONTACT_variation3() {
        assertMetamorphicRelationFor(fixture(2, 2, 0, 2, 4, 2, 2, -7, 11));
    }

    @Test
    void ZERO_RADIUS_POINT_CIRCLE_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 0, 2, 4, 2, 4, 10, -9));
    }

    @Test
    void ZERO_RADIUS_POINT_CIRCLE_variation2() {
        assertMetamorphicRelationFor(fixture(1, 1, 0, 0, 2, 0, 2, -13, 8));
    }

    @Test
    void ZERO_WIDTH_OR_HEIGHT_RECTANGLE_variation1() {
        assertMetamorphicRelationFor(fixture(0, 0, 3, 2, 2, -2, 4, 7, -5));
    }

    @Test
    void ZERO_WIDTH_OR_HEIGHT_RECTANGLE_variation2() {
        assertMetamorphicRelationFor(fixture(0, 0, 3, -2, 4, 2, 2, -8, 12));
    }
}
