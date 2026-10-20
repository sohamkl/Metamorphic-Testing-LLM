import org.junit.jupiter.api.Test;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private void runCase(
            double minX,
            double maxX,
            double minY,
            double maxY,
            double pointX,
            double pointY) {

        org.locationtech.spatial4j.context.SpatialContext context =
                new org.locationtech.spatial4j.context.SpatialContext(true);

        org.locationtech.spatial4j.shape.impl.RectangleImpl sourceRectangle =
                new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                        minX, maxX, minY, maxY, context);

        org.locationtech.spatial4j.shape.Point sourcePoint =
                context.makePoint(pointX, pointY);

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

    private void assertMetamorphicRelation(
            org.locationtech.spatial4j.shape.SpatialRelation sourceOutput,
            org.locationtech.spatial4j.shape.SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Simultaneously rotating the rectangle and point changed their spatial relation");
        }
    }

    @Test
    public void LATITUDE_ABOVE_MAXIMUM_variation1() {
        runCase(-20.0, 20.0, -10.0, 10.0, 0.0, 10.000000001);
    }

    @Test
    public void LATITUDE_BELOW_MINIMUM_variation1() {
        runCase(-20.0, 20.0, -10.0, 10.0, 0.0, -10.000000001);
    }

    @Test
    public void LATITUDE_AT_MINIMUM_variation1() {
        runCase(-20.0, 20.0, -10.0, 10.0, 0.0, -10.0);
    }

    @Test
    public void LATITUDE_AT_MAXIMUM_variation1() {
        runCase(-20.0, 20.0, -10.0, 10.0, 0.0, 10.0);
    }

    @Test
    public void NORTH_POLE_INCLUDED_variation1() {
        runCase(-20.0, 20.0, 80.0, 90.0, 0.0, 90.0);
    }

    @Test
    public void SOUTH_POLE_INCLUDED_variation1() {
        runCase(-20.0, 20.0, -90.0, -80.0, 0.0, -90.0);
    }

    @Test
    public void ZERO_HEIGHT_EXACT_LATITUDE_variation1() {
        runCase(-20.0, 20.0, 5.0, 5.0, 0.0, 5.0);
    }

    @Test
    public void ZERO_HEIGHT_LATITUDE_MISS_variation1() {
        runCase(-20.0, 20.0, 5.0, 5.0, 0.0, 5.000000001);
    }

    @Test
    public void NON_WRAP_STRICT_INTERIOR_variation1() {
        runCase(-40.0, 20.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void NON_WRAP_MIN_LONGITUDE_variation1() {
        runCase(-40.0, 20.0, -10.0, 10.0, -40.0, 0.0);
    }

    @Test
    public void NON_WRAP_MAX_LONGITUDE_variation1() {
        runCase(-40.0, 20.0, -10.0, 10.0, 20.0, 0.0);
    }

    @Test
    public void NON_WRAP_WEST_OUTSIDE_variation1() {
        runCase(-40.0, 20.0, -10.0, 10.0, -50.0, 0.0);
    }

    @Test
    public void NON_WRAP_EAST_OUTSIDE_variation1() {
        runCase(-40.0, 20.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void EAST_DATELINE_EQUIVALENT_TO_WEST_variation1() {
        runCase(-180.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void WEST_DATELINE_EQUIVALENT_TO_EAST_variation1() {
        runCase(170.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_EXACT_LONGITUDE_variation1() {
        runCase(10.0, 10.0, -10.0, 10.0, 10.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_MISS_variation1() {
        runCase(10.0, 10.0, -10.0, 10.0, 10.000000001, 0.0);
    }

    @Test
    public void ZERO_WIDTH_DATELINE_EQUIVALENCE_variation1() {
        runCase(-180.0, -180.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void REVERSED_DATELINE_ENDPOINT_LINE_variation1() {
        runCase(180.0, -180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void WRAP_POSITIVE_LONGITUDE_INTERIOR_variation1() {
        runCase(170.0, -170.0, -10.0, 10.0, 175.0, 0.0);
    }

    @Test
    public void WRAP_NEGATIVE_LONGITUDE_INTERIOR_variation1() {
        runCase(170.0, -170.0, -10.0, 10.0, -175.0, 0.0);
    }

    @Test
    public void WRAP_MIN_LONGITUDE_BOUNDARY_variation1() {
        runCase(170.0, -170.0, -10.0, 10.0, 170.0, 0.0);
    }

    @Test
    public void WRAP_MAX_LONGITUDE_BOUNDARY_variation1() {
        runCase(170.0, -170.0, -10.0, 10.0, -170.0, 0.0);
    }

    @Test
    public void WRAP_CENTRAL_GAP_variation1() {
        runCase(170.0, -170.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void WRAP_JUST_OUTSIDE_NEGATIVE_EDGE_variation1() {
        runCase(170.0, -170.0, -10.0, 10.0, -169.999999999, 0.0);
    }

    @Test
    public void WRAP_JUST_OUTSIDE_POSITIVE_EDGE_variation1() {
        runCase(170.0, -170.0, -10.0, 10.0, 169.999999999, 0.0);
    }

    @Test
    public void NON_WRAP_TO_WRAP_CONTAINS_variation1() {
        runCase(20.0, 40.0, -10.0, 10.0, 25.0, 0.0);
    }

    @Test
    public void NON_WRAP_TO_WRAP_DISJOINT_variation1() {
        runCase(20.0, 40.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void NON_WRAP_TO_WRAP_THRESHOLD_POINT_variation1() {
        runCase(20.0, 40.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void WRAP_TO_NON_WRAP_POSITIVE_SIDE_variation1() {
        runCase(100.0, -100.0, -10.0, 10.0, 150.0, 0.0);
    }

    @Test
    public void WRAP_TO_NON_WRAP_NEGATIVE_SIDE_variation1() {
        runCase(100.0, -100.0, -10.0, 10.0, -150.0, 0.0);
    }

    @Test
    public void WRAP_TO_NON_WRAP_GAP_POINT_variation1() {
        runCase(100.0, -100.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void NON_WRAP_REMAINS_NON_WRAP_LOW_LONGITUDES_variation1() {
        runCase(-100.0, 0.0, -10.0, 10.0, -50.0, 0.0);
    }

    @Test
    public void NON_WRAP_REMAINS_NON_WRAP_HIGH_LONGITUDES_variation1() {
        runCase(60.0, 120.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void WRAP_REMAINS_WRAP_ENDPOINTS_ABOVE_THRESHOLD_variation1() {
        runCase(170.0, 100.0, -10.0, 10.0, -150.0, 0.0);
    }

    @Test
    public void WRAP_REMAINS_WRAP_ENDPOINTS_BELOW_THRESHOLD_variation1() {
        runCase(20.0, -100.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void NON_WRAP_JUST_INSIDE_MAX_variation1() {
        runCase(-10.0, 10.0, -10.0, 10.0, 9.999999999, 0.0);
    }

    @Test
    public void NON_WRAP_JUST_OUTSIDE_MAX_variation1() {
        runCase(-10.0, 10.0, -10.0, 10.0, 10.000000001, 0.0);
    }

    @Test
    public void POINT_RECTANGLE_EXACT_MATCH_variation1() {
        runCase(12.0, 12.0, 7.0, 7.0, 12.0, 7.0);
    }

    @Test
    public void POINT_RECTANGLE_LONGITUDE_MISMATCH_variation1() {
        runCase(12.0, 12.0, 7.0, 7.0, 13.0, 7.0);
    }

    @Test
    public void NARROW_DATELINE_BOX_CONTAINS_SEAM_variation1() {
        runCase(179.999, -179.999, -1.0, 1.0, 180.0, 0.0);
    }

    @Test
    public void NARROW_DATELINE_BOX_EXCLUDES_ZERO_variation1() {
        runCase(179.999, -179.999, -1.0, 1.0, 0.0, 0.0);
    }

    @Test
    public void LATITUDE_REJECTION_PRECEDES_WRAP_LOGIC_variation1() {
        runCase(170.0, -170.0, -10.0, 10.0, -175.0, 11.0);
    }

    @Test
    public void CORNER_AT_WRAP_MIN_AND_MAX_LATITUDE_variation1() {
        runCase(170.0, -170.0, -10.0, 10.0, 170.0, 10.0);
    }

    @Test
    public void CORNER_AT_WRAP_MAX_AND_MIN_LATITUDE_variation1() {
        runCase(170.0, -170.0, -10.0, 10.0, -170.0, -10.0);
    }
}
