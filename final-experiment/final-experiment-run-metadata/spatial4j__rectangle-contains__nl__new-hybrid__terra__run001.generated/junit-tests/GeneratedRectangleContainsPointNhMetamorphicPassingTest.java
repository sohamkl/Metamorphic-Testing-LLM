import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final double ROTATION_DEGREES = 150.0;

    private MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source(
            double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point point = new PointImpl(pointX, pointY, context);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rectangle, point);
    }

    private MtllmGeneratedRectangleImplRelateInvocationyms78s.Input generateFollowUp(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        RectangleImpl rectangle = source.receiver();
        Point point = source.arg0();
        SpatialContext context = new SpatialContext(true);
        RectangleImpl rotatedRectangle = new RectangleImpl(
                rotateLongitude(rectangle.getMinX()),
                rotateLongitude(rectangle.getMaxX()),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);
        Point rotatedPoint = new PointImpl(
                rotateLongitude(point.getX()),
                point.getY(),
                context);
        return new MtllmGeneratedRectangleImplRelateInvocationyms78s.Input(rotatedRectangle, rotatedPoint);
    }

    private double rotateLongitude(double longitude) {
        double rotated = longitude + ROTATION_DEGREES;
        while (rotated > 180.0) {
            rotated -= 360.0;
        }
        while (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelationFor(
            MtllmGeneratedRectangleImplRelateInvocationyms78s.Input source) {
        SpatialRelation sourceOutput = MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(source);
        SpatialRelation followUpOutput =
                MtllmGeneratedRectangleImplRelateInvocationyms78s.invoke(generateFollowUp(source));
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertCase(double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        assertMetamorphicRelationFor(source(minX, maxX, minY, maxY, pointX, pointY));
    }

    @Test
    void ORDINARY_STRICT_INTERIOR_variation1() {
        assertCase(10.0, 20.0, -10.0, 10.0, 15.0, 0.0);
    }

    @Test
    void ORDINARY_MIN_X_INCLUSIVE_variation1() {
        assertCase(10.0, 20.0, -10.0, 10.0, 10.0, 0.0);
    }

    @Test
    void ORDINARY_MAX_X_INCLUSIVE_variation1() {
        assertCase(10.0, 20.0, -10.0, 10.0, 20.0, 0.0);
    }

    @Test
    void ORDINARY_MIN_Y_INCLUSIVE_variation1() {
        assertCase(10.0, 20.0, -10.0, 10.0, 15.0, -10.0);
    }

    @Test
    void ORDINARY_MAX_Y_INCLUSIVE_variation1() {
        assertCase(10.0, 20.0, -10.0, 10.0, 15.0, 10.0);
    }

    @Test
    void LATITUDE_BELOW_MINIMUM_variation1() {
        assertCase(10.0, 20.0, -10.0, 10.0, 15.0, -10.000001);
    }

    @Test
    void LATITUDE_ABOVE_MAXIMUM_variation1() {
        assertCase(10.0, 20.0, -10.0, 10.0, 15.0, 10.000001);
    }

    @Test
    void ORDINARY_LEFT_OF_MIN_SHIFTED_DISJOINT_variation1() {
        assertCase(10.0, 20.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void ORDINARY_RIGHT_OF_MAX_SHIFTED_DISJOINT_variation1() {
        assertCase(10.0, 20.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    void EAST_DATELINE_EQUIVALENT_LEFT_SHIFT_CONTAINS_variation1() {
        assertCase(170.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    void WEST_DATELINE_EQUIVALENT_RIGHT_SHIFT_CONTAINS_variation1() {
        assertCase(-180.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    void WRAPPED_DIRECT_EAST_SEGMENT_INTERIOR_variation1() {
        assertCase(170.0, -170.0, -10.0, 10.0, 175.0, 0.0);
    }

    @Test
    void WRAPPED_SHIFTED_WEST_SEGMENT_INTERIOR_variation1() {
        assertCase(170.0, -170.0, -10.0, 10.0, -175.0, 0.0);
    }

    @Test
    void WRAPPED_POSITIVE_DATELINE_POINT_variation1() {
        assertCase(170.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    void WRAPPED_NEGATIVE_DATELINE_POINT_variation1() {
        assertCase(170.0, -170.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    void WRAPPED_MIN_X_INCLUSIVE_variation1() {
        assertCase(170.0, -170.0, -10.0, 10.0, 170.0, 0.0);
    }

    @Test
    void WRAPPED_MAX_X_INCLUSIVE_AFTER_SHIFT_variation1() {
        assertCase(170.0, -170.0, -10.0, 10.0, -170.0, 0.0);
    }

    @Test
    void WRAPPED_CENTRAL_LONGITUDE_DISJOINT_variation1() {
        assertCase(170.0, -170.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void WRAPPED_LATITUDE_REJECTION_variation1() {
        assertCase(170.0, -170.0, -10.0, 10.0, -175.0, 10.000001);
    }

    @Test
    void ZERO_WIDTH_ORDINARY_MATCHING_POINT_variation1() {
        assertCase(25.0, 25.0, -10.0, 10.0, 25.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_ORDINARY_NONMATCHING_POINT_variation1() {
        assertCase(25.0, 25.0, -10.0, 10.0, 25.000001, 0.0);
    }

    @Test
    void ZERO_WIDTH_POSITIVE_DATELINE_WITH_NEGATIVE_POINT_variation1() {
        assertCase(180.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_NEGATIVE_DATELINE_WITH_POSITIVE_POINT_variation1() {
        assertCase(-180.0, -180.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    void ZERO_HEIGHT_MATCHING_LATITUDE_variation1() {
        assertCase(10.0, 20.0, 5.0, 5.0, 15.0, 5.0);
    }

    @Test
    void ZERO_HEIGHT_NONMATCHING_LATITUDE_variation1() {
        assertCase(10.0, 20.0, 5.0, 5.0, 15.0, 5.000001);
    }

    @Test
    void NEAR_WORLD_WIDTH_DIRECT_CONTAINS_variation1() {
        assertCase(-180.0, 179.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void NEAR_WORLD_WIDTH_DATELINE_EQUIVALENT_CONTAINS_variation1() {
        assertCase(-180.0, 179.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    void ORDINARY_BECOMES_WRAPPED_AFTER_ROTATION_variation1() {
        assertCase(20.0, 100.0, -20.0, 20.0, 50.0, 0.0);
    }

    @Test
    void WRAPPED_BECOMES_ORDINARY_AFTER_ROTATION_variation1() {
        assertCase(-170.0, -100.0, -20.0, 20.0, -150.0, 0.0);
    }

    @Test
    void SOUTH_POLE_LATITUDE_BOUNDARY_variation1() {
        assertCase(-20.0, 20.0, -90.0, -80.0, 0.0, -90.0);
    }

    @Test
    void NORTH_POLE_LATITUDE_BOUNDARY_variation1() {
        assertCase(-20.0, 20.0, 80.0, 90.0, 0.0, 90.0);
    }
}
