import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static RectangleImpl rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, SpatialContext.GEO);
    }

    private static Point point(double x, double y) {
        return SpatialContext.GEO.makePoint(x, y);
    }

    private static void verifyMetamorphicRelation(
            RectangleImpl sourceRectangle, Point sourcePoint) {
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUpValues =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(
                        sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUpValues[0];
        Point followUpPoint = (Point) followUpValues[1];

        SpatialRelation followUpOutput =
                followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void test_LATITUDE_ABOVE_MAXIMUM_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20, 20, -10, 10);
        Point sourcePoint = point(0, 11);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_LATITUDE_BELOW_MINIMUM_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20, 20, -10, 10);
        Point sourcePoint = point(0, -11);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_POINT_ON_UPPER_LATITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20, 20, -10, 10);
        Point sourcePoint = point(0, 10);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_POINT_ON_LOWER_LATITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20, 20, -10, 10);
        Point sourcePoint = point(0, -10);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ZERO_HEIGHT_RECTANGLE_CONTAINS_POINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20, 20, 5, 5);
        Point sourcePoint = point(0, 5);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NONWRAP_INTERIOR_SHORT_CIRCUIT_variation1() {
        RectangleImpl sourceRectangle = rectangle(-10, 10, -5, 5);
        Point sourcePoint = point(0, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NONWRAP_MINIMUM_LONGITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(-10, 10, -5, 5);
        Point sourcePoint = point(-10, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NONWRAP_MAXIMUM_LONGITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(-10, 10, -5, 5);
        Point sourcePoint = point(10, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NONWRAP_WEST_DISJOINT_AFTER_SHIFT_variation1() {
        RectangleImpl sourceRectangle = rectangle(-10, 10, -5, 5);
        Point sourcePoint = point(-11, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NONWRAP_EAST_DISJOINT_AFTER_SHIFT_variation1() {
        RectangleImpl sourceRectangle = rectangle(-10, 10, -5, 5);
        Point sourcePoint = point(11, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_WEST_DATELINE_ALIAS_CONTAINED_AFTER_PLUS_360_variation1() {
        RectangleImpl sourceRectangle = rectangle(170, 180, -5, 5);
        Point sourcePoint = point(-180, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_EAST_DATELINE_ALIAS_CONTAINED_AFTER_MINUS_360_variation1() {
        RectangleImpl sourceRectangle = rectangle(-180, -170, -5, 5);
        Point sourcePoint = point(180, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ORDINARY_ZERO_WIDTH_RECTANGLE_CONTAINS_POINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(40, 40, -5, 5);
        Point sourcePoint = point(40, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ORDINARY_ZERO_WIDTH_RECTANGLE_REJECTS_POINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(40, 40, -5, 5);
        Point sourcePoint = point(41, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_POSITIVE_DATELINE_LINE_ACCEPTS_NEGATIVE_ALIAS_variation1() {
        RectangleImpl sourceRectangle = rectangle(180, 180, -5, 5);
        Point sourcePoint = point(-180, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NEGATIVE_DATELINE_LINE_ACCEPTS_POSITIVE_ALIAS_variation1() {
        RectangleImpl sourceRectangle = rectangle(-180, -180, -5, 5);
        Point sourcePoint = point(180, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ZERO_WIDTH_LINE_AT_ROTATION_SEAM_variation1() {
        RectangleImpl sourceRectangle = rectangle(30, 30, -5, 5);
        Point sourcePoint = point(30, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_WRAP_EASTERN_SEGMENT_INTERIOR_variation1() {
        RectangleImpl sourceRectangle = rectangle(170, -170, -5, 5);
        Point sourcePoint = point(175, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_WRAP_WESTERN_SEGMENT_INTERIOR_variation1() {
        RectangleImpl sourceRectangle = rectangle(170, -170, -5, 5);
        Point sourcePoint = point(-175, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_WRAP_CENTRAL_GAP_variation1() {
        RectangleImpl sourceRectangle = rectangle(170, -170, -5, 5);
        Point sourcePoint = point(0, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_WRAP_MINIMUM_LONGITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(170, -170, -5, 5);
        Point sourcePoint = point(170, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_WRAP_MAXIMUM_LONGITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(170, -170, -5, 5);
        Point sourcePoint = point(-170, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_WRAP_POSITIVE_DATELINE_POINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(170, -170, -5, 5);
        Point sourcePoint = point(180, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_WRAP_NEGATIVE_DATELINE_POINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(170, -170, -5, 5);
        Point sourcePoint = point(-180, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NARROW_WRAP_LARGE_GAP_variation1() {
        RectangleImpl sourceRectangle = rectangle(179, -179, -5, 5);
        Point sourcePoint = point(0, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NEAR_FULL_WRAP_CONTAINED_LONGITUDE_variation1() {
        RectangleImpl sourceRectangle = rectangle(0, -1, -5, 5);
        Point sourcePoint = point(90, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NEAR_FULL_WRAP_NARROW_GAP_variation1() {
        RectangleImpl sourceRectangle = rectangle(0, -1, -5, 5);
        Point sourcePoint = point(-0.5, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATION_CREATES_WRAP_CONTAINED_variation1() {
        RectangleImpl sourceRectangle = rectangle(0, 60, -5, 5);
        Point sourcePoint = point(20, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATION_CREATES_WRAP_DISJOINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(0, 60, -5, 5);
        Point sourcePoint = point(100, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATION_PRESERVES_NONWRAP_WITHOUT_ENDPOINT_NORMALIZATION_variation1() {
        RectangleImpl sourceRectangle = rectangle(-100, 0, -5, 5);
        Point sourcePoint = point(-50, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATION_PRESERVES_NONWRAP_AFTER_BOTH_ENDPOINTS_NORMALIZE_variation1() {
        RectangleImpl sourceRectangle = rectangle(50, 100, -5, 5);
        Point sourcePoint = point(75, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATION_REMOVES_WRAP_CONTAINED_variation1() {
        RectangleImpl sourceRectangle = rectangle(100, -100, -5, 5);
        Point sourcePoint = point(150, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATION_REMOVES_WRAP_DISJOINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(100, -100, -5, 5);
        Point sourcePoint = point(0, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATION_PRESERVES_WRAP_CONTAINED_variation1() {
        RectangleImpl sourceRectangle = rectangle(10, -100, -5, 5);
        Point sourcePoint = point(100, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATION_PRESERVES_WRAP_GAP_variation1() {
        RectangleImpl sourceRectangle = rectangle(10, -100, -5, 5);
        Point sourcePoint = point(-50, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_MAX_ENDPOINT_AT_ROTATION_NORMALIZATION_SEAM_variation1() {
        RectangleImpl sourceRectangle = rectangle(-10, 30, -5, 5);
        Point sourcePoint = point(30, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_MIN_ENDPOINT_AT_ROTATION_NORMALIZATION_SEAM_variation1() {
        RectangleImpl sourceRectangle = rectangle(30, 60, -5, 5);
        Point sourcePoint = point(30, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_INTERIOR_POINT_AT_ROTATION_NORMALIZATION_SEAM_variation1() {
        RectangleImpl sourceRectangle = rectangle(0, 60, -5, 5);
        Point sourcePoint = point(30, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NEGATIVE_DATELINE_MINIMUM_WITH_POSITIVE_ALIAS_variation1() {
        RectangleImpl sourceRectangle = rectangle(-180, 0, -5, 5);
        Point sourcePoint = point(180, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_POSITIVE_DATELINE_MAXIMUM_WITH_NEGATIVE_ALIAS_variation1() {
        RectangleImpl sourceRectangle = rectangle(0, 180, -5, 5);
        Point sourcePoint = point(-180, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_NEAR_FULL_NONWRAP_NARROW_GAP_variation1() {
        RectangleImpl sourceRectangle = rectangle(-180, 179, -5, 5);
        Point sourcePoint = point(179.5, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATED_NARROW_NONWRAP_GAP_variation1() {
        RectangleImpl sourceRectangle = rectangle(0, 60, -5, 5);
        Point sourcePoint = point(60.5, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATED_NARROW_WRAP_GAP_variation1() {
        RectangleImpl sourceRectangle = rectangle(20, 10, -5, 5);
        Point sourcePoint = point(15, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATED_WIDE_WRAP_INTERIOR_variation1() {
        RectangleImpl sourceRectangle = rectangle(20, 10, -5, 5);
        Point sourcePoint = point(-100, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_ROTATION_WITH_UPPER_LATITUDE_BOUNDARY_variation1() {
        RectangleImpl sourceRectangle = rectangle(0, 60, -10, 10);
        Point sourcePoint = point(20, 10);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_WRAPPING_ZERO_HEIGHT_RECTANGLE_variation1() {
        RectangleImpl sourceRectangle = rectangle(170, -170, 5, 5);
        Point sourcePoint = point(-175, 5);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_SOUTHWEST_CORNER_INCLUSIVE_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20, 20, -10, 10);
        Point sourcePoint = point(-20, -10);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_WRAPPING_RECTANGLE_LATITUDE_REJECTION_variation1() {
        RectangleImpl sourceRectangle = rectangle(170, -170, -5, 5);
        Point sourcePoint = point(-175, 6);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_CANONICAL_EMPTY_RECTANGLE_SENTINEL_variation1() {
        RectangleImpl sourceRectangle = rectangle(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN);
        Point sourcePoint = point(0, 0);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }

    @Test
    public void test_CANONICAL_EMPTY_POINT_SENTINEL_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20, 20, -10, 10);
        Point sourcePoint = point(Double.NaN, Double.NaN);
        verifyMetamorphicRelation(sourceRectangle, sourcePoint);
    }
}
