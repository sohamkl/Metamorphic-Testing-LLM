import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointNhMetamorphicTest {

    private static final SpatialContext GEO = new SpatialContext(true);

    private static RectangleImpl rectangle(double minX, double maxX,
                                           double minY, double maxY) {
        return new RectangleImpl(minX, maxX, minY, maxY, GEO);
    }

    private static Point point(double x, double y) {
        return GEO.makePoint(x, y);
    }

    private static void assertMetamorphicRelation(
            SpatialRelation sourceOutput,
            SpatialRelation followUpOutput) {
        if (sourceOutput != followUpOutput) {
            throw new AssertionError(
                    "The point/rectangle relation changed under the developer transformation: "
                            + sourceOutput + " -> " + followUpOutput);
        }
    }

    private static void exercise(RectangleImpl sourceRectangle, Point sourcePoint) {
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(
                sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        assertMetamorphicRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void LATITUDE_BELOW_NONWRAPPING_1() {
        RectangleImpl sourceRectangle = rectangle(-20.0, 40.0, 10.0, 30.0);
        Point sourcePoint = point(0.0, 5.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void LATITUDE_ABOVE_NONWRAPPING_1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -30.0, -10.0);
        Point sourcePoint = point(170.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void LATITUDE_AT_MINIMUM_1() {
        RectangleImpl sourceRectangle = rectangle(-180.0, 180.0, -30.0, 30.0);
        Point sourcePoint = point(0.0, -30.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void LATITUDE_AT_MAXIMUM_1() {
        RectangleImpl sourceRectangle = rectangle(30.0, 30.0, -30.0, 30.0);
        Point sourcePoint = point(30.0, 30.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void ORDINARY_INTERIOR_SHORT_CIRCUIT_1() {
        RectangleImpl sourceRectangle = rectangle(-20.0, 40.0, -30.0, 30.0);
        Point sourcePoint = point(0.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void ORDINARY_POINT_LEFT_1() {
        RectangleImpl sourceRectangle = rectangle(-20.0, 40.0, -30.0, 30.0);
        Point sourcePoint = point(-30.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void ORDINARY_POINT_RIGHT_1() {
        RectangleImpl sourceRectangle = rectangle(-180.0, 180.0, -30.0, 30.0);
        Point sourcePoint = point(179.0, -30.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void ORDINARY_MIN_LONGITUDE_BOUNDARY_1() {
        RectangleImpl sourceRectangle = rectangle(-20.0, 40.0, -30.0, 30.0);
        Point sourcePoint = point(-20.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void ORDINARY_MAX_LONGITUDE_BOUNDARY_1() {
        RectangleImpl sourceRectangle = rectangle(-20.0, 40.0, -30.0, 30.0);
        Point sourcePoint = point(40.0, 30.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void NONWRAPPING_INTERVAL_BECOMES_DATELINE_WRAPPING_1() {
        RectangleImpl sourceRectangle = rectangle(-20.0, 40.0, -30.0, 30.0);
        Point sourcePoint = point(0.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void WRAPPED_INTERIOR_EAST_SIDE_1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -20.0, 20.0);
        Point sourcePoint = point(180.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void WRAPPED_INTERIOR_WEST_SIDE_1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -20.0, 20.0);
        Point sourcePoint = point(-180.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void WRAPPED_GAP_DISJOINT_1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -20.0, 20.0);
        Point sourcePoint = point(0.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void WRAPPED_MIN_ENDPOINT_SOURCE_EDGE_1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -20.0, 20.0);
        Point sourcePoint = point(170.0, 20.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void WRAPPED_MAX_ENDPOINT_1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -20.0, 20.0);
        Point sourcePoint = point(-170.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_INTERIOR_LATITUDE_1() {
        RectangleImpl sourceRectangle = rectangle(30.0, 30.0, -20.0, 20.0);
        Point sourcePoint = point(30.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_OUTSIDE_1() {
        RectangleImpl sourceRectangle = rectangle(30.0, 30.0, -20.0, 20.0);
        Point sourcePoint = point(31.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void FULL_WORLD_LONGITUDE_1() {
        RectangleImpl sourceRectangle = rectangle(-180.0, 180.0, -45.0, 45.0);
        Point sourcePoint = point(0.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void NEAR_FULL_WIDTH_WRAPPED_INTERVAL_1() {
        RectangleImpl sourceRectangle = rectangle(179.0, 178.0, -10.0, 10.0);
        Point sourcePoint = point(0.0, 0.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void DATELINE_LATITUDE_BOUNDARY_COMBINATION_1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -20.0, 20.0);
        Point sourcePoint = point(180.0, 20.0);
        exercise(sourceRectangle, sourcePoint);
    }

    @Test
    public void DATELINE_LATITUDE_OUTSIDE_COMBINATION_1() {
        RectangleImpl sourceRectangle = rectangle(170.0, -170.0, -20.0, 20.0);
        Point sourcePoint = point(180.0, 20.0000001);
        exercise(sourceRectangle, sourcePoint);
    }
}
