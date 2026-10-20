import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final class Fixture {
        final RectangleImpl rectangle;
        final Point point;

        Fixture(RectangleImpl rectangle, Point point) {
            this.rectangle = rectangle;
            this.point = point;
        }
    }

    private Fixture source(double minX, double maxX, double minY, double maxY,
                           double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        return new Fixture(
            new RectangleImpl(minX, maxX, minY, maxY, context),
            new PointImpl(pointX, pointY, context)
        );
    }

    private Fixture generateFollowUp(Fixture source) {
        SpatialContext context = new SpatialContext(true);
        return new Fixture(
            new RectangleImpl(
                rotateEast150(source.rectangle.getMinX()),
                rotateEast150(source.rectangle.getMaxX()),
                source.rectangle.getMinY(),
                source.rectangle.getMaxY(),
                context
            ),
            new PointImpl(
                rotateEast150(source.point.getX()),
                source.point.getY(),
                context
            )
        );
    }

    private double rotateEast150(double longitude) {
        double rotated = (longitude + 150.0) % 360.0;
        if (rotated < -180.0) {
            rotated += 360.0;
        }
        if (rotated >= 180.0) {
            rotated -= 360.0;
        }
        return rotated;
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput,
                                           SpatialRelation followUpOutput) {
        Assertions.assertEquals(sourceOutput, followUpOutput);
    }

    private void check(double minX, double maxX, double minY, double maxY,
                       double pointX, double pointY, SpatialRelation expectedSourceOutput) {
        Fixture source = source(minX, maxX, minY, maxY, pointX, pointY);
        Fixture followUp = generateFollowUp(source);
        SpatialRelation sourceOutput = source.rectangle.relate(source.point);
        SpatialRelation followUpOutput = followUp.rectangle.relate(followUp.point);
        Assertions.assertEquals(expectedSourceOutput, sourceOutput);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_NONWRAP_STRICT_INTERIOR_variation1() {
        check(-40, 40, -20, 20, 0, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NONWRAP_MIN_X_BOUNDARY_variation1() {
        check(-40, 40, -20, 20, -40, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NONWRAP_MAX_X_BOUNDARY_variation1() {
        check(-40, 40, -20, 20, 40, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NONWRAP_MIN_Y_BOUNDARY_variation1() {
        check(-40, 40, -20, 20, 0, -20, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NONWRAP_MAX_Y_BOUNDARY_variation1() {
        check(-40, 40, -20, 20, 0, 20, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NONWRAP_LOWER_LEFT_CORNER_variation1() {
        check(-40, 40, -20, 20, -40, -20, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NONWRAP_LOWER_RIGHT_CORNER_variation1() {
        check(-40, 40, -20, 20, 40, -20, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NONWRAP_UPPER_LEFT_CORNER_variation1() {
        check(-40, 40, -20, 20, -40, 20, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NONWRAP_UPPER_RIGHT_CORNER_variation1() {
        check(-40, 40, -20, 20, 40, 20, SpatialRelation.CONTAINS);
    }

    @Test
    void test_LATITUDE_ABOVE_MAX_variation1() {
        check(-40, 40, -20, 20, 0, 21, SpatialRelation.DISJOINT);
    }

    @Test
    void test_LATITUDE_BELOW_MIN_variation1() {
        check(-40, 40, -20, 20, 0, -21, SpatialRelation.DISJOINT);
    }

    @Test
    void test_NONWRAP_LEFT_OF_INTERVAL_SHIFTED_DISJOINT_variation1() {
        check(-40, 40, -20, 20, -41, 0, SpatialRelation.DISJOINT);
    }

    @Test
    void test_NONWRAP_RIGHT_OF_INTERVAL_SHIFTED_DISJOINT_variation1() {
        check(-40, 40, -20, 20, 41, 0, SpatialRelation.DISJOINT);
    }

    @Test
    void test_WESTERN_ALIAS_OF_EASTERN_EDGE_variation1() {
        check(-100, 180, -10, 10, -180, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_EASTERN_ALIAS_OF_WESTERN_EDGE_variation1() {
        check(-180, 100, -10, 10, 180, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_ZERO_WIDTH_VERTICAL_LINE_CONTAINS_variation1() {
        check(10, 10, -20, 20, 10, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_ZERO_WIDTH_VERTICAL_LINE_DISJOINT_variation1() {
        check(10, 10, -20, 20, 11, 0, SpatialRelation.DISJOINT);
    }

    @Test
    void test_ZERO_HEIGHT_HORIZONTAL_LINE_CONTAINS_variation1() {
        check(-20, 20, 5, 5, 0, 5, SpatialRelation.CONTAINS);
    }

    @Test
    void test_ZERO_HEIGHT_HORIZONTAL_LINE_DISJOINT_variation1() {
        check(-20, 20, 5, 5, 0, 6, SpatialRelation.DISJOINT);
    }

    @Test
    void test_ZERO_AREA_POINT_RECTANGLE_CONTAINS_variation1() {
        check(10, 10, 5, 5, 10, 5, SpatialRelation.CONTAINS);
    }

    @Test
    void test_SOUTH_POLE_LATITUDE_BOUNDARY_variation1() {
        check(-30, 30, -90, -80, 0, -90, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NORTH_POLE_LATITUDE_BOUNDARY_variation1() {
        check(-30, 30, 80, 90, 0, 90, SpatialRelation.CONTAINS);
    }

    @Test
    void test_FULL_LATITUDE_BAND_variation1() {
        check(-30, 30, -90, 90, 0, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NONWRAP_EASTERN_DATELINE_EDGE_variation1() {
        check(170, 180, -10, 10, 180, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NONWRAP_WESTERN_DATELINE_EDGE_variation1() {
        check(-180, -170, -10, 10, -180, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NARROW_WRAP_EASTERN_LOBE_INTERIOR_variation1() {
        check(170, -170, -10, 10, 175, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NARROW_WRAP_WESTERN_LOBE_INTERIOR_variation1() {
        check(170, -170, -10, 10, -175, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NARROW_WRAP_MIN_X_BOUNDARY_variation1() {
        check(170, -170, -10, 10, 170, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NARROW_WRAP_MAX_X_BOUNDARY_variation1() {
        check(170, -170, -10, 10, -170, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NARROW_WRAP_CENTRAL_GAP_variation1() {
        check(170, -170, -10, 10, 0, 0, SpatialRelation.DISJOINT);
    }

    @Test
    void test_NARROW_WRAP_LATITUDE_ABOVE_variation1() {
        check(170, -170, -10, 10, 175, 11, SpatialRelation.DISJOINT);
    }

    @Test
    void test_NARROW_WRAP_LATITUDE_BELOW_variation1() {
        check(170, -170, -10, 10, -175, -11, SpatialRelation.DISJOINT);
    }

    @Test
    void test_WIDE_WRAP_INTERIOR_variation1() {
        check(10, 0, -10, 10, -180, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_WIDE_WRAP_CENTRAL_GAP_variation1() {
        check(10, 0, -10, 10, 5, 0, SpatialRelation.DISJOINT);
    }

    @Test
    void test_WIDE_WRAP_MIN_X_BOUNDARY_variation1() {
        check(10, 0, -10, 10, 10, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_WIDE_WRAP_MAX_X_BOUNDARY_variation1() {
        check(10, 0, -10, 10, 0, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_DATELINE_ZERO_WIDTH_POSITIVE_REPRESENTATION_variation1() {
        check(180, -180, -10, 10, 180, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_DATELINE_ZERO_WIDTH_NEGATIVE_ALIAS_variation1() {
        check(180, -180, -10, 10, -180, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_DATELINE_ZERO_WIDTH_OFF_LINE_variation1() {
        check(180, -180, -10, 10, 179, 0, SpatialRelation.DISJOINT);
    }

    @Test
    void test_NONWRAP_TO_WRAP_AFTER_ROTATION_variation1() {
        check(20, 40, -10, 10, 30, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_WRAP_TO_NONWRAP_AFTER_ROTATION_variation1() {
        Fixture source = source(170, -170, -10, 10, -175, 0);
        Fixture followUp = generateFollowUp(source);
        Assertions.assertFalse(followUp.rectangle.getCrossesDateLine());
        SpatialRelation sourceOutput = source.rectangle.relate(source.point);
        SpatialRelation followUpOutput = followUp.rectangle.relate(followUp.point);
        Assertions.assertEquals(SpatialRelation.CONTAINS, sourceOutput);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    void test_ALMOST_FULL_NONWRAP_ALIAS_CONTAINS_variation1() {
        check(-180, 179, -10, 10, 180, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_NARROW_WRAP_NEAR_DATELINE_variation1() {
        check(179, -179, -10, 10, -180, 0, SpatialRelation.CONTAINS);
    }

    @Test
    void test_EXTREME_LEFT_SHIFTED_DISJOINT_variation1() {
        check(0, 100, -10, 10, -180, 0, SpatialRelation.DISJOINT);
    }

    @Test
    void test_EXTREME_RIGHT_SHIFTED_DISJOINT_variation1() {
        check(-100, 0, -10, 10, 180, 0, SpatialRelation.DISJOINT);
    }
}
