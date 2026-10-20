import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicPassingTest {

    private static final SpatialContext GEO = new SpatialContext(true);
    private static final double ROTATION_DEG = 150.0;

    private static void runCase(
            double minX,
            double maxX,
            double minY,
            double maxY,
            double pointX,
            double pointY) {

        RectangleImpl sourceRectangle =
                new RectangleImpl(minX, maxX, minY, maxY, GEO);
        Point sourcePoint = GEO.makePoint(pointX, pointY);

        SpatialRelation sourceOutput =
                ((org.locationtech.spatial4j.shape.impl.RectangleImpl) sourceRectangle)
                        .relate(sourcePoint);

        Object[] followUp = generateFollowUp(sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput =
                ((org.locationtech.spatial4j.shape.impl.RectangleImpl) followUpRectangle)
                        .relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    private static Object[] generateFollowUp(
            RectangleImpl rectangle,
            Point point) {

        SpatialContext context = rectangle.getContext();

        RectangleImpl rotatedRectangle = new RectangleImpl(
                DistanceUtils.normLonDEG(rectangle.getMinX() + ROTATION_DEG),
                DistanceUtils.normLonDEG(rectangle.getMaxX() + ROTATION_DEG),
                rectangle.getMinY(),
                rectangle.getMaxY(),
                context);

        Point rotatedPoint = context.makePoint(
                DistanceUtils.normLonDEG(point.getX() + ROTATION_DEG),
                point.getY());

        return new Object[]{rotatedRectangle, rotatedPoint};
    }

    @Test
    void LATITUDE_STRICTLY_ABOVE_v1() {
        runCase(-100.0, -50.0, -10.0, 20.0, 0.0, 21.0);
    }

    @Test
    void LATITUDE_STRICTLY_BELOW_v1() {
        runCase(160.0, -160.0, -20.0, 20.0, 170.0, -21.0);
    }

    @Test
    void LATITUDE_MAX_BOUNDARY_v1() {
        runCase(25.0, 25.0, -10.0, 20.0, 25.0, 20.0);
    }

    @Test
    void LATITUDE_STRICT_INTERIOR_v1() {
        runCase(-20.0, 40.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void ZERO_HEIGHT_LATITUDE_HIT_v1() {
        runCase(-100.0, -20.0, 5.0, 5.0, -50.0, 5.0);
    }

    @Test
    void NORTH_POLE_BOUNDARY_v1() {
        runCase(150.0, -150.0, 0.0, 90.0, 170.0, 90.0);
    }

    @Test
    void ORDINARY_LONGITUDE_INTERIOR_v1() {
        runCase(-30.0, 30.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void ORDINARY_MIN_X_BOUNDARY_v1() {
        runCase(-40.0, 20.0, -15.0, 15.0, -40.0, 0.0);
    }

    @Test
    void ORDINARY_MAX_X_BOUNDARY_v1() {
        runCase(-20.0, 40.0, -15.0, 15.0, 40.0, 15.0);
    }

    @Test
    void ORDINARY_LEFT_DISJOINT_v1() {
        runCase(-20.0, 30.0, -10.0, 10.0, -100.0, 0.0);
    }

    @Test
    void ORDINARY_RIGHT_DISJOINT_v1() {
        runCase(-30.0, 20.0, -10.0, 10.0, 100.0, 0.0);
    }

    @Test
    void POSITIVE_DATELINE_ALIAS_TO_NEGATIVE_POINT_v1() {
        runCase(100.0, 180.0, -20.0, 20.0, -180.0, 0.0);
    }

    @Test
    void NEGATIVE_DATELINE_ALIAS_TO_POSITIVE_POINT_v1() {
        runCase(-180.0, -100.0, -20.0, 20.0, 180.0, 0.0);
    }

    @Test
    void WRAPPED_EAST_SEGMENT_INTERIOR_v1() {
        runCase(140.0, -150.0, -20.0, 20.0, 160.0, 0.0);
    }

    @Test
    void WRAPPED_WEST_SEGMENT_INTERIOR_v1() {
        runCase(140.0, -150.0, -20.0, 20.0, -170.0, 0.0);
    }

    @Test
    void WRAPPED_MIN_X_BOUNDARY_v1() {
        runCase(140.0, -150.0, -20.0, 20.0, 140.0, 0.0);
    }

    @Test
    void WRAPPED_MAX_X_BOUNDARY_v1() {
        runCase(140.0, -150.0, -20.0, 20.0, -150.0, 0.0);
    }

    @Test
    void WRAPPED_GAP_INTERIOR_v1() {
        runCase(140.0, -150.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    void WRAPPED_POSITIVE_180_v1() {
        runCase(140.0, -150.0, -20.0, 20.0, 180.0, 0.0);
    }

    @Test
    void WRAPPED_NEGATIVE_180_v1() {
        runCase(140.0, -150.0, -20.0, 20.0, -180.0, 0.0);
    }

    @Test
    void FULL_WORLD_NEGATIVE_180_v1() {
        runCase(-180.0, 180.0, -30.0, 30.0, -180.0, 0.0);
    }

    @Test
    void FULL_WORLD_POSITIVE_180_v1() {
        runCase(-180.0, 180.0, -30.0, 30.0, 180.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_ORDINARY_HIT_v1() {
        runCase(25.0, 25.0, -10.0, 10.0, 25.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_ORDINARY_MISS_v1() {
        runCase(25.0, 25.0, -10.0, 10.0, 26.0, 0.0);
    }

    @Test
    void VERTICAL_POSITIVE_DATELINE_ALIAS_v1() {
        runCase(180.0, 180.0, -10.0, 10.0, -180.0, 0.0);
    }

    @Test
    void VERTICAL_NEGATIVE_DATELINE_ALIAS_v1() {
        runCase(-180.0, -180.0, -10.0, 10.0, 180.0, 0.0);
    }

    @Test
    void POINT_RECTANGLE_EXACT_HIT_v1() {
        runCase(35.0, 35.0, 12.0, 12.0, 35.0, 12.0);
    }

    @Test
    void POINT_RECTANGLE_LATITUDE_MISS_v1() {
        runCase(35.0, 35.0, 12.0, 12.0, 35.0, 13.0);
    }

    @Test
    void ROTATION_ORDINARY_NO_ENDPOINT_WRAP_CONTAINS_v1() {
        runCase(-100.0, 20.0, -25.0, 25.0, -40.0, 0.0);
    }

    @Test
    void ROTATION_ORDINARY_NO_ENDPOINT_WRAP_DISJOINT_v1() {
        runCase(-100.0, 20.0, -25.0, 25.0, 80.0, 0.0);
    }

    @Test
    void ROTATION_ORDINARY_BOTH_ENDPOINTS_WRAP_CONTAINS_v1() {
        runCase(40.0, 100.0, -25.0, 25.0, 70.0, 0.0);
    }

    @Test
    void ROTATION_ORDINARY_BOTH_ENDPOINTS_WRAP_DISJOINT_v1() {
        runCase(40.0, 100.0, -25.0, 25.0, 0.0, 0.0);
    }

    @Test
    void ROTATION_ORDINARY_TO_WRAPPED_CONTAINS_v1() {
        runCase(-20.0, 80.0, -25.0, 25.0, 30.0, 0.0);
    }

    @Test
    void ROTATION_ORDINARY_TO_WRAPPED_DISJOINT_v1() {
        runCase(-20.0, 80.0, -25.0, 25.0, 120.0, 0.0);
    }

    @Test
    void ROTATION_WRAPPED_TO_ORDINARY_CONTAINS_v1() {
        runCase(100.0, -40.0, -25.0, 25.0, 150.0, 0.0);
    }

    @Test
    void ROTATION_WRAPPED_TO_ORDINARY_DISJOINT_v1() {
        runCase(100.0, -40.0, -25.0, 25.0, 20.0, 0.0);
    }

    @Test
    void ROTATION_WRAPPED_NO_ENDPOINT_WRAP_CONTAINS_v1() {
        runCase(20.0, -100.0, -25.0, 25.0, 100.0, 0.0);
    }

    @Test
    void ROTATION_WRAPPED_NO_ENDPOINT_WRAP_DISJOINT_v1() {
        runCase(20.0, -100.0, -25.0, 25.0, 0.0, 0.0);
    }

    @Test
    void ROTATION_WRAPPED_BOTH_ENDPOINTS_WRAP_CONTAINS_v1() {
        runCase(150.0, 60.0, -25.0, 25.0, 170.0, 0.0);
    }

    @Test
    void ROTATION_WRAPPED_BOTH_ENDPOINTS_WRAP_DISJOINT_v1() {
        runCase(150.0, 60.0, -25.0, 25.0, 100.0, 0.0);
    }

    @Test
    void EMPTY_SENTINEL_LATITUDE_INSIDE_v1() {
        runCase(Double.NaN, 50.0, -10.0, 10.0, 0.0, 0.0);
    }

    @Test
    void EMPTY_SENTINEL_LATITUDE_OUTSIDE_v1() {
        runCase(Double.NaN, 50.0, -10.0, 10.0, 0.0, 20.0);
    }

    @Test
    void IMMEDIATE_LATITUDE_ABOVE_MAX_v1() {
        runCase(140.0, -150.0, -10.0, 20.0, 160.0, Math.nextUp(20.0));
    }
}
