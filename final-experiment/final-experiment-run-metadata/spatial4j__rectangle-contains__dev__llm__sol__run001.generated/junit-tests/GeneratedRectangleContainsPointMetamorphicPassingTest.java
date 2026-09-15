import org.junit.jupiter.api.Test;

public class GeneratedRectangleContainsPointMetamorphicPassingTest {

    private void exercise(double minX, double maxX, double minY, double maxY,
                          double pointX, double pointY) {
        org.locationtech.spatial4j.context.SpatialContext context =
                org.locationtech.spatial4j.context.SpatialContext.GEO;

        org.locationtech.spatial4j.shape.impl.RectangleImpl rectangle =
                new org.locationtech.spatial4j.shape.impl.RectangleImpl(
                        minX, maxX, minY, maxY, context);

        org.locationtech.spatial4j.shape.Point point =
                context.makePoint(pointX, pointY);

        org.locationtech.spatial4j.shape.SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.RectangleImpl) rectangle).relate(point);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);

        org.locationtech.spatial4j.shape.impl.RectangleImpl followUpRectangle =
                (org.locationtech.spatial4j.shape.impl.RectangleImpl) followUp[0];
        org.locationtech.spatial4j.shape.Point followUpPoint =
                (org.locationtech.spatial4j.shape.Point) followUp[1];

        org.locationtech.spatial4j.shape.SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.RectangleImpl) followUpRectangle)
                        .relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void NONWRAP_INTERIOR_ROTATES_TO_WRAP_variation1() {
        exercise(-20.0, 40.0, -10.0, 20.0, 0.0, 5.0);
    }

    @Test
    public void NONWRAP_INTERIOR_REMAINS_NONWRAP_variation1() {
        exercise(-100.0, -50.0, -20.0, 20.0, -75.0, 0.0);
    }

    @Test
    public void WRAP_POSITIVE_LOBE_ROTATES_TO_NONWRAP_variation1() {
        exercise(170.0, -170.0, -20.0, 20.0, 175.0, 0.0);
    }

    @Test
    public void WRAP_NEGATIVE_LOBE_REQUIRES_PLUS_360_variation1() {
        exercise(170.0, -170.0, -20.0, 20.0, -175.0, 0.0);
    }

    @Test
    public void WRAP_REMAINS_WRAP_AFTER_ROTATION_variation1() {
        exercise(170.0, 100.0, -30.0, 30.0, -150.0, 10.0);
    }

    @Test
    public void WRAP_CENTRAL_GAP_DISJOINT_variation1() {
        exercise(170.0, -170.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    public void NONWRAP_WEST_OUTSIDE_AFTER_SHIFT_variation1() {
        exercise(-20.0, 40.0, -10.0, 10.0, -170.0, 0.0);
    }

    @Test
    public void NONWRAP_EAST_OUTSIDE_AFTER_SHIFT_variation1() {
        exercise(-40.0, 20.0, -10.0, 10.0, 170.0, 0.0);
    }

    @Test
    public void MINUS_180_EQUIVALENT_TO_PLUS_180_BOUNDARY_variation1() {
        exercise(100.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void PLUS_180_EQUIVALENT_TO_MINUS_180_BOUNDARY_variation1() {
        exercise(-180.0, -100.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void MINUS_180_SHIFTED_PAST_EAST_BOUND_variation1() {
        exercise(100.0, 170.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void PLUS_180_SHIFTED_BEFORE_WEST_BOUND_variation1() {
        exercise(-170.0, -100.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void NONWRAP_MIN_X_INCLUDED_variation1() {
        exercise(-20.0, 40.0, -10.0, 20.0, -20.0, 5.0);
    }

    @Test
    public void NONWRAP_MAX_X_INCLUDED_variation1() {
        exercise(-20.0, 40.0, -10.0, 20.0, 40.0, 5.0);
    }

    @Test
    public void WRAP_MIN_X_INCLUDED_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 170.0, 0.0);
    }

    @Test
    public void WRAP_MAX_X_INCLUDED_AFTER_UNWRAP_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -170.0, 0.0);
    }

    @Test
    public void MIN_Y_INCLUDED_variation1() {
        exercise(-30.0, 30.0, -10.0, 20.0, 0.0, -10.0);
    }

    @Test
    public void MAX_Y_INCLUDED_variation1() {
        exercise(-30.0, 30.0, -10.0, 20.0, 0.0, 20.0);
    }

    @Test
    public void ABOVE_MAX_Y_EARLY_DISJOINT_variation1() {
        exercise(-30.0, 30.0, -10.0, 20.0, 0.0, 30.0);
    }

    @Test
    public void BELOW_MIN_Y_EARLY_DISJOINT_variation1() {
        exercise(-30.0, 30.0, -10.0, 20.0, 0.0, -20.0);
    }

    @Test
    public void ZERO_HEIGHT_MATCHING_LATITUDE_variation1() {
        exercise(-40.0, 40.0, 5.0, 5.0, 0.0, 5.0);
    }

    @Test
    public void ZERO_HEIGHT_NONMATCHING_LATITUDE_variation1() {
        exercise(-40.0, 40.0, 5.0, 5.0, 0.0, 6.0);
    }

    @Test
    public void ZERO_WIDTH_MATCHING_LONGITUDE_AT_ROTATION_SEAM_variation1() {
        exercise(30.0, 30.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_DIFFERENT_LONGITUDE_variation1() {
        exercise(30.0, 30.0, -10.0, 10.0, 31.0, 0.0);
    }

    @Test
    public void POINT_RECTANGLE_EXACT_MATCH_variation1() {
        exercise(12.5, 12.5, -7.5, -7.5, 12.5, -7.5);
    }

    @Test
    public void VERTICAL_LINE_AT_PLUS_180_ACCEPTS_MINUS_180_variation1() {
        exercise(180.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void VERTICAL_LINE_AT_MINUS_180_ACCEPTS_PLUS_180_variation1() {
        exercise(-180.0, -180.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void NARROW_WRAP_POSITIVE_SIDE_variation1() {
        exercise(179.0, -179.0, -5.0, 5.0, 179.5, 0.0);
    }

    @Test
    public void NARROW_WRAP_NEGATIVE_SIDE_variation1() {
        exercise(179.0, -179.0, -5.0, 5.0, -179.5, 0.0);
    }

    @Test
    public void NARROW_WRAP_NEARBY_GAP_variation1() {
        exercise(179.0, -179.0, -5.0, 5.0, 178.999, 0.0);
    }

    @Test
    public void NEAR_FULL_NONWRAP_DATELINE_GAP_variation1() {
        exercise(-179.0, 179.0, -45.0, 45.0, 180.0, 0.0);
    }

    @Test
    public void NEAR_FULL_WRAP_INTERIOR_variation1() {
        exercise(1.0, -1.0, -45.0, 45.0, 180.0, 0.0);
    }

    @Test
    public void NEAR_FULL_WRAP_SMALL_GAP_variation1() {
        exercise(1.0, -1.0, -45.0, 45.0, 0.0, 0.0);
    }

    @Test
    public void NORTH_POLE_BOUNDARY_INCLUDED_variation1() {
        exercise(-60.0, 60.0, 80.0, 90.0, 0.0, 90.0);
    }

    @Test
    public void SOUTH_POLE_BOUNDARY_INCLUDED_variation1() {
        exercise(-60.0, 60.0, -90.0, -80.0, 0.0, -90.0);
    }

    @Test
    public void NORTH_POLE_ZERO_HEIGHT_RECTANGLE_variation1() {
        exercise(-20.0, 20.0, 90.0, 90.0, 10.0, 90.0);
    }

    @Test
    public void SOUTHWEST_CORNER_INCLUDED_variation1() {
        exercise(-40.0, 20.0, -30.0, 10.0, -40.0, -30.0);
    }

    @Test
    public void NORTHEAST_CORNER_INCLUDED_variation1() {
        exercise(-40.0, 20.0, -30.0, 10.0, 20.0, 10.0);
    }

    @Test
    public void JUST_WEST_OF_MIN_X_variation1() {
        exercise(-20.0, 40.0, -10.0, 10.0, -20.000000001, 0.0);
    }

    @Test
    public void JUST_EAST_OF_MAX_X_variation1() {
        exercise(-20.0, 40.0, -10.0, 10.0, 40.000000001, 0.0);
    }

    @Test
    public void JUST_ABOVE_MAX_Y_variation1() {
        exercise(-20.0, 40.0, -10.0, 10.0, 0.0, 10.000000001);
    }

    @Test
    public void JUST_BELOW_MIN_Y_variation1() {
        exercise(-20.0, 40.0, -10.0, 10.0, 0.0, -10.000000001);
    }

    @Test
    public void POINT_ON_ROTATION_CUT_INSIDE_WIDE_RECTANGLE_variation1() {
        exercise(20.0, 40.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void WRAPPED_POINT_AT_PLUS_180_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void WRAPPED_POINT_AT_MINUS_180_variation1() {
        exercise(170.0, -170.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void LATITUDE_INSIDE_LONGITUDE_OUTSIDE_variation1() {
        exercise(-10.0, 10.0, -80.0, 80.0, 90.0, 0.0);
    }

    @Test
    public void LONGITUDE_INSIDE_LATITUDE_OUTSIDE_variation1() {
        exercise(-100.0, 100.0, 40.0, 50.0, 0.0, 0.0);
    }

    @Test
    public void FULL_LATITUDE_SPAN_INTERIOR_variation1() {
        exercise(-120.0, 120.0, -90.0, 90.0, 0.0, 45.0);
    }

    @Test
    public void WRAP_FULL_LATITUDE_SPAN_GAP_variation1() {
        exercise(120.0, -120.0, -90.0, 90.0, 0.0, 45.0);
    }
}
