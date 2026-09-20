import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static Object[] generateFollowUp(RectangleImpl rectangle, Point point) {
        SpatialContext context = rectangle.getContext();

        RectangleImpl rotatedRectangle = new RectangleImpl(
                DistanceUtils.normLonDEG(rectangle.getMinX() + 150.0),
                DistanceUtils.normLonDEG(rectangle.getMaxX() + 150.0),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);

        Point rotatedPoint = context.makePoint(
                DistanceUtils.normLonDEG(point.getX() + 150.0),
                point.getY());

        return new Object[] {rotatedRectangle, rotatedPoint};
    }

    private static void verifyRotation(
            double minX, double maxX, double minY, double maxY, double pointX, double pointY) {
        SpatialContext context = new SpatialContext(true);
        RectangleImpl sourceRectangle = new RectangleImpl(minX, maxX, minY, maxY, context);
        Point sourcePoint = context.makePoint(pointX, pointY);

        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void ORDINARY_INTERIOR_SHORT_CIRCUIT_variation1() {
        verifyRotation(-100.0, -50.0, -20.0, 20.0, -75.0, 0.0);
    }

    @Test
    void ORDINARY_MIN_X_BOUNDARY_variation1() {
        verifyRotation(-100.0, -50.0, -20.0, 20.0, -100.0, 0.0);
    }

    @Test
    void ORDINARY_MAX_X_BOUNDARY_variation1() {
        verifyRotation(-100.0, -50.0, -20.0, 20.0, -50.0, 0.0);
    }

    @Test
    void ORDINARY_MIN_Y_BOUNDARY_variation1() {
        verifyRotation(-100.0, -50.0, -20.0, 20.0, -75.0, -20.0);
    }

    @Test
    void ORDINARY_MAX_Y_BOUNDARY_variation1() {
        verifyRotation(-100.0, -50.0, -20.0, 20.0, -75.0, 20.0);
    }

    @Test
    void LATITUDE_BELOW_RECTANGLE_variation1() {
        verifyRotation(-100.0, -50.0, -20.0, 20.0, -75.0, -20.000001);
    }

    @Test
    void LATITUDE_ABOVE_RECTANGLE_variation1() {
        verifyRotation(-100.0, -50.0, -20.0, 20.0, -75.0, 20.000001);
    }

    @Test
    void NONWRAPPING_WESTERN_LONGITUDE_DISJOINT_variation1() {
        verifyRotation(-100.0, -50.0, -20.0, 20.0, -120.0, 0.0);
    }

    @Test
    void NONWRAPPING_EASTERN_LONGITUDE_DISJOINT_variation1() {
        verifyRotation(-100.0, -50.0, -20.0, 20.0, -30.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_NON_DATELINE_MERIDIAN_variation1() {
        verifyRotation(10.0, 10.0, -20.0, 20.0, 10.0, 0.0);
    }

    @Test
    void ZERO_HEIGHT_HORIZONTAL_LINE_variation1() {
        verifyRotation(-40.0, 40.0, 15.0, 15.0, 0.0, 15.0);
    }

    @Test
    void WRAPPING_EASTERN_LOBE_INTERIOR_variation1() {
        verifyRotation(170.0, -170.0, -20.0, 20.0, 175.0, 0.0);
    }

    @Test
    void WRAPPING_WESTERN_LOBE_INTERIOR_variation1() {
        verifyRotation(170.0, -170.0, -20.0, 20.0, -175.0, 0.0);
    }

    @Test
    void WRAPPING_DATELINE_GAP_DISJOINT_variation1() {
        verifyRotation(170.0, -170.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    void WRAPPING_MIN_X_BOUNDARY_variation1() {
        verifyRotation(170.0, -170.0, -20.0, 20.0, 170.0, 0.0);
    }

    @Test
    void WRAPPING_MAX_X_BOUNDARY_AFTER_SHIFT_variation1() {
        verifyRotation(170.0, -170.0, -20.0, 20.0, -170.0, 0.0);
    }

    @Test
    void NONWRAPPING_TO_FOLLOWUP_WRAPPING_INTERIOR_variation1() {
        verifyRotation(20.0, 40.0, -10.0, 10.0, 25.0, 0.0);
    }

    @Test
    void NONWRAPPING_TO_FOLLOWUP_WRAPPING_DISJOINT_variation1() {
        verifyRotation(20.0, 40.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void FOLLOWUP_NORMALIZES_POSITIVE_180_POINT_variation1() {
        verifyRotation(20.0, 40.0, -10.0, 10.0, 30.0, 0.0);
    }

    @Test
    void WRAPPING_TO_FOLLOWUP_NONWRAPPING_CONTAINS_variation1() {
        double westernLobeLongitude = -175.0;
        verifyRotation(170.0, -170.0, -20.0, 20.0, westernLobeLongitude, 0.0);
    }

    @Test
    void WRAPPING_TO_FOLLOWUP_NONWRAPPING_DISJOINT_variation1() {
        double datelineGapLongitude = -0.0;
        verifyRotation(170.0, -170.0, -20.0, 20.0, datelineGapLongitude, 0.0);
    }

    @Test
    void NEGATIVE_DATELINE_MERIDIAN_ACCEPTS_POSITIVE_180_variation1() {
        verifyRotation(-180.0, -180.0, -20.0, 20.0, 180.0, 0.0);
    }

    @Test
    void POSITIVE_DATELINE_MERIDIAN_ACCEPTS_NEGATIVE_180_variation1() {
        verifyRotation(180.0, 180.0, -20.0, 20.0, -180.0, 0.0);
    }
}
