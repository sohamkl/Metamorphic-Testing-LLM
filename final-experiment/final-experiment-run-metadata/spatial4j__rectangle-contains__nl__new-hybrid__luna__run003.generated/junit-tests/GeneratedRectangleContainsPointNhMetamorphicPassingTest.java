import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final SpatialContext GEO = new SpatialContext(true);
    private static final double ROTATION = 150.0;

    private static final class Source {
        final RectangleImpl rectangle;
        final Point point;

        Source(RectangleImpl rectangle, Point point) {
            this.rectangle = rectangle;
            this.point = point;
        }
    }

    private static Point point(double x, double y) {
        return new PointImpl(x, y, GEO);
    }

    private static RectangleImpl direct(double minX, double maxX,
                                        double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, GEO);
    }

    private static RectangleImpl endpointRectangle(double minX, double maxX,
                                                   double minY, double maxY) {
        return new RectangleImpl(point(minX, minY), point(maxX, maxY), GEO);
    }

    private static RectangleImpl copiedRectangle(double minX, double maxX,
                                                 double minY, double maxY) {
        Rectangle source = new RectangleImpl(minX, maxX, minY, maxY, GEO);
        return new RectangleImpl(source, GEO);
    }

    private static double rotate(double longitude) {
        double rotated = longitude + ROTATION;
        while (rotated > 180.0) {
            rotated -= 360.0;
        }
        while (rotated < -180.0) {
            rotated += 360.0;
        }
        return rotated;
    }

    private static Source generateFollowUp(Source source) {
        RectangleImpl rectangle = source.rectangle;
        RectangleImpl rotatedRectangle = direct(
                rotate(rectangle.getMinX()),
                rotate(rectangle.getMaxX()),
                rectangle.getMinY(),
                rectangle.getMaxY());
        Point rotatedPoint = point(
                rotate(source.point.getX()),
                source.point.getY());
        return new Source(rotatedRectangle, rotatedPoint);
    }

    private static void assertMetamorphicRelation(Source source) {
        SpatialRelation sourceOutput = source.rectangle.relate(source.point);
        Source followUp = generateFollowUp(source);
        SpatialRelation followUpOutput =
                followUp.rectangle.relate(followUp.point);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                                  SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    @Test
    void NONWRAPPING_INTERIOR_DIRECT_CONTAINS_variation1() {
        assertMetamorphicRelation(new Source(
                direct(-40, 60, -20, 30), point(10, 5)));
    }

    @Test
    void NONWRAPPING_MINIMUM_LONGITUDE_INCLUSIVE_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(-40, 60, -20, 30), point(-40, 5)));
    }

    @Test
    void NONWRAPPING_MAXIMUM_LONGITUDE_INCLUSIVE_variation1() {
        assertMetamorphicRelation(new Source(
                copiedRectangle(-40, 60, -20, 30), point(60, 5)));
    }

    @Test
    void NONWRAPPING_LONGITUDE_BELOW_MIN_DISJOINT_variation1() {
        assertMetamorphicRelation(new Source(
                direct(-40, 60, -20, 30), point(-100, 5)));
    }

    @Test
    void NONWRAPPING_LONGITUDE_ABOVE_MAX_DISJOINT_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(-40, 60, -20, 30), point(100, 5)));
    }

    @Test
    void LATITUDE_BELOW_MIN_EARLY_DISJOINT_variation1() {
        assertMetamorphicRelation(new Source(
                copiedRectangle(-40, 60, -20, 30), point(10, -20.000001)));
    }

    @Test
    void LATITUDE_AT_MIN_INCLUSIVE_variation1() {
        assertMetamorphicRelation(new Source(
                direct(-40, 60, -20, 30), point(10, -20)));
    }

    @Test
    void LATITUDE_AT_MAX_INCLUSIVE_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(-40, 60, -20, 30), point(10, 30)));
    }

    @Test
    void LATITUDE_ABOVE_MAX_EARLY_DISJOINT_variation1() {
        assertMetamorphicRelation(new Source(
                copiedRectangle(-40, 60, -20, 30), point(10, 30.000001)));
    }

    @Test
    void DATELINE_RECTANGLE_DIRECT_INTERIOR_variation1() {
        assertMetamorphicRelation(new Source(
                direct(170, -170, -20, 30), point(175, 5)));
    }

    @Test
    void DATELINE_RECTANGLE_POINT_BELOW_MIN_WRAP_CONTAINS_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(170, -170, -20, 30), point(-175, 5)));
    }

    @Test
    void DATELINE_RECTANGLE_LONGITUDE_GAP_DISJOINT_variation1() {
        assertMetamorphicRelation(new Source(
                copiedRectangle(170, -170, -20, 30), point(0, 5)));
    }

    @Test
    void DATELINE_RECTANGLE_POINT_ABOVE_MAX_WRAP_CONTAINS_variation1() {
        assertMetamorphicRelation(new Source(
                direct(-190, -170, -20, 30), point(175, 5)));
    }

    @Test
    void DATELINE_ENDPOINT_MIN180_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(170, -180, -10, 10), point(-180, 0)));
    }

    @Test
    void DATELINE_ENDPOINT_MAX180_variation1() {
        assertMetamorphicRelation(new Source(
                copiedRectangle(180, -170, -10, 10), point(180, 0)));
    }

    @Test
    void ZERO_WIDTH_LONGITUDE_EQUAL_variation1() {
        assertMetamorphicRelation(new Source(
                direct(25, 25, -20, 30), point(25, 5)));
    }

    @Test
    void ZERO_WIDTH_LONGITUDE_DIFFERENT_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(25, 25, -20, 30), point(26, 5)));
    }

    @Test
    void ZERO_HEIGHT_LATITUDE_EQUAL_variation1() {
        assertMetamorphicRelation(new Source(
                copiedRectangle(-40, 60, 12, 12), point(10, 12)));
    }

    @Test
    void ZERO_HEIGHT_LATITUDE_DIFFERENT_variation1() {
        assertMetamorphicRelation(new Source(
                direct(-40, 60, 12, 12), point(10, 12.000001)));
    }

    @Test
    void ROTATION_CROSSES_POINT_DATELINE_variation1() {
        assertMetamorphicRelation(new Source(
                copiedRectangle(-20, 20, -30, 30), point(30, 0)));
    }

    @Test
    void ROTATION_CROSSES_RECEIVER_DATELINE_variation1() {
        assertMetamorphicRelation(new Source(
                direct(100, 160, -30, 30), point(130, 0)));
    }

    @Test
    void ROTATION_CROSSES_BOTH_RECEIVER_AND_POINT_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(150, 179, -30, 30), point(170, 0)));
    }

    @Test
    void ROTATED_DATELINE_CONTAINS_BOUNDARY_POINT_variation1() {
        assertMetamorphicRelation(new Source(
                copiedRectangle(140, -160, -30, 30), point(-160, 0)));
    }

    @Test
    void POINT_ENDPOINT_CONSTRUCTOR_CONTAINS_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(-30, 40, -10, 20), point(0, 0)));
    }

    @Test
    void RECTANGLE_COPY_CONSTRUCTOR_DATELINE_variation1() {
        Rectangle source = new RectangleImpl(175, -175, -15, 15, GEO);
        assertMetamorphicRelation(new Source(
                new RectangleImpl(source, GEO), point(-178, 0)));
    }

    @Test
    void NONCANONICAL_RECEIVER_PLUS360_SHIFT_variation1() {
        assertMetamorphicRelation(new Source(
                direct(170, 190, -20, 20), point(-175, 0)));
    }

    @Test
    void NONCANONICAL_RECEIVER_MINUS360_SHIFT_variation1() {
        assertMetamorphicRelation(new Source(
                direct(-190, -170, -20, 20), point(175, 0)));
    }

    @Test
    void ROTATION_PRESERVES_LATITUDE_REJECTION_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(160, -160, -10, 10), point(170, 10.000001)));
    }

    @Test
    void ROTATION_PRESERVES_LATITUDE_BOUNDARY_variation1() {
        assertMetamorphicRelation(new Source(
                copiedRectangle(160, -160, -10, 10), point(-170, 10)));
    }

    @Test
    void ROTATION_TO_CANONICAL_DATELINE_ENDPOINT_variation1() {
        assertMetamorphicRelation(new Source(
                direct(160, 180, -20, 20), point(30, 0)));
    }

    @Test
    void ROTATION_FROM_NEGATIVE_TO_POSITIVE_ENDPOINT_variation1() {
        assertMetamorphicRelation(new Source(
                endpointRectangle(-180, -150, -20, 20), point(-10, 0)));
    }
}
