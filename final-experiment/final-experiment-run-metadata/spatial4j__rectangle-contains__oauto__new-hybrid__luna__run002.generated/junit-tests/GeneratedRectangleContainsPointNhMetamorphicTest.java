import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicTest {

    private static final double ROTATION_DEG = 150.0;
    private static final SpatialContext GEO = new SpatialContext(true);

    private static RectangleImpl rectangle(double minX, double maxX,
                                           double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, GEO);
    }

    private static Point point(double x, double y) {
        return new PointImpl(x, y, GEO);
    }

    private static Object[] generateFollowUp(RectangleImpl rectangle,
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

    private static void run(double minX, double maxX, double minY, double maxY,
                            double pointX, double pointY) {
        RectangleImpl source = rectangle(minX, maxX, minY, maxY);
        Point sourcePoint = point(pointX, pointY);

        SpatialRelation sourceOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl
                        .relate(source, sourcePoint);

        Object[] followUp = generateFollowUp(source, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput =
                org.locationtech.spatial4j.shape.impl.RectangleImpl
                        .relate(followUpRectangle, followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(
                sourceOutput, followUpOutput);
    }

    @Test
    void LATITUDE_ABOVE_REJECTED_variation1() {
        run(-40.0, 40.0, -20.0, 20.0, 0.0, 20.000001);
    }

    @Test
    void LATITUDE_ABOVE_REJECTED_variation2() {
        run(170.0, -170.0, -20.0, 20.0, 175.0, 20.000001);
    }

    @Test
    void LATITUDE_BELOW_REJECTED_variation1() {
        run(-30.0, 30.0, -20.0, 20.0, 0.0, -20.000001);
    }

    @Test
    void LATITUDE_BELOW_REJECTED_variation2() {
        run(10.0, 100.0, -20.0, 20.0, 50.0, -20.000001);
    }

    @Test
    void LATITUDE_LOWER_BOUNDARY_CONTAINED_variation1() {
        run(-40.0, 40.0, -20.0, 20.0, 0.0, -20.0);
    }

    @Test
    void LATITUDE_LOWER_BOUNDARY_CONTAINED_variation2() {
        run(180.0, 180.0, -20.0, 20.0, 180.0, -20.0);
    }

    @Test
    void LATITUDE_UPPER_BOUNDARY_CONTAINED_variation1() {
        run(-35.0, 35.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Test
    void LATITUDE_UPPER_BOUNDARY_CONTAINED_variation2() {
        run(170.0, -170.0, -20.0, 20.0, -175.0, 20.0);
    }

    @Test
    void ORDINARY_INTERIOR_CONTAINED_variation1() {
        run(-40.0, 40.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    void ORDINARY_INTERIOR_CONTAINED_variation2() {
        run(10.0, 100.0, -20.0, 20.0, 50.0, 10.0);
    }

    @Test
    void ORDINARY_INTERIOR_CONTAINED_variation3() {
        run(-180.0, -120.0, 0.0, 0.0, -150.0, 0.0);
    }

    @Test
    void ORDINARY_LONGITUDE_GAP_DISJOINT_variation1() {
        run(-40.0, 40.0, -20.0, 20.0, 60.0, 0.0);
    }

    @Test
    void ORDINARY_LONGITUDE_GAP_DISJOINT_variation2() {
        run(10.0, 100.0, -20.0, 20.0, 150.0, 0.0);
    }

    @Test
    void ORDINARY_LONGITUDE_GAP_DISJOINT_variation3() {
        run(160.0, -160.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    void ORDINARY_WEST_GAP_DISJOINT_variation1() {
        run(30.0, 30.0, 0.0, 0.0, 31.0, 0.0);
    }

    @Test
    void ORDINARY_WEST_GAP_DISJOINT_variation2() {
        run(10.0, 100.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    void ORDINARY_WEST_GAP_DISJOINT_variation3() {
        run(-180.0, -120.0, -20.0, 20.0, -179.0, 0.0);
    }

    @Test
    void ORDINARY_LONGITUDE_ENDPOINTS_variation1() {
        run(-40.0, 40.0, -20.0, 20.0, -40.0, 0.0);
    }

    @Test
    void ORDINARY_LONGITUDE_ENDPOINTS_variation2() {
        run(-40.0, 40.0, -20.0, 0.0, 40.0, 0.0);
    }

    @Test
    void DATELINE_INTERIOR_EAST_LOBE_variation1() {
        run(170.0, -170.0, -20.0, 20.0, 175.0, 0.0);
    }

    @Test
    void DATELINE_INTERIOR_EAST_LOBE_variation2() {
        run(160.0, -160.0, -20.0, 20.0, 165.0, 10.0);
    }

    @Test
    void DATELINE_INTERIOR_EAST_LOBE_variation3() {
        run(170.0, -170.0, -20.0, 20.0, 180.0, -10.0);
    }

    @Test
    void DATELINE_INTERIOR_WEST_LOBE_variation1() {
        run(170.0, -170.0, -20.0, 20.0, -175.0, 0.0);
    }

    @Test
    void DATELINE_INTERIOR_WEST_LOBE_variation2() {
        run(170.0, -170.0, -20.0, 20.0, -180.0, 10.0);
    }

    @Test
    void DATELINE_INTERIOR_WEST_LOBE_variation3() {
        run(170.0, -170.0, -20.0, 20.0, -170.0, -10.0);
    }

    @Test
    void DATELINE_GAP_DISJOINT_variation1() {
        run(170.0, -170.0, -20.0, 20.0, 0.0, 0.0);
    }

    @Test
    void DATELINE_GAP_DISJOINT_variation2() {
        run(160.0, -160.0, -20.0, 20.0, 30.0, 5.0);
    }

    @Test
    void DATELINE_GAP_DISJOINT_variation3() {
        run(150.0, -150.0, -20.0, 20.0, 100.0, -5.0);
    }

    @Test
    void DATELINE_LONGITUDE_ENDPOINTS_variation1() {
        run(170.0, -170.0, -20.0, 20.0, 170.0, 0.0);
    }

    @Test
    void DATELINE_LONGITUDE_ENDPOINTS_variation2() {
        run(170.0, -170.0, -20.0, 20.0, -170.0, 0.0);
    }

    @Test
    void DATELINE_LATITUDE_REJECTION_variation1() {
        run(170.0, -170.0, -20.0, 20.0, 175.0, -20.000001);
    }

    @Test
    void DATELINE_LATITUDE_REJECTION_variation2() {
        run(170.0, -170.0, -20.0, 20.0, -175.0, 20.000001);
    }

    @Test
    void ROTATION_CREATES_DATELINE_WRAP_variation1() {
        run(10.0, 100.0, -20.0, 20.0, 50.0, 0.0);
    }

    @Test
    void ROTATION_CREATES_DATELINE_WRAP_variation2() {
        run(30.0, 120.0, -20.0, 20.0, 75.0, 20.0);
    }

    @Test
    void ROTATION_CREATES_DATELINE_WRAP_variation3() {
        run(-180.0, -120.0, 0.0, 0.0, -150.0, 0.0);
    }

    @Test
    void ROTATION_REMOVES_DATELINE_WRAP_variation1() {
        run(160.0, -160.0, -20.0, 20.0, -170.0, 0.0);
    }

    @Test
    void ROTATION_REMOVES_DATELINE_WRAP_variation2() {
        run(150.0, -150.0, -20.0, 20.0, 170.0, -10.0);
    }

    @Test
    void ZERO_WIDTH_LONGITUDE_CONTAINED_variation1() {
        run(30.0, 30.0, -20.0, 20.0, 30.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_LONGITUDE_CONTAINED_variation2() {
        run(-180.0, -180.0, -20.0, 20.0, -180.0, 10.0);
    }

    @Test
    void ZERO_WIDTH_LONGITUDE_DISJOINT_variation1() {
        run(31.0, 31.0, -20.0, 20.0, 32.0, 0.0);
    }

    @Test
    void ZERO_WIDTH_LONGITUDE_DISJOINT_variation2() {
        run(-180.0, -180.0, -20.0, 20.0, 180.0, 0.0);
    }

    @Test
    void ZERO_HEIGHT_LONGITUDE_INTERIOR_variation1() {
        run(180.0, 180.0, 0.0, 0.0, 180.0, 0.0);
    }

    @Test
    void ZERO_HEIGHT_LONGITUDE_INTERIOR_variation2() {
        run(-40.0, 40.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Test
    void ZERO_HEIGHT_LATITUDE_OUTSIDE_variation1() {
        run(170.0, -170.0, 0.0, 0.0, 175.0, 0.000001);
    }

    @Test
    void ZERO_HEIGHT_LATITUDE_OUTSIDE_variation2() {
        run(-40.0, 40.0, 0.0, 0.0, 0.0, -0.000001);
    }

    @Test
    void DATELINE_NEAR_POLE_BOUNDARY_variation1() {
        run(170.0, -170.0, 89.0, 90.0, -175.0, 90.0);
    }

    @Test
    void DATELINE_NEAR_POLE_BOUNDARY_variation2() {
        run(-180.0, -120.0, 89.0, 90.0, -180.0, 89.0);
    }

    @Test
    void DATELINE_NEAR_SOUTH_POLE_BOUNDARY_variation1() {
        run(170.0, -170.0, -90.0, -89.0, 175.0, -90.0);
    }

    @Test
    void DATELINE_NEAR_SOUTH_POLE_BOUNDARY_variation2() {
        run(-40.0, 40.0, -90.0, -89.0, 0.0, -89.0);
    }
}
