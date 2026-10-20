import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointMetamorphicPassingTest {

    private static final SpatialContext GEO = SpatialContext.GEO;

    private void runCase(RectangleImpl sourceRectangle, Point sourcePoint) {
        SpatialRelation sourceOutput = sourceRectangle.relate(sourcePoint);

        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(
                sourceRectangle, sourcePoint);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);

        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    void LATITUDE_ABOVE_MAX_EARLY_DISJOINT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(30.0, 11.0));
    }

    @Test
    void LATITUDE_BELOW_MIN_EARLY_DISJOINT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(30.0, -11.0));
    }

    @Test
    void ROTATES_TO_DATELINE_WRAPPING_INTERIOR_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(30.0, 0.0));
    }

    @Test
    void ROTATES_TO_DATELINE_WRAPPING_MIN_Y_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(30.0, -10.0));
    }

    @Test
    void ROTATES_TO_DATELINE_WRAPPING_MAX_Y_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(30.0, 10.0));
    }

    @Test
    void ROTATES_TO_DATELINE_WRAPPING_MIN_X_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(20.0, 0.0));
    }

    @Test
    void ROTATES_TO_DATELINE_WRAPPING_MAX_X_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(40.0, 0.0));
    }

    @Test
    void ROTATES_TO_DATELINE_WRAPPING_LEFT_OUTSIDE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(10.0, 0.0));
    }

    @Test
    void ROTATES_TO_DATELINE_WRAPPING_RIGHT_OUTSIDE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(20.0, 40.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(50.0, 0.0));
    }

    @Test
    void ORDINARY_INTERVAL_INTERIOR_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-100.0, -80.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-90.0, 0.0));
    }

    @Test
    void ORDINARY_INTERVAL_MIN_X_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-100.0, -80.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-100.0, 0.0));
    }

    @Test
    void ORDINARY_INTERVAL_MAX_X_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-100.0, -80.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-80.0, 0.0));
    }

    @Test
    void ORDINARY_INTERVAL_LEFT_SHIFT_DISJOINT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-100.0, -80.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-101.0, 0.0));
    }

    @Test
    void ORDINARY_INTERVAL_RIGHT_SHIFT_DISJOINT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-100.0, -80.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-79.0, 0.0));
    }

    @Test
    void DATELINE_WRAP_DIRECT_EAST_ARM_CONTAINS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(175.0, 0.0));
    }

    @Test
    void DATELINE_WRAP_SHIFTED_WEST_ARM_CONTAINS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-175.0, 0.0));
    }

    @Test
    void DATELINE_WRAP_SHIFTED_WEST_ARM_DISJOINT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-160.0, 0.0));
    }

    @Test
    void DATELINE_WRAP_MIN_Y_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-175.0, -10.0));
    }

    @Test
    void DATELINE_WRAP_MAX_Y_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-175.0, 10.0));
    }

    @Test
    void DATELINE_WRAP_NEGATIVE_ANTIMERIDIAN_CONTAINS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-180.0, 0.0));
    }

    @Test
    void DATELINE_WRAP_POSITIVE_ANTIMERIDIAN_CONTAINS_variation1() {
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(180.0, 0.0));
    }

    @Test
    void ZERO_WIDTH_RECTANGLE_EQUAL_POINT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(30.0, 30.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(30.0, 0.0));
    }

    @Test
    void ZERO_WIDTH_RECTANGLE_LEFT_POINT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(30.0, 30.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(29.0, 0.0));
    }

    @Test
    void ZERO_WIDTH_RECTANGLE_RIGHT_POINT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(30.0, 30.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(31.0, 0.0));
    }

    @Test
    void ZERO_HEIGHT_RECTANGLE_ON_LINE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-20.0, 20.0, 0.0, 0.0, GEO);
        runCase(rectangle, GEO.makePoint(0.0, 0.0));
    }

    @Test
    void SOUTH_POLE_LATITUDE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-100.0, 100.0, -90.0, 20.0, GEO);
        runCase(rectangle, GEO.makePoint(0.0, -90.0));
    }

    @Test
    void NORTH_POLE_LATITUDE_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-100.0, 100.0, -20.0, 90.0, GEO);
        runCase(rectangle, GEO.makePoint(0.0, 90.0));
    }

    @Test
    void NEGATIVE_ANTIMERIDIAN_ORDINARY_INTERVAL_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(-180.0, -170.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-180.0, 0.0));
    }

    @Test
    void POSITIVE_ANTIMERIDIAN_ORDINARY_INTERVAL_EDGE_variation1() {
        RectangleImpl rectangle = new RectangleImpl(170.0, 180.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(180.0, 0.0));
    }

    @Test
    void REVERSED_ANTIMERIDIAN_ZERO_WIDTH_DIRECT_variation1() {
        RectangleImpl rectangle = new RectangleImpl(180.0, -180.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(180.0, 0.0));
    }

    @Test
    void REVERSED_ANTIMERIDIAN_ZERO_WIDTH_SHIFTED_variation1() {
        RectangleImpl rectangle = new RectangleImpl(180.0, -180.0, -10.0, 10.0, GEO);
        runCase(rectangle, GEO.makePoint(-180.0, 0.0));
    }
}
