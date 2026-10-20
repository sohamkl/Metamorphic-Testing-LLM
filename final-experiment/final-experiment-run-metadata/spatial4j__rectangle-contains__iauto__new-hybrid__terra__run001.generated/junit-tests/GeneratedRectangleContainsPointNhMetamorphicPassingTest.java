import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private void exercise(double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl sourceRectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(SpatialRelation sourceOutput, SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError("Rotating the rectangle and point together changed containment relation from "
                    + sourceOutput + " to " + followUpOutput);
        }
    }

    @Test
    public void LATITUDE_ABOVE_MAX_nonwrapping() {
        exercise(-80, -20, -30, 10, -50, 20);
    }

    @Test
    public void LATITUDE_ABOVE_MAX_wrapping() {
        exercise(120, -140, -20, 25, 150, 40);
    }

    @Test
    public void LATITUDE_BELOW_MIN_nonwrapping() {
        exercise(-60, 30, -10, 25, 0, -20);
    }

    @Test
    public void LATITUDE_BELOW_MIN_wrapping() {
        exercise(100, -120, -25, 20, 140, -40);
    }

    @Test
    public void LATITUDE_MIN_BOUNDARY_CONTAINS_nonwrapping_followupNonwrapping() {
        exercise(-100, -20, -15, 25, -60, -15);
    }

    @Test
    public void LATITUDE_MIN_BOUNDARY_CONTAINS_nonwrapping_followupWrapping() {
        exercise(20, 100, -20, 30, 60, -20);
    }

    @Test
    public void LATITUDE_MAX_BOUNDARY_CONTAINS_nonwrapping_followupNonwrapping() {
        exercise(-120, -40, -25, 15, -80, 15);
    }

    @Test
    public void LATITUDE_MAX_BOUNDARY_CONTAINS_nonwrapping_followupWrapping() {
        exercise(35, 110, -30, 20, 70, 20);
    }

    @Test
    public void NONWRAP_INTERIOR_CONTAINS_followupWrapping() {
        exercise(40, 110, -20, 20, 75, 0);
    }

    @Test
    public void NONWRAP_INTERIOR_CONTAINS_followupNonwrapping() {
        exercise(-130, -40, -30, 30, -80, 5);
    }

    @Test
    public void NONWRAP_MIN_X_BOUNDARY_CONTAINS_followupWrapping() {
        exercise(30, 90, -10, 25, 30, 0);
    }

    @Test
    public void NONWRAP_MIN_X_BOUNDARY_CONTAINS_followupNonwrapping() {
        exercise(-120, -50, -25, 15, -120, 5);
    }

    @Test
    public void NONWRAP_MAX_X_BOUNDARY_CONTAINS_followupWrapping() {
        exercise(25, 105, -15, 20, 105, 10);
    }

    @Test
    public void NONWRAP_MAX_X_BOUNDARY_CONTAINS_followupNonwrapping() {
        exercise(-140, -60, -20, 20, -60, -5);
    }

    @Test
    public void NONWRAP_WEST_OUTSIDE_DISJOINT_followupWrapping() {
        exercise(30, 100, -20, 20, -20, 0);
    }

    @Test
    public void NONWRAP_WEST_OUTSIDE_DISJOINT_followupNonwrapping() {
        exercise(-100, -30, -20, 20, -140, 0);
    }

    @Test
    public void NONWRAP_EAST_OUTSIDE_DISJOINT_followupWrapping() {
        exercise(20, 80, -20, 20, 120, 0);
    }

    @Test
    public void NONWRAP_EAST_OUTSIDE_DISJOINT_followupNonwrapping() {
        exercise(-130, -50, -20, 20, -10, 0);
    }

    @Test
    public void NONWRAP_WEST_DATELINE_ALIAS_CONTAINS_wide() {
        exercise(20, 180, -20, 20, -180, 0);
    }

    @Test
    public void NONWRAP_WEST_DATELINE_ALIAS_CONTAINS_narrow() {
        exercise(130, 180, -30, 10, -180, -5);
    }

    @Test
    public void NONWRAP_EAST_DATELINE_ALIAS_CONTAINS_wide() {
        exercise(-180, -20, -20, 20, 180, 0);
    }

    @Test
    public void NONWRAP_EAST_DATELINE_ALIAS_CONTAINS_narrow() {
        exercise(-180, -130, -30, 15, 180, 5);
    }

    @Test
    public void WRAP_EAST_SEGMENT_CONTAINS_remainsWrapping() {
        exercise(100, -100, -20, 20, 140, 0);
    }

    @Test
    public void WRAP_EAST_SEGMENT_CONTAINS_becomesNonwrapping() {
        exercise(80, -160, -25, 25, 120, 5);
    }

    @Test
    public void WRAP_WEST_SEGMENT_CONTAINS_remainsWrapping() {
        exercise(140, -80, -20, 20, -120, 0);
    }

    @Test
    public void WRAP_WEST_SEGMENT_CONTAINS_becomesNonwrapping() {
        exercise(90, -150, -25, 25, -170, -5);
    }

    @Test
    public void WRAP_MIN_X_BOUNDARY_CONTAINS_narrowWrap() {
        exercise(150, -150, -20, 20, 150, 0);
    }

    @Test
    public void WRAP_MIN_X_BOUNDARY_CONTAINS_wideWrap() {
        exercise(60, -120, -30, 20, 60, 10);
    }

    @Test
    public void WRAP_MAX_X_BOUNDARY_CONTAINS_narrowWrap() {
        exercise(160, -160, -20, 20, -160, 0);
    }

    @Test
    public void WRAP_MAX_X_BOUNDARY_CONTAINS_wideWrap() {
        exercise(70, -100, -25, 25, -100, -5);
    }

    @Test
    public void WRAP_GAP_DISJOINT_narrowWrappedRectangle() {
        exercise(150, -150, -20, 20, 0, 0);
    }

    @Test
    public void WRAP_GAP_DISJOINT_wideWrappedRectangle() {
        exercise(80, -80, -30, 30, 0, 10);
    }

    @Test
    public void ZERO_WIDTH_MATCH_CONTAINS_negativeLongitude() {
        exercise(-70, -70, -30, 20, -70, 0);
    }

    @Test
    public void ZERO_WIDTH_MATCH_CONTAINS_positiveLongitude() {
        exercise(60, 60, -20, 35, 60, 10);
    }

    @Test
    public void ZERO_WIDTH_MISMATCH_DISJOINT_westOfLine() {
        exercise(30, 30, -20, 20, -10, 0);
    }

    @Test
    public void ZERO_WIDTH_MISMATCH_DISJOINT_eastOfLine() {
        exercise(-40, -40, -25, 25, 10, 0);
    }

    @Test
    public void ZERO_HEIGHT_MATCH_CONTAINS_interiorLongitude() {
        exercise(-100, -20, 15, 15, -60, 15);
    }

    @Test
    public void ZERO_HEIGHT_MATCH_CONTAINS_endpointLongitude() {
        exercise(30, 110, -10, -10, 110, -10);
    }

    @Test
    public void SINGLE_POINT_RECTANGLE_MATCH_CONTAINS_negativeLongitude() {
        exercise(-45, -45, 12, 12, -45, 12);
    }

    @Test
    public void SINGLE_POINT_RECTANGLE_MATCH_CONTAINS_positiveLongitude() {
        exercise(75, 75, -18, -18, 75, -18);
    }

    @Test
    public void NORTH_POLE_BOUNDARY_CONTAINS_nonwrapping() {
        exercise(-100, -20, 40, 90, -60, 90);
    }

    @Test
    public void NORTH_POLE_BOUNDARY_CONTAINS_wrapping() {
        exercise(120, -120, 30, 90, 150, 90);
    }

    @Test
    public void SOUTH_POLE_BOUNDARY_CONTAINS_nonwrapping() {
        exercise(-110, -30, -90, -40, -70, -90);
    }

    @Test
    public void SOUTH_POLE_BOUNDARY_CONTAINS_wrapping() {
        exercise(110, -130, -90, -30, 150, -90);
    }

    @Test
    public void NEAR_WORLD_NONWRAP_CONTAINS_width355() {
        exercise(-180, 175, -20, 20, 0, 0);
    }

    @Test
    public void NEAR_WORLD_NONWRAP_CONTAINS_width358() {
        exercise(-180, 178, -30, 30, 120, 10);
    }

    @Test
    public void NEAR_WORLD_EXCLUDED_SLIVER_DISJOINT_sliverFiveDegrees() {
        exercise(-180, 175, -20, 20, 178, 0);
    }

    @Test
    public void NEAR_WORLD_EXCLUDED_SLIVER_DISJOINT_sliverTwoDegrees() {
        exercise(-180, 178, -25, 25, 179, -5);
    }
}
