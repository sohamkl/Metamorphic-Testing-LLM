import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointMetamorphicPassingTest {

    private void verify(double minX, double maxX, double minY, double maxY,
                        double pointX, double pointY) {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput =
                ((RectangleImpl) sourceRectangle).relate(sourcePoint);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(
                        sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput =
                ((RectangleImpl) followUpRectangle).relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    public void NONWRAP_INTERIOR_NO_ROTATION_SEAM_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, -40.0, 0.0);
    }

    @Test
    public void NONWRAP_INTERIOR_ROTATES_TO_WRAP_variation1() {
        verify(0.0, 60.0, -20.0, 20.0, 30.0, 0.0);
    }

    @Test
    public void NONWRAP_INTERIOR_BOTH_ENDPOINTS_NORMALIZE_variation1() {
        verify(60.0, 120.0, -20.0, 20.0, 90.0, 0.0);
    }

    @Test
    public void NONWRAP_WEST_OUTSIDE_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, -120.0, 0.0);
    }

    @Test
    public void NONWRAP_EAST_OUTSIDE_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, 40.0, 0.0);
    }

    @Test
    public void LATITUDE_ABOVE_NONWRAP_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, -40.0, 21.0);
    }

    @Test
    public void LATITUDE_BELOW_NONWRAP_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, -40.0, -21.0);
    }

    @Test
    public void NONWRAP_MIN_X_INCLUSIVE_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, -100.0, 0.0);
    }

    @Test
    public void NONWRAP_MAX_X_INCLUSIVE_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, 20.0, 0.0);
    }

    @Test
    public void MIN_Y_INCLUSIVE_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, -40.0, -20.0);
    }

    @Test
    public void MAX_Y_INCLUSIVE_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, -40.0, 20.0);
    }

    @Test
    public void LOWER_LEFT_CORNER_INCLUSIVE_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, -100.0, -20.0);
    }

    @Test
    public void UPPER_RIGHT_CORNER_INCLUSIVE_variation1() {
        verify(-100.0, 20.0, -20.0, 20.0, 20.0, 20.0);
    }

    @Test
    public void NEGATIVE_DATELINE_ALIASES_POSITIVE_MAX_variation1() {
        verify(100.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    public void POSITIVE_DATELINE_ALIASES_NEGATIVE_MIN_variation1() {
        verify(-180.0, -100.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    public void WRAP_POSITIVE_SEGMENT_INTERIOR_variation1() {
        verify(170.0, -170.0, -20.0, 20.0, 175.0, 0.0);
    }

    @Test
    public void WRAP_NEGATIVE_SEGMENT_INTERIOR_variation1() {
        verify(170.0, -170.0, -20.0, 20.0, -175.0, 0.0);
    }

    @Test
    public void WRAP_GAP_POSITIVE_SIDE_variation1() {
        verify(170.0, -170.0, -20.0, 20.0, 160.0, 0.0);
    }

    @Test
    public void WRAP_GAP_NEGATIVE_SIDE_variation1() {
        verify(170.0, -170.0, -20.0, 20.0, -160.0, 0.0);
    }

    @Test
    public void LATITUDE_ABOVE_WRAPPED_RECTANGLE_variation1() {
        verify(170.0, -170.0, -20.0, 20.0, 175.0, 21.0);
    }

    @Test
    public void LATITUDE_BELOW_WRAPPED_RECTANGLE_variation1() {
        verify(170.0, -170.0, -20.0, 20.0, -175.0, -21.0);
    }

    @Test
    public void WRAP_MIN_X_INCLUSIVE_variation1() {
        verify(170.0, -170.0, -20.0, 20.0, 170.0, 0.0);
    }

    @Test
    public void WRAP_MAX_X_INCLUSIVE_variation1() {
        verify(170.0, -170.0, -20.0, 20.0, -170.0, 0.0);
    }

    @Test
    public void WRAP_TO_NONWRAP_ROTATION_variation1() {
        verify(40.0, 20.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void WRAP_REMAINS_WRAP_AFTER_ROTATION_variation1() {
        verify(20.0, -20.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void NEAR_WORLD_WRAP_CONTAINS_variation1() {
        verify(-170.0, -175.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    public void NEAR_WORLD_WRAP_NARROW_GAP_variation1() {
        verify(-170.0, -175.0, -10.0, 10.0, -172.0, 0.0);
    }

    @Test
    public void VERTICAL_RECTANGLE_EXACT_LONGITUDE_variation1() {
        verify(10.0, 10.0, -20.0, 20.0, 10.0, 0.0);
    }

    @Test
    public void VERTICAL_RECTANGLE_WEST_OUTSIDE_variation1() {
        verify(10.0, 10.0, -20.0, 20.0, 9.0, 0.0);
    }

    @Test
    public void VERTICAL_RECTANGLE_EAST_OUTSIDE_variation1() {
        verify(10.0, 10.0, -20.0, 20.0, 11.0, 0.0);
    }

    @Test
    public void POSITIVE_DATELINE_VERTICAL_ACCEPTS_NEGATIVE_ALIAS_variation1() {
        verify(180.0, 180.0, -20.0, 20.0, -180.0, 0.0);
    }

    @Test
    public void NEGATIVE_DATELINE_VERTICAL_ACCEPTS_POSITIVE_ALIAS_variation1() {
        verify(-180.0, -180.0, -20.0, 20.0, 180.0, 0.0);
    }

    @Test
    public void REVERSE_DATELINE_ALIAS_EXACT_variation1() {
        verify(180.0, -180.0, -20.0, 20.0, 180.0, 0.0);
    }

    @Test
    public void REVERSE_DATELINE_ALIAS_OUTSIDE_variation1() {
        verify(180.0, -180.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    public void HORIZONTAL_RECTANGLE_ON_LATITUDE_variation1() {
        verify(-20.0, 20.0, 10.0, 10.0, 0.0, 10.0);
    }

    @Test
    public void HORIZONTAL_RECTANGLE_JUST_ABOVE_variation1() {
        verify(-20.0, 20.0, 10.0, 10.0, 0.0, 10.0001);
    }

    @Test
    public void HORIZONTAL_RECTANGLE_JUST_BELOW_variation1() {
        verify(-20.0, 20.0, 10.0, 10.0, 0.0, 9.9999);
    }

    @Test
    public void POINT_RECTANGLE_EXACT_MATCH_variation1() {
        verify(10.0, 10.0, 20.0, 20.0, 10.0, 20.0);
    }

    @Test
    public void POINT_RECTANGLE_LONGITUDE_MISMATCH_variation1() {
        verify(10.0, 10.0, 20.0, 20.0, 11.0, 20.0);
    }

    @Test
    public void POINT_RECTANGLE_LATITUDE_MISMATCH_variation1() {
        verify(10.0, 10.0, 20.0, 20.0, 10.0, 21.0);
    }

    @Test
    public void FULL_LATITUDE_SPAN_POLE_CONTAINED_variation1() {
        verify(-10.0, 10.0, -90.0, 90.0, 0.0, 90.0);
    }

    @Test
    public void FULL_LATITUDE_SPAN_LONGITUDE_OUTSIDE_variation1() {
        verify(-10.0, 10.0, -90.0, 90.0, 20.0, 0.0);
    }

    @Test
    public void NORTH_POLE_ZERO_HEIGHT_LINE_variation1() {
        verify(-20.0, 20.0, 90.0, 90.0, 0.0, 90.0);
    }

    @Test
    public void SOUTH_POLE_ZERO_HEIGHT_LINE_variation1() {
        verify(-20.0, 20.0, -90.0, -90.0, 0.0, -90.0);
    }

    @Test
    public void ROTATED_ENDPOINT_AT_LONGITUDE_SEAM_variation1() {
        verify(30.0, 60.0, -10.0, 10.0, 45.0, 0.0);
    }

    @Test
    public void ROTATED_POINT_AT_LONGITUDE_SEAM_variation1() {
        verify(20.0, 40.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    public void WRAPPED_FULL_LATITUDE_NORTH_POLE_variation1() {
        verify(170.0, -170.0, -90.0, 90.0, -175.0, 90.0);
    }

    @Test
    public void WRAPPED_FULL_LATITUDE_GAP_variation1() {
        verify(170.0, -170.0, -90.0, 90.0, 0.0, -90.0);
    }
}
