import org.junit.jupiter.api.Test;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final org.locationtech.spatial4j.context.SpatialContext GEO =
            new org.locationtech.spatial4j.context.SpatialContext(true);

    private static void verifyRotationInvariant(
            double minX,
            double maxX,
            double minY,
            double maxY,
            double pointX,
            double pointY) {

        org.locationtech.spatial4j.shape.impl.RectangleImpl sourceRectangle =
                new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                        minX, maxX, minY, maxY, GEO);
        org.locationtech.spatial4j.shape.Point sourcePoint =
                GEO.makePoint(pointX, pointY);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                sourceRectangle.relate(sourcePoint);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(
                        sourceRectangle, sourcePoint);
        org.locationtech.spatial4j.shape.impl.RectangleImpl followUpRectangle =
                (org.locationtech.spatial4j.shape.impl.RectangleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Point followUpPoint =
                (org.locationtech.spatial4j.shape.Point) followUp[1];

        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                followUpRectangle.relate(followUpPoint);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private static void assertMetamorphicRelation(
            org.locationtech.spatial4j.shape.SpatialRelation sourceOutput,
            org.locationtech.spatial4j.shape.SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Joint longitude rotation changed the point-in-rectangle relation: "
                            + sourceOutput + " -> " + followUpOutput);
        }
    }

    @Test
    public void test_INTERIOR_NONWRAP_BASELINE_variation1() {
        verifyRotationInvariant(-40.0, 40.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void test_LATITUDE_STRICTLY_ABOVE_variation1() {
        verifyRotationInvariant(-30.0, 30.0, -10.0, 10.0, 0.0, 20.0);
    }

    @Test
    public void test_LATITUDE_STRICTLY_BELOW_variation1() {
        verifyRotationInvariant(-30.0, 30.0, -10.0, 10.0, 0.0, -20.0);
    }

    @Test
    public void test_LATITUDE_AT_MINIMUM_variation1() {
        verifyRotationInvariant(-30.0, 30.0, -10.0, 10.0, 0.0, -10.0);
    }

    @Test
    public void test_LATITUDE_AT_MAXIMUM_variation1() {
        verifyRotationInvariant(-30.0, 30.0, -10.0, 10.0, 0.0, 10.0);
    }

    @Test
    public void test_ZERO_HEIGHT_ON_LINE_variation1() {
        verifyRotationInvariant(-30.0, 30.0, 5.0, 5.0, 0.0, 5.0);
    }

    @Test
    public void test_NORTH_POLE_BOUNDARY_variation1() {
        verifyRotationInvariant(-40.0, 40.0, 70.0, 90.0, 0.0, 90.0);
    }

    @Test
    public void test_SOUTH_POLE_BOUNDARY_variation1() {
        verifyRotationInvariant(-40.0, 40.0, -90.0, -70.0, 0.0, -90.0);
    }

    @Test
    public void test_NONWRAP_AT_MIN_LONGITUDE_variation1() {
        verifyRotationInvariant(-40.0, 40.0, -10.0, 10.0, -40.0, 0.0);
    }

    @Test
    public void test_NONWRAP_AT_MAX_LONGITUDE_variation1() {
        verifyRotationInvariant(-40.0, 40.0, -10.0, 10.0, 40.0, 0.0);
    }

    @Test
    public void test_NONWRAP_WEST_OUTSIDE_variation1() {
        verifyRotationInvariant(-40.0, 40.0, -10.0, 10.0, -50.0, 0.0);
    }

    @Test
    public void test_NONWRAP_EAST_OUTSIDE_variation1() {
        verifyRotationInvariant(-40.0, 40.0, -10.0, 10.0, 50.0, 0.0);
    }

    @Test
    public void test_ZERO_WIDTH_POINT_ON_LINE_variation1() {
        verifyRotationInvariant(25.0, 25.0, -10.0, 10.0, 25.0, 0.0);
    }

    @Test
    public void test_ZERO_WIDTH_POINT_WEST_variation1() {
        verifyRotationInvariant(25.0, 25.0, -10.0, 10.0, 20.0, 0.0);
    }

    @Test
    public void test_ZERO_WIDTH_POINT_EAST_variation1() {
        verifyRotationInvariant(25.0, 25.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void test_POSITIVE_ANTIMERIDIAN_POINT_MATCHES_NEGATIVE_EDGE_variation1() {
        verifyRotationInvariant(-180.0, -100.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void test_NEGATIVE_ANTIMERIDIAN_POINT_MATCHES_POSITIVE_EDGE_variation1() {
        verifyRotationInvariant(100.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void test_NONWRAP_ROTATES_TO_WRAP_POSITIVE_LOBE_variation1() {
        verifyRotationInvariant(-20.0, 60.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void test_NONWRAP_ROTATES_TO_WRAP_NEGATIVE_LOBE_variation1() {
        verifyRotationInvariant(-20.0, 60.0, -10.0, 10.0, 50.0, 0.0);
    }

    @Test
    public void test_NONWRAP_ROTATES_TO_WRAP_OUTSIDE_GAP_variation1() {
        verifyRotationInvariant(-20.0, 60.0, -10.0, 10.0, 100.0, 0.0);
    }

    @Test
    public void test_NONWRAP_ROTATES_WITHOUT_NORMALIZATION_variation1() {
        verifyRotationInvariant(-100.0, -50.0, -10.0, 10.0, -75.0, 0.0);
    }

    @Test
    public void test_NONWRAP_BOTH_ENDPOINTS_NORMALIZE_variation1() {
        verifyRotationInvariant(40.0, 80.0, -10.0, 10.0, 60.0, 0.0);
    }

    @Test
    public void test_ROTATED_ENDPOINT_EXACTLY_POSITIVE_180_variation1() {
        verifyRotationInvariant(0.0, 30.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void test_ROTATED_ENDPOINT_JUST_BEYOND_WRAP_THRESHOLD_variation1() {
        verifyRotationInvariant(0.0, 31.0, -10.0, 10.0, 31.0, 0.0);
    }

    @Test
    public void test_WRAPPED_POSITIVE_LOBE_INTERIOR_variation1() {
        verifyRotationInvariant(140.0, -140.0, -10.0, 10.0, 160.0, 0.0);
    }

    @Test
    public void test_WRAPPED_NEGATIVE_LOBE_INTERIOR_variation1() {
        verifyRotationInvariant(140.0, -140.0, -10.0, 10.0, -160.0, 0.0);
    }

    @Test
    public void test_WRAPPED_LONGITUDE_GAP_variation1() {
        verifyRotationInvariant(140.0, -140.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void test_WRAPPED_AT_MIN_LONGITUDE_variation1() {
        verifyRotationInvariant(140.0, -140.0, -10.0, 10.0, 140.0, 0.0);
    }

    @Test
    public void test_WRAPPED_AT_MAX_LONGITUDE_variation1() {
        verifyRotationInvariant(140.0, -140.0, -10.0, 10.0, -140.0, 0.0);
    }

    @Test
    public void test_NARROW_WRAP_ROTATES_TO_NONWRAP_variation1() {
        verifyRotationInvariant(170.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void test_WRAP_REMAINS_WRAP_WITH_POSITIVE_ENDPOINTS_variation1() {
        verifyRotationInvariant(20.0, -20.0, -10.0, 10.0, 170.0, 0.0);
    }

    @Test
    public void test_WRAP_REMAINS_WRAP_AFTER_BOTH_ENDPOINTS_NORMALIZE_variation1() {
        verifyRotationInvariant(100.0, 50.0, -10.0, 10.0, 120.0, 0.0);
    }

    @Test
    public void test_WIDE_WRAP_NEGATIVE_LOBE_variation1() {
        verifyRotationInvariant(100.0, 50.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void test_WIDE_WRAP_EXCLUDED_GAP_variation1() {
        verifyRotationInvariant(100.0, 50.0, -10.0, 10.0, 75.0, 0.0);
    }

    @Test
    public void test_WRAPPED_POINT_AT_POSITIVE_180_variation1() {
        verifyRotationInvariant(140.0, -140.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void test_WRAPPED_POINT_AT_NEGATIVE_180_variation1() {
        verifyRotationInvariant(140.0, -140.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void test_ANTIMERIDIAN_ALIAS_ZERO_WIDTH_POSITIVE_variation1() {
        verifyRotationInvariant(180.0, -180.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void test_ANTIMERIDIAN_ALIAS_ZERO_WIDTH_NEGATIVE_variation1() {
        verifyRotationInvariant(180.0, -180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void test_ANTIMERIDIAN_ALIAS_ZERO_WIDTH_OFF_LINE_variation1() {
        verifyRotationInvariant(180.0, -180.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void test_FULL_LATITUDE_RANGE_LONGITUDE_OUTSIDE_variation1() {
        verifyRotationInvariant(-20.0, 20.0, -90.0, 90.0, 50.0, 0.0);
    }

    @Test
    public void test_LATITUDE_REJECTION_PRECEDES_WRAPPED_CONTAINMENT_variation1() {
        verifyRotationInvariant(140.0, -140.0, -10.0, 10.0, 160.0, 20.0);
    }

    @Test
    public void test_LATITUDE_REJECTION_PRECEDES_WRAPPED_GAP_variation1() {
        verifyRotationInvariant(140.0, -140.0, -10.0, 10.0, 0.0, -20.0);
    }

    @Test
    public void test_POINT_ROTATES_TO_POSITIVE_180_variation1() {
        verifyRotationInvariant(0.0, 60.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void test_SOURCE_EDGE_AT_POSITIVE_180_variation1() {
        verifyRotationInvariant(120.0, 180.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void test_EMPTY_RECTANGLE_SENTINEL_variation1() {
        verifyRotationInvariant(
                Double.NaN, Double.NaN, Double.NaN, Double.NaN, 0.0, 0.0);
    }

    @Test
    public void test_EMPTY_POINT_SENTINEL_variation1() {
        verifyRotationInvariant(
                -40.0, 40.0, -10.0, 10.0, Double.NaN, Double.NaN);
    }
}
