import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicFailingTest {

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
    void LATITUDE_MIN_BOUNDARY_v1() {
        runCase(-180.0, 180.0, -30.0, 40.0, 0.0, -30.0);
    }

    @Test
    void SOUTH_POLE_BOUNDARY_v1() {
        runCase(-180.0, 180.0, -90.0, -20.0, 50.0, -90.0);
    }

    @Test
    void FULL_WORLD_INTERIOR_LONGITUDE_v1() {
        runCase(-180.0, 180.0, -30.0, 30.0, 45.0, 0.0);
    }

    @Test
    void IMMEDIATE_LONGITUDE_BELOW_MIN_v1() {
        runCase(-20.0, 30.0, -10.0, 10.0, Math.nextDown(-20.0), 0.0);
    }
}
