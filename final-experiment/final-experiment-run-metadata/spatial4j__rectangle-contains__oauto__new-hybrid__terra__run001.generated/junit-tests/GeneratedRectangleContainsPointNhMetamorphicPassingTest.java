import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final double ROTATION_DEGREES = 150.0;

    private static RectangleImpl rectangle(double minX, double maxX, double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, new SpatialContext(true));
    }

    private static Point point(RectangleImpl rectangle, double x, double y) {
        return rectangle.getContext().makePoint(x, y);
    }

    private static double rotateLongitude(double longitude) {
        return DistanceUtils.normLonDEG(longitude + ROTATION_DEGREES);
    }

    private static Object[] generateFollowUp(RectangleImpl sourceRectangle, Point sourcePoint) {
        SpatialContext context = sourceRectangle.getContext();
        RectangleImpl followUpRectangle = new RectangleImpl(
                rotateLongitude(sourceRectangle.getMinX()),
                rotateLongitude(sourceRectangle.getMaxX()),
                sourceRectangle.getMinY(),
                sourceRectangle.getMaxY(),
                context);
        Point followUpPoint = context.makePoint(
                rotateLongitude(sourcePoint.getX()),
                sourcePoint.getY());
        return new Object[]{followUpRectangle, followUpPoint};
    }

    private static void verify(RectangleImpl sourceRectangle, Point sourcePoint) {
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LATITUDE_ABOVE_MAX_REJECTED_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20.0, 20.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 0.0, 10.1));
    }

    @Test
    void LATITUDE_BELOW_MIN_REJECTED_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20.0, 20.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 0.0, -10.1));
    }

    @Test
    void SOUTH_POLE_LATITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(-30.0, 30.0, -90.0, -70.0);
        verify(sourceRectangle, point(sourceRectangle, 0.0, -90.0));
    }

    @Test
    void NORTH_POLE_LATITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(-30.0, 30.0, 70.0, 90.0);
        verify(sourceRectangle, point(sourceRectangle, 0.0, 90.0));
    }

    @Test
    void NONWRAPPING_STRICT_INTERIOR_variation1() {
        RectangleImpl sourceRectangle = rectangle(-40.0, 20.0, -20.0, 20.0);
        verify(sourceRectangle, point(sourceRectangle, -10.0, 0.0));
    }

    @Test
    void NONWRAPPING_MIN_LONGITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(-40.0, 20.0, -20.0, 20.0);
        verify(sourceRectangle, point(sourceRectangle, -40.0, 0.0));
    }

    @Test
    void NONWRAPPING_MAX_LONGITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(-40.0, 20.0, -20.0, 20.0);
        verify(sourceRectangle, point(sourceRectangle, 20.0, 0.0));
    }

    @Test
    void NONWRAPPING_LEFT_SHIFT_DISJOINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(-100.0, -50.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, -120.0, 0.0));
    }

    @Test
    void NONWRAPPING_RIGHT_SHIFT_DISJOINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(-100.0, -50.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, -20.0, 0.0));
    }

    @Test
    void EAST_DATELINE_EQUIVALENT_EDGE_CONTAINS_variation1() {
        RectangleImpl sourceRectangle = rectangle(170.0, 180.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, -180.0, 0.0));
    }

    @Test
    void WEST_DATELINE_EQUIVALENT_EDGE_CONTAINS_variation1() {
        RectangleImpl sourceRectangle = rectangle(-180.0, -170.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 180.0, 0.0));
    }

    @Test
    void WRAPPING_EASTERN_LOBE_INTERIOR_variation1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 175.0, 0.0));
    }

    @Test
    void WRAPPING_WESTERN_LOBE_INTERIOR_variation1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, -175.0, 0.0));
    }

    @Test
    void WRAPPING_MIN_LONGITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 170.0, 0.0));
    }

    @Test
    void WRAPPING_MAX_LONGITUDE_EDGE_variation1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, -170.0, 0.0));
    }

    @Test
    void WRAPPING_CENTRAL_GAP_DISJOINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 0.0, 0.0));
    }

    @Test
    void WRAPPING_OUTSIDE_AFTER_UNWRAP_variation1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, -160.0, 0.0));
    }

    @Test
    void ZERO_WIDTH_VERTICAL_LINE_CONTAINS_variation1() {
        RectangleImpl sourceRectangle = rectangle(25.0, 25.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 25.0, 0.0));
    }

    @Test
    void ZERO_WIDTH_VERTICAL_LINE_DISJOINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(25.0, 25.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 25.1, 0.0));
    }

    @Test
    void DATELINE_ZERO_WIDTH_POSITIVE_180_variation1() {
        RectangleImpl sourceRectangle = rectangle(180.0, -180.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 180.0, 0.0));
    }

    @Test
    void DATELINE_ZERO_WIDTH_NEGATIVE_180_variation1() {
        RectangleImpl sourceRectangle = rectangle(180.0, -180.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, -180.0, 0.0));
    }

    @Test
    void ZERO_HEIGHT_HORIZONTAL_LINE_CONTAINS_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20.0, 20.0, 5.0, 5.0);
        verify(sourceRectangle, point(sourceRectangle, 0.0, 5.0));
    }

    @Test
    void ZERO_HEIGHT_HORIZONTAL_LINE_DISJOINT_variation1() {
        RectangleImpl sourceRectangle = rectangle(-20.0, 20.0, 5.0, 5.0);
        verify(sourceRectangle, point(sourceRectangle, 0.0, 5.000001));
    }

    @Test
    void ROTATION_CHANGES_TO_WRAPPING_REPRESENTATION_variation1() {
        RectangleImpl sourceRectangle = rectangle(20.0, 40.0, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 30.0, 0.0));
    }

    @Test
    void ROTATION_CHANGES_FROM_WRAPPING_REPRESENTATION_variation1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -10.0, 10.0);
        double westernLobeLongitude = -200.0 + 25.0;
        verify(sourceRectangle, point(sourceRectangle, westernLobeLongitude, 0.0));
    }

    @Test
    void NEAR_FULL_WORLD_NONWRAPPING_WIDTH_variation1() {
        RectangleImpl sourceRectangle = rectangle(-180.0, 179.999, -10.0, 10.0);
        verify(sourceRectangle, point(sourceRectangle, 180.0, 0.0));
    }
}
