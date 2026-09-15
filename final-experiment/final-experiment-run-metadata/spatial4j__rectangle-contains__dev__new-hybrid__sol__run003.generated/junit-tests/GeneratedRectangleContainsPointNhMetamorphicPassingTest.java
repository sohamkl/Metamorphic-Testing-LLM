import org.junit.jupiter.api.Test;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final org.locationtech.spatial4j.context.SpatialContext GEO =
            new org.locationtech.spatial4j.context.SpatialContext(true);

    private static org.locationtech.spatial4j.shape.impl.RectangleImpl rectangle(
            double minX, double maxX, double minY, double maxY) {
        return new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                minX, maxX, minY, maxY, GEO);
    }

    private static org.locationtech.spatial4j.shape.Point point(double x, double y) {
        return GEO.makePoint(x, y);
    }

    private static void verify(
            org.locationtech.spatial4j.shape.impl.RectangleImpl sourceRectangle,
            org.locationtech.spatial4j.shape.Point sourcePoint) {

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.class
                        .cast(sourceRectangle)
                        .relate(sourcePoint);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(
                        sourceRectangle, sourcePoint);

        org.locationtech.spatial4j.shape.impl.RectangleImpl followUpRectangle =
                (org.locationtech.spatial4j.shape.impl.RectangleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Point followUpPoint =
                (org.locationtech.spatial4j.shape.Point) followUp[1];

        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl.class
                        .cast(followUpRectangle)
                        .relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    void LATITUDE_ABOVE_MAXIMUM_variation1() {
        verify(rectangle(-20, 20, -10, 10), point(0, 11));
    }

    @Test
    void LATITUDE_BELOW_MINIMUM_variation1() {
        verify(rectangle(-20, 20, -10, 10), point(0, -11));
    }

    @Test
    void LATITUDE_AT_MAXIMUM_variation1() {
        verify(rectangle(-20, 20, -10, 10), point(0, 10));
    }

    @Test
    void LATITUDE_AT_MINIMUM_variation1() {
        verify(rectangle(-20, 20, -10, 10), point(0, -10));
    }

    @Test
    void ZERO_HEIGHT_MATCHING_LATITUDE_variation1() {
        verify(rectangle(-20, 20, 5, 5), point(0, 5));
    }

    @Test
    void ZERO_HEIGHT_NONMATCHING_LATITUDE_variation1() {
        verify(rectangle(-20, 20, 5, 5), point(0, 4));
    }

    @Test
    void NORTH_POLE_BOUNDARY_variation1() {
        verify(rectangle(-20, 20, 80, 90), point(0, 90));
    }

    @Test
    void SOUTH_POLE_BOUNDARY_variation1() {
        verify(rectangle(-20, 20, -90, -80), point(0, -90));
    }

    @Test
    void LATITUDE_REJECTION_PRECEDES_LONGITUDE_variation1() {
        verify(rectangle(-20, 20, -10, 10), point(100, 20));
    }

    @Test
    void NONWRAP_LONGITUDE_INTERIOR_variation1() {
        verify(rectangle(-20, 20, -10, 10), point(0, 0));
    }

    @Test
    void NONWRAP_MINIMUM_LONGITUDE_variation1() {
        verify(rectangle(-20, 20, -10, 10), point(-20, 0));
    }

    @Test
    void NONWRAP_MAXIMUM_LONGITUDE_variation1() {
        verify(rectangle(-20, 20, -10, 10), point(20, 0));
    }

    @Test
    void NONWRAP_WEST_OUTSIDE_variation1() {
        verify(rectangle(-20, 20, -10, 10), point(-21, 0));
    }

    @Test
    void NONWRAP_EAST_OUTSIDE_variation1() {
        verify(rectangle(-20, 20, -10, 10), point(21, 0));
    }

    @Test
    void ZERO_WIDTH_MATCHING_LONGITUDE_variation1() {
        verify(rectangle(30, 30, -10, 10), point(30, 0));
    }

    @Test
    void ZERO_WIDTH_NONMATCHING_LONGITUDE_variation1() {
        verify(rectangle(30, 30, -10, 10), point(31, 0));
    }

    @Test
    void NEGATIVE_ANTIMERIDIAN_ACCEPTS_POSITIVE_ALIAS_variation1() {
        verify(rectangle(-180, -170, -10, 10), point(180, 0));
    }

    @Test
    void POSITIVE_ANTIMERIDIAN_ACCEPTS_NEGATIVE_ALIAS_variation1() {
        verify(rectangle(170, 180, -10, 10), point(-180, 0));
    }

    @Test
    void NEGATIVE_SEAM_JUST_OUTSIDE_variation1() {
        verify(rectangle(-179, -170, -10, 10), point(180, 0));
    }

    @Test
    void POSITIVE_SEAM_JUST_OUTSIDE_variation1() {
        verify(rectangle(170, 179, -10, 10), point(-180, 0));
    }

    @Test
    void WRAP_POSITIVE_LONGITUDE_LOBE_variation1() {
        verify(rectangle(170, -170, -10, 10), point(175, 0));
    }

    @Test
    void WRAP_NEGATIVE_LONGITUDE_LOBE_variation1() {
        verify(rectangle(170, -170, -10, 10), point(-175, 0));
    }

    @Test
    void WRAP_MINIMUM_LONGITUDE_variation1() {
        verify(rectangle(170, -170, -10, 10), point(170, 0));
    }

    @Test
    void WRAP_MAXIMUM_LONGITUDE_variation1() {
        verify(rectangle(170, -170, -10, 10), point(-170, 0));
    }

    @Test
    void WRAP_CENTRAL_GAP_variation1() {
        verify(rectangle(170, -170, -10, 10), point(0, 0));
    }

    @Test
    void WRAP_JUST_BELOW_MINIMUM_variation1() {
        verify(rectangle(170, -170, -10, 10), point(169, 0));
    }

    @Test
    void WRAP_JUST_ABOVE_MAXIMUM_variation1() {
        verify(rectangle(170, -170, -10, 10), point(-169, 0));
    }

    @Test
    void WIDE_WRAP_POSITIVE_INTERIOR_variation1() {
        verify(rectangle(10, -10, -10, 10), point(100, 0));
    }

    @Test
    void WIDE_WRAP_NEGATIVE_INTERIOR_variation1() {
        verify(rectangle(10, -10, -10, 10), point(-100, 0));
    }

    @Test
    void WIDE_WRAP_SMALL_GAP_variation1() {
        verify(rectangle(10, -10, -10, 10), point(0, 0));
    }

    @Test
    void VERTICAL_POSITIVE_ANTIMERIDIAN_ALIAS_variation1() {
        verify(rectangle(180, 180, -10, 10), point(-180, 0));
    }

    @Test
    void VERTICAL_NEGATIVE_ANTIMERIDIAN_ALIAS_variation1() {
        verify(rectangle(-180, -180, -10, 10), point(180, 0));
    }

    @Test
    void ROTATION_NONWRAP_TO_WRAP_CONTAINS_variation1() {
        verify(rectangle(20, 60, -10, 10), point(40, 0));
    }

    @Test
    void ROTATION_NONWRAP_TO_WRAP_DISJOINT_variation1() {
        verify(rectangle(20, 60, -10, 10), point(0, 0));
    }

    @Test
    void ROTATION_WRAP_TO_NONWRAP_CONTAINS_variation1() {
        verify(rectangle(100, -100, -10, 10), point(150, 0));
    }

    @Test
    void ROTATION_WRAP_TO_NONWRAP_DISJOINT_variation1() {
        verify(rectangle(100, -100, -10, 10), point(0, 0));
    }

    @Test
    void ROTATED_ENDPOINT_AT_ANTIMERIDIAN_variation1() {
        verify(rectangle(30, 60, -10, 10), point(30, 0));
    }

    @Test
    void ROTATION_REMAINS_NONWRAPPING_variation1() {
        verify(rectangle(-100, -50, -10, 10), point(-75, 0));
    }

    @Test
    void ROTATION_REMAINS_WRAPPING_variation1() {
        verify(rectangle(-50, -100, -10, 10), point(0, 0));
    }

    @Test
    void NEAR_WORLD_WIDTH_INTERIOR_variation1() {
        verify(rectangle(-179, 180, -10, 10), point(0, 0));
    }

    @Test
    void NEAR_WORLD_WIDTH_NARROW_GAP_variation1() {
        verify(rectangle(-179, 180, -10, 10), point(-179.5, 0));
    }

    @Test
    void FULL_LATITUDE_WITH_LONGITUDE_GAP_variation1() {
        verify(rectangle(170, -170, -90, 90), point(0, 90));
    }

    @Test
    void CANONICAL_EMPTY_RECTANGLE_variation1() {
        verify(
                rectangle(
                        Double.NaN, Double.NaN, Double.NaN, Double.NaN),
                point(0, 0));
    }

    @Test
    void CANONICAL_EMPTY_POINT_variation1() {
        verify(
                rectangle(-20, 20, -10, 10),
                point(Double.NaN, Double.NaN));
    }

    @Test
    void BOTH_CANONICAL_EMPTY_SENTINELS_variation1() {
        verify(
                rectangle(
                        Double.NaN, Double.NaN, Double.NaN, Double.NaN),
                point(Double.NaN, Double.NaN));
    }

    @Test
    void SIGNED_ZERO_POINT_RECTANGLE_variation1() {
        verify(rectangle(-0.0d, +0.0d, -0.0d, +0.0d), point(+0.0d, -0.0d));
    }

    @Test
    void FRACTIONAL_MAXIMUM_BOUNDARY_variation1() {
        verify(rectangle(0.1d, 0.3d, -0.2d, 0.2d), point(0.3d, 0.0d));
    }

    @Test
    void FRACTIONAL_JUST_OUTSIDE_MAXIMUM_variation1() {
        verify(
                rectangle(0.1d, 0.3d, -0.2d, 0.2d),
                point(0.300001d, 0.0d));
    }

    @Test
    void NARROW_ANTIMERIDIAN_SLIVER_CONTAINS_variation1() {
        verify(
                rectangle(179.999d, -179.999d, -1.0d, 1.0d),
                point(180.0d, 0.0d));
    }

    @Test
    void NARROW_ANTIMERIDIAN_SLIVER_GAP_variation1() {
        verify(
                rectangle(179.999d, -179.999d, -1.0d, 1.0d),
                point(0.0d, 0.0d));
    }
}
