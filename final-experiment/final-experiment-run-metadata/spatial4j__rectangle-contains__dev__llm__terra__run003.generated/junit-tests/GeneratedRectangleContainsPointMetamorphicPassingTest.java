import org.junit.jupiter.api.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

public class GeneratedRectangleContainsPointMetamorphicPassingTest {

    private void exercise(RectangleImpl rectangle, Point point) {
        SpatialRelation sourceOutput = rectangle.relate(point);

        Object[] followUp = RectangleContainsPointMetamorphicSpec.generateFollowUp(rectangle, point);
        RectangleImpl followUpRectangle = (RectangleImpl) followUp[0];
        Point followUpPoint = (Point) followUp[1];

        SpatialRelation followUpOutput = followUpRectangle.relate(followUpPoint);
        RectangleContainsPointMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void ORDINARY_INTERIOR_CONTAINS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-100.0, -50.0, -20.0, 20.0, context);
        Point point = context.makePoint(-75.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_MIN_X_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-100.0, -50.0, -20.0, 20.0, context);
        Point point = context.makePoint(-100.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_MAX_X_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-100.0, -50.0, -20.0, 20.0, context);
        Point point = context.makePoint(-50.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_MIN_Y_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-100.0, -50.0, -20.0, 20.0, context);
        Point point = context.makePoint(-75.0, -20.0);

        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_MAX_Y_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-100.0, -50.0, -20.0, 20.0, context);
        Point point = context.makePoint(-75.0, 20.0);

        exercise(rectangle, point);
    }

    @Test
    public void LATITUDE_BELOW_MIN_Y_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-100.0, -50.0, -20.0, 20.0, context);
        Point point = context.makePoint(-75.0, -20.000001);

        exercise(rectangle, point);
    }

    @Test
    public void LATITUDE_ABOVE_MAX_Y_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-100.0, -50.0, -20.0, 20.0, context);
        Point point = context.makePoint(-75.0, 20.000001);

        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_LEFT_LONGITUDE_OUTSIDE_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-100.0, -50.0, -20.0, 20.0, context);
        Point point = context.makePoint(-100.000001, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void ORDINARY_RIGHT_LONGITUDE_OUTSIDE_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-100.0, -50.0, -20.0, 20.0, context);
        Point point = context.makePoint(-49.999999, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void SOURCE_NORMAL_FOLLOWUP_WRAPS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(0.0, 60.0, -10.0, 10.0, context);
        Point point = context.makePoint(30.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_RIGHT_LOBE_CONTAINS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point point = context.makePoint(175.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_LEFT_LOBE_CONTAINS_AFTER_SHIFT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point point = context.makePoint(-175.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_GAP_DISJOINT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point point = context.makePoint(0.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_MIN_X_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point point = context.makePoint(170.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_MAX_X_BOUNDARY_AFTER_SHIFT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point point = context.makePoint(-170.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_PLUS_180_CONTAINS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point point = context.makePoint(180.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_MINUS_180_CONTAINS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point point = context.makePoint(-180.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void WRAPPED_SOURCE_FOLLOWUP_UNWRAPS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(170.0, -170.0, -10.0, 10.0, context);
        Point point = context.makePoint(-175.0, 5.0);

        exercise(rectangle, point);
    }

    @Test
    public void WIDE_WRAPPED_INTERVAL_CONTAINS_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-10.0, -20.0, -30.0, 30.0, context);
        Point point = context.makePoint(160.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void WIDE_WRAPPED_INTERVAL_GAP_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-10.0, -20.0, -30.0, 30.0, context);
        Point point = context.makePoint(-15.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void NEAR_WORLD_NONWRAPPING_INTERVAL_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-180.0, 179.0, -10.0, 10.0, context);
        Point point = context.makePoint(0.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_EXACT_POINT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(40.0, 40.0, -10.0, 10.0, context);
        Point point = context.makePoint(40.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void ZERO_WIDTH_LONGITUDE_OFF_LINE_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(40.0, 40.0, -10.0, 10.0, context);
        Point point = context.makePoint(40.000001, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void ZERO_HEIGHT_LATITUDE_EXACT_POINT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-20.0, 20.0, 15.0, 15.0, context);
        Point point = context.makePoint(0.0, 15.0);

        exercise(rectangle, point);
    }

    @Test
    public void ZERO_HEIGHT_LATITUDE_OFF_LINE_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-20.0, 20.0, 15.0, 15.0, context);
        Point point = context.makePoint(0.0, 15.000001);

        exercise(rectangle, point);
    }

    @Test
    public void DEGENERATE_POINT_RECTANGLE_EXACT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(12.0, 12.0, -7.0, -7.0, context);
        Point point = context.makePoint(12.0, -7.0);

        exercise(rectangle, point);
    }

    @Test
    public void NORTH_POLE_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-30.0, 30.0, 80.0, 90.0, context);
        Point point = context.makePoint(0.0, 90.0);

        exercise(rectangle, point);
    }

    @Test
    public void SOUTH_POLE_BOUNDARY_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(-30.0, 30.0, -90.0, -80.0, context);
        Point point = context.makePoint(0.0, -90.0);

        exercise(rectangle, point);
    }

    @Test
    public void ROTATION_MIN_ENDPOINT_AT_180_CUT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(30.0, 60.0, -10.0, 10.0, context);
        Point point = context.makePoint(30.0, 0.0);

        exercise(rectangle, point);
    }

    @Test
    public void ROTATION_MAX_ENDPOINT_AT_180_CUT_variation1() {
        SpatialContext context = SpatialContext.GEO;
        RectangleImpl rectangle = new RectangleImpl(0.0, 30.0, -10.0, 10.0, context);
        Point point = context.makePoint(30.0, 0.0);

        exercise(rectangle, point);
    }
}
