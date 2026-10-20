import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointMetamorphicPassingTest {

    private static final SpatialContext GEO = SpatialContext.GEO;

    private static void exercise(RectangleImpl source, Point point) {
        SpatialRelation sourceOutput = source.relate(point);
        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(source, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];
        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void INTERIOR_NON_WRAPPING_CONTAINS_1() {
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, GEO);
        Point point = GEO.makePoint(0.0, 0.0);
        exercise(source, point);
    }

    @Test
    void LOWER_LATITUDE_BOUNDARY_CONTAINS_1() {
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, -30.0, 30.0, GEO);
        Point point = GEO.makePoint(5.0, -30.0);
        exercise(source, point);
    }

    @Test
    void UPPER_LATITUDE_BOUNDARY_CONTAINS_1() {
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, -30.0, 30.0, GEO);
        Point point = GEO.makePoint(5.0, 30.0);
        exercise(source, point);
    }

    @Test
    void LOWER_LATITUDE_DISJOINT_1() {
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, -30.0, 30.0, GEO);
        Point point = GEO.makePoint(0.0, -30.000001);
        exercise(source, point);
    }

    @Test
    void UPPER_LATITUDE_DISJOINT_1() {
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, -30.0, 30.0, GEO);
        Point point = GEO.makePoint(0.0, 30.000001);
        exercise(source, point);
    }

    @Test
    void MIN_LONGITUDE_BOUNDARY_CONTAINS_1() {
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, -10.0, 10.0, GEO);
        Point point = GEO.makePoint(-40.0, 0.0);
        exercise(source, point);
    }

    @Test
    void MAX_LONGITUDE_BOUNDARY_CONTAINS_1() {
        RectangleImpl source = new RectangleImpl(-40.0, 40.0, -10.0, 10.0, GEO);
        Point point = GEO.makePoint(40.0, 0.0);
        exercise(source, point);
    }

    @Test
    void WEST_OUTSIDE_NON_WRAPPING_DISJOINT_1() {
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, GEO);
        Point point = GEO.makePoint(-30.0, 0.0);
        exercise(source, point);
    }

    @Test
    void EAST_OUTSIDE_NON_WRAPPING_DISJOINT_1() {
        RectangleImpl source = new RectangleImpl(-20.0, 20.0, -10.0, 10.0, GEO);
        Point point = GEO.makePoint(30.0, 0.0);
        exercise(source, point);
    }

    @Test
    void DATELINE_INTERVAL_CONTAINS_FROM_WEST_SHIFT_1() {
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, GEO);
        Point point = GEO.makePoint(-175.0, 0.0);
        exercise(source, point);
    }

    @Test
    void DATELINE_INTERVAL_CONTAINS_FROM_EAST_SHIFT_1() {
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, GEO);
        Point point = GEO.makePoint(179.0, 0.0);
        exercise(source, point);
    }

    @Test
    void DATELINE_OUTSIDE_DISJOINT_1() {
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, GEO);
        Point point = GEO.makePoint(0.0, 0.0);
        exercise(source, point);
    }

    @Test
    void DATELINE_ENDPOINT_CONTAINS_1() {
        RectangleImpl source = new RectangleImpl(170.0, -170.0, -20.0, 20.0, GEO);
        Point point = GEO.makePoint(170.0, 0.0);
        exercise(source, point);
    }

    @Test
    void ZERO_WIDTH_LONGITUDE_CONTAINS_1() {
        RectangleImpl source = new RectangleImpl(10.0, 10.0, -20.0, 20.0, GEO);
        Point point = GEO.makePoint(10.0, 0.0);
        exercise(source, point);
    }

    @Test
    void ZERO_WIDTH_LONGITUDE_DISJOINT_1() {
        RectangleImpl source = new RectangleImpl(10.0, 10.0, -20.0, 20.0, GEO);
        Point point = GEO.makePoint(10.000001, 0.0);
        exercise(source, point);
    }

    @Test
    void ZERO_HEIGHT_EQUATOR_LINE_CONTAINS_1() {
        RectangleImpl source = new RectangleImpl(-30.0, 30.0, 0.0, 0.0, GEO);
        Point point = GEO.makePoint(0.0, 0.0);
        exercise(source, point);
    }

    @Test
    void FULL_WORLD_LONGITUDE_LATITUDE_DISJOINT_1() {
        RectangleImpl source = new RectangleImpl(-180.0, 180.0, -45.0, 45.0, GEO);
        Point point = GEO.makePoint(0.0, 45.000001);
        exercise(source, point);
    }

    @Test
    void NEAR_FULL_WIDTH_WRAPPING_CONTAINS_1() {
        RectangleImpl source = new RectangleImpl(-170.0, 170.0, -10.0, 10.0, GEO);
        Point point = GEO.makePoint(179.0, 0.0);
        exercise(source, point);
    }

    @Test
    void NEAR_FULL_WIDTH_WRAPPING_DISJOINT_1() {
        RectangleImpl source = new RectangleImpl(-170.0, 170.0, -10.0, 10.0, GEO);
        Point point = GEO.makePoint(0.0, 0.0);
        exercise(source, point);
    }

    @Test
    void ROTATION_CREATES_DATELINE_WRAPPING_FOLLOWUP_1() {
        RectangleImpl source = new RectangleImpl(-170.0, 170.0, -20.0, 20.0, GEO);
        Point point = GEO.makePoint(0.0, 0.0);
        exercise(source, point);
    }

    @Test
    void EMPTY_RECTANGLE_MINX_NAN_SENTINEL_1() {
        RectangleImpl source = new RectangleImpl(Double.NaN, 10.0, -10.0, 10.0, GEO);
        Point point = GEO.makePoint(0.0, 0.0);
        exercise(source, point);
    }
}
