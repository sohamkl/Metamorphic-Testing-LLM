import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private void exerciseCandidate(
            double minX, double maxX, double minY, double maxY,
            double pointX, double pointY) {

        SpatialContext context = new SpatialContext(true);
        RectangleImpl sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp =
                RectangleContainsPointMetamorphicSpec.generateFollowUp(
                        sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput =
                followUpRectangle.relate(followUpPoint);

        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    private void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "Rotating the rectangle and point changed their spatial relation: "
                            + sourceOutput + " -> " + followUpOutput);
        }
    }

    @Test
    public void ORDINARY_INTERIOR_CONTAINED_variation1_centeredInterior() {
        exerciseCandidate(-120.0, -60.0, -40.0, 20.0, -90.0, -10.0);
    }

    @Test
    public void ORDINARY_INTERIOR_CONTAINED_variation2_easternInterior() {
        exerciseCandidate(20.0, 80.0, 5.0, 55.0, 65.0, 30.0);
    }

    @Test
    public void POINT_ON_MIN_X_EDGE_variation1_westEdge() {
        exerciseCandidate(-75.0, -25.0, -20.0, 30.0, -75.0, 5.0);
    }

    @Test
    public void POINT_ON_MAX_X_EDGE_variation1_eastEdge() {
        exerciseCandidate(35.0, 95.0, -30.0, 40.0, 95.0, 10.0);
    }

    @Test
    public void POINT_ON_MIN_Y_EDGE_variation1_southEdge() {
        exerciseCandidate(-140.0, -80.0, -45.0, 15.0, -110.0, -45.0);
    }

    @Test
    public void POINT_ON_MAX_Y_EDGE_variation1_northEdge() {
        exerciseCandidate(10.0, 70.0, -15.0, 65.0, 40.0, 65.0);
    }

    @Test
    public void POINT_ON_SOUTHWEST_CORNER_variation1_exactCorner() {
        exerciseCandidate(-100.0, -40.0, -50.0, 10.0, -100.0, -50.0);
    }

    @Test
    public void POINT_ON_NORTHEAST_CORNER_variation1_exactCorner() {
        exerciseCandidate(25.0, 85.0, -10.0, 45.0, 85.0, 45.0);
    }

    @Test
    public void POINT_STRICTLY_ABOVE_variation1_ordinaryLongitudeContained() {
        exerciseCandidate(-70.0, -10.0, -30.0, 20.0, -40.0, 21.0);
    }

    @Test
    public void POINT_STRICTLY_ABOVE_variation2_wrappedLongitudeContained() {
        exerciseCandidate(160.0, -150.0, 10.0, 40.0, 175.0, 80.0);
    }

    @Test
    public void POINT_STRICTLY_BELOW_variation1_ordinaryLongitudeContained() {
        exerciseCandidate(15.0, 75.0, -10.0, 50.0, 45.0, -11.0);
    }

    @Test
    public void POINT_STRICTLY_BELOW_variation2_wrappedLongitudeContained() {
        exerciseCandidate(150.0, -160.0, -20.0, 35.0, -175.0, -70.0);
    }

    @Test
    public void ORDINARY_POINT_WEST_OF_RECTANGLE_variation1_negativeLongitudes() {
        exerciseCandidate(20.0, 40.0, -25.0, 25.0, -100.0, 0.0);
    }

    @Test
    public void ORDINARY_POINT_WEST_OF_RECTANGLE_variation2_nearWesternLimit() {
        exerciseCandidate(-100.0, -50.0, -60.0, -10.0, -170.0, -35.0);
    }

    @Test
    public void ORDINARY_POINT_EAST_OF_RECTANGLE_variation1_centralRectangle() {
        exerciseCandidate(-40.0, 20.0, -20.0, 20.0, 100.0, 0.0);
    }

    @Test
    public void ORDINARY_POINT_EAST_OF_RECTANGLE_variation2_easternRectangle() {
        exerciseCandidate(100.0, 140.0, 30.0, 70.0, 170.0, 50.0);
    }

    @Test
    public void ZERO_HEIGHT_EXACT_LATITUDE_variation1_onHorizontalLine() {
        exerciseCandidate(160.0, -150.0, 12.0, 12.0, -170.0, 12.0);
    }

    @Test
    public void ZERO_HEIGHT_DIFFERENT_LATITUDE_variation1_aboveHorizontalLine() {
        exerciseCandidate(-50.0, 50.0, -15.0, -15.0, 0.0, -14.0);
    }

    @Test
    public void ZERO_WIDTH_EXACT_LONGITUDE_variation1_onVerticalLine() {
        exerciseCandidate(35.0, 35.0, -30.0, 30.0, 35.0, 0.0);
    }

    @Test
    public void ZERO_WIDTH_DIFFERENT_LONGITUDE_variation1_offVerticalLine() {
        exerciseCandidate(-45.0, -45.0, -20.0, 20.0, -44.0, 0.0);
    }

    @Test
    public void MINUS_180_LINE_CONTAINS_PLUS_180_POINT_variation1_datelineEquivalent() {
        exerciseCandidate(-180.0, -180.0, -45.0, 45.0, 180.0, 0.0);
    }

    @Test
    public void PLUS_180_LINE_CONTAINS_MINUS_180_POINT_variation1_datelineEquivalent() {
        exerciseCandidate(180.0, 180.0, -50.0, 50.0, -180.0, 10.0);
    }

    @Test
    public void WRAPPED_EAST_SEGMENT_INTERIOR_variation1_positiveLongitudeSegment() {
        exerciseCandidate(160.0, -140.0, -30.0, 30.0, 175.0, 5.0);
    }

    @Test
    public void WRAPPED_EAST_EDGE_variation1_minXBoundary() {
        exerciseCandidate(165.0, -155.0, -40.0, 40.0, 165.0, 0.0);
    }

    @Test
    public void WRAPPED_WEST_SEGMENT_INTERIOR_variation1_negativeLongitudeSegment() {
        exerciseCandidate(155.0, -145.0, -35.0, 35.0, -170.0, 0.0);
    }

    @Test
    public void WRAPPED_WEST_SEGMENT_INTERIOR_variation2_nearDateline() {
        exerciseCandidate(175.0, -165.0, 10.0, 60.0, -179.0, 30.0);
    }

    @Test
    public void WRAPPED_WEST_EDGE_variation1_maxXBoundary() {
        exerciseCandidate(170.0, -170.0, -20.0, 20.0, -170.0, 0.0);
    }

    @Test
    public void WRAPPED_CENTRAL_GAP_variation1_gapCenter() {
        exerciseCandidate(170.0, -170.0, -25.0, 25.0, 0.0, 0.0);
    }

    @Test
    public void WRAPPED_CENTRAL_GAP_variation2_positiveGapLongitude() {
        exerciseCandidate(120.0, -100.0, 20.0, 60.0, 80.0, 40.0);
    }

    @Test
    public void WRAPPED_GAP_JUST_BELOW_EAST_EDGE_variation1_adjacentToMinX() {
        exerciseCandidate(170.0, -160.0, -10.0, 10.0, 169.999999, 0.0);
    }

    @Test
    public void ROTATION_CREATES_WRAP_CONTAINED_variation1_equatorialInterior() {
        exerciseCandidate(-20.0, 40.0, -30.0, 30.0, 0.0, 0.0);
    }

    @Test
    public void ROTATION_CREATES_WRAP_CONTAINED_variation2_northernInterior() {
        exerciseCandidate(-20.0, 40.0, 10.0, 70.0, 0.0, 35.0);
    }

    @Test
    public void ROTATION_CREATES_WRAP_DISJOINT_variation1_equatorialGap() {
        exerciseCandidate(-20.0, 40.0, -30.0, 30.0, 100.0, 0.0);
    }

    @Test
    public void ROTATION_CREATES_WRAP_DISJOINT_variation2_latitudeBoundary() {
        exerciseCandidate(-20.0, 40.0, 15.0, 65.0, 100.0, 65.0);
    }

    @Test
    public void ROTATION_REMOVES_WRAP_CONTAINED_variation1_eastSegment() {
        exerciseCandidate(170.0, -170.0, -25.0, 25.0, 175.0, 0.0);
    }

    @Test
    public void ROTATION_REMOVES_WRAP_CONTAINED_variation2_northernEastSegment() {
        exerciseCandidate(170.0, -170.0, 20.0, 80.0, 175.0, 50.0);
    }

    @Test
    public void ROTATION_REMOVES_WRAP_DISJOINT_variation1_gapCenter() {
        exerciseCandidate(170.0, -170.0, -30.0, 30.0, 0.0, 0.0);
    }

    @Test
    public void ROTATION_REMOVES_WRAP_DISJOINT_variation2_gapAtLatitudeEdge() {
        exerciseCandidate(170.0, -170.0, 10.0, 60.0, 0.0, 10.0);
    }

    @Test
    public void NORTH_POLE_BOUNDARY_variation1_exactPole() {
        exerciseCandidate(-60.0, 20.0, 40.0, 90.0, -20.0, 90.0);
    }

    @Test
    public void SOUTH_POLE_BOUNDARY_variation1_exactPole() {
        exerciseCandidate(130.0, -140.0, -90.0, -35.0, 170.0, -90.0);
    }

    @Test
    public void FULL_LATITUDE_RANGE_INTERIOR_variation1_midLatitude() {
        exerciseCandidate(-120.0, 90.0, -90.0, 90.0, 10.0, 25.0);
    }

    @Test
    public void PLUS_180_ORDINARY_EDGE_variation1_maxLongitudeSentinel() {
        exerciseCandidate(100.0, 180.0, -45.0, 45.0, 180.0, 0.0);
    }

    @Test
    public void MINUS_180_ORDINARY_EDGE_variation1_minLongitudeSentinel() {
        exerciseCandidate(-180.0, -100.0, -35.0, 35.0, -180.0, 5.0);
    }

    @Test
    public void NEARLY_FULL_NON_WRAPPING_CONTAINS_variation1_farFromGap() {
        exerciseCandidate(-179.5, 179.5, -50.0, 50.0, 0.0, 0.0);
    }

    @Test
    public void NEARLY_FULL_NON_WRAPPING_GAP_variation1_datelineGap() {
        exerciseCandidate(-179.5, 179.5, -40.0, 40.0, 180.0, 0.0);
    }

    @Test
    public void NEARLY_FULL_WRAPPED_CONTAINS_variation1_oppositeNarrowGap() {
        exerciseCandidate(0.5, -0.5, -30.0, 30.0, 180.0, 0.0);
    }

    @Test
    public void NEARLY_FULL_WRAPPED_GAP_variation1_narrowCentralGap() {
        exerciseCandidate(0.5, -0.5, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    public void DATELINE_EQUIVALENT_REVERSED_ENDPOINTS_CONTAIN_variation1_minus180Probe() {
        exerciseCandidate(180.0, -180.0, -45.0, 45.0, -180.0, 0.0);
    }

    @Test
    public void DATELINE_EQUIVALENT_REVERSED_ENDPOINTS_DISJOINT_variation1_primeMeridianProbe() {
        exerciseCandidate(180.0, -180.0, -45.0, 45.0, 0.0, 0.0);
    }
}
